package sqr.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sqr.core.SQR;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBeeHive extends Block
{
	private IIcon topIcon;
	private IIcon sideIcon;
	
	public BlockBeeHive() 
	{
		super(Material.ground);
		
		final String name = "beehive";
		setBlockName(name);
		setBlockTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
		setHardness(3.0F);
		
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public Item getItemDropped(int unknown, Random random, int unknown2) 
	{
		return null;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) 
    {
		super.updateTick(world, x, y, z, random);
		
		if (world.getClosestPlayer((double)x, (double)y, (double)z, 16.0D) != null)
		{
			//TODO Spawn bee.
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) 
	{
        return side == 1 || side == 0 ? this.topIcon : this.sideIcon;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{
		sideIcon = iconRegister.registerIcon("sqr:beehive-side");
		topIcon = iconRegister.registerIcon("sqr:beehive-top");
	}
}
