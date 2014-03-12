// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.item;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


// Referenced classes of package net.minecraft.src:
//            Item, Material, Block, EntityPlayer, 
//            MathHelper, World, ItemStack

public class ItemSkull extends Item
{

    public ItemSkull(int i)
    {
        super(i);
        maxStackSize = 16;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if(l != 1)
        {
            return false;
        }
        j++;
        Block block = mod_SpiderQueen.bskull;
        if(!block.canPlaceBlockAt(world, i, j, k))
        {
            return false;
        }
        world.editingBlocks = true;
        world.setBlockAndMetadataWithNotify(i, j, k, block.blockID, 0);
        world.editingBlocks = false;
        world.notifyBlocksOfNeighborChange(i, j, k, block.blockID);
        itemstack.stackSize--;
        return true;
    }

    private Material doorMaterial;
}
