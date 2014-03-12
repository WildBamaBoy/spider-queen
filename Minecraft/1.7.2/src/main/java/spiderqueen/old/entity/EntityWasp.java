// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.old.core.INewMob;


// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item, 
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie, 
//            EntityLightningBolt

public class EntityWasp extends EntityNewAnimal
	implements INewMob
{

    public EntityWasp(World world)
    {
        super(world);
        texture = "/imgz/waspskin.png";
        health = 20;
		moveSpeed = 0.94F;
        setSize(0.9F, 0.9F);
    }

	public int getMaxHealth() { return 20; }
	
	protected boolean canDespawn()
    {
        return true;
    }
	
	protected void fall(float f)
    {
	
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
					el.knockBack(this, 3, d, d1);
					el.knockBack(this, 3, d, d1);
				}
			}
        }
    }
	
	protected Entity findPlayerToAttack()
    {
		Entity nn = null;
		nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 5);
		if(nn != null) { return nn; }
        return worldObj.getClosestPlayerToEntity(this, 16D);
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
        return "bee";
    }

    protected String getHurtSound()
    {
        return "beehurt";
    }

    protected String getDeathSound()
    {
        return "beedeath";
    }

    protected int getDropItemId()
    {
        return mod_SpiderQueen.stinger.blockID;
    }
	
    public void onLivingUpdate()
    {
		Entity entTrack = null;
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
		
		if(motionY > 0)
		{
			motionY = motionY * 0.97F + 0.06F;
		}
		else
		{
			double dd = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
			
			motionY = motionY * 0.3F + dd * 0.3F;
		}
		
		if(entityToAttack != null)
		{
			entTrack = entityToAttack;
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

}
