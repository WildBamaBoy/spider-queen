package spiderqueen.old.item;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntityCocoon;

public class ItemCocoon extends Item
{

    public ItemCocoon(int i)
    {
        super(i);
        maxStackSize = 1;
    }
	
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
		if(entityplayer.inventory.consumeInventoryItem(mod_SpiderQueen.itemCocoon.shiftedIndex))
        {
            //world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.isRemote)
            {
			
					EntityCocoon entCocoon = new EntityCocoon(world);
					//EntityPoisonSpider entCocoon = new EntityPoisonSpider(world);
					entCocoon.posX = entityplayer.posX;
					entCocoon.posY = entityplayer.posY;
					entCocoon.posZ = entityplayer.posZ;
					entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
						
					world.spawnEntityInWorld(entCocoon);
				    
            }
        }
        return itemstack;
    }
}
