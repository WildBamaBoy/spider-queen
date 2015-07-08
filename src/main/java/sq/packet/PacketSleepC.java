package sq.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import radixcore.packets.AbstractPacket;
import sq.core.SpiderCore;
import sq.core.forge.GuiHandler;

/**
 * Defines the sleep packet processed on the client.
 */
public class PacketSleepC extends AbstractPacket implements IMessage, IMessageHandler<PacketSleepC, IMessage>
{
	private boolean doStop;
	
	public PacketSleepC()
	{
	}

	public PacketSleepC(boolean doStop)
	{
		this.doStop = doStop;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		doStop = byteBuf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		byteBuf.writeBoolean(doStop);
	}

	@Override
	public IMessage onMessage(PacketSleepC packet, MessageContext context)
	{
		final EntityPlayer player = getPlayer(context);

		if (player != null)
		{ 
			if (packet.doStop)
			{
				Minecraft.getMinecraft().displayGuiScreen(null);
			}
			
			else
			{
				player.openGui(SpiderCore.getInstance(), GuiHandler.ID_GUI_SLEEP, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
			}
		}

		return null;
	}
}