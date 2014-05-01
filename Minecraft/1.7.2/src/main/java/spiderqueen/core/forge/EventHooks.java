/*******************************************************************************
 * EventHooks.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import net.minecraft.item.ItemFood;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.PlayerEatEntry;
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
}
