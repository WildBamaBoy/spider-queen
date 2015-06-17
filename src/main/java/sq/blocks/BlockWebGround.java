package sq.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sq.enums.EnumWebType;
import cpw.mods.fml.common.registry.GameRegistry;

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
