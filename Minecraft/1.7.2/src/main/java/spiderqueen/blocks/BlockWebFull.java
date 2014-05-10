package spiderqueen.blocks;

import net.minecraft.world.IBlockAccess;

public class BlockWebFull extends BlockWeb
{
	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
}
