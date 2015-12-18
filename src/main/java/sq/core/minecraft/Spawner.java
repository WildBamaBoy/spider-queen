package sq.core.minecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import radixcore.math.Point3D;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.util.SpawnEntry;
import sq.util.Utils;

public class Spawner 
{
	private static List<SpawnEntry> spawnEntries = new ArrayList<SpawnEntry>();

	public static void registerSpawnEntry(SpawnEntry entry)
	{
		if (SpiderCore.getConfig().useSpawnSystem)
		{
			spawnEntries.add(entry);
		}

		else
		{
			EntityRegistry.addSpawn(
					entry.getSpawnClass(), 
					entry.getPercentChanceToSpawn(), entry.getMinimumSpawn(), entry.getMaximumSpawn(), 
					entry.getCreatureType(), (BiomeGenBase[])entry.getSpawnBiomes().toArray());
		}
	}

	public static void runSpawner(World world)
	{
		final int MIN_DISTANCE_FROM_PLAYER = 16;
		final int MAX_DISTANCE_FROM_PLAYER = 32;
		final int MIN_OFFSET = 3;
		final int MAX_OFFSET = 8;

		if (spawnEntries.size() > 0) //Will be zero if spawner is turned off.
		{
			for (Object obj : world.playerEntities)
			{
				EntityPlayer player = (EntityPlayer)obj;
				SpawnEntry entry = getNextSpawnEntry();
				BiomeGenBase biome = world.getBiomeGenForCoords((int)player.posX, (int)player.posZ);

				List<Entity> entities = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(world, player.posX, player.posY, player.posZ, MAX_DISTANCE_FROM_PLAYER);
				int sqEntityCount = 0;

				for (Entity entity : entities)
				{
					if (entity.getClass().getName().contains("sq.entity"))
					{
						sqEntityCount++;
					}
				}
				
				if (sqEntityCount < SpiderCore.getConfig().maxSQEntities && entry.getSpawnBiomes().contains(biome))
				{
					int spawnAmount = RadixMath.getNumberInRange(entry.getMinimumSpawn(), entry.getMaximumSpawn());

					Point3D groupSpawnArea = new Point3D(
							player.posX + Utils.plusOrMinus(RadixMath.getNumberInRange(MIN_DISTANCE_FROM_PLAYER, MAX_DISTANCE_FROM_PLAYER)),
							255.0D,
							player.posZ + Utils.plusOrMinus(RadixMath.getNumberInRange(MIN_DISTANCE_FROM_PLAYER, MAX_DISTANCE_FROM_PLAYER)));

					while (spawnAmount > 0)
					{
						spawnAmount--;

						try
						{
							EntityCreature entity = (EntityCreature) entry.getSpawnClass().getConstructor(World.class).newInstance(world);
							Point3D spawnPoint = new Point3D(
									groupSpawnArea.dPosX + Utils.plusOrMinus(RadixMath.getNumberInRange(MIN_OFFSET, MAX_OFFSET)),
									255.0D,
									groupSpawnArea.dPosZ + Utils.plusOrMinus(RadixMath.getNumberInRange(MIN_OFFSET, MAX_OFFSET)));

							//Find the ground
							while (spawnPoint.dPosY > 0)
							{
								Block blockAtPoint = world.getBlock((int)spawnPoint.dPosX, (int)spawnPoint.dPosY, (int)spawnPoint.dPosZ);

								if (blockAtPoint != Blocks.air)
								{
									spawnPoint.dPosY++;
									break;
								}

								else
								{
									spawnPoint.dPosY--;
								}
							}
							
							entity.setPosition(spawnPoint.dPosX, spawnPoint.dPosY, spawnPoint.dPosZ);

							if (entity.getCanSpawnHere())
							{
								world.spawnEntityInWorld(entity);
							}
						}

						catch (Exception e)
						{
							e.printStackTrace(System.err);
						}
					}
				}
			}
		}
	}

	private static SpawnEntry getNextSpawnEntry()
	{
		int totalSum = 0;

        for(SpawnEntry entry : spawnEntries) 
        {
            totalSum += entry.getPercentChanceToSpawn();
        }
        
		int index = SpiderCore.rand.nextInt(totalSum);
		int sum = 0;
		int i = 0;

		while (sum < index) 
		{
			sum += spawnEntries.get(i++).getPercentChanceToSpawn();
		}

		return spawnEntries.get(Math.max(0,i-1));
	}

	private Spawner()
	{
	}
}
