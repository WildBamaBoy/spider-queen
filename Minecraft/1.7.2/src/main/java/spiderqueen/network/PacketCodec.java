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
import com.radixshock.radixcore.network.ByteBufIO;
import com.radixshock.radixcore.network.Packet;

/**
 * Spider Queen's packet codec.
 */
public final class PacketCodec extends AbstractPacketCodec
{
	/**
	 * Constructor
	 * 
	 * @param mod
	 *            The owner mod.
	 */
	public PacketCodec(IEnforcedCore mod)
	{
		super(mod);
	}

	@Override
	public void encode(Packet packet, ChannelHandlerContext context, ByteBuf buffer)
	{
		final EnumPacketType type = (EnumPacketType) packet.packetType;

		try
		{
			switch (type)
			{
				case GetInventory:
					buffer.writeInt((Integer) packet.arguments[0]);
					break;

				case SetInventory:
					buffer.writeInt((Integer) packet.arguments[0]);
					writeObject(buffer, packet.arguments[1]);
					break;

				case SetEaten:
					buffer.writeInt((Integer) packet.arguments[0]);
					break;

				case SetLevel:
					buffer.writeInt((Integer) packet.arguments[0]);
					buffer.writeInt((Integer) packet.arguments[1]);
					break;

				case SetPlayerMotion:
					ByteBufIO.writeObject(buffer, packet.arguments[0].toString());
					buffer.writeDouble((Double) packet.arguments[1]);
					buffer.writeDouble((Double) packet.arguments[2]);
					buffer.writeDouble((Double) packet.arguments[3]);
					break;

				case SetDistance:
					buffer.writeDouble((Double) packet.arguments[0]);
					break;

				case DestroySlinger:
					buffer.writeInt((Integer) packet.arguments[0]);
					buffer.writeDouble((Double) packet.arguments[1]);
					buffer.writeDouble((Double) packet.arguments[2]);
					buffer.writeDouble((Double) packet.arguments[3]);
					break;

				case CreateClientExplosion:
					buffer.writeDouble((Double) packet.arguments[0]);
					buffer.writeDouble((Double) packet.arguments[1]);
					buffer.writeDouble((Double) packet.arguments[2]);
					buffer.writeFloat((Float) packet.arguments[3]);
					buffer.writeBoolean((Boolean) packet.arguments[4]);
					break;

				case SwingArm:
					buffer.writeInt((Integer) packet.arguments[0]);
					break;

				case CreateParticle:
					ByteBufIO.writeObject(buffer, packet.arguments[0]);
					buffer.writeDouble((Double) packet.arguments[1]);
					buffer.writeDouble((Double) packet.arguments[2]);
					buffer.writeDouble((Double) packet.arguments[3]);
					buffer.writeDouble((Double) packet.arguments[4]);
					buffer.writeDouble((Double) packet.arguments[5]);
					buffer.writeDouble((Double) packet.arguments[6]);
					break;

				case SetSkin:
					ByteBufIO.writeObject(buffer, packet.arguments[0]);
					ByteBufIO.writeObject(buffer, packet.arguments[1]);
					break;

				case OpenGui:
					buffer.writeInt((Integer) packet.arguments[0]);
					break;

				default:
					break;
			}
		}

		catch (final Throwable e)
		{
			SpiderQueen.getInstance().getLogger().log(e);
		}
	}

	@Override
	public void decode(Packet packet, ChannelHandlerContext context, ByteBuf buffer)
	{
		final EnumPacketType type = (EnumPacketType) packet.packetType;

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

				case SetLevel:
					packet.arguments[0] = buffer.readInt();
					packet.arguments[1] = buffer.readInt();
					break;

				case SetPlayerMotion:
					packet.arguments[0] = ByteBufIO.readObject(buffer);
					packet.arguments[1] = buffer.readDouble();
					packet.arguments[2] = buffer.readDouble();
					packet.arguments[3] = buffer.readDouble();
					break;

				case SetDistance:
					packet.arguments[0] = buffer.readDouble();
					break;

				case DestroySlinger:
					packet.arguments[0] = buffer.readInt();
					packet.arguments[1] = buffer.readDouble();
					packet.arguments[2] = buffer.readDouble();
					packet.arguments[3] = buffer.readDouble();
					break;

				case CreateClientExplosion:
					packet.arguments[0] = buffer.readDouble();
					packet.arguments[1] = buffer.readDouble();
					packet.arguments[2] = buffer.readDouble();
					packet.arguments[3] = buffer.readFloat();
					packet.arguments[4] = buffer.readBoolean();
					break;

				case SwingArm:
					packet.arguments[0] = buffer.readInt();
					break;

				case CreateParticle:
					packet.arguments[0] = ByteBufIO.readObject(buffer);
					packet.arguments[1] = buffer.readDouble();
					packet.arguments[2] = buffer.readDouble();
					packet.arguments[3] = buffer.readDouble();
					packet.arguments[4] = buffer.readDouble();
					packet.arguments[5] = buffer.readDouble();
					packet.arguments[6] = buffer.readDouble();
					break;

				case SetSkin:
					packet.arguments[0] = ByteBufIO.readObject(buffer);
					packet.arguments[1] = ByteBufIO.readObject(buffer);
					break;

				case OpenGui:
					packet.arguments[0] = buffer.readInt();
					break;

				default:
					break;
			}
		}

		catch (final Throwable e)
		{
			SpiderQueen.getInstance().getLogger().log(e);
		}
	}
}
