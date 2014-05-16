/*******************************************************************************
 * EventHooks.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.CreatureReputationEntry;
import spiderqueen.core.util.PlayerEatEntry;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.constant.Font.Color;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

/**
 * Contains methods that perform a function when an event in Minecraft occurs.
 */
public class EventHooks
{
	/**
	 * Ticks the client tick handler.
	 * 
	 * @param event
	 *            The event.
	 */
	@SubscribeEvent
	public void clientTickEventHandler(ClientTickEvent event)
	{
		SpiderQueen.clientTickHandler.onTick();
	}

	/**
	 * Ticks the server tick handler.
	 * 
	 * @param event
	 *            The event.
	 */
	@SubscribeEvent
	public void serverTickEventHandler(ServerTickEvent event)
	{
		SpiderQueen.serverTickHandler.onTick();
	}

	/**
	 * Fired when the player right-clicks something.
	 * 
	 * @param event
	 *            An instance of the PlayerInteractEvent.
	 */
	@SubscribeEvent
	public void playerInteractEventHandler(PlayerInteractEvent event)
	{
		if (!event.entityPlayer.worldObj.isRemote && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
		{
			SpiderQueen.serverTickHandler.playersEating.clear();

			if (event.entityPlayer.getCurrentEquippedItem() != null)
			{
				if (event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemFood)
				{
					event.entityPlayer.getFoodStats().setFoodLevel(19);
					SpiderQueen.serverTickHandler.playersEating.add(new PlayerEatEntry(event.entityPlayer, event.entityPlayer.inventory.currentItem, event.entityPlayer.getCurrentEquippedItem().stackSize));
				}
			}
		}
	}

	@SubscribeEvent
	public void onAttackEntity(AttackEntityEvent event)
	{
		final EntityPlayer player = event.entityPlayer;
		final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);

		try
		{
			for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
			{
				if (entry.reputationValue > 0 && entry.getCreatureClass() != event.target.getClass())
				{
					for (final EntityLiving entity : (List<EntityLiving>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(event.entityPlayer, entry.getCreatureClass(), 15))
					{
						entity.setAttackTarget((EntityLivingBase) event.target);
					}
				}
			}

			for (final EntityHatchedSpider spider : (List<EntityHatchedSpider>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(event.entityPlayer, EntityHatchedSpider.class, 15))
			{
				if (spider.owner != null)
				{
					if (spider.owner.equals(event.entityPlayer.getCommandSenderName()) && !(event.target instanceof EntityCocoon))
					{
						if (event.target instanceof EntityHatchedSpider)
						{
							final EntityHatchedSpider targetSpider = (EntityHatchedSpider) event.target;

							if (targetSpider.owner.equals(player.getCommandSenderName()))
							{
								continue;
							}
						}

						spider.target = event.target;
					}
				}
			}
		}

		catch (final ClassCastException e)
		{
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(PlayerExtension.ID) == null)
		{
			PlayerExtension.register((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void entityJoinedWorldEventHandler(EntityJoinWorldEvent event)
	{
		if (!event.world.isRemote)
		{
			if (event.entity instanceof EntityMob)
			{
				doAddAttackTasks((EntityCreature) event.entity);
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer)
		{
			final EntityPlayer player = (EntityPlayer) event.source.getEntity();
			final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);

			for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
			{
				if (event.entityLiving.getClass().toString().equals(entry.getCreatureClass().toString()))
				{
					entry.creaturesKilled++;

					if (entry.creaturesKilled % 10 == 0 && entry.reputationValue > -5)
					{
						entry.reputationValue--;
						player.addChatMessage(new ChatComponentText(Color.RED + "Your reputation with the " + entry.creatureGroupName + " has dropped to " + entry.reputationValue + "."));
					}
				}
			}

			if (event.entityLiving instanceof EntitySpider && LogicHelper.getBooleanWithProbability(20))
			{
				event.entityLiving.dropItem(SpiderQueen.getInstance().itemSpiderStone, 1);
			}
		}
	}

	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event)
	{
		final EntityPlayer player = event.entityPlayer;

		if (!player.worldObj.isRemote)
		{
			final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);
			final ItemStack currentItem = player.getCurrentEquippedItem();
			CreatureReputationEntry entry = null;

			if (currentItem != null)
			{
				if (event.target instanceof EntityCreeper && currentItem.getItem() == SpiderQueen.getInstance().itemHeart)
				{
					entry = playerExtension.getReputationEntry(EntityCreeper.class);
				}

				if (event.target instanceof EntityZombie && currentItem.getItem() == SpiderQueen.getInstance().itemBrain)
				{
					entry = playerExtension.getReputationEntry(EntityZombie.class);
				}

				if (event.target instanceof EntitySkeleton && currentItem.getItem() == SpiderQueen.getInstance().itemSkull)
				{
					entry = playerExtension.getReputationEntry(EntitySkeleton.class);
				}

				if (entry != null)
				{
					entry.reputationValue = entry.reputationValue == 5 ? 5 : entry.reputationValue + 1;
					player.addChatMessage(new ChatComponentText(Color.YELLOW + "Your reputation with the " + entry.creatureGroupName + " has increased to " + entry.reputationValue + "."));
					currentItem.stackSize--;

					for (int i = 0; i < 6; i++)
					{
						final Random rand = player.worldObj.rand;
						final double velX  = rand.nextGaussian() * 0.02D;
						final double velY = rand.nextGaussian() * 0.02D;
						final double velZ = rand.nextGaussian() * 0.02D;

						SpiderQueen.packetPipeline.sendPacketToAllPlayers(
								new Packet(EnumPacketType.CreateParticle, 
										"heart", event.target.posX + rand.nextFloat() * event.target.width * 2.0F - event.target.width, 
										event.target.posY + 0.5D + rand.nextFloat() * event.target.height, 
										event.target.posZ + rand.nextFloat() * event.target.width * 2.0F - event.target.width, 
										velX, velY, velZ));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingSetTarget(LivingSetAttackTargetEvent event)
	{
		if (event.target instanceof EntityPlayer)
		{
			final EntityLiving entity = (EntityLiving) event.entityLiving;
			final EntityPlayer player = (EntityPlayer) event.target;
			final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);

			for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
			{
				if (entity.getClass().toString().equals(entry.getCreatureClass().toString()))
				{
					if (entity instanceof EntityFakePlayer)
					{
						final EntityFakePlayer fakePlayer = (EntityFakePlayer) entity;

						if (fakePlayer.lastAttackingPlayer.equals(player.getCommandSenderName())) { return; }
					}

					if (entry.reputationValue >= 0 && entity.getAttackTarget() != null)
					{
						entity.setAttackTarget(null);
					}
				}
			}
		}
	}

	private void doAddAttackTasks(EntityCreature mob)
	{
		if (mob instanceof EntityMob)
		{
			float moveSpeed = 0.7F;

			if (mob instanceof EntitySpider)
			{
				moveSpeed = 1.2F;
			}

			else if (mob instanceof EntitySkeleton)
			{
				moveSpeed = 1.1F;
			}

			else if (mob instanceof EntityZombie)
			{
				moveSpeed = 0.9F;
			}

			mob.tasks.addTask(2, new EntityAIAttackOnCollide(mob, EntityFakePlayer.class, moveSpeed, false));
			mob.targetTasks.addTask(2, new EntityAINearestAttackableTarget(mob, EntityFakePlayer.class, 16, false));
		}
	}
}
