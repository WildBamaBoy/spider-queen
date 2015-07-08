package sq.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import radixcore.util.RadixString;
import sq.entity.ai.AIAttackPlayerOnUnlike;
import sq.entity.creature.EntityHuman;

/**
 * Defines a general "new" mob that can be hostile to players.
 */
public abstract class AbstractNewMob extends EntityMob 
{
	private final String codeName;

	public AbstractNewMob(World world, String codeName)
	{
		super(world);
		this.codeName = codeName;

		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(1, new EntityAIWander(this, getMoveSpeed()));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));

		if (!this.isPassive())
		{
			this.tasks.addTask(4, new EntityAIAttackOnCollide(this, getMoveSpeed(), false));

			if (this instanceof IRep)
			{
				this.targetTasks.addTask(1, new AIAttackPlayerOnUnlike(this));
			}

			else
			{
				this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
			}

			this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityHuman.class, 0, true));
		}
		
		appendAI();
	}

	protected final void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(getMoveSpeed());
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(getHitDamage());
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(getMobMaxHealth());
	}
	
	public abstract float getMobMaxHealth();

	public abstract float getHitDamage();

	public abstract double getMoveSpeed();

	@Override
    public boolean isAIEnabled()
    {
        return true;
    }
	
	@Override
	protected String getHurtSound() 
	{
		return "sq:" + codeName + ".hurt";
	}

	@Override
	protected String getDeathSound() 
	{
		return "sq:" + codeName + ".death";
	}

	@Override
	protected String getLivingSound()
	{
		return "sq:" + codeName + ".idle";
	}

	@Override
	public String getCommandSenderName() 
	{
		return RadixString.upperFirstLetter(codeName);
	}

	public void appendAI()
	{
		
	}
	
	public abstract boolean isPassive();
}
