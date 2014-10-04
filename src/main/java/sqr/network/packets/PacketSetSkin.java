package sqr.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.radixshock.radixcore.network.ByteBufIO;
import com.radixshock.radixcore.network.packets.AbstractPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSetSkin extends AbstractPacket implements IMessage, IMessageHandler<PacketSetSkin, IMessage>
{
	private String skinName;
	private String playerName;
	
	public PacketSetSkin()
	{
	}

	public PacketSetSkin(String skinName, String playerName)
	{
		this.skinName = skinName;
		this.playerName = playerName;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		skinName = (String) ByteBufIO.readObject(byteBuf);
		playerName = (String) ByteBufIO.readObject(byteBuf);
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		ByteBufIO.writeObject(byteBuf, skinName);
		ByteBufIO.writeObject(byteBuf, playerName);
	}

	@Override
	public IMessage onMessage(PacketSetSkin packet, MessageContext context)
	{
		final EntityPlayer clientPlayer = getPlayerClient();
		final EntityPlayer recievedPlayer = clientPlayer.worldObj.getPlayerEntityByName(packet.playerName);
		
		if (recievedPlayer != null)
		{
			
		}
		
		return null;
	}
}
