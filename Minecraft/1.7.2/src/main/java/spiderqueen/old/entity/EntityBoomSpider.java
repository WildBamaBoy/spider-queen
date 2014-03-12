package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoomSpider extends EntityFriendlySpider
{

    public EntityBoomSpider(World world)
    {
        super(world);
        texture = "/imgz/boomspider_1.png";
        setSize(1.4F, 0.9F);
        moveSpeed = 0.8F;
		timeo = 0;
    }
	
	public int getMaxHealth() { return 30; }

	public String getEntityTexture()
    {
        if(getLevel() == 2)
        {
            return "/imgz/boomspider_3.png";
		}
        if(getLevel() == 1)
        {
            return "/imgz/boomspider_2.png";
		}
        return "/imgz/boomspider_1.png";
	}
	
    public void onDeath(DamageSource damagesource)
    {
		super.onDeath(damagesource);
		
		hurtArea(16F);
	}
	
	public int getAttackStrength()
	{
		return 3 + 4 * getLevel();
	}
	
	public void hurtArea(float explosionSize)
	{
        int k = MathHelper.floor_double(posX - (double)explosionSize - 1.0D);
        int i1 = MathHelper.floor_double(posX + (double)explosionSize + 1.0D);
        int k1 = MathHelper.floor_double(posY - (double)explosionSize - 1.0D);
        int l1 = MathHelper.floor_double(posY + (double)explosionSize + 1.0D);
        int i2 = MathHelper.floor_double(posZ - (double)explosionSize - 1.0D);
        int j2 = MathHelper.floor_double(posZ + (double)explosionSize + 1.0D);
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBoxFromPool(k, k1, i2, i1, l1, j2));
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        for(int k2 = 0; k2 < list.size(); k2++)
        {
            Entity entity = (Entity)list.get(k2);
            double d4 = entity.getDistance(posX, posY, posZ) / (double)explosionSize;
            if(d4 <= 1.0D)
            {
                double d6 = entity.posX - posX;
                double d8 = entity.posY - posY;
                double d10 = entity.posZ - posZ;
                double d11 = MathHelper.sqrt_double(d6 * d6 + d8 * d8 + d10 * d10);
                d6 /= d11;
                d8 /= d11;
                d10 /= d11;
                double d12 = worldObj.getBlockDensity(vec3d, entity.boundingBox);
                double d13 = (1.0D - d4) * d12;
				
				
				boolean don = false;
				if(entity instanceof EntityPlayer) { don = true; }
				if(entity instanceof EntityFriendlySpider) { don = true; }
				if(entity instanceof EntityESpiderQueen) { EntityESpiderQueen ee = (EntityESpiderQueen) entity; if(ee.getFriendly()) { don = true; } }
				
				if(!don)
				{
					entity.attackEntityFrom(DamageSource.causeMobDamage(this), (int)(((d13 * d13 + d13) / 2D) * 8D * (double)explosionSize + 1.0D)/16);
				}
                double d14 = d13;
                entity.motionX += d6 * d14;
                entity.motionY += d8 * d14;
                entity.motionZ += d10 * d14;
            }
        }
	}
	
	protected boolean canDespawn()
    {
        return false;
    }

    protected Entity findPlayerToAttack()
    {
        return mod_SpiderQueen.findEnemyToAttack(worldObj, this);
    }
	
    protected void attackEntity(Entity entity, float f)
    {
        float f1 = getBrightness(1.0F);
        if(f1 > 0.5F && rand.nextInt(100) == 0)
        {
            entityToAttack = null;
            return;
        }
        if(f > 2.0F && f < 7F && rand.nextInt(10) == 0)
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
			if(attackTime == 0)
            {
				double d = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f21 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.5F;
				d = d + entity.motionX * 7.2D * f21;
				d1 = d1 + entity.motionZ * 7.2D * f21;
				EntityBoomball entityarrow = new EntityBoomball(worldObj, this);
				double d2 = (entity.posY + 0.4D) - 0.20000000298023224D - entityarrow.posY;
				worldObj.playSoundAtEntity(this, "Octoball", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
				worldObj.spawnEntityInWorld(entityarrow);
				entityarrow.setBoomballHeading(d, d2, d1, 0.6F, 0F);
                attackTime = 25 - getLevel() * 4;
			}
            //super.attackEntity(entity, f);
        }
    }
	
	private int timeo;
}
