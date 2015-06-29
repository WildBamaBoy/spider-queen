package sq.entity;

import io.netty.buffer.ByteBuf;

import java.util.List;
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
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import radixcore.constant.Particle;
import radixcore.constant.Time;
import radixcore.inventory.Inventory;
import radixcore.math.Point3D;
import radixcore.util.RadixLogic;
import radixcore.util.RadixMath;
import sq.core.minecraft.ModBlocks;
import sq.core.minecraft.ModItems;
import sq.enums.EnumSpiderType;
import sq.util.Utils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySpiderEx extends EntityCreature implements IWebClimber, IEntityAdditionalSpawnData
{
	private int abilityCounter;
	private int abilityThreshold;
	private int killsUntilLevelUp = RadixMath.getNumberInRange(5, 15);
	private EnumSpiderType spiderType = EnumSpiderType.NONE;
	private UUID owner = new UUID(0, 0);
	private EntityLivingBase target;
	private Inventory inventory;

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

		inventory = new Inventory("Spider Inventory", false, 18);
	}

	public EntitySpiderEx(World world, UUID owner, EnumSpiderType type)
	{
		this(world);
		this.owner = owner;
		this.spiderType = type;
		this.updateEntityAttributes();
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
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
		updateEntityAttributes();
		updateAbility();
		setHitboxSize();

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

				else if (target != null && target.isDead)
				{
					registerKill();
					target = null;
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

		if (spiderType == EnumSpiderType.RIDER && riddenByEntity != null)
		{
			final EntityPlayer player = (EntityPlayer)riddenByEntity;
			player.dismountEntity(this);
			player.ridingEntity = null;
		}
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (owner.equals(entityPlayer.getUniqueID()))
		{
			if (spiderType == EnumSpiderType.PACK)
			{
				entityPlayer.displayGUIChest(inventory);
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
	public void moveEntityWithHeading(float moveStrafe, float moveForward)
	{
		if (riddenByEntity != null)
		{
			final int level = getLevel();

			prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;
			rotationPitch = riddenByEntity.rotationPitch * 0.5F;
			setRotation(rotationYaw, rotationPitch);
			rotationYawHead = renderYawOffset = rotationYaw;

			if (level == 3)
			{
				moveStrafe = ((EntityLivingBase) riddenByEntity).moveStrafing * level * 0.5F / 4;
				moveForward = ((EntityLivingBase) riddenByEntity).moveForward * level * 0.6F / 4;
			}

			else if (level == 2)
			{
				moveStrafe = ((EntityLivingBase) riddenByEntity).moveStrafing * level * 0.4F / 3;
				moveForward = ((EntityLivingBase) riddenByEntity).moveForward * level * 0.5F / 3;
			}

			else
			{
				moveStrafe = ((EntityLivingBase) riddenByEntity).moveStrafing * level * 0.7F / 3;
				moveForward = ((EntityLivingBase) riddenByEntity).moveForward * level * 0.8F / 3;
			}

			if (moveForward <= 0.0F)
			{
				moveForward *= 0.25F;
			}

			stepHeight = 1.0F;
			jumpMovementFactor = getAIMoveSpeed() * 0.1F;

			if (!worldObj.isRemote)
			{
				setAIMoveSpeed((float) getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				super.moveEntityWithHeading(moveStrafe, moveForward);
			}

			prevLimbSwingAmount = limbSwingAmount;
			final double d1 = posX - prevPosX;
			final double d0 = posZ - prevPosZ;
			float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

			if (f4 > 1.0F)
			{
				f4 = 1.0F;
			}

			limbSwingAmount += (f4 - limbSwingAmount) * 0.4F;
			limbSwing += limbSwingAmount;
		}
		else
		{
			stepHeight = 0.5F;
			jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(moveStrafe, moveForward);
		}
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
		nbt.setInteger("killsUntilLevelUp", killsUntilLevelUp);
		nbt.setTag("inventory", inventory.saveInventoryToNBT());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		spiderType = EnumSpiderType.byId(nbt.getInteger("spiderType"));
		abilityCounter = nbt.getInteger("abilityCounter");
		abilityThreshold = nbt.getInteger("abilityThreshold");
		killsUntilLevelUp = nbt.getInteger("killsUntilLevelUp");
		owner = new UUID(nbt.getLong("ownerMSB"), nbt.getLong("ownerLSB"));
		final NBTTagList tagList = nbt.getTagList("inventory", 10);
		inventory.loadInventoryFromNBT(tagList);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeInt(spiderType.getId());

		if (owner != null)
		{
			buffer.writeLong(owner.getMostSignificantBits());
			buffer.writeLong(owner.getLeastSignificantBits());
		}
	}

	@Override
	public void readSpawnData(ByteBuf buffer)
	{
		spiderType = EnumSpiderType.byId(buffer.readInt());
		
		try
		{
			owner = new UUID(buffer.readLong(), buffer.readLong());
		}
		
		catch (IndexOutOfBoundsException e)
		{
			//Ignore.
		}
	}

	@Override
	protected void updateEntityActionState()
	{
		return;
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
	public boolean attackEntityFrom(DamageSource source, float damage) 
	{
		super.attackEntityFrom(source, damage);

		if (source.getSourceOfDamage() instanceof EntityLivingBase)
		{
			setTarget(source.getSourceOfDamage());

			//Alert other spiders that this one is being attacked.
			List<Entity> entities = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(worldObj, posX, posY, posZ, 15);

			for (Entity entity : entities)
			{
				if (entity instanceof EntitySpiderEx)
				{
					EntitySpiderEx spider = (EntitySpiderEx)entity;

					if (spider.owner.equals(owner))
					{
						spider.setTarget(source.getSourceOfDamage());
					}
				}
			}
		}

		return true;
	}

	@Override
	protected void attackEntity(Entity entity, float damage) 
	{
		super.attackEntity(entity, damage);

		double distance = RadixMath.getDistanceToEntity(this, entity);

		if (distance > 3.0F)
		{
			setAttackPath(entity);
		}

		else
		{
			final EntityLivingBase living = (EntityLivingBase)entity;

			if (spiderType == EnumSpiderType.TANK)
			{
				PotionEffect poison = new PotionEffect(Potion.poison.id, Time.SECOND * 5 * getLevel());
				EntityLivingBase entityLiving = (EntityLivingBase)entity;

				if (entityLiving.isPotionApplicable(poison) && !entityLiving.isPotionActive(Potion.poison))
				{
					entityLiving.addPotionEffect(poison);
				}
			}

			entity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackDamage());
		}

		if (onGround && distance < 3.0F)
		{
			final double dX = entity.posX - posX;
			final double dY = entity.posZ - posZ;
			final float f2 = MathHelper.sqrt_double(dX * dX + dY * dY);
			motionX = dX / f2 * 0.5D * 0.8D + motionX * 0.2D;
			motionZ = dY / f2 * 0.5D * 0.8D + motionZ * 0.2D;
			motionY = 0.4D;
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

	@Override
	public String getCommandSenderName() 
	{
		return spiderType.getFriendlyName() + " Spider";
	}

	@Override
	public void setTarget(Entity entity) 
	{
		if (entity instanceof EntityPlayer) //Check for player attacking their own spiders.
		{
			if (entity.getPersistentID().equals(owner))
			{
				return;
			}
		}

		if (entity instanceof EntitySpiderEx)
		{
			EntitySpiderEx spider = (EntitySpiderEx)entity;

			if (spider.owner.equals(this.owner))
			{
				return;
			}
		}

		else if (entity instanceof EntityCocoon)
		{
			return;
		}

		if (entity instanceof EntityLivingBase)
		{
			target = (EntityLivingBase) entity;
		}
	}

	@Override
	protected boolean canDespawn() 
	{
		return false;
	}

	public EnumSpiderType getSpiderType()
	{
		return spiderType;
	}

	public int getLevel()
	{
		return dataWatcher.getWatchableObjectInt(18);
	}

	public UUID getOwner()
	{
		return owner;
	}

	public void registerKill()
	{
		killsUntilLevelUp--;

		if (killsUntilLevelUp <= 0)
		{
			levelUp();
			killsUntilLevelUp = RadixMath.getNumberInRange(5, 15);
		}
	}

	public void levelUp()
	{
		if (getLevel() != 3)
		{
			worldObj.playSoundAtEntity(this, "random.levelup", 0.75F, 1.0F);
			Utils.spawnParticlesAroundEntityS(Particle.REDSTONE, this, 16);
			setLevel(getLevel() + 1);
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

	private void setHitboxSize()
	{
		float width = 1.0F;
		float height = 1.0F;

		switch (spiderType)
		{
		case BOOM: width = 1.2F; height = 1.3F;	break;
		case ENDER: width = 1.3F; height = 1.4F; break;
		case NORMAL: width = 1.7F; height = 0.9F; break;
		case NOVA: width = 1.1F; height = 0.65F; break;
		case PACK: width = 1.6F; height = 0.9F; break;
		case RIDER: width = 1.9F; height = 0.6F; break;
		case SLINGER: width = 1.6F; height = 1.0F; break;
		case TANK: width = 1.8F; height = 1.0F; break;
		case WIMPY: width = 0.9F; height = 0.5F; break;			
		}

		if (spiderType == EnumSpiderType.TANK)
		{
			if (getLevel() == 2)
			{
				width = 2.1F;
				height = 1.4F;
			}

			else if (getLevel() == 3)
			{
				width = 2.5F;
				height = 1.6F;
			}
		}

		setSize(width, height);
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

		IAttributeInstance moveSpeed = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
		IAttributeInstance maxHealth = getEntityAttribute(SharedMonsterAttributes.maxHealth);

		if (moveSpeed.getBaseValue() != getMovementSpeed())
		{
			moveSpeed.setBaseValue(getMovementSpeed());
		}

		if (maxHealth.getBaseValue() != getMaximumHealth())
		{
			maxHealth.setBaseValue(getMaximumHealth());
			setHealth(getMaximumHealth());
		}
	}

	private void setAttackPath(Entity entityBeingAttacked)
	{
		if (spiderType == EnumSpiderType.SLINGER || spiderType == EnumSpiderType.BOOM)
		{

		}

		else
		{
			getNavigator().setPath(getNavigator().getPathToEntityLiving(entityBeingAttacked), 0.4D);
		}
	}

	private float getAttackDamage()
	{
		switch (spiderType)
		{
		case WIMPY:
			return 0.5F;
		case NOVA:
			return 1.0F;
		default:
			return 2.5F + getLevel() / 2;
		}
	}

	private float getMaximumHealth()
	{
		switch (spiderType)
		{
		case WIMPY:
			return 3F;
		case NOVA:
			return 10F;
		case TANK:
			return 26F + getLevel() * 6;
		default:
			return 16F + getLevel() * 4;
		}
	}

	private double getMovementSpeed() 
	{
		switch (spiderType)
		{
		case SLINGER:
			return 1.0F;
		case NOVA:
			return 1.2F;
		case TANK:
			return 0.6F;
		default:
			return 0.8F;
		}
	}

	private void updateAbility()
	{
		if (!worldObj.isRemote)
		{
			abilityCounter--;

			if (abilityCounter <= 0)
			{
				if (spiderType == EnumSpiderType.BOOM && target != null)
				{
					EntityBoomBall boomBall = new EntityBoomBall(this, target, 2.5F);
					worldObj.spawnEntityInWorld(boomBall);
				}

				else if (spiderType == EnumSpiderType.SLINGER && target != null)
				{
					EntityWebShot webShot = new EntityWebShot(this, target, 2.5F);
					worldObj.spawnEntityInWorld(webShot);

					worldObj.playSoundAtEntity(this, "random.bow", 0.75F, 1.0F);
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

				else if (spiderType == EnumSpiderType.ENDER && target != null)
				{
					if (worldObj.canBlockSeeTheSky((int)posX, (int)posY, (int)posZ))
					{
						Utils.spawnParticlesAroundEntityS(Particle.PORTAL, this, 6);
						target.motionY = 1.0F + (0.3F * getLevel());

						target.motionX = rand.nextBoolean() ? rand.nextDouble() : rand.nextBoolean() ? rand.nextDouble() * -1 : 0;
						target.motionZ = rand.nextBoolean() ? rand.nextDouble() : rand.nextBoolean() ? rand.nextDouble() * -1 : 0;

						if (getLevel() == 3)
						{
							target.motionX = target.motionX > 0 ? target.motionX + 2.0F : target.motionX - 2.0F;
							target.motionZ = target.motionZ > 0 ? target.motionZ + 2.0F : target.motionZ - 2.0F;
						}

						worldObj.playSoundAtEntity(target, "mob.endermen.portal", 1.0F, 1.0F);

						Utils.spawnParticlesAroundEntityS(Particle.PORTAL, target, 6);
					}
				}

				abilityCounter = abilityThreshold;
			}
		}
	}

	private int calculateAbilityThreshold() 
	{
		switch (spiderType)
		{
		case BOOM: return (Time.SECOND * 7) - getLevel();
		case SLINGER: return (Time.SECOND * 5) - getLevel();
		case NOVA: return Time.SECOND * 3;
		case ENDER: return Time.SECOND * 5;
		default: return 0;
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
					return true;
				}
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

	private EntityLivingBase findEntityToAttack()
	{
		//Disable combat here.
		switch (spiderType)
		{
		case PACK: return null;
		case RIDER: return null;	
		default:
			break;			
		}

		//Attack on their own?
		return null;

		//		final List<Entity> entitiesAroundMe = RadixLogic.getAllEntitiesWithinDistanceOfCoordinates(worldObj, posX, posY, posZ, 15);
		//		EntityLivingBase closestValidTarget = null;
		//		double distanceToTarget = 100D;
		//	
		//		for (final Entity entity : entitiesAroundMe)
		//		{
		//			if (entity == this)
		//			{
		//				continue;
		//			}
		//			
		//			final double distanceToThisEntity = getDistanceToEntity(entity);
		//	
		//			if (entity instanceof EntitySheep && distanceToThisEntity < distanceToTarget)
		//			{
		//				closestValidTarget = (EntityLivingBase) entity;
		//				distanceToTarget = distanceToThisEntity;
		//			}
		//		}
		//	
		//		return closestValidTarget;
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
}
