/*******************************************************************************
 * ItemCocoon.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.enums.EnumCocoonType;

import com.radixshock.radixcore.constant.Font.Color;

public class ItemCocoon extends Item
{
	private final EnumCocoonType	cocoonType;

	public ItemCocoon(EnumCocoonType cocoonType)
	{
		super();
		maxStackSize = 1;
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
		this.cocoonType = cocoonType;
		this.cocoonType.setCocoonItem(this);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (!world.isRemote)
		{
			if (!player.capabilities.isCreativeMode)
			{
				itemStack.stackSize--;
			}

			posX += Facing.offsetsXForSide[meta];
			posY += Facing.offsetsYForSide[meta];
			posZ += Facing.offsetsZForSide[meta];

			final EntityCocoon entityCocoon = new EntityCocoon(world, cocoonType);
			entityCocoon.setPositionAndRotation(posX + 0.5F, posY + 1, posZ + 0.5F, player.rotationYaw * -1, 0F);
			world.spawnEntityInWorld(entityCocoon);
		}

		return true;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		String name = cocoonType.toString();
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();

		itemIcon = iconRegister.registerIcon("spiderqueen:Cocoon" + name);
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		if (this == SpiderQueen.getInstance().itemCocoonChicken || this == SpiderQueen.getInstance().itemCocoonCow || this == SpiderQueen.getInstance().itemCocoonHorse || this == SpiderQueen.getInstance().itemCocoonPig || this == SpiderQueen.getInstance().itemCocoonSheep || this == SpiderQueen.getInstance().itemCocoonHuman)
		{
			list.add(Color.GRAY + "Produces a typical spider.");
		}

		else if (this == SpiderQueen.getInstance().itemCocoonEnderman)
		{
			list.add(Color.GRAY + "Produces an " + Color.PURPLE + "Ender Spider" + Color.GRAY + ".");
		}

		else if (this == SpiderQueen.getInstance().itemCocoonCreeper)
		{
			list.add(Color.GRAY + "Produces a " + Color.GREEN + "Boom Spider" + Color.GRAY + ".");
		}

		else if (this == SpiderQueen.getInstance().itemCocoonSkeleton)
		{
			list.add(Color.GRAY + "Produces a " + Color.WHITE + "Slinger Spider" + Color.GRAY + ".");
		}

		else if (this == SpiderQueen.getInstance().itemCocoonTestificate)
		{
			list.add(Color.GRAY + "Produces a " + Color.GOLD + "Pack Spider" + Color.GRAY + ".");
		}

		else if (this == SpiderQueen.getInstance().itemCocoonWolf)
		{
			list.add(Color.GRAY + "Produces a " + Color.WHITE + "Nova Spider" + Color.GRAY + ".");
		}

		else if (this == SpiderQueen.getInstance().itemCocoonZombie)
		{
			list.add(Color.GRAY + "Produces a " + Color.DARKGREEN + "Tank Spider" + Color.GRAY + ".");
		}
	}
}
