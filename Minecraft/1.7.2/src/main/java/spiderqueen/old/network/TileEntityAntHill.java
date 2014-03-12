// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.network;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import spiderqueen.old.entity.EntityAnt;
import spiderqueen.old.entity.EntityRedAnt;

// Referenced classes of package net.minecraft.src:
//            TileEntity, AxisAlignedBB, World, EntityList, 
//            EntityLiving, NBTTagCompound

public class TileEntityAntHill extends TileEntity
{

    public TileEntityAntHill()
    {
        delay = -1;
        yaw2 = 0.0D;
        mobID = "AntH";
        delay = 20;
		if(antType == 0)
		{
			antType = 1;
			Random rm = new Random();
			if(rm.nextInt(2)==0) { antType = 2; }
		}
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
        return worldObj.getClosestPlayer((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, 96D) != null;
    }
	
	public void onDeath()
	{
		delay = 0;
		mobID = "die";
	}

    public void updateEntity()
    {
	
	
		if(antType == 0)
		{
			antType = 1;
			Random rm = new Random();
			if(rm.nextInt(2)==0) { antType = 2; }
		}
		
        yaw2 = yaw;
        double d = (float)xCoord + worldObj.rand.nextFloat();
        double d2 = (float)yCoord + worldObj.rand.nextFloat();
        double d4 = (float)zCoord + worldObj.rand.nextFloat();
		
		
        //worldObj.spawnParticle("smoke", d, d2, d4, 0.0D, 0.0D, 0.0D);
        //worldObj.spawnParticle("flame", d, d2, d4, 0.0D, 0.0D, 0.0D);
        if(delay == -1)
        {
            updateDelay();
			return;
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
		
		//if(!worldObj.getChunkProvider().chunkExists(xCoord / 16,zCoord / 16)) { updateDelay(); return; }
		
		//System.out.print(xCoord + ", " + yCoord + "," + zCoord + ", Nodelay\n");
		
        byte byte0 = 1;
		
		boolean noGather = false;
		boolean noWarrior = false;
		
		int adr = 0;
		
        for(int i = 0; i < byte0; i++)
        {
            EntityLiving entityliving = null;
			if(antType == 1) { entityliving = (EntityLiving) new EntityAnt(worldObj); }//EntityList.createEntityInWorld(mobID, worldObj);
			if(antType == 2) { entityliving = (EntityLiving) new EntityRedAnt(worldObj); }//EntityList.createEntityInWorld(mobID, worldObj);
			
            if(entityliving == null)
            {
                return;
            }
            int j = 0;
			
			if(antType == 1) { j = worldObj.getEntitiesWithinAABB(EntityAnt.class, AxisAlignedBB.getBoundingBoxFromPool(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(32D, 32D, 32D)).size(); }
			if(antType == 2) { j = worldObj.getEntitiesWithinAABB(EntityRedAnt.class, AxisAlignedBB.getBoundingBoxFromPool(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(32D, 32D, 32D)).size(); }

            if(j >= 6)
            {
                updateDelay();
                noGather = true;
            }
			if(!noGather)
			{
				Random rk = new Random();
				
					double d6 = (double)xCoord + (rk.nextDouble() - 0.5D) * 4D;
					double d7 = (yCoord + 1 + (rk.nextInt(2)));
					double d8 = (double)zCoord + (rk.nextDouble() - 0.5D) * 4D;
					
					if(worldObj.getBlockId((int)d6,(int)d6,(int)d8) == 0)
					{
						entityliving.setLocationAndAngles(d6, d7, d8, rk.nextFloat() * 360F, 0.0F);
						worldObj.spawnEntityInWorld(entityliving);
					}
					delay = 50;
			}
			else
			{
				updateDelay();
			}
        }

		super.updateEntity();
    }

    private void updateDelay()
    {
		//System.out.print("upd\n");
        delay = 500 + worldObj.rand.nextInt(500);
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        antType = nbttagcompound.getShort("AntType");
        super.readFromNBT(nbttagcompound);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("AntType", (short)antType);
        super.writeToNBT(nbttagcompound);
    }

    public int delay;
    private String mobID;
    private int antType;
    public double yaw;
    public double yaw2;
}
