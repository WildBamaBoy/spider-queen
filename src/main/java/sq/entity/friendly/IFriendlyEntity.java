package sq.entity.friendly;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;

/**
 * Defines an entity that is a friendly version of a previously hostile creature.
 */
public interface IFriendlyEntity 
{
	/** Sets the remembered UUID of the owner player. */
	void setFriendPlayerUUID(UUID value);
	
	/** Gets the UUID of the owner player. */
	UUID getFriendPlayerUUID();
	
	/** Returns an EntityCreature instance of this friendly. */
	EntityCreature getInstance();
	
	/** Returns this friendly's current target. */
	EntityLivingBase getTarget();
	
	/** Sets this friendly's current target. */
	void setTarget(EntityLivingBase target);
	
	/** Performs the friendly's attack. */
	boolean doManualAttack(Entity entityBeingAttacked, float damageAmount);
	
	/** Gets the time until the friendly is going to speak. */
	int getTimeUntilSpeak();
	
	/** Sets the time until the friendly is going to speak. */
	void setTimeUntilSpeak(int value);
	
	/** Returns the creature's type as used in the language files. */
	String getSpeakId();
	
	/** Returns whether or not this creature is imprisoned. Imprisoned creatures yield a reputation boost on interact. */
	boolean isImprisoned();
	
	/** Sets this creature as imprisoned. */
	void setImprisoned(boolean value);
	
	/** Returns the class of the creature that is the non-friendly form of the creature implementing this interface. */
	Class getNonFriendlyClass();
}