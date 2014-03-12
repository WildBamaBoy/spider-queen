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

public class EntityAnt extends EntityNewAnimal
    implements INewMob
{

    public EntityAnt(World world)
    {
        super(world);
        texture = "/imgz/antskinb.png";
		moveSpeed = 0.6F;
        setSize(0.9F, 0.9F);
    }

	public int getMaxHealth() { return 10; }
	

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
	
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);
        return worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox) && (worldObj.getBlockId(i, j - 1, k) == Block.stone.blockID || worldObj.getBlockId(i, j - 1, k) == Block.grass.blockID);
    }

	protected Entity findPlayerToAttack()
    {
		if(mod_SpiderQueen.AntLike < 25)
		{
			Entity nn = null;
			nn = worldObj.getClosestPlayerToEntity(this, 16D); 
			if(nn != null) { return nn; }
		}

        return mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 4); //worldObj.getClosestPlayerToEntity(this, 16D);
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
	
	public boolean isLeader()
	{
		return leader;
	}
	
	protected boolean canDespawn()
    {
        return true;
    }
	
	protected void updateEntityActionState()
    {
		entityAge++;
		if(entityAge > 600 && rand.nextInt(800) == 0)
        {
			setDead();
		}
			
		if(isInWater()) { setDead(); return; }
		
        moveStrafing = 0.0F;
        moveForward = 0.0F;
		isJumping = false;
		if(entityToAttack != null)
		{
			faceEntity(entityToAttack, 30F, 30F);
			if(mod_SpiderQueen.getDistance3d(posX,posY,posZ,entityToAttack.posX,entityToAttack.posY,entityToAttack.posZ) > 14D) { moveForward = moveSpeed; } else { moveForward = 0; }
		}
		if(tracking != null)
		{
			faceEntity(tracking, 30F, 30F);
			if(mod_SpiderQueen.getDistance3d(posX,posY,posZ,tracking.posX,tracking.posY,tracking.posZ) > 14D) { moveForward = moveSpeed; } else { moveForward = 0; }			
		}
		if(leader || stunned > 50) { moveForward = moveSpeed; }
		
		if(rand.nextFloat() < 0.05F)
        {
            randomYawVelocity = (rand.nextFloat() - 0.5F) * 5F;
			if(stunned > 50) { randomYawVelocity = 0; }
        }
        rotationYaw += randomYawVelocity;
		
		hasAttacked = isMovementCeased();
        float f = 16F;
		Random rm = new Random();
        if(entityToAttack == null)
        {
			if(rm.nextInt(3) == 1) { entityToAttack = findPlayerToAttack(); }
        } else
        if(!entityToAttack.isEntityAlive())
        {
            entityToAttack = null;
        } else
        {
            float f1 = entityToAttack.getDistanceToEntity(this);
            if(canEntityBeSeen(entityToAttack))
            {
                attackEntity(entityToAttack, f1);
            } else
            {
                attackBlockedEntity(entityToAttack, f1);
            }
        }
        
        int i = MathHelper.floor_double(boundingBox.minY + 0.5D);
        boolean flag = isInWater();
        boolean flag1 = handleLavaMovement();
        rotationPitch = 0.0F;
		

        isJumping = false;
        if(entityToAttack != null)
        {
            moveForward = moveSpeed;
           
            if(hasAttacked && entityToAttack != null)
            {
                double d4 = entityToAttack.posX - posX;
                double d5 = entityToAttack.posZ - posZ;
                float f5 = rotationYaw;
                rotationYaw = (float)((Math.atan2(d5, d4) * 180D) / 3.1415927410125732D) - 90F;
                float f4 = (((f5 - rotationYaw) + 90F) * 3.141593F) / 180F;
                moveStrafing = -MathHelper.sin(f4) * moveForward * 1.0F;
                moveForward = MathHelper.cos(f4) * moveForward * 1.0F;
            }
        }
        if(entityToAttack != null)
        {
            faceEntity(entityToAttack, 30F, 30F);
        }
        if(isCollidedHorizontally && !hasPath())
        {
            isJumping = true;
        }
        if(rand.nextFloat() < 0.8F && (flag || flag1))
        {
            isJumping = true;
        }
	}
	
	public int getAntId()
	{
		if(antId == 0)
		{
			Random rr = new Random();
			antId = rr.nextInt(500);
		}
		if(antId < 0) { antId = 0; }
		if(antId > 500) { antId = 500; }
		return antId;
	}

	public void setTracking(Entity tar)
	{
		tracking = tar;
	}
	
	public Entity getTracking()
	{
		return tracking;
	}
	
    public void onLivingUpdate()
    {
	
		if(isInWater()) { setDead(); return; }
		
		if(entityToAttack != null) { tracking = null; }
		if(tracking != null) { EntityAnt nne = (EntityAnt)tracking; if(nne.getTracking() == this) { tracking = null; } }
		
		if(isCollidedHorizontally & isCollidedVertically)
		{
			munch++;
			if(munch>20)
			{
				munch = 0;
				int pX = (int)posX;
				int pY = (int)posY;
				int pZ = (int)posZ;
				Random rr = new Random();
				for(int xx=-1; xx<=1; xx++){ for(int yy=-1; yy<=1; yy++){ for(int zz=-1; zz<=1; zz++){
					if(yy < 1 || rr.nextInt(5) == 0)
					{
						if(rr.nextInt(10) == 0 & (rr.nextInt(4) == 0 || xx != 0 || zz != 0 || yy < 1)) 
						{ 
							int id = worldObj.getBlockId(pX+xx,pY+yy,pZ+zz);
							if(id == Block.dirt.blockID || id == Block.grass.blockID || id == Block.stone.blockID)
							{
								worldObj.setBlockWithNotify(pX+xx,pY+yy,pZ+zz,0); 
							}
						}
					}
				}}}
			}			
		}
		else
		{
			munch = munch - 5;
			if(munch < 0) { munch = 0; }
		}
		
		if(stunned > 0) 
		{ stunned--; }
		else
		{
			if(entityToAttack == null)
			{
				Random rr = new Random();
				if(rr.nextInt(100) == 0) 
				{ 
					antId = antId - 50 + rr.nextInt(50); leader = false; tracking = null; updateEntityActionState(); 
				}
				
				if(tracking == null)
				{
					Random rm2 = new Random();
					if(rm2.nextInt(5) == 2)
					{
						tracking = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 6);
						if(tracking == null) { leader = true; }
					}
				}
				else
				{
					faceEntity(tracking, 30F, 30F);
				}
			}
		}
		
		super.onLivingUpdate();
    }
	
	
    public void applyEntityCollision(Entity entity)
    {
		super.applyEntityCollision(entity);
		stunned = stunned + 10;
		if(stunned > 100)
		{
			stunned = 100;
			tracking = null;
		}
	}
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		if(entity instanceof EntityPlayer & entityToAttack == null)
		{
			mod_SpiderQueen.likeChange("ant",-1);
		}
    
		entityToAttack = entity;
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
        return super.attackEntityFrom(damagesource, i);
    }
	
	private Entity tracking;
	private boolean leader;
	private int antId;
	private int stunned;
	private int munch;
}
