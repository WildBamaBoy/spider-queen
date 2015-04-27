package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.entity.EntityJack;

public class BlockJack extends Block
{
	protected BlockJack()
	{
		super(Material.gourd);
		this.setTickRandomly(true);
	}

	public int tickRate()
	{
		return 60;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if(side == 1)
		{
			return SQR.texx[18];
		}
		
		if(side == 0)
		{
			return SQR.texx[18];
		}
		
		final IIcon k = SQR.texx[17];
		
		if(meta == 2 && side == 2)
		{
			return k;
		}
		
		if(meta == 3 && side == 5)
		{
			return k;
		}
		
		if(meta == 0 && side == 3)
		{
			return k;
		}
		
		if(meta == 1 && side == 4)
		{
			return k;
		} 
		
		else
		{
			return SQR.texx[16];
		}
	}

//	@Override 										Final, redirects to getIcon(int, int)
//	public IIcon getIcon(int side, int meta)
//	{
//		if(i == 1)
//		{
//			return SQR.texx[18];
//		}
//		if(i == 0)
//		{
//			return SQR.texx[18];
//		}
//		if(i == 3)
//		{
//			return SQR.texx[17];
//		} else
//		{
//			return SQR.texx[16];
//		}
//	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate());
		super.onBlockAdded(world, x, y, z);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if(!world.isDaytime())
		{
			final EntityJack entCocoon = new EntityJack(world);
			entCocoon.posX = x +0.5D;
			entCocoon.posY = y + 1.5D;
			entCocoon.posZ = z + 0.5D;
			entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);

			world.spawnEntityInWorld(entCocoon);
			world.setBlockToAir(x,y,z);
			return;
		}
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate());
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return true;
		//int l = world.getBlock(x, y, z);
		//return (l == 0 || Blocks.blocksList[l].blockMaterial.getIsGroundCover()) && world.isBlockNormalCube(x, y - 1, z);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack stack)
	{
		final int l = MathHelper.floor_double(entityliving.rotationYaw * 4F / 360F + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}

}
