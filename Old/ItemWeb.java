package spiderqueen.old.item;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntityWeb;

public class ItemWeb extends Item
{

    public ItemWeb(int i)
    {
        super(i);
        maxStackSize = 64;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
		if(entityplayer.inventory.consumeInventoryItem(mod_SpiderQueen.itemWeb.shiftedIndex))
        {
            world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.isRemote)
            {
                world.spawnEntityInWorld(new EntityWeb(world, entityplayer));
            }
        }
        return itemstack;
    }
}
