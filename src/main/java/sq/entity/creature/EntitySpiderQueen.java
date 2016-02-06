package sq.entity.creature;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import radixcore.data.DataWatcherEx;
import radixcore.data.IWatchable;
import radixcore.data.WatchedInt;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;
import sq.core.minecraft.ModItems;
import sq.entity.IWebClimber;

/**
 * The spider queen creatures are rival spider queens that spawn in the world. They are hostile to the player, and attack
 * with either swords or bows. They can alternate their attacks.
 */
public class EntitySpiderQueen extends EntityCreature implements IWebClimber, IWatchable
{
	private static final ItemStack stoneSword = new ItemStack(Items.stone_sword, 1);
	private static final ItemStack bow = new ItemStack(Items.bow, 1);

	private DataWatcherEx dataWatcherEx;
	private WatchedInt textureId;
	private WatchedInt attackMode;

	private int attackTime;
	public EntitySpiderQueen(World world)
	{
		super(world);
		setSize(1.4F, 1.9F);
		dataWatcherEx = new DataWatcherEx(this, SpiderCore.ID);
		textureId = new WatchedInt(rand.nextInt(4), 1, dataWatcherEx);
		attackMode = new WatchedInt(0, 2, dataWatcherEx);
	}

	@Override
	protected final void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.1F);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(140.0F);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		fallDistance = 0.0F;

		if (getAttackTarget() == null)
		{
			setAttackTarget(findPlayerToAttack());
		}
		
		//Randomly change our attack mode.
		if (!worldObj.isRemote && worldObj.rand.nextInt(150) == 0)
		{
			attackMode.setValue(worldObj.rand.nextInt(2));
		}
	}

	protected EntityPlayer findPlayerToAttack()
	{
		if (rand.nextInt(15) != 1) 
		{ 
			return null; 
		}

		return worldObj.getClosestPlayerToEntity(this, 16D);
	}

	@Override
	public boolean getCanSpawnHere() 
	{
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(getEntityBoundingBox().minY);
		int k = MathHelper.floor_double(posZ);

		return BlockHelper.getBlock(worldObj, i, j - 1, k) == Blocks.grass && super.getCanSpawnHere();
	}

	@Override
	protected boolean canDespawn()
	{
		return true;
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

	//	@Override
	//FIXME
	protected void attackEntity(Entity entity, float f)
	{
		if (attackMode.getInt() == 0) // SWORD
		{
			if (attackTime <= 0 && f < 3.0F && entity.getEntityBoundingBox().maxY > getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < getEntityBoundingBox().maxY)
			{
				attackTime = 12;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 6.0F);
			}

			return;
		}

		else if (attackMode.getInt() == 1)
		{
			if (f < 10F)
			{
				double dX = entity.posX - posX;
				double dZ = entity.posZ - posZ;

				if (attackTime == 0)
				{
					EntityArrow arrow = new EntityArrow(worldObj, this, 1);
					double d2 = (entity.posY + entity.getEyeHeight()) - 0.20D - arrow.posY;
					float f1 = MathHelper.sqrt_double(dX * dX + dZ * dZ) * 0.2F;
					worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
					worldObj.spawnEntityInWorld(arrow);
					arrow.setThrowableHeading(dX, d2 + f1, dZ, 0.75F, 12F);
					attackTime = 30;
				}

				rotationYaw = (float)((Math.atan2(dZ, dX) * 180D) / 3.1415927410125732D) - 90F;
			}

			return;
		}
	}


	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float f)
	{
		final Entity entity = damageSource.getEntity();

		if (entity instanceof EntityLivingBase)
		{
			setAttackTarget((EntityLivingBase) entity);
		}
		
		return super.attackEntityFrom(damageSource, f);
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootLevel)
	{
		dropItem(ModItems.spiderEgg, 3);
		dropItem(ModItems.webNormal, 3);
		dropItem(ModItems.royalBlood, 1);
		dropItem(Items.saddle, 1);
	}

	@Override
	public ItemStack getHeldItem()
	{
		return attackMode.getInt() == 0 ? stoneSword : bow;
	}

	@Override
	public DataWatcherEx getDataWatcherEx() 
	{
		return dataWatcherEx;
	}

	public int getTextureId()
	{
		return textureId.getInt();
	}
}
