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

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import spiderqueen.command.CommandCheckReputation;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.CreatureReputationEntry;
import spiderqueen.core.util.PlayerEatEntry;
import spiderqueen.entity.EntityFakePlayer;

import com.radixshock.radixcore.constant.Font.Color;
import com.radixshock.radixcore.constant.Time;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.logic.Point3D;

/**
 * Handles ticking server-side.
 */
public class ServerTickHandler
{
	/** The number of ticks since the loop has been ran. */
	private int serverTicks = 20;
	private int timeUntilSpawnWeb = Time.MINUTE * 3;
	private int timeUntilSpawnPlayers = Time.SECOND * 30; 
	private int timeUntilSpawnWarParties = Time.MINUTE * 2;

	private boolean hasCalculatedReputationForDay = false;
	public List<PlayerEatEntry> playersEating = new ArrayList<PlayerEatEntry>();
	private List<PlayerEatEntry> playersNoLongerEating = new ArrayList<PlayerEatEntry>();

	/**
	 * Fires once per tick in-game.
	 */
	public void onTick()
	{
		updateNightVision();
		updateSpawnWeb();
		updatePlayerEat();
		updateReputation();
		updateSpawnPlayers();
		updateSpawnWarParties();
	}

	private void updateReputation()
	{
		final World worldServer = MinecraftServer.getServer().worldServers[0];

		if (worldServer.isDaytime() && hasCalculatedReputationForDay)
		{
			hasCalculatedReputationForDay = false;
		}

		else if (!worldServer.isDaytime() && !hasCalculatedReputationForDay)
		{
			for (Object obj : worldServer.playerEntities)
			{	
				final EntityPlayer player = (EntityPlayer)obj;
				final PlayerReputationHandler reputationHandler = (PlayerReputationHandler) player.getExtendedProperties(PlayerReputationHandler.ID);

				modifyReputations(reputationHandler);
				new CommandCheckReputation().processCommand(player, null);
				applyReputationEffects(player, reputationHandler);
			}

			hasCalculatedReputationForDay = true;
		}
	}

	private void applyReputationEffects(EntityPlayer player, PlayerReputationHandler reputationHandler) 
	{
		for (CreatureReputationEntry entry : reputationHandler.getReputationEntries())
		{
			if (entry.reputationValue == -2 && !entry.isAtWar)
			{
				player.addChatMessage(new ChatComponentText(Color.RED + "The " + entry.creatureGroupName.toLowerCase() + " are not pleased with your latest actions."));
			}

			else if (entry.reputationValue == -4 && !entry.isAtWar)
			{
				player.addChatMessage(new ChatComponentText(Color.RED + "The " + entry.creatureGroupName.toLowerCase() + " are threatening war."));
				player.addChatMessage(new ChatComponentText(Color.RED + "They have sent a group to assassinate you."));
				LogicHelper.spawnGroupOfEntitiesAroundPlayer(player, entry.getCreatureClass(), 2, 5);
			}

			else if (entry.reputationValue == -5 && !entry.isAtWar)
			{
				player.addChatMessage(new ChatComponentText(Color.RED + "The " + entry.creatureGroupName.toLowerCase() + " have declared war on you for your actions."));
				entry.isAtWar = true;
			}

			else if (entry.reputationValue == 0 && entry.isAtWar)
			{
				player.addChatMessage(new ChatComponentText(Color.GREEN + "The " + entry.creatureGroupName.toLowerCase() + " have ended their war with you."));
				entry.isAtWar = false;
			}

			else if (entry.reputationValue == 3)
			{
				player.addChatMessage(new ChatComponentText(Color.GREEN + "The " + entry.creatureGroupName.toLowerCase() + " are pleased with you. They have sent you one of their own."));
				LogicHelper.spawnEntityAtPlayer(player, EntityCreeper.class);
			}
		}
	}

	private void modifyReputations(PlayerReputationHandler reputationHandler) 
	{
		final EntityPlayer player = reputationHandler.getPlayer();

		for (CreatureReputationEntry entry : reputationHandler.getReputationEntries())
		{
			if (entry.creaturesKilled >= 3)
			{
				entry.reputationValue = 
						entry.reputationValue == -5 ? -5 :
							entry.reputationValue - 1;
			}

			else if (entry.creaturesKilled == 0)
			{
				entry.reputationValue = 
						entry.reputationValue == 5 ? 5 :
							entry.reputationValue + 1;
			}

			entry.creaturesKilled = 0;
		}
	}

	private void updateNightVision()
	{
		for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
		{
			for (Object obj : worldServer.playerEntities)
			{
				EntityPlayer player = (EntityPlayer)obj;

				if (player.worldObj.getBlockLightValue((int)player.posX, (int)player.posY, (int)player.posZ) <= 8)
				{
					if (player.getActivePotionEffect(Potion.nightVision) == null)
					{
						player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 12000, 1, true));
					}
				}

				else
				{
					if (player.worldObj.canBlockSeeTheSky((int)player.posX, (int)player.posY, (int)player.posZ))
					{
						if (player.getActivePotionEffect(Potion.nightVision) != null)
						{
							player.removePotionEffect(Potion.nightVision.id);
						}
					}
				}
			}
		}
	}

	private void updateSpawnWeb()
	{
		timeUntilSpawnWeb--;

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

			timeUntilSpawnWeb = Time.MINUTE * 3;
		}
	}

	private void updatePlayerEat()
	{
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

	private void updateSpawnPlayers()
	{
		timeUntilSpawnPlayers--;

		if (timeUntilSpawnPlayers <= 0)
		{
			for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
			{
				for (Object obj : worldServer.playerEntities)
				{
					final EntityPlayer player = (EntityPlayer)obj;
					final boolean doSpawnPlayers = LogicHelper.getBooleanWithProbability(30);
					final int modX = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(35, 60) : LogicHelper.getNumberInRange(35, 60) * -1;
					final int modZ = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(35, 60) : LogicHelper.getNumberInRange(35, 60) * -1;

					final List<Entity> nearbyEntities = ((List<Entity>)LogicHelper.getAllEntitiesWithinDistanceOfCoordinates(player.worldObj, player.posX + modX, player.posY, player.posZ + modZ, 30));
					int numberOfPlayersNearby = 0;

					for (final Object entity : nearbyEntities)
					{
						if (entity instanceof EntityFakePlayer)
						{
							numberOfPlayersNearby++;
						}
					}

					if (doSpawnPlayers && numberOfPlayersNearby < 5)
					{
						LogicHelper.spawnGroupOfEntitiesAroundPoint(player.worldObj, new Point3D(player.posX + modX, player.posY, player.posZ + modZ), EntityFakePlayer.class, 1, 4);
					}
				}
			}

			timeUntilSpawnPlayers = Time.SECOND * 30;
		}
	}

	private void updateSpawnWarParties() 
	{
		timeUntilSpawnWarParties--;

		if (timeUntilSpawnWarParties <= 0)
		{
			for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
			{
				for (Object obj : worldServer.playerEntities)
				{
					final EntityPlayer player = (EntityPlayer)obj;
					final PlayerReputationHandler reputationHandler = (PlayerReputationHandler)player.getExtendedProperties(PlayerReputationHandler.ID);

					for (CreatureReputationEntry entry : reputationHandler.getReputationEntries())
					{
						if (entry.isAtWar)
						{
							final boolean doSpawnWarParty = LogicHelper.getBooleanWithProbability(70);
							final int modX = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(10, 25) : LogicHelper.getNumberInRange(10, 25) * -1;
							final int modZ = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(10, 25) : LogicHelper.getNumberInRange(10, 25) * -1;

							if (doSpawnWarParty)
							{
								LogicHelper.spawnGroupOfEntitiesAroundPoint(player.worldObj, new Point3D(player.posX + modX, player.posY, player.posZ + modZ), entry.getCreatureClass(), 3, 6);
							}
						}
					}
				}
			}

			timeUntilSpawnWarParties = LogicHelper.getNumberInRange(2, 15) * Time.MINUTE;
		}
	}
}