package sqr.item;

import net.minecraft.item.ItemFood;

// Referenced classes of package net.minecraft.src:
//            Item, EntityPlayer, ItemStack, World

public class ItemNectar extends ItemFood
{
	
	public ItemNectar(int i, int j, boolean flag)
	{
		super(i, j, flag);
		this.maxStackSize = 16;
	}
	
}
