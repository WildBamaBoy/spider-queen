

package sqr.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sqr.core.INewMob;
import sqr.core.SQR;

public class EntityHuman extends EntityCreature implements INewMob
{
	public EntityHuman(World world)
	{
		super(world);
		//TODO texture ="/mob/char.png";
		//TODO Attribute moveSpeed = 0.75F;
//		health = 20;

		if(this.myType > -1) { return; }
		this.myType = 1;
		final Random rr = new Random();
		final int tt = rr.nextInt(100);
//		this.myItem = defaultHeldItem1;
		this.attackStrength = 3;
		//TODO
//		if(tt > 25) { this.myType = 2; this.myItem = defaultHeldItem2; this.attackStrength = 5; }
//		if(tt > 50) { this.myType = 3; this.myItem = defaultHeldItem3; this.attackStrength = 5; //TODO Attribute moveSpeed = 0.85F; }
//		if(tt > 70) { this.myType = 4; this.myItem = defaultHeldItem4; this.attackStrength = 6; //TODO Attribute moveSpeed = 0.95F; }
//		if(tt > 80) { this.myType = 5; this.myItem = defaultHeldItem5; this.attackStrength = 8; //TODO Attribute moveSpeed = 1F; }
//		if(tt > 90) { this.myType = 6; this.myItem = defaultHeldItem6; this.attackStrength = 1; }
//		if(tt > 95) { this.myType = 7; this.myItem = defaultHeldItem7; this.attackStrength = 1; }
	}

//	@Override
//	public int getMaxHealth() { return 20; }

	@Override
	protected String getHurtSound()
	{
		return "random.hhurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "random.hhurt";
	}


	@Override
	protected void dropFewItems(boolean flag, int i)
	{
		//TODO
//		final int kk = this.getDropItemId();
//
//		if(kk > 0)
//		{
//			this.dropItem(kk, 1);
//		}

		//TODO
//		final Random mn = new Random();
//		if(mn.nextInt(100) < 17) { this.dropItem(ModItems.itemSkull,1); }
//		if(mn.nextInt(100) < 17) { this.dropItem(SQR.brain,1); }
//		if(mn.nextInt(100) < 17) { this.dropItem(SQR.heart,1); }

	}

	@Override
	protected boolean canDespawn()
	{
		return true;
	}

	@Override
	protected Entity findPlayerToAttack()
	{
		//TODO
//		if (spiderfriend)
//		{
//			return SQR.getClosestEntityToEntity(this.worldObj, this, 16D, 15);
//		}

		final EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16D);
		if (entityplayer != null && this.canEntityBeSeen(entityplayer))
		{
			if(SQR.HumanLike < 10)
			{
				return entityplayer;
			}
			
			else
			{
				return null; //mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 10);//entityplayer;
			}
		} 
		
		else
		{
			return null; //mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 10);//entityplayer;
		}
	}

	@Override
	protected void attackEntity(Entity entity, float f)
	{

		if(this.myType == 2)
		{
			if(f < 10F)
			{
				final double d = entity.posX - this.posX;
				final double d1 = entity.posZ - this.posZ;
				if(this.attackTime == 0)
				{
					final EntityArrow entityarrow = new EntityArrow(this.worldObj, this, 1);
					entityarrow.posY += 1.3999999761581421D;
					final double d2 = entity.posY + entity.getEyeHeight() - 0.20000000298023224D - entityarrow.posY;
					final float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
					this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
					this.worldObj.spawnEntityInWorld(entityarrow);
					entityarrow.setThrowableHeading(d, d2 + f1, d1, 0.6F, 12F);
					this.attackTime = 30;
				}
				this.rotationYaw = (float)(Math.atan2(d1, d) * 180D / 3.1415927410125732D) - 90F;
				this.hasAttacked = true;
			}
			return;
		}

		if(this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.attackStrength);
		}

	}

	@Override
	public boolean getCanSpawnHere()
	{
		final int x = MathHelper.floor_double(this.posX);
		final int y = MathHelper.floor_double(this.boundingBox.minY);
		final int z = MathHelper.floor_double(this.posZ);
		return this.worldObj.getBlock(x, y - 1, z) == Blocks.grass && super.getCanSpawnHere();
	}

	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		
		if (entity instanceof EntityPlayer & this.entityToAttack == null)
		{
			//TODO
			//SQR.likeChange("human",-1);
		}
		
		this.entityToAttack = entity;

		if(entity != null) 
		{ 
			//TODO
			//SQR.pissOffHumans(this.worldObj, entity, this.posX, this.posY, this.posZ, 64F); 	
		}

		return super.attackEntityFrom(damagesource, i);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		// SNIP TODO
//		if(this.entityToAttack != null)
//		{
//			if(spiderfriend)
//			{
//				if(this.entityToAttack instanceof EntityPlayer) { this.entityToAttack = null; }
//				if(this.entityToAttack instanceof EntityLivingBase)
//				{
//					final EntityLivingBase eta = (EntityLivingBase)this.entityToAttack;
//					if(eta.spiderfriend) { this.entityToAttack = null; }
//				}
//			}
//		}
	}
//
//	protected int getDropItemId()
//	{
//		if(myItems.getItem() == Items.bow) { return Items.arrow; }
//		return myItems.getItem();
//	}


	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();
		if(this.entityToAttack != null)
		{
			this.faceEntity(this.entityToAttack, 30F, 30F);
//			this.moveForward = moveSpeed; //TODO
		}
	}


	@Override
	protected String getLivingSound()
	{
		return null;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return this.myItem;
	}

//	private static final ItemStack defaultHeldItem1;
//	private static final ItemStack defaultHeldItem2;
//	private static final ItemStack defaultHeldItem3;
//	private static final ItemStack defaultHeldItem4;
//	private static final ItemStack defaultHeldItem5;
//	private static final ItemStack defaultHeldItem6;
//	private static final ItemStack defaultHeldItem7;
	private ItemStack myItem;

	private int myType = -1;
	private int attackStrength;

//	@Override
//	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
//	{
//		nbttagcompound.setShort("myType", (short)this.myType);
//	}
//
//	@Override
//	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
//	{
//		this.myType = nbttagcompound.getShort("myType");
//
//		this.myItem = defaultHeldItem1;
//		this.attackStrength = 3;
//		if(this.myType == 2) { this.myItem = defaultHeldItem2; this.attackStrength = 5; }
//		if(this.myType == 3) { this.myItem = defaultHeldItem3; this.attackStrength = 5; //TODO Attribute moveSpeed = 0.85F; }
//		if(this.myType == 4) { this.myItem = defaultHeldItem4; this.attackStrength = 6; //TODO Attribute moveSpeed = 0.95F; }
//		if(this.myType == 5) { this.myItem = defaultHeldItem5; this.attackStrength = 2; //TODO Attribute moveSpeed = 1F; }
//		if(this.myType == 6) { this.myItem = defaultHeldItem6; this.attackStrength = 1; }
//		if(this.myType == 7) { this.myItem = defaultHeldItem7; this.attackStrength = 1; }
//
//	}
//
//	static
//	{
//		defaultHeldItem1 = new ItemStack(Items.swordStone, 1);
//		defaultHeldItem2 = new ItemStack(Items.bow, 1);
//		defaultHeldItem3 = new ItemStack(Items.pickaxeWood, 1);
//		defaultHeldItem4 = new ItemStack(Items.ingotIron, 1);
//		defaultHeldItem5 = new ItemStack(Items.stick, 1);
//		defaultHeldItem6 = new ItemStack(Blocks.torchWood, 1);
//		defaultHeldItem7 = new ItemStack(Items.cake, 1);
//	}
}
