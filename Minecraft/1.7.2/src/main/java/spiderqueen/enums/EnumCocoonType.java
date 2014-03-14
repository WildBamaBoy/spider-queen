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
	Empty(null, 0),
	Pig(EntityPig.class, 0),
	Sheep(EntitySheep.class, 6),
	Creeper(EntityCreeper.class, 4),
	Chicken(EntityChicken.class, 0),
	Zombie(EntityZombie.class, 4),
	Skeleton(EntitySkeleton.class, 4),
	Cow(EntityCow.class, 6),
	Human(null, 0),
	GathererBee(null, 4),
	WarriorBee(null, 4),
	QueenBee(null, 1),
	Wasp(null, 4),
	Ant(null, 4),
	Wolf(EntityWolf.class, 4);
	
	private Class entityClass;
	private Item itemCocoon;
	
	/** How difficult it is to catch this entity. Lower factor is more difficult, unless 0. */
	private int entityCatchDifficulty;
	
	private EnumCocoonType(Class entityClass, int entityCatchDifficulty)
	{
		this.entityClass = entityClass;
		this.entityCatchDifficulty = entityCatchDifficulty;
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
