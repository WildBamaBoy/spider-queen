/*******************************************************************************
 * PacketOpenGui.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MCA Minecraft Mod license.
 ******************************************************************************/

package sqr.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import sqr.core.SpiderQueen;
import sqr.entity.EntityFakePlayer;
import sqr.entity.EntityHatchedSpider;

import com.radixshock.radixcore.network.packets.AbstractPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketGetInventory extends AbstractPacket implements IMessage, IMessageHandler<PacketGetInventory, IMessage>
{
	private int entityId;
	
	public PacketGetInventory()
	{
	}

	public PacketGetInventory(int entityId)
	{
		this.entityId = entityId;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		this.entityId = byteBuf.readInt();
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		byteBuf.writeInt(this.entityId);
	}

	@Override
	public IMessage onMessage(PacketGetInventory packet, MessageContext context)
	{
		final EntityPlayer player = this.getPlayer(context);
		final EntityLivingBase entity = (EntityLivingBase) player.worldObj.getEntityByID(entityId);

		if (entity instanceof EntityFakePlayer)
		{
			final EntityFakePlayer fakePlayer = (EntityFakePlayer) entity;
			SpiderQueen.packetHandler.sendPacketToPlayer(new PacketSetInventory(packet.entityId, fakePlayer.inventory), (EntityPlayerMP) player);
		}

		else if (entity instanceof EntityHatchedSpider)
		{
			final EntityHatchedSpider hatchedSpider = (EntityHatchedSpider) entity;
			SpiderQueen.packetHandler.sendPacketToPlayer(new PacketSetInventory(packet.entityId, hatchedSpider.inventory), (EntityPlayerMP) player);
		}
		
		return null;
	}
}
