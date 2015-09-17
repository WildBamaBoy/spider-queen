package sq.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import radixcore.constant.Font.Color;
import radixcore.constant.Particle;
import radixcore.data.WatchedInt;
import radixcore.util.RadixExcept;
import radixcore.util.RadixLogic;
import radixcore.util.RadixString;
import sq.core.minecraft.ModAchievements;
import sq.core.radix.PlayerData;
import sq.entity.IRep;
import sq.entity.ai.RepEntityExtension;
import sq.entity.ai.ReputationContainer;
import sq.entity.friendly.EntityFriendlyBee;
import sq.entity.friendly.EntityFriendlyCreeper;
import sq.entity.friendly.EntityFriendlyMandragora;
import sq.entity.friendly.EntityFriendlySkeleton;
import sq.entity.friendly.EntityFriendlyZombie;
import sq.entity.friendly.FriendlyEntityHelper;
import sq.entity.friendly.IFriendlyEntity;
import sq.util.Utils;

/**
 * Handles increasing or decreasing a player's reputation with a certain reputation group.
 */
public class ReputationHandler 
{
	/**
	 * When a player interacts with an imprisoned friendly creature (locked in a factory), this
	 * method will run.
	 */
	public static void handleInteractWithImprisoned(EntityPlayer player, EntityLivingBase friendlyInstance)
	{
		//Get an instance of the friendly as its interface.
		IFriendlyEntity friendly = (IFriendlyEntity)friendlyInstance;

		//Tell the player what they've done.
		player.addChatComponentMessage(new ChatComponentText(Color.GREEN + "You have freed this " + friendlyInstance.getCommandSenderName() + " from captivity."));
		player.addChatComponentMessage(new ChatComponentText(Color.GREEN + "This greatly boosted your favor with the " + friendlyInstance.getCommandSenderName() + "s."));

		try
		{
			//Make a new instance of the creature's non-friendly class, and pass that to onReputationChange so
			//that the appropriate group is modified.
			EntityLivingBase repEntity = (EntityLivingBase) friendly.getNonFriendlyClass().getConstructor(World.class).newInstance(friendlyInstance.worldObj);
			onReputationChange(player, repEntity, 10);
			
			//Remove the friendly and leave the happy particles in its place.
			friendlyInstance.setDead();
			Utils.spawnParticlesAroundEntityS(Particle.HAPPY, friendlyInstance, 16);
		}

		catch (Exception e)
		{
			RadixExcept.logErrorCatch(e, "Increasing friendly reputation.");
		}
	}

	public static void onReputationChange(EntityPlayer player, EntityLivingBase living, int changeAmount)
	{
		//To start, we need the player's player data and the reputation entity's extended properties.
		final PlayerData data = SpiderCore.getPlayerData(player);
		final RepEntityExtension extension = (RepEntityExtension) living.getExtendedProperties(RepEntityExtension.ID);
		WatchedInt likeData = null;

		//Assign like data based on what entity we're working with.
		if (extension != null)
		{
			likeData = ReputationContainer.getLikeDataByClass(living.getClass(), data);
		}

		else if (living instanceof IRep)
		{
			likeData = ((IRep)living).getLikeData(data);
		}

		//Set the new reputation value and save the old one.
		int oldReputation = likeData.getInt();
		likeData.setValue(likeData.getInt() + changeAmount);
		int newReputation = likeData.getInt();

		//Handle what may happen next.
		if (changeAmount > 0)
		{
			handlePositiveRepChange(player, living, oldReputation, newReputation);
		}

		else if (changeAmount < 0)
		{
			handleNegativeRepChange(player, living, oldReputation, newReputation);
		}
	}

	private static void handlePositiveRepChange(EntityPlayer player, EntityLivingBase living, int oldReputation, int newReputation)
	{
		//Truce situation
		if (newReputation == 0 && oldReputation == -1)
		{
			player.addChatComponentMessage(new ChatComponentText(Color.GREEN + "They are calling for a truce."));
			player.triggerAchievement(ModAchievements.makeFriend);

			for (Entity entity : RadixLogic.getAllEntitiesOfTypeWithinDistance(living.getClass(), player, 20))
			{
				Utils.spawnParticlesAroundEntityS("heart", entity, 16);
				EntityLiving e = (EntityLiving)entity;
				e.setAttackTarget(null);
				e.getNavigator().clearPathEntity();
			}
		}

		//Allies
		if (newReputation > 3 && newReputation % 2 == 0)
		{
			player.addChatComponentMessage(new ChatComponentText(Color.GREEN + "They give you one of their own."));
			player.triggerAchievement(ModAchievements.makeFriend);
			
			Class friendClass = FriendlyEntityHelper.getFriendlyVariant(living);

			try
			{
				EntityCreature entity = (EntityCreature) friendClass.getConstructor(World.class, EntityPlayer.class).newInstance(player.worldObj, player);
				IFriendlyEntity friendly = (IFriendlyEntity)entity;

				Vec3 target = RandomPositionGenerator.findRandomTarget(entity, 5, 1);
				entity.setPosition(player.posX + target.xCoord, player.posY + target.yCoord, player.posZ + target.zCoord);
				player.worldObj.spawnEntityInWorld(entity);

				String messageId = "message." + friendly.getSpeakId() + ".appearance";
				player.addChatComponentMessage(new ChatComponentText(
						RadixString.upperFirstLetter(friendly.getSpeakId() + ": " + 
								SpiderCore.getLanguageManager().getString(messageId))));

				Achievement achievement = null;

				if (entity instanceof EntityFriendlyBee)
				{
					achievement = ModAchievements.getFriendlyBee;
				}

				else if (entity instanceof EntityFriendlyMandragora)
				{
					achievement = ModAchievements.getFriendlyMandragora;					
				}

				else if (entity instanceof EntityFriendlyCreeper)
				{
					achievement = ModAchievements.getFriendlyCreeper;
				}

				else if (entity instanceof EntityFriendlySkeleton)
				{
					achievement = ModAchievements.getFriendlySkeleton;
				}

				else if (entity instanceof EntityFriendlyZombie)
				{
					achievement = ModAchievements.getFriendlyZombie;
				}

				if (achievement != null)
				{
					player.triggerAchievement(achievement);
				}
			}

			catch (Exception e)
			{
				RadixExcept.logErrorCatch(e, "Spawning friendly entity.");
			}
		}

		//There's an increase, but they are still hostile to the player.
		if (newReputation < 0)
		{
			player.addChatComponentMessage(new ChatComponentText(Color.RED + "They are still hostile."));
		}
	}

	private static void handleNegativeRepChange(EntityPlayer player, EntityLivingBase living, int oldReputation, int newReputation)
	{
		//Broken truce.
		if (newReputation == -1 && oldReputation == 0)
		{
			player.triggerAchievement(ModAchievements.makeEnemy);
			player.addChatComponentMessage(new ChatComponentText(
					Color.RED + "You have broken your truce with the " + living.getCommandSenderName() + "s!"));
		}
	}

	private ReputationHandler()
	{
	}
}
