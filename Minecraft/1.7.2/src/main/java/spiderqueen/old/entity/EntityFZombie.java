
	package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFZombie extends EntityFriendlySpider
{

    public EntityFZombie(World world)
    {
        super(world);
        texture = "/imgz/fzombie.png";
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
	
	 protected String getLivingSound()
    {
        return "mob.zombie";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.zombiehurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.zombiedeath";
    }

	
    public double getMountedYOffset()
    {
        return (double)height * 0.7D;
    }

	
	protected void attackEntity(Entity entity, float f)
    {
        float f1 = getBrightness(1.0F);
        if(f1 > 0.5F && rand.nextInt(100) == 0)
        {
            entityToAttack = null;
            return;
        }
        if(f > 2.0F && f < 6F && rand.nextInt(40) == 0)
        {
            if(onGround)
            {
                double d = entity.posX - posX;
                double d1 = entity.posZ - posZ;
                float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
                motionX = (d / (double)f2) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
                motionZ = (d1 / (double)f2) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
                motionY = 0.40000000596046448D;
            }
        } else
        {
            attack2Entity(entity, f);
        }
    }
	
}
