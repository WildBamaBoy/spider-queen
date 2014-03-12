// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item, 
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie, 
//            EntityLightningBolt

public class EntityGatherer extends EntityNewAnimal
{

    public EntityGatherer(World world)
    {
        super(world);
        texture = "/imgz/gathererskin.png";
        setSize(0.9F, 0.9F);
    }

	public int getMaxHealth() { return 10; }
	
	
	protected void fall(float f)
    {
    }
	
    public boolean isOnLadder()
    {
        return isCollidedHorizontally;
    }
	
	public float getShadowSize()
    {
        return 0.1F;
    }
	
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "bee";
    }

    protected String getHurtSound()
    {
        return "beehurt";
    }

    protected String getDeathSound()
    {
        return "beedeath";
    }

    protected int getDropItemId()
    {
		Random rr = new Random();
		if(rr.nextInt(6) != 0) { return 0; }
        return mod_SpiderQueen.itemPWeb.shiftedIndex;
    }
	
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = getDropItemId();
        if(i > 0)
        {
            dropItem(i, 1);
        }
            dropItem(mod_SpiderQueen.stinger.blockID, 1);
    }
	
    public void onLivingUpdate()
    {
    	if(getAte() > 0) 
		{ fPlay = null; setAte(getAte()-1); }
    	else
    	{
			Random rm = new Random();
			if(rm.nextInt() == 150)
			{
				if(fPlay == null) { fPlay = (TileEntityFlower) mod_SpiderQueen.findBaitToAttack(worldObj,this); }
			}
	        if(fPlay != null)
	        {
				int bbid = worldObj.getBlockId(fPlay.xCoord, fPlay.yCoord, fPlay.zCoord);
				if(bbid != Block.plantYellow.blockID & bbid != Block.plantRed.blockID)
				{
					fPlay = null;
				}
				else
				{		
					if(mod_SpiderQueen.getDistance3d(posX, posY, posZ, fPlay.xCoord, fPlay.yCoord, fPlay.zCoord) > 6F)
					{
						mod_SpiderQueen.faceTEntity(this,fPlay, 30F);
						float xx = (float) (posX - fPlay.xCoord);
						xx = xx * xx;
						float zz = (float) (posZ - fPlay.zCoord);
						zz = zz * zz;
					}
				}
	        }
    	}
		
		if(motionY > 0)
		{
			motionY = motionY * 0.97F + 0.06F;
		}
		else
		{
			double dd = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
			
			motionY = motionY * 0.3F + dd * 0.3F;
		}
		
		fallDistance = 0.0F;
		
		super.onLivingUpdate();
    }
	
		
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		if(damagesource != null)
		{
			Entity entity = damagesource.getEntity();
			if(entity != null) { mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F); }
		}
        return super.attackEntityFrom(damagesource, i);
    }

	private TileEntity fPlay;
}
