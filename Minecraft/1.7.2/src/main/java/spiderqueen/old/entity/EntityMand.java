// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, World, Item, NBTTagCompound

public class EntityMand extends EntityAnimal
{

    public EntityMand(World world)
    {
        super(world);
        field_753_a = false;
        field_752_b = 0.0F;
        destPos = 0.0F;
        field_755_h = 1.0F;
        texture = "/imgz/mand.png";
        setSize(0.5F, 1F);
        health = 4;
        timeUntilNextEgg = rand.nextInt(6000) + 6000;
		setFriendly(false);
    }
	
    public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal)
    {
        return new EntityMand(worldObj);
    }

	public int getMaxHealth() { return 20; }
	
	public void setFriendly(boolean f)
	{
		friendly = f;
		spiderfriend = f;
		if(friendly)
		{
			texture = "/imgz/fmand.png";
		}
		else
		{
			texture = "/imgz/mand.png";
		}
	}
	
	protected boolean canDespawn()
    {
		if(friendly) { return false; }
        return true;
    }
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		if(!friendly)
		{
			entityToAttack = entity;
		}
		else
		{
			if(entity instanceof EntityPlayer || entity instanceof EntityFriendlySpider)
			{
			}
			else
			{
				entityToAttack = entity;
			}
		}
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
        return super.attackEntityFrom(damagesource, i);
    }
	
	
	protected Entity findPlayerToAttack()
    {
		//if(rand.nextInt(15) != 1) { return null; }
		Entity nn = null;
		if(friendly)
		{
			nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 15);
		}
		else
		{
			nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 14);
			if(nn != null) { return nn; }
			return worldObj.getClosestPlayerToEntity(this, 16D);
		}
		
		return nn;
    }
	
	
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);
        return worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox) && (worldObj.getBlockId(i, j - 1, k) == Block.stone.blockID || worldObj.getBlockId(i, j - 1, k) == Block.grass.blockID);
    }
	
	protected void attackEntity(Entity entity, float f)
    {
	
	    if(f < 18F)
        {
            double d = entity.posX - posX + entity.motionX * 15D;
            double d1 = entity.posZ - posZ + entity.motionZ * 15D;
            if(attackTime == 0)
            {
                EntityVines entityarrow = new EntityVines(worldObj, this);
				entityarrow.setFriendly(friendly);
                entityarrow.posY += 0.3999999761581421D;
                double d2 = (entity.posY) - 0.20000000298023224D - entityarrow.posY;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
                worldObj.playSoundAtEntity(this, "Vine", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                worldObj.spawnEntityInWorld(entityarrow);
                entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 0.5F, 8F);
                attackTime = 20 - rand.nextInt(5);
            }
            rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
            hasAttacked = true;
        }
	}
	
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        motionX = 0;
		motionY = -0.1F;
		motionZ = 0;	
		moveStrafing = 0;
		moveForward = 0;
        isJumping = false;
    }
	
	protected void updateEntityActionState()
    {
		super.updateEntityActionState();
		if(entityToAttack != null)
		{
			attackEntity(entityToAttack,getDistanceToEntity(entityToAttack));
			Random rm = new Random();
			if(rm.nextInt(100) == 1 || !entityToAttack.isEntityAlive()) { entityToAttack = null; }
		}
		else
		{
			Random rm = new Random();
			if(rm.nextInt(300) == 1) { entityToAttack = findPlayerToAttack(); }
		}
		
        motionX = 0;
		motionY = -0.1F;
		motionZ = 0;
		moveStrafing = 0;
		moveForward = 0;
        isJumping = false;
	}

    protected void fall(float f)
    {
    }

    protected String getLivingSound()
    {
        return "FMan";
    }

    protected String getHurtSound()
    {
        return "FManHurt";
    }

    protected String getDeathSound()
    {
        return "FMandie";
    }

    protected int getDropItemId()
    {
        return mod_SpiderQueen.mseeds.blockID;
    }


    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("friendly", friendly);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setFriendly(nbttagcompound.getBoolean("friendly"));
    }
		
	public boolean getFriendly() { return friendly; }
	private boolean friendly;
    public boolean field_753_a;
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;
    public int timeUntilNextEgg;
}
