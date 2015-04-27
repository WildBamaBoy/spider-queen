package sqr.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.core.minecraft.ModItems;
import sqr.entity.EntityWeb;

public class ItemPWeb extends Item
{
	
	public ItemPWeb()
	{
		super();
		this.maxStackSize = 64;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (entityplayer.inventory.consumeInventoryItem(ModItems.itemPWeb))
		{
			world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityWeb(world, entityplayer, true));
			}
		}
		return itemstack;
	}
}
