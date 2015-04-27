package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class EntityMiniGhast extends EntityCreature
{
	public String owner = "";
	
	public EntityMiniGhast(World world)
	{
		super(world);
		this.setSize(1.0F, 3.0F);
		this.isImmuneToFire = true;
		this.experienceValue = 5;
		
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}
	
	public EntityMiniGhast(World world, String owner)
	{
		this(world);
		this.owner = owner;
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(3.8D);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		final Entity entity = damageSource.getEntity();
		
		if (entity instanceof EntityWeb)
		{
			return false;
		}
		
		else
		{
			return super.attackEntityFrom(damageSource, damage);
		}
	}
	
	@Override
	protected boolean canDespawn()
	{
		return false;
	}
	
	@Override
	protected void updateEntityActionState()
	{
		return;
	}
	
	@Override
	public void setInWeb()
	{
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
	protected String getLivingSound()
	{
		return "mob.ghast.moan";
	}
	
	@Override
	protected String getHurtSound()
	{
		return "mob.ghast.scream";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "mob.ghast.death";
	}
	
	@Override
	protected Item getDropItem()
	{
		return Items.gunpowder;
	}
	
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}
	
	@Override
	protected void dropFewItems(boolean recentlyHitByPlayer, int lootingLevel)
	{
		int dropAmount = this.rand.nextInt(2) + this.rand.nextInt(1 + lootingLevel);
		int loops;
		
		for (loops = 0; loops < dropAmount; ++loops)
		{
			this.dropItem(Items.ghast_tear, 1);
		}
		
		dropAmount = this.rand.nextInt(3) + this.rand.nextInt(1 + lootingLevel);
		
		for (loops = 0; loops < dropAmount; ++loops)
		{
			this.dropItem(Items.gunpowder, 1);
		}
	}
	
	@Override
	protected float getSoundVolume()
	{
		return 3.0F;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
	}
	
	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (this.owner.equals(entityPlayer.getCommandSenderName()))
		{
			entityPlayer.rotationYaw = this.rotationYaw;
			entityPlayer.rotationPitch = this.rotationPitch;
			
			if (!this.worldObj.isRemote)
			{
				entityPlayer.mountEntity(this);
			}
		}
		
		return true;
	}
	
	@Override
	public void moveEntityWithHeading(float moveStrafe, float moveForward)
	{
		if (this.riddenByEntity != null)
		{
			this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
			this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
			
			moveStrafe = ((EntityLivingBase) this.riddenByEntity).moveStrafing * 0.5F / 5;
			moveForward = ((EntityLivingBase) this.riddenByEntity).moveForward * 0.5F / 5;
			
			if (this.onGround)
			{
				moveStrafe /= 4;
				moveForward /= 4;
			}
			
			if (ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, (EntityLivingBase) this.riddenByEntity, 41))
			{
				this.motionY = 0.4F;
			}
			
			if (moveForward <= 0.0F)
			{
				moveForward *= 0.25F;
			}
			
			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
			
			if (!this.worldObj.isRemote)
			{
				this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				super.moveEntityWithHeading(moveStrafe, moveForward);
			}
			
			this.prevLimbSwingAmount = this.limbSwingAmount;
			final double d1 = this.posX - this.prevPosX;
			final double d0 = this.posZ - this.prevPosZ;
			float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;
			
			if (f4 > 1.0F)
			{
				f4 = 1.0F;
			}
			
			this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}
		
		else
		{
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(moveStrafe, moveForward);
		}
	}
	
	@Override
	protected void fall(float fallAmount)
	{
		return;
	}
	
	@Override
	protected void updateFallState(double distanceFallen, boolean onGround)
	{
		return;
	}
	
	@Override
	public boolean isOnLadder()
	{
		return false;
	}
}
