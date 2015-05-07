package sqr.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import sqr.core.SQR;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockStinger extends Block
{
	public BlockStinger() 
	{
		super(Material.ground);
		
		final String name = "stinger";
		setBlockName(name);
		setBlockTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
		
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
	}
}
