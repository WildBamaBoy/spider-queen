package sq.entity;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.constant.Time;
import radixcore.math.Point3D;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.Constants;
import sq.core.SpiderCore;
import sq.core.minecraft.ModBlocks;
import sq.core.minecraft.ModItems;
import sq.enums.EnumSpiderType;
import sq.util.Utils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySpiderEx extends EntityCreature implements IWebClimber, IEntityAdditionalSpawnData
{
	private int abilityCounter;
	private int abilityThreshold;
	private EnumSpiderType spiderType = EnumSpiderType.NONE;
	private UUID owner;
	private EntityLivingBase target;

	public EntitySpiderEx(World world) 
	{
		super(world);
		
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.6D));
		tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.4D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

		this.setLevel(1);
		this.updateEntityAttributes();
	}

	public EntitySpiderEx(World world, UUID owner, EnumSpiderType type)
	{
		this(world);
		this.owner = owner;
		this.spiderType = type;
		this.updateEntityAttributes();
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		updateAbility();

		if (!worldObj.isRemote)
		{
			setBesideClimbableBlock(isCollidedHorizontally);

			if (getHealth() > 0)
			{
				updateEntityAttributes();
			}

			if (!tryFollowOwnerPlayer())
			{
				if (target != null && !target.isDead)
				{
					attackEntity(target, 3.5F);
				}

				else
				{
					target = findEntityToAttack();
				}

				tryMoveToSpiderRod();
			}
		}
	}

	@Override
	public void onDeath(DamageSource source) 
	{
		super.onDeath(source);

		if (spiderType == EnumSpiderType.BOOM)
		{
			worldObj.createExplosion(this, posX, posY, posZ, 5.0F, false);
		}
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (owner.equals(entityPlayer.getCommandSenderName()))
		{
			if (spiderType == EnumSpiderType.PACK)
			{
				entityPlayer.openGui(SpiderCore.getInstance(), Constants.ID_GUI_INVENTORY, worldObj, (int) posX, (int) posY, (int) posZ);
			}

			else if (spiderType == EnumSpiderType.RIDER)
			{
				entityPlayer.rotationYaw = rotationYaw;
				entityPlayer.rotationPitch = rotationPitch;

				if (!worldObj.isRemote)
				{
					entityPlayer.mountEntity(this);
				}
			}
		}

		return true;
	}

	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
	{
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("spiderType", spiderType.getId());
		nbt.setInteger("abilityCounter", abilityCounter);
		nbt.setInteger("abilityThreshold", abilityThreshold);
		nbt.setLong("ownerMSB", owner.getMostSignificantBits());
		nbt.setLong("ownerLSB", owner.getLeastSignificantBits());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		spiderType = EnumSpiderType.byId(nbt.getInteger("spiderType"));
		owner = new UUID(nbt.getLong("ownerMSB"), nbt.getLong("ownerLSB"));
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeInt(spiderType.getId());
	}

	@Override
	public void readSpawnData(ByteBuf buffer)
	{
		spiderType = EnumSpiderType.byId(buffer.readInt());
	}

	@Override
	protected Item getDropItem()
	{
		return Items.string;
	}

	@Override
	protected void dropFewItems(boolean hitByPlayerRecently, int lootingLevel)
	{
		super.dropFewItems(hitByPlayerRecently, lootingLevel);

		if (hitByPlayerRecently && (rand.nextInt(3) == 0 || rand.nextInt(1 + lootingLevel) > 0))
		{
			dropItem(Items.spider_eye, 1);
		}
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.spider.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.spider.say";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.spider.death";
	}

	@Override
	protected void attackEntity(Entity entity, float damage) 
	{
		super.attackEntity(entity, damage);

		if (entity instanceof EntityLivingBase && spiderType == EnumSpiderType.TANK)
		{
			PotionEffect poison = new PotionEffect(Potion.poison.id, Time.SECOND * 5 * getLevel());
			EntityLivingBase entityLiving = (EntityLivingBase)entity;

			if (entityLiving.isPotionApplicable(poison))
			{
				entityLiving.addPotionEffect(poison);
			}
		}
	}

	@Override
	public boolean isOnLadder()
	{
		final boolean returnBool = isBesideClimbableBlock();

		if (returnBool)
		{
			limbSwingAmount += 0.1;
			motionY = getLevel() * 0.115F;
		}

		return returnBool;
	}

	@Override
	public void setInWeb()
	{
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potionEffect)
	{
		return potionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(potionEffect);
	}

	public EnumSpiderType getSpiderType()
	{
		return spiderType;
	}

	private boolean isBesideClimbableBlock()
	{
		return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	private void setBesideClimbableBlock(boolean setBoolean)
	{
		byte value = dataWatcher.getWatchableObjectByte(16);

		if (setBoolean == true)
		{
			value = (byte) (value | 1);
		}
		else
		{
			value &= -2;
		}

		dataWatcher.updateObject(16, Byte.valueOf(value));
	}

	private void updateEntityAttributes()
	{
		this.abilityThreshold = calculateAbilityThreshold();
		
		if (spiderType == EnumSpiderType.SLINGER)
		{
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
		}

		else if (spiderType == EnumSpiderType.NOVA)
		{
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.4D);
		}

		else if (spiderType == EnumSpiderType.WIMPY && getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() != 3.0D)
		{
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(3.0D);
			setHealth((float) getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue());
		}

		else if (spiderType == EnumSpiderType.TANK && getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() != 30.0D + getLevel() * 10)
		{
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D + getLevel() * 10);
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
			setHealth((float) getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue());
		}
	}

	private float getAttackDamage()
	{
		switch (spiderType)
		{
		case WIMPY:
			return 0.5F;
		case ENDERMINION:
			return 0.3F;
		case NOVA:
			return 1.0F;
		default:
			return 2.5F + getLevel() / 2;
		}
	}

	private void updateAbility()
	{
		abilityCounter--;

		if (abilityCounter <= 0)
		{
			if (spiderType == EnumSpiderType.BOOM && target != null)
			{
				EntityBoomBall boomBall = new EntityBoomBall(this, target, 5.0F);
				worldObj.spawnEntityInWorld(boomBall);
			}

			else if (spiderType == EnumSpiderType.SLINGER && target != null)
			{
				EntityWebShot webShot = new EntityWebShot(this, target, 5.0F);
				worldObj.spawnEntityInWorld(webShot);
			}

			else if (spiderType == EnumSpiderType.NOVA && RadixLogic.getBooleanWithProbability(20))
			{
				EntitySpiderEx spider = (EntitySpiderEx) RadixLogic.getNearestEntityOfTypeWithinDistance(EntitySpiderEx.class, this, 8);

				if (spider != null && spider.getHealth() < spider.getMaxHealth())
				{
					int healthIncrease = getLevel() * 5;
					spider.setHealth(healthIncrease);
					Utils.spawnParticlesAroundEntityS("heart", this, 6);
					Utils.spawnParticlesAroundEntityS("heart", spider, 6);
				}
			}

			abilityCounter = abilityThreshold;
		}
	}

	private EntityLivingBase findEntityToAttack()
	{
		return null;
//		final List<Entity> entitiesAroundMe = LogicHelper.getAllEntitiesWithinDistanceOfEntity(this, 15);
//		EntityLivingBase closestValidTarget = null;
//		double distanceToTarget = 100D;
//
//		for (final Entity entity : entitiesAroundMe)
//		{
//			final double distanceToThisEntity = getDistanceToEntity(entity);
//
//			if (entity instanceof EntityFakePlayer && canEntityBeSeen(entity) && isHostile || entity instanceof EntityHatchedSpider && isSpiderValidTarget((EntityHatchedSpider) entity) && isHostile || entity instanceof EntityOtherQueen && isQueenValidTarget((EntityOtherQueen) entity) || entity instanceof EntityPlayer && isPlayerValidTarget((EntityPlayer) entity) && distanceToThisEntity < distanceToTarget)
//			{
//				closestValidTarget = (EntityLivingBase) entity;
//				distanceToTarget = distanceToThisEntity;
//			}
//		}
//
//		return closestValidTarget;
	}
	
	private int calculateAbilityThreshold() 
	{
		switch (spiderType)
		{
		case BOOM: return Time.SECOND * 7;
		case SLINGER: return Time.SECOND * 4;
		case NOVA: return Time.SECOND * 3;
		default: return 0;
		}
	}

	private void levelUp()
	{
		if (getLevel() != 3)
		{
			setLevel(getLevel() + 1);
		}
	}

	private boolean tryFollowOwnerPlayer()
	{
		if (owner != null && target == null)
		{
			final EntityPlayer ownerPlayer = worldObj.func_152378_a(owner);

			if (ownerPlayer != null)
			{
				final double distanceToOwner = RadixMath.getDistanceToEntity(this, ownerPlayer);
				final ItemStack currentItemStack = ownerPlayer.inventory.mainInventory[ownerPlayer.inventory.currentItem];

				if (currentItemStack != null && distanceToOwner < 25.0D && currentItemStack.getItem() == ModItems.spiderRod)
				{
					target = null;
					moveToPlayer(ownerPlayer);
				}

				return true;
			}
		}
		
		return false;
	}

	private void tryMoveToSpiderRod()
	{
		final Point3D nearestRod = RadixLogic.getFirstNearestBlock(this, ModBlocks.spiderRod, 10);

		if (nearestRod != null && RadixMath.getDistanceToXYZ(nearestRod.dPosX, nearestRod.dPosY, nearestRod.dPosZ, posX, posY, posZ) > 5.0D)
		{
			getNavigator().tryMoveToXYZ(nearestRod.dPosX, nearestRod.dPosY, nearestRod.dPosZ, 0.4D);
		}
	}

	private void moveToPlayer(EntityPlayer player)
	{
		if (player != null && player.onGround)
		{
			getLookHelper().setLookPositionWithEntity(player, 10.0F, getVerticalFaceSpeed());

			if (getDistanceToEntity(player) > 3.5D)
			{
				final boolean pathSet = getNavigator().tryMoveToEntityLiving(player, 0.4D);
				getNavigator().onUpdateNavigation();
			}
		}
	}
	
	private void setLevel(int value)
	{
		try
		{
			dataWatcher.updateObject(18, value);
		}

		catch (Exception e)
		{
			dataWatcher.addObject(18, value);
		}
	}

	public int getLevel()
	{
		return dataWatcher.getWatchableObjectInt(18);
	}
}
