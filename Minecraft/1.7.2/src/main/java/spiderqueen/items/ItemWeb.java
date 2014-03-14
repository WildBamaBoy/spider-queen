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
	private boolean isPoison;
	
	public ItemWeb(boolean isPoison)
	{
		this.isPoison = isPoison;
		this.maxStackSize = 64;
		this.setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity(entityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if (!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityWeb(entityPlayer, isPoison));
		}

		if (!entityPlayer.capabilities.isCreativeMode)
		{
			if (isPoison)
			{
				entityPlayer.inventory.consumeInventoryItem(SpiderQueen.getInstance().itemPoisonWeb);
			}
			
			else
			{
				entityPlayer.inventory.consumeInventoryItem(SpiderQueen.getInstance().itemWeb);
			}
		}
		
		return itemStack;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (isPoison)
		{
			itemIcon = iconRegister.registerIcon("spiderqueen:WebPoison");
		}
		
		else
		{
			itemIcon = iconRegister.registerIcon("spiderqueen:Web");
		}
	}
}
