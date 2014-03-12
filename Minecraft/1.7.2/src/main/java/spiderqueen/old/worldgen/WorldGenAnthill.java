// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.worldgen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block

public class WorldGenAnthill extends WorldGenerator
{

    public WorldGenAnthill()
    {
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
                    Material material = world.getBlockMaterial(k1, j2, i3);
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
		
        for(int x = 0; x < 5; x++)
        {
			for(int y = 0; y < 3; y++)
			{
				for(int z = 0; z < 5; z++)
				{
					world.setBlock(i+(x-2),j+(y),k+(z-2),0);
				}
			}
        }
		
		world.setBlock(i,j,k,mod_SpiderQueen.anthill.blockID);
		System.out.print("New: " + i + "," + j + "," + k + "\n");
        return true;
    }
}
