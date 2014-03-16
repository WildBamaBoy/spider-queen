// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntityMand;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World, AxisAlignedBB, 
//            Entity

public class BlockMSeed extends Block
{

    protected BlockMSeed(int i, int j)
    {
        super(i, j, Material.ground);
        setBlockBounds(0F, 0F, 0F, 1.0F, 0.02F, 1.0F);
        setTickRandomly(true);
    }
	
    public void onBlockAdded(World world, int i, int j, int k)
    {
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
    }
	
	public void updateTick(World world, int i, int j, int k, Random random)
    {
			int meta = world.getBlockMetadata(i,j,k);
			meta++;
			if(meta > 12)
			{
					EntityMand entCocoon = new EntityMand(world);
					entCocoon.posX = i + 0.5D;
					entCocoon.posY = j + 1D;
					entCocoon.posZ = k + 0.5D;
					entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
					entCocoon.setFriendly(true);
					
					world.spawnEntityInWorld(entCocoon);
					
				world.setBlockWithNotify(i,j,k,0);
			}
			else
			{
				world.setBlockMetadata(i,j,k,meta);
			}
			
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
	}
	
    public int tickRate()
    {
        return 80;
    }
	
    public int quantityDropped(Random random)
    {
        return 1;
    }

    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return getBlockTextureFromSide(l);
    }

    public int getBlockTextureFromSide(int i)
    {
		return mod_SpiderQueen.texx[13];
	}

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        if(!super.canPlaceBlockAt(world, i, j, k))
        {
            return false;
        } else
        {
            return canBlockStay(world, i, j, k);
        }
    }
	
	
    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        if(!canBlockStay(world, i, j, k))
        {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k),1);
            world.setBlockWithNotify(i, j, k, 0);
        }
    }

    public boolean canBlockStay(World world, int i, int j, int k)
    {
		if(world.getBlockId(i, j - 1, k) == Block.dirt.blockID || world.getBlockId(i, j - 1, k) == Block.grass.blockID || world.getBlockId(i, j - 1, k) == Block.tilledField.blockID)
        {
			return true;
		}
		
		return false;
    }

}
