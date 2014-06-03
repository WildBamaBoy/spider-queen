/*******************************************************************************
 * EntitySpiderEgg.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.enums.EnumCocoonType;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.logic.Point3D;
import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntitySpiderEgg extends EntityCreature
{
	private String	owner;
	private int		timeUntilEggHatch;

	public EntitySpiderEgg(World world)
	{
		super(world);
		setSize(0.15F, 0.15F);
	}

	public EntitySpiderEgg(World world, String owner)
	{
		super(world);
		this.owner = owner;
		this.renderDistanceWeight = 50.0F;
		setSize(0.15F, 0.15F);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		timeUntilEggHatch = LogicHelper.getNumberInRange(500, 5000);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
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

		if (!worldObj.isRemote)
		{
			// DEBUG
			if (SpiderQueen.getInstance().inDebugMode && SpiderQueen.getInstance().debugDoRapidSpiderGrowth)
			{
				timeUntilEggHatch = 0;
			}
			// DEBUG

			if (timeUntilEggHatch <= 0)
			{
				final EntityCocoon cocoonToConsume = getConsumableCocoon();

				if (cocoonToConsume != null && cocoonToConsume.getCocoonType() == EnumCocoonType.GHAST)
				{
					final EntityPlayer player = worldObj.getPlayerEntityByName(owner);

					if (player != null)
					{
						player.triggerAchievement(SpiderQueen.getInstance().achievementHatchGhastSpider);
					}

					final EntityMiniGhast miniGhast = new EntityMiniGhast(worldObj, owner);
					miniGhast.setPosition(posX, posY + 1, posZ);
					worldObj.spawnEntityInWorld(miniGhast);
					setDead();

					cocoonToConsume.setEaten(true);
					SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetEaten, cocoonToConsume.getEntityId()));
				}

				else
				{
					final EntityHatchedSpider hatchedSpider = consumeCocoon(cocoonToConsume);
					doHatch(hatchedSpider);
				}
			}

			else
			{
				timeUntilEggHatch--;
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		final Entity entity = damageSource.getEntity();

		if (entity instanceof EntityPlayer && !entity.worldObj.isRemote)
		{
			setDead();
			entityDropItem(new ItemStack(SpiderQueen.getInstance().itemSpiderEgg), entity.worldObj.rand.nextFloat());
		}

		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		final int sqrtDistance = (int)Math.sqrt(distance);
		return sqrtDistance < 50;
	}

	private EntityCocoon getConsumableCocoon()
	{
		final List<EntityCocoon> nearbyCocoons = (List<EntityCocoon>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(this, EntityCocoon.class, 5);
		EntityCocoon nearestCocoon = null;
		double lowestDistance = 100D;

		for (final EntityCocoon cocoon : nearbyCocoons)
		{
			final double distanceToCurrentEntity = LogicHelper.getDistanceToEntity(this, cocoon);

			if (!cocoon.isEaten() && distanceToCurrentEntity < lowestDistance)
			{
				lowestDistance = distanceToCurrentEntity;
				nearestCocoon = cocoon;
			}
		}

		return nearestCocoon;
	}

	private EntityHatchedSpider consumeCocoon(EntityCocoon cocoonToConsume)
	{
		EntityHatchedSpider spiderToSpawn;

		if (cocoonToConsume == null)
		{
			spiderToSpawn = new EntityHatchedSpider(worldObj, owner, EnumCocoonType.EMPTY);
		}

		else
		{
			cocoonToConsume.setEaten(true);
			spiderToSpawn = new EntityHatchedSpider(worldObj, owner, cocoonToConsume.getCocoonType());
			SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetEaten, cocoonToConsume.getEntityId()));
		}

		return spiderToSpawn;
	}

	private void doHatch(EntityHatchedSpider hatchedSpider)
	{
		try
		{
			final EntityPlayer player = worldObj.getPlayerEntityByName(owner);

			if (player != null)
			{
				player.triggerAchievement(SpiderQueen.getInstance().achievementHatchSpider);

				Achievement achievementToUnlock = null;

				switch (hatchedSpider.cocoonType)
				{
					case BLAZE:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchBlazeSpider;
						break;
					case CREEPER:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchCreeperSpider;
						break;
					case ENDERMAN:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchEndermanSpider;
						break;
					case GHAST:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchGhastSpider;
						break;
					case HORSE:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchHorseSpider;
						break;
					case SKELETON:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchSkeletonSpider;
						break;
					case VILLAGER:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchVillagerSpider;
						break;
					case WOLF:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchWolfSpider;
						break;
					case ZOMBIE:
						achievementToUnlock = SpiderQueen.getInstance().achievementHatchZombieSpider;
						break;
					default:
						break;
				}

				if (achievementToUnlock != null)
				{
					player.triggerAchievement(achievementToUnlock);
				}
			}

			final Block spawnBlock = worldObj.getBlock((int)posX,(int)posY,(int)posZ);
			Point3D spawnPoint = new Point3D(posX, posY, posZ);

			if (worldObj.getBlock(spawnPoint.iPosX + 1, spawnPoint.iPosY, spawnPoint.iPosZ) == Blocks.air) { spawnPoint = new Point3D(posX + 1, posY, posZ); System.out.println("1"); }
			else if (worldObj.getBlock(spawnPoint.iPosX, spawnPoint.iPosY, spawnPoint.iPosZ + 1) == Blocks.air) { spawnPoint = new Point3D(posX, posY, posZ + 1); System.out.println("1"); }
			else if (worldObj.getBlock(spawnPoint.iPosX - 1, spawnPoint.iPosY, spawnPoint.iPosZ) == Blocks.air) { spawnPoint = new Point3D(posX - 1, posY, posZ); System.out.println("3"); }
			else if (worldObj.getBlock(spawnPoint.iPosX, spawnPoint.iPosY, spawnPoint.iPosZ - 1) == Blocks.air) { spawnPoint = new Point3D(posX, posY, posZ - 1); System.out.println("4"); }

			hatchedSpider.setLocationAndAngles(spawnPoint.iPosX, spawnPoint.iPosY, spawnPoint.iPosZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(hatchedSpider);
			setDead();
		}

		catch (NullPointerException e)
		{
			//Happens when player is null - not logged in.
		}
	}
}
