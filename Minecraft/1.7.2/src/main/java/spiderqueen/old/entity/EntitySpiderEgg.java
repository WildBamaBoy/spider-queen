package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySpiderEgg extends Entity
{

    public EntitySpiderEgg(World world)
    {
        super(world);
        boatCurrentDamage = 0;
        boatTimeSinceHit = 0;
        boatRockDirection = 1;
        preventEntitySpawning = true;
        setSize(0.5F, 0.5F);
        yOffset = height / 2.0F;
		eggHatch = 0;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
    }

    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

    public AxisAlignedBB getBoundingBox()
    {
        return boundingBox;
    }

    public boolean canBePushed()
    {
        return true;
    }

    public EntitySpiderEgg(World world, double d, double d1, double d2)
    {
        this(world);
        setPosition(d, d1 + (double)yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }

    public double getMountedYOffset()
    {
        return (double)height * 0.0D - 0.30000001192092896D;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
        if(worldObj.isRemote || isDead)
        {
            return true;
        }
        boatRockDirection = -boatRockDirection;
        boatTimeSinceHit = 10;
        boatCurrentDamage += i * 10;
        setBeenAttacked();
        if(boatCurrentDamage > 40)
        {
            setDead();
        }
        return true;
    }

    public void performHurtAnimation()
    {
        boatRockDirection = -boatRockDirection;
        boatTimeSinceHit = 10;
        boatCurrentDamage += boatCurrentDamage * 10;
    }

    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f, 
            float f1, int i)
    {
        field_9393_e = d;
        field_9392_f = d1;
        field_9391_g = d2;
        field_9390_h = f;
        field_9389_i = f1;
        field_9394_d = i + 4;
        motionX = field_9388_j;
        motionY = field_9387_k;
        motionZ = field_9386_l;
    }

    public void setVelocity(double d, double d1, double d2)
    {
        field_9388_j = motionX = d;
        field_9387_k = motionY = d1;
        field_9386_l = motionZ = d2;
    }

	public boolean getBait(World world, double i, double j, double k, int what, boolean doMove)
	{
		double d4 = 16D;
        TileEntity entityplayer = null;
		int pX = 0;
		int pY = 0;
		int pZ = 0;
		
        for(int ss = 0; ss < world.loadedTileEntityList.size(); ss++)
        {
            TileEntity entityplayer1 = (TileEntity)world.loadedTileEntityList.get(ss);
			if(entityplayer1 instanceof TileEntityHBait)
			{
				double d5 = mod_SpiderQueen.getDistance3d(entityplayer1.xCoord, entityplayer1.yCoord, entityplayer1.zCoord, i, j, k);
				if(d5 < 16 * 16 && (d4 == -1D || d5 < d4))
				{
					int bID = worldObj.getBlockId(entityplayer1.xCoord, entityplayer1.yCoord, entityplayer1.zCoord);
					if(what == bID)
					{
						pX = (int)entityplayer1.xCoord;
						pY = (int)entityplayer1.yCoord;
						pZ = (int)entityplayer1.zCoord;
						
						d4 = d5;
						entityplayer = entityplayer1;
					}
				}
			}
        }
		
		if(entityplayer != null) 
		{ 
			if(doMove)
			{
				worldObj.setBlockWithNotify(pX,pY,pZ,0);
			}
			return true; 
		}
		return false;
	}
	public boolean getClosestCocoon(World world, double d, double d1, double d2, double d3)
    {
        double d4 = -1D;
        Entity entityplayer = null;
        for(int i = 0; i < world.loadedEntityList.size(); i++)
        {
            Entity entityplayer1 = (Entity)world.loadedEntityList.get(i);
			if(entityplayer1 instanceof EntityCocoon)
			{
				EntityCocoon coc = (EntityCocoon)entityplayer1;
				if(!coc.getEaten())
				{
					double d5 = entityplayer1.getDistanceSq(d, d1, d2);
					if((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1D || d5 < d4))
					{
						d4 = d5;
						entityplayer = entityplayer1;
					}
				}
			}
        }
		
		if(entityplayer instanceof EntityCocoonSkeleton)
		{
			EntityCocoon coc = (EntityCocoon)entityplayer;
			coc.setEaten(true);
			EntityThinSpider nent = new EntityThinSpider(worldObj);
			nent.setPosition(posX,posY + 1,posZ);
			worldObj.spawnEntityInWorld(nent);
			return true;
		}
		/*if(entityplayer instanceof EntityCocoonHuman)
		{
			EntityCocoon coc = (EntityCocoon)entityplayer;
			coc.setEaten(true);
			//EntityChestSpider nent = new EntityChestSpider(worldObj);
			nent.setPosition(posX,posY + 1,posZ);
			worldObj.spawnEntityInWorld(nent);
			return true;
		}*/
		if(entityplayer instanceof EntityCocoonWolf)
		{
			EntityCocoon coc = (EntityCocoon)entityplayer;
			coc.setEaten(true);
			EntityFurrySpider nent = new EntityFurrySpider(worldObj);
			nent.setPosition(posX,posY + 1,posZ);
			worldObj.spawnEntityInWorld(nent);
			return true;
		}
		if(entityplayer instanceof EntityCocoonZombie)
		{
			EntityCocoon coc = (EntityCocoon)entityplayer;
			coc.setEaten(true);
			EntityPoisonSpider nent = new EntityPoisonSpider(worldObj);
			nent.setPosition(posX,posY + 1,posZ);
			worldObj.spawnEntityInWorld(nent);
			return true;
		}
		if(entityplayer instanceof EntityCocoonCreeper)
		{
			EntityCocoon coc = (EntityCocoon)entityplayer;
			coc.setEaten(true);
			EntityBoomSpider nent = new EntityBoomSpider(worldObj);
			nent.setPosition(posX,posY + 1,posZ);
			worldObj.spawnEntityInWorld(nent);
			return true;
		}
		if(entityplayer instanceof EntityCocoon)
		{
			
			EntityCocoon coc = (EntityCocoon)entityplayer;
			coc.setEaten(true);
			if(getBait(world,posX,posY,posZ,mod_SpiderQueen.royalblood.blockID,false))
			{
				getBait(world,posX,posY,posZ,mod_SpiderQueen.royalblood.blockID,true);
				EntityESpiderQueen nent = new EntityESpiderQueen(worldObj);
				nent.setPosition(posX,posY + 1,posZ);
				nent.setFriendly(true);
				worldObj.spawnEntityInWorld(nent);
			}
			else
			{
				EntityFriendlySpider nent = new EntityFriendlySpider(worldObj);
				nent.setPosition(posX,posY + 1,posZ);
				worldObj.spawnEntityInWorld(nent);
			}
			return true;
		}
		
        return false;
    }
	
    public void onUpdate()
    {
        super.onUpdate();
		eggHatch++;
		if(eggHatch > 1000)
		{
            setDead();
			eggHatch = 0;
			if(!getClosestCocoon(worldObj,posX,posY,posZ,8D))
			{
				EntityTinySpider nent = new EntityTinySpider(worldObj);
				nent.setPosition(posX,posY,posZ);
				worldObj.spawnEntityInWorld(nent);
            }
			return;
		}
        if(boatTimeSinceHit > 0)
        {
            boatTimeSinceHit--;
        }
        if(boatCurrentDamage > 0)
        {
            boatCurrentDamage--;
        }
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        int i = 5;
        double d = 0.0D;
        for(int j = 0; j < i; j++)
        {
            double d4 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 0)) / (double)i) - 0.125D;
            double d8 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 1)) / (double)i) - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBoxFromPool(boundingBox.minX, d4, boundingBox.minZ, boundingBox.maxX, d8, boundingBox.maxZ);
            if(worldObj.isAABBInMaterial(axisalignedbb, Material.water))
            {
                d += 1.0D / (double)i;
            }
        }

        if(worldObj.isRemote)
        {
            if(field_9394_d > 0)
            {
                double d1 = posX + (field_9393_e - posX) / (double)field_9394_d;
                double d5 = posY + (field_9392_f - posY) / (double)field_9394_d;
                double d9 = posZ + (field_9391_g - posZ) / (double)field_9394_d;
                double d12;
                for(d12 = field_9390_h - (double)rotationYaw; d12 < -180D; d12 += 360D) { }
                for(; d12 >= 180D; d12 -= 360D) { }
                rotationYaw += d12 / (double)field_9394_d;
                rotationPitch += (field_9389_i - (double)rotationPitch) / (double)field_9394_d;
                field_9394_d--;
                setPosition(d1, d5, d9);
                setRotation(rotationYaw, rotationPitch);
            } else
            {
                double d2 = posX + motionX;
                double d6 = posY + motionY;
                double d10 = posZ + motionZ;
                setPosition(d2, d6, d10);
                if(onGround)
                {
                    motionX *= 0.5D;
                    motionY *= 0.5D;
                    motionZ *= 0.5D;
                }
                motionX *= 0.99000000953674316D;
                motionY *= 0.94999998807907104D;
                motionZ *= 0.99000000953674316D;
            }
            return;
        }
        double d3 = d * 2D - 1.0D;
        motionY += 0.039999999105930328D * d3;
        if(riddenByEntity != null)
        {
            motionX += riddenByEntity.motionX * 0.20000000000000001D;
            motionZ += riddenByEntity.motionZ * 0.20000000000000001D;
        }
        double d7 = 0.40000000000000002D;
        if(motionX < -d7)
        {
            motionX = -d7;
        }
        if(motionX > d7)
        {
            motionX = d7;
        }
        if(motionZ < -d7)
        {
            motionZ = -d7;
        }
        if(motionZ > d7)
        {
            motionZ = d7;
        }
        if(onGround)
        {
            motionX *= 0.5D;
            motionY *= 0.5D;
            motionZ *= 0.5D;
        }
        moveEntity(motionX, motionY, motionZ);
        double d11 = Math.sqrt(motionX * motionX + motionZ * motionZ);
        if(d11 > 0.14999999999999999D)
        {
            double d13 = Math.cos(((double)rotationYaw * 3.1415926535897931D) / 180D);
            double d15 = Math.sin(((double)rotationYaw * 3.1415926535897931D) / 180D);
            for(int i1 = 0; (double)i1 < 1.0D + d11 * 60D; i1++)
            {
                double d18 = rand.nextFloat() * 2.0F - 1.0F;
                double d20 = (double)(rand.nextInt(2) * 2 - 1) * 0.69999999999999996D;
                if(rand.nextBoolean())
                {
                    double d21 = (posX - d13 * d18 * 0.80000000000000004D) + d15 * d20;
                    double d23 = posZ - d15 * d18 * 0.80000000000000004D - d13 * d20;
                    //worldObj.spawnParticle("splash", d21, posY - 0.125D, d23, motionX, motionY, motionZ);
                } else
                {
                    double d22 = posX + d13 + d15 * d18 * 0.69999999999999996D;
                    double d24 = (posZ + d15) - d13 * d18 * 0.69999999999999996D;
                    //worldObj.spawnParticle("splash", d22, posY - 0.125D, d24, motionX, motionY, motionZ);
                }
            }

        }
        if(isCollidedHorizontally && d11 > 0.14999999999999999D)
        {
            if(!worldObj.isRemote)
            {
                setDead();
            }
        } else
        {
            motionX *= 0.99000000953674316D;
            motionY *= 0.94999998807907104D;
            motionZ *= 0.99000000953674316D;
        }
        rotationPitch = 0.0F;
        double d14 = rotationYaw;
        double d16 = prevPosX - posX;
        double d17 = prevPosZ - posZ;
        if(d16 * d16 + d17 * d17 > 0.001D)
        {
            d14 = (float)((Math.atan2(d17, d16) * 180D) / 3.1415926535897931D);
        }
        double d19;
        for(d19 = d14 - (double)rotationYaw; d19 >= 180D; d19 -= 360D) { }
        for(; d19 < -180D; d19 += 360D) { }
        if(d19 > 20D)
        {
            d19 = 20D;
        }
        if(d19 < -20D)
        {
            d19 = -20D;
        }
        rotationYaw += d19;
        setRotation(rotationYaw, rotationPitch);
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if(list != null && list.size() > 0)
        {
            for(int j1 = 0; j1 < list.size(); j1++)
            {
                Entity entity = (Entity)list.get(j1);
                if(entity != riddenByEntity && entity.canBePushed() && (entity instanceof EntityBoat))
                {
                    entity.applyEntityCollision(this);
                }
            }

        }
        if(riddenByEntity != null && riddenByEntity.isDead)
        {
            riddenByEntity = null;
        }
    }

    public void updateRiderPosition()
    {
        /*if(riddenByEntity == null)
        {
            return;
        } else
        {
            double d = Math.cos(((double)rotationYaw * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
            double d1 = Math.sin(((double)rotationYaw * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
            riddenByEntity.setPosition(posX + d, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ + d1);
            return;
        }*/
		
    }

    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }

    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        /*if(riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer)
        {
            return true;
        }
        if(!worldObj.isRemote)
        {
            entityplayer.mountEntity(this);
        }*/
        return false;
    }

    public int boatCurrentDamage;
    public int boatTimeSinceHit;
    public int boatRockDirection;
    public int eggHatch;
    private int field_9394_d;
    private double field_9393_e;
    private double field_9392_f;
    private double field_9391_g;
    private double field_9390_h;
    private double field_9389_i;
    private double field_9388_j;
    private double field_9387_k;
    private double field_9386_l;
}
