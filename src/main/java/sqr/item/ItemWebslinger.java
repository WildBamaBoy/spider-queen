

package sqr.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Item, World, EntityPlayer, ItemStack,
//            EntityFish

public class ItemWebslinger extends Item
{

	public ItemWebslinger()
	{
		super();
		this.setMaxDamage(64);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1)
	{
		if(entityliving1 instanceof EntityPlayer)
		{
//			final EntityPlayer ell = (EntityPlayer) entityliving1;
//			if(ell.webEntity != null)
//			{
//				if(entityliving instanceof EntityPlayer)
//				{}
//
//				else
//				{
//					ell.webEntity.angler = null;
//					ell.webEntity.holder = entityliving;
//				}
//			}
			return true;
		}
		return false;
	}


	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
//		if(entityplayer.webEntity != null)
//		{
//
//			final Vec3 ww = Vec3.createVectorHelper(entityplayer.posX - entityplayer.webEntity.posX, entityplayer.posY - entityplayer.webEntity.posY, entityplayer.posZ - entityplayer.webEntity.posZ).normalize();
//			entityplayer.motionX *= 1.9D;
//			entityplayer.motionY *= 1.9D;
//			entityplayer.motionZ *= 1.9D;
//
//			entityplayer.webEntity.setDead();
//			entityplayer.webEntity = null;
//			entityplayer.swingItem();
//		}
//
//		else
//		{
//			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
//			if(!world.isRemote)
//			{
//				world.spawnEntityInWorld(new EntitySlingerWeb(world, entityplayer));
//			}
//			entityplayer.swingItem();
//		}
//
		return itemstack;
	}
}
