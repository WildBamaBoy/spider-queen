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
	public String owner = "";
	public EnumCocoonType cocoonType = EnumCocoonType.EMPTY;
	public int level = 1;
	public int killsUntilLevelUp = LogicHelper.getNumberInRange(5, 15);
	public int timeUntilWebshot = 0;
	public int timeUntilTeleport = 0;
	public int timeUntilExplosion = 0;
	public boolean isHostile = false;
	public Inventory inventory = new Inventory(this);

	public transient boolean hasSyncedInventory = false;
	public Entity target;

	public EntityHatchedSpider(World world)
	{
		super(world);

		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.6D));
		tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.4D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

		updateEntityAttributes();
	}

	public EntityHatchedSpider(World world, String owner, EnumCocoonType cocoonType)
	{
		this(world);
		this.owner = owner;
		setSize(1.4F, 0.9F);
		this.cocoonType = cocoonType;
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
		dataWatcher.addObject(16, new Byte((byte)0));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		//Server-side only
		if (!worldObj.isRemote)
		{
			setBesideClimbableBlock(isCollidedHorizontally);

			if (getHealth() > 0)
			{
				updateEntityAttributes();
			}

			if (!tryFollowOwnerPlayer(false))
			{
				if (target != null)
				{
					attackEntity(target, 3.5F);
				}

				else
				{
					target = findPlayerToAttack();
				}

				tryMoveToSpiderRod();
			}

			if (timeUntilTeleport > 0)
			{
				timeUntilTeleport--;
			}

			if (timeUntilExplosion > 0)
			{
				timeUntilExplosion--;
			}

			if (timeUntilWebshot > 0)
			{
				timeUntilWebshot--;
			}
		}

		else //Client-side only
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

			if (entity instanceof EntityFakePlayer && canEntityBeSeen(entity) && isHostile ||
					entity instanceof EntityHatchedSpider && isSpiderValidTarget((EntityHatchedSpider)entity) && isHostile ||
					entity instanceof EntityOtherQueen && isQueenValidTarget((EntityOtherQueen)entity) ||
					entity instanceof EntityPlayer && isPlayerValidTarget((EntityPlayer)entity)
					&& distanceToThisEntity < distanceToTarget)
			{
				closestValidTarget = (EntityLivingBase)entity;
				distanceToTarget = distanceToThisEntity;
			}
		}

		if (closestValidTarget != null && cocoonType == EnumCocoonType.ENDERMAN && timeUntilTeleport <= 0)
		{
			resetTimeUntilTeleport();

			worldObj.playSoundAtEntity(this, "mob.endermen.portal", 0.75F, 1.0F);
			setPosition(closestValidTarget.posX, closestValidTarget.posY, closestValidTarget.posZ);
			worldObj.playSound(closestValidTarget.posX, closestValidTarget.posY, closestValidTarget.posZ, "mob.endermen.portal", 0.75F, 1.0F, true);
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

				if (spider.owner.equals(owner))
				{
					return super.attackEntityFrom(damageSource, damageAmount);
				}
			}

			final List<EntityHatchedSpider> nearbySpiders = (List<EntityHatchedSpider>)LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(this, EntityHatchedSpider.class, 15);
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
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 */
	@Override
	protected void attackEntity(Entity entityBeingAttacked, float damageAmount)
	{
		damageAmount = getAttackDamage();
		getNavigator().setPath(getNavigator().getPathToEntityLiving(entityBeingAttacked), 0.4D);

		if (rand.nextInt(10) == 0)
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
				worldObj.spawnEntityInWorld(new EntityWeb(this, (EntityLivingBase)entityBeingAttacked, 2.6F));
			}

			if (LogicHelper.getDistanceToEntity(this, entityBeingAttacked) < 2.0D)
			{
				final EntityLivingBase entityLiving = (EntityLivingBase)entityBeingAttacked;
				entityBeingAttacked.attackEntityFrom(DamageSource.causeMobDamage(this), damageAmount);

				if (cocoonType == EnumCocoonType.CREEPER && timeUntilExplosion <= 0)
				{
					resetTimeUntilExplosion();
					worldObj.createExplosion(this, posX, posY, posZ, 5.0F, false);
				}

				if (entityLiving.getHealth() <= 0.0F)
				{
					killsUntilLevelUp--;

					if (level < 3 && (killsUntilLevelUp <= 0 || SpiderQueen.getInstance().inDebugMode))
					{
						timeUntilTeleport = 0;
						timeUntilExplosion = 0;
						timeUntilWebshot = 0;

						worldObj.playSoundAtEntity(this, "random.levelup", 0.75F, 1.0F);
						killsUntilLevelUp = LogicHelper.getNumberInRange(5, 15);
						level++;

						SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetLevel, getEntityId(), level));
						SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetInventory, getEntityId(), inventory));
					}

					target = null;
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

		inventory.dropAllItems();
	}

	@Override
	public boolean isOnLadder()
	{
		return isBesideClimbableBlock();
	}

	@Override
	public void setInWeb() {}

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
		if (cocoonType == EnumCocoonType.VILLAGER && owner.equals(entityPlayer.getCommandSenderName()))
		{
			entityPlayer.openGui(SpiderQueen.getInstance(), Constants.ID_GUI_INVENTORY, worldObj, (int)posX, (int)posY, (int)posZ);
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
			cocoonType = (EnumCocoonType)EnumCocoonType.class.getFields()[nbt.getInteger("cocoonType")].get(EnumCocoonType.class);
		}

		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}

		inventory.readInventoryFromNBT(nbt);
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
			cocoonType = (EnumCocoonType)EnumCocoonType.class.getFields()[additionalData.readInt()].get(EnumCocoonType.class);
		}

		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}

		level = additionalData.readInt();
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
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
			value = (byte)(value | 1);
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
		case EMPTY: return 0.5F;
		case WOLF: return 1.0F;
		default: return 2.5F + level / 2;
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
		if (cocoonType == EnumCocoonType.ENDERMAN)
		{
			worldObj.spawnParticle("portal",
					posX + (rand.nextDouble() - 0.5D) * width,
					posY + 0.5D + rand.nextDouble() * 0.25D,
					posZ + rand.nextDouble() - 0.5D * width,
					(rand.nextDouble() - 0.5D) * 2.0D,
					-rand.nextDouble(),
					(rand.nextDouble() - 0.5D) * 2.0D);
		}
	}

	private void resetTimeUntilTeleport()
	{
		switch (level)
		{
		case 1: timeUntilTeleport = Time.MINUTE; break;
		case 2: timeUntilTeleport = Time.SECOND * 30; break;
		case 3: timeUntilTeleport = Time.SECOND * 10; break;
		}
	}

	private void resetTimeUntilExplosion()
	{
		switch (level)
		{
		case 1: timeUntilExplosion = Time.MINUTE; break;
		case 2: timeUntilExplosion = Time.SECOND * 30; break;
		case 3: timeUntilExplosion = Time.SECOND * 10; break;
		}
	}

	private void resetTimeUntilWebshot()
	{
		switch (level)
		{
		case 1: timeUntilWebshot = Time.SECOND * 5; break;
		case 2: timeUntilWebshot = Time.SECOND * 3; break;
		case 3: timeUntilWebshot = Time.SECOND * 1; break;
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