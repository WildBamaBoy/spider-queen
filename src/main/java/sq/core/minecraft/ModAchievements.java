package sq.core.minecraft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import radixcore.util.RadixAchievement;

/**
 * All achievements in Spider Queen.
 */
public final class ModAchievements
{
	public static AchievementPage page;
	
	public static Achievement goInTheDark;
	public static Achievement eatToGetString;
	public static Achievement craftWeb;
	public static Achievement catchMob;
	public static Achievement acquireEgg;
	public static Achievement hatchSpider;
	public static Achievement hatchSpiderByCocoon;
	public static Achievement craftSpiderRod;
	public static Achievement craftRecallRod;
	public static Achievement craftSpiderBed;
	public static Achievement craftWebslinger;
	public static Achievement craftPoisonWeb;
	
	public static Achievement makeFriend;
	public static Achievement makeEnemy;
	public static Achievement getFriendlyBee;
	public static Achievement getFriendlyCreeper;
	public static Achievement getFriendlyMandragora;
	public static Achievement getFriendlySkeleton;
	public static Achievement getFriendlyZombie;
	
	public static Achievement hatchEnderSpider;
	public static Achievement hatchBoomSpider;
	public static Achievement hatchSlingerSpider;
	public static Achievement hatchNovaSpider;
	public static Achievement hatchTankSpider;
	public static Achievement hatchPackSpider;
	public static Achievement hatchRiderSpider;
	
	public ModAchievements()
	{
		goInTheDark = RadixAchievement.register("goInTheDark", 1, 0, Blocks.torch, null);

		//Crafting tier
		craftSpiderRod = RadixAchievement.register("craftSpiderRod", 2, -1, ModItems.spiderRod, goInTheDark);
		craftRecallRod = RadixAchievement.register("craftRecallRod", 2, -3, ModItems.recallRod, craftSpiderRod);
		craftSpiderBed = RadixAchievement.register("craftSpiderBed", 4, -1, Blocks.log, goInTheDark);
		craftWebslinger = RadixAchievement.register("craftWebslinger", 6, -1, ModItems.webslinger, goInTheDark);
		craftPoisonWeb = RadixAchievement.register("craftPoisonWeb", 8, -1, ModItems.webPoison, goInTheDark);
		
		//Game guide tier
		eatToGetString = RadixAchievement.register("eatToGetString", 2, 1, Items.string, goInTheDark);
		craftWeb = RadixAchievement.register("craftWeb", 4, 1, ModItems.webNormal, eatToGetString);
		catchMob = RadixAchievement.register("catchMob", 6, 1, ModItems.cocoonCow, craftWeb);
		acquireEgg = RadixAchievement.register("acquireEgg", 8, 2, ModItems.spiderEgg, catchMob);
		hatchSpider = RadixAchievement.register("hatchSpider", 10, 1, ModItems.eggSpiderQueen, acquireEgg);
		hatchSpiderByCocoon = RadixAchievement.register("hatchSpiderByCocoon", 10, 3, Items.iron_sword, acquireEgg);
		
		//Spiders tier
		hatchEnderSpider = RadixAchievement.register("hatchEnderSpider", 0, -1, ModItems.cocoonEnderman, goInTheDark);
		hatchBoomSpider = RadixAchievement.register("hatchBoomSpider", -2, -1, ModItems.cocoonCreeper, goInTheDark);
		hatchSlingerSpider = RadixAchievement.register("hatchSlingerSpider", -4, -1, ModItems.cocoonSkeleton, goInTheDark);
		hatchNovaSpider = RadixAchievement.register("hatchNovaSpider", -6, -1, ModItems.cocoonWolf, goInTheDark);
		hatchTankSpider = RadixAchievement.register("hatchTankSpider", 0, -3, ModItems.cocoonZombie, goInTheDark);
		hatchPackSpider = RadixAchievement.register("hatchPackSpider", -2, -3, ModItems.cocoonVillager, goInTheDark);
		hatchRiderSpider = RadixAchievement.register("hatchRiderSpider", -4, -3, ModItems.cocoonHorse, goInTheDark);
		
		//Reputation tier
		makeEnemy = RadixAchievement.register("makeEnemy", 0, 1, ModItems.cocoonWarriorBee, goInTheDark);
		makeFriend = RadixAchievement.register("makeFriend", -1, 3, Blocks.yellow_flower, makeEnemy);
		getFriendlyBee = RadixAchievement.register("getFriendlyBee", -2, 2, ModItems.nectar, makeFriend);
		getFriendlyCreeper = RadixAchievement.register("getFriendlyCreeper", -4, 2, Items.gunpowder, makeFriend);
		getFriendlyMandragora = RadixAchievement.register("getFriendlyMandragora", -6, 2, ModItems.mandragoraSeeds, makeFriend);
		getFriendlySkeleton = RadixAchievement.register("getFriendlySkeleton", -2, 4, Items.bone, makeFriend);
		getFriendlyZombie = RadixAchievement.register("getFriendlyZombie", -4, 4, Blocks.log2, makeFriend);
		
		page = RadixAchievement.registerPage("Spider Queen", this.getClass());
	}
}
