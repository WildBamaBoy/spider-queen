package sq.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import radixcore.constant.Time;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;
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

		setUnlocalizedName(name);
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
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
			Block fillerBlock = SpiderCore.getBlocks().webFull;
			Block[] outlineBlocks = new Block[]
					{
							Blocks.log,
							Blocks.log2
					};

			if (BlockHelper.getBlock(world, x,y,z) != fillerBlock) { return; }

			int fillerBlocksPresent = 0;
			int outlineBlocksPresent = 0;

			if (BlockHelper.getBlock(world, x-1,y,z-1) == fillerBlock) { fillerBlocksPresent++; }
			if (BlockHelper.getBlock(world, x-1,y,z) == fillerBlock)   { fillerBlocksPresent++; }
			if (BlockHelper.getBlock(world, x-1,y,z+1) == fillerBlock) { fillerBlocksPresent++; }
			if (BlockHelper.getBlock(world, x,y,z-1) == fillerBlock)   { fillerBlocksPresent++; }
			if (BlockHelper.getBlock(world, x,y,z) == fillerBlock)     { fillerBlocksPresent++; }
			if (BlockHelper.getBlock(world, x,y,z+1) == fillerBlock)   { fillerBlocksPresent++; }
			if (BlockHelper.getBlock(world, x+1,y,z-1) == fillerBlock) { fillerBlocksPresent++; }
			if (BlockHelper.getBlock(world, x+1,y,z) == fillerBlock)   { fillerBlocksPresent++; }
			if (BlockHelper.getBlock(world, x+1,y,z+1) == fillerBlock) { fillerBlocksPresent++; }

			for (Block outlineBlock : outlineBlocks)
			{
				if (BlockHelper.getBlock(world, x-2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x-2,y,z-1) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x-2,y,z) == outlineBlock)   { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x-2,y,z+1) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x-2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+2,y,z-1) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+2,y,z) == outlineBlock)   { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+2,y,z+1) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x-2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x-1,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x,y,z-2) == outlineBlock)   { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+1,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+2,y,z-2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x-2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x-1,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x,y,z+2) == outlineBlock)   { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+1,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
				if (BlockHelper.getBlock(world, x+2,y,z+2) == outlineBlock) { outlineBlocksPresent++; }
			}

			if (fillerBlocksPresent == 9 & outlineBlocksPresent == 20)
			{
				BlockHelper.setBlock(world, x-1,y,z-1,SpiderCore.getBlocks().webBed);
				BlockHelper.setBlock(world, x-1,y,z,SpiderCore.getBlocks().webBed);
				BlockHelper.setBlock(world, x-1,y,z+1,SpiderCore.getBlocks().webBed);
				BlockHelper.setBlock(world, x,y,z-1,SpiderCore.getBlocks().webBed);
				BlockHelper.setBlock(world, x,y,z,SpiderCore.getBlocks().webBed);
				BlockHelper.setBlock(world, x,y,z+1,SpiderCore.getBlocks().webBed);
				BlockHelper.setBlock(world, x+1,y,z-1,SpiderCore.getBlocks().webBed);
				BlockHelper.setBlock(world, x+1,y,z,SpiderCore.getBlocks().webBed);
				BlockHelper.setBlock(world, x+1,y,z+1,SpiderCore.getBlocks().webBed);
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
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		//Each time we're placed in the world, notify other blocks and check for the bed.
		checkForBed(worldIn, pos.getX(), pos.getY(), pos.getZ(), 0);
		onNeighborBlockChange(worldIn, pos, state);
	}

	private void onNeighborBlockChange(World world, BlockPos pos, IBlockState state)
	{
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();

		if (BlockHelper.getBlock(world, posX - 1, posY, posZ) != Blocks.air) { return; }
		if (BlockHelper.getBlock(world, posX + 1, posY, posZ) != Blocks.air) { return; }
		if (BlockHelper.getBlock(world, posX, posY - 1, posZ) != Blocks.air) { return; }
		if (BlockHelper.getBlock(world, posX, posY + 1, posZ) != Blocks.air) { return; }
		if (BlockHelper.getBlock(world, posX, posY, posZ - 1) != Blocks.air) { return; }
		if (BlockHelper.getBlock(world, posX, posY, posZ + 1) != Blocks.air) { return; }

		BlockHelper.setBlock(world, posX, posY, posZ, Blocks.air);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		//No collision.
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) 
	{
		//Hinder the motion of entities that aren't spiders.
		if (entityIn instanceof EntitySpider || entityIn instanceof EntityPlayer || entityIn instanceof IWebClimber)
		{
			return;
		}

		else
		{
			entityIn.setInWeb();

			entityIn.motionX = entityIn.motionX * -0.1D;
			entityIn.motionZ = entityIn.motionZ * -0.1D;
			entityIn.motionY = entityIn.motionY * 0.1D;

			//If this web is poison, add a poison effect to the creature caught inside.
			if (webType == EnumWebType.POISON && entityIn instanceof EntityLivingBase)
			{
				final EntityLivingBase entityLiving = (EntityLivingBase)entityIn;

				if (entityLiving.getActivePotionEffect(Potion.poison) == null)
				{
					entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, Time.SECOND * 5));
				}
			}
		}
	}

	@Override
	public boolean isLadder(IBlockAccess world, BlockPos pos, EntityLivingBase entity) 
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
