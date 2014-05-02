/*******************************************************************************
 * ServerTickHandler.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.PlayerEatEntry;

import com.radixshock.radixcore.constant.Time;
import com.radixshock.radixcore.logic.LogicHelper;

/**
 * Handles ticking server-side.
 */
public class ServerTickHandler
{
	/** The number of ticks since the loop has been ran. */
	private int serverTicks = 20;
	private int timeUntilSpawnWeb = Time.MINUTE * 1;
	public List<PlayerEatEntry> playersEating = new ArrayList<PlayerEatEntry>();
	private List<PlayerEatEntry> playersNoLongerEating = new ArrayList<PlayerEatEntry>();

	/**
	 * Fires once per tick in-game.
	 */
	public void onTick()
	{
		timeUntilSpawnWeb--;

		for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
		{
			for (Object obj : worldServer.playerEntities)
			{
				EntityPlayer player = (EntityPlayer)obj;

				if (worldServer.isDaytime())
				{
					if (player.getActivePotionEffect(Potion.nightVision) != null)
					{
						player.removePotionEffect(Potion.nightVision.id);
					}
				}

				else
				{
					if (player.getActivePotionEffect(Potion.nightVision) == null)
					{
						player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 12000, 1, true));
					}
				}
			}
		}
		if (timeUntilSpawnWeb <= 0)
		{
			for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
			{
				for (Object obj : worldServer.playerEntities)
				{
					EntityPlayer player = (EntityPlayer)obj;
					player.inventory.addItemStackToInventory(new ItemStack(SpiderQueen.getInstance().itemWeb, LogicHelper.getNumberInRange(1, 3)));
				}
			}

			timeUntilSpawnWeb = Time.MINUTE * 1;
		}

		for (PlayerEatEntry eatEntry : playersEating)
		{
			//Check if the player is still on the same slot.
			if (eatEntry.getPlayer().inventory.currentItem == eatEntry.getSlot())
			{
				final ItemStack currentItemStack = eatEntry.getPlayer().getCurrentEquippedItem();

				if (currentItemStack == null || (currentItemStack.stackSize < eatEntry.getCount() && currentItemStack.getItem() instanceof ItemFood))
				{
					eatEntry.getPlayer().inventory.addItemStackToInventory(new ItemStack(SpiderQueen.getInstance().itemWeb, LogicHelper.getNumberInRange(1, 3)));
					playersNoLongerEating.add(eatEntry);
				}
			}

			else //They've moved to a different slot. Ignore.
			{
				playersNoLongerEating.add(eatEntry);
			}
		}

		//Empty the playersEating list.
		for (PlayerEatEntry eatEntry : playersNoLongerEating)
		{
			playersEating.remove(eatEntry);
		}
	}
}