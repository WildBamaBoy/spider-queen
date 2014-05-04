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

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
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
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.PlayerEatEntry;
import spiderqueen.entity.EntityEnemyQueen;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;

import com.radixshock.radixcore.logic.LogicHelper;

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
	 * @param 	event	The event.
	 */
	@SubscribeEvent
	public void clientTickEventHandler(ClientTickEvent event)
	{
		SpiderQueen.getInstance().clientTickHandler.onTick();
	}
	
	/**
	 * Ticks the server tick handler.
	 * 
	 * @param 	event	The event.
	 */
	@SubscribeEvent
	public void serverTickEventHandler(ServerTickEvent event)
	{
		SpiderQueen.getInstance().serverTickHandler.onTick();
	}

	/**
	 * Fired when the player right-clicks something.
	 * 
	 * @param 	event	An instance of the PlayerInteractEvent.
	 */
	@SubscribeEvent
	public void playerInteractEventHandler(PlayerInteractEvent event)
	{
		if (!event.entityPlayer.worldObj.isRemote && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
		{
			SpiderQueen.getInstance().serverTickHandler.playersEating.clear();

			if (event.entityPlayer.getCurrentEquippedItem() != null)
			{
				if (event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemFood)
				{
					SpiderQueen.getInstance().serverTickHandler.playersEating.add(new PlayerEatEntry(event.entityPlayer, event.entityPlayer.inventory.currentItem, event.entityPlayer.getCurrentEquippedItem().stackSize));
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(PlayerReputationHandler.ID) == null)
		{
			PlayerReputationHandler.register((EntityPlayer) event.entity);
		}
	}

	@SubscribeEvent
	public void entityJoinedWorldEventHandler(EntityJoinWorldEvent event)
	{
		if (!event.world.isRemote)
		{
			if (event.entity instanceof EntityMob)
			{
				doAddAttackTasks((EntityCreature)event.entity);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer)
		{
			final EntityPlayer player = (EntityPlayer) event.source.getEntity();
			final PlayerReputationHandler reputationHandler = (PlayerReputationHandler) player.getExtendedProperties(PlayerReputationHandler.ID);

			if (event.entityLiving instanceof EntityCreeper)
			{
				reputationHandler.creepersKilled++;
			}

			else if (event.entityLiving instanceof EntityFakePlayer)
			{
				reputationHandler.humansKilled++;
			}

			else if (event.entityLiving instanceof EntitySkeleton)
			{
				reputationHandler.skeletonsKilled++;
			}

			else if (event.entityLiving instanceof EntityZombie)
			{
				reputationHandler.zombiesKilled++;
			}

			else if (event.entityLiving instanceof EntityEnemyQueen || event.entityLiving instanceof EntityHatchedSpider)
			{
				if (event.entityLiving instanceof EntityEnemyQueen)
				{
					final EntityEnemyQueen queen = (EntityEnemyQueen)event.entityLiving;

					if (!queen.isHostile)
					{
						reputationHandler.friendlySpidersKilled++;
					}
				}

				else
				{
					final EntityHatchedSpider spider = (EntityHatchedSpider)event.entityLiving;

					if (spider.owner.length() == 32)
					{
						reputationHandler.friendlySpidersKilled++;
					}
				}
			}

			else if (event.entityLiving instanceof EntitySpider)
			{
				reputationHandler.spidersKilled++;
			}
		}
	}

	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event)
	{
		final EntityPlayer player = event.entityPlayer;

		if (!player.worldObj.isRemote)
		{
			final PlayerReputationHandler reputationHandler = (PlayerReputationHandler) player.getExtendedProperties(PlayerReputationHandler.ID);
			final ItemStack currentItem = player.getCurrentEquippedItem();

			if (currentItem != null)
			{
				if (event.target instanceof EntityCreeper && currentItem.getItem() == SpiderQueen.getInstance().itemHeart)
				{
					reputationHandler.reputationCreepers = reputationHandler.reputationCreepers == 5 ? 5 : reputationHandler.reputationCreepers + 1;
					currentItem.stackSize--;
				}

				if (event.target instanceof EntityZombie && currentItem.getItem() == SpiderQueen.getInstance().itemBrain)
				{
					reputationHandler.reputationZombies = reputationHandler.reputationZombies == 5 ? 5 : reputationHandler.reputationZombies + 1;
					currentItem.stackSize--;
				}

				if (event.target instanceof EntitySkeleton && currentItem.getItem() == SpiderQueen.getInstance().itemSkull)
				{
					reputationHandler.reputationSkeletons = reputationHandler.reputationSkeletons == 5 ? 5 : reputationHandler.reputationSkeletons + 1;
					currentItem.stackSize--;
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingSetTarget(LivingSetAttackTargetEvent event)
	{
		if (event.target instanceof EntityPlayer)
		{
			final EntityLiving entity = (EntityLiving)event.entityLiving;
			final EntityPlayer player = (EntityPlayer)event.target;
			final PlayerReputationHandler reputationHandler = (PlayerReputationHandler) player.getExtendedProperties(PlayerReputationHandler.ID);

			if (entity instanceof EntityCreeper && reputationHandler.reputationCreepers >= 0 ||
					entity instanceof EntityZombie && reputationHandler.reputationZombies >= 0 ||
					entity instanceof EntityFakePlayer && reputationHandler.reputationHumans >= 0 ||
					entity instanceof EntitySkeleton && reputationHandler.reputationSkeletons >= 0)
			{
				entity.setAttackTarget(null);
			}

			else //Player is a valid target.
			{
				if (reputationHandler.reputationCreepers > 0)
				{
					final List<EntityCreeper> nearbyCreepers = (List<EntityCreeper>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(player, EntityCreeper.class, 10);

					for (EntityCreeper creeper : nearbyCreepers)
					{
						creeper.setAttackTarget(entity);
					}
				}

				if (reputationHandler.reputationHumans > 0)
				{
					final List<EntityFakePlayer> nearbyFakePlayers = (List<EntityFakePlayer>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(player, EntityFakePlayer.class, 10);

					for (EntityFakePlayer fakePlayer : nearbyFakePlayers)
					{
						fakePlayer.setAttackTarget(entity);
					}
				}

				if (reputationHandler.reputationSkeletons > 0)
				{
					final List<EntitySkeleton> nearbySkeletons = (List<EntitySkeleton>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(player, EntitySkeleton.class, 10);

					for (EntitySkeleton skeleton : nearbySkeletons)
					{
						skeleton.setAttackTarget(entity);
					}
				}

				if (reputationHandler.reputationZombies > 0)
				{
					final List<EntityZombie> nearbyZombies = (List<EntityZombie>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(player, EntityZombie.class, 10);

					for (EntityZombie zombie : nearbyZombies)
					{
						zombie.setAttackTarget(entity);
					}
				}

				if (reputationHandler.reputationFriendlySpiderQueens > 0)
				{
					final List<EntityEnemyQueen> nearbyEnemyQueens = (List<EntityEnemyQueen>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(player, EntityEnemyQueen.class, 10);

					for (EntityEnemyQueen queen : nearbyEnemyQueens)
					{
						queen.setAttackTarget(entity);
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
