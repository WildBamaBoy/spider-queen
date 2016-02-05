package sq.gen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import radixcore.util.BlockHelper;
import sq.core.minecraft.ModBlocks;

/**
 * Generates the ant hill in the world.
 */
public class WorldGenAntHill implements IWorldGenerator
{
	public WorldGenAntHill()
	{
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (chunkGenerator instanceof ChunkProviderGenerate)
		{
			int x = chunkX * 16 + random.nextInt(16);
			int z = chunkZ * 16 + random.nextInt(16);

			for (int maxY = 256; maxY > 0; maxY--)
			{
				generate(world, random, x, maxY, z);
			}
		}
	}

	public boolean generate(World world, Random random, int i, int j, int k)
	{
		byte byte0 = 3;
		int l = random.nextInt(2) + 2;
		int i1 = random.nextInt(2) + 2;
		int j1 = 0;
		
		for(int k1 = i - l - 1; k1 <= i + l + 1; k1++)
		{
			for(int j2 = j - 1; j2 <= j + byte0 + 1; j2++)
			{
				for(int i3 = k - i1 - 1; i3 <= k + i1 + 1; i3++)
				{
					Material material = BlockHelper.getBlock(world, k1, j2, i3).getMaterial();
					
					if(j2 == j - 1 && !material.isSolid())
					{
						return false;
					}
					
					if(j2 == j + byte0 + 1 && !material.isSolid())
					{
						return false;
					}
					
					if((k1 == i - l - 1 || k1 == i + l + 1 || i3 == k - i1 - 1 || i3 == k + i1 + 1) && j2 == j && world.isAirBlock(k1, j2, i3) && world.isAirBlock(k1, j2 + 1, i3))
					{
						j1++;
					}
				}

			}

		}

		if(j1 < 1 || j1 > 5)
		{
			return false;
		}

		//This creates an empty square room around an origin point.
		for(int x = 0; x < 5; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				for(int z = 0; z < 5; z++)
				{
					BlockHelper.setBlock(world, i+(x-2),j+(y),k+(z-2), Blocks.air);
				}
			}
		}

		//Set the ant hill at the origin point.
		BlockHelper.setBlock(world, i,j,k, ModBlocks.antHill);
		return true;
	}
}