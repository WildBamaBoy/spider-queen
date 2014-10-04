package sqr.network;

import sqr.network.packets.PacketCreateExplosion;
import sqr.network.packets.PacketDestroySlinger;
import sqr.network.packets.PacketGetInventory;
import sqr.network.packets.PacketOpenGui;
import sqr.network.packets.PacketSetInventory;
import sqr.network.packets.PacketSetMotion;
import sqr.network.packets.PacketSetSkin;
import sqr.network.packets.PacketSleep;
import sqr.network.packets.PacketSwingArm;

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
		registerDoubleSidedPacket(PacketCreateExplosion.class, 0);
		registerDoubleSidedPacket(PacketDestroySlinger.class, 1);
		registerDoubleSidedPacket(PacketGetInventory.class, 2);
		registerDoubleSidedPacket(PacketOpenGui.class, 3);
		registerDoubleSidedPacket(PacketSetInventory.class, 4);
		registerDoubleSidedPacket(PacketSetMotion.class, 5);
		registerDoubleSidedPacket(PacketSetSkin.class, 6);
		registerDoubleSidedPacket(PacketSleep.class, 7);
		registerDoubleSidedPacket(PacketSwingArm.class, 8);
	}
}
