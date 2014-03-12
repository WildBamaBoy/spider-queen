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
import spiderqueen.old.entity.EntityFCreeper;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World, AxisAlignedBB, 
//            Entity

public class BlockHeart extends BlockContainer
{

    protected BlockHeart(int i, int j)
    {
        super(i, j, Material.ground);
        setBlockBounds(0F, 0F, 0F, 1.0F, 0.02F, 1.0F);
        setTickRandomly(true);
    }

    public int tickRate()
    {
        return 80;
    }
	
    public void onBlockAdded(World world, int i, int j, int k)
    {
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
    }
	
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        int meta = world.getBlockMetadata(i,j,k);
			meta++;
			if(meta > 13)
			{
				mod_SpiderQueen.outputTxt = "Your offering was accepted.";
				mod_SpiderQueen.likeChange("creeper",2);
				
				if(mod_SpiderQueen.CreeperLike > 9)
				{
					mod_SpiderQueen.CreeperLike = 0;
					mod_SpiderQueen.outputTxt = "The creepers have given you one of their own.";
					
					EntityFCreeper entCocoon = new EntityFCreeper(world);//, entityplayer.posX, entityplayer.posY, entityplayer.posZ, entityplayer.rotationYaw - 90F);//,itemstack.getItemDamage());
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
		return mod_SpiderQueen.texx[11];
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
