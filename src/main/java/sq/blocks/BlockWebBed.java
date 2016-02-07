package sq.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;
import sq.entity.creature.EntitySpiderEx;
import sq.entity.creature.EntitySpiderQueen;
import sq.packet.PacketSleepC;

/**
 * The web bed is a block usable on right-click that enables the player to sleep.
 */
public class BlockWebBed extends Block
{
	public BlockWebBed()
	{
		super(Material.circuits);
		
		setUnlocalizedName("web-bed");
		GameRegistry.registerBlock(this, "web-bed");
	}

	@Override
	public int quantityDropped(Random random)
	{
		//This is not an item that should be obtainable via any means. It is placed
		//in the world only under special circumstances.
		return 0;
	}


	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		onNeighborBlockChange(worldIn, pos, state);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) 
	{
		//This is a web, so if the creature caught inside isn't a web climber, hinder its motion.
		if (entityIn instanceof EntitySpider || entityIn instanceof EntitySpiderEx || entityIn instanceof EntityPlayer || entityIn instanceof EntitySpiderQueen)
		{
			return;
		}

		else
		{
			entityIn.setInWeb();

			entityIn.motionX = entityIn.motionX * -0.1D;
			entityIn.motionZ = entityIn.motionZ * -0.1D;
			entityIn.motionY = entityIn.motionY * 0.1D;
		}

	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) 
	{
		//No collision, this is a web.
		return null;
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) 
	{
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();

		if (BlockHelper.getBlock(worldIn, posX - 1, posY, posZ) != Blocks.air) { return true; }
		if (BlockHelper.getBlock(worldIn, posX + 1, posY, posZ) != Blocks.air) { return true; }
		if (BlockHelper.getBlock(worldIn, posX, posY - 1, posZ) != Blocks.air) { return true; }
		if (BlockHelper.getBlock(worldIn, posX, posY + 1, posZ) != Blocks.air) { return true; }
		if (BlockHelper.getBlock(worldIn, posX, posY, posZ - 1) != Blocks.air) { return true; }
		if (BlockHelper.getBlock(worldIn, posX, posY, posZ + 1) != Blocks.air) { return true; }

		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!worldIn.isRemote)
		{
			if (worldIn.isDaytime())
			{
				//Send a packet to the client that will open the sleep GUI.
				SpiderCore.getPacketHandler().sendPacketToPlayer(new PacketSleepC(false), (EntityPlayerMP) playerIn);
			}

			else
			{
				playerIn.addChatMessage(new ChatComponentText("You can only sleep during the day."));
			}
		}

		return true;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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

		world.setBlockToAir(pos);
	}

	private boolean canBePlacedOn(Block block)
	{
		if (block == Blocks.air)
		{
			return false;
		}

		else
		{
			return block.isBlockNormalCube() && block.getMaterial().blocksMovement();
		}
	}
}