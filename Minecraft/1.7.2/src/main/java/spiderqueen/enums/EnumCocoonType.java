package spiderqueen.enums;

import com.radixshock.radixcore.logic.LogicHelper;

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

public enum EnumCocoonType 
{
	EMPTY(null, 0, "null", EnumCocoonSize.NORMAL),
	PIG(EntityPig.class, 0, "mob.pig.death", EnumCocoonSize.NORMAL),
	SHEEP(EntitySheep.class, 6, "mob.sheep.say", EnumCocoonSize.NORMAL),
	CREEPER(EntityCreeper.class, 4, "mob.creeper.death", EnumCocoonSize.NORMAL),
	CHICKEN(EntityChicken.class, 0, "mob.chicken.hurt", EnumCocoonSize.SMALL),
	ZOMBIE(EntityZombie.class, 4, "mob.zombie.death", EnumCocoonSize.NORMAL),
	SKELETON(EntitySkeleton.class, 4, "mob.skeleton.death", EnumCocoonSize.NORMAL),
	COW(EntityCow.class, 6, "mob.cow.hurt", EnumCocoonSize.NORMAL),
	HUMAN(null, 0, "null", EnumCocoonSize.NORMAL),
	VILLAGER(EntityVillager.class, 0, "mob.villager.death", EnumCocoonSize.TALL),
	HORSE(EntityHorse.class, 3, "mob.horse.death", EnumCocoonSize.NORMAL),
	ENDERMAN(EntityEnderman.class, 2, "mob.endermen.death", EnumCocoonSize.TALL),
//	GATHERERBEE(null, 4, "null", EnumCocoonSize.NORMAL),
//	WARRIORBEE(null, 4, "null", EnumCocoonSize.NORMAL),
//	QUEENBEE(null, 1, "null", EnumCocoonSize.NORMAL),
//	WASP(null, 4, "null", EnumCocoonSize.NORMAL),
//	ANT(null, 4, "null", EnumCocoonSize.NORMAL),
	WOLF(EntityWolf.class, 4, "mob.wolf.death", EnumCocoonSize.NORMAL);
	
	private Class entityClass;
	private Item itemCocoon;
	
	/** How difficult it is to catch this entity. Lower factor is more difficult, unless 0. */
	private int entityCatchDifficulty;
	private String deathSound;
	private EnumCocoonSize cocoonSize;
	
	private EnumCocoonType(Class entityClass, int entityCatchDifficulty, String deathSound, EnumCocoonSize cocoonSize)
	{
		this.entityClass = entityClass;
		this.entityCatchDifficulty = entityCatchDifficulty;
		this.deathSound = deathSound;
		this.cocoonSize = cocoonSize;
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

	public static boolean isAnimalBased(EnumCocoonType cocoonType)
	{
		return cocoonType == PIG || cocoonType == SHEEP || cocoonType == CHICKEN || cocoonType == COW;
	}
	
	public static EnumCocoonType getCocoonTypeByCapturedClass(Class entityClass)
	{
		for (EnumCocoonType type : EnumCocoonType.values())
		{
			if (type.getEntityClass() == entityClass)
			{
				return type;
			}
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
