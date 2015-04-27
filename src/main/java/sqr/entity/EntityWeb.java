package sqr.entity;


import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sqr.core.minecraft.ModItems;

public class EntityWeb extends Entity
{

	public EntityWeb(World world)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inGround = false;
		this.arrowShake = 0;
		this.field_680_i = 0;
		this.setSize(0.15F, 0.15F);
		this.destr = 0;
		this.damage = 0;
		this.inactive = false;
		this.nomake = false;
	}

	public EntityWeb(World world, double d, double d1, double d2)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inGround = false;
		this.arrowShake = 0;
		this.field_680_i = 0;
		this.setSize(0.15F, 0.15F);
		this.setPosition(d, d1, d2);
		this.yOffset = 0.0F;
		this.inactive = false;
		this.nomake = false;
	}

	public EntityWeb(World world, EntityLivingBase entityliving, int dmg)
	{
		this(world,entityliving);
		this.damage = dmg;
		this.inactive = false;
		this.nomake = false;
	}

	public EntityWeb(World world, EntityLivingBase entityliving)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inGround = false;
		this.arrowShake = 0;
		this.field_680_i = 0;
		this.field_682_g = entityliving;
		this.setSize(0.15F, 0.15F);
		this.setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180F * 3.141593F) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180F * 3.141593F) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180F * 3.141593F);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180F * 3.141593F);
		this.motionY = -MathHelper.sin(this.rotationPitch / 180F * 3.141593F);
		this.setArrowHeading(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
		this.inactive = false;
		this.nomake = false;
	}

	public EntityWeb(World world, EntityLivingBase entityliving, boolean poisonous)
	{
		this(world,entityliving);
		this.poison = poisonous;
	}

	@Override
	protected void entityInit()
	{
	}

	@Override
	public boolean isInRangeToRenderDist(double d)
	{
		double d1 = this.boundingBox.getAverageEdgeLength() * 4D;
		d1 *= 128D;
		return d < d1 * d1;
	}

	public void setArrowHeading(double d, double d1, double d2, float f,
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
		this.field_681_h = 0;
	}

	@Override
	public void setVelocity(double d, double d1, double d2)
	{
		this.motionX = d;
		this.motionY = d1;
		this.motionZ = d2;
		if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			final float f = MathHelper.sqrt_double(d * d + d2 * d2);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(d, d2) * 180D / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(d1, f) * 180D / 3.1415927410125732D);
		}
	}

	public void setEntityDead2()
	{
		this.inactive = true;
		final Random rr = new Random();
		if(rr.nextInt(2) == 0) { this.motionX = this.motionX * -0.2F; }
		if(rr.nextInt(2) == 0) { this.motionY = this.motionY * -0.2F; }
		if(rr.nextInt(2) == 0) { this.motionZ = this.motionZ * -0.2F; }
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		//TODO Unusable, easily rewritten.
//
//		if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
//		{
//			final float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
//			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180D / 3.1415927410125732D);
//			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180D / 3.1415927410125732D);
//		}
//		if(this.arrowShake > 0)
//		{
//			this.arrowShake--;
//		}
//		if(this.inGround)
//		{
//			final int i = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
//			if(i != this.inTile || i == 0)
//			{
//				this.inGround = false;
//				this.motionX *= this.rand.nextFloat() * 0.2F;
//				this.motionY *= this.rand.nextFloat() * 0.2F;
//				this.motionZ *= this.rand.nextFloat() * 0.2F;
//				this.field_681_h = 0;
//				this.field_680_i = 0;
//			} else
//			{
//				this.field_681_h++;
//				if(this.field_681_h == 1200)
//				{
//					this.setDead();
//				}
//				return;
//			}
//		} else
//		{
//			this.field_680_i++;
//		}
//		Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
//		Vec3D vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
//		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3d, vec3d1);
//		vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
//		vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
//		if(movingobjectposition != null)
//		{
//			vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
//		}
//		Entity entity = null;
//		final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.8D, 1.8D, 1.8D));
//		double d = 0.0D;
//
//		for(int j = 0; j < list.size(); j++)
//		{
//			final Entity entity1 = (Entity)list.get(j);
//			if(!entity1.canBeCollidedWith() || entity1 == this.field_682_g && this.field_680_i < 5)
//			{
//				continue;
//			}
//			final float f4 = 0.05F;
//			final AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f4, f4, f4);
//			final MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
//			if(movingobjectposition1 == null)
//			{
//				continue;
//			}
//			final double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
//			if(d1 < d || d == 0.0D)
//			{
//				entity = entity1;
//				d = d1;
//			}
//		}
//
//		if(entity != null)
//		{
//			movingobjectposition = new MovingObjectPosition(entity);
//		}
//		if(movingobjectposition != null)
//		{
//			if(movingobjectposition.entityHit != null & !this.inactive)
//			{
//				if(movingobjectposition.entityHit instanceof EntityLivingBase)
//				{
//					final EntityLivingBase enl = (EntityLivingBase) movingobjectposition.entityHit;
//					if(!enl.spiderfriend)
//					{
//						int atk = 0;
//						if(this.poison) { atk = 4; }
//						enl.attackEntityFrom(DamageSource.causeMobDamage(this.field_682_g),atk);
//
//						int hh = enl.health;
//						if(hh > 0)
//						{
//							if(hh < 1) { hh = 1; }
//
//							final Random rr = new Random();
//
//							if(this.damage > 0)
//							{
//								if(movingobjectposition.entityHit instanceof EntityLivingBase)
//								{
//									this.setEntityDead2();
//									movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.field_682_g),this.damage);
//								}
//							}
//
//							if(movingobjectposition.entityHit instanceof EntityFly)
//							{
//								final EntityFly ef = (EntityFly) movingobjectposition.entityHit;
//								ef.setCocooned(true);
//								this.setDead();
//							}
//							//TODO 
////
////							if(movingobjectposition.entityHit instanceof EntityHuman)
////							{
////								this.worldObj.spawnEntityInWorld(new EntityCocoonHuman(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////
////							if(movingobjectposition.entityHit instanceof EntityChicken)
////							{
////								this.worldObj.spawnEntityInWorld(new EntityCocoon(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityPig)
////							{
////								this.worldObj.spawnEntityInWorld(new EntityCocoonPig(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntitySheep)
////							{
////								if(rr.nextInt(hh / 6 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonSheep(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityCreeper)
////							{
////								if(rr.nextInt(hh / 4 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonCreeper(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityZombie)
////							{
////								if(rr.nextInt(hh / 4 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonZombie(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityCow)
////							{
////								if(rr.nextInt(hh / 6 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonCow(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntitySkeleton)
////							{
////								if(rr.nextInt(hh / 4 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonSkeleton(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityWolf)
////							{
////								if(rr.nextInt(hh / 4 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonWolf(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityAnt || movingobjectposition.entityHit instanceof EntityRedAnt)
////							{
////								if(rr.nextInt(hh / 4 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonAnt(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityWasp)
////							{
////								if(rr.nextInt(hh / 4 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonWasp(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityGatherer)
////							{
////								if(rr.nextInt(hh / 4 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonGatherer(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityWarrior)
////							{
////								if(rr.nextInt(hh / 4 + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonWarrior(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////							if(movingobjectposition.entityHit instanceof EntityQueenBee)
////							{
////								if(rr.nextInt(hh + 1) != 0) { this.setEntityDead2(); return; }
////								this.worldObj.spawnEntityInWorld(new EntityCocoonQueenBee(this.worldObj, movingobjectposition.entityHit.posX,
////										movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
////										movingobjectposition.entityHit.rotationYaw,0));
////
////								movingobjectposition.entityHit.setDead();
////
////								this.setDead();
////							}
////
//
//						}
//					}
//				}
//			} else
//			{
//
//				float fm = -51F;
//				int bID = 0;
//				//while(fm < 50F)
//					//{
//					//if(bID != mod_SpiderQueen.smallWeb)
//						//{
//						bID = this.worldObj.getBlock((int)(this.posX + this.motionX / 25F * fm),(int)(this.posY + this.motionY / 25F * fm),(int)(this.posZ + this.motionZ / 25F * fm));
//						if(bID == Blocks.web) { fm = 55F; }
//
//						//}
//					//if(bID != mod_SpiderQueen.smallWeb)
//						//{
//						//						bID = worldObj.getBlock((int)(movingobjectposition.hitVec.xCoord + motionX / 25F * fm),(int)(movingobjectposition.hitVec.yCoord + motionY / 25F * fm),(int)(movingobjectposition.hitVec.zCoord + motionZ / 25F * fm));
//						//if(bID == Blocks.web) { fm = 55F; }
//						//}
//					fm = fm + 5F;
//					//}
//				if(bID == SQR.smallWeb)
//				{
//					// NOTHING
//				}
//				else
//				{
//					int ff = -5;
//
//					while(ff < 100)
//					{
//						this.xTile = (int)(movingobjectposition.hitVec.xCoord - this.motionX / 25F * ff);
//						this.yTile = (int)(movingobjectposition.hitVec.yCoord - this.motionY / 25F * ff);
//						this.zTile = (int)(movingobjectposition.hitVec.zCoord - this.motionZ / 25F * ff);
//						if(this.zTile < 0) { this.zTile--;}
//						if(this.xTile < 0) { this.xTile--;}
//
//						ff++;
//						int tId = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
//						if(tId == Blocks.tallGrass || tId == Blocks.deadBush)
//						{
//							tId = 0;
//							this.worldObj.setBlock(this.xTile,this.yTile,this.zTile,0);
//						}
//						if(tId == 0) { ff = 150; }
//						if(tId == SQR.smallWeb || tId == SQR.poisonWeb) { ff = 151; }
//					}
//
//					if(ff == 151)
//					{
//						if(this.nomake == false)
//						{
//							if(this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile) < 1)
//							{
//								this.worldObj.setBlockMetadata(this.xTile, this.yTile, this.zTile, this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile) + 1);
//							}
//							else
//							{
//
//								this.worldObj.setBlockWithNotify(this.xTile, this.yTile, this.zTile, Blocks.web);
//
//								//checkForBed(xTile,yTile,zTile,0);
//							}
//						}
//						this.setDead();
//						return;
//					}
//
//					if(ff == 150)
//					{
//						if(this.nomake == false)
//						{
//							if(this.poison)
//							{
//								this.worldObj.setBlockWithNotify(this.xTile, this.yTile, this.zTile, SQR.poisonWeb);
//							}
//							else
//							{
//								this.worldObj.setBlockWithNotify(this.xTile, this.yTile, this.zTile, SQR.smallWeb);
//							}
//						}
//						this.setDead();
//						return;
//					}
//					else
//					{
//						this.motionX = this.motionX * -0.8F;
//						this.motionY = this.motionY * -0.8F;
//						this.motionZ = this.motionZ * -0.8F;
//					}
//				}
//			}
//		}
//		this.posX += this.motionX;
//		this.posY += this.motionY;
//		this.posZ += this.motionZ;
//
//
//		final float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
//		this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180D / 3.1415927410125732D);
//		for(this.rotationPitch = (float)(Math.atan2(this.motionY, f2) * 180D / 3.1415927410125732D); this.rotationPitch - this.prevRotationPitch < -180F; this.prevRotationPitch -= 360F) { }
//		for(; this.rotationPitch - this.prevRotationPitch >= 180F; this.prevRotationPitch += 360F) { }
//		for(; this.rotationYaw - this.prevRotationYaw < -180F; this.prevRotationYaw -= 360F) { }
//		for(; this.rotationYaw - this.prevRotationYaw >= 180F; this.prevRotationYaw += 360F) { }
//		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
//		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
//		float f3 = 0.99F;
//		final float f5 = 0.03F;
//		if(this.handleWaterMovement())
//		{
//			for(int k = 0; k < 4; k++)
//			{
//				final float f6 = 0.25F;
//				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f6, this.posY - this.motionY * f6, this.posZ - this.motionZ * f6, this.motionX, this.motionY, this.motionZ);
//			}
//
//			f3 = 0.8F;
//		}
//		this.motionX *= f3;
//		this.motionY *= f3;
//		this.motionZ *= f3;
//		this.motionY -= f5;
//		this.setPosition(this.posX, this.posY, this.posZ);
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short)this.xTile);
		nbttagcompound.setShort("yTile", (short)this.yTile);
		nbttagcompound.setShort("zTile", (short)this.zTile);
		nbttagcompound.setByte("inTile", (byte)this.inTile);
		nbttagcompound.setByte("shake", (byte)this.arrowShake);
		nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.xTile = nbttagcompound.getShort("xTile");
		this.yTile = nbttagcompound.getShort("yTile");
		this.zTile = nbttagcompound.getShort("zTile");
		this.inTile = nbttagcompound.getByte("inTile") & 0xff;
		this.arrowShake = nbttagcompound.getByte("shake") & 0xff;
		this.inGround = nbttagcompound.getByte("inGround") == 1;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if(this.worldObj.isRemote)
		{
			return;
		}
		if(this.inGround && this.field_682_g == entityplayer && this.arrowShake <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(ModItems.itemWeb, 1)))
		{
			entityplayer.onItemPickup(this, 1);
			this.setDead();
		}
	}

	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}

	public void setNoMake()
	{
		this.nomake = true;
	}

	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private int destr;
	private boolean inGround;
	public int arrowShake;
	public EntityLivingBase field_682_g;
	private int field_681_h;
	private int field_680_i;
	private int damage;
	private boolean inactive;
	private boolean nomake;
	private boolean poison;
}
