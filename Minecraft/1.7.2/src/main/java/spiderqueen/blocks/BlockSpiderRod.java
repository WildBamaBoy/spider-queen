package spiderqueen.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;

public class BlockSpiderRod extends Block
{	
	public BlockSpiderRod() 
	{
		super(Material.plants);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}

	public int getRenderType()
	{
		return 1;
	}

    public Item getItemDropped(int unknown, Random random, int unknown2)
    {
        return SpiderQueen.getInstance().itemSpiderRod;
    }
    
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int posX, int posY, int posZ, Random random)
	{
		final double particlePosX = (double)posX + 0.5D + ((double)random.nextFloat() - 0.5D) * random.nextGaussian();
		final double particlePosY = (double)((float)posY + 0.5F);
		final double particlePosZ = (double)posZ + 0.5D + ((double)random.nextFloat() - 0.5D) * random.nextGaussian();

		double velX = 1.0D;
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
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		blockIcon = IIconRegister.registerIcon("spiderqueen:SpiderRod");
	}
}
