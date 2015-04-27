package sqr.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.core.minecraft.ModBlocks;

// Referenced classes of package net.minecraft.src:
//            Item, Material, Block, EntityPlayer,
//            MathHelper, World, ItemStack

public class ItemSkull extends Item
{
	public ItemSkull()
	{
		super();
		this.maxStackSize = 16;
	}
	
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int l)
	{
		if (l != 1)
		{
			return false;
		}
		
		y++;
		
		final Block block = ModBlocks.bskull;
		if (!block.canPlaceBlockAt(world, x, y, z))
		{
			return false;
		}
		
		world.setBlock(x, y, z, block);
		world.notifyBlocksOfNeighborChange(x, y, z, block);
		
		itemstack.stackSize--;
		return true;
	}
	
	private Material doorMaterial;
}
