package sq.entity.friendly;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.constant.Time;
import sq.core.ReputationHandler;
import sq.core.minecraft.ModItems;
import sq.entity.creature.EntityHuman;

/**
 * The friendly zombie is gifted to the player with a high zombie reputation. 
 * By gifting the friendly zombie a brain, the player will receive logs.
 */
public class EntityFriendlyZombie extends EntityZombie implements IFriendlyEntity
{
	private int timeUntilSpeak = Time.MINUTE * 5;
	private UUID friendPlayerUUID = new UUID(0, 0);
	public EntityLivingBase target;
	private boolean isImprisoned;
	
	public EntityFriendlyZombie(World world)
	{
		super(world);
	}

	public EntityFriendlyZombie(World world, EntityPlayer friendPlayer)
	{
		super(world);
		this.friendPlayerUUID = friendPlayer.getPersistentID();

		//Clear old task entries.
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();

		//Add custom tasks.
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.55D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityHuman.class, 0.55, false));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.55D));
		this.tasks.addTask(5, new EntityAIWander(this, 0.55D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityHuman.class, 16, false));
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

		if (isImprisoned)
		{
			ReputationHandler.handleInteractWithImprisoned(entity, this);
		}
		
		if (heldItem != null && heldItem.getItem() == ModItems.brain)
		{
			heldItem.stackSize--;
			
			if (!entity.worldObj.isRemote)
			{
				dropItem(Item.getItemFromBlock(Blocks.log), 10);
			}
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

		nbt.setLong("friendPlayerUUID-lsb", friendPlayerUUID.getLeastSignificantBits());
		nbt.setLong("friendPlayerUUID-msb", friendPlayerUUID.getMostSignificantBits());
		nbt.setBoolean("isImprisoned", isImprisoned);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		friendPlayerUUID = new UUID(nbt.getLong("friendPlayerUUID-msb"), nbt.getLong("friendPlayerUUID-lsb"));
		isImprisoned = nbt.getBoolean("isImprisoned");
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
	protected boolean canDespawn() 
	{
		return false;
	}

	@Override
	public EntityLivingBase getTarget() 
	{
		return target;
	}

	@Override
	public void setTarget(EntityLivingBase target) 
	{
		this.target = target;
	}

	@Override
	public boolean doManualAttack(Entity entityBeingAttacked, float damageAmount) 
	{
		return false;
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
		return "zombie";
	}

	@Override
	public boolean isImprisoned() 
	{
		return isImprisoned;
	}

	@Override
	public void setImprisoned(boolean value) 
	{
		this.isImprisoned = value;
	}

	@Override
	public Class getNonFriendlyClass() 
	{
		return EntityZombie.class;
	}
	
	@Override
	public String getCommandSenderName() 
	{
		return "Zombie";
	}
}