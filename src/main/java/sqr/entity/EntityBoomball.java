package sqr.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Entity, AxisAlignedBB, EntityLivingBase, MathHelper,
//            World, Vec3D, MovingObjectPosition, NBTTagCompound,
//            EntityPlayer, ItemStack, Item, InventoryPlayer

public class EntityBoomball extends Entity
{
	
	public EntityBoomball(World world)
	{
		super(world);
		this.xTileSnowball = -1;
		this.yTileSnowball = -1;
		this.zTileSnowball = -1;
		this.inTileSnowball = 0;
		this.inGroundSnowball = false;
		this.shakeSnowball = 0;
		this.ticksInAirSnowball = 0;
		this.setSize(0.25F, 0.25F);
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public boolean isInRangeToRenderDist(double d)
	{
		double d1 = this.boundingBox.getAverageEdgeLength() * 4D;
		d1 *= 64D;
		return d < d1 * d1;
	}
	
	public EntityBoomball(World world, EntityLivingBase entityliving)
	{
		super(world);
		this.xTileSnowball = -1;
		this.yTileSnowball = -1;
		this.zTileSnowball = -1;
		this.inTileSnowball = 0;
		this.inGroundSnowball = false;
		this.shakeSnowball = 0;
		this.ticksInAirSnowball = 0;
		this.thrower = entityliving;
		this.setSize(0.25F, 0.25F);
		this.setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180F * 3.141593F) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180F * 3.141593F) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		final float f = 0.4F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180F * 3.141593F) * f;
		this.motionZ = MathHelper.cos(this.rotationYaw / 180F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180F * 3.141593F) * f;
		this.motionY = -MathHelper.sin(this.rotationPitch / 180F * 3.141593F) * f;
		this.setBoomballHeading(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
	}
	
	public void hurtArea(float explosionSize)
	{
		final int k = MathHelper.floor_double(this.posX - explosionSize - 1.0D);
		final int i1 = MathHelper.floor_double(this.posX + explosionSize + 1.0D);
		final int k1 = MathHelper.floor_double(this.posY - explosionSize - 1.0D);
		final int l1 = MathHelper.floor_double(this.posY + explosionSize + 1.0D);
		final int i2 = MathHelper.floor_double(this.posZ - explosionSize - 1.0D);
		final int j2 = MathHelper.floor_double(this.posZ + explosionSize + 1.0D);
		final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(k, k1, i2, i1, l1, j2));
		final Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		for (int k2 = 0; k2 < list.size(); k2++)
		{
			final Entity entity = (Entity) list.get(k2);
			final double d4 = entity.getDistance(this.posX, this.posY, this.posZ) / explosionSize;
			if (d4 <= 1.0D)
			{
				double d6 = entity.posX - this.posX;
				double d8 = entity.posY - this.posY;
				double d10 = entity.posZ - this.posZ;
				final double d11 = MathHelper.sqrt_double(d6 * d6 + d8 * d8 + d10 * d10);
				d6 /= d11;
				d8 /= d11;
				d10 /= d11;
				final double d12 = this.worldObj.getBlockDensity(vec3d, entity.boundingBox);
				final double d13 = (1.0D - d4) * d12;
				boolean don = false;
				if (entity instanceof EntityPlayer)
				{
					don = true;
				}
				if (entity instanceof EntitySpiderEx)
				{
					don = true;
				}
				if (entity instanceof EntityESpiderQueen)
				{
					final EntityESpiderQueen ee = (EntityESpiderQueen) entity;
					if (ee.getFriendly())
					{
						don = true;
					}
				}
				
				if (!don)
				{
					entity.attackEntityFrom(DamageSource.causeMobDamage(this.thrower), (int) ((d13 * d13 + d13) / 2D * 8D * explosionSize + 1.0D) / 16);
				}
				final double d14 = d13;
				entity.motionX += d6 * d14;
				entity.motionY += d8 * d14;
				entity.motionZ += d10 * d14;
			}
		}
	}
	
	public EntityBoomball(World world, double d, double d1, double d2)
	{
		super(world);
		this.xTileSnowball = -1;
		this.yTileSnowball = -1;
		this.zTileSnowball = -1;
		this.inTileSnowball = 0;
		this.inGroundSnowball = false;
		this.shakeSnowball = 0;
		this.ticksInAirSnowball = 0;
		this.ticksInGroundSnowball = 0;
		this.setSize(0.25F, 0.25F);
		this.setPosition(d, d1, d2);
		this.yOffset = 0.0F;
	}
	
	public void setBoomballHeading(double d, double d1, double d2, float f, float f1)
	{
		final float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += this.rand.nextGaussian() * 0.0074999998323619366D * f1;
		d1 += this.rand.nextGaussian() * 0.0074999998323619366D * f1;
		d2 += this.rand.nextGaussian() * 0.0074999998323619366D * f1;
		d *= f;
		d1 *= f;
		d2 *= f;
		this.motionX = d;
		this.motionY = d1;
		this.motionZ = d2;
		final float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(d, d2) * 180D / 3.1415927410125732D);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(d1, f3) * 180D / 3.1415927410125732D);
		this.ticksInGroundSnowball = 0;
	}
	
	@Override
	public void setVelocity(double d, double d1, double d2)
	{
		this.motionX = d;
		this.motionY = d1;
		this.motionZ = d2;
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			final float f = MathHelper.sqrt_double(d * d + d2 * d2);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(d, d2) * 180D / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(d1, f) * 180D / 3.1415927410125732D);
		}
	}
	
	@Override
	public void onUpdate()
	{
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();
		if (this.shakeSnowball > 0)
		{
			this.shakeSnowball--;
		}
		if (this.inGroundSnowball)
		{
			// TODO Explode on an area
			// final int i = this.worldObj.getBlock(this.xTileSnowball,
			// this.yTileSnowball, this.zTileSnowball);
			// if(i != this.inTileSnowball)
			// {
			// this.inGroundSnowball = false;
			// this.motionX *= this.rand.nextFloat() * 0.2F;
			// this.motionY *= this.rand.nextFloat() * 0.2F;
			// this.motionZ *= this.rand.nextFloat() * 0.2F;
			// this.ticksInGroundSnowball = 0;
			// this.ticksInAirSnowball = 0;
			// } else
			// {
			// this.ticksInGroundSnowball++;
			// if(this.ticksInGroundSnowball > 20)
			// {
			// this.hurtArea(6F);
			// this.setDead();
			// }
			// return;
			// }
		}
		else
		{
			this.ticksInAirSnowball++;
		}
		
		if (this.ticksInAirSnowball > 10)
		{
			this.hurtArea(6F);
			this.setDead();
		}
		Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3d, vec3d1);
		vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		
		if (Math.abs(this.motionX) < 0.1F & Math.abs(this.motionZ) < 0.1F)
		{
			this.hurtArea(6F);
			this.setDead();
			return;
		}
		else
		{
			this.worldObj.spawnParticle("bubble", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("bubble", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("bubble", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
		
		if (movingobjectposition != null)
		{
			vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		if (!this.worldObj.isRemote)
		{
			Entity entity = null;
			final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d = 0.0D;
			for (int l = 0; l < list.size(); l++)
			{
				final Entity entity1 = (Entity) list.get(l);
				if (!entity1.canBeCollidedWith() || entity1 == this.thrower && this.ticksInAirSnowball < 5)
				{
					continue;
				}
				final float f4 = 0.3F;
				final AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f4, f4, f4);
				final MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
				if (movingobjectposition1 == null)
				{
					continue;
				}
				final double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
				if (d1 < d || d == 0.0D)
				{
					entity = entity1;
					d = d1;
				}
			}
			
			if (entity != null)
			{
				movingobjectposition = new MovingObjectPosition(entity);
			}
		}
		if (movingobjectposition != null)
		{
			if (movingobjectposition.entityHit != null)
			{
				movingobjectposition.entityHit.motionX += this.motionX * 3D;
				movingobjectposition.entityHit.motionY += this.motionY * 3D;
				movingobjectposition.entityHit.motionZ += this.motionZ * 3D;
				
				boolean don = false;
				if (movingobjectposition.entityHit instanceof EntityPlayer)
				{
					don = true;
				}
				if (movingobjectposition.entityHit instanceof EntitySpiderEx)
				{
					don = true;
				}
				if (movingobjectposition.entityHit instanceof EntityESpiderQueen)
				{
					final EntityESpiderQueen ee = (EntityESpiderQueen) movingobjectposition.entityHit;
					if (ee.getFriendly())
					{
						don = true;
					}
				}
				
				if (!don)
				{
					if (!movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.thrower), 3))
						;
				}
			}
			for (int j = 0; j < 8; j++)
			{
				// worldObj.spawnParticle("splash", posX, posY, posZ, 0.0D,
				// 0.0D, 0.0D);
			}
			this.hurtArea(6F);
			this.setDead();
		}
		this.motionY = 0;
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		final float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180D / 3.1415927410125732D);
		for (this.rotationPitch = (float) (Math.atan2(this.motionY, f) * 180D / 3.1415927410125732D); this.rotationPitch - this.prevRotationPitch < -180F; this.prevRotationPitch -= 360F)
		{
		}
		for (; this.rotationPitch - this.prevRotationPitch >= 180F; this.prevRotationPitch += 360F)
		{
		}
		for (; this.rotationYaw - this.prevRotationYaw < -180F; this.prevRotationYaw -= 360F)
		{
		}
		for (; this.rotationYaw - this.prevRotationYaw >= 180F; this.prevRotationYaw += 360F)
		{
		}
		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float f1 = 0.99F;
		final float f2 = 0.03F;
		if (this.isInWater())
		{
			for (int k = 0; k < 4; k++)
			{
				final float f3 = 0.25F;
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ);
			}
			
			f1 = 0.8F;
		}
		this.motionX *= f1;
		this.motionY *= f1;
		this.motionZ *= f1;
		this.motionY -= f2;
		this.setPosition(this.posX, this.posY, this.posZ);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short) this.xTileSnowball);
		nbttagcompound.setShort("yTile", (short) this.yTileSnowball);
		nbttagcompound.setShort("zTile", (short) this.zTileSnowball);
		nbttagcompound.setByte("inTile", (byte) this.inTileSnowball);
		nbttagcompound.setByte("shake", (byte) this.shakeSnowball);
		nbttagcompound.setByte("inGround", (byte) (this.inGroundSnowball ? 1 : 0));
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.xTileSnowball = nbttagcompound.getShort("xTile");
		this.yTileSnowball = nbttagcompound.getShort("yTile");
		this.zTileSnowball = nbttagcompound.getShort("zTile");
		this.inTileSnowball = nbttagcompound.getByte("inTile") & 0xff;
		this.shakeSnowball = nbttagcompound.getByte("shake") & 0xff;
		this.inGroundSnowball = nbttagcompound.getByte("inGround") == 1;
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if (this.inGroundSnowball && this.thrower == entityplayer && this.shakeSnowball <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1)))
		{
			// worldObj.playSoundAtEntity(this, "random.pop", 0.2F,
			// ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			// entityplayer.onItemPickup(this, 1);
			this.hurtArea(6F);
			this.setDead();
		}
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}
	
	private int xTileSnowball;
	private int yTileSnowball;
	private int zTileSnowball;
	private int inTileSnowball;
	private boolean inGroundSnowball;
	public int shakeSnowball;
	private EntityLivingBase thrower;
	private int ticksInGroundSnowball;
	private int ticksInAirSnowball;
}
