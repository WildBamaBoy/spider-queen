
	package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.world.World;

public class EntityFCreeper extends EntityFriendlySpider
{
	private int cstate;
    int timeSinceIgnited;
    int lastActiveTime;

    public EntityFCreeper(World world)
    {
        super(world);
        texture = "/imgz/fcreeper.png";
		cstate = 0;
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
        return null;
    }

    protected String getHurtSound()
    {
        return null;
    }

    protected String getDeathSound()
    {
        return null;
    }

    protected void updateEntityActionState()
    {
		super.updateEntityActionState();
		
		if (isEntityAlive())
        {
            lastActiveTime = timeSinceIgnited;
            int i = cstate;

            if (i > 0 && timeSinceIgnited == 0)
            {
                worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 0.5F);
            }

            timeSinceIgnited += i;

            if (timeSinceIgnited < 0)
            {
                timeSinceIgnited = 0;
            }

            if (timeSinceIgnited >= 30)
            {
                timeSinceIgnited = 30;

                if (!worldObj.isRemote)
                {
                    worldObj.createExplosion(this, posX, posY, posZ, 3F);
                    setDead();
                }
            }
        }
		
	}
	
    public float setCreeperFlashTime(float par1)
    {
        return ((float)lastActiveTime + (float)(timeSinceIgnited - lastActiveTime) * par1) / 28F;
    }
	
    public boolean getPowered()
	{
		return false;
	}
	
	protected void attackEntity(Entity entity, float f)
    {
        float f1 = getBrightness(1.0F);
		
        if(f < 5F)
        {
			cstate = 1;
		}
		else
		{
			cstate = 0;
		}
    }
	
}
