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

public class BlockAntHill extends BlockContainer
{

    protected BlockAntHill(int i, int j)
    {
        super(i, j, Material.rock);
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityAntHill();
    }
	
    public int getRenderType()
    {
        return 1;
    }
	
    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
	
		dropBlockAsItem(world, i, j, k, l,1);
    }
	
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return mod_SpiderQueen.texx[5];
    }

    public int getBlockTextureFromSide(int i)
    {
		return mod_SpiderQueen.texx[5];
	}

    public int idDropped(int i, Random random, int j)
    {
        return 0;
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
}
