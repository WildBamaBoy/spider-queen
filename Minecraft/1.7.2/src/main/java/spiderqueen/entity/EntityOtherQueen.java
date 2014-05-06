/*******************************************************************************
 * EntityOtherQueen.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.entity;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.enums.EnumCocoonType;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.inventory.Inventory;

import com.radixshock.radixcore.crypto.HashGenerator;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.logic.NBTHelper;
import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityOtherQueen extends EntityCreature implements IEntityAdditionalSpawnData
{
	public Inventory inventory = new Inventory(this);
	public String identifier;
	public Entity target;
	public int friendlySkinIndex;
	public boolean isHostile;
	
	public transient boolean hasSyncedInventory = false;

	public EntityOtherQueen(World world)
	{
		super(world);

		this.isHostile = LogicHelper.getBooleanWithProbability(10);
		this.friendlySkinIndex = LogicHelper.getNumberInRange(1, 4);
		this.identifier = HashGenerator.getMD5Hash(String.valueOf(LogicHelper.getNumberInRange(0, 100) * getEntityId()));

		this.setSize(1.4F, 0.9F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.6D));
		this.tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(3, new EntityAIWander(this, 0.4D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}

	public void onUpdate()
	{
		super.onUpdate();

		//Server-side only
		if (!worldObj.isRemote)
		{
			setBesideClimbableBlock(isCollidedHorizontally);
			target = findPlayerToAttack();

			if (target != null)
			{
				attackEntity(target, 3.5F);
			}
		}

		else //Client-side only
		{
			syncInventory();
		}
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
	}

	protected Entity findPlayerToAttack()
	{
		List<Entity> entitiesAroundMe = LogicHelper.getAllEntitiesWithinDistanceOfEntity(this, 15);
		EntityLivingBase closestValidTarget = null;
		double distanceToTarget = 100D;

		for (Entity entity : entitiesAroundMe)
		{
			final double distanceToThisEntity = getDistanceToEntity(entity);

			if ((entity instanceof EntityFakePlayer && this.canEntityBeSeen(entity) && isHostile) ||
				(entity instanceof EntityHatchedSpider && isSpiderValidTarget((EntityHatchedSpider)entity) && isHostile) ||
				(entity instanceof EntityOtherQueen) && isQueenValidTarget((EntityOtherQueen)entity) ||
				(entity instanceof EntityPlayer) && isPlayerValidTarget((EntityPlayer)entity)
					&& distanceToThisEntity < distanceToTarget)
			{
				closestValidTarget = (EntityLivingBase)entity;
				distanceToTarget = distanceToThisEntity;
				alertSpidersOfTarget();
			}
		}

		return closestValidTarget;
	}

	private void alertSpidersOfTarget() 
	{
		List<EntityHatchedSpider> spidersAroundMe = (List<EntityHatchedSpider>)LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(this, EntityHatchedSpider.class, 15);

		for (EntityHatchedSpider spider : spidersAroundMe)
		{
			if (spider.owner.equals(this.identifier))
			{
				spider.target = attackingPlayer;
			}
		}
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "mob.spider.say";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.spider.say";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.spider.death";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
	{
		this.playSound("mob.spider.step", 0.15F, 1.0F);
	}


	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) 
	{
		final Entity attackingEntity = par1DamageSource.getEntity();

		if (attackingEntity != null)
		{
			if (attackingEntity instanceof EntityPlayer)
			{
				return super.attackEntityFrom(par1DamageSource, par2); 
			}

			else if (attackingEntity instanceof EntityHatchedSpider)
			{
				final EntityHatchedSpider spider = (EntityHatchedSpider) attackingEntity;

				if (spider.owner.equals(this.identifier))
				{
					return super.attackEntityFrom(par1DamageSource, par2);
				}
			}

			this.target = attackingEntity;
			alertSpidersOfTarget();
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 */
	protected void attackEntity(Entity entityBeingAttacked, float damageAmount)
	{
		damageAmount = 5.0F;
		getNavigator().setPath(getNavigator().getPathToEntityLiving(entityBeingAttacked), 0.4D);
		alertSpidersOfTarget();

		if (rand.nextInt(10) == 0)
		{
			if (this.onGround)
			{
				double d0 = entityBeingAttacked.posX - this.posX;
				double d1 = entityBeingAttacked.posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / (double)f2 * 0.5D * 0.8D + this.motionX * 0.2D;
				this.motionZ = d1 / (double)f2 * 0.5D * 0.8D + this.motionZ * 0.2D;
				this.motionY = 0.4D;
			}
		}

		else
		{
			if (LogicHelper.getDistanceToEntity(this, entityBeingAttacked) < 2.0D)
			{
				if (entityBeingAttacked instanceof EntityPlayer)
				{
					final EntityPlayer player = (EntityPlayer)entityBeingAttacked;
					player.attackEntityFrom(DamageSource.causeMobDamage(this), damageAmount);
				}

				else
				{
					final EntityLivingBase entityLiving = (EntityLivingBase)entityBeingAttacked;
					entityBeingAttacked.attackEntityFrom(DamageSource.causeMobDamage(this), damageAmount);

					if (entityLiving.getHealth() <= 0.0F)
					{
						target = null;
					}
				}
			}
		}
	}

	protected Item getDropItem()
	{
		return Items.string;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * par2 - Level of Looting used to kill this mob.
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		super.dropFewItems(par1, par2);

		if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0))
		{
			this.dropItem(Items.spider_eye, 1);
		}

		inventory.dropAllItems();
	}

	/**
	 * returns true if this entity is by a ladder, false otherwise
	 */
	public boolean isOnLadder()
	{
		return this.isBesideClimbableBlock();
	}

	/**
	 * Sets the Entity inside a web block.
	 */
	public void setInWeb() {}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
	{
		return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);
		NBTHelper.autoWriteEntityToNBT(this, nbt);
		inventory.writeInventoryToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		super.readEntityFromNBT(nbt);
		NBTHelper.autoReadEntityFromNBT(this, nbt);
		inventory.readInventoryFromNBT(nbt);
	}

	@Override
    protected boolean canDespawn()
    {
        return false;
    }
	
	public boolean isBesideClimbableBlock()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			b0 = (byte)(b0 | 1);
		}
		else
		{
			b0 &= -2;
		}

		this.dataWatcher.updateObject(16, Byte.valueOf(b0));
	}

	public void spawnAdditionalSpiders()
	{
		for (int i = 0; i < LogicHelper.getNumberInRange(1, 16); i++)
		{
			final boolean spawnBoomSpider = false; //LogicHelper.getBooleanWithProbability(20);
			final boolean spawnPackSpider = LogicHelper.getBooleanWithProbability(30);
			final boolean spawnSlingerSpider = LogicHelper.getBooleanWithProbability(20);
			final boolean spawnNovaSpider = LogicHelper.getBooleanWithProbability(25);
			final boolean spawnEnderSpider = LogicHelper.getBooleanWithProbability(10);
			final boolean spawnTankSpider = LogicHelper.getBooleanWithProbability(10);
			final int spiderLevel = LogicHelper.getNumberInRange(1, 3);

			EntityHatchedSpider spiderToSpawn = null;

			if (spawnBoomSpider)
			{
				spiderToSpawn = new EntityHatchedSpider(worldObj, identifier, EnumCocoonType.CREEPER);
			}

			else if (spawnPackSpider)
			{
				spiderToSpawn = new EntityHatchedSpider(worldObj, identifier, EnumCocoonType.VILLAGER);
				//TODO fillSpiderInventory(spiderToSpawn);
			}

			else if (spawnSlingerSpider)
			{
				spiderToSpawn = new EntityHatchedSpider(worldObj, identifier, EnumCocoonType.SKELETON);
			}

			else if (spawnNovaSpider)
			{
				spiderToSpawn = new EntityHatchedSpider(worldObj, identifier, EnumCocoonType.WOLF);
			}

			else if (spawnEnderSpider)
			{
				spiderToSpawn = new EntityHatchedSpider(worldObj, identifier, EnumCocoonType.ENDERMAN);
			}

			else if (spawnTankSpider)
			{
				spiderToSpawn = new EntityHatchedSpider(worldObj, identifier, EnumCocoonType.ZOMBIE);
			}

			else
			{
				if (LogicHelper.getBooleanWithProbability(25))
				{
					spiderToSpawn = new EntityHatchedSpider(worldObj, identifier, EnumCocoonType.EMPTY);
				}

				else
				{
					spiderToSpawn = new EntityHatchedSpider(worldObj, identifier, EnumCocoonType.PIG);
				}
			}

			spiderToSpawn.level = spiderLevel;
			spiderToSpawn.isHostile = this.isHostile;
			spiderToSpawn.setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(spiderToSpawn);
		}
	}

	private void syncInventory() 
	{
		if (!hasSyncedInventory)
		{
			SpiderQueen.packetPipeline.sendPacketToServer(new Packet(EnumPacketType.GetInventory, getEntityId()));
			hasSyncedInventory = true;
		}
	}

	private boolean isSpiderValidTarget(EntityHatchedSpider spider)
	{
		if (spider != null)
		{
			return !spider.owner.equals(this.identifier) && this.canEntityBeSeen(spider) && spider.isHostile && this.isHostile;
		}
		
		else
		{
			return false;
		}
	}

	private boolean isQueenValidTarget(EntityOtherQueen queen)
	{
		if (queen != null)
		{
			return queen.isHostile;
		}
		
		else
		{
			return false;
		}
	}

	private boolean isPlayerValidTarget(EntityPlayer player)
	{
		if (player != null)
		{
			return this.canEntityBeSeen(player) && this.isHostile;
		}
		
		else
		{
			return false;
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeBoolean(isHostile);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) 
	{
		isHostile = additionalData.readBoolean();
	}
}