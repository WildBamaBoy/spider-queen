package sq.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import radixcore.constant.Time;
import radixcore.util.RadixLogic;
import sq.core.SpiderCore;
import sq.entity.creature.EntityAnt;
import sq.enums.EnumAntType;

/**
 * The ant hill ticks every fifteen seconds, and on each tick, an ant can spawn.
 */
public class BlockAntHill extends Block
{
	public BlockAntHill() 
	{
		super(Material.ground);
		
		final String name = "anthill";
		setCreativeTab(SpiderCore.getCreativeTab());
		setTickRandomly(true);
		setHardness(1.0F);
		
		GameRegistry.registerBlock(this, name);
	}
	
	public int tickRate()
	{
		return Time.SECOND * 15;
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) 
    {
		super.updateTick(world, pos, state, random);
		
		//Grab the nearest player within 16 blocks.
		final EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 16.0D);

		if (player != null) //Don't bother spawning anything if there are no players nearby.
		{
			//Count the number of ants that are already around this block.
			List<Entity> nearbyEntities = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(world, pos.getX(), pos.getY(), pos.getZ(), 16);
			int nearbyAnts = 0;
			
			for (Entity entity : nearbyEntities)
			{
				if (entity instanceof EntityAnt)
				{
					nearbyAnts++;
				}
			}
			
			//Make sure that we're not going to be over the spawn cap, and spawn the ant if so.
			if (nearbyAnts < SpiderCore.getConfig().antSpawnCap)
			{
				EntityAnt ant = new EntityAnt(world, EnumAntType.BLACK);
				ant.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), (float)random.nextInt(360) + 1, 0.0F);
				world.spawnEntityInWorld(ant);
			}
		}
		
		//On each tick, schedule another update 15 seconds later.
		world.scheduleBlockUpdate(pos, this, tickRate(), 1);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
    public boolean isNormalCube()
    {
    	return false;
    }
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return null;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) 
	{
		return null;
	}

}
