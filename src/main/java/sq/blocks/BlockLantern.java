package sq.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sq.core.minecraft.ModItems;

/**
 * The lantern is simply a new placeable light block.
 */
public class BlockLantern extends Block
{
	private static IIcon sideIcon;
	private static IIcon topIcon;
	
	public BlockLantern() 
	{
		super(Material.circuits);
		
		final String name = "lantern";
		setBlockName(name);
		setBlockTextureName("sq:" + name);
		setTickRandomly(true);
		setHardness(3.0F);
		
		GameRegistry.registerBlock(this, name + "-block");
	}
	
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
    {
		 this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
    }
	
	@Override
	public int getLightValue() 
	{
		return 7;
	}
	
	@Override
	public IIcon getIcon(int side, int meta) 
	{
		return side == 0 || side == 1 ? topIcon : sideIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{
		sideIcon = iconRegister.registerIcon("sq:lantern-side");
		topIcon = iconRegister.registerIcon("sq:lantern-top");
	}
	
	@Override
	public Item getItemDropped(int unknown, Random random, int unknown2) 
	{
		//Return the lantern item when this block is broken.
		return ModItems.lantern;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) 
	{
		//The "block" is used only in the world. Return our item when middle clicking.
		return new ItemStack(ModItems.lantern);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return 0;
	}
}
