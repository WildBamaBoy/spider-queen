// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.entity;

import java.util.Random;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.old.core.INewMob;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, Item, MathHelper

public class EntityHuman extends EntityNewAnimal
    implements INewMob
{

    public EntityHuman(World world)
    {
        super(world);
        texture = "/mob/char.png";
        moveSpeed = 0.75F;
        health = 20;
		
		if(myType > -1) { return; }
		myType = 1;
		Random rr = new Random();
		int tt = rr.nextInt(100);
		myItem = defaultHeldItem1;
		attackStrength = 3;
		if(tt > 25) { myType = 2; myItem = defaultHeldItem2; attackStrength = 5; }
		if(tt > 50) { myType = 3; myItem = defaultHeldItem3; attackStrength = 5; moveSpeed = 0.85F; }
		if(tt > 70) { myType = 4; myItem = defaultHeldItem4; attackStrength = 6; moveSpeed = 0.95F; }
		if(tt > 80) { myType = 5; myItem = defaultHeldItem5; attackStrength = 8; moveSpeed = 1F; }
		if(tt > 90) { myType = 6; myItem = defaultHeldItem6; attackStrength = 1; }
		if(tt > 95) { myType = 7; myItem = defaultHeldItem7; attackStrength = 1; }
		
    }

	public int getMaxHealth() { return 20; }
	
    protected String getHurtSound()
    {
        return "random.hhurt";
    }

    protected String getDeathSound()
    {
        return "random.hhurt";
    }
	
	
    protected void dropFewItems(boolean flag, int i)
    {
        int kk = getDropItemId();
        if(kk > 0)
        {
            dropItem(kk, 1);
        }
		
		Random mn = new Random();
		if(mn.nextInt(100) < 17) { dropItem(mod_SpiderQueen.itemSkull.shiftedIndex,1); }
		if(mn.nextInt(100) < 17) { dropItem(mod_SpiderQueen.brain.blockID,1); }
		if(mn.nextInt(100) < 17) { dropItem(mod_SpiderQueen.heart.blockID,1); }
		
	}	
	
	protected boolean canDespawn()
    {
        return true;
    }
	
	protected Entity findPlayerToAttack()
    {
		if(spiderfriend)
		{
			return mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 16D, 15);
		}
		
        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 16D);
        if(entityplayer != null && canEntityBeSeen(entityplayer))
        {
			if(mod_SpiderQueen.HumanLike < 10)
			{
				return entityplayer;
			}
			else
			{
				return null;//mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 10);//entityplayer;
			}
        } else
        {
            return null;//mod_SpiderQueen.getClosestEntityToEntity(worldObj, this, 32D, 10);//entityplayer;
        }
    }
	
	protected void attackEntity(Entity entity, float f)
    {
		
		if(myType == 2)
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
					entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 0.6F, 12F);
					attackTime = 30;
				}
				rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
				hasAttacked = true;
			}
			return;
		}
		
			if(attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			{
				attackTime = 20;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackStrength);
			}
		
    }
	
	public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);
        return worldObj.getBlockId(i, j - 1, k) == Block.grass.blockID && super.getCanSpawnHere();
    }
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
		Entity entity = damagesource.getEntity();
		if(entity instanceof EntityPlayer & entityToAttack == null)
		{
			mod_SpiderQueen.likeChange("human",-1);
		}
		entityToAttack = entity;
		if(entity != null) { mod_SpiderQueen.pissOffHumans(worldObj, entity, posX, posY, posZ, 64F); }		
		
        return super.attackEntityFrom(damagesource, i);
    }
	
    public void onLivingUpdate()
    {
		super.onLivingUpdate();
		// SNIP
		if(entityToAttack != null)
		{
			if(spiderfriend)
			{
				if(entityToAttack instanceof EntityPlayer) { entityToAttack = null; }
				if(entityToAttack instanceof EntityLiving)
				{
					EntityLiving eta = (EntityLiving)entityToAttack;
					if(eta.spiderfriend) { entityToAttack = null; }
				}
			}
		}
	}
	
	protected int getDropItemId()
    {
		if(myItem.getItem().shiftedIndex == Item.bow.shiftedIndex) { return Item.arrow.shiftedIndex; }
		return myItem.getItem().shiftedIndex;
    }
	
	
	protected void updateEntityActionState()
    {
		super.updateEntityActionState();
		if(entityToAttack != null)
		{
			faceEntity(entityToAttack, 30F, 30F);
			moveForward = moveSpeed;
		}
	}
	
	
    protected String getLivingSound()
    {
        return null;
    }

	public ItemStack getHeldItem()
    {
        return myItem;
    }

    private static final ItemStack defaultHeldItem1;
    private static final ItemStack defaultHeldItem2;
    private static final ItemStack defaultHeldItem3;
    private static final ItemStack defaultHeldItem4;
    private static final ItemStack defaultHeldItem5;
    private static final ItemStack defaultHeldItem6;
    private static final ItemStack defaultHeldItem7;
	private ItemStack myItem;
	
	private int myType = -1;
	private int attackStrength;

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("myType", (short)myType);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        myType = nbttagcompound.getShort("myType");
		
		myItem = defaultHeldItem1;
		attackStrength = 3;
		if(myType == 2) { myItem = defaultHeldItem2; attackStrength = 5; }
		if(myType == 3) { myItem = defaultHeldItem3; attackStrength = 5; moveSpeed = 0.85F; }
		if(myType == 4) { myItem = defaultHeldItem4; attackStrength = 6; moveSpeed = 0.95F; }
		if(myType == 5) { myItem = defaultHeldItem5; attackStrength = 2; moveSpeed = 1F; }
		if(myType == 6) { myItem = defaultHeldItem6; attackStrength = 1; }
		if(myType == 7) { myItem = defaultHeldItem7; attackStrength = 1; }
		
    }
	
    static 
    {
        defaultHeldItem1 = new ItemStack(Item.swordStone, 1);
        defaultHeldItem2 = new ItemStack(Item.bow, 1);
        defaultHeldItem3 = new ItemStack(Item.pickaxeWood, 1);
        defaultHeldItem4 = new ItemStack(Item.ingotIron, 1);
        defaultHeldItem5 = new ItemStack(Item.stick, 1);
        defaultHeldItem6 = new ItemStack(Block.torchWood, 1);
        defaultHeldItem7 = new ItemStack(Item.cake, 1);
    }
}
