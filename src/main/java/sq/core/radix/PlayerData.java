package sq.core.radix;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import radixcore.data.AbstractPlayerData;
import radixcore.data.WatchedBoolean;
import sq.core.SpiderCore;
import sq.data.WatcherIDsPlayerData;
import cpw.mods.fml.common.ModMetadata;

public final class PlayerData extends AbstractPlayerData
{
	public static final long serialVersionUID = 1L;

	public WatchedBoolean isMale;

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
