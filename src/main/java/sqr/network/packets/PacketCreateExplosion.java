/*******************************************************************************
 * PacketOpenGui.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MCA Minecraft Mod license.
 ******************************************************************************/

package sqr.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.network.packets.AbstractPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketCreateExplosion extends AbstractPacket implements IMessage, IMessageHandler<PacketCreateExplosion, IMessage>
{
	private double posX;
	private double posY;
	private double posZ;
	private float  intensity;
	private boolean doGreifing;
	
	public PacketCreateExplosion()
	{
	}

	public PacketCreateExplosion(double posX, double posY, double posZ, float intensity, boolean doGreifing)
	{
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.intensity = intensity;
		this.doGreifing = doGreifing;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		this.posX = byteBuf.readDouble();
		this.posY = byteBuf.readDouble();
		this.posZ = byteBuf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		byteBuf.writeDouble(posX);
		byteBuf.writeDouble(posY);
		byteBuf.writeDouble(posZ);
	}

	@Override
	public IMessage onMessage(PacketCreateExplosion packet, MessageContext context)
	{
		final EntityPlayer player = getPlayer(context);
		
		player.worldObj.createExplosion(null, packet.posX, packet.posY, packet.posZ, packet.intensity, packet.doGreifing);
		player.worldObj.playSound(packet.posX, packet.posY, packet.posZ, "random.explode", 4.0F, (1.0F + (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.2F) * 0.7F, true);

		for (int i = 0; i < 10; i++)
		{
			final int modX = LogicHelper.getNumberInRange(0, 3);
			final int modY = LogicHelper.getNumberInRange(0, 3);
			final int modZ = LogicHelper.getNumberInRange(0, 3);
			player.worldObj.spawnParticle("largeexplode", posX + modX, posY + modY, posZ + modZ, 1.0D, 0.0D, 0.0D);
		}
		
		return null;
	}
}
