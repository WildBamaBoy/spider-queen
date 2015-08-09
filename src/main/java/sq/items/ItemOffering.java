package sq.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
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
import radixcore.util.RadixLogic;
import sq.core.ReputationHandler;
import sq.core.SpiderCore;
import sq.core.minecraft.ModItems;
import sq.enums.EnumOfferingType;

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

		if (entities.size() > 0 && entityItem.ticksExisted >= Time.SECOND * 5 && entityItem.getEntityItem().hasTagCompound())
		{
			Entity exampleEntity = entities.get(0);
			String player = entityItem.getEntityItem().stackTagCompound.getString("player");
			entityItem.setDead();

			//Handle notification and like increase.
			final EntityPlayer entityPlayer = entityItem.worldObj.getPlayerEntityByName(player);

			if (entityPlayer != null && !entityPlayer.worldObj.isRemote)
			{
				entityPlayer.addChatComponentMessage(new ChatComponentText(Color.GREEN + "The " + exampleEntity.getCommandSenderName() + "s have accepted your offering."));
				ReputationHandler.onReputationChange(entityPlayer, (EntityLivingBase)exampleEntity, 1);
			}
		}

		//For when the player drops the item by clicking from the inventory, not pressing Q.
		else if (!entityItem.getEntityItem().hasTagCompound())
		{
			EntityPlayer player = entityItem.worldObj.getClosestPlayerToEntity(entityItem, 5.0D);
			
			if (player != null)
			{
				entityItem.getEntityItem().stackTagCompound = new NBTTagCompound();
				entityItem.getEntityItem().stackTagCompound.setString("player", player.getCommandSenderName());
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

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean unknown) 
	{
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.add("Press SHIFT for info.");
		}

		else
		{
			list.add("This item can be offered to ");
			list.add(offeringType.getAcceptorName() + " to boost reputation.");
			list.add(Color.GREEN + "Throw at a group of " + offeringType.getAcceptorName() + ".");
		}
	}
}
