package sq.blocks;

import java.util.Random;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sq.core.SpiderCore;
import sq.entity.creature.EntityJack;

/**
 * The Jack block (Jack o'Lantern) will spawn the Jack creature randomly at night.
 */
public class BlockJack extends BlockDirectional
{
	public BlockJack() 
	{
		super(Material.gourd);
		
		final String name = "jack";
		setCreativeTab(SpiderCore.getCreativeTab());
		setTickRandomly(true);
		setHardness(3.0F);
		setUnlocalizedName(name);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) 
	{
		//We don't care about timing here, let the world tick this block at random.
		super.updateTick(worldIn, pos, state, rand);

		if (!worldIn.isDaytime())
		{
			//When we tick at night, delete this block.
			worldIn.setBlockToAir(pos);
			
			//Spawn Jack at this location, as he "emerges" from the Jack o'Lantern block.
			EntityJack jack = new EntityJack(worldIn);
			jack.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
			worldIn.spawnEntityInWorld(jack);
		}
	}
	
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
    }
}
