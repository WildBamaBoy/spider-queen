package sqr.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.enums.EnumAntType;

public class EntityAnt extends EntityMob implements IMobAttributes
{
	private int eatTime;

	public EntityAnt(World world) 
	{
		super(world);
		eatTime = 0;
		setSize(1.2F, 1.0F);

		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	public EntityAnt(World world, EnumAntType type)
	{
		this(world);
		dataWatcher.updateObject(12, type.getId());
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(getMoveSpeed());
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(getHitDamage());
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(getMobMaxHealth());
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

		if (!SQR.getConfig().enableAnt)
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
					if (yMov < 1 || SQR.rand.nextInt(5) == 0)
					{
						if (SQR.rand.nextInt(10) == 0 && (SQR.rand.nextInt(4) == 0 || xMov != 0 || zMov != 0 || yMov < 1)) 
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
	protected String getHurtSound() 
	{
		return "sqr:ant.hurt";
	}

	@Override
	protected String getDeathSound() 
	{
		return "sqr:ant.death";
	}

	@Override
	protected String getLivingSound()
	{
		return "sqr:ant.idle";
	}

	@Override
	public String getCommandSenderName() 
	{
		return getAntType().getFriendlyName() + " Ant";
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
