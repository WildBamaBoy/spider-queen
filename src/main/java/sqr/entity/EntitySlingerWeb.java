

package sqr.entity;

import java.util.List;

import net.minecraft.block.material.Material;
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

// Referenced classes of package net.minecraft.src:
//            Entity, Vec3D, AxisAlignedBB, World,
//            Item, EntityPlayer, EntityItem, MathHelper,
//            ItemStack, StatList, Material, NBTTagCompound,
//            MovingObjectPosition

public class EntitySlingerWeb extends Entity
{

	public EntitySlingerWeb(World world)
	{
		super(world);
		this.tileX = -1;
		this.tileY = -1;
		this.tileZ = -1;
		this.field_4092_g = 0;
		this.field_4091_h = false;
		this.field_4098_a = 0;
		this.field_4089_j = 0;
		this.field_4088_k = 0;
		this.field_4096_c = null;
		this.setSize(0.15F, 0.15F);
		//ignoreFrustumCheck = true;
		this.ddist = 0;
	}

	public EntitySlingerWeb(World world, double d, double d1, double d2)
	{
		this(world);
		this.setPosition(d, d1, d2);
		//ignoreFrustumCheck = true;
	}

	public EntitySlingerWeb(World world, EntityPlayer entityplayer)
	{
		super(world);
		this.tileX = -1;
		this.tileY = -1;
		this.tileZ = -1;
		this.field_4092_g = 0;
		this.field_4091_h = false;
		this.field_4098_a = 0;
		this.field_4089_j = 0;
		this.field_4088_k = 0;
		this.field_4096_c = null;
		//ignoreFrustumCheck = true;
		this.angler = entityplayer;
		this.holder = this.angler;
//		this.angler.webEntity = this; //TODO
		this.setSize(0.25F, 0.25F);
		this.setLocationAndAngles(entityplayer.posX, entityplayer.posY + 1.6200000000000001D - entityplayer.yOffset, entityplayer.posZ, entityplayer.rotationYaw, entityplayer.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180F * 3.141593F) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180F * 3.141593F) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		final float f = 8F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180F * 3.141593F) * f;
		this.motionZ = MathHelper.cos(this.rotationYaw / 180F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180F * 3.141593F) * f;
		this.motionY = -MathHelper.sin(this.rotationPitch / 180F * 3.141593F) * f;
		this.func_4042_a(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
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

	public void func_4042_a(double d, double d1, double d2, float f,
			float f1)
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
		this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(d, d2) * 180D / 3.1415927410125732D);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(d1, f3) * 180D / 3.1415927410125732D);
		this.field_4090_i = 0;
	}

	@Override
	public void setPositionAndRotation2(double d, double d1, double d2, float f,
			float f1, int i)
	{
		this.field_6387_m = d;
		this.field_6386_n = d1;
		this.field_6385_o = d2;
		this.field_6384_p = f;
		this.field_6383_q = f1;
		this.field_6388_l = i;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	@Override
	public void setVelocity(double d, double d1, double d2)
	{
		this.velocityX = this.motionX = d;
		this.velocityY = this.motionY = d1;
		this.velocityZ = this.motionZ = d2;
	}

	public void matchMotion(int typ, double amt)
	{
		double amt2 = amt;
		if(typ == 0)
		{
			amt2 = amt - this.holder.motionX;
			this.holder.motionX = amt;
			if(this.field_4096_c != null) { this.field_4096_c.motionX = this.field_4096_c.motionX - amt2; }
		}
		if(typ == 1)
		{
			amt2 = amt - this.holder.motionY;
			this.holder.motionY = amt;
			if(this.field_4096_c != null) { this.field_4096_c.motionY = this.field_4096_c.motionY - amt2; }
		}
		if(typ == 2)
		{
			amt2 = amt - this.holder.motionZ;
			this.holder.motionZ = amt;
			if(this.field_4096_c != null) { this.field_4096_c.motionZ = this.field_4096_c.motionZ - amt2; }
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.holder != null & (this.field_4091_h || this.field_4096_c != null))
		{
			if(this.angler != null)
			{
				this.angler.fallDistance = 0.0F;
				if(this.angler.isSneaking()) { this.ddist++; }
			}
			final Vec3 ww = Vec3.createVectorHelper(this.posX,this.posY,this.posZ);
			final Vec3 aa = Vec3.createVectorHelper(this.holder.posX,this.holder.posY,this.holder.posZ);


			if(this.ddist != 0)
			{
				if(this.ddist < 2) { this.ddist = 2; }
				if(this.ddist > 1000) { this.ddist = 1000; }
			}

			if(this.ddist == 0) { this.ddist = ww.squareDistanceTo(aa); }
			if(ww.squareDistanceTo(aa) > this.ddist / 4 * 3)
			{
				final Vec3 nn = Vec3.createVectorHelper(this.holder.motionX * 1.2D + (this.posX - this.holder.posX) / 2, this.holder.motionY * 1.2D + (this.posY - this.holder.posY) / 2, this.holder.motionZ * 1.2D + (this.posZ - this.holder.posZ) / 2).normalize();
				double dd2 = 0.3D;// + (ww.squareDistanceTo(aa) - ddist) / (ddist);

				if(this.field_4096_c != null) { dd2 = dd2 / 2; }

				final double tmotionX = this.holder.motionX * 1D + nn.xCoord * dd2 / 2D;
				final double tmotionY = this.holder.motionY * 1D + nn.yCoord * dd2 / 2D;
				final double tmotionZ = this.holder.motionZ * 1D + nn.zCoord * dd2 / 2D;


				if(this.holder.posX > this.posX) { if(tmotionX < this.holder.motionX) { this.matchMotion(0, tmotionX); } } else { if(tmotionX > this.holder.motionX) { this.matchMotion(0, tmotionX); } }
				if(this.holder.posY > this.posY) { if(tmotionY < this.holder.motionY) { this.matchMotion(1, tmotionY); } } else { if(tmotionY > this.holder.motionY) { this.matchMotion(1, tmotionY); } }
				if(this.holder.posZ > this.posZ) { if(tmotionZ < this.holder.motionZ) { this.matchMotion(2, tmotionZ); } } else { if(tmotionZ > this.holder.motionZ) { this.matchMotion(2, tmotionZ); } }

			}
			else
			{
				if(this.angler != null)
				{
					//Replaces isJumping.
					if(this.angler.isAirBorne) { this.ddist = this.ddist - 6; }
				}
			}
		}

		if(this.field_6388_l > 0)
		{
			final double d = this.posX + (this.field_6387_m - this.posX) / this.field_6388_l;
			final double d1 = this.posY + (this.field_6386_n - this.posY) / this.field_6388_l;
			final double d2 = this.posZ + (this.field_6385_o - this.posZ) / this.field_6388_l;
			double d4;
			for(d4 = this.field_6384_p - this.rotationYaw; d4 < -180D; d4 += 360D) { }
			for(; d4 >= 180D; d4 -= 360D) { }
			this.rotationYaw += d4 / this.field_6388_l;
			this.rotationPitch += (this.field_6383_q - this.rotationPitch) / this.field_6388_l;
			this.field_6388_l--;
			this.setPosition(d, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
			return;
		}
		if(!this.worldObj.isRemote)
		{
			if(this.holder.isDead || !this.holder.isEntityAlive() || this.getDistanceSqToEntity(this.holder) > 1024D)
			{
				this.setDead();
				//TODO
//				if(this.angler != null) { this.angler.webEntity = null; }
				return;
			}
			if(this.field_4096_c != null)
			{
				if(this.field_4096_c.isDead)
				{
					this.field_4096_c = null;
				} else
				{
					this.posX = this.field_4096_c.posX;
					this.posY = this.field_4096_c.boundingBox.minY + this.field_4096_c.height * 0.80000000000000004D;
					this.posZ = this.field_4096_c.posZ;
					return;
				}
			}
		}
		if(this.field_4098_a > 0)
		{
			this.field_4098_a--;
		}
		if(this.field_4091_h)
		{

			this.field_4090_i++;
			if(this.field_4090_i == 1200)
			{
				this.setDead();
			}
			return;

		} else
		{
			this.field_4089_j++;
		}
		Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3d, vec3d1);
		vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		if(movingobjectposition != null)
		{
			vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		Entity entity = null;
		final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double d3 = 0.0D;
		for(int j = 0; j < list.size(); j++)
		{
			final Entity entity1 = (Entity)list.get(j);
			if(!entity1.canBeCollidedWith() || entity1 == this.holder && this.field_4089_j < 5)
			{
				continue;
			}
			final float f2 = 0.3F;
			final AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f2, f2, f2);
			final MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
			if(movingobjectposition1 == null)
			{
				continue;
			}
			final double d6 = vec3d.distanceTo(movingobjectposition1.hitVec);
			if(d6 < d3 || d3 == 0.0D)
			{
				entity = entity1;
				d3 = d6;
			}
		}

		if(entity != null)
		{
			movingobjectposition = new MovingObjectPosition(entity);
		}
		if(movingobjectposition != null)
		{
			if(movingobjectposition.entityHit != null)
			{
				if(movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.holder), 0))
				{
					this.field_4096_c = movingobjectposition.entityHit;
				}
			} else
			{
				this.motionX = this.motionX * 0.7; this.motionY = this.motionY * 0.7; this.motionZ = this.motionZ * 0.7;
				this.field_4091_h = true;
				this.posX = this.posX + this.motionX;
				this.posY = this.posY + this.motionY;
				this.posZ = this.posZ + this.motionZ;

			}
		}
		if(this.field_4091_h)
		{
			return;
		}
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		final float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180D / 3.1415927410125732D);
		for(this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180D / 3.1415927410125732D); this.rotationPitch - this.prevRotationPitch < -180F; this.prevRotationPitch -= 360F) { }
		for(; this.rotationPitch - this.prevRotationPitch >= 180F; this.prevRotationPitch += 360F) { }
		for(; this.rotationYaw - this.prevRotationYaw < -180F; this.prevRotationYaw -= 360F) { }
		for(; this.rotationYaw - this.prevRotationYaw >= 180F; this.prevRotationYaw += 360F) { }
		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float f1 = 0.92F;
		if(this.isCollidedVertically || this.isCollidedHorizontally)
		{
			f1 = 0.5F;
			this.field_4091_h = true;
		}
		final int k = 5;
		double d5 = 0.0D;
		for(int l = 0; l < k; l++)
		{
			final double d8 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (l + 0) / k - 0.125D + 0.125D;
			final double d9 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (l + 1) / k - 0.125D + 0.125D;
			final AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d8, this.boundingBox.minZ, this.boundingBox.maxX, d9, this.boundingBox.maxZ);
			if(this.worldObj.isAABBInMaterial(axisalignedbb1, Material.water))
			{
				d5 += 1.0D / k;
			}
		}

		if(d5 > 0.0D)
		{
			if(this.field_4088_k > 0)
			{
				this.field_4088_k--;
			} else
			{
				char c = '\u01F4';
				if(this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) + 1, MathHelper.floor_double(this.posZ)))
				{
					c = '\u012C';
				}
				if(this.rand.nextInt(c) == 0)
				{
					this.field_4088_k = this.rand.nextInt(30) + 10;
					this.motionY -= 0.20000000298023224D;
					this.worldObj.playSoundAtEntity(this, "random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
					final float f3 = MathHelper.floor_double(this.boundingBox.minY);
					for(int i1 = 0; i1 < 1.0F + this.width * 20F; i1++)
					{
						final float f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
						final float f6 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
						this.worldObj.spawnParticle("bubble", this.posX + f4, f3 + 1.0F, this.posZ + f6, this.motionX, this.motionY - this.rand.nextFloat() * 0.2F, this.motionZ);
					}

					for(int j1 = 0; j1 < 1.0F + this.width * 20F; j1++)
					{
						final float f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
						final float f7 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
						this.worldObj.spawnParticle("splash", this.posX + f5, f3 + 1.0F, this.posZ + f7, this.motionX, this.motionY, this.motionZ);
					}

				}
			}
		}
		if(this.field_4088_k > 0)
		{
			this.motionY -= this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat() * 0.20000000000000001D;
		}
		final double d7 = d5 * 2D - 1.0D;
		this.motionY += 0.029999999105930328D * d7;
		if(d5 > 0.0D)
		{
			f1 = (float)(f1 * 0.90000000000000002D);
			this.motionY *= 0.80000000000000004D;
		}
		/*motionX *= f1;
        motionY *= f1;
        motionZ *= f1;*/
		this.setPosition(this.posX, this.posY, this.posZ);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short)this.tileX);
		nbttagcompound.setShort("yTile", (short)this.tileY);
		nbttagcompound.setShort("zTile", (short)this.tileZ);
		nbttagcompound.setByte("inTile", (byte)this.field_4092_g);
		nbttagcompound.setByte("shake", (byte)this.field_4098_a);
		nbttagcompound.setByte("inGround", (byte)(this.field_4091_h ? 1 : 0));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.tileX = nbttagcompound.getShort("xTile");
		this.tileY = nbttagcompound.getShort("yTile");
		this.tileZ = nbttagcompound.getShort("zTile");
		this.field_4092_g = nbttagcompound.getByte("inTile") & 0xff;
		this.field_4098_a = nbttagcompound.getByte("shake") & 0xff;
		this.field_4091_h = nbttagcompound.getByte("inGround") == 1;
	}

	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}

	public int catchFish()
	{
		return 0;
	}

	private int tileX;
	private int tileY;
	private int tileZ;
	private int field_4092_g;
	private boolean field_4091_h;
	public int field_4098_a;
	public EntityPlayer angler;
	public EntityLivingBase holder;
	private int field_4090_i;
	private int field_4089_j;
	private int field_4088_k;
	public Entity field_4096_c;
	private int field_6388_l;
	private double field_6387_m;
	private double field_6386_n;
	private double field_6385_o;
	private double field_6384_p;
	private double field_6383_q;
	private double velocityX;
	private double velocityY;
	private double velocityZ;
	private double ddist;
}
