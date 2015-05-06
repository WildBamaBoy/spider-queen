package sqr.core.radix;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import radixcore.data.AbstractPlayerData;
import radixcore.data.WatchedBoolean;
import sqr.core.SQR;
import sqr.data.WatcherIDsPlayerData;
import cpw.mods.fml.common.ModMetadata;

public final class PlayerData extends AbstractPlayerData
{
	public static final long serialVersionUID = 1L;

	public WatchedBoolean isMale;

	public PlayerData(String playerUUID, World world)
	{
		super(playerUUID, SQR.ID, world);
	}
	
	public PlayerData(EntityPlayer player)
	{
		super(player, SQR.ID);
	}

	@Override
	public ModMetadata getModMetadata() 
	{
		return SQR.getMetadata();
	}

	@Override
	public void instantiateData()
	{
		isMale = new WatchedBoolean(true, WatcherIDsPlayerData.IS_MALE, dataWatcher);
	}
	
	@Override
	public void initializeNewData(EntityPlayer player) 
	{
	}

	public void dumpToConsole()
	{
		SQR.getLog().info("--------PLAYER DATA DUMP--------");
		SQR.getLog().info("Owner: " + owner);
		SQR.getLog().info("Owner's Identity: " + ownerIdentifier);
	}
}
