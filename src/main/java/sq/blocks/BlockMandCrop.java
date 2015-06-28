package sq.blocks;

import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sq.core.minecraft.ModItems;
import sq.entity.EntityFriendlyMandragora;
import sq.entity.EntityMandragora;
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
			
			//Find the nearest player.
			Entity entityToSpawn = null;
			EntityPlayer player = world.getClosestPlayer(x, y, z, 16.0D);
			
			if (player != null)
			{
				entityToSpawn = new EntityFriendlyMandragora(world, player);
			}
			
			else
			{
				entityToSpawn = new EntityMandragora(world);
			}
			
			entityToSpawn.setPosition(x, y, z);
			world.spawnEntityInWorld(entityToSpawn);
		}
	}

	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	icon = iconRegister.registerIcon("sq:mand_crop");
    }
}
