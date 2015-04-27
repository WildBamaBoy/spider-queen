package sqr.tile;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import sqr.entity.EntityAnt;
import sqr.entity.EntityRedAnt;

// Referenced classes of package net.minecraft.src:
//            TileEntity, AxisAlignedBB, World, EntityList,
//            EntityLivingBase, NBTTagCompound

public class TileEntityAntHill extends TileEntity
{

	public TileEntityAntHill()
	{
		this.delay = -1;
		this.yaw2 = 0.0D;
		this.mobID = "AntH";
		this.delay = 20;
		if(this.antType == 0)
		{
			this.antType = 1;
			final Random rm = new Random();
			if(rm.nextInt(2)==0) { this.antType = 2; }
		}
	}

	public String getMobID()
	{
		return this.mobID;
	}

	public void setMobID(String s)
	{
		this.mobID = s;
	}

	public boolean anyPlayerInRange()
	{
		return this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 96D) != null;
	}

	public void onDeath()
	{
		this.delay = 0;
		this.mobID = "die";
	}

	@Override
	public void updateEntity()
	{


		if(this.antType == 0)
		{
			this.antType = 1;
			final Random rm = new Random();
			if(rm.nextInt(2)==0) { this.antType = 2; }
		}

		this.yaw2 = this.yaw;
		final double d = this.xCoord + this.worldObj.rand.nextFloat();
		final double d2 = this.yCoord + this.worldObj.rand.nextFloat();
		final double d4 = this.zCoord + this.worldObj.rand.nextFloat();


		//worldObj.spawnParticle("smoke", d, d2, d4, 0.0D, 0.0D, 0.0D);
		//worldObj.spawnParticle("flame", d, d2, d4, 0.0D, 0.0D, 0.0D);
		if(this.delay == -1)
		{
			this.updateDelay();
			return;
		}
		if(this.delay > 0)
		{
			this.delay--;
			return;
		}
		if(!this.anyPlayerInRange())
		{
			return;
		}

		//if(!worldObj.getChunkProvider().chunkExists(xCoord / 16,zCoord / 16)) { updateDelay(); return; }

		//System.out.print(xCoord + ", " + yCoord + "," + zCoord + ", Nodelay\n");

		final byte byte0 = 1;

		boolean noGather = false;
		final boolean noWarrior = false;

		final int adr = 0;

		for(int i = 0; i < byte0; i++)
		{
			EntityLivingBase entityliving = null;
			if(this.antType == 1) { entityliving = new EntityAnt(this.worldObj); }//EntityList.createEntityInWorld(mobID, worldObj);
			if(this.antType == 2) { entityliving = new EntityRedAnt(this.worldObj); }//EntityList.createEntityInWorld(mobID, worldObj);

			if(entityliving == null)
			{
				return;
			}
			int j = 0;

			if(this.antType == 1) { j = this.worldObj.getEntitiesWithinAABB(EntityAnt.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(32D, 32D, 32D)).size(); }
			if(this.antType == 2) { j = this.worldObj.getEntitiesWithinAABB(EntityRedAnt.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(32D, 32D, 32D)).size(); }

			if(j >= 6)
			{
				this.updateDelay();
				noGather = true;
			}
			if(!noGather)
			{
				final Random rk = new Random();

				final double d6 = this.xCoord + (rk.nextDouble() - 0.5D) * 4D;
				final double d7 = this.yCoord + 1 + rk.nextInt(2);
				final double d8 = this.zCoord + (rk.nextDouble() - 0.5D) * 4D;

				if(this.worldObj.isAirBlock((int)d6,(int)d6,(int)d8))
				{
					entityliving.setLocationAndAngles(d6, d7, d8, rk.nextFloat() * 360F, 0.0F);
					this.worldObj.spawnEntityInWorld(entityliving);
				}
				this.delay = 50;
			}
			else
			{
				this.updateDelay();
			}
		}

		super.updateEntity();
	}

	private void updateDelay()
	{
		//System.out.print("upd\n");
		this.delay = 500 + this.worldObj.rand.nextInt(500);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		this.antType = nbttagcompound.getShort("AntType");
		super.readFromNBT(nbttagcompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("AntType", (short)this.antType);
		super.writeToNBT(nbttagcompound);
	}

	public int delay;
	private String mobID;
	private int antType;
	public double yaw;
	public double yaw2;
}
