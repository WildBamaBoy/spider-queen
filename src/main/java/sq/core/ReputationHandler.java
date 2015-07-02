package sq.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import radixcore.constant.Font.Color;
import radixcore.data.WatchedInt;
import radixcore.util.RadixExcept;
import radixcore.util.RadixLogic;
import radixcore.util.RadixString;
import sq.core.radix.PlayerData;
import sq.entity.FriendlyEntityHelper;
import sq.entity.IFriendlyEntity;
import sq.entity.IRep;
import sq.entity.ai.RepEntityExtension;
import sq.entity.ai.ReputationContainer;
import sq.util.Utils;

public class ReputationHandler 
{
	public static void onReputationChange(EntityPlayer player, EntityLivingBase living, int changeAmount)
	{
		final PlayerData data = SpiderCore.getPlayerData(player);
		final RepEntityExtension extension = (RepEntityExtension) living.getExtendedProperties(RepEntityExtension.ID);
		WatchedInt likeData = null;
		
		//Assign like data.
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
			
			Class friendClass = FriendlyEntityHelper.getFriendlyVariant(living);
			
			try
			{
				EntityCreature entity = (EntityCreature) friendClass.getConstructor(World.class, EntityPlayer.class).newInstance(player.worldObj, player);
				IFriendlyEntity friendly = (IFriendlyEntity)entity;
				
				Vec3 target = RandomPositionGenerator.findRandomTarget(entity, 5, 1);
				entity.setPosition(player.posX + target.xCoord, player.posY, player.posZ + target.zCoord);
				player.worldObj.spawnEntityInWorld(entity);
				
				String messageId = "message." + friendly.getSpeakId() + ".announce";
				player.addChatComponentMessage(new ChatComponentText(
						RadixString.upperFirstLetter(friendly.getSpeakId() + ": " + 
						SpiderCore.getLanguageManager().getString(messageId))));
			}
			
			catch (Exception e)
			{
				RadixExcept.logErrorCatch(e, "Spawning friendly entity.");
			}
		}
	}
	
	private static void handleNegativeRepChange(EntityPlayer player, EntityLivingBase living, int oldReputation, int newReputation)
	{
		//Broken truce.
		if (newReputation == -1 && oldReputation == 0)
		{
			player.addChatComponentMessage(new ChatComponentText(
					Color.RED + "You have broken your truce with the " + living.getCommandSenderName() + "s!"));
		}
	}
	
	private ReputationHandler()
	{
	}
}
