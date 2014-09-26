/*******************************************************************************
 * ItemSpiderEgg.java
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
import net.minecraft.world.World;
import sqr.core.SpiderQueen;
import sqr.entity.EntitySpiderEgg;

public class ItemSpiderEgg extends AbstractItemSpawner
{
	public ItemSpiderEgg()
	{
		super();
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	@Override
	public void registerIcons(IIconRegister IIconRegister)
	{
		itemIcon = IIconRegister.registerIcon("spiderqueen:SpiderEgg");
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
			player.triggerAchievement(SpiderQueen.getInstance().achievementLayEgg);
			
			final EntitySpiderEgg entitySpiderEgg = new EntitySpiderEgg(world, player.getCommandSenderName());
			entitySpiderEgg.setLocationAndAngles(posX, posY, posZ, world.rand.nextFloat() * 360F, 0.0F);

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(entitySpiderEgg);
			}

			return true;
		}
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		list.add("Consumes a nearby cocoon");
		list.add("when ready to grow.");
	}
}
