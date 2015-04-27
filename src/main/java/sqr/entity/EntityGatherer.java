package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sqr.core.minecraft.ModBlocks;
import sqr.core.minecraft.ModItems;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item,
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie,
//            EntityLightningBolt

public class EntityGatherer extends EntityCreature
{
	
	public EntityGatherer(World world)
	{
		super(world);
		// TODO texture ="/imgz/gathererskin.png";
		this.setSize(0.9F, 0.9F);
	}
	
	// TODO
	// @Override
	// public int getMaxHealth() { return 10; }
	
	@Override
	protected void fall(float f)
	{
	}
	
	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
	}
	
	@Override
	protected String getLivingSound()
	{
		return "bee";
	}
	
	@Override
	protected String getHurtSound()
	{
		return "beehurt";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "beedeath";
	}
	
	// TODO
	// protected int getDropItemId()
	// {
	// final Random rr = new Random();
	// if(rr.nextInt(6) != 0) { return 0; }
	// return ModItems.itemPWeb;
	// }
	
	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		final Item i = ModItems.itemPWeb;
		this.dropItem(i, 1);
		this.dropItem(Item.getItemFromBlock(ModBlocks.stinger), 1);
	}
	
	// TODO Irrelevant.
	// @Override
	// public void onLivingUpdate()
	// {
	// if(getAte() > 0)
	// { this.fPlay = null; setAte(getAte()-1); }
	// else
	// {
	// final Random rm = new Random();
	// if(rm.nextInt() == 150)
	// {
	// if(this.fPlay == null) { this.fPlay =
	// SQR.findBaitToAttack(this.worldObj,this); }
	// }
	// if(this.fPlay != null)
	// {
	// final int bbid = this.worldObj.getBlock(this.fPlay.xCoord,
	// this.fPlay.yCoord, this.fPlay.zCoord);
	// if(bbid != Blocks.plantYellow & bbid != Blocks.plantRed)
	// {
	// this.fPlay = null;
	// }
	// else
	// {
	// if(SQR.getDistance3d(this.posX, this.posY, this.posZ, this.fPlay.xCoord,
	// this.fPlay.yCoord, this.fPlay.zCoord) > 6F)
	// {
	// SQR.faceTEntity(this,this.fPlay, 30F);
	// float xx = (float) (this.posX - this.fPlay.xCoord);
	// xx = xx * xx;
	// float zz = (float) (this.posZ - this.fPlay.zCoord);
	// zz = zz * zz;
	// }
	// }
	// }
	// }
	//
	// if(this.motionY > 0)
	// {
	// this.motionY = this.motionY * 0.97F + 0.06F;
	// }
	// else
	// {
	// final double dd = Math.sqrt(this.motionX * this.motionX + this.motionZ *
	// this.motionZ);
	//
	// this.motionY = this.motionY * 0.3F + dd * 0.3F;
	// }
	//
	// this.fallDistance = 0.0F;
	//
	// super.onLivingUpdate();
	// }
	
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float f)
	{
		if (damageSource != null)
		{
			final Entity entity = damageSource.getEntity();
			
			if (entity != null)
			{
				// TODO
				// SQR.pissOffBees(this.worldObj, entity, this.posX, this.posY,
				// this.posZ, 64F);
			}
		}
		
		return super.attackEntityFrom(damageSource, f);
	}
	
	private TileEntity fPlay;
}
