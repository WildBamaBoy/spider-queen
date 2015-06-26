package sq.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
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
import sq.client.render.RenderSpiderQueen;
import sq.core.minecraft.ModItems;

public class EntitySpiderQueen extends EntityCreature implements IWebClimber
{
	private int backoff;
	private int attackMode;
	private boolean lavastart;
	private boolean nospecial;
	private int lavatick;
	private int lavax;
	private int lavay;
	private int lavaz;
	private int attackStrength;
	private int textureId;
	private boolean friendly;
	private TileEntity fPlay;

	private ItemStack myItem;
	private static final ItemStack defaultHeldItem = new ItemStack(Items.stone_sword, 1);
	private static final ItemStack defaultHeldItem1 = new ItemStack(Items.bow, 1);
	private static final ItemStack defaultHeldItem2 = new ItemStack(Items.lava_bucket, 1);
	private static final ItemStack defaultHeldItem3 = new ItemStack(ModItems.spiderEgg, 1);

	public EntitySpiderQueen(World world)
	{
		super(world);
		setSize(1.4F, 1.9F);
		attackStrength = 6;
		backoff = 0;
		setAttackMode(0);
		textureId = world.rand.nextInt(RenderSpiderQueen.spiderQueenTextures.length);
		nospecial = false;
		setFriendly(false);
	}

	protected final void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.1F);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(140.0F);
	}

	public void setFriendly(boolean f)
	{
		friendly = f;

		if (friendly)
		{
			
		}

		else
		{

		}
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		fallDistance = 0.0F;

		if (backoff > 0) 
		{ 
			backoff--; 
		}

		if (worldObj.rand.nextInt(150) == 0)
		{
			setAttackMode(worldObj.rand.nextInt(2));

			if (worldObj.rand.nextInt(25) == 0 & !nospecial && getHealth() < 40)
			{
				nospecial = true;
				setAttackMode(worldObj.rand.nextInt(2) + 2);
			}
		}

	}

	/*    protected void attackEntity(Entity entity, float f)
	{
	    float f1 = getBrightness(1.0F);
	    if(f1 > 0.5F && rand.nextInt(100) == 0)
	    {
	        entityToAttack = null;
	        return;
	    }
	    if(f > 2.0F && f < 6F && rand.nextInt(10) == 0)
	    {
	        if(onGround)
	        {
	            double d = entity.posX - posX;
	            double d1 = entity.posZ - posZ;
	            float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
	            motionX = (d / (double)f2) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
	            motionZ = (d1 / (double)f2) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
	            motionY = 0.40000000596046448D;
	        }
	    } else
	    {
	        super.attackEntity(entity, f);
	    }
	}
	 */

	public boolean interact(EntityPlayer entityplayer)
	{
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();

		if(itemstack != null && itemstack.getItem() == Items.porkchop)
		{
			itemstack.stackSize--;
			if (itemstack.stackSize <= 0)
			{
				entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
			}

			if (!worldObj.isRemote)
			{
				setHealth(getHealth() + 10);
				showHeartsOrSmokeFX(true);
			}

			return true;
		}

		return false;
	}

	protected void updateEntityActionState()
	{
		entityAge++;
		if(!friendly & entityAge > 1600 & rand.nextInt(800) == 0)
		{
			setDead();
		}
		super.updateEntityActionState();

		if(isCollidedHorizontally && !hasPath())
		{
			isJumping = true;
		}

		if (friendly)
		{
//			spiderfriend = true;
//			spiderBaitCode();
		}

		// SPIDER FOLLOW CODE vvvv
		/*
	            if(entityToAttack == null & friendly)
	            {
	                    if(getAte() > 0)
	                    { }
	                    else
	                    {
	                            if(fPlay == null) { fPlay = (TileEntity) mod_SpiderQueen.getClosestBait(worldObj, this, posX, posY, posZ, 64D); }
	                            if(fPlay != null)
	                            {
	                                    if(mod_SpiderQueen.getDistance3d(posX,posY,posZ,fPlay.xCoord,fPlay.yCoord,fPlay.zCoord) > 16D)
	                                    { moveForward = moveSpeed; }

	                                    mod_SpiderQueen.faceTEntity(this,fPlay, 30F);
	                                    Random rm = new Random();
	                                    if(rm.nextInt(50) == 0) { fPlay = null; }
	                            }
	                    }

	                    if(friendly)
	                    {
	                            EntityPlayer epl = worldObj.getClosestPlayer(posX, posY, posZ, 32D);
	                            if(epl != null)
	                            {
	                                    if(epl.getCurrentEquippedItem() != null)
	                                    {
	                                            if(epl.getCurrentEquippedItem().getItem() != null)
	                                            {
	                                                    if(epl.getCurrentEquippedItem().getItem().shiftedIndex == mod_SpiderQueen.spiderbait.blockID)
	                                                    {
	                                                            if(mod_SpiderQueen.getDistance3d(posX, posY, posZ, epl.posX, epl.posY, epl.posZ) > 16F)
	                                                            {
	                                                                    setAte(10);
	                                                                    fPlay = null;
	                                                                    moveForward = moveSpeed;
	                                                                    faceEntity(epl, 30F, 30F);
	                                                            }
	                                                    }
	                                            }
	                                    }
	                            }
	                    }
	            }*/

		if (backoff > 0)
		{
			moveForward = -this.getAIMoveSpeed();
		}
	}

	protected Entity findPlayerToAttack()
	{
		if(rand.nextInt(15) != 1) 
		{ 
			return null; 
		}

		Entity nn = null;

//		if (friendly)
//		{
//			nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 15);
//		}
//
//		else
//		{
//			nn = RadixLogic.getClosestEntityToEntity(worldObj, this, 32D, 14);
//			if(nn != null) { return nn; }
			return worldObj.getClosestPlayerToEntity(this, 64D);
//		}

//		return nn;
	}

	public double getMountedYOffset()
	{
		return (double)height * 0.75D - 0.5D;
	}

	protected boolean canDespawn()
	{
		if (friendly) 
		{
			return false; 
		}

		return true;
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}
    
	protected String getLivingSound()
	{
		return "mob.spider.say";
	}

	protected String getHurtSound()
	{
		return "mob.spider.say";
	}

	protected String getDeathSound()
	{
		return "mob.spider.death";
	}

	protected void attackEntity(Entity entity, float f)
	{
		if (attackMode == 0) // SWORD
		{
			if (attackTime <= 0 && f < 3.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			{
				attackTime = 12;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackStrength);
				backoff = 40;
			}
			return;
		}

		if (attackMode == 1) // BOW
		{
			if(f < 10F)
			{
				double d = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				if(attackTime == 0)
				{
					EntityArrow entityarrow = new EntityArrow(worldObj, this, 1);
					entityarrow.posY += 1.3999999761581421D;
					double d2 = (entity.posY + (double)entity.getEyeHeight()) - 0.20000000298023224D - entityarrow.posY;
					float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
					worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
					worldObj.spawnEntityInWorld(entityarrow);
					entityarrow.setThrowableHeading(d, d2 + (double)f1, d1, 0.75F, 12F);
					attackTime = 30;
					backoff = 5;
				}
				rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
				hasAttacked = true;
			}
			return;
		}

		if(attackMode == 2) // LAVA
		{
			if(!friendly)
			{
				for(int eo = 0; eo < 4; eo++)
				{
					EntitySpider entCocoon = new EntitySpider(worldObj);
					entCocoon.posX = posX;
					entCocoon.posY = posY;
					entCocoon.posZ = posZ;
					entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
					worldObj.spawnEntityInWorld(entCocoon);
				}
			}

			backoff = 75;

			setAttackMode(0);
			return;
		}

		if(attackMode == 3) // TNT
		{
			if(f < 3F)
			{
				if(!friendly)
				{
					EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldObj, (float)posX, (float)posY + 1, (float)posZ, this);
					worldObj.spawnEntityInWorld(entitytntprimed);
					worldObj.playSoundAtEntity(entitytntprimed, "random.fuse", 1.0F, 1.0F);
				}
				backoff = 75;

				setAttackMode(0);
				return;
			}
		}

	}


	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float f)
	{
		Entity entity = damagesource.getEntity();
		backoff = 40;

		if(!friendly)
		{
			entityToAttack = entity;
		}

		else
		{
			if(entity instanceof EntityPlayer) //|| entity instanceof EntityFriendlySpider)
			{
			}

			else
			{
				entityToAttack = entity;
			}
		}

		return super.attackEntityFrom(damagesource, f);
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		int j = 3;
		for(int k = 0; k < j; k++)
		{
			dropItem(ModItems.spiderEgg, 1);
			dropItem(ModItems.webNormal, 1);
		}

		dropItem(ModItems.royalBlood, 1);
		dropItem(Items.saddle, 1);
	}

	/*    protected void attackEntity(Entity entity, float f)
    {
        float f1 = getBrightness(1.0F);
        if(f1 > 0.5F && rand.nextInt(100) == 0)
        {
            entityToAttack = null;
            return;
        }
        if(f > 2.0F && f < 6F && rand.nextInt(10) == 0)
        {
            if(onGround)
            {
                double d = entity.posX - posX;
                double d1 = entity.posZ - posZ;
                float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
                motionX = (d / (double)f2) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
                motionZ = (d1 / (double)f2) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
                motionY = 0.40000000596046448D;
            }
        } else
        {
            super.attackEntity(entity, f);
        }
    }
	 */

	public ItemStack getHeldItem()
	{
		return myItem;
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("friendly", friendly);
		nbttagcompound.setShort("tid", (short)textureId);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		textureId = (int)nbttagcompound.getShort("tid");
		setFriendly(nbttagcompound.getBoolean("friendly"));
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
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle(s, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
		}
	}

	public boolean getFriendly() 
	{ 
		return friendly; 
	}

	public void setAttackMode(int i)
	{
		attackMode = i;
		if(i == 0) { myItem = defaultHeldItem; }
		if(i == 1) { myItem = defaultHeldItem1; }
		if(i == 2) { myItem = defaultHeldItem2; }
		if(i == 3) { myItem = defaultHeldItem3; }
	}

	public int getTextureIndex() 
	{
		return textureId;
	}
}
