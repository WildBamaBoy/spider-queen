

package sqr.tile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import sqr.core.minecraft.ModBlocks;
import sqr.entity.EntityGatherer;
import sqr.entity.EntityWarrior;

// Referenced classes of package net.minecraft.src:
//            TileEntity, AxisAlignedBB, World, EntityList,
//            EntityLivingBase, NBTTagCompound

public class TileEntityBeeHive extends TileEntity
{

	public TileEntityBeeHive()
	{
		this.delay = -1;
		this.yaw2 = 0.0D;
		this.mobID = "Bee";
		this.delay = 20;
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
		return this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 64D) != null;
	}

	public void onDeath()
	{
		this.delay = 0;
		this.mobID = "die";
	}

	@Override
	public void updateEntity()
	{
		this.yaw2 = this.yaw;
		final double d = this.xCoord + this.worldObj.rand.nextFloat();
		final double d2 = this.yCoord + this.worldObj.rand.nextFloat();
		final double d4 = this.zCoord + this.worldObj.rand.nextFloat();

		if(this.worldObj.getBlock(this.xCoord,this.yCoord+1,this.zCoord) == ModBlocks.beehive) { return; }
		if(this.worldObj.getBlock(this.xCoord+1,this.yCoord+1,this.zCoord) == ModBlocks.beehive) { return; }
		if(this.worldObj.getBlock(this.xCoord-1,this.yCoord+1,this.zCoord) == ModBlocks.beehive) { return; }
		if(this.worldObj.getBlock(this.xCoord,this.yCoord+1,this.zCoord+1) == ModBlocks.beehive) { return; }
		if(this.worldObj.getBlock(this.xCoord,this.yCoord+1,this.zCoord-1) == ModBlocks.beehive) { return; }

		//if(mod_SpiderQueen.hiveAlert) { this.delay = 0; }
		//worldObj.spawnParticle("smoke", d, d2, d4, 0.0D, 0.0D, 0.0D);
		//worldObj.spawnParticle("flame", d, d2, d4, 0.0D, 0.0D, 0.0D);
		if(this.delay == -1)
		{
			this.updateDelay();
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

		byte byte0 = 1;

		boolean noGather = false;
		boolean noWarrior = false;

		int adr = 0;
		//if(mod_SpiderQueen.hiveAlert) { adr = 2; byte0 = 5; }

		for(int i = 0; i < byte0; i++)
		{
			final EntityLivingBase entityliving = new EntityGatherer(this.worldObj);//EntityList.createEntityInWorld(mobID, worldObj);

			final int j = this.worldObj.getEntitiesWithinAABB(entityliving.getClass(), AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(64D, 32D, 64D)).size();
			if(j >= 6)
			{
				this.updateDelay();
				noGather = true;
			}
			if(!noGather)
			{
				final double d6 = this.xCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 16D;
				final double d7 = this.yCoord + this.worldObj.rand.nextInt(3) - 1;
				final double d8 = this.zCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 16D;

				if(this.worldObj.isAirBlock((int)d6,(int)d6,(int)d8))
				{
					entityliving.setLocationAndAngles(d6, d7, d8, this.worldObj.rand.nextFloat() * 360F, 0.0F);
					this.worldObj.spawnEntityInWorld(entityliving);
				}
			}


			final EntityLivingBase entityliving2 = new EntityWarrior(this.worldObj);//EntityList.createEntityInWorld(mobID, worldObj);

			final int j2 = this.worldObj.getEntitiesWithinAABB(entityliving2.getClass(), AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(64D, 32D, 64D)).size();
			if(j2 >= 3+adr)
			{
				this.updateDelay();
				noWarrior = true;
			}
			if(!noWarrior)
			{
				final double d6 = this.xCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 16D;
				final double d7 = this.yCoord + this.worldObj.rand.nextInt(3) - 1;
				final double d8 = this.zCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 16D;

				if(this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6-1,(int)d7,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6+1,(int)d7,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6-2,(int)d7,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6+2,(int)d7,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6,(int)d7-1,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6,(int)d7+1,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6,(int)d7-2,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6,(int)d7+2,(int)d8)  &&
						this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8-1)  &&
						this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8+1)  &&
						this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8-2)  &&
						this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8+2) )
				{
					entityliving2.setLocationAndAngles(d6, d7, d8, this.worldObj.rand.nextFloat() * 360F, 0.0F);
					this.worldObj.spawnEntityInWorld(entityliving2);
				}
			}

//			if(mod_SpiderQueen.hiveAlert)
//			{
//				final EntityLivingBase entityliving3 = new EntityQueenBee(this.worldObj);//EntityList.createEntityInWorld(mobID, worldObj);
//				if(entityliving3 == null)
//				{
//					return;
//				}
//				final int j3 = this.worldObj.getEntitiesWithinAABB(entityliving3.getClass(), AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(64D, 32D, 64D)).size();
//				if(j3 != 0)
//				{
//					this.updateDelay();
//				}
//				else
//				{
//
//					final double d6 = this.xCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 16D;
//					final double d7 = this.yCoord + this.worldObj.rand.nextInt(3) - 1;
//					final double d8 = this.zCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * 16D;
//
//					if(this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6-1,(int)d7,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6+1,(int)d7,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6-2,(int)d7,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6+2,(int)d7,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6,(int)d7-1,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6,(int)d7+1,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6,(int)d7-2,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6,(int)d7+2,(int)d8)  &&
//							this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8-1)  &&
//							this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8+1)  &&
//							this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8-2)  &&
//							this.worldObj.isAirBlock((int)d6,(int)d7,(int)d8+2) )
//					{
//						entityliving3.setLocationAndAngles(d6, d7, d8, this.worldObj.rand.nextFloat() * 360F, 0.0F);
//						this.worldObj.spawnEntityInWorld(entityliving3);
//					}
//				}
//			}

			this.updateDelay();
		}

//		if(mod_SpiderQueen.hiveAlert) { mod_SpiderQueen.hiveAlert = false; mod_SpiderQueen.pissOffBees(this.worldObj, null, this.xCoord, this.yCoord, this.zCoord, 64D); }
		super.updateEntity();
	}

	private void updateDelay()
	{
		this.delay = 3000 + this.worldObj.rand.nextInt(3000);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
	}

	public int delay;
	private String mobID;
	public double yaw;
	public double yaw2;
}
