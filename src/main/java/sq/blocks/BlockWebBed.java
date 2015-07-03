package sq.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import sq.entity.EntitySpiderEx;
import sq.entity.EntitySpiderQueen;
import sq.packet.PacketSleepC;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockWebBed extends Block
{
	public BlockWebBed()
	{
		super(Material.circuits);
		GameRegistry.registerBlock(this, "web-bed");
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public void onBlockAdded(World world, int posX, int posY, int posZ)
	{
		onNeighborBlockChange(world, posX, posY, posZ, 0);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity)
	{
		if (entity instanceof EntitySpider || entity instanceof EntitySpiderEx || entity instanceof EntityPlayer || entity instanceof EntitySpiderQueen)
		{
			return;
		}

		else
		{
			entity.setInWeb();

			entity.motionX = entity.motionX * -0.1D;
			entity.motionZ = entity.motionZ * -0.1D;
			entity.motionY = entity.motionY * 0.1D;
		}
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		blockIcon = IIconRegister.registerIcon("sq:web-bed");
	}

	@Override
	public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ)
	{
		if (world.getBlock(posX - 1, posY, posZ) != Blocks.air) { return true; }
		if (world.getBlock(posX + 1, posY, posZ) != Blocks.air) { return true; }
		if (world.getBlock(posX, posY - 1, posZ) != Blocks.air) { return true; }
		if (world.getBlock(posX, posY + 1, posZ) != Blocks.air) { return true; }
		if (world.getBlock(posX, posY, posZ - 1) != Blocks.air) { return true; }
		if (world.getBlock(posX, posY, posZ + 1) != Blocks.air) { return true; }

		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer entityPlayer, int meta, float unknown, float unknown1, float unknown2)
	{
		if (!world.isRemote)
		{
			if (world.isDaytime())
			{
				SpiderCore.getPacketHandler().sendPacketToPlayer(new PacketSleepC(false), (EntityPlayerMP) entityPlayer);
			}

			else
			{
				entityPlayer.addChatMessage(new ChatComponentText("You can only sleep during the day."));
			}
		}

		return true;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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

	private boolean canBePlacedOn(Block block)
	{
		if (block == Blocks.air)
		{
			return false;
		}

		else
		{
			return block.renderAsNormalBlock() && block.getMaterial().blocksMovement();
		}
	}
}