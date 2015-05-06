package sqr.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import radixcore.packets.PacketDataContainer;
import sqr.core.SQR;
import sqr.core.radix.PlayerData;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public final class EventHooksFML
{
	@SubscribeEvent
	public void onConfigChanges(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if (eventArgs.modID.equals(SQR.ID))
		{
			SQR.getConfig().getConfigInstance().save();
			SQR.getConfig().syncConfiguration();
		}
	}

	@SubscribeEvent
	public void playerLoggedInEventHandler(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		PlayerData data = null;

		if (!SQR.playerDataMap.containsKey(player.getUniqueID().toString()))
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

			SQR.playerDataMap.put(event.player.getUniqueID().toString(), data);
		}

		else
		{
			data = SQR.getPlayerData(player);
			data = data.readDataFromFile(event.player, PlayerData.class, null);  //Read from the file again to assign owner.
			SQR.playerDataMap.put(event.player.getUniqueID().toString(), data);  //Put updated data back into the map.
		}

		SQR.getPacketHandler().sendPacketToPlayer(new PacketDataContainer(SQR.ID, data), (EntityPlayerMP)event.player);
	}

	@SubscribeEvent
	public void playerLoggedOutEventHandler(PlayerLoggedOutEvent event)
	{
		PlayerData data = SQR.getPlayerData(event.player);

		if (data != null)
		{
			data.saveDataToFile();
		}
	}
}
