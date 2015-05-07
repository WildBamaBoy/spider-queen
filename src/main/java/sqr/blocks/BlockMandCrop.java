package sqr.blocks;

import sqr.core.minecraft.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMandCrop extends BlockCrops
{
	private IIcon icons[];
	
	public BlockMandCrop()
	{
		super();
		
		final String name = "mandragora";
		setBlockName(name);
		
		GameRegistry.registerBlock(this, name);
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	return icons[meta];
    }

    protected Item func_149866_i()
    {
        return ModItems.mandragoraSeeds;
    }

    protected Item func_149865_P()
    {
        return ModItems.mandragoraSeeds;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	icons = new IIcon[3];

        for (int i = 0; i < icons.length; ++i)
        {
            icons[i] = iconRegister.registerIcon("sqr:mand_stage_" + i);
        }
    }
}
