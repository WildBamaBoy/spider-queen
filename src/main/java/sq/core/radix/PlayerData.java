package sq.core.radix;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import radixcore.data.AbstractPlayerData;
import radixcore.data.WatchedBoolean;
import radixcore.data.WatchedInt;
import sq.core.SpiderCore;
import sq.data.WatcherIDsPlayerData;
import cpw.mods.fml.common.ModMetadata;

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
		isMale = new WatchedBoolean(false, WatcherIDsPlayerData.IS_MALE, dataWatcher);
		antLike = new WatchedInt(0, WatcherIDsPlayerData.ANT_LIKE, dataWatcher);
		beeLike = new WatchedInt(0, WatcherIDsPlayerData.BEE_LIKE, dataWatcher);
		creeperLike = new WatchedInt(0, WatcherIDsPlayerData.CREEPER_LIKE, dataWatcher);
		skeletonLike = new WatchedInt(0, WatcherIDsPlayerData.SKELETON_LIKE, dataWatcher);
		zombieLike = new WatchedInt(0, WatcherIDsPlayerData.ZOMBIE_LIKE, dataWatcher);
		humanLike = new WatchedInt(0, WatcherIDsPlayerData.HUMAN_LIKE, dataWatcher);
		spiderLike = new WatchedInt(0, WatcherIDsPlayerData.SPIDER_LIKE, dataWatcher);
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
}
