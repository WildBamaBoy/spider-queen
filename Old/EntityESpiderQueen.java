// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import java.util.Random;

import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, Entity, MathHelper, 
//            Item, NBTTagCompound

public class EntityESpiderQueen extends EntityNewAnimal
{

    public EntityESpiderQueen(World world)
    {
        super(world);
        texture = "/imgz/rivalskin.png";
        setSize(1.4F, 0.9F);
        moveSpeed = 1.1F;
        attackStrength = 6;
        health = 140;
		backoff = 0;
		setAttackMode(0);
		Random rm = new Random();
		texID = rm.nextInt(3);
		nospecial = false;
		setFriendly(false);
    }
	
	public int getMaxHealth() { return 140; }

	
	protected boolean canDespawn()
    {
		if(friendly) { return false; }
        return true;
    }
	
	public void setFriendly(boolean f)
	{
		friendly = f;
		if(friendly)
		{
			texture = "/imgz/spiderqueenskin2.png";
			if(texID == 1) { texture = "/imgz/spiderqueenskin3.png"; }
			if(texID == 2) { texture = "/imgz/spiderqueenskin4.png"; }
		}
		else
		{
			texture = "/imgz/rivalskin.png";
		}
	}
	
	
    public double getMountedYOffset()
    {
        return (double)height * 0.75D - 0.5D;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

	protected Entity findPlayerToAttack()
    {
		if(rand.nextInt(15) != 1) { return null; }
		Entity nn = null;
		if(friendly)
		{
			nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 15);
		}
		else
		{
			nn = mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 14);
			if(nn != null) { return nn; }
			return worldObj.getClosestPlayerToEntity(this, 64D);
		}
		
		return nn;
    }
	
    protected String getLivingSound()
    {
        return "mob.spider";
    }

    protected String getHurtSound()
    {
        return "mob.spider";
    }

    protected String getDeathSound()
    {
        return "mob.spiderdeath";
    }

	
    protected void attackEntity(Entity entity, float f)
    {
		if(attackMode == 0) // SWORD
		{
			if(attackTime <= 0 && f < 3.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			{
				attackTime = 12;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackStrength);
				backoff = 40;
			}
			return;
		}

		if(attackMode == 1) // BOW
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
					entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 0.75F, 12F);
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
					EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldObj, (float)posX, (float)posY + 1, (float)posZ);
					worldObj.spawnEntityInWorld(entitytntprimed);
					worldObj.playSoundAtEntity(entitytntprimed, "random.fuse", 1.0F, 1.0F);
				}
				backoff = 75;
					
				setAttackMode(0);
				return;
			}
		}
		
    }
	
	
    public void onLivingUpdate()
    {
		super.onLivingUpdate();
		fallDistance = 0.0F;
		
		
		if(backoff > 0) { backoff--; }
		Random rm = new Random();
		if(rm.nextInt(150) == 0)
		{
			setAttackMode(rm.nextInt(2));
			if(rm.nextInt(25) == 0 & !nospecial & health < 40)
			{
				nospecial = true;
				setAttackMode(rm.nextInt(2) + 2);
			}
		}
		
	}
		
	
    protected void dropFewItems(boolean par1, int par2)
    {
        int i = getDropItemId();
        if(i > 0)
        {
            int j = 3;
            for(int k = 0; k < j; k++)
            {
                dropItem(mod_SpiderQueen.itemSpiderEgg.shiftedIndex, 1);
				dropItem(mod_SpiderQueen.itemWeb.shiftedIndex, 1);
            }
			dropItem(mod_SpiderQueen.royalblood.blockID, 1);				
			dropItem(Item.saddle.shiftedIndex, 1);				

        }
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
		
		if(friendly)
		{
			spiderfriend = true;
			spiderBaitCode();
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
		
		if(backoff > 0)
		{
			moveForward = -moveSpeed;
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
      
            if(itemstack != null && itemstack.itemID == Item.porkRaw.shiftedIndex)
            {
                itemstack.stackSize--;
                if(itemstack.stackSize <= 0)
                {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                }
                if(!worldObj.isRemote)
                {
                    health = health + 10;
					if(health > 140) { health = 140; }
                    showHeartsOrSmokeFX(true);
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
            double d = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            worldObj.spawnParticle(s, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
        }

    }
	
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("friendly", friendly);
		nbttagcompound.setShort("tid", (short)texID);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
		texID = (int)nbttagcompound.getShort("tid");
        setFriendly(nbttagcompound.getBoolean("friendly"));
    }
		
	public boolean getFriendly() { return friendly; }
	private boolean friendly;
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		backoff = 40;
		
		if(!friendly)
		{
			entityToAttack = entity;
		}
		else
		{
			if(entity instanceof EntityPlayer || entity instanceof EntityFriendlySpider)
			{
			}
			else
			{
				entityToAttack = entity;
			}
		}
		//mod_SpiderQueen.pissOffBees(worldObj, entity, posX, posY, posZ, 64F);
        return super.attackEntityFrom(damagesource, i);
    }
	
    protected int getDropItemId()
    {
        return Item.silk.shiftedIndex;
    }

    public boolean isOnLadder()
    {
        return isCollidedHorizontally;
    }

	public ItemStack getHeldItem()
    {
        return myItem;
    }

	public void setAttackMode(int i)
	{
		attackMode = i;
		if(i == 0) { myItem = defaultHeldItem; }
		if(i == 1) { myItem = defaultHeldItem1; }
		if(i == 2) { myItem = defaultHeldItem2; }
		if(i == 3) { myItem = defaultHeldItem3; }
	}
	
    private static final ItemStack defaultHeldItem;
    private static final ItemStack defaultHeldItem1;
    private static final ItemStack defaultHeldItem2;
    private static final ItemStack defaultHeldItem3;
	private ItemStack myItem;
	
    static 
    {
        defaultHeldItem = new ItemStack(Item.swordStone, 1);
        defaultHeldItem1 = new ItemStack(Item.bow, 1);
        defaultHeldItem2 = new ItemStack(Item.bucketLava, 1);
        defaultHeldItem3 = new ItemStack(mod_SpiderQueen.itemSpiderEgg, 1);
	}
	
	private int backoff;
	private int attackMode;
	private boolean lavastart;
	private boolean nospecial;
	private int lavatick;
	private int lavax;
	private int lavay;
	private int lavaz;
	private int attackStrength;
	private int texID;
	private TileEntity fPlay;
}
