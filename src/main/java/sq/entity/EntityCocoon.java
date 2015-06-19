package sq.entity;

import io.netty.buffer.ByteBuf;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.minecraft.ModItems;
import sq.enums.EnumCocoonType;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityCocoon extends EntityCreature implements IEntityAdditionalSpawnData
{
	private EnumCocoonType	cocoonType;

	private int currentDamage;
	private int	timeSinceHit;
	public EntityCocoon(World world)
	{
		super(world);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		tasks.addTask(0, new EntityAISwimming(this));
	}

	public EntityCocoon(World world, EnumCocoonType cocoonType)
	{
		this(world);
		this.cocoonType = cocoonType;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(20, 0);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
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
		return boundingBox;
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

		if (cocoonType == EnumCocoonType.ENDERMAN && !isEaten())
		{
			worldObj.spawnParticle("portal", posX + (rand.nextDouble() - 0.5D) * width, posY + 1 + rand.nextDouble() * 0.25D, posZ + rand.nextDouble() - 0.5D * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
		}
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		final Entity entity = damageSource.getEntity();

		if (entity instanceof EntityPlayer)
		{
			timeSinceHit = 10;
			currentDamage += damage * 10;

			setBeenAttacked();

			if (currentDamage > 80)
			{
				if (isEaten())
				{
					worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2 + 1, posZ - motionZ * 2, motionX, motionY, motionZ);
				}

				if (!worldObj.isRemote && !isEaten())
				{
					dropItem(cocoonType.getCocoonItem(), 1);
				}

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
			cocoonType = (EnumCocoonType) EnumCocoonType.class.getFields()[nbt.getInteger("cocoonType")].get(EnumCocoonType.class);
			dataWatcher.updateObject(20, nbt.getInteger("isEaten"));
		}

		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}

		setHitboxSize();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("cocoonType", cocoonType.ordinal());
		nbt.setInteger("isEaten", dataWatcher.getWatchableObjectInt(20));
		setHitboxSize();
	}

	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		buffer.writeInt(cocoonType.ordinal());
		buffer.writeInt(dataWatcher.getWatchableObjectInt(20));
	}

	@Override
	public void readSpawnData(ByteBuf buffer)
	{
		try
		{
			cocoonType = (EnumCocoonType) EnumCocoonType.class.getFields()[buffer.readInt()].get(EnumCocoonType.class);
			dataWatcher.updateObject(20, buffer.readInt());
		}

		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}

		setHitboxSize();
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (!isEaten())
		{
			entityPlayer.heal(3);
			entityPlayer.getFoodStats().addStats(4, 0.4f);

			worldObj.spawnParticle("largesmoke", posX, posY + 2, posZ, motionX, motionY, motionZ);
			worldObj.spawnParticle("largesmoke", posX, posY + 2, posZ, motionX, motionY, motionZ);

			setEaten(true);

			if (!worldObj.isRemote)
			{
				final boolean doDropEgg = RadixLogic.getBooleanWithProbability(25);
				final int dropAmount = RadixMath.getNumberInRange(1, 2);

				entityDropItem(new ItemStack(Items.string, RadixMath.getNumberInRange(0, 5), 0), 0);

				if (doDropEgg)
				{
					entityDropItem(new ItemStack(ModItems.spiderEgg, dropAmount, 0), 0);
				}
			}
		}

		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	public void setHitboxSize()
	{
		switch (cocoonType)
		{
		default:
			setSize(1.0F, 1.0F);
			break;
		}
	}

	public EnumCocoonType getCocoonType()
	{
		return cocoonType;
	}

	public boolean isEaten()
	{
		return dataWatcher.getWatchableObjectInt(20) == 1;
	}

	public void setEaten(boolean isEaten)
	{
		if (!worldObj.isRemote)
		{
			this.getDataWatcher().updateObject(20, isEaten ? 1 : 0);

			if (isEaten)
			{
				worldObj.playSoundAtEntity(this, cocoonType.getDeathSound(), 0.3F, getSoundPitch());
			}
		}
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
