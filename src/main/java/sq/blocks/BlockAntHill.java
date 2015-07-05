package sq.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import radixcore.constant.Time;
import radixcore.util.RadixLogic;
import sq.core.SpiderCore;
import sq.entity.creature.EntityAnt;
import sq.enums.EnumAntType;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockAntHill extends Block
{
	public BlockAntHill() 
	{
		super(Material.ground);
		
		final String name = "anthill";
		setBlockName(name);
		setBlockTextureName("sq:" + name);
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
	public void updateTick(World world, int x, int y, int z, Random random) 
    {
		super.updateTick(world, x, y, z, random);
		
		final EntityPlayer player = world.getClosestPlayer((double)x, (double)y, (double)z, 16.0D);

		if (player != null)
		{
			List<Entity> nearbyEntities = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(world, x, y, z, 16);
			int nearbyAnts = 0;
			
			for (Entity entity : nearbyEntities)
			{
				if (entity instanceof EntityAnt)
				{
					nearbyAnts++;
				}
			}
			
			if (nearbyAnts < SpiderCore.getConfig().antSpawnCap)
			{
				EntityAnt ant = new EntityAnt(world, EnumAntType.BLACK);
				ant.setPositionAndRotation((double) x, (double) y, (double) z, (float)random.nextInt(360) + 1, 0.0F);
				world.spawnEntityInWorld(ant);
			}
		}
		
		world.scheduleBlockUpdate(x, y, z, this, tickRate());
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
	}
}
