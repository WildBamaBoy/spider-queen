/*******************************************************************************
 * EnumCocoonType.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.enums;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.Item;
import spiderqueen.entity.EntityFakePlayer;

import com.radixshock.radixcore.logic.LogicHelper;

public enum EnumCocoonType
{
	EMPTY(null, 0, "null", EnumCocoonSize.NORMAL, EnumSpiderSize.NORMAL), 
	PIG(EntityPig.class, 0, "mob.pig.death", EnumCocoonSize.NORMAL, EnumSpiderSize.NORMAL), 
	SHEEP(EntitySheep.class, 6, "mob.sheep.say", EnumCocoonSize.NORMAL, EnumSpiderSize.NORMAL), 
	CREEPER(EntityCreeper.class, 4, "mob.creeper.death", EnumCocoonSize.NORMAL, EnumSpiderSize.RAISED), 
	CHICKEN(EntityChicken.class, 0, "mob.chicken.hurt", EnumCocoonSize.SMALL, EnumSpiderSize.NORMAL), 
	ZOMBIE(EntityZombie.class, 4, "mob.zombie.death", EnumCocoonSize.NORMAL, EnumSpiderSize.HUGE), 
	SKELETON(EntitySkeleton.class, 4, "mob.skeleton.death", EnumCocoonSize.NORMAL, EnumSpiderSize.THIN), 
	COW(EntityCow.class, 6, "mob.cow.hurt", EnumCocoonSize.NORMAL, EnumSpiderSize.NORMAL), 
	HUMAN(EntityFakePlayer.class, 0, "null", EnumCocoonSize.NORMAL, EnumSpiderSize.NORMAL), 
	VILLAGER(EntityVillager.class, 0, "mob.villager.death", EnumCocoonSize.TALL, EnumSpiderSize.NORMAL), 
	HORSE(EntityHorse.class, 3, "mob.horse.death", EnumCocoonSize.NORMAL, EnumSpiderSize.SWARM), 
	ENDERMAN(EntityEnderman.class, 2, "mob.endermen.death", EnumCocoonSize.TALL, EnumSpiderSize.LONGLEG), 
	WOLF(EntityWolf.class, 4, "mob.wolf.death", EnumCocoonSize.NORMAL, EnumSpiderSize.TINY),
	
	_ENDERMITE(null, 0, "null", EnumCocoonSize.NORMAL, EnumSpiderSize.TINYLONGLEG);
	
	private Class			entityClass;
	private Item			itemCocoon;

	/**
	 * How difficult it is to catch this entity. Lower factor is more difficult,
	 * unless 0.
	 */
	private int				entityCatchDifficulty;
	private String			deathSound;
	private EnumCocoonSize	cocoonSize;
	private EnumSpiderSize	spiderSize;

	private EnumCocoonType(Class entityClass, int entityCatchDifficulty, String deathSound, EnumCocoonSize cocoonSize, EnumSpiderSize spiderSize)
	{
		this.entityClass = entityClass;
		this.entityCatchDifficulty = entityCatchDifficulty;
		this.deathSound = deathSound;
		this.cocoonSize = cocoonSize;
		this.spiderSize = spiderSize;
	}

	public Class getEntityClass()
	{
		return entityClass;
	}

	public void setCocoonItem(Item item)
	{
		itemCocoon = item;
	}

	public Item getCocoonItem()
	{
		return itemCocoon;
	}

	public int getEntityCatchDifficulty()
	{
		return entityCatchDifficulty;
	}

	public String getDeathSound()
	{
		return deathSound;
	}

	public EnumCocoonSize getCocoonSize()
	{
		return cocoonSize;
	}

	public EnumSpiderSize getSpiderSize()
	{
		return spiderSize;
	}

	public static boolean isAnimalBased(EnumCocoonType cocoonType)
	{
		return cocoonType == PIG || cocoonType == SHEEP || cocoonType == CHICKEN || cocoonType == COW;
	}

	public static EnumCocoonType getCocoonTypeByCapturedClass(Class entityClass)
	{
		for (final EnumCocoonType type : EnumCocoonType.values())
		{
			if (type.getEntityClass() == entityClass) { return type; }
		}

		return null;
	}

	public static EnumCocoonType getRandomCocoonType()
	{
		final int maxRange = EnumCocoonType.values().length - 1;
		final int typeIndexToReturn = LogicHelper.getNumberInRange(0, maxRange);

		return EnumCocoonType.values()[typeIndexToReturn];
	}
}
