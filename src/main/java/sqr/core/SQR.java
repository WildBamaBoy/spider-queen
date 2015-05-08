package sqr.core;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
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
import sqr.command.CommandSQR;
import sqr.core.forge.EventHooksFML;
import sqr.core.forge.EventHooksForge;
import sqr.core.forge.GuiHandler;
import sqr.core.forge.ServerProxy;
import sqr.core.minecraft.ModAchievements;
import sqr.core.minecraft.ModBlocks;
import sqr.core.minecraft.ModItems;
import sqr.core.radix.CrashWatcher;
import sqr.core.radix.PlayerData;
import sqr.core.radix.SQRPacketHandler;
import sqr.enums.EnumCocoonType;
import sqr.items.ItemCocoon;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = SQR.ID, name = SQR.NAME, version = SQR.VERSION, dependencies = "required-after:RadixCore@[2.0.0,)", acceptedMinecraftVersions = "[1.7.10]",
guiFactory = "sqr.core.forge.client.SQRGuiFactory")
public final class SQR
{
	public static final String ID = "sqr";
	public static final String NAME = "Spider Queen - Reborn";
	public static final String VERSION = "1.0.0";

	@Instance(ID)
	private static SQR instance;
	private static ModMetadata metadata;
	private static ModItems items;
	private static ModBlocks blocks;
	private static ModAchievements achievements;
	private static CreativeTabs creativeTab;
	private static Config config;
	private static SQRPacketHandler packetHandler;
	private static CrashWatcher crashWatcher;

	private static Logger logger;
	public static Random rand;
	
	@SidedProxy(clientSide = "sqr.core.forge.ClientProxy", serverSide = "sqr.core.forge.ServerProxy")
	public static ServerProxy proxy;

	public static Map<String, AbstractPlayerData> playerDataMap;

	@SideOnly(Side.CLIENT)
	public static DataContainer playerDataContainer;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{	
		instance = this;
		metadata = event.getModMetadata();
		logger = event.getModLog();
		config = new Config(event);
		crashWatcher = new CrashWatcher();
		packetHandler = new SQRPacketHandler(ID);
		proxy.registerRenderers();
		proxy.registerEventHandlers();
		playerDataMap = new HashMap<String, AbstractPlayerData>();

		ModMetadataEx exData = ModMetadataEx.getFromModMetadata(metadata);
		exData.updateProtocolClass = config.allowUpdateChecking ? RDXUpdateProtocol.class : null;
		exData.classContainingClientDataContainer = SQR.class;
		exData.classContainingGetPlayerDataMethod = SQR.class;
		exData.playerDataMap = playerDataMap;

		RadixCore.registerMod(exData);

		if (exData.updateProtocolClass == null)
		{
			logger.fatal("Config: Update checking is turned off. You will not be notified of any available updates for SQR.");
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
		creativeTab = new CreativeTabs("tabSQR")
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
		event.registerServerCommand(new CommandSQR());

		File playerDataPath = new File(AbstractPlayerData.getPlayerDataPath(event.getServer().getEntityWorld(), SQR.ID));
		playerDataPath.mkdirs();

		for (File f : playerDataPath.listFiles())
		{
			String uuid = f.getName().replace(".dat", "");
			PlayerData data = new PlayerData(uuid, event.getServer().getEntityWorld());
			data = data.readDataFromFile(null, PlayerData.class, f);

			SQR.playerDataMap.put(uuid, data);
		}
	}

	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
		for (AbstractPlayerData data : playerDataMap.values())
		{
			data.saveDataToFile();
		}

		SQR.playerDataMap.clear();
	}

	public static SQR getInstance()
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

	public static SQRPacketHandler getPacketHandler()
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
