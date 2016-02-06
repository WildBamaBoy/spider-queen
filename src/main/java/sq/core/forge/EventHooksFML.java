package sq.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import radixcore.constant.Time;
import radixcore.packets.PacketDataContainer;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.core.minecraft.ModAchievements;
import sq.core.minecraft.ModItems;
import sq.core.minecraft.Spawner;
import sq.core.radix.PlayerData;
import sq.entity.ai.PlayerExtension;
import sq.packet.PacketSleepC;

/**
 * This class contains all event hooks that belong to FML.
 */
public final class EventHooksFML
{
	private int counter;
	private int timeUntilSpawnWeb;
	private int timeUntilSpawnerRun;

	@SubscribeEvent
	public void onConfigChanges(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if (eventArgs.modID.equals(SpiderCore.ID))
		{
			SpiderCore.getConfig().getConfigInstance().save();
			SpiderCore.getConfig().syncConfiguration();
		}
	}

	@SubscribeEvent
	public void serverTickEventHandler(ServerTickEvent event)
	{
		//Update web spawning
		timeUntilSpawnWeb--;

		if (timeUntilSpawnWeb <= 0)
		{
			for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
			{
				for (final Object obj : worldServer.playerEntities)
				{
					final EntityPlayer player = (EntityPlayer) obj;
					player.inventory.addItemStackToInventory(new ItemStack(Items.string, RadixMath.getNumberInRange(1, 3)));
				}
			}

			timeUntilSpawnWeb = Time.MINUTE * 3;
		}

		//Update sleeping. Check each second to see if all players are asleep.
		if (counter <= 0)
		{
			int totalPlayers = MinecraftServer.getServer().getCurrentPlayerCount();

			if (SpiderCore.sleepingPlayers.size() >= totalPlayers && totalPlayers != 0)
			{
				//When all players are asleep, loop through all worlds and look for them.
				for (String s : SpiderCore.sleepingPlayers)
				{
					for (WorldServer world : MinecraftServer.getServer().worldServers)
					{
						EntityPlayerMP player = (EntityPlayerMP) world.getPlayerEntityByName(s);

						if (player != null)
						{
							player.setSpawnChunk(new BlockPos((int)player.posX, (int)player.posY, (int)player.posZ), true, player.worldObj.provider.getDimensionId());

							//Each time we find them, send a packet to close their sleeping GUI.
							SpiderCore.getPacketHandler().sendPacketToPlayer(new PacketSleepC(true), player);
						}
					}
				}

				//When all done, set the time to night and clear the sleeping player list.
				MinecraftServer.getServer().worldServers[0].provider.setWorldTime(13000);
				SpiderCore.sleepingPlayers.clear();
			}

			counter = Time.SECOND * 1;
		}

		counter--;

		//Update day/night buffs and tick extensions.
		for (final WorldServer worldServer : MinecraftServer.getServer().worldServers)
		{
			for (final Object obj : worldServer.playerEntities)
			{
				final EntityPlayer player = (EntityPlayer) obj;
				final PlayerExtension extension = PlayerExtension.get(player);

				//Tick player extensions to reduce slinger cooldown.
				extension.tick();

				//Check for buffs when light level is above 8.
				if (player.worldObj.getBlockLightOpacity(new BlockPos((int) player.posX, (int) player.posY, (int) player.posZ)) <= 8)
				{
					player.triggerAchievement(ModAchievements.goInTheDark);

					if (SpiderCore.getConfig().enableNightVision && player.getActivePotionEffect(Potion.nightVision) == null)
					{
						player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 12000, 0, true, false));
					}

					if (player.getActivePotionEffect(Potion.weakness) != null)
					{
						player.removePotionEffect(Potion.weakness.id);
					}
				}

				else //Light level below 8
				{
					if (player.worldObj.canBlockSeeSky(new BlockPos((int) player.posX, (int) player.posY, (int) player.posZ)))
					{
						if (player.getActivePotionEffect(Potion.nightVision) != null)
						{
							player.removePotionEffect(Potion.nightVision.id);
						}

						if (player.getActivePotionEffect(Potion.weakness) == null)
						{
							player.addPotionEffect(new PotionEffect(Potion.weakness.id, 12000, 0, true, false));
						}
					}
				}
			}
		}

		//Run the spawner when appropriate.
		if (timeUntilSpawnerRun <= 0)
		{
			timeUntilSpawnerRun = Time.MINUTE * 1; 

			for (World world : MinecraftServer.getServer().worldServers)
			{
				Spawner.runSpawner(world);
			}
		}

		timeUntilSpawnerRun--;
	}

	@SubscribeEvent
	public void playerLoggedInEventHandler(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		PlayerData data = null;

		//If this player's player data is not contained in our map.
		if (!SpiderCore.playerDataMap.containsKey(player.getUniqueID().toString()))
		{
			data = new PlayerData(player);

			//Check to see if there's an existing file, if so, read it.
			if (data.dataExists())
			{
				data = data.readDataFromFile(event.player, PlayerData.class, null);
			}

			//Otherwise set up the new file.
			else
			{
				data.initializeNewData(event.player);
			}

			//Save the data in our player data map.
			SpiderCore.playerDataMap.put(event.player.getUniqueID().toString(), data);
		}

		else //We already have the player's data loaded.
		{
			data = SpiderCore.getPlayerData(player);
			data = data.readDataFromFile(event.player, PlayerData.class, null);  //Read from the file again to assign owner.
			SpiderCore.playerDataMap.put(event.player.getUniqueID().toString(), data);  //Put updated data back into the map.
		}

		//Send the player their own player data.
		SpiderCore.getPacketHandler().sendPacketToPlayer(new PacketDataContainer(SpiderCore.ID, data), (EntityPlayerMP)event.player);
	}

	@SubscribeEvent
	public void playerLoggedOutEventHandler(PlayerLoggedOutEvent event)
	{
		PlayerData data = SpiderCore.getPlayerData(event.player);

		if (data != null)
		{
			data.saveDataToFile();
		}

		//TODO SpiderCore.sleepingPlayers.remove(event.player);
	}

	@SubscribeEvent
	public void itemCraftedEventHandler(ItemCraftedEvent event)
	{
		final EntityPlayer player = event.player;

		if (event.crafting.getItem() == ModItems.webNormal)
		{
			player.triggerAchievement(ModAchievements.craftWeb);
		}

		else if (event.crafting.getItem() == ModItems.spiderRod)
		{
			player.triggerAchievement(ModAchievements.craftSpiderRod);
		}

		else if (event.crafting.getItem() == ModItems.recallRod)
		{
			player.triggerAchievement(ModAchievements.craftRecallRod);
		}

		else if (event.crafting.getItem() == ModItems.webslinger)
		{
			player.triggerAchievement(ModAchievements.craftWebslinger);
		}

		else if (event.crafting.getItem() == ModItems.webPoison)
		{
			player.triggerAchievement(ModAchievements.craftPoisonWeb);
		}
	}
}
