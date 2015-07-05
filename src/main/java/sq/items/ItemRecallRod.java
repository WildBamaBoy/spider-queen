package sq.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import radixcore.constant.Particle;
import radixcore.util.RadixLogic;
import sq.core.SpiderCore;
import sq.entity.creature.EntitySpiderEx;
import sq.entity.friendly.IFriendlyEntity;
import sq.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRecallRod extends Item
{
	public ItemRecallRod()
	{
		super();
		
		final String name = "recall-rod";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(1);
		setMaxDamage(16);

		GameRegistry.registerItem(this, name);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		if (!world.isRemote)
		{
			for (Entity entity : RadixLogic.getAllEntitiesOfTypeWithinDistance(EntitySpiderEx.class, player, 32))
			{
				EntitySpiderEx spider = (EntitySpiderEx)entity;
				
				if (spider.getOwner() == player.getUniqueID())
				{
					entity.setPosition(player.posX, player.posY, player.posZ);
				}
			}
			
			for (Entity entity : RadixLogic.getAllEntitiesOfTypeWithinDistance(IFriendlyEntity.class, player, 32))
			{
				IFriendlyEntity friendly = (IFriendlyEntity)entity;
				
				if (friendly.getFriendPlayerUUID() == player.getUniqueID())
				{
					entity.setPosition(player.posX, player.posY, player.posZ);
				}
			}
			
			Utils.spawnParticlesAroundEntityS(Particle.MAGIC_CRITICAL_HIT, player, 32);
		}
		
		stack.setItemDamage(stack.getItemDamage() + 1);
		
		if (stack.getItemDamage() >= 16)
		{
			stack.stackSize = 0;
		}
		
		world.playSoundAtEntity(player, "sq:recall.rod", 1.0F, 1.0F);
		return stack;
	}	
}
