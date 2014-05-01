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

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int posX, int posY, int posZ, int meta, float unknown1, float unknown2, float unknown3) 
	{
		if (world.isAirBlock(posX, posY + 1, posZ))
		{
			world.setBlock(posX, posY + 1, posZ, SpiderQueen.getInstance().blockSpiderRod);
			
			if (!entityPlayer.capabilities.isCreativeMode)
			{
				itemStack.stackSize--;
			}
		}
		
		return super.onItemUse(itemStack, entityPlayer, world, posX, posY, posZ, meta, unknown1, unknown2, unknown3);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:SpiderRod");
	}
}
