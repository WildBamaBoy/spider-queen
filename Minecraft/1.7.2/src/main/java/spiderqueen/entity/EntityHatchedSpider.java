package spiderqueen.entity;

import io.netty.buffer.ByteBuf;
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
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.core.Constants;
import spiderqueen.core.SpiderQueen;
import spiderqueen.enums.EnumCocoonType;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.inventory.Inventory;

import com.radixshock.radixcore.constant.Time;
import com.radixshock.radixcore.logic.LogicHelper;
import com.radixshock.radixcore.logic.NBTHelper;
import com.radixshock.radixcore.logic.Point3D;
import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityHatchedSpider extends EntityCreature implements IEntityAdditionalSpawnData
{
	public String owner;
	public EnumCocoonType cocoonType = EnumCocoonType.EMPTY;
	public int level = 1;
	public int killsUntilLevelUp = LogicHelper.getNumberInRange(5, 15);
	public int timeUntilNextWebshot = 0;
	public int timeUntilNextTeleport = 0;
	public int timeUntilNextExplosion = 0;
	public Inventory inventory = new Inventory(this);

	public transient boolean hasSyncedInventory = false;

	public EntityHatchedSpider(World world)
	{
		super(world);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.6D));
		this.tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(3, new EntityAIWander(this, 0.4D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

		this.updateEntityAttributes();
	}

	public EntityHatchedSpider(World world, String owner, EnumCocoonType cocoonType)
	{
		this(world);
		this.owner = owner;
		this.setSize(1.4F, 0.9F);
		this.cocoonType = cocoonType;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();

		//Server-side only
		if (!worldObj.isRemote)
		{
			setBesideClimbableBlock(isCollidedHorizontally);

			if (getHealth() > 0)
			{
				updateEntityAttributes();
			}
			
			if (!tryFollowOwnerPlayer(false))
			{
				updateEntityActionState();
				tryMoveToSpiderRod();
			}

			if (timeUntilNextTeleport > 0)
			{
				timeUntilNextTeleport--;
			}

			if (timeUntilNextExplosion > 0)
			{
				timeUntilNextExplosion--;
			}

			if (timeUntilNextWebshot > 0)
			{
				timeUntilNextWebshot--;
			}
		}

		else //Client-side only
		{
			syncInventory();
			displayParticles();
		}
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
	}

	private void updateEntityAttributes()
	{
		if (cocoonType == EnumCocoonType.SKELETON)
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);	
		}

		else if (cocoonType == EnumCocoonType.WOLF)
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.8D);	
		}

		else if (cocoonType == EnumCocoonType.ZOMBIE && getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() != 30.0D + (level * 10))
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D + (level * 10));
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);	
			this.setHealth((float) this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue());
		}
	}

	private float getAttackDamage()
	{
		switch (cocoonType)
		{
		case WOLF: return 1.0F;
		default: return 2.5F;
		}
	}

	/**
	 * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
	 * (Animals, Spiders at day, peaceful PigZombies).
	 */
	protected Entity findPlayerToAttack()
	{
		EntityLiving entityAttacking = (EntityLiving)LogicHelper.getNearestEntityOfType(this, EntitySheep.class, 20);

		if (entityAttacking != null && this.canEntityBeSeen(entityAttacking))
		{
			if (entityAttacking.getHealth() > 0.0F)
			{
				if (cocoonType == EnumCocoonType.ENDERMAN && timeUntilNextTeleport <= 0)
				{
					resetTimeUntilTeleport();

					worldObj.playSoundAtEntity(this, "mob.endermen.portal", 0.75F, 1.0F);
					setPosition(entityAttacking.posX, entityAttacking.posY, entityAttacking.posZ);
					worldObj.playSound(entityAttacking.posX, entityAttacking.posY, entityAttacking.posZ, "mob.endermen.portal", 0.75F, 1.0F, true);
				}

				return entityAttacking;	
			}
		}

		return null;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "mob.spider.say";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.spider.say";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.spider.death";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
	{
		this.playSound("mob.spider.step", 0.15F, 1.0F);
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 */
	protected void attackEntity(Entity entityBeingAttacked, float damageAmount)
	{
		damageAmount = getAttackDamage();
		getNavigator().setPath(getNavigator().getPathToEntityLiving(entityBeingAttacked), 0.4D);
		
		if (rand.nextInt(10) == 0)
		{
			if (this.onGround)
			{
				double d0 = entityBeingAttacked.posX - this.posX;
				double d1 = entityBeingAttacked.posZ - this.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				this.motionX = d0 / (double)f2 * 0.5D * 0.8D + this.motionX * 0.2D;
				this.motionZ = d1 / (double)f2 * 0.5D * 0.8D + this.motionZ * 0.2D;
				this.motionY = 0.4D;
			}
		}

		else
		{
			if (cocoonType == EnumCocoonType.SKELETON && timeUntilNextWebshot <= 0)
			{
				resetTimeUntilWebshot();
				worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
				worldObj.spawnEntityInWorld(new EntityWeb(this, (EntityLivingBase)entityBeingAttacked, 2.6F));
			}

			if (LogicHelper.getDistanceToEntity(this, entityBeingAttacked) < 2.0D)
			{
				final EntityLiving entityLiving = (EntityLiving)entityBeingAttacked;
				entityBeingAttacked.attackEntityFrom(DamageSource.causeMobDamage(this), damageAmount);

				if (cocoonType == EnumCocoonType.CREEPER && timeUntilNextExplosion <= 0)
				{
					resetTimeUntilExplosion();
					worldObj.createExplosion(this, posX, posY, posZ, 5.0F, false);
				}

				if (entityLiving.getHealth() <= 0.0F)
				{
					killsUntilLevelUp--;

					if (level < 3 && (killsUntilLevelUp <= 0 || SpiderQueen.getInstance().inDebugMode))
					{
						timeUntilNextTeleport = 0;
						timeUntilNextExplosion = 0;
						timeUntilNextWebshot = 0;

						worldObj.playSoundAtEntity(this, "random.levelup", 0.75F, 1.0F);
						killsUntilLevelUp = LogicHelper.getNumberInRange(5, 15);
						level++;

						SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetLevel, getEntityId(), level));
					}

					entityToAttack = null;
				}
			}
		}
	}

	protected Item getDropItem()
	{
		return Items.string;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * par2 - Level of Looting used to kill this mob.
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		super.dropFewItems(par1, par2);

		if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0))
		{
			this.dropItem(Items.spider_eye, 1);
		}
		
		inventory.dropAllItems();
	}

	/**
	 * returns true if this entity is by a ladder, false otherwise
	 */
	public boolean isOnLadder()
	{
		return this.isBesideClimbableBlock();
	}

	/**
	 * Sets the Entity inside a web block.
	 */
	public void setInWeb() {}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
	{
		return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (cocoonType == EnumCocoonType.VILLAGER)
		{
			entityPlayer.openGui(SpiderQueen.getInstance(), Constants.ID_GUI_INVENTORY, worldObj, (int)posX, (int)posY, (int)posZ);
		}

		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);

		NBTHelper.autoWriteEntityToNBT(this, nbt);
		nbt.setInteger("cocoonType", cocoonType.ordinal());

		inventory.writeInventoryToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		super.readEntityFromNBT(nbt);
		NBTHelper.autoReadEntityFromNBT(this, nbt);

		try
		{
			cocoonType = (EnumCocoonType)EnumCocoonType.class.getFields()[nbt.getInteger("cocoonType")].get(EnumCocoonType.class);
		}

		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

		inventory.readInventoryFromNBT(nbt);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeInt(cocoonType.ordinal());
		buffer.writeInt(level);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) 
	{
		try
		{
			cocoonType = (EnumCocoonType)EnumCocoonType.class.getFields()[additionalData.readInt()].get(EnumCocoonType.class);
		}

		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

		level = additionalData.readInt();
	}

	/**
	 * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns false. The WatchableObject is updated using
	 * setBesideClimableBlock.
	 */
	public boolean isBesideClimbableBlock()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	/**
	 * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
	 * false.
	 */
	public void setBesideClimbableBlock(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			b0 = (byte)(b0 | 1);
		}
		else
		{
			b0 &= -2;
		}

		this.dataWatcher.updateObject(16, Byte.valueOf(b0));
	}

	private boolean tryFollowOwnerPlayer(boolean checkOnly)
	{
		if (owner != null)
		{
			final EntityPlayer ownerPlayer = worldObj.getPlayerEntityByName(owner);

			if (ownerPlayer != null)
			{
				final double distanceToOwner = LogicHelper.getDistanceToEntity(this, ownerPlayer);
				final ItemStack currentItemStack = ownerPlayer.inventory.mainInventory[ownerPlayer.inventory.currentItem];

				if (currentItemStack != null && distanceToOwner < 30.0D && currentItemStack.getItem() == SpiderQueen.getInstance().itemSpiderRod)
				{
					if (!checkOnly)
					{
						entityToAttack = null;
						moveToPlayer(ownerPlayer);
					}

					return true;
				}
			}
		}

		return false;
	}

	private void tryMoveToSpiderRod()
	{
		final Point3D nearestRod = LogicHelper.getNearbyBlock_StartAtTop(this, SpiderQueen.getInstance().blockSpiderRod, 10);

		if (nearestRod != null && LogicHelper.getDistanceToXYZ(nearestRod.posX, nearestRod.posY, nearestRod.posZ, posX, posY, posZ) > 5.0D)
		{
			getNavigator().tryMoveToXYZ(nearestRod.posX, nearestRod.posY, nearestRod.posZ, 0.4D);
		}
	}

	private void moveToPlayer(EntityPlayer player)
	{
		if (player != null && (player.onGround))
		{
			getLookHelper().setLookPositionWithEntity(player, 10.0F, getVerticalFaceSpeed());

			if (getDistanceToEntity(player) > 3.5D)
			{
				final boolean pathSet = getNavigator().tryMoveToEntityLiving(player, 0.4D);
				getNavigator().onUpdateNavigation();

				if (!pathSet && getDistanceToEntity(player) >= 10.0D)
				{
					final int playerX = MathHelper.floor_double(player.posX) - 2;
					final int playerY = MathHelper.floor_double(player.boundingBox.minY);
					final int playerZ = MathHelper.floor_double(player.posZ) - 2;

					for (int i = 0; i <= 4; ++i)
					{
						for (int i2 = 0; i2 <= 4; ++i2)
						{
							if ((i < 1 || i2 < 1 || i > 3 || i2 > 3) && worldObj.doesBlockHaveSolidTopSurface(worldObj, playerX + i, playerY - 1, playerZ + i2) && !worldObj.getBlock(playerX + i, playerY, playerZ + i2).isNormalCube() && !worldObj.getBlock(playerX + i, playerY + 1, playerZ + i2).isNormalCube())
							{
								setLocationAndAngles(playerX + i + 0.5F, playerY, playerZ + i2 + 0.5F, rotationYaw, rotationPitch);
								getNavigator().clearPathEntity();
								return;
							}
						}
					}
				}
			}
		}
	}

	private void syncInventory() 
	{
		if (!hasSyncedInventory)
		{
			SpiderQueen.packetPipeline.sendPacketToServer(new Packet(EnumPacketType.GetInventory, getEntityId()));
			hasSyncedInventory = true;
		}
	}

	private void displayParticles() 
	{
		if (cocoonType == EnumCocoonType.ENDERMAN)
		{
			worldObj.spawnParticle("portal", 
					posX + (rand.nextDouble() - 0.5D) * (double)width, 
					posY + 0.5D + rand.nextDouble() * (double)0.25D, 
					posZ + rand.nextDouble() - 0.5D * (double)width, 
					(rand.nextDouble() - 0.5D) * 2.0D, 
					-rand.nextDouble(), 
					(rand.nextDouble() - 0.5D) * 2.0D);
		}
	}

	private void resetTimeUntilTeleport()
	{
		switch (level)
		{
		case 1: timeUntilNextTeleport = Time.MINUTE; break;
		case 2: timeUntilNextTeleport = Time.SECOND * 30; break;
		case 3: timeUntilNextTeleport = Time.SECOND * 10; break;
		}
	}

	private void resetTimeUntilExplosion()
	{
		switch (level)
		{
		case 1: timeUntilNextExplosion = Time.MINUTE; break;
		case 2: timeUntilNextExplosion = Time.SECOND * 30; break;
		case 3: timeUntilNextExplosion = Time.SECOND * 10; break;
		}
	}

	private void resetTimeUntilWebshot()
	{
		switch (level)
		{
		case 1: timeUntilNextWebshot = Time.SECOND * 5; break;
		case 2: timeUntilNextWebshot = Time.SECOND * 3; break;
		case 3: timeUntilNextWebshot = Time.SECOND * 1; break;
		}
	}
}