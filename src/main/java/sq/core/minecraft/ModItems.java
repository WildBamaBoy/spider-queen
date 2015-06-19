package sq.core.minecraft;

import net.minecraft.item.Item;
import sq.core.SpiderCore;
import sq.enums.EnumCocoonType;
import sq.enums.EnumOfferingType;
import sq.enums.EnumWebType;
import sq.items.ItemCocoon;
import sq.items.ItemGhastEgg;
import sq.items.ItemMandSeeds;
import sq.items.ItemNectar;
import sq.items.ItemOffering;
import sq.items.ItemRareFruit;
import sq.items.ItemSpiderEgg;
import sq.items.ItemSpiderRod;
import sq.items.ItemWeb;
import sq.items.ItemWebslinger;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ModItems
{
	public static ItemCocoon cocoonBlackAnt;
	public static ItemCocoon cocoonRedAnt;
	public static ItemCocoon cocoonCow;
	public static ItemCocoon cocoonCreeper;
	public static ItemCocoon cocoonGathererBee;
	public static ItemCocoon cocoonHuman;
	public static ItemCocoon cocoonPig;
	public static ItemCocoon cocoonQueenBee;
	public static ItemCocoon cocoonSheep;
	public static ItemCocoon cocoonSkeleton;
	public static ItemCocoon cocoonWarriorBee;
	public static ItemCocoon cocoonWasp;
	public static ItemCocoon cocoonWolf;
	public static ItemCocoon cocoonZombie;
	public static ItemCocoon cocoonEnderman;
	public static ItemCocoon cocoonChicken;
	public static ItemCocoon cocoonVillager;
	public static ItemCocoon cocoonHorse;
	
	public static ItemGhastEgg ghastEgg;
	public static ItemSpiderEgg spiderEgg;
	public static ItemWeb webNormal;
	public static ItemWeb webPoison;
	public static ItemRareFruit rareFruit;
	public static ItemOffering skull;
	public static ItemOffering heart;
	public static ItemOffering brain;
	public static ItemSpiderRod spiderRod;
	public static ItemWebslinger webslinger;
	public static ItemNectar nectar;
	public static ItemMandSeeds mandragoraSeeds;
	public static Item bugLight;
	
	public ModItems()
	{
		cocoonBlackAnt = new ItemCocoon(EnumCocoonType.BLACK_ANT);
		cocoonRedAnt = new ItemCocoon(EnumCocoonType.RED_ANT);
		cocoonCow = new ItemCocoon(EnumCocoonType.COW);
		cocoonCreeper = new ItemCocoon(EnumCocoonType.CREEPER);
		cocoonGathererBee = new ItemCocoon(EnumCocoonType.GATHERER_BEE);
		cocoonHuman = new ItemCocoon(EnumCocoonType.HUMAN);
		cocoonPig = new ItemCocoon(EnumCocoonType.PIG);
		cocoonQueenBee = new ItemCocoon(EnumCocoonType.QUEEN_BEE);
		cocoonSheep = new ItemCocoon(EnumCocoonType.SHEEP);
		cocoonSkeleton = new ItemCocoon(EnumCocoonType.SKELETON);
		cocoonWarriorBee = new ItemCocoon(EnumCocoonType.WARRIOR_BEE);
		cocoonWasp = new ItemCocoon(EnumCocoonType.WASP);
		cocoonWolf = new ItemCocoon(EnumCocoonType.WOLF);
		cocoonZombie = new ItemCocoon(EnumCocoonType.ZOMBIE);
		cocoonChicken = new ItemCocoon(EnumCocoonType.CHICKEN);
		cocoonVillager = new ItemCocoon(EnumCocoonType.VILLAGER);
		cocoonHorse = new ItemCocoon(EnumCocoonType.HORSE);
		
		ghastEgg = new ItemGhastEgg();
		spiderEgg = new ItemSpiderEgg();
		webNormal = new ItemWeb(EnumWebType.NORMAL);
		webPoison = new ItemWeb(EnumWebType.POISON);
		rareFruit = new ItemRareFruit();
		skull = new ItemOffering(EnumOfferingType.SKULL);
		heart = new ItemOffering(EnumOfferingType.HEART);
		brain = new ItemOffering(EnumOfferingType.BRAIN);
		spiderRod = new ItemSpiderRod();
		webslinger = new ItemWebslinger();
		nectar = new ItemNectar();
		mandragoraSeeds = new ItemMandSeeds();

		bugLight = new Item().setCreativeTab(SpiderCore.getCreativeTab()).setUnlocalizedName("buglight").setTextureName("sq:buglight").setMaxStackSize(1);
		GameRegistry.registerItem(bugLight, "buglight");
	}
}
