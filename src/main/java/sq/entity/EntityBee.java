package sq.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.data.WatchedInt;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.core.radix.PlayerData;
import sq.entity.ai.RepEntityExtension;
import sq.enums.EnumBeeType;

public class EntityBee extends AbstractFlyingMob implements IRep
{
	public EntityBee(World world) 
	{
		super(world, "bee");
		setSize(1.2F, 1.0F);

		int beeType = RadixMath.getNumberInRange(0, 2);
		dataWatcher.updateObject(12, beeType);
	}

	public EntityBee(World world, EnumBeeType type)
	{
		this(world);
		dataWatcher.updateObject(12, type.getId());
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(12, EnumBeeType.GATHERER.getId());
	}

	@Override
	public boolean isAIEnabled() 
	{
		//Bees use their own logic.
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) 
	{
		super.attackEntityFrom(source, damage);
		
		//Cancel setting the target if the player hasn't hit them enough, or if reputation is too high.
		if (source.getEntity() instanceof EntityPlayer)
		{
			PlayerData data = SpiderCore.getPlayerData(((EntityPlayer)source.getEntity()));
			RepEntityExtension extension = (RepEntityExtension) this.getExtendedProperties(RepEntityExtension.ID);
			
			if (data.beeLike.getInt() >= 0 && extension.getTimesHitByPlayer() <= 2)
			{
				this.setTarget(null);
			}
		}
		
		return true;
	}

	
	@Override
	protected void attackEntity(Entity entity, float damage) 
	{
		super.attackEntity(entity, getHitDamage());
		
		if (RadixMath.getDistanceToEntity(this, entity) <= 1.0D)
		{
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), getHitDamage());
		}
	}

	@Override
	protected Entity findPlayerToAttack() 
	{
		EntityPlayer player = worldObj.getClosestPlayerToEntity(this, 16.0D);
		
		if (player != null)
		{
			PlayerData data = SpiderCore.getPlayerData(player);
			RepEntityExtension extension = (RepEntityExtension) this.getExtendedProperties(RepEntityExtension.ID);
			
			if (data.beeLike.getInt() >= 0 && extension.getTimesHitByPlayer() <= 2)
			{
				return null;
			}
			
			else
			{
				return player;
			}
		}
		
		else
		{
			Entity entity = RadixLogic.getNearestEntityOfTypeWithinDistance(EntityWasp.class, this, 16);
			return entity;
		}
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		if (entityToAttack != null)
		{
			double sqDistanceTo = Math.sqrt(Math.pow(entityToAttack.posX - posX, 2) + Math.pow(entityToAttack.posZ - posZ, 2));
			float moveAmount = 0.0F;
			
			if(sqDistanceTo < 8F) 
			{ 
				moveAmount = ((8F - (float)sqDistanceTo) / 8F)*4F; 
			}
			
			if (entityToAttack.posY + 0.2F < posY)
			{
				motionY = motionY - 0.05F * moveAmount;
			}
			
			if(entityToAttack.posY - 0.5F > posY)
			{
				motionY = motionY + 0.01F * moveAmount;
			}
		}

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("type", dataWatcher.getWatchableObjectInt(12));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(12, nbt.getInteger("type"));
	}

	@Override
	public String getCommandSenderName() 
	{
		return "Bee";
	}

	@Override
	public float getHitDamage() 
	{
		EnumBeeType type = getBeeType();
		return type == EnumBeeType.GATHERER ? 0.0F : type == EnumBeeType.WARRIOR ? 2.0F : 5.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 0.7D;
	}

	@Override
	public float getMobMaxHealth() 
	{
		EnumBeeType type = getBeeType();
		return type == EnumBeeType.GATHERER ? 10.0F : type == EnumBeeType.WARRIOR ? 20.0F : 30.0F;
	}

	public EnumBeeType getBeeType()
	{
		return EnumBeeType.getById(dataWatcher.getWatchableObjectInt(12));
	}

	@Override
	public WatchedInt getLikeData(PlayerData data) 
	{
		return data.beeLike;
	}

	@Override
	public boolean isPassive() 
	{
		return false;
	}
}
