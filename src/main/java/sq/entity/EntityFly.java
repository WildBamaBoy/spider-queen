package sq.entity;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import sq.core.SpiderCore;

public class EntityFly extends AbstractFlyingMob
{
	public EntityFly(World world) 
	{
		super(world, "bee"); //Uses bee sounds.
		setSize(0.9F, 0.9F);
	}
	
	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		
		this.entityToAttack = null;
		
		if (!SpiderCore.getConfig().enableFly)
		{
			setDead();
		}
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
	}

	@Override
	public String getCommandSenderName() 
	{
		return "Fly";
	}

	@Override
	public float getHitDamage() 
	{
		return 0.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 0.7D;
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 20.0F;
	}

    protected Entity findPlayerToAttack()
    {
    	return null;
    }

	@Override
	public boolean isPassive() 
	{
		return true;
	}

	@Override
	public boolean getCanSpawnHere() 
	{
		return true;
	}
}
