// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item, 
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie, 
//            EntityLightningBolt

public class EntityQueenBee extends EntityNewAnimal
{

    public EntityQueenBee(World world)
    {
        super(world);
        texture = "/imgz/queenbeeskin.png";
		moveSpeed = 1.2F;
        setSize(0.9F, 0.9F);
        health = 30;
    }

	public int getMaxHealth() { return 30; }
	
	protected void fall(float f)
    {
    }
	
    public boolean isOnLadder()
    {
        return isCollidedHorizontally;
    }

	public float getShadowSize()
    {
        return 0.1F;
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
        return mod_SpiderQueen.itemNectar.shiftedIndex;
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
		}
		
		
		if(motionY > 0.4F)
		{
			motionY = motionY * 0.97F - 0.01F;
		}
		else
		{
			double dd = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
			
			motionY = motionY * 0.3F + dd * 0.3F;
		}
		fallDistance = 0.0F;
		
		if(entityToAttack != null) { entTrack = entityToAttack; }
		
		if(entTrack != null)
		{
			double dd = Math.sqrt(Math.pow(entTrack.posX-posX,2) + Math.pow(entTrack.posZ-posZ,2));
			float amt = 0F;
			if(dd < 8F) { amt = ((8F - (float)dd) / 8F)*4F; }
			if(entTrack.posY + 0.2F < posY)
			{
				motionY = motionY - 0.05F*amt;
			}
			if(entTrack.posY - 0.5F > posY)
			{
				motionY = motionY + 0.01F*amt;
			}
		}
		
    }
	
	protected void attackEntity(Entity entity, float f)
    {
        if(attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5);
        }
    }
		
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = getDropItemId();
        if(i > 0)
        {
            int j = 4;
            for(int k = 0; k < j; k++)
            {
                dropItem(i, 1);
				if(k < 2) { dropItem(mod_SpiderQueen.itemRareFruit.shiftedIndex, 1); }
            }

        }
            dropItem(mod_SpiderQueen.stinger.blockID, 1);
    }
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		entityToAttack = entity;
		if(entity != null) { mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F); }
        return super.attackEntityFrom(damagesource, i);
    }

}
