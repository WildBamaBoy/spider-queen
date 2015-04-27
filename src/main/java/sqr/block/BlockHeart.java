package sqr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.tile.TileEntityHBait;

public class BlockHeart extends BlockContainer
{
	protected BlockHeart()
	{
		super(Material.ground);
		this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.02F, 1.0F);
		this.setTickRandomly(true);
	}

	public int tickRate()
	{
		return 80;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate());
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x,y,z);
		meta++;
		if(meta > 13)
		{
			SQR.outputTxt = "Your offering was accepted.";
//			SQR.likeChange("creeper",2);

			if(SQR.CreeperLike > 9)
			{
				SQR.CreeperLike = 0;
				SQR.outputTxt = "The creepers have given you one of their own.";

				//final EntityFCreeper entCocoon = new EntityFCreeper(world);//, entityplayer.posX, entityplayer.posY, entityplayer.posZ, entityplayer.rotationYaw - 90F);//,itemstack.getItemDamage());
//				entCocoon.posX = x - MathHelper.cos(entCocoon.rotationYaw / 180F * 3.141593F) * 0.16F;
//				entCocoon.posY = y + 1;
//				entCocoon.posZ = z;
//				entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);
//				entCocoon.setRotationYawHead(entCocoon.rotationYaw - 90F);
//				//entCocoon.spiderfriend = true;
//				world.spawnEntityInWorld(entCocoon);
			}

			final Random rm = new Random();
			for(int ok = 0; ok < 32; ok++)
			{
				world.spawnParticle("reddust", x +(rm.nextFloat() - 0.5f), y + (rm.nextFloat() - 0.5f), z + (rm.nextFloat() - 0.5f), (rm.nextFloat()-0.5f)*3f, (rm.nextFloat()-0.5f)*3f, (rm.nextFloat()-0.5f)*3f);
			}
			world.setBlockToAir(x,y,z);
		}
		
		else
		{
			world.setBlockMetadataWithNotify(x,y,z,meta, 0);
		}

		world.scheduleBlockUpdate(x, y, z, this, this.tickRate());
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public void registerBlockIcons(IIconRegister IIconRegister)
	{
		blockIcon = SQR.texx[11];
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if(!super.canPlaceBlockAt(world, x, y, z))
		{
			return false;
		} else
		{
			return this.canBlockStay(world, x, y, z);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i)
	{
		return new TileEntityHBait();
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		if(!this.canBlockStay(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z),1);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if(world.getBlock(x, y - 1, z).getMaterial().isSolid())
		{
			return true;
		}


		return false;
	}

}
