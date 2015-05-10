package sqr.entity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.core.minecraft.ModBlocks;

public class EntityJack extends AbstractFlyingMob
{
	public EntityJack(World world) 
	{
		super(world, "jack");
		setSize(1.2F, 1.0F);
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		fallDistance = 0.0F;
		
		if (!SQR.getConfig().enableJack)
		{
			setDead();
		}
		
		if (!worldObj.isRemote && worldObj.isDaytime())
		{
			int yMov = 0;
			boolean allowBlockPlace =  false;

			while (yMov > -255)
			{
				Block block = worldObj.getBlock((int)posX, (int)posY + yMov, (int)posZ);
				
				if (block != Blocks.air)
				{
					allowBlockPlace = true;
					break;
				}
				yMov--;
			}
			
			if (allowBlockPlace)
			{
				worldObj.setBlock((int)posX, (int)posY + yMov + 1, (int)posZ, ModBlocks.jack);
				setDead();
			}
		}
	}

	@Override
	public float getHitDamage() 
	{
		return 6.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 1.0D;
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 80.0F;
	}
}
