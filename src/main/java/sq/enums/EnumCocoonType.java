package sq.enums;

import java.lang.reflect.Field;

import sq.core.minecraft.ModItems;
import sq.items.ItemCocoon;

public enum EnumCocoonType
{
	BLACK_ANT 	(0, 50, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL),
	COW 		(1, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL),
	CREEPER 	(2, 40, EnumCocoonSize.NORMAL, EnumSpiderType.BOOM),
	GATHERER_BEE (3, 50, EnumCocoonSize.BEE, EnumSpiderType.NORMAL),
	HUMAN 		(4, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL),
	PIG 		(5, 50, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL),
	QUEEN_BEE 	(6, 20, EnumCocoonSize.BEE, EnumSpiderType.NORMAL),
	SHEEP 		(7, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL),
	SKELETON 	(8, 40, EnumCocoonSize.NORMAL, EnumSpiderType.SLINGER),
	WARRIOR_BEE (9, 35, EnumCocoonSize.BEE, EnumSpiderType.NORMAL),
	WASP 		(10, 35, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL),
	WOLF 		(11, 50, EnumCocoonSize.NORMAL, EnumSpiderType.NOVA),
	ZOMBIE 		(12, 40, EnumCocoonSize.NORMAL, EnumSpiderType.TANK),
	ENDERMAN 	(13, 30, EnumCocoonSize.TALL, EnumSpiderType.ENDER),
	BLAZE 		(14, 30, EnumCocoonSize.NORMAL, EnumSpiderType.INFERNO),
	CHICKEN 	(15, 80, EnumCocoonSize.SMALL, EnumSpiderType.NORMAL),
	VILLAGER 	(16, 80, EnumCocoonSize.TALL, EnumSpiderType.PACK),
	HORSE 		(17, 35, EnumCocoonSize.NORMAL, EnumSpiderType.RIDER),
	RED_ANT		(18, 40, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL);
	
	final int id;
	final int catchChance;
	final EnumCocoonSize cocoonSize;
	final EnumSpiderType spiderTypeYield;
	final String deathSound;
	
	EnumCocoonType(int id, int catchChance, EnumCocoonSize size, EnumSpiderType spiderType)
	{
		this.id = id;
		this.catchChance = catchChance;
		this.cocoonSize = size;
		this.spiderTypeYield = spiderType;
		
		this.deathSound = "mob." + name().toLowerCase() + ".death";
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getCatchChance()
	{
		return catchChance;
	}
	
	public ItemCocoon getCocoonItem()
	{
		try
		{
			for (Field f : ModItems.class.getDeclaredFields())
			{
				if (f.getName().equalsIgnoreCase("cocoon" + name().toLowerCase().replace("_", "")))
				{
					ItemCocoon item = (ItemCocoon) f.get(null);
					return item;
				}
			}
		}
		
		catch (Exception e)
		{
		}
		
		return null;
	}
	
	public EnumCocoonSize getCocoonSize()
	{
		return cocoonSize;
	}
	
	public EnumSpiderType getSpiderTypeYield()
	{
		return spiderTypeYield;
	}
	
	public String getDeathSound()
	{
		//Apply some corrections here
		switch (this)
		{
		case ENDERMAN: return "mob.endermen.death";
		case COW: return "mob.cow.hurt";
		case SHEEP: return "mob.sheep.hurt";
		case WARRIOR_BEE:
		case GATHERER_BEE:
		case QUEEN_BEE:
		case WASP: return "sq:bee.hurt";
		case BLACK_ANT:
		case RED_ANT: return "sq:ant.death";
		}
		
		return deathSound;
	}
	
	public String getName()
	{
		return name().toLowerCase().replace("_", "");
	}
}
