package sqr.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.core.minecraft.ModItems;
import sqr.entity.EntityWeb;

public class ItemWeb extends Item
{
	public ItemWeb()
	{
		super();
		this.maxStackSize = 64;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (entityplayer.inventory.consumeInventoryItem(ModItems.itemWeb))
		{
			world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityWeb(world, entityplayer));
			}
		}
		return itemstack;
	}
}
