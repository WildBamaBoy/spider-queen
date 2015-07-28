package sq.core.forge;

import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import radixcore.constant.Font.Color;
import radixcore.data.WatchedInt;
import radixcore.util.RadixLogic;
import sq.client.render.RenderSpiderQueen;
import sq.core.ReputationHandler;
import sq.core.SpiderCore;
import sq.core.minecraft.ModAchievements;
import sq.core.minecraft.ModItems;
import sq.core.radix.PlayerData;
import sq.entity.AbstractNewMob;
import sq.entity.IRep;
import sq.entity.ai.PlayerExtension;
import sq.entity.ai.RepEntityExtension;
import sq.entity.ai.ReputationContainer;
import sq.entity.creature.EntityCocoon;
import sq.entity.creature.EntityHuman;
import sq.entity.creature.EntitySpiderEx;
import sq.entity.friendly.EntityFriendlyMandragora;
import sq.entity.friendly.IFriendlyEntity;
import sq.enums.EnumWatchedDataIDs;

public final class EventHooksForge
{
	@SubscribeEvent
	public void onPlayerSleepInBed(PlayerSleepInBedEvent event)
	{
		event.result = EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE;
		event.entityPlayer.addChatMessage(new ChatComponentText("Spiders can't sleep in normal beds."));
	}

	@SubscribeEvent
	public void onAttackEntity(AttackEntityEvent event)
	{
		final EntityPlayer player = event.entityPlayer;
		final PlayerData data = SpiderCore.getPlayerData(player);

		if (event.target instanceof EntityLivingBase && !(event.target instanceof EntityCocoon))
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

			//Also alert the friendly entities.
			for (Entity entity : entities)
			{
				try
				{
					if (entity instanceof IFriendlyEntity && !(entity instanceof EntityFriendlyMandragora))
					{
						IFriendlyEntity friendly = (IFriendlyEntity)entity;

						if (!entity.worldObj.isRemote && friendly.getFriendPlayerUUID().equals(player.getPersistentID()) && event.target instanceof EntityLivingBase)
						{
							friendly.setTarget((EntityLivingBase) event.target);
						}
					}
				}

				catch (Exception e)
				{
					continue;
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

			if (event.target instanceof EntityGhast)
			{
				EntityGhast ghast = (EntityGhast) event.target;

				//isInWeb
				if (ObfuscationReflectionHelper.getPrivateValue(Entity.class, ghast, 27))
				{
					if (!ghast.worldObj.isRemote && RadixLogic.getBooleanWithProbability(50))
					{
						ghast.dropItem(ModItems.ghastEgg, 1);
					}

					//Half the ghast's health to prevent farming eggs.
					ghast.setHealth(ghast.getHealth() / 2);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entityLiving;

			//isInWeb
			ObfuscationReflectionHelper.setPrivateValue(Entity.class, player, false, 27);
		}
	}

	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer && event.source == DamageSource.fall)
		{
			PlayerExtension extension = PlayerExtension.get((EntityPlayer)event.entityLiving);

			boolean cancel = extension.webEntity != null || extension.slingerCooldown > 0;
			event.setCanceled(cancel);
		}

		else if (event.entityLiving.getClass().equals(EntitySpider.class) && event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.source.getSourceOfDamage();
			PlayerData data = SpiderCore.getPlayerData((EntityPlayer)event.source.getSourceOfDamage());
			RepEntityExtension extension = (RepEntityExtension) event.entityLiving.getExtendedProperties(RepEntityExtension.ID);

			//Cancel the target if...
			boolean doCancel = data.spiderLike.getInt() >= 0 && extension.getTimesHitByPlayer() < 3;

			if (doCancel)
			{
				EntityLivingBase living = new EntitySpider(event.entityLiving.worldObj);
				living.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);

				//Redirect the attack as an attack from a fake entity so that the spider doesn't hit the player back.
				event.entityLiving.attackEntityFrom(DamageSource.causeMobDamage(living), event.ammount);

				event.setCanceled(true);
				living.setDead(); //Set the entity as dead so that the spider will not try to attack it.
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
			final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);
			final RepEntityExtension repExtension = (RepEntityExtension) event.entityLiving.getExtendedProperties(RepEntityExtension.ID);

			if (repExtension != null) //If they have an extension, they are a vanilla mob with a reputation entry.
			{
				WatchedInt likeData = ReputationContainer.getLikeDataByClass(event.entityLiving.getClass(), data);
				int chanceToAffectRep = 25;

				//Check if the player is invisible. If so, no potential for reputation penalty.
				if (likeData != null && RadixLogic.getBooleanWithProbability(chanceToAffectRep) && !player.isInvisible())
				{
					ReputationHandler.onReputationChange(player, event.entityLiving, -1);
				}

				//Increase number of monsters killed if not human.
				if (likeData != data.humanLike)
				{
					if (event.entityLiving instanceof EntityCreeper || event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySkeleton)
					{
						playerExtension.setMonstersKilled(playerExtension.getMonstersKilled() + 1);

						if (playerExtension.getMonstersKilled() >= 5)
						{
							player.addChatComponentMessage(new ChatComponentText(Color.GREEN + "Your attack on enemy mobs has earned the humans' trust."));
							playerExtension.setMonstersKilled(0); //Reset

							ReputationHandler.onReputationChange(player, new EntityHuman(player.worldObj), 1);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		boolean injectExtension = EnumWatchedDataIDs.doesEntityHaveLikeStatus(event.entity) || event.entity instanceof EntityPlayer;

		if (injectExtension)
		{
			if (event.entity instanceof EntityPlayer)
			{
				PlayerExtension.register((EntityPlayer) event.entity);
			}

			else
			{
				RepEntityExtension.register(event.entity);
			}
		}

		if (event.entity instanceof EntityMob && !(event.entity instanceof AbstractNewMob))
		{
			EntityMob mob = (EntityMob)event.entity;

			if (mob.targetTasks != null)
			{
				mob.targetTasks.addTask(3, new EntityAINearestAttackableTarget(mob, EntityHuman.class, 0, true));
			}
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

			else if (event.entityLiving instanceof IFriendlyEntity)
			{
				IFriendlyEntity entity = (IFriendlyEntity)event.entityLiving;

				if (entity.getFriendPlayerUUID().equals(event.target.getPersistentID()))
				{
					//Never attack the friend player.
					entityLiving.setAttackTarget(null);
					return;
				}
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

	@SubscribeEvent
	public void onEntityItemPickup(EntityItemPickupEvent event)
	{
		if (event.entityPlayer != null)
		{
			if (event.item.getEntityItem().getItem() == ModItems.spiderEgg)
			{
				event.entityPlayer.triggerAchievement(ModAchievements.acquireEgg);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayerPre(RenderPlayerEvent.Pre pre) 
	{
		if (SpiderCore.getConfig().useSpiderQueenModel && pre.entityPlayer == Minecraft.getMinecraft().thePlayer)
		{
			pre.setCanceled(true);

			GL11.glPushMatrix();
			{
				GL11.glScaled(0.95D, 0.95D, 0.95D);
				GL11.glTranslated(0.0D, -0.10D, 0.0D);
				ClientProxy.renderSpiderQueen.doRender(pre.entity, 0F, 0F, 0F, 0F, pre.partialRenderTick);
			}
			GL11.glPopMatrix();
		}
	}
}
