// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Entity, AxisAlignedBB, EntityLiving, MathHelper, 
//            World, Vec3D, MovingObjectPosition, NBTTagCompound, 
//            EntityPlayer, ItemStack, Item, InventoryPlayer

public class EntityBoomball extends Entity
{

    public EntityBoomball(World world)
    {
        super(world);
        xTileSnowball = -1;
        yTileSnowball = -1;
        zTileSnowball = -1;
        inTileSnowball = 0;
        inGroundSnowball = false;
        shakeSnowball = 0;
        ticksInAirSnowball = 0;
        setSize(0.25F, 0.25F);
    }

    protected void entityInit()
    {
    }

    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = boundingBox.getAverageEdgeLength() * 4D;
        d1 *= 64D;
        return d < d1 * d1;
    }

    public EntityBoomball(World world, EntityLiving entityliving)
    {
        super(world);
        xTileSnowball = -1;
        yTileSnowball = -1;
        zTileSnowball = -1;
        inTileSnowball = 0;
        inGroundSnowball = false;
        shakeSnowball = 0;
        ticksInAirSnowball = 0;
        thrower = entityliving;
        setSize(0.25F, 0.25F);
        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        float f = 0.4F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
        motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F) * f;
        setBoomballHeading(motionX, motionY, motionZ, 1.5F, 1.0F);
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
					entity.attackEntityFrom(DamageSource.causeMobDamage(thrower), (int)(((d13 * d13 + d13) / 2D) * 8D * (double)explosionSize + 1.0D)/16);
				}
                double d14 = d13;
                entity.motionX += d6 * d14;
                entity.motionY += d8 * d14;
                entity.motionZ += d10 * d14;
            }
        }
	}
	
	
    public EntityBoomball(World world, double d, double d1, double d2)
    {
        super(world);
        xTileSnowball = -1;
        yTileSnowball = -1;
        zTileSnowball = -1;
        inTileSnowball = 0;
        inGroundSnowball = false;
        shakeSnowball = 0;
        ticksInAirSnowball = 0;
        ticksInGroundSnowball = 0;
        setSize(0.25F, 0.25F);
        setPosition(d, d1, d2);
        yOffset = 0.0F;
    }

    public void setBoomballHeading(double d, double d1, double d2, float f, 
            float f1)
    {
        float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        motionX = d;
        motionY = d1;
        motionZ = d2;
        float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
        ticksInGroundSnowball = 0;
    }

    public void setVelocity(double d, double d1, double d2)
    {
        motionX = d;
        motionY = d1;
        motionZ = d2;
        if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(d * d + d2 * d2);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f) * 180D) / 3.1415927410125732D);
        }
    }

    public void onUpdate()
    {
        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        super.onUpdate();
        if(shakeSnowball > 0)
        {
            shakeSnowball--;
        }
        if(inGroundSnowball)
        {
            int i = worldObj.getBlockId(xTileSnowball, yTileSnowball, zTileSnowball);
            if(i != inTileSnowball)
            {
                inGroundSnowball = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
                ticksInGroundSnowball = 0;
                ticksInAirSnowball = 0;
            } else
            {
                ticksInGroundSnowball++;
                if(ticksInGroundSnowball > 20)
                {
					hurtArea(6F);
                    setDead();
                }
                return;
            }
        } else
        {
            ticksInAirSnowball++;
        }
		
		
                if(ticksInAirSnowball > 10)
                {
					hurtArea(6F);
                    setDead();
                }
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        Vec3D vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
        vec3d = Vec3D.createVector(posX, posY, posZ);
        vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
		
		if(Math.abs(motionX) < 0.1F & Math.abs(motionZ) < 0.1F)
		{
			hurtArea(6F);
			setDead();
			return;
		}
		else
		{
			worldObj.spawnParticle("bubble", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			worldObj.spawnParticle("bubble", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			worldObj.spawnParticle("bubble", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
		}
		
        if(movingobjectposition != null)
        {
            vec3d1 = Vec3D.createVector(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
        if(!worldObj.isRemote)
        {
            Entity entity = null;
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
            double d = 0.0D;
            for(int l = 0; l < list.size(); l++)
            {
                Entity entity1 = (Entity)list.get(l);
                if(!entity1.canBeCollidedWith() || entity1 == thrower && ticksInAirSnowball < 5)
                {
                    continue;
                }
                float f4 = 0.3F;
                AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f4, f4, f4);
                MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
                if(movingobjectposition1 == null)
                {
                    continue;
                }
                double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
                if(d1 < d || d == 0.0D)
                {
                    entity = entity1;
                    d = d1;
                }
            }

            if(entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }
        if(movingobjectposition != null)
        {
            if(movingobjectposition.entityHit != null)
            {
				movingobjectposition.entityHit.motionX += motionX * 3D;
				movingobjectposition.entityHit.motionY += motionY * 3D;
				movingobjectposition.entityHit.motionZ += motionZ * 3D;
				
				boolean don = false;
				if(movingobjectposition.entityHit instanceof EntityPlayer) { don = true; }
				if(movingobjectposition.entityHit instanceof EntityFriendlySpider) { don = true; }
				if(movingobjectposition.entityHit instanceof EntityESpiderQueen) { EntityESpiderQueen ee = (EntityESpiderQueen) movingobjectposition.entityHit; if(ee.getFriendly()) { don = true; } }
				
				if(!don)
				{
					if(!movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(thrower), 3));
				}
            }
            for(int j = 0; j < 8; j++)
            {
                //worldObj.spawnParticle("splash", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
			hurtArea(6F);
            setDead();
        }
		motionY = 0;
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        for(rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f1 = 0.99F;
        float f2 = 0.03F;
        if(isInWater())
        {
            for(int k = 0; k < 4; k++)
            {
                float f3 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f3, posY - motionY * (double)f3, posZ - motionZ * (double)f3, motionX, motionY, motionZ);
            }

            f1 = 0.8F;
        }
        motionX *= f1;
        motionY *= f1;
        motionZ *= f1;
        motionY -= f2;
        setPosition(posX, posY, posZ);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTileSnowball);
        nbttagcompound.setShort("yTile", (short)yTileSnowball);
        nbttagcompound.setShort("zTile", (short)zTileSnowball);
        nbttagcompound.setByte("inTile", (byte)inTileSnowball);
        nbttagcompound.setByte("shake", (byte)shakeSnowball);
        nbttagcompound.setByte("inGround", (byte)(inGroundSnowball ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTileSnowball = nbttagcompound.getShort("xTile");
        yTileSnowball = nbttagcompound.getShort("yTile");
        zTileSnowball = nbttagcompound.getShort("zTile");
        inTileSnowball = nbttagcompound.getByte("inTile") & 0xff;
        shakeSnowball = nbttagcompound.getByte("shake") & 0xff;
        inGroundSnowball = nbttagcompound.getByte("inGround") == 1;
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
        if(inGroundSnowball && thrower == entityplayer && shakeSnowball <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Item.arrow, 1)))
        {
            //worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            //entityplayer.onItemPickup(this, 1);
			hurtArea(6F);
            setDead();
        }
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    private int xTileSnowball;
    private int yTileSnowball;
    private int zTileSnowball;
    private int inTileSnowball;
    private boolean inGroundSnowball;
    public int shakeSnowball;
    private EntityLiving thrower;
    private int ticksInGroundSnowball;
    private int ticksInAirSnowball;
}
