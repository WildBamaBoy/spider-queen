package sq.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import radixcore.util.BlockHelper;
import sq.core.minecraft.ModItems;
import sq.entity.creature.EntityMandragora;
import sq.entity.friendly.EntityFriendlyMandragora;

/**
 * The mandragora crop has seven stages of growth, and on the final stage, a new
 * mandragora will appear in the place of the crop. If there's a player nearby,
 * it will be friendly to that player. If there are no players nearby, it will
 * be a hostile mandragora.
 */
public class BlockMandCrop extends BlockCrops
{
	private IIcon[] icons = new IIcon[4];
	
	public BlockMandCrop()
	{
		super();
		
		final String name = "mandragora";
		setBlockName(name);
		
		GameRegistry.registerBlock(this, name);
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	//We only switch icons every other growth cycle.
    	switch (meta)
    	{
    	case 0:
    	case 1: return icons[0];
    	case 2:
    	case 3: return icons[1];
    	case 4:  
    	case 5: return icons[2];
    	case 6:
    	case 7: return icons[3];
    	}
    	
    	return icons[0];
    }

    /**
     * Return the item dropped when this crop is destroyed while fully grown.
     */
    @Override
	protected Item func_149866_i()
    {
        return ModItems.mandragoraSeeds;
    }

    /**
     * Return the item dropped when this crop is destroyed while partially grown.
     */
    @Override
	protected Item func_149865_P()
    {
        return ModItems.mandragoraSeeds;
    }

    @Override
	public void updateTick(World world, int x, int y, int z, Random random) 
    {
		super.updateTick(world, x, y, z, random);
		
		//Only grow if we're not fully grown (meta >= 7)
		if (BlockHelper.getBlockMetadata(world, x, y, z) >= 7)
		{
			//Get rid of the crop block as it has sprouted into a mandragora.
			world.setBlockToAir(x, y, z);
			
			//Find a nearby player.
			Entity entityToSpawn = null;
			EntityPlayer player = world.getClosestPlayer(x, y, z, 16.0D);
			
			//If we found one, we're friendly to that player.
			if (player != null)
			{
				entityToSpawn = new EntityFriendlyMandragora(world, player);
			}
			
			else //If not, we're hostile.
			{
				entityToSpawn = new EntityMandragora(world);
			}
			
			//Set the position to the crop's position and spawn.
			entityToSpawn.setPosition(x, y, z);
			world.spawnEntityInWorld(entityToSpawn);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	icons[0] = iconRegister.registerIcon("sq:mand_crop_1");
    	icons[1] = iconRegister.registerIcon("sq:mand_crop_2");
    	icons[2] = iconRegister.registerIcon("sq:mand_crop_3");
    	icons[3] = iconRegister.registerIcon("sq:mand_crop_4");
    }
}
