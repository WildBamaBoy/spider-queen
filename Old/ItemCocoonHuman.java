package spiderqueen.old.item;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntityCocoonHuman;

public class ItemCocoonHuman extends Item
{

    public ItemCocoonHuman(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
		if(entityplayer.inventory.consumeInventoryItem(mod_SpiderQueen.itemCocoonHuman.shiftedIndex))
        {
            //world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.isRemote)
            {
			
					EntityCocoonHuman entCocoon = new EntityCocoonHuman(world);
					entCocoon.posX = entityplayer.posX - MathHelper.cos((entCocoon.rotationYaw / 180F) * 3.141593F) * 0.16F;
					entCocoon.posY = entityplayer.posY - 0.40000000149011612D;
					entCocoon.posZ = entityplayer.posZ - MathHelper.sin((entCocoon.rotationYaw / 180F) * 3.141593F) * 0.16F;
					entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
					entCocoon.setRotation(entCocoon.rotationYaw - 90F,0F);
						
					world.spawnEntityInWorld(entCocoon);
				    
            }
        }
        return itemstack;
    }
}
