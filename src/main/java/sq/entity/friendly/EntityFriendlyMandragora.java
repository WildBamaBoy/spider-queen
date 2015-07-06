package sq.entity.friendly;

import java.util.UUID;

import net.minecraft.block.BlockCrops;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.constant.Particle;
import radixcore.constant.Time;
import radixcore.math.Point3D;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.ReputationHandler;
import sq.entity.creature.EntityMandragora;
import sq.util.Utils;

public class EntityFriendlyMandragora extends EntityMandragora implements IFriendlyEntity
{
	private int timeUntilAbility;
	private UUID friendPlayerUUID;
	public EntityLivingBase target;
	private boolean isImprisoned;

	public EntityFriendlyMandragora(World world)
	{
		super(world);
	}

	public EntityFriendlyMandragora(World world, EntityPlayer friendPlayer)
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

		if (!worldObj.isRemote)
		{
			timeUntilAbility--;

			if (timeUntilAbility <= 0)
			{
				timeUntilAbility = Time.MINUTE * RadixMath.getNumberInRange(2, 5);

				for (Point3D point : RadixLogic.getNearbyBlocks(this, BlockCrops.class, 10))
				{
					try
					{
						BlockCrops crop = (BlockCrops)worldObj.getBlock(point.iPosX, point.iPosY, point.iPosZ);
						int metadata = worldObj.getBlockMetadata(point.iPosX, point.iPosY, point.iPosZ);

						if (metadata < 7)
						{
							int max = 7 - metadata;
							worldObj.setBlockMetadataWithNotify(point.iPosX, point.iPosY, point.iPosZ, metadata + RadixMath.getNumberInRange(1, max), 2);
							Utils.spawnParticlesAroundEntityS(Particle.HAPPY, this, 16);
						}
					}

					catch (ClassCastException e)
					{
						//Seems to happen during world generation.
						continue;
					}
				}
			}
		}
	}

	@Override
	public boolean interact(EntityPlayer entity) 
	{
		if (isImprisoned)
		{
			ReputationHandler.handleInteractWithImprisoned(entity, this);
		}

		return super.interact(entity);
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
		return 100; //TODO this is temporary.
	}

	@Override
	public void setTimeUntilSpeak(int value) 
	{
		return;
	}

	@Override
	public String getSpeakId() 
	{
		return "mandragora";
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
		return EntityMandragora.class;
	}
	
	@Override
	public String getCommandSenderName() 
	{
		return "Mandragora";
	}
}