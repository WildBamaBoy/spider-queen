package sq.gen;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import radixcore.data.BlockObj;
import radixcore.math.Point3D;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.entity.EntityHuman;
import sq.entity.EntitySpiderQueen;
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

	public boolean generate(World world, Random random, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);

		if (block == Blocks.grass && random.nextInt(2400) == 0)
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
	}
}
