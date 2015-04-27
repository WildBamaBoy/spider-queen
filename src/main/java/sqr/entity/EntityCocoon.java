package sqr.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sqr.core.minecraft.ModBlocks;
import sqr.core.minecraft.ModItems;
import sqr.enums.EnumTypeVariant;

public class EntityCocoon extends Entity
{
	private EnumTypeVariant type;
	
	public EntityCocoon(World world)
	{
		super(world);
		this.boatCurrentDamage = 0;
		this.boatTimeSinceHit = 0;
		this.boatRockDirection = 1;
		this.mySize();
		this.yOffset = 0.8F;
		this.eggHatch = 0;
		this.myType = 0;
		this.eaten = false;
		this.preventEntitySpawning = true;
	}
	
	public EnumTypeVariant getType()
	{
		return this.type;
	}
	
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	
	public boolean getEaten()
	{
		return this.eaten;
	}
	
	@Override
	protected void entityInit()
	{
		
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity entity)
	{
		return entity.boundingBox;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}
	
	@Override
	public boolean canBePushed()
	{
		return true;
	}
	
	public void mySize()
	{
		this.setSize(1F, 1F);
	}
	
	public EntityCocoon(World world, double d, double d1, double d2, float rr, int Typ)
	{
		this(world);
		this.setPosition(d, d1 + this.yOffset, d2);
		this.setRotation(rr, 0);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = d;
		this.prevPosY = d1;
		this.prevPosZ = d2;
		this.myType = Typ;
		this.eaten = false;
	}
	
	@Override
	public double getMountedYOffset()
	{
		return this.height * 0.0D - 0.30000001192092896D;
	}
	
	public void doSound()
	{
		this.worldObj.playSoundAtEntity(this, "mob.chickenhurt", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float damage)
	{
		final Entity entity = damagesource.getEntity();
		
		if (this.worldObj.isRemote || this.isDead)
		{
			return true;
		}
		
		this.doSound();
		this.boatRockDirection = -this.boatRockDirection;
		this.boatTimeSinceHit = 10;
		this.boatCurrentDamage += damage * 10;
		this.setBeenAttacked();
		
		if (this.boatCurrentDamage > 80)
		{
			if (this.eaten)
			{
				this.worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
				this.setDead();
				return true;
			}
			
			this.dropItem(this.type.getCocoon(), 1);
			
			this.setDead();
		}
		return true;
	}
	
	@Override
	public void performHurtAnimation()
	{
		this.boatRockDirection = -this.boatRockDirection;
		this.boatTimeSinceHit = 10;
		this.boatCurrentDamage += this.boatCurrentDamage * 10;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}
	
	@Override
	public void setPositionAndRotation2(double x, double y, double z, float f, float f1, int i)
	{
		this.field_9393_e = x;
		this.field_9392_f = y;
		this.field_9391_g = z;
		this.field_9390_h = f;
		this.field_9389_i = f1;
		this.field_9394_d = (int) x + 4;
		this.motionX = this.field_9388_j;
		this.motionY = this.field_9387_k;
		this.motionZ = this.field_9386_l;
	}
	
	@Override
	public void setVelocity(double d, double d1, double d2)
	{
		this.field_9388_j = this.motionX = d;
		this.field_9387_k = this.motionY = d1;
		this.field_9386_l = this.motionZ = d2;
	}
	
	protected boolean canDespawn()
	{
		return false;
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		if (this.boatTimeSinceHit > 0)
		{
			this.boatTimeSinceHit--;
		}
		if (this.boatCurrentDamage > 0)
		{
			final Random rr = new Random();
			this.rotationPitch += rr.nextFloat();
			this.rotationPitch -= rr.nextFloat();
			this.boatCurrentDamage--;
		}
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.worldObj.getBlock((int) this.prevPosX, (int) this.prevPosY, (int) this.prevPosZ) == ModBlocks.smallWeb)
		{
			this.motionY = 0;
			return;
		}
		
		final int i = 5;
		double d = 0.0D;
		
		for (int j = 0; j < i; j++)
		{
			// TODO check
			final double d4 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (i + 0) / i - 0.125D;
			final double d8 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (i + 1) / i - 0.125D;
			final AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d4, this.boundingBox.minZ, this.boundingBox.maxX, d8, this.boundingBox.maxZ);
			if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water))
			{
				d += 1.0D / i;
			}
		}
		
		if (this.worldObj.isRemote)
		{
			if (this.field_9394_d > 0)
			{
				final double d1 = this.posX + (this.field_9393_e - this.posX) / this.field_9394_d;
				final double d5 = this.posY + (this.field_9392_f - this.posY) / this.field_9394_d;
				final double d9 = this.posZ + (this.field_9391_g - this.posZ) / this.field_9394_d;
				double d12;
				for (d12 = this.field_9390_h - this.rotationYaw; d12 < -180D; d12 += 360D)
				{
				}
				for (; d12 >= 180D; d12 -= 360D)
				{
				}
				this.rotationYaw += d12 / this.field_9394_d;
				this.rotationPitch += (this.field_9389_i - this.rotationPitch) / this.field_9394_d;
				this.field_9394_d--;
				this.setPosition(d1, d5, d9);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			}
			else
			{
				final double d2 = this.posX + this.motionX;
				final double d6 = this.posY + this.motionY;
				final double d10 = this.posZ + this.motionZ;
				this.setPosition(d2, d6, d10);
				if (this.onGround)
				{
					this.motionX *= 0.5D;
					this.motionY *= 0.5D;
					this.motionZ *= 0.5D;
				}
				this.motionX *= 0.99000000953674316D;
				this.motionY *= 0.94999998807907104D;
				this.motionZ *= 0.99000000953674316D;
			}
			return;
		}
		final double d3 = d * 2D - 1.0D;
		this.motionY += 0.039999999105930328D * d3;
		if (this.riddenByEntity != null)
		{
			this.motionX += this.riddenByEntity.motionX * 0.20000000000000001D;
			this.motionZ += this.riddenByEntity.motionZ * 0.20000000000000001D;
		}
		final double d7 = 0.40000000000000002D;
		if (this.motionX < -d7)
		{
			this.motionX = -d7;
		}
		if (this.motionX > d7)
		{
			this.motionX = d7;
		}
		if (this.motionZ < -d7)
		{
			this.motionZ = -d7;
		}
		if (this.motionZ > d7)
		{
			this.motionZ = d7;
		}
		if (this.onGround)
		{
			this.motionX *= 0.5D;
			this.motionY *= 0.5D;
			this.motionZ *= 0.5D;
		}
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		
		this.motionX *= 0.99000000953674316D;
		this.motionY *= 0.94999998807907104D;
		this.motionZ *= 0.99000000953674316D;
		
		this.rotationPitch = 0.0F;
		double d14 = this.rotationYaw;
		final double d16 = this.prevPosX - this.posX;
		final double d17 = this.prevPosZ - this.posZ;
		if (d16 * d16 + d17 * d17 > 0.001D)
		{
			d14 = (float) (Math.atan2(d17, d16) * 180D / 3.1415926535897931D);
		}
		double d19;
		for (d19 = d14 - this.rotationYaw; d19 >= 180D; d19 -= 360D)
		{
		}
		for (; d19 < -180D; d19 += 360D)
		{
		}
		if (d19 > 20D)
		{
			d19 = 20D;
		}
		if (d19 < -20D)
		{
			d19 = -20D;
		}
		this.rotationYaw += d19;
		this.setRotation(this.rotationYaw, this.rotationPitch);
		final List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
		if (list != null && list.size() > 0)
		{
			for (int j1 = 0; j1 < list.size(); j1++)
			{
				final Entity entity = (Entity) list.get(j1);
				if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBoat)
				{
					entity.applyEntityCollision(this);
				}
			}
			
		}
		if (this.riddenByEntity != null && this.riddenByEntity.isDead)
		{
			this.riddenByEntity = null;
		}
	}
	
	@Override
	public void updateRiderPosition()
	{
		/*
		 * if(riddenByEntity == null) { return; } else { double d =
		 * Math.cos(((double)rotationYaw * 3.1415926535897931D) / 180D) *
		 * 0.40000000000000002D; double d1 = Math.sin(((double)rotationYaw *
		 * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
		 * riddenByEntity.setPosition(posX + d, posY + getMountedYOffset() +
		 * riddenByEntity.getYOffset(), posZ + d1); return; }
		 */
		
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setBoolean("eaten", this.getEaten());
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.setEaten(nbttagcompound.getBoolean("eaten"));
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}
	
	public boolean interact(EntityPlayer entityplayer)
	{
		/*
		 * if(riddenByEntity != null && (riddenByEntity instanceof EntityPlayer)
		 * && riddenByEntity != entityplayer) { return true; }
		 * if(!worldObj.isRemote) { entityplayer.mountEntity(this); }
		 */
		if (this.eaten)
		{
			return true;
		}
		this.onEaten();
		entityplayer.heal(3);
		
		entityplayer.getFoodStats().addStats(4, 0.4f);
		this.worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
		this.worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
		this.eaten = true;
		return true;
	}
	
	public void setEaten(boolean ee)
	{
		this.eaten = ee;
	}
	
	public void onEaten()
	{
		this.entityDropItem(new ItemStack(ModItems.itemWeb, 5, 0), 0);
	}
	
	public int boatCurrentDamage;
	public int boatTimeSinceHit;
	public int boatRockDirection;
	public int eggHatch;
	private int field_9394_d;
	private double field_9393_e;
	private double field_9392_f;
	private double field_9391_g;
	private double field_9390_h;
	private double field_9389_i;
	private double field_9388_j;
	private double field_9387_k;
	private double field_9386_l;
	private boolean eaten;
	private int myType;
}
