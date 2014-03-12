// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.old.core.INewMob;


// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item, 
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie, 
//            EntityLightningBolt

public class EntityYuki extends EntityNewAnimal
	implements INewMob
{

    public EntityYuki(World world)
    {
        super(world);
        texture = "/imgz/yuki.png";
        health = 20;
		moveSpeed = 1.2F;
        setSize(0.9F, 1.5F);
    }
	
	public int getMaxHealth() { return 20; }

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
        return worldObj.worldInfo.isRaining() && worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox) && (worldObj.getBlockId(i, j, k) == Block.snow.blockID);
    }
	

	public float getShadowSize()
    {
        return 0.1F;
    }
	
	public void onStruckByLightning(EntityLightningBolt entitylightningbolt)
    {
        setFire(0);
    }

	
    protected void updateEntityActionState()
    {
		super.updateEntityActionState();
		
		if(attackTime > 0)
		{
			moveForward = Math.abs(moveForward) * -1;
		}
	}
	
	protected void attackEntity(Entity entity, float f)
    {
       if(f < 16F)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            if(attackTime == 0)
            {
				double xx = entity.posX + rand.nextFloat() * 16F - 8F;
				double zz = entity.posZ + rand.nextFloat() * 16F - 8F;
				double yy = worldObj.getTopSolidOrLiquidBlock((int)xx,(int)zz);
                EntityLightningBolt entityarrow = new EntityLightningBolt(worldObj,xx,yy,zz);
                //entityarrow.posY += 1.3999999761581421D;
                //double d2 = (entity.posY + (double)entity.getEyeHeight()) - 0.20000000298023224D - entityarrow.posY;
                //float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
                //worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                worldObj.spawnEntityInWorld(entityarrow);
                //entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 0.6F, 12F);
                attackTime = 60;
            }
            rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
            hasAttacked = true;
        }
    }
	
	protected Entity findPlayerToAttack()
    {
		/*Entity nn = null;
		nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 5);
		if(nn != null) { return nn; }*/
        return null;//worldObj.getClosestPlayerToEntity(this, 16D);
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
        return null;
    }

    protected String getHurtSound()
    {
        return null;
    }

    protected String getDeathSound()
    {
        return null;
    }

    protected int getDropItemId()
    {
        return 0;
    }
	
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = getDropItemId();
        if(i > 0)
        {
            int j = 9;
            for(int k = 0; k < j; k++)
            {
				dropItem(Block.ice.blockID, 1);
            }

        }
            dropItem(Block.ice.blockID, 1);
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
		
		if(attackTime > 0)
		{
			moveForward = Math.abs(moveForward) * -1;
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
