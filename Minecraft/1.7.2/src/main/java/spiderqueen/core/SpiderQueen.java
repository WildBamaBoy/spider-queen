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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import spiderqueen.blocks.BlockPoisonWeb;
import spiderqueen.blocks.BlockSpiderRod;
import spiderqueen.blocks.BlockWeb;
import spiderqueen.command.CommandCheckReputation;
import spiderqueen.command.CommandDebug;
import spiderqueen.command.CommandPlayerSkins;
import spiderqueen.core.forge.CommonProxy;
import spiderqueen.core.forge.EventHooks;
import spiderqueen.core.forge.GuiHandlerInventory;
import spiderqueen.core.forge.ServerTickHandler;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityEnemyQueen;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.entity.EntitySpiderEgg;
import spiderqueen.entity.EntityWeb;
import spiderqueen.enums.EnumCocoonType;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.items.ItemCocoon;
import spiderqueen.items.ItemSpawnEnemyQueen;
import spiderqueen.items.ItemSpawnPlayer;
import spiderqueen.items.ItemSpawnSpider;
import spiderqueen.items.ItemSpiderEgg;
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
import com.radixshock.radixcore.logic.Point3D;
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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="spiderqueen", name="Spider Queen", version = Constants.VERSION, dependencies="required-after:radixcore")
public class SpiderQueen implements IEnforcedCore
{
	@Instance("spiderqueen")
	private static SpiderQueen instance;
	@SidedProxy(clientSide="spiderqueen.core.forge.ClientProxy", serverSide="spiderqueen.core.forge.CommonProxy")
	public static CommonProxy proxy;

	public static ServerTickHandler serverTickHandler;
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
	public Item itemCocoonWolf;

	public Item itemSpawnPlayer;
	public Item itemSpawnSpider;
	public Item itemSpawnEnemyQueen;

	public Item itemBrain;
	public Item itemSkull;
	public Item itemHeart;

	//Blocks
	public Block blockWeb;
	public Block blockPoisonWeb;
	public Block blockWebBed;
	public Block blockSpiderRod;

	public List<String> fakePlayerNames = new ArrayList<String>();
	public boolean doDisplayPlayerSkins = true;
	public boolean inDebugMode = true;
	public boolean debugDoRapidSpiderGrowth = true;

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

		serverTickHandler = new ServerTickHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerInventory());
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
		itemSpiderEgg = new ItemSpiderEgg().setUnlocalizedName("spideregg");
		itemBrain = new Item().setUnlocalizedName("brain").setTextureName("spiderqueen:Brain").setCreativeTab(tabSpiderQueen);
		itemSkull = new Item().setUnlocalizedName("skull").setTextureName("spiderqueen:Skull").setCreativeTab(tabSpiderQueen);
		itemHeart = new Item().setUnlocalizedName("heart").setTextureName("spiderqueen:Heart").setCreativeTab(tabSpiderQueen);

		itemSpawnPlayer = new ItemSpawnPlayer().setUnlocalizedName("spawnplayer");
		itemSpawnSpider = new ItemSpawnSpider().setUnlocalizedName("spawnspider");
		itemSpawnEnemyQueen = new ItemSpawnEnemyQueen().setUnlocalizedName("spawnenemyqueen");

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
		GameRegistry.registerItem(itemSpiderEgg, itemSpiderEgg.getUnlocalizedName());
		GameRegistry.registerItem(itemBrain, itemBrain.getUnlocalizedName());
		GameRegistry.registerItem(itemSkull, itemSkull.getUnlocalizedName());
		GameRegistry.registerItem(itemHeart, itemHeart.getUnlocalizedName());

		GameRegistry.registerItem(itemSpawnPlayer, itemSpawnPlayer.getUnlocalizedName());
		GameRegistry.registerItem(itemSpawnSpider, itemSpawnSpider.getUnlocalizedName());
		GameRegistry.registerItem(itemSpawnEnemyQueen, itemSpawnEnemyQueen.getUnlocalizedName());
	}

	@Override
	public void initializeBlocks() 
	{
		blockWeb = new BlockWeb();
		blockPoisonWeb = new BlockPoisonWeb();
		blockSpiderRod = new BlockSpiderRod();

		GameRegistry.registerBlock(blockWeb, "Web");
		GameRegistry.registerBlock(blockPoisonWeb, "Poison Web");
		GameRegistry.registerBlock(blockSpiderRod, "Spider Rod");
	}

	@Override
	public void initializeRecipes() 
	{
		GameRegistry.addRecipe(new ItemStack(itemSpiderRod), 
				"GTG", " S ", " S ", 'G', Blocks.glass, 'S', Items.stick, 'T', Blocks.torch);
		GameRegistry.addRecipe(new ItemStack(itemSpiderRod), 
				"GTG", " S ", " S ", 'G', Blocks.glass_pane, 'S', Items.stick, 'T', Blocks.torch);
		GameRegistry.addRecipe(new ItemStack(itemWeb),
				"SS ", "S  ", 'S', Items.string);
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
		EntityRegistry.registerModEntity(EntitySpiderEgg.class, EntitySpiderEgg.class.getSimpleName(), 5, this, 50, 2, true);
		EntityRegistry.registerModEntity(EntityEnemyQueen.class, EntityEnemyQueen.class.getSimpleName(), 6, this, 50, 2, true);
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

	//TODO Candidate for inclusion in RadixCore
	public static void spawnGroupOfEntitiesAtPlayer(EntityPlayer player, Class entityClass) 
	{
		try
		{
			final int amountToSpawn = LogicHelper.getNumberInRange(2, 5);

			for (int i = 0; i < amountToSpawn; i++)
			{
				final EntityLivingBase entity = (EntityLivingBase) entityClass.getDeclaredConstructor(World.class).newInstance(player.worldObj);
				final Point3D spawnPoint = LogicHelper.getRandomNearbyBlockCoordinatesOfType(player, Blocks.air);

				entity.setPosition(spawnPoint.posX, spawnPoint.posY, spawnPoint.posZ);
				player.worldObj.spawnEntityInWorld(entity);
			}
		}

		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	//TODO Candidate for inclusion in RadixCore
	public static void spawnGroupOfEntitiesAtPoint(World world, Point3D point, Class entityClass) 
	{
		try
		{
			final int amountToSpawn = LogicHelper.getNumberInRange(1, 5);

			for (int i = 0; i < amountToSpawn; i++)
			{
				final EntityLivingBase entity = (EntityLivingBase) entityClass.getDeclaredConstructor(World.class).newInstance(world);
				final Point3D spawnPoint = getRandomNearbyBlockCoordinatesOfType(point, world, Blocks.air);

				if (spawnPoint != null)
				{
					int blocksUntilGround = 0;

					while (world.isAirBlock((int)point.posX, (int)point.posY + blocksUntilGround, (int)point.posZ) && blocksUntilGround != 255)
					{
						blocksUntilGround--;
					}

					entity.setPosition(spawnPoint.posX, spawnPoint.posY + blocksUntilGround + 1, spawnPoint.posZ);
					world.spawnEntityInWorld(entity);
				}
			}
		}

		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Gets the coordinates of a random block of the specified type within 10 blocks away from the provided entity.
	 * 
	 * @param 	point	The entity being used as a base point to start the search.
	 * @param 	block	The block being searched for.
	 * 
	 * @return	An coordinates object containing the coordinates of the randomly selected block.
	 */
	//TODO Candidate for inclusion in RadixCore
	public static Point3D getRandomNearbyBlockCoordinatesOfType(Point3D point, World world, Block block)
	{
		//Create a list to store valid coordinates and specify the maximum distance away.
		List<Point3D> validCoordinatesList = new LinkedList<Point3D>();
		int maxDistanceAway = 10;

		//Assign entity's position.
		int x = (int)point.posX;
		int y = (int)point.posY;
		int z = (int)point.posZ;

		//Assign x, y, and z movement.
		int xMov = 0 - maxDistanceAway;
		int yMov = -3;
		int zMov = 0 - maxDistanceAway;

		while (true)
		{
			//If the block ID at the following coordinates matches the block ID being searched for...
			if (world.getBlock(x + xMov, y + yMov, z + zMov) == block)
			{
				//Add the block's coordinates to the coordinates list.
				validCoordinatesList.add(new Point3D(x + xMov, y + yMov, z + zMov));
			}

			//If z and x movement has reached the maximum distance and y movement has reached 2, then return the list as searching has completed.
			if (zMov == maxDistanceAway && xMov == maxDistanceAway && yMov == 2)
			{
				if (!validCoordinatesList.isEmpty())
				{
					return validCoordinatesList.get(world.rand.nextInt(validCoordinatesList.size()));
				}

				else
				{
					return null;
				}
			}

			//But if y movement isn't 2 then searching should continue.
			else if (zMov == maxDistanceAway && xMov == maxDistanceAway)
			{
				//Increase y movement by 1 and reset x and z movement, bringing the search up another level.
				yMov++;
				xMov = 0 - maxDistanceAway;
				zMov = 0 - maxDistanceAway;
			}

			//If x movement has reached the maximum distance...
			if (xMov == maxDistanceAway)
			{
				//Increase z movement by one and reset x movement, restarting the loop.
				zMov++;
				xMov = 0 - maxDistanceAway;
				continue;
			}

			xMov++;
		}
	}

	//TODO Candidate for inclusion in RadixCore
	public static void spawnEntityAtPlayer(EntityPlayer player, Class entityClass) 
	{
		try
		{
			final EntityLivingBase entity = (EntityLivingBase) entityClass.getDeclaredConstructor(World.class).newInstance(player.worldObj);
			final Point3D spawnPoint = LogicHelper.getRandomNearbyBlockCoordinatesOfType(player, Blocks.air);

			entity.setPosition(spawnPoint.posX, spawnPoint.posY, spawnPoint.posZ);
			player.worldObj.spawnEntityInWorld(entity);
		}

		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	public static boolean isValidSpawnBiome(BiomeGenBase biome)
	{
		return 	biome == BiomeGenBase.desert || biome == BiomeGenBase.birchForest || biome == BiomeGenBase.coldTaiga || biome == BiomeGenBase.extremeHills ||
				biome == BiomeGenBase.forest || biome == BiomeGenBase.taiga || biome == BiomeGenBase.swampland || biome == BiomeGenBase.plains || biome == BiomeGenBase.jungle ||
				biome == BiomeGenBase.megaTaiga|| biome == BiomeGenBase.savanna || biome == BiomeGenBase.roofedForest || biome == BiomeGenBase.river;
	}
}
