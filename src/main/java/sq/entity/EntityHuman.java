package sq.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import radixcore.data.DataWatcherEx;
import radixcore.data.IWatchable;
import radixcore.data.WatchedBoolean;
import radixcore.data.WatchedInt;
import radixcore.network.ByteBufIO;
import radixcore.util.RadixLogic;
import radixcore.util.RadixString;
import sq.core.SpiderCore;
import sq.core.radix.PlayerData;
import sq.entity.ai.RepEntityExtension;
import sq.enums.EnumHumanType;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityHuman extends EntityCreature implements IEntityAdditionalSpawnData, IRep, IWatchable
{
	private static final ItemStack swordStone;
	private static final ItemStack bow;
	private static final ItemStack pickaxeWood;
	private static final ItemStack ingotIron;
	private static final ItemStack stick;
	private static final ItemStack torchWood;
	private static final ItemStack cake;

	private DataWatcherEx dataWatcherEx;
	private final WatchedBoolean isSwinging;
	private int swingProgressTicks;
	private String username;
	private EnumHumanType type;
	private ItemStack heldItem;
	private int fortuneLevel;
	private ResourceLocation skinResourceLocation;
	private ThreadDownloadImageData	imageDownloadThread;

	public EntityHuman(World world)
	{
		super(world);
		dataWatcherEx = new DataWatcherEx(this, SpiderCore.ID);
		isSwinging = new WatchedBoolean(false, 1, dataWatcherEx);
		username = SpiderCore.getRandomPlayerName();

		type = EnumHumanType.getAtRandom();

		if (RadixLogic.getBooleanWithProbability(30))
		{
			fortuneLevel = 1;
		}

		else if (RadixLogic.getBooleanWithProbability(7))
		{
			fortuneLevel = 2;
		}
	}

	protected final void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.75F);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0F);
	}

	protected void dropFewItems(boolean flag, int i)
	{
		for (ItemStack stack : type.getDropsForType(this))
		{
			entityDropItem(stack, 1.0F);
		}
	}

	@Override
	public void swingItem()
	{
		if (!isSwinging.getBoolean() || swingProgressTicks >= 8 / 2 || swingProgressTicks < 0)
		{
			swingProgressTicks = -1;
			isSwinging.setValue(true);
		}
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		updateSwinging();
	}

	private void updateSwinging()
	{
		if (isSwinging.getBoolean())
		{
			swingProgressTicks++;

			if (swingProgressTicks >= 8)
			{
				swingProgressTicks = 0;

				if (!DataWatcherEx.allowClientSideModification)
				{
					DataWatcherEx.allowClientSideModification = true;
					isSwinging.setValue(false);
					DataWatcherEx.allowClientSideModification = false;
				}

				else
				{
					isSwinging.setValue(false);					
				}
			}
		}

		else
		{
			swingProgressTicks = 0;
		}

		swingProgress = (float) swingProgressTicks / (float) 8;
	}

	@Override //armorItemInSlot
	public ItemStack func_130225_q(int armorId)
	{
		return null;
	}

	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected Entity findPlayerToAttack()
	{
		EntityPlayer entityPlayer = worldObj.getClosestPlayerToEntity(this, 16D);

		if (entityPlayer != null && canEntityBeSeen(entityPlayer))
		{
			PlayerData data = SpiderCore.getPlayerData(entityPlayer);
			RepEntityExtension extension = (RepEntityExtension) this.getExtendedProperties(RepEntityExtension.ID);
			
			if (data.humanLike.getInt() < 0 || extension.getTimesHitByPlayer() >= 2)
			{
				return entityPlayer;
			}

			else
			{
				return null;
			}
		} 

		else
		{
			return null;
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		//Cancel setting the target if the player hasn't hit them enough, or if reputation is too high.
		if (source.getEntity() instanceof EntityPlayer)
		{
			PlayerData data = SpiderCore.getPlayerData(((EntityPlayer)source.getEntity()));
			RepEntityExtension extension = (RepEntityExtension) this.getExtendedProperties(RepEntityExtension.ID);
			
			if (data.humanLike.getInt() >= 0 && extension.getTimesHitByPlayer() <= 2)
			{
				//Do nothing.
			}
		}
		
		if (source.getEntity() != null)
		{
			entityToAttack = source.getEntity();
		}
		
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected void attackEntity(Entity entity, float f)
	{
		attackTime--;

		if (type == EnumHumanType.ARCHER)
		{
			if (f < 10F)
			{
				double dX = entity.posX - posX;
				double dZ = entity.posZ - posZ;
				if(attackTime == 0)
				{
					EntityArrow entityarrow = new EntityArrow(worldObj, this, 1);
					entityarrow.posY += 1.3999999761581421D;
					double d2 = (entity.posY + (double)entity.getEyeHeight()) - 0.20000000298023224D - entityarrow.posY;
					float f1 = MathHelper.sqrt_double(dX * dX + dZ * dZ) * 0.2F;
					worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
					worldObj.spawnEntityInWorld(entityarrow);
					entityarrow.setThrowableHeading(dX, d2 + (double)f1, dZ, 0.6F, 12F);
					attackTime = 30;
				}
				rotationYaw = (float)((Math.atan2(dZ, dX) * 180D) / 3.1415927410125732D) - 90F;
				hasAttacked = true;
			}

			return;
		}

		if (attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
		{
			attackTime = 40;
			swingItem();
			
			EntityPlayer p = (EntityPlayer)entity;
		}
	}

	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(boundingBox.minY);
		int k = MathHelper.floor_double(posZ);
		return worldObj.getBlock(i, j - 1, k) == Blocks.grass && super.getCanSpawnHere();
	}

	protected String getLivingSound()
	{
		return null;
	}

	public ItemStack getHeldItem()
	{
		switch (type)
		{
		case ARCHER: return bow;
		case FARMER: return cake;
		case MINER: return pickaxeWood;
		case NOMAD: return torchWood;
		case NOOB: return stick;
		case PROSPECTOR: return ingotIron;
		case WARRIOR: return swordStone;
		default: return null;
		}
	}

	@Override
	public String getCommandSenderName() 
	{
		return "Human";
	}

	private void setupCustomSkin()
	{
		if (!username.isEmpty())
		{
			skinResourceLocation = AbstractClientPlayer.getLocationSkin(getUsername());
			imageDownloadThread = AbstractClientPlayer.getDownloadImageSkin(skinResourceLocation, getUsername());
		}
	}

	public String getFortuneString()
	{
		String typeName = RadixString.upperFirstLetter(type.toString().toLowerCase());
		StringBuilder sb = new StringBuilder();
		sb.append("(");

		if (type != EnumHumanType.NOOB)
		{
			String fortuneString = fortuneLevel == 2 ? "Rich" : fortuneLevel == 1 ? "Experienced" : "Poor"; 
			sb.append(fortuneString);
			sb.append(" ");
			sb.append(typeName);
		}

		else
		{
			sb.append(RadixString.upperFirstLetter(EnumHumanType.NOOB.toString().toLowerCase()));
		}

		sb.append(")");
		return sb.toString();
	}

	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setString("username", username);
		nbt.setInteger("type", type.getId());
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		username = nbt.getString("username");
		type = EnumHumanType.byId(nbt.getInteger("type"));
	}

	public int getFortuneLevel()
	{
		return fortuneLevel;
	}

	@Override
	public WatchedInt getLikeData(PlayerData data) 
	{
		return data.humanLike;
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		buffer.writeInt(type.getId());
		buffer.writeInt(fortuneLevel);
		ByteBufIO.writeObject(buffer, username);
	}

	@Override
	public void readSpawnData(ByteBuf buffer) 
	{
		type = EnumHumanType.byId(buffer.readInt());
		fortuneLevel = buffer.readInt();
		username = (String) ByteBufIO.readObject(buffer);
		setupCustomSkin();
	}

	public ResourceLocation getSkinResourceLocation()
	{
		return skinResourceLocation;
	}

	public String getUsername()
	{
		return username.replace("*", "");
	}

	static
	{
		swordStone = new ItemStack(Items.stone_sword, 1);
		bow = new ItemStack(Items.bow, 1);
		pickaxeWood = new ItemStack(Items.wooden_pickaxe, 1);
		ingotIron = new ItemStack(Items.iron_ingot, 1);
		stick = new ItemStack(Items.stick, 1);
		torchWood = new ItemStack(Blocks.torch, 1);
		cake = new ItemStack(Items.cake, 1);
	}

	@Override
	public DataWatcherEx getDataWatcherEx() 
	{
		return dataWatcherEx;
	}

	public boolean getIsUsernameContributor() 
	{
		return username.contains("*");
	}
}
