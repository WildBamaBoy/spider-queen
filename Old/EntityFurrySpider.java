package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFurrySpider extends EntityFriendlySpider
{

    public EntityFurrySpider(World world)
    {
        super(world);
        texture = "/imgz/furryspider_1.png";
        setSize(1.4F, 0.9F);
        moveSpeed = 0.8F;
    }

	public int getMaxHealth() { return 30; }
	
	public String getEntityTexture()
    {
        if(getLevel() == 2)
        {
            return "/imgz/furryspider_3.png";
		}
        if(getLevel() == 1)
        {
            return "/imgz/furryspider_2.png";
		}
        return "/imgz/furryspider_1.png";
	}
	
	protected boolean canDespawn()
    {
        return false;
    }
	
	protected Entity findPlayerToAttack()
    {
		if(recharge) { return null; }
		Entity nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 7);
		if(nn instanceof EntityFriendlySpider)
		{
			EntityFriendlySpider tnt = (EntityFriendlySpider) nn;
			if(tnt.health + 5 < tnt.getMaxHealth()) { return nn; }
		}
		
		return null;
    }
	
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
        if(super.attackEntityFrom(damagesource, i))
        {
            if(riddenByEntity == entity || ridingEntity == entity)
            {
                return true;
            }
            if(entity != this)
            {
                entityToAttack = null;
            }
            return true;
        } else
        {
            return false;
        }
    }
	
    public void onLivingUpdate()
    {
		super.onLivingUpdate();
		if(recharge) { entityToAttack = null; rechargeAmt--; }
		if(rechargeAmt < 0) { recharge = false; rechargeAmt = 0; }
		if(rechargeAmt > 8 + getLevel() * 8 & recharge == false) { recharge = true; rechargeAmt = 500; }
	}
	
	
    protected void attackEntity(Entity entity, float f)
    {
		if(entity instanceof EntityFriendlySpider)
		{}
		else
		{
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
			return;
		}
		
		if(recharge) { entityToAttack = null; return; }
       float f1 = getBrightness(1.0F);
        if(f1 > 0.5F && rand.nextInt(100) == 0)
        {
            entityToAttack = null;
            return;
        }
		
		Random rr = new Random();
		
		if(rr.nextInt(20) == 0 & mod_SpiderQueen.getDistance3d(entityToAttack.posX,entityToAttack.posY,entityToAttack.posZ,posX,posY,posZ) < 5D)
		{
            if(entityToAttack instanceof EntityFriendlySpider)
			{
				EntityFriendlySpider tnt = (EntityFriendlySpider) entityToAttack;
				tnt.health = tnt.health + 1;
				if(rr.nextInt(3) == 0 ) { tnt.showHeartsOrSmokeFX(true); }
				rechargeAmt++;
				if(tnt.health > tnt.getMaxHealth()) { tnt.health = tnt.getMaxHealth(); }
//				System.out.print(f + ", " + tnt.health + "\n");
				entityToAttack = null;
			}
			else
			{
				entityToAttack = null;
			}
		}
    }

	private boolean recharge;
	private int rechargeAmt;
}
