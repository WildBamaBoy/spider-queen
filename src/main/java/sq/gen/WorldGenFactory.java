package sq.gen;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import radixcore.data.BlockObj;
import radixcore.math.Point3D;
import radixcore.util.RadixExcept;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.entity.EntityFriendlyBee;
import sq.entity.EntityFriendlyCreeper;
import sq.entity.EntityFriendlySkeleton;
import sq.entity.EntityFriendlyZombie;
import sq.entity.EntityHuman;
import sq.entity.EntitySpiderQueen;
import sq.entity.IFriendlyEntity;
import sq.enums.EnumPlaceholderBlock;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenFactory implements IWorldGenerator
{
	public WorldGenFactory()
	{
		super();
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		if (SpiderCore.getConfig().enableFactories)
		{
			if (chunkGenerator instanceof ChunkProviderGenerate)
			{
				int x = chunkX * 16 + random.nextInt(16);
				int z = chunkZ * 16 + random.nextInt(16);
				BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

				if (biome == BiomeGenBase.plains)
				{
					for (int maxY = 256; maxY > 0; maxY--)
					{
						if (generate(world, random, x, maxY, z))
						{
							break;
						}
					}
				}
			}
		}
	}

	public boolean generate(World world, Random random, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);

		if (block == Blocks.grass && random.nextInt(1400) == 0)
		{
			y += 2; //Account for the test block and player standing location in schematic.
			SpiderCore.getLog().info("Generating factory at " + x + ", " + y + ", " + z );

			Point3D point = new Point3D(x, y, z);

			long t1 = System.nanoTime();
			spawnStructureRelativeToPoint(SpiderCore.structureSchematics.get(random.nextInt(SpiderCore.structureSchematics.size())), point, world);		
			long t2 = System.nanoTime();

			long elapsedTime = TimeUnit.MILLISECONDS.convert(t2 - t1, TimeUnit.NANOSECONDS);
			SpiderCore.getLog().info("Generation completed in " + elapsedTime + "ms.");
		}

		return false;
	}

	//Memory-based implementation from RadixCore.
	private void spawnStructureRelativeToPoint(Map<Point3D, BlockObj> schemBlocks, Point3D point, World world)
	{
		Map<Point3D, BlockObj> torchMap = new HashMap<Point3D, BlockObj>();
		Map<Point3D, BlockObj> doorMap = new HashMap<Point3D, BlockObj>();
		Map<Point3D, BlockObj> placeholderMap = new HashMap<Point3D, BlockObj>();

		for (Map.Entry<Point3D, BlockObj> entry : schemBlocks.entrySet())
		{
			if (entry.getValue().getBlock() == Blocks.torch)
			{
				torchMap.put(entry.getKey(), entry.getValue());
			}

			else if (entry.getValue().getBlock() == Blocks.wooden_door)
			{
				doorMap.put(entry.getKey(), entry.getValue());
			}

			else if (EnumPlaceholderBlock.isPlaceholder(entry.getValue().getBlock()))
			{
				placeholderMap.put(entry.getKey(), entry.getValue());
			}

			else
			{
				Point3D blockPoint = entry.getKey();

				int x = blockPoint.iPosX + point.iPosX;
				int y = blockPoint.iPosY + point.iPosY;
				int z = blockPoint.iPosZ + point.iPosZ;

				BlockObj blockObj = entry.getValue();
				world.setBlock(x, y, z, blockObj.getBlock(), blockObj.getMeta(), 0);

				if (blockObj.getBlock() == Blocks.chest && world.rand.nextBoolean() && world.rand.nextBoolean())
				{
					try
					{
						TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y, z);

						if (chest != null)
						{							
							for (int i = 0; i < chest.getSizeInventory(); i++)
							{
								if (RadixLogic.getBooleanWithProbability(10))
								{
									int lootId = RadixMath.getNumberInRange(0, LOOT_ARRAY.length - 1);
									Object[] lootEntry = LOOT_ARRAY[lootId];
									Item lootItem = (Item) ((lootEntry[0] instanceof Block) ? Item.getItemFromBlock((Block) lootEntry[0]) : lootEntry[0]);
									int minimum = (Integer) lootEntry[1];
									int maximum = (Integer) lootEntry[2];

									int amountToSpawn = RadixMath.getNumberInRange(minimum, maximum);

									chest.setInventorySlotContents(i, new ItemStack(lootItem, amountToSpawn));
								}
							}
						}
					}

					catch (Exception e)
					{
						continue;
					}
				}

				if (blockObj.getBlock() == Blocks.dispenser)
				{
					try
					{
						world.setBlockMetadataWithNotify(x, y, z, blockObj.getMeta() & 7, 2); //Operation used by Minecraft to determine texture.
						TileEntityDispenser dispenser = (TileEntityDispenser) world.getTileEntity(x, y, z);

						if (dispenser != null)
						{
							if (RadixLogic.getBooleanWithProbability(10))
							{
								dispenser.setInventorySlotContents(0, new ItemStack(Blocks.tnt));
							}

							else
							{
								ItemStack potionStack = new ItemStack(Items.potionitem, 4, (Integer) DISPENSER_ARRAY[RadixMath.getNumberInRange(0, DISPENSER_ARRAY.length - 1)]);
								dispenser.setInventorySlotContents(0, potionStack);
							}
						}
					}

					catch (Exception e)
					{
						continue;
					}
				}
			}
		}

		for (Map.Entry<Point3D, BlockObj> entry : torchMap.entrySet())
		{
			Point3D blockPoint = entry.getKey();

			int x = blockPoint.iPosX + point.iPosX;
			int y = blockPoint.iPosY + point.iPosY;
			int z = blockPoint.iPosZ + point.iPosZ;

			BlockObj blockObj = entry.getValue();
			world.setBlock(x, y, z, blockObj.getBlock(), blockObj.getMeta(), 0);
		}

		for (Map.Entry<Point3D, BlockObj> entry : doorMap.entrySet())
		{
			Point3D blockPoint = entry.getKey();

			int x = blockPoint.iPosX + point.iPosX;
			int y = blockPoint.iPosY + point.iPosY;
			int z = blockPoint.iPosZ + point.iPosZ;

			BlockObj blockObj = entry.getValue();
			world.setBlock(x, y, z, blockObj.getBlock(), blockObj.getMeta(), 0);
		}

		for (Map.Entry<Point3D, BlockObj> entry : placeholderMap.entrySet())
		{
			EnumPlaceholderBlock placeHolder = EnumPlaceholderBlock.getByBlock(entry.getValue().getBlock());
			Point3D blockPoint = entry.getKey();

			int x = blockPoint.iPosX + point.iPosX;
			int y = blockPoint.iPosY + point.iPosY;
			int z = blockPoint.iPosZ + point.iPosZ;

			BlockObj blockObj = entry.getValue();
			world.setBlock(x, y, z, placeHolder.getYield(), 0, 2);

			if (placeHolder.getDoCallback())
			{
				handlePlaceholderCallback(placeHolder, world, x, y, z);
			}
		}
	}

	private void handlePlaceholderCallback(EnumPlaceholderBlock enumPlaceHolder, World world, int x, int y, int z)
	{
		if (enumPlaceHolder == EnumPlaceholderBlock.BEDROCK) //Spawn humans at bedrock's location.
		{
			int humansToSpawn = RadixMath.getNumberInRange(0, 3);

			while (humansToSpawn > 0)
			{
				EntityHuman human = new EntityHuman(world);
				human.setPosition(x, y, z);
				world.spawnEntityInWorld(human);

				humansToSpawn--;
			}
		}

		else if (enumPlaceHolder == EnumPlaceholderBlock.NETHER_QUARTZ)
		{
			EntitySpiderQueen spiderQueen = new EntitySpiderQueen(world);
			spiderQueen.setPosition(x, y, z);
			world.spawnEntityInWorld(spiderQueen);
		}

		else if (enumPlaceHolder == EnumPlaceholderBlock.BEACON && RadixLogic.getBooleanWithProbability(85))
		{
			Class friendlyClass = null;
			int friendlyId = RadixMath.getNumberInRange(1, 4);

			switch (friendlyId)
			{
			case 1: friendlyClass = EntityFriendlyBee.class; break;
			case 2: friendlyClass = EntityFriendlyCreeper.class; break;
			case 3: friendlyClass = EntityFriendlySkeleton.class; break;
			case 4: friendlyClass = EntityFriendlyZombie.class; break;
			}

			try
			{
				EntityLivingBase friendlyInstance = (EntityLivingBase) friendlyClass.getConstructor(World.class).newInstance(world);
				IFriendlyEntity friendlyInterface = (IFriendlyEntity)friendlyInstance;

				friendlyInterface.setImprisoned(true);
				friendlyInstance.setPosition(x, y, z);
				world.spawnEntityInWorld(friendlyInstance);
			}

			catch (Exception e)
			{
				RadixExcept.logErrorCatch(e, "Spawning friendly in prison.");
			}
		}
	}

	/**
	 * Array of objects used to place loot into factories.
	 * Second index definitions:
	 * [0] = Item/block reference
	 * [1] = Minimum amount spawned
	 * [2] = Maximum amount spawned
	 */
	private static final Object[][] LOOT_ARRAY = new Object[][]
			{
		{ Items.iron_ingot, 1, 1 },
		{ Items.iron_sword, 1, 1 },
		{ Items.iron_shovel, 1, 1 },
		{ Items.iron_pickaxe, 1, 1 },
		{ Items.redstone, 8, 32 },
		{ Blocks.iron_ore, 1, 16 },
		{ Items.coal, 1, 32 },
		{ Items.brick, 1, 32 },
		{ Blocks.gold_ore, 1, 16 },
		{ Blocks.cobblestone, 32, 64},
		{ Blocks.diamond_block, 1, 1},
		{ Blocks.obsidian, 1, 32 },
		{ Items.emerald, 1, 8},
		{ Items.diamond_boots, 1, 1},
		{ Items.diamond_helmet, 1, 1},
		{ Items.iron_chestplate, 1, 1},
		{ Items.iron_helmet, 1, 1},
		{ Items.iron_leggings, 1, 1},
		{ Items.iron_boots, 1, 1},
		{ Items.arrow, 1, 32},
		{ Items.diamond_axe, 1, 1},
		{ Items.diamond_sword, 1, 1},
		{ Items.diamond_hoe, 1, 1},
		{ Items.diamond_shovel, 1, 1},
		{ Items.ender_eye, 1, 2},
		{ Items.gold_ingot, 1, 16},
		{ Items.diamond, 1, 3},
		{ Items.stick, 1, 6},
		{ Blocks.brick_block, 1, 32},
		{ Items.gunpowder, 1, 16},
		{ Items.bone, 1, 16}
			};

	/**
	 * IDs of potions that will be placed in dispensers.
	 */
	private static final int[] DISPENSER_ARRAY = new int[]
			{
		16388,
		16420,
		16392,
		16394,
		16396,
		16428
			};
}
