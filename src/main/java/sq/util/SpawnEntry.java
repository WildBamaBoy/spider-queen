package sq.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;

public class SpawnEntry 
{
	private final Class entityClass;
	private final int percentChanceToSpawn;
	private final int minimumGroupSpawn;
	private final int maximumGroupSpawn;
	private final List<BiomeGenBase> spawnBiomes;
	private final int maximumInArea;
	
	public SpawnEntry(Class entityClass, int percentChanceToSpawn, int minimumSpawn, int maximumSpawn, int maximumInArea, BiomeGenBase... biomes)
	{
		this.spawnBiomes = new ArrayList<BiomeGenBase>();	
		this.entityClass = entityClass;
		this.percentChanceToSpawn = percentChanceToSpawn;
		this.minimumGroupSpawn = minimumSpawn;
		this.maximumGroupSpawn = maximumSpawn;
		this.maximumInArea = maximumInArea;
		
		for (BiomeGenBase biome : biomes)
		{
			spawnBiomes.add(biome);
		}
	}
	
	public int getMaxInArea()
	{
		return maximumInArea;
	}
	
	public int getMaximumSpawn()
	{
		return maximumGroupSpawn;
	}
	
	public int getMinimumSpawn()
	{
		return minimumGroupSpawn;
	}
	
	public List<BiomeGenBase> getSpawnBiomes()
	{
		return spawnBiomes;
	}

	public Class getSpawnClass() 
	{
		return entityClass;
	}
}
