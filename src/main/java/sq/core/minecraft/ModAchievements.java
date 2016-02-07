package sq.core.minecraft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import radixcore.util.RadixAchievement;
import sq.core.SpiderCore;

/**
 * All achievements in Spider Queen.
 */
public final class ModAchievements
{
	private static ModAchievements instance;
	
	public AchievementPage page;
	
	public Achievement goInTheDark;
	public Achievement eatToGetString;
	public Achievement craftWeb;
	public Achievement catchMob;
	public Achievement acquireEgg;
	public Achievement hatchSpider;
	public Achievement hatchSpiderByCocoon;
	public Achievement craftSpiderRod;
	public Achievement craftRecallRod;
	public Achievement craftSpiderBed;
	public Achievement craftWebslinger;
	public Achievement craftPoisonWeb;
	
	public Achievement makeFriend;
	public Achievement makeEnemy;
	public Achievement getFriendlyBee;
	public Achievement getFriendlyCreeper;
	public Achievement getFriendlyMandragora;
	public Achievement getFriendlySkeleton;
	public Achievement getFriendlyZombie;
	
	public Achievement hatchEnderSpider;
	public Achievement hatchBoomSpider;
	public Achievement hatchSlingerSpider;
	public Achievement hatchNovaSpider;
	public Achievement hatchTankSpider;
	public Achievement hatchPackSpider;
	public Achievement hatchRiderSpider;
	
	public static ModAchievements getInstance()
	{
		if (instance == null)
		{
			instance = new ModAchievements(); 
		}
		
		return instance;
	}
	
	private ModAchievements()
	{
		goInTheDark = RadixAchievement.register("goInTheDark", 1, 0, Blocks.torch, null);

		//Crafting tier
		craftSpiderRod = RadixAchievement.register("craftSpiderRod", 2, -1, SpiderCore.getItems().spiderRod, goInTheDark);
		craftRecallRod = RadixAchievement.register("craftRecallRod", 2, -3, SpiderCore.getItems().recallRod, craftSpiderRod);
		craftSpiderBed = RadixAchievement.register("craftSpiderBed", 4, -1, Blocks.log, goInTheDark);
		craftWebslinger = RadixAchievement.register("craftWebslinger", 6, -1, SpiderCore.getItems().webslinger, goInTheDark);
		craftPoisonWeb = RadixAchievement.register("craftPoisonWeb", 8, -1, SpiderCore.getItems().webPoison, goInTheDark);
		
		//Game guide tier
		eatToGetString = RadixAchievement.register("eatToGetString", 2, 1, Items.string, goInTheDark);
		craftWeb = RadixAchievement.register("craftWeb", 4, 1, SpiderCore.getItems().webNormal, eatToGetString);
		catchMob = RadixAchievement.register("catchMob", 6, 1, SpiderCore.getItems().cocoonCow, craftWeb);
		acquireEgg = RadixAchievement.register("acquireEgg", 8, 2, SpiderCore.getItems().spiderEgg, catchMob);
		hatchSpider = RadixAchievement.register("hatchSpider", 10, 1, SpiderCore.getItems().eggSpiderQueen, acquireEgg);
		hatchSpiderByCocoon = RadixAchievement.register("hatchSpiderByCocoon", 10, 3, Items.iron_sword, acquireEgg);
		
		//Spiders tier
		hatchEnderSpider = RadixAchievement.register("hatchEnderSpider", 0, -1, SpiderCore.getItems().cocoonEnderman, goInTheDark);
		hatchBoomSpider = RadixAchievement.register("hatchBoomSpider", -2, -1, SpiderCore.getItems().cocoonCreeper, goInTheDark);
		hatchSlingerSpider = RadixAchievement.register("hatchSlingerSpider", -4, -1, SpiderCore.getItems().cocoonSkeleton, goInTheDark);
		hatchNovaSpider = RadixAchievement.register("hatchNovaSpider", -6, -1, SpiderCore.getItems().cocoonWolf, goInTheDark);
		hatchTankSpider = RadixAchievement.register("hatchTankSpider", 0, -3, SpiderCore.getItems().cocoonZombie, goInTheDark);
		hatchPackSpider = RadixAchievement.register("hatchPackSpider", -2, -3, SpiderCore.getItems().cocoonVillager, goInTheDark);
		hatchRiderSpider = RadixAchievement.register("hatchRiderSpider", -4, -3, SpiderCore.getItems().cocoonHorse, goInTheDark);
		
		//Reputation tier
		makeEnemy = RadixAchievement.register("makeEnemy", 0, 1, SpiderCore.getItems().cocoonWarriorBee, goInTheDark);
		makeFriend = RadixAchievement.register("makeFriend", -1, 3, Blocks.yellow_flower, makeEnemy);
		getFriendlyBee = RadixAchievement.register("getFriendlyBee", -2, 2, SpiderCore.getItems().nectar, makeFriend);
		getFriendlyCreeper = RadixAchievement.register("getFriendlyCreeper", -4, 2, Items.gunpowder, makeFriend);
		getFriendlyMandragora = RadixAchievement.register("getFriendlyMandragora", -6, 2, SpiderCore.getItems().mandragoraSeeds, makeFriend);
		getFriendlySkeleton = RadixAchievement.register("getFriendlySkeleton", -2, 4, Items.bone, makeFriend);
		getFriendlyZombie = RadixAchievement.register("getFriendlyZombie", -4, 4, Blocks.log2, makeFriend);
		
		page = RadixAchievement.registerPage("Spider Queen", this.getClass());
	}
}
