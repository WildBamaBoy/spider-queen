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

public class BlockBeeHive extends BlockContainer
{

    protected BlockBeeHive(int i, int j)
    {
        super(i, j, Material.rock);
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityBeeHive();
    }
	
    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
		mod_SpiderQueen.hiveAlert = true;
		mod_SpiderQueen.pissOffBees(world, null, i, j, k, 64F);
		dropBlockAsItem(world, i, j, k, l,1);
    }
	
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return mod_SpiderQueen.texx[2];
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 0) { return mod_SpiderQueen.texx[4]; }
		return mod_SpiderQueen.texx[2];
	}

    public int idDropped(int i, Random random, int j)
    {
        return mod_SpiderQueen.itemNectar.shiftedIndex;
    }

    public int quantityDropped(Random random)
    {
        return 4;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
}
