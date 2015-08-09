package sq.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import radixcore.constant.Time;
import sq.core.minecraft.ModBlocks;
import sq.entity.IWebClimber;
import sq.enums.EnumWebType;

/**
 * The full web is the base block for the other types of web.
 */
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

		//Change registry name based on which web we are working with.
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
		//1 block high, crossed squares.
		return 1;
	}

	@Override
	public Item getItemDropped(int fortune, Random rand, int meta) 
	{
		//Do not drop the block, it is not obtainable.
		return Items.string;
	}

	/**
	 * Checks the provided area for the requirements of the web bed and spawns it if needed.
	 */
	private void checkForBed(World world, int x, int y, int z, int itr)
	{
		if (webType == EnumWebType.NORMAL)
		{
			Block fillerBlock = ModBlocks.webFull;
			Block[] outlineBlocks = new Block[]
			{
				Blocks.log,
				Blocks.log2
			};

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

			for (Block outlineBlock : outlineBlocks)
			{
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
			}
			
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
		//Each time we're placed in the world, notify other blocks and check for the bed.
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
		//No collision.
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
		//Hinder the motion of entities that aren't spiders.
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

			//If this web is poison, add a poison effect to the creature caught inside.
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
		//Allow web climbers and players to climb this web like a ladder.
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
