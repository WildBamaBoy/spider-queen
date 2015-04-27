package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sqr.core.SQR;

public class BlockStinger extends Block
{
	protected BlockStinger()
	{
		super(Material.plants);
		this.setTickRandomly(true);
		this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.02F, 1.0F);
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			final EntityLivingBase el = (EntityLivingBase) entity;
			el.attackEntityFrom(DamageSource.fall, 4);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return SQR.texx[22];
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return world.getBlock(x, y - 1, z).getMaterial().isSolid();
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		super.onNeighborBlockChange(world, x, y, z, b);
		this.func_268_h(world, x, y, z);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		this.func_268_h(world, x, y, z);
	}
	
	protected final void func_268_h(World world, int x, int y, int z)
	{
		if (!world.getBlock(x, y - 1, z).getMaterial().isSolid())
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 1);
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlock(x, y - 1, z).getMaterial().isSolid();
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
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
}
