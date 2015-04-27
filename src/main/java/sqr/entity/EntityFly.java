package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sqr.core.INewMob;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item,
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie,
//            EntityLightningBolt

public class EntityFly extends EntityCreature implements INewMob
{
	
	public EntityFly(World world)
	{
		super(world);
		// TODO texture ="/imgz/flytexture.png";
		// health = 20;
		// TODO Attribute moveSpeed = 0.94F;
		this.setSize(0.9F, 0.9F);
	}
	
	// @Override
	// public int getMaxHealth() { return 20; }
	
	@Override
	protected void updateEntityActionState()
	{
		if (this.cocooned)
		{
			return;
		}
		super.updateEntityActionState();
	}
	
	@Override
	protected void fall(float f)
	{
	}
	
	@Override
	public boolean getCanSpawnHere()
	{
		final int x = MathHelper.floor_double(this.posX);
		final int y = MathHelper.floor_double(this.boundingBox.minY);
		final int z = MathHelper.floor_double(this.posZ);
		return this.worldObj.checkBlockCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.getBlock(x, y - 1, z) == Blocks.grass;
	}
	
	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}
	
	@Override
	public boolean interact(EntityPlayer entityplayer)
	{
		entityplayer.heal(3);
		entityplayer.entityDropItem(new ItemStack(Items.string, 3, 0), 0);
		this.worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
		this.worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
		this.setDead();
		return true;
	}
	
	@Override
	protected Entity findPlayerToAttack()
	{
		return null;
	}
	
	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
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
	
	protected int getDropItemId()
	{
		return 0;
	}
	
	@Override
	public void onLivingUpdate()
	{
		if (this.cocooned)
		{
			// TODO texture ="/imgz/flytexture_c.png";
			this.motionX = 0F;
			this.motionZ = 0F;
		}
		else
		{
			// TODO texture ="/imgz/flytexture.png";
		}
		
		if (!this.cocooned)
		{
			if (this.motionY > 0)
			{
				this.motionY = this.motionY * 0.97F + 0.06F;
			}
			else
			{
				final double dd = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
				
				this.motionY = this.motionY * 0.3F + dd * 0.3F;
			}
		}
		
		this.fallDistance = 0.0F;
		
		super.onLivingUpdate();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		if (entity instanceof EntityLivingBase)
		{
			final EntityLivingBase entityplayer = (EntityLivingBase) entity;
			entityplayer.heal(3);
			entityplayer.entityDropItem(new ItemStack(Items.string, 3, 0), 0);
			this.worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
			this.worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
			this.setDead();
		}
		return false;
	}
	
	public void setCocooned(boolean b)
	{
		this.cocooned = b;
	}
	
	private boolean cocooned;
}
