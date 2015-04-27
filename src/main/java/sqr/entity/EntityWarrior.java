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

public class EntityWarrior extends EntityCreature
{
	
	public EntityWarrior(World world)
	{
		super(world);
		// TODO texture ="/imgz/warriorskinp.png";
		// TODO Attribute moveSpeed = 0.86F;
		this.setSize(0.9F, 0.9F);
		// health = 15;
	}
	
	// @Override
	// public int getMaxHealth() { return 15; }
	
	@Override
	protected void fall(float f)
	{
		
	}
	
	// @Override
	// protected void dropFewItems(boolean par1, int par2)
	// {
	// final int i = this.getDropItemId();
	// if(i > 0)
	// {
	// this.dropItem(i, 1);
	// }
	// this.dropItem(SQR.stinger, 1);
	// }
	
	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}
	
	@Override
	protected void attackEntity(Entity entity, float f)
	{
		if (this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
		}
	}
	
	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}
	
	@Override
	protected Entity findPlayerToAttack()
	{
		return null;// worldObj.getClosestPlayerToEntity(this, 16D);
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
	
	// protected int getDropItemId()
	// {
	// final Random rr = new Random();
	// if(rr.nextInt(3) != 0) { return SQR.stinger; }
	// return ModItems.itemPWeb;
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
		Entity entTrack = null;
		
		// if(this.entityToAttack == null)
		// {
		// //TODO texture ="/imgz/warriorskinp.png";
		// if(getAte() > 0)
		// { this.fPlay = null; setAte(getAte()-1); }
		// else
		// {
		// //Entity fPlay;
		// if(this.fPlay == null) { this.fPlay =
		// SQR.findBeeToAttack(this.worldObj,this); }
		// if(this.fPlay != null)
		// {
		// this.faceEntity(this.fPlay, 30F, 30F);
		// entTrack = this.fPlay;
		// }
		// }
		// }
		
		if (this.motionY > 0)
		{
			this.motionY = this.motionY * 0.97F + 0.06F;
		}
		else
		{
			final double dd = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			
			this.motionY = this.motionY * 0.3F + dd * 0.3F;
		}
		
		if (this.entityToAttack != null)
		{
			// TODO texture ="/imgz/warriorskina.png";
			entTrack = this.entityToAttack;
		}
		
		if (entTrack != null)
		{
			if (entTrack.posY + 0.2F < this.posY)
			{
				this.motionY = this.motionY - 0.12F;
			}
			if (entTrack.posY - 0.5F > this.posY)
			{
				this.motionY = this.motionY + 0.01F;
			}
		}
		
		this.fallDistance = 0.0F;
		
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
	
	private Entity fPlay;
	
}
