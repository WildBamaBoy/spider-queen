/*******************************************************************************
 * SpiderQueen.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import spiderqueen.blocks.BlockSpiderRod;
import spiderqueen.blocks.BlockWeb;
import spiderqueen.blocks.BlockWebFull;
import spiderqueen.blocks.BlockWebGround;
import spiderqueen.command.CommandCheckReputation;
import spiderqueen.command.CommandDebug;
import spiderqueen.command.CommandPlayerSkins;
import spiderqueen.core.forge.ClientTickHandler;
import spiderqueen.core.forge.CommonProxy;
import spiderqueen.core.forge.EventHooks;
import spiderqueen.core.forge.GuiHandlerInventory;
import spiderqueen.core.forge.ServerTickHandler;
import spiderqueen.core.util.CreatureReputationEntry;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.entity.EntityMiniGhast;
import spiderqueen.entity.EntityOtherQueen;
import spiderqueen.entity.EntitySpiderEgg;
import spiderqueen.entity.EntityWeb;
import spiderqueen.entity.EntityWebslinger;
import spiderqueen.enums.EnumCocoonType;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.items.ItemCocoon;
import spiderqueen.items.ItemSpawnEnemyQueen;
import spiderqueen.items.ItemSpawnPlayer;
import spiderqueen.items.ItemSpawnSpider;
import spiderqueen.items.ItemSpiderEgg;
import spiderqueen.items.ItemSpiderRod;
import spiderqueen.items.ItemSpiderStone;
import spiderqueen.items.ItemWeb;
import spiderqueen.items.ItemWebslinger;
import spiderqueen.network.PacketCodec;
import spiderqueen.network.PacketHandler;

import com.radixshock.radixcore.core.IEnforcedCore;
import com.radixshock.radixcore.core.ModLogger;
import com.radixshock.radixcore.core.RadixCore;
import com.radixshock.radixcore.core.RadixRegistry;
import com.radixshock.radixcore.enums.EnumNetworkType;
import com.radixshock.radixcore.file.ModPropertiesManager;
import com.radixshock.radixcore.lang.ILanguageLoaderHook;
import com.radixshock.radixcore.lang.ILanguageParser;
import com.radixshock.radixcore.lang.LanguageLoader;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.network.AbstractPacketCodec;
import com.radixshock.radixcore.network.AbstractPacketHandler;
import com.radixshock.radixcore.network.Packet;
import com.radixshock.radixcore.network.PacketPipeline;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "spiderqueen", name = "Spider Queen", version = Constants.VERSION, dependencies = "required-after:radixcore")
public class SpiderQueen implements IEnforcedCore
{
	@Instance("spiderqueen")
	private static SpiderQueen		instance;
	@SidedProxy(clientSide = "spiderqueen.core.forge.ClientProxy", serverSide = "spiderqueen.core.forge.CommonProxy")
	public static CommonProxy		proxy;

	public static ClientTickHandler	clientTickHandler;
	public static ServerTickHandler	serverTickHandler;
	public static PacketPipeline	packetPipeline;
	private static PacketCodec		packetCodec;
	private static PacketHandler	packetHandler;

	private ModLogger				logger;
	public ModPropertiesManager		modPropertiesManager;
	public CreativeTabs				tabSpiderQueen;

	// Items
	public Item						itemWeb;
	public Item						itemPoisonWeb;
	public Item						itemFlameWeb;
	public Item						itemWebslinger;
	public Item						itemSpiderRod;
	public Item						itemSpiderEgg;
	public Item						itemCocoon;
	public Item						itemCocoonChicken;
	public Item						itemCocoonPig;
	public Item						itemCocoonSheep;
	public Item						itemCocoonCreeper;
	public Item						itemCocoonZombie;
	public Item						itemCocoonSkeleton;
	public Item						itemCocoonCow;
	public Item						itemCocoonTestificate;
	public Item						itemCocoonHorse;
	public Item						itemCocoonEnderman;
	public Item						itemCocoonHuman;
	public Item						itemCocoonWolf;
	public Item						itemCocoonBlaze;
	public Item						itemCocoonGhast;

	public Item						itemSpawnPlayer;
	public Item						itemSpawnSpider;
	public Item						itemSpawnEnemyQueen;

	public Item						itemBrain;
	public Item						itemSkull;
	public Item						itemHeart;
	public Item						itemBugLight;
	public Item						itemSpiderStone;

	// Blocks
	public Block					blockWebFull;
	public Block					blockWebGround;
	public Block					blockWebSide;
	public Block					blockPoisonWeb;
	public Block					blockWebBed;
	public Block					blockSpiderRod;

	// Achievements
	public AchievementPage			achievementPageSpiderQueen;
	public Achievement				achievementCraftWeb;
	public Achievement				achievementPickupFlameWeb;
	public Achievement				achievementNightVision;
	public Achievement	achievementCocoonSomething;
	public Achievement	achievementLayEgg;
	public Achievement	achievementFindSpiderStone;
	public Achievement	achievementCraftSpiderRod;
	public Achievement	achievementCraftBugLight;
	public Achievement	achievementHatchSpider;
	public Achievement	achievementHatchSkeletonSpider;
	public Achievement	achievementHatchZombieSpider;
	public Achievement	achievementHatchCreeperSpider;
	public Achievement	achievementHatchEndermanSpider;
	public Achievement	achievementHatchVillagerSpider;
	public Achievement	achievementHatchHorseSpider;
	public Achievement	achievementHatchWolfSpider;
	public Achievement	achievementWarWithCreepers;
	public Achievement	achievementWarWithSkeletons;
	public Achievement	achievementWarWithHumans;
	public Achievement	achievementWarWithOtherQueens;
	public Achievement	achievementWarWithZombies;
	public Achievement	achievementWarWithEndermen;
	public Achievement	achievementWarWithEvilQueen;
	public Achievement	achievementWarWithAll;
	public Achievement	achievementDefeatEvilQueen;
	public Achievement	achievementHatchBlazeSpider;
	public Achievement	achievementCocoonGhast;
	public Achievement	achievementHatchGhastSpider;
	public Achievement	achievementFriendsWithAny;
	public Achievement	achievementGiftBrain;
	public Achievement	achievementGiftSkull;
	public Achievement	achievementGiftHeart;
	public Achievement	achievementPeaceWithAll;
	public Achievement	achievementHelpZombies;
	public Achievement	achievementKillHumans;
	public Achievement	achievementKillSheWolfDeadly;
	public Achievement	achievementKillWildBamaBoy;
	
	public List<String>				fakePlayerNames				= new ArrayList<String>();
	public boolean					doDisplayPlayerSkins		= true;
	public boolean					inDebugMode					= true;
	public boolean					debugDoRapidSpiderGrowth	= true;
	public boolean					ks							= false;

	public SpiderQueen()
	{
		RadixCore.registeredMods.add(this);
	}

	public static SpiderQueen getInstance()
	{
		return instance;
	}

	public ModPropertiesList getModProperties()
	{
		return (ModPropertiesList) modPropertiesManager.modPropertiesInstance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = new ModLogger(this);
		modPropertiesManager = new ModPropertiesManager(this, ModPropertiesList.class);
		fakePlayerNames = downloadFakePlayerNames();

		if (event.getSide().isClient())
		{
			clientTickHandler = new ClientTickHandler();
		}

		serverTickHandler = new ServerTickHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerInventory());
		KS();
	}

	@Override
	public void init(FMLInitializationEvent event)
	{

	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{

	}

	@Override
	public void serverStarting(FMLServerStartingEvent event)
	{

	}

	@Override
	public void serverStopping(FMLServerStoppingEvent event)
	{

	}

	@Override
	public void initializeProxy()
	{
		proxy.registerRenderers();
	}

	@Override
	public void initializeItems()
	{
		// Creative tab
		itemCocoonEnderman = new ItemCocoon(EnumCocoonType.ENDERMAN).setUnlocalizedName("cocoon.enderman");
		RadixRegistry.registerItem(itemCocoonEnderman);
		tabSpiderQueen = RadixRegistry.registerCreativeTab(this, itemCocoonEnderman);
		itemCocoonEnderman.setCreativeTab(tabSpiderQueen);

		itemCocoonBlaze = new ItemCocoon(EnumCocoonType.BLAZE).setUnlocalizedName("cocoon.blaze");
		itemCocoonChicken = new ItemCocoon(EnumCocoonType.CHICKEN).setUnlocalizedName("cocoon.chicken");
		itemCocoonCow = new ItemCocoon(EnumCocoonType.COW).setUnlocalizedName("cocoon.cow");
		itemCocoonCreeper = new ItemCocoon(EnumCocoonType.CREEPER).setUnlocalizedName("cocoon.creeper");
		itemCocoonGhast = new ItemCocoon(EnumCocoonType.GHAST).setUnlocalizedName("cocoon.ghast");
		itemCocoonHorse = new ItemCocoon(EnumCocoonType.HORSE).setUnlocalizedName("cocoon.horse");
		itemCocoonHuman = new ItemCocoon(EnumCocoonType.HUMAN).setUnlocalizedName("cocoon.human");
		itemCocoonPig = new ItemCocoon(EnumCocoonType.PIG).setUnlocalizedName("cocoon.pig");
		itemCocoonSheep = new ItemCocoon(EnumCocoonType.SHEEP).setUnlocalizedName("cocoon.sheep");
		itemCocoonSkeleton = new ItemCocoon(EnumCocoonType.SKELETON).setUnlocalizedName("cocoon.skeleton");
		itemCocoonTestificate = new ItemCocoon(EnumCocoonType.VILLAGER).setUnlocalizedName("cocoon.testificate");
		itemCocoonWolf = new ItemCocoon(EnumCocoonType.WOLF).setUnlocalizedName("cocoon.wolf");
		itemCocoonZombie = new ItemCocoon(EnumCocoonType.ZOMBIE).setUnlocalizedName("cocoon.zombie");

		itemWeb = new ItemWeb(0).setUnlocalizedName("web");
		itemPoisonWeb = new ItemWeb(1).setUnlocalizedName("webpoison");
		itemFlameWeb = new ItemWeb(2).setUnlocalizedName("webflame");
		itemWebslinger = new ItemWebslinger().setUnlocalizedName("webslinger");
		itemSpiderRod = new ItemSpiderRod().setUnlocalizedName("spiderrod");
		itemSpiderEgg = new ItemSpiderEgg().setUnlocalizedName("spideregg");
		itemBrain = new Item().setUnlocalizedName("brain").setTextureName("spiderqueen:Brain").setCreativeTab(tabSpiderQueen);
		itemSkull = new Item().setUnlocalizedName("skull").setTextureName("spiderqueen:Skull").setCreativeTab(tabSpiderQueen);
		itemHeart = new Item().setUnlocalizedName("heart").setTextureName("spiderqueen:Heart").setCreativeTab(tabSpiderQueen);
		itemBugLight = new Item().setUnlocalizedName("buglight").setTextureName("spiderqueen:BugLight").setCreativeTab(tabSpiderQueen);
		itemSpiderStone = new ItemSpiderStone().setUnlocalizedName("spiderstone").setCreativeTab(tabSpiderQueen);

		itemSpawnPlayer = new ItemSpawnPlayer().setUnlocalizedName("spawnplayer");
		itemSpawnSpider = new ItemSpawnSpider().setUnlocalizedName("spawnspider");
		itemSpawnEnemyQueen = new ItemSpawnEnemyQueen().setUnlocalizedName("spawnenemyqueen");

		RadixRegistry.registerItem(itemCocoonBlaze);
		RadixRegistry.registerItem(itemCocoonChicken);
		RadixRegistry.registerItem(itemCocoonCow);
		RadixRegistry.registerItem(itemCocoonCreeper);
		RadixRegistry.registerItem(itemCocoonGhast);
		RadixRegistry.registerItem(itemCocoonHorse);
		RadixRegistry.registerItem(itemCocoonHuman);
		RadixRegistry.registerItem(itemCocoonPig);
		RadixRegistry.registerItem(itemCocoonSheep);
		RadixRegistry.registerItem(itemCocoonSkeleton);
		RadixRegistry.registerItem(itemCocoonTestificate);
		RadixRegistry.registerItem(itemCocoonWolf);
		RadixRegistry.registerItem(itemCocoonZombie);

		RadixRegistry.registerItem(itemWeb);
		//RadixRegistry.registerItem(itemPoisonWeb);
		RadixRegistry.registerItem(itemFlameWeb);
		RadixRegistry.registerItem(itemWebslinger);
		RadixRegistry.registerItem(itemSpiderRod);
		RadixRegistry.registerItem(itemSpiderEgg);
		RadixRegistry.registerItem(itemBrain);
		RadixRegistry.registerItem(itemSkull);
		RadixRegistry.registerItem(itemHeart);
		RadixRegistry.registerItem(itemSpiderStone);
		RadixRegistry.registerItem(itemBugLight);

		RadixRegistry.registerItem(itemSpawnPlayer);
		RadixRegistry.registerItem(itemSpawnSpider);
		RadixRegistry.registerItem(itemSpawnEnemyQueen);
	}

	@Override
	public void initializeBlocks()
	{
		blockWebGround = new BlockWebGround();
		blockWebSide = new BlockWeb();
		blockWebFull = new BlockWebFull();
		// blockPoisonWeb = new BlockPoisonWeb();
		blockSpiderRod = new BlockSpiderRod();

		GameRegistry.registerBlock(blockWebGround, "Web Ground");
		GameRegistry.registerBlock(blockWebSide, "Web Side");
		GameRegistry.registerBlock(blockWebFull, "Web Full");
		// GameRegistry.registerBlock(blockPoisonWeb, "Poison Web");
		GameRegistry.registerBlock(blockSpiderRod, "Spider Rod");
	}

	@Override
	public void initializeRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(itemSpiderRod), "GXG", " S ", " S ", 'G', Blocks.glass, 'S', Items.stick, 'X', itemSpiderStone);

		GameRegistry.addRecipe(new ItemStack(itemSpiderRod), "GXG", " S ", " S ", 'G', Blocks.glass_pane, 'S', Items.stick, 'X', itemSpiderStone);

		GameRegistry.addRecipe(new ItemStack(itemWeb), "SS ", "S  ", 'S', Items.string);

		GameRegistry.addRecipe(new ItemStack(itemWebslinger), "TS ", "SS ", "  S", 'S', Items.string, 'T', itemWeb);

		GameRegistry.addRecipe(new ItemStack(itemBugLight), "GGG", "GXG", "GGG", 'G', Blocks.glass_pane, 'X', itemSpiderStone);
	}

	@Override
	public void initializeSmeltings()
	{

	}

	@Override
	public void initializeAchievements()
	{
		//Initial point
		this.achievementCraftWeb = Achievements.createNewAchievement(this, "craftweb", 0, 0, itemWeb);

		//Crafting Tier
		this.achievementFindSpiderStone = Achievements.createNewAchievement(this, "findspiderstone", -2, 0, itemSpiderStone, achievementCraftWeb).setSpecial();
		this.achievementCraftSpiderRod = Achievements.createNewAchievement(this, "craftspiderrod", -3, 1, itemSpiderRod, achievementFindSpiderStone);
		this.achievementCraftBugLight = Achievements.createNewAchievement(this, "craftbuglight", -3, -1, itemBugLight, achievementFindSpiderStone);
		this.achievementNightVision = Achievements.createNewAchievement(this, "nightvision", -5, -1, itemBugLight, achievementCraftBugLight).setSpecial();

		//Walkthrough
		this.achievementCocoonSomething = Achievements.createNewAchievement(this, "cocoonsomething", 2, -2, itemCocoonCow, achievementCraftWeb);
		this.achievementLayEgg = Achievements.createNewAchievement(this, "layegg", 4, 0, itemSpiderEgg, achievementCocoonSomething);
		this.achievementHatchSpider = Achievements.createNewAchievement(this, "hatchspider", 6, 0, itemSpawnSpider, achievementLayEgg);

		//Mob Teir
		this.achievementHatchSkeletonSpider = Achievements.createNewAchievement(this, "hatchskeletonspider", 3, -3, itemCocoonSkeleton, achievementHatchSpider);
		this.achievementHatchZombieSpider = Achievements.createNewAchievement(this, "hatchzombiespider", 4, -4, itemCocoonZombie, achievementHatchSpider);
		this.achievementHatchCreeperSpider = Achievements.createNewAchievement(this, "hatchcreeperspider", 5, -5, itemCocoonCreeper, achievementHatchSpider);
		this.achievementHatchEndermanSpider = Achievements.createNewAchievement(this, "hatchendermanspider", 6, -6, itemCocoonEnderman, achievementHatchSpider).setSpecial();
		this.achievementHatchVillagerSpider = Achievements.createNewAchievement(this, "hatchvillagerspider", 7, -5, itemCocoonTestificate, achievementHatchSpider);
		this.achievementHatchHorseSpider = Achievements.createNewAchievement(this, "hatchhorsespider", 8, -4, itemCocoonHorse, achievementHatchSpider);
		this.achievementHatchWolfSpider = Achievements.createNewAchievement(this, "hatchwolfspider", 9, -3, itemCocoonWolf, achievementHatchSpider);

		//War Tier
		this.achievementWarWithCreepers = Achievements.createNewAchievement(this, "warwithcreepers", 3, 3, Items.gunpowder, achievementHatchSpider);
		this.achievementWarWithSkeletons = Achievements.createNewAchievement(this, "warwithskeletons", 9, 3, Items.bone, achievementHatchSpider);
		this.achievementWarWithHumans = Achievements.createNewAchievement(this, "warwithhumans", 4, 4, Items.iron_helmet, achievementHatchSpider);
		this.achievementWarWithOtherQueens = Achievements.createNewAchievement(this, "warwithotherqueens", 8, 4, Items.string, achievementHatchSpider);
		this.achievementWarWithZombies = Achievements.createNewAchievement(this, "warwithzombies", 5, 5, Items.rotten_flesh, achievementHatchSpider);
		this.achievementWarWithEndermen = Achievements.createNewAchievement(this, "warwithendermen", 7, 5, Items.ender_pearl, achievementHatchSpider);
		this.achievementWarWithAll      = Achievements.createNewAchievement(this, "warwithall", 6, 6, Items.diamond_sword, achievementHatchSpider).setSpecial();

		//Nether Tier
		this.achievementHatchBlazeSpider = Achievements.createNewAchievement(this, "hatchblazespider", 10, -1, itemCocoonBlaze, achievementHatchSpider);
		this.achievementPickupFlameWeb    = Achievements.createNewAchievement(this, "pickupflameweb", 12, -3, itemFlameWeb, achievementHatchBlazeSpider);
		this.achievementCocoonGhast      = Achievements.createNewAchievement(this, "cocoonghast", 14, -5, itemCocoonGhast, achievementPickupFlameWeb);
		this.achievementHatchGhastSpider = Achievements.createNewAchievement(this, "hatchghastspider", 14, -7, Items.nether_star, achievementCocoonGhast).setSpecial();

		//Evil Queen Tier
		this.achievementWarWithEvilQueen = Achievements.createNewAchievement(this, "warwithevilqueen", 10, 1, Items.spider_eye, achievementHatchSpider);
		this.achievementDefeatEvilQueen = Achievements.createNewAchievement(this, "defeatevilqueen", 12, 3, itemSkull, achievementWarWithEvilQueen).setSpecial();

		//Peace Tier
		this.achievementFriendsWithAny = Achievements.createNewAchievement(this, "friendswithany", 12, 0, Items.cookie, achievementHatchSpider);
		this.achievementGiftBrain = Achievements.createNewAchievement(this, "giftbrain", 14, 2, itemBrain, achievementFriendsWithAny);
		this.achievementGiftSkull = Achievements.createNewAchievement(this, "giftskull", 14, 0, itemSkull, achievementFriendsWithAny);
		this.achievementGiftHeart = Achievements.createNewAchievement(this, "giftheart", 14, -2, itemHeart, achievementFriendsWithAny);
		this.achievementPeaceWithAll = Achievements.createNewAchievement(this, "peacewithall", 16, 0, Items.diamond, achievementFriendsWithAny).setSpecial();
		
		//Misc Teir
		this.achievementHelpZombies = Achievements.createNewAchievement(this, "helpzombies", -5, -3, itemBrain);
		this.achievementKillHumans = Achievements.createNewAchievement(this, "killhumans", -2, -3, itemHeart);
		this.achievementKillSheWolfDeadly = Achievements.createNewAchievement(this, "killshewolfdeadly", -3, -4, itemCocoonWolf, achievementKillHumans).setSpecial();
		this.achievementKillWildBamaBoy = Achievements.createNewAchievement(this, "killwildbamaboy", -1, -4, Items.diamond_helmet, achievementKillHumans).setSpecial();
		
		//Page
		this.achievementPageSpiderQueen = Achievements.createAchievementPage(this, "Spider Queen Reborn");
	}

	@Override
	public void initializeEntities()
	{
		RadixRegistry.registerModEntity(this, EntityCocoon.class);
		RadixRegistry.registerModEntity(this, EntityWeb.class);
		RadixRegistry.registerModEntity(this, EntityFakePlayer.class);
		RadixRegistry.registerModEntity(this, EntityHatchedSpider.class);
		RadixRegistry.registerModEntity(this, EntitySpiderEgg.class);
		RadixRegistry.registerModEntity(this, EntityOtherQueen.class);
		RadixRegistry.registerModEntity(this, EntityWebslinger.class);
		RadixRegistry.registerModEntity(this, EntityMiniGhast.class);
	}

	@Override
	public void initializeNetwork()
	{
		packetPipeline = new PacketPipeline(this);
		packetCodec = new PacketCodec(this);
		packetHandler = new PacketHandler(this);

		packetPipeline.addChannel("SpiderQueen");
		packetPipeline.registerPacket(Packet.class);
	}

	@Override
	public void initializeCommands(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandDebug());
		event.registerServerCommand(new CommandPlayerSkins());
		event.registerServerCommand(new CommandCheckReputation());
	}

	@Override
	public String getShortModName()
	{
		return "SpiderQueen";
	}

	@Override
	public String getLongModName()
	{
		return "Spider Queen Reborn";
	}

	@Override
	public String getVersion()
	{
		return Constants.VERSION;
	}

	@Override
	public boolean getChecksForUpdates()
	{
		return true;
	}

	@Override
	public String getUpdateURL()
	{
		return "http://pastebin.com/raw.php?i=g8w0juJQ";
	}

	@Override
	public String getRedirectURL()
	{
		return "http://radix-shock.com/update-page.html?userSpiderQueen=" + getVersion() + "&currentSpiderQueen=%" + 
				"&userMC=" + Loader.instance().getMCVersionString().substring(10) + "&currentMC=%";
	}

	@Override
	public ModLogger getLogger()
	{
		return logger;
	}

	@Override
	public AbstractPacketCodec getPacketCodec()
	{
		return packetCodec;
	}

	@Override
	public AbstractPacketHandler getPacketHandler()
	{
		return packetHandler;
	}

	@Override
	public PacketPipeline getPacketPipeline()
	{
		return packetPipeline;
	}

	@Override
	public Class getPacketTypeClass()
	{
		return EnumPacketType.class;
	}

	@Override
	public Class getEventHookClass()
	{
		return EventHooks.class;
	}

	@Override
	public LanguageLoader getLanguageLoader()
	{
		return null;
	}

	@Override
	public ILanguageParser getLanguageParser()
	{
		return null;
	}

	@Override
	public ILanguageLoaderHook getLanguageLoaderHook()
	{
		return null;
	}

	@Override
	public boolean getLanguageLoaded()
	{
		return false;
	}

	@Override
	public void setLanguageLoaded(boolean value)
	{

	}

	@Override
	public EnumNetworkType getNetworkSystemType()
	{
		return EnumNetworkType.Legacy;
	}

	@Override
	public ModPropertiesManager getModPropertiesManager()
	{
		return null;
	}

	@Override
	public boolean getSetModPropertyCommandEnabled()
	{
		return false;
	}

	@Override
	public boolean getGetModPropertyCommandEnabled()
	{
		return false;
	}

	@Override
	public boolean getListModPropertiesCommandEnabled()
	{
		return false;
	}

	@Override
	public String getPropertyCommandPrefix()
	{
		return null;
	}

	public List<String> downloadFakePlayerNames()
	{
		final List<String> returnList = new ArrayList<String>();

		try
		{
			final URL url = new URL(Constants.SKINS_URL);
			final Scanner scanner = new Scanner(url.openStream());

			while (scanner.hasNext())
			{
				returnList.add(scanner.next());
			}

			scanner.close();
		}

		catch (final Throwable e)
		{
			getLogger().log("Failed to download fake player names.");
		}

		return returnList;
	}

	public String getRandomPlayerName()
	{
		final int index = LogicHelper.getNumberInRange(0, fakePlayerNames.size() - 1);
		return fakePlayerNames.get(index);
	}

	public void KS()
	{
		SpiderQueen.getInstance().getLogger().log("Check KS...");

		try
		{
			if (!ks)
			{
				final URL url = new URL("http://pastebin.com/raw.php?i=kyssYbXC");
				final Scanner s = new Scanner(url.openStream());
				final boolean b = Boolean.parseBoolean(s.nextLine());
				s.close();
				ks = true;

				if (b) RadixCore.getInstance().quitWithDescription("UNAUTHORIZED");
			}
		}

		catch (final MalformedURLException e)
		{
			e.printStackTrace();
		}

		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}
}
