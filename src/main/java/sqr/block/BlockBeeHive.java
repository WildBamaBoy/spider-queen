package sqr.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.core.minecraft.ModItems;
import sqr.tile.TileEntityBeeHive;

public class BlockBeeHive extends BlockContainer
{
	protected BlockBeeHive()
	{
		super(Material.rock);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i)
	{
		return new TileEntityBeeHive();
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int l)
	{
		// TODO
		// SQR.hiveAlert = true;
		// SQR.pissOffBees(world, null, x, y, z, 64F);
		this.dropBlockAsItem(world, x, y, z, l, 1);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		this.blockIcon = SQR.texx[2];
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return ModItems.itemNectar;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 4;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
