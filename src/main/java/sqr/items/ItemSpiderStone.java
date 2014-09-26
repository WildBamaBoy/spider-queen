/*******************************************************************************
 * ItemSpiderStone.java
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
import sqr.core.SpiderQueen;

public class ItemSpiderStone extends Item
{
	public ItemSpiderStone()
	{
		super();
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	@Override
	public void registerIcons(IIconRegister IIconRegister)
	{
		itemIcon = IIconRegister.registerIcon("spiderqueen:SpiderStone");
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		list.add("A rare stone used in");
		list.add("various crafting recipes.");
	}

	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{
		return true;
	}
}
