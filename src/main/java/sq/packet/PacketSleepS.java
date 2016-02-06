package sq.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import radixcore.network.ByteBufIO;
import radixcore.packets.AbstractPacket;
import sq.core.SpiderCore;

/**
 * Defines the sleep packet processed on the server.
 */
public class PacketSleepS extends AbstractPacket implements IMessage, IMessageHandler<PacketSleepS, IMessage>
{
	private String username;
	private boolean addRemoveFlag;
	
	public PacketSleepS()
	{
	}

	public PacketSleepS(String username, boolean flag)
	{
		this.username = username;
		this.addRemoveFlag = flag;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		username = (String)ByteBufIO.readObject(byteBuf);
		addRemoveFlag = byteBuf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		ByteBufIO.writeObject(byteBuf, username);
		byteBuf.writeBoolean(addRemoveFlag);
	}

	@Override
	public IMessage onMessage(PacketSleepS packet, MessageContext context)
	{
		SpiderCore.getPacketHandler().addPacketForProcessing(context.side, packet, context);
		return null;
	}

	@Override
	public void processOnGameThread(IMessageHandler message, MessageContext context) 
	{
		final PacketSleepS packet = (PacketSleepS)message;
		
		if (packet.addRemoveFlag)
		{
			SpiderCore.sleepingPlayers.add(packet.username);
		}
		
		else
		{
			SpiderCore.sleepingPlayers.remove(packet.username);
		}
	}
}