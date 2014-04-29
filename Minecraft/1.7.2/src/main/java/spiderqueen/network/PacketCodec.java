/*******************************************************************************
 * PacketCodec.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import spiderqueen.core.SpiderQueen;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.core.IEnforcedCore;
import com.radixshock.radixcore.network.AbstractPacketCodec;
import com.radixshock.radixcore.network.Packet;

/**
 * Spider Queen's packet codec.
 */
public final class PacketCodec extends AbstractPacketCodec
{
	/**
	 * Constructor
	 * 
	 * @param	mod	The owner mod.
	 */
	public PacketCodec(IEnforcedCore mod) 
	{
		super(mod);
	}

	public void encode(Packet packet, ChannelHandlerContext context, ByteBuf buffer)
	{
		final EnumPacketType type = (EnumPacketType)packet.packetType;

		try
		{
			switch (type)
			{
			case GetInventory:
				buffer.writeInt((Integer)packet.arguments[0]);
				break;
				
			case SetInventory:
				buffer.writeInt((Integer)packet.arguments[0]);
				writeObject(buffer, packet.arguments[1]);
				break;
			case SetEaten:
				buffer.writeInt((Integer)packet.arguments[0]);
				break;
				
			default:
				break;
			}
		}

		catch (Throwable e)
		{
			SpiderQueen.getInstance().getLogger().log(e);
		}
	}

	public void decode(Packet packet, ChannelHandlerContext context, ByteBuf buffer)
	{
		final EnumPacketType type = (EnumPacketType)packet.packetType;

		try
		{
			switch (type)
			{
			case GetInventory:
				packet.arguments[0] = buffer.readInt();
				break;
				
			case SetInventory:
				packet.arguments[0] = buffer.readInt();
				packet.arguments[1] = readObject(buffer);
				break;

			case SetEaten:
				packet.arguments[0] = buffer.readInt();
				break;
				
			default:
				break;
			}
		}
		
		catch (Throwable e)
		{
			SpiderQueen.getInstance().getLogger().log(e);
		}
	}
}
