package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Main class that handles new spiders and their behavior.
 */
public class EntitySpiderEx extends EntityCreature
{
	public EntitySpiderEx(World world)
	{
		super(world);
		// health = 20;
		// this.maxhealth = health;
		// TODO texture ="/imgz/friendlyspider_1.png";
		// TODO Attribute moveSpeed = 0.87F;
		this.level = 0;
		this.xp = 0;
		this.setSize(1.4F, 0.9F);
		// spiderfriend = true;
	}
	
	// TODO Attribute
	// @Override
	// public int getMaxHealth()
	// {
	// return this.maxhealth;
	// }
	
	public String getEntityTexture()
	{
		if (this.getLevel() == 2)
		{
			return "/imgz/friendlyspider_3.png";
		}
		if (this.getLevel() == 1)
		{
			return "/imgz/friendlyspider_2.png";
		}
		return "/imgz/friendlyspider_1.png";
	}
	
	@Override
	public double getMountedYOffset()
	{
		return this.height * 0.75D - 0.5D;
	}
	
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	
	@Override
	protected String getLivingSound()
	{
		return "mob.spider";
	}
	
	@Override
	protected String getHurtSound()
	{
		return "mob.spider";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "mob.spiderdeath";
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("maxhealth", (short) this.maxhealth);
		nbttagcompound.setShort("xp", (short) this.xp);
		nbttagcompound.setShort("level", (short) this.level);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.maxhealth = nbttagcompound.getShort("maxhealth");
		this.xp = nbttagcompound.getShort("xp");
		this.level = nbttagcompound.getShort("level");
	}
	
	// TODO Irrelevant
	// protected int getDropItemId()
	// {
	// return Items.string;
	// }
	
	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}
	
	@Override
	protected boolean canDespawn()
	{
		return false;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	// TODO Irrelevant
	// @Override
	// protected Entity findPlayerToAttack()
	// {
	// return SQR.findEnemyToAttack(this.worldObj, this);
	// }
	
	@Override
	public int getTalkInterval()
	{
		return 160;
	}
	
	public int getAttackStrength()
	{
		return 2 + 3 * this.level;
	}
	
	// protected void attack2Entity(Entity entity, float f)
	// {
	//
	// if(this.attackTime <= 0 && f < 3.0F && entity.boundingBox.maxY >=
	// this.boundingBox.minY && entity.boundingBox.minY <=
	// this.boundingBox.maxY)
	// {
	//
	// boolean ins = false;
	// if(entity instanceof EntityFly) { ins = true; }
	// if(entity instanceof EntityCocoonAnt) { ins = true; }
	// if(entity instanceof EntityCocoonWasp) { ins = true; }
	// if(entity instanceof EntityCocoonGatherer) { ins = true; }
	// if(entity instanceof EntityCocoonWarrior) { ins = true; }
	// if(entity instanceof EntityCocoonQueenBee) { ins = true; }
	//
	// if(ins)
	// {
	// // this.addHP(10);
	// this.setXP(this.getXP() + 5);
	// this.worldObj.spawnParticle("largesmoke", entity.posX - this.motionX * 2,
	// entity.posY - this.motionY * 2, entity.posZ - this.motionZ * 2,
	// this.motionX, this.motionY, this.motionZ);
	// this.worldObj.spawnParticle("largesmoke", entity.posX - this.motionX * 2,
	// entity.posY - this.motionY * 2, entity.posZ - this.motionZ * 2,
	// this.motionX, this.motionY, this.motionZ);
	// entity.setDead();
	// }
	//
	// this.attackTime = 20;
	// entity.attackEntityFrom(DamageSource.causeMobDamage(this),
	// this.getAttackStrength());
	// this.xp = this.xp + 1;
	// }
	// }
	
	@Override
	public boolean interact(EntityPlayer entityplayer)
	{
		final ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		
		if (itemstack != null && itemstack.getItem() == Items.porkchop)
		{
			itemstack.stackSize--;
			if (itemstack.stackSize <= 0)
			{
				entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
			}
			if (!this.worldObj.isRemote)
			{
				// health = health + 10;
				// if(health > this.maxhealth) { health = this.maxhealth; }
				this.xp = this.xp + 5;
				this.showHeartsOrSmokeFX(true);
			}
			return true;
		}
		
		return false;
	}
	
	void showHeartsOrSmokeFX(boolean flag)
	{
		String s = "heart";
		if (!flag)
		{
			s = "explode";
		}
		for (int i = 0; i < 7; i++)
		{
			final double d = this.rand.nextGaussian() * 0.02D;
			final double d1 = this.rand.nextGaussian() * 0.02D;
			final double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle(s, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d, d1, d2);
		}
		
	}
	
	@Override
	protected void attackEntity(Entity entity, float f)
	{
		final float f1 = this.getBrightness(1.0F);
		if (f1 > 0.5F && this.rand.nextInt(100) == 0)
		{
			this.entityToAttack = null;
			return;
		}
		if (f > 3.0F && f < 8F && this.rand.nextInt(20) == 0)
		{
			if (this.onGround)
			{
				final double d = entity.posX - this.posX;
				final double d1 = entity.posZ - this.posZ;
				final float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
				this.motionX = d / f2 * 0.5D * 0.80000001192092896D + this.motionX * 0.20000000298023224D;
				this.motionZ = d1 / f2 * 0.5D * 0.80000001192092896D + this.motionZ * 0.20000000298023224D;
				this.motionY = 0.40000000596046448D;
			}
		}
		
		else
		{
			// TODO
			// this.attack2Entity(entity, f);
		}
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.fallDistance = 0.0F;
		if (this.xp > 15 + 15 * this.level)
		{
			
			this.showHeartsOrSmokeFX(false);
			this.showHeartsOrSmokeFX(false);
			this.showHeartsOrSmokeFX(false);
			this.showHeartsOrSmokeFX(false);
			this.xp = 0;
			this.level++;
			if (this.level > 2)
			{
				this.level = 2;
			}
			// this.maxhealth = 20 + 15 * this.level;
			// health = this.maxhealth;
		}
		
	}
	
	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();
		
	}
	
	public int getXP()
	{
		return this.xp;
	}
	
	public void setXP(int xx)
	{
		this.xp = xx;
	}
	
	// public void addHP(int xx) { health = health + xx; if(health >
	// this.maxhealth) { health = this.maxhealth; } }
	private int maxhealth;
	private int xp;
	private int level;
}
