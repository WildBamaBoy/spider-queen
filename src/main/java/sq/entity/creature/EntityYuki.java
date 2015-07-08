package sq.entity.creature;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import radixcore.constant.Time;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.core.minecraft.ModItems;
import sq.entity.AbstractFlyingMob;
import sq.entity.throwable.EntityFreezeBall;

/**
 * Yuki is a hostile creature that attacks with a freezing projectile periodically. 
 * It drops the freeze rod on death.
 */
public class EntityYuki extends AbstractFlyingMob
{
	private int freezeTimer;

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

	@Override
	public boolean isPassive()
	{
		return false;
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		if (!worldObj.isRemote)
		{
			if (!SpiderCore.getConfig().enableYuki)
			{
				setDead();
			}
			
			freezeTimer--;

			if (freezeTimer <= 0)
			{
				freezeTimer = Time.SECOND * RadixMath.getNumberInRange(1, 3);

				if (this.getEntityToAttack() != null)
				{
					Entity entityToAttack = this.getEntityToAttack();
					EntityFreezeBall ball = new EntityFreezeBall(worldObj, this, (EntityLivingBase) this.getEntityToAttack(), 2.0F, 1.0F);
					worldObj.spawnEntityInWorld(ball);
					worldObj.playSoundAtEntity(this, "sq:freeze.rod", 1.0F, 1.0F);
				}
			}
		}
	}

	@Override
	public boolean getCanSpawnHere() 
	{
		if (worldObj.isRaining())
		{
			//Make sure there's no others in the area.
			List<Entity> entities = RadixLogic.getAllEntitiesOfTypeWithinDistance(EntityYuki.class, this, 16);
			
			if (entities.size() > 1)
			{
				return false;
			}
			
			else
			{
				return true;
			}
		}
		
		else
		{
			return super.getCanSpawnHere();
		}
	}

	@Override
	protected boolean canDespawn() 
	{
		return false;
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int fortune) 
	{
		if (RadixLogic.getBooleanWithProbability(75))
		{
			this.dropItem(ModItems.freezeRod, 1);
		}
	}
}

