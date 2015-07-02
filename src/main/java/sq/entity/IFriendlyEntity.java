package sq.entity;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;

public interface IFriendlyEntity 
{
	void setFriendPlayerUUID(UUID value);
	UUID getFriendPlayerUUID();
	EntityCreature getInstance();
	EntityLivingBase getTarget();
	void setTarget(EntityLivingBase target);
	boolean doManualAttack(Entity entityBeingAttacked, float damageAmount);
	int getTimeUntilSpeak();
	void setTimeUntilSpeak(int value);
	String getSpeakId();
}