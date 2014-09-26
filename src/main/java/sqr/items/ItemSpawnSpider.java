/*******************************************************************************
 * ItemSpawnSpider.java
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import sqr.core.SpiderQueen;
import sqr.entity.EntityHatchedSpider;
import sqr.enums.EnumCocoonType;

import com.radixshock.radixcore.logic.LogicHelper;

public class ItemSpawnSpider extends AbstractItemSpawner
{
	public ItemSpawnSpider()
	{
		super();
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	@Override
	public void registerIcons(IIconRegister IIconRegister)
	{
		itemIcon = IIconRegister.registerIcon("spiderqueen:SpawnSpider");
	}

	@Override
	public boolean spawnEntity(World world, EntityPlayer player, double posX, double posY, double posZ)
	{
		if (world.isRemote)
		{
			return true;
		}

		else
		{
			final EntityHatchedSpider entityHatchedSpider = new EntityHatchedSpider(world, player.getCommandSenderName(), EnumCocoonType.getRandomCocoonType());
			final int spiderLevel = entityHatchedSpider.cocoonType == EnumCocoonType.VILLAGER ? 1 : LogicHelper.getNumberInRange(1, 3);
			entityHatchedSpider.setLocationAndAngles(posX, posY, posZ, world.rand.nextFloat() * 360F, 0.0F);
			
			if (!world.isRemote)
			{
				player.addChatMessage(new ChatComponentText("Spawned " + entityHatchedSpider.cocoonType.toString() + " spider at level " + entityHatchedSpider.level));
				world.spawnEntityInWorld(entityHatchedSpider);
			}

			return true;
		}
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		list.add("Spawns a random spider");
		list.add("at a random level.");
	}
}
