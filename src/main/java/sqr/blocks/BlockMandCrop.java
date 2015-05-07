package sqr.blocks;

import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sqr.core.minecraft.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMandCrop extends BlockCrops
{
	private IIcon icon;
	
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
    	return icon;
    }

    protected Item func_149866_i()
    {
        return ModItems.mandragoraSeeds;
    }

    protected Item func_149865_P()
    {
        return ModItems.mandragoraSeeds;
    }

    @Override
	public void updateTick(World world, int x, int y, int z, Random random) 
    {
		super.updateTick(world, x, y, z, random);
		
		if (world.getBlockMetadata(x, y, z) >= 7)
		{
			world.setBlockToAir(x, y, z);
			//TODO Spawn mandragora.
		}
	}

	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	icon = iconRegister.registerIcon("sqr:mand_crop");
    }
}
