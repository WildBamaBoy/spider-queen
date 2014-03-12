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
//            EntityAnimal, AchievementList, World, Item, 
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie, 
//            EntityLightningBolt

public class EntityMinighast extends EntityNewAnimal
{

    public int prevAttackCounter;
    public int attackCounter;
	byte oldattack = 0;
	
    public EntityMinighast(World world)
    {
        super(world);
        texture = "/mob/ghast.png";
		moveSpeed = 1.5F;
        setSize(0.9F, 0.9F);
        health = 110;
		lcontrol = 0;
        prevAttackCounter = 0;
        attackCounter = 0;
		spiderfriend = true;
    }
	
	public int getMaxHealth() { return 110; }
	
	protected void fall(float f)
    {
        super.fall(f);
        int i = (int)Math.ceil(f - 3F);
        if(i > 0)
        {
            attackEntityFrom(null, i);
        }
    }
	
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = getDropItemId();
        if(i > 0)
        {
            dropItem(i, 1);
        }
            //dropItem(mod_SpiderQueen.stinger.blockID, 1);
    }


	public float getShadowSize()
    {
        return 0.1F;
    }
	/*
	protected void attackEntity(Entity entity, float f)
    {
		if(f < 13F)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            if(attackTime == 0)
            {
                EntityJackball entityarrow = new EntityJackball(worldObj, this);
                //entityarrow.posY += 1.3999999761581421D;
				entityarrow.friendly = true;
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
	*/
	
    public boolean isOnLadder()
    {
        return isCollidedHorizontally;
    }
	
    protected Entity findPlayerToAttack()
    {
		if(rand.nextInt(15) != 1) { return null; }
		Entity nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 15);
		
		return nn;
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
		if(rr.nextInt(3) != 0) { return 0; }
        return mod_SpiderQueen.itemPWeb.shiftedIndex;
    }
	
	
	protected void updateEntityActionState()
    {
		super.updateEntityActionState();
		
		

        prevAttackCounter = attackCounter;
		
		Entity targetedEntity = entityToAttack;
		if (targetedEntity != null)
        {
			if(getDistanceToEntity(targetedEntity) < 12D)
			{
				moveForward = -moveSpeed;
			}
            double d5 = targetedEntity.posX - posX;
            double d6 = (targetedEntity.boundingBox.minY + (double)(targetedEntity.height / 2.0F)) - (posY + (double)(height / 2.0F));
            double d7 = targetedEntity.posZ - posZ;
            if (canEntityBeSeen(targetedEntity))
            {
                if (attackCounter == 10)
                {
                    worldObj.playAuxSFXAtEntity(null, 1007, (int)posX, (int)posY, (int)posZ, 0);
                }
                attackCounter++;
                if (attackCounter == 20)
                {
                    worldObj.playAuxSFXAtEntity(null, 1008, (int)posX, (int)posY, (int)posZ, 0);
					//attack
					EntityJackball entityarrow = new EntityJackball(worldObj, this);
					//entityarrow.posY += 1.3999999761581421D;
					entityarrow.friendly = true;
					double d = targetedEntity.posX - posX;
					double d1 = targetedEntity.posZ - posZ;
					double d2 = targetedEntity.posY - 5D - entityarrow.posY;
					float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
					worldObj.playSoundAtEntity(this, "fire.fire", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
					worldObj.spawnEntityInWorld(entityarrow);
					entityarrow.setJackballHeading(d, d2 + (double)f1, d1, 0.4F, 6F, (EntityLiving)targetedEntity);
                    attackCounter = -40;
                }
            }
            else if (attackCounter > 0)
            {
                attackCounter--;
            }
        }
        else
        {
            if (attackCounter > 0)
            {
                attackCounter--;
            }
        }
		
		if(lcontrol < 1)
		{
			
			if(entityToAttack != null)
			{
				faceEntity(entityToAttack, 30F, 30F);
				moveForward = moveSpeed;
				if(attackTime > 0) { moveForward *= -1; }
			}
			
			isControlled = false;
		}
		else
		{
			if(mymount == null)
			{
				lcontrol = 0;
				return;
			}
			if(!mymount.isEntityAlive())
			{
				lcontrol = 0;
				return;
			}
			isControlled = true;
			moveForward = 0F;
			moveStrafing = 0F;
			
				rotationYaw = mymount.rotationYaw;
				
			Random rm = new Random();
			if(rm.nextInt(5) == 0)
			{
				lcontrol--;
			}
		}
		
		if(mymount != null)
		{
			
				
			if(mymount.isSneaking()) 
			{ 
				isControlled = true; 
				lcontrol = 10;
				if(motionY > -0.5F) { motionY = motionY * 0.95F - 0.07F; }
				
					setAte(1500);
			}
			if(mymount.iJumping()) 
			{ 
				isControlled = true; 
				lcontrol = 10;
				if(motionY < 0.5F) { motionY = motionY * 0.95F + 0.07F; }
			}
			if(Math.abs(mymount.moveForward) > 0.35D)
			{
				isControlled = true;
				lcontrol = 10;
				moveForward = mymount.moveForward * 1.2F;
					setAte(1500);
			}
			if(Math.abs(mymount.moveStrafing) > 0.35D)
			{
				isControlled = true;
				lcontrol = 10;
				moveStrafing = mymount.moveStrafing;
					setAte(1500);
			}
			if(mymount.ridingEntity != this) { mymount = null; }
		}
		
		
        if (!worldObj.isRemote)
        {
            byte byte1 = (byte)(attackCounter <= 10 ? 0 : 1);
            if (oldattack != byte1)
            {
				oldattack = byte1;
            }
        }
		
		
	}
	
	
    public void onUpdate()
    {
        super.onUpdate();
        texture = oldattack != 1 ? "/mob/ghast.png" : "/mob/ghast_fire.png";
    }
	
    public void onLivingUpdate()
    {
		super.onLivingUpdate();
		Entity entTrack = entityToAttack;
		
		
		if(motionY > 0.1F)
		{
			motionY = motionY * 0.9F - 0.02F;
		}
		else
		{
			double dd = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
			
			motionY = motionY * 0.5F + dd * 0.3F;
		}
		
		if(entityToAttack != null)
		{
			//texture = "/imgz/warriorskina.png";
			entTrack = entityToAttack;
		}

		if(entTrack != null & !isControlled)
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
	
    public double getMountedYOffset()
    {
        return (double)height * 0.7D;
    }
	
	protected boolean canDespawn()
    {
        return false;
    }
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		if(entity instanceof EntityLiving)
		{
			EntityLiving el = (EntityLiving)entity;
			if(!el.spiderfriend & !(entity instanceof EntityPlayer))
			{
				entityToAttack = entity;
			}
		}
        return super.attackEntityFrom(damagesource, i);
    }
	
	public boolean interact(EntityPlayer entityplayer)
    {
		if(super.interact(entityplayer)) { return true; }
		
        if(!worldObj.isRemote && (riddenByEntity == null || riddenByEntity == entityplayer)) //getSaddled() && 
        {
			mymount = (EntityLiving)entityplayer;
            entityplayer.mountEntity(this);
            return true;
        } else
        {
            return false;
        }
    }
	
    public boolean getSaddled()
    {
        return saddle;
    }

    public void setSaddled(boolean flag)
    {
        saddle = flag;
    }
	
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Saddle", getSaddled());
    }
	
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setSaddled(nbttagcompound.getBoolean("Saddle"));
    }
	
	private Entity fPlay;

	public boolean getControlled() { return isControlled; }
	private boolean saddle;
	private EntityLiving mymount;
	private int lcontrol;
	private boolean isControlled;
}
