package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.core.minecraft.ModBlocks;

public class BlockLantern extends Block
{
	protected BlockLantern()
	{
		super(Material.ground);
		final float f = 0.375F;

		//setBlockBounds(f, 0F, f, 1.0F - f, 1.0F - f * 2F, 1.0F - f);
		this.setBlockBounds(f, 0.63F, f, 1.0F - f, 1.0F, 1.0F - f);
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{

	}

	public Item getItemDropped(int i, Random random, int j)
	{
		return Item.getItemFromBlock(ModBlocks.lantern);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if(side == 1 || side == 0) 
		{ 
			return SQR.texx[15]; 
		}
		
		return SQR.texx[14];
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if(!super.canPlaceBlockAt(world, x, y, z))
		{
			return false;
		} else
		{
			return this.canBlockStay(world, x, y, z);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		if(!this.canBlockStay(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z),1);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if(world.getBlock(x, y + 1, z).getMaterial().isSolid())
		{
			return true;
		}

		return false;
	}

}
