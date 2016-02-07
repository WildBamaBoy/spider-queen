package sq.entity.creature;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import radixcore.util.RadixExcept;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.enums.EnumAntType;
import sq.enums.EnumBeeType;
import sq.enums.EnumCocoonType;

/**
 * Cocoons are entities placed in the world by the player or placed in the world after an entity is struck by a web shot.
 */
public class EntityCocoon extends EntityCreature implements IEntityAdditionalSpawnData
{
	private EnumCocoonType cocoonType;
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
	public boolean isAIDisabled()
	{
		return true;
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

		//Make the cocoon rock back and forth when it's struck.
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

		//For endermen, we spawn portal particles around the cocoon.
		if (cocoonType == EnumCocoonType.ENDERMAN && !isEaten())
		{
			worldObj.spawnParticle(EnumParticleTypes.PORTAL, posX + (rand.nextDouble() - 0.5D) * width, posY + 1 + rand.nextDouble() * 0.25D, posZ + rand.nextDouble() - 0.5D * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
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
			//When punched by a player, increment the damage to make the cocoon rock back and forth.
			timeSinceHit = 10;
			currentDamage += damage * 10;
			setBeenAttacked();

			//Destroy the cocoon when damage is greater than 8.
			if (currentDamage > 8)
			{
				//When the cocoon has been eaten, destroy it with a puff of smoke.
				if (isEaten())
				{
					worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX - motionX * 2, posY - motionY * 2 + 1, posZ - motionZ * 2, motionX, motionY, motionZ);
				}

				if (!worldObj.isRemote)
				{
					if (!isEaten()) //When it's not eaten, drop the cocoon's item.
					{
						dropItem(cocoonType.getCocoonItem(), 1);	
					}

					setDead();
				}

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
			ItemStack stack = entityPlayer.getCurrentEquippedItem();

			//Right-clicking with shears frees the creature inside.
			if (stack != null && stack.getItem() instanceof ItemShears && !worldObj.isRemote)
			{
				try
				{
					Class clazz = cocoonType.getCaptureClass();
					Entity newEntity = null;

					if (clazz == EntityBee.class)
					{
						EnumBeeType beeType = null;
						
						switch (cocoonType)
						{
							case QUEEN_BEE: beeType = EnumBeeType.QUEEN; break;
							case WARRIOR_BEE: beeType = EnumBeeType.WARRIOR; break;
							case GATHERER_BEE: beeType = EnumBeeType.GATHERER; break;
						}
						
						newEntity = (Entity) cocoonType.getCaptureClass().getConstructor(World.class, EnumBeeType.class).newInstance(worldObj, beeType);
					}

					else if (clazz == EntityAnt.class)
					{
						EnumAntType antType = null;
						
						switch (cocoonType)
						{
							case RED_ANT: antType = EnumAntType.RED; break;
							case BLACK_ANT: antType = EnumAntType.BLACK; break;
						}
						
						newEntity = (Entity) cocoonType.getCaptureClass().getConstructor(World.class, EnumAntType.class).newInstance(worldObj, antType);
					}

					else
					{
						newEntity = (Entity) cocoonType.getCaptureClass().getConstructor(World.class).newInstance(worldObj);
					}
					
					newEntity.setPositionAndRotation(posX, posY, posZ, worldObj.rand.nextInt(360), rotationPitch);
					worldObj.spawnEntityInWorld(newEntity);

					stack.damageItem(1, this);
					playSound("mob.sheep.shear", 1.0F, 1.0F);
					setDead();
				}

				catch (Exception e)
				{
					RadixExcept.logErrorCatch(e, "Unable to free captured entity from cocoon.");
				}
			}

			else //Right-clicked with anything other than shears.
			{
				//Heal the player's health and food stats when eaten.
				entityPlayer.heal(3);
				entityPlayer.getFoodStats().addStats(4, 0.4f);

				worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY + 2, posZ, motionX, motionY, motionZ);
				worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY + 2, posZ, motionX, motionY, motionZ);

				setEaten(true);

				//Then calculate and drop string/eggs.
				if (!worldObj.isRemote)
				{
					final boolean doDropEgg = RadixLogic.getBooleanWithProbability(25);
					final int dropAmount = RadixMath.getNumberInRange(1, 2);

					int maxString = 5;

					switch (cocoonType)
					{
					case ENDERMAN:
					case QUEEN_BEE:
					case HUMAN: maxString = 20;
					}

					entityDropItem(new ItemStack(Items.string, RadixMath.getNumberInRange(2, 20), 0), 0);

					if (doDropEgg)
					{
						entityDropItem(new ItemStack(SpiderCore.getItems().spiderEgg, dropAmount, 0), 0);
					}
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
		case GATHERER_BEE:
		case WARRIOR_BEE: 
		case QUEEN_BEE:
		case BLACK_ANT:
		case RED_ANT: setSize(0.75F, 0.75F); break;
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
