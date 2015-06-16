package sq.enums;

import java.lang.reflect.Field;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import sq.core.minecraft.ModItems;
import sq.entity.EntityAnt;
import sq.entity.EntityBee;
import sq.entity.EntityWasp;
import sq.items.ItemCocoon;

public enum EnumCocoonType
{
	BLACK_ANT 	(0, 50, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL, EntityAnt.class),
	COW 		(1, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL, EntityCow.class),
	CREEPER 	(2, 40, EnumCocoonSize.NORMAL, EnumSpiderType.BOOM, EntityCreeper.class),
	GATHERER_BEE (3, 50, EnumCocoonSize.BEE, EnumSpiderType.NORMAL, EntityBee.class),
	HUMAN 		(4, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL, EntitySilverfish.class), //TODO
	PIG 		(5, 50, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL, EntityPig.class),
	QUEEN_BEE 	(6, 20, EnumCocoonSize.BEE, EnumSpiderType.NORMAL, EntityBee.class),
	SHEEP 		(7, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL, EntitySheep.class),
	SKELETON 	(8, 40, EnumCocoonSize.NORMAL, EnumSpiderType.SLINGER, EntitySkeleton.class),
	WARRIOR_BEE (9, 35, EnumCocoonSize.BEE, EnumSpiderType.NORMAL, EntityBee.class),
	WASP 		(10, 35, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL, EntityWasp.class),
	WOLF 		(11, 50, EnumCocoonSize.NORMAL, EnumSpiderType.NOVA, EntityWolf.class),
	ZOMBIE 		(12, 40, EnumCocoonSize.NORMAL, EnumSpiderType.TANK, EntityZombie.class),
	ENDERMAN 	(13, 30, EnumCocoonSize.TALL, EnumSpiderType.ENDER, EntityEnderman.class),
	BLAZE 		(14, 30, EnumCocoonSize.NORMAL, EnumSpiderType.INFERNO, EntityBlaze.class),
	CHICKEN 	(15, 80, EnumCocoonSize.SMALL, EnumSpiderType.NORMAL, EntityChicken.class),
	VILLAGER 	(16, 80, EnumCocoonSize.TALL, EnumSpiderType.PACK, EntityVillager.class),
	HORSE 		(17, 35, EnumCocoonSize.NORMAL, EnumSpiderType.RIDER, EntityHorse.class),
	RED_ANT		(18, 40, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL, EntityAnt.class);
	
	final int id;
	final int catchChance;
	final EnumCocoonSize cocoonSize;
	final EnumSpiderType spiderTypeYield;
	final String deathSound;
	final Class captureClass;
	
	EnumCocoonType(int id, int catchChance, EnumCocoonSize size, EnumSpiderType spiderType, Class captureClass)
	{
		this.id = id;
		this.catchChance = catchChance;
		this.cocoonSize = size;
		this.spiderTypeYield = spiderType;
		this.captureClass = captureClass;
		
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
	
	public static EnumCocoonType getCocoonType(Entity entity)
	{
		if (entity instanceof EntityAnt)
		{
			EntityAnt ant = (EntityAnt)entity;
			
			switch (ant.getAntType())
			{
			case RED: return EnumCocoonType.RED_ANT;
			case BLACK: return EnumCocoonType.BLACK_ANT;
			}
		}
		
		else if (entity instanceof EntityBee)
		{
			EntityBee bee = (EntityBee)entity;
			
			switch (bee.getBeeType())
			{
			case GATHERER: return EnumCocoonType.GATHERER_BEE;
			case QUEEN: return EnumCocoonType.QUEEN_BEE;
			case WARRIOR: return EnumCocoonType.WARRIOR_BEE;
			}
		}
		
		else
		{
			for (EnumCocoonType type : values())
			{
				if (type.captureClass.isAssignableFrom(entity.getClass()))
				{
					return type;
				}
			}
		}
		
		return null;
	}
}
