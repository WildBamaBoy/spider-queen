// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.block;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntityJack;


// Referenced classes of package net.minecraft.src:
//            Block, Material, World, EntityLiving, 
//            MathHelper

public class BlockJack extends Block
{

    protected BlockJack(int i, int j)
    {
        super(i, Material.pumpkin);
        blockIndexInTexture = j;
        setTickRandomly(true);
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if(i == 1)
        {
            return mod_SpiderQueen.texx[18];
        }
        if(i == 0)
        {
            return mod_SpiderQueen.texx[18];
        }
        int k = mod_SpiderQueen.texx[17];
        if(j == 2 && i == 2)
        {
            return k;
        }
        if(j == 3 && i == 5)
        {
            return k;
        }
        if(j == 0 && i == 3)
        {
            return k;
        }
        if(j == 1 && i == 4)
        {
            return k;
        } else
        {
            return mod_SpiderQueen.texx[16];
        }
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1)
        {
            return mod_SpiderQueen.texx[18];
        }
        if(i == 0)
        {
            return mod_SpiderQueen.texx[18];
        }
        if(i == 3)
        {
            return mod_SpiderQueen.texx[17];
        } else
        {
            return mod_SpiderQueen.texx[16];
        }
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
        super.onBlockAdded(world, i, j, k);
    }

	
    public int tickRate()
    {
        return 60;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
		if(!world.isDaytime())
		{
			EntityJack entCocoon = new EntityJack(world);
			entCocoon.posX = i + 0.5D;
			entCocoon.posY = j + 1.5D;
			entCocoon.posZ = k + 0.5D;
			entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
					
			world.spawnEntityInWorld(entCocoon);
			world.setBlockWithNotify(i,j,k,0);
			return;
		}
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
    }
	
    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
		return true;
        //int l = world.getBlockId(i, j, k);
        //return (l == 0 || Block.blocksList[l].blockMaterial.getIsGroundCover()) && world.isBlockNormalCube(i, j - 1, k);
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(i, j, k, l);
    }

}
