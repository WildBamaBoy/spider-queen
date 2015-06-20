package sq.entity;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.world.World;

//TODO
// Can fly to attack targets.
// Changes texture if flying.
public class EntityBeetle extends AbstractNewMob
{
	public EntityBeetle(World world) 
	{
		super(world, "beetle");
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 20.0F;
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
