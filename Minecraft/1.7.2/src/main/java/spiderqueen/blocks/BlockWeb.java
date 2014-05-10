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
import net.minecraft.init.Blocks;
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

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	public void onBlockAdded(World world, int posX, int posY, int posZ)
	{
		checkForBed(world, posX, posY, posZ, 0);
		onNeighborBlockChange(world, posX, posY, posZ, 0);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity)
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
		return 20;
	}

	@Override
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		blockIcon = IIconRegister.registerIcon("spiderqueen:Web");
	}

	@Override
	public boolean isLadder(IBlockAccess world, int posX, int posY, int posZ, EntityLivingBase entity) 
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

	@Override
	public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ)
	{
		if (world.getBlock(posX - 1, posY, posZ) != Blocks.air) { return true; }
		if (world.getBlock(posX + 1, posY, posZ) != Blocks.air) { return true; }
		if (world.getBlock(posX, posY - 1, posZ) != Blocks.air) { return true; }
		if (world.getBlock(posX, posY + 1, posZ) != Blocks.air) { return true; }
		if (world.getBlock(posX, posY, posZ - 1) != Blocks.air) { return true; }
		if (world.getBlock(posX, posY, posZ + 1) != Blocks.air) { return true; }

		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		int meta = blockAccess.getBlockMetadata(posX, posY, posZ);
		float minX = 1.0F, minY = 1.0F, minZ = 1.0F;
		float maxX = 0.0F, maxY = 0.0F, maxZ = 0.0F;
		boolean flag = meta > 0;

		if ((meta & 2) != 0)
		{
			maxX = Math.max(maxX, 0.0625F);
			minX = 0.0F;
			minY = 0.0F;
			maxY = 1.0F;
			minZ = 0.0F;
			maxZ = 1.0F;
			flag = true;
		}

		if ((meta & 8) != 0)
		{
			minX = Math.min(minX, 0.9375F);
			maxX = 1.0F;
			minY = 0.0F;
			maxY = 1.0F;
			minZ = 0.0F;
			maxZ = 1.0F;
			flag = true;
		}

		if ((meta & 4) != 0)
		{
			maxZ = Math.max(maxZ, 0.0625F);
			minZ = 0.0F;
			minX = 0.0F;
			maxX = 1.0F;
			minY = 0.0F;
			maxY = 1.0F;
			flag = true;
		}

		if ((meta & 1) != 0)
		{
			minZ = Math.min(minZ, 0.9375F);
			maxZ = 1.0F;
			minX = 0.0F;
			maxX = 1.0F;
			minY = 0.0F;
			maxY = 1.0F;
			flag = true;
		}

		if (!flag && this.canBePlacedOn(blockAccess.getBlock(posX, posY + 1, posZ)))
		{
			minY = Math.min(minY, 0.9375F);
			maxY = 1.0F;
			minX = 0.0F;
			maxX = 1.0F;
			minZ = 0.0F;
			maxZ = 1.0F;
		}

		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	private void onNeighborBlockChange(World world, int posX, int posY, int posZ, int meta)
	{
		if (world.getBlock(posX - 1, posY, posZ) != Blocks.air) { return; }
		if (world.getBlock(posX + 1, posY, posZ) != Blocks.air) { return; }
		if (world.getBlock(posX, posY - 1, posZ) != Blocks.air) { return; }
		if (world.getBlock(posX, posY + 1, posZ) != Blocks.air) { return; }
		if (world.getBlock(posX, posY, posZ - 1) != Blocks.air) { return; }
		if (world.getBlock(posX, posY, posZ + 1) != Blocks.air) { return; }

		world.setBlock(posX, posY, posZ, Blocks.air);
	}

	private void checkForBed(World world, int x, int y, int z, int itr)
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

	private boolean canBePlacedOn(Block block)
	{
		if (block == Blocks.air)
		{
			return false;
		}

		else
		{
			return block.renderAsNormalBlock() && block.getMaterial().blocksMovement();
		}
	}
}