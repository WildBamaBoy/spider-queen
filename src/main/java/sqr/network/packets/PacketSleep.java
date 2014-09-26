package sqr.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import sqr.core.Constants;
import sqr.core.SpiderQueen;

import com.radixshock.radixcore.network.packets.AbstractPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSleep extends AbstractPacket implements IMessage, IMessageHandler<PacketSleep, IMessage>
{
	private boolean isSleepFinished;
	
	public PacketSleep()
	{
	}

	public PacketSleep(boolean isSleepFinished)
	{
		this.isSleepFinished = isSleepFinished;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		isSleepFinished = byteBuf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		byteBuf.writeBoolean(isSleepFinished);
	}

	@Override
	public IMessage onMessage(PacketSleep packet, MessageContext context)
	{
		final EntityPlayer player = getPlayer(context);
	
		if (packet.isSleepFinished)
		{
			player.worldObj.setWorldTime(13000);
			player.setSpawnChunk(player.getPlayerCoordinates(), true);
			SpiderQueen.packetHandler.sendPacketToPlayer(new PacketOpenGui(Constants.ID_GUI_NONE), (EntityPlayerMP) player);
		}
		
		return null;
	}
}
