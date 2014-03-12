// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import spiderqueen.old.entity.EntityGatherer;
import spiderqueen.old.entity.EntityQueenBee;
import spiderqueen.old.entity.EntityWarrior;

// Referenced classes of package net.minecraft.src:
//            TileEntity, AxisAlignedBB, World, EntityList, 
//            EntityLiving, NBTTagCompound

public class TileEntityBeeHive extends TileEntity
{

    public TileEntityBeeHive()
    {
        delay = -1;
        yaw2 = 0.0D;
        mobID = "Bee";
        delay = 20;
    }

    public String getMobID()
    {
        return mobID;
    }

    public void setMobID(String s)
    {
        mobID = s;
    }

    public boolean anyPlayerInRange()
    {
        return worldObj.getClosestPlayer((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, 64D) != null;
    }
	
	public void onDeath()
	{
		delay = 0;
		mobID = "die";
	}

    public void updateEntity()
    {
        yaw2 = yaw;
        double d = (float)xCoord + worldObj.rand.nextFloat();
        double d2 = (float)yCoord + worldObj.rand.nextFloat();
        double d4 = (float)zCoord + worldObj.rand.nextFloat();
		
		if(worldObj.getBlockId(xCoord,yCoord+1,zCoord) == mod_SpiderQueen.beehive.blockID) { return; }
		if(worldObj.getBlockId(xCoord+1,yCoord+1,zCoord) == mod_SpiderQueen.beehive.blockID) { return; }
		if(worldObj.getBlockId(xCoord-1,yCoord+1,zCoord) == mod_SpiderQueen.beehive.blockID) { return; }
		if(worldObj.getBlockId(xCoord,yCoord+1,zCoord+1) == mod_SpiderQueen.beehive.blockID) { return; }
		if(worldObj.getBlockId(xCoord,yCoord+1,zCoord-1) == mod_SpiderQueen.beehive.blockID) { return; }
		
		if(mod_SpiderQueen.hiveAlert) { delay = 0; }
        //worldObj.spawnParticle("smoke", d, d2, d4, 0.0D, 0.0D, 0.0D);
        //worldObj.spawnParticle("flame", d, d2, d4, 0.0D, 0.0D, 0.0D);
        if(delay == -1)
        {
            updateDelay();
        }
        if(delay > 0)
        {
            delay--;
            return;
        }
		
        if(!anyPlayerInRange())
        {
            return;
        }
		
        byte byte0 = 1;
		
		boolean noGather = false;
		boolean noWarrior = false;
		
		int adr = 0;
		if(mod_SpiderQueen.hiveAlert) { adr = 2; byte0 = 5; }
		
        for(int i = 0; i < byte0; i++)
        {
            EntityLiving entityliving = (EntityLiving) new EntityGatherer(worldObj);//EntityList.createEntityInWorld(mobID, worldObj);
            if(entityliving == null)
            {
                return;
            }
            int j = worldObj.getEntitiesWithinAABB(entityliving.getClass(), AxisAlignedBB.getBoundingBoxFromPool(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(64D, 32D, 64D)).size();
            if(j >= 6)
            {
                updateDelay();
                noGather = true;
            }
			if(!noGather)
			{
				double d6 = (double)xCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 16D;
				double d7 = (yCoord + worldObj.rand.nextInt(3)) - 1;
				double d8 = (double)zCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 16D;
				
				if(worldObj.getBlockId((int)d6,(int)d6,(int)d8) == 0)
				{
					entityliving.setLocationAndAngles(d6, d7, d8, worldObj.rand.nextFloat() * 360F, 0.0F);
					worldObj.spawnEntityInWorld(entityliving);
				}
			}
			
			
			EntityLiving entityliving2 = (EntityLiving) new EntityWarrior(worldObj);//EntityList.createEntityInWorld(mobID, worldObj);
            if(entityliving2 == null)
            {
                return;
            }
            int j2 = worldObj.getEntitiesWithinAABB(entityliving2.getClass(), AxisAlignedBB.getBoundingBoxFromPool(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(64D, 32D, 64D)).size();
            if(j2 >= 3+adr)
            {
                updateDelay();
                noWarrior = true;
            }
			if(!noWarrior)
			{
				double d6 = (double)xCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 16D;
				double d7 = (yCoord + worldObj.rand.nextInt(3)) - 1;
				double d8 = (double)zCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 16D;
				
				if(worldObj.getBlockId((int)d6,(int)d7,(int)d8) == 0 & 
				worldObj.getBlockId((int)d6-1,(int)d7,(int)d8) == 0 &
				worldObj.getBlockId((int)d6+1,(int)d7,(int)d8) == 0 &
				worldObj.getBlockId((int)d6-2,(int)d7,(int)d8) == 0 &
				worldObj.getBlockId((int)d6+2,(int)d7,(int)d8) == 0 &
				worldObj.getBlockId((int)d6,(int)d7-1,(int)d8) == 0 &
				worldObj.getBlockId((int)d6,(int)d7+1,(int)d8) == 0 &
				worldObj.getBlockId((int)d6,(int)d7-2,(int)d8) == 0 &
				worldObj.getBlockId((int)d6,(int)d7+2,(int)d8) == 0 &
				worldObj.getBlockId((int)d6,(int)d7,(int)d8-1) == 0 &
				worldObj.getBlockId((int)d6,(int)d7,(int)d8+1) == 0 &
				worldObj.getBlockId((int)d6,(int)d7,(int)d8-2) == 0 &
				worldObj.getBlockId((int)d6,(int)d7,(int)d8+2) == 0)
				{
					entityliving2.setLocationAndAngles(d6, d7, d8, worldObj.rand.nextFloat() * 360F, 0.0F);
					worldObj.spawnEntityInWorld(entityliving2);
				}
			}
			
			if(mod_SpiderQueen.hiveAlert)
			{
				EntityLiving entityliving3 = (EntityLiving) new EntityQueenBee(worldObj);//EntityList.createEntityInWorld(mobID, worldObj);
				if(entityliving3 == null)
				{
					return;
				}
				int j3 = worldObj.getEntitiesWithinAABB(entityliving3.getClass(), AxisAlignedBB.getBoundingBoxFromPool(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(64D, 32D, 64D)).size();
				if(j3 != 0)
				{
					updateDelay();
				}
				else
				{
				
					double d6 = (double)xCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 16D;
					double d7 = (yCoord + worldObj.rand.nextInt(3)) - 1;
					double d8 = (double)zCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 16D;
					
					if(worldObj.getBlockId((int)d6,(int)d7,(int)d8) == 0 & 
					worldObj.getBlockId((int)d6-1,(int)d7,(int)d8) == 0 &
					worldObj.getBlockId((int)d6+1,(int)d7,(int)d8) == 0 &
					worldObj.getBlockId((int)d6-2,(int)d7,(int)d8) == 0 &
					worldObj.getBlockId((int)d6+2,(int)d7,(int)d8) == 0 &
					worldObj.getBlockId((int)d6,(int)d7-1,(int)d8) == 0 &
					worldObj.getBlockId((int)d6,(int)d7+1,(int)d8) == 0 &
					worldObj.getBlockId((int)d6,(int)d7-2,(int)d8) == 0 &
					worldObj.getBlockId((int)d6,(int)d7+2,(int)d8) == 0 &
					worldObj.getBlockId((int)d6,(int)d7,(int)d8-1) == 0 &
					worldObj.getBlockId((int)d6,(int)d7,(int)d8+1) == 0 &
					worldObj.getBlockId((int)d6,(int)d7,(int)d8-2) == 0 &
					worldObj.getBlockId((int)d6,(int)d7,(int)d8+2) == 0)
					{
						entityliving3.setLocationAndAngles(d6, d7, d8, worldObj.rand.nextFloat() * 360F, 0.0F);
						worldObj.spawnEntityInWorld(entityliving3);
					}
				}
			}
			
            updateDelay();
        }

		if(mod_SpiderQueen.hiveAlert) { mod_SpiderQueen.hiveAlert = false; mod_SpiderQueen.pissOffBees(worldObj, null, xCoord, yCoord, zCoord, 64D); }
        super.updateEntity();
    }

    private void updateDelay()
    {
        delay = 3000 + worldObj.rand.nextInt(3000);
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
    }

    public int delay;
    private String mobID;
    public double yaw;
    public double yaw2;
}
