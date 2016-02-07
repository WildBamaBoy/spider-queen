package sq.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sq.core.SpiderCore;

/**
 * The lantern is simply a new placeable light block.
 */
public class BlockLantern extends Block
{
	public BlockLantern() 
	{
		super(Material.circuits);
		
		final String name = "lantern";
		setTickRandomly(true);
		setHardness(3.0F);
		setUnlocalizedName(name);
		
		GameRegistry.registerBlock(this, name + "-block");
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		 this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
	}

	@Override
	public int getLightValue() 
	{
		return 7;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random random, int unknown2) 
	{
		//Return the lantern item when this block is broken.
		return SpiderCore.getItems().lantern;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) 
	{
		//The "block" is used only in the world. Return our item when middle clicking.
		return new ItemStack(SpiderCore.getItems().lantern);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return 0;
	}
}
