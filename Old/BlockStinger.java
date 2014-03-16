// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Block, World, Material, BlockGrass, 
//            AxisAlignedBB

public class BlockStinger extends Block
{

    protected BlockStinger(int i, int j)
    {
        super(i, Material.plants);
        blockIndexInTexture = j;
        setTickRandomly(true);
        setBlockBounds(0F, 0F, 0F, 1.0F, 0.02F, 1.0F);
    }
	
	public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
		if(entity instanceof EntityLiving)
		{
			EntityLiving el = (EntityLiving)entity;
			el.attackEntityFrom(DamageSource.fall,4);
		}
    }

	
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return mod_SpiderQueen.texx[22];
    }

    public int getBlockTextureFromSide(int i)
    {
		return mod_SpiderQueen.texx[22];
	}

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        return world.getBlockMaterial(i, j - 1, k).isSolid();
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        super.onNeighborBlockChange(world, i, j, k, l);
        func_268_h(world, i, j, k);
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        func_268_h(world, i, j, k);
    }

    protected final void func_268_h(World world, int i, int j, int k)
    {
        if(!world.getBlockMaterial(i, j - 1, k).isSolid())
        {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k),1);
            world.setBlockWithNotify(i, j, k, 0);
        }
    }

    public boolean canBlockStay(World world, int i, int j, int k)
    {
        return world.getBlockMaterial(i, j - 1, k).isSolid();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
    public int getRenderType()
    {
        return 1;
    }
}
