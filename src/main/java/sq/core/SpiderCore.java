package sq.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import radixcore.core.ModMetadataEx;
import radixcore.core.RadixCore;
import radixcore.data.AbstractPlayerData;
import radixcore.data.BlockObj;
import radixcore.data.DataContainer;
import radixcore.lang.LanguageManager;
import radixcore.math.Point3D;
import radixcore.update.RDXUpdateProtocol;
import radixcore.util.RadixExcept;
import radixcore.util.RadixMath;
import radixcore.util.SchematicHandler;
import sq.command.CommandSQ;
import sq.core.forge.EventHooksFML;
import sq.core.forge.EventHooksForge;
import sq.core.forge.GuiHandler;
import sq.core.forge.ServerProxy;
import sq.core.minecraft.ModAchievements;
import sq.core.minecraft.ModBlocks;
import sq.core.minecraft.ModItems;
import sq.core.minecraft.Spawner;
import sq.core.radix.CrashWatcher;
import sq.core.radix.PlayerData;
import sq.core.radix.SpiderPacketHandler;
import sq.entity.ai.ReputationContainer;
import sq.entity.creature.EntityAnt;
import sq.entity.creature.EntityBee;
import sq.entity.creature.EntityBeetle;
import sq.entity.creature.EntityCocoon;
import sq.entity.creature.EntityFly;
import sq.entity.creature.EntityGhastEgg;
import sq.entity.creature.EntityHuman;
import sq.entity.creature.EntityJack;
import sq.entity.creature.EntityMandragora;
import sq.entity.creature.EntityMiniGhast;
import sq.entity.creature.EntityOctopus;
import sq.entity.creature.EntitySpiderEgg;
import sq.entity.creature.EntitySpiderEx;
import sq.entity.creature.EntitySpiderQueen;
import sq.entity.creature.EntityVines;
import sq.entity.creature.EntityWasp;
import sq.entity.creature.EntityWebShot;
import sq.entity.creature.EntityYuki;
import sq.entity.friendly.EntityFriendlyBee;
import sq.entity.friendly.EntityFriendlyCreeper;
import sq.entity.friendly.EntityFriendlyMandragora;
import sq.entity.friendly.EntityFriendlySkeleton;
import sq.entity.friendly.EntityFriendlyZombie;
import sq.entity.throwable.EntityBoomBall;
import sq.entity.throwable.EntityFreezeBall;
import sq.entity.throwable.EntityJackBall;
import sq.enums.EnumWatchedDataIDs;
import sq.gen.WorldGenAntHill;
import sq.gen.WorldGenBeeHive;
import sq.gen.WorldGenFactory;
import sq.util.SpawnEntry;

@Mod(modid = SpiderCore.ID, name = SpiderCore.NAME, version = SpiderCore.VERSION, dependencies = "required-after:RadixCore@[2.1.1,)", acceptedMinecraftVersions = "[1.8.9]",
guiFactory = "sq.core.forge.client.SpiderGuiFactory")
public final class SpiderCore
{
	public static final String ID = "SQ";
	public static final String NAME = "Spider Queen";
	public static final String VERSION = "@VERSION@";
	
	/** The URL for skins that are permanently in the contributor list. */
	public static final String PERM_SKINS_URL = "http://pastebin.com/raw.php?i=MNWrUxwa";
	/** The URL for periodic skins in the contributor list. */
	public static final String SKINS_URL = "http://pastebin.com/raw.php?i=L5S632xR";
	
	@Instance(ID)
	private static SpiderCore instance;
	private static ModMetadata metadata;
	private static ModItems items;
	private static ModBlocks blocks;
	private static ModAchievements achievements;
	private static CreativeTabs creativeTab;
	private static Config config;
	private static SpiderPacketHandler packetHandler;
	private static LanguageManager languageManager;
	private static CrashWatcher crashWatcher;

	private static Logger logger;
	public static Random rand;
	
	@SidedProxy(clientSide = "sq.core.forge.ClientProxy", serverSide = "sq.core.forge.ServerProxy")
	public static ServerProxy proxy;

	public static Map<String, AbstractPlayerData> playerDataMap;
	public static List<String> fakePlayerNames;
	public static List<String> sleepingPlayers;
	public static Map<Integer, Map<Point3D, BlockObj>> structureSchematics;
	
	@SideOnly(Side.CLIENT)
	public static DataContainer playerDataContainer;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
		rand = new Random();
		metadata = event.getModMetadata();
		logger = event.getModLog();
		config = new Config(event);
		crashWatcher = new CrashWatcher();
		packetHandler = new SpiderPacketHandler(ID);
		proxy.registerRenderers();
		proxy.registerEventHandlers();
		playerDataMap = new HashMap<String, AbstractPlayerData>();
		fakePlayerNames = downloadFakePlayerNames();
		sleepingPlayers = new ArrayList<String>();
		structureSchematics = new HashMap<Integer, Map<Point3D, BlockObj>>();
		languageManager = new LanguageManager(ID);
		
		//Fill in data used to enable functions within RadixCore.
		ModMetadataEx exData = ModMetadataEx.getFromModMetadata(metadata);
		exData.updateProtocolClass = config.allowUpdateChecking ? RDXUpdateProtocol.class : null;
		exData.classContainingClientDataContainer = SpiderCore.class;
		exData.classContainingGetPlayerDataMethod = SpiderCore.class;
		exData.url = "http://www.radix-shock.com/sq--download.html";
		exData.playerDataMap = playerDataMap;

		//Register with RadixCore.
		RadixCore.registerMod(exData);

		//If this is null (assigned above), there will be no update checking capabilities.
		if (exData.updateProtocolClass == null)
		{
			logger.fatal("Config: Update checking is turned off. You will not be notified of any available updates for SQ.");
		}

		//Register event hooks.
		FMLCommonHandler.instance().bus().register(new EventHooksFML());
		MinecraftForge.EVENT_BUS.register(new EventHooksForge());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		//Set all food as being always edible.
		for (Field f : Items.class.getDeclaredFields())
		{
			try
			{
				Object o = f.get(null);

				if (o instanceof ItemFood)
				{
					ItemFood food = (ItemFood)o;
					food.setAlwaysEdible();
					
					f.setAccessible(true);
					Field modifiers = Field.class.getDeclaredField("modifiers");
					modifiers.setAccessible(true);
					modifiers.setInt(f, f.getModifiers() & ~Modifier.FINAL);
					f.set(null, food);
					f.setAccessible(false);
				}
			}

			catch (Exception e)
			{
			}
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//Set up the creative tab.
		creativeTab = new CreativeTabs("tabSQ")
		{
			@Override
			public Item getTabIconItem()
			{
				return items == null ? null : items.cocoonEnderman;
			}
		};

		//Register classes from sq.core.minecraft.
		items = ModItems.getInstance();
		blocks = ModBlocks.getInstance();
		achievements = ModAchievements.getInstance();
		proxy.registerModelMeshers();
		
		//Register entities.
		int id = config.baseEntityId;
		EntityRegistry.registerModEntity(EntityAnt.class, EntityAnt.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityBee.class, EntityBee.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityBeetle.class, EntityBeetle.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityFly.class, EntityFly.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityJack.class, EntityJack.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityMandragora.class, EntityMandragora.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityOctopus.class, EntityOctopus.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityWasp.class, EntityWasp.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityYuki.class, EntityYuki.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityJackBall.class, EntityJackBall.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityVines.class, EntityVines.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityCocoon.class, EntityCocoon.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityWebShot.class, EntityWebShot.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntitySpiderEx.class, EntitySpiderEx.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityBoomBall.class, EntityBoomBall.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntitySpiderEgg.class, EntitySpiderEgg.class.getSimpleName(), id, this, 50, 2, true); id++;		
		EntityRegistry.registerModEntity(EntityFriendlyCreeper.class, EntityFriendlyCreeper.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityFriendlySkeleton.class, EntityFriendlySkeleton.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityFriendlyZombie.class, EntityFriendlyZombie.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntitySpiderQueen.class, EntitySpiderQueen.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityHuman.class, EntityHuman.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityFriendlyMandragora.class, EntityFriendlyMandragora.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityGhastEgg.class, EntityGhastEgg.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityMiniGhast.class, EntityMiniGhast.class.getSimpleName(), id, this, 50, 2, true); id++;
		EntityRegistry.registerModEntity(EntityFriendlyBee.class, EntityFriendlyBee.class.getSimpleName(), id, this, 50,  2, true); id++;
		EntityRegistry.registerModEntity(EntityFreezeBall.class, EntityFreezeBall.class.getSimpleName(), id, this, 50,  2, true); id++;

		//Register recipes.
		GameRegistry.addShapelessRecipe(new ItemStack(items.webNormal), Items.string, Items.string, Items.string);
		GameRegistry.addShapelessRecipe(new ItemStack(items.webPoison), Items.string, Items.string, Items.string, SpiderCore.getBlocks().stinger);
		GameRegistry.addShapelessRecipe(new ItemStack(items.webPoison), items.webNormal, SpiderCore.getBlocks().stinger);
		GameRegistry.addRecipe(new ItemStack(items.spiderRod), "GTG", " S ", " S ", 'G', Blocks.glass, 'T', Blocks.torch, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(items.recallRod), "GDG", " S ", " S ", 'G', Blocks.glass, 'D', Items.glowstone_dust, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(items.webslinger), "DS ", "SS ", "  S", 'D', SpiderCore.getBlocks().stinger, 'S', Items.string);
		
		//Add spawns.
		Spawner.registerSpawnEntry(new SpawnEntry(EntityBeetle.class, 70, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.extremeHills, BiomeGenBase.forest,
				BiomeGenBase.jungle, BiomeGenBase.taiga, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.forestHills, BiomeGenBase.roofedForest));
		Spawner.registerSpawnEntry(new SpawnEntry(EntityFly.class, 75, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.extremeHills, BiomeGenBase.forest,
				BiomeGenBase.jungle, BiomeGenBase.taiga, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.forestHills, BiomeGenBase.roofedForest));
		Spawner.registerSpawnEntry(new SpawnEntry(EntityMandragora.class, 50, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.extremeHills, BiomeGenBase.forest,
				BiomeGenBase.jungle, BiomeGenBase.taiga, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.forestHills, BiomeGenBase.roofedForest));
		Spawner.registerSpawnEntry(new SpawnEntry(EntityOctopus.class, 70, 1, 3, EnumCreatureType.WATER_CREATURE, BiomeGenBase.extremeHills, BiomeGenBase.forest,
				BiomeGenBase.jungle, BiomeGenBase.taiga, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.beach, BiomeGenBase.ocean, BiomeGenBase.deepOcean));
		Spawner.registerSpawnEntry(new SpawnEntry(EntityWasp.class, 45, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.extremeHills, BiomeGenBase.forest,
				BiomeGenBase.jungle, BiomeGenBase.taiga, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.forestHills, BiomeGenBase.roofedForest));
		Spawner.registerSpawnEntry(new SpawnEntry(EntityHuman.class, 30, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.extremeHills, BiomeGenBase.forest,
				BiomeGenBase.jungle, BiomeGenBase.taiga, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.forestHills, BiomeGenBase.roofedForest));
		Spawner.registerSpawnEntry(new SpawnEntry(EntitySpiderQueen.class, 15, 1, 2, EnumCreatureType.MONSTER, BiomeGenBase.extremeHills, BiomeGenBase.forest,
				BiomeGenBase.jungle, BiomeGenBase.taiga, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.forestHills, BiomeGenBase.roofedForest));
		Spawner.registerSpawnEntry(new SpawnEntry(EntityYuki.class, 10, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver,
				BiomeGenBase.iceMountains, BiomeGenBase.icePlains));
		
		//Register world generation.
		GameRegistry.registerWorldGenerator(new WorldGenAntHill(), 10);
		GameRegistry.registerWorldGenerator(new WorldGenBeeHive(), 14);
		GameRegistry.registerWorldGenerator(new WorldGenFactory(), 4096);
		
		//Set up reputations.
		ReputationContainer.createNew(EntitySkeleton.class, EnumWatchedDataIDs.SKELETON_LIKE.getId());
		ReputationContainer.createNew(EntityCreeper.class, EnumWatchedDataIDs.CREEPER_LIKE.getId());
		ReputationContainer.createNew(EntityZombie.class, EnumWatchedDataIDs.ZOMBIE_LIKE.getId());
		ReputationContainer.createNew(EntityHuman.class, EnumWatchedDataIDs.HUMAN_LIKE.getId());
		ReputationContainer.createNew(EntityBee.class, EnumWatchedDataIDs.BEE_LIKE.getId());
		
		//Load factories.
		long t1 = System.nanoTime();
		logger.info("Preloading schematics...");
		structureSchematics.put(0, SchematicHandler.readSchematic("/assets/sq/schematics/factorySawtooth44-fixed.schematic"));
		structureSchematics.put(1, SchematicHandler.readSchematic("/assets/sq/schematics/factoryEndergirl0-fixed.schematic"));
		structureSchematics.put(2, SchematicHandler.readSchematic("/assets/sq/schematics/factoryAllenWL-fixed.schematic"));
		structureSchematics.put(3, SchematicHandler.readSchematic("/assets/sq/schematics/factoryMario384-fixed.schematic"));
		structureSchematics.put(4, SchematicHandler.readSchematic("/assets/sq/schematics/lairFangdam1.schematic"));
		structureSchematics.put(5, SchematicHandler.readSchematic("/assets/sq/schematics/factoryDarktemplaar.schematic"));
		long t2 = System.nanoTime();
		
		long elapsedTime = TimeUnit.MILLISECONDS.convert(t2 - t1, TimeUnit.NANOSECONDS);
		logger.info("Schematic loading completed in " + elapsedTime + "ms.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		//Register the player's replacement model after all mods have loaded to try and guarantee
		//that it remains set to the spider queen model.
		proxy.registerSpiderQueenRenderer();
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		//Register commands.
		event.registerServerCommand(new CommandSQ());

		//Prepare the player data directory.
		File playerDataPath = new File(AbstractPlayerData.getPlayerDataPath(event.getServer().getEntityWorld(), SpiderCore.ID));
		playerDataPath.mkdirs();

		for (File f : playerDataPath.listFiles()) //Load up existing player data.
		{
			String uuid = f.getName().replace(".dat", "");
			PlayerData data = new PlayerData(uuid, event.getServer().getEntityWorld());
			data = data.readDataFromFile(null, PlayerData.class, f);

			SpiderCore.playerDataMap.put(uuid, data);
		}
	}

	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
		//Save and clear all player data when shutting down.
		for (AbstractPlayerData data : playerDataMap.values())
		{
			data.saveDataToFile();
		}

		SpiderCore.playerDataMap.clear();
	}
	
	public static SpiderCore getInstance()
	{
		return instance;
	}

	public static ModBlocks getBlocks()
	{
		return blocks;
	}
	
	public static ModItems getItems()
	{
		return items;
	}
	
	public static ModAchievements getAchievements()
	{
		return achievements;
	}
	
	public static Logger getLog()
	{
		return logger;
	}

	public static Config getConfig()
	{
		return config;
	}

	public static ModMetadata getMetadata()
	{
		return metadata;
	}

	public static CreativeTabs getCreativeTab()
	{
		return creativeTab;
	}

	public static SpiderPacketHandler getPacketHandler()
	{
		return packetHandler;
	}

	public static PlayerData getPlayerData(EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
			return (PlayerData) playerDataMap.get(player.getUniqueID().toString());
		}

		else
		{
			return playerDataContainer.getPlayerData(PlayerData.class);
		}
	}

	public static PlayerData getPlayerData(String uuid)
	{
		return (PlayerData) playerDataMap.get(uuid);
	}

	public static CrashWatcher getCrashWatcher() 
	{
		return crashWatcher;
	}
	
	public static LanguageManager getLanguageManager()
	{
		return languageManager;
	}
	
	public List<String> downloadFakePlayerNames()
	{
		logger.info("Downloading contributor/volunteered player names...");
		final List<String> returnList = new ArrayList<String>();

		try
		{
			readSkinsFromURL(PERM_SKINS_URL, returnList);
			readSkinsFromURL(SKINS_URL, returnList);
			
			logger.info("Contributor/volunteer player names downloaded successfully!");
		}

		catch (final Throwable e)
		{
			RadixExcept.logErrorCatch(e, "Unable to download player names.");
		}

		return returnList;
	}

	public static String getRandomPlayerName()
	{
		final int index = RadixMath.getNumberInRange(0, fakePlayerNames.size() - 1);
		return fakePlayerNames.get(index);
	}

	private void readSkinsFromURL(String stringUrl, List<String> returnList) throws IOException
	{
		URL url = new URL(stringUrl);
		Scanner scanner = new Scanner(url.openStream());

		while (scanner.hasNext())
		{
			returnList.add(scanner.next());
		}

		scanner.close();
	}
}
