

package sqr.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
//            Entity, EntityPlayer, EntityLivingBase, MathHelper,
//            World, Block, Vec3D, AxisAlignedBB,
//            MovingObjectPosition, NBTTagCompound, ItemStack, Item,
//            InventoryPlayer

public class EntityVines extends Entity
{

	public EntityVines(World world)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.field_28019_h = 0;
		this.inGround = false;
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.setSize(0.2F, 0.2F);
	}

	public EntityVines(World world, double d, double d1, double d2)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.field_28019_h = 0;
		this.inGround = false;
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.setSize(0.2F, 0.2F);
		this.setPosition(d, d1, d2);
		this.yOffset = 0.0F;
	}

	public EntityVines(World world, EntityLivingBase entityliving)
	{
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.field_28019_h = 0;
		this.inGround = false;
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.owner = entityliving;
		this.doesArrowBelongToPlayer = entityliving instanceof EntityPlayer;

		this.setSize(0.2F, 0.2F);
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
	}

	@Override
	protected void entityInit()
	{
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
		this.motionY = 0;//d1;
		this.motionZ = d2;
		final float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(d, d2) * 180D / 3.1415927410125732D);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(d1, f3) * 180D / 3.1415927410125732D);
		this.ticksInGround = 0;
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
			//prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(d1, f) * 180D / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			final float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			// prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180D / 3.1415927410125732D);
		}
		final Block b = this.worldObj.getBlock(this.xTile, this.yTile - 1, this.zTile);

		if(b != Blocks.air)
		{
			final Block b1 = this.worldObj.getBlock(this.xTile, this.yTile + 2 - 1, this.zTile);
			final Block b2 = this.worldObj.getBlock(this.xTile, this.yTile - 1 - 1, this.zTile);
			this.motionY = 0F;
			
			if(b1 == Blocks.air)
			{
				this.motionY = this.motionY * 0.9 + 0.2F;
			}
			if(b2 == Blocks.air)
			{
				this.motionY = this.motionY * 0.9 - 0.2F;
			}

			if(b == Blocks.tallgrass || b == Blocks.yellow_flower || b == Blocks.red_flower)
			{
				this.worldObj.setBlockToAir(this.xTile, this.yTile - 1, this.zTile);
			}
			else
			{
				if(b != Blocks.dirt & b != Blocks.grass)
				{
					this.setDead();
				}
			}
		}
		else
		{
			this.motionY = this.motionY * 0.9 - 0.05F;
		}

		if(this.arrowShake > 0)
		{
			this.arrowShake--;
		}

		this.ticksInAir++;
		if(this.ticksInAir > 200) { this.setDead(); }

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
		double d = 0.0D;
		for(int l = 0; l < list.size(); l++)
		{
			final Entity entity1 = (Entity)list.get(l);
			if(!entity1.canBeCollidedWith() || entity1 == this.owner && this.ticksInAir < 5)
			{
				continue;
			}
			final float f4 = 0.3F;
			final AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f4, f4, f4);
			final MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3d, vec3d1);
			if(movingobjectposition1 == null)
			{
				continue;
			}
			final double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
			if(d1 < d || d == 0.0D)
			{
				entity = entity1;
				d = d1;
			}
		}

		if(entity != null)
		{
			movingobjectposition = new MovingObjectPosition(entity);
		}

		this.xTile = (int)this.posX;
		this.yTile = (int)this.posY;
		this.zTile = (int)this.posZ;

		if(movingobjectposition != null)
		{
			this.xTile = movingobjectposition.blockX;
			this.yTile = movingobjectposition.blockY;
			this.zTile = movingobjectposition.blockZ;

			if(movingobjectposition.entityHit != null)
			{
				boolean change = false;
				if(this.friendly)
				{
					if(movingobjectposition.entityHit instanceof EntityPlayer || movingobjectposition.entityHit instanceof EntitySpiderEx) { change = true; }
					if(movingobjectposition.entityHit instanceof EntityMand)
					{
						final EntityMand m = (EntityMand)movingobjectposition.entityHit;
						if(m.getFriendly() == this.friendly) { change = true; }
					}
				}
				else
				{
					if(movingobjectposition.entityHit instanceof EntityMand)
					{
						final EntityMand m = (EntityMand)movingobjectposition.entityHit;
						if(m.getFriendly() == this.friendly) { change = true; }
					}
				}

				if(!change)
				{
					if(movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.owner), 1))
					{
						//worldObj.playSoundAtEntity(this, "random.drr", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
						this.setDead();
					}
				}
			} else
			{
				/*xTile = movingobjectposition.blockX;
                yTile = movingobjectposition.blockY;
                zTile = movingobjectposition.blockZ;
                inTile = worldObj.getBlock(xTile, yTile, zTile);
                field_28019_h = worldObj.getBlockMetadata(xTile, yTile, zTile);
                motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
                motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
                motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
                float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
                posX -= (motionX / (double)f1) * 0.05000000074505806D;
                posY -= (motionY / (double)f1) * 0.05000000074505806D;
                posZ -= (motionZ / (double)f1) * 0.05000000074505806D;
                worldObj.playSoundAtEntity(this, "random.drr", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
                inGround = true;
                arrowShake = 7;*/
			}
		}
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		final float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		//rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
		for(this.rotationPitch = (float)(Math.atan2(this.motionY, f2) * 180D / 3.1415927410125732D); this.rotationPitch - this.prevRotationPitch < -180F; this.prevRotationPitch -= 360F) { }
		for(; this.rotationPitch - this.prevRotationPitch >= 180F; this.prevRotationPitch += 360F) { }
		for(; this.rotationYaw - this.prevRotationYaw < -180F; this.prevRotationYaw -= 360F) { }
		for(; this.rotationYaw - this.prevRotationYaw >= 180F; this.prevRotationYaw += 360F) { }
		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		//rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		float f3 = 0.99F;
		final float f5 = 0.03F;
		if(this.isInWater())
		{
			for(int i1 = 0; i1 < 4; i1++)
			{
				final float f6 = 0.25F;
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f6, this.posY - this.motionY * f6, this.posZ - this.motionZ * f6, this.motionX, this.motionY, this.motionZ);
			}

			f3 = 0.8F;
		}
		this.motionX *= f3;
		this.motionY *= f3;
		this.motionZ *= f3;
		this.motionY -= f5;
		this.setPosition(this.posX, this.posY, this.posZ);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short)this.xTile);
		nbttagcompound.setShort("yTile", (short)this.yTile);
		nbttagcompound.setShort("zTile", (short)this.zTile);
		nbttagcompound.setByte("inTile", (byte)this.inTile);
		nbttagcompound.setByte("inData", (byte)this.field_28019_h);
		nbttagcompound.setByte("shake", (byte)this.arrowShake);
		nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		nbttagcompound.setBoolean("player", this.doesArrowBelongToPlayer);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.xTile = nbttagcompound.getShort("xTile");
		this.yTile = nbttagcompound.getShort("yTile");
		this.zTile = nbttagcompound.getShort("zTile");
		this.inTile = nbttagcompound.getByte("inTile") & 0xff;
		this.field_28019_h = nbttagcompound.getByte("inData") & 0xff;
		this.arrowShake = nbttagcompound.getByte("shake") & 0xff;
		this.inGround = nbttagcompound.getByte("inGround") == 1;
		this.doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if(this.worldObj.isRemote)
		{
			return;
		}
		if(this.inGround && this.doesArrowBelongToPlayer && this.arrowShake <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1)))
		{
			//worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			//entityplayer.onItemPickup(this, 1);
			this.setDead();
		}
	}

	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}

	public void setFriendly(boolean f) { this.friendly = f; }

	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private int field_28019_h;
	private boolean inGround;
	public boolean doesArrowBelongToPlayer;
	public int arrowShake;
	public EntityLivingBase owner;
	private int ticksInGround;
	private int ticksInAir;
	private boolean friendly;
}
