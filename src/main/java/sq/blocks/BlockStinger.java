package sq.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * The stinger is an item dropped by bees and wasps. It damages creatures that fall on it.
 */
public class BlockStinger extends Block
{
	public BlockStinger() 
	{
		super(Material.circuits);
		
		final String name = "stinger";
		setBlockName(name);
		setBlockTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setHardness(1.0F);
		
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) 
	{
		//onEntityWalking is only called when the entity was falling and it landed on
		//this block. When this happens, damage them for 3 points.
		entity.attackEntityFrom(DamageSource.cactus, 3);
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
		//No collision.
		return null;
	}
}
