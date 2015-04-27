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
import sqr.core.minecraft.ModBlocks;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item,
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie,
//            EntityLightningBolt

public class EntityJack extends EntityCreature implements INewMob
{
	
	public EntityJack(World world)
	{
		super(world);
		// TODO texture ="/imgz/jackskin.png";
		// health = 90;
		// TODO Attribute moveSpeed = 0.94F;
		this.setSize(0.9F, 1.7F);
	}
	
	// @Override
	// public int getMaxHealth() { return 80; }
	
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
		if (f < 10F)
		{
			final double d = entity.posX - this.posX;
			final double d1 = entity.posZ - this.posZ;
			if (this.attackTime == 0)
			{
				final EntityJackball entityarrow = new EntityJackball(this.worldObj, this);
				entityarrow.posY += 1.3999999761581421D;
				final double d2 = entity.posY - 5D - entityarrow.posY;
				final float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
				this.worldObj.playSoundAtEntity(this, "fire.fire", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
				this.worldObj.spawnEntityInWorld(entityarrow);
				entityarrow.setJackballHeading(d, d2 + f1, d1, 0.4F, 6F, (EntityLivingBase) entity);
				this.attackTime = 30;
			}
			this.rotationYaw = (float) (Math.atan2(d1, d) * 180D / 3.1415927410125732D) - 90F;
			this.hasAttacked = true;
		}
	}
	
	// @Override
	// protected Entity findPlayerToAttack()
	// {
	// if(this.worldObj.isDaytime()) { return null; }
	// Entity nn = null;
	// nn = SQR.getClosestEntityToEntity(this.worldObj, this, 16D, 5);
	// if(nn != null) { return nn; }
	// return this.worldObj.getClosestPlayerToEntity(this, 16D);
	// }
	
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
		return "Pumpkinidle";
	}
	
	@Override
	protected String getHurtSound()
	{
		return "Pumpkinhurt";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "Pumpkindie";
	}
	
	//
	// protected int getDropItemId()
	// {
	// return SQR.lantern;
	// }
	
	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		// final int i = this.getDropItemId();
		// if(i > 0)
		// {
		// final int j = this.rand.nextInt(16) + 8;
		// for(int k = 0; k < j; k++)
		// {
		// this.dropItem(i, 1);
		// }
		//
		// }
	}
	
	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();
	}
	
	@Override
	public void onLivingUpdate()
	{
		
		if (this.entityToAttack == this)
		{
			this.entityToAttack = null;
		}
		
		if (this.worldObj.isDaytime())
		{
			this.motionX = this.motionX * 0.9F;
			this.motionZ = this.motionZ * 0.9F;
			this.motionY = this.motionY * 0.5F;
			this.entityToAttack = null;
			final int x = MathHelper.floor_double(this.posX);
			final int y = MathHelper.floor_double(this.boundingBox.minY);
			final int z = MathHelper.floor_double(this.posZ);
			
			if (this.worldObj.isAirBlock(x, y, z) & !this.worldObj.isAirBlock(x, y - 1, z))
			{
				this.worldObj.setBlock(x, y, z, ModBlocks.bjack);
				this.setDead();
			}
			
			super.onLivingUpdate();
			return;
		}
		Entity entTrack = null;
		
		if (this.motionY > 0)
		{
			this.motionY = this.motionY * 0.97F + 0.06F;
		}
		else
		{
			final double dd = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			
			this.motionY = this.motionY * 0.3F + dd * 0.35F;
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
