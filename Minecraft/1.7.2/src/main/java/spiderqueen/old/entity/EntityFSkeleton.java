
	package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFSkeleton extends EntityFriendlySpider
{

    public EntityFSkeleton(World world)
    {
        super(world);
        texture = "/imgz/fskeleton.png";
        moveSpeed = 0.8F;
        setSize(1.4F, 0.9F);
    }

	public int getMaxHealth() { return 50; }

	
    protected Entity findPlayerToAttack()
    {
        return mod_SpiderQueen.findEnemyToAttack(worldObj, this);
    }
	
	protected boolean canDespawn()
    {
        return false;
    }
	
	public int getAttackStrength()
	{
		return 4 + 4 * getLevel();
	}
	
	
    public double getMountedYOffset()
    {
        return (double)height * 0.7D;
    }

	protected String getLivingSound()
    {
        return "mob.skeleton";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.skeletonhurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.skeletonhurt";
    }
	
	protected void attackEntity(Entity entity, float f)
    {
	    if(f < 10F)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            if(attackTime == 0)
            {
                EntityArrow entityarrow = new EntityArrow(worldObj, this, 2 + 3 * 2);
                entityarrow.posY += 1.3999999761581421D;
                double d2 = (entity.posY) - 0.20000000298023224D - entityarrow.posY;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.4F;
                worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                worldObj.spawnEntityInWorld(entityarrow);
                entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 0.6F, 9F - 2 * 2);
                attackTime = 25 - 2 * 4;
            }
            rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
            hasAttacked = true;
        }
    }
	
}
