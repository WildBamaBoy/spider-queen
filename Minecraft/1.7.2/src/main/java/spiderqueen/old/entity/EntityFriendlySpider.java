package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFriendlySpider extends EntityCreature
{

    public EntityFriendlySpider(World world)
    {
        super(world);
        health = 20;
		maxhealth = health;
        texture = "/imgz/friendlyspider_1.png";
        moveSpeed = 0.87F;
		level=0;
		xp=0;
        setSize(1.4F, 0.9F);
		spiderfriend = true;
    }
	
	
	public int getMaxHealth()
	{
		return maxhealth;
	}
	
	public String getEntityTexture()
    {
        if(getLevel() == 2)
        {
            return "/imgz/friendlyspider_3.png";
		}
        if(getLevel() == 1)
        {
            return "/imgz/friendlyspider_2.png";
		}
        return "/imgz/friendlyspider_1.png";
	}
	
	public double getMountedYOffset()
    {
        return (double)height * 0.75D - 0.5D;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected String getLivingSound()
    {
        return "mob.spider";
    }

    protected String getHurtSound()
    {
        return "mob.spider";
    }

    protected String getDeathSound()
    {
        return "mob.spiderdeath";
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("maxhealth", (short)maxhealth);
        nbttagcompound.setShort("xp", (short)xp);
        nbttagcompound.setShort("level", (short)level);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        maxhealth = nbttagcompound.getShort("maxhealth");
        xp = nbttagcompound.getShort("xp");
        level = nbttagcompound.getShort("level");
    }

    protected int getDropItemId()
    {
        return Item.silk.shiftedIndex;
    }

    public boolean isOnLadder()
    {
        return isCollidedHorizontally;
    }
	
	protected boolean canDespawn()
    {
        return false;
    }

	public int getLevel() { return level; }

    protected Entity findPlayerToAttack()
    {
        return mod_SpiderQueen.findEnemyToAttack(worldObj, this);
    }
	
	public int getTalkInterval()
    {
        return 160;
    }
	

	public int getAttackStrength()
	{
		return 2 + 3 * level;
	}
	
    protected void attack2Entity(Entity entity, float f)
    {
		
        if(attackTime <= 0 && f < 3.0F && entity.boundingBox.maxY >= boundingBox.minY && entity.boundingBox.minY <= boundingBox.maxY)
        {
		
			boolean ins = false;
			if(entity instanceof EntityFly) { ins = true; }
			if(entity instanceof EntityCocoonAnt) { ins = true; }
			if(entity instanceof EntityCocoonWasp) { ins = true; }
			if(entity instanceof EntityCocoonGatherer) { ins = true; }
			if(entity instanceof EntityCocoonWarrior) { ins = true; }
			if(entity instanceof EntityCocoonQueenBee) { ins = true; }
			
			if(ins)
			{
				addHP(10);
				setXP(getXP() + 5);
				worldObj.spawnParticle("largesmoke", entity.posX - motionX * 2, entity.posY - motionY * 2, entity.posZ - motionZ * 2, motionX, motionY, motionZ);
				worldObj.spawnParticle("largesmoke", entity.posX - motionX * 2, entity.posY - motionY * 2, entity.posZ - motionZ * 2, motionX, motionY, motionZ);
				entity.setDead();
			}
			
            attackTime = 20;
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackStrength());
			xp = xp + 1;
        }
    }
	
	public boolean interact(EntityPlayer entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
      
            if(itemstack != null && itemstack.itemID == Item.porkRaw.shiftedIndex)
            {
                itemstack.stackSize--;
                if(itemstack.stackSize <= 0)
                {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                }
                if(!worldObj.isRemote)
                {
                    health = health + 10;
					if(health > maxhealth) { health = maxhealth; }
					xp = xp + 5;
                    showHeartsOrSmokeFX(true);
                }
                return true;
            }
        
		
        return false;
    }

    void showHeartsOrSmokeFX(boolean flag)
    {
        String s = "heart";
        if(!flag)
        {
            s = "explode";
        }
        for(int i = 0; i < 7; i++)
        {
            double d = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            worldObj.spawnParticle(s, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
        }

    }
	
    protected void attackEntity(Entity entity, float f)
    {
        float f1 = getBrightness(1.0F);
        if(f1 > 0.5F && rand.nextInt(100) == 0)
        {
            entityToAttack = null;
            return;
        }
        if(f > 3.0F && f < 8F && rand.nextInt(20) == 0)
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
            attack2Entity(entity, f);
        }
    }
	
    public void onLivingUpdate()
    {
		super.onLivingUpdate();
		fallDistance = 0.0F;
		if(xp > 15 + 15 * level) 
		{ 
		
            showHeartsOrSmokeFX(false);
            showHeartsOrSmokeFX(false);
            showHeartsOrSmokeFX(false);
            showHeartsOrSmokeFX(false);
			xp = 0; level++; 
			if(level>2) { level = 2; }
			maxhealth = 20 + 15 * level;
			health = maxhealth;
		}

    }
	
	
    protected void updateEntityActionState()
    {
		super.updateEntityActionState();
		
	}

	public int getXP() { return xp; }
	public void setXP(int xx) { xp = xx; }
	public void addHP(int xx) { health = health + xx; if(health > maxhealth) { health = maxhealth; } }
	private int maxhealth;
	private int xp;
	private int level;
}
