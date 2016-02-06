package sq.entity.throwable;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sq.entity.creature.EntitySpiderEx;

/**
 * The boom ball is a projectile that explodes on impact.
 */
public class EntityBoomBall extends Entity implements IProjectile
{
	private int	ticksInAir;
	public EntityLivingBase	shooter;

	public EntityBoomBall(World worldObj)
	{
		super(worldObj);
	}

	public EntityBoomBall(EntityLivingBase shooter, EntityLivingBase target, float speed)
	{
		this(shooter.worldObj);
		this.shooter = shooter;
		renderDistanceWeight = 10.0D;

		posY = shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D;
		final double deltaX = target.posX - shooter.posX;
		final double deltaY = target.getEntityBoundingBox().minY + target.height / 3.0F - posY;
		final double deltaZ = target.posZ - shooter.posZ;
		final double distanceXZ = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);

		if (distanceXZ >= 1.0E-7D)
		{
			final float rotationYaw = (float) (Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI) - 90.0F;
			final float rotationPitch = (float) -(Math.atan2(deltaY, distanceXZ) * 180.0D / Math.PI);
			final double modX = deltaX / distanceXZ;
			final double modY = deltaZ / distanceXZ;

			setLocationAndAngles(shooter.posX + modX, posY, shooter.posZ + modY, rotationYaw, rotationPitch);

			final float modDeltaY = (float) distanceXZ * 0.2F;
			setThrowableHeading(deltaX, deltaY - modDeltaY, deltaZ, speed, 16F);
		}
	}

	@Override
	protected void entityInit()
	{
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		double weightedLength = getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
		weightedLength *= 64.0D;
		return distance < weightedLength * weightedLength;
	}

	@Override
	public void onUpdate()
	{
		if (!worldObj.isRemote && (ticksInAir > 150 || shooter != null && shooter.isDead || !worldObj.isAirBlock(new BlockPos((int) posX, (int) posY, (int) posZ))))
		{
			setDead();
		}

		else
		{
			super.onUpdate();
			updateCollision();
			updateMotion();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		// No data to write.
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		// No data to read.
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
		return false;
	}

	//FIXME
//	@SideOnly(Side.CLIENT)
//	@Override
//	public float getShadowSize()
//	{
//		return 0.0F;
//	}

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
	public void setThrowableHeading(double posX, double posY, double posZ, float speed, float unknown)
	{
		final float distanceXYZ = MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
		posX /= distanceXYZ;
		posY /= distanceXYZ;
		posZ /= distanceXYZ;

		posX += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * unknown;
		posY += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * unknown;
		posZ += rand.nextGaussian() * (rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * unknown;

		posX *= speed;
		posY *= speed;
		posZ *= speed;

		motionX = posX;
		motionZ = posZ;

		final float distanceXZ = MathHelper.sqrt_double(posX * posX + posZ * posZ);
		prevRotationYaw = rotationYaw = (float) (Math.atan2(posX, posZ) * 180.0D / Math.PI);
		prevRotationPitch = rotationPitch = (float) (Math.atan2(posY, distanceXZ) * 180.0D / Math.PI);
	}

	@Override
	public void setDead()
	{
		super.setDead();
	}

	private void updateCollision()
	{
		Vec3 currentVector = new Vec3(posX, posY, posZ);
		Vec3 nextVector = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition collisionPosition = worldObj.rayTraceBlocks(currentVector, nextVector);
		currentVector = new Vec3(posX, posY, posZ);
		nextVector = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);

		if (collisionPosition != null)
		{
			nextVector = new Vec3(collisionPosition.hitVec.xCoord, collisionPosition.hitVec.yCoord, collisionPosition.hitVec.zCoord);
		}

		Entity collidedEntity = null;
		final List entitiesInAABB = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double lastDistance = 0.0D;

		for (int counter = 0; counter < entitiesInAABB.size(); ++counter)
		{
			final Entity entityInList = (Entity) entitiesInAABB.get(counter);

			if (entityInList.canBeCollidedWith() && (!entityInList.isEntityEqual(shooter) || ticksInAir >= 25))
			{
				final AxisAlignedBB AABB = entityInList.getEntityBoundingBox().expand(0.3D, 0.3D, 0.3D);
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
		final float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) + 90.0F;

		for (rotationPitch = (float) (Math.atan2(f1, motionY) * 180.0D / Math.PI) - 90.0F; rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
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
		float motionFactor = 0.95F;

		if (isInWater())
		{
			setDead();
		}

		motionX *= motionFactor;
		motionY *= motionFactor;
		motionZ *= motionFactor;

		setPosition(posX, posY, posZ);
	}

	private void onImpact(MovingObjectPosition impactPoint)
	{
		if (!worldObj.isRemote)
		{
			EntitySpiderEx spider = (EntitySpiderEx)shooter;
			
			if (spider.getPowered())
			{
				worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 5.0F, false);
			}
			
			else
			{
				worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, false);				
			}
			
			setDead();
		}
	}
}