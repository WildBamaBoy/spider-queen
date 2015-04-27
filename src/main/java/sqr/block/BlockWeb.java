package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import sqr.core.minecraft.ModBlocks;
import sqr.core.minecraft.ModItems;
import sqr.entity.EntityFly;
import sqr.entity.EntitySpiderEx;

public class BlockWeb extends Block
{
	public BlockWeb()
	{
		super(Material.plants);
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return ModItems.web;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 3;
	}
	
	public void checkForBed(World world, int x, int y, int z, int itr)
	{
		final Block ww = Blocks.web;
		final Block ll = Blocks.log;
		
		if (world.getBlock(x, y, z) != ww)
		{
			return;
		}
		
		int cc = 0;
		int wc = 0;
		
		if (world.getBlock(x - 1, y, z - 1) == ww)
		{
			cc++;
		}
		if (world.getBlock(x - 1, y, z) == ww)
		{
			cc++;
		}
		if (world.getBlock(x - 1, y, z + 1) == ww)
		{
			cc++;
		}
		if (world.getBlock(x, y, z - 1) == ww)
		{
			cc++;
		}
		if (world.getBlock(x, y, z) == ww)
		{
			cc++;
		}
		if (world.getBlock(x, y, z + 1) == ww)
		{
			cc++;
		}
		if (world.getBlock(x + 1, y, z - 1) == ww)
		{
			cc++;
		}
		if (world.getBlock(x + 1, y, z) == ww)
		{
			cc++;
		}
		if (world.getBlock(x + 1, y, z + 1) == ww)
		{
			cc++;
		}
		
		if (world.getBlock(x - 2, y, z - 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x - 2, y, z - 1) == ll)
		{
			wc++;
		}
		if (world.getBlock(x - 2, y, z) == ll)
		{
			wc++;
		}
		if (world.getBlock(x - 2, y, z + 1) == ll)
		{
			wc++;
		}
		if (world.getBlock(x - 2, y, z + 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 2, y, z - 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 2, y, z - 1) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 2, y, z) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 2, y, z + 1) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 2, y, z + 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x - 2, y, z - 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x - 1, y, z - 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x, y, z - 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 1, y, z - 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 2, y, z - 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x - 2, y, z + 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x - 1, y, z + 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x, y, z + 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 1, y, z + 2) == ll)
		{
			wc++;
		}
		if (world.getBlock(x + 2, y, z + 2) == ll)
		{
			wc++;
		}
		
		if (cc == 9 & wc == 20)
		{
			world.setBlock(x - 1, y, z - 1, ModBlocks.webBed);
			world.setBlock(x - 1, y, z, ModBlocks.webBed);
			world.setBlock(x - 1, y, z + 1, ModBlocks.webBed);
			world.setBlock(x, y, z - 1, ModBlocks.webBed);
			world.setBlock(x, y, z, ModBlocks.webBed);
			world.setBlock(x, y, z + 1, ModBlocks.webBed);
			world.setBlock(x + 1, y, z - 1, ModBlocks.webBed);
			world.setBlock(x + 1, y, z, ModBlocks.webBed);
			world.setBlock(x + 1, y, z + 1, ModBlocks.webBed);
		}
		
		else
		{
			if (itr < 3)
			{
				this.checkForBed(world, x - 1, y, z - 1, itr + 1);
				this.checkForBed(world, x - 1, y, z, itr + 1);
				this.checkForBed(world, x - 1, y, z + 1, itr + 1);
				this.checkForBed(world, x, y, z - 1, itr + 1);
				this.checkForBed(world, x, y, z + 1, itr + 1);
				this.checkForBed(world, x + 1, y, z - 1, itr + 1);
				this.checkForBed(world, x + 1, y, z, itr + 1);
				this.checkForBed(world, x + 1, y, z + 1, itr + 1);
			}
		}
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		this.checkForBed(world, x, y, z, 0);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntitySpider)
		{
			return;
		}
		if (entity instanceof EntitySpiderEx)
		{
			return;
		}
		if (entity instanceof EntityPlayer)
		{
			return;
		}
		if (entity instanceof EntityFly)
		{
			final EntityFly el = (EntityFly) entity;
			el.setCocooned(true);
		}
		
		if (entity instanceof EntityGhast)
		{
			// TODO
			// final EntityGhast ghast = (EntityGhast)entity;
			// ghast.webHit++;
			//
			// if(ghast.webHit > 2)
			// {
			// entity.setInWeb();
			// }
			//
			// return;
		}
		
		entity.setInWeb();
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
}
