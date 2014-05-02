package spiderqueen.core.forge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerReputationHandler implements IExtendedEntityProperties
{
	public static final String ID = "SpiderQueenReputationHandler";
	private final EntityPlayer player;

	public int reputationCreepers;
	public int reputationHumans;
	public int reputationSkeletons;
	public int reputationZombies;
	public int reputationFriendlySpiderQueens;
	public int reputationEvilSpiderQueen;

	public int creepersKilled;
	public int humansKilled;
	public int skeletonsKilled;
	public int zombiesKilled;
	public int friendlySpidersKilled;
	public int spidersKilled;

	public boolean isAtWarWithCreepers;
	public boolean isAtWarWithHumans;
	public boolean isAtWarWithSkeletons;
	public boolean isAtWarWithZombies;
	public boolean isAtWarWithFriendlySpiderQueens;
	public boolean isAtWarWithEvilSpiderQueen;

	public PlayerReputationHandler(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt) 
	{
		nbt.setInteger("reputationCreepers", reputationCreepers);
		nbt.setInteger("reputationHumans", reputationHumans);
		nbt.setInteger("reputationSkeletons", reputationSkeletons);
		nbt.setInteger("reputationZombies", reputationZombies);
		nbt.setInteger("reputationFriendlySpiderQueens", reputationFriendlySpiderQueens);
		nbt.setInteger("reputationEvilSpiderQueen", reputationEvilSpiderQueen);

		nbt.setInteger("creepersKilled", creepersKilled);
		nbt.setInteger("humansKilled", humansKilled);
		nbt.setInteger("skeletonsKilled", skeletonsKilled);
		nbt.setInteger("zombiesKilled", zombiesKilled);
		nbt.setInteger("friendlySpidersKilled", friendlySpidersKilled);
		nbt.setInteger("spidersKilled", spidersKilled);

		nbt.setBoolean("isAtWarWithCreepers", isAtWarWithCreepers);
		nbt.setBoolean("isAtWarWithHumans", isAtWarWithHumans);
		nbt.setBoolean("isAtWarWithSkeletons", isAtWarWithSkeletons);
		nbt.setBoolean("isAtWarWithZombies", isAtWarWithZombies);
		nbt.setBoolean("isAtWarWithFriendlySpiderQueens", isAtWarWithFriendlySpiderQueens);
		nbt.setBoolean("isAtWarWithEvilSpiderQueen", isAtWarWithEvilSpiderQueen);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		reputationCreepers = nbt.getInteger("reputationCreepers");
		reputationHumans = nbt.getInteger("reputationHumans");
		reputationSkeletons = nbt.getInteger("reputationSkeletons");
		reputationZombies = nbt.getInteger("reputationZombies");
		reputationFriendlySpiderQueens = nbt.getInteger("reputationFriendlySpiderQueens");
		reputationEvilSpiderQueen = nbt.getInteger("reputationEvilSpiderQueen");

		creepersKilled = nbt.getInteger("creepersKilled");
		humansKilled = nbt.getInteger("humansKilled");
		skeletonsKilled = nbt.getInteger("skeletonsKilled");
		zombiesKilled = nbt.getInteger("zombiesKilled");
		friendlySpidersKilled = nbt.getInteger("friendlySpidersKilled");
		spidersKilled = nbt.getInteger("spidersKilled");

		isAtWarWithCreepers = nbt.getBoolean("isAtWarWithCreepers");
		isAtWarWithHumans = nbt.getBoolean("isAtWarWithHumans");
		isAtWarWithSkeletons = nbt.getBoolean("isAtWarWithSkeletons");
		isAtWarWithZombies = nbt.getBoolean("isAtWarWithZombies");
		isAtWarWithFriendlySpiderQueens = nbt.getBoolean("isAtWarWithFriendlySpiderQueens");
		isAtWarWithEvilSpiderQueen = nbt.getBoolean("isAtWarWithEvilSpiderQueen");
	}

	@Override
	public void init(Entity entity, World world) 
	{
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(PlayerReputationHandler.ID, new PlayerReputationHandler(player));
	}
}
