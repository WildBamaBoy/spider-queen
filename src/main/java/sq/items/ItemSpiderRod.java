package sq.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;
import sq.core.minecraft.ModBlocks;

/**
 * The spider rod item is used to attract friendlies to the player holding it, or to place the spider rod block into the world.
 */
public class ItemSpiderRod extends Item
{
	public ItemSpiderRod()
	{
		super();
		
		final String name = "spider-rod";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(1);
		
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		Block targetBlock = BlockHelper.getBlock(world, posX, posY, posZ);
		Block aboveBlock = BlockHelper.getBlock(world, posX, posY + 1, posZ);
		boolean targetIsPlant = targetBlock instanceof IPlantable;
		
		if (!world.isRemote && facing == EnumFacing.UP && targetBlock.getMaterial().isSolid() || targetIsPlant && aboveBlock == Blocks.air)
		{
			if (!player.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
			posY = targetIsPlant ? posY : posY + 1;
			BlockHelper.setBlock(world, posX, posY, posZ, ModBlocks.spiderRod);
		}
		
		return super.onItemUse(stack, player, world, posX, posY, posZ, meta, xOffset, yOffset, zOffset);
	}
}
