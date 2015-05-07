package sqr.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sqr.enums.EnumWebType;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockWeb extends Block
{
	private EnumWebType webType;
	
	public BlockWeb(EnumWebType type) 
	{
		super(Material.ground);
		
		final String name = "web-" + type.getName() + "-block";
		setWebType(type);
		setBlockName(name);
		setBlockTextureName("sqr:" + name);
		setHardness(1.0F);
		
		GameRegistry.registerBlock(this, name);
	}
	
	private void setWebType(EnumWebType type)
	{
		this.webType = type;
	}
	
	public EnumWebType getWebType()
	{
		return webType;
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
	
	@Override
	public boolean isLadder(IBlockAccess world, int posX, int posY, int posZ, EntityLivingBase entity)
	{
		if (entity instanceof EntityPlayer || entity instanceof EntitySpider)
		{
			return true;
		}

		else
		{
			return false;
		}
	}
}
