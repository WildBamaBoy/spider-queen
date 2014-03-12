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
//            BlockContainer, TileEntityMobSpawner, Material, TileEntity

public class BlockSpiderBait extends BlockContainer
{

    protected BlockSpiderBait(int i, int j)
    {
        super(i, j, Material.circuits);
        blockIndexInTexture = 79;
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntitySpiderBait();
    }
	
    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        return world.isBlockNormalCube(i, j - 1, k);
    }
	
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return mod_SpiderQueen.texx[3];
    }

    public int getBlockTextureFromSide(int i)
    {
		return mod_SpiderQueen.texx[3];
	}
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
    public int idDropped(int i, Random random, int j)
    {
        return blockID;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        if(world.isRemote)
        {
            return;
        }
        boolean flag = false;
        if(!world.isBlockNormalCube(i, j - 1, k))
        {
            flag = true;
        }
		if(flag)
        {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k),1);
            world.setBlockWithNotify(i, j, k, 0);
		}
	}
	
    public int getRenderType()
    {
        return 1;
    }
}
