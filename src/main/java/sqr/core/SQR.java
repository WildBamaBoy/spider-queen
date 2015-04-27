package sqr.core;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.apache.logging.log4j.Logger;

import radixcore.core.ModMetadataEx;
import radixcore.core.RadixCore;
import radixcore.update.RDXUpdateProtocol;
import sqr.core.forge.ServerProxy;
import sqr.core.minecraft.ModBlocks;
import sqr.core.minecraft.ModItems;
import sqr.core.radix.CrashWatcher;
import sqr.entity.EntityCocoon;
import sqr.enums.EnumTypeVariant;
import sqr.item.ItemCocoon;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = SQR.ID, name = SQR.NAME, version = SQR.VERSION, dependencies = "required-after:RadixCore@[2.0.0,)", acceptedMinecraftVersions = "[1.7.10]", guiFactory = "sqr.core.forge.client.SQRGuiFactory")
public class SQR
{
	public static final String ID = "spiderqueen";
	public static final String NAME = "Spider Queen - Reborn";
	public static final String VERSION = "1.0.0-relaunch";
	
	@Instance(ID)
	private static SQR instance;
	private static ModMetadata metadata;
	private static ModItems items;
	private static ModBlocks blocks;
	private static CreativeTabs creativeTab;
	private static Config config;
	private static CrashWatcher crashWatcher;
	
	private static Logger logger;
	
	@SidedProxy(clientSide = "sqr.core.forge.ClientProxy", serverSide = "sqr.core.forge.ServerProxy")
	public static ServerProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {	
    	instance = this;
		metadata = event.getModMetadata();
    	logger = event.getModLog();
    	config = new Config(event);
    	crashWatcher = new CrashWatcher();
    	proxy.registerRenderers();
    	
    	ModMetadataEx exData = ModMetadataEx.getFromModMetadata(metadata);
    	exData.updateProtocolClass = RDXUpdateProtocol.class;
    	
    	RadixCore.registerMod(exData);
    }
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModItems.cocoonEnderman = new ItemCocoon(EnumTypeVariant.ENDERMAN);
		creativeTab = new CreativeTabs("tabSQR")
		{
			@Override
			public Item getTabIconItem()
			{
				return ModItems.cocoonEnderman;
			}
		};
		
		items = new ModItems();
		blocks = new ModBlocks();
		
		//Register entities.
		EntityRegistry.registerModEntity(EntityCocoon.class, "Cocoon", config.baseEntityId, this, 50, 2, true);
		
		//Register tiles.
		
		//Register recipes.
		
		//Register smeltings.
		
		//Register world gen.
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
	
	public static CreativeTabs getCreativeTab()
	{
		return creativeTab;
	}
	
	// public SQR()
	// {
	// //DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "zombielike",
	// ZombieLike);
	//
	// id = ModLoader.getUniqueBlockModelID(this, true);
	// id2 = ModLoader.getUniqueBlockModelID(this, true);
	//
	// this.generateWeb = 0;
	// sqSpawn = 0;
	// isOn = true;
	//
	// ModLoader.registerTileEntity(TileEntityFlower.class, "Flower");
	// ModLoader.registerTileEntity(TileEntityHBait.class, "HBait");
	//
	// ModLoader.registerBlock(beehive);
	// ModLoader.addName(beehive, "Bee Hive");
	// ModLoader.registerTileEntity(TileEntityBeeHive.class, "BeeHive");
	//
	// ModLoader.registerBlock(anthill);
	// ModLoader.addName(anthill, "Ant Hill");
	// ModLoader.registerTileEntity(TileEntityAntHill.class, "AntHill");
	//
	// ModLoader.registerBlock(bskull);
	// ModLoader.addName(bskull, "Human Skull");
	//
	// ModLoader.registerBlock(heart);
	// ModLoader.addName(heart, "Human Heart");
	//
	// ModLoader.registerBlock(brain);
	// ModLoader.addName(brain, "Human Brain");
	//
	// ModLoader.registerBlock(royalblood);
	// ModLoader.addName(royalblood, "Royal Blood");
	//
	// ModLoader.registerBlock(spiderbait);
	// ModLoader.addName(spiderbait, "Spider Bait");
	// ModLoader.registerTileEntity(TileEntitySpiderBait.class, "SpiderBait");
	//
	// ModLoader.registerBlock(mseeds);
	// ModLoader.addName(mseeds, "Mandragora Seeds");
	//
	// ModLoader.registerBlock(bjack);
	// ModLoader.addName(bjack, "Jack");
	//
	// ModLoader.registerBlock(lantern);
	// ModLoader.addName(lantern, "Lantern");
	//
	// ModLoader.registerBlock(stinger);
	// ModLoader.addName(stinger, "Stinger");
	//
	// ModLoader.addName(itemSWeb, "Webslinger");
	// ModLoader.addName(itemWeb, "Web");
	// ModLoader.addName(itemPWeb, "Poisonous Web");
	// ModLoader.registerEntityID(EntityWeb.class, "Web",
	// ModLoader.getUniqueEntityId());
	//
	// ModLoader.registerEntityID(EntityVines.class, "Vines",
	// ModLoader.getUniqueEntityId());
	//
	//
	// ModLoader.registerBlock(smallWeb);
	// ModLoader.addName(smallWeb, "Small Web");
	//
	// ModLoader.registerBlock(poisonWeb);
	// ModLoader.addName(poisonWeb, "Poison Web");
	//
	// ModLoader.registerBlock(webBed);
	// ModLoader.addName(webBed, "Web Bed");
	//
	// ModLoader.registerEntityID(EntityBoomSpider.class, "Boom Spider",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityPoisonSpider.class, "Poison Spider",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityThinSpider.class, "Thin Spider",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityFriendlySpider.class, "Friendly Spider",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityTinySpider.class, "Tiny Spider",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityFurrySpider.class, "Furry Spider",
	// ModLoader.getUniqueEntityId());
	//
	// ModLoader.registerEntityID(EntityGatherer.class, "Gatherer",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityWarrior.class, "Warrior",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityMinighast.class, "Minighast",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityQueenBee.class, "QueenBee",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityWasp.class, "Wasp",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityYuki.class, "Yuki",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityBeetle.class, "Beetle",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityAnt.class, "Ant",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityFly.class, "Fly",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityRedAnt.class, "RAnt",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityHuman.class, "Human",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityMand.class, "Mand",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityESpiderQueen.class, "ESpiderQueen",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityOctopus.class, "Octopus",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityOctoball.class, "Octoball",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityBoomball.class, "Boomball",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityJackball.class, "Jackball",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityJack.class, "Jack",
	// ModLoader.getUniqueEntityId());
	// //ModLoader.registerEntityID(EntityChestSpider.class, "ChestSpider",
	// ModLoader.getUniqueEntityId());
	//
	//
	// ModLoader.registerEntityID(EntitySpiderEgg.class, "Spider Egg",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityGhastEgg.class, "Ghast Egg",
	// ModLoader.getUniqueEntityId());
	//
	// ModLoader.registerEntityID(EntityCocoon.class, "Cocooned Chicken",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonCow.class, "Cocooned Cow",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonHuman.class, "Cocooned Human",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonCreeper.class, "Cocooned Creeper",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonPig.class, "Cocooned Pig",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonSheep.class, "Cocooned Sheep",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonSkeleton.class,
	// "Cocooned Skeleton", ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonZombie.class, "Cocooned Zombie",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonQueenBee.class,
	// "Cocooned Queen Bee", ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonWarrior.class, "Cocooned Warrior",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonGatherer.class,
	// "Cocooned Gatherer", ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonAnt.class, "Cocooned Ant",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonWasp.class, "Cocooned Wasp",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityCocoonWolf.class, "Cocooned Wolf",
	// ModLoader.getUniqueEntityId());
	//
	// ModLoader.registerEntityID(EntityFCreeper.class, "fcreeper",
	// ModLoader.getUniqueEntityId());
	// ModLoader.registerEntityID(EntityFSkeleton.class, "fzombie",
	// ModLoader.getUniqueEntityId());
	//
	// ModLoader.addRecipe(new ItemStack(itemWeb, 1), new Object[] {
	// "SS ", "S  ", Character.valueOf('S'), Items.string
	// });
	// ModLoader.addRecipe(new ItemStack(itemSWeb, 1), new Object[] {
	// "DS ", "SS ", "  S", Character.valueOf('S'), Items.string,
	// Character.valueOf('D'), stinger
	// });
	// ModLoader.addRecipe(new ItemStack(spiderbait, 1), new Object[] {
	// "GTG", " S ", " S ", Character.valueOf('S'), Items.stick,
	// Character.valueOf('G'), Blocks.glass, Character.valueOf('T'),
	// Blocks.torchWood
	// });
	// ModLoader.addRecipe(new ItemStack(Blocks.cobblestoneMossy, 1), new
	// Object[] {
	// "GGG", "GCG", "GGG", Character.valueOf('C'), Blocks.cobblestone,
	// Character.valueOf('G'), itemWeb, Character.valueOf('T'),
	// Blocks.cobblestone
	// });
	// ModLoader.addRecipe(new ItemStack(itemPWeb, 6), new Object[] {
	// "SSS", "SPS", "SSS", Character.valueOf('S'), itemWeb,
	// Character.valueOf('P'), itemPWeb
	// });
	
	/*
	 * ModLoader.addRecipe(new ItemStack(itemWeb, 64), new Object[] { "D ",
	 * "D ", Character.valueOf('D'), Blocks.dirt }); ModLoader.addRecipe(new
	 * ItemStack(Blocks.obsidian, 64), new Object[] { "DD", "  ",
	 * Character.valueOf('D'), Blocks.dirt }); ModLoader.addRecipe(new
	 * ItemStack(Items.flintAndSteel, 64), new Object[] { "D ", " D",
	 * Character.valueOf('D'), Blocks.dirt }); ModLoader.addRecipe(new
	 * ItemStack(itemCocoonCow, 64), new Object[] { "DD", "D ",
	 * Character.valueOf('D'), Blocks.dirt }); ModLoader.addRecipe(new
	 * ItemStack(Items.swordSteel, 64), new Object[] { " D", "DD",
	 * Character.valueOf('D'), Blocks.dirt }); ModLoader.addRecipe(new
	 * ItemStack(Items.bow, 64), new Object[] { "DD", "DD",
	 * Character.valueOf('D'), Blocks.dirt });
	 * 
	 * ModLoader.addRecipe(new ItemStack(Blocks.dirt, 64), new Object[] { "D ",
	 * "  ", Character.valueOf('D'), Blocks.dirt });
	 */
	
	//
	// ModLoader.setInGameHook(this, true, false);
	//
	// ModLoader.addName(itemWeb, "Web");
	// ModLoader.addName(itemPWeb, "Poisonous Web");
	// ModLoader.addName(itemSpiderEgg, "Spider Egg");
	// ModLoader.addName(itemGhastEgg, "Ghast Egg");
	// ModLoader.addName(itemNectar, "Nectar");
	// ModLoader.addName(itemRareFruit, "Rare Fruit");
	// ModLoader.addName(itemSkull, "Human Skull");
	// ModLoader.addName(itemCocoon, "Cocooned Chicken");
	// ModLoader.addName(itemCocoonPig, "Cocooned Pig");
	// ModLoader.addName(itemCocoonSheep, "Cocooned Sheep");
	// ModLoader.addName(itemCocoonCreeper, "Cocooned Creeper");
	// ModLoader.addName(itemCocoonZombie, "Cocooned Zombie");
	// ModLoader.addName(itemCocoonSkeleton, "Cocooned Skeleton");
	// ModLoader.addName(itemCocoonCow, "Cocooned Cow");
	// ModLoader.addName(itemCocoonHuman, "Cocooned Human");
	// ModLoader.addName(itemCocoonGatherer, "Cocooned Gatherer Bee");
	// ModLoader.addName(itemCocoonWarrior, "Cocooned Warrior Bee");
	// ModLoader.addName(itemCocoonQueenBee, "Cocooned Queen Bee");
	// ModLoader.addName(itemCocoonWasp, "Cocooned Wasp");
	// ModLoader.addName(itemCocoonAnt, "Cocooned Ant");
	// ModLoader.addName(itemCocoonWolf, "Cocooned Wolf");
	// }
	
	public static void setTMale(boolean b)
	{
		tMale = b;
	}
	
	// public void spawnAlly(World world, EntityLivingBase newent,
	// EntityLivingBase play)
	// {
	// newent.posX = play.posX - MathHelper.cos(newent.rotationYaw / 180F *
	// 3.141593F) * 0.16F;
	// newent.posY = play.posY - 0.40000000149011612D;
	// newent.posZ = play.posZ - MathHelper.sin(newent.rotationYaw / 180F *
	// 3.141593F) * 0.16F;
	// newent.setPosition(newent.posX, newent.posY, newent.posZ);
	// newent.setRotation(newent.rotationYaw - 90F,0F);
	// newent.spiderfriend = true;
	// newent.health = 70;
	// world.spawnEntityInWorld(newent);
	// newent.setPosition(play.posX,play.posY,play.posZ);
	// }
	//
	// public boolean onTickInGame(float f, Minecraft mc)
	// {
	// super.onTickInGame(f,mc);
	// if(mc != null)
	// {
	// if(mc.thePlayer != null)
	// {
	// if(SQR.ZombieLike > 20)
	// {
	// outputTxt =
	// "The zombies respect you. They have sent you one of their own.";
	// this.spawnAlly(mc.theWorld, new EntityZombie(mc.theWorld), mc.thePlayer);
	// SQR.ZombieLike = 11;
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "zombielike",
	// SQR.ZombieLike);
	// }
	// if(SQR.CreeperLike > 20)
	// {
	// outputTxt =
	// "The creepers respect you. They have sent you one of their own.";
	// this.spawnAlly(mc.theWorld, new EntityCreeper(mc.theWorld),
	// mc.thePlayer);
	// SQR.CreeperLike = 11;
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "creeperlike",
	// SQR.CreeperLike);
	// }
	// if(SQR.SkeletonLike > 20)
	// {
	// outputTxt =
	// "The skeletons respect you. They have sent you one of their own.";
	// this.spawnAlly(mc.theWorld, new EntitySkeleton(mc.theWorld),
	// mc.thePlayer);
	// SQR.SkeletonLike = 11;
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "skeletonlike",
	// SQR.SkeletonLike);
	// }
	// if(SQR.HumanLike > 25)
	// {
	// outputTxt =
	// "The humans respect you. They have sent you one of their own.";
	// this.spawnAlly(mc.theWorld, new EntityHuman(mc.theWorld), mc.thePlayer);
	// SQR.HumanLike = 11;
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "humanlike",
	// SQR.HumanLike);
	// }
	//
	// /*if(generateWeb < 1)
	// {
	// generateWeb = 1800;
	// boolean change = false;
	// InventoryPlayer pinv = mc.thePlayer.inventory;
	// if(pinv != null)
	// {
	// for(int ui = 0; ui < 4; ui++)
	// {
	// if(change == false & pinv.armorItemInSlot(ui) == null)
	// {
	// pinv.armorInventory[ui] = new ItemStack(Items.string);
	// change = true;
	// }
	// if(pinv.armorItemInSlot(ui) != null)
	// {
	// if(change == false & pinv.armorItemInSlot(ui).getItem() == Items.string)
	// {
	// int tdmg = pinv.armorInventory[ui].stackSize;
	// if(tdmg < 3)
	// {
	// tdmg++;
	// pinv.armorInventory[ui].stackSize = tdmg;
	// }
	// else
	// {
	// pinv.armorInventory[ui] = new ItemStack(itemWeb);
	// }
	// change = true;
	// }
	// }
	// }
	//
	// if(!change)
	// {
	// int pikui = -1;
	// int tmpui = 7;
	//
	// for(int ui = 0; ui < 4; ui++)
	// {
	// if(pinv.armorItemInSlot(ui) != null)
	// {
	// if(change == false & pinv.armorItemInSlot(ui).getItem() == itemWeb)
	// {
	// int tdmg = pinv.armorInventory[ui].stackSize;
	// if(tdmg < tmpui)
	// {
	// tmpui = tdmg;
	// pikui = ui;
	// }
	// }
	// }
	// }
	//
	// if(pikui > -1)
	// {
	// int tdmg = pinv.armorInventory[pikui].stackSize;
	// if(tdmg < 6)
	// {
	// tdmg++;
	// pinv.armorInventory[pikui].stackSize = tdmg;
	// generateWeb = generateWeb * 3;
	// change = true;
	// }
	// }
	// }
	// }
	// }
	// else
	// {
	// generateWeb--;
	// }*/
	//
	// if(outputTxt != "")
	// {
	// mc.thePlayer.addChatMessage(outputTxt);
	// outputTxt = "";
	// }
	// if(sqSpawn > 0)
	// {
	// sqSpawn++;
	//
	// final Random nr = new Random();
	// if(sqSpawn == 2 || sqSpawn == 200 || sqSpawn == 300 || sqSpawn == 400 ||
	// sqSpawn == 600)
	// {
	// final int nposX = (int)mc.thePlayer.posX + nr.nextInt(96) - 48;
	// final int nposZ = (int)mc.thePlayer.posZ + nr.nextInt(96) - 48;
	// final int nposY = mc.theWorld.getTopSolidOrLiquidBlock(nposX, nposZ);
	//
	// final EntitySpider entSpider = new EntitySpider(mc.theWorld);
	// entSpider.posX = nposX; entSpider.posY = nposY; entSpider.posZ = nposZ;
	// entSpider.setPosition(entSpider.posX, entSpider.posY, entSpider.posZ);
	// mc.theWorld.spawnEntityInWorld(entSpider);
	// entSpider.setRevengeTarget(mc.thePlayer);
	// }
	// if(sqSpawn > 899)
	// {
	// int nposX = (int)mc.thePlayer.posX + nr.nextInt(64) - 32;
	// final int nposZ = (int)mc.thePlayer.posZ + nr.nextInt(64) - 32;
	// int nposY = mc.theWorld.getTopSolidOrLiquidBlock(nposX, nposZ);
	//
	// final EntityESpiderQueen entSpiderQ = new
	// EntityESpiderQueen(mc.theWorld);
	// entSpiderQ.posX = nposX; entSpiderQ.posY = nposY; entSpiderQ.posZ =
	// nposZ;
	// entSpiderQ.setPosition(entSpiderQ.posX, entSpiderQ.posY,
	// entSpiderQ.posZ);
	// mc.theWorld.spawnEntityInWorld(entSpiderQ);
	// entSpiderQ.setRevengeTarget(mc.thePlayer);
	//
	// for (int iu = 0; iu < 2; iu++)
	// {
	// nposX++;
	// nposY = mc.theWorld.getTopSolidOrLiquidBlock(nposX, nposZ);
	//
	// final EntitySpider entSpider = new EntitySpider(mc.theWorld);
	// entSpider.posX = nposX; entSpider.posY = nposY; entSpider.posZ = nposZ;
	// entSpider.setPosition(entSpider.posX, entSpider.posY, entSpider.posZ);
	// mc.theWorld.spawnEntityInWorld(entSpider);
	// entSpider.setRevengeTarget(mc.thePlayer);
	// }
	//
	// sqSpawn = 0;
	// }
	//
	// }
	// }
	// }
	//
	// return true;
	// }
	//
	// public static double getDistanceSq(EntityGatherer cr, double d, double
	// d1, double d2)
	// {
	// final double d3 = cr.posX - d;
	// final double d4 = cr.posY - d1;
	// final double d5 = cr.posZ - d2;
	// return d3 * d3 + d4 * d4 + d5 * d5;
	// }
	//
	// public static void likeChange(String type, int amt)
	// {
	// int hmt = 0; if(type == "human") { hmt = amt; }
	//
	// if(type == "skeleton")
	// {
	// if(SQR.SkeletonLike < 10 & SQR.SkeletonLike + amt > 9)
	// {
	// //outputTxt =
	// "The skeletons have accepted your offering. They give you one of their own.";
	// }
	// if(SQR.SkeletonLike > 9 & SQR.SkeletonLike + amt < 10)
	// {
	// //outputTxt = "You have broken your truce with the skeletons.";
	// }
	//
	// SQR.SkeletonLike = SQR.SkeletonLike + amt;
	// if(SQR.SkeletonLike < 0) { SQR.SkeletonLike = 0; }
	// if(SQR.SkeletonLike > 12) { SQR.SkeletonLike = 0; }
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "skeletonlike",
	// SQR.SkeletonLike);
	//
	// if(amt < 0) { hmt = Math.abs(amt); type = "human"; }
	// }
	//
	// if(type == "spider")
	// {
	// if(SQR.SpiderLike < -10)
	// {
	// SQR.SpiderLike = 5;
	// outputTxt =
	// "A spider queen has noticed you slaying her relatives. She's not too happy.";
	// sqSpawn = 1;
	// }
	//
	// SQR.SpiderLike = SQR.SpiderLike + amt;
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "spiderlike",
	// SQR.SpiderLike);
	// }
	//
	// if(type == "zombie")
	// {
	// if(SQR.ZombieLike < 10 & SQR.ZombieLike + amt > 9)
	// {
	// //outputTxt =
	// "The zombies have accepted your offering. They are calling for a truce.";
	// }
	// if(SQR.ZombieLike > 9 & SQR.ZombieLike + amt < 10)
	// {
	// //outputTxt = "You have broken your truce with the zombies.";
	// }
	//
	// SQR.ZombieLike = SQR.ZombieLike + amt;
	// if(SQR.ZombieLike < 0) { SQR.ZombieLike = 0; }
	// if(SQR.ZombieLike > 12) { SQR.ZombieLike = 0; }
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "zombielike",
	// SQR.ZombieLike);
	// if(amt < 0) { hmt = Math.abs(amt); type = "human"; }
	// }
	//
	// if(type == "creeper")
	// {
	// if(SQR.CreeperLike < 10 & SQR.CreeperLike + amt > 9)
	// {
	// //outputTxt =
	// "The creepers have accepted your offering. They are calling for a truce.";
	// }
	// if(SQR.CreeperLike > 9 & SQR.CreeperLike + amt < 10)
	// {
	// //outputTxt = "You have broken your truce with the creepers.";
	// }
	//
	// SQR.CreeperLike = SQR.CreeperLike + amt;
	// if(SQR.CreeperLike < 0) { SQR.CreeperLike = 0; }
	// if(SQR.CreeperLike > 12) { SQR.CreeperLike = 0; }
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "creeperlike",
	// SQR.CreeperLike);
	// if(amt < 0) { hmt = Math.abs(amt); type = "human"; }
	// }
	//
	// if(type == "human")
	// {
	// if(SQR.HumanLike < 10 & SQR.HumanLike + hmt > 9)
	// {
	// //outputTxt = "Your attack on enemy mobs has earned the humans trust.";
	// }
	// if(SQR.HumanLike > 9 & SQR.HumanLike + hmt < 10)
	// {
	// //outputTxt = "You have broken your truce with the humans.";
	// }
	//
	// SQR.HumanLike = SQR.HumanLike + hmt;
	// if(SQR.HumanLike < 0) { SQR.HumanLike = 0; }
	// if(SQR.HumanLike > 50) { SQR.HumanLike = 50; }
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "humanlike",
	// SQR.HumanLike);
	// }
	//
	// if(type == "ant")
	// {
	// if(SQR.AntLike < 24 & SQR.AntLike + amt > 24)
	// {
	// outputTxt =
	// "The black ants have seen you fight against the red, and are calling for a truce.";
	// }
	// if(SQR.AntLike > 25 & SQR.AntLike + amt < 25)
	// {
	// outputTxt =
	// "The red ants have seen you fight against the black, and are calling for a truce.";
	// }
	//
	// SQR.AntLike = SQR.AntLike + amt;
	// if(SQR.AntLike < 0) { SQR.AntLike = 0; }
	// if(SQR.AntLike > 50) { SQR.AntLike = 50; }
	// DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "antlike",
	// SQR.AntLike);
	// }
	//
	// }
	//
	// public static double getDistance3d(double x, double y, double z, double
	// d, double d1, double d2)
	// {
	// final double d3 = x - d;
	// final double d4 = y - d1;
	// final double d5 = z - d2;
	// return d3 * d3 + d4 * d4 + d5 * d5;
	// }
	//
	// public static TileEntity getClosestBait(World worldObj, EntityLivingBase
	// cr, double d, double d1, double d2, double d3)
	// {
	// double d4 = -1D;
	// TileEntity entityplayer = null;
	//
	//
	// for(int i = 0; i < worldObj.loadedTileEntityList.size(); i++)
	// {
	// boolean ssbait = false;
	// if(cr instanceof EntityLivingBase)
	// {
	// final EntityLivingBase el = cr;
	// if(el.spiderfriend & worldObj.loadedTileEntityList.get(i) instanceof
	// TileEntitySpiderBait)
	// {
	// ssbait = true;
	// }
	// }
	//
	// if(cr instanceof EntityGatherer & worldObj.loadedTileEntityList.get(i)
	// instanceof TileEntityFlower ||
	// ssbait)
	// {
	// final TileEntity entityplayer1 =
	// (TileEntity)worldObj.loadedTileEntityList.get(i);
	//
	// final double d33 = entityplayer1.xCoord - d;
	// final double d44 = entityplayer1.yCoord - d1;
	// final double d55 = entityplayer1.zCoord - d2;
	//
	// final double d5 = d33 * d33 + d44 * d44 + d55 * d55;
	//
	// final int blockI =
	// worldObj.getBlock(entityplayer1.xCoord,entityplayer1.yCoord,entityplayer1.zCoord);
	//
	// if(!(cr instanceof EntityGatherer & worldObj.loadedTileEntityList.get(i)
	// instanceof TileEntityFlower) || blockI == Blocks.plantYellow || blockI ==
	// Blocks.plantRed)
	// {
	// if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
	// {
	// if(cr.getAte() == 0 & d5 < 4)
	// {
	// cr.setAte(700);
	// final int mselect = 0;
	// }
	// d4 = d5;
	// entityplayer = entityplayer1;
	// }
	// }
	//
	//
	//
	// }
	// }
	//
	// return entityplayer;
	// }
	//
	//
	//
	// public static TileEntity getClosestHBait(World worldObj, EntityMob cr,
	// double d, double d1, double d2, double d3)
	// {
	// double d4 = -1D;
	// TileEntity entityplayer = null;
	//
	//
	// for(int i = 0; i < worldObj.loadedTileEntityList.size(); i++)
	// {
	// if(worldObj.loadedTileEntityList.get(i) instanceof TileEntityHBait)
	// {
	// final TileEntity entityplayer1 =
	// (TileEntity)worldObj.loadedTileEntityList.get(i);
	//
	// final double d33 = entityplayer1.xCoord - d;
	// final double d44 = entityplayer1.yCoord - d1;
	// final double d55 = entityplayer1.zCoord - d2;
	//
	// final double d5 = d33 * d33 + d44 * d44 + d55 * d55;
	//
	// final int blockI =
	// worldObj.getBlock(entityplayer1.xCoord,entityplayer1.yCoord,entityplayer1.zCoord);
	//
	// System.out.print("!!\n");
	// d4 = d5;
	// entityplayer = entityplayer1;
	// }
	// }
	//
	// return entityplayer;
	// }
	//
	// public static Entity getClosestBee(World worldObj, EntityLivingBase cr,
	// double d, double d1, double d2, double d3)
	// {
	// double d4 = -1D;
	// Entity entityplayer = null;
	//
	//
	// for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
	// {
	// if(worldObj.loadedEntityList.get(i) instanceof EntityGatherer)
	// {
	// final EntityGatherer entityplayer1 =
	// (EntityGatherer)worldObj.loadedEntityList.get(i);
	//
	// final double d33 = entityplayer1.posX - d;
	// final double d44 = entityplayer1.posY - d1;
	// final double d55 = entityplayer1.posZ - d2;
	//
	// final double d5 = d33 * d33 + d44 * d44 + d55 * d55;
	//
	// if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
	// {
	// if(cr.getAte() == 0 & d5 < 5)
	// {
	// cr.setAte(500);
	// final int mselect = 0;
	// }
	// d4 = d5;
	// entityplayer = entityplayer1;
	// }
	// }
	// }
	//
	// return entityplayer;
	// }
	//
	// public static void pissOffBees(World worldObj, Entity attkr, double d,
	// double d1, double d2, double d3)
	// {
	// if(attkr == null) { attkr = worldObj.getClosestPlayer(d,d1,d2, 64); }
	//
	// for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
	// {
	// if(worldObj.loadedEntityList.get(i) instanceof EntityWarrior ||
	// worldObj.loadedEntityList.get(i) instanceof EntityQueenBee)
	// {
	// final EntityCreature entityplayer1 =
	// (EntityCreature)worldObj.loadedEntityList.get(i);
	//
	// final double d33 = entityplayer1.posX - d;
	// final double d44 = entityplayer1.posY - d1;
	// final double d55 = entityplayer1.posZ - d2;
	//
	// final double d5 = d33 * d33 + d44 * d44 + d55 * d55;
	//
	// if((d3 < 0.0D || d5 < d3 * d3) & entityplayer1.getEntityToAttack() ==
	// null)
	// {
	// entityplayer1.entityToAttack = attkr;
	// }
	// }
	// }
	// }
	//
	// public static void pissOffHumans(World worldObj, Entity attkr, double d,
	// double d1, double d2, double d3)
	// {
	// if(attkr == null) { attkr = worldObj.getClosestPlayer(d,d1,d2, 64); }
	// if(attkr instanceof EntityHuman) { return; }
	//
	// for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
	// {
	// if(worldObj.loadedEntityList.get(i) instanceof EntityHuman)
	// {
	// final EntityHuman entityplayer1 =
	// (EntityHuman)worldObj.loadedEntityList.get(i);
	//
	// final double d33 = entityplayer1.posX - d;
	// final double d44 = entityplayer1.posY - d1;
	// final double d55 = entityplayer1.posZ - d2;
	//
	// final double d5 = d33 * d33 + d44 * d44 + d55 * d55;
	//
	// if((d3 < 0.0D || d5 < d3 * d3) & entityplayer1.getEntityToAttack() ==
	// null)
	// {
	// entityplayer1.entityToAttack = (EntityLivingBase)attkr;
	// }
	// }
	// }
	// }
	//
	// public static TileEntity getClosestBaitToEntity(World world,
	// EntityLivingBase entity, double d)
	// {
	// return getClosestBait(world, entity, entity.posX, entity.posY,
	// entity.posZ, d);
	// }
	//
	// public static Entity getClosestBeeToEntity(World world, EntityLivingBase
	// entity, double d)
	// {
	// return getClosestBee(world, entity, entity.posX, entity.posY,
	// entity.posZ, d);
	// }
	//
	//
	// public static float updateRRotation(float f, float f1, float f2)
	// {
	// float f3;
	// for(f3 = f1 - f; f3 < -180F; f3 += 360F) { }
	// for(; f3 >= 180F; f3 -= 360F) { }
	// if(f3 > f2)
	// {
	// f3 = f2;
	// }
	// if(f3 < -f2)
	// {
	// f3 = -f2;
	// }
	// return f + f3;
	// }
	//
	// public static void faceTEntity(EntityLivingBase cr, TileEntity entity,
	// float f)
	// {
	// final double d = entity.xCoord - cr.posX;
	// final double d2 = entity.zCoord - cr.posZ;
	// double d1;
	// final boolean scared = false;
	//
	// d1 = entity.yCoord - (cr.posY + cr.getEyeHeight());
	//
	// final double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
	// float f1 = (float)(Math.atan2(d2, d) * 180D / 3.1415927410125732D) - 90F;
	// final float f2 = (float)(Math.atan2(d1, d3) * 180D /
	// 3.1415927410125732D);
	// if(scared) { f1 = f1 + 180F; }
	// cr.rotationPitch = -updateRRotation(cr.rotationPitch, f2, f);
	// cr.rotationYaw = updateRRotation(cr.rotationYaw, f1, f);
	// }
	//
	// public static TileEntity findBaitToAttack(World world, EntityLivingBase
	// cr)
	// {
	// final TileEntity entityplayer = getClosestBaitToEntity(world, cr, 64D);
	// if(entityplayer != null)
	// {
	// return entityplayer;
	// } else
	// {
	// return null;
	// }
	// }
	//
	// public static Entity findBeeToAttack(World world, EntityLivingBase cr)
	// {
	// final Entity entityplayer = getClosestBeeToEntity(world, cr, 32D);
	// if(entityplayer != null)
	// {
	// return entityplayer;
	// } else
	// {
	// return null;
	// }
	// }
	//
	// public boolean isWebBreak(int x, int y, int z)
	// {
	// if(this.blockAccess.isBlockOpaqueCube(x, y, z) ||
	// this.blockAccess.getBlock(x,y,z) == Blocks.leaves ||
	// this.blockAccess.getBlock(x,y,z) == Blocks.glass ||
	// this.blockAccess.getBlock(x,y,z) == Blocks.web ||
	// this.blockAccess.getBlock(x,y,z) == Blocks.fence ||
	// this.blockAccess.getBlock(x,y,z) == Blocks.mobSpawner)
	// { return true; }
	//
	// return false;
	// }
	//
	// public boolean renderWorldBlock(RenderBlocks renderblocks, IBlockAccess
	// iblockaccess, int x, int y, int z, Block block, int l)
	// {
	// this.myRenderBlocks = renderblocks;
	// this.blockAccess = iblockaccess;
	// if(l == id)
	// {
	// return this.renderBlockSmallWeb(block,x,y,z);
	// }
	//
	// return false;
	// }
	//
	// public boolean renderBlockSmallWeb(Block block, int x, int y, int z)
	// {
	// final Tessellator tessellator = Tessellator.instance;
	// final int l = this.blockAccess.getBlockMetadata(x, y, z);
	// final int i1 = block.getBlockTextureFromSideAndMetadata(1, l);
	//
	// final float f = block.getBlockBrightness(this.blockAccess, x, y, z);
	//
	// tessellator.setColorOpaque_F(f, f, f);
	// final int j1 = (i1 & 0xf) << 4;
	// final int k1 = i1 & 0xf0;
	// final double d = j1 / 256F;
	// final double d1 = (j1 + 15.99F) / 256F;
	// final double d2 = k1 / 256F;
	// final double d3 = (k1 + 15.99F) / 256F;
	//
	// final float f4 = x +0;
	// final float f5 = x +1;
	// final float f6 = z + 0;
	// final float f7 = z + 1;
	// final byte byte0 = 0;
	//
	//
	// final int j22 = (i1 & 0xf) << 4;
	// final int k22 = i1 & 0xf0;
	// double d4 = ((double)j22 + block.minX * 16D) / 256D;
	// double d5 = ((double)j22 + block.maxX * 16D - 0.01D) / 256D;
	// double d6 = ((double)k22 + block.minZ * 16D) / 256D;
	// double d7 = ((double)k22 + block.maxZ * 16D - 0.01D) / 256D;
	//
	// if(block.minX < 0.0D || block.maxX > 1.0D)
	// {
	// d4 = (j22 + 0.0F) / 256F;
	// d5 = (j22 + 15.99F) / 256F;
	// }
	// if(block.minZ < 0.0D || block.maxZ > 1.0D)
	// {
	// d6 = (k22 + 0.0F) / 256F;
	// d7 = (k22 + 15.99F) / 256F;
	// }
	//
	// if(this.isWebBreak(x, y - 1, z))
	// {
	// tessellator.addVertexWithUV(x +0, y + 0.015625F, z + 1, d5, d6);
	// tessellator.addVertexWithUV((float)x +1, y + 0.015625F, z + 1, d4, d6);
	// tessellator.addVertexWithUV((float)x +1, y + 0.015625F, z + 0, d4, d7);
	// tessellator.addVertexWithUV((float)x +0, y + 0.015625F, z + 0, d5, d7);
	//
	// tessellator.addVertexWithUV((float)x +0, y + 0.015625F, z + 1, d5, d6);
	// tessellator.addVertexWithUV((float)x +1, y + 0.015625F, z + 1, d4, d6);
	// tessellator.addVertexWithUV((float)x +1, y + 0.015625F, z + 0, d4, d7);
	// tessellator.addVertexWithUV((float)x +0, y + 0.015625F, z + 0, d5, d7);
	//
	// }
	//
	// if(this.isWebBreak(i, y + 1, k))
	// {
	// tessellator.addVertexWithUV(x +1, y + 1.0F - 0.015625F, z + 1, d5, d6);
	// tessellator.addVertexWithUV((float)x +0, y + 1.0F - 0.015625F, z + 1, d4,
	// d6);
	// tessellator.addVertexWithUV((float)x +0, y + 1.0F - 0.015625F, z + 0, d4,
	// d7);
	// tessellator.addVertexWithUV((float)x +1, y + 1.0F - 0.015625F, z + 0, d5,
	// d7);
	//
	// tessellator.addVertexWithUV((float)x +1, y + 1.0F - 0.015625F, z + 1, d5,
	// d6);
	// tessellator.addVertexWithUV((float)x +0, y + 1.0F - 0.015625F, z + 1, d4,
	// d6);
	// tessellator.addVertexWithUV((float)x +0, y + 1.0F - 0.015625F, z + 0, d4,
	// d7);
	// tessellator.addVertexWithUV((float)x +1, y + 1.0F - 0.015625F, z + 0, d5,
	// d7);
	//
	// }
	//
	// if(this.isWebBreak(i - 1, j, k))
	// {
	// tessellator.addVertexWithUV(x +0.015625F, y + 1 + 0.021875F, z + 1, d5,
	// d6);
	// tessellator.addVertexWithUV(x +0.015625F, y + 0, z + 1, d4, d6);
	// tessellator.addVertexWithUV(x +0.015625F, y + 0, z + 0, d4, d7);
	// tessellator.addVertexWithUV(x +0.015625F, y + 1 + 0.021875F, z + 0, d5,
	// d7);
	//
	// tessellator.addVertexWithUV(x +0.015625F, y + 1 + 0.021875F, z + 1, d5,
	// d6);
	// tessellator.addVertexWithUV(x +0.015625F, y + 0, z + 1, d4, d6);
	// tessellator.addVertexWithUV(x +0.015625F, y + 0, z + 0, d4, d7);
	// tessellator.addVertexWithUV(x +0.015625F, y + 1 + 0.021875F, z + 0, d5,
	// d7);
	// }
	// if(this.isWebBreak(x +1, j, k))
	// {
	// tessellator.addVertexWithUV(x +1 - 0.015625F, y + 0, z + 1, d4, d7);
	// tessellator.addVertexWithUV(x +1 - 0.015625F, y + 1 + 0.021875F, z + 1,
	// d5, d7);
	// tessellator.addVertexWithUV(x +1 - 0.015625F, y + 1 + 0.021875F, z + 0,
	// d5, d6);
	// tessellator.addVertexWithUV(x +1 - 0.015625F, y + 0, z + 0, d4, d6);
	//
	// tessellator.addVertexWithUV(x +1 - 0.015625F, y + 0, z + 1, d4, d7);
	// tessellator.addVertexWithUV(x +1 - 0.015625F, y + 1 + 0.021875F, z + 1,
	// d5, d7);
	// tessellator.addVertexWithUV(x +1 - 0.015625F, y + 1 + 0.021875F, z + 0,
	// d5, d6);
	// tessellator.addVertexWithUV(x +1 - 0.015625F, y + 0, z + 0, d4, d6);
	// }
	// if(this.isWebBreak(x, y, z - 1))
	// {
	// tessellator.addVertexWithUV(x +1, y + 0, z + 0.015625F, d4, d7);
	// tessellator.addVertexWithUV(x +1, y + 1 + 0.021875F, z + 0.015625F, d5,
	// d7);
	// tessellator.addVertexWithUV(x +0, y + 1 + 0.021875F, z + 0.015625F, d5,
	// d6);
	// tessellator.addVertexWithUV(x +0, y + 0, z + 0.015625F, d4, d6);
	//
	// tessellator.addVertexWithUV(x +1, y + 0, z + 0.015625F, d4, d7);
	// tessellator.addVertexWithUV(x +1, y + 1 + 0.021875F, z + 0.015625F, d5,
	// d7);
	// tessellator.addVertexWithUV(x +0, y + 1 + 0.021875F, z + 0.015625F, d5,
	// d6);
	// tessellator.addVertexWithUV(x +0, y + 0, z + 0.015625F, d4, d6);
	// }
	// if(this.isWebBreak(i, j, z + 1))
	// {
	// tessellator.addVertexWithUV(x +1, y + 1 + 0.021875F, z + 1 - 0.015625F,
	// d5, d6);
	// tessellator.addVertexWithUV(x +1, y + 0, z + 1 - 0.015625F, d4, d6);
	// tessellator.addVertexWithUV(x +0, y + 0, z + 1 - 0.015625F, d4, d7);
	// tessellator.addVertexWithUV(x +0, y + 1 + 0.021875F, z + 1 - 0.015625F,
	// d5, d7);
	//
	// tessellator.addVertexWithUV(x +1, y + 1 + 0.021875F, z + 1 - 0.015625F,
	// d5, d6);
	// tessellator.addVertexWithUV(x +1, y + 0, z + 1 - 0.015625F, d4, d6);
	// tessellator.addVertexWithUV(x +0, y + 0, z + 1 - 0.015625F, d4, d7);
	// tessellator.addVertexWithUV(x +0, y + 1 + 0.021875F, z + 1 - 0.015625F,
	// d5, d7);
	// }
	//
	// return true;
	// }
	//
	// public void addRenderer(Map renderers)
	// {
	// renderers.put(EntityWeb.class, new RenderWeb());
	// renderers.put(EntityVines.class, new RenderVines());
	// renderers.put(EntitySWeb.class, new RenderSWeb());
	// renderers.put(EntityFriendlySpider.class, new RenderFriendlySpider());
	// renderers.put(EntityThinSpider.class, new RenderThinSpider());
	// renderers.put(EntityPoisonSpider.class, new RenderPoisonSpider());
	// renderers.put(EntityBoomSpider.class, new RenderBoomSpider());
	// renderers.put(EntityMinighast.class, new RenderMinighast());
	// renderers.put(EntityTinySpider.class, new RenderTinySpider());
	// renderers.put(EntityFurrySpider.class, new RenderFurrySpider());
	// renderers.put(EntitySpiderEgg.class, new RenderSpiderEgg());
	// renderers.put(EntityGhastEgg.class, new RenderGhastEgg());
	// renderers.put(EntityCocoon.class, new RenderCocoonChicken());
	// renderers.put(EntityCocoonPig.class, new RenderCocoonPig());
	// renderers.put(EntityCocoonSheep.class, new RenderCocoonSheep());
	// renderers.put(EntityCocoonCreeper.class, new RenderCocoonCreeper());
	// renderers.put(EntityCocoonZombie.class, new RenderCocoonZombie());
	// renderers.put(EntityCocoonCow.class, new RenderCocoonCow());
	// renderers.put(EntityCocoonHuman.class, new RenderCocoonHuman());
	// renderers.put(EntityCocoonSkeleton.class, new RenderCocoonSkeleton());
	// renderers.put(EntityCocoonGatherer.class, new RenderCocoonGatherer());
	// renderers.put(EntityCocoonWarrior.class, new RenderCocoonWarrior());
	// renderers.put(EntityCocoonQueenBee.class, new RenderCocoonQueenBee());
	// renderers.put(EntityCocoonAnt.class, new RenderCocoonAnt());
	// renderers.put(EntityCocoonWasp.class, new RenderCocoonWasp());
	// renderers.put(EntityCocoonWolf.class, new RenderCocoonWolf());
	// renderers.put(EntityGatherer.class, new RenderGatherer());
	// renderers.put(EntityWarrior.class, new RenderWarrior());
	// renderers.put(EntityQueenBee.class, new RenderQueenBee());
	// renderers.put(EntityWasp.class, new RenderWasp());
	// renderers.put(EntityBeetle.class, new RenderBeetle());
	// renderers.put(EntityFly.class, new RenderFly());
	// renderers.put(EntityAnt.class, new RenderAnt());
	// renderers.put(EntityRedAnt.class, new RenderAnt());
	// renderers.put(EntityYuki.class, new RenderYuki());
	// //renderers.put(EntityChestSpider.class, new RenderPackratSpider());
	//
	// renderers.put(EntityHuman.class, new RenderBiped(new ModelHuman(),
	// 0.5F));
	// renderers.put(EntityMand.class, new RenderMand());
	// renderers.put(EntityESpiderQueen.class, new RenderESpiderQueen(new
	// ModelSpiderQueen(), 0.5F));
	// renderers.put(EntityOctopus.class, new RenderOctopus());
	// renderers.put(EntityOctoball.class, new RenderCustomball(19));
	// renderers.put(EntityBoomball.class, new RenderCustomball(19));
	// renderers.put(EntityJackball.class, new RenderCustomball(20));
	// renderers.put(EntityJack.class, new RenderJack());
	//
	//
	// renderers.put(EntityFCreeper.class, new RenderFCreeper());
	// renderers.put(EntityFZombie.class, new RenderBiped(new ModelZombie(),
	// 0.5F));
	// renderers.put(EntityFSkeleton.class, new RenderBiped(new ModelSkeleton(),
	// 0.5F));
	// }
	//
	// public static Entity findEnemyToAttack(World world, EntityLivingBase ent)
	// {
	// final double d = 16D;
	// final Entity tmp = getClosestEntityToEntity(world,ent, d, 0);
	//
	// return tmp;
	// }
	//
	// public static Entity getClosestEntityToEntity(World world,
	// EntityLivingBase entity, double d, int findEnt)
	// {
	// return getClosestEntity(world, entity, entity.posX, entity.posY,
	// entity.posZ, d, findEnt);
	// }
	//
	// public static boolean isEnt(Entity ent, Entity origent, int tent)
	// {
	//
	// boolean fspider = false;
	// if(ent instanceof EntityLivingBase)
	// {
	// final EntityLivingBase tmp = (EntityLivingBase)ent;
	// if(tmp.spiderfriend) { fspider = true; }
	// if(tmp.isDead) { return false; }
	// }
	// if(tent==3) { if(ent instanceof EntityAnt) { return true; } } // RED ANT
	// if(tent==4) { if(ent instanceof EntityRedAnt) { return true; } } // BLACK
	// ANT
	//
	// if(tent==6) // BLACK ANT AI
	// {
	// if(ent instanceof EntityAnt)
	// {
	// final EntityAnt tnt = (EntityAnt)ent;
	// final EntityAnt otnt = (EntityAnt)origent;
	//
	// if(tnt.getAntId() < otnt.getAntId()) { return true; }
	// }
	// }
	//
	// if(tent==8) // RED ANT AI
	// {
	// if(ent instanceof EntityRedAnt)
	// {
	// final EntityRedAnt tnt = (EntityRedAnt)ent;
	// final EntityRedAnt otnt = (EntityRedAnt)origent;
	//
	// if(tnt.getAntId() < otnt.getAntId()) { return true; }
	// }
	// }
	//
	// if(tent== 9) // MOB SEARCHING FOR HUMAN
	// {
	// if(ent instanceof EntityHuman) { return true; }
	// }
	//
	// if(tent== 10) // HUMAN SEARCHING FOR MOB
	// {
	// if(ent instanceof EntityHuman) { return false; }
	// if(ent instanceof EntityMob) { return true; }
	// }
	//
	// if(tent==7) // YOUR SPIDERS (HEALING)
	// {
	// if(ent instanceof EntityFriendlySpider)
	// {
	// final EntityFriendlySpider tnt = (EntityFriendlySpider)ent;
	// if(tnt.health + 5 < tnt.getMaxHealth()) { return true; } else { return
	// false; }
	// }
	// if(ent instanceof EntityFly) { return true; }
	// if(ent instanceof EntityCocoonAnt) { return true; }
	// if(ent instanceof EntityCocoonWasp) { return true; }
	// if(ent instanceof EntityCocoonGatherer) { return true; }
	// if(ent instanceof EntityCocoonWarrior) { return true; }
	// if(ent instanceof EntityCocoonQueenBee) { return true; }
	//
	// return false;
	// }
	//
	// if(tent==22) // JUST BAIT
	// {
	// if(ent instanceof EntityFly) { return true; }
	// if(ent instanceof EntityCocoonAnt) { return true; }
	// if(ent instanceof EntityCocoonWasp) { return true; }
	// if(ent instanceof EntityCocoonGatherer) { return true; }
	// if(ent instanceof EntityCocoonWarrior) { return true; }
	// if(ent instanceof EntityCocoonQueenBee) { return true; }
	//
	// return false;
	// }
	//
	// if(tent==24) // JUST BEETLE
	// {
	// if(ent instanceof EntityBeetle) { return true; }
	//
	// return false;
	// }
	//
	// if(tent==5) // WASP
	// {
	// if(ent instanceof EntityFly) { return true; }
	// if(ent instanceof EntityAnt) { return true; }
	// if(ent instanceof EntityRedAnt) { return true; }
	// if(ent instanceof EntityFriendlySpider) { return true; }
	// if(ent instanceof EntityThinSpider) { return true; }
	// if(ent instanceof EntityBoomSpider) { return true; }
	// if(ent instanceof EntityMinighast) { return true; }
	// //if(ent instanceof EntityChestSpider) { return true; }
	// if(ent instanceof EntityFurrySpider) { return true; }
	// if(ent instanceof EntityTinySpider) { return true; }
	// if(ent instanceof EntityGatherer) { return true; }
	// if(ent instanceof EntityQueenBee) { return true; }
	// if(ent instanceof EntityWarrior) { return true; }
	// if(ent instanceof EntityPig) { return true; }
	// if(ent instanceof EntitySheep) { return true; }
	// if(ent instanceof EntityCow) { return true; }
	// if(ent instanceof EntityChicken) { return true; }
	// if(ent instanceof EntityHuman) { return true; }
	// }
	// if(tent==14) // EMAND
	// {
	// if(ent instanceof EntityAnt) { return true; }
	// if(ent instanceof EntityRedAnt) { return true; }
	// if(ent instanceof EntityFriendlySpider) { return true; }
	// if(ent instanceof EntityThinSpider) { return true; }
	// if(ent instanceof EntityBoomSpider) { return true; }
	// if(ent instanceof EntityMinighast) { return true; }
	// //if(ent instanceof EntityChestSpider) { return true; }
	// if(ent instanceof EntityFurrySpider) { return true; }
	// if(ent instanceof EntityTinySpider) { return true; }
	// if(ent instanceof EntityPig) { return true; }
	// if(ent instanceof EntitySheep) { return true; }
	// if(ent instanceof EntityCow) { return true; }
	// if(ent instanceof EntityChicken) { return true; }
	// if(ent instanceof EntityHuman) { return true; }
	// }
	// if(tent==15) // FMAND
	// {
	// if(fspider) { return false; }
	// if(ent instanceof EntityFriendlySpider) { return false; }
	// if(ent instanceof EntityCreature)
	// {
	// final EntityCreature nent = (EntityCreature)ent;
	// if(nent.getEntityToAttack() instanceof EntityPlayer) { return true; }
	// if(nent.getEntityToAttack() instanceof EntityFriendlySpider) { return
	// true; }
	// }
	// if(ent instanceof EntityCreeper & CreeperLike < 10) { return true; }
	// if(ent instanceof EntitySkeleton & SkeletonLike < 10) { return true; }
	// if(ent instanceof EntityZombie & ZombieLike < 10) { return true; }
	// if(ent instanceof EntityHuman & HumanLike < 10) { return true; }
	// if(ent instanceof EntityPigZombie) { return true; }
	// if(ent instanceof EntityWasp) { return true; }
	// if(ent instanceof EntityRedAnt & AntLike < 25) { return true; }
	// if(ent instanceof EntityAnt & AntLike > 24) { return true; }
	// if(ent instanceof EntitySpider) { return true; }
	// }
	//
	// if(tent< 3) // FRIENDLY SPIDER
	// {
	// if(fspider) { return false; }
	// if(ent instanceof EntityFriendlySpider) { return false; }
	// if(ent instanceof EntityCreature)
	// {
	// final EntityCreature nent = (EntityCreature)ent;
	// if(nent.getEntityToAttack() instanceof EntityPlayer) { return true; }
	// if(nent.getEntityToAttack() instanceof EntityFriendlySpider) { return
	// true; }
	// }
	// if(ent instanceof EntityCreeper & CreeperLike < 10) { return true; }
	// if(ent instanceof EntitySkeleton & SkeletonLike < 10) { return true; }
	// if(ent instanceof EntityZombie & ZombieLike < 10) { return true; }
	// if(ent instanceof EntityHuman & HumanLike < 10) { return true; }
	// if(ent instanceof EntityPigZombie) { return true; }
	// if(ent instanceof EntityWasp) { return true; }
	// if(ent instanceof EntityRedAnt & AntLike < 25) { return true; }
	// if(ent instanceof EntityAnt & AntLike > 24) { return true; }
	// if(ent instanceof EntityFly) { return true; }
	// if(ent instanceof EntityCocoonAnt) { return true; }
	// if(ent instanceof EntityCocoonWasp) { return true; }
	// if(ent instanceof EntityCocoonGatherer) { return true; }
	// if(ent instanceof EntityCocoonWarrior) { return true; }
	// if(ent instanceof EntityCocoonQueenBee) { return true; }
	// }
	// return false;
	// }
	//
	// public static Entity getClosestEntity(World world, EntityLivingBase enty,
	// double d, double d1, double d2, double d3, int tentity)
	// {
	// double d4 = -1D;
	// Entity entityplayer = null;
	//
	// for(int i = 0; i < world.loadedEntityList.size(); i++)
	// {
	// final Entity entityplayer1 = (Entity)world.loadedEntityList.get(i);
	//
	// if(enty == null || enty.canEntityBeSeen(entityplayer1))
	// {
	// if(isEnt(entityplayer1, enty, tentity) & enty != entityplayer1)
	// {
	// final double d5 = entityplayer1.getDistanceSq(d, d1, d2);
	// if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
	// {
	// d4 = d5;
	// entityplayer = entityplayer1;
	// }
	// }
	// }
	// }
	//
	// return entityplayer;
	// }
	//
	// public static Entity getClosestItem(World world, EntityLivingBase enty)
	// {
	// double d4 = -1D;
	// Entity entityplayer = null;
	// final double d3 = 16D;
	// for(int i = 0; i < world.loadedEntityList.size(); i++)
	// {
	// final Entity entityplayer1 = (Entity)world.loadedEntityList.get(i);
	//
	// if(enty == null || enty.canEntityBeSeen(entityplayer1))
	// {
	// if(entityplayer1 instanceof EntityItem)
	// {
	// final double d5 = entityplayer1.getDistanceSq(enty.posX, enty.posY,
	// enty.posZ);
	// if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
	// {
	// d4 = d5;
	// entityplayer = entityplayer1;
	// }
	// }
	// }
	// }
	//
	// return entityplayer;
	// }
	//
	// public static int sqSpawn;
	public static IIcon texx[] = new IIcon[25];
	// public static boolean isOn;
	//
	//
	//
	// public static boolean hiveAlert = false;
	//
	// static
	// {
	// texx[0] = ModLoader.addOverride("/terrain.png", "/imgz/webbingr.png");
	// texx[1] = ModLoader.addOverride("/terrain.png", "/imgz/webbed.png");
	// texx[2] = ModLoader.addOverride("/terrain.png", "/imgz/hive.png");
	// texx[3] = ModLoader.addOverride("/terrain.png", "/imgz/spiderwand.png");
	// texx[4] = ModLoader.addOverride("/terrain.png", "/imgz/hivebottom.png");
	// texx[5] = ModLoader.addOverride("/terrain.png", "/imgz/anthill.png");
	// texx[6] = ModLoader.addOverride("/terrain.png", "/imgz/skullf.png");
	// texx[7] = ModLoader.addOverride("/terrain.png", "/imgz/skullb.png");
	// texx[8] = ModLoader.addOverride("/terrain.png", "/imgz/skulls.png");
	// texx[9] = ModLoader.addOverride("/terrain.png", "/imgz/skullt.png");
	// texx[10] = ModLoader.addOverride("/terrain.png", "/imgz/skullf1.png");
	// texx[11] = ModLoader.addOverride("/terrain.png", "/imgz/heart.png");
	// texx[12] = ModLoader.addOverride("/terrain.png", "/imgz/brain.png");
	// texx[13] = ModLoader.addOverride("/terrain.png", "/imgz/mandseeds.png");
	// texx[14] = ModLoader.addOverride("/terrain.png", "/imgz/lside.png");
	// texx[15] = ModLoader.addOverride("/terrain.png", "/imgz/ltop.png");
	// texx[16] = ModLoader.addOverride("/terrain.png", "/imgz/jside.png");
	// texx[17] = ModLoader.addOverride("/terrain.png", "/imgz/jface.png");
	// texx[18] = ModLoader.addOverride("/terrain.png", "/imgz/jtop.png");
	// texx[19] = ModLoader.addOverride("/terrain.png", "/imgz/octoball.png");
	// texx[20] = ModLoader.addOverride("/terrain.png", "/imgz/jackball.png");
	// texx[21] = ModLoader.addOverride("/terrain.png", "/imgz/royalblood.png");
	// texx[22] = ModLoader.addOverride("/terrain.png", "/imgz/stinger.png");
	// texx[23] = ModLoader.addOverride("/terrain.png",
	// "/imgz/poisonblock.png");
	// }
	
	// private final int generateWeb;
	
	public static String outputTxt = "";
	public static int id;
	public static int id2;
	public static boolean tMale;
	public static int idEnt2 = DJRead.readInt("/mods/SpiderQueen/Block_and_Item_IDs.txt", "Web_EntID", 25);
	public static int isMale = DJRead.readInt("/mods/SpiderQueen/Block_and_Item_IDs.txt", "KingMode", 0);
	public static int ZombieLike = DJRead.readInt("/mods/SpiderQueen/mobsettings.txt", "zombielike", 0);
	public static int SkeletonLike = DJRead.readInt("/mods/SpiderQueen/mobsettings.txt", "skeletonlike", 0);
	public static int CreeperLike = DJRead.readInt("/mods/SpiderQueen/mobsettings.txt", "creeperlike", 0);
	public static int HumanLike = DJRead.readInt("/mods/SpiderQueen/mobsettings.txt", "humanlike", 15);
	public static int AntLike = DJRead.readInt("/mods/SpiderQueen/mobsettings.txt", "antlike", 30);
	public static int SpiderLike = DJRead.readInt("/mods/SpiderQueen/mobsettings.txt", "spiderlike", 0);
	private RenderBlocks myRenderBlocks;
	private IBlockAccess blockAccess;
}
