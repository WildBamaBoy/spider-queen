package sq.blocks;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import sq.enums.EnumWebType;

public class BlockWebSide extends BlockWebFull
{
	private EnumWebType webType;
	
	public BlockWebSide(EnumWebType type) 
	{
		super(type);
	}
	
	@Override
	public int getRenderType()
	{
		return 20;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		final int meta = blockAccess.getBlockMetadata(posX, posY, posZ);
		float minX = 1.0F, minY = 1.0F, minZ = 1.0F;
		float maxX = 0.0F, maxY = 0.0F, maxZ = 0.0F;
		boolean flag = meta > 0;

		if ((meta & 2) != 0)
		{
			maxX = Math.max(maxX, 0.0625F);
			minX = 0.0F;
			minY = 0.0F;
			maxY = 1.0F;
			minZ = 0.0F;
			maxZ = 1.0F;
			flag = true;
		}

		if ((meta & 8) != 0)
		{
			minX = Math.min(minX, 0.9375F);
			maxX = 1.0F;
			minY = 0.0F;
			maxY = 1.0F;
			minZ = 0.0F;
			maxZ = 1.0F;
			flag = true;
		}

		if ((meta & 4) != 0)
		{
			maxZ = Math.max(maxZ, 0.0625F);
			minZ = 0.0F;
			minX = 0.0F;
			maxX = 1.0F;
			minY = 0.0F;
			maxY = 1.0F;
			flag = true;
		}

		if ((meta & 1) != 0)
		{
			minZ = Math.min(minZ, 0.9375F);
			maxZ = 1.0F;
			minX = 0.0F;
			maxX = 1.0F;
			minY = 0.0F;
			maxY = 1.0F;
			flag = true;
		}

		if (!flag && canBePlacedOn(blockAccess.getBlock(posX, posY + 1, posZ)))
		{
			minY = Math.min(minY, 0.9375F);
			maxY = 1.0F;
			minX = 0.0F;
			maxX = 1.0F;
			minZ = 0.0F;
			maxZ = 1.0F;
		}

		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	private boolean canBePlacedOn(Block block)
	{
		return true;
	}
}
