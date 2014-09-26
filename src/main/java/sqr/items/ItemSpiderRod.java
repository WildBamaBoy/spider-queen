/*******************************************************************************
 * ItemSpiderRod.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.core.SpiderQueen;

public class ItemSpiderRod extends Item
{
	public ItemSpiderRod()
	{
		maxStackSize = 1;
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int posX, int posY, int posZ, int meta, float unknown1, float unknown2, float unknown3)
	{
		if (world.isAirBlock(posX, posY + 1, posZ))
		{
			world.setBlock(posX, posY + 1, posZ, SpiderQueen.getInstance().blockSpiderRod);

			if (!entityPlayer.capabilities.isCreativeMode)
			{
				itemStack.stackSize--;
			}
		}

		return super.onItemUse(itemStack, entityPlayer, world, posX, posY, posZ, meta, unknown1, unknown2, unknown3);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:SpiderRod");
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		list.add("Lead your followers");
		list.add("with this item equipped.");
		list.add("");
		list.add("Place on the ground to");
		list.add("keep spiders stationary.");
	}
}
