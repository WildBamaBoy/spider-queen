package sq.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import radixcore.constant.Time;
import radixcore.packets.PacketDataContainer;
import sq.core.SpiderCore;
import sq.core.radix.PlayerData;
import sq.packet.PacketSleepC;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public final class EventHooksFML
{
	private int counter;
	
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
}
