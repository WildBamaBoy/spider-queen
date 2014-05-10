package spiderqueen.core.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityOtherQueen;

public class CreatureReputationEntry
{
	private final Class	creatureClass;
	public String		creatureGroupName;
	public int			reputationValue;
	public int			creaturesKilled;
	public boolean		isAtWar;

	public static List<CreatureReputationEntry> getListOfCleanEntries()
	{
		final List<CreatureReputationEntry> returnList = new ArrayList<CreatureReputationEntry>();

		returnList.add(new CreatureReputationEntry(EntityCreeper.class, "Creepers"));
		returnList.add(new CreatureReputationEntry(EntitySkeleton.class, "Skeletons"));
		returnList.add(new CreatureReputationEntry(EntityFakePlayer.class, "Humans"));
		returnList.add(new CreatureReputationEntry(EntityOtherQueen.class, "Other Queens"));
		returnList.add(new CreatureReputationEntry(EntityZombie.class, "Zombies"));
		returnList.add(new CreatureReputationEntry(EntityEnderman.class, "Endermen"));
		returnList.add(new CreatureReputationEntry(EntitySpider.class, "Evil Queen"));

		return returnList;
	}

	public CreatureReputationEntry(Class creatureClass, String creatureGroupName)
	{
		this.creatureClass = creatureClass;
		this.creatureGroupName = creatureGroupName;
		reputationValue = 0;
		creaturesKilled = 0;
		isAtWar = false;
	}

	public Class getCreatureClass()
	{
		return creatureClass;
	}
}
