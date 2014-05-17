/*******************************************************************************
 * ItemWeb.java
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
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityWeb;

import com.radixshock.radixcore.constant.Font.Color;

public class ItemWeb extends Item
{
	/** 0 = normal, 1 = poison, 2 = flame */
	private int	type	= 0;

	public ItemWeb(int type)
	{
		this.type = type;
		maxStackSize = 64;
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (type == 2)
		{
			world.playSoundAtEntity(entityPlayer, "mob.ghast.fireball", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}

		else
		{
			world.playSoundAtEntity(entityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}

		if (!world.isRemote)
		{
			final EntityWeb web = new EntityWeb(entityPlayer, type);

			if (type == 2)
			{
				web.setFire(5);
			}

			world.spawnEntityInWorld(web);
		}

		if (!entityPlayer.capabilities.isCreativeMode)
		{
			switch (type)
			{
				case 0:
					entityPlayer.inventory.consumeInventoryItem(SpiderQueen.getInstance().itemWeb);
					break;
				case 1:
					entityPlayer.inventory.consumeInventoryItem(SpiderQueen.getInstance().itemPoisonWeb);
					break;
				case 2:
					entityPlayer.inventory.consumeInventoryItem(SpiderQueen.getInstance().itemFlameWeb);
					break;
			}
		}

		return itemStack;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		switch (type)
		{
			case 0:
				itemIcon = iconRegister.registerIcon("spiderqueen:Web");
				break;
			case 1:
				itemIcon = iconRegister.registerIcon("spiderqueen:WebPoison");
				break;
			case 2:
				itemIcon = iconRegister.registerIcon("spiderqueen:WebFlame");
				break;
		}
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		if (type == 0)
		{
			list.add("Cocoons enemies and");
			list.add("creates climbable webs.");
		}
		
		else if (type == 1)
		{
			list.add(Color.GREEN + "Poison");
		}

		else if (type == 2)
		{
			list.add(Color.GRAY + "Extinguishes lava");
			list.add(Color.GRAY + "Cocoons Ghasts");
			list.add(Color.GOLD + "Fire");
		}
	}
}
