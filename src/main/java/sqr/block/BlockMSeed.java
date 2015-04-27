package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.entity.EntityMand;

public class BlockMSeed extends Block
{
	protected BlockMSeed()
	{
		super(Material.ground);
		this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.02F, 1.0F);
		this.setTickRandomly(true);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate());
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z);
		meta++;
		if (meta > 12)
		{
			final EntityMand entCocoon = new EntityMand(world);
			entCocoon.posX = x + 0.5D;
			entCocoon.posY = y + 1D;
			entCocoon.posZ = z + 0.5D;
			entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
			// entCocoon.setFriendly(true);
			
			world.spawnEntityInWorld(entCocoon);
			
			world.setBlockToAir(x, y, z);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		}
		
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate());
	}
	
	public int tickRate()
	{
		return 80;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = SQR.texx[13];
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
		if (!super.canPlaceBlockAt(world, x, y, z))
		{
			return false;
		}
		else
		{
			return this.canBlockStay(world, x, y, z);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		if (!this.canBlockStay(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 1);
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y - 1, z) == Blocks.dirt || world.getBlock(x, y - 1, z) == Blocks.grass || world.getBlock(x, y - 1, z) == Blocks.farmland)
		{
			return true;
		}
		
		return false;
	}
	
}
