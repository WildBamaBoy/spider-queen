/*******************************************************************************
 * SpiderQueen.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import spiderqueen.blocks.BlockSpiderRod;
import spiderqueen.blocks.BlockWeb;
import spiderqueen.blocks.BlockWebBed;
import spiderqueen.blocks.BlockWebFull;
import spiderqueen.blocks.BlockWebGround;
import spiderqueen.command.CommandCheckReputation;
import spiderqueen.command.CommandDebug;
import spiderqueen.command.CommandPlayerSkins;
import spiderqueen.core.forge.ClientTickHandler;
import spiderqueen.core.forge.CommonProxy;
import spiderqueen.core.forge.EventHooks;
import spiderqueen.core.forge.GuiHandler;
import spiderqueen.core.forge.ServerTickHandler;
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
import spiderqueen.items.ItemSkinSwitcher;
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
	public Item						itemSkinSwitcher;

	public Item						itemBrain;
	public Item						itemSkull;
	public Item						itemHeart;
	public Item						itemPoisonBarbs;
	public Item						itemBugLight;
	public Item						itemSpiderStone;

	// Blocks
	public Block					blockWebFull;
	public Block					blockWebGround;
	public Block					blockWebSide;
	public Block					blockPoisonWebFull;
	public Block					blockPoisonWebGround;
	public Block					blockPoisonWebSide;
	public Block					blockWebBed;
	public Block					blockSpiderRod;

	// Achievements
	public AchievementPage			achievementPageSpiderQueen;
	public Achievement				achievementCraftWeb;
	public Achievement				achievementPickupFlameWeb;
	public Achievement				achievementNightVision;
	public Achievement				achievementCocoonSomething;
	public Achievement				achievementLayEgg;
	public Achievement				achievementFindSpiderStone;
	public Achievement				achievementCraftSpiderRod;
	public Achievement				achievementCraftBugLight;
	public Achievement				achievementHatchSpider;
	public Achievement				achievementHatchSkeletonSpider;
	public Achievement				achievementHatchZombieSpider;
	public Achievement				achievementHatchCreeperSpider;
	public Achievement				achievementHatchEndermanSpider;
	public Achievement				achievementHatchVillagerSpider;
	public Achievement				achievementHatchHorseSpider;
	public Achievement				achievementHatchWolfSpider;
	public Achievement				achievementWarWithCreepers;
	public Achievement				achievementWarWithSkeletons;
	public Achievement				achievementWarWithHumans;
	public Achievement				achievementWarWithOtherQueens;
	public Achievement				achievementWarWithZombies;
	public Achievement				achievementWarWithEndermen;
	public Achievement				achievementWarWithEvilQueen;
	public Achievement				achievementWarWithAll;
	public Achievement				achievementDefeatEvilQueen;
	public Achievement				achievementHatchBlazeSpider;
	public Achievement				achievementCocoonGhast;
	public Achievement				achievementHatchGhastSpider;
	public Achievement				achievementFriendsWithAny;
	public Achievement				achievementGiftBrain;
	public Achievement				achievementGiftSkull;
	public Achievement				achievementGiftHeart;
	public Achievement				achievementPeaceWithAll;
	public Achievement				achievementHelpZombies;
	public Achievement				achievementKillHumans;
	public Achievement				achievementKillSheWolfDeadly;
	public Achievement				achievementKillWildBamaBoy;
	public Achievement				achievementCraftPoisonBarbs;
	public Achievement				achievementCraftPoisonWeb;
	public Achievement				achievementEatSomething;
	public Achievement				achievementCraftWebslinger;
	public Achievement				achievementLevelUpSpider;
	public Achievement				achievementCreateSpiderBed;
	
	public List<String>				fakePlayerNames				= new ArrayList<String>();
	public boolean					doDisplayPlayerSkins		= true;
	public boolean					inDebugMode					= false;
	public boolean					debugDoRapidSpiderGrowth	= false;

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
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
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
		RadixRegistry.Items.registerItem(itemCocoonEnderman);
		tabSpiderQueen = RadixRegistry.Items.registerCreativeTab(this, itemCocoonEnderman);
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
		itemPoisonBarbs = new Item().setUnlocalizedName("poisonbarbs").setTextureName("spiderqueen:PoisonBarbs").setCreativeTab(tabSpiderQueen);
		itemBugLight = new Item().setUnlocalizedName("buglight").setTextureName("spiderqueen:BugLight").setCreativeTab(tabSpiderQueen);
		itemSpiderStone = new ItemSpiderStone().setUnlocalizedName("spiderstone").setCreativeTab(tabSpiderQueen);

		itemSpawnPlayer = new ItemSpawnPlayer().setUnlocalizedName("spawnplayer");
		itemSpawnSpider = new ItemSpawnSpider().setUnlocalizedName("spawnspider");
		itemSpawnEnemyQueen = new ItemSpawnEnemyQueen().setUnlocalizedName("spawnenemyqueen");
		itemSkinSwitcher = new ItemSkinSwitcher().setUnlocalizedName("skinswitcher").setCreativeTab(tabSpiderQueen);
		
		RadixRegistry.Items.registerItem(itemCocoonBlaze);
		RadixRegistry.Items.registerItem(itemCocoonChicken);
		RadixRegistry.Items.registerItem(itemCocoonCow);
		RadixRegistry.Items.registerItem(itemCocoonCreeper);
		RadixRegistry.Items.registerItem(itemCocoonGhast);
		RadixRegistry.Items.registerItem(itemCocoonHorse);
		RadixRegistry.Items.registerItem(itemCocoonHuman);
		RadixRegistry.Items.registerItem(itemCocoonPig);
		RadixRegistry.Items.registerItem(itemCocoonSheep);
		RadixRegistry.Items.registerItem(itemCocoonSkeleton);
		RadixRegistry.Items.registerItem(itemCocoonTestificate);
		RadixRegistry.Items.registerItem(itemCocoonWolf);
		RadixRegistry.Items.registerItem(itemCocoonZombie);

		RadixRegistry.Items.registerItem(itemWeb);
		RadixRegistry.Items.registerItem(itemPoisonWeb);
		RadixRegistry.Items.registerItem(itemFlameWeb);
		RadixRegistry.Items.registerItem(itemWebslinger);
		RadixRegistry.Items.registerItem(itemSpiderRod);
		RadixRegistry.Items.registerItem(itemSpiderEgg);
		RadixRegistry.Items.registerItem(itemBrain);
		RadixRegistry.Items.registerItem(itemSkull);
		RadixRegistry.Items.registerItem(itemHeart);
		RadixRegistry.Items.registerItem(itemPoisonBarbs);
		RadixRegistry.Items.registerItem(itemSpiderStone);
		RadixRegistry.Items.registerItem(itemBugLight);

		RadixRegistry.Items.registerItem(itemSpawnPlayer);
		RadixRegistry.Items.registerItem(itemSpawnSpider);
		RadixRegistry.Items.registerItem(itemSpawnEnemyQueen);
		RadixRegistry.Items.registerItem(itemSkinSwitcher);
	}

	@Override
	public void initializeBlocks()
	{
		blockWebGround = new BlockWebGround(false);
		blockWebSide = new BlockWeb(false);
		blockWebFull = new BlockWebFull(false);
		blockPoisonWebGround = new BlockWebGround(true);
		blockPoisonWebSide = new BlockWeb(true);
		blockPoisonWebFull = new BlockWebFull(true);
		blockSpiderRod = new BlockSpiderRod();
		blockWebBed = new BlockWebBed();
		
		GameRegistry.registerBlock(blockWebGround, "Web Ground");
		GameRegistry.registerBlock(blockWebSide, "Web Side");
		GameRegistry.registerBlock(blockWebFull, "Web Full");
		GameRegistry.registerBlock(blockPoisonWebGround, "Poison Web Ground");
		GameRegistry.registerBlock(blockPoisonWebSide, "Poison Web Side");
		GameRegistry.registerBlock(blockPoisonWebFull, "Poison Web Full");
		GameRegistry.registerBlock(blockSpiderRod, "Spider Rod");
		GameRegistry.registerBlock(blockWebBed, "Web Bed");
	}

	@Override
	public void initializeRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(itemSpiderRod), 
				"GXG", 
				" S ", 
				" S ", 
				'G', Blocks.glass_pane, 
				'S', Items.stick, 
				'X', itemSpiderStone);

		GameRegistry.addShapelessRecipe(new ItemStack(itemWeb),
				Items.string, Items.string, Items.string);

		GameRegistry.addRecipe(new ItemStack(itemWebslinger), 
				"TS ", 
				"SS ", 
				"  S", 
				'S', Items.string, 
				'T', itemWeb);

		GameRegistry.addRecipe(new ItemStack(itemBugLight), 
				"GGG", 
				"GXG", 
				"GGG", 
				'G', Blocks.glass_pane, 
				'X', itemSpiderStone);
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemPoisonWeb), 
				itemPoisonBarbs,
				itemWeb);
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemPoisonBarbs, 4), 
				new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a()));
	}

	@Override
	public void initializeSmeltings()
	{

	}

	@Override
	public void initializeAchievements()
	{
		// Initial point
		achievementCraftWeb = RadixRegistry.Achievements.createAchievement(this, "craftweb", 0, 0, itemWeb);

		// Crafting Tier
		achievementCraftPoisonBarbs = RadixRegistry.Achievements.createAchievement(this, "craftpoisonbarbs", 0, 3, itemPoisonBarbs, achievementCraftWeb);
		achievementCraftPoisonWeb = RadixRegistry.Achievements.createAchievement(this, "craftpoisonweb", -1, 4, itemPoisonWeb, achievementCraftPoisonBarbs);
		achievementFindSpiderStone = RadixRegistry.Achievements.createAchievement(this, "findspiderstone", -2, 0, itemSpiderStone, achievementCraftWeb).setSpecial();
		achievementCraftSpiderRod = RadixRegistry.Achievements.createAchievement(this, "craftspiderrod", -3, 1, itemSpiderRod, achievementFindSpiderStone);
		achievementCraftBugLight = RadixRegistry.Achievements.createAchievement(this, "craftbuglight", -3, -1, itemBugLight, achievementFindSpiderStone);
		achievementNightVision = RadixRegistry.Achievements.createAchievement(this, "nightvision", -5, -1, itemBugLight, achievementCraftBugLight).setSpecial();
		achievementCraftWebslinger = RadixRegistry.Achievements.createAchievement(this, "craftwebslinger", 2, 2, itemWebslinger, achievementCraftWeb).setSpecial();
		achievementCreateSpiderBed = RadixRegistry.Achievements.createAchievement(this, "createspiderbed", -2, 2, blockWebBed, achievementCraftWeb);
		
		// Walkthrough
		achievementCocoonSomething = RadixRegistry.Achievements.createAchievement(this, "cocoonsomething", 0, -2, itemCocoonCow, achievementCraftWeb);
		achievementEatSomething = RadixRegistry.Achievements.createAchievement(this, "eatsomething", 2, -2, Items.cooked_beef, achievementCocoonSomething);
		achievementLayEgg = RadixRegistry.Achievements.createAchievement(this, "layegg", 2, 0, itemSpiderEgg, achievementEatSomething);
		achievementHatchSpider = RadixRegistry.Achievements.createAchievement(this, "hatchspider", 6, 0, itemSpawnSpider, achievementLayEgg);
		achievementLevelUpSpider = RadixRegistry.Achievements.createAchievement(this, "levelupspider", 7, -2, itemSpiderRod, achievementHatchSpider).setSpecial();

		// Mob Teir
		achievementHatchSkeletonSpider = RadixRegistry.Achievements.createAchievement(this, "hatchskeletonspider", 3, -3, itemCocoonSkeleton, achievementHatchSpider);
		achievementHatchZombieSpider = RadixRegistry.Achievements.createAchievement(this, "hatchzombiespider", 4, -4, itemCocoonZombie, achievementHatchSpider);
		achievementHatchCreeperSpider = RadixRegistry.Achievements.createAchievement(this, "hatchcreeperspider", 5, -5, itemCocoonCreeper, achievementHatchSpider);
		achievementHatchEndermanSpider = RadixRegistry.Achievements.createAchievement(this, "hatchendermanspider", 6, -6, itemCocoonEnderman, achievementHatchSpider).setSpecial();
		achievementHatchVillagerSpider = RadixRegistry.Achievements.createAchievement(this, "hatchvillagerspider", 7, -5, itemCocoonTestificate, achievementHatchSpider);
		achievementHatchHorseSpider = RadixRegistry.Achievements.createAchievement(this, "hatchhorsespider", 8, -4, itemCocoonHorse, achievementHatchSpider);
		achievementHatchWolfSpider = RadixRegistry.Achievements.createAchievement(this, "hatchwolfspider", 9, -3, itemCocoonWolf, achievementHatchSpider);

		// War Tier
		achievementWarWithCreepers = RadixRegistry.Achievements.createAchievement(this, "warwithcreepers", 3, 3, Items.gunpowder, achievementHatchSpider);
		achievementWarWithSkeletons = RadixRegistry.Achievements.createAchievement(this, "warwithskeletons", 9, 3, Items.bone, achievementHatchSpider);
		achievementWarWithHumans = RadixRegistry.Achievements.createAchievement(this, "warwithhumans", 4, 4, Items.iron_helmet, achievementHatchSpider);
		achievementWarWithOtherQueens = RadixRegistry.Achievements.createAchievement(this, "warwithotherqueens", 8, 4, Items.string, achievementHatchSpider);
		achievementWarWithZombies = RadixRegistry.Achievements.createAchievement(this, "warwithzombies", 5, 5, Items.rotten_flesh, achievementHatchSpider);
		achievementWarWithEndermen = RadixRegistry.Achievements.createAchievement(this, "warwithendermen", 7, 5, Items.ender_pearl, achievementHatchSpider);
		achievementWarWithAll = RadixRegistry.Achievements.createAchievement(this, "warwithall", 6, 6, Items.diamond_sword, achievementHatchSpider).setSpecial();

		// Nether Tier
		achievementHatchBlazeSpider = RadixRegistry.Achievements.createAchievement(this, "hatchblazespider", 10, -1, itemCocoonBlaze, achievementHatchSpider);
		achievementPickupFlameWeb = RadixRegistry.Achievements.createAchievement(this, "pickupflameweb", 12, -3, itemFlameWeb, achievementHatchBlazeSpider);
		achievementCocoonGhast = RadixRegistry.Achievements.createAchievement(this, "cocoonghast", 14, -5, itemCocoonGhast, achievementPickupFlameWeb);
		achievementHatchGhastSpider = RadixRegistry.Achievements.createAchievement(this, "hatchghastspider", 14, -7, Items.nether_star, achievementCocoonGhast).setSpecial();

		// Evil Queen Tier
		achievementWarWithEvilQueen = RadixRegistry.Achievements.createAchievement(this, "warwithevilqueen", 10, 1, Items.spider_eye, achievementHatchSpider);
		achievementDefeatEvilQueen = RadixRegistry.Achievements.createAchievement(this, "defeatevilqueen", 12, 3, itemSkull, achievementWarWithEvilQueen).setSpecial();

		// Peace Tier
		achievementFriendsWithAny = RadixRegistry.Achievements.createAchievement(this, "friendswithany", 12, 0, Items.cookie, achievementHatchSpider);
		achievementGiftBrain = RadixRegistry.Achievements.createAchievement(this, "giftbrain", 14, 2, itemBrain, achievementFriendsWithAny);
		achievementGiftSkull = RadixRegistry.Achievements.createAchievement(this, "giftskull", 14, 0, itemSkull, achievementFriendsWithAny);
		achievementGiftHeart = RadixRegistry.Achievements.createAchievement(this, "giftheart", 14, -2, itemHeart, achievementFriendsWithAny);
		achievementPeaceWithAll = RadixRegistry.Achievements.createAchievement(this, "peacewithall", 16, 0, Items.diamond, achievementFriendsWithAny).setSpecial();

		// Misc Teir
		achievementHelpZombies = RadixRegistry.Achievements.createAchievement(this, "helpzombies", 1, -4, itemBrain, achievementCraftWeb);
		achievementKillHumans = RadixRegistry.Achievements.createAchievement(this, "killhumans", -1, -4, itemHeart, achievementCraftWeb);
		
		// this.achievementKillSheWolfDeadly =
		// RadixRegistry.Achievements.createAchievement(this,
		// "killshewolfdeadly", -3, -4, itemCocoonWolf,
		// achievementKillHumans).setSpecial();
		// this.achievementKillWildBamaBoy =
		// RadixRegistry.Achievements.createAchievement(this, "killwildbamaboy",
		// -1, -4, Items.diamond_helmet, achievementKillHumans).setSpecial();

		// Page
		achievementPageSpiderQueen = RadixRegistry.Achievements.createAchievementPage(this, "Spider Queen Reborn");
	}

	@Override
	public void initializeEntities()
	{
		RadixRegistry.Entities.registerModEntity(this, EntityCocoon.class);
		RadixRegistry.Entities.registerModEntity(this, EntityWeb.class);
		RadixRegistry.Entities.registerModEntity(this, EntityFakePlayer.class);
		RadixRegistry.Entities.registerModEntity(this, EntityHatchedSpider.class);
		RadixRegistry.Entities.registerModEntity(this, EntitySpiderEgg.class);
		RadixRegistry.Entities.registerModEntity(this, EntityOtherQueen.class);
		RadixRegistry.Entities.registerModEntity(this, EntityWebslinger.class);
		RadixRegistry.Entities.registerModEntity(this, EntityMiniGhast.class);
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
		return "http://radix-shock.com/update-page.html?userSpiderQueen=" + getVersion() + "&currentSpiderQueen=%" + "&userMC=" + Loader.instance().getMCVersionString().substring(10) + "&currentMC=%";
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
		return modPropertiesManager;
	}

	@Override
	public boolean getSetModPropertyCommandEnabled()
	{
		return true;
	}

	@Override
	public boolean getGetModPropertyCommandEnabled()
	{
		return false;
	}

	@Override
	public boolean getListModPropertiesCommandEnabled()
	{
		return true;
	}

	@Override
	public String getPropertyCommandPrefix()
	{
		return "sq.";
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
}
