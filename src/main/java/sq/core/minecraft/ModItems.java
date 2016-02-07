package sq.core.minecraft;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
import sq.entity.friendly.EntityFriendlyBee;
import sq.entity.friendly.EntityFriendlyCreeper;
import sq.entity.friendly.EntityFriendlyMandragora;
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

/**
 * All items in Spider Queen.
 */
public final class ModItems
{
	private static ModItems instance;
	
	public final ItemCocoon cocoonBlackAnt;
	public final ItemCocoon cocoonRedAnt;
	public final ItemCocoon cocoonCow;
	public final ItemCocoon cocoonCreeper;
	public final ItemCocoon cocoonGathererBee;
	public final ItemCocoon cocoonHuman;
	public final ItemCocoon cocoonPig;
	public final ItemCocoon cocoonQueenBee;
	public final ItemCocoon cocoonSheep;
	public final ItemCocoon cocoonSkeleton;
	public final ItemCocoon cocoonWarriorBee;
	public final ItemCocoon cocoonWasp;
	public final ItemCocoon cocoonWolf;
	public final ItemCocoon cocoonZombie;
	public final ItemCocoon cocoonEnderman;
	public final ItemCocoon cocoonChicken;
	public final ItemCocoon cocoonVillager;
	public final ItemCocoon cocoonHorse;
	
	public final ItemEggSpawner eggAntBlack;
	public final ItemEggSpawner eggBee;
	public final ItemEggSpawner eggBeetle;
	public final ItemEggSpawner eggFly;
	public final ItemEggSpawner eggJack;
	public final ItemEggSpawner eggMandragora;
	public final ItemEggSpawner eggOctopus;
	public final ItemEggSpawner eggSpiderQueen;
	public final ItemEggSpawner eggWasp;
	public final ItemEggSpawner eggYuki;
	public final ItemEggSpawner eggFriendlyCreeper;
	public final ItemEggSpawner eggFriendlySkeleton;
	public final ItemEggSpawner eggFriendlyZombie;
	public final ItemEggSpawner eggFriendlyBee;
	public final ItemEggSpawner eggFriendlyMandragora;
	public final ItemEggSpawner eggHuman;
	
	public final ItemGhastEgg ghastEgg;
	public final ItemSpiderEgg spiderEgg;
	public final ItemWeb webNormal;
	public final ItemWeb webPoison;
	public final ItemRareFruit rareFruit;
	public final ItemOffering skull;
	public final ItemOffering heart;
	public final ItemOffering brain;
	public final ItemSpiderRod spiderRod;
	public final ItemRecallRod recallRod;
	public final ItemFreezeRod freezeRod;
	public final ItemWebslinger webslinger;
	public final ItemNectar nectar;
	public final ItemMandSeeds mandragoraSeeds;
	public final Item royalBlood;
	public final ItemLantern lantern;
	
	private ModItems()
	{
		instance = this;
		
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
		cocoonEnderman = new ItemCocoon(EnumCocoonType.ENDERMAN);
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
		eggFriendlyMandragora = new ItemEggSpawner(EntityFriendlyMandragora.class, "egg-f-mandragora");
		eggFriendlyBee = new ItemEggSpawner(EntityFriendlyBee.class, "egg-f-bee");
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

		royalBlood = new Item().setCreativeTab(SpiderCore.getCreativeTab()).setUnlocalizedName("royalblood").setMaxStackSize(1);
		GameRegistry.registerItem(royalBlood, "royalblood");
	}
	
	public static ModItems getInstance()
	{
		if (instance == null)
		{
			instance = new ModItems(); 
		}
		
		return instance;
	}

	@SideOnly(Side.CLIENT)
	public static void registerModelMeshers()
	{
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

		for (Field f : ModItems.class.getFields())
		{
			try
			{
				Item item = (Item) f.get(instance);
				String name = item.getUnlocalizedName().substring(5);
				mesher.register(item, 0, new ModelResourceLocation("sq:" + name, "inventory"));
			}

			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
