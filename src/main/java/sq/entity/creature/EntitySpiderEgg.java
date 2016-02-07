package sq.entity.creature;

import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import radixcore.math.Point3D;
import radixcore.util.BlockHelper;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.core.minecraft.ModAchievements;
import sq.enums.EnumSpiderType;

/**
 * The spider egg is the entity placed by using the spider egg item on a block. It will hatch into a spider after
 * a random amount of time. Depending on any cocoons nearby, the type of spider that will hatch will change.
 */
public class EntitySpiderEgg extends EntityCreature
{
	private UUID	owner = new UUID(0, 0);
	private int		timeUntilEggHatch;

	public EntitySpiderEgg(World world)
	{
		super(world);
		setSize(0.15F, 0.15F);
	}

	public EntitySpiderEgg(World world, UUID owner)
	{
		super(world);
		this.owner = owner;
		this.renderDistanceWeight = 50.0F;
		setSize(0.15F, 0.15F);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		timeUntilEggHatch = RadixMath.getNumberInRange(500, 5000);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public boolean isAIDisabled()
	{
		return true;
	}

//	@Override
//	protected boolean isMovementCeased()
//	{
//		return true;
//	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!worldObj.isRemote)
		{
			if (timeUntilEggHatch <= 0)
			{
				final EntityCocoon cocoonToConsume = getConsumableCocoon();
				final EntitySpiderEx spider = consumeCocoon(cocoonToConsume);
				doHatch(spider);
			}

			else
			{
				timeUntilEggHatch--;
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		final Entity entity = damageSource.getEntity();

		if (entity instanceof EntityPlayer && !entity.worldObj.isRemote)
		{
			setDead();
			entityDropItem(new ItemStack(SpiderCore.getItems().spiderEgg), entity.worldObj.rand.nextFloat());
		}

		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("timeUntilEggHatch", timeUntilEggHatch);
		nbt.setLong("ownerMSB", owner.getMostSignificantBits());
		nbt.setLong("ownerLSB", owner.getLeastSignificantBits());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		super.readEntityFromNBT(nbt);
		timeUntilEggHatch = nbt.getInteger("timeUntilEggHatch");
		owner = new UUID(nbt.getLong("ownerMSB"), nbt.getLong("ownerLSB"));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		final int sqrtDistance = (int)Math.sqrt(distance);
		return sqrtDistance < 50;
	}

	private EntityCocoon getConsumableCocoon()
	{
		//Search for cocoons up to 5 blocks away. Return the nearest one that is not eaten.
		final List<Entity> nearbyCocoons = RadixLogic.getAllEntitiesOfTypeWithinDistance(EntityCocoon.class, this, 5);
		EntityCocoon nearestCocoon = null;
		double lowestDistance = 100D;

		for (final Entity entity : nearbyCocoons)
		{
			EntityCocoon cocoon = (EntityCocoon)entity;
			final double distanceToCurrentEntity = RadixMath.getDistanceToEntity(this, cocoon);

			if (!cocoon.isEaten() && distanceToCurrentEntity < lowestDistance)
			{
				lowestDistance = distanceToCurrentEntity;
				nearestCocoon = cocoon;
			}
		}

		return nearestCocoon;
	}

	private EntitySpiderEx consumeCocoon(EntityCocoon cocoonToConsume)
	{
		EntitySpiderEx spider;

		//No cocoon, so the spider is a wimpy spider.
		if (cocoonToConsume == null)
		{
			spider = new EntitySpiderEx(worldObj, owner, EnumSpiderType.WIMPY);
		}

		else
		{
			//There is a cocoon, so look up the spider type that this cocoon yields.
			cocoonToConsume.setEaten(true);
			spider = new EntitySpiderEx(worldObj, owner, cocoonToConsume.getCocoonType().getSpiderTypeYield());
		}

		return spider;
	}

	protected void doHatch(EntitySpiderEx spider)
	{
		try
		{
			final EntityPlayer player = worldObj.getPlayerEntityByUUID(owner);

			//Find a safe spawn point.
			final Block spawnBlock = BlockHelper.getBlock(worldObj, (int)posX,(int)posY,(int)posZ);
			Point3D spawnPoint = new Point3D(posX, posY, posZ);

			if (BlockHelper.getBlock(worldObj, spawnPoint.iPosX + 1, spawnPoint.iPosY, spawnPoint.iPosZ) == Blocks.air) { spawnPoint = new Point3D(posX + 1, posY, posZ); }
			else if (BlockHelper.getBlock(worldObj, spawnPoint.iPosX, spawnPoint.iPosY, spawnPoint.iPosZ + 1) == Blocks.air) { spawnPoint = new Point3D(posX, posY, posZ + 1); }
			else if (BlockHelper.getBlock(worldObj, spawnPoint.iPosX - 1, spawnPoint.iPosY, spawnPoint.iPosZ) == Blocks.air) { spawnPoint = new Point3D(posX - 1, posY, posZ); }
			else if (BlockHelper.getBlock(worldObj, spawnPoint.iPosX, spawnPoint.iPosY, spawnPoint.iPosZ - 1) == Blocks.air) { spawnPoint = new Point3D(posX, posY, posZ - 1); }

			spider.setLocationAndAngles(spawnPoint.iPosX, spawnPoint.iPosY, spawnPoint.iPosZ, rotationYaw, rotationPitch);

			//Spawn the spider and remove the egg.
			worldObj.spawnEntityInWorld(spider);
			setDead();

			//Trigger needed achievements.
			if (player != null)
			{
				if (spider.getSpiderType() == EnumSpiderType.WIMPY)
				{
					player.triggerAchievement(SpiderCore.getAchievements().hatchSpider);
				}

				else
				{
					player.triggerAchievement(SpiderCore.getAchievements().hatchSpiderByCocoon);
					
					Achievement specialAchievement = null;
					
					switch (spider.getSpiderType())
					{
					case BOOM: specialAchievement = SpiderCore.getAchievements().hatchBoomSpider;
						break;
					case ENDER: specialAchievement = SpiderCore.getAchievements().hatchEnderSpider;
						break;
					case NOVA: specialAchievement = SpiderCore.getAchievements().hatchNovaSpider;
						break;
					case PACK: specialAchievement = SpiderCore.getAchievements().hatchPackSpider;
						break;
					case RIDER: specialAchievement = SpiderCore.getAchievements().hatchRiderSpider;
						break;
					case SLINGER: specialAchievement = SpiderCore.getAchievements().hatchSlingerSpider;
						break;
					case TANK: specialAchievement = SpiderCore.getAchievements().hatchTankSpider;
						break;
					default:
						break;
					
					}
					
					if (specialAchievement != null)
					{
						player.triggerAchievement(specialAchievement);
					}
				}
			}
		}

		catch (NullPointerException e)
		{
			//Happens when player is null - not logged in.
		}
	}
}