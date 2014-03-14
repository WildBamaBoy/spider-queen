package spiderqueen.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityWeb;

public class ItemWeb extends Item
{
	public ItemWeb()
	{
		this.maxStackSize = 64;
		this.setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity(entityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if (!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityWeb(entityPlayer));
		}

		if (!entityPlayer.capabilities.isCreativeMode)
		{
			entityPlayer.inventory.consumeInventoryItem(SpiderQueen.getInstance().itemWeb);
		}
		
		return itemStack;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:Web");
	}
}
