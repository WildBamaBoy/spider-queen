package sqr.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, EntityPlayer, World

public class ItemRareFruit extends Item
{
	
	public ItemRareFruit(int i, int j)
	{
		super();
		this.healAmount = j;
		this.maxStackSize = 8;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		itemstack.stackSize--;
		entityplayer.entityDropItem(new ItemStack(Items.string, this.healAmount, 0), 0);
		entityplayer.heal(this.healAmount);
		/*
		 * int tint = entityplayer.getMArmor() + healAmount; if(tint > 100) {
		 * tint = 100; } entityplayer.setMArmor(tint);
		 */
		return itemstack;
	}
	
	public int getHealAmount()
	{
		return this.healAmount;
	}
	
	private final int healAmount;
}
