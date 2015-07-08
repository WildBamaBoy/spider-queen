package sq.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
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
import radixcore.constant.Font.Color;
import sq.core.minecraft.ModItems;
import sq.entity.creature.EntityAnt;
import sq.entity.creature.EntityBee;
import sq.entity.creature.EntityHuman;
import sq.entity.creature.EntityWasp;
import sq.items.ItemCocoon;

/**
 * Defines all possible cocoon types. Each entry ties the type to an id, defines a base catch chance,
 * provides the cocoon size, spider yield, and the creature class that can be cocooned.
 */
public enum EnumCocoonType
{
	BLACK_ANT 	(0, 50, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL, EntityAnt.class),
	COW 		(1, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL, EntityCow.class),
	CREEPER 	(2, 40, EnumCocoonSize.NORMAL, EnumSpiderType.BOOM, EntityCreeper.class),
	GATHERER_BEE (3, 50, EnumCocoonSize.BEE, EnumSpiderType.NORMAL, EntityBee.class),
	HUMAN 		(4, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL, EntityHuman.class),
	PIG 		(5, 50, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL, EntityPig.class),
	QUEEN_BEE 	(6, 20, EnumCocoonSize.BEE, EnumSpiderType.NORMAL, EntityBee.class),
	SHEEP 		(7, 40, EnumCocoonSize.NORMAL, EnumSpiderType.NORMAL, EntitySheep.class),
	SKELETON 	(8, 40, EnumCocoonSize.NORMAL, EnumSpiderType.SLINGER, EntitySkeleton.class),
	WARRIOR_BEE (9, 35, EnumCocoonSize.BEE, EnumSpiderType.NORMAL, EntityBee.class),
	WASP 		(10, 35, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL, EntityWasp.class),
	WOLF 		(11, 50, EnumCocoonSize.NORMAL, EnumSpiderType.NOVA, EntityWolf.class),
	ZOMBIE 		(12, 40, EnumCocoonSize.NORMAL, EnumSpiderType.TANK, EntityZombie.class),
	ENDERMAN 	(13, 30, EnumCocoonSize.TALL, EnumSpiderType.ENDER, EntityEnderman.class),
	CHICKEN 	(14, 80, EnumCocoonSize.SMALL, EnumSpiderType.NORMAL, EntityChicken.class),
	VILLAGER 	(15, 80, EnumCocoonSize.TALL, EnumSpiderType.PACK, EntityVillager.class),
	HORSE 		(16, 35, EnumCocoonSize.NORMAL, EnumSpiderType.RIDER, EntityHorse.class),
	RED_ANT		(17, 40, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL, EntityAnt.class);
	
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
	
	public String getNameColor()
	{
		switch (getSpiderTypeYield())
		{
		case ENDER: return Color.PURPLE;
		case RIDER: return Color.GOLD;
		case BOOM: return Color.GREEN;
		case SLINGER: return Color.WHITE;
		case PACK: return Color.GOLD;
		case NOVA: return Color.WHITE;
		case TANK: return Color.DARKGREEN;
		default: return Color.GRAY;
		}
	}
	
	public List<String> getAbilities()
	{
		List<String> list = new ArrayList<String>();
		
		switch (getSpiderTypeYield())
		{
		case ENDER: list.add("Tosses targets into the air."); break;
		case RIDER: list.add("Can be ridden on ground."); list.add("Can be ridden up walls."); break;
		case BOOM: list.add("Shoots explosives at enemies."); break;
		case SLINGER: list.add("Shoots web at enemies."); break;
		case PACK: list.add("Carries items."); break;
		case NOVA: list.add("Heals injured spiders."); break;
		case TANK: list.add("Poisons targets."); list.add("High health."); break;
		default: break;
		}
		
		return list;
	}
	
	public List<String> getCaveats()
	{
		List<String> list = new ArrayList<String>();
		
		switch (getSpiderTypeYield())
		{
		case ENDER: list.add("Does not fight."); break;
		case RIDER: list.add("Does not fight."); break;
		case BOOM: list.add("Explodes on death."); break;
		case SLINGER: list.add("None."); break;
		case PACK: list.add("Does not fight."); break;
		case NOVA: list.add("Low health."); break;
		case TANK: list.add("Moves slowly."); break;
		default: break;
		}
		
		return list;
	}
	
	public List<String> getLevelUpConditions() 
	{
		List<String> list = new ArrayList<String>();
		
		switch (getSpiderTypeYield())
		{
		case ENDER: list.add("From using ability."); break;
		case RIDER: list.add("From being ridden."); break;
		case BOOM: list.add("From combat."); break;
		case SLINGER: list.add("From combat."); break;
		case PACK: list.add("Over time."); break;
		case NOVA: list.add("From healing."); break;
		case TANK: list.add("From combat."); break;
		default: break;
		}
		
		return list;
	}

	public String getDeathSound()
	{
		//Apply some corrections here
		switch (this)
		{
		case ENDERMAN: return "mob.endermen.death";
		case COW: return "mob.cow.hurt";
		case SHEEP: return "mob.sheep.say";
		case WARRIOR_BEE:
		case GATHERER_BEE:
		case QUEEN_BEE:
		case WASP: return "sq:bee.hurt";
		case BLACK_ANT:
		case RED_ANT: return "sq:ant.death";
		case CHICKEN: return "mob.chicken.hurt";
		default:
			break;
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
