package sq.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import radixcore.constant.Time;
import sq.core.minecraft.ModBlocks;
import sq.entity.IWebClimber;
import sq.enums.EnumWebType;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockWebFull extends Block
{
	private EnumWebType webType;

	public BlockWebFull(EnumWebType type) 
	{
		super(Material.circuits);

		String name = "web-" + type.getName() + "-block";
		setWebType(type);
		setBlockName(name);
		setBlockTextureName("sq:" + name);
		setHardness(1.0F);

		if (this instanceof BlockWebGround)
		{
			name += "-ground";
		}

		else if (this instanceof BlockWebSide)
		{
			name += "-side";
		}

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

	private void checkForBed(World world, int x, int y, int z, int itr)
	{
		if (webType == EnumWebType.NORMAL)
		{
			Block fillerBlock = ModBlocks.webFull;
			Block outlineBlock = Blocks.log;

			if (world.getBlock(x,y,z) != fillerBlock) { return; }

			int fillerBlocksPresent = 0;
			int outlineBlocksPresent = 0;

			if (world.getBlock(x-1,y,z-1) == fillerBlock) { fillerBlocksPresent++; }
			if (world.getBlock(x-1,y,z) == fillerBlock)   { fillerBlocksPresent++; }
			if (world.getBlock(x-1,y,z+1) == fillerBlock) { fillerBlocksPresent++; }
			if (world.getBlock(x,y,z-1) == fillerBlock)   { fillerBlocksPresent++; }
			if (world.getBlock(x,y,z) == fillerBlock)     { fillerBlocksPresent++; }
			if (world.getBlock(x,y,z+1) == fillerBlock)   { fillerBlocksPresent++; }
			if (world.getBlock(x+1,y,z-1) == fillerBlock) { fillerBlocksPresent++; }
			if (world.getBlock(x+1,y,z) == fillerBlock)   { fillerBlocksPresent++; }
			if (world.getBlock(x+1,y,z+1) == fillerBlock) { fillerBlocksPresent++; }

			if (world.getBlock(x-2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z-1) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z) == outlineBlock)   { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z+1) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z-1) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z) == outlineBlock)   { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z+1) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-1,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x,y,z-2) == outlineBlock)   { outlineBlocksPresent++; }
			if (world.getBlock(x+1,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x-1,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x,y,z+2) == outlineBlock)   { outlineBlocksPresent++; }
			if (world.getBlock(x+1,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			if (world.getBlock(x+2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }

			if (fillerBlocksPresent == 9 & outlineBlocksPresent == 20)
			{
				world.setBlock(x-1,y,z-1,ModBlocks.webBed);
				world.setBlock(x-1,y,z,ModBlocks.webBed);
				world.setBlock(x-1,y,z+1,ModBlocks.webBed);
				world.setBlock(x,y,z-1,ModBlocks.webBed);
				world.setBlock(x,y,z,ModBlocks.webBed);
				world.setBlock(x,y,z+1,ModBlocks.webBed);
				world.setBlock(x+1,y,z-1,ModBlocks.webBed);
				world.setBlock(x+1,y,z,ModBlocks.webBed);
				world.setBlock(x+1,y,z+1,ModBlocks.webBed);
			}

			else
			{
				if (itr < 3)
				{
					checkForBed(world,x-1,y,z-1,itr+1);
					checkForBed(world,x-1,y,z,itr+1);
					checkForBed(world,x-1,y,z+1,itr+1);
					checkForBed(world,x,y,z-1,itr+1);
					checkForBed(world,x,y,z+1,itr+1);
					checkForBed(world,x+1,y,z-1,itr+1);
					checkForBed(world,x+1,y,z,itr+1);
					checkForBed(world,x+1,y,z+1,itr+1);
				}
			}
		}
	}
	
	@Override
	public void onBlockAdded(World world, int posX, int posY, int posZ)
	{
		checkForBed(world, posX, posY, posZ, 0);
		onNeighborBlockChange(world, posX, posY, posZ, 0);
	}
	
	private void onNeighborBlockChange(World world, int posX, int posY, int posZ, int meta)
	{
		if (world.getBlock(posX - 1, posY, posZ) != Blocks.air) { return; }
		if (world.getBlock(posX + 1, posY, posZ) != Blocks.air) { return; }
		if (world.getBlock(posX, posY - 1, posZ) != Blocks.air) { return; }
		if (world.getBlock(posX, posY + 1, posZ) != Blocks.air) { return; }
		if (world.getBlock(posX, posY, posZ - 1) != Blocks.air) { return; }
		if (world.getBlock(posX, posY, posZ + 1) != Blocks.air) { return; }

		world.setBlock(posX, posY, posZ, Blocks.air);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) 
	{
		if (entity instanceof EntitySpider || entity instanceof EntityPlayer || entity instanceof IWebClimber)
		{
			return;
		}

		else
		{
			entity.setInWeb();

			entity.motionX = entity.motionX * -0.1D;
			entity.motionZ = entity.motionZ * -0.1D;
			entity.motionY = entity.motionY * 0.1D;

			if (webType == EnumWebType.POISON && entity instanceof EntityLivingBase)
			{
				final EntityLivingBase entityLiving = (EntityLivingBase)entity;

				if (entityLiving.getActivePotionEffect(Potion.poison) == null)
				{
					entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, Time.SECOND * 5));
				}
			}
		}
	}

	@Override
	public boolean isLadder(IBlockAccess world, int posX, int posY, int posZ, EntityLivingBase entity)
	{
		if (entity instanceof EntityPlayer || entity instanceof EntitySpider || entity instanceof IWebClimber)
		{
			return true;
		}

		else
		{
			return false;
		}
	}
}
