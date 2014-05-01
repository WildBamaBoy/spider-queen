package spiderqueen.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
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

public class EntityWeb extends Entity implements IProjectile
{
	private int ticksInAir;
	private boolean isInactive;
	private boolean isPoison;
	private boolean doBlockSpawn;

	public EntityPlayer shooter;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;

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
		this.accelerationX = vec.xCoord * 1.5;
		this.accelerationY = vec.yCoord * 1.5;
		this.accelerationZ = vec.zCoord * 1.5;

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

	public EntityWeb(EntityLivingBase shooter, EntityLivingBase target, float speed)
	{
		super(shooter.worldObj);
		this.renderDistanceWeight = 10.0D;

		this.posY = shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D;
		double d0 = target.posX - shooter.posX;
		double d1 = target.boundingBox.minY + (double)(target.height / 3.0F) - this.posY;
		double d2 = target.posZ - shooter.posZ;
		double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f2, f3);
			this.yOffset = 0.0F;
			float f4 = (float)d3 * 0.2F;
			this.setThrowableHeading(d0, d1 + (double)f4, d2, speed, 16F);
		}
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
		double weightedLength = boundingBox.getAverageEdgeLength() * 4.0D;
		weightedLength *= 64.0D;
		return distance < weightedLength * weightedLength;
	}

	@Override
	public void onUpdate()
	{
		if (!worldObj.isRemote && (ticksInAir > 150 || shooter != null && shooter.isDead || !worldObj.blockExists((int)posX, (int)posY, (int)posZ)))
		{
			setDead();
		}

		else
		{
			super.onUpdate();

			if (!worldObj.isRemote)
			{
				if (shooter != null && getDistanceToEntity(shooter) > 150.0D)
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
		Vec3 currentVector = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
		Vec3 nextVector = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition collisionPosition = worldObj.rayTraceBlocks(currentVector, nextVector);
		currentVector = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
		nextVector = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);

		if (collisionPosition != null)
		{
			nextVector = worldObj.getWorldVec3Pool().getVecFromPool(collisionPosition.hitVec.xCoord, collisionPosition.hitVec.yCoord, collisionPosition.hitVec.zCoord);
		}

		Entity collidedEntity = null;
		List entitiesInAABB = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double lastDistance = 0.0D;

		for (int counter = 0; counter < entitiesInAABB.size(); ++counter)
		{
			final Entity entityInList = (Entity)entitiesInAABB.get(counter);

			if (entityInList.canBeCollidedWith() && (!entityInList.isEntityEqual(shooter) || ticksInAir >= 25))
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
			onImpact(collisionPosition);
		}
	}

	private void updateMotion()
	{
		ticksInAir++;

		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float)(Math.atan2(motionZ, motionX) * 180.0D / Math.PI) + 90.0F;

		for (rotationPitch = (float)(Math.atan2((double)f1, motionY) * 180.0D / Math.PI) - 90.0F; rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
		{
			;
		}

		while (rotationPitch - prevRotationPitch >= 180.0F)
		{
			prevRotationPitch += 360.0F;
		}

		while (rotationYaw - prevRotationYaw < -180.0F)
		{
			prevRotationYaw -= 360.0F;
		}

		while (rotationYaw - prevRotationYaw >= 180.0F)
		{
			prevRotationYaw += 360.0F;
		}

		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		float motionFactor =  0.95F;

		if (isInWater())
		{
			for (int counter = 0; counter < 4; ++counter)
			{
				float speedFactor = 0.25F;
				worldObj.spawnParticle("bubble", 
						posX - motionX * (double)speedFactor, 
						posY - motionY * (double)speedFactor, 
						posZ - motionZ * (double)speedFactor, 
						motionX, motionY, motionZ);
			}

			motionFactor = 0.8F;
		}

		motionX += accelerationX;
		motionY += accelerationY;
		motionZ += accelerationZ;
		motionX *= (double)motionFactor;
		motionY *= (double)motionFactor;
		motionZ *= (double)motionFactor;

		setPosition(posX, posY, posZ);
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
				final Block blockHit = worldObj.getBlock(impactPoint.blockX, impactPoint.blockY, impactPoint.blockZ);

				if (blockHit != SpiderQueen.getInstance().blockWeb && blockHit != SpiderQueen.getInstance().blockPoisonWeb)
				{
					if (doBlockSpawn)
					{
						final int modX = accelerationX > 0.5 ? -1 : accelerationX < -0.5 ? 1 : 0;
						final int modZ = accelerationZ > 0.5 ? -1 : accelerationZ < -0.5 ? 1 : 0;
						final int modY = !worldObj.isAirBlock(impactPoint.blockX + modX, impactPoint.blockY, impactPoint.blockZ + modZ) ? 1 : 0;

						if (worldObj.isAirBlock(impactPoint.blockX + modX, impactPoint.blockY + modY, impactPoint.blockZ + modZ))
						{
							if (isPoison)
							{
								worldObj.setBlock(impactPoint.blockX, impactPoint.blockY + 1, impactPoint.blockZ, SpiderQueen.getInstance().blockPoisonWeb);
							}

							else
							{
								worldObj.setBlock(impactPoint.blockX + modX, impactPoint.blockY + modY, impactPoint.blockZ + modZ, SpiderQueen.getInstance().blockWeb);
							}
						}
					}

					setDead();
				}
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
		//No data to read.
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
		if (isEntityInvulnerable())
		{
			return false;
		}

		else
		{
			setBeenAttacked();

			if (damageSource.getEntity() != null)
			{
				final Vec3 sourceLookVector = damageSource.getEntity().getLookVec();

				if (sourceLookVector != null)
				{
					motionX = sourceLookVector.xCoord;
					motionY = sourceLookVector.yCoord;
					motionZ = sourceLookVector.zCoord;
					accelerationX = motionX * 0.1D;
					accelerationY = motionY * 0.1D;
					accelerationZ = motionZ * 0.1D;
				}

				if (damageSource.getEntity() instanceof EntityLivingBase)
				{
					shooter = (EntityPlayer)damageSource.getEntity();
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

	@Override
	public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) 
	{
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= (double)f2;
		par3 /= (double)f2;
		par5 /= (double)f2;
		par1 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)par8;
		par3 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)par8;
		par5 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)par8;
		par1 *= (double)par7;
		par3 *= (double)par7;
		par5 *= (double)par7;
		this.motionX = par1;
		this.motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)f3) * 180.0D / Math.PI);
	}
}
