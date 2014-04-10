package spiderqueen.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.ByteBufIO;

import com.radixshock.radixcore.logic.NBTHelper;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityFakePlayer extends EntityCreature implements IEntityAdditionalSpawnData
{
	public String username;
	public ResourceLocation skinResourceLocation;
	public ThreadDownloadImageData imageDownloadThread;

	public EntityFakePlayer(World world) 
	{
		super(world);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);

		if (!this.worldObj.isRemote)
		{
			this.addAI();
			this.username = SpiderQueen.getInstance().getRandomPlayerName();
		}
	}

	protected void setupCustomSkin()
	{
		if (!username.isEmpty())
		{
			skinResourceLocation = AbstractClientPlayer.getLocationSkin(username);
			imageDownloadThread = AbstractClientPlayer.getDownloadImageSkin(skinResourceLocation, username);
		}
	}

	public void addAI()
	{
		this.getNavigator().setBreakDoors(true);
		this.getNavigator().setAvoidsWater(false);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveIndoors(this));
		this.tasks.addTask(2, new EntityAIRestrictOpenDoor(this));
		this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.6F));
		this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(6, new EntityAIWander(this, 0.6F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));
	}
	
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) 
	{
		super.writeEntityToNBT(nbt);
		NBTHelper.autoWriteEntityToNBT(this, nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		super.readEntityFromNBT(nbt);
		NBTHelper.autoReadEntityFromNBT(this, nbt);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		ByteBufIO.writeObject(buffer, username);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) 
	{
		username = (String)ByteBufIO.readObject(additionalData);
		setupCustomSkin();
	}
}
