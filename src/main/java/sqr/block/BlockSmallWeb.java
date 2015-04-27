package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.core.minecraft.ModItems;
import sqr.entity.EntitySpiderEx;

public class BlockSmallWeb extends Block
{
	public BlockSmallWeb()
	{
		super(Material.plants);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		if(world.getBlock(x-1,y,z) != Blocks.air) { return; }
		if(world.getBlock(x+1,y,z) != Blocks.air) { return; }
		if(world.getBlock(x,y-1,z) != Blocks.air) { return; }
		if(world.getBlock(x,y+1,z) != Blocks.air) { return; }
		if(world.getBlock(x,y,z-1) != Blocks.air) { return; }
		if(world.getBlock(x,y,z+1) != Blocks.air) { return; }

		world.setBlockToAir(x, y, z);
		this.dropBlockAsItem(world,x,y,z,0,1);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return SQR.texx[0];
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int l, float f)
	{
		if (world.isRemote)
		{
			return;
		}

		final int i1 = 1 + world.getBlockMetadata(x,y,z);
		
		for (int j1 = 0; j1 < i1; j1++)
		{
			final Item item = this.getItemDropped(l, world.rand, 1);
			dropBlockAsItem(world, x, y, z, new ItemStack(item, 1, this.damageDropped(l)));
		}
	}


	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		this.onNeighborBlockChange(world, x, y, z, Blocks.air);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if(world.getBlock(x-1,y,z) != Blocks.air) { return true; }
		if(world.getBlock(x+1,y,z) != Blocks.air) { return true; }
		if(world.getBlock(x,y-1,z) != Blocks.air) { return true; }
		if(world.getBlock(x,y+1,z) != Blocks.air) { return true; }
		if(world.getBlock(x,y,z-1) != Blocks.air) { return true; }
		if(world.getBlock(x,y,z+1) != Blocks.air) { return true; }
		return false;
	}

	public Item getItemDropped(int i, Random random, int j)
	{
		return ModItems.itemWeb;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		boolean dont = false;
		if(entity instanceof EntityPlayer) { dont = true; }
		if(entity instanceof EntitySpider) { dont = true; }
		if(entity instanceof EntitySpiderEx) { dont = true; }

		if(dont == false)
		{
			entity.motionX = entity.motionX * -0.5D;
			entity.motionZ = entity.motionZ * -0.5D;
		}
	}

	@Override
	public int getRenderType()
	{
		return SQR.id;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}
}
