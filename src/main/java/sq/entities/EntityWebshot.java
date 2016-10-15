package sq.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import sq.enums.EnumCocoonType;

public class EntityWebshot extends EntityThrowable 
{
	public EntityWebshot(World world) 
	{
		super(world);
	}

	public EntityWebshot(World world, EntityLivingBase thrower)
	{
		super(world, thrower);
	}

	@Override
	protected void onImpact(RayTraceResult result) 
	{
		if (result.entityHit != null)
		{
			try
			{
				Entity entityHit = result.entityHit;
				EnumCocoonType cocoonType = EnumCocoonType.getCocoonType(entityHit);

				if (cocoonType != null)
				{
					entityHit.setDead();

					if (!worldObj.isRemote)
					{
						EntityCocoon cocoon = new EntityCocoon(worldObj, cocoonType);
						cocoon.setHeldEntity(entityHit);
						cocoon.setPosition(entityHit.posX, entityHit.posY, entityHit.posZ);
						worldObj.spawnEntityInWorld(cocoon);
					}
				}

				setDead();
			}

			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
