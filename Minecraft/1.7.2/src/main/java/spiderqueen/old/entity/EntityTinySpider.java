package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTinySpider extends EntityFriendlySpider
{

    public EntityTinySpider(World world)
    {
        super(world);
        texture = "/imgz/tinyspider_1.png";
        setSize(0.8F, 0.8F);
        moveSpeed = 0.8F;
    }
	
	public int getMaxHealth() { return 10; }
	
	public String getEntityTexture()
    {
        if(getLevel() == 2)
        {
            return "/imgz/tinyspider_3.png";
		}
        if(getLevel() == 1)
        {
            return "/imgz/tinyspider_2.png";
		}
        return "/imgz/tinyspider_1.png";
	}
	
	protected boolean canDespawn()
    {
        return true;
    }

    protected Entity findPlayerToAttack()
    {
       /* float f = getBrightness(1.0F);
        if(f < 0.5F)
        {
            double d = 16D;
            return worldObj.getClosestPlayerToEntity(this, d);
        } else
        {
            return null;
        }*/
            return null;
    }
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
        if(super.attackEntityFrom(damagesource, i))
        {
            if(riddenByEntity == entity || ridingEntity == entity)
            {
                return true;
            }
            if(entity != this)
            {
                entityToAttack = null;
            }
            return true;
        } else
        {
            return false;
        }
    }
	
    public boolean interact(EntityPlayer entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        
            if(itemstack != null && itemstack.itemID == Item.porkRaw.shiftedIndex)
            {
				health = health + 10;
				if(health > getMaxHealth()) { health = getMaxHealth(); }
				setXP(getXP() + 5);
                showHeartsOrSmokeFX(true);
					
                itemstack.stackSize--;
                if(itemstack.stackSize <= 0)
                {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                }
                if(!worldObj.isRemote)
                {
					entityDropItem(new ItemStack(Item.silk.shiftedIndex, 12 + 6 * getLevel(), 0), 0);
                }
                return true;
            }
        
        return false;
    }
	
    protected void attackEntity(Entity entity, float f)
    {
       /* float f1 = getBrightness(1.0F);
        if(f1 > 0.5F && rand.nextInt(100) == 0)
        {
            entityToAttack = null;
            return;
        }
        if(f > 2.0F && f < 7F && rand.nextInt(10) == 0)
        {
            if(onGround)
            {
                double d = entity.posX - posX;
                double d1 = entity.posZ - posZ;
                float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
                motionX = (d / (double)f2) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
                motionZ = (d1 / (double)f2) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
                motionY = 0.40000000596046448D;
            }
        } else
        {
            super.attackEntity(entity, f * 2);
        }*/
    }


}
