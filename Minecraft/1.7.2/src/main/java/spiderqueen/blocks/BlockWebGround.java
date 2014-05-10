package spiderqueen.blocks;

import net.minecraft.world.IBlockAccess;

public class BlockWebGround extends BlockWeb
{
	@Override
	public int getRenderType() 
	{
		return 23;	
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) 
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}
}
