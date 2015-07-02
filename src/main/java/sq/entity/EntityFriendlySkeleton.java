package sq.entity;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.constant.Time;
import sq.core.minecraft.ModItems;

public class EntityFriendlySkeleton extends EntitySkeleton implements IFriendlyEntity
{
	private int timeUntilSpeak;
	private UUID friendPlayerUUID;
	private int rangedAttackProgress;
	
	public EntityLivingBase target;
	
	public EntityFriendlySkeleton(World world)
	{
		super(world);
	}
	
	public EntityFriendlySkeleton(World world, EntityPlayer friendPlayer)
	{
		super(world);
		this.friendPlayerUUID = friendPlayer.getPersistentID();

		//Clear old task entries.
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();
		
		//Add custom tasks.
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(5, new EntityAIWander(this, 0.55D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        
        //Add bow.
        this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		FriendlyEntityHelper.onUpdate(this);
		extinguish();
	}
	
	@Override
	public boolean interact(EntityPlayer entity) 
	{
		final ItemStack heldItem = entity.inventory.getCurrentItem();
		
		if (heldItem != null && heldItem.getItem() == ModItems.skull)
		{
			dropItem(Items.bone, 10);
		}
		
		return super.interact(entity);
	}
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.55D);
    }
    
	@Override
	public UUID getFriendPlayerUUID()
	{
		return friendPlayerUUID;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);
		
		nbt.setInteger("rangedAttackProgress", rangedAttackProgress);
		nbt.setLong("friendPlayerUUID-lsb", friendPlayerUUID.getLeastSignificantBits());
		nbt.setLong("friendPlayerUUID-msb", friendPlayerUUID.getMostSignificantBits());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		
		rangedAttackProgress = nbt.getInteger("rangedAttackProgress");
		friendPlayerUUID = new UUID(nbt.getLong("friendPlayerUUID-msb"), nbt.getLong("friendPlayerUUID-lsb"));
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
	{
		super.attackEntityFrom(damageSource, damageAmount);
		return FriendlyEntityHelper.attackEntityFrom(this, damageSource, damageAmount);
	}

	@Override
	public EntityCreature getInstance() 
	{
		return this;
	}

	@Override
	public EntityLivingBase getTarget() 
	{
		return target;
	}

	@Override
	protected boolean canDespawn() 
	{
		return false;
	}
	
	@Override
	public void setTarget(EntityLivingBase target) 
	{
		this.target = target;
	}

	@Override
	public boolean doManualAttack(Entity entityBeingAttacked, float damageAmount) 
	{
		if (rangedAttackProgress == 0)
		{
			attackEntityWithRangedAttack((EntityLivingBase) entityBeingAttacked, damageAmount);
			rangedAttackProgress = Time.SECOND * 2;
		}
		
		else
		{
			rangedAttackProgress--;
		}
		
		return true;
	}
	
	@Override
	public void setFriendPlayerUUID(UUID value) 
	{
		friendPlayerUUID = value;
	}
	
	@Override
	public int getTimeUntilSpeak() 
	{
		return timeUntilSpeak;
	}

	@Override
	public void setTimeUntilSpeak(int value) 
	{
		timeUntilSpeak = value;
	}
	
	@Override
	public String getSpeakId() 
	{
		return "skeleton";
	}
}