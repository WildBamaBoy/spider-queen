package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityThinSpider extends EntityFriendlySpider
{

    public EntityThinSpider(World world)
    {
        super(world);
        texture = "/imgz/thinspider_1.png";
        setSize(1.4F, 0.9F);
        moveSpeed = 1.4F;
        health = 13;
    }
	
	
	public String getEntityTexture()
    {
        if(getLevel() == 2)
        {
            return "/imgz/thinspider_3.png";
		}
        if(getLevel() == 1)
        {
            return "/imgz/thinspider_2.png";
		}
        return "/imgz/thinspider_1.png";
	}
	
	protected boolean canDespawn()
    {
        return false;
    }


    protected Entity findPlayerToAttack()
    {
        return mod_SpiderQueen.findEnemyToAttack(worldObj, this);
    }
	
	public void onLivingUpdate()
    {
		super.onLivingUpdate();
		if(entityToAttack != null)
		{
			if(mod_SpiderQueen.getDistance3d(posX,posY,posZ,entityToAttack.posX,entityToAttack.posY,entityToAttack.posZ) < 7D) { moveForward = -moveSpeed; }
		}
	}
	
    protected void attackEntity(Entity entity, float f)
    {
	
			boolean ins = false;
			if(entity instanceof EntityFly) { ins = true; }
			if(entity instanceof EntityCocoonAnt) { ins = true; }
			if(entity instanceof EntityCocoonWasp) { ins = true; }
			if(entity instanceof EntityCocoonGatherer) { ins = true; }
			if(entity instanceof EntityCocoonWarrior) { ins = true; }
			if(entity instanceof EntityCocoonQueenBee) { ins = true; }
			
			if(ins == true)
			{
				attack2Entity(entity,f);
				return;
			}
			
	    if(f < 10F)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            if(attackTime == 0)
            {
                EntityWeb entityarrow = new EntityWeb(worldObj, this, 2 + 3 * getLevel());
                entityarrow.posY += 1.3999999761581421D;
                double d2 = (entity.posY) - 0.20000000298023224D - entityarrow.posY;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.4F;
                worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                worldObj.spawnEntityInWorld(entityarrow);
                entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 0.6F, 9F - getLevel() * 2);
				entityarrow.setNoMake();
                attackTime = 25 - getLevel() * 4;
            }
            rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
            hasAttacked = true;
        }
		
        /*float f1 = getBrightness(1.0F);
        if(f1 > 0.5F && rand.nextInt(100) == 0)
        {
            entityToAttack = null;
            return;
        }
        if(f > 2.0F && f < 6F && rand.nextInt(10) == 0)
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
            super.attackEntity(entity, f);
        }*/
		
    }

}
