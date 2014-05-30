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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.entity.EntityOtherQueen;

import com.radixshock.radixcore.constant.Time;
import com.radixshock.radixcore.logic.LogicHelper;

public class BlockWeb extends Block
{
	/** 1 = Normal, 2 = Poison, 3 = Flame */
	private int type;

	public BlockWeb(int type)
	{
		super(Material.circuits);
		this.type = type;
		this.setHardness(0.2F);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public void onBlockAdded(World world, int posX, int posY, int posZ)
	{
		checkForBed(world, posX, posY, posZ, 0);
		onNeighborBlockChange(world, posX, posY, posZ, 0);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity)
	{
		if (entity instanceof EntitySpider || entity instanceof EntityHatchedSpider || entity instanceof EntityPlayer || entity instanceof EntityOtherQueen)
		{
			return;
		}

		else
		{
			if (type != 3)
			{
				entity.setInWeb();

				entity.motionX = entity.motionX * -0.1D;
				entity.motionZ = entity.motionZ * -0.1D;
				entity.motionY = entity.motionY * 0.1D;

				if (type == 2 && LogicHelper.getBooleanWithProbability(40) && entity instanceof EntityLivingBase)
				{
					final EntityLivingBase entityLiving = (EntityLivingBase)entity;
					entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, Time.SECOND * 5));
				}
			}

			else
			{
				if (entity instanceof EntityLiving)
				{
					world.playSoundAtEntity(entity, "fire.ignite", 1.0F, 1.0F);
					world.playSoundAtEntity(entity, "random.fizz", 1.0F, 1.0F);
					
					if (!world.isRemote)
					{
						entity.setFire(5);
						world.setBlock(posX, posY, posZ, Blocks.fire);

					}

					else
					{
						for (int i = 0; i < 32; i++)
						{
							final double motionX = world.rand.nextGaussian() / 2;
							final double motionY = world.rand.nextGaussian() / 2;
							final double motionZ = world.rand.nextGaussian() / 2;

							world.spawnParticle("smoke", posX, posY, posZ, motionX, motionY, motionZ);
						}
					}
				}
			}
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
		switch (type)
		{
			case 1: blockIcon = IIconRegister.registerIcon("spiderqueen:Web"); break;
			case 2: blockIcon = IIconRegister.registerIcon("spiderqueen:WebPoison"); break;
			case 3: blockIcon = IIconRegister.registerIcon("spiderqueen:WebFlame"); break;

		}
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
		final int meta = blockAccess.getBlockMetadata(posX, posY, posZ);
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

		if (!flag && canBePlacedOn(blockAccess.getBlock(posX, posY + 1, posZ)))
		{
			minY = Math.min(minY, 0.9375F);
			maxY = 1.0F;
			minX = 0.0F;
			maxX = 1.0F;
			minZ = 0.0F;
			maxZ = 1.0F;
		}

		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
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
		if (type == 1)
		{
			Block fillerBlock = SpiderQueen.getInstance().blockWebFull;
			Block outlineBlock = Blocks.log;

			if (world.getBlock(x,y,z) != fillerBlock) { return; }

			int fillerBlocksPresent = 0;
			int outlineBlocksPresent = 0;

			if (world.getBlock(x-1,y,z-1) == fillerBlock) { fillerBlocksPresent++; }
			if (world.getBlock(x-1,y,z) == fillerBlock)   { fillerBlocksPresent++; }
			if (world.getBlock(x-1,y,z+1) == fillerBlock) { fillerBlocksPresent++; }
			if (world.getBlock(x,y,z-1) == fillerBlock)   { fillerBlocksPresent++; }
			if (world.getBlock(x,y,z) == fillerBlock)     { fillerBlocksPresent++; }
			if (world.getBlock(x,y,z+1) == fillerBlock)   { fillerBlocksPresent++; }
			if (world.getBlock(x+1,y,z-1) == fillerBlock) { fillerBlocksPresent++; }
			if (world.getBlock(x+1,y,z) == fillerBlock)   { fillerBlocksPresent++; }
			if (world.getBlock(x+1,y,z+1) == fillerBlock) { fillerBlocksPresent++; }

			if (world.getBlock(x-2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z-1) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z) == outlineBlock)   { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z+1) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z-1) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z) == outlineBlock)   { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z+1) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-1,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x,y,z-2) == outlineBlock)   { outlineBlocksPresent++; }
			if (world.getBlock(x+1,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-1,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x,y,z+2) == outlineBlock)   { outlineBlocksPresent++; }
			if (world.getBlock(x+1,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }

			if (fillerBlocksPresent == 9 & outlineBlocksPresent == 20)
			{
				final EntityPlayer player = world.getClosestPlayer(x, y, z, 10);

				if (player != null)
				{
					player.triggerAchievement(SpiderQueen.getInstance().achievementCreateSpiderBed);
				}

				world.setBlock(x-1,y,z-1,SpiderQueen.getInstance().blockWebBed);
				world.setBlock(x-1,y,z,SpiderQueen.getInstance().blockWebBed);
				world.setBlock(x-1,y,z+1,SpiderQueen.getInstance().blockWebBed);
				world.setBlock(x,y,z-1,SpiderQueen.getInstance().blockWebBed);
				world.setBlock(x,y,z,SpiderQueen.getInstance().blockWebBed);
				world.setBlock(x,y,z+1,SpiderQueen.getInstance().blockWebBed);
				world.setBlock(x+1,y,z-1,SpiderQueen.getInstance().blockWebBed);
				world.setBlock(x+1,y,z,SpiderQueen.getInstance().blockWebBed);
				world.setBlock(x+1,y,z+1,SpiderQueen.getInstance().blockWebBed);
			}

			else
			{
				if (itr < 3)
				{
					checkForBed(world,x-1,y,z-1,itr+1);
					checkForBed(world,x-1,y,z,itr+1);
					checkForBed(world,x-1,y,z+1,itr+1);
					checkForBed(world,x,y,z-1,itr+1);
					checkForBed(world,x,y,z+1,itr+1);
					checkForBed(world,x+1,y,z-1,itr+1);
					checkForBed(world,x+1,y,z,itr+1);
					checkForBed(world,x+1,y,z+1,itr+1);
				}
			}
		}
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
