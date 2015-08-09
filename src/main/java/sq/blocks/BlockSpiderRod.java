package sq.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sq.core.minecraft.ModItems;

/**
 * The spider rod is a small placeable item in the world that generates a small amount of light.
 * It is used by some entities to detect the point they should remain within a radius of.
 */
public class BlockSpiderRod extends Block
{
	public BlockSpiderRod() 
	{
		super(Material.plants); //So we don't get the grass walking sound.
		
		final String name = "spiderrod";
		setBlockName(name);
		setBlockTextureName("sq:" + name);
		setHardness(0.5F);

		GameRegistry.registerBlock(this, name);
	}
	
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
    {
		//Set the bounds based on what the block looks like in-game.
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    }
    
	@Override
	public Item getItemDropped(int unknown, Random random, int unknown2) 
	{
		//Drop the item, NEVER the block.
		return ModItems.spiderRod;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) 
	{
		//Return the item on middle click, not the block.
		return new ItemStack(ModItems.spiderRod);
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
	public int getLightValue() 
	{
		return 11;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int posX, int posY, int posZ, Random random)
	{
		//This randomly generates the redstone effect on the rod.
		final double particlePosX = posX + 0.5D + (random.nextFloat() - 0.5D) * random.nextGaussian();
		final double particlePosY = posY + 0.5F;
		final double particlePosZ = posZ + 0.5D + (random.nextFloat() - 0.5D) * random.nextGaussian();

		final double velX = 1.0D;
		double velY = 0.2D;
		double velZ = -0.1D;

		if (velY < 0.0D)
		{
			velY = 0.0D;
		}

		if (velZ < 0.0D)
		{
			velZ = 0.0D;
		}

		world.spawnParticle("reddust", particlePosX, particlePosY, particlePosZ, velX, velY, velZ);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		//This block has no collision.
		return null;
	}
}
