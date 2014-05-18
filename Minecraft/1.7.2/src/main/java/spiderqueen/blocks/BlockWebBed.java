/*******************************************************************************
 * BlockWeb.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import spiderqueen.core.Constants;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.entity.EntityOtherQueen;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.network.Packet;

public class BlockWebBed extends Block
{
	public BlockWebBed()
	{
		super(Material.circuits);
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
		if (entity instanceof EntitySpider || entity instanceof EntityHatchedSpider || entity instanceof EntityPlayer || entity instanceof EntityOtherQueen)
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
		blockIcon = IIconRegister.registerIcon("spiderqueen:WebBed");
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
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int meta, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (!world.isRemote)
		{
			if (world.isDaytime())
			{
				SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.OpenGui, Constants.ID_GUI_SLEEP));
				entityplayer.openGui(SpiderQueen.getInstance(), Constants.ID_GUI_SLEEP, world, i, j, k);
//				world.setWorldTime(13000);
			}

			else
			{
				entityplayer.addChatMessage(new ChatComponentText("You can only sleep during the day."));
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
