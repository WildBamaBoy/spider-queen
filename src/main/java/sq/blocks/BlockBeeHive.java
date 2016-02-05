package sq.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import radixcore.constant.Time;
import radixcore.math.Point3D;
import radixcore.util.BlockHelper;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.ReputationHandler;
import sq.core.SpiderCore;
import sq.core.minecraft.ModItems;
import sq.entity.creature.EntityBee;
import sq.enums.EnumBeeType;

/**
 * The beehive works just like the ant hill. Every 15 seconds, a bee can spawn from it.
 * When destroyed, it yields a random amount of nectar and lowers the player's reputation
 * with bees.
 */
public class BlockBeeHive extends Block
{
	private IIcon topIcon;
	private IIcon sideIcon;

	public BlockBeeHive() 
	{
		super(Material.ground);

		final String name = "beehive";
		setBlockName(name);
		setBlockTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setTickRandomly(true);
		setHardness(3.0F);

		GameRegistry.registerBlock(this, name);
	}

	public int tickRate()
	{
		return Time.SECOND * 15;
	}

	@Override
	public Item getItemDropped(int unknown, Random random, int unknown2) 
	{
		return ModItems.nectar;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) 
	{
		return random.nextInt(8) + 1;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int posX, int posY, int posZ, int meta) 
	{
		//The block was actively destroyed by a player, so grab the nearest one to blame.
		//It *should* be the person who destroyed it.
		EntityPlayer player = world.getClosestPlayer(posX, posY, posZ, 16.0D);
		
		if (player != null) //Reputation requires a player instance, always.
		{
			boolean hasChangedReputation = false;
			
			//The reputation handler also requires an instance of the entity who belongs to the group
			//that the reputation will be modified for. Search for bees around the player.
			for (Entity entity : RadixLogic.getAllEntitiesOfTypeWithinDistance(EntityBee.class, player, 16))
			{
				//Only change the reputation once by setting hasChangedReputation.
				if (!hasChangedReputation && !world.isRemote)
				{
					ReputationHandler.onReputationChange(player, (EntityBee) entity, -1);
					hasChangedReputation = true;
				}
				
				//Then have all bees set the player as their target.
				EntityBee bee = (EntityBee)entity;
				bee.setTarget(player);
			}
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) 
	{
		super.updateTick(world, x, y, z, random);

		//Grab the nearest player.
		final EntityPlayer player = world.getClosestPlayer(x, y, z, 16.0D);

		if (player != null) //Don't bother spawning if there's no players around.
		{
			//Count the bees in the area.
			List<Entity> nearbyEntities = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(world, x, y, z, 16);
			int nearbyBees = 0;

			for (Entity entity : nearbyEntities)
			{
				if (entity instanceof EntityBee)
				{
					nearbyBees++;
				}
			}

			//If we're not over the cap, we can spawn.
			if (nearbyBees < SpiderCore.getConfig().beeSpawnCap)
			{
				//Build a list of points that are safe to spawn at (no blocks at that location)
				List<Point3D> safeSpawnAreas = new ArrayList<Point3D>();

				//Get blocks from [-5, -2, -5] to [5, 2, 5]
				for (int xMov = -5; xMov < 5; xMov++)
				{
					for (int zMov = -5; zMov < 5; zMov++)
					{
						for (int yMov = -2; yMov < 2; yMov++)
						{
							Point3D newPoint = new Point3D(xMov, yMov, zMov);

							if (BlockHelper.getBlock(world, x + newPoint.iPosX, y + newPoint.iPosY, z + newPoint.iPosZ) == Blocks.air)
							{
								safeSpawnAreas.add(newPoint);
							}
						}
					}
				}
				
				//Don't bother spawning if there's no safe area.
				if (!safeSpawnAreas.isEmpty())
				{
					//Generate a type. Warriors and gatherers are essentially 50/50 in terms of commonality.
					EnumBeeType type = world.rand.nextBoolean() ? EnumBeeType.GATHERER : EnumBeeType.WARRIOR;
					
					//5% chance of the type being a queen.
					if (RadixLogic.getBooleanWithProbability(5))
					{
						type = EnumBeeType.QUEEN;
					}
					
					//Create the new bee and grab a random safe spawn area.
					EntityBee bee = new EntityBee(world, type);					
					Point3D spawnMovement = safeSpawnAreas.get(RadixMath.getNumberInRange(0, safeSpawnAreas.size() - 1));
					
					//Set the bee's rotation relative to the spawn area and spawn.
					bee.setPositionAndRotation((double) x + spawnMovement.iPosX + 0.5F, (double) y + spawnMovement.iPosY, (double) z + spawnMovement.iPosZ + 0.5F, (float)random.nextInt(360) + 1, 0.0F);
					world.spawnEntityInWorld(bee);
				}
			}
		}

		//On ecah tick, schedule a new update 15 seconds later.
		world.scheduleBlockUpdate(x, y, z, this, tickRate());
	}

	@Override
	public IIcon getIcon(int side, int meta) 
	{
		//Side 1 and side 0 need to use the top icon.
		return side == 1 || side == 0 ? this.topIcon : this.sideIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{
		sideIcon = iconRegister.registerIcon("sq:beehive-side");
		topIcon = iconRegister.registerIcon("sq:beehive-top");
	}
}
