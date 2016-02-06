package sq.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;
import sq.entity.creature.EntitySpiderEgg;

/**
 * The spider egg allows the player to place a spider egg entity into the world.
 */
public class ItemSpiderEgg extends Item
{
	public ItemSpiderEgg()
	{
		super();
		
		final String name = "spider-egg";
		setUnlocalizedName(name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(8);
		
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!worldIn.isRemote)
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
			final Block block = BlockHelper.getBlock(worldIn, pos.getX(), pos.getY(), pos.getZ());

            IBlockState blockState = worldIn.getBlockState(pos);
            
            pos = pos.offset(side);
            double yOffset = 0.0D;

            if (side == EnumFacing.UP && blockState.getBlock() instanceof BlockFence)
            {
                yOffset = 0.5D;
            }
            
			final EntitySpiderEgg entitySpiderEgg = new EntitySpiderEgg(worldIn, playerIn.getUniqueID());
			entitySpiderEgg.setLocationAndAngles(pos.getX() + 0.5F, pos.getY() + yOffset, pos.getZ() + 0.5F, worldIn.rand.nextFloat() * 360F, 0.0F);
			worldIn.spawnEntityInWorld(entitySpiderEgg);

			return true;
		}
		
		return true;
	}
}
