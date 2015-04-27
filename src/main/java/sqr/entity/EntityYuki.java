

package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sqr.core.INewMob;


// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item,
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie,
//            EntityLightningBolt

public class EntityYuki extends EntityCreature
implements INewMob
{

	public EntityYuki(World world)
	{
		super(world);
		//TODO texture ="/imgz/yuki.png";
//		health = 20;
		//TODO Attribute moveSpeed = 1.2F;
		this.setSize(0.9F, 1.5F);
	}

//	@Override
//	public int getMaxHealth() { return 20; }

	@Override
	protected boolean canDespawn()
	{
		return true;
	}

	@Override
	protected void fall(float f)
	{
		super.fall(f);
		final int i = (int)Math.ceil(f - 3F);
		if(i > 0)
		{
			this.attackEntityFrom(DamageSource.fall, i);
		}
	}

	@Override
	public boolean getCanSpawnHere()
	{
		final int x = MathHelper.floor_double(this.posX);
		final int y = MathHelper.floor_double(this.boundingBox.minY);
		final int z = MathHelper.floor_double(this.posZ);
		return this.worldObj.isRaining() && this.worldObj.checkBlockCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.getBlock(x, y, z) == Blocks.snow;
	}


	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt entitylightningbolt)
	{
		this.setFire(0);
	}


	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();

		if(this.attackTime > 0)
		{
			this.moveForward = Math.abs(this.moveForward) * -1;
		}
	}

	@Override
	protected void attackEntity(Entity entity, float f)
	{
		if(f < 16F)
		{
			final double d = entity.posX - this.posX;
			final double d1 = entity.posZ - this.posZ;
			if(this.attackTime == 0)
			{
				final double xx = entity.posX + this.rand.nextFloat() * 16F - 8F;
				final double zz = entity.posZ + this.rand.nextFloat() * 16F - 8F;
				final double yy = this.worldObj.getTopSolidOrLiquidBlock((int)xx,(int)zz);
				final EntityLightningBolt entityarrow = new EntityLightningBolt(this.worldObj,xx,yy,zz);
				//entityarrow.posY += 1.3999999761581421D;
				//double d2 = (entity.posY + (double)entity.getEyeHeight()) - 0.20000000298023224D - entityarrow.posY;
				//float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
				//worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
				this.worldObj.spawnEntityInWorld(entityarrow);
				//entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 0.6F, 12F);
				this.attackTime = 60;
			}
			this.rotationYaw = (float)(Math.atan2(d1, d) * 180D / 3.1415927410125732D) - 90F;
			this.hasAttacked = true;
		}
	}

	@Override
	protected Entity findPlayerToAttack()
	{
		/*Entity nn = null;
		nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 5);
		if(nn != null) { return nn; }*/
		return null;//worldObj.getClosestPlayerToEntity(this, 16D);
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
		return null;
	}

	@Override
	protected String getHurtSound()
	{
		return null;
	}

	@Override
	protected String getDeathSound()
	{
		return null;
	}

	protected int getDropItemId()
	{
		return 0;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		final int i = this.getDropItemId();
		if(i > 0)
		{
			final int j = 9;
			for(int k = 0; k < j; k++)
			{
				this.dropItem(Item.getItemFromBlock(Blocks.ice), 1);
			}

		}
		this.dropItem(Item.getItemFromBlock(Blocks.ice), 1);
	}


	@Override
	public void onLivingUpdate()
	{
		Entity entTrack = null;
		/*
		if(entityToAttack == null)
		{
			if(getAte() > 0)
			{ setAte(getAte()-1); }
			else
			{
				Entity fPlay;
				fPlay = mod_SpiderQueen.findBeeToAttack(worldObj,this);
				if(fPlay != null)
				{
					faceEntity(fPlay, 30F, 30F);
					entTrack = fPlay;
				}
			}
		}*/

		if(this.motionY > 0)
		{
			this.motionY = this.motionY * 0.97F + 0.06F;
		}
		else
		{
			final double dd = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			this.motionY = this.motionY * 0.3F + dd * 0.3F;
		}

		if(this.entityToAttack != null)
		{
			entTrack = this.entityToAttack;
		}

		if(entTrack != null)
		{
			if(entTrack.posY + 0.2F < this.posY)
			{
				this.motionY = this.motionY - 0.12F;
			}
			if(entTrack.posY - 0.5F > this.posY)
			{
				this.motionY = this.motionY + 0.01F;
			}
		}

		if(this.attackTime > 0)
		{
			this.moveForward = Math.abs(this.moveForward) * -1;
		}

		this.fallDistance = 0.0F;

		super.onLivingUpdate();
	}


	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		this.entityToAttack = entity;
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
		return super.attackEntityFrom(damagesource, i);
	}

}
