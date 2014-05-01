/*******************************************************************************
 * BlockWeb.java
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import spiderqueen.entity.EntityHatchedSpider;

public class BlockWeb extends Block
{	
	public BlockWeb() 
	{
		super(Material.circuits);
	}

	public int quantityDropped(Random random)
	{
		return 0;
	}

	public void checkForBed(World world, int x, int y, int z, int itr)
	{
		//FIXME
//		Block ww = SpiderQueen.getInstance().blockWeb;
//		Block ll = Blocks.log;
//
//		if(world.getBlock(x,y,z) != ww) { return; }
//
//		int cc = 0;
//		int wc = 0;
//
//		if(world.getBlock(x-1,y,z-1) == ww) { cc++; }
//		if(world.getBlock(x-1,y,z) == ww) { cc++; }
//		if(world.getBlock(x-1,y,z+1) == ww) { cc++; }
//		if(world.getBlock(x,y,z-1) == ww) { cc++; }
//		if(world.getBlock(x,y,z) == ww) { cc++; }
//		if(world.getBlock(x,y,z+1) == ww) { cc++; }
//		if(world.getBlock(x+1,y,z-1) == ww) { cc++; }
//		if(world.getBlock(x+1,y,z) == ww) { cc++; }
//		if(world.getBlock(x+1,y,z+1) == ww) { cc++; }
//
//		if(world.getBlock(x-2,y,z-2) == ll) { wc++; }
//		if(world.getBlock(x-2,y,z-1) == ll) { wc++; }
//		if(world.getBlock(x-2,y,z) == ll) { wc++; }
//		if(world.getBlock(x-2,y,z+1) == ll) { wc++; }
//		if(world.getBlock(x-2,y,z+2) == ll) { wc++; }
//		if(world.getBlock(x+2,y,z-2) == ll) { wc++; }
//		if(world.getBlock(x+2,y,z-1) == ll) { wc++; }
//		if(world.getBlock(x+2,y,z) == ll) { wc++; }
//		if(world.getBlock(x+2,y,z+1) == ll) { wc++; }
//		if(world.getBlock(x+2,y,z+2) == ll) { wc++; }
//		if(world.getBlock(x-2,y,z-2) == ll) { wc++; }
//		if(world.getBlock(x-1,y,z-2) == ll) { wc++; }
//		if(world.getBlock(x,y,z-2) == ll) { wc++; }
//		if(world.getBlock(x+1,y,z-2) == ll) { wc++; }
//		if(world.getBlock(x+2,y,z-2) == ll) { wc++; }
//		if(world.getBlock(x-2,y,z+2) == ll) { wc++; }
//		if(world.getBlock(x-1,y,z+2) == ll) { wc++; }
//		if(world.getBlock(x,y,z+2) == ll) { wc++; }
//		if(world.getBlock(x+1,y,z+2) == ll) { wc++; }
//		if(world.getBlock(x+2,y,z+2) == ll) { wc++; }
//
//		if(cc == 9 & wc == 20)
//		{
//			world.setBlock(x-1,y,z-1,SpiderQueen.getInstance().blockWebBed);
//			world.setBlock(x-1,y,z,SpiderQueen.getInstance().blockWebBed);
//			world.setBlock(x-1,y,z+1,SpiderQueen.getInstance().blockWebBed);
//			world.setBlock(x,y,z-1,SpiderQueen.getInstance().blockWebBed);
//			world.setBlock(x,y,z,SpiderQueen.getInstance().blockWebBed);
//			world.setBlock(x,y,z+1,SpiderQueen.getInstance().blockWebBed);
//			world.setBlock(x+1,y,z-1,SpiderQueen.getInstance().blockWebBed);
//			world.setBlock(x+1,y,z,SpiderQueen.getInstance().blockWebBed);
//			world.setBlock(x+1,y,z+1,SpiderQueen.getInstance().blockWebBed);
//		}
//		else
//		{
//			if (itr < 3)
//			{
//				checkForBed(world,x-1,y,z-1,itr+1);
//				checkForBed(world,x-1,y,z,itr+1);
//				checkForBed(world,x-1,y,z+1,itr+1);
//				checkForBed(world,x,y,z-1,itr+1);
//				checkForBed(world,x,y,z+1,itr+1);
//				checkForBed(world,x+1,y,z-1,itr+1);
//				checkForBed(world,x+1,y,z,itr+1);
//				checkForBed(world,x+1,y,z+1,itr+1);
//			}
//		}
	}

	public void onBlockAdded(World world, int i, int j, int k)
	{
		checkForBed(world,i,j,k,0);
	}

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		if (entity instanceof EntitySpider || entity instanceof EntityHatchedSpider || entity instanceof EntityPlayer) 
		{ 
			return; 
		}

		else
		{
			entity.setInWeb();

			entity.motionX = entity.motionX * -0.1D;
			entity.motionZ = entity.motionZ * -0.1D;
			entity.motionY = entity.motionY * 0.1D;
		}
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}

	public int getRenderType()
	{
		return 1;
	}

	@Override
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		blockIcon = IIconRegister.registerIcon("spiderqueen:Web");
	}

	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) 
	{
		if (entity instanceof EntityPlayer || entity instanceof EntitySpider || entity instanceof EntityHatchedSpider)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}	
}
