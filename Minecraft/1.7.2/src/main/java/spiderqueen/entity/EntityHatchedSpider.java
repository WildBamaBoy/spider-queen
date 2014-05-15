/*******************************************************************************
 * EntityHatchedSpider.java
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.core.Constants;
import spiderqueen.core.SpiderQueen;
import spiderqueen.enums.EnumCocoonType;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.inventory.Inventory;

import com.radixshock.radixcore.constant.Time;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.logic.NBTHelper;
import com.radixshock.radixcore.logic.Point3D;
import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityHatchedSpider extends EntityCreature implements IEntityAdditionalSpawnData
{
	public String				owner					= "";
	public EnumCocoonType		cocoonType				= EnumCocoonType.EMPTY;
	public int					level					= 1;
	public int					killsUntilLevelUp		= LogicHelper.getNumberInRange(5, 15);
	public int					timeUntilLevelUp		= Time.MINUTE * LogicHelper.getNumberInRange(1, 5);
	public int					timeUntilWebshot		= 0;
	public int					timeUntilSpawnMinions		= 0;
	public int					timeUntilExplosion		= 0;
	public int					timeUntilDespawn		= -1;
	public int					timeUntilMakeFlameWeb	= Time.MINUTE * LogicHelper.getNumberInRange(1, 3);
	public int					minionSpawnProgress		= 0;
	public int					flameWebSpawnProgress	= 0;
	public int					flameWebProduced		= 0;
	public boolean				isHostile				= false;
	public Inventory			inventory				= new Inventory(this);

	public transient boolean	hasSyncedInventory		= false;
	public Entity				target;

	public EntityHatchedSpider(World world)
	{
		super(world);

		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.6D));
		tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.4D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

		updateEntityAttributes();
		dataWatcher.addObject(17, flameWebProduced);
	}

	public EntityHatchedSpider(World world, String owner, EnumCocoonType cocoonType)
	{
		this(world);
		this.owner = owner;
		this.cocoonType = cocoonType;

		setHitboxSize();
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
			setBesideClimbableBlock(isCollidedHorizontally);

			if (getHealth() > 0)
			{
				updateEntityAttributes();
			}

			if (!tryFollowOwnerPlayer(false))
			{
				if (target != null && !target.isDead)
				{
					attackEntity(target, 3.5F);
				}

				else
				{
					target = findPlayerToAttack();
				}

				tryMoveToSpiderRod();
			}

			if (target != null)
			{
				if (cocoonType == EnumCocoonType.ENDERMAN && timeUntilSpawnMinions <= 0)
				{
					resetTimeUntilSpawnMinions();
					minionSpawnProgress = 10 * LogicHelper.getNumberInRange(1, 5);
				}
			}

			if (cocoonType == EnumCocoonType.CREEPER)
			{
				if (timeUntilExplosion > 0)
				{
					timeUntilExplosion--;
				}
			}

			if (cocoonType == EnumCocoonType.SKELETON)
			{
				if (timeUntilWebshot > 0)
				{
					timeUntilWebshot--;
				}
			}

			if (cocoonType == EnumCocoonType.ENDERMAN)
			{
				if (timeUntilSpawnMinions > 0)
				{
					timeUntilSpawnMinions--;
				}

				if (minionSpawnProgress > 0)
				{
					spawnEnderMinions();
					minionSpawnProgress--;
				}
			}

			if (cocoonType == EnumCocoonType.BLAZE)
			{
				extinguish();

				if (timeUntilMakeFlameWeb > 0 && flameWebSpawnProgress <= 0)
				{
					timeUntilMakeFlameWeb--;
				}

				else if (timeUntilMakeFlameWeb <= 0 && flameWebSpawnProgress <= 0)
				{
					flameWebSpawnProgress = 20;
				}

				if (flameWebSpawnProgress > 0)
				{
					spawnFlameWeb();
					flameWebSpawnProgress--;
				}
			}

			if (cocoonType == EnumCocoonType._ENDERMINION)
			{
				if (timeUntilDespawn > 0)
				{
					timeUntilDespawn--;
				}

				else if (timeUntilDespawn == 0)
				{
					worldObj.playSoundAtEntity(this, "mob.endermen.portal", 0.75F, 1.0F);
					setDead();
				}
			}

			if (cocoonType == EnumCocoonType.HORSE)
			{
				if (riddenByEntity != null && (motionX >= 0.1F || motionX <= -0.1F || motionZ >= 0.1F || motionZ <= -0.1F))
				{
					timeUntilLevelUp--;
				}

				if (timeUntilLevelUp <= 0)
				{
					tryLevelUp();
				}
			}
		}

		// Client-side only
		else
		{
			syncInventory();
			displayParticles();
		}
	}

	@Override
	protected void updateEntityActionState()
	{
		return;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
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

				if (spider.owner.equals(owner)) { return super.attackEntityFrom(damageSource, damageAmount); }
			}

			final List<EntityHatchedSpider> nearbySpiders = (List<EntityHatchedSpider>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(this, EntityHatchedSpider.class, 15);
			target = attackingEntity;

			for (final EntityHatchedSpider spider : nearbySpiders)
			{
				if (spider.owner.equals(owner))
				{
					spider.target = attackingEntity;
				}
			}
		}

		return super.attackEntityFrom(damageSource, damageAmount);
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden
	 * by each mob to define their attack.
	 */
	@Override
	protected void attackEntity(Entity entityBeingAttacked, float damageAmount)
	{
		damageAmount = getAttackDamage();
		setAttackPath(entityBeingAttacked);

		if (rand.nextInt(10) == 0 && cocoonType != EnumCocoonType.SKELETON && cocoonType != EnumCocoonType.ENDERMAN)
		{
			if (onGround)
			{
				final double d0 = entityBeingAttacked.posX - posX;
				final double d1 = entityBeingAttacked.posZ - posZ;
				final float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.8D + motionX * 0.2D;
				motionZ = d1 / f2 * 0.5D * 0.8D + motionZ * 0.2D;
				motionY = 0.4D;
			}
		}

		else
		{
			if (cocoonType == EnumCocoonType.SKELETON && timeUntilWebshot <= 0)
			{
				resetTimeUntilWebshot();
				worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
				worldObj.spawnEntityInWorld(new EntityWeb(this, (EntityLivingBase) entityBeingAttacked, 2.6F));
			}

			if (LogicHelper.getDistanceToEntity(this, entityBeingAttacked) < 2.0D)
			{
				final EntityLivingBase entityLiving = (EntityLivingBase) entityBeingAttacked;
				entityBeingAttacked.attackEntityFrom(DamageSource.causeMobDamage(this), damageAmount);

				if (cocoonType == EnumCocoonType.CREEPER && timeUntilExplosion <= 0)
				{
					resetTimeUntilExplosion();
					SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.CreateClientExplosion, posX, posY, posZ, 5.0F, false));

					for (final EntityLivingBase entity : (List<EntityLivingBase>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(this, EntityLivingBase.class, 5))
					{
						if (entity instanceof EntityPlayer)
						{
							final EntityPlayer player = (EntityPlayer) entity;

							if (owner.equals(player.getCommandSenderName()))
							{
								continue;
							}
						}

						else if (entity instanceof EntityHatchedSpider)
						{
							final EntityHatchedSpider spider = (EntityHatchedSpider) entity;

							if (spider.owner.equals(owner))
							{
								continue;
							}
						}

						else
						{
							entity.attackEntityFrom(DamageSource.generic, 5.0F);
						}
					}
				}

				if (entityLiving.getHealth() <= 0.0F)
				{
					killsUntilLevelUp--;
					tryLevelUp();
				}
			}
		}
	}

	public void tryLevelUp(boolean doForce)
	{
		if (doForce || (cocoonType != EnumCocoonType._ENDERMINION && level < 3 && (killsUntilLevelUp <= 0 || cocoonType == EnumCocoonType.HORSE && timeUntilLevelUp < 0 || SpiderQueen.getInstance().inDebugMode)))
		{
			timeUntilExplosion = 0;
			timeUntilWebshot = 0;
			timeUntilSpawnMinions = 0;
			timeUntilLevelUp = Time.MINUTE * LogicHelper.getNumberInRange(1, 5);
			resetTimeUntilMakeFlameWeb();

			worldObj.playSoundAtEntity(this, "random.levelup", 0.75F, 1.0F);
			killsUntilLevelUp = LogicHelper.getNumberInRange(5, 15);
			level++;
			setHitboxSize();

			SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetLevel, getEntityId(), level));
			SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetInventory, getEntityId(), inventory));
		}

		target = null;
	}

	public void tryLevelUp()
	{
		tryLevelUp(false);
	}
	
	private void setAttackPath(Entity entityBeingAttacked)
	{
		if (cocoonType == EnumCocoonType.SKELETON || cocoonType == EnumCocoonType.ENDERMAN)
		{
			if (LogicHelper.getDistanceToEntity(this, entityBeingAttacked) > 12.0F)
			{
				final Point3D point = LogicHelper.getRandomNearbyBlockCoordinatesOfType(entityBeingAttacked, Blocks.air, 8);
				getNavigator().setPath(getNavigator().getPathToXYZ(point.dPosX, point.dPosY, point.dPosZ), 0.4D);
				faceEntity(entityBeingAttacked, 1.0F, 1.0F);
			}
		}

		else
		{
			getNavigator().setPath(getNavigator().getPathToEntityLiving(entityBeingAttacked), 0.4D);
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

		inventory.dropAllItems();
	}

	@Override
	public boolean isOnLadder()
	{
		final boolean returnBool = isBesideClimbableBlock();

		if (returnBool)
		{
			limbSwingAmount += 0.1;
			motionY = level * 0.115F;
		}

		return returnBool;
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
		if (owner.equals(entityPlayer.getCommandSenderName()))
		{
			if (cocoonType == EnumCocoonType.VILLAGER)
			{
				entityPlayer.openGui(SpiderQueen.getInstance(), Constants.ID_GUI_INVENTORY, worldObj, (int) posX, (int) posY, (int) posZ);
			}

			else if (cocoonType == EnumCocoonType.HORSE)
			{
				entityPlayer.rotationYaw = rotationYaw;
				entityPlayer.rotationPitch = rotationPitch;

				if (!worldObj.isRemote)
				{
					entityPlayer.mountEntity(this);
				}
			}

			else if (cocoonType == EnumCocoonType.BLAZE)
			{
				if (flameWebProduced > 0)
				{
					worldObj.playSoundAtEntity(this, "random.fizz", 0.75F, 1.5F);
					dropItem(SpiderQueen.getInstance().itemFlameWeb, flameWebProduced);

					flameWebProduced = 0;
					dataWatcher.updateObject(17, flameWebProduced);
				}
			}
		}

		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		NBTHelper.autoWriteEntityToNBT(this, nbt);
		nbt.setInteger("cocoonType", cocoonType.ordinal());

		inventory.writeInventoryToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		NBTHelper.autoReadEntityFromNBT(this, nbt);

		try
		{
			cocoonType = (EnumCocoonType) EnumCocoonType.class.getFields()[nbt.getInteger("cocoonType")].get(EnumCocoonType.class);
		}

		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}

		inventory.readInventoryFromNBT(nbt);
		setHitboxSize();
	}

	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		buffer.writeInt(cocoonType.ordinal());
		buffer.writeInt(level);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		try
		{
			cocoonType = (EnumCocoonType) EnumCocoonType.class.getFields()[additionalData.readInt()].get(EnumCocoonType.class);
		}

		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}

		level = additionalData.readInt();
		setHitboxSize();
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
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

			if (level == 3)
			{
				moveStrafe = ((EntityLivingBase) riddenByEntity).moveStrafing * level * 0.4F / 4;
				moveForward = ((EntityLivingBase) riddenByEntity).moveForward * level * 0.5F / 4;				
			}
			
			else
			{
				moveStrafe = ((EntityLivingBase) riddenByEntity).moveStrafing * level * 0.4F / 3;
				moveForward = ((EntityLivingBase) riddenByEntity).moveForward * level * 0.5F / 3;				
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

	public void setHitboxSize()
	{
		switch (cocoonType.getSpiderSize())
		{
			case HUGE:
				setSize(1.6F + 0.20F * level, 0.75F + 0.25F * level);
				break;
			case NORMAL:
				setSize(1.6F, 0.75F);
				break;
			case RAISED:
				setSize(1.6F, 0.95F);
				break;
			case THIN:
				setSize(0.8F, 0.5F);
				break;
			case TINY:
				setSize(0.8F, 0.375F);
				break;
			case LONGLEG:
				setSize(1.2F, 1.0F);
				break;
			case TINYLONGLEG:
				setSize(0.3F, 0.15F);
				break;
			case SWARM:
				setSize(0.7F, 0.6F);
			default:
				break;
		}
	}

	private void spawnEnderMinions()
	{
		if (minionSpawnProgress % 10 == 0)
		{
			if (target != null)
			{
				final EntityHatchedSpider minion = new EntityHatchedSpider(worldObj, owner, EnumCocoonType._ENDERMINION);
				final Point3D spawnPoint = LogicHelper.getRandomNearbyBlockCoordinatesOfType(worldObj, new Point3D(target.posX, target.posY, target.posZ), Blocks.air, 3);

				if (spawnPoint != null)
				{
					minion.setPosition(spawnPoint.dPosX, spawnPoint.dPosY, spawnPoint.dPosZ);
					minion.target = target;

					if (!worldObj.isRemote)
					{
						minion.timeUntilDespawn = Time.SECOND * LogicHelper.getNumberInRange(15, 45);
						minion.level = this.level;
						worldObj.spawnEntityInWorld(minion);
						worldObj.playSoundAtEntity(this, "mob.endermen.portal", 0.75F, 1.0F);
					}
				}
			}
		}
		
		if (minionSpawnProgress == 1 && LogicHelper.getBooleanWithProbability(25))
		{
			tryLevelUp(true);
		}
	}

	private void spawnFlameWeb()
	{
		if (flameWebSpawnProgress % 5 == 0)
		{
			if (!worldObj.isRemote)
			{
				worldObj.playSoundAtEntity(this, "random.fizz", 0.75F, 1.5F);
			}
		}

		if (flameWebSpawnProgress == 1)
		{
			flameWebSpawnProgress = 0;
			flameWebProduced += LogicHelper.getNumberInRange(1, 4);

			if (flameWebProduced >= 64)
			{
				flameWebProduced = 64;
			}

			dataWatcher.updateObject(17, flameWebProduced);
			resetTimeUntilMakeFlameWeb();
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

	private void updateEntityAttributes()
	{
		if (cocoonType == EnumCocoonType.SKELETON)
		{
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
		}

		else if (cocoonType == EnumCocoonType.WOLF)
		{
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.8D);
		}

		else if (cocoonType == EnumCocoonType.ZOMBIE && getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() != 30.0D + level * 10)
		{
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D + level * 10);
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
			setHealth((float) getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue());
		}
	}

	private float getAttackDamage()
	{
		switch (cocoonType)
		{
			case EMPTY:
				return 0.5F;
			case _ENDERMINION:
				return 0.3F;
			case WOLF:
				return 1.0F;
			default:
				return 2.5F + level / 2;
		}
	}

	private boolean tryFollowOwnerPlayer(boolean checkOnly)
	{
		if (owner != null)
		{
			final EntityPlayer ownerPlayer = worldObj.getPlayerEntityByName(owner);

			if (ownerPlayer != null)
			{
				final double distanceToOwner = LogicHelper.getDistanceToEntity(this, ownerPlayer);
				final ItemStack currentItemStack = ownerPlayer.inventory.mainInventory[ownerPlayer.inventory.currentItem];

				if (currentItemStack != null && distanceToOwner < 30.0D && currentItemStack.getItem() == SpiderQueen.getInstance().itemSpiderRod)
				{
					if (!checkOnly)
					{
						target = null;
						moveToPlayer(ownerPlayer);
					}

					return true;
				}
			}
		}

		return false;
	}

	private void tryMoveToSpiderRod()
	{
		final Point3D nearestRod = LogicHelper.getNearbyBlock_StartAtTop(this, SpiderQueen.getInstance().blockSpiderRod, 10);

		if (nearestRod != null && LogicHelper.getDistanceToXYZ(nearestRod.dPosX, nearestRod.dPosY, nearestRod.dPosZ, posX, posY, posZ) > 5.0D)
		{
			getNavigator().tryMoveToXYZ(nearestRod.dPosX, nearestRod.dPosY, nearestRod.dPosZ, 0.4D);
		}
	}

	private void moveToPlayer(EntityPlayer player)
	{
		if (player != null && player.onGround)
		{
			getLookHelper().setLookPositionWithEntity(player, 10.0F, getVerticalFaceSpeed());

			if (getDistanceToEntity(player) > 3.5D)
			{
				final boolean pathSet = getNavigator().tryMoveToEntityLiving(player, 0.4D);
				getNavigator().onUpdateNavigation();

				if (!pathSet && getDistanceToEntity(player) >= 10.0D)
				{
					final int playerX = MathHelper.floor_double(player.posX) - 2;
					final int playerY = MathHelper.floor_double(player.boundingBox.minY);
					final int playerZ = MathHelper.floor_double(player.posZ) - 2;

					for (int i = 0; i <= 4; ++i)
					{
						for (int i2 = 0; i2 <= 4; ++i2)
						{
							if ((i < 1 || i2 < 1 || i > 3 || i2 > 3) && World.doesBlockHaveSolidTopSurface(worldObj, playerX + i, playerY - 1, playerZ + i2) && !worldObj.getBlock(playerX + i, playerY, playerZ + i2).isNormalCube() && !worldObj.getBlock(playerX + i, playerY + 1, playerZ + i2).isNormalCube())
							{
								setLocationAndAngles(playerX + i + 0.5F, playerY, playerZ + i2 + 0.5F, rotationYaw, rotationPitch);
								getNavigator().clearPathEntity();
								return;
							}
						}
					}
				}
			}
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

	private void displayParticles()
	{
		if (cocoonType == EnumCocoonType.ENDERMAN || cocoonType == EnumCocoonType._ENDERMINION)
		{
			worldObj.spawnParticle("portal", posX + (rand.nextDouble() - 0.5D) * width, posY + 0.9D + rand.nextDouble() * 0.25D, posZ + rand.nextDouble() - 0.5D * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
		}

		if (cocoonType == EnumCocoonType.BLAZE)
		{
			for (int i = 0; i < dataWatcher.getWatchableObjectInt(17); i++)
			{
				if (LogicHelper.getBooleanWithProbability(20))
				{
					final double motionX = worldObj.rand.nextDouble() / 32 - 0.008;
					final double motionY = worldObj.rand.nextDouble() / 32 - 0.008;
					final double motionZ = worldObj.rand.nextDouble() / 32 - 0.008;

					worldObj.spawnParticle("flame", posX + worldObj.rand.nextFloat() - 0.5D, posY + 0.5, posZ + worldObj.rand.nextFloat() - 0.5D, motionX, motionY, motionZ);
				}
			}
		}
	}

	private void resetTimeUntilMakeFlameWeb()
	{
		switch (level)
		{
			case 1:
				timeUntilMakeFlameWeb = Time.MINUTE * 2;
				break;
			case 2:
				timeUntilMakeFlameWeb = Time.MINUTE;
				break;
			case 3:
				timeUntilMakeFlameWeb = Time.SECOND * 30;
				break;
		}
	}

	private void resetTimeUntilSpawnMinions()
	{
		switch (level)
		{
			case 1:
				timeUntilSpawnMinions = Time.MINUTE;
				break;
			case 2:
				timeUntilSpawnMinions = Time.SECOND * 30;
				break;
			case 3:
				timeUntilSpawnMinions = Time.SECOND * 10;
				break;
		}
	}

	private void resetTimeUntilExplosion()
	{
		switch (level)
		{
			case 1:
				timeUntilExplosion = Time.MINUTE;
				break;
			case 2:
				timeUntilExplosion = Time.SECOND * 30;
				break;
			case 3:
				timeUntilExplosion = Time.SECOND * 10;
				break;
		}
	}

	private void resetTimeUntilWebshot()
	{
		switch (level)
		{
			case 1:
				timeUntilWebshot = Time.SECOND * 5;
				break;
			case 2:
				timeUntilWebshot = Time.SECOND * 3;
				break;
			case 3:
				timeUntilWebshot = Time.SECOND * 1;
				break;
		}
	}

	private boolean isSpiderValidTarget(EntityHatchedSpider spider)
	{
		if (owner != null && spider.owner != null)
		{
			return !spider.owner.equals(owner) && canEntityBeSeen(spider) && (spider.isHostile || isHostile);
		}

		else
		{
			return false;
		}
	}

	private boolean isQueenValidTarget(EntityOtherQueen queen)
	{
		if (owner != null)
		{
			return !owner.equals(queen.identifier) && canEntityBeSeen(queen) && queen.isHostile;
		}

		else
		{
			return false;
		}
	}

	private boolean isPlayerValidTarget(EntityPlayer player)
	{
		if (owner != null)
		{
			return !owner.equals(player.getCommandSenderName()) && canEntityBeSeen(player) && isHostile;
		}

		else
		{
			return false;
		}
	}
}
