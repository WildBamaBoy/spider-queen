package spiderqueen.old.block;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntityFriendlySpider;
public class BlockPoisonWeb extends Block
{

    public BlockPoisonWeb(int i, int j)
    {
        super(i, j, Material.plants);
		sting = 0;
    }
	
    
    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
		if(world.getBlockId(i-1,j,k) != 0) { return; }
		if(world.getBlockId(i+1,j,k) != 0) { return; }
		if(world.getBlockId(i,j-1,k) != 0) { return; }
		if(world.getBlockId(i,j+1,k) != 0) { return; }
		if(world.getBlockId(i,j,k-1) != 0) { return; }
		if(world.getBlockId(i,j,k+1) != 0) { return; }
		world.setBlockWithNotify(i,j,k,0);
		dropBlockAsItem(world,i,j,k,0,1);
    }
	
	public int getBlockTextureFromSide(int i)
    {
        return mod_SpiderQueen.texx[23];
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
	
    public int quantityDropped(Random random)
    {
        return 1;
    }
	
	public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f)
    {
        if(world.isRemote)
        {
            return;
        }
        int i1 = 1 + world.getBlockMetadata(i,j,k);
        for(int j1 = 0; j1 < i1; j1++)
        {
            int k1 = idDropped(l, world.rand, 1);
            if(k1 > 0)
            {
                dropBlockAsItem_do(world, i, j, k, new ItemStack(k1, 1, damageDropped(l)));
            }
        }
    }
	
	
    public void onBlockAdded(World world, int i, int j, int k)
    {
		onNeighborBlockChange(world,i,j,k,0);
    }
	
    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
		if(world.getBlockId(i-1,j,k) != 0) { return true; }
		if(world.getBlockId(i+1,j,k) != 0) { return true; }
		if(world.getBlockId(i,j-1,k) != 0) { return true; }
		if(world.getBlockId(i,j+1,k) != 0) { return true; }
		if(world.getBlockId(i,j,k-1) != 0) { return true; }
		if(world.getBlockId(i,j,k+1) != 0) { return true; }
        return false;
    }
	
    public int idDropped(int i, Random random, int j)
    {
        return mod_SpiderQueen.itemPWeb.shiftedIndex;
    }
	
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		boolean dont = false;
		if(entity instanceof EntityPlayer) { dont = true; }
		if(entity instanceof EntitySpider) { dont = true; }
		if(entity instanceof EntityFriendlySpider) { dont = true; }
		
		if(dont == false)
		{
			if(sting > 0) { sting--; }
			if(entity instanceof EntityLiving)
			{
				sting = 10;
				EntityLiving el = (EntityLiving)entity;
				el.attackEntityFrom(DamageSource.fall, 1);
			}
			
			
			entity.motionX = entity.motionX * -0.1D;
			entity.motionZ = entity.motionZ * -0.1D;
			entity.motionY = entity.motionY * 0.1D;
		}
    }
	
	public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        return mod_SpiderQueen.texx[23];
    }
	
    public int getRenderType()
    {
        return mod_SpiderQueen.id;
    }
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }
	
	private int sting;
}
