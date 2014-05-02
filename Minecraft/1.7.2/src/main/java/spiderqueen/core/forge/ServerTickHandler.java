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
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.PlayerEatEntry;

import com.radixshock.radixcore.constant.Font.Color;
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
				notifyReputations(player, reputationHandler);
			}

			hasCalculatedReputationForDay = true;
		}
	}

	private void notifyReputations(EntityPlayer player, PlayerReputationHandler reputationHandler) 
	{
		//Creepers
		if (reputationHandler.reputationCreepers == -2 && !reputationHandler.isAtWarWithCreepers)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The creepers are not pleased with your latest actions."));
		}

		else if (reputationHandler.reputationCreepers == -4 && !reputationHandler.isAtWarWithCreepers)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The creepers are threatening war."));
			player.addChatMessage(new ChatComponentText(Color.RED + "They have sent a group to assassinate you."));
		}

		else if (reputationHandler.reputationCreepers == -5 && !reputationHandler.isAtWarWithCreepers)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The creepers have declared war on you for your actions."));
			reputationHandler.isAtWarWithCreepers = true;
		}

		else if (reputationHandler.reputationCreepers == 0 && reputationHandler.isAtWarWithCreepers)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The creepers have ended their war with you."));
			reputationHandler.isAtWarWithCreepers = false;
		}

		else if (reputationHandler.reputationCreepers == 3)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The creepers are pleased with you. They have sent you one of their own."));
		}

		//Humans
		if (reputationHandler.reputationHumans == -2 && !reputationHandler.isAtWarWithHumans)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The humans are not pleased with your latest actions."));
		}

		else if (reputationHandler.reputationHumans == -4 && !reputationHandler.isAtWarWithHumans)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The humans are threatening war."));
			player.addChatMessage(new ChatComponentText(Color.RED + "They have sent a group to assassinate you."));
		}

		else if (reputationHandler.reputationHumans == -5 && !reputationHandler.isAtWarWithHumans)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The humans have declared war on you for your actions."));
			reputationHandler.isAtWarWithHumans = true;
		}

		else if (reputationHandler.reputationHumans == 0 && reputationHandler.isAtWarWithHumans)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The humans have ended their war with you."));
			reputationHandler.isAtWarWithHumans = false;
		}

		else if (reputationHandler.reputationHumans == 3)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The humans are pleased with you. They have sent you one of their own."));
		}

		//Skeletons
		if (reputationHandler.reputationSkeletons == -2 && !reputationHandler.isAtWarWithSkeletons)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The skeletons are not pleased with your latest actions."));
		}

		else if (reputationHandler.reputationSkeletons == -4 && !reputationHandler.isAtWarWithSkeletons)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The skeletons are threatening war."));
			player.addChatMessage(new ChatComponentText(Color.RED + "They have sent a group to assassinate you."));
		}

		else if (reputationHandler.reputationSkeletons == -5 && !reputationHandler.isAtWarWithSkeletons)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The skeletons have declared war on you for your actions."));
			reputationHandler.isAtWarWithSkeletons = true;
		}

		else if (reputationHandler.reputationSkeletons == 0 && reputationHandler.isAtWarWithSkeletons)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The skeletons have ended their war with you."));
			reputationHandler.isAtWarWithSkeletons = false;
		}

		else if (reputationHandler.reputationSkeletons == 3)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The skeletons are pleased with you. They have sent you one of their own."));
		}

		//Zombies
		if (reputationHandler.reputationZombies == -2 && !reputationHandler.isAtWarWithZombies)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The zombies are not pleased with your latest actions."));
		}

		else if (reputationHandler.reputationZombies == -4 && !reputationHandler.isAtWarWithZombies)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The zombies are threatening war."));
			player.addChatMessage(new ChatComponentText(Color.RED + "They have sent a group to assassinate you."));
		}

		else if (reputationHandler.reputationZombies == -5 && !reputationHandler.isAtWarWithZombies)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The zombies have declared war on you for your actions."));
			reputationHandler.isAtWarWithZombies = true;
		}

		else if (reputationHandler.reputationZombies == 0 && reputationHandler.isAtWarWithZombies)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The zombies have ended their war with you."));
			reputationHandler.isAtWarWithZombies = false;
		}

		else if (reputationHandler.reputationZombies == 3)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The zombies are pleased with you. They have sent you one of their own."));
		}

		//Friendly Spiders
		if (reputationHandler.reputationFriendlySpiderQueens == -2 && !reputationHandler.isAtWarWithFriendlySpiderQueens)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The other spider queens are not pleased with your latest actions."));
		}

		else if (reputationHandler.reputationFriendlySpiderQueens == -4 && !reputationHandler.isAtWarWithFriendlySpiderQueens)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The other spider queens are threatening war."));
			player.addChatMessage(new ChatComponentText(Color.RED + "They have sent a group to assassinate you."));
		}

		else if (reputationHandler.reputationFriendlySpiderQueens == -5 && !reputationHandler.isAtWarWithFriendlySpiderQueens)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "The other spider queens have declared war on you for your actions."));
			reputationHandler.isAtWarWithFriendlySpiderQueens = true;
		}

		else if (reputationHandler.reputationFriendlySpiderQueens == 0 && reputationHandler.isAtWarWithFriendlySpiderQueens)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The other spider queens have ended their war with you."));
			reputationHandler.isAtWarWithFriendlySpiderQueens = false;
		}

		else if (reputationHandler.reputationFriendlySpiderQueens == 3)
		{
			player.addChatMessage(new ChatComponentText(Color.GREEN + "The other spider queens are pleased with you. They have sent you a group of spiders."));
		}

		//Evil Spider Queen
		if (reputationHandler.reputationEvilSpiderQueen == -5 && !reputationHandler.isAtWarWithEvilSpiderQueen)
		{
			player.addChatMessage(new ChatComponentText(Color.RED + "You have angered the evil spider queen!"));
			player.addChatMessage(new ChatComponentText(Color.RED + "She has come to seek vengeance!"));
		}
	}

	private void modifyReputations(PlayerReputationHandler reputationHandler) 
	{
		if (reputationHandler.creepersKilled > 3)
		{
			reputationHandler.reputationCreepers = 
					reputationHandler.reputationCreepers == -5 ? -5 :
						reputationHandler.reputationCreepers - 1;
		}

		else if (reputationHandler.creepersKilled == 0)
		{
			reputationHandler.reputationCreepers = 
					reputationHandler.reputationCreepers == 5 ? 5 :
						reputationHandler.reputationCreepers + 1;
		}

		if (reputationHandler.humansKilled > 3)
		{
			reputationHandler.reputationHumans = 
					reputationHandler.reputationHumans == -5 ? -5 :
						reputationHandler.reputationHumans - 1;
		}

		else if (reputationHandler.humansKilled == 0)
		{
			reputationHandler.reputationHumans = 
					reputationHandler.reputationHumans == 5 ? 5 :
						reputationHandler.reputationHumans + 1;
		}

		if (reputationHandler.skeletonsKilled > 3)
		{
			reputationHandler.reputationSkeletons = 
					reputationHandler.reputationSkeletons == -5 ? -5 :
						reputationHandler.reputationSkeletons - 1;
		}

		else if (reputationHandler.skeletonsKilled == 0)
		{
			reputationHandler.reputationSkeletons = 
					reputationHandler.reputationSkeletons == 5 ? 5 :
						reputationHandler.reputationSkeletons + 1;
		}

		if (reputationHandler.zombiesKilled > 3)
		{
			reputationHandler.reputationZombies = 
					reputationHandler.reputationZombies == -5 ? -5 :
						reputationHandler.reputationZombies - 1;
		}

		else if (reputationHandler.zombiesKilled == 0)
		{
			reputationHandler.reputationZombies = 
					reputationHandler.reputationZombies == 5 ? 5 :
						reputationHandler.reputationZombies + 1;
		}

		if (reputationHandler.friendlySpidersKilled > 3)
		{
			reputationHandler.reputationFriendlySpiderQueens = 
					reputationHandler.reputationFriendlySpiderQueens == -5 ? -5 :
						reputationHandler.reputationFriendlySpiderQueens - 1;
		}

		else if (reputationHandler.friendlySpidersKilled == 0)
		{
			reputationHandler.reputationFriendlySpiderQueens = 
					reputationHandler.reputationFriendlySpiderQueens == 5 ? 5 :
						reputationHandler.reputationFriendlySpiderQueens + 1;
		}

		if (reputationHandler.spidersKilled > 3)
		{
			reputationHandler.reputationEvilSpiderQueen = 
					reputationHandler.reputationEvilSpiderQueen == -5 ? -5 :
						reputationHandler.reputationEvilSpiderQueen - 1;
		}

		else if (reputationHandler.spidersKilled == 0)
		{
			reputationHandler.reputationEvilSpiderQueen = 
					reputationHandler.reputationEvilSpiderQueen == 5 ? 5 :
						reputationHandler.reputationEvilSpiderQueen + 1;
		}

		reputationHandler.creepersKilled = 0;
		reputationHandler.friendlySpidersKilled = 0;
		reputationHandler.spidersKilled = 0;
		reputationHandler.humansKilled = 0;
		reputationHandler.zombiesKilled = 0;
		reputationHandler.skeletonsKilled = 0;
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

			timeUntilSpawnWeb = Time.MINUTE * 1;
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
}