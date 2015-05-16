package sq.entity;

import net.minecraft.world.World;

public abstract class AbstractFlyingMob extends AbstractNewMob 
{
	public AbstractFlyingMob(World world, String codeName)
	{
		super(world, codeName);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
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
