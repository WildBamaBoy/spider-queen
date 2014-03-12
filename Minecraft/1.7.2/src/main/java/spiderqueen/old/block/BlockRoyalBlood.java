// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World, AxisAlignedBB, 
//            Entity

public class BlockRoyalBlood extends BlockContainer
{

    protected BlockRoyalBlood(int i, int j)
    {
        super(i, j, Material.ground);
        setBlockBounds(0F, 0F, 0F, 1.0F, 0.02F, 1.0F);
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        
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
		return mod_SpiderQueen.texx[21];
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
	
    public TileEntity getBlockEntity()
    {
        return new TileEntityHBait();
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
        if(world.getBlockMaterial(i, j - 1, k).isSolid())
        {
            return true;
        }
		
		
		return false;
    }

}
