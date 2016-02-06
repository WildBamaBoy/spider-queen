package sq.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(1);
		
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		Block targetBlock = worldIn.getBlockState(pos).getBlock();
		Block aboveBlock = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock();
		boolean targetIsPlant = targetBlock instanceof IPlantable;
		
		if (!worldIn.isRemote && side == EnumFacing.UP && targetBlock.getMaterial().isSolid() || targetIsPlant && aboveBlock == Blocks.air)
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
			int posY = targetIsPlant ? pos.getY() : pos.getY() + 1;
			BlockHelper.setBlock(worldIn, pos.getX(), posY, pos.getZ(), ModBlocks.spiderRod);
		}
		
		return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
	}
}
