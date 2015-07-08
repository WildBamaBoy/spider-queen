package sq.entity.throwable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * The jack ball is the projectile thrown by Jack. It injures the player for 6 points.
 */
public class EntityJackBall extends EntityThrowable
{
	private EntityLivingBase shooter;
	
	public EntityJackBall(World world) 
	{
		super(world);
	}

    public EntityJackBall(World world, EntityLivingBase shooter, EntityLivingBase target, float speed, float unknown)
    {
        this(world);
        this.shooter = shooter;
        this.renderDistanceWeight = 10.0D;
        
        this.posY = shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D;
        double d0 = target.posX - shooter.posX;
        double d1 = target.boundingBox.minY + (double)(target.height / 3.0F) - this.posY;
        double d2 = target.posZ - shooter.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f2, f3);
            this.yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + (double)f4, d2, speed, unknown);
        }
    }
    
	@Override
	protected void onImpact(MovingObjectPosition objectPosition) 
	{
		if (objectPosition.entityHit instanceof EntityLivingBase && objectPosition.entityHit != shooter)
		{
			EntityLivingBase entityHit = (EntityLivingBase)objectPosition.entityHit;
			entityHit.attackEntityFrom(DamageSource.causeMobDamage(shooter), 6.0F);
			setDead();
		}
	}
}
