package sq.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;

/**
 * Defines the lantern item, which just places the lantern block into the world when used.
 */
public class ItemLantern extends Item
{
	public ItemLantern()
	{
		super();
		
		final String name = "lantern";
		setUnlocalizedName(name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(1);
		
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!worldIn.isRemote && worldIn.isAirBlock(pos))
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
			BlockHelper.setBlock(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), SpiderCore.getBlocks().lantern);
		}
		
		return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
	}
}
