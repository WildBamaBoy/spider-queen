package sq.core;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Logger;

import radixcore.core.ModMetadataEx;
import radixcore.core.RadixCore;
import radixcore.data.AbstractPlayerData;
import radixcore.data.DataContainer;
import radixcore.update.RDXUpdateProtocol;
import sq.command.CommandSQ;
import sq.core.forge.EventHooksFML;
import sq.core.forge.EventHooksForge;
import sq.core.forge.GuiHandler;
import sq.core.forge.ServerProxy;
import sq.core.minecraft.ModAchievements;
import sq.core.minecraft.ModBlocks;
import sq.core.minecraft.ModItems;
import sq.core.radix.CrashWatcher;
import sq.core.radix.PlayerData;
import sq.core.radix.SpiderPacketHandler;
import sq.entity.EntityAnt;
import sq.entity.EntityBee;
import sq.entity.EntityBeetle;
import sq.entity.EntityFly;
import sq.entity.EntityJack;
import sq.entity.EntityMandragora;
import sq.entity.EntityOctopus;
import sq.entity.EntityWasp;
import sq.entity.EntityYuki;
import sq.enums.EnumCocoonType;
import sq.items.ItemCocoon;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = SQ.ID, name = SQ.NAME, version = SQ.VERSION, dependencies = "required-after:RadixCore@[2.0.0,)", acceptedMinecraftVersions = "[1.7.10]",
guiFactory = "sq.core.forge.client.SpiderGuiFactory")
public final class SQ
{
	public static final String ID = "SQ";
	public static final String NAME = "Spider Queen";
	public static final String VERSION = "1.0.0";

	@Instance(ID)
	private static SQ instance;
	private static ModMetadata metadata;
	private static ModItems items;
	private static ModBlocks blocks;
	private static ModAchievements achievements;
	private static CreativeTabs creativeTab;
	private static Config config;
	private static SpiderPacketHandler packetHandler;
	private static CrashWatcher crashWatcher;

	private static Logger logger;
	public static Random rand;
	
	@SidedProxy(clientSide = "sq.core.forge.ClientProxy", serverSide = "sq.core.forge.ServerProxy")
	public static ServerProxy proxy;

	public static Map<String, AbstractPlayerData> playerDataMap;

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

		ModMetadataEx exData = ModMetadataEx.getFromModMetadata(metadata);
		exData.updateProtocolClass = config.allowUpdateChecking ? RDXUpdateProtocol.class : null;
		exData.classContainingClientDataContainer = SQ.class;
		exData.classContainingGetPlayerDataMethod = SQ.class;
		exData.playerDataMap = playerDataMap;

		RadixCore.registerMod(exData);

		if (exData.updateProtocolClass == null)
		{
			logger.fatal("Config: Update checking is turned off. You will not be notified of any available updates for SQ.");
		}

		FMLCommonHandler.instance().bus().register(new EventHooksFML());
		MinecraftForge.EVENT_BUS.register(new EventHooksForge());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

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
		ModItems.cocoonEnderman = new ItemCocoon(EnumCocoonType.ENDERMAN);
		creativeTab = new CreativeTabs("tabSQ")
		{
			@Override
			public Item getTabIconItem()
			{
				return ModItems.cocoonEnderman;
			}
		};
		ModItems.cocoonEnderman.setCreativeTab(creativeTab);

		items = new ModItems();
		blocks = new ModBlocks();
		achievements = new ModAchievements();

		//Entity registry
		int id = config.baseEntityId;
		dualRegisterEntity(EntityAnt.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		dualRegisterEntity(EntityBee.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		dualRegisterEntity(EntityBeetle.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		dualRegisterEntity(EntityFly.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		dualRegisterEntity(EntityJack.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		dualRegisterEntity(EntityMandragora.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		dualRegisterEntity(EntityOctopus.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		dualRegisterEntity(EntityWasp.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		dualRegisterEntity(EntityYuki.class, id, 0xFFFFFF, 0xFFFFFF); id++;
		
		//Tile registry

		//Recipes
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.webNormal), Items.string, Items.string, Items.string);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.webPoison), Items.string, Items.string, Items.string, ModBlocks.stinger);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.webPoison), ModItems.webNormal, ModBlocks.stinger);
		GameRegistry.addRecipe(new ItemStack(ModItems.spiderRod), "GTG", " S ", " S ", 'G', Blocks.glass, 'T', Blocks.torch, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(ModItems.webslinger), "DS ", "SS ", "  S", 'D', ModBlocks.stinger, 'S', Items.string);
		
		//Smeltings
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandSQ());

		File playerDataPath = new File(AbstractPlayerData.getPlayerDataPath(event.getServer().getEntityWorld(), SQ.ID));
		playerDataPath.mkdirs();

		for (File f : playerDataPath.listFiles())
		{
			String uuid = f.getName().replace(".dat", "");
			PlayerData data = new PlayerData(uuid, event.getServer().getEntityWorld());
			data = data.readDataFromFile(null, PlayerData.class, f);

			SQ.playerDataMap.put(uuid, data);
		}
	}

	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
		for (AbstractPlayerData data : playerDataMap.values())
		{
			data.saveDataToFile();
		}

		SQ.playerDataMap.clear();
	}

	private void dualRegisterEntity(Class<? extends Entity> clazz, int id, int backgroundColor, int foregroundColor)
	{
		EntityRegistry.registerGlobalEntityID(clazz, clazz.getSimpleName(), id, backgroundColor, foregroundColor);
		EntityRegistry.registerModEntity(clazz, clazz.getSimpleName(), id, this, 50, 2, true);
	}
	
	public static SQ getInstance()
	{
		return instance;
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
}
