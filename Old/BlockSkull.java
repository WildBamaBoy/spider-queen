// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntityFSkeleton;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World, AxisAlignedBB, 
//            Entity

public class BlockSkull extends BlockContainer
{

    protected BlockSkull(int i, int j)
    {
        super(i, j, Material.ground);
		float f = 0.25F;
        setBlockBounds(f, 0F, f, 1.0F - f, 1.0F - f * 2F, 1.0F - f);
        setTickRandomly(true);
    }

    public int tickRate()
    {
        return 80;
    }
	
    public void onBlockAdded(World world, int i, int j, int k)
    {
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
		
		if(world.getBlockId(i, j - 1, k) == Block.torchWood.blockID)
        {
			world.setBlockMetadataWithNotify(i,j,k,1);
		}
    }
	
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        int meta = world.getBlockMetadata(i,j,k);
			meta++;
			if(meta > 13)
			{
				mod_SpiderQueen.outputTxt = "Your offering was accepted.";
				mod_SpiderQueen.likeChange("skeleton",2);
				if(mod_SpiderQueen.SkeletonLike > 9)
				{
					mod_SpiderQueen.SkeletonLike = 0;
					mod_SpiderQueen.outputTxt = "The skeletons have given you one of their own.";
					
					EntityFSkeleton entCocoon = new EntityFSkeleton(world);//, entityplayer.posX, entityplayer.posY, entityplayer.posZ, entityplayer.rotationYaw - 90F);//,itemstack.getItemDamage());
					entCocoon.posX = i - MathHelper.cos((entCocoon.rotationYaw / 180F) * 3.141593F) * 0.16F;
					entCocoon.posY = j + 1;
					entCocoon.posZ = k;
					entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
					entCocoon.setRotation(entCocoon.rotationYaw - 90F,0F);
					//entCocoon.spiderfriend = true;
					world.spawnEntityInWorld(entCocoon);
				}
				
				Random rm = new Random();
				for(int ok = 0; ok < 32; ok++)
				{
					world.spawnParticle("reddust", i + (rm.nextFloat() - 0.5f), j + (rm.nextFloat() - 0.5f), k + (rm.nextFloat() - 0.5f), (rm.nextFloat()-0.5f)*3f, (rm.nextFloat()-0.5f)*3f, (rm.nextFloat()-0.5f)*3f);
				}
				world.setBlockWithNotify(i,j,k,0);
			}
			else
			{
				world.setBlockMetadata(i,j,k,meta);
			}
			
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
    }
	
	
    public int idDropped(int i, Random random, int j)
    {
		return mod_SpiderQueen.itemSkull.shiftedIndex;
    }
	
    public TileEntity getBlockEntity()
    {
        return new TileEntityHBait();
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }
	
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
		if(l == 2 & iblockaccess.getBlockMetadata(i,j,k) == 1) { return mod_SpiderQueen.texx[10]; }
        return getBlockTextureFromSide(l);
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1) { return mod_SpiderQueen.texx[9]; }
        if(i == 0) { return mod_SpiderQueen.texx[7]; }
        if(i == 2) { return mod_SpiderQueen.texx[6]; }
		return mod_SpiderQueen.texx[8];
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
	
		if(world.getBlockId(i, j - 1, k) == Block.torchWood.blockID)
        {
			world.setBlockMetadataWithNotify(i,j,k,1);
		}
		
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
		
		if(world.getBlockId(i, j - 1, k) == Block.torchWood.blockID)
        {
			world.setBlockMetadataWithNotify(i,j,k,1);
            return true;
        }
		
		return false;
    }

}
