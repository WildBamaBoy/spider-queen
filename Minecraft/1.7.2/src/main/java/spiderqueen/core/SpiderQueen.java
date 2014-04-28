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
import net.minecraft.item.ItemStack;
import spiderqueen.blocks.BlockPoisonWeb;
import spiderqueen.blocks.BlockWeb;
import spiderqueen.command.CommandPlayerSkins;
import spiderqueen.core.forge.CommonProxy;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.entity.EntityWeb;
import spiderqueen.enums.EnumCocoonType;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.items.ItemCocoon;
import spiderqueen.items.ItemDebugSpawner;
import spiderqueen.items.ItemSpiderRod;
import spiderqueen.items.ItemWeb;
import spiderqueen.network.PacketCodec;
import spiderqueen.network.PacketHandler;

import com.radixshock.radixcore.core.IEnforcedCore;
import com.radixshock.radixcore.core.ModLogger;
import com.radixshock.radixcore.core.RadixCore;
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

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="spiderqueen", name="Spider Queen", version = Constants.VERSION, dependencies="required-after:radixcore")
public class SpiderQueen implements IEnforcedCore
{
	@Instance("spiderqueen")
	private static SpiderQueen instance;
	@SidedProxy(clientSide="spiderqueen.core.forge.ClientProxy", serverSide="spiderqueen.core.forge.CommonProxy")
	public static CommonProxy proxy;
	
	public static PacketPipeline packetPipeline;
	private static PacketCodec packetCodec;
	private static PacketHandler packetHandler;
	private ModLogger logger;
	
	public ModPropertiesManager modPropertiesManager;
	
	public CreativeTabs tabSpiderQueen;
	
	//Items
	public Item itemWeb;
	public Item itemPoisonWeb;
	public Item itemWebslinger;
	public Item itemSpiderRod;
	public Item itemSpiderEgg;
	public Item itemGhastEgg;
	public Item itemCocoon;
	public Item itemCocoonChicken;
	public Item itemCocoonPig;
	public Item itemCocoonSheep;
	public Item itemCocoonCreeper;
	public Item itemCocoonZombie;
	public Item itemCocoonSkeleton;
	public Item itemCocoonCow;
	public Item itemCocoonTestificate;
	public Item itemCocoonHorse;
	public Item itemCocoonEnderman;
	public Item itemCocoonHuman;
	public Item itemCocoonGathererBee;
	public Item itemCocoonWarriorBee;
	public Item itemCocoonQueenBee;
	public Item itemCocoonWasp;
	public Item itemCocoonAnt;
	public Item itemCocoonWolf;
	public Item itemNectar;
	public Item itemRareFruit;
	public Item itemHumanSkull;
	public Item itemDebugSpawner;
	
	//Blocks
	public Block blockWeb;
	public Block blockPoisonWeb;
	public Block blockWebBed;
	public Block blockBeehive;
	public Block blockAntHill;
	public Block blockSpiderBait;
	public Block blockMandragoraSeeds;
	public Block blockHumanHeart;
	public Block blockHumanBrain;
	public Block blockRoyalBlood;
	public Block blockJack;
	public Block blockLantern;
	public Block blockStinger;
	
	public List<String> fakePlayerNames = new ArrayList<String>();
	public boolean doDisplayPlayerSkins = true;
	
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
		return (ModPropertiesList)modPropertiesManager.modPropertiesInstance;
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent event) 
	{
		logger = new ModLogger(this);
		modPropertiesManager = new ModPropertiesManager(this, ModPropertiesList.class);
		fakePlayerNames = downloadFakePlayerNames();
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
		//Creative tab
		itemCocoonEnderman = new ItemCocoon(EnumCocoonType.ENDERMAN).setUnlocalizedName("cocoon.enderman");
		GameRegistry.registerItem(itemCocoonEnderman, itemCocoonEnderman.getUnlocalizedName());
		tabSpiderQueen = new CreativeTabs("tabSpiderQueen")
		{
			public Item getTabIconItem()
			{
				return itemCocoonEnderman;
			}
		};
		itemCocoonEnderman.setCreativeTab(tabSpiderQueen);
		
		itemCocoonChicken = new ItemCocoon(EnumCocoonType.CHICKEN).setUnlocalizedName("cocoon.chicken");
		itemCocoonCow = new ItemCocoon(EnumCocoonType.COW).setUnlocalizedName("cocoon.cow");
		itemCocoonCreeper = new ItemCocoon(EnumCocoonType.CREEPER).setUnlocalizedName("cocoon.creeper");
		itemCocoonHorse = new ItemCocoon(EnumCocoonType.HORSE).setUnlocalizedName("cocoon.horse");
		itemCocoonHuman = new ItemCocoon(EnumCocoonType.HUMAN).setUnlocalizedName("cocoon.human");
		itemCocoonPig = new ItemCocoon(EnumCocoonType.PIG).setUnlocalizedName("cocoon.pig");
		itemCocoonSheep = new ItemCocoon(EnumCocoonType.SHEEP).setUnlocalizedName("cocoon.sheep");
		itemCocoonSkeleton = new ItemCocoon(EnumCocoonType.SKELETON).setUnlocalizedName("cocoon.skeleton");
		itemCocoonTestificate = new ItemCocoon(EnumCocoonType.VILLAGER).setUnlocalizedName("cocoon.testificate");
		itemCocoonWolf = new ItemCocoon(EnumCocoonType.WOLF).setUnlocalizedName("cocoon.wolf");
		itemCocoonZombie = new ItemCocoon(EnumCocoonType.ZOMBIE).setUnlocalizedName("cocoon.zombie");
	
		itemWeb = new ItemWeb(false).setUnlocalizedName("web");
		itemPoisonWeb = new ItemWeb(true).setUnlocalizedName("webpoison");
		itemSpiderRod = new ItemSpiderRod().setUnlocalizedName("spiderrod");
		itemDebugSpawner = new ItemDebugSpawner();
		
		GameRegistry.registerItem(itemCocoonChicken, itemCocoonChicken.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonCow, itemCocoonCow.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonCreeper, itemCocoonCreeper.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonHorse, itemCocoonHorse.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonHuman, itemCocoonHuman.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonPig, itemCocoonPig.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonSheep, itemCocoonSheep.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonSkeleton, itemCocoonSkeleton.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonTestificate, itemCocoonTestificate.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonWolf, itemCocoonWolf.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonZombie, itemCocoonZombie.getUnlocalizedName());
		
		GameRegistry.registerItem(itemWeb, itemWeb.getUnlocalizedName());
		GameRegistry.registerItem(itemPoisonWeb, itemPoisonWeb.getUnlocalizedName());
		GameRegistry.registerItem(itemSpiderRod, itemSpiderRod.getUnlocalizedName());
		GameRegistry.registerItem(itemDebugSpawner, "spawner");
	}

	@Override
	public void initializeBlocks() 
	{
		blockWeb = new BlockWeb();
		blockPoisonWeb = new BlockPoisonWeb();
		
		GameRegistry.registerBlock(blockWeb, "Web");
		GameRegistry.registerBlock(blockPoisonWeb, "Poison Web");
	}

	@Override
	public void initializeRecipes() 
	{
		GameRegistry.addRecipe(new ItemStack(itemSpiderRod), 
				"GTG", " S ", " S ", 'G', Blocks.glass, 'S', Items.stick, 'T', Blocks.torch);
		GameRegistry.addRecipe(new ItemStack(itemSpiderRod), 
				"GTG", " S ", " S ", 'G', Blocks.glass_pane, 'S', Items.stick, 'T', Blocks.torch);
	}

	@Override
	public void initializeSmeltings()
	{
		
	}

	@Override
	public void initializeAchievements() 
	{
		
	}

	@Override
	public void initializeEntities() 
	{
		EntityRegistry.registerModEntity(EntityCocoon.class, EntityCocoon.class.getSimpleName(), 1, this, 50, 2, true);
		EntityRegistry.registerModEntity(EntityWeb.class, EntityWeb.class.getSimpleName(), 2, this, 50, 2, true);
		EntityRegistry.registerModEntity(EntityFakePlayer.class, EntityFakePlayer.class.getSimpleName(), 3, this, 50, 2, true);
		EntityRegistry.registerModEntity(EntityHatchedSpider.class, EntityHatchedSpider.class.getSimpleName(), 4, this, 50, 2, true);
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
		event.registerServerCommand(new CommandPlayerSkins());
	}

	@Override
	public String getShortModName() 
	{
		return "SpiderQueen";
	}

	@Override
	public String getLongModName() 
	{
		return "Spider Queen Remastered";
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
		return "{REDIR}"; //TODO
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
		return null;
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
	public ModPropertiesManager getModPropertiesManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getSetModPropertyCommandEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getGetModPropertyCommandEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getListModPropertiesCommandEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPropertyCommandPrefix() {
		// TODO Auto-generated method stub
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
		
		catch (Throwable e)
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
