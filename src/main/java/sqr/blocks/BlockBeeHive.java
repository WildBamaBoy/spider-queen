package sqr.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import sqr.core.SQR;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

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
		
		GameRegistry.registerBlock(this, name);
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
