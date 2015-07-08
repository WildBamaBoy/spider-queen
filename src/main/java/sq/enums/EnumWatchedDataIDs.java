package sq.enums;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import sq.entity.creature.EntityAnt;
import sq.entity.creature.EntityBee;
import sq.entity.creature.EntityHuman;

/**
 * Defines the data watcher IDs of the data that is watched by the DataWatcherEx. This is for player data.
 */
public enum EnumWatchedDataIDs
{
	IS_MALE(0),
	ANT_LIKE(1),
	BEE_LIKE(2),
	CREEPER_LIKE(3),
	SKELETON_LIKE(4),
	ZOMBIE_LIKE(5),
	HUMAN_LIKE(6),
	SPIDER_LIKE(7);
	
	private int id;
	
	EnumWatchedDataIDs(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public static EnumWatchedDataIDs byId(int id)
	{
		for (EnumWatchedDataIDs value : values())
		{
			if (value.id == id)
			{
				return value;
			}
		}
		
		return null;
	}
	
	public static boolean doesEntityHaveLikeStatus(Entity entity)
	{
		return entity instanceof EntityAnt || entity instanceof EntityCreeper || entity instanceof EntitySkeleton || entity instanceof EntityZombie ||
				entity instanceof EntitySpider || entity instanceof EntityBee || entity instanceof EntityHuman;
	}
}
