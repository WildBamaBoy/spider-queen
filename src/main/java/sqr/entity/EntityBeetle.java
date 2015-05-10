package sqr.entity;

import net.minecraft.world.World;

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
		return 0.8F;
	}
}
