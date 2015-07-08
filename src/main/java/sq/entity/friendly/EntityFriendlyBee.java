package sq.entity.friendly;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.constant.Time;
import sq.core.ReputationHandler;
import sq.core.minecraft.ModItems;
import sq.entity.creature.EntityBee;

/**
 * The friendly bee is gifted to the player with a high bee reputation. It does not follow the spider rod in the player's hand.
 * Instead, the player must lure it with a flower. By gifting the friendly bee a flower, the player will receive nectar.
 */
public class EntityFriendlyBee extends EntityBee implements IFriendlyEntity
{
	private int timeUntilSpeak = Time.MINUTE * 5;
	private UUID friendPlayerUUID = new UUID(0, 0);;
	private boolean isImprisoned;
	public EntityLivingBase target;
	
	public EntityFriendlyBee(World world)
	{
		super(world);
	}
	
	public EntityFriendlyBee(World world, EntityPlayer friendPlayer)
	{
		super(world);
		this.friendPlayerUUID = friendPlayer.getPersistentID();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		FriendlyEntityHelper.onUpdate(this);
		
		final EntityPlayer nearestPlayer = worldObj.getClosestPlayerToEntity(this, 16.0D);
		
		if (nearestPlayer != null && nearestPlayer.getUniqueID().equals(friendPlayerUUID))
		{
			if (nearestPlayer.getHeldItem() != null && Block.getBlockFromItem(nearestPlayer.getHeldItem().getItem()) instanceof BlockFlower)
			{
				entityToAttack = nearestPlayer;
			}
			
			else
			{
				if (entityToAttack == nearestPlayer)
				{
					entityToAttack = null;
				}
			}
		}
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
	public boolean interact(EntityPlayer entity) 
	{
		final ItemStack heldItem = entity.inventory.getCurrentItem();
		
		if (isImprisoned)
		{
			ReputationHandler.handleInteractWithImprisoned(entity, this);
		}
		
		else if (heldItem != null && Block.getBlockFromItem(heldItem.getItem()) instanceof BlockFlower)
		{
			heldItem.stackSize--;
			
			if (!entity.worldObj.isRemote)
			{
				dropItem(ModItems.nectar, 1);
			}
		}
		
		return false;
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
		return EntityBee.class;
	}

	@Override
	public String getCommandSenderName() 
	{
		return super.getCommandSenderName();
	}
}