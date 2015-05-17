package sq.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;
import radixcore.constant.Time;
import radixcore.util.RadixMath;

public class EntityYuki extends AbstractFlyingMob
{
	private int lightningTimer;

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
			lightningTimer--;

			if (lightningTimer <= 0)
			{
				lightningTimer = Time.SECOND * RadixMath.getNumberInRange(3, 5);

				if (this.getEntityToAttack() != null)
				{
					int modX = RadixMath.getNumberInRange(-3, 3);
					int modZ = RadixMath.getNumberInRange(-3, 3);

					Entity entityToAttack = this.getEntityToAttack();
					EntityLightningBolt lightning = new EntityLightningBolt(worldObj, entityToAttack.posX + modX, entityToAttack.posY, entityToAttack.posZ + modZ);
					worldObj.spawnEntityInWorld(lightning);
				}
			}
		}
	}
}

