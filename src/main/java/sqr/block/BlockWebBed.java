package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sqr.core.SQR;

public class BlockWebBed extends Block
{
	protected BlockWebBed()
	{
		super(Material.ground);
		this.setBlockBounds(0.0F, 0.4F, 0.0F, 1.0F, 0.6F, 1.0F);
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return SQR.texx[1];
	}
	
	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Item.getItemFromBlock(Blocks.web);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		boolean die = false;
		if (world.getBlock(x - 1, y, z) == Blocks.air)
		{
			die = true;
		}
		if (world.getBlock(x + 1, y, z) == Blocks.air)
		{
			die = true;
		}
		if (world.getBlock(x, y, z - 1) == Blocks.air)
		{
			die = true;
		}
		if (world.getBlock(x, y, z + 1) == Blocks.air)
		{
			die = true;
		}
		
		if (die)
		{
			this.dropBlockAsItem(world, x, y, z, Block.getIdFromBlock(b), 1); // TODO
																				// may
																				// not
																				// work.
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int i, float f1, float f2, float f3)
	{
		// TODO
		// final EnumStatus enumstatus = entityplayer.sleepInWebBedAt(x, y, z);
		//
		// if(enumstatus == EnumStatus.OK)
		// {
		// entityplayer.addChatMessage(new ChatComponentText("Sleep."));
		// //setBedOccupied(world, x, y, z, true);
		// return true;
		// }
		//
		// if(enumstatus == EnumStatus.NOT_POSSIBLE_NOW)
		// {
		// entityplayer.addChatMessage(new
		// ChatComponentText("tile.bed.noSleep"));
		// }
		//
		return true;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
