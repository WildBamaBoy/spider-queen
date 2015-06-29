package sq.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import sq.core.minecraft.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenJack implements IWorldGenerator
{
	public WorldGenJack()
	{
	}

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (chunkGenerator instanceof ChunkProviderGenerate)
		{
			int x = chunkX * 16 + random.nextInt(16);
			int z = chunkZ * 16 + random.nextInt(16);

			for (int maxY = 256; maxY > 0; maxY--)
			{
				if (generate(world, random, x, maxY, z)) //Limit to one per chunk.
				{
					break;
				}
			}
		}
	}
	
	public boolean generate(World world, Random random, int par3, int par4, int par5)
	{
		for (int i = 0; i < 64; i++)
		{
			int x = (par3 + random.nextInt(8)) - random.nextInt(8);
			int y = (par4 + random.nextInt(4)) - random.nextInt(4);
			int z = (par5 + random.nextInt(8)) - random.nextInt(8);

			if (world.isAirBlock(x, y, z) && world.getBlock(x, y - 1, z) == Blocks.grass && Blocks.pumpkin.canPlaceBlockAt(world, x, y, z))
			{
				if (random.nextInt(12000) == 0)
				{
					world.setBlock(x, y, z, ModBlocks.jack, random.nextInt(4), 2);
					return true;
				}
			}
		}

		return false;
	}
}
