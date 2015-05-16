package sq.core.radix;

import radixcore.network.AbstractPacketHandler;
import radixcore.packets.PacketDataContainer;
import cpw.mods.fml.relauncher.Side;

public final class SpiderPacketHandler extends AbstractPacketHandler
{
	public SpiderPacketHandler(String modId) 
	{
		super(modId);
	}

	@Override
	public void registerPackets() 
	{
		this.registerPacket(PacketDataContainer.class, Side.CLIENT);
	}
}
