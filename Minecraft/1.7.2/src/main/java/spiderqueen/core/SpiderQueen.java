package spiderqueen.core;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import spiderqueen.core.forge.CommonProxy;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.enums.EnumCocoonType;
import spiderqueen.items.ItemCocoon;

import com.radixshock.radixcore.core.IMod;
import com.radixshock.radixcore.core.ModLogger;
import com.radixshock.radixcore.core.RadixCore;
import com.radixshock.radixcore.file.ModPropertiesManager;
import com.radixshock.radixcore.lang.ILanguageLoaderHook;
import com.radixshock.radixcore.lang.ILanguageParser;
import com.radixshock.radixcore.lang.LanguageLoader;
import com.radixshock.radixcore.network.AbstractPacketCodec;
import com.radixshock.radixcore.network.AbstractPacketHandler;
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
public class SpiderQueen implements IMod
{
	@Instance("spiderqueen")
	private static SpiderQueen instance;
	@SidedProxy(clientSide="spiderqueen.core.forge.ClientProxy", serverSide="spiderqueen.core.forge.CommonProxy")
	public static CommonProxy proxy;
	
	private ModLogger logger;
	
	public ModPropertiesManager modPropertiesManager;
	
	public CreativeTabs tabSpiderQueen;
	
	//Items
	public Item itemWeb;
	public Item itemPoisonWeb;
	public Item itemWebslinger;
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
	
	//Blocks
	public Block blockSmallWeb;
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
		itemCocoonAnt = new ItemCocoon(EnumCocoonType.Ant).setUnlocalizedName("cocoon.ant");
		GameRegistry.registerItem(itemCocoonAnt, itemCocoonAnt.getUnlocalizedName());
		
		tabSpiderQueen = new CreativeTabs("tabSpiderQueen")
		{
			public Item getTabIconItem()
			{
				return itemCocoonAnt;
			}
		};
		
		itemCocoonAnt.setCreativeTab(tabSpiderQueen);
		
		//Other items
		itemCocoonChicken = new ItemCocoon(EnumCocoonType.Chicken).setUnlocalizedName("cocoon.chicken");
		itemCocoonCow = new ItemCocoon(EnumCocoonType.Cow).setUnlocalizedName("cocoon.cow");
		itemCocoonCreeper = new ItemCocoon(EnumCocoonType.Creeper).setUnlocalizedName("cocoon.creeper");
		itemCocoonGathererBee = new ItemCocoon(EnumCocoonType.GathererBee).setUnlocalizedName("cocoon.gathererbee");
		itemCocoonHuman = new ItemCocoon(EnumCocoonType.Human).setUnlocalizedName("cocoon.human");
		itemCocoonPig = new ItemCocoon(EnumCocoonType.Pig).setUnlocalizedName("cocoon.pig");
		itemCocoonQueenBee = new ItemCocoon(EnumCocoonType.QueenBee).setUnlocalizedName("cocoon.queenbee");
		itemCocoonSheep = new ItemCocoon(EnumCocoonType.Sheep).setUnlocalizedName("cocoon.sheep");
		itemCocoonSkeleton = new ItemCocoon(EnumCocoonType.Skeleton).setUnlocalizedName("cocoon.skeleton");
		itemCocoonWarriorBee = new ItemCocoon(EnumCocoonType.WarriorBee).setUnlocalizedName("cocoon.warriorbee");
		itemCocoonWasp = new ItemCocoon(EnumCocoonType.Wasp).setUnlocalizedName("cocoon.wasp");
		itemCocoonWolf = new ItemCocoon(EnumCocoonType.Wolf).setUnlocalizedName("cocoon.wolf");
		itemCocoonZombie = new ItemCocoon(EnumCocoonType.Zombie).setUnlocalizedName("cocoon.zombie");
		
		GameRegistry.registerItem(itemCocoonChicken, itemCocoonChicken.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonCow, itemCocoonCow.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonCreeper, itemCocoonCreeper.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonGathererBee, itemCocoonGathererBee.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonHuman, itemCocoonHuman.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonPig, itemCocoonPig.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonQueenBee, itemCocoonQueenBee.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonSheep, itemCocoonSheep.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonSkeleton, itemCocoonSkeleton.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonWarriorBee, itemCocoonWarriorBee.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonWasp, itemCocoonWasp.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonWolf, itemCocoonWolf.getUnlocalizedName());
		GameRegistry.registerItem(itemCocoonZombie, itemCocoonZombie.getUnlocalizedName());
	}

	@Override
	public void initializeBlocks() 
	{
		
	}

	@Override
	public void initializeRecipes() 
	{
		
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
	}

	@Override
	public void initializeNetwork() 
	{
		
	}

	@Override
	public String getShortModName() 
	{
		return "SQReborn";
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
		return "{REDIR}";
	}

	@Override
	public ModLogger getLogger() 
	{
		return logger;
	}

	@Override
	public AbstractPacketCodec getPacketCodec() 
	{
		return null;
	}

	@Override
	public AbstractPacketHandler getPacketHandler() 
	{
		return null;
	}

	@Override
	public PacketPipeline getPacketPipeline() 
	{
		return null;
	}

	@Override
	public Class getPacketTypeClass() 
	{
		return null;
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
}
