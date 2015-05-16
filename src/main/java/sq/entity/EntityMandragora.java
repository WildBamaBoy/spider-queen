package sq.entity;

import net.minecraft.world.World;

public class EntityMandragora extends AbstractNewMob
{
	public EntityMandragora(World world) 
	{
		super(world, "mandragora");
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
		return 0.4F;
	}
}
