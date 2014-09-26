/*******************************************************************************
 * PacketOpenGui.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MCA Minecraft Mod license.
 ******************************************************************************/

package sqr.network.packets;

import ibxm.Player;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.radixshock.radixcore.network.packets.AbstractPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSetMotion extends AbstractPacket implements IMessage, IMessageHandler<PacketSetMotion, IMessage>
{
	private double motionX;
	private double motionY;
	private double motionZ;
	
	public PacketSetMotion()
	{
	}

	public PacketSetMotion(double motionX, double motionY, double motionZ)
	{
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		this.motionX = byteBuf.readDouble();
		this.motionY = byteBuf.readDouble();
		this.motionZ = byteBuf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		byteBuf.writeDouble(motionX);
		byteBuf.writeDouble(motionY);
		byteBuf.writeDouble(motionZ);
	}

	@Override
	public IMessage onMessage(PacketSetMotion packet, MessageContext context)
	{
		final EntityPlayer player = getPlayer(context);

		player.setVelocity(packet.motionX, packet.motionY, packet.motionZ);

		return null;
	}
}
