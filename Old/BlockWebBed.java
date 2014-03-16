package spiderqueen.old.block;
import java.util.Random;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer.EnumStatus;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockWebBed extends Block
{

    protected BlockWebBed(int i, int j)
    {
        super(i, j, Material.ground);
        blockIndexInTexture = j;
        setBlockBounds(0.0F, 0.4F, 0.0F, 1.0F, 0.6F, 1.0F);
    }
	
	public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return mod_SpiderQueen.texx[1];//getBlockTextureFromSideAndMetadata(l, iblockaccess.getBlockMetadata(i, j, k));
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        return mod_SpiderQueen.texx[1];//return getBlockTextureFromSide(i);
    }

    public int getBlockTextureFromSide(int i)
    {
        return mod_SpiderQueen.texx[1];//return blockIndexInTexture;
    }
	
	
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.web.blockID;
    }
	
    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
		boolean die = false;
		if(world.getBlockId(i-1,j,k) == 0) { die = true; }
		if(world.getBlockId(i+1,j,k) == 0) { die = true; }
		if(world.getBlockId(i,j,k-1) == 0) { die = true; }
		if(world.getBlockId(i,j,k+1) == 0) { die = true; }
		
		if(die)
		{
			dropBlockAsItem(world,i,j,k,Block.web.blockID,1);
			world.setBlockWithNotify(i,j,k,0);
		}
    }
	
	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
		EnumStatus enumstatus = entityplayer.sleepInWebBedAt(i, j, k);
        if(enumstatus == EnumStatus.OK)
        {
            entityplayer.addChatMessage("Sleep.");
            //setBedOccupied(world, i, j, k, true);
            return true;
        }
        if(enumstatus == EnumStatus.NOT_POSSIBLE_NOW)
        {
            entityplayer.addChatMessage("tile.bed.noSleep");
        }
		return true;
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
}
