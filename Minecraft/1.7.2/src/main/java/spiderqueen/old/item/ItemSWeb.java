// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.item;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import spiderqueen.old.entity.EntitySWeb;

// Referenced classes of package net.minecraft.src:
//            Item, World, EntityPlayer, ItemStack, 
//            EntityFish

public class ItemSWeb extends Item
{

    public ItemSWeb(int i)
    {
        super(i);
        setMaxDamage(64);
        setMaxStackSize(1);
    }

    public boolean isFull3D()
    {
        return true;
    }

    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

	public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
		if(entityliving1 instanceof EntityPlayer)
		{
			EntityPlayer ell = (EntityPlayer) entityliving1;
			if(ell.webEntity != null)
			{
				if(entityliving instanceof EntityPlayer)
				{} else {
					ell.webEntity.angler = null;
					ell.webEntity.holder = entityliving;
				}
			}
			return true;
		}
		return false;
    }

	
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(entityplayer.webEntity != null)
        {
		
        Vec3D ww = Vec3D.createVector(entityplayer.posX - entityplayer.webEntity.posX, entityplayer.posY - entityplayer.webEntity.posY, entityplayer.posZ - entityplayer.webEntity.posZ).normalize();
			entityplayer.motionX *= 1.9D;
			entityplayer.motionY *= 1.9D;
			entityplayer.motionZ *= 1.9D;
			
			entityplayer.webEntity.setDead();
			entityplayer.webEntity = null;
			entityplayer.swingItem();
        } else
        {
            world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.isRemote)
            {
                world.spawnEntityInWorld(new EntitySWeb(world, entityplayer));
            }
            entityplayer.swingItem();
        }
        return itemstack;
    }
}
