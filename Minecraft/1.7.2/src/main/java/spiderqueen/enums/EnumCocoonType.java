package spiderqueen.enums;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.Item;

public enum EnumCocoonType 
{
	Empty(null, 0, "null"),
	Pig(EntityPig.class, 0, "mob.pig.death"),
	Sheep(EntitySheep.class, 6, "mob.sheep.say"),
	Creeper(EntityCreeper.class, 4, "mob.creeper.death"),
	Chicken(EntityChicken.class, 0, "mob.chicken.death"),
	Zombie(EntityZombie.class, 4, "mob.zombie.death"),
	Skeleton(EntitySkeleton.class, 4, "mob.skeleton.death"),
	Cow(EntityCow.class, 6, "mob.cow.hurt"),
	Human(null, 0, "null"),
	GathererBee(null, 4, "null"),
	WarriorBee(null, 4, "null"),
	QueenBee(null, 1, "null"),
	Wasp(null, 4, "null"),
	Ant(null, 4, "null"),
	Wolf(EntityWolf.class, 4, "mob.wolf.death");
	
	private Class entityClass;
	private Item itemCocoon;
	
	/** How difficult it is to catch this entity. Lower factor is more difficult, unless 0. */
	private int entityCatchDifficulty;
	private String deathSound;
	
	private EnumCocoonType(Class entityClass, int entityCatchDifficulty, String deathSound)
	{
		this.entityClass = entityClass;
		this.entityCatchDifficulty = entityCatchDifficulty;
		this.deathSound = deathSound;
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
}
