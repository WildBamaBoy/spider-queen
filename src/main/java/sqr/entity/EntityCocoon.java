/*******************************************************************************
 * EntityCocoon.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.entity;

import io.netty.buffer.ByteBuf;

import java.util.Random;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sqr.core.SpiderQueen;
import sqr.enums.EnumCocoonType;

import com.radixshock.radixcore.constant.Time;
import com.radixshock.radixcore.logic.LogicHelper;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityCocoon extends EntityCreature implements IEntityAdditionalSpawnData
{
	private EnumCocoonType	cocoonType;

	private int				currentDamage;
	private int				timeSinceHit;
	private int				timeUntilTryBreakFree	= -1;

	public EntityCocoon(World world)
	{
		super(world);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	public EntityCocoon(World world, EnumCocoonType cocoonType)
	{
		this(world);
		this.cocoonType = cocoonType;
		setHitboxSize();
	}

	public EntityCocoon(World world, EnumCocoonType cocoonType, boolean isGhast)
	{
		this(world, cocoonType);

		if (isGhast)
		{
			timeUntilTryBreakFree = LogicHelper.getNumberInRange(1, 4) * Time.SECOND;
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		
		dataWatcher.addObject(20, 0);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected boolean isMovementCeased()
	{
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entity)
	{
		return entity.boundingBox;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return boundingBox;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		if (timeSinceHit > 0)
		{
			timeSinceHit--;
		}

		if (currentDamage > 0)
		{
			final Random rand = new Random();

			rotationPitch += rand.nextFloat();
			rotationPitch -= rand.nextFloat();
			currentDamage--;
		}

		if (cocoonType == EnumCocoonType.ENDERMAN && !isEaten())
		{
			worldObj.spawnParticle("portal", posX + (rand.nextDouble() - 0.5D) * width, posY + 1 + rand.nextDouble() * 0.25D, posZ + rand.nextDouble() - 0.5D * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
		}

		if (timeUntilTryBreakFree > 0)
		{
			timeUntilTryBreakFree--;
		}

		if (timeUntilTryBreakFree == 0)
		{
			if (!worldObj.isRemote && !isEaten())
			{
				if (LogicHelper.getBooleanWithProbability(75))
				{
					setDead();

					final EntityGhast ghast = new EntityGhast(worldObj);
					ghast.setPosition(posX, posY + 2, posZ);
					worldObj.spawnEntityInWorld(ghast);
					worldObj.playSoundAtEntity(ghast, "mob.ghast.scream", 10.0F, 1.0F);
					worldObj.playSoundAtEntity(ghast, "fireworks.largeBlast_far", 10.0F, 1.0F);
				}

				else
				{
					worldObj.playSoundAtEntity(this, "random.levelup", 10.0F, 1.0F);
					timeUntilTryBreakFree = -1;
				}
			}
			
			else if (isEaten())
			{
				timeUntilTryBreakFree = -1;
			}
		}
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		final Entity entity = damageSource.getEntity();

		if (entity instanceof EntityPlayer)
		{
			timeSinceHit = 10;
			currentDamage += damage * 10;

			setBeenAttacked();

			if (currentDamage > 80)
			{
				if (isEaten())
				{
					worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
				}

				if (!worldObj.isRemote && !isEaten())
				{
					dropItem(cocoonType.getCocoonItem(), 1);
				}

				setDead();
			}
		}

		return true;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		try
		{
			cocoonType = (EnumCocoonType) EnumCocoonType.class.getFields()[nbt.getInteger("cocoonType")].get(EnumCocoonType.class);
			dataWatcher.updateObject(20, nbt.getInteger("isEaten"));
		}

		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}

		timeUntilTryBreakFree = nbt.getInteger("timeUntilTryBreakFree");
		setHitboxSize();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("cocoonType", cocoonType.ordinal());
		nbt.setInteger("timeUntilTryBreakFree", timeUntilTryBreakFree);
		nbt.setInteger("isEaten", dataWatcher.getWatchableObjectInt(20));
		setHitboxSize();
	}

	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		buffer.writeInt(cocoonType.ordinal());
		buffer.writeInt(dataWatcher.getWatchableObjectInt(20));
	}

	@Override
	public void readSpawnData(ByteBuf buffer)
	{
		try
		{
			cocoonType = (EnumCocoonType) EnumCocoonType.class.getFields()[buffer.readInt()].get(EnumCocoonType.class);
			dataWatcher.updateObject(20, buffer.readInt());
		}

		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}

		setHitboxSize();
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (!isEaten())
		{
			entityPlayer.triggerAchievement(SpiderQueen.getInstance().achievementEatSomething);
			entityPlayer.heal(3);
			entityPlayer.getFoodStats().addStats(4, 0.4f);

			worldObj.spawnParticle("largesmoke", posX, posY + 2, posZ, motionX, motionY, motionZ);
			worldObj.spawnParticle("largesmoke", posX, posY + 2, posZ, motionX, motionY, motionZ);

			setEaten(true);

			if (!worldObj.isRemote)
			{
				final int loops = cocoonType == EnumCocoonType.GHAST ? 6 : 1;

				for (int i = 0; i < loops; i++)
				{
					final boolean doDropEgg = LogicHelper.getBooleanWithProbability(25);
					final int dropAmount = LogicHelper.getNumberInRange(1, 2);

					entityDropItem(new ItemStack(Items.string, LogicHelper.getNumberInRange(0, 5), 0), 0);

					if (doDropEgg)
					{
						entityDropItem(new ItemStack(SpiderQueen.getInstance().itemSpiderEgg, dropAmount, 0), 0);
					}
				}

				try
				{
					worldObj.playSoundAtEntity(this, cocoonType.getDeathSound(), getSoundVolume(), getSoundPitch());
				}

				catch (final Throwable e)
				{
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	public void setHitboxSize()
	{
		switch (cocoonType)
		{
		case GHAST:
			setSize(3.8F, 3.8F);
			break;
		default:
			setSize(1.0F, 1.0F);
			break;
		}
	}

	public EnumCocoonType getCocoonType()
	{
		return cocoonType;
	}

	public boolean isEaten()
	{
		return dataWatcher.getWatchableObjectInt(20) == 1;
	}

	public void setEaten(boolean isEaten)
	{
		if (!worldObj.isRemote)
		{
			this.getDataWatcher().updateObject(20, isEaten ? 1 : 0);

			if (isEaten)
			{
				try
				{
					worldObj.playSoundAtEntity(this, cocoonType.getDeathSound(), getSoundVolume(), getSoundPitch());
				}

				catch (final Throwable e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public int getTimeSinceHit()
	{
		return timeSinceHit;
	}

	public int getCurrentDamage()
	{
		return currentDamage;
	}
}
