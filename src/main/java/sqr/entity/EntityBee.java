package sqr.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBee extends AbstractFlyingMob
{
	public EntityBee(World world) 
	{
		super(world, "bee");
		setSize(1.2F, 1.0F);
	}

	public EntityBee(World world, EnumBeeType type)
	{
		this(world);
		dataWatcher.updateObject(12, type.getId());
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(12, EnumBeeType.GATHERER.getId());
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("type", dataWatcher.getWatchableObjectInt(12));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(12, nbt.getInteger("type"));
	}
	
	@Override
	public String getCommandSenderName() 
	{
		return getBeeType().getFriendlyName() + " Bee";
	}

	@Override
	public float getHitDamage() 
	{
		EnumBeeType type = getBeeType();
		return type == EnumBeeType.GATHERER ? 0.0F : type == EnumBeeType.WARRIOR ? 2.0F : 5.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 0.7D;
	}

	@Override
	public float getMobMaxHealth() 
	{
		EnumBeeType type = getBeeType();
		return type == EnumBeeType.GATHERER ? 10.0F : type == EnumBeeType.WARRIOR ? 20.0F : 3.0F;
	}

	public EnumBeeType getBeeType()
	{
		return EnumBeeType.getById(dataWatcher.getWatchableObjectInt(12));
	}
}
