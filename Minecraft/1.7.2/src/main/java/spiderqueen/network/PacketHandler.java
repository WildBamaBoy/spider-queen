/*******************************************************************************
 * PacketHandler.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.network;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.forge.PlayerExtension;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.entity.EntityWebslinger;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.inventory.Inventory;

import com.radixshock.radixcore.core.IEnforcedCore;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.network.AbstractPacketHandler;
import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.relauncher.Side;

/**
 * SpiderQueen's packet handler.
 */
public final class PacketHandler extends AbstractPacketHandler
{
	/**
	 * Constructor
	 * 
	 * @param mod
	 *            The owner mod.
	 */
	public PacketHandler(IEnforcedCore mod)
	{
		super(mod);
	}

	@Override
	public void onHandlePacket(Packet packet, EntityPlayer player, Side side)
	{
		final EnumPacketType type = (EnumPacketType) packet.packetType;

		try
		{
			switch (type)
			{
				case GetInventory:
					handleGetInventory(packet.arguments, player);
					break;

				case SetInventory:
					handleSetInventory(packet.arguments, player);
					break;

				case SetEaten:
					handleSetEaten(packet.arguments, player);
					break;

				case SetLevel:
					handleSetLevel(packet.arguments, player);
					break;

				case SetPlayerMotion:
					handleSetPlayerMotion(packet.arguments, player);
					break;

				case SetDistance:
					handleSetDistance(packet.arguments, player);
					break;

				case DestroySlinger:
					handleDestroySlinger(packet.arguments, player);
					break;

				case CreateClientExplosion:
					handleCreateClientExplosion(packet.arguments, player);
					break;

				case SwingArm:
					handleSwingArm(packet.arguments, player);
					break;

				case CreateParticle:
					handleCreateParticle(packet.arguments, player);
					break;

				default:
					SpiderQueen.getInstance().getLogger().log("WARNING: DEFAULTED PACKET TYPE - " + packet.packetType.toString());
			}
		}

		catch (final Exception e)
		{
			SpiderQueen.getInstance().getLogger().log(e);
		}
	}

	private void handleGetInventory(Object[] arguments, EntityPlayer player)
	{
		final int entityId = (Integer) arguments[0];
		final EntityLivingBase entity = (EntityLivingBase) player.worldObj.getEntityByID(entityId);

		if (entity instanceof EntityFakePlayer)
		{
			final EntityFakePlayer fakePlayer = (EntityFakePlayer) entity;
			mod.getPacketPipeline().sendPacketToPlayer(new Packet(EnumPacketType.SetInventory, entityId, fakePlayer.inventory), (EntityPlayerMP) player);
		}

		else if (entity instanceof EntityHatchedSpider)
		{
			final EntityHatchedSpider hatchedSpider = (EntityHatchedSpider) entity;
			mod.getPacketPipeline().sendPacketToPlayer(new Packet(EnumPacketType.SetInventory, entityId, hatchedSpider.inventory), (EntityPlayerMP) player);
		}
	}

	private void handleSetInventory(Object[] arguments, EntityPlayer player)
	{
		final int entityId = (Integer) arguments[0];
		final Inventory inventory = (Inventory) arguments[1];

		final EntityLivingBase entity = (EntityLivingBase) player.worldObj.getEntityByID(entityId);
		inventory.owner = entity;

		if (entity instanceof EntityFakePlayer)
		{
			final EntityFakePlayer fakePlayer = (EntityFakePlayer) entity;
			fakePlayer.inventory = inventory;
			fakePlayer.inventory.setWornArmorItems();
		}

		else if (entity instanceof EntityHatchedSpider)
		{
			final EntityHatchedSpider hatchedSpider = (EntityHatchedSpider) entity;
			hatchedSpider.inventory = inventory;
		}

		if (!player.worldObj.isRemote)
		{
			mod.getPacketPipeline().sendPacketToAllPlayersExcept(new Packet(EnumPacketType.SetInventory, entityId, inventory), (EntityPlayerMP) player);
		}

		else
		{
			inventory.setWornArmorItems();
		}
	}

	private void handleSetEaten(Object[] arguments, EntityPlayer player)
	{
		final int entityId = (Integer) arguments[0];
		final EntityLivingBase entity = (EntityLivingBase) player.worldObj.getEntityByID(entityId);

		if (entity instanceof EntityCocoon)
		{
			final EntityCocoon cocoon = (EntityCocoon) entity;
			cocoon.worldObj.spawnParticle("largesmoke", cocoon.posX, cocoon.posY + 2, cocoon.posZ, cocoon.motionX, cocoon.motionY, cocoon.motionZ);
			cocoon.worldObj.spawnParticle("largesmoke", cocoon.posX, cocoon.posY + 2, cocoon.posZ, cocoon.motionX, cocoon.motionY, cocoon.motionZ);
			cocoon.setEaten(true);
		}
	}

	private void handleSetLevel(Object[] arguments, EntityPlayer player)
	{
		final int entityId = (Integer) arguments[0];
		final int level = (Integer) arguments[1];
		final EntityHatchedSpider spider = (EntityHatchedSpider) player.worldObj.getEntityByID(entityId);

		if (spider != null)
		{
			final Random rand = new Random();

			for (int i = 0; i < 16; i++)
			{
				spider.worldObj.spawnParticle("smoke", spider.posX + (rand.nextDouble() - 0.5D) * spider.width, spider.posY + 0.5D + rand.nextDouble() * 0.25D, spider.posZ + rand.nextDouble() - 0.5D * spider.width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
			}

			spider.level = level;
			spider.setHitboxSize();
		}
	}

	private void handleSetPlayerMotion(Object[] arguments, EntityPlayer player)
	{
		final String playerName = (String) arguments[0];
		final double motionX = (Double) arguments[1];
		final double motionY = (Double) arguments[2];
		final double motionZ = (Double) arguments[3];

		for (final Object obj : player.worldObj.loadedEntityList)
		{
			if (obj instanceof EntityPlayer)
			{
				final EntityPlayer clientPlayer = (EntityPlayer) obj;

				if (clientPlayer.getCommandSenderName().equals(playerName))
				{
					clientPlayer.setVelocity(motionX, motionY, motionZ);
				}
			}
		}
	}

	private void handleSetDistance(Object[] arguments, EntityPlayer player)
	{
		final double distance = (Double) arguments[0];
		final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);

		playerExtension.webEntity.distance = distance;
	}

	private void handleDestroySlinger(Object[] arguments, EntityPlayer player)
	{
		final int slingerId = (Integer) arguments[0];
		final EntityWebslinger webslinger = (EntityWebslinger) player.worldObj.getEntityByID(slingerId);
		final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);

		playerExtension.webEntity.player = null;
		playerExtension.webEntity = null;

		if (webslinger != null)
		{
			webslinger.setDead();
		}

		SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetPlayerMotion, player.getCommandSenderName(), player.motionX, player.motionY, player.motionZ));
	}

	private void handleCreateClientExplosion(Object[] arguments, EntityPlayer player)
	{
		final double posX = (Double) arguments[0];
		final double posY = (Double) arguments[1];
		final double posZ = (Double) arguments[2];
		final float radius = (Float) arguments[3];
		final boolean doGreifing = (Boolean) arguments[4];

		player.worldObj.createExplosion(null, posX, posY, posZ, radius, doGreifing);
		player.worldObj.playSound(posX, posY, posZ, "random.explode", 4.0F, (1.0F + (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.2F) * 0.7F, true);

		for (int i = 0; i < 10; i++)
		{
			final int modX = LogicHelper.getNumberInRange(0, 3);
			final int modY = LogicHelper.getNumberInRange(0, 3);
			final int modZ = LogicHelper.getNumberInRange(0, 3);
			player.worldObj.spawnParticle("largeexplode", posX + modX, posY + modY, posZ + modZ, 1.0D, 0.0D, 0.0D);
		}
	}

	private void handleSwingArm(Object[] arguments, EntityPlayer player)
	{
		final int entityId = (Integer) arguments[0];
		final EntityFakePlayer entity = (EntityFakePlayer) player.worldObj.getEntityByID(entityId);

		if (entity != null)
		{
			entity.swingItem();
		}
	}

	private void handleCreateParticle(Object[] arguments, EntityPlayer player)
	{
		final String particleName = arguments[0].toString();
		final double posX = (Double) arguments[1];
		final double posY = (Double) arguments[2];
		final double posZ = (Double) arguments[3];
		final double velX = (Double) arguments[4];
		final double velY = (Double) arguments[5];
		final double velZ = (Double) arguments[6];

		player.worldObj.spawnParticle(particleName, posX, posY, posZ, velX, velY, velZ);
	}
}
