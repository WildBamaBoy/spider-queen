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

public class EntityJack extends EntityNewAnimal
	implements INewMob
{

    public EntityJack(World world)
    {
        super(world);
        texture = "/imgz/jackskin.png";
        health = 90;
		moveSpeed = 0.94F;
        setSize(0.9F, 1.7F);
    }
	
	public int getMaxHealth() { return 80; }

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
        if(f < 10F)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            if(attackTime == 0)
            {
                EntityJackball entityarrow = new EntityJackball(worldObj, this);
                entityarrow.posY += 1.3999999761581421D;
                double d2 = entity.posY - 5D - entityarrow.posY;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
                worldObj.playSoundAtEntity(this, "fire.fire", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                worldObj.spawnEntityInWorld(entityarrow);
                entityarrow.setJackballHeading(d, d2 + (double)f1, d1, 0.4F, 6F, (EntityLiving)entity);
                attackTime = 30;
            }
            rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
            hasAttacked = true;
        }
    }

	protected Entity findPlayerToAttack()
    {
		if(worldObj.isDaytime()) { return null; }
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
        return "Pumpkinidle";
    }

    protected String getHurtSound()
    {
        return "Pumpkinhurt";
    }

    protected String getDeathSound()
    {
        return "Pumpkindie";
    }

    protected int getDropItemId()
    {
        return mod_SpiderQueen.lantern.blockID;
    }
	
	
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = getDropItemId();
        if(i > 0)
        {
            int j = rand.nextInt(16) + 8;
            for(int k = 0; k < j; k++)
            {
                dropItem(i, 1);
            }

        }
    }
	
	protected void updateEntityActionState()
    {
		super.updateEntityActionState();
	}
	
    public void onLivingUpdate()
    {
	
		if(entityToAttack == this) { entityToAttack = null; }
		
		if(worldObj.isDaytime())
		{
			motionX = motionX * 0.9F;
			motionZ = motionZ * 0.9F;
			motionY = motionY * 0.5F;
			entityToAttack = null;
			int i = MathHelper.floor_double(posX);
			int j = MathHelper.floor_double(boundingBox.minY);
			int k = MathHelper.floor_double(posZ);
			if(worldObj.getBlockId(i,j,k) == 0 & worldObj.getBlockId(i,j-1,k) != 0)
			{
				worldObj.setBlockWithNotify(i,j,k,mod_SpiderQueen.bjack.blockID);
				setDead();
			}
			
			super.onLivingUpdate();
			return;
		}
		Entity entTrack = null;
		
		if(motionY > 0)
		{
			motionY = motionY * 0.97F + 0.06F;
		}
		else
		{
			double dd = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
			
			motionY = motionY * 0.3F + dd * 0.35F;
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
