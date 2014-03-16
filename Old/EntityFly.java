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

public class EntityFly extends EntityNewAnimal
	implements INewMob
{

    public EntityFly(World world)
    {
        super(world);
        texture = "/imgz/flytexture.png";
        health = 20;
		moveSpeed = 0.94F;
        setSize(0.9F, 0.9F);
    }
	
	
	public int getMaxHealth() { return 20; }
	
    protected void updateEntityActionState()
    {
		if(cocooned) { return; }
		super.updateEntityActionState();
	}
	
	protected void fall(float f)
    {
    }
	
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);
        return worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox) && (worldObj.getBlockId(i, j - 1, k) == Block.grass.blockID);
    }
	

	public float getShadowSize()
    {
        return 0.1F;
    }
	
	
    public boolean interact(EntityPlayer entityplayer)
    {
		entityplayer.heal(3);
		entityplayer.entityDropItem(new ItemStack(Item.silk.shiftedIndex, 3, 0), 0);
		worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
		worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
		setDead();
        return true;
    }
	
	protected Entity findPlayerToAttack()
    {
		return null;
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
        return 0;
    }
	
    public void onLivingUpdate()
    {
		if(cocooned)
		{
			texture = "/imgz/flytexture_c.png";
			motionX = 0F;
			motionZ = 0F;
		}
		else
		{
			texture = "/imgz/flytexture.png";
		}
		
		if(!cocooned)
		{
			if(motionY > 0)
			{
				motionY = motionY * 0.97F + 0.06F;
			}
			else
			{
				double dd = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
				
				motionY = motionY * 0.3F + dd * 0.3F;
			}
		}
		
		fallDistance = 0.0F;
		
		super.onLivingUpdate();
    }
	
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		if(entity instanceof EntityLiving)
		{
			EntityLiving entityplayer = (EntityLiving)entity;
			entityplayer.heal(3);
			entityplayer.entityDropItem(new ItemStack(Item.silk.shiftedIndex, 3, 0), 0);
			worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
			worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
			setDead();
		}
		return false;
    }

	public void setCocooned(boolean b) { cocooned = b; }
	private boolean cocooned;
}
