package sq.core;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import radixcore.core.ModMetadataEx;
import radixcore.core.RadixCore;
import radixcore.update.RDXUpdateProtocol;
import sq.core.forge.ServerProxy;
import sq.core.forge.SpiderEvents;
import sq.core.forge.SpiderGuiHandler;
import sq.core.minecraft.SpiderAchievements;
import sq.core.minecraft.SpiderBlocks;
import sq.core.minecraft.SpiderItems;
import sq.core.minecraft.SpiderSounds;
import sq.core.radix.CrashWatcher;
import sq.core.radix.SpiderPacketHandler;
import sq.entities.EntityCocoon;

@Mod(modid = SpiderCore.ID, name = SpiderCore.NAME, version = SpiderCore.VERSION, dependencies = "required-after:RadixCore", acceptedMinecraftVersions = "[1.10.2]",
guiFactory = "sq.core.forge.client.SpiderGuiFactory")
public final class SpiderCore 
{
	public static final String ID = "spiderqueen";
	public static final String NAME = "Spider Queen";
	public static final String VERSION = "@VERSION@";

	@Instance(ID)
	private static SpiderCore instance;
	private static ModMetadata metadata;
	private static CreativeTabs creativeTab;
	private static Config config;
	private static SpiderPacketHandler packetHandler;
	private static CrashWatcher crashWatcher;
	
	private static Logger logger;

	@SidedProxy(clientSide = "sq.core.forge.ClientProxy", serverSide = "sq.core.forge.ServerProxy")
	public static ServerProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Initial setup
		instance = this;
		metadata = event.getModMetadata();
		logger = event.getModLog();
		config = new Config(event);
		crashWatcher = new CrashWatcher();
		packetHandler = new SpiderPacketHandler(ID);
		
		//Register with RadixCore
		ModMetadataEx exData = ModMetadataEx.getFromModMetadata(metadata);
		exData.updateProtocolClass = config.allowUpdateChecking ? RDXUpdateProtocol.class : null;
		RadixCore.registerMod(exData);
		
		//Register some items with Forge
		SpiderItems.registerItems();
		SpiderBlocks.registerBlocks();
		SpiderAchievements.registerAchievements();
		SpiderSounds.registerSounds();
		proxy.registerEventHandlers();
		proxy.registerEntityModels();
		
		//Alert if update checking is turned off.
		if (!config.allowUpdateChecking)
		{
			logger.fatal("Update checking is turned off. You will not be notified of any available updates for Spider Queen.");
			logger.fatal("Support can NOT be provided if you are running an old version of Spider Queen. You have been warned!");
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//Entities
		EntityRegistry.registerModEntity(EntityCocoon.class, EntityCocoon.class.getSimpleName(), 5897, this, 50, 2, true);
		
		MinecraftForge.EVENT_BUS.register(new SpiderEvents());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new SpiderGuiHandler());
	}
	
	public static CreativeTabs getCreativeTab()
	{
		return creativeTab;
	}
	
	public static void setCreativeTab(CreativeTabs tab)
	{
		creativeTab = tab;
	}
}
