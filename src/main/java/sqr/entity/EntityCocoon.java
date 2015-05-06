package sqr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import radixcore.data.DataWatcherEx;
import radixcore.data.IWatchable;
import radixcore.data.WatchedBoolean;
import radixcore.data.WatchedInt;
import sqr.core.SQR;
import sqr.core.minecraft.ModBlocks;
import sqr.core.minecraft.ModItems;
import sqr.data.WatcherIDsCocoon;
import sqr.enums.EnumTypeVariant;

/**
 * Definition of all cocoons in the game.
 */
public class EntityCocoon extends EntityLiving implements IWatchable
{
	private DataWatcherEx dataWatcherEx;
	
	private final WatchedInt typeId;
	private final WatchedBoolean isEaten;
	
	private int currentDamage;
	private int timeSinceHit;
	private int rockDirection;
	
	/**
	 * Constructor for loading cocoon in the world.
	 */
	public EntityCocoon(World world)
	{
		super(world);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		tasks.addTask(0, new EntityAISwimming(this));
		
		dataWatcherEx = new DataWatcherEx(this, SQR.ID);
		
		this.typeId = new WatchedInt(EnumTypeVariant.EMPTY.getId(), WatcherIDsCocoon.TYPE, dataWatcherEx);
		this.isEaten = new WatchedBoolean(false, WatcherIDsCocoon.IS_EATEN, dataWatcherEx);
		
		this.currentDamage = 0;
		this.timeSinceHit = 0;
		this.rockDirection = 1;
		
		this.setHitboxSize();
		this.preventEntitySpawning = true;
	}
	
	/**
	 * Constructor for creating a new cocoon with the specified type.
	 */
	public EntityCocoon(World world, EnumTypeVariant type)
	{
		this(world);
		this.typeId.setValue(type.getId());
	}
	
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity entity)
	{
		return entity.boundingBox;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}
	
	@Override
	public boolean canBePushed()
	{
		return true;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
	{		
		rockDirection = -this.rockDirection;
		timeSinceHit = 10;
		currentDamage += damage * 10;
		
		//Get rid of the cocoon after it's been hit enough.
		if (currentDamage > 80 && !worldObj.isRemote)
		{
			if (getIsEaten())
			{
				worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
			}
			
			else
			{
				dropItem(this.getType().getCocoon(), 1);
			}
			
			setDead();
		}
		
		return true;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return !isDead;
	}
	
	@Override
	protected boolean canDespawn()
	{
		return false;
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		//Prevent jumping from EntityLiving code.
		this.isJumping = false;
		
		//Update motion effects from being hit.
		if (timeSinceHit > 0)
		{
			timeSinceHit--;
		}
		
		if (currentDamage > 0)
		{
			rotationPitch += worldObj.rand.nextFloat() - worldObj.rand.nextFloat();
			currentDamage--;
		}
		
		//Stop y-motion when in a web.
		if (worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) == ModBlocks.smallWeb)
		{
			this.motionY = 0;
			return;
		}
		
		//Removed code here to enable floating in water. Caused issues on ground, may look at this later.
	}
	
	@Override
	protected void updateEntityActionState()
	{
		//Do nothing to prevent looking around.
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("typeId", typeId.getInt());
		nbt.setBoolean("isEaten", isEaten.getBoolean());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		typeId.setValue(nbt.getInteger("typeId"));
		isEaten.setValue(nbt.getBoolean("isEaten"));
	}
	
	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (getIsEaten())
		{
			return true;
		}
		
		else
		{
			//Spawn smoke.
			if (worldObj.isRemote)
			{
				for (int i = 0; i < 3; i++)
				{
					this.worldObj.spawnParticle("largesmoke", this.posX - this.motionX * 2, this.posY - this.motionY * 2, this.posZ - this.motionZ * 2, this.motionX, this.motionY, this.motionZ);
				}
			}
			
			else
			{
				//Heal player and add food.
				entityPlayer.heal(3);
				entityPlayer.getFoodStats().addStats(4, 0.4f);
				
				//Drop web and set as eaten.
				entityDropItem(new ItemStack(ModItems.web, 5, 0), 0);
				setIsEaten(true);
			}
			
			return true;
		}
	}
	
	@Override
	public DataWatcherEx getDataWatcherEx()
	{
		return dataWatcherEx;
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
	}
	
	@Override
	protected void updateAITasks()
	{
		
	}
	
	@Override
	public boolean isAIEnabled()
	{
		return false;
	}
	
	public int getTimeSinceHit()
	{
		return timeSinceHit;
	}
	
	public int getCurrentDamage()
	{
		return currentDamage;
	}
	
	public EnumTypeVariant getType()
	{
		if (typeId == null)
		{
			return EnumTypeVariant.EMPTY;
		}
		
		else
		{
			return EnumTypeVariant.getById(typeId.getInt());
		}
	}
	
	public boolean getIsEaten()
	{
		return isEaten.getBoolean();
	}
	
	/**
	 * Sets the size of the hitbox based on this cocoon's type.
	 */
	public void setHitboxSize()
	{
		//TODO
		this.setSize(1F, 1F);
	}
	
	public void setIsEaten(boolean value)
	{
		this.isEaten.setValue(value);
	}
}
