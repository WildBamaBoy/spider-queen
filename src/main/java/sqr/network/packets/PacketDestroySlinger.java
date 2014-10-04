package sqr.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import sqr.core.SpiderQueen;
import sqr.core.forge.PlayerExtension;
import sqr.entity.EntityWebslinger;

import com.radixshock.radixcore.network.packets.AbstractPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketDestroySlinger extends AbstractPacket implements IMessage, IMessageHandler<PacketDestroySlinger, IMessage>
{
	private int entityId;
	private double posX;
	private double posY;
	private double posZ;
	
	public PacketDestroySlinger()
	{
	}

	public PacketDestroySlinger(int entityId, double posX, double posY, double posZ)
	{
		this.entityId = entityId;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		this.entityId = byteBuf.readInt();
		this.posX = byteBuf.readDouble();
		this.posY = byteBuf.readDouble();
		this.posZ = byteBuf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		byteBuf.writeInt(entityId);
		byteBuf.writeDouble(posX);
		byteBuf.writeDouble(posY);
		byteBuf.writeDouble(posZ);
	}

	@Override
	public IMessage onMessage(PacketDestroySlinger packet, MessageContext context)
	{
		final EntityPlayer player = getPlayer(context);
		
		final EntityWebslinger webslinger = (EntityWebslinger) player.worldObj.getEntityByID(packet.entityId);
		final PlayerExtension playerExtension = PlayerExtension.get(player);

		playerExtension.webEntity.player = null;
		playerExtension.webEntity = null;

		if (webslinger != null)
		{
			webslinger.setDead();
		}

		SpiderQueen.packetHandler.sendPacketToAllPlayers(new PacketSetMotion(player.motionX, player.motionY, player.motionZ));
		
		return null;
	}
}
