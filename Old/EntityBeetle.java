// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.old.core.INewMob;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item, 
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie, 
//            EntityLightningBolt

public class EntityBeetle extends EntityNewAnimal
	implements INewMob
{

    public EntityBeetle(World world)
    {
        super(world);
		setTex();
        health = 20;
		moveSpeed = 0.94F;
        setSize(0.9F, 0.9F);
		timeState = 0;
		flying = false;
    }

	public int getMaxHealth() { return 20; }
	
	public EntityBeetle(World world, double d, double d1, double d2, float rr)
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
    }

	public void setTex()
	{
        texture = "/imgz/beetlew.png";
		
		if(flying)
		{
			texture = "/imgz/beetlef.png";
		}
	}
	protected boolean canDespawn()
    {
        return true;
    }
	
	protected void fall(float f)
    {
        super.fall(f);
        int i = (int)Math.ceil(f - 3F);
        if(i > 0)
        {
            attackEntityFrom(DamageSource.fall, i);
        }
    }
	
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);
        return worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox) && (worldObj.getBlockId(i, j - 1, k) == Block.sand.blockID);
    }
	

	public float getShadowSize()
    {
        return 0.1F;
    }
	
	protected void attackEntity(Entity entity, float f)
    {
        if(attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            if(entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4))
			{
				double d = entity.posX - posX;
                double d1;
                for(d1 = entity.posZ - posZ; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
                {
                    d = (Math.random() - Math.random()) * 0.01D;
                }

                knockBack(entity, 5, d, d1);
				if(entity instanceof EntityLiving)
				{
					EntityLiving el = (EntityLiving)entity;
					el.knockBack(this, 8, -d, -d1);
					el.knockBack(this, 8, -d, -d1);
				}
			}
        }
    }
	
	protected Entity findPlayerToAttack()
    {
		return mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 24);
    }
	
    public boolean isOnLadder()
    {
        return isCollidedHorizontally;
    }
	
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "mob.ant";
    }

    protected String getHurtSound()
    {
        return "mob.anthurt";
    }

    protected String getDeathSound()
    {
        return "mob.antdeath";
    }
	
    protected int getDropItemId()
    {
        return Item.appleRed.shiftedIndex;
    }
	
    public void onLivingUpdate()
    {
		Entity entTrack = null;
		
		Random rm = new Random();
		
		if(rm.nextInt(10) == 0)
		{
			timeState++;
			if(timeState > 30 & rm.nextInt(10) == 0)
			{
				timeState = 0;
				flying = !flying;
				setTex();
			}
		}
		/*
		if(entityToAttack == null)
		{
			if(getAte() > 0) 
			{ setAte(getAte()-1); }
			else
			{
				Entity fPlay;
				fPlay = mod_SpiderQueen.findBeeToAttack(worldObj,this);
				if(fPlay != null)
				{
					faceEntity(fPlay, 30F, 30F);
					entTrack = fPlay;
				}
			}
		}*/
		
		if(entityToAttack != null)
		{
			entTrack = entityToAttack;
		}
		if(flying)
		{
			if(motionY > 0)
			{
				motionY = motionY * 0.97F + 0.06F;
			}
			else
			{
				double dd = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
				
				motionY = motionY * 0.4F + dd * 0.5F;
			}
			
			if(entTrack != null)
			{
				if(entTrack.posY + 0.2F < posY)
				{
					motionY = motionY - 0.12F;
				}
				if(entTrack.posY - 0.5F > posY)
				{
					motionY = motionY + 0.01F;
				}
			}
		}

		
		fallDistance = 0.0F;
		
		super.onLivingUpdate();
    }
	
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		entityToAttack = entity;
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
        return super.attackEntityFrom(damagesource, i);
    }
	
	private int timeState;
	private boolean flying;
}
