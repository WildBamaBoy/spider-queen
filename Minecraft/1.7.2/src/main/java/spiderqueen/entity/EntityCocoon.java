package spiderqueen.entity;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import spiderqueen.enums.EnumCocoonType;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityCocoon extends Entity implements IEntityAdditionalSpawnData
{
	private EnumCocoonType cocoonType;
	private boolean isEaten;
	private int currentDamage;
	private int timeSinceHit;
	private int rockDirection = 1;

	public EntityCocoon(World world)
	{
		super(world);
		setSize(1F, 1F);
	}

	public EntityCocoon(World world, EnumCocoonType cocoonType) 
	{
		super(world);
		this.cocoonType = cocoonType;
	}

	@Override
	protected void entityInit() 
	{

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
		
		if(currentDamage > 0)
		{
			Random rr = new Random();
			rotationPitch += rr.nextFloat();
			rotationPitch -= rr.nextFloat();
			currentDamage--;
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		Entity entity = damageSource.getEntity();

		if (worldObj.isRemote || isDead)
		{
			return true;
		}

		rockDirection = -rockDirection;
		timeSinceHit = 10;
		currentDamage += damage * 10;

		setBeenAttacked();

		if (currentDamage > 80)
		{
			if (isEaten())
			{
				worldObj.spawnParticle("largesmoke", posX - motionX * 2, posY - motionY * 2, posZ - motionZ * 2, motionX, motionY, motionZ);
				setDead();
				return true;
			}

			//TODO
			//dropItemWithDamage(mod_SpiderQueen.itemCocoon.shiftedIndex, 1, myType);

			setDead();
		}
		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) 
	{
		try
		{
			cocoonType = (EnumCocoonType)EnumCocoonType.class.getFields()[nbt.getInteger("cocoonType")].get(EnumCocoonType.class);
		}

		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) 
	{
		nbt.setInteger("cocoonType", cocoonType.ordinal());
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeInt(cocoonType.ordinal());
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
	}

	public void performHurtAnimation()
	{
		rockDirection = -rockDirection;
		timeSinceHit = 10;
		currentDamage += currentDamage * 10;
	}

	public EnumCocoonType getCocoonType()
	{
		return cocoonType;
	}

	public boolean isEaten() 
	{
		return isEaten;
	}

	public void setEaten(boolean isCocoonedEntityDead) 
	{
		this.isEaten = isCocoonedEntityDead;
	}
}
