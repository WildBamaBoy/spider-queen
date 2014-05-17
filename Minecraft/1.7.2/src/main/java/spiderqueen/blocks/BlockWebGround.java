package spiderqueen.blocks;

import net.minecraft.world.IBlockAccess;

public class BlockWebGround extends BlockWeb
{
	public BlockWebGround(boolean isPoison)
	{
		super(isPoison);
	}

	@Override
	public int getRenderType()
	{
		return 23;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}
}
