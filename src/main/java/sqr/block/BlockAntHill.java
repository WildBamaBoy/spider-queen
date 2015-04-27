package sqr.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.tile.TileEntityAntHill;

public class BlockAntHill extends BlockContainer
{
	protected BlockAntHill()
	{
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World worldObj, int i)
	{
		return new TileEntityAntHill();
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int l)
	{

		this.dropBlockAsItem(world, x, y, z, l,1);
	}

	@Override
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		this.blockIcon = SQR.texx[5];
	}

	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return null;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
