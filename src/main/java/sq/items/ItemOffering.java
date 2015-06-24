package sq.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import radixcore.constant.Font.Color;
import radixcore.constant.Time;
import radixcore.data.WatchedInt;
import radixcore.util.RadixLogic;
import sq.core.SpiderCore;
import sq.core.minecraft.ModItems;
import sq.core.radix.PlayerData;
import sq.enums.EnumOfferingType;
import sq.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemOffering extends Item
{
	private EnumOfferingType offeringType;
	
	public ItemOffering(EnumOfferingType type)
	{
		super();
		
		final String name = type.getName();
		setOfferingType(type);
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
	
	private void setOfferingType(EnumOfferingType type)
	{
		offeringType = type;
	}
	
	private EnumOfferingType getOfferingType()
	{
		return offeringType;
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) 
	{
		List<Entity> entities = null;
		Class acceptorClass = null;
		Item item = entityItem.getEntityItem().getItem();
		
		//Determine the entity list to use.
		if (item == ModItems.brain)
		{
			acceptorClass = EntityZombie.class;
		}
		
		else if (item == ModItems.skull)
		{
			acceptorClass = EntitySkeleton.class;
		}
		
		else if (item == ModItems.heart)
		{
			acceptorClass = EntityCreeper.class;
		}
		
		else
		{
			return false;
		}
		
		entities = RadixLogic.getAllEntitiesOfTypeWithinDistance(acceptorClass, entityItem, 8);
		
		for (Entity entity : entities)
		{
			EntityLiving living = (EntityLiving)entity;
			living.getNavigator().tryMoveToXYZ(entityItem.posX, entityItem.posY, entityItem.posZ, 0.8D);
		}
		
		if (entities.size() > 0 && entityItem.ticksExisted >= Time.SECOND * 5)
		{	
			Entity exampleEntity = entities.get(0);
			String player = entityItem.getEntityItem().stackTagCompound.getString("player");
			entityItem.setDead();
			
			//Handle notification and like increase.
			final EntityPlayer entityPlayer = entityItem.worldObj.getPlayerEntityByName(player);
			
			if (entityPlayer != null && !entityPlayer.worldObj.isRemote)
			{
				entityPlayer.addChatComponentMessage(new ChatComponentText(Color.GREEN + "The " + exampleEntity.getCommandSenderName() + "s have accepted your offering."));
				PlayerData data = SpiderCore.getPlayerData(entityPlayer);
				WatchedInt likeVar = null;
				if (exampleEntity instanceof EntityCreeper)
				{
					likeVar = data.creeperLike;
				}
				
				else if (exampleEntity instanceof EntitySkeleton)
				{
					likeVar = data.skeletonLike;
				}
				
				else if (exampleEntity instanceof EntityZombie)
				{
					likeVar = data.zombieLike;
				}
				
				int lastLike = likeVar.getInt();
				likeVar.setValue(likeVar.getInt() + 1);
				int newLike = likeVar.getInt();
				
				if (lastLike < 0)
				{
					entityPlayer.addChatComponentMessage(new ChatComponentText(Color.GREEN + "They are calling for a truce."));
					
					for (Entity entity : RadixLogic.getAllEntitiesOfTypeWithinDistance(acceptorClass, entityItem, 20))
					{
						Utils.spawnParticlesAroundEntityS("heart", entity, 16);
						EntityLiving living = (EntityLiving)entity;
						living.setAttackTarget(null);
						living.getNavigator().clearPathEntity();
					}
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) 
	{
		item.stackTagCompound = new NBTTagCompound();
		item.stackTagCompound.setString("player", player.getCommandSenderName());
		
		return super.onDroppedByPlayer(item, player);
	}
}
