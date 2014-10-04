/*******************************************************************************
 * PacketOpenGui.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MCA Minecraft Mod license.
 ******************************************************************************/

package sqr.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import sqr.core.SpiderQueen;

import com.radixshock.radixcore.network.packets.AbstractPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenGui extends AbstractPacket implements IMessage, IMessageHandler<PacketOpenGui, IMessage>
{
	private byte guiId;

	public PacketOpenGui()
	{
	}

	public PacketOpenGui(byte guiId)
	{
		this.guiId = guiId;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		guiId = byteBuf.readByte();
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		byteBuf.writeByte(guiId);
	}

	@Override
	public IMessage onMessage(PacketOpenGui packet, MessageContext context)
	{
		final EntityPlayer player = getPlayer(context);

		if (player != null)
		{ 
			player.openGui(SpiderQueen.getInstance(), packet.guiId, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		}

		return null;
	}
}
