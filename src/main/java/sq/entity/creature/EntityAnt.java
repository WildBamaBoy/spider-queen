package sq.entity.creature;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import sq.core.minecraft.ModBlocks;
import sq.entity.AbstractNewMob;
import sq.enums.EnumAntType;

/**
 * Ants are aggressive creatures that can be red or black. Red ants inflict more damage.
 * Ants also dig away blocks nearby in order to dig from their spawn location up to the surface.
 */
public class EntityAnt extends AbstractNewMob
{
	private int eatTime;
	
	public EntityAnt(World world) 
	{
		super(world, "ant");
		eatTime = 0;
		setSize(1.2F, 1.0F);
	}

	public EntityAnt(World world, EnumAntType type)
	{
		this(world);
		dataWatcher.updateObject(12, type.getId());
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(12, EnumAntType.BLACK.getId());
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		if (!SpiderCore.getConfig().enableAnt)
		{
			setDead();
		}

		//Ants instantly drown in water.
		if (isInWater())
		{
			attackEntityFrom(DamageSource.drown, 100.0F);
		}

		if (!worldObj.isRemote)
		{
			//Every 20 ticks, check for a block to eat and destroy it.
			eatTime++;
			
			if (eatTime > 20)
			{
				//Check for the block to eat.
				for (int i = -1; i < 2; i++) for (int j = 0; j < 2; j++) for (int k = -1; k < 2; k++)
				{
					Block block = worldObj.getBlock((int)posX + i, (int)posY + j, (int)posZ + k);

					if (block != Blocks.bedrock && block != ModBlocks.antHill && worldObj.rand.nextBoolean())
					{
						worldObj.setBlock((int)posX + i, (int)posY + j, (int)posZ + k, Blocks.air);
						break;
					}
				}

				eatTime = 0;
			}
		}
	}

	@Override
	public boolean isAIEnabled() 
	{
		return true;
	}

	@Override
	public boolean isOnLadder()
	{
		return isCollidedHorizontally;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("type", dataWatcher.getWatchableObjectInt(12));
		nbt.setInteger("eatTime", eatTime);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(12, nbt.getInteger("type"));
		eatTime = nbt.getInteger("eatTime");
	}

	@Override
	public float getHitDamage() 
	{
		return getAntType() == EnumAntType.BLACK ? 2.0F : 4.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 0.6D;
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 10.0F;
	}

	public EnumAntType getAntType()
	{
		return EnumAntType.getById(dataWatcher.getWatchableObjectInt(12));
	}

	@Override
	public boolean isPassive() 
	{
		return false;
	}
}
