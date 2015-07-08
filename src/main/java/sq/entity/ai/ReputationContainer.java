package sq.entity.ai;

import java.util.ArrayList;
import java.util.List;

import radixcore.data.WatchedInt;
import sq.core.radix.PlayerData;
import sq.entity.IRep;

/**
 * Creates and holds reputation data for mobs by implementing IRep and returning 
 * the like data for mob classes that have been registered.
 */
public class ReputationContainer implements IRep
{
	private static List<ReputationContainer> reputations = new ArrayList<ReputationContainer>();
	private Class creatureClass;
	private int likeValueId;
	
	private ReputationContainer(Class creatureClass, int likeValueId)
	{
		this.creatureClass = creatureClass;
		this.likeValueId = likeValueId;
	}
	
	@Override
	public WatchedInt getLikeData(PlayerData data) 
	{
		return data.getLikeValueById(likeValueId);
	}
	
	public static void createNew(Class creatureClass, int likeValueId)
	{
		ReputationContainer container = new ReputationContainer(creatureClass, likeValueId);
		reputations.add(container);
	}
	
	public static WatchedInt getLikeDataByClass(Class creatureClass, PlayerData data)
	{
		for (ReputationContainer container : reputations)
		{
			if (creatureClass.equals(container.creatureClass))
			{
				return container.getLikeData(data);
			}
		}
		
		return null;
	}
}