/*******************************************************************************
 * PacketHandler.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.network;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.inventory.Inventory;

import com.radixshock.radixcore.core.IEnforcedCore;
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
	 * @param 	mod	The owner mod.
	 */
	public PacketHandler(IEnforcedCore mod) 
	{
		super(mod);
	}

	@Override
	public void onHandlePacket(Packet packet, EntityPlayer player, Side side) 
	{
		EnumPacketType type = (EnumPacketType)packet.packetType;

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

			default:
				SpiderQueen.getInstance().getLogger().log("WARNING: DEFAULTED PACKET TYPE - " + packet.packetType.toString());
			}
		}

		catch (Exception e)
		{
			SpiderQueen.getInstance().getLogger().log(e);
		}
	}

	private void handleGetInventory(Object[] arguments, EntityPlayer player)
	{
		final int entityId = (Integer)arguments[0];
		final EntityLivingBase entity = (EntityLivingBase)player.worldObj.getEntityByID(entityId);

		if (entity instanceof EntityFakePlayer)
		{
			final EntityFakePlayer fakePlayer = (EntityFakePlayer)entity;
			mod.getPacketPipeline().sendPacketToPlayer(new Packet(EnumPacketType.SetInventory, entityId, fakePlayer.inventory), (EntityPlayerMP) player);
		}

		else if (entity instanceof EntityHatchedSpider)
		{
			final EntityHatchedSpider hatchedSpider = (EntityHatchedSpider)entity;
			mod.getPacketPipeline().sendPacketToPlayer(new Packet(EnumPacketType.SetInventory, entityId, hatchedSpider.inventory), (EntityPlayerMP) player);
		}
	}

	private void handleSetInventory(Object[] arguments, EntityPlayer player) 
	{
		final int entityId = (Integer)arguments[0];
		final Inventory inventory = (Inventory)arguments[1];

		final EntityLivingBase entity = (EntityLivingBase)player.worldObj.getEntityByID(entityId);
		inventory.owner = entity;

		if (entity instanceof EntityFakePlayer)
		{
			final EntityFakePlayer fakePlayer = (EntityFakePlayer)entity;
			fakePlayer.inventory = inventory;
			fakePlayer.inventory.setWornArmorItems();
		}

		else if (entity instanceof EntityHatchedSpider)
		{
			final EntityHatchedSpider hatchedSpider = (EntityHatchedSpider)entity;
			hatchedSpider.inventory = inventory;
		}

		if (!player.worldObj.isRemote)
		{
			mod.getPacketPipeline().sendPacketToAllPlayersExcept(new Packet(EnumPacketType.SetInventory, entityId, inventory), (EntityPlayerMP)player);
		}

		else
		{
			inventory.setWornArmorItems();
		}
	}

	private void handleSetEaten(Object[] arguments, EntityPlayer player)
	{
		final int entityId = (Integer)arguments[0];
		final EntityLivingBase entity = (EntityLivingBase)player.worldObj.getEntityByID(entityId);

		if (entity instanceof EntityCocoon)
		{
			final EntityCocoon cocoon = (EntityCocoon)entity;
			cocoon.worldObj.spawnParticle("largesmoke", cocoon.posX, cocoon.posY + 2, cocoon.posZ, cocoon.motionX, cocoon.motionY, cocoon.motionZ);
			cocoon.worldObj.spawnParticle("largesmoke", cocoon.posX, cocoon.posY + 2, cocoon.posZ, cocoon.motionX, cocoon.motionY, cocoon.motionZ);
			cocoon.setEaten(true);
		}
	}
}
