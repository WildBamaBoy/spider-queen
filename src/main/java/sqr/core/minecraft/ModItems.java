package sqr.core.minecraft;

import net.minecraft.item.Item;

public final class ModItems
{
	public static Item itemWeb;
	public static Item itemPWeb;
	public static Item itemSpiderEgg;
	public static Item itemGhastEgg;
	public static Item itemCocoonEmpty;
	public static Item itemCocoonPig;
	public static Item itemCocoonSheep;
	public static Item itemCocoonCreeper;
	public static Item itemCocoonZombie;
	public static Item itemCocoonSkeleton;
	public static Item itemCocoonCow;
	public static Item itemCocoonHuman;
	public static Item itemCocoonGatherer;
	public static Item itemCocoonWarrior;
	public static Item itemCocoonQueenBee;
	public static Item itemCocoonWasp;
	public static Item itemCocoonAnt;
	public static Item itemCocoonWolf;
	public static Item itemCocoonBlaze;
	public static Item itemCocoonChicken;
	public static Item itemCocoonEnderman;
	public static Item itemCocoonGhast;
	public static Item itemCocoonHorse;
	public static Item itemCocoonVillager;
	public static Item itemNectar;
	public static Item itemRareFruit;
	public static Item itemSWeb;
	public static Item itemSkull;
	
	public ModItems()
	{
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
