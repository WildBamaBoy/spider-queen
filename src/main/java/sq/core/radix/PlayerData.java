package sq.core.radix;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ModMetadata;
import radixcore.data.AbstractPlayerData;
import radixcore.data.WatchedBoolean;
import radixcore.data.WatchedInt;
import sq.core.SpiderCore;
import sq.enums.EnumWatchedDataIDs;

/**
 * An extension from RadixCore that contains all "player data" that needs to persist after player logout.
 * Using AbstractPlayerData from RadixCore allows us to easily update the values client/server-side.
 */
public final class PlayerData extends AbstractPlayerData
{
	public static final long serialVersionUID = 1L;

	public WatchedBoolean isMale;
	public WatchedInt antLike;
	public WatchedInt beeLike;
	public WatchedInt creeperLike;
	public WatchedInt skeletonLike;
	public WatchedInt zombieLike;
	public WatchedInt humanLike;
	public WatchedInt spiderLike;
	
	public PlayerData(String playerUUID, World world)
	{
		super(playerUUID, SpiderCore.ID, world);
	}
	
	public PlayerData(EntityPlayer player)
	{
		super(player, SpiderCore.ID);
	}

	@Override
	public ModMetadata getModMetadata() 
	{
		return SpiderCore.getMetadata();
	}

	@Override
	public void instantiateData()
	{
		isMale = new WatchedBoolean(false, EnumWatchedDataIDs.IS_MALE.getId(), dataWatcher);
		antLike = new WatchedInt(0, EnumWatchedDataIDs.ANT_LIKE.getId(), dataWatcher);
		beeLike = new WatchedInt(0, EnumWatchedDataIDs.BEE_LIKE.getId(), dataWatcher);
		creeperLike = new WatchedInt(0, EnumWatchedDataIDs.CREEPER_LIKE.getId(), dataWatcher);
		skeletonLike = new WatchedInt(0, EnumWatchedDataIDs.SKELETON_LIKE.getId(), dataWatcher);
		zombieLike = new WatchedInt(0, EnumWatchedDataIDs.ZOMBIE_LIKE.getId(), dataWatcher);
		humanLike = new WatchedInt(0, EnumWatchedDataIDs.HUMAN_LIKE.getId(), dataWatcher);
		spiderLike = new WatchedInt(0, EnumWatchedDataIDs.SPIDER_LIKE.getId(), dataWatcher);
	}
	
	@Override
	public void initializeNewData(EntityPlayer player) 
	{
	}

	public void dumpToConsole()
	{
		SpiderCore.getLog().info("--------PLAYER DATA DUMP--------");
		SpiderCore.getLog().info("Owner: " + owner);
		SpiderCore.getLog().info("Owner's Identity: " + ownerIdentifier);
	}
	
	public WatchedInt getLikeValueById(int i)
	{
		switch (EnumWatchedDataIDs.byId(i))
		{
		case ANT_LIKE: return antLike;
		case BEE_LIKE: return beeLike;
		case CREEPER_LIKE: return creeperLike;
		case HUMAN_LIKE: return humanLike;
		case SKELETON_LIKE: return skeletonLike;
		case SPIDER_LIKE: return spiderLike;
		case ZOMBIE_LIKE: return zombieLike;
		default:
			return null;
		}
	}
}
