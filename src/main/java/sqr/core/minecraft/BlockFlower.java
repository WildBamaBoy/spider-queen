package sqr.core.minecraft;
//package sqr.block;
//
//import java.util.Random;
//
//import net.minecraft.block.BlockContainer;
//import net.minecraft.block.material.Material;
//import net.minecraft.init.Blocks;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.world.World;
//import sqr.tile.TileEntityFlower;
//
//public class BlockFlower extends BlockContainer
//{
//
//	protected BlockFlower(Material material)
//	{
//		super(par3Material);
//		
//		this.setTickRandomly(true);
//		final float f = 0.2F;
//		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3F, 0.5F + f);
//	}
//
//	protected BlockFlower(int par1, int par2)
//	{
//		this(par1, par2, Material.plants);
//	}
//
//	@Override
//	public boolean canPlaceBlockAt(World world, int x, int y, int z)
//	{
//		return super.canPlaceBlockAt(world, x, y, z) && this.canThisPlantGrowOnThisBlockID(world.getBlock(x, y - 1, z));
//	}
//
//	protected boolean canThisPlantGrowOnThisBlockID(int i)
//	{
//		return i == Blocks.grass || i == Blocks.dirt || i == Blocks.tilledField;
//	}
//
//	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
//	{
//		super.onNeighborBlockChange(world, x, y, z, l);
//		this.func_268_h(world, x, y, z);
//	}
//
//	@Override
//	public void updateTick(World world, int x, int y, int z, Random random)
//	{
//		this.func_268_h(world, x, y, z);
//	}
//
//	protected final void func_268_h(World world, int x, int y, int z)
//	{
//		if(!this.canBlockStay(world, x, y, z))
//		{
//			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z),1);
//			world.setBlockToAir(x, y, z);
//		}
//	}
//
//	@Override
//	public boolean canBlockStay(World world, int x, int y, int z)
//	{
//		return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlock(x, y - 1, z));
//	}
//
//	@Override
//	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
//	{
//		return null;
//	}
//
//	@Override
//	public boolean isOpaqueCube()
//	{
//		return false;
//	}
//
//	@Override
//	public boolean renderAsNormalBlock()
//	{
//		return false;
//	}
//
//	@Override
//	public TileEntity createNewTileEntity(World world, int i)
//	{
//		return new TileEntityFlower();
//	}
//
//	@Override
//	public int getRenderType()
//	{
//		return 1;
//	}
//}
