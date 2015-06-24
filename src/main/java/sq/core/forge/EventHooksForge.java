package sq.core.forge;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import radixcore.constant.Font.Color;
import radixcore.data.WatchedInt;
import radixcore.util.RadixLogic;
import sq.core.SpiderCore;
import sq.core.minecraft.ModItems;
import sq.core.radix.PlayerData;
import sq.entity.EntitySpiderEx;
import sq.entity.IRep;
import sq.entity.ai.RepEntityExtension;
import sq.entity.ai.ReputationContainer;
import sq.enums.EnumWatchedDataIDs;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class EventHooksForge
{
	@SubscribeEvent
	public void onPlayerSleepInBed(PlayerSleepInBedEvent event)
	{
		event.result = EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE;
		event.entityPlayer.addChatMessage(new ChatComponentText("Spiders can't sleep in normal beds."));
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
//		if (!event.entityLiving.worldObj.isRemote)
//		{
//			RepEntityExtension extension = (RepEntityExtension) event.entityLiving.getExtendedProperties(RepEntityExtension.ID);
//
//			if (extension != null)
//			{
//				List<Entity> entities = RadixLogic.getAllEntitiesOfTypeWithinDistance(EntityItem.class, event.entityLiving, 6);
//
//				for (Entity entity : entities)
//				{
//					EntityItem item = (EntityItem)entity;
//					
//					if (item.getEntityItem().getItem() == ModItems.skull)
//					{
////						ItemOffering.
//					}
//				}
//			}
//		}
	}

	@SubscribeEvent
	public void onAttackEntity(AttackEntityEvent event)
	{
		final EntityPlayer player = event.entityPlayer;
		final PlayerData data = SpiderCore.getPlayerData(player);

		if (event.target instanceof EntityLivingBase)
		{
			EntityLivingBase livingBase = (EntityLivingBase)event.target;
			List<Entity> entities = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(player.worldObj, player.posX, player.posY, player.posZ, 20);
			RepEntityExtension extension = (RepEntityExtension) event.target.getExtendedProperties(RepEntityExtension.ID);

			//Alert a player's nearby spiders when the player attacks something.
			for (Entity entity : entities)
			{
				if (entity instanceof EntitySpiderEx)
				{
					EntitySpiderEx spider = (EntitySpiderEx)entity;

					if (spider.getOwner().equals(player.getPersistentID()))
					{
						spider.setTarget(event.target);
					}
				}
			}

			//Check for the entity extension and up the number of times this entity has been attacked.
			if (extension != null)
			{
				//Guess what the new health will be as this is ran before attack damage takes effect.
				float calculatedHealth = livingBase.getHealth();
				ItemStack stack = player.inventory.getCurrentItem();

				if (stack != null)
				{
					if (stack.getItem() instanceof ItemTool)
					{
						ItemTool tool = (ItemTool)stack.getItem();
						Item.ToolMaterial toolMaterial = Item.ToolMaterial.valueOf(tool.getToolMaterialName());
						calculatedHealth -= toolMaterial.getDamageVsEntity();
					}

					else if (stack.getItem() instanceof ItemSword)
					{
						ItemSword sword = (ItemSword)stack.getItem();
						Item.ToolMaterial toolMaterial = Item.ToolMaterial.valueOf(sword.getToolMaterialName());
						calculatedHealth -= 4.0F + toolMaterial.getDamageVsEntity(); //Swords add 4.0 to the damage.
					}
				}

				//Get reputation for anger check.
				int reputation = -1;
				WatchedInt watchedLikeInstance = ReputationContainer.getLikeDataByClass(event.target.getClass(), data);

				if (event.entityLiving instanceof IRep)
				{
					reputation = ((IRep)event.target).getLikeData(data).getInt();
				}

				else if (watchedLikeInstance != null)
				{
					reputation = watchedLikeInstance.getInt();
				}

				//Check for anger.
				extension.setTimesHitByPlayer(extension.getTimesHitByPlayer() + 1);

				if (!player.worldObj.isRemote && extension.getTimesHitByPlayer() == 3 && calculatedHealth > 0.0F && reputation >= 0)
				{
					player.addChatComponentMessage(new ChatComponentText(Color.RED + "You have angered this " + event.target.getCommandSenderName() + "!"));
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if (event.entityLiving != null && event.source.getEntity() instanceof EntityPlayer)
		{
			final EntityPlayer player = (EntityPlayer) event.source.getEntity();
			final PlayerData data = SpiderCore.getPlayerData(player);
			final RepEntityExtension extension = (RepEntityExtension) event.entityLiving.getExtendedProperties(RepEntityExtension.ID);

			if (extension != null) //If they have an extension, they are a vanilla mob with a reputation entry.
			{				
				WatchedInt likeData = ReputationContainer.getLikeDataByClass(event.entityLiving.getClass(), data);
				int chanceToAffectRep = 25;

				if (likeData != null && RadixLogic.getBooleanWithProbability(chanceToAffectRep))
				{
					likeData.setValue(likeData.getInt() - 1);

					if (likeData.getInt() == -1)
					{
						player.addChatComponentMessage(new ChatComponentText(Color.RED + "You have broken your truce with the " + event.entityLiving.getCommandSenderName() + "s!"));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		boolean injectExtension = EnumWatchedDataIDs.doesEntityHaveLikeStatus(event.entity);

		if (injectExtension)
		{
			RepEntityExtension.register(event.entity);
		}
	}

	@SubscribeEvent
	public void onLivingSetTarget(LivingSetAttackTargetEvent event)
	{
		if (event.target instanceof EntityPlayer && event.entityLiving instanceof EntityLiving)
		{
			PlayerData data = SpiderCore.getPlayerData((EntityPlayer) event.target);
			EntityLiving entityLiving = (EntityLiving)event.entityLiving;
			RepEntityExtension extension = (RepEntityExtension) entityLiving.getExtendedProperties(RepEntityExtension.ID);
			WatchedInt watchedLikeInstance = ReputationContainer.getLikeDataByClass(event.entityLiving.getClass(), data);
			int reputation = -1;

			if (event.entityLiving instanceof IRep)
			{
				reputation = ((IRep)entityLiving).getLikeData(data).getInt();
			}

			else if (watchedLikeInstance != null)
			{
				reputation = watchedLikeInstance.getInt();
			}

			if (reputation >= 0)
			{
				if (extension != null && extension.getTimesHitByPlayer() >= 3)
				{
					//Ignore unsetting liked target when they've been hit by the player too many times.
					return;
				}

				entityLiving.setAttackTarget(null);
			}
		}
	}
}
