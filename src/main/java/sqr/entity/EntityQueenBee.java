package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item,
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie,
//            EntityLightningBolt

public class EntityQueenBee extends EntityCreature
{
	
	public EntityQueenBee(World world)
	{
		super(world);
		// TODO texture ="/imgz/queenbeeskin.png";
		// TODO Attribute moveSpeed = 1.2F;
		this.setSize(0.9F, 0.9F);
		// health = 30;
	}
	
	// @Override
	// public int getMaxHealth() { return 30; }
	
	@Override
	protected void fall(float f)
	{
	}
	
	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
	}
	
	@Override
	protected String getLivingSound()
	{
		return "bee";
	}
	
	@Override
	protected String getHurtSound()
	{
		return "beehurt";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "beedeath";
	}
	
	//
	// protected int getDropItemId()
	// {
	// return ModItems.itemNectar;
	// }
	
	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();
		if (this.entityToAttack != null)
		{
			this.faceEntity(this.entityToAttack, 30F, 30F);
			// this.moveForward = moveSpeed;
		}
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		// Entity entTrack = null;
		//
		// if(this.entityToAttack == null)
		// {
		// if(getAte() > 0)
		// { setAte(getAte()-1); }
		// else
		// {
		// Entity fPlay;
		// fPlay = SQR.findBeeToAttack(this.worldObj,this);
		// if(fPlay != null)
		// {
		// this.faceEntity(fPlay, 30F, 30F);
		// entTrack = fPlay;
		// }
		// }
		// }
		
		if (this.motionY > 0.4F)
		{
			this.motionY = this.motionY * 0.97F - 0.01F;
		}
		else
		{
			final double dd = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			
			this.motionY = this.motionY * 0.3F + dd * 0.3F;
		}
		this.fallDistance = 0.0F;
		
		// if(this.entityToAttack != null) { entTrack = this.entityToAttack; }
		//
		// if(entTrack != null)
		// {
		// final double dd = Math.sqrt(Math.pow(entTrack.posX-this.posX,2) +
		// Math.pow(entTrack.posZ-this.posZ,2));
		// float amt = 0F;
		// if(dd < 8F) { amt = (8F - (float)dd) / 8F*4F; }
		// if(entTrack.posY + 0.2F < this.posY)
		// {
		// this.motionY = this.motionY - 0.05F*amt;
		// }
		// if(entTrack.posY - 0.5F > this.posY)
		// {
		// this.motionY = this.motionY + 0.01F*amt;
		// }
		// }
		
	}
	
	@Override
	protected void attackEntity(Entity entity, float f)
	{
		if (this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5);
		}
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		// final int i = this.getDropItemId();
		// if(i > 0)
		// {
		// final int j = 4;
		// for(int k = 0; k < j; k++)
		// {
		// this.dropItem(i, 1);
		// if(k < 2) { this.dropItem(ModItems.itemRareFruit, 1); }
		// }
		//
		// }
		// this.dropItem(SQR.stinger, 1);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		this.entityToAttack = entity;
		// if(entity != null) { SQR.pissOffBees(this.worldObj, entity,
		// this.posX, this.posY, this.posZ, 64F); }
		return super.attackEntityFrom(damagesource, i);
	}
	
}
