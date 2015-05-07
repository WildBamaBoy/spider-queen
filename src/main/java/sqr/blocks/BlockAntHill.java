package sqr.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import sqr.core.SQR;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockAntHill extends Block
{
	public BlockAntHill() 
	{
		super(Material.ground);
		
		final String name = "anthill";
		setBlockName(name);
		setBlockTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
		setTickRandomly(true);
		setHardness(1.0F);
		
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) 
    {
		super.updateTick(world, x, y, z, random);
		
		if (world.getClosestPlayer((double)x, (double)y, (double)z, 16.0D) != null)
		{
			//TODO Spawn ant.
		}
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
