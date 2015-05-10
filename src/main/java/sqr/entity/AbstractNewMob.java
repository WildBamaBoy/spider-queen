package sqr.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import radixcore.util.RadixString;

public abstract class AbstractNewMob extends EntityMob 
{
	private final String codeName;
	
	public AbstractNewMob(World world, String codeName)
	{
		super(world);
		this.codeName = codeName;
		
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(1, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
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
	protected final String getHurtSound() 
	{
		return "sqr:" + codeName + ".hurt";
	}

	@Override
	protected final String getDeathSound() 
	{
		return "sqr:" + codeName + ".death";
	}

	@Override
	protected final String getLivingSound()
	{
		return "sqr:" + codeName + ".idle";
	}
	
	@Override
	public String getCommandSenderName() 
	{
		return RadixString.upperFirstLetter(codeName);
	}
}
