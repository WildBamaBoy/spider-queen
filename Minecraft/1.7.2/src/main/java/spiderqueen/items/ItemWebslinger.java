/*******************************************************************************
 * ItemWebslinger.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package spiderqueen.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.forge.PlayerExtension;
import spiderqueen.entity.EntityWebslinger;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.network.Packet;

public class ItemWebslinger extends Item
{
	public ItemWebslinger()
	{
		setMaxDamage(64);
		setMaxStackSize(1);
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	@Override
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase entitySource, EntityLivingBase entityHit)
	{
		if (entityHit instanceof EntityPlayer)
		{
			final EntityPlayer player = (EntityPlayer) entityHit;
			final PlayerExtension playerExtension = PlayerExtension.get(player);

			if (playerExtension.webEntity != null)
			{
				if (!(entitySource instanceof EntityPlayer))
				{
					playerExtension.webEntity.player = null;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
	{
		final PlayerExtension playerExtension = PlayerExtension.get(entityPlayer);

		if (playerExtension.webEntity != null)
		{
			if (world.isRemote)
			{
				SpiderQueen.packetPipeline.sendPacketToServer(new Packet(EnumPacketType.DestroySlinger, playerExtension.webEntity.getEntityId(), entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ));

				playerExtension.webEntity.player = null;
				playerExtension.webEntity = null;
			}
		}

		else
		{
			world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityWebslinger(world, entityPlayer));
			}
		}

		return itemstack;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:Webslinger");
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		list.add("Swing from walls and ceilings.");
		list.add("");
		list.add("JUMP to reel in.");
		list.add("SNEAK to drop down.");
	}
}
