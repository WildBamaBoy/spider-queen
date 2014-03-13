package spiderqueen.entity;

import io.netty.buffer.ByteBuf;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.enums.EnumCocoonType;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityCocoon extends EntityCreature implements IEntityAdditionalSpawnData
{
	private EnumCocoonType cocoonType;
	private boolean isEaten;
	private int currentDamage;
	private int timeSinceHit;
	private int rockDirection = 1;

	public EntityCocoon(World world)
	{
		super(world);
		this.setSize(1F, 1F);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	public EntityCocoon(World world, EnumCocoonType cocoonType) 
	{
		super(world);
		this.cocoonType = cocoonType;
	}

	@Override
	protected void entityInit() 
	{
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
	}

	@Override
	protected boolean isMovementCeased()
	{
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entity)
	{
		return entity.boundingBox;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		if (timeSinceHit > 0)
		{
			timeSinceHit--;
		}

		if (currentDamage > 0)
		{
			final Random rand = new Random();

			rotationPitch += rand.nextFloat();
			rotationPitch -= rand.nextFloat();
			currentDamage--;
		}

	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		final Entity entity = damageSource.getEntity();

		timeSinceHit = 10;
		currentDamage += damage * 10;

		setBeenAttacked();

		if (currentDamage > 80)
		{
			if (isEaten())
			{
				worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
			}

			if (!worldObj.isRemote)
			{
				dropItem(cocoonType.getCocoonItem(), 1);
				setDead();
			}
		}

		return true;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		try
		{
			cocoonType = (EnumCocoonType)EnumCocoonType.class.getFields()[nbt.getInteger("cocoonType")].get(EnumCocoonType.class);
		}

		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

		isEaten = nbt.getBoolean("isEaten");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		nbt.setInteger("cocoonType", cocoonType.ordinal());
		nbt.setBoolean("isEaten", isEaten);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeInt(cocoonType.ordinal());
		buffer.writeBoolean(isEaten);
	}

	@Override
	public void readSpawnData(ByteBuf buffer)
	{
		try
		{
			cocoonType = (EnumCocoonType)EnumCocoonType.class.getFields()[buffer.readInt()].get(EnumCocoonType.class);
		}

		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

		isEaten = buffer.readBoolean();
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if(!isEaten) 
		{
			entityPlayer.heal(3);
			entityPlayer.getFoodStats().addStats(4, 0.4f);

			worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
			worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
			isEaten = true;

			if (!worldObj.isRemote)
			{
				entityDropItem(new ItemStack(SpiderQueen.getInstance().itemWeb, 5, 0), 0);
			}
		}

		return true;
	}

	public EnumCocoonType getCocoonType()
	{
		return cocoonType;
	}

	public boolean isEaten() 
	{
		return isEaten;
	}

	public void setEaten(boolean isEaten) 
	{
		this.isEaten = isEaten;
	}

	public int getTimeSinceHit()
	{
		return timeSinceHit;
	}

	public int getCurrentDamage()
	{
		return currentDamage;
	}
}
