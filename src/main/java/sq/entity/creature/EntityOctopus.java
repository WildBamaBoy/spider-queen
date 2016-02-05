package sq.entity.creature;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import radixcore.util.BlockHelper;

/**
 * The octopus is simply a filler water creature.
 */
public class EntityOctopus extends EntityWaterMob
{
	public EntityOctopus(World world) 
	{
		super(world);
	}

	@Override
	public boolean getCanSpawnHere() 
	{
		Block block = BlockHelper.getBlock(worldObj, (int)posX, (int)posY, (int)posZ);
		return block == Blocks.water;
	}
}

