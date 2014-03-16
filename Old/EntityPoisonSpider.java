
	package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPoisonSpider extends EntityFriendlySpider
{

    public EntityPoisonSpider(World world)
    {
        super(world);
        texture = "/imgz/poisonspider_1.png";
        moveSpeed = 0.8F;
		setSaddled(false);
		lcontrol = 0;
		mymount = null;
        setSize(1.4F, 0.9F);
    }

	public int getMaxHealth() { return 30; }

	public String getEntityTexture()
    {
        if(getLevel() == 2)
        {
            return "/imgz/poisonspider_3.png";
		}
        if(getLevel() == 1)
        {
            return "/imgz/poisonspider_2.png";
		}
        return "/imgz/poisonspider_1.png";
	}
	
    protected Entity findPlayerToAttack()
    {
        return mod_SpiderQueen.findEnemyToAttack(worldObj, this);
    }
	
	protected boolean canDespawn()
    {
        return false;
    }
	
	public int getAttackStrength()
	{
		return 4 + 4 * getLevel();
	}
	
	
    public double getMountedYOffset()
    {
        return (double)height * 0.7D;
    }

    protected void updateEntityActionState()
    {
		super.updateEntityActionState();
		
		
		if(lcontrol < 1)
		{
			isControlled = false;
		}
		else
		{
			if(mymount == null)
			{
				lcontrol = 0;
				return;
			}
			if(!mymount.isEntityAlive())
			{
				lcontrol = 0;
				return;
			}
			isControlled = true;
			moveForward = 0F;
			moveStrafing = 0F;
			entityToAttack = null;
			
				rotationYaw = mymount.rotationYaw;
				
			Random rm = new Random();
			if(rm.nextInt(5) == 0)
			{
				lcontrol--;
			}
		}
		
		if(mymount != null)
		{
			if(mymount.isSneaking()) 
			{ 
				isControlled = true; 
				lcontrol = 10;
				moveForward = mymount.moveForward * 1.15F;
				moveStrafing = mymount.moveStrafing * 1.15F;
					entityToAttack = null;
					setAte(1500);
			}
			if(Math.abs(mymount.moveForward) > 0.35D)
			{
				isControlled = true;
				lcontrol = 10;
				moveForward = mymount.moveForward * 1.15F;
					entityToAttack = null;
					setAte(1500);
			}
			if(Math.abs(mymount.moveStrafing) > 0.35D)
			{
				isControlled = true;
				lcontrol = 10;
				moveStrafing = mymount.moveStrafing;
					entityToAttack = null;
					setAte(1500);
			}
			if(mymount.ridingEntity != this) { mymount = null; }
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
        if(f > 2.0F && f < 6F && rand.nextInt(40) == 0)
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
	
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		if(mymount != null)
		{
			if(entity == mymount) { return false; }
		}
		return super.attackEntityFrom(damagesource,i);
	}
	
	public boolean interact(EntityPlayer entityplayer)
    {
		if(super.interact(entityplayer)) { return true; }
		
        if(getSaddled() && !worldObj.isRemote && (riddenByEntity == null || riddenByEntity == entityplayer))
        {
			mymount = (EntityLiving)entityplayer;
            entityplayer.mountEntity(this);
            return true;
        } else
        {
            return false;
        }
    }
	
	
    public boolean getSaddled()
    {
        return saddle;
    }

    public void setSaddled(boolean flag)
    {
        saddle = flag;
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Saddle", getSaddled());
    }
	
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setSaddled(nbttagcompound.getBoolean("Saddle"));
    }
	
	public boolean getControlled() { return isControlled; }
	private boolean saddle;
	private EntityLiving mymount;
	private boolean isControlled;
	private int lcontrol;
}
