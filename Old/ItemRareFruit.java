// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.item;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, EntityPlayer, World

public class ItemRareFruit extends Item
{

    public ItemRareFruit(int i, int j)
    {
        super(i);
        healAmount = j;
        maxStackSize = 8;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
		entityplayer.entityDropItem(new ItemStack(Item.silk.shiftedIndex, healAmount, 0), 0);
        entityplayer.heal(healAmount);
		/*int tint = entityplayer.getMArmor() + healAmount;
		if(tint > 100) { tint = 100; }
		entityplayer.setMArmor(tint);*/
        return itemstack;
    }

    public int getHealAmount()
    {
        return healAmount;
    }

    private int healAmount;
}
