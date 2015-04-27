package sqr.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import sqr.core.minecraft.ModBlocks;

public class WorldGenPumpkin extends WorldGenerator
{
	public WorldGenPumpkin()
	{
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int i = 0; i < 64; i++)
		{
			final int j = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			final int k = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			final int l = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(j, k, l) && par1World.getBlock(j, k - 1, l) == Blocks.grass && Blocks.pumpkin.canPlaceBlockAt(par1World, j, k, l))
			{

				if(par2Random.nextInt(6) == 0)
				{
					par1World.setBlock(j, k, l, ModBlocks.bjack, par2Random.nextInt(4), 2);
				}
				else
				{
					par1World.setBlock(j, k, l, Blocks.pumpkin, par2Random.nextInt(4), 2);
				}
			}
		}

		return true;
	}
}
