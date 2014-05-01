/*******************************************************************************
 * BlockPoisonWeb.java
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;

public class BlockPoisonWeb extends Block
{
	private int sting = 0;
	public BlockPoisonWeb()
	{
		super(Material.plants);
	}

	public void onNeighborBlockChange(World world, int i, int j, int k, int l)
	{
		if(world.getBlock(i-1,j,k) != Blocks.air) { return; }
		if(world.getBlock(i+1,j,k) != Blocks.air) { return; }
		if(world.getBlock(i,j-1,k) != Blocks.air) { return; }
		if(world.getBlock(i,j+1,k) != Blocks.air) { return; }
		if(world.getBlock(i,j,k-1) != Blocks.air) { return; }
		if(world.getBlock(i,j,k+1) != Blocks.air) { return; }
		world.setBlock(i,j,k, Blocks.air);
		dropBlockAsItem(world,i,j,k,0,1);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public int quantityDropped(Random random)
	{
		return 1;
	}

	public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f)
	{
		if(world.isRemote)
		{
			return;
		}
		
		int i1 = 1 + world.getBlockMetadata(i,j,k);
		
		for(int j1 = 0; j1 < i1; j1++)
		{
			dropBlockAsItem(world, i, j, k, new ItemStack(SpiderQueen.getInstance().blockPoisonWeb, 1, 0));
		}
	}

	public void onBlockAdded(World world, int i, int j, int k)
	{
		onNeighborBlockChange(world,i,j,k,0);
	}

	public boolean canPlaceBlockAt(World world, int i, int j, int k)
	{
		if(world.getBlock(i-1,j,k) != Blocks.air) { return true; }
		if(world.getBlock(i+1,j,k) != Blocks.air) { return true; }
		if(world.getBlock(i,j-1,k) != Blocks.air) { return true; }
		if(world.getBlock(i,j+1,k) != Blocks.air) { return true; }
		if(world.getBlock(i,j,k-1) != Blocks.air) { return true; }
		if(world.getBlock(i,j,k+1) != Blocks.air) { return true; }
		return false;
	}

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		boolean dont = false;
		if(entity instanceof EntityPlayer) { dont = true; }
		if(entity instanceof EntitySpider) { dont = true; }
		//if(entity instanceof EntityFriendlySpider) { dont = true; } //TODO

		if(dont == false)
		{
			if(sting > 0) { sting--; }
			if(entity instanceof EntityLiving)
			{
				sting = 10;
				EntityLiving el = (EntityLiving)entity;
				el.attackEntityFrom(DamageSource.fall, 1);
			}


			entity.motionX = entity.motionX * -0.1D;
			entity.motionZ = entity.motionZ * -0.1D;
			entity.motionY = entity.motionY * 0.1D;
		}
	}

	public int getRenderType()
	{
		return 1;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}

	@Override
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		blockIcon = IIconRegister.registerIcon("spiderqueen:WebPoison");
	}
}
