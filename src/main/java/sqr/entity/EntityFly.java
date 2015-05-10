package sqr.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sqr.core.SQR;

public class EntityFly extends AbstractFlyingMob
{
	public EntityFly(World world) 
	{
		super(world, "bee"); //Uses bee sounds.
		setSize(0.9F, 0.9F);
	}
	
	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		if (!SQR.getConfig().enableFly)
		{
			setDead();
		}
		
		if (motionY > 0)
		{
			motionY = motionY * 1.03F;
		}
		
		else
		{
			double yMod = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
			motionY = motionY * 0.3F + yMod * 0.3F;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		super.readEntityFromNBT(nbt);
	}

	@Override
	public String getCommandSenderName() 
	{
		return "Fly";
	}

	@Override
	public float getHitDamage() 
	{
		return 0.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 0.9D;
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 20.0F;
	}
}
