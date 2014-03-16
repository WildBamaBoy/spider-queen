// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.old.core.INewMob;


// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item, 
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie, 
//            EntityLightningBolt

public class EntityOctopus extends EntityNewAnimal
	implements INewMob
{

    public EntityOctopus(World world)
    {
        super(world);
        texture = "/imgz/octoskin.png";
        health = 20;
		moveSpeed = 0.8F;
        setSize(0.9F, 0.9F);
		natk = 0;
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
        return worldObj.checkIfAABBIsClear(boundingBox);
    }
	

	public float getShadowSize()
    {
        return 0.1F;
    }
	
	protected void attackEntity(Entity entity, float f)
    {
	
		if(natk > 0)
		{
			natk++;
			
			if(natk < 60)
			{
				motionZ = 0;
				motionX = 0;
			}
			motionY = 0;
			
			if(natk < 10)
			{
				motionY = 0.1F;
			}
			
			if(natk == 50)
			{
			
				double d = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.5F;
				d = d + entity.motionX * 7.2D * f1;
				d1 = d1 + entity.motionZ * 7.2D * f1;
				EntityOctoball entityarrow = new EntityOctoball(worldObj, this);
				double d2 = (entity.posY + 0.4D) - 0.20000000298023224D - entityarrow.posY;
				worldObj.playSoundAtEntity(this, "Octoball", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
				worldObj.spawnEntityInWorld(entityarrow);
				entityarrow.setOctoballHeading(d, d2, d1, 0.6F, 0F);
			}
			
			if(natk > 50)
			{
				motionY = -0.15F;
			}
			else
			{
				if(posY + 0.5F - 0.3F > entity.posY) { motionY = motionY - 0.25F; }
				if(posY + 0.5F + 0.3F < entity.posY) { motionY = motionY + 0.25F; }
			}
			
			if(natk > 60)
			{
				motionX = (motionX * 1.1F + (rand.nextFloat() - 0.5F) / 10F) * 0.95F;
				motionZ = (motionZ * 1.1F + (rand.nextFloat() - 0.5F) / 10F) * 0.95F;
				moveSpeed = Math.abs(moveSpeed) * -0.95F;
			}
			
			if(natk > 140)
			{
				natk = 0;
				attackTime = 20;
				return;
			}
			
			
			
		}
		
		if(natk == 0)
		{
			if(f > 18.0F & entityToAttack == entity) { entityToAttack = null; return; }
			
			if(attackTime <= 0 && f < 18.0F)
			{
				natk = 1;
				motionX = 0;
				motionZ = 0;
				moveForward = 0;
				//attackTime = 20;
				//entity.attackEntityFrom(this, 4);
			}
		}
    }
	
	protected Entity findPlayerToAttack()
    {
		if(rand.nextInt(15) != 1) { return null; }
		
		Entity nn = null;
		nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 18D, 5);
		if(nn != null) { return nn; }
        return worldObj.getClosestPlayerToEntity(this, 18D);
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
        return "Octoidle";
    }

    protected String getHurtSound()
    {
        return "Octohurt";
    }

    protected String getDeathSound()
    {
        return "Octohurt";
    }

    
    protected int getDropItemId()
    {
        return 0;
    }

    protected void dropFewItems(boolean par1, int par2)
    {
        int i = rand.nextInt(3) + 1;
        for(int j = 0; j < i; j++)
        {
            entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
        }

    }
	
    public boolean canBreatheUnderwater()
    {
        return true;
    }
	
	protected void updateEntityActionState()
    {
	
		super.updateEntityActionState();
		if(!worldObj.isAnyLiquid(boundingBox)) { moveForward = 0F; }
		if(isCollidedHorizontally)  { moveForward = -0.5F; }
		
		if((natk > 0 & natk < 60) || (natk == 0 & worldObj.getBlockId((int)posX,(int)(posY + 1.17F),(int)posZ) == 0))
		{
			motionX = 0;
			motionZ = 0;
			moveForward = 0;
		}

	}
	
    public void onLivingUpdate()
    {
		Entity entTrack = null;
		
			double dd = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
			if(worldObj.getBlockId((int)posX,(int)(posY + 1.17F),(int)posZ) == 0) { dd = 0; }
			
			motionY = motionY * 0.05F + dd * 0.08F;
			//if(natk == 0){ motionY = motionY  - 0.05F; }
			
		if(entityToAttack != null)
		{
			entTrack = entityToAttack;
			
			float f1 = entityToAttack.getDistanceToEntity(this);
			attackEntity(entityToAttack, f1);
			if(f1 > 13) { motionY = motionY - 0.05F; }
		}

		if(natk == 0)
		{
			if(!worldObj.isAnyLiquid(boundingBox) || worldObj.getBlockId((int)posX,(int)(posY + 0.2F),(int)posZ) == 0) { motionY = motionY * 0.9F - 0.1F; }
			else
			{
				if(rand.nextInt(30) == 1) { motionY = motionY + 0.01F; }
			}
			
			
			if(worldObj.getBlockId((int)posX,(int)(posY + 1.17F),(int)posZ) == 0)
			{
				motionY = -0.2F;
			}
			else
			{
				moveForward = 3F;
			}
		}
		
		fallDistance = 0.0F;
		
		if(natk > 0 & natk < 60)
		{
			motionX = 0;
			motionZ = 0;
			moveForward = 0;
		}

		super.onLivingUpdate();
    }
	
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		entityToAttack = entity;
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
        return super.attackEntityFrom(damagesource, i);
    }
	
	private int natk;

}
