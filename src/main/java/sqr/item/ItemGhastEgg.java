package sqr.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.core.minecraft.ModItems;
import sqr.entity.EntityGhastEgg;

public class ItemGhastEgg extends Item
{
	
	public ItemGhastEgg()
	{
		super();
		this.maxStackSize = 16;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (entityplayer.inventory.consumeInventoryItem(ModItems.itemGhastEgg))
		{
			// world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F /
			// (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
			{
				final EntityGhastEgg nn = new EntityGhastEgg(world);
				nn.setPosition(entityplayer.posX, entityplayer.posY, entityplayer.posZ);
				world.spawnEntityInWorld(nn);
			}
		}
		return itemstack;
	}
}
