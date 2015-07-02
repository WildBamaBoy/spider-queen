package sq.entity;

import java.util.UUID;

import sq.core.minecraft.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFriendlyBee extends EntityBee implements IFriendlyEntity
{
	private int timeUntilSpeak;
	private UUID friendPlayerUUID;
	public EntityLivingBase target;
	
	public EntityFriendlyBee(World world)
	{
		super(world);
	}
	
	public EntityFriendlyBee(World world, EntityPlayer friendPlayer)
	{
		super(world);
		this.friendPlayerUUID = friendPlayer.getPersistentID();

		//Clear old task entries.
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();
		
		//Add custom tasks.
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 0.55D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		FriendlyEntityHelper.onUpdate(this);
	}
	
	@Override
	public double getMoveSpeed() 
	{
		return 0.55F;
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
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		
		friendPlayerUUID = new UUID(nbt.getLong("friendPlayerUUID-msb"), nbt.getLong("friendPlayerUUID-lsb"));
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
	{
		super.attackEntityFrom(damageSource, damageAmount);
		return FriendlyEntityHelper.attackEntityFrom(this, damageSource, damageAmount);
	}

	@Override
	public boolean interact(EntityPlayer entity) 
	{
		final ItemStack heldItem = entity.inventory.getCurrentItem();
		
		if (heldItem != null && Block.getBlockFromItem(heldItem.getItem()) instanceof BlockFlower)
		{
			dropItem(ModItems.nectar, 1);
		}
		
		return super.interact(entity);
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
		return "bee";
	}
}