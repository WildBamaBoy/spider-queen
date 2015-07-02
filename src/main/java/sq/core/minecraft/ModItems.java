package sq.core.minecraft;

import net.minecraft.item.Item;
import sq.core.SpiderCore;
import sq.entity.EntityAnt;
import sq.entity.EntityBee;
import sq.entity.EntityBeetle;
import sq.entity.EntityFly;
import sq.entity.EntityFriendlyCreeper;
import sq.entity.EntityFriendlySkeleton;
import sq.entity.EntityFriendlyZombie;
import sq.entity.EntityHuman;
import sq.entity.EntityJack;
import sq.entity.EntityMandragora;
import sq.entity.EntityOctopus;
import sq.entity.EntitySpiderQueen;
import sq.entity.EntityWasp;
import sq.entity.EntityYuki;
import sq.enums.EnumCocoonType;
import sq.enums.EnumOfferingType;
import sq.enums.EnumWebType;
import sq.items.ItemCocoon;
import sq.items.ItemEggSpawner;
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
	
	public static ItemEggSpawner eggAntBlack;
	public static ItemEggSpawner eggBee;
	public static ItemEggSpawner eggBeetle;
	public static ItemEggSpawner eggFly;
	public static ItemEggSpawner eggJack;
	public static ItemEggSpawner eggMandragora;
	public static ItemEggSpawner eggOctopus;
	public static ItemEggSpawner eggSpiderQueen;
	public static ItemEggSpawner eggWasp;
	public static ItemEggSpawner eggYuki;
	public static ItemEggSpawner eggFriendlyCreeper;
	public static ItemEggSpawner eggFriendlySkeleton;
	public static ItemEggSpawner eggFriendlyZombie;
	public static ItemEggSpawner eggHuman;
	
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
	public static Item royalBlood;
	
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
		
		eggAntBlack = new ItemEggSpawner(EntityAnt.class, "egg-ant");
		eggBee = new ItemEggSpawner(EntityBee.class, "egg-bee");
		eggBeetle = new ItemEggSpawner(EntityBeetle.class, "egg-beetle");
		eggFly = new ItemEggSpawner(EntityFly.class, "egg-fly");
		eggJack = new ItemEggSpawner(EntityJack.class, "egg-jack");
		eggMandragora = new ItemEggSpawner(EntityMandragora.class, "egg-mandragora");
		eggOctopus = new ItemEggSpawner(EntityOctopus.class, "egg-octopus");
		eggSpiderQueen = new ItemEggSpawner(EntitySpiderQueen.class, "egg-spider-queen");
		eggWasp = new ItemEggSpawner(EntityWasp.class, "egg-wasp");
		eggYuki = new ItemEggSpawner(EntityYuki.class, "egg-yuki");
		eggFriendlyCreeper = new ItemEggSpawner(EntityFriendlyCreeper.class, "egg-f-creeper");
		eggFriendlySkeleton = new ItemEggSpawner(EntityFriendlySkeleton.class, "egg-f-skeleton");
		eggFriendlyZombie = new ItemEggSpawner(EntityFriendlyZombie.class, "egg-f-zombie");
		eggHuman = new ItemEggSpawner(EntityHuman.class, "egg-human");
		
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

		royalBlood = new Item().setCreativeTab(SpiderCore.getCreativeTab()).setUnlocalizedName("royalblood").setTextureName("sq:royal-blood").setMaxStackSize(1);
		GameRegistry.registerItem(royalBlood, "royalblood");
	}
}
