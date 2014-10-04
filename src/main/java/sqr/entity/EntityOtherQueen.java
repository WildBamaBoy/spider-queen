/*******************************************************************************
 * EntityOtherQueen.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.entity;

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
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import sqr.core.SpiderQueen;
import sqr.enums.EnumCocoonType;
import sqr.inventory.Inventory;
import sqr.network.packets.PacketGetInventory;

import com.radixshock.radixcore.crypto.HashGenerator;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.logic.NBTHelper;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityOtherQueen extends EntityCreature implements IEntityAdditionalSpawnData
{
	public Inventory			inventory			= new Inventory(this);
	public String				identifier;
	public Entity				target;
	public int					friendlySkinIndex;
	public boolean				isHostile;

	public transient boolean	hasSyncedInventory	= false;

	public EntityOtherQueen(World world)
	{
		super(world);

		isHostile = LogicHelper.getBooleanWithProbability(10);
		friendlySkinIndex = LogicHelper.getNumberInRange(1, 4);
		identifier = HashGenerator.getMD5Hash(String.valueOf(LogicHelper.getNumberInRange(0, 100) * getEntityId()));

		setSize(1.4F, 1.8F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.6D));
		tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.4D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}

	public EntityOtherQueen(World world, boolean isHostile)
	{
		super(world);

		this.isHostile = isHostile;
		friendlySkinIndex = LogicHelper.getNumberInRange(1, 4);
		identifier = HashGenerator.getMD5Hash(String.valueOf(LogicHelper.getNumberInRange(0, 100) * getEntityId()));

		setSize(1.4F, 1.8F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.6D));
		tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.4D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		// Server-side only
		if (!worldObj.isRemote)
		{
			if (worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
			{
				setDead();
				return;
			}

			setBesideClimbableBlock(isCollidedHorizontally);
			target = findPlayerToAttack();

			if (target != null)
			{
				attackEntity(target, 3.5F);

				if (target instanceof EntityPlayer && ((EntityPlayer) target).getHealth() <= 0.0F)
				{
					setDead();
					despawnAllOwnedSpiders();
				}
			}
		}

		else
			// Client-side only
		{
			syncInventory();
		}
	}

	@Override
	public void onDeath(DamageSource damageSource)
	{
		super.onDeath(damageSource);

		if (damageSource.getEntity() instanceof EntityPlayer)
		{
			final EntityPlayer player = (EntityPlayer) damageSource.getEntity();
			player.triggerAchievement(SpiderQueen.getInstance().achievementDefeatEvilQueen);
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
	}

	@Override
	protected Entity findPlayerToAttack()
	{
		final List<Entity> entitiesAroundMe = LogicHelper.getAllEntitiesWithinDistanceOfEntity(this, 15);
		EntityLivingBase closestValidTarget = null;
		double distanceToTarget = 100D;

		for (final Entity entity : entitiesAroundMe)
		{
			final double distanceToThisEntity = getDistanceToEntity(entity);

			if (entity instanceof EntityFakePlayer && canEntityBeSeen(entity) && isHostile || entity instanceof EntityHatchedSpider && isSpiderValidTarget((EntityHatchedSpider) entity) && isHostile || entity instanceof EntityOtherQueen && isQueenValidTarget((EntityOtherQueen) entity) || entity instanceof EntityPlayer && isPlayerValidTarget((EntityPlayer) entity) && distanceToThisEntity < distanceToTarget)
			{
				closestValidTarget = (EntityLivingBase) entity;
				distanceToTarget = distanceToThisEntity;
				alertSpidersOfTarget();
			}
		}

		return closestValidTarget;
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.spider.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.spider.say";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.spider.death";
	}

	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
	{
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
	{
		final Entity attackingEntity = damageSource.getEntity();

		if (attackingEntity != null)
		{
			if (attackingEntity instanceof EntityPlayer)
			{
				return super.attackEntityFrom(damageSource, damageAmount);
			}

			else if (attackingEntity instanceof EntityHatchedSpider)
			{
				final EntityHatchedSpider spider = (EntityHatchedSpider) attackingEntity;

				if (spider.owner.equals(identifier)) { return super.attackEntityFrom(damageSource, damageAmount); }
			}

			target = attackingEntity;
			alertSpidersOfTarget();
		}

		return super.attackEntityFrom(damageSource, damageAmount);
	}

	@Override
	protected void attackEntity(Entity entityBeingAttacked, float damageAmount)
	{
		if (getHealth() > 0.0F)
		{
			damageAmount = 5.0F;
			getNavigator().setPath(getNavigator().getPathToEntityLiving(entityBeingAttacked), 0.4D);
			alertSpidersOfTarget();

			if (rand.nextInt(10) == 0)
			{
				if (onGround)
				{
					final double deltaX = entityBeingAttacked.posX - posX;
					final double deltaZ = entityBeingAttacked.posZ - posZ;
					final float distanceBetweenPoints = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);
					motionX = deltaX / distanceBetweenPoints * 0.5D * 0.8D + motionX * 0.2D;
					motionZ = deltaZ / distanceBetweenPoints * 0.5D * 0.8D + motionZ * 0.2D;
					motionY = 0.4D;
				}
			}

			else
			{
				if (LogicHelper.getDistanceToEntity(this, entityBeingAttacked) < 2.0D)
				{
					if (entityBeingAttacked instanceof EntityPlayer)
					{
						final EntityPlayer player = (EntityPlayer) entityBeingAttacked;
						player.attackEntityFrom(DamageSource.generic, damageAmount);
					}

					else
					{
						final EntityLivingBase entityLiving = (EntityLivingBase) entityBeingAttacked;
						entityBeingAttacked.attackEntityFrom(DamageSource.generic, damageAmount);

						if (entityLiving.getHealth() <= 0.0F)
						{
							target = null;
						}
					}
				}
			}
		}
	}

	@Override
	protected Item getDropItem()
	{
		return Items.string;
	}

	@Override
	protected void dropFewItems(boolean hitByPlayerRecently, int lootingLevel)
	{
		super.dropFewItems(hitByPlayerRecently, lootingLevel);

		if (hitByPlayerRecently && (rand.nextInt(3) == 0 || rand.nextInt(1 + lootingLevel) > 0))
		{
			dropItem(Items.spider_eye, 1);
		}

		inventory.dropAllItems(false);
	}

	@Override
	public boolean isOnLadder()
	{
		return isBesideClimbableBlock();
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
	public boolean isPotionApplicable(PotionEffect potionEffect)
	{
		return potionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(potionEffect);
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

	public void spawnAdditionalSpiders()
	{
		for (int i = 0; i < LogicHelper.getNumberInRange(1, 16); i++)
		{
			final boolean spawnBoomSpider = false; // LogicHelper.getBooleanWithProbability(20);
			final boolean spawnPackSpider = LogicHelper.getBooleanWithProbability(15);
			final boolean spawnSlingerSpider = LogicHelper.getBooleanWithProbability(20);
			final boolean spawnNovaSpider = LogicHelper.getBooleanWithProbability(25);
			final boolean spawnEnderSpider = false; //LogicHelper.getBooleanWithProbability(10);
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
				// TODO fillSpiderInventory(spiderToSpawn);
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

			spiderToSpawn.setLevel(spiderLevel);
			spiderToSpawn.isHostile = isHostile;
			spiderToSpawn.setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(spiderToSpawn);
		}
	}

	private boolean isBesideClimbableBlock()
	{
		return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	private void setBesideClimbableBlock(boolean setBoolean)
	{
		byte value = dataWatcher.getWatchableObjectByte(16);

		if (setBoolean == true)
		{
			value = (byte) (value | 1);
		}
		else
		{
			value &= -2;
		}

		dataWatcher.updateObject(16, Byte.valueOf(value));
	}

	private void alertSpidersOfTarget()
	{
		final List<EntityHatchedSpider> spidersAroundMe = (List<EntityHatchedSpider>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(this, EntityHatchedSpider.class, 15);

		for (final EntityHatchedSpider spider : spidersAroundMe)
		{
			if (spider.owner.equals(identifier))
			{
				spider.target = attackingPlayer;
			}
		}
	}

	private void despawnAllOwnedSpiders()
	{
		final List<EntityHatchedSpider> spidersAroundMe = (List<EntityHatchedSpider>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(this, EntityHatchedSpider.class, 15);

		for (final EntityHatchedSpider spider : spidersAroundMe)
		{
			if (spider.owner.equals(identifier))
			{
				spider.setDead();
			}
		}
	}

	private void syncInventory()
	{
		if (!hasSyncedInventory)
		{
			SpiderQueen.packetHandler.sendPacketToServer(new PacketGetInventory(getEntityId()));
			hasSyncedInventory = true;
		}
	}

	private boolean isSpiderValidTarget(EntityHatchedSpider spider)
	{
		if (spider != null)
		{
			return !spider.owner.equals(identifier) && canEntityBeSeen(spider) && spider.isHostile && isHostile;
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
			return canEntityBeSeen(player) && isHostile;
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
		buffer.writeInt(friendlySkinIndex);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		isHostile = additionalData.readBoolean();
		friendlySkinIndex = additionalData.readInt();
		setSize(1.4F, 1.8F);
	}
}
