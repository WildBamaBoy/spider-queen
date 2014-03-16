package spiderqueen.old.core;

import java.util.Map;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import spiderqueen.old.block.BlockAntHill;
import spiderqueen.old.block.BlockBeeHive;
import spiderqueen.old.block.BlockBrain;
import spiderqueen.old.block.BlockHeart;
import spiderqueen.old.block.BlockJack;
import spiderqueen.old.block.BlockLantern;
import spiderqueen.old.block.BlockMSeed;
import spiderqueen.old.block.BlockPoisonWeb;
import spiderqueen.old.block.BlockRoyalBlood;
import spiderqueen.old.block.BlockSmallWeb;
import spiderqueen.old.block.BlockSpiderBait;
import spiderqueen.old.block.BlockStinger;
import spiderqueen.old.block.BlockWebBed;
import spiderqueen.old.client.model.ModelHuman;
import spiderqueen.old.client.model.ModelSpiderQueen;
import spiderqueen.old.client.render.RenderAnt;
import spiderqueen.old.client.render.RenderBeetle;
import spiderqueen.old.client.render.RenderBoomSpider;
import spiderqueen.old.client.render.RenderCocoonAnt;
import spiderqueen.old.client.render.RenderCocoonChicken;
import spiderqueen.old.client.render.RenderCocoonCow;
import spiderqueen.old.client.render.RenderCocoonCreeper;
import spiderqueen.old.client.render.RenderCocoonGatherer;
import spiderqueen.old.client.render.RenderCocoonHuman;
import spiderqueen.old.client.render.RenderCocoonPig;
import spiderqueen.old.client.render.RenderCocoonQueenBee;
import spiderqueen.old.client.render.RenderCocoonSheep;
import spiderqueen.old.client.render.RenderCocoonSkeleton;
import spiderqueen.old.client.render.RenderCocoonWarrior;
import spiderqueen.old.client.render.RenderCocoonWasp;
import spiderqueen.old.client.render.RenderCocoonWolf;
import spiderqueen.old.client.render.RenderCocoonZombie;
import spiderqueen.old.client.render.RenderCustomball;
import spiderqueen.old.client.render.RenderESpiderQueen;
import spiderqueen.old.client.render.RenderFCreeper;
import spiderqueen.old.client.render.RenderFly;
import spiderqueen.old.client.render.RenderFriendlySpider;
import spiderqueen.old.client.render.RenderFurrySpider;
import spiderqueen.old.client.render.RenderGatherer;
import spiderqueen.old.client.render.RenderGhastEgg;
import spiderqueen.old.client.render.RenderJack;
import spiderqueen.old.client.render.RenderMand;
import spiderqueen.old.client.render.RenderMinighast;
import spiderqueen.old.client.render.RenderOctopus;
import spiderqueen.old.client.render.RenderPoisonSpider;
import spiderqueen.old.client.render.RenderQueenBee;
import spiderqueen.old.client.render.RenderSWeb;
import spiderqueen.old.client.render.RenderSpiderEgg;
import spiderqueen.old.client.render.RenderThinSpider;
import spiderqueen.old.client.render.RenderTinySpider;
import spiderqueen.old.client.render.RenderVines;
import spiderqueen.old.client.render.RenderWarrior;
import spiderqueen.old.client.render.RenderWasp;
import spiderqueen.old.client.render.RenderWeb;
import spiderqueen.old.client.render.RenderYuki;
import spiderqueen.old.entity.EntityAnt;
import spiderqueen.old.entity.EntityBeetle;
import spiderqueen.old.entity.EntityBoomSpider;
import spiderqueen.old.entity.EntityBoomball;
import spiderqueen.old.entity.EntityCocoon;
import spiderqueen.old.entity.EntityCocoonAnt;
import spiderqueen.old.entity.EntityCocoonCow;
import spiderqueen.old.entity.EntityCocoonCreeper;
import spiderqueen.old.entity.EntityCocoonGatherer;
import spiderqueen.old.entity.EntityCocoonHuman;
import spiderqueen.old.entity.EntityCocoonPig;
import spiderqueen.old.entity.EntityCocoonQueenBee;
import spiderqueen.old.entity.EntityCocoonSheep;
import spiderqueen.old.entity.EntityCocoonSkeleton;
import spiderqueen.old.entity.EntityCocoonWarrior;
import spiderqueen.old.entity.EntityCocoonWasp;
import spiderqueen.old.entity.EntityCocoonWolf;
import spiderqueen.old.entity.EntityCocoonZombie;
import spiderqueen.old.entity.EntityESpiderQueen;
import spiderqueen.old.entity.EntityFCreeper;
import spiderqueen.old.entity.EntityFSkeleton;
import spiderqueen.old.entity.EntityFZombie;
import spiderqueen.old.entity.EntityFly;
import spiderqueen.old.entity.EntityFriendlySpider;
import spiderqueen.old.entity.EntityFurrySpider;
import spiderqueen.old.entity.EntityGatherer;
import spiderqueen.old.entity.EntityGhastEgg;
import spiderqueen.old.entity.EntityHuman;
import spiderqueen.old.entity.EntityJack;
import spiderqueen.old.entity.EntityJackball;
import spiderqueen.old.entity.EntityMand;
import spiderqueen.old.entity.EntityMinighast;
import spiderqueen.old.entity.EntityNewAnimal;
import spiderqueen.old.entity.EntityOctoball;
import spiderqueen.old.entity.EntityOctopus;
import spiderqueen.old.entity.EntityPoisonSpider;
import spiderqueen.old.entity.EntityQueenBee;
import spiderqueen.old.entity.EntityRedAnt;
import spiderqueen.old.entity.EntitySWeb;
import spiderqueen.old.entity.EntitySpiderEgg;
import spiderqueen.old.entity.EntityThinSpider;
import spiderqueen.old.entity.EntityTinySpider;
import spiderqueen.old.entity.EntityVines;
import spiderqueen.old.entity.EntityWarrior;
import spiderqueen.old.entity.EntityWasp;
import spiderqueen.old.entity.EntityWeb;
import spiderqueen.old.entity.EntityYuki;
import spiderqueen.old.item.ItemCocoon;
import spiderqueen.old.item.ItemCocoonAnt;
import spiderqueen.old.item.ItemCocoonCow;
import spiderqueen.old.item.ItemCocoonCreeper;
import spiderqueen.old.item.ItemCocoonGatherer;
import spiderqueen.old.item.ItemCocoonHuman;
import spiderqueen.old.item.ItemCocoonPig;
import spiderqueen.old.item.ItemCocoonQueenBee;
import spiderqueen.old.item.ItemCocoonSheep;
import spiderqueen.old.item.ItemCocoonSkeleton;
import spiderqueen.old.item.ItemCocoonWarrior;
import spiderqueen.old.item.ItemCocoonWasp;
import spiderqueen.old.item.ItemCocoonWolf;
import spiderqueen.old.item.ItemCocoonZombie;
import spiderqueen.old.item.ItemGhastEgg;
import spiderqueen.old.item.ItemNectar;
import spiderqueen.old.item.ItemPWeb;
import spiderqueen.old.item.ItemRareFruit;
import spiderqueen.old.item.ItemSWeb;
import spiderqueen.old.item.ItemSpiderEgg;
import spiderqueen.old.item.ItemWeb;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
//Creepers, Zombies - 5 Webbing 2 Eggs.
//Skeletons - 0 Webbing, 3 Eggs.

public class mod_SpiderQueen extends DJBaseMod
{    
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
	
    public mod_SpiderQueen()
    {
		//DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "zombielike", ZombieLike);
		
		id = ModLoader.getUniqueBlockModelID(this, true);
		id2 = ModLoader.getUniqueBlockModelID(this, true);
		
		generateWeb = 0;
		sqSpawn = 0;
		isOn = true;

    	ModLoader.registerTileEntity(TileEntityFlower.class, "Flower");
    	ModLoader.registerTileEntity(TileEntityHBait.class, "HBait");
		
        ModLoader.registerBlock(beehive);
        ModLoader.addName(beehive, "Bee Hive");
    	ModLoader.registerTileEntity(TileEntityBeeHive.class, "BeeHive");
		
		ModLoader.registerBlock(anthill);
        ModLoader.addName(anthill, "Ant Hill");
    	ModLoader.registerTileEntity(TileEntityAntHill.class, "AntHill");
		
		ModLoader.registerBlock(bskull);
        ModLoader.addName(bskull, "Human Skull");
		
		ModLoader.registerBlock(heart);
        ModLoader.addName(heart, "Human Heart");
		
		ModLoader.registerBlock(brain);
        ModLoader.addName(brain, "Human Brain");

		ModLoader.registerBlock(royalblood);
        ModLoader.addName(royalblood, "Royal Blood");
		
        ModLoader.registerBlock(spiderbait);
        ModLoader.addName(spiderbait, "Spider Bait");
    	ModLoader.registerTileEntity(TileEntitySpiderBait.class, "SpiderBait");

        ModLoader.registerBlock(mseeds);
        ModLoader.addName(mseeds, "Mandragora Seeds");
    	
		ModLoader.registerBlock(bjack);
        ModLoader.addName(bjack, "Jack");
		
		ModLoader.registerBlock(lantern);
        ModLoader.addName(lantern, "Lantern");
		
		ModLoader.registerBlock(stinger);
        ModLoader.addName(stinger, "Stinger");
    	
		ModLoader.addName(itemSWeb, "Webslinger");
		ModLoader.addName(itemWeb, "Web");
		ModLoader.addName(itemPWeb, "Poisonous Web");
    	ModLoader.registerEntityID(EntityWeb.class, "Web", ModLoader.getUniqueEntityId());
		
    	ModLoader.registerEntityID(EntityVines.class, "Vines", ModLoader.getUniqueEntityId());


        ModLoader.registerBlock(smallWeb);
        ModLoader.addName(smallWeb, "Small Web");
		
		ModLoader.registerBlock(poisonWeb);
        ModLoader.addName(poisonWeb, "Poison Web");
		
        ModLoader.registerBlock(webBed);
        ModLoader.addName(webBed, "Web Bed");
		
		ModLoader.registerEntityID(EntityBoomSpider.class, "Boom Spider", ModLoader.getUniqueEntityId());     
		ModLoader.registerEntityID(EntityPoisonSpider.class, "Poison Spider", ModLoader.getUniqueEntityId());     
		ModLoader.registerEntityID(EntityThinSpider.class, "Thin Spider", ModLoader.getUniqueEntityId());     
		ModLoader.registerEntityID(EntityFriendlySpider.class, "Friendly Spider", ModLoader.getUniqueEntityId());    
		ModLoader.registerEntityID(EntityTinySpider.class, "Tiny Spider", ModLoader.getUniqueEntityId());
		ModLoader.registerEntityID(EntityFurrySpider.class, "Furry Spider", ModLoader.getUniqueEntityId());
		
		ModLoader.registerEntityID(EntityGatherer.class, "Gatherer", ModLoader.getUniqueEntityId());     
		ModLoader.registerEntityID(EntityWarrior.class, "Warrior", ModLoader.getUniqueEntityId());     
		ModLoader.registerEntityID(EntityMinighast.class, "Minighast", ModLoader.getUniqueEntityId());     
		ModLoader.registerEntityID(EntityQueenBee.class, "QueenBee", ModLoader.getUniqueEntityId());     
		ModLoader.registerEntityID(EntityWasp.class, "Wasp", ModLoader.getUniqueEntityId());       
		ModLoader.registerEntityID(EntityYuki.class, "Yuki", ModLoader.getUniqueEntityId());       
		ModLoader.registerEntityID(EntityBeetle.class, "Beetle", ModLoader.getUniqueEntityId());      
		ModLoader.registerEntityID(EntityAnt.class, "Ant", ModLoader.getUniqueEntityId());       
		ModLoader.registerEntityID(EntityFly.class, "Fly", ModLoader.getUniqueEntityId());      
		ModLoader.registerEntityID(EntityRedAnt.class, "RAnt", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityHuman.class, "Human", ModLoader.getUniqueEntityId());      
		ModLoader.registerEntityID(EntityMand.class, "Mand", ModLoader.getUniqueEntityId());      
		ModLoader.registerEntityID(EntityESpiderQueen.class, "ESpiderQueen", ModLoader.getUniqueEntityId()); 
		ModLoader.registerEntityID(EntityOctopus.class, "Octopus", ModLoader.getUniqueEntityId());      
		ModLoader.registerEntityID(EntityOctoball.class, "Octoball", ModLoader.getUniqueEntityId());    
		ModLoader.registerEntityID(EntityBoomball.class, "Boomball", ModLoader.getUniqueEntityId());    
		ModLoader.registerEntityID(EntityJackball.class, "Jackball", ModLoader.getUniqueEntityId());      
		ModLoader.registerEntityID(EntityJack.class, "Jack", ModLoader.getUniqueEntityId());      
		//ModLoader.registerEntityID(EntityChestSpider.class, "ChestSpider", ModLoader.getUniqueEntityId());     
		
		
		ModLoader.registerEntityID(EntitySpiderEgg.class, "Spider Egg", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityGhastEgg.class, "Ghast Egg", ModLoader.getUniqueEntityId());   
		
		ModLoader.registerEntityID(EntityCocoon.class, "Cocooned Chicken", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonCow.class, "Cocooned Cow", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonHuman.class, "Cocooned Human", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonCreeper.class, "Cocooned Creeper", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonPig.class, "Cocooned Pig", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonSheep.class, "Cocooned Sheep", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonSkeleton.class, "Cocooned Skeleton", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonZombie.class, "Cocooned Zombie", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonQueenBee.class, "Cocooned Queen Bee", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonWarrior.class, "Cocooned Warrior", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonGatherer.class, "Cocooned Gatherer", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonAnt.class, "Cocooned Ant", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityCocoonWasp.class, "Cocooned Wasp", ModLoader.getUniqueEntityId()); 
		ModLoader.registerEntityID(EntityCocoonWolf.class, "Cocooned Wolf", ModLoader.getUniqueEntityId());   
		
		ModLoader.registerEntityID(EntityFCreeper.class, "fcreeper", ModLoader.getUniqueEntityId());   
		ModLoader.registerEntityID(EntityFSkeleton.class, "fzombie", ModLoader.getUniqueEntityId());   
		
		ModLoader.addRecipe(new ItemStack(itemWeb, 1), new Object[] {
            "SS ", "S  ", Character.valueOf('S'), Item.silk
        });
		ModLoader.addRecipe(new ItemStack(itemSWeb, 1), new Object[] {
            "DS ", "SS ", "  S", Character.valueOf('S'), Item.silk, Character.valueOf('D'), stinger
        });
		ModLoader.addRecipe(new ItemStack(spiderbait, 1), new Object[] {
            "GTG", " S ", " S ", Character.valueOf('S'), Item.stick, Character.valueOf('G'), Block.glass, Character.valueOf('T'), Block.torchWood
        });
		ModLoader.addRecipe(new ItemStack(Block.cobblestoneMossy, 1), new Object[] {
            "GGG", "GCG", "GGG", Character.valueOf('C'), Block.cobblestone, Character.valueOf('G'), itemWeb, Character.valueOf('T'), Block.cobblestone
        });
		ModLoader.addRecipe(new ItemStack(itemPWeb, 6), new Object[] {
            "SSS", "SPS", "SSS", Character.valueOf('S'), itemWeb, Character.valueOf('P'), itemPWeb
        });
		
		/*
		ModLoader.addRecipe(new ItemStack(itemWeb, 64), new Object[] {
            "D ", "D ", Character.valueOf('D'), Block.dirt
        });
		ModLoader.addRecipe(new ItemStack(Block.obsidian, 64), new Object[] {
            "DD", "  ", Character.valueOf('D'), Block.dirt
        });
		ModLoader.addRecipe(new ItemStack(Item.flintAndSteel, 64), new Object[] {
            "D ", " D", Character.valueOf('D'), Block.dirt
        });
		ModLoader.addRecipe(new ItemStack(itemCocoonCow, 64), new Object[] {
            "DD", "D ", Character.valueOf('D'), Block.dirt
        });
		ModLoader.addRecipe(new ItemStack(Item.swordSteel, 64), new Object[] {
            " D", "DD", Character.valueOf('D'), Block.dirt
        });
		ModLoader.addRecipe(new ItemStack(Item.bow, 64), new Object[] {
            "DD", "DD", Character.valueOf('D'), Block.dirt
        });
		
		ModLoader.addRecipe(new ItemStack(Block.dirt, 64), new Object[] {
            "D ", "  ", Character.valueOf('D'), Block.dirt
        });*/
		
		

	ModLoader.setInGameHook(this, true, false);

    ModLoader.addName(itemWeb, "Web");
    ModLoader.addName(itemPWeb, "Poisonous Web");
	ModLoader.addName(itemSpiderEgg, "Spider Egg");
	ModLoader.addName(itemGhastEgg, "Ghast Egg");
	ModLoader.addName(itemNectar, "Nectar");
	ModLoader.addName(itemRareFruit, "Rare Fruit");
	ModLoader.addName(itemSkull, "Human Skull");
	ModLoader.addName(itemCocoon, "Cocooned Chicken");
	ModLoader.addName(itemCocoonPig, "Cocooned Pig");
	ModLoader.addName(itemCocoonSheep, "Cocooned Sheep");
	ModLoader.addName(itemCocoonCreeper, "Cocooned Creeper");
	ModLoader.addName(itemCocoonZombie, "Cocooned Zombie");
	ModLoader.addName(itemCocoonSkeleton, "Cocooned Skeleton");
	ModLoader.addName(itemCocoonCow, "Cocooned Cow");
	ModLoader.addName(itemCocoonHuman, "Cocooned Human");
	ModLoader.addName(itemCocoonGatherer, "Cocooned Gatherer Bee");
	ModLoader.addName(itemCocoonWarrior, "Cocooned Warrior Bee");
	ModLoader.addName(itemCocoonQueenBee, "Cocooned Queen Bee");
	ModLoader.addName(itemCocoonWasp, "Cocooned Wasp");
	ModLoader.addName(itemCocoonAnt, "Cocooned Ant");
	ModLoader.addName(itemCocoonWolf, "Cocooned Wolf");
	}
	
	public void load() {}
	
	public String getModName() { return "spiderqueen"; }
	public String getVersion() { return "v5.3"; }
	
	public static void setTMale(boolean b)
	{
		tMale = b;
	}
	
	public void spawnAlly(World world, EntityLiving newent, EntityLiving play)
	{
		newent.posX = play.posX - MathHelper.cos((newent.rotationYaw / 180F) * 3.141593F) * 0.16F;
		newent.posY = play.posY - 0.40000000149011612D;
		newent.posZ = play.posZ - MathHelper.sin((newent.rotationYaw / 180F) * 3.141593F) * 0.16F;
		newent.setPosition(newent.posX, newent.posY, newent.posZ);
		newent.setRotation(newent.rotationYaw - 90F,0F);
		newent.spiderfriend = true;
		newent.health = 70;
		world.spawnEntityInWorld(newent);
		newent.setPosition(play.posX,play.posY,play.posZ);
	}
	
    public boolean onTickInGame(float f, Minecraft mc)
    {
		super.onTickInGame(f,mc);
		if(mc != null)
		{
			if(mc.thePlayer != null)
			{
				if(mod_SpiderQueen.ZombieLike > 20) 
				{ 
					outputTxt = "The zombies respect you. They have sent you one of their own.";
					spawnAlly(mc.theWorld, new EntityZombie(mc.theWorld), mc.thePlayer);
					mod_SpiderQueen.ZombieLike = 11; 
					DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "zombielike", mod_SpiderQueen.ZombieLike);
				}
				if(mod_SpiderQueen.CreeperLike > 20) 
				{ 
					outputTxt = "The creepers respect you. They have sent you one of their own.";
					spawnAlly(mc.theWorld, new EntityCreeper(mc.theWorld), mc.thePlayer);
					mod_SpiderQueen.CreeperLike = 11; 
					DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "creeperlike", mod_SpiderQueen.CreeperLike);
				}
				if(mod_SpiderQueen.SkeletonLike > 20) 
				{ 
					outputTxt = "The skeletons respect you. They have sent you one of their own.";
					spawnAlly(mc.theWorld, new EntitySkeleton(mc.theWorld), mc.thePlayer);
					mod_SpiderQueen.SkeletonLike = 11; 
					DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "skeletonlike", mod_SpiderQueen.SkeletonLike);
				}
				if(mod_SpiderQueen.HumanLike > 25) 
				{ 
					outputTxt = "The humans respect you. They have sent you one of their own.";
					spawnAlly(mc.theWorld, new EntityHuman(mc.theWorld), mc.thePlayer);
					mod_SpiderQueen.HumanLike = 11; 
					DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "humanlike", mod_SpiderQueen.HumanLike);
				}
				
				/*if(generateWeb < 1)
				{
					generateWeb = 1800;
					boolean change = false;
					InventoryPlayer pinv = mc.thePlayer.inventory;
					if(pinv != null)
					{
						for(int ui = 0; ui < 4; ui++)
						{
							if(change == false & pinv.armorItemInSlot(ui) == null)
							{
								pinv.armorInventory[ui] = new ItemStack(Item.silk);
								change = true;
							}
							if(pinv.armorItemInSlot(ui) != null)
							{
								if(change == false & pinv.armorItemInSlot(ui).getItem() == Item.silk)
								{
									int tdmg = pinv.armorInventory[ui].stackSize;
									if(tdmg < 3)
									{
										tdmg++;
										pinv.armorInventory[ui].stackSize = tdmg;
									}
									else
									{
										pinv.armorInventory[ui] = new ItemStack(itemWeb);
									}
									change = true;
								}
							}
						}
						
						if(!change)
						{
							int pikui = -1;
							int tmpui = 7;
							
							for(int ui = 0; ui < 4; ui++)
							{
								if(pinv.armorItemInSlot(ui) != null)
								{
									if(change == false & pinv.armorItemInSlot(ui).getItem() == itemWeb)
									{
										int tdmg = pinv.armorInventory[ui].stackSize;
										if(tdmg < tmpui)
										{
											tmpui = tdmg;
											pikui = ui;
										}
									}
								}
							}
							
							if(pikui > -1)
							{
								int tdmg = pinv.armorInventory[pikui].stackSize;
								if(tdmg < 6)
								{
									tdmg++;
									pinv.armorInventory[pikui].stackSize = tdmg;
									generateWeb = generateWeb * 3;
									change = true;
								}
							}
						}
					}	
				}
				else
				{
					generateWeb--;
				}*/
				
				if(outputTxt != "")
				{
					mc.thePlayer.addChatMessage(outputTxt);
					outputTxt = "";
				}
				if(sqSpawn > 0)
				{
					sqSpawn++;
					
					Random nr = new Random();
					if(sqSpawn == 2 || sqSpawn == 200 || sqSpawn == 300 || sqSpawn == 400 || sqSpawn == 600)
					{
						int nposX = (int)mc.thePlayer.posX + nr.nextInt(96) - 48;
						int nposZ = (int)mc.thePlayer.posZ + nr.nextInt(96) - 48;
						int nposY = mc.theWorld.getTopSolidOrLiquidBlock(nposX, nposZ);
						
						EntitySpider entSpider = new EntitySpider(mc.theWorld);
						entSpider.posX = nposX; entSpider.posY = nposY; entSpider.posZ = nposZ;
						entSpider.setPosition(entSpider.posX, entSpider.posY, entSpider.posZ);	
						mc.theWorld.spawnEntityInWorld(entSpider);	
						entSpider.setRevengeTarget((EntityLiving)mc.thePlayer);
					}
					if(sqSpawn > 899)
					{
						int nposX = (int)mc.thePlayer.posX + nr.nextInt(64) - 32;
						int nposZ = (int)mc.thePlayer.posZ + nr.nextInt(64) - 32;
						int nposY = mc.theWorld.getTopSolidOrLiquidBlock(nposX, nposZ);
						
						EntityESpiderQueen entSpiderQ = new EntityESpiderQueen(mc.theWorld);
						entSpiderQ.posX = nposX; entSpiderQ.posY = nposY; entSpiderQ.posZ = nposZ;
						entSpiderQ.setPosition(entSpiderQ.posX, entSpiderQ.posY, entSpiderQ.posZ);	
						mc.theWorld.spawnEntityInWorld(entSpiderQ);
						entSpiderQ.setRevengeTarget((EntityLiving)mc.thePlayer);
						
						for (int iu = 0; iu < 2; iu++) 
						{ 
							nposX++;
							nposY = mc.theWorld.getTopSolidOrLiquidBlock(nposX, nposZ);
							
							EntitySpider entSpider = new EntitySpider(mc.theWorld);
							entSpider.posX = nposX; entSpider.posY = nposY; entSpider.posZ = nposZ;
							entSpider.setPosition(entSpider.posX, entSpider.posY, entSpider.posZ);	
							mc.theWorld.spawnEntityInWorld(entSpider);	
							entSpider.setRevengeTarget((EntityLiving)mc.thePlayer);
						}
						
						sqSpawn = 0;
					}
					
				}
			}
		}
		
		return true;
	}
	
    public static double getDistanceSq(EntityGatherer cr, double d, double d1, double d2)
    {
        double d3 = cr.posX - d;
        double d4 = cr.posY - d1;
        double d5 = cr.posZ - d2;
        return d3 * d3 + d4 * d4 + d5 * d5;
    }	

	public static void likeChange(String type, int amt)
	{
		int hmt = 0; if(type == "human") { hmt = amt; }
		
		if(type == "skeleton")
		{
			if(mod_SpiderQueen.SkeletonLike < 10 & mod_SpiderQueen.SkeletonLike + amt > 9)
			{
				//outputTxt = "The skeletons have accepted your offering. They give you one of their own.";
			}
			if(mod_SpiderQueen.SkeletonLike > 9 & mod_SpiderQueen.SkeletonLike + amt < 10)
			{
				//outputTxt = "You have broken your truce with the skeletons.";
			}
			
			mod_SpiderQueen.SkeletonLike = mod_SpiderQueen.SkeletonLike + amt;
			if(mod_SpiderQueen.SkeletonLike < 0) { mod_SpiderQueen.SkeletonLike = 0; }
			if(mod_SpiderQueen.SkeletonLike > 12) { mod_SpiderQueen.SkeletonLike = 0; }
			DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "skeletonlike", mod_SpiderQueen.SkeletonLike);
			
			if(amt < 0) { hmt = Math.abs(amt); type = "human"; }
		}
		
		if(type == "spider")
		{
			if(mod_SpiderQueen.SpiderLike < -10)
			{
				mod_SpiderQueen.SpiderLike = 5;
				outputTxt = "A spider queen has noticed you slaying her relatives. She's not too happy.";
				sqSpawn = 1;
			}
			
			mod_SpiderQueen.SpiderLike = mod_SpiderQueen.SpiderLike + amt;
			DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "spiderlike", mod_SpiderQueen.SpiderLike);
		}
		
		if(type == "zombie")
		{
			if(mod_SpiderQueen.ZombieLike < 10 & mod_SpiderQueen.ZombieLike + amt > 9)
			{
				//outputTxt = "The zombies have accepted your offering. They are calling for a truce.";
			}
			if(mod_SpiderQueen.ZombieLike > 9 & mod_SpiderQueen.ZombieLike + amt < 10)
			{
				//outputTxt = "You have broken your truce with the zombies.";
			}
			
			mod_SpiderQueen.ZombieLike = mod_SpiderQueen.ZombieLike + amt;
			if(mod_SpiderQueen.ZombieLike < 0) { mod_SpiderQueen.ZombieLike = 0; }
			if(mod_SpiderQueen.ZombieLike > 12) { mod_SpiderQueen.ZombieLike = 0; }
			DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "zombielike", mod_SpiderQueen.ZombieLike);
			if(amt < 0) { hmt = Math.abs(amt); type = "human"; }
		}
		
		if(type == "creeper")
		{
			if(mod_SpiderQueen.CreeperLike < 10 & mod_SpiderQueen.CreeperLike + amt > 9)
			{
				//outputTxt = "The creepers have accepted your offering. They are calling for a truce.";
			}
			if(mod_SpiderQueen.CreeperLike > 9 & mod_SpiderQueen.CreeperLike + amt < 10)
			{
				//outputTxt = "You have broken your truce with the creepers.";
			}
			
			mod_SpiderQueen.CreeperLike = mod_SpiderQueen.CreeperLike + amt;
			if(mod_SpiderQueen.CreeperLike < 0) { mod_SpiderQueen.CreeperLike = 0; }
			if(mod_SpiderQueen.CreeperLike > 12) { mod_SpiderQueen.CreeperLike = 0; }
			DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "creeperlike", mod_SpiderQueen.CreeperLike);
			if(amt < 0) { hmt = Math.abs(amt); type = "human"; }
		}
		
		if(type == "human")
		{
			if(mod_SpiderQueen.HumanLike < 10 & mod_SpiderQueen.HumanLike + hmt > 9)
			{
				//outputTxt = "Your attack on enemy mobs has earned the humans trust.";
			}
			if(mod_SpiderQueen.HumanLike > 9 & mod_SpiderQueen.HumanLike + hmt < 10)
			{
				//outputTxt = "You have broken your truce with the humans.";
			}
			
			mod_SpiderQueen.HumanLike = mod_SpiderQueen.HumanLike + hmt;
			if(mod_SpiderQueen.HumanLike < 0) { mod_SpiderQueen.HumanLike = 0; }
			if(mod_SpiderQueen.HumanLike > 50) { mod_SpiderQueen.HumanLike = 50; }
			DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "humanlike", mod_SpiderQueen.HumanLike);
		}

		if(type == "ant")
		{
			if(mod_SpiderQueen.AntLike < 24 & mod_SpiderQueen.AntLike + amt > 24)
			{
				outputTxt = "The black ants have seen you fight against the red, and are calling for a truce.";
			}
			if(mod_SpiderQueen.AntLike > 25 & mod_SpiderQueen.AntLike + amt < 25)
			{
				outputTxt = "The red ants have seen you fight against the black, and are calling for a truce.";
			}
			
			mod_SpiderQueen.AntLike = mod_SpiderQueen.AntLike + amt;
			if(mod_SpiderQueen.AntLike < 0) { mod_SpiderQueen.AntLike = 0; }
			if(mod_SpiderQueen.AntLike > 50) { mod_SpiderQueen.AntLike = 50; }
			DJRead.writeInt("/mods/SpiderQueen/mobsettings.txt", "antlike", mod_SpiderQueen.AntLike);
		}

	}
	
	public static double getDistance3d(double x, double y, double z, double d, double d1, double d2)
    {
        double d3 = x - d;
        double d4 = y - d1;
        double d5 = z - d2;
        return d3 * d3 + d4 * d4 + d5 * d5;
    }	

    public static TileEntity getClosestBait(World worldObj, EntityLiving cr, double d, double d1, double d2, double d3)
    {
        double d4 = -1D;
        TileEntity entityplayer = null;
		
		
        for(int i = 0; i < worldObj.loadedTileEntityList.size(); i++)
        {
			boolean ssbait = false;
			if(cr instanceof EntityLiving)
			{
				EntityLiving el = (EntityLiving)cr;
				if(el.spiderfriend & worldObj.loadedTileEntityList.get(i) instanceof TileEntitySpiderBait)
				{
					ssbait = true;
				}
			}
			
        	if(cr instanceof EntityGatherer & worldObj.loadedTileEntityList.get(i) instanceof TileEntityFlower ||
			ssbait)
        	{
        		TileEntity entityplayer1 = (TileEntity)worldObj.loadedTileEntityList.get(i);
        		
	        			double d33 = entityplayer1.xCoord - d;
	        			double d44 = entityplayer1.yCoord - d1;
	        			double d55 = entityplayer1.zCoord - d2;
	                
	        			double d5 = d33 * d33 + d44 * d44 + d55 * d55;
		            
						int blockI = worldObj.getBlockId(entityplayer1.xCoord,entityplayer1.yCoord,entityplayer1.zCoord);
						
							if(!(cr instanceof EntityGatherer & worldObj.loadedTileEntityList.get(i) instanceof TileEntityFlower) || (blockI == Block.plantYellow.blockID || blockI == Block.plantRed.blockID))
							{
								if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
								{
									if(cr.getAte() == 0 & d5 < 4)
									{
										cr.setAte(700);
										int mselect = 0;				
									}
									d4 = d5;
									entityplayer = entityplayer1;
								}
							}
						

						
        	}
        }

        return (TileEntity) entityplayer;
    }
	
	
	
    public static TileEntity getClosestHBait(World worldObj, EntityMob cr, double d, double d1, double d2, double d3)
    {
        double d4 = -1D;
        TileEntity entityplayer = null;
		
		
        for(int i = 0; i < worldObj.loadedTileEntityList.size(); i++)
        {
        	if(worldObj.loadedTileEntityList.get(i) instanceof TileEntityHBait)
        	{
        		TileEntity entityplayer1 = (TileEntity)worldObj.loadedTileEntityList.get(i);
        		
	        			double d33 = entityplayer1.xCoord - d;
	        			double d44 = entityplayer1.yCoord - d1;
	        			double d55 = entityplayer1.zCoord - d2;
	                
	        			double d5 = d33 * d33 + d44 * d44 + d55 * d55;
		            
						int blockI = worldObj.getBlockId(entityplayer1.xCoord,entityplayer1.yCoord,entityplayer1.zCoord);
						
							System.out.print("!!\n");
							d4 = d5;
							entityplayer = entityplayer1;
        	}
        }

        return (TileEntity) entityplayer;
    }
	
    public static Entity getClosestBee(World worldObj, EntityLiving cr, double d, double d1, double d2, double d3)
    {
        double d4 = -1D;
        Entity entityplayer = null;
		
		
        for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
        	if(worldObj.loadedEntityList.get(i) instanceof EntityGatherer)
        	{
        		EntityGatherer entityplayer1 = (EntityGatherer)worldObj.loadedEntityList.get(i);
        		
	        			double d33 = entityplayer1.posX - d;
	        			double d44 = entityplayer1.posY - d1;
	        			double d55 = entityplayer1.posZ - d2;
	                
	        			double d5 = d33 * d33 + d44 * d44 + d55 * d55;
		            
							if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
							{
								if(cr.getAte() == 0 & d5 < 5)
								{
									cr.setAte(500);
									int mselect = 0;				
								}
								d4 = d5;
								entityplayer = entityplayer1;
							}
        	}
        }

        return entityplayer;
    }
	
    public static void pissOffBees(World worldObj, Entity attkr, double d, double d1, double d2, double d3)
    {
		if(attkr == null) { attkr = worldObj.getClosestPlayer(d,d1,d2, 64); }
		
        for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
        	if(worldObj.loadedEntityList.get(i) instanceof EntityWarrior || worldObj.loadedEntityList.get(i) instanceof EntityQueenBee)
        	{
        		EntityNewAnimal entityplayer1 = (EntityNewAnimal)worldObj.loadedEntityList.get(i);
        		
	        			double d33 = entityplayer1.posX - d;
	        			double d44 = entityplayer1.posY - d1;
	        			double d55 = entityplayer1.posZ - d2;
	                
	        			double d5 = d33 * d33 + d44 * d44 + d55 * d55;
		            
							if((d3 < 0.0D || d5 < d3 * d3) & entityplayer1.getEntityToAttack() == null)
							{
								entityplayer1.entityToAttack = attkr;
							}
        	}
        }
    }
	
	public static void pissOffHumans(World worldObj, Entity attkr, double d, double d1, double d2, double d3)
    {
		if(attkr == null) { attkr = worldObj.getClosestPlayer(d,d1,d2, 64); }
		if(attkr instanceof EntityHuman) { return; }
		
        for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
        	if(worldObj.loadedEntityList.get(i) instanceof EntityHuman)
        	{
        		EntityHuman entityplayer1 = (EntityHuman)worldObj.loadedEntityList.get(i);
        		
	        			double d33 = entityplayer1.posX - d;
	        			double d44 = entityplayer1.posY - d1;
	        			double d55 = entityplayer1.posZ - d2;
	                
	        			double d5 = d33 * d33 + d44 * d44 + d55 * d55;
		            
							if((d3 < 0.0D || d5 < d3 * d3) & entityplayer1.getEntityToAttack() == null)
							{
								entityplayer1.entityToAttack = (EntityLiving)attkr;
							}
        	}
        }
    }
	
    public static TileEntity getClosestBaitToEntity(World world, EntityLiving entity, double d)
    {
        return getClosestBait(world, entity, entity.posX, entity.posY, entity.posZ, d);
    }
	
	public static Entity getClosestBeeToEntity(World world, EntityLiving entity, double d)
    {
        return getClosestBee(world, entity, entity.posX, entity.posY, entity.posZ, d);
    }

	
    public static float updateRRotation(float f, float f1, float f2)
    {
        float f3;
        for(f3 = f1 - f; f3 < -180F; f3 += 360F) { }
        for(; f3 >= 180F; f3 -= 360F) { }
        if(f3 > f2)
        {
            f3 = f2;
        }
        if(f3 < -f2)
        {
            f3 = -f2;
        }
        return f + f3;
    }
	
    public static void faceTEntity(EntityLiving cr, TileEntity entity, float f)
    {
        double d = entity.xCoord - cr.posX;
        double d2 = entity.zCoord - cr.posZ;
        double d1;
        boolean scared = false;
        
            d1 = (entity.yCoord) - (cr.posY + (double)(cr.getEyeHeight()));
       
        double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
        float f1 = (float)((Math.atan2(d2, d) * 180D) / 3.1415927410125732D) - 90F;
        float f2 = (float)((Math.atan2(d1, d3) * 180D) / 3.1415927410125732D);
        if(scared) { f1 = f1 + 180F; }
        cr.rotationPitch = -updateRRotation(cr.rotationPitch, f2, f);
        cr.rotationYaw = updateRRotation(cr.rotationYaw, f1, f);
    }
	
    public static TileEntity findBaitToAttack(World world, EntityLiving cr)
    {
    	TileEntity entityplayer = getClosestBaitToEntity(world, cr, 64D);
        if(entityplayer != null)
        {
            return entityplayer;
        } else
        {
            return null;
        }
    }
	
	 public static Entity findBeeToAttack(World world, EntityLiving cr)
    {
    	Entity entityplayer = getClosestBeeToEntity(world, cr, 32D);
        if(entityplayer != null)
        {
            return entityplayer;
        } else
        {
            return null;
        }
    }
	
	public boolean isWebBreak(int i, int j, int k)
	{
		if(blockAccess.isBlockOpaqueCube(i, j, k) || 
		blockAccess.getBlockId(i,j,k) == Block.leaves.blockID || 
		blockAccess.getBlockId(i,j,k) == Block.glass.blockID || 
		blockAccess.getBlockId(i,j,k) == Block.web.blockID || 
		blockAccess.getBlockId(i,j,k) == Block.fence.blockID || 
		blockAccess.getBlockId(i,j,k) == Block.mobSpawner.blockID)
		{ return true; }
		
		return false;
	}
	
	public boolean renderWorldBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l)
	{
		myRenderBlocks = renderblocks;
        blockAccess = iblockaccess;
		if(l == id)
		{
			return renderBlockSmallWeb(block,i,j,k);
		}
		
		return false;
	}
	
    public boolean renderBlockSmallWeb(Block block, int i, int j, int k)
    {
        Tessellator tessellator = Tessellator.instance;
        int l = blockAccess.getBlockMetadata(i, j, k);
        int i1 = block.getBlockTextureFromSideAndMetadata(1, l);
		
        float f = block.getBlockBrightness(blockAccess, i, j, k);
		
        tessellator.setColorOpaque_F(f, f, f);
        int j1 = (i1 & 0xf) << 4;
        int k1 = i1 & 0xf0;
        double d = (float)j1 / 256F;
        double d1 = ((float)j1 + 15.99F) / 256F;
        double d2 = (float)k1 / 256F;
        double d3 = ((float)k1 + 15.99F) / 256F;
		
        float f4 = i + 0;
        float f5 = i + 1;
        float f6 = k + 0;
        float f7 = k + 1;
        byte byte0 = 0;
		
		
		int j22 = (i1 & 0xf) << 4;
        int k22 = i1 & 0xf0;
        double d4 = ((double)j22 + block.minX * 16D) / 256D;
        double d5 = (((double)j22 + block.maxX * 16D) - 0.01D) / 256D;
        double d6 = ((double)k22 + block.minZ * 16D) / 256D;
        double d7  = (((double)k22 + block.maxZ * 16D) - 0.01D) / 256D;
		
        if(block.minX < 0.0D || block.maxX > 1.0D)
        {
            d4 = ((float)j22 + 0.0F) / 256F;
            d5 = ((float)j22 + 15.99F) / 256F;
        }
        if(block.minZ < 0.0D || block.maxZ > 1.0D)
        {
            d6 = ((float)k22 + 0.0F) / 256F;
            d7 = ((float)k22 + 15.99F) / 256F;
        }
		
			if(isWebBreak(i, j - 1, k))
            {
                tessellator.addVertexWithUV((float)(i + 0), (float)j + 0.015625F, k + 1, d5, d6);
                tessellator.addVertexWithUV((float)i + 1, j + 0.015625F, k + 1, d4, d6);
                tessellator.addVertexWithUV((float)i + 1, j + 0.015625F, k + 0, d4, d7);
                tessellator.addVertexWithUV((float)i + 0, (float)(j + 0.015625F), k + 0, d5, d7);
				
                tessellator.addVertexWithUV((float)i + 0, (float)(j + 0.015625F), k + 1, d5, d6);
                tessellator.addVertexWithUV((float)i + 1, j + 0.015625F, k + 1, d4, d6);
                tessellator.addVertexWithUV((float)i + 1, j + 0.015625F, k + 0, d4, d7);
                tessellator.addVertexWithUV((float)i + 0, (float)(j + 0.015625F), k + 0, d5, d7);
				
            }
			
			if(isWebBreak(i, j + 1, k))
            {
                tessellator.addVertexWithUV((float)(i + 1), (float)j + 1.0F - 0.015625F, k + 1, d5, d6);
                tessellator.addVertexWithUV((float)i + 0, j + 1.0F - 0.015625F, k + 1, d4, d6);
                tessellator.addVertexWithUV((float)i + 0, j + 1.0F - 0.015625F, k + 0, d4, d7);
                tessellator.addVertexWithUV((float)i + 1, (float)(j + 1.0F - 0.015625F), k + 0, d5, d7);
				
                tessellator.addVertexWithUV((float)i + 1, (float)(j + 1.0F - 0.015625F), k + 1, d5, d6);
                tessellator.addVertexWithUV((float)i + 0, j + 1.0F - 0.015625F, k + 1, d4, d6);
                tessellator.addVertexWithUV((float)i + 0, j + 1.0F - 0.015625F, k + 0, d4, d7);
                tessellator.addVertexWithUV((float)i + 1, (float)(j + 1.0F - 0.015625F), k + 0, d5, d7);
				
            }
			
            if(isWebBreak(i - 1, j, k))
            {
                tessellator.addVertexWithUV((float)i + 0.015625F, (float)(j + 1) + 0.021875F, k + 1, d5, d6);
                tessellator.addVertexWithUV((float)i + 0.015625F, j + 0, k + 1, d4, d6);
                tessellator.addVertexWithUV((float)i + 0.015625F, j + 0, k + 0, d4, d7);
                tessellator.addVertexWithUV((float)i + 0.015625F, (float)(j + 1) + 0.021875F, k + 0, d5, d7);
				
                tessellator.addVertexWithUV((float)i + 0.015625F, (float)(j + 1) + 0.021875F, k + 1, d5, d6);
                tessellator.addVertexWithUV((float)i + 0.015625F, j + 0, k + 1, d4, d6);
                tessellator.addVertexWithUV((float)i + 0.015625F, j + 0, k + 0, d4, d7);
                tessellator.addVertexWithUV((float)i + 0.015625F, (float)(j + 1) + 0.021875F, k + 0, d5, d7);
            }
            if(isWebBreak(i + 1, j, k))
            {
                tessellator.addVertexWithUV((float)(i + 1) - 0.015625F, j + 0, k + 1, d4, d7);
                tessellator.addVertexWithUV((float)(i + 1) - 0.015625F, (float)(j + 1) + 0.021875F, k + 1, d5, d7);
                tessellator.addVertexWithUV((float)(i + 1) - 0.015625F, (float)(j + 1) + 0.021875F, k + 0, d5, d6);
                tessellator.addVertexWithUV((float)(i + 1) - 0.015625F, j + 0, k + 0, d4, d6);
				
                tessellator.addVertexWithUV((float)(i + 1) - 0.015625F, j + 0, k + 1, d4, d7);
                tessellator.addVertexWithUV((float)(i + 1) - 0.015625F, (float)(j + 1) + 0.021875F, k + 1, d5, d7);
                tessellator.addVertexWithUV((float)(i + 1) - 0.015625F, (float)(j + 1) + 0.021875F, k + 0, d5, d6);
                tessellator.addVertexWithUV((float)(i + 1) - 0.015625F, j + 0, k + 0, d4, d6);
            }
            if(isWebBreak(i, j, k - 1))
            {
                tessellator.addVertexWithUV(i + 1, j + 0, (float)k + 0.015625F, d4, d7);
                tessellator.addVertexWithUV(i + 1, (float)(j + 1) + 0.021875F, (float)k + 0.015625F, d5, d7);
                tessellator.addVertexWithUV(i + 0, (float)(j + 1) + 0.021875F, (float)k + 0.015625F, d5, d6);
                tessellator.addVertexWithUV(i + 0, j + 0, (float)k + 0.015625F, d4, d6);
				
                tessellator.addVertexWithUV(i + 1, j + 0, (float)k + 0.015625F, d4, d7);
                tessellator.addVertexWithUV(i + 1, (float)(j + 1) + 0.021875F, (float)k + 0.015625F, d5, d7);
                tessellator.addVertexWithUV(i + 0, (float)(j + 1) + 0.021875F, (float)k + 0.015625F, d5, d6);
                tessellator.addVertexWithUV(i + 0, j + 0, (float)k + 0.015625F, d4, d6);
            }
            if(isWebBreak(i, j, k + 1))
            {
                tessellator.addVertexWithUV(i + 1, (float)(j + 1) + 0.021875F, (float)(k + 1) - 0.015625F, d5, d6);
                tessellator.addVertexWithUV(i + 1, j + 0, (float)(k + 1) - 0.015625F, d4, d6);
                tessellator.addVertexWithUV(i + 0, j + 0, (float)(k + 1) - 0.015625F, d4, d7);
                tessellator.addVertexWithUV(i + 0, (float)(j + 1) + 0.021875F, (float)(k + 1) - 0.015625F, d5, d7);
				
                tessellator.addVertexWithUV(i + 1, (float)(j + 1) + 0.021875F, (float)(k + 1) - 0.015625F, d5, d6);
                tessellator.addVertexWithUV(i + 1, j + 0, (float)(k + 1) - 0.015625F, d4, d6);
                tessellator.addVertexWithUV(i + 0, j + 0, (float)(k + 1) - 0.015625F, d4, d7);
                tessellator.addVertexWithUV(i + 0, (float)(j + 1) + 0.021875F, (float)(k + 1) - 0.015625F, d5, d7);
            }
			
        return true;
    }
	
	public void addRenderer(Map renderers)
    {
		 renderers.put(EntityWeb.class, new RenderWeb());
		 renderers.put(EntityVines.class, new RenderVines());
		 renderers.put(EntitySWeb.class, new RenderSWeb());
		 renderers.put(EntityFriendlySpider.class, new RenderFriendlySpider());
		 renderers.put(EntityThinSpider.class, new RenderThinSpider());
		 renderers.put(EntityPoisonSpider.class, new RenderPoisonSpider());
		 renderers.put(EntityBoomSpider.class, new RenderBoomSpider());
		 renderers.put(EntityMinighast.class, new RenderMinighast());
		 renderers.put(EntityTinySpider.class, new RenderTinySpider());
		 renderers.put(EntityFurrySpider.class, new RenderFurrySpider());
		 renderers.put(EntitySpiderEgg.class, new RenderSpiderEgg());
		 renderers.put(EntityGhastEgg.class, new RenderGhastEgg());
         renderers.put(EntityCocoon.class, new RenderCocoonChicken());
         renderers.put(EntityCocoonPig.class, new RenderCocoonPig());
         renderers.put(EntityCocoonSheep.class, new RenderCocoonSheep());
         renderers.put(EntityCocoonCreeper.class, new RenderCocoonCreeper());
         renderers.put(EntityCocoonZombie.class, new RenderCocoonZombie());
         renderers.put(EntityCocoonCow.class, new RenderCocoonCow());
         renderers.put(EntityCocoonHuman.class, new RenderCocoonHuman());
         renderers.put(EntityCocoonSkeleton.class, new RenderCocoonSkeleton());
         renderers.put(EntityCocoonGatherer.class, new RenderCocoonGatherer());
         renderers.put(EntityCocoonWarrior.class, new RenderCocoonWarrior());
         renderers.put(EntityCocoonQueenBee.class, new RenderCocoonQueenBee());
         renderers.put(EntityCocoonAnt.class, new RenderCocoonAnt());
         renderers.put(EntityCocoonWasp.class, new RenderCocoonWasp());
         renderers.put(EntityCocoonWolf.class, new RenderCocoonWolf());
		 renderers.put(EntityGatherer.class, new RenderGatherer());
		 renderers.put(EntityWarrior.class, new RenderWarrior());
		 renderers.put(EntityQueenBee.class, new RenderQueenBee());
		 renderers.put(EntityWasp.class, new RenderWasp());
		 renderers.put(EntityBeetle.class, new RenderBeetle());
		 renderers.put(EntityFly.class, new RenderFly());
		 renderers.put(EntityAnt.class, new RenderAnt());
		 renderers.put(EntityRedAnt.class, new RenderAnt());
		 renderers.put(EntityYuki.class, new RenderYuki());
		 //renderers.put(EntityChestSpider.class, new RenderPackratSpider());
		 
		 renderers.put(EntityHuman.class, new RenderBiped(new ModelHuman(), 0.5F));
		 renderers.put(EntityMand.class, new RenderMand());
		 renderers.put(EntityESpiderQueen.class, new RenderESpiderQueen(new ModelSpiderQueen(), 0.5F));
		 renderers.put(EntityOctopus.class, new RenderOctopus());
		 renderers.put(EntityOctoball.class, new RenderCustomball(19));
		 renderers.put(EntityBoomball.class, new RenderCustomball(19));
		 renderers.put(EntityJackball.class, new RenderCustomball(20));
		 renderers.put(EntityJack.class, new RenderJack());
		 
		 
		 renderers.put(EntityFCreeper.class, new RenderFCreeper());
		 renderers.put(EntityFZombie.class, new RenderBiped(new ModelZombie(), 0.5F));
		 renderers.put(EntityFSkeleton.class, new RenderBiped(new ModelSkeleton(), 0.5F));
	}

	public static Entity findEnemyToAttack(World world, EntityLiving ent)
    {
		double d = 16D;
        Entity tmp = getClosestEntityToEntity(world,ent, d, 0);
		
		return tmp;
    }
	
    public static Entity getClosestEntityToEntity(World world, EntityLiving entity, double d, int findEnt)
    {
        return getClosestEntity(world, entity, entity.posX, entity.posY, entity.posZ, d, findEnt);
    }

	public static boolean isEnt(Entity ent, Entity origent, int tent)
	{
	
		boolean fspider = false;
		if(ent instanceof EntityLiving)
		{
			EntityLiving tmp = (EntityLiving)ent;
			if(tmp.spiderfriend) { fspider = true; }
			if(tmp.isDead) { return false; }
		}
		if(tent==3) { if(ent instanceof EntityAnt) { return true; } } // RED ANT
		if(tent==4) { if(ent instanceof EntityRedAnt) { return true; } } // BLACK ANT

		if(tent==6)  // BLACK ANT AI
		{ 
			if(ent instanceof EntityAnt) 
			{ 
				EntityAnt tnt = (EntityAnt)ent;
				EntityAnt otnt = (EntityAnt)origent;
				
				if(tnt.getAntId() < otnt.getAntId()) { return true; }
			} 
		}
		
		if(tent==8)  // RED ANT AI
		{ 
			if(ent instanceof EntityRedAnt) 
			{ 
				EntityRedAnt tnt = (EntityRedAnt)ent;
				EntityRedAnt otnt = (EntityRedAnt)origent;
				
				if(tnt.getAntId() < otnt.getAntId()) { return true; }
			} 
		}
		
		if(tent== 9)  // MOB SEARCHING FOR HUMAN
		{ 
			if(ent instanceof EntityHuman) { return true; } 
		}

		if(tent== 10)  // HUMAN SEARCHING FOR MOB
		{ 
			if(ent instanceof EntityHuman) { return false; } 
			if(ent instanceof EntityMob) { return true; } 
		}
		
		if(tent==7)  // YOUR SPIDERS (HEALING)
		{ 
			if(ent instanceof EntityFriendlySpider) 
			{ 
				EntityFriendlySpider tnt = (EntityFriendlySpider)ent;
				if(tnt.health + 5 < tnt.getMaxHealth()) { return true; } else { return false; }
			} 
			if(ent instanceof EntityFly) { return true; }
			if(ent instanceof EntityCocoonAnt) { return true; }
			if(ent instanceof EntityCocoonWasp) { return true; }
			if(ent instanceof EntityCocoonGatherer) { return true; }
			if(ent instanceof EntityCocoonWarrior) { return true; }
			if(ent instanceof EntityCocoonQueenBee) { return true; }
			
			return false;
		}
		
		if(tent==22)  // JUST BAIT
		{ 
			if(ent instanceof EntityFly) { return true; }
			if(ent instanceof EntityCocoonAnt) { return true; }
			if(ent instanceof EntityCocoonWasp) { return true; }
			if(ent instanceof EntityCocoonGatherer) { return true; }
			if(ent instanceof EntityCocoonWarrior) { return true; }
			if(ent instanceof EntityCocoonQueenBee) { return true; }
			
			return false;
		}
		
		if(tent==24)  // JUST BEETLE
		{ 
			if(ent instanceof EntityBeetle) { return true; }
			
			return false;
		}
		
		if(tent==5)  // WASP
		{ 
			if(ent instanceof EntityFly) { return true; } 
			if(ent instanceof EntityAnt) { return true; } 
			if(ent instanceof EntityRedAnt) { return true; } 
			if(ent instanceof EntityFriendlySpider) { return true; } 
			if(ent instanceof EntityThinSpider) { return true; } 
			if(ent instanceof EntityBoomSpider) { return true; } 
			if(ent instanceof EntityMinighast) { return true; } 
			//if(ent instanceof EntityChestSpider) { return true; } 
			if(ent instanceof EntityFurrySpider) { return true; } 
			if(ent instanceof EntityTinySpider) { return true; } 
			if(ent instanceof EntityGatherer) { return true; } 
			if(ent instanceof EntityQueenBee) { return true; } 
			if(ent instanceof EntityWarrior) { return true; } 
			if(ent instanceof EntityPig) { return true; } 
			if(ent instanceof EntitySheep) { return true; } 
			if(ent instanceof EntityCow) { return true; } 
			if(ent instanceof EntityChicken) { return true; } 
			if(ent instanceof EntityHuman) { return true; } 
		}
		if(tent==14)  // EMAND
		{ 
			if(ent instanceof EntityAnt) { return true; } 
			if(ent instanceof EntityRedAnt) { return true; } 
			if(ent instanceof EntityFriendlySpider) { return true; } 
			if(ent instanceof EntityThinSpider) { return true; } 
			if(ent instanceof EntityBoomSpider) { return true; } 
			if(ent instanceof EntityMinighast) { return true; } 
			//if(ent instanceof EntityChestSpider) { return true; } 
			if(ent instanceof EntityFurrySpider) { return true; } 
			if(ent instanceof EntityTinySpider) { return true; } 
			if(ent instanceof EntityPig) { return true; } 
			if(ent instanceof EntitySheep) { return true; } 
			if(ent instanceof EntityCow) { return true; } 
			if(ent instanceof EntityChicken) { return true; } 
			if(ent instanceof EntityHuman) { return true; } 
		}
		if(tent==15)  // FMAND
		{ 
			if(fspider) { return false; }
			if(ent instanceof EntityFriendlySpider) { return false; }
			if(ent instanceof EntityCreature)
			{
				EntityCreature nent = (EntityCreature)ent;
				if(nent.getEntityToAttack() instanceof EntityPlayer) { return true; }
				if(nent.getEntityToAttack() instanceof EntityFriendlySpider) { return true; }
			}
			if(ent instanceof EntityCreeper & CreeperLike < 10) { return true; }
			if(ent instanceof EntitySkeleton & SkeletonLike < 10) { return true; }
			if(ent instanceof EntityZombie & ZombieLike < 10) { return true; }
			if(ent instanceof EntityHuman & HumanLike < 10) { return true; }
			if(ent instanceof EntityPigZombie) { return true; }
			if(ent instanceof EntityWasp) { return true; }
			if(ent instanceof EntityRedAnt & AntLike < 25) { return true; }
			if(ent instanceof EntityAnt & AntLike > 24) { return true; }
			if(ent instanceof EntitySpider) { return true; }
		}
		
		if(tent< 3) // FRIENDLY SPIDER
		{
			if(fspider) { return false; }
			if(ent instanceof EntityFriendlySpider) { return false; }
			if(ent instanceof EntityCreature)
			{
				EntityCreature nent = (EntityCreature)ent;
				if(nent.getEntityToAttack() instanceof EntityPlayer) { return true; }
				if(nent.getEntityToAttack() instanceof EntityFriendlySpider) { return true; }
			}
			if(ent instanceof EntityCreeper & CreeperLike < 10) { return true; }
			if(ent instanceof EntitySkeleton & SkeletonLike < 10) { return true; }
			if(ent instanceof EntityZombie & ZombieLike < 10) { return true; }
			if(ent instanceof EntityHuman & HumanLike < 10) { return true; }
			if(ent instanceof EntityPigZombie) { return true; }
			if(ent instanceof EntityWasp) { return true; }
			if(ent instanceof EntityRedAnt & AntLike < 25) { return true; }
			if(ent instanceof EntityAnt & AntLike > 24) { return true; }
			if(ent instanceof EntityFly) { return true; }
			if(ent instanceof EntityCocoonAnt) { return true; }
			if(ent instanceof EntityCocoonWasp) { return true; }
			if(ent instanceof EntityCocoonGatherer) { return true; }
			if(ent instanceof EntityCocoonWarrior) { return true; }
			if(ent instanceof EntityCocoonQueenBee) { return true; }
		}
		return false;
	}
	
    public static Entity getClosestEntity(World world, EntityLiving enty, double d, double d1, double d2, double d3, int tentity)
    {
        double d4 = -1D;
        Entity entityplayer = null;
		
        for(int i = 0; i < world.loadedEntityList.size(); i++)
        {
            Entity entityplayer1 = (Entity)world.loadedEntityList.get(i);
			
			if(enty == null || enty.canEntityBeSeen(entityplayer1))
			{
				if(isEnt(entityplayer1, enty, tentity) & enty != entityplayer1)
				{
					double d5 = entityplayer1.getDistanceSq(d, d1, d2);
					if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
					{
						d4 = d5;
						entityplayer = entityplayer1;
					}
				}
			}
        }

        return entityplayer;
    }
	
    public static Entity getClosestItem(World world, EntityLiving enty)
    {
        double d4 = -1D;
        Entity entityplayer = null;
		double d3 = 16D;
        for(int i = 0; i < world.loadedEntityList.size(); i++)
        {
            Entity entityplayer1 = (Entity)world.loadedEntityList.get(i);
			
			if(enty == null || enty.canEntityBeSeen(entityplayer1))
			{
				if(entityplayer1 instanceof EntityItem)
				{
					double d5 = entityplayer1.getDistanceSq(enty.posX, enty.posY, enty.posZ);
					if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
					{
						d4 = d5;
						entityplayer = entityplayer1;
					}
				}
			}
        }

        return entityplayer;
    }
	
	public static int sqSpawn;
    public static int texx[] = new int[25];
	public static boolean isOn;
    public static final Item itemWeb;
    public static final Item itemPWeb;
    public static final Item itemSpiderEgg;
    public static final Item itemGhastEgg;
    public static final Item itemCocoon;
    public static final Item itemCocoonPig;
    public static final Item itemCocoonSheep;
    public static final Item itemCocoonCreeper;
    public static final Item itemCocoonZombie;
    public static final Item itemCocoonSkeleton;
    public static final Item itemCocoonCow;
    public static final Item itemCocoonHuman;
    public static final Item itemCocoonGatherer;
    public static final Item itemCocoonWarrior;
    public static final Item itemCocoonQueenBee;
    public static final Item itemCocoonWasp;
    public static final Item itemCocoonAnt;
    public static final Item itemCocoonWolf;
    public static final Block smallWeb;
    public static final Block poisonWeb;
    public static final Block webBed;
    public static final Block beehive;
    public static final Block anthill;
    public static final Block spiderbait;
    public static final Block mseeds;
    public static final Block bskull;
    public static final Block heart;
    public static final Block brain;
    public static final Block royalblood;
    public static final Block bjack;
    public static final Block lantern;
    public static final Block stinger;
	public static Item itemNectar;
	public static Item itemRareFruit;
	public static Item itemSWeb;
	public static Item itemSkull;
	public static boolean hiveAlert = false;
	static 
    {
	
		texx[0] = ModLoader.addOverride("/terrain.png", "/imgz/webbingr.png");
		texx[1] = ModLoader.addOverride("/terrain.png", "/imgz/webbed.png");
		texx[2] = ModLoader.addOverride("/terrain.png", "/imgz/hive.png");
		texx[3] = ModLoader.addOverride("/terrain.png", "/imgz/spiderwand.png");
		texx[4] = ModLoader.addOverride("/terrain.png", "/imgz/hivebottom.png");
		texx[5] = ModLoader.addOverride("/terrain.png", "/imgz/anthill.png");
		texx[6] = ModLoader.addOverride("/terrain.png", "/imgz/skullf.png");
		texx[7] = ModLoader.addOverride("/terrain.png", "/imgz/skullb.png");
		texx[8] = ModLoader.addOverride("/terrain.png", "/imgz/skulls.png");
		texx[9] = ModLoader.addOverride("/terrain.png", "/imgz/skullt.png");
		texx[10] = ModLoader.addOverride("/terrain.png", "/imgz/skullf1.png");
		texx[11] = ModLoader.addOverride("/terrain.png", "/imgz/heart.png");
		texx[12] = ModLoader.addOverride("/terrain.png", "/imgz/brain.png");
		texx[13] = ModLoader.addOverride("/terrain.png", "/imgz/mandseeds.png");
		texx[14] = ModLoader.addOverride("/terrain.png", "/imgz/lside.png");
		texx[15] = ModLoader.addOverride("/terrain.png", "/imgz/ltop.png");
		texx[16] = ModLoader.addOverride("/terrain.png", "/imgz/jside.png");
		texx[17] = ModLoader.addOverride("/terrain.png", "/imgz/jface.png");
		texx[18] = ModLoader.addOverride("/terrain.png", "/imgz/jtop.png");
		texx[19] = ModLoader.addOverride("/terrain.png", "/imgz/octoball.png");
		texx[20] = ModLoader.addOverride("/terrain.png", "/imgz/jackball.png");
		texx[21] = ModLoader.addOverride("/terrain.png", "/imgz/royalblood.png");
		texx[22] = ModLoader.addOverride("/terrain.png", "/imgz/stinger.png");
		texx[23] = ModLoader.addOverride("/terrain.png", "/imgz/poisonblock.png");
		
    	itemWeb = (new ItemWeb(332)).setItemName("Webbing").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/webicon.png").toString()));
		itemPWeb = (new ItemPWeb(358)).setItemName("PWebbing").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/poisonweb.png").toString()));
		itemSWeb = (new ItemSWeb(351)).setItemName("Webslinger").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/websling.png").toString()));
		itemSpiderEgg = (new ItemSpiderEgg(335)).setItemName("Spider Egg").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/spidereggicon.png").toString()));
		itemGhastEgg = (new ItemGhastEgg(362)).setItemName("Ghast Egg").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/ghastegg.png").toString()));
		itemCocoon = (new ItemCocoon(336)).setItemName("Chicken Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCChicken.png").toString()));
		itemCocoonPig = (new ItemCocoonPig(337)).setItemName("Pig Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCPig.png").toString()));
		itemCocoonSheep = (new ItemCocoonSheep(338)).setItemName("Sheep Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCSheep.png").toString()));
		itemCocoonCreeper = (new ItemCocoonCreeper(342)).setItemName("Creeper Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCCreeper.png").toString()));
		itemCocoonZombie = (new ItemCocoonZombie(341)).setItemName("Zombie Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCZombie.png").toString()));
		itemCocoonSkeleton = (new ItemCocoonSkeleton(340)).setItemName("Skeleton Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCSkeleton.png").toString()));
		itemCocoonCow = (new ItemCocoonCow(339)).setItemName("Cow Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCCow.png").toString()));
		itemCocoonHuman = (new ItemCocoonHuman(361)).setItemName("Human Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/iconchuman.png").toString()));
		itemCocoonGatherer = (new ItemCocoonGatherer(344)).setItemName("Gatherer Bee Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCGatherer.png").toString()));
		itemCocoonWarrior = (new ItemCocoonWarrior(345)).setItemName("Warrior Bee Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCWarrior.png").toString()));
		itemCocoonQueenBee = (new ItemCocoonQueenBee(346)).setItemName("Queen Bee Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCQueenBee.png").toString()));
		itemCocoonWasp = (new ItemCocoonWasp(347)).setItemName("Wasp Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCWasp.png").toString()));
		itemCocoonAnt = (new ItemCocoonAnt(348)).setItemName("Ant Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconCAnt.png").toString()));
		itemCocoonWolf = (new ItemCocoonWolf(349)).setItemName("Wolf Cocoon").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/IconWolf.png").toString()));
		itemNectar = (new ItemNectar(343, 6, false)).setItemName("Nectar").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/nectar.png").toString()));
		itemRareFruit = (new ItemRareFruit(354, 3)).setItemName("RareFruit").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/rarefruit.png").toString()));
		itemSkull = (new ItemSkull(352)).setItemName("Human Skull").setIconIndex(ModLoader.addOverride("/gui/items.png", (new StringBuilder()).append("/imgz/skull.png").toString()));
		smallWeb = (new BlockSmallWeb(173,texx[0])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("SmallWeb");
    	poisonWeb = (new BlockPoisonWeb(186,texx[0])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("PoisonWeb");
    	webBed = (new BlockWebBed(174,texx[0])).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setBlockName("WebBed");
		beehive = (new BlockBeeHive(175, texx[2])).setHardness(2F).setStepSound(Block.soundMetalFootstep).setBlockName("beeHive");
    	bskull = (new BlockSkull(179, texx[6])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("skull");
    	heart = (new BlockHeart(180, texx[11])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("heart");
    	brain = (new BlockBrain(181, texx[12])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("brain");
    	royalblood = (new BlockRoyalBlood(185, texx[21])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("rblood");
    	anthill = (new BlockAntHill(178, texx[2])).setHardness(0.2F).setStepSound(Block.soundMetalFootstep).setBlockName("antHill");
    	spiderbait = (new BlockSpiderBait(176, texx[3])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("SpiderBait");
		mseeds = (new BlockMSeed(182, texx[13])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("mseeds");
		bjack = (new BlockJack(183, texx[16])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("bjack");
		lantern = (new BlockLantern(184, texx[15])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setLightValue(1F).setBlockName("alantern");
    	stinger = (new BlockStinger(189, texx[22])).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setLightValue(1F).setBlockName("stinger");
    	
    }
	
	private int generateWeb;
}
