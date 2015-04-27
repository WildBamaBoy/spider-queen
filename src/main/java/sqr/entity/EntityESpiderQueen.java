

package sqr.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sqr.core.minecraft.ModBlocks;
import sqr.core.minecraft.ModItems;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, Entity, MathHelper,
//            Item, NBTTagCompound

public class EntityESpiderQueen extends EntityCreature
{

	public EntityESpiderQueen(World world)
	{
		super(world);
		//TODO texture ="/imgz/rivalskin.png";
		this.setSize(1.4F, 0.9F);
		//TODO Attribute moveSpeed = 1.1F;
		this.attackStrength = 6;
		//		health = 140;
		this.backoff = 0;
		this.setAttackMode(0);
		final Random rm = new Random();
		this.texID = rm.nextInt(3);
		this.nospecial = false;
		this.setFriendly(false);
	}

	//TODO Attribute
	//	@Override
	//	public int getMaxHealth() { return 140; }


	@Override
	protected boolean canDespawn()
	{
		if(this.friendly) { return false; }
		return true;
	}

	public void setFriendly(boolean f)
	{
		this.friendly = f;
		if(this.friendly)
		{
			//TODO texture ="/imgz/spiderqueenskin2.png";
			//			if(this.texID == 1) { //TODO texture ="/imgz/spiderqueenskin3.png"; }
			//			if(this.texID == 2) { //TODO texture ="/imgz/spiderqueenskin4.png"; }
		}

		else
		{
			//TODO texture ="/imgz/rivalskin.png";
		}
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

	//TODO Attacking AI here
	//	@Override
	//	protected Entity findPlayerToAttack()
	//	{
	//		if(this.rand.nextInt(15) != 1) { return null; }
	//		Entity nn = null;
	//		if(this.friendly)
	//		{
	//			nn = SQR.getClosestEntityToEntity(this.worldObj, this, 32D, 15);
	//		}
	//		else
	//		{
	//			nn = SQR.getClosestEntityToEntity(this.worldObj, this, 32D, 14);
	//			if(nn != null) { return nn; }
	//			return this.worldObj.getClosestPlayerToEntity(this, 64D);
	//		}
	//
	//		return nn;
	//	}

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
	protected void attackEntity(Entity entity, float f)
	{
		if(this.attackMode == 0) // SWORD
		{
			if(this.attackTime <= 0 && f < 3.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
			{
				this.attackTime = 12;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.attackStrength);
				this.backoff = 40;
			}
			return;
		}

		if(this.attackMode == 1) // BOW
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
					entityarrow.setThrowableHeading(d, d2 + f1, d1, 0.75F, 12F);
					this.attackTime = 30;
					this.backoff = 5;
				}
				this.rotationYaw = (float)(Math.atan2(d1, d) * 180D / 3.1415927410125732D) - 90F;
				this.hasAttacked = true;
			}
			return;
		}

		if(this.attackMode == 2) // LAVA
		{
			if(!this.friendly)
			{
				for(int eo = 0; eo < 4; eo++)
				{
					final EntitySpider entCocoon = new EntitySpider(this.worldObj);
					entCocoon.posX = this.posX;
					entCocoon.posY = this.posY;
					entCocoon.posZ = this.posZ;
					entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
					this.worldObj.spawnEntityInWorld(entCocoon);
				}
			}

			this.backoff = 75;

			this.setAttackMode(0);
			return;
		}

		if(this.attackMode == 3) // TNT
		{
			if(f < 3F)
			{
				if(!this.friendly)
				{
					final EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(this.worldObj);
					entitytntprimed.setPosition(this.posX, this.posY + 1, this.posZ);
					this.worldObj.spawnEntityInWorld(entitytntprimed);
					this.worldObj.playSoundAtEntity(entitytntprimed, "random.fuse", 1.0F, 1.0F);
				}
				this.backoff = 75;

				this.setAttackMode(0);
				return;
			}
		}

	}


	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.fallDistance = 0.0F;


		if(this.backoff > 0) { this.backoff--; }
		final Random rm = new Random();
		if(rm.nextInt(150) == 0)
		{
			this.setAttackMode(rm.nextInt(2));
			if(rm.nextInt(25) == 0 & !this.nospecial & getHealth() < 40)
			{
				this.nospecial = true;
				this.setAttackMode(rm.nextInt(2) + 2);
			}
		}

	}


	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		final int j = 3;
		for(int k = 0; k < j; k++)
		{
			this.dropItem(ModItems.itemSpiderEgg, 1);
			this.dropItem(ModItems.itemWeb, 1);
		}
		
		this.dropItem(Item.getItemFromBlock(ModBlocks.royalblood), 1);
		this.dropItem(Items.saddle, 1);
	}

	//TODO AI, no longer relevant
	//	@Override
	//	protected void updateEntityActionState()
	//	{
	//		this.entityAge++;
	//		if(!this.friendly & this.entityAge > 1600 & this.rand.nextInt(800) == 0)
	//		{
	//			this.setDead();
	//		}
	//		super.updateEntityActionState();
	//
	//		if(this.isCollidedHorizontally && !this.hasPath())
	//		{
	//			this.isJumping = true;
	//		}
	//
	//		if(this.friendly)
	//		{
	//			spiderfriend = true;
	//			spiderBaitCode();
	//		}
	//
	//		// SPIDER FOLLOW CODE vvvv
	//		/*
	//		if(entityToAttack == null & friendly)
	//		{
	//			if(getAte() > 0)
	//			{ }
	//			else
	//			{
	//				if(fPlay == null) { fPlay = (TileEntity) mod_SpiderQueen.getClosestBait(worldObj, this, posX, posY, posZ, 64D); }
	//				if(fPlay != null)
	//				{
	//					if(mod_SpiderQueen.getDistance3d(posX,posY,posZ,fPlay.xCoord,fPlay.yCoord,fPlay.zCoord) > 16D)
	//					{ moveForward = moveSpeed; }
	//
	//					mod_SpiderQueen.faceTEntity(this,fPlay, 30F);
	//					Random rm = new Random();
	//					if(rm.nextInt(50) == 0) { fPlay = null; }
	//				}
	//			}
	//
	//			if(friendly)
	//			{
	//				EntityPlayer epl = worldObj.getClosestPlayer(posX, posY, posZ, 32D);
	//				if(epl != null)
	//				{
	//					if(epl.getCurrentEquippedItem() != null)
	//					{
	//						if(epl.getCurrentEquippedItem().getItem() != null)
	//						{
	//							if(epl.getCurrentEquippedItem().getItem() == mod_SpiderQueen.spiderbait)
	//							{
	//								if(mod_SpiderQueen.getDistance3d(posX, posY, posZ, epl.posX, epl.posY, epl.posZ) > 16F)
	//								{
	//									setAte(10);
	//									fPlay = null;
	//									moveForward = moveSpeed;
	//									faceEntity(epl, 30F, 30F);
	//								}
	//							}
	//						}
	//					}
	//				}
	//			}
	//		}*/
	//
	//		if(this.backoff > 0)
	//		{
	//			this.moveForward = -moveSpeed;
	//		}
	//	}
	//
	//	/*    protected void attackEntity(Entity entity, float f)
	//    {
	//        float f1 = getBrightness(1.0F);
	//        if(f1 > 0.5F && rand.nextInt(100) == 0)
	//        {
	//            entityToAttack = null;
	//            return;
	//        }
	//        if(f > 2.0F && f < 6F && rand.nextInt(10) == 0)
	//        {
	//            if(onGround)
	//            {
	//                double d = entity.posX - posX;
	//                double d1 = entity.posZ - posZ;
	//                float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
	//                motionX = (d / (double)f2) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
	//                motionZ = (d1 / (double)f2) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
	//                motionY = 0.40000000596046448D;
	//            }
	//        } else
	//        {
	//            super.attackEntity(entity, f);
	//        }
	//    }
	//	 */

	@Override
	public boolean interact(EntityPlayer entityplayer)
	{
		final ItemStack itemstack = entityplayer.inventory.getCurrentItem();

		if(itemstack != null && itemstack.getItem() == Items.porkchop)
		{
			itemstack.stackSize--;
			if(itemstack.stackSize <= 0)
			{
				entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
			}
			if(!this.worldObj.isRemote)
			{
				//TODO Attribute
				//				health = health + 10;
				//				if(health > 140) { health = 140; }
				this.showHeartsOrSmokeFX(true);
			}
			return true;
		}


		return false;
	}

	void showHeartsOrSmokeFX(boolean flag)
	{
		String s = "heart";
		if(!flag)
		{
			s = "explode";
		}
		for(int i = 0; i < 7; i++)
		{
			final double d = this.rand.nextGaussian() * 0.02D;
			final double d1 = this.rand.nextGaussian() * 0.02D;
			final double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle(s, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d, d1, d2);
		}

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("friendly", this.friendly);
		nbttagcompound.setShort("tid", (short)this.texID);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		this.texID = nbttagcompound.getShort("tid");
		this.setFriendly(nbttagcompound.getBoolean("friendly"));
	}

	public boolean getFriendly() { return this.friendly; }
	private boolean friendly;

	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		final Entity entity = damagesource.getEntity();
		this.backoff = 40;

		if(!this.friendly)
		{
			this.entityToAttack = entity;
		}
		else
		{
			if(entity instanceof EntityPlayer || entity instanceof EntitySpiderEx)
			{
			}
			else
			{
				this.entityToAttack = entity;
			}
		}
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
		return super.attackEntityFrom(damagesource, i);
	}

	@Override
	public boolean isOnLadder()
	{
		return this.isCollidedHorizontally;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return this.myItem;
	}

	public void setAttackMode(int i)
	{
		this.attackMode = i;
		if(i == 0) { this.myItem = defaultHeldItem; }
		if(i == 1) { this.myItem = defaultHeldItem1; }
		if(i == 2) { this.myItem = defaultHeldItem2; }
		if(i == 3) { this.myItem = defaultHeldItem3; }
	}

	private static final ItemStack defaultHeldItem;
	private static final ItemStack defaultHeldItem1;
	private static final ItemStack defaultHeldItem2;
	private static final ItemStack defaultHeldItem3;
	private ItemStack myItem;

	static
	{
		defaultHeldItem = new ItemStack(Items.stone_sword, 1);
		defaultHeldItem1 = new ItemStack(Items.bow, 1);
		defaultHeldItem2 = new ItemStack(Items.lava_bucket, 1);
		defaultHeldItem3 = new ItemStack(ModItems.itemSpiderEgg, 1);
	}

	private int backoff;
	private int attackMode;
	private boolean lavastart;
	private boolean nospecial;
	private int lavatick;
	private int lavax;
	private int lavay;
	private int lavaz;
	private final int attackStrength;
	private int texID;
	private TileEntity fPlay;
}
