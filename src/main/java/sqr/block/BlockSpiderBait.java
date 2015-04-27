package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.tile.TileEntitySpiderBait;

public class BlockSpiderBait extends BlockContainer
{
	protected BlockSpiderBait(int i, int j)
	{
		super(Material.circuits);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i)
	{
		return new TileEntitySpiderBait();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return world.isBlockNormalCubeDefault(x, y - 1, z, false);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return SQR.texx[3];
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public Item getItemDropped(int i, Random random, int j)
	{
		//TODO Spider rod?
		return null;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		if (world.isRemote)
		{
			return;
		}
		
		boolean flag = false;
		
		if (!world.isBlockNormalCubeDefault(x, y - 1, z, false))
		{
			flag = true;
		}
		
		if (flag)
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z),1);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}
}
