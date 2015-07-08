package sq.entity.creature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.constant.Font.Color;
import radixcore.data.WatchedInt;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.ReputationHandler;
import sq.core.SpiderCore;
import sq.core.minecraft.ModBlocks;
import sq.core.radix.PlayerData;
import sq.entity.AbstractFlyingMob;
import sq.entity.IRep;
import sq.entity.ai.RepEntityExtension;
import sq.entity.friendly.EntityFriendlyBee;
import sq.enums.EnumBeeType;
import sq.util.Utils;

/**
 * Bees are passive/aggressive based on reputation. There are three types, Warrior, Gatherer, and Queen.
 * Gatherers cannot attack, Warriors sting the player with an animation, and Queens deal 5 points of damage.
 */
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

	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootingLvl) 
	{
		if (SpiderCore.rand.nextBoolean())
		{
			dropItem(Item.getItemFromBlock(ModBlocks.stinger), 1);
		}
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(12, EnumBeeType.GATHERER.getId());
		this.dataWatcher.addObject(13, 0);
	}

	public void setAttacking(boolean value)
	{
		this.dataWatcher.updateObject(13, value == true ? 1 : 0);
	}

	public boolean getAttacking()
	{
		return this.dataWatcher.getWatchableObjectInt(13) == 1 ? true : false;
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
		//Check whether the bee is in a log or leaves, ignore if so.
		if (source == DamageSource.inWall)
		{
			Block block = worldObj.getBlock((int)posX, (int)posY, (int)posZ);

			if (block == Blocks.log || block == Blocks.log2 || block == Blocks.leaves || block == Blocks.leaves2 || block == Blocks.tallgrass)
			{
				return false;
			}
		}

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
		//Gatherers do not attack.
		if (this.getBeeType() == EnumBeeType.GATHERER || this instanceof EntityFriendlyBee)
		{
			return;
		}

		//Within 1.2 blocks of the target, damage it.
		if (RadixMath.getDistanceToEntity(entityToAttack, this) <= 1.2D)
		{
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), getHitDamage());
		}

		//Within 3 blocks of the target, set the attacking flag to true. This switches the
		//warrior to its "stinging" model.
		if (RadixMath.getDistanceToEntity(entityToAttack, this) <= 3.0D)
		{
			setAttacking(true);
		}

		else //Reset the attacking flag when we're more than 3 blocks away.
		{
			if (getAttacking())
			{
				setAttacking(false);
			}
		}
	}

	public boolean hasEntitytoAttack()
	{
		return entityToAttack != null;
	}

	@Override
	protected Entity findPlayerToAttack() 
	{
		//Find players to attack, but check reputation and times hit first.
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

		else //Bees also attack wasps.
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
			//If we have a creature to attack, we need to move downwards if we're above it, and vice-versa.
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

			//When stinging, speed up in order to lunge at the player.
			if (getBeeType() == EnumBeeType.WARRIOR && getAttacking())
			{
				motionX = motionX * 1.2F;
				motionZ = motionZ * 1.2F;
			}
		}

		//We also make gatherer bees set their target to the nearest player holding a flower in order
		//to show that they are attracted to flowers. Since gatherers do not attack, they won't hurt
		//the player, they'll only path towards them.
		if (getBeeType() == EnumBeeType.GATHERER)
		{
			final EntityPlayer nearbyPlayer = worldObj.getClosestPlayer(posX, posY, posZ, 8.0D);

			if (nearbyPlayer != null)
			{
				ItemStack heldItem = nearbyPlayer.getHeldItem();

				if (heldItem != null && Block.getBlockFromItem(heldItem.getItem()) instanceof BlockFlower)
				{
					this.entityToAttack = nearbyPlayer;
				}
			}
		}
	}

	/**
	 * When the player right-clicks a bee with a flower, they gain reputation.
	 */
	@Override
	protected boolean interact(EntityPlayer entityPlayer) 
	{
		if (entityPlayer != null 
				&& entityPlayer.getHeldItem() != null 
				&& Block.getBlockFromItem(entityPlayer.getHeldItem().getItem()) instanceof BlockFlower 
				&& !entityPlayer.worldObj.isRemote)
		{
			entityPlayer.getHeldItem().stackSize--;
			entityPlayer.addChatComponentMessage(new ChatComponentText(Color.GREEN + "The Bees have accepted your offering."));
			ReputationHandler.onReputationChange(entityPlayer, this, 1);
			Utils.spawnParticlesAroundEntityS("heart", this, 16);
			this.entityToAttack = null;
		}

		return super.interact(entityPlayer);
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
