package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sqr.core.INewMob;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item,
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie,
//            EntityLightningBolt

public class EntityWasp extends EntityCreature implements INewMob
{
	
	public EntityWasp(World world)
	{
		super(world);
		// TODO texture ="/imgz/waspskin.png";
		// health = 20;
		// TODO Attribute moveSpeed = 0.94F;
		this.setSize(0.9F, 0.9F);
	}
	
	//
	// @Override
	// public int getMaxHealth() { return 20; }
	
	@Override
	protected boolean canDespawn()
	{
		return true;
	}
	
	@Override
	protected void fall(float f)
	{
		
	}
	
	@Override
	public boolean getCanSpawnHere()
	{
		final int x = MathHelper.floor_double(this.posX);
		final int y = MathHelper.floor_double(this.boundingBox.minY);
		final int z = MathHelper.floor_double(this.posZ);
		return this.worldObj.checkBlockCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.getBlock(x, y - 1, z) == Blocks.sand;
	}
	
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
			if (entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4))
			{
				double d = entity.posX - this.posX;
				double d1;
				for (d1 = entity.posZ - this.posZ; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
				{
					d = (Math.random() - Math.random()) * 0.01D;
				}
				
				this.knockBack(entity, 5, d, d1);
				if (entity instanceof EntityLivingBase)
				{
					final EntityLivingBase el = (EntityLivingBase) entity;
					el.knockBack(this, 3, d, d1);
					el.knockBack(this, 3, d, d1);
				}
			}
		}
	}
	
	@Override
	protected Entity findPlayerToAttack()
	{
		final Entity nn = null;
		// nn = SQR.getClosestEntityToEntity(this.worldObj, this, 16D, 5);
		// if(nn != null) { return nn; }
		return this.worldObj.getClosestPlayerToEntity(this, 16D);
	}
	
	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
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
	// return SQR.stinger;
	// }
	
	@Override
	public void onLivingUpdate()
	{
		Entity entTrack = null;
		/*
		 * if(entityToAttack == null) { if(getAte() > 0) { setAte(getAte()-1); }
		 * else { Entity fPlay; fPlay =
		 * mod_SpiderQueen.findBeeToAttack(worldObj,this); if(fPlay != null) {
		 * faceEntity(fPlay, 30F, 30F); entTrack = fPlay; } } }
		 */
		
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
		
		super.onLivingUpdate();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		this.entityToAttack = entity;
		// mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
		return super.attackEntityFrom(damagesource, i);
	}
	
}
