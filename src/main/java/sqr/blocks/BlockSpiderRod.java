package sqr.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import sqr.core.minecraft.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpiderRod extends Block
{
	public BlockSpiderRod() 
	{
		super(Material.ground);
		
		final String name = "spiderrod";
		setBlockName(name);
		setBlockTextureName("sqr:" + name);
		setHardness(0.5F);
		
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public Item getItemDropped(int unknown, Random random, int unknown2) 
	{
		return ModItems.spiderRod;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) 
	{
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
		return null;
	}
}
