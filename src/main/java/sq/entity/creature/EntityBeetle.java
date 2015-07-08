package sq.entity.creature;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.world.World;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.entity.AbstractNewMob;

/**
 * The beetle is a flying mob that is hostile to the player and bees.
 */
public class EntityBeetle extends AbstractNewMob
{
	private EntityLivingBase target;

	public EntityBeetle(World world) 
	{
		super(world, "beetle");
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(13, 0);
	}

	public void setIsFlying(boolean value)
	{
		if (!worldObj.isRemote)
		{
			this.dataWatcher.updateObject(13, value == true ? 1 : 0);
		}
	}

	@Override
	public boolean isOnLadder() 
	{
		return isCollidedHorizontally;
	}

	public boolean getIsFlying()
	{
		return this.dataWatcher.getWatchableObjectInt(13) == 1 ? true : false;
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 20.0F;
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		if (!SpiderCore.getConfig().enableBeetle)
		{
			setDead();
		}

		//"target" stores the attack target assigned by the AI system.
		//We set this to prevent flipping between multiple targets, which makes the beetle do nothing at all.
		if (this.getAttackTarget() != null && target == null)
		{
			target = this.getAttackTarget();
		}

		if (target != null)
		{
			//If we have a target, the beetle will start to fly.
			if (!getIsFlying())
			{
				setIsFlying(true);
			}

			//Flying code towards the target.
			double sqDistanceTo = Math.sqrt(Math.pow(target.posX - posX, 2) + Math.pow(target.posZ - posZ, 2));
			float moveAmount = 0.0F;

			if(sqDistanceTo < 8F) 
			{ 
				moveAmount = ((8F - (float)sqDistanceTo) / 8F)*4F; 
			}

			if (target.posY < posY)
			{
				motionY = motionY - 0.15F * moveAmount;
			}

			if(target.posY > posY)
			{
				motionY = motionY + 0.11F * moveAmount;
			}

			double distanceTo = RadixMath.getDistanceToEntity(this, target);
			
			if (distanceTo >= 4 && distanceTo <= 9)
			{
				motionX = motionX * 1.1F;
				motionZ = motionZ * 1.1F;
			}
		}

		else //If we don't have a target and we're flying, stop.
		{
			if (getIsFlying())
			{
				setIsFlying(false);
			}
		}

		//Reset our target if it's dead or more than 16 blocks away.
		if (target != null && (target.isDead || RadixMath.getDistanceToEntity(this, target) >= 16.0D))
		{
			target = null;
		}

		//When we're flying, run the flying code for passive movement.
		if (getIsFlying())
		{
			if (motionY > 0)
			{
				motionY = motionY * 1.03F;
			}

			else
			{
				double yMod = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
				motionY = motionY * 0.3F + yMod * 0.3F;
			}

			fallDistance = 0.0F;
		}
	}

	@Override
	public float getHitDamage() 
	{
		return 4.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 0.45F;
	}

	@Override
	public boolean isPassive() 
	{
		return false;
	}

	@Override
	public void appendAI()
	{
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityBee.class, 0, true));
	}

	@Override
	public boolean getCanSpawnHere() 
	{
		return true;
	}
}
