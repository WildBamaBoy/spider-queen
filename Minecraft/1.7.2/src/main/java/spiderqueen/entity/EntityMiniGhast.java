package spiderqueen.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.radixshock.radixcore.logic.NBTHelper;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityMiniGhast extends EntityCreature
{
	public String owner = "";

	public EntityMiniGhast(World world)
	{
		super(world);
		this.setSize(2.5F, 2.9F);
		this.isImmuneToFire = true;
		this.experienceValue = 5;
		
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}
	
	public EntityMiniGhast(World world, String owner)
	{
		this(world);
		this.owner = owner;
	}

	@SideOnly(Side.CLIENT)
	public boolean func_110182_bF()
	{
		return this.dataWatcher.getWatchableObjectByte(16) != 0;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(3.8D);
	}

	@Override
	protected void updateEntityActionState()
	{
		return;
	}
	@Override
	public void setInWeb()
	{
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}
	protected String getLivingSound()
	{
		return "mob.ghast.moan";
	}

	protected String getHurtSound()
	{
		return "mob.ghast.scream";
	}

	protected String getDeathSound()
	{
		return "mob.ghast.death";
	}

	protected Item getDropItem()
	{
		return Items.gunpowder;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);
		int k;

		for (k = 0; k < j; ++k)
		{
			this.dropItem(Items.ghast_tear, 1);
		}

		j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);

		for (k = 0; k < j; ++k)
		{
			this.dropItem(Items.gunpowder, 1);
		}
	}

	protected float getSoundVolume()
	{
		return 3.0F;
	}

	public boolean getCanSpawnHere()
	{
		return this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
	}

	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		NBTHelper.autoWriteEntityToNBT(this, nbt);
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		NBTHelper.autoReadEntityFromNBT(this, nbt);
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (owner.equals(entityPlayer.getCommandSenderName()))
		{
			entityPlayer.rotationYaw = rotationYaw;
			entityPlayer.rotationPitch = rotationPitch;

			if (!worldObj.isRemote)
			{
				entityPlayer.mountEntity(this);
			}
		}

		return true;
	}

	@Override
	public void moveEntityWithHeading(float moveStrafe, float moveForward)
	{
		if (riddenByEntity != null)
		{
			prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;
			rotationPitch = riddenByEntity.rotationPitch * 0.5F;
			setRotation(rotationYaw, rotationPitch);
			rotationYawHead = renderYawOffset = rotationYaw;

			moveStrafe = ((EntityLivingBase) riddenByEntity).moveStrafing * 0.5F / 5;
			moveForward = ((EntityLivingBase) riddenByEntity).moveForward * 0.5F / 5;				

			if (ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, (EntityLivingBase)riddenByEntity, 41))
			{
				motionY = 0.4F;
			}
			
			if (moveForward <= 0.0F)
			{
				moveForward *= 0.25F;
			}

			stepHeight = 1.0F;
			jumpMovementFactor = getAIMoveSpeed() * 0.1F;

			if (!worldObj.isRemote)
			{
				setAIMoveSpeed((float) getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				super.moveEntityWithHeading(moveStrafe, moveForward);
			}

			prevLimbSwingAmount = limbSwingAmount;
			final double d1 = posX - prevPosX;
			final double d0 = posZ - prevPosZ;
			float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

			if (f4 > 1.0F)
			{
				f4 = 1.0F;
			}

			limbSwingAmount += (f4 - limbSwingAmount) * 0.4F;
			limbSwing += limbSwingAmount;
		}
		else
		{
			stepHeight = 0.5F;
			jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(moveStrafe, moveForward);
		}
	}
	
    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1) {}

    /**
     * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
     * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
     */
    protected void updateFallState(double par1, boolean par3) {}

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    public boolean isOnLadder()
    {
        return false;
    }
}