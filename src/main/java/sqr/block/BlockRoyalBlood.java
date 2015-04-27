package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.tile.TileEntityHBait;

public class BlockRoyalBlood extends BlockContainer
{
	protected BlockRoyalBlood()
	{
		super(Material.ground);
		this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.02F, 1.0F);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return SQR.texx[21];
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
		} 
		
		else
		{
			return this.canBlockStay(world, x, y, z);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i)
	{
		return new TileEntityHBait();
	}

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
		if(world.getBlock(x, y - 1, z).getMaterial().isSolid())
		{
			return true;
		}

		return false;
	}
}
