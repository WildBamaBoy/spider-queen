

package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
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

public class EntityOctopus extends EntityCreature
implements INewMob
{

	public EntityOctopus(World world)
	{
		super(world);
		//TODO texture ="/imgz/octoskin.png";
		//health = 20;
		//TODO Attribute moveSpeed = 0.8F;
		this.setSize(0.9F, 0.9F);
		this.natk = 0;
	}
//
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

	}


	@Override
	public boolean getCanSpawnHere()
	{
		return this.worldObj.checkBlockCollision(this.boundingBox);
	}


	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}

	@Override
	protected void attackEntity(Entity entity, float f)
	{

		if(this.natk > 0)
		{
			this.natk++;

			if(this.natk < 60)
			{
				this.motionZ = 0;
				this.motionX = 0;
			}
			this.motionY = 0;

			if(this.natk < 10)
			{
				this.motionY = 0.1F;
			}

			if(this.natk == 50)
			{

				double d = entity.posX - this.posX;
				double d1 = entity.posZ - this.posZ;
				final float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.5F;
				d = d + entity.motionX * 7.2D * f1;
				d1 = d1 + entity.motionZ * 7.2D * f1;
				final EntityOctoball entityarrow = new EntityOctoball(this.worldObj, this);
				final double d2 = entity.posY + 0.4D - 0.20000000298023224D - entityarrow.posY;
				this.worldObj.playSoundAtEntity(this, "Octoball", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
				this.worldObj.spawnEntityInWorld(entityarrow);
				entityarrow.setOctoballHeading(d, d2, d1, 0.6F, 0F);
			}

			if(this.natk > 50)
			{
				this.motionY = -0.15F;
			}
			else
			{
				if(this.posY + 0.5F - 0.3F > entity.posY) { this.motionY = this.motionY - 0.25F; }
				if(this.posY + 0.5F + 0.3F < entity.posY) { this.motionY = this.motionY + 0.25F; }
			}

			if(this.natk > 60)
			{
				this.motionX = (this.motionX * 1.1F + (this.rand.nextFloat() - 0.5F) / 10F) * 0.95F;
				this.motionZ = (this.motionZ * 1.1F + (this.rand.nextFloat() - 0.5F) / 10F) * 0.95F;
				//TODO Attribute moveSpeed = Math.abs(moveSpeed) * -0.95F;
			}

			if(this.natk > 140)
			{
				this.natk = 0;
				this.attackTime = 20;
				return;
			}



		}

		if(this.natk == 0)
		{
			if(f > 18.0F & this.entityToAttack == entity) { this.entityToAttack = null; return; }

			if(this.attackTime <= 0 && f < 18.0F)
			{
				this.natk = 1;
				this.motionX = 0;
				this.motionZ = 0;
				this.moveForward = 0;
				//attackTime = 20;
				//entity.attackEntityFrom(this, 4);
			}
		}
	}

	//TODO Irrelevant
//	@Override
//	protected Entity findPlayerToAttack()
//	{
//		if(this.rand.nextInt(15) != 1) { return null; }
//
//		Entity nn = null;
//		nn = SQR.getClosestEntityToEntity(this.worldObj, this, 18D, 5);
//		if(nn != null) { return nn; }
//		return this.worldObj.getClosestPlayerToEntity(this, 18D);
//	}

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
		return "Octoidle";
	}

	@Override
	protected String getHurtSound()
	{
		return "Octohurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "Octohurt";
	}


	protected int getDropItemId()
	{
		return 0;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		final int i = this.rand.nextInt(3) + 1;
		for(int j = 0; j < i; j++)
		{
			this.entityDropItem(new ItemStack(Items.dye, 1, 0), 0.0F);
		}

	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	protected void updateEntityActionState()
	{

		super.updateEntityActionState();
		if(!this.worldObj.isAnyLiquid(this.boundingBox)) { this.moveForward = 0F; }
		if(this.isCollidedHorizontally)  { this.moveForward = -0.5F; }

		if(this.natk > 0 & this.natk < 60 || this.natk == 0 & this.worldObj.isAirBlock((int)this.posX,(int)(this.posY + 1.17F),(int)this.posZ))
		{
			this.motionX = 0;
			this.motionZ = 0;
			this.moveForward = 0;
		}

	}

	@Override
	public void onLivingUpdate()
	{
		Entity entTrack = null;

		double dd = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		if(this.worldObj.isAirBlock((int)this.posX,(int)(this.posY + 1.17F),(int)this.posZ)) { dd = 0; }

		this.motionY = this.motionY * 0.05F + dd * 0.08F;
		//if(natk == 0){ motionY = motionY  - 0.05F; }

		if(this.entityToAttack != null)
		{
			entTrack = this.entityToAttack;

			final float f1 = this.entityToAttack.getDistanceToEntity(this);
			this.attackEntity(this.entityToAttack, f1);
			if(f1 > 13) { this.motionY = this.motionY - 0.05F; }
		}

		//TODO
//		if(this.natk == 0)
//		{
//			if(!this.worldObj.isAnyLiquid(this.boundingBox) || this.worldObj.getBlock((int)this.posX,(int)(this.posY + 0.2F),(int)this.posZ) == 0) { this.motionY = this.motionY * 0.9F - 0.1F; }
//			else
//			{
//				if(this.rand.nextInt(30) == 1) { this.motionY = this.motionY + 0.01F; }
//			}
//
//
//			if(this.worldObj.getBlock((int)this.posX,(int)(this.posY + 1.17F),(int)this.posZ) == 0)
//			{
//				this.motionY = -0.2F;
//			}
//			else
//			{
//				this.moveForward = 3F;
//			}
//		}

		this.fallDistance = 0.0F;

		if(this.natk > 0 & this.natk < 60)
		{
			this.motionX = 0;
			this.motionZ = 0;
			this.moveForward = 0;
		}

		super.onLivingUpdate();
	}


	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		this.entityToAttack = entity;
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
		return super.attackEntityFrom(damagesource, i);
	}

	private int natk;

}
