// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Entity, Vec3D, AxisAlignedBB, World, 
//            Item, EntityPlayer, EntityItem, MathHelper, 
//            ItemStack, StatList, Material, NBTTagCompound, 
//            MovingObjectPosition

public class EntitySWeb extends Entity
{

    public EntitySWeb(World world)
    {
        super(world);
        tileX = -1;
        tileY = -1;
        tileZ = -1;
        field_4092_g = 0;
        field_4091_h = false;
        field_4098_a = 0;
        field_4089_j = 0;
        field_4088_k = 0;
        field_4096_c = null;
        setSize(0.15F, 0.15F);
        //ignoreFrustumCheck = true;
		ddist = 0;
    }

    public EntitySWeb(World world, double d, double d1, double d2)
    {
        this(world);
        setPosition(d, d1, d2);
        //ignoreFrustumCheck = true;
    }

    public EntitySWeb(World world, EntityPlayer entityplayer)
    {
        super(world);
        tileX = -1;
        tileY = -1;
        tileZ = -1;
        field_4092_g = 0;
        field_4091_h = false;
        field_4098_a = 0;
        field_4089_j = 0;
        field_4088_k = 0;
        field_4096_c = null;
        //ignoreFrustumCheck = true;
        angler = entityplayer;
		holder = (EntityLiving) angler;
        angler.webEntity = this;
        setSize(0.25F, 0.25F);
        setLocationAndAngles(entityplayer.posX, (entityplayer.posY + 1.6200000000000001D) - (double)entityplayer.yOffset, entityplayer.posZ, entityplayer.rotationYaw, entityplayer.rotationPitch);
        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        float f = 8F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
        motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F) * f;
        func_4042_a(motionX, motionY, motionZ, 1.5F, 1.0F);
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

    public void func_4042_a(double d, double d1, double d2, float f, 
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
        field_4090_i = 0;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f, 
            float f1, int i)
    {
        field_6387_m = d;
        field_6386_n = d1;
        field_6385_o = d2;
        field_6384_p = f;
        field_6383_q = f1;
        field_6388_l = i;
        motionX = velocityX;
        motionY = velocityY;
        motionZ = velocityZ;
    }

    public void setVelocity(double d, double d1, double d2)
    {
        velocityX = motionX = d;
        velocityY = motionY = d1;
        velocityZ = motionZ = d2;
    }

	public void matchMotion(int typ, double amt)
	{
		double amt2 = amt;
		if(typ == 0) 
		{
			amt2 = amt - holder.motionX;
			holder.motionX = amt;
			if(field_4096_c != null) { field_4096_c.motionX = field_4096_c.motionX - amt2; }
		}
		if(typ == 1) 
		{
			amt2 = amt - holder.motionY;
			holder.motionY = amt;
			if(field_4096_c != null) { field_4096_c.motionY = field_4096_c.motionY - amt2; }
		}
		if(typ == 2) 
		{
			amt2 = amt - holder.motionZ;
			holder.motionZ = amt;
			if(field_4096_c != null) { field_4096_c.motionZ = field_4096_c.motionZ - amt2; }
		}		
	}
	
    public void onUpdate()
    {
        super.onUpdate();
		
		if(holder != null & (field_4091_h || field_4096_c != null))
		{
			if(angler != null) 
			{ 
				angler.fallDistance = 0.0F;
				if(angler.isSneaking()) { ddist++; }
			}
        Vec3D ww = Vec3D.createVector(posX,posY,posZ);
        Vec3D aa = Vec3D.createVector(holder.posX,holder.posY,holder.posZ);
		
		
		if(ddist != 0)
		{
			if(ddist < 2) { ddist = 2; }
			if(ddist > 1000) { ddist = 1000; }
		}
		
		if(ddist == 0) { ddist = ww.squareDistanceTo(aa); }
			if(ww.squareDistanceTo(aa) > ddist / 4 * 3)
			{
				Vec3D nn = Vec3D.createVector(holder.motionX * 1.2D + (posX - holder.posX) / 2, holder.motionY * 1.2D + (posY - holder.posY) / 2, holder.motionZ * 1.2D + (posZ - holder.posZ) / 2).normalize();
				double dd2 = 0.3D;// + (ww.squareDistanceTo(aa) - ddist) / (ddist);
				
				if(field_4096_c != null) { dd2 = dd2 / 2; }
				
				double tmotionX = holder.motionX * 1D + nn.xCoord * dd2 / 2D;
				double tmotionY = holder.motionY * 1D + nn.yCoord * dd2 / 2D;
				double tmotionZ = holder.motionZ * 1D + nn.zCoord * dd2 / 2D;
				
				
				if(holder.posX > posX) { if(tmotionX < holder.motionX) { matchMotion(0, tmotionX); } } else { if(tmotionX > holder.motionX) { matchMotion(0, tmotionX); } }
				if(holder.posY > posY) { if(tmotionY < holder.motionY) { matchMotion(1, tmotionY); } } else { if(tmotionY > holder.motionY) { matchMotion(1, tmotionY); } }
				if(holder.posZ > posZ) { if(tmotionZ < holder.motionZ) { matchMotion(2, tmotionZ); } } else { if(tmotionZ > holder.motionZ) { matchMotion(2, tmotionZ); } }
				
			}
			else
			{
				if(angler != null)
				{
					if(angler.iJumping()) { ddist = ddist - 6; }
				}
			}
		}
		
        if(field_6388_l > 0)
        {
            double d = posX + (field_6387_m - posX) / (double)field_6388_l;
            double d1 = posY + (field_6386_n - posY) / (double)field_6388_l;
            double d2 = posZ + (field_6385_o - posZ) / (double)field_6388_l;
            double d4;
            for(d4 = field_6384_p - (double)rotationYaw; d4 < -180D; d4 += 360D) { }
            for(; d4 >= 180D; d4 -= 360D) { }
            rotationYaw += d4 / (double)field_6388_l;
            rotationPitch += (field_6383_q - (double)rotationPitch) / (double)field_6388_l;
            field_6388_l--;
            setPosition(d, d1, d2);
            setRotation(rotationYaw, rotationPitch);
            return;
        }
        if(!worldObj.isRemote)
        {
            if(holder.isDead || !holder.isEntityAlive() || getDistanceSqToEntity(holder) > 1024D)
            {
                setDead();
                if(angler != null) { angler.webEntity = null; }
                return;
            }
            if(field_4096_c != null)
            {
                if(field_4096_c.isDead)
                {
                    field_4096_c = null;
                } else
                {
                    posX = field_4096_c.posX;
                    posY = field_4096_c.boundingBox.minY + (double)field_4096_c.height * 0.80000000000000004D;
                    posZ = field_4096_c.posZ;
                    return;
                }
            }
        }
        if(field_4098_a > 0)
        {
            field_4098_a--;
        }
        if(field_4091_h)
        {
           
                field_4090_i++;
                if(field_4090_i == 1200)
                {
                    setDead();
                }
                return;
           
        } else
        {
            field_4089_j++;
        }
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        Vec3D vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
        vec3d = Vec3D.createVector(posX, posY, posZ);
        vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        if(movingobjectposition != null)
        {
            vec3d1 = Vec3D.createVector(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
        Entity entity = null;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double d3 = 0.0D;
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);
            if(!entity1.canBeCollidedWith() || entity1 == holder && field_4089_j < 5)
            {
                continue;
            }
            float f2 = 0.3F;
            AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f2, f2, f2);
            MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
            if(movingobjectposition1 == null)
            {
                continue;
            }
            double d6 = vec3d.distanceTo(movingobjectposition1.hitVec);
            if(d6 < d3 || d3 == 0.0D)
            {
                entity = entity1;
                d3 = d6;
            }
        }

        if(entity != null)
        {
            movingobjectposition = new MovingObjectPosition(entity);
        }
        if(movingobjectposition != null)
        {
            if(movingobjectposition.entityHit != null)
            {
                if(movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(holder), 0))
                {
                    field_4096_c = movingobjectposition.entityHit;
                }
            } else
            {
				motionX = motionX * 0.7; motionY = motionY * 0.7; motionZ = motionZ * 0.7;
                field_4091_h = true;
				posX = posX + motionX;
				posY = posY + motionY;
				posZ = posZ + motionZ;
				
            }
        }
        if(field_4091_h)
        {
            return;
        }
        moveEntity(motionX, motionY, motionZ);
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        for(rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f1 = 0.92F;
        if(isCollidedVertically || isCollidedHorizontally)
        {
            f1 = 0.5F;
			field_4091_h = true;
        }
        int k = 5;
        double d5 = 0.0D;
        for(int l = 0; l < k; l++)
        {
            double d8 = ((boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(l + 0)) / (double)k) - 0.125D) + 0.125D;
            double d9 = ((boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(l + 1)) / (double)k) - 0.125D) + 0.125D;
            AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBoxFromPool(boundingBox.minX, d8, boundingBox.minZ, boundingBox.maxX, d9, boundingBox.maxZ);
            if(worldObj.isAABBInMaterial(axisalignedbb1, Material.water))
            {
                d5 += 1.0D / (double)k;
            }
        }

        if(d5 > 0.0D)
        {
            if(field_4088_k > 0)
            {
                field_4088_k--;
            } else
            {
                char c = '\u01F4';
                if(worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + 1, MathHelper.floor_double(posZ)))
                {
                    c = '\u012C';
                }
                if(rand.nextInt(c) == 0)
                {
                    field_4088_k = rand.nextInt(30) + 10;
                    motionY -= 0.20000000298023224D;
                    worldObj.playSoundAtEntity(this, "random.splash", 0.25F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.4F);
                    float f3 = MathHelper.floor_double(boundingBox.minY);
                    for(int i1 = 0; (float)i1 < 1.0F + width * 20F; i1++)
                    {
                        float f4 = (rand.nextFloat() * 2.0F - 1.0F) * width;
                        float f6 = (rand.nextFloat() * 2.0F - 1.0F) * width;
                        worldObj.spawnParticle("bubble", posX + (double)f4, f3 + 1.0F, posZ + (double)f6, motionX, motionY - (double)(rand.nextFloat() * 0.2F), motionZ);
                    }

                    for(int j1 = 0; (float)j1 < 1.0F + width * 20F; j1++)
                    {
                        float f5 = (rand.nextFloat() * 2.0F - 1.0F) * width;
                        float f7 = (rand.nextFloat() * 2.0F - 1.0F) * width;
                        worldObj.spawnParticle("splash", posX + (double)f5, f3 + 1.0F, posZ + (double)f7, motionX, motionY, motionZ);
                    }

                }
            }
        }
        if(field_4088_k > 0)
        {
            motionY -= (double)(rand.nextFloat() * rand.nextFloat() * rand.nextFloat()) * 0.20000000000000001D;
        }
        double d7 = d5 * 2D - 1.0D;
        motionY += 0.029999999105930328D * d7;
        if(d5 > 0.0D)
        {
            f1 = (float)((double)f1 * 0.90000000000000002D);
            motionY *= 0.80000000000000004D;
        }
        /*motionX *= f1;
        motionY *= f1;
        motionZ *= f1;*/
        setPosition(posX, posY, posZ);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)tileX);
        nbttagcompound.setShort("yTile", (short)tileY);
        nbttagcompound.setShort("zTile", (short)tileZ);
        nbttagcompound.setByte("inTile", (byte)field_4092_g);
        nbttagcompound.setByte("shake", (byte)field_4098_a);
        nbttagcompound.setByte("inGround", (byte)(field_4091_h ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        tileX = nbttagcompound.getShort("xTile");
        tileY = nbttagcompound.getShort("yTile");
        tileZ = nbttagcompound.getShort("zTile");
        field_4092_g = nbttagcompound.getByte("inTile") & 0xff;
        field_4098_a = nbttagcompound.getByte("shake") & 0xff;
        field_4091_h = nbttagcompound.getByte("inGround") == 1;
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    public int catchFish()
    {
        return 0;
    }

    private int tileX;
    private int tileY;
    private int tileZ;
    private int field_4092_g;
    private boolean field_4091_h;
    public int field_4098_a;
    public EntityPlayer angler;
    public EntityLiving holder;
    private int field_4090_i;
    private int field_4089_j;
    private int field_4088_k;
    public Entity field_4096_c;
    private int field_6388_l;
    private double field_6387_m;
    private double field_6386_n;
    private double field_6385_o;
    private double field_6384_p;
    private double field_6383_q;
    private double velocityX;
    private double velocityY;
    private double velocityZ;
	private double ddist;
}
