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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import spiderqueen.entity.EntityOtherQueen;

import com.radixshock.radixcore.constant.Font.Color;
import com.radixshock.radixcore.constant.Time;
import com.radixshock.radixcore.core.RadixCore;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.logic.Point3D;

/**
 * Handles ticking server-side.
 */
public class ServerTickHandler
{
	private int							timeUntilSpawnWeb				= Time.MINUTE * 3;
	private int							timeUntilSpawnPlayers			= Time.SECOND * 30;
	private int							timeUntilSpawnQueens			= Time.SECOND * 30;
	private int							timeUntilSpawnWarParties		= Time.MINUTE * 2;

	private boolean						hasCalculatedReputationForDay	= false;
	public List<PlayerEatEntry>			playersEating					= new ArrayList<PlayerEatEntry>();
	private final List<PlayerEatEntry>	playersNoLongerEating			= new ArrayList<PlayerEatEntry>();

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
		updateSpawnQueens();
		updateSpawnWarParties();
		updateFriendlyEntityPathing();
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
			for (final Object obj : worldServer.playerEntities)
			{
				final EntityPlayer player = (EntityPlayer) obj;
				final PlayerExtension playerExtension = PlayerExtension.get(player);

				modifyReputations(playerExtension);
				new CommandCheckReputation().processCommand(player, null);
				applyReputationEffects(player, playerExtension);
			}

			hasCalculatedReputationForDay = true;
		}
	}

	private void applyReputationEffects(EntityPlayer player, PlayerExtension playerExtension)
	{
		boolean atWarWithAll = true;
		boolean atFriendsWithAll = true;

		for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
		{
			if (!entry.creatureGroupName.equals("Evil Queen"))
			{
				if (!entry.isAtWar)
				{
					atWarWithAll = false;
				}

				else
				{
					if (entry.getCreatureClass() == EntityCreeper.class)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementWarWithCreepers);
					}

					else if (entry.getCreatureClass() == EntityFakePlayer.class)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementWarWithHumans);
					}

					else if (entry.getCreatureClass() == EntityZombie.class)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementWarWithZombies);
					}

					else if (entry.getCreatureClass() == EntityEnderman.class)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementWarWithEndermen);
					}

					else if (entry.getCreatureClass() == EntityOtherQueen.class)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementWarWithOtherQueens);
					}

					else if (entry.getCreatureClass() == EntitySkeleton.class)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementWarWithSkeletons);
					}
				}

				if (entry.reputationValue < 3)
				{
					atFriendsWithAll = false;
				}

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
					player.triggerAchievement(SpiderQueen.getInstance().achievementFriendsWithAny);
					player.addChatMessage(new ChatComponentText(Color.GREEN + "The " + entry.creatureGroupName.toLowerCase() + " are pleased with you. They have sent you one of their own."));
					LogicHelper.spawnEntityAtPlayer(player, entry.getCreatureClass());
				}
			}

			else
			{
				if (entry.reputationValue == -5 && LogicHelper.getBooleanWithProbability(10))
				{
					player.triggerAchievement(SpiderQueen.getInstance().achievementWarWithEvilQueen);
					player.addChatMessage(new ChatComponentText(Color.RED + "The evil spider queen has come to seek vengeance!"));

					final EntityOtherQueen entity = new EntityOtherQueen(player.worldObj, true);

					final int modX = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(5, 15) : LogicHelper.getNumberInRange(5, 15) * -1;
					final int modZ = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(5, 15) : LogicHelper.getNumberInRange(5, 15) * -1;
					final World world = player.worldObj;
					final Point3D point = new Point3D(player.posX + modX, player.posY, player.posZ + modZ);

					try
					{
						final Point3D spawnPoint = LogicHelper.getRandomNearbyBlockCoordinatesOfType(world, point, Blocks.air, 10);

						if (spawnPoint != null)
						{
							int blocksUntilGround = 0;

							while (world.isAirBlock(point.iPosX, point.iPosY + blocksUntilGround, point.iPosZ) && blocksUntilGround != 255)
							{
								blocksUntilGround--;
							}

							entity.setPosition(spawnPoint.dPosX, spawnPoint.dPosY + blocksUntilGround + 1, spawnPoint.dPosZ);
							entity.spawnAdditionalSpiders();
							entity.spawnAdditionalSpiders();
							entity.spawnAdditionalSpiders();

							world.spawnEntityInWorld(entity);
						}
					}

					catch (final Exception e)
					{
						RadixCore.getInstance().getLogger().log("Unexpected exception while spawning a group of entities.");
						RadixCore.getInstance().getLogger().log(e);
					}
				}
			}
		}

		if (atWarWithAll)
		{
			player.triggerAchievement(SpiderQueen.getInstance().achievementWarWithAll);
		}

		else if (atFriendsWithAll)
		{
			player.triggerAchievement(SpiderQueen.getInstance().achievementPeaceWithAll);
		}
	}

	private void modifyReputations(PlayerExtension playerExtension)
	{
		playerExtension.getPlayer();

		for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
		{
			if (entry.creaturesKilled >= 3)
			{
				entry.reputationValue = entry.reputationValue == -5 ? -5 : entry.reputationValue - 1;
			}

			else if (entry.creaturesKilled == 0 && !entry.creatureGroupName.equals("Evil Queen"))
			{
				entry.reputationValue = entry.reputationValue == 5 ? 5 : entry.reputationValue + 1;
			}

			entry.creaturesKilled = 0;
		}
	}

	private void updateNightVision()
	{
		for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
		{
			for (final Object obj : worldServer.playerEntities)
			{
				final EntityPlayer player = (EntityPlayer) obj;
				boolean hasBugLight = false;

				for (final ItemStack stack : player.inventory.mainInventory)
				{
					if (stack != null && stack.getItem() == SpiderQueen.getInstance().itemBugLight)
					{
						hasBugLight = true;
						break;
					}
				}

				if (player.worldObj.getBlockLightValue((int) player.posX, (int) player.posY, (int) player.posZ) <= 8)
				{
					if (hasBugLight)
					{
						if (player.getActivePotionEffect(Potion.nightVision) == null)
						{
							player.triggerAchievement(SpiderQueen.getInstance().achievementNightVision);
							player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 12000, 1, true));
						}
					}

					else if (!hasBugLight && player.getActivePotionEffect(Potion.nightVision) != null)
					{
						player.removePotionEffect(Potion.nightVision.id);
					}
				}

				else
				{
					if (player.worldObj.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ))
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
				for (final Object obj : worldServer.playerEntities)
				{
					final EntityPlayer player = (EntityPlayer) obj;
					player.inventory.addItemStackToInventory(new ItemStack(Items.string, LogicHelper.getNumberInRange(1, 3)));
				}
			}

			timeUntilSpawnWeb = Time.MINUTE * 3;
		}
	}

	private void updatePlayerEat()
	{
		for (final PlayerEatEntry eatEntry : playersEating)
		{
			// Check if the player is still on the same slot.
			if (eatEntry.getPlayer().inventory.currentItem == eatEntry.getSlot())
			{
				final ItemStack currentItemStack = eatEntry.getPlayer().getCurrentEquippedItem();

				if (currentItemStack == null || currentItemStack.stackSize < eatEntry.getCount() && currentItemStack.getItem() instanceof ItemFood)
				{
					eatEntry.getPlayer().inventory.addItemStackToInventory(new ItemStack(Items.string, LogicHelper.getNumberInRange(1, 3)));
					playersNoLongerEating.add(eatEntry);
				}

				if (eatEntry.getPlayer().getItemInUseCount() == 1 && eatEntry.getPlayer().getFoodStats().getFoodLevel() == 19)
				{
					eatEntry.getPlayer().getFoodStats().setFoodLevel(20);
				}
			}

			else
				// They've moved to a different slot. Ignore.
			{
				playersNoLongerEating.add(eatEntry);
			}
		}

		// Empty the playersEating list.
		for (final PlayerEatEntry eatEntry : playersNoLongerEating)
		{
			playersEating.remove(eatEntry);
		}
	}

	private void updateSpawnPlayers()
	{
		if (!SpiderQueen.getInstance().debugHaltSpawnPlayers)
		{
			timeUntilSpawnPlayers--;

			if (timeUntilSpawnPlayers <= 0)
			{
				for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
				{
					for (final Object obj : worldServer.playerEntities)
					{
						final EntityPlayer player = (EntityPlayer) obj;
						final boolean doSpawnPlayers = LogicHelper.getBooleanWithProbability(20);
						final int modX = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(35, 60) : LogicHelper.getNumberInRange(35, 60) * -1;
						final int modZ = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(35, 60) : LogicHelper.getNumberInRange(35, 60) * -1;

						final List<Entity> nearbyEntities = LogicHelper.getAllEntitiesWithinDistanceOfCoordinates(player.worldObj, player.posX + modX, player.posY, player.posZ + modZ, 30);
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
							final boolean spawnGroup = LogicHelper.getBooleanWithProbability(20);
							
							if (spawnGroup)
							{
								LogicHelper.spawnGroupOfEntitiesAroundPoint(player.worldObj, new Point3D(player.posX + modX, player.posY, player.posZ + modZ), EntityFakePlayer.class, 1, 4);
							}
							
							else
							{
								LogicHelper.spawnGroupOfEntitiesAroundPoint(player.worldObj, new Point3D(player.posX + modX, player.posY, player.posZ + modZ), EntityFakePlayer.class, 1, 1);
							}
						}
					}
				}

				timeUntilSpawnPlayers = Time.SECOND * 30;
			}
		}
	}

	private void updateSpawnQueens()
	{
		timeUntilSpawnQueens--;

		if (timeUntilSpawnQueens <= 0)
		{
			for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
			{
				for (final Object obj : worldServer.playerEntities)
				{
					final EntityPlayer player = (EntityPlayer) obj;
					final boolean doSpawnQueens = LogicHelper.getBooleanWithProbability(10);
					final int modX = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(35, 60) : LogicHelper.getNumberInRange(35, 60) * -1;
					final int modZ = LogicHelper.getBooleanWithProbability(50) ? LogicHelper.getNumberInRange(35, 60) : LogicHelper.getNumberInRange(35, 60) * -1;

					final List<Entity> nearbyEntities = LogicHelper.getAllEntitiesWithinDistanceOfCoordinates(player.worldObj, player.posX + modX, player.posY, player.posZ + modZ, 30);
					int numberOfQueensNearby = 0;

					for (final Object entity : nearbyEntities)
					{
						if (entity instanceof EntityOtherQueen)
						{
							numberOfQueensNearby++;
						}
					}

					if (doSpawnQueens && numberOfQueensNearby < 3)
					{
						final World world = player.worldObj;
						final Point3D point = new Point3D(player.posX + modX, player.posY, player.posZ + modZ);
						final int minimum = 1;
						final int maximum = 1;

						try
						{
							final int amountToSpawn = LogicHelper.getNumberInRange(minimum, maximum);

							for (int i = 0; i < amountToSpawn; i++)
							{
								final EntityOtherQueen entity = new EntityOtherQueen(world);
								final Point3D spawnPoint = LogicHelper.getRandomNearbyBlockCoordinatesOfType(world, point, Blocks.air, 10);

								if (spawnPoint != null)
								{
									int blocksUntilGround = 0;

									while (world.isAirBlock(point.iPosX, point.iPosY + blocksUntilGround, point.iPosZ) && blocksUntilGround != 255)
									{
										blocksUntilGround--;
									}

									entity.setPosition(spawnPoint.dPosX, spawnPoint.dPosY + blocksUntilGround + 1, spawnPoint.dPosZ);
									entity.spawnAdditionalSpiders();
									world.spawnEntityInWorld(entity);
								}
							}
						}

						catch (final Exception e)
						{
							RadixCore.getInstance().getLogger().log("Unexpected exception while spawning a group of entities.");
							RadixCore.getInstance().getLogger().log(e);
						}
					}
				}
			}

			timeUntilSpawnQueens = Time.SECOND * 30;
		}
	}

	private void updateSpawnWarParties()
	{
		timeUntilSpawnWarParties--;

		if (timeUntilSpawnWarParties <= 0)
		{
			for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
			{
				for (final Object obj : worldServer.playerEntities)
				{
					final EntityPlayer player = (EntityPlayer) obj;
					final PlayerExtension playerExtension = PlayerExtension.get(player);

					for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
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

	private void updateFriendlyEntityPathing()
	{
		for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
		{
			for (final Object obj : worldServer.playerEntities)
			{
				final EntityPlayer player = (EntityPlayer) obj;
				final PlayerExtension playerExtension = PlayerExtension.get(player);

				for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
				{
					if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == SpiderQueen.getInstance().itemSpiderRod && entry.reputationValue > 0)
					{
						final List<EntityLiving> nearbyEntities = (List<EntityLiving>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(player, entry.getCreatureClass(), 10);

						for (final EntityLiving entity : nearbyEntities)
						{
							if (entity instanceof EntityMob)
							{
								float moveSpeed = 0.7F;

								if (entity instanceof EntitySpider)
								{
									moveSpeed = 1.2F;
								}

								else if (entity instanceof EntitySkeleton)
								{
									moveSpeed = 1.1F;
								}

								else if (entity instanceof EntityZombie)
								{
									moveSpeed = 0.9F;
								}

								if (entity.getNavigator().noPath())
								{
									entity.getNavigator().tryMoveToEntityLiving(player, moveSpeed);
								}
							}
						}
					}
				}
			}
		}
	}
}
