package sqr.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import sqr.core.minecraft.ModBlocks;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block

public class WorldGenAnthill extends WorldGenerator
{
	
	public WorldGenAnthill()
	{
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		final byte byte0 = 3;
		final int l = random.nextInt(2) + 2;
		final int i1 = random.nextInt(2) + 2;
		int j1 = 0;
		for (int k1 = x - l - 1; k1 <= x + l + 1; k1++)
		{
			for (int j2 = y - 1; j2 <= y + byte0 + 1; j2++)
			{
				for (int i3 = z - i1 - 1; i3 <= z + i1 + 1; i3++)
				{
					final Material material = world.getBlock(k1, j2, i3).getMaterial();
					if (j2 == y - 1 && !material.isSolid())
					{
						return false;
					}
					if (j2 == y + byte0 + 1 && !material.isSolid())
					{
						return false;
					}
					if ((k1 == x - l - 1 || k1 == x + l + 1 || i3 == z - i1 - 1 || i3 == z + i1 + 1) && j2 == y && world.isAirBlock(k1, j2, i3) && world.isAirBlock(k1, j2 + 1, i3))
					{
						j1++;
					}
				}
				
			}
			
		}
		
		if (j1 < 1 || j1 > 5)
		{
			return false;
		}
		
		for (final int x5 = 0; x < 5; x++)
		{
			for (final int y5 = 0; y < 3; y++)
			{
				for (final int z5 = 0; z < 5; z++)
				{
					world.setBlock(x5 + x - 2, y5 + y, z5 + z - 2, Blocks.air);
				}
			}
		}
		
		world.setBlock(x, y, z, ModBlocks.anthill);
		System.out.print("New: " + x + "," + y + "," + z + "\n");
		return true;
	}
}
