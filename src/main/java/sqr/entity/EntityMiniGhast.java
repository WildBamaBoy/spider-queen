/*******************************************************************************
 * EntityMiniGhast.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

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

import com.radixshock.radixcore.logic.NBTHelper;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class EntityMiniGhast extends EntityCreature
{
	public String	owner	= "";

	public EntityMiniGhast(World world)
	{
		super(world);
		setSize(1.0F, 3.0F);
		isImmuneToFire = true;
		experienceValue = 5;

		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(3.8D);
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
		int dropAmount = rand.nextInt(2) + rand.nextInt(1 + lootingLevel);
		int loops;

		for (loops = 0; loops < dropAmount; ++loops)
		{
			dropItem(Items.ghast_tear, 1);
		}

		dropAmount = rand.nextInt(3) + rand.nextInt(1 + lootingLevel);

		for (loops = 0; loops < dropAmount; ++loops)
		{
			dropItem(Items.gunpowder, 1);
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
		NBTHelper.autoWriteEntityToNBT(this, nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		NBTHelper.autoReadEntityFromNBT(this, nbt);
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (owner.equals(entityPlayer.getCommandSenderName()))
		{
			entityPlayer.rotationYaw = rotationYaw;
			entityPlayer.rotationPitch = rotationPitch;

			if (!worldObj.isRemote)
			{
				entityPlayer.mountEntity(this);
			}
		}

		return true;
	}

	@Override
	public void moveEntityWithHeading(float moveStrafe, float moveForward)
	{
		if (riddenByEntity != null)
		{
			prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;
			rotationPitch = riddenByEntity.rotationPitch * 0.5F;
			setRotation(rotationYaw, rotationPitch);
			rotationYawHead = renderYawOffset = rotationYaw;

			moveStrafe = ((EntityLivingBase) riddenByEntity).moveStrafing * 0.5F / 5;
			moveForward = ((EntityLivingBase) riddenByEntity).moveForward * 0.5F / 5;

			if (onGround)
			{
				moveStrafe /= 4;
				moveForward /= 4;
			}
			
			if (ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, (EntityLivingBase) riddenByEntity, 41))
			{
				motionY = 0.4F;
			}
			
			if (moveForward <= 0.0F)
			{
				moveForward *= 0.25F;
			}

			stepHeight = 1.0F;
			jumpMovementFactor = getAIMoveSpeed() * 0.1F;

			if (!worldObj.isRemote)
			{
				setAIMoveSpeed((float) getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				super.moveEntityWithHeading(moveStrafe, moveForward);
			}

			prevLimbSwingAmount = limbSwingAmount;
			final double d1 = posX - prevPosX;
			final double d0 = posZ - prevPosZ;
			float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

			if (f4 > 1.0F)
			{
				f4 = 1.0F;
			}

			limbSwingAmount += (f4 - limbSwingAmount) * 0.4F;
			limbSwing += limbSwingAmount;
		}
		
		else
		{
			stepHeight = 0.5F;
			jumpMovementFactor = 0.02F;
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
