package sq.enums;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import radixcore.constant.Font.Color;
import sq.entities.EntityAnt;
import sq.entities.EntityBee;
import sq.entities.EntityHuman;
import sq.entities.EntityWasp;

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
	RED_ANT		(17, 40, EnumCocoonSize.INSECT, EnumSpiderType.NORMAL, EntityAnt.class),
	RABBIT		(18, 70, EnumCocoonSize.SMALL, EnumSpiderType.NORMAL, EntityRabbit.class),
	GUARDIAN	(19, 35, EnumCocoonSize.HUGE, EnumSpiderType.NORMAL, EntityGuardian.class),
	WITCH		(20, 45, EnumCocoonSize.TALL, EnumSpiderType.NORMAL, EntityWitch.class),
	POLARBEAR	(21, 35, EnumCocoonSize.HUGE, EnumSpiderType.NORMAL, EntityPolarBear.class);
	
	final int id;
	final int catchChance;
	final EnumCocoonSize cocoonSize;
	final EnumSpiderType spiderTypeYield;
	final String deathSound;
	final Class captureClass;
	final ResourceLocation textureLocation;
	
	EnumCocoonType(int id, int catchChance, EnumCocoonSize size, EnumSpiderType spiderType, Class captureClass)
	{
		this.id = id;
		this.catchChance = catchChance;
		this.cocoonSize = size;
		this.spiderTypeYield = spiderType;
		this.captureClass = captureClass;
		this.deathSound = "mob." + name().toLowerCase() + ".death";
		this.textureLocation = size == EnumCocoonSize.HUGE ? 
				new ResourceLocation("spiderqueen", "textures/entities/cocoon-small.png") : 
					new ResourceLocation("spiderqueen", "textures/entities/cocoon-" + size.name().toLowerCase() + ".png");
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getCatchChance()
	{
		return catchChance;
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
	
	public static EnumCocoonType getCocoonType(int id)
	{
		for (EnumCocoonType type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}
		
		return null;
	}

	public Class getCaptureClass()
	{
		return captureClass;
	}
	
	public ResourceLocation getTextureLocation() 
	{
		return textureLocation;
	}

	public Entity getCaptureEntityInstance(World world) 
	{
		try
		{
			return (Entity) captureClass.getDeclaredConstructor(World.class).newInstance(world);
		}
		
		catch (Exception e)
		{
			return null;
		}
	}
}