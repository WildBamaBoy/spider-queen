package sq.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;
import sq.core.minecraft.ModBlocks;

/**
 * Defines the mandragora seeds, which place mandragora crop blocks on farmland when used.
 */
public class ItemMandSeeds extends Item
{
	public ItemMandSeeds()
	{
		super();
		
		final String name = "mandragora-seeds";
		setUnlocalizedName(name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(64);
		
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!worldIn.isRemote && worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) && BlockHelper.getBlock(worldIn, pos.getX(), pos.getY(), pos.getZ()) == Blocks.farmland)
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
			BlockHelper.setBlock(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), ModBlocks.cropMand);
		}
		
		return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
	}
}
