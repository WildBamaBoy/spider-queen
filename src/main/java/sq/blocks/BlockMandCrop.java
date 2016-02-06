package sq.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
	public BlockMandCrop()
	{
		super();

		final String name = "mandragora";		
		GameRegistry.registerBlock(this, name);
	}

    @Override
    public List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(ModItems.mandragoraSeeds));
        return ret;
    }

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) 
	{
		super.updateTick(worldIn, pos, state, rand);

		//Only grow if we're not fully grown (meta >= 7)
		if (BlockHelper.getBlockMetadata(worldIn, pos.getX(), pos.getY(), pos.getZ()) >= 7)
		{
			//Get rid of the crop block as it has sprouted into a mandragora.
			worldIn.setBlockToAir(pos);

			//Find a nearby player.
			Entity entityToSpawn = null;
			EntityPlayer player = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 16.0D);

			//If we found one, we're friendly to that player.
			if (player != null)
			{
				entityToSpawn = new EntityFriendlyMandragora(worldIn, player);
			}

			else //If not, we're hostile.
			{
				entityToSpawn = new EntityMandragora(worldIn);
			}

			//Set the position to the crop's position and spawn.
			entityToSpawn.setPosition(pos.getX(), pos.getY(), pos.getZ());
			worldIn.spawnEntityInWorld(entityToSpawn);
		}
	}
}
