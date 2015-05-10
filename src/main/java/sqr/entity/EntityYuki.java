package sqr.entity;

import net.minecraft.world.World;

public class EntityYuki extends AbstractFlyingMob
{
	public EntityYuki(World world) 
	{
		super(world, "yuki");
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 20.0F;
	}

	@Override
	public float getHitDamage() 
	{
		return 0.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 0.75F;
	}
}

