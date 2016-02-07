package sq.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sq.core.SpiderCore;

/**
 * The stinger is an item dropped by bees and wasps. It damages creatures that fall on it.
 */
public class BlockStinger extends Block
{
	public BlockStinger() 
	{
		super(Material.circuits);
		
		final String name = "stinger";
		setCreativeTab(SpiderCore.getCreativeTab());
		setHardness(1.0F);
		
		setUnlocalizedName(name);
		GameRegistry.registerBlock(this, name);
	}

	
	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) 
	{
		//onEntityWalking is only called when the entity was falling and it landed on
		//this block. When this happens, damage them for 3 points.
		entityIn.attackEntityFrom(DamageSource.cactus, 3);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) 
	{
		//No collision.
		return null;
	}
}
