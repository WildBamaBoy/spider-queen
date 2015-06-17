package sq.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import radixcore.constant.Time;
import radixcore.math.Point3D;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.core.minecraft.ModItems;
import sq.entity.EntityBee;
import sq.enums.EnumBeeType;
import cpw.mods.fml.common.registry.GameRegistry;

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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
	{
		return null;
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
	public void updateTick(World world, int x, int y, int z, Random random) 
	{
		super.updateTick(world, x, y, z, random);

		final EntityPlayer player = world.getClosestPlayer((double)x, (double)y, (double)z, 16.0D);

		if (player != null)
		{
			List<Entity> nearbyEntities = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(world, x, y, z, 16);
			int nearbyBees = 0;

			for (Entity entity : nearbyEntities)
			{
				if (entity instanceof EntityBee)
				{
					nearbyBees++;
				}
			}

			if (nearbyBees < SpiderCore.getConfig().antSpawnCap)
			{
				//getSafeSpawnArea()
				List<Point3D> safeSpawnAreas = new ArrayList<Point3D>();

				for (int xMov = -5; xMov < 5; xMov++)
				{
					for (int zMov = -5; zMov < 5; zMov++)
					{
						for (int yMov = -2; yMov < 2; yMov++)
						{
							Point3D newPoint = new Point3D(xMov, yMov, zMov);

							if (world.getBlock(x + newPoint.iPosX, y + newPoint.iPosY, z + newPoint.iPosZ) == Blocks.air)
							{
								safeSpawnAreas.add(newPoint);
							}
						}
					}
				}
				////
				
				if (!safeSpawnAreas.isEmpty())
				{
					EnumBeeType type = world.rand.nextBoolean() ? EnumBeeType.GATHERER : EnumBeeType.WARRIOR;
					EntityBee bee = new EntityBee(world, type);
					
					Point3D spawnMovement = safeSpawnAreas.get(RadixMath.getNumberInRange(0, safeSpawnAreas.size() - 1));
					bee.setPositionAndRotation((double) x + spawnMovement.iPosX + 0.5F, (double) y + spawnMovement.iPosY, (double) z + spawnMovement.iPosZ + 0.5F, (float)random.nextInt(360) + 1, 0.0F);
					world.spawnEntityInWorld(bee);
				}
			}
		}

		world.scheduleBlockUpdate(x, y, z, this, tickRate());
	}

	@Override
	public IIcon getIcon(int side, int meta) 
	{
		return side == 1 || side == 0 ? this.topIcon : this.sideIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{
		sideIcon = iconRegister.registerIcon("sq:beehive-side");
		topIcon = iconRegister.registerIcon("sq:beehive-top");
	}
}
