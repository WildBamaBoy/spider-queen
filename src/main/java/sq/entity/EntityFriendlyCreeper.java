package sq.entity;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.util.RadixMath;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityFriendlyCreeper extends EntityCreeper implements IFriendlyEntity
{
	private UUID friendPlayerUUID;
	private boolean hasPlayedSound;

	public EntityLivingBase target;

	public EntityFriendlyCreeper(World world)
	{
		super(world);

		dataWatcher.addObject(23, 0);
	}

	public EntityFriendlyCreeper(World world, EntityPlayer friendPlayer)
	{
		this(world);
		this.friendPlayerUUID = friendPlayer.getUniqueID();

		//Clear old task entries.
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();

		//Add custom tasks.
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAICreeperSwell(this));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		FriendlyEntityHelper.onUpdate(this);

		if (!worldObj.isRemote)
		{
			if (target != null && RadixMath.getDistanceToEntity(this, target) <= 2)
			{
				if (!hasPlayedSound)
				{
					playSound("creeper.primed", 1.0F, 0.5F);
					hasPlayedSound = true;
				}

				setExplosionTicks(getExplosionTicks() + 1);

				if (getExplosionTicks() >= 40)
				{
					worldObj.createExplosion(this, posX, posY, posZ, 2, worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
					setDead();
				}
			}

			else if (target == null || RadixMath.getDistanceToEntity(this, target) > 2)
			{
				setExplosionTicks(0);
				hasPlayedSound = false;
			}
		}
	}

	@Override
	public UUID getFriendPlayerUUID()
	{
		return friendPlayerUUID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float partialTickTime)
	{
		return (float)(getExplosionTicks() / 32.0F) * partialTickTime;
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
	public void setTarget(EntityLivingBase target) 
	{
		this.target = target;
	}

	@Override
	public boolean doManualAttack(Entity entityBeingAttacked, float damageAmount) 
	{
		return false;
	}

	public int getExplosionTicks()
	{
		return dataWatcher.getWatchableObjectInt(23);
	}

	public void setExplosionTicks(int value)
	{
		dataWatcher.updateObject(23, value);
	}

	@Override
	public void setFriendPlayerUUID(UUID value) 
	{
		friendPlayerUUID = value;
	}
}