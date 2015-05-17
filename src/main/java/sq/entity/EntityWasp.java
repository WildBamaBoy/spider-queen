package sq.entity;

import net.minecraft.world.World;

public class EntityWasp extends AbstractFlyingMob
{
	public EntityWasp(World world) 
	{
		super(world, "wasp");
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
		return 0.9F;
	}
	
	@Override
	public boolean isPassive()
	{
		return false;
	}
}

