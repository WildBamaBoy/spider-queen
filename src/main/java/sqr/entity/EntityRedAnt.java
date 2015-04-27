

package sqr.entity;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import radixcore.util.RadixMath;
import sqr.core.INewMob;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, AchievementList, World, Item,
//            EntityPlayer, NBTTagCompound, DataWatcher, EntityPigZombie,
//            EntityLightningBolt

public class EntityRedAnt extends EntityCreature
implements INewMob
{

	public EntityRedAnt(World world)
	{
		super(world);
		//TODO texture ="/imgz/antskinr.png";
		this.setSize(0.9F, 0.9F);
	}

//	@Override
//	public int getMaxHealth() { return 10; }


	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		final int x = MathHelper.floor_double(this.posX);
		final int y = MathHelper.floor_double(this.boundingBox.minY);
		final int z = MathHelper.floor_double(this.posZ);
		return this.worldObj.checkBlockCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox) && (this.worldObj.getBlock(x, y - 1, z) == Blocks.stone || this.worldObj.getBlock(x, y - 1, z) == Blocks.sand);
	}

	@Override
	protected void attackEntity(Entity entity, float f)
	{
		if(this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
		}
	}

//	@Override
//	protected Entity findPlayerToAttack()
//	{
//		Entity nn = null;
//		if(SQR.AntLike > 24)
//		{
//			nn = this.worldObj.getClosestPlayerToEntity(this, 16D);
//			if(nn != null) { return nn; }
//		}
//		return SQR.getClosestEntityToEntity(this.worldObj, this, 16D, 3);
//	}

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
		return "mob.ant";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.anthurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.antdeath";
	}

	protected int getDropItemId()
	{
		return 0;
	}

	public boolean isLeader()
	{
		return this.leader;
	}

	@Override
	protected void updateEntityActionState()
	{
		this.entityAge++;
		if(this.entityAge > 600 && this.rand.nextInt(800) == 0)
		{
			this.setDead();
		}

		if(this.isInWater()) { this.setDead(); return; }

		this.moveStrafing = 0.0F;
		this.moveForward = 0.0F;
		this.isJumping = false;
		if(this.entityToAttack != null)
		{
			this.faceEntity(this.entityToAttack, 30F, 30F);
			if(RadixMath.getDistanceToXYZ(this.posX,this.posY,this.posZ,this.entityToAttack.posX,this.entityToAttack.posY,this.entityToAttack.posZ) > 14D) 
			{ 
//				this.moveForward = moveSpeed; 
			} 
			
			else 
			{ 
				this.moveForward = 0; 
			}
		}
		
		if(this.tracking != null)
		{
			this.faceEntity(this.tracking, 30F, 30F);
			if(RadixMath.getDistanceToXYZ(this.posX,this.posY,this.posZ,this.tracking.posX,this.tracking.posY,this.tracking.posZ) > 14D) 
			{ 
				//this.moveForward = moveSpeed; 
			}
			
			else 
			{ 
				this.moveForward = 0; 
			}
		}
//		if(this.leader || this.stunned > 50) { this.moveForward = moveSpeed; }

		if(this.rand.nextFloat() < 0.05F)
		{
			this.randomYawVelocity = (this.rand.nextFloat() - 0.5F) * 5F;
			if(this.stunned > 50) { this.randomYawVelocity = 0; }
		}
		this.rotationYaw += this.randomYawVelocity;

		this.hasAttacked = this.isMovementCeased();
		final float f = 16F;
		final Random rm = new Random();
		if(this.entityToAttack == null)
		{
			if(rm.nextInt(3) == 1) { this.entityToAttack = this.findPlayerToAttack(); }
		} else
			if(!this.entityToAttack.isEntityAlive())
			{
				this.entityToAttack = null;
			} else
			{
				final float f1 = this.entityToAttack.getDistanceToEntity(this);
				if(this.canEntityBeSeen(this.entityToAttack))
				{
					this.attackEntity(this.entityToAttack, f1);
				} 
				
				else
				{
					//TODO Missing
					//attackBlockedEntity(this.entityToAttack, f1);
				}
			}

		final int i = MathHelper.floor_double(this.boundingBox.minY + 0.5D);
		final boolean flag = this.isInWater();
		final boolean flag1 = this.handleLavaMovement();
		this.rotationPitch = 0.0F;


		this.isJumping = false;
		if(this.entityToAttack != null)
		{
//			this.moveForward = moveSpeed;

			if(this.hasAttacked && this.entityToAttack != null)
			{
				final double d4 = this.entityToAttack.posX - this.posX;
				final double d5 = this.entityToAttack.posZ - this.posZ;
				final float f5 = this.rotationYaw;
				this.rotationYaw = (float)(Math.atan2(d5, d4) * 180D / 3.1415927410125732D) - 90F;
				final float f4 = (f5 - this.rotationYaw + 90F) * 3.141593F / 180F;
				this.moveStrafing = -MathHelper.sin(f4) * this.moveForward * 1.0F;
				this.moveForward = MathHelper.cos(f4) * this.moveForward * 1.0F;
			}
		}
		if(this.entityToAttack != null)
		{
			this.faceEntity(this.entityToAttack, 30F, 30F);
		}
		if(this.isCollidedHorizontally && !this.hasPath())
		{
			this.isJumping = true;
		}
		if(this.rand.nextFloat() < 0.8F && (flag || flag1))
		{
			this.isJumping = true;
		}
	}

	@Override
	protected boolean canDespawn()
	{
		return true;
	}

	public int getAntId()
	{
		if(this.antId == 0)
		{
			final Random rr = new Random();
			this.antId = rr.nextInt(500);
		}
		if(this.antId < 0) { this.antId = 0; }
		if(this.antId > 500) { this.antId = 500; }
		return this.antId;
	}

	public void setTracking(Entity tar)
	{
		this.tracking = tar;
	}

	public Entity getTracking()
	{
		return this.tracking;
	}

	@Override
	public void onLivingUpdate()
	{

		if(this.isInWater()) { this.setDead(); return; }

		if(this.entityToAttack != null) { this.tracking = null; }
		if(this.tracking != null) { final EntityRedAnt nne = (EntityRedAnt)this.tracking; if(nne.getTracking() == this) { this.tracking = null; } }

		if(this.isCollidedHorizontally & this.isCollidedVertically)
		{
			this.munch++;
			if(this.munch>20)
			{
				this.munch = 0;
				final int pX = (int)this.posX;
				final int pY = (int)this.posY;
				final int pZ = (int)this.posZ;
				final Random rr = new Random();
				
				//TODO
//				for(int xx=-1; xx<=1; xx++){ for(int yy=-1; yy<=1; yy++){ for(int zz=-1; zz<=1; zz++){
//					if(yy < 1 || rr.nextInt(5) == 0)
//					{
//						final int id = this.worldObj.getBlock(pX+xx,pY+yy,pZ+zz);
//						if(id == Blocks.dirt || id == Blocks.grass || id == Blocks.stone)
//						{
//							if(rr.nextInt(10) == 0 & (rr.nextInt(4) == 0 || xx != 0 || zz != 0 || yy < 1)) { this.worldObj.setBlockWithNotify(pX+xx,pY+yy,pZ+zz,0); }
//						}
//					}
//				}}}
			}
		}
		else
		{
			this.munch = this.munch - 5;
			if(this.munch < 0) { this.munch = 0; }
		}

		if(this.stunned > 0)
		{ this.stunned--; }
		else
		{
			if(this.entityToAttack == null)
			{
				final Random rr = new Random();
				if(rr.nextInt(100) == 0)
				{
					this.antId = this.antId - 50 + rr.nextInt(50); this.leader = false; this.tracking = null; this.updateEntityActionState();
				}

				if(this.tracking == null)
				{
					final Random rm2 = new Random();
					if(rm2.nextInt(5) == 2)
					{
//						this.tracking = SQR.getClosestEntityToEntity(this.worldObj, this, 32D, 8);
						if(this.tracking == null) { this.leader = true; }
					}
				}
				else
				{
					this.faceEntity(this.tracking, 30F, 30F);
				}
			}
		}

		super.onLivingUpdate();
	}


	@Override
	public void applyEntityCollision(Entity entity)
	{
		super.applyEntityCollision(entity);
		this.stunned = this.stunned + 10;
		if(this.stunned > 100)
		{
			this.stunned = 100;
			this.tracking = null;
		}
	}

	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		if(entity instanceof EntityPlayer & this.entityToAttack == null)
		{
//			SQR.likeChange("ant",1);
		}

		this.entityToAttack = entity;
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
		return super.attackEntityFrom(damagesource, i);
	}

	private Entity tracking;
	private boolean leader;
	private int antId;
	private int stunned;
	private int munch;
}
