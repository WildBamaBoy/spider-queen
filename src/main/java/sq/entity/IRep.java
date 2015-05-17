package sq.entity;

import radixcore.data.WatchedInt;
import sq.core.radix.PlayerData;

public interface IRep 
{
	public WatchedInt getLikeData(PlayerData data);
}
