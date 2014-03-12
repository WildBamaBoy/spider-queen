package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCocoon extends Entity
{

    public EntityCocoon(World world)
    {
        super(world);
        boatCurrentDamage = 0;
        boatTimeSinceHit = 0;
        boatRockDirection = 1;
        mySize();
        yOffset = 0.8F;
		eggHatch = 0;
		myType=0;
		eaten=false;
        preventEntitySpawning = true;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }
	
	public boolean getEaten()
	{
		return eaten;
	}
	
    protected void entityInit()
    {
	
    }

    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

    public AxisAlignedBB getBoundingBox()
    {
        return boundingBox;
    }

    public boolean canBePushed()
    {
        return true;
    }
	
	public void mySize()
	{
        setSize(1F, 1F);
	}
	
    public EntityCocoon(World world, double d, double d1, double d2, float rr, int Typ)
    {
        this(world);
        setPosition(d, d1 + (double)yOffset, d2);
		setRotation(rr,0);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
		myType = Typ;
		eaten=false;
    }

    public double getMountedYOffset()
    {
        return (double)height * 0.0D - 0.30000001192092896D;
    }

	public void doSound()
	{
		worldObj.playSoundAtEntity(this, "mob.chickenhurt", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
	}
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
        if(worldObj.isRemote || isDead)
        {
            return true;
        }
		
		
        doSound();
		boatRockDirection = -boatRockDirection;
        boatTimeSinceHit = 10;
        boatCurrentDamage += i * 10;
        setBeenAttacked();
        if(boatCurrentDamage > 80)
        {
			if(eaten)
			{
				worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
				setDead();
				return true;
			}
        
			dropItemWithDamage(mod_SpiderQueen.itemCocoon.shiftedIndex, 1, myType);
			
            setDead();
        }
        return true;
    }

	public EntityItem dropItemWithDamage(int i, int j, int f)
    {
		if(eaten) { return null; }
        return entityDropItem(new ItemStack(i, j, 0), 0);
    }
	
    public void performHurtAnimation()
    {
        boatRockDirection = -boatRockDirection;
        boatTimeSinceHit = 10;
        boatCurrentDamage += boatCurrentDamage * 10;
    }

    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f, 
            float f1, int i)
    {
        field_9393_e = d;
        field_9392_f = d1;
        field_9391_g = d2;
        field_9390_h = f;
        field_9389_i = f1;
        field_9394_d = i + 4;
        motionX = field_9388_j;
        motionY = field_9387_k;
        motionZ = field_9386_l;
    }

    public void setVelocity(double d, double d1, double d2)
    {
        field_9388_j = motionX = d;
        field_9387_k = motionY = d1;
        field_9386_l = motionZ = d2;
    }

	protected boolean canDespawn()
    {
        return false;
    }
	
    public void onUpdate()
    {
        super.onUpdate();
		
		
        if(boatTimeSinceHit > 0)
        {
            boatTimeSinceHit--;
        }
        if(boatCurrentDamage > 0)
        {
			Random rr = new Random();
			rotationPitch += rr.nextFloat();
			rotationPitch -= rr.nextFloat();
            boatCurrentDamage--;
        }
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
		if(worldObj.getBlockId((int)prevPosX,(int)prevPosY,(int)prevPosZ) == mod_SpiderQueen.smallWeb.blockID)
		{
			motionY = 0;
			return;
		}
		
        int i = 5;
        double d = 0.0D;
        for(int j = 0; j < i; j++)
        {
            double d4 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 0)) / (double)i) - 0.125D;
            double d8 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 1)) / (double)i) - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBoxFromPool(boundingBox.minX, d4, boundingBox.minZ, boundingBox.maxX, d8, boundingBox.maxZ);
            if(worldObj.isAABBInMaterial(axisalignedbb, Material.water))
            {
                d += 1.0D / (double)i;
            }
        }

        if(worldObj.isRemote)
        {
            if(field_9394_d > 0)
            {
                double d1 = posX + (field_9393_e - posX) / (double)field_9394_d;
                double d5 = posY + (field_9392_f - posY) / (double)field_9394_d;
                double d9 = posZ + (field_9391_g - posZ) / (double)field_9394_d;
                double d12;
                for(d12 = field_9390_h - (double)rotationYaw; d12 < -180D; d12 += 360D) { }
                for(; d12 >= 180D; d12 -= 360D) { }
                rotationYaw += d12 / (double)field_9394_d;
                rotationPitch += (field_9389_i - (double)rotationPitch) / (double)field_9394_d;
                field_9394_d--;
                setPosition(d1, d5, d9);
                setRotation(rotationYaw, rotationPitch);
            } else
            {
                double d2 = posX + motionX;
                double d6 = posY + motionY;
                double d10 = posZ + motionZ;
                setPosition(d2, d6, d10);
                if(onGround)
                {
                    motionX *= 0.5D;
                    motionY *= 0.5D;
                    motionZ *= 0.5D;
                }
                motionX *= 0.99000000953674316D;
                motionY *= 0.94999998807907104D;
                motionZ *= 0.99000000953674316D;
            }
            return;
        }
        double d3 = d * 2D - 1.0D;
        motionY += 0.039999999105930328D * d3;
        if(riddenByEntity != null)
        {
            motionX += riddenByEntity.motionX * 0.20000000000000001D;
            motionZ += riddenByEntity.motionZ * 0.20000000000000001D;
        }
        double d7 = 0.40000000000000002D;
        if(motionX < -d7)
        {
            motionX = -d7;
        }
        if(motionX > d7)
        {
            motionX = d7;
        }
        if(motionZ < -d7)
        {
            motionZ = -d7;
        }
        if(motionZ > d7)
        {
            motionZ = d7;
        }
        if(onGround)
        {
            motionX *= 0.5D;
            motionY *= 0.5D;
            motionZ *= 0.5D;
        }
        moveEntity(motionX, motionY, motionZ);
        
        
            motionX *= 0.99000000953674316D;
            motionY *= 0.94999998807907104D;
            motionZ *= 0.99000000953674316D;
			
        rotationPitch = 0.0F;
        double d14 = rotationYaw;
        double d16 = prevPosX - posX;
        double d17 = prevPosZ - posZ;
        if(d16 * d16 + d17 * d17 > 0.001D)
        {
            d14 = (float)((Math.atan2(d17, d16) * 180D) / 3.1415926535897931D);
        }
        double d19;
        for(d19 = d14 - (double)rotationYaw; d19 >= 180D; d19 -= 360D) { }
        for(; d19 < -180D; d19 += 360D) { }
        if(d19 > 20D)
        {
            d19 = 20D;
        }
        if(d19 < -20D)
        {
            d19 = -20D;
        }
        rotationYaw += d19;
        setRotation(rotationYaw, rotationPitch);
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if(list != null && list.size() > 0)
        {
            for(int j1 = 0; j1 < list.size(); j1++)
            {
                Entity entity = (Entity)list.get(j1);
                if(entity != riddenByEntity && entity.canBePushed() && (entity instanceof EntityBoat))
                {
                    entity.applyEntityCollision(this);
                }
            }

        }
        if(riddenByEntity != null && riddenByEntity.isDead)
        {
            riddenByEntity = null;
        }
    }

    public void updateRiderPosition()
    {
        /*if(riddenByEntity == null)
        {
            return;
        } else
        {
            double d = Math.cos(((double)rotationYaw * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
            double d1 = Math.sin(((double)rotationYaw * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
            riddenByEntity.setPosition(posX + d, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ + d1);
            return;
        }*/
		
    }

    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setBoolean("eaten", getEaten());	
    }

    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
		setEaten(nbttagcompound.getBoolean("eaten"));
    }
	
    public float getShadowSize()
    {
        return 0.0F;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        /*if(riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer)
        {
            return true;
        }
        if(!worldObj.isRemote)
        {
            entityplayer.mountEntity(this);
        }*/
		if(eaten) { return true; }		
		onEaten();
		entityplayer.heal(3);
		
        entityplayer.getFoodStats().addStats(4, 0.4f);
		worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
		worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
		eaten = true;
        return true;
    }

	public void setEaten(boolean ee)
	{
		eaten = ee;
	}
	
	public void onEaten()
	{
		entityDropItem(new ItemStack(mod_SpiderQueen.itemWeb.shiftedIndex, 5, 0), 0);
	}
	
    public int boatCurrentDamage;
    public int boatTimeSinceHit;
    public int boatRockDirection;
    public int eggHatch;
    private int field_9394_d;
    private double field_9393_e;
    private double field_9392_f;
    private double field_9391_g;
    private double field_9390_h;
    private double field_9389_i;
    private double field_9388_j;
    private double field_9387_k;
    private double field_9386_l;
	private boolean eaten;
	private int myType;
}
