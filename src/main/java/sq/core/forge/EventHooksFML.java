package sq.core.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import radixcore.constant.Time;
import radixcore.packets.PacketDataContainer;
import radixcore.util.RadixMath;
import sq.client.gui.GuiScreenWarning;
import sq.core.SpiderCore;
import sq.core.radix.PlayerData;
import sq.packet.PacketSleepC;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class EventHooksFML
{
	private int counter;
	private int timeUntilSpawnWeb;
	private boolean displayedASMWarning;
	
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
	@SideOnly(Side.CLIENT)
	public void clientTickEventHandler(ClientTickEvent event)
	{
		if (!displayedASMWarning && SpiderCore.asmErrors.size() > 0)
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiScreenWarning());
			displayedASMWarning = true;
		}
	}
	
	@SubscribeEvent
	public void serverTickEventHandler(ServerTickEvent event)
	{
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
