package sq.core.radix;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import radixcore.data.AbstractPlayerData;
import radixcore.data.WatchedBoolean;
import sq.core.SQ;
import sq.data.WatcherIDsPlayerData;
import cpw.mods.fml.common.ModMetadata;

public final class PlayerData extends AbstractPlayerData
{
	public static final long serialVersionUID = 1L;

	public WatchedBoolean isMale;

	public PlayerData(String playerUUID, World world)
	{
		super(playerUUID, SQ.ID, world);
	}
	
	public PlayerData(EntityPlayer player)
	{
		super(player, SQ.ID);
	}

	@Override
	public ModMetadata getModMetadata() 
	{
		return SQ.getMetadata();
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
		SQ.getLog().info("--------PLAYER DATA DUMP--------");
		SQ.getLog().info("Owner: " + owner);
		SQ.getLog().info("Owner's Identity: " + ownerIdentifier);
	}
}
