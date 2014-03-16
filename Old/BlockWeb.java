package spiderqueen.old.block;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntityFly;
import spiderqueen.old.entity.EntityFriendlySpider;
public class BlockWeb extends Block
{

    public BlockWeb(int i, int j)
    {
        super(i, j, Material.plants);
    }

	
    public int idDropped(int i, Random random, int j)
    {
        return mod_SpiderQueen.itemWeb.shiftedIndex;
    }
	
	
    public int quantityDropped(Random random)
    {
        return 3;
    }
	
	public void checkForBed(World world, int x, int y, int z, int itr)
	{
		int ww = Block.web.blockID;
		int ll = Block.wood.blockID;
		
		if(world.getBlockId(x,y,z) != ww) { return; }
		
		int cc = 0;
		int wc = 0;
		
		if(world.getBlockId(x-1,y,z-1) == ww) { cc++; }
		if(world.getBlockId(x-1,y,z) == ww) { cc++; }
		if(world.getBlockId(x-1,y,z+1) == ww) { cc++; }
		if(world.getBlockId(x,y,z-1) == ww) { cc++; }
		if(world.getBlockId(x,y,z) == ww) { cc++; }
		if(world.getBlockId(x,y,z+1) == ww) { cc++; }
		if(world.getBlockId(x+1,y,z-1) == ww) { cc++; }
		if(world.getBlockId(x+1,y,z) == ww) { cc++; }
		if(world.getBlockId(x+1,y,z+1) == ww) { cc++; }
		
		if(world.getBlockId(x-2,y,z-2) == ll) { wc++; }
		if(world.getBlockId(x-2,y,z-1) == ll) { wc++; }
		if(world.getBlockId(x-2,y,z) == ll) { wc++; }
		if(world.getBlockId(x-2,y,z+1) == ll) { wc++; }
		if(world.getBlockId(x-2,y,z+2) == ll) { wc++; }
		if(world.getBlockId(x+2,y,z-2) == ll) { wc++; }
		if(world.getBlockId(x+2,y,z-1) == ll) { wc++; }
		if(world.getBlockId(x+2,y,z) == ll) { wc++; }
		if(world.getBlockId(x+2,y,z+1) == ll) { wc++; }
		if(world.getBlockId(x+2,y,z+2) == ll) { wc++; }
		if(world.getBlockId(x-2,y,z-2) == ll) { wc++; }
		if(world.getBlockId(x-1,y,z-2) == ll) { wc++; }
		if(world.getBlockId(x,y,z-2) == ll) { wc++; }
		if(world.getBlockId(x+1,y,z-2) == ll) { wc++; }
		if(world.getBlockId(x+2,y,z-2) == ll) { wc++; }
		if(world.getBlockId(x-2,y,z+2) == ll) { wc++; }
		if(world.getBlockId(x-1,y,z+2) == ll) { wc++; }
		if(world.getBlockId(x,y,z+2) == ll) { wc++; }
		if(world.getBlockId(x+1,y,z+2) == ll) { wc++; }
		if(world.getBlockId(x+2,y,z+2) == ll) { wc++; }
		
		if(cc == 9 & wc == 20)
		{
			world.setBlock(x-1,y,z-1,mod_SpiderQueen.webBed.blockID);
			world.setBlock(x-1,y,z,mod_SpiderQueen.webBed.blockID);
			world.setBlock(x-1,y,z+1,mod_SpiderQueen.webBed.blockID);
			world.setBlock(x,y,z-1,mod_SpiderQueen.webBed.blockID);
			world.setBlock(x,y,z,mod_SpiderQueen.webBed.blockID);
			world.setBlock(x,y,z+1,mod_SpiderQueen.webBed.blockID);
			world.setBlock(x+1,y,z-1,mod_SpiderQueen.webBed.blockID);
			world.setBlock(x+1,y,z,mod_SpiderQueen.webBed.blockID);
			world.setBlock(x+1,y,z+1,mod_SpiderQueen.webBed.blockID);
		}
		else
		{
			if(itr < 3)
			{
				checkForBed(world,x-1,y,z-1,itr+1);
				checkForBed(world,x-1,y,z,itr+1);
				checkForBed(world,x-1,y,z+1,itr+1);
				checkForBed(world,x,y,z-1,itr+1);
				checkForBed(world,x,y,z+1,itr+1);
				checkForBed(world,x+1,y,z-1,itr+1);
				checkForBed(world,x+1,y,z,itr+1);
				checkForBed(world,x+1,y,z+1,itr+1);
			}
		}
	}
	
    public void onBlockAdded(World world, int i, int j, int k)
    {
		checkForBed(world,i,j,k,0);
    }
	
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		if(entity instanceof EntitySpider) { return; }
		if(entity instanceof EntityFriendlySpider) { return; }
		if(entity instanceof EntityPlayer) { return; }
		if(entity instanceof EntityFly)
		{
			EntityFly el = (EntityFly)entity;
			el.setCocooned(true);
		}
		
		if(entity instanceof EntityGhast)
		{
			EntityGhast gg = (EntityGhast)entity;
			gg.webHit++;
			if(gg.webHit > 2)
			{
				entity.isInWeb = true;
			}
			return;
		}
		
        entity.isInWeb = true;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public int getRenderType()
    {
        return 1;
    }
}
