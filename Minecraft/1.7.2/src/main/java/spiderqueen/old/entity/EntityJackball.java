// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import java.util.List;
import java.util.Random;

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

public class EntityJackball extends Entity
{

    public EntityJackball(World world)
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
		friendly = false;
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

    public EntityJackball(World world, EntityLiving entityliving)
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
        setJackballHeading(motionX, motionY, motionZ, 1.5F, 1.0F);
    }

    public EntityJackball(World world, double d, double d1, double d2)
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

    public void setJackballHeading(double d, double d1, double d2, float f, 
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

	
    public void setJackballHeading(double d, double d1, double d2, float f, float f1, EntityLiving mtarget)
    {
		setJackballHeading(d,d1,d2,f,f1);
		target = mtarget;
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

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		if(entity != null)
		{
        double dz = entity.posX - posX;
		double d1z = entity.posZ - posZ;
		double d2z = entity.posY - posY - 2D;
		float f1z = MathHelper.sqrt_double(dz * dz + d1z * d1z) * 0.2F;
		setJackballHeading(dz, d2z + (double)f1z + Math.abs(motionX / 2D) + Math.abs(motionZ / 2D), d1z, 2F, 2F);
		motionX *= -5D;
		motionZ *= -5D;
		tTime = 30;
		}
        return true;
    }
	
    public void onUpdate()
    {
        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        super.onUpdate();
		
			if(tTime < 1)
            {
				if(target != null)
				{
					double dz = target.posX - posX;
					double d1z = target.posZ - posZ;
					double d2z = target.posY - posY - 2D;
					float f1z = MathHelper.sqrt_double(dz * dz + d1z * d1z) * 0.4F;
					setJackballHeading(dz, d2z - 2D + (double)f1z + Math.abs(motionX / 2D) + Math.abs(motionZ / 2D), d1z, 0.44F, 28F);
					tTime = 12;
					Random rm = new Random();
					if(!target.isEntityAlive() || rm.nextInt(500) == 0) { target = null; }
				}
			}
			else
			{
				tTime--;
			}
			
			motionY = motionY * 0.8F + 0.03F;
			
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
                if(ticksInGroundSnowball == 1200)
                {
                    setDead();
                }
                return;
            }
        } else
        {
            ticksInAirSnowball++;
        }
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        Vec3D vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
        vec3d = Vec3D.createVector(posX, posY, posZ);
        vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
		
		if(Math.abs(motionX) < 0.01F & Math.abs(motionZ) < 0.01F)
		{
			setDead();
			return;
		}
		else
		{
			worldObj.spawnParticle("snowballpoof", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			worldObj.spawnParticle("snowballpoof", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			worldObj.spawnParticle("snowballpoof", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
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
			boolean doeet = true;
            if(movingobjectposition.entityHit != null)
            {
				if(friendly)
				{
					if(movingobjectposition.entityHit instanceof EntityLiving)
					{
						EntityLiving ell = (EntityLiving)movingobjectposition.entityHit;
						if(ell.spiderfriend) { doeet = false; }
						if(movingobjectposition.entityHit instanceof EntityPlayer) { doeet = false; }
					}
				}
				else
				{
					if(movingobjectposition.entityHit instanceof EntityJack)
					{
						doeet = false;
					}
				}
				
				if(doeet)
				{
					movingobjectposition.entityHit.motionX += motionX * 1D;
					movingobjectposition.entityHit.motionY += motionY * 1D;
					movingobjectposition.entityHit.motionZ += motionZ * 1D;
					
					if(!movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(thrower), 6));
				}
            }
			
			
			if(doeet)
			{
				for(int j = 0; j < 4; j++)
				{
					worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
				}

				setDead();
			}
        }
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
	private EntityLiving target;
	private int tTime;
	protected boolean friendly;
}
