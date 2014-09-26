package sqr.network;

import sqr.network.packets.PacketOpenGui;
import sqr.network.packets.PacketSetSkin;
import sqr.network.packets.PacketSleep;

import com.radixshock.radixcore.core.IEnforcedCore;
import com.radixshock.radixcore.network.AbstractPacketHandler;

public class PacketRegistry extends AbstractPacketHandler
{
	public PacketRegistry(IEnforcedCore ownerMod) 
	{
		super(ownerMod);
	}
	
	@Override
	public void registerPackets()
	{
		registerDoubleSidedPacket(PacketOpenGui.class, 0);
		registerDoubleSidedPacket(PacketSetSkin.class, 1);
		registerDoubleSidedPacket(PacketSleep.class, 2);
	}
}
