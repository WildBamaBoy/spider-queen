package sq.entity.friendly;

import java.util.Random;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import radixcore.constant.Time;
import radixcore.math.Point3D;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import radixcore.util.RadixString;
import sq.core.SpiderCore;
import sq.core.minecraft.ModBlocks;
import sq.core.minecraft.ModItems;
import sq.entity.creature.EntityBee;

/**
 * This class contains static methods called by most friendly creatures.
 * This is used as no common abstract creature can be defined while the friendly
 * creatures retain the properties of their originals.
 */
public final class FriendlyEntityHelper
{
	private FriendlyEntityHelper() {}

	public static Class getFriendlyVariant(EntityLivingBase living)
	{
		if (living instanceof EntityCreeper)
		{
			return EntityFriendlyCreeper.class;
		}

		else if (living instanceof EntityZombie)
		{
			return EntityFriendlyZombie.class;
		}

		else if (living instanceof EntitySkeleton)
		{
			return EntityFriendlySkeleton.class;
		}

		else if (living instanceof EntityBee)
		{
			return EntityFriendlyBee.class;
		}

		return null;
	}

	public static void onUpdate(IFriendlyEntity friendlyEntity)
	{
		if (!friendlyEntity.getInstance().worldObj.isRemote && friendlyEntity.getTimeUntilSpeak() <= 0)
		{
			EntityCreature friendlyCreature = friendlyEntity.getInstance();
			EntityPlayer player = friendlyCreature.worldObj.getClosestPlayerToEntity(friendlyCreature, 8.0D);

			//Make sure there's a player to talk to, and it's our friend.
			if (player != null && player.getUniqueID().equals(friendlyEntity.getFriendPlayerUUID()))
			{
				String messageId = "message." + friendlyEntity.getSpeakId() + ".periodic";
				player.addChatComponentMessage(new ChatComponentText(
						RadixString.upperFirstLetter(friendlyEntity.getSpeakId() + ": " + 
						SpiderCore.getLanguageManager().getString(messageId))));
				
				friendlyEntity.setTimeUntilSpeak(Time.MINUTE * RadixMath.getNumberInRange(10, 20));

				//Announce to all nearby friendlies that this one has spoken, reset all of them to prevent annoying the player.
				for (Entity entity : RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(friendlyCreature.worldObj, friendlyCreature.posX, friendlyCreature.posY, friendlyCreature.posZ, 20))
				{
					if (entity instanceof IFriendlyEntity)
					{
						IFriendlyEntity otherFriendly = (IFriendlyEntity)entity;
						otherFriendly.setTimeUntilSpeak(Time.MINUTE * RadixMath.getNumberInRange(10, 20));
					}
				}
			}
		}

		//Generally the same code used for spiders.
		if (!tryFollowFriend(friendlyEntity))
		{
			final Entity target = friendlyEntity.getTarget();

			if (target != null && !target.isDead && isTargetValid(friendlyEntity, target))
			{
				attackEntity(friendlyEntity, target, 3.5F);
			}

			else
			{
				if (target != null)
				{
					friendlyEntity.setTarget(null);
				}

				else
				{
					tryMoveToStationaryRod(friendlyEntity);
				}
			}
		}
	}

	public static boolean tryFollowFriend(IFriendlyEntity friendlyEntity)
	{
		final EntityLiving me = friendlyEntity.getInstance();
		final Entity target = friendlyEntity.getTarget();
		final EntityPlayer friendPlayer = getPlayer(me.worldObj, friendlyEntity.getFriendPlayerUUID());

		if (friendPlayer != null && target == null)
		{
			final double distanceToOwner = RadixMath.getDistanceToEntity(me, friendPlayer);
			final ItemStack currentItemStack = friendPlayer.inventory.mainInventory[friendPlayer.inventory.currentItem];

			if (currentItemStack != null && distanceToOwner < 25.0D && (currentItemStack.getItem() == ModItems.spiderRod))
			{
				moveToPlayer(friendlyEntity, friendPlayer);
				return true;
			}
		}

		return false;
	}

	public static void tryMoveToStationaryRod(IFriendlyEntity friendlyEntity)
	{
		final EntityLiving me = friendlyEntity.getInstance();
		Point3D nearestRod = RadixLogic.getFirstNearestBlock(me, ModBlocks.spiderRod, 10);

		if (nearestRod != null && RadixMath.getDistanceToXYZ(nearestRod.dPosX, nearestRod.dPosY, nearestRod.dPosZ, me.posX, me.posY, me.posZ) >= 5.0D)
		{
			double moveSpeed = friendlyEntity.getInstance().getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
			me.getNavigator().tryMoveToXYZ(nearestRod.dPosX, nearestRod.dPosY, nearestRod.dPosZ, moveSpeed);
		}
	}

	public static void attackEntity(IFriendlyEntity friendlyEntity, Entity entityBeingAttacked, float damageAmount)
	{
		if (!friendlyEntity.doManualAttack(entityBeingAttacked, damageAmount))
		{
			final EntityLiving me = friendlyEntity.getInstance();
			final Random rand = me.worldObj.rand;
			final PathNavigate navigator = me.getNavigator();

			if (me.getHealth() > 0.0F && me.riddenByEntity == null)
			{
				double moveSpeed = friendlyEntity.getInstance().getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
				navigator.setPath(navigator.getPathToEntityLiving(entityBeingAttacked), moveSpeed);

				if (RadixMath.getDistanceToEntity(me, entityBeingAttacked) < 2.0D && !(friendlyEntity instanceof EntityFriendlyCreeper))
				{
					final EntityLivingBase entityLiving = (EntityLivingBase) entityBeingAttacked;
					entityBeingAttacked.attackEntityFrom(DamageSource.generic, damageAmount);
				}
			}
		}
	}

	public static boolean attackEntityFrom(IFriendlyEntity friendlyEntity, DamageSource damageSource, float damageAmount)
	{
		final Entity sourceEntity = damageSource.getSourceOfDamage();

		if (sourceEntity instanceof EntityLivingBase) //Check that the source was an attackable entity.
		{
			if (sourceEntity instanceof EntityPlayer)
			{
				final EntityPlayer attackingPlayer = (EntityPlayer) sourceEntity;

				if (!attackingPlayer.capabilities.isCreativeMode)
				{
					if (!attackingPlayer.getUniqueID().equals(friendlyEntity.getFriendPlayerUUID()))
					{
						friendlyEntity.setTarget(attackingPlayer);
					}
				}
			}

			else
			{
				friendlyEntity.setTarget((EntityLivingBase) sourceEntity);
			}
		}

		return false;
	}

	private static void moveToPlayer(IFriendlyEntity friendlyEntity, EntityPlayer friendPlayer)
	{
		final EntityLiving me = friendlyEntity.getInstance();

		if (friendPlayer.onGround)
		{
			me.getLookHelper().setLookPositionWithEntity(friendPlayer, 10.0F, me.getVerticalFaceSpeed());

			if (me.getDistanceToEntity(friendPlayer) > 3.5D)
			{
				double moveSpeed = friendlyEntity.getInstance().getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
				final boolean pathSet = me.getNavigator().tryMoveToEntityLiving(friendPlayer, moveSpeed);
				me.getNavigator().onUpdateNavigation();

				if (me.getDistanceToEntity(friendPlayer) >= 10.0D && me.getDistanceToEntity(friendPlayer) <= 30.0D)
				{
					final int playerX = MathHelper.floor_double(friendPlayer.posX) - 2;
					final int playerY = MathHelper.floor_double(friendPlayer.boundingBox.minY);
					final int playerZ = MathHelper.floor_double(friendPlayer.posZ) - 2;

					for (int i = 0; i <= 4; ++i)
					{
						for (int i2 = 0; i2 <= 4; ++i2)
						{
							if ((i < 1 || i2 < 1 || i > 3 || i2 > 3) && World.doesBlockHaveSolidTopSurface(me.worldObj, playerX + i, playerY - 1, playerZ + i2) && !me.worldObj.getBlock(playerX + i, playerY, playerZ + i2).isNormalCube() && !me.worldObj.getBlock(playerX + i, playerY + 1, playerZ + i2).isNormalCube())
							{
								me.setLocationAndAngles(playerX + i + 0.5F, playerY, playerZ + i2 + 0.5F, me.rotationYaw, me.rotationPitch);
								me.getNavigator().clearPathEntity();
								return;
							}
						}
					}
				}
			}
		}
	}

	private static EntityPlayer getPlayer(World world, UUID uuid)
	{
		try
		{
			final EntityPlayer returnPlayer = world.func_152378_a(uuid);
			return returnPlayer;
		}

		catch (NullPointerException e)
		{
			return null;
		}
	}

	private static boolean isTargetValid(IFriendlyEntity friendlyEntity, Entity target)
	{
		if (target instanceof IFriendlyEntity)
		{
			//Don't attack friendly entities belonging to the same player.
			IFriendlyEntity targetFriendlyEntity = (IFriendlyEntity)target;
			boolean ownersAreEqual = friendlyEntity.getFriendPlayerUUID().equals(targetFriendlyEntity.getFriendPlayerUUID());
			return !ownersAreEqual;
		}

		return true;
	}
}