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
	Empty(null),
	Pig(EntityPig.class),
	Sheep(EntitySheep.class),
	Creeper(EntityCreeper.class),
	Chicken(EntityChicken.class),
	Zombie(EntityZombie.class),
	Skeleton(EntitySkeleton.class),
	Cow(EntityCow.class),
	Human(null),
	GathererBee(null),
	WarriorBee(null),
	QueenBee(null),
	Wasp(null),
	Ant(null),
	Wolf(EntityWolf.class);
	
	private Class entityClass;
	private Item itemCocoon;
	
	private EnumCocoonType(Class entityClass)
	{
		this.entityClass = entityClass;
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
	
	public static EnumCocoonType getCocoonTypeByClass(Class entityClass)
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
