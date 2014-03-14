package spiderqueen.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.enums.EnumCocoonType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityWeb extends Entity
{
	private int ticksInAir;
	private boolean isInactive;
	private boolean isPoison;
	private boolean doBlockSpawn;

	public EntityPlayer shooter;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;

	/**
	 * Constructor for client rendering.
	 * 
	 * @param 	worldObj	The world to render the object in.
	 */
	public EntityWeb(World worldObj)
	{
		super(worldObj);
	}

	public EntityWeb(EntityPlayer player) 
	{
		this(player.worldObj);

		final Vec3 vec = player.getLookVec();

		this.shooter = player;
		this.setPosition(player.posX + vec.xCoord * 2, player.posY + 1 + vec.yCoord * 2, player.posZ + vec.zCoord * 2);
		this.accelerationX = vec.xCoord * 0.5;
		this.accelerationY = vec.yCoord * 0.5;
		this.accelerationZ = vec.zCoord * 0.5;

		this.motionX = accelerationX;
		this.motionY = accelerationY;
		this.motionZ = accelerationZ;
		
		this.doBlockSpawn = true;
	}

	public EntityWeb(EntityPlayer player, boolean isPoison)
	{
		this(player);
		this.isPoison = isPoison;
	}
	
	@Override
	protected void entityInit() 
	{
		// No init.
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		double weightedLength = this.boundingBox.getAverageEdgeLength() * 4.0D;
		weightedLength *= 64.0D;
		return distance < weightedLength * weightedLength;
	}

	@Override
	public void onUpdate()
	{
		if (!this.worldObj.isRemote && (this.shooter != null && this.shooter.isDead || !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
		{
			this.setDead();
		}

		else
		{
			super.onUpdate();

			if (!this.worldObj.isRemote)
			{
				if (this.shooter != null && this.getDistanceToEntity(shooter) > 150.0D)
				{
					setDead();
					return;
				}
			}

			updateCollision();
			updateMotion();
		}
	}

	private void updateCollision()
	{
		Vec3 currentVector = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
		Vec3 nextVector = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition collisionPosition = this.worldObj.rayTraceBlocks(currentVector, nextVector);
		currentVector = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
		nextVector = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if (collisionPosition != null)
		{
			nextVector = this.worldObj.getWorldVec3Pool().getVecFromPool(collisionPosition.hitVec.xCoord, collisionPosition.hitVec.yCoord, collisionPosition.hitVec.zCoord);
		}

		Entity collidedEntity = null;
		List entitiesInAABB = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double lastDistance = 0.0D;

		for (int counter = 0; counter < entitiesInAABB.size(); ++counter)
		{
			final Entity entityInList = (Entity)entitiesInAABB.get(counter);

			if (entityInList.canBeCollidedWith() && (!entityInList.isEntityEqual(this.shooter) || this.ticksInAir >= 25))
			{
				final AxisAlignedBB AABB = entityInList.boundingBox.expand(0.3D, 0.3D, 0.3D);
				final MovingObjectPosition entityCollisionPosition = AABB.calculateIntercept(currentVector, nextVector);

				if (entityCollisionPosition != null)
				{
					final double thisDistance = currentVector.distanceTo(entityCollisionPosition.hitVec);

					if (thisDistance < lastDistance || lastDistance == 0.0D)
					{
						collidedEntity = entityInList;
						lastDistance = thisDistance;
					}
				}
			}
		}

		if (collidedEntity != null)
		{
			collisionPosition = new MovingObjectPosition(collidedEntity);
		}

		if (collisionPosition != null)
		{
			this.onImpact(collisionPosition);
		}
	}

	private void updateMotion()
	{
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

		for (this.rotationPitch = (float)(Math.atan2((double)f1, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
		{
			;
		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
		{
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F)
		{
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
		{
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float motionFactor =  0.95F;

		if (this.isInWater())
		{
			for (int k = 0; k < 4; ++k)
			{
				float f3 = 0.25F;
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f3, this.posY - this.motionY * (double)f3, this.posZ - this.motionZ * (double)f3, this.motionX, this.motionY, this.motionZ);
			}

			motionFactor = 0.8F;
		}

		this.motionX += this.accelerationX;
		this.motionY += this.accelerationY;
		this.motionZ += this.accelerationZ;
		this.motionX *= (double)motionFactor;
		this.motionY *= (double)motionFactor;
		this.motionZ *= (double)motionFactor;

		this.setPosition(this.posX, this.posY, this.posZ);
	}

	private void onImpact(MovingObjectPosition impactPoint) 
	{
		if (!worldObj.isRemote)
		{
			if (impactPoint.entityHit != null && impactPoint.entityHit instanceof EntityLivingBase)
			{
				final EnumCocoonType cocoonType = EnumCocoonType.getCocoonTypeByCapturedClass(impactPoint.entityHit.getClass());
				final EntityLivingBase entityHit = (EntityLivingBase) impactPoint.entityHit;
				final float attackPower = isPoison ? 4.0F : 0.0F;
				entityHit.attackEntityFrom(DamageSource.causeMobDamage(shooter), attackPower);

				if (cocoonType != null)
				{
					if (entityHit.getHealth() > 0.4F)
					{
						final Random rand = new Random();
						final int intHealth = (int)entityHit.getHealth();
						final int captureDifficulty = cocoonType.getEntityCatchDifficulty();

						//TODO
						//						if(movingobjectposition.entityHit instanceof EntityFly)
						//						{
						//							EntityFly ef = (EntityFly) movingobjectposition.entityHit;
						//							ef.setCocooned(true);
						//							setDead();
						//						}

						if (captureDifficulty != 0 && rand.nextInt(intHealth / captureDifficulty + 1) != 0)
						{
							setToInactive();
							setNoBlockSpawn();
							return;
						}

						else
						{
							final EntityCocoon entityCocoon = new EntityCocoon(worldObj, cocoonType);

							entityCocoon.setLocationAndAngles(entityHit.posX, entityHit.posY, entityHit.posZ, entityHit.rotationYaw, entityHit.rotationPitch);
							worldObj.spawnEntityInWorld(entityCocoon);
							entityHit.setDead();
							setDead();
						}
					}
				}
			}

			else //Hit a block.
			{
				if (doBlockSpawn)
				{
					if (isPoison)
					{
						worldObj.setBlock(impactPoint.blockX, impactPoint.blockY, impactPoint.blockZ, SpiderQueen.getInstance().blockPoisonWeb);
					}

					else
					{
						worldObj.setBlock(impactPoint.blockX, impactPoint.blockY, impactPoint.blockZ, SpiderQueen.getInstance().blockSmallWeb);
					}
				}

				setDead();
			}
		}
	}

	private void setToInactive()
	{
		isInactive = true;

		final Random rand = new Random();
		if (rand.nextInt(2) == 0) { motionX = motionX * -0.2F; }
		if (rand.nextInt(2) == 0) { motionY = motionY * -0.2F; }
		if (rand.nextInt(2) == 0) { motionZ = motionZ * -0.2F; }
	}

	private void setNoBlockSpawn()
	{
		doBlockSpawn = false;
	}
	
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		//No data to write.
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		//No data to write.
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public float getCollisionBorderSize()
	{
		return 1.0F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}

		else
		{
			this.setBeenAttacked();

			if (damageSource.getEntity() != null)
			{
				final Vec3 sourceLookVector = damageSource.getEntity().getLookVec();

				if (sourceLookVector != null)
				{
					this.motionX = sourceLookVector.xCoord;
					this.motionY = sourceLookVector.yCoord;
					this.motionZ = sourceLookVector.zCoord;
					this.accelerationX = this.motionX * 0.1D;
					this.accelerationY = this.motionY * 0.1D;
					this.accelerationZ = this.motionZ * 0.1D;
				}

				if (damageSource.getEntity() instanceof EntityLivingBase)
				{
					this.shooter = (EntityPlayer)damageSource.getEntity();
				}

				return true;
			}

			else
			{
				return false;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}

	@Override
	public float getBrightness(float unknown)
	{
		return 1.0F;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getBrightnessForRender(float unknown)
	{
		return 15728880;
	}
}
