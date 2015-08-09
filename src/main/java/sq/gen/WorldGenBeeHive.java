package sq.gen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import sq.core.minecraft.ModBlocks;

public class WorldGenBeeHive implements IWorldGenerator
{
	public WorldGenBeeHive()
	{
		super();
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
				if (generate(world, random, x, maxY, z))
				{
					break;
				}
			}
		}
	}

	public boolean generate(World world, Random random, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);

		//Find a tree
		if (block == Blocks.log)
		{
			//Find the top.
			int yMov = 0;

			while (yMov < 30)
			{
				if (world.getBlock(x, y + yMov, z) != Blocks.log)
				{
					yMov--;
					break;
				}

				yMov++;
			}

			int topLogY = y + yMov;

			//At this point we've found the top of the tree. Test for leaves above the top log to verify it's a tree.
			if (world.getBlock(x, topLogY + 1, z) instanceof BlockLeaves)
			{
				int midLogY = topLogY - 2; //Move down towards the middle of the tree.
				int leavesRadius = 2;

				int pass = 1;

				while (leavesRadius > 0)
				{
					if (pass == 1)
					{
						if (random.nextBoolean()) { world.setBlock(x + 1, midLogY + 1, z, ModBlocks.beeHive); }
						world.setBlock(x + 1, midLogY, z, ModBlocks.beeHive);
						if (random.nextBoolean()) { world.setBlock(x + 1, midLogY - 1, z, ModBlocks.beeHive); }
						if (random.nextBoolean()) { world.setBlock(x + 1, midLogY, z + 1, ModBlocks.beeHive); }
						world.setBlock(x + 1, midLogY, z - 1, ModBlocks.beeHive);
					}

					else
					{
						if (world.getBlock(x + pass + 1, midLogY, z) != Blocks.air)
						{
							world.setBlock(x + pass, midLogY, z, ModBlocks.beeHive);
						}
					}

					pass++;
					leavesRadius--;
				}
			}
		}

		return false;
	}
}
