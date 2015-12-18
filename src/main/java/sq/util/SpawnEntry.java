package sq.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class SpawnEntry 
{
	private final Class entityClass;
	private final int percentChanceToSpawn;
	private final int minimumGroupSpawn;
	private final int maximumGroupSpawn;
	private final EnumCreatureType creatureType;
	private final List<BiomeGenBase> spawnBiomes;
	
	public SpawnEntry(Class entityClass, int percentChanceToSpawn, int minimumSpawn, int maximumSpawn, EnumCreatureType type, BiomeGenBase... biomes)
	{
		this.spawnBiomes = new ArrayList<BiomeGenBase>();	
		this.entityClass = entityClass;
		this.percentChanceToSpawn = percentChanceToSpawn;
		this.creatureType = type;
		this.minimumGroupSpawn = minimumSpawn;
		this.maximumGroupSpawn = maximumSpawn;
		
		for (BiomeGenBase biome : biomes)
		{
			spawnBiomes.add(biome);
		}
	}
	
	public EnumCreatureType getCreatureType()
	{
		return creatureType;
	}
	
	public int getPercentChanceToSpawn()
	{
		return percentChanceToSpawn;
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
