package spiderqueen.old.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPumpkin extends WorldGenerator
{
    public WorldGenPumpkin()
    {
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (int i = 0; i < 64; i++)
        {
            int j = (par3 + par2Random.nextInt(8)) - par2Random.nextInt(8);
            int k = (par4 + par2Random.nextInt(4)) - par2Random.nextInt(4);
            int l = (par5 + par2Random.nextInt(8)) - par2Random.nextInt(8);

            if (par1World.isAirBlock(j, k, l) && par1World.getBlockId(j, k - 1, l) == Block.grass.blockID && Block.pumpkin.canPlaceBlockAt(par1World, j, k, l))
            {
			
				if(par2Random.nextInt(6) == 0)
				{
					par1World.setBlockAndMetadata(j, k, l, mod_SpiderQueen.bjack.blockID, par2Random.nextInt(4));
				}
				else
				{
					par1World.setBlockAndMetadata(j, k, l, Block.pumpkin.blockID, par2Random.nextInt(4));
				}
            }
        }

        return true;
    }
}
