// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item, 
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie, 
//            EntityLightningBolt

public class EntityWarrior extends EntityNewAnimal
{

    public EntityWarrior(World world)
    {
        super(world);
        texture = "/imgz/warriorskinp.png";
		moveSpeed = 0.86F;
        setSize(0.9F, 0.9F);
        health = 15;
    }
	
	public int getMaxHealth() { return 15; }
	
	protected void fall(float f)
    {
	
    }
	
	
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = getDropItemId();
        if(i > 0)
        {
            dropItem(i, 1);
        }
            dropItem(mod_SpiderQueen.stinger.blockID, 1);
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
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
        }
    }
	
    public boolean isOnLadder()
    {
        return isCollidedHorizontally;
    }
	
	protected Entity findPlayerToAttack()
    {
        return null;//worldObj.getClosestPlayerToEntity(this, 16D);
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
		Random rr = new Random();
		if(rr.nextInt(3) != 0) { return mod_SpiderQueen.stinger.blockID; }
        return mod_SpiderQueen.itemPWeb.shiftedIndex;
    }
	
	
	protected void updateEntityActionState()
    {
		super.updateEntityActionState();
		if(entityToAttack != null)
		{
			faceEntity(entityToAttack, 30F, 30F);
			moveForward = moveSpeed;
		}
	}
	
    public void onLivingUpdate()
    {
		super.onLivingUpdate();
		Entity entTrack = null;
		
		if(entityToAttack == null)
		{
			texture = "/imgz/warriorskinp.png";
			if(getAte() > 0) 
			{ fPlay = null; setAte(getAte()-1); }
			else
			{
				//Entity fPlay;
				if(fPlay == null) { fPlay = mod_SpiderQueen.findBeeToAttack(worldObj,this); }
				if(fPlay != null)
				{
					faceEntity(fPlay, 30F, 30F);
					entTrack = fPlay;
				}
			}
		}
		
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
			texture = "/imgz/warriorskina.png";
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
		
    }
	
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		entityToAttack = entity;
		if(entity != null) { mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F); }
        return super.attackEntityFrom(damagesource, i);
    }
	
	private Entity fPlay;

}
