package sq.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import radixcore.constant.Time;
import radixcore.packets.PacketDataContainer;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.core.minecraft.ModAchievements;
import sq.core.minecraft.ModItems;
import sq.core.radix.PlayerData;
import sq.entity.ai.PlayerExtension;
import sq.packet.PacketSleepC;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public final class EventHooksFML
{
	private int counter;
	private int timeUntilSpawnWeb;

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

		//Update sleeping
		if (counter <= 0)
		{
			int totalPlayers = MinecraftServer.getServer().getCurrentPlayerCount();

			if (SpiderCore.sleepingPlayers.size() >= totalPlayers && totalPlayers != 0)
			{
				for (String s : SpiderCore.sleepingPlayers)
				{
					for (WorldServer world : MinecraftServer.getServer().worldServers)
					{
						EntityPlayerMP player = (EntityPlayerMP) world.getPlayerEntityByName(s);

						if (player != null)
						{
							SpiderCore.getPacketHandler().sendPacketToPlayer(new PacketSleepC(true), player);
						}
					}
				}

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

				//Tick extensions.
				extension.tick();

				//Check for buffs.
				if (player.worldObj.getBlockLightValue((int) player.posX, (int) player.posY, (int) player.posZ) <= 8)
				{
					player.triggerAchievement(ModAchievements.goInTheDark);
					
					if (SpiderCore.getConfig().enableNightVision && player.getActivePotionEffect(Potion.nightVision) == null)
					{
						player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 12000, 0, true));
					}

					if (player.getActivePotionEffect(Potion.weakness) != null)
					{
						player.removePotionEffect(Potion.weakness.id);
					}
				}

				else //Light level below 8
				{
					if (player.worldObj.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ))
					{
						if (player.getActivePotionEffect(Potion.nightVision) != null)
						{
							player.removePotionEffect(Potion.nightVision.id);
						}

						if (player.getActivePotionEffect(Potion.weakness) == null)
						{
							player.addPotionEffect(new PotionEffect(Potion.weakness.id, 12000, 0, true));
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void playerLoggedInEventHandler(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		PlayerData data = null;

		if (!SpiderCore.playerDataMap.containsKey(player.getUniqueID().toString()))
		{
			data = new PlayerData(player);

			if (data.dataExists())
			{
				data = data.readDataFromFile(event.player, PlayerData.class, null);
			}

			else
			{
				data.initializeNewData(event.player);
			}

			SpiderCore.playerDataMap.put(event.player.getUniqueID().toString(), data);
		}

		else
		{
			data = SpiderCore.getPlayerData(player);
			data = data.readDataFromFile(event.player, PlayerData.class, null);  //Read from the file again to assign owner.
			SpiderCore.playerDataMap.put(event.player.getUniqueID().toString(), data);  //Put updated data back into the map.
		}

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
