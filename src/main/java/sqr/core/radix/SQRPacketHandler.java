package sqr.core.radix;

import radixcore.network.AbstractPacketHandler;
import radixcore.packets.PacketDataContainer;
import cpw.mods.fml.relauncher.Side;

public final class SQRPacketHandler extends AbstractPacketHandler
{
	public SQRPacketHandler(String modId) 
	{
		super(modId);
	}

	@Override
	public void registerPackets() 
	{
		this.registerPacket(PacketDataContainer.class, Side.CLIENT);
	}
}
