/*******************************************************************************
 * EventHooks.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.PlayerEatEntry;
import spiderqueen.entity.EntityEnemyQueen;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

/**
 * Contains methods that perform a function when an event in Minecraft occurs.
 */
public class EventHooks 
{
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
}
