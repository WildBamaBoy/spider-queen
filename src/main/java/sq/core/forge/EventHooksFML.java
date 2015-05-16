package sq.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import radixcore.packets.PacketDataContainer;
import sq.core.SQ;
import sq.core.radix.PlayerData;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public final class EventHooksFML
{
	@SubscribeEvent
	public void onConfigChanges(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if (eventArgs.modID.equals(SQ.ID))
		{
			SQ.getConfig().getConfigInstance().save();
			SQ.getConfig().syncConfiguration();
		}
	}

	@SubscribeEvent
	public void playerLoggedInEventHandler(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		PlayerData data = null;

		if (!SQ.playerDataMap.containsKey(player.getUniqueID().toString()))
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

			SQ.playerDataMap.put(event.player.getUniqueID().toString(), data);
		}

		else
		{
			data = SQ.getPlayerData(player);
			data = data.readDataFromFile(event.player, PlayerData.class, null);  //Read from the file again to assign owner.
			SQ.playerDataMap.put(event.player.getUniqueID().toString(), data);  //Put updated data back into the map.
		}

		SQ.getPacketHandler().sendPacketToPlayer(new PacketDataContainer(SQ.ID, data), (EntityPlayerMP)event.player);
	}

	@SubscribeEvent
	public void playerLoggedOutEventHandler(PlayerLoggedOutEvent event)
	{
		PlayerData data = SQ.getPlayerData(event.player);

		if (data != null)
		{
			data.saveDataToFile();
		}
	}
}
