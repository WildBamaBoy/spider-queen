package sq.entity;

import radixcore.data.WatchedInt;
import sq.core.radix.PlayerData;

/**
 * Creatures that implement this interface are assumed to belong to a reputation group, so they must return
 * their like data when given a set of player data.
 */
public interface IRep 
{
	/** Returns a WatchedInt that is a reference to the like data for the creature implementing this interface. */
	public WatchedInt getLikeData(PlayerData data);
}
