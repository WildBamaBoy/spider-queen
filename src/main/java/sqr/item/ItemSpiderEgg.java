package sqr.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.core.minecraft.ModItems;
import sqr.entity.EntitySpiderEgg;

public class ItemSpiderEgg extends Item
{
	public ItemSpiderEgg()
	{
		super();
		this.maxStackSize = 16;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (entityplayer.inventory.consumeInventoryItem(ModItems.itemSpiderEgg))
		{
			// world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F /
			// (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
			{
				final EntitySpiderEgg nn = new EntitySpiderEgg(world);
				nn.setPosition(entityplayer.posX, entityplayer.posY, entityplayer.posZ);
				world.spawnEntityInWorld(nn);
			}
		}
		return itemstack;
	}
}
