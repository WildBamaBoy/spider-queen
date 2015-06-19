package sq.blocks;

import net.minecraft.world.IBlockAccess;
import sq.enums.EnumWebType;

public class BlockWebGround extends BlockWebFull
{
	private EnumWebType webType;
	
	public BlockWebGround(EnumWebType type) 
	{
		super(type);
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
