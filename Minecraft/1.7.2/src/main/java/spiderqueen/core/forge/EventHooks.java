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

import net.minecraft.block.Block;
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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import spiderqueen.core.ModPropertiesList;
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
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
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
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			SpiderQueen.clientTickHandler.onTick();
		}
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
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			SpiderQueen.serverTickHandler.onTick();
		}
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
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			if (!event.entityPlayer.worldObj.isRemote && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
			{
				if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
				{
					final World world = event.entityPlayer.worldObj;
					final Block block = world.getBlock(event.x, event.y, event.z);

					if (block == Blocks.farmland)
					{
						return;
					}
				}

				SpiderQueen.serverTickHandler.playersEating.clear();

				if (event.entityPlayer.getCurrentEquippedItem() != null)
				{
					if (event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemFood)
					{
						if (event.entityPlayer.getFoodStats().getFoodLevel() == 20)
						{
							event.entityPlayer.getFoodStats().addStats(-1, 0);
						}

						SpiderQueen.serverTickHandler.playersEating.add(new PlayerEatEntry(event.entityPlayer, event.entityPlayer.inventory.currentItem, event.entityPlayer.getCurrentEquippedItem().stackSize));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void playerLoggedInEventHandler(PlayerLoggedInEvent event)
	{
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			final EntityPlayer player = (EntityPlayer)event.player;
			final ModPropertiesList modPropertiesList = SpiderQueen.getInstance().getModProperties();
			
			SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetSkin, modPropertiesList.spiderSkin, player.getCommandSenderName()));
		}
	}

	@SubscribeEvent
	public void onPlayerSleepInBed(PlayerSleepInBedEvent event)
	{
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			event.result = EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE;
			event.entityPlayer.addChatMessage(new ChatComponentText("Spiders can't sleep in normal beds."));
		}
	}

	@SubscribeEvent
	public void onEntityItemPickup(EntityItemPickupEvent event)
	{
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			if (event.entityPlayer != null)
			{
				if (event.item.getEntityItem().getItem() == SpiderQueen.getInstance().itemSpiderStone)
				{
					event.entityPlayer.triggerAchievement(SpiderQueen.getInstance().achievementFindSpiderStone);
				}

				else if (event.item.getEntityItem().getItem() == SpiderQueen.getInstance().itemFlameWeb)
				{
					event.entityPlayer.triggerAchievement(SpiderQueen.getInstance().achievementPickupFlameWeb);
				}
			}
		}
	}

	@SubscribeEvent
	public void onAttackEntity(AttackEntityEvent event)
	{
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			final EntityPlayer player = event.entityPlayer;
			final PlayerExtension playerExtension = PlayerExtension.get(player);

			try
			{
				for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
				{
					if (entry.reputationValue > 0 && entry.getCreatureClass() != event.target.getClass() && event.target instanceof EntityLivingBase && !(event.target instanceof EntityCocoon))
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
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			if (event.source.getEntity() instanceof EntityPlayer)
			{
				final EntityPlayer player = (EntityPlayer) event.source.getEntity();
				final PlayerExtension playerExtension = PlayerExtension.get(player);

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

				if (event.entityLiving instanceof EntitySpider && LogicHelper.getBooleanWithProbability(30))
				{
					event.entityLiving.dropItem(SpiderQueen.getInstance().itemSpiderStone, 1);
				}

				if (event.entityLiving instanceof EntityVillager)
				{
					final List<EntityZombie> nearbyZombies = (List<EntityZombie>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(event.entityLiving, EntityZombie.class, 8);
					final boolean cobblestoneNearby = LogicHelper.getNearbyBlock_StartAtTop(event.entityLiving, Blocks.cobblestone, 5) != null;
					final boolean planksNearby = LogicHelper.getNearbyBlock_StartAtTop(event.entityLiving, Blocks.planks, 5) != null;

					if (nearbyZombies.size() > 0 && cobblestoneNearby && planksNearby)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementHelpZombies);
					}
				}

				if (event.entityLiving instanceof EntityFakePlayer)
				{
					playerExtension.totalHumansKilled++;

					if (playerExtension.totalHumansKilled >= 50)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementKillHumans);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event)
	{
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			final EntityPlayer player = event.entityPlayer;

			if (!player.worldObj.isRemote)
			{
				final PlayerExtension playerExtension = PlayerExtension.get(player);
				final ItemStack currentItem = player.getCurrentEquippedItem();
				CreatureReputationEntry entry = null;

				if (currentItem != null)
				{
					if (event.target instanceof EntityCreeper && currentItem.getItem() == SpiderQueen.getInstance().itemHeart)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementGiftHeart);
						entry = playerExtension.getReputationEntry(EntityCreeper.class);
					}

					if (event.target instanceof EntityZombie && currentItem.getItem() == SpiderQueen.getInstance().itemBrain)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementGiftBrain);
						entry = playerExtension.getReputationEntry(EntityZombie.class);
					}

					if (event.target instanceof EntitySkeleton && currentItem.getItem() == SpiderQueen.getInstance().itemSkull)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementGiftSkull);
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
							final double velX = rand.nextGaussian() * 0.02D;
							final double velY = rand.nextGaussian() * 0.02D;
							final double velZ = rand.nextGaussian() * 0.02D;

							SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.CreateParticle, "heart", event.target.posX + rand.nextFloat() * event.target.width * 2.0F - event.target.width, event.target.posY + 0.5D + rand.nextFloat() * event.target.height, event.target.posZ + rand.nextFloat() * event.target.width * 2.0F - event.target.width, velX, velY, velZ));
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingSetTarget(LivingSetAttackTargetEvent event)
	{
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			try
			{
				if (event.target instanceof EntityPlayer)
				{
					final EntityLiving entity = (EntityLiving) event.entityLiving;
					final EntityPlayer player = (EntityPlayer) event.target;
					final PlayerExtension playerExtension = PlayerExtension.get(player);

					for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
					{
						if (entity.getClass().toString().equals(entry.getCreatureClass().toString()))
						{
							if (entity instanceof EntityFakePlayer)
							{
								final EntityFakePlayer fakePlayer = (EntityFakePlayer) entity;

								if (fakePlayer.lastAttackingPlayer.equals(player.getCommandSenderName())) { return; }
							}

							if (entry.reputationValue >= 0 && entity.getAttackTarget() != null && entity.getHealth() > entity.getMaxHealth() / 2)
							{
								entity.setAttackTarget(null);
							}
						}
					}
				}
			}

			catch (final ClassCastException e)
			{
				// When hit by another player.
			}
		}
	}

	/**
	 * Handles crafting of a crown and setting to monarch status.
	 * 
	 * @param event
	 *            The event.
	 */
	@SubscribeEvent
	public void itemCraftedEventHandler(ItemCraftedEvent event)
	{
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			final EntityPlayer player = event.player;

			if (event.crafting.getItem() == SpiderQueen.getInstance().itemWeb)
			{
				player.triggerAchievement(SpiderQueen.getInstance().achievementCraftWeb);
			}

			else if (event.crafting.getItem() == SpiderQueen.getInstance().itemBugLight)
			{
				player.triggerAchievement(SpiderQueen.getInstance().achievementCraftBugLight);
			}

			else if (event.crafting.getItem() == SpiderQueen.getInstance().itemSpiderRod)
			{
				player.triggerAchievement(SpiderQueen.getInstance().achievementCraftSpiderRod);
			}

			else if (event.crafting.getItem() == SpiderQueen.getInstance().itemPoisonBarbs)
			{
				player.triggerAchievement(SpiderQueen.getInstance().achievementCraftPoisonBarbs);
			}

			else if (event.crafting.getItem() == SpiderQueen.getInstance().itemPoisonWeb)
			{
				player.triggerAchievement(SpiderQueen.getInstance().achievementCraftPoisonWeb);
			}

			else if (event.crafting.getItem() == SpiderQueen.getInstance().itemWebslinger)
			{
				player.triggerAchievement(SpiderQueen.getInstance().achievementCraftWebslinger);
			}
		}
	}

	@SubscribeEvent
	public void itemSmeltedEventHandler(ItemSmeltedEvent event)
	{
	}

	private void doAddAttackTasks(EntityCreature mob)
	{
		if (!SpiderQueen.getInstance().getModProperties().isDisabled)
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
}
