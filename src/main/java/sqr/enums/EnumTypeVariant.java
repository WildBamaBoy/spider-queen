package sqr.enums;

import net.minecraft.item.Item;
import sqr.core.minecraft.ModItems;

public enum EnumTypeVariant
{
	ANT, COW, CREEPER, GATHERER, HUMAN, PIG, QUEEN_BEE, SHEEP, SKELETON, WARRIOR, WASP, WOLF, ZOMBIE, ENDERMAN, BLAZE, CHICKEN, VILLAGER, HORSE, GHAST, _ENDERMINION;
	
	public Item getCocoon()
	{
		Item item = null;
		
		switch (this)
		{
		case ANT:
			item = ModItems.itemCocoonAnt;
			break;
		case BLAZE:
			item = ModItems.itemCocoonBlaze;
			break;
		case CHICKEN:
			item = ModItems.itemCocoonChicken;
			break;
		case COW:
			item = ModItems.itemCocoonCow;
			break;
		case CREEPER:
			item = ModItems.itemCocoonCreeper;
			break;
		case ENDERMAN:
			item = ModItems.itemCocoonEnderman;
			break;
		case GATHERER:
			item = ModItems.itemCocoonGatherer;
			break;
		case HUMAN:
			item = ModItems.itemCocoonHuman;
			break;
		case PIG:
			item = ModItems.itemCocoonPig;
			break;
		case QUEEN_BEE:
			item = ModItems.itemCocoonQueenBee;
			break;
		case SHEEP:
			item = ModItems.itemCocoonSheep;
			break;
		case SKELETON:
			item = ModItems.itemCocoonSkeleton;
			break;
		case WARRIOR:
			item = ModItems.itemCocoonWarrior;
			break;
		case WASP:
			item = ModItems.itemCocoonWasp;
			break;
		case WOLF:
			item = ModItems.itemCocoonWolf;
			break;
		case ZOMBIE:
			item = ModItems.itemCocoonZombie;
			break;
		case GHAST:
			item = ModItems.itemCocoonGhast;
			break;
		case HORSE:
			item = ModItems.itemCocoonHorse;
			break;
		case VILLAGER:
			item = ModItems.itemCocoonVillager;
			break;
		default:
			item = ModItems.itemCocoonEmpty;
		}
		
		return item;
	}
}
