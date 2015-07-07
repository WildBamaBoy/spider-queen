package sq.blocks;

import net.minecraft.world.IBlockAccess;
import sq.enums.EnumWebType;

/**
 * This is the web block first spawned on a block that previously had no web attached to it.
 */
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
		//Renders flat on the ground.
		return 23;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		//Collision matches the area of the web as it is on the ground.
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}
}
