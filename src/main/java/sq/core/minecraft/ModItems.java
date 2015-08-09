package sq.core.minecraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import sq.core.SpiderCore;
import sq.entity.creature.EntityAnt;
import sq.entity.creature.EntityBee;
import sq.entity.creature.EntityBeetle;
import sq.entity.creature.EntityFly;
import sq.entity.creature.EntityHuman;
import sq.entity.creature.EntityJack;
import sq.entity.creature.EntityMandragora;
import sq.entity.creature.EntityOctopus;
import sq.entity.creature.EntitySpiderQueen;
import sq.entity.creature.EntityWasp;
import sq.entity.creature.EntityYuki;
import sq.entity.friendly.EntityFriendlyCreeper;
import sq.entity.friendly.EntityFriendlySkeleton;
import sq.entity.friendly.EntityFriendlyZombie;
import sq.enums.EnumCocoonType;
import sq.enums.EnumOfferingType;
import sq.enums.EnumWebType;
import sq.items.ItemCocoon;
import sq.items.ItemEggSpawner;
import sq.items.ItemFreezeRod;
import sq.items.ItemGhastEgg;
import sq.items.ItemLantern;
import sq.items.ItemMandSeeds;
import sq.items.ItemNectar;
import sq.items.ItemOffering;
import sq.items.ItemRareFruit;
import sq.items.ItemRecallRod;
import sq.items.ItemSpiderEgg;
import sq.items.ItemSpiderRod;
import sq.items.ItemWeb;
import sq.items.ItemWebslinger;

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
	public static ItemRecallRod recallRod;
	public static ItemFreezeRod freezeRod;
	public static ItemWebslinger webslinger;
	public static ItemNectar nectar;
	public static ItemMandSeeds mandragoraSeeds;
	public static Item royalBlood;
	public static ItemLantern lantern;
	
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
		recallRod = new ItemRecallRod();
		freezeRod = new ItemFreezeRod();
		webslinger = new ItemWebslinger();
		nectar = new ItemNectar();
		mandragoraSeeds = new ItemMandSeeds();
		lantern = new ItemLantern();

		royalBlood = new Item().setCreativeTab(SpiderCore.getCreativeTab()).setUnlocalizedName("royalblood").setTextureName("sq:royal-blood").setMaxStackSize(1);
		GameRegistry.registerItem(royalBlood, "royalblood");
	}
}
