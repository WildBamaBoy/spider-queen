package sq.entity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sq.core.SQ;
import sq.enums.EnumAntType;

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

		if (!SQ.getConfig().enableAnt)
		{
			setDead();
		}

		if (isInWater())
		{
			attackEntityFrom(DamageSource.drown, 100.0F);
		}

		if (isCollidedHorizontally || getAttackTarget() != null)
		{
			eatTime++;

			if (eatTime > 20)
			{
				eatTime = 0;

				for (int xMov = -1; xMov <= 1; xMov++){ for(int yMov = -1; yMov <= 1; yMov++) { for(int zMov = -1; zMov <= 1; zMov++)
				{
					if (yMov < 1 || SQ.rand.nextInt(5) == 0)
					{
						if (SQ.rand.nextInt(10) == 0 && (SQ.rand.nextInt(4) == 0 || xMov != 0 || zMov != 0 || yMov < 1)) 
						{ 
							Block block = worldObj.getBlock((int)posX + xMov, (int)posY + yMov, (int)posZ + zMov);

							if (block == Blocks.dirt || block == Blocks.grass || block == Blocks.stone)
							{
								worldObj.setBlockToAir((int)posX + xMov, (int)posY + yMov, (int)posZ + zMov); 
							}
						}
					}
				}}}
			}
		}
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
}
