/*******************************************************************************
 * EntityFakePlayer.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.ByteBufIO;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.inventory.Inventory;

import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.logic.NBTHelper;
import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityFakePlayer extends EntityCreature implements IEntityAdditionalSpawnData
{
	public String					username;
	public String					lastAttackingPlayer;
	public boolean					isContributor;
	public boolean					hasInventoryBeenPopulated;
	public ResourceLocation			skinResourceLocation;
	public ThreadDownloadImageData	imageDownloadThread;
	public Inventory				inventory	= new Inventory(this);

	public EntityFakePlayer(World world)
	{
		super(world);

		if (!worldObj.isRemote)
		{
			addAI();
			username = SpiderQueen.getInstance().getRandomPlayerName();

			if (username.endsWith("*"))
			{
				isContributor = true;
				username = username.substring(0, username.length() - 1);
			}
		}
	}

	public void addAI()
	{
		getNavigator().setBreakDoors(true);
		getNavigator().setAvoidsWater(false);

		tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityCreeper.class, 0.6F, false));
		tasks.addTask(0, new EntityAIAttackOnCollide(this, EntitySkeleton.class, 0.6F, false));
		tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityZombie.class, 0.6F, false));
		tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityOtherQueen.class, 0.6F, false));
		tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.6F, false));
		tasks.addTask(0, new EntityAIAttackOnCollide(this, EntitySpider.class, 0.6F, false));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIMoveIndoors(this));
		tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
		tasks.addTask(4, new EntityAIOpenDoor(this, true));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6F));
		tasks.addTask(6, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		tasks.addTask(7, new EntityAIWander(this, 0.4F));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));

		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 16, false));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 16, false));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityZombie.class, 16, false));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityOtherQueen.class, 16, false));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16, false));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntitySpider.class, 16, false));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!worldObj.isRemote)
		{
			damageTarget();

			if (!hasInventoryBeenPopulated)
			{
				Inventory.populateWithRandomEquipment(inventory);
				hasInventoryBeenPopulated = true;
			}
		}

		inventory.setWornArmorItems();
	}

	@Override
	protected void damageEntity(DamageSource damageSource, float damageAmount)
	{
		final float unabsorbedDamage = ISpecialArmor.ArmorProperties.ApplyArmor(this, inventory.armorItems, damageSource, damageAmount);
		super.damageEntity(damageSource, unabsorbedDamage);
	}

	@Override
	public ItemStack func_130225_q(int armorId)
	{
		return inventory.armorItemInSlot(3 - armorId);
	}

	private void damageTarget()
	{
		if (getAttackTarget() instanceof EntityCreature)
		{
			final EntityCreature attackTarget = (EntityCreature) getAttackTarget();

			if (attackTarget != null)
			{
				if (LogicHelper.getDistanceToEntity(this, attackTarget) < 2.0F)
				{
					if (attackTarget.attackEntityFrom(DamageSource.generic, inventory.getDamageVsEntity(attackTarget)))
					{
						attackTarget.setAttackTarget(this);
						swingItem();
					}
				}
			}
		}

		else if (getAttackTarget() instanceof EntityPlayer)
		{
			final EntityPlayer attackTarget = (EntityPlayer) getAttackTarget();

			if (attackTarget != null)
			{
				if (LogicHelper.getDistanceToEntity(this, attackTarget) < 2.0F)
				{
					if (attackTarget.attackEntityFrom(DamageSource.generic, inventory.getDamageVsEntity(attackTarget)))
					{
						swingItem();
					}
				}
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
	{
		final Entity attackingEntity = damageSource.getEntity();

		if (attackingEntity != null)
		{
			if (attackingEntity instanceof EntityPlayer)
			{
				final EntityPlayer player = (EntityPlayer)attackingEntity;
				lastAttackingPlayer = player.getCommandSenderName();
			}

			setAttackTarget((EntityLivingBase) attackingEntity);
		}

		return super.attackEntityFrom(damageSource, damageAmount);
	}

	@Override
	public void onDeath(DamageSource damageSource)
	{
		super.onDeath(damageSource);

		inventory.dropAllItems();
	}

	@Override
	protected void dropFewItems(boolean recentlyHitByPlayer, int lootingLevel)
	{
		final int amountToDrop = lootingLevel > 0 ? lootingLevel : 1;
		final boolean dropHeart = LogicHelper.getBooleanWithProbability(30);
		final boolean dropBrain = LogicHelper.getBooleanWithProbability(30);
		final boolean dropSkull = LogicHelper.getBooleanWithProbability(30);

		if (dropHeart)
		{
			dropItem(SpiderQueen.getInstance().itemHeart, amountToDrop);
		}

		if (dropBrain)
		{
			dropItem(SpiderQueen.getInstance().itemBrain, amountToDrop);
		}

		if (dropSkull)
		{
			dropItem(SpiderQueen.getInstance().itemSkull, amountToDrop);
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6F);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return inventory.getBestItemOfType(ItemSword.class);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		inventory.writeInventoryToNBT(nbt);
		NBTHelper.autoWriteEntityToNBT(this, nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		inventory.readInventoryFromNBT(nbt);
		NBTHelper.autoReadEntityFromNBT(this, nbt);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		ByteBufIO.writeObject(buffer, username);
		buffer.writeBoolean(isContributor);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		username = (String) ByteBufIO.readObject(additionalData);
		isContributor = additionalData.readBoolean();
		setupCustomSkin();
		getInventoryFromServer();
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	private void getInventoryFromServer()
	{
		SpiderQueen.packetPipeline.sendPacketToServer(new Packet(EnumPacketType.GetInventory, getEntityId()));
	}

	private void setupCustomSkin()
	{
		if (!username.isEmpty())
		{
			skinResourceLocation = AbstractClientPlayer.getLocationSkin(username);
			imageDownloadThread = AbstractClientPlayer.getDownloadImageSkin(skinResourceLocation, username);
		}
	}
}
