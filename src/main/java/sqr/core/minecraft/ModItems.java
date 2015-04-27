package sqr.core.minecraft;

import java.lang.reflect.Field;

import net.minecraft.item.Item;
import sqr.enums.EnumTypeVariant;
import sqr.item.ItemCocoon;
import sqr.item.ItemGhastEgg;
import sqr.item.ItemSpiderEgg;
import sqr.item.ItemWeb;
import sqr.item.ItemWebslinger;

public final class ModItems
{
	public static ItemWeb web;
	public static ItemWeb poisonWeb;
	public static ItemSpiderEgg spiderEgg;
	public static ItemGhastEgg ghastEgg;
	
	public static ItemCocoon cocoonEmpty;
	public static ItemCocoon cocoonPig;
	public static ItemCocoon cocoonSheep;
	public static ItemCocoon cocoonCreeper;
	public static ItemCocoon cocoonZombie;
	public static ItemCocoon cocoonSkeleton;
	public static ItemCocoon cocoonCow;
	public static ItemCocoon cocoonHuman;
	public static ItemCocoon cocoonGathererBee;
	public static ItemCocoon cocoonWarriorBee;
	public static ItemCocoon cocoonQueenBee;
	public static ItemCocoon cocoonWasp;
	public static ItemCocoon cocoonAnt;
	public static ItemCocoon cocoonWolf;
	public static ItemCocoon cocoonBlaze;
	public static ItemCocoon cocoonChicken;
	public static ItemCocoon cocoonEnderman;
	public static ItemCocoon cocoonGhast;
	public static ItemCocoon cocoonHorse;
	public static ItemCocoon cocoonVillager;
	
	public static Item nectar;
	public static Item rareFruit;
	public static Item skull;
	
	public static ItemWebslinger webSlinger;
	
	public ModItems()
	{
		for (final Field f : getClass().getFields())
		{
			try
			{
				//Check that the field is not null to account for cocoons that may already be assigned.
				if (f.getName().contains("cocoon") && f.get(null) == null)
				{
					EnumTypeVariant type = EnumTypeVariant.getByName(f.getName().replace("cocoon", ""));
					
					if (type == null)
					{
						continue;
					}
					
					else
					{
						f.set(null, new ItemCocoon(type));
					}
				}
			}
			
			catch (Exception e)
			{
				continue;
			}
		}
		// TODO
		/*
		 * itemWeb = new
		 * ItemWeb(332).setItemName("Webbing").setIconIndex(ModLoader
		 * .addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/webicon.png").toString())); itemPWeb =
		 * new
		 * ItemPWeb(358).setItemName("PWebbing").setIconIndex(ModLoader.addOverride
		 * ("/gui/items.png", new
		 * StringBuilder().append("/imgz/poisonweb.png").toString())); itemSWeb
		 * = new ItemSWeb(351).setItemName("Webslinger").setIconIndex(ModLoader.
		 * addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/websling.png").toString()));
		 * itemSpiderEgg = new
		 * ItemSpiderEgg(335).setItemName("Spider Egg").setIconIndex
		 * (ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/spidereggicon.png").toString()));
		 * itemGhastEgg = new
		 * ItemGhastEgg(362).setItemName("Ghast Egg").setIconIndex
		 * (ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/ghastegg.png").toString())); itemCocoon
		 * = new
		 * ItemCocoon(336).setItemName("Chicken Cocoon").setIconIndex(ModLoader
		 * .addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCChicken.png").toString()));
		 * itemCocoonPig = new
		 * ItemCocoonPig(337).setItemName("Pig Cocoon").setIconIndex
		 * (ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCPig.png").toString()));
		 * itemCocoonSheep = new
		 * ItemCocoonSheep(338).setItemName("Sheep Cocoon")
		 * .setIconIndex(ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCSheep.png").toString()));
		 * itemCocoonCreeper = new
		 * ItemCocoonCreeper(342).setItemName("Creeper Cocoon"
		 * ).setIconIndex(ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCCreeper.png").toString()));
		 * itemCocoonZombie = new
		 * ItemCocoonZombie(341).setItemName("Zombie Cocoon"
		 * ).setIconIndex(ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCZombie.png").toString()));
		 * itemCocoonSkeleton = new
		 * ItemCocoonSkeleton(340).setItemName("Skeleton Cocoon"
		 * ).setIconIndex(ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCSkeleton.png").toString()));
		 * itemCocoonCow = new
		 * ItemCocoonCow(339).setItemName("Cow Cocoon").setIconIndex
		 * (ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCCow.png").toString()));
		 * itemCocoonHuman = new
		 * ItemCocoonHuman(361).setItemName("Human Cocoon")
		 * .setIconIndex(ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/iconchuman.png").toString()));
		 * itemCocoonGatherer = new
		 * ItemCocoonGatherer(344).setItemName("Gatherer Bee Cocoon"
		 * ).setIconIndex(ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCGatherer.png").toString()));
		 * itemCocoonWarrior = new
		 * ItemCocoonWarrior(345).setItemName("Warrior Bee Cocoon"
		 * ).setIconIndex(ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCWarrior.png").toString()));
		 * itemCocoonQueenBee = new
		 * ItemCocoonQueenBee(346).setItemName("Queen Bee Cocoon"
		 * ).setIconIndex(ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCQueenBee.png").toString()));
		 * itemCocoonWasp = new
		 * ItemCocoonWasp(347).setItemName("Wasp Cocoon").setIconIndex
		 * (ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCWasp.png").toString()));
		 * itemCocoonAnt = new
		 * ItemCocoonAnt(348).setItemName("Ant Cocoon").setIconIndex
		 * (ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconCAnt.png").toString()));
		 * itemCocoonWolf = new
		 * ItemCocoonWolf(349).setItemName("Wolf Cocoon").setIconIndex
		 * (ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/IconWolf.png").toString())); itemNectar
		 * = new ItemNectar(343, 6,
		 * false).setItemName("Nectar").setIconIndex(ModLoader
		 * .addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/nectar.png").toString()));
		 * itemRareFruit = new ItemRareFruit(354,
		 * 3).setItemName("RareFruit").setIconIndex
		 * (ModLoader.addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/rarefruit.png").toString())); itemSkull
		 * = new
		 * ItemSkull(352).setItemName("Human Skull").setIconIndex(ModLoader
		 * .addOverride("/gui/items.png", new
		 * StringBuilder().append("/imgz/skull.png").toString()));
		 */
	}
}
