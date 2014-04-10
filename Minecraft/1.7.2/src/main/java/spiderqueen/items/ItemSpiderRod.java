package spiderqueen.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;

public class ItemSpiderRod extends Item
{
	public ItemSpiderRod()
	{
		this.maxStackSize = 1;
		this.setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		return itemStack;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:SpiderRod");
	}
}
