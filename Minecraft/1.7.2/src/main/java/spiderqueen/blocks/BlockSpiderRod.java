/*******************************************************************************
 * BlockSpiderRod.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpiderRod extends Block
{
	public BlockSpiderRod()
	{
		super(Material.plants);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public Item getItemDropped(int unknown, Random random, int unknown2)
	{
		return SpiderQueen.getInstance().itemSpiderRod;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int posX, int posY, int posZ, Random random)
	{
		final double particlePosX = posX + 0.5D + (random.nextFloat() - 0.5D) * random.nextGaussian();
		final double particlePosY = posY + 0.5F;
		final double particlePosZ = posZ + 0.5D + (random.nextFloat() - 0.5D) * random.nextGaussian();

		final double velX = 1.0D;
		double velY = 0.2D;
		double velZ = -0.1D;

		if (velY < 0.0D)
		{
			velY = 0.0D;
		}

		if (velZ < 0.0D)
		{
			velZ = 0.0D;
		}

		world.spawnParticle("reddust", particlePosX, particlePosY, particlePosZ, velX, velY, velZ);
	}

	@Override
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		blockIcon = IIconRegister.registerIcon("spiderqueen:SpiderRod");
	}
}
