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
import sqr.inventory.Inventory;

import com.radixshock.radixcore.network.ByteBufIO;
import com.radixshock.radixcore.network.packets.AbstractPacket;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSetInventory extends AbstractPacket implements IMessage, IMessageHandler<PacketSetInventory, IMessage>
{
	private int entityId;
	private Inventory inventory;
	
	public PacketSetInventory()
	{
	}

	public PacketSetInventory(int entityId, Inventory inventory)
	{
		this.entityId = entityId;
		this.inventory = inventory;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf)
	{
		this.entityId = byteBuf.readInt();
		this.inventory = (Inventory) ByteBufIO.readObject(byteBuf);
	}

	@Override
	public void toBytes(ByteBuf byteBuf)
	{
		byteBuf.writeInt(this.entityId);
		ByteBufIO.writeObject(byteBuf, inventory);
	}

	@Override
	public IMessage onMessage(PacketSetInventory packet, MessageContext context)
	{
		final EntityPlayer player = this.getPlayer(context);
		final EntityLivingBase entity = (EntityLivingBase) player.worldObj.getEntityByID(packet.entityId);
		packet.inventory.owner = entity;

		if (entity instanceof EntityFakePlayer)
		{
			final EntityFakePlayer fakePlayer = (EntityFakePlayer) entity;
			fakePlayer.inventory = packet.inventory;
			fakePlayer.inventory.setWornArmorItems();
		}

		else if (entity instanceof EntityHatchedSpider)
		{
			final EntityHatchedSpider hatchedSpider = (EntityHatchedSpider) entity;
			hatchedSpider.inventory = packet.inventory;
		}

		if (!player.worldObj.isRemote)
		{
			SpiderQueen.packetHandler.sendPacketToAllPlayersExcept(new PacketSetInventory(packet.entityId, packet.inventory), (EntityPlayerMP) player);
		}

		else
		{
			packet.inventory.setWornArmorItems();
		}
		
		return null;
	}
}
