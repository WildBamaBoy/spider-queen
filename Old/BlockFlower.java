// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Block, World, Material, BlockGrass, 
//            AxisAlignedBB

public class BlockFlower extends BlockContainer
{

    protected BlockFlower(int i, int j, Material par3Material)
    {
        super(i, par3Material);
        blockIndexInTexture = j;
        setTickRandomly(true);
        float f = 0.2F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3F, 0.5F + f);
    }

    protected BlockFlower(int par1, int par2)
    {
        this(par1, par2, Material.plants);
    }
	
    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        return super.canPlaceBlockAt(world, i, j, k) && canThisPlantGrowOnThisBlockID(world.getBlockId(i, j - 1, k));
    }

    protected boolean canThisPlantGrowOnThisBlockID(int i)
    {
        return i == Block.grass.blockID || i == Block.dirt.blockID || i == Block.tilledField.blockID;
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
        if(!canBlockStay(world, i, j, k))
        {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k),1);
            world.setBlockWithNotify(i, j, k, 0);
        }
    }

    public boolean canBlockStay(World world, int i, int j, int k)
    {
        return (world.getFullBlockLightValue(i, j, k) >= 8 || world.canBlockSeeTheSky(i, j, k)) && canThisPlantGrowOnThisBlockID(world.getBlockId(i, j - 1, k));
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
    public TileEntity getBlockEntity()
    {
        return new TileEntityFlower();
    }
	
    public int getRenderType()
    {
        return 1;
    }
}
