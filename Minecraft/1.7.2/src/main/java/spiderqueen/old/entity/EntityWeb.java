package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWeb extends Entity
{
	
    public EntityWeb(World world)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        arrowShake = 0;
        field_680_i = 0;
        setSize(0.15F, 0.15F);
        destr = 0;
		damage = 0;
		inactive = false;
		nomake = false;
    }

    public EntityWeb(World world, double d, double d1, double d2)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        arrowShake = 0;
        field_680_i = 0;
        setSize(0.15F, 0.15F);
        setPosition(d, d1, d2);
        yOffset = 0.0F;
		inactive = false;
 		nomake = false;
   }
	
	public EntityWeb(World world, EntityLiving entityliving, int dmg)
    {
		this(world,entityliving);
		damage = dmg;
		inactive = false;
		nomake = false;
	}
	
    public EntityWeb(World world, EntityLiving entityliving)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        arrowShake = 0;
        field_680_i = 0;
        field_682_g = entityliving;
        setSize(0.15F, 0.15F);
        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
        setArrowHeading(motionX, motionY, motionZ, 1.5F, 1.0F);
		inactive = false;
 		nomake = false;
   }

   public EntityWeb(World world, EntityLiving entityliving, boolean poisonous)
   {
		this(world,entityliving);
		poison = poisonous;
   }
   
    protected void entityInit()
    {
    }

    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = boundingBox.getAverageEdgeLength() * 4D;
        d1 *= 128D;
        return d < d1 * d1;
    }

    public void setArrowHeading(double d, double d1, double d2, float f, 
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
        field_681_h = 0;
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

	public void setEntityDead2()
	{
		inactive = true;
		Random rr = new Random();
		if(rr.nextInt(2) == 0) { motionX = motionX * -0.2F; }
		if(rr.nextInt(2) == 0) { motionY = motionY * -0.2F; }
		if(rr.nextInt(2) == 0) { motionZ = motionZ * -0.2F; }
	}
	
    public void onUpdate()
    {
        super.onUpdate();
        
        
        if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D);
        }
        if(arrowShake > 0)
        {
            arrowShake--;
        }
        if(inGround)
        {
            int i = worldObj.getBlockId(xTile, yTile, zTile);
            if(i != inTile || i == 0)
            {
                inGround = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
                field_681_h = 0;
                field_680_i = 0;
            } else
            {
                field_681_h++;
                if(field_681_h == 1200)
                {
                	setDead();
                }
                return;
            }
        } else
        {
            field_680_i++;
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
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.8D, 1.8D, 1.8D));
        double d = 0.0D;
        
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);
            if(!entity1.canBeCollidedWith() || entity1 == field_682_g && field_680_i < 5)
            {
                continue;
            }
            float f4 = 0.05F;
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
        if(movingobjectposition != null)
        {
            if(movingobjectposition.entityHit != null & !inactive)
            {
				if(movingobjectposition.entityHit instanceof EntityLiving)
				{
					EntityLiving enl = (EntityLiving) movingobjectposition.entityHit;
					if(!enl.spiderfriend)
					{
						int atk = 0;
						if(poison) { atk = 4; }
						enl.attackEntityFrom(DamageSource.causeMobDamage(field_682_g),atk);
						
						int hh = enl.health;
						if(hh > 0)
						{
								if(hh < 1) { hh = 1; }
							
								Random rr = new Random();
								
								if(damage > 0)
								{
									if(movingobjectposition.entityHit instanceof EntityLiving)
									{
										setEntityDead2();
										movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(field_682_g),damage);
									}
								}
								
								if(movingobjectposition.entityHit instanceof EntityFly)
								{
									EntityFly ef = (EntityFly) movingobjectposition.entityHit;
									ef.setCocooned(true);
									setDead();
								}
								
								if(movingobjectposition.entityHit instanceof EntityHuman)
								{
									worldObj.spawnEntityInWorld(new EntityCocoonHuman(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								
								if(movingobjectposition.entityHit instanceof EntityChicken)
								{
									worldObj.spawnEntityInWorld(new EntityCocoon(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityPig)
								{
									worldObj.spawnEntityInWorld(new EntityCocoonPig(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntitySheep)
								{
									if(rr.nextInt(hh / 6 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonSheep(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityCreeper)
								{
									if(rr.nextInt(hh / 4 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonCreeper(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityZombie)
								{
									if(rr.nextInt(hh / 4 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonZombie(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityCow)
								{
									if(rr.nextInt(hh / 6 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonCow(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntitySkeleton)
								{
									if(rr.nextInt(hh / 4 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonSkeleton(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityWolf)
								{
									if(rr.nextInt(hh / 4 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonWolf(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityAnt || movingobjectposition.entityHit instanceof EntityRedAnt)
								{
									if(rr.nextInt(hh / 4 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonAnt(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityWasp)
								{
									if(rr.nextInt(hh / 4 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonWasp(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityGatherer)
								{
									if(rr.nextInt(hh / 4 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonGatherer(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityWarrior)
								{
									if(rr.nextInt(hh / 4 + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonWarrior(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								if(movingobjectposition.entityHit instanceof EntityQueenBee)
								{
									if(rr.nextInt(hh + 1) != 0) { setEntityDead2(); return; }
									worldObj.spawnEntityInWorld(new EntityCocoonQueenBee(worldObj, movingobjectposition.entityHit.posX, 
									movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ,
									movingobjectposition.entityHit.rotationYaw,0));
									
									movingobjectposition.entityHit.setDead();
									
									setDead();
								}
								
								
						}
					}
				}
            } else
            {
			
				float fm = -51F;
				int bID = 0;
				//while(fm < 50F)
				//{
					//if(bID != mod_SpiderQueen.smallWeb.blockID)
					//{
						bID = worldObj.getBlockId((int)(posX + motionX / 25F * fm),(int)(posY + motionY / 25F * fm),(int)(posZ + motionZ / 25F * fm));
						if(bID == Block.web.blockID) { fm = 55F; }
							
					//}
					//if(bID != mod_SpiderQueen.smallWeb.blockID)
					//{
//						bID = worldObj.getBlockId((int)(movingobjectposition.hitVec.xCoord + motionX / 25F * fm),(int)(movingobjectposition.hitVec.yCoord + motionY / 25F * fm),(int)(movingobjectposition.hitVec.zCoord + motionZ / 25F * fm));
						//if(bID == Block.web.blockID) { fm = 55F; }
					//}
					fm = fm + 5F;
				//}
				if(bID == mod_SpiderQueen.smallWeb.blockID)
				{
					// NOTHING
				}
				else
				{
					int ff = -5;
					
					while(ff < 100)
					{
						xTile = (int)(movingobjectposition.hitVec.xCoord - motionX / 25F * ff);
						yTile = (int)(movingobjectposition.hitVec.yCoord - motionY / 25F * ff);
						zTile = (int)(movingobjectposition.hitVec.zCoord - motionZ / 25F * ff);
						if(zTile < 0) { zTile--;}
						if(xTile < 0) { xTile--;}
						
						ff++;
						int tId = worldObj.getBlockId(xTile, yTile, zTile);
						if(tId == Block.tallGrass.blockID || tId == Block.deadBush.blockID)
						{
							tId = 0;
							worldObj.setBlock(xTile,yTile,zTile,0);
						}
						if(tId == 0) { ff = 150; }
						if(tId == mod_SpiderQueen.smallWeb.blockID || tId == mod_SpiderQueen.poisonWeb.blockID) { ff = 151; }
					}
					
					if(ff == 151)
					{
						if(nomake == false)
						{
							if(worldObj.getBlockMetadata(xTile, yTile, zTile) < 1)
							{
								worldObj.setBlockMetadata(xTile, yTile, zTile, worldObj.getBlockMetadata(xTile, yTile, zTile) + 1);
							}
							else
							{
								
									worldObj.setBlockWithNotify(xTile, yTile, zTile, Block.web.blockID);
								
								//checkForBed(xTile,yTile,zTile,0);
							}
						}
						setDead();
						return;
					}
					
					if(ff == 150)
					{
						if(nomake == false)
						{
							if(poison)
							{
								worldObj.setBlockWithNotify(xTile, yTile, zTile, mod_SpiderQueen.poisonWeb.blockID);
							}
							else
							{
								worldObj.setBlockWithNotify(xTile, yTile, zTile, mod_SpiderQueen.smallWeb.blockID);
							}
						}
						setDead();
						return;
					}
					else
					{
						motionX = motionX * -0.8F;
						motionY = motionY * -0.8F;
						motionZ = motionZ * -0.8F;
					}
            	}
            }
        }
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
		
				
        float f2 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        for(rotationPitch = (float)((Math.atan2(motionY, f2) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f3 = 0.99F;
        float f5 = 0.03F;
        if(handleWaterMovement())
        {
            for(int k = 0; k < 4; k++)
            {
                float f6 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f6, posY - motionY * (double)f6, posZ - motionZ * (double)f6, motionX, motionY, motionZ);
            }

            f3 = 0.8F;
        }
        motionX *= f3;
        motionY *= f3;
        motionZ *= f3;
        motionY -= f5;
        setPosition(posX, posY, posZ);
    }

	
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("shake", (byte)arrowShake);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        arrowShake = nbttagcompound.getByte("shake") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
        if(worldObj.isRemote)
        {
            return;
        }
        if(inGround && field_682_g == entityplayer && arrowShake <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_SpiderQueen.itemWeb, 1)))
        {
            entityplayer.onItemPickup(this, 1);
            setDead();
        }
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

	public void setNoMake()
	{
		nomake = true;
	}

    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private int destr;
    private boolean inGround;
    public int arrowShake;
    public EntityLiving field_682_g;
    private int field_681_h;
    private int field_680_i;
	private int damage;
	private boolean inactive;
	private boolean nomake;
	private boolean poison;
}
