package sq.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import radixcore.network.ByteBufIO;
import radixcore.packets.AbstractPacket;
import sq.core.SpiderCore;

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
		if (packet.addRemoveFlag)
		{
			SpiderCore.sleepingPlayers.add(packet.username);
		}
		
		else
		{
			SpiderCore.sleepingPlayers.remove(packet.username);
		}
		
		return null;
	}
}