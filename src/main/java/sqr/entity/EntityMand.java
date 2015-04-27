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

public class EntityMand extends EntityCreature
{
	public EntityMand(World world)
	{
		super(world);
		this.field_753_a = false;
		this.field_752_b = 0.0F;
		this.destPos = 0.0F;
		this.field_755_h = 1.0F;
		// TODO texture ="/imgz/mand.png";
		this.setSize(0.5F, 1F);
		// health = 4;
		this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		// this.setFriendly(false);
	}
	
	// @Override
	// public int getMaxHealth() { return 20; }
	
	// public void setFriendly(boolean f)
	// {
	// this.friendly = f;
	// spiderfriend = f;
	// if(this.friendly)
	// {
	// //TODO texture ="/imgz/fmand.png";
	// }
	// else
	// {
	// //TODO texture ="/imgz/mand.png";
	// }
	// }
	
	@Override
	protected boolean canDespawn()
	{
		if (this.friendly)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		if (!this.friendly)
		{
			this.entityToAttack = entity;
		}
		else
		{
			if (entity instanceof EntityPlayer || entity instanceof EntitySpiderEx)
			{
			}
			else
			{
				this.entityToAttack = entity;
			}
		}
		// mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
		return super.attackEntityFrom(damagesource, i);
	}
	
	// TODO
	// @Override
	// protected Entity findPlayerToAttack()
	// {
	// //if(rand.nextInt(15) != 1) { return null; }
	// Entity nn = null;
	// if(this.friendly)
	// {
	// nn = SQR.getClosestEntityToEntity(this.worldObj, this, 16D, 15);
	// }
	// else
	// {
	// nn = SQR.getClosestEntityToEntity(this.worldObj, this, 16D, 14);
	// if(nn != null) { return nn; }
	// return this.worldObj.getClosestPlayerToEntity(this, 16D);
	// }
	//
	// return nn;
	// }
	
	@Override
	public boolean getCanSpawnHere()
	{
		final int x = MathHelper.floor_double(this.posX);
		final int y = MathHelper.floor_double(this.boundingBox.minY);
		final int z = MathHelper.floor_double(this.posZ);
		return this.worldObj.checkBlockCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox) && (this.worldObj.getBlock(x, y - 1, z) == Blocks.stone || this.worldObj.getBlock(x, y - 1, z) == Blocks.grass);
	}
	
	@Override
	protected void attackEntity(Entity entity, float f)
	{
		
		if (f < 18F)
		{
			final double d = entity.posX - this.posX + entity.motionX * 15D;
			final double d1 = entity.posZ - this.posZ + entity.motionZ * 15D;
			if (this.attackTime == 0)
			{
				final EntityVines entityarrow = new EntityVines(this.worldObj, this);
				entityarrow.setFriendly(this.friendly);
				entityarrow.posY += 0.3999999761581421D;
				final double d2 = entity.posY - 0.20000000298023224D - entityarrow.posY;
				final float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
				this.worldObj.playSoundAtEntity(this, "Vine", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
				this.worldObj.spawnEntityInWorld(entityarrow);
				entityarrow.setArrowHeading(d, d2 + f1, d1, 0.5F, 8F);
				this.attackTime = 20 - this.rand.nextInt(5);
			}
			this.rotationYaw = (float) (Math.atan2(d1, d) * 180D / 3.1415927410125732D) - 90F;
			this.hasAttacked = true;
		}
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.motionX = 0;
		this.motionY = -0.1F;
		this.motionZ = 0;
		this.moveStrafing = 0;
		this.moveForward = 0;
		this.isJumping = false;
	}
	
	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();
		if (this.entityToAttack != null)
		{
			this.attackEntity(this.entityToAttack, this.getDistanceToEntity(this.entityToAttack));
			final Random rm = new Random();
			if (rm.nextInt(100) == 1 || !this.entityToAttack.isEntityAlive())
			{
				this.entityToAttack = null;
			}
		}
		else
		{
			final Random rm = new Random();
			if (rm.nextInt(300) == 1)
			{
				this.entityToAttack = this.findPlayerToAttack();
			}
		}
		
		this.motionX = 0;
		this.motionY = -0.1F;
		this.motionZ = 0;
		this.moveStrafing = 0;
		this.moveForward = 0;
		this.isJumping = false;
	}
	
	@Override
	protected void fall(float f)
	{
	}
	
	@Override
	protected String getLivingSound()
	{
		return "FMan";
	}
	
	@Override
	protected String getHurtSound()
	{
		return "FManHurt";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "FMandie";
	}
	
	// protected int getDropItemId()
	// {
	// return SQR.mseeds;
	// }
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		// nbttagcompound.setBoolean("friendly", this.friendly);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		// this.setFriendly(nbttagcompound.getBoolean("friendly"));
	}
	
	public boolean getFriendly()
	{
		return this.friendly;
	}
	
	private boolean friendly;
	public boolean field_753_a;
	public float field_752_b;
	public float destPos;
	public float field_757_d;
	public float field_756_e;
	public float field_755_h;
	public int timeUntilNextEgg;
}
