package spiderqueen.entity;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.forge.PlayerExtension;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityWebslinger extends Entity implements IEntityAdditionalSpawnData
{
	public EntityPlayer player;
	public Entity entityStruck;
	public int shake;

	private int tileX;
	private int tileY;
	private int tileZ;
	private int inTile;
	private boolean inGround;
	private int timeInGround;
	private int timeInAir;
	private int field_4088_k;
	private int field_6388_l;
	private double field_6387_m;
	private double field_6386_n;
	private double field_6385_o;
	private double field_6384_p;
	private double field_6383_q;
	private double velocityX;
	private double velocityY;
	private double velocityZ;
	public double distance;

	public EntityWebslinger(World world)
	{
		super(world);
		tileX = -1;
		tileY = -1;
		tileZ = -1;
		inTile = 0;
		inGround = false;
		shake = 0;
		timeInAir = 0;
		field_4088_k = 0;
		entityStruck = null;
		setSize(0.15F, 0.15F);
		distance = 0;
	}

	public EntityWebslinger(World world, double posX, double posY, double posZ)
	{
		this(world);
		setPosition(posX, posY, posZ);
	}

	public EntityWebslinger(World world, EntityPlayer entityPlayer)
	{
		super(world);

		PlayerExtension playerExtension = (PlayerExtension) entityPlayer.getExtendedProperties(PlayerExtension.ID);

		tileX = -1;
		tileY = -1;
		tileZ = -1;
		inTile = 0;
		inGround = false;
		shake = 0;
		timeInAir = 0;
		field_4088_k = 0;
		entityStruck = null;
		player = (EntityPlayerMP)entityPlayer;
		playerExtension.webEntity = this;

		setSize(0.25F, 0.25F);
		setLocationAndAngles(entityPlayer.posX, (entityPlayer.posY + 1.62D) - (double)entityPlayer.yOffset, entityPlayer.posZ, entityPlayer.rotationYaw, entityPlayer.rotationPitch);
		posX -= MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * 0.16F;
		posY -= 0.1D;
		posZ -= MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;

		motionX = -MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI) * 8F;
		motionZ = MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI) * 8F;
		motionY = -MathHelper.sin((rotationPitch / 180F) * (float)Math.PI) * 8F;

		func_4042_a(motionX, motionY, motionZ, 1.5F, 1.0F);
	}

	protected void entityInit()
	{
	}

	public boolean isInRangeToRenderDist(double d)
	{
		double d1 = boundingBox.getAverageEdgeLength() * 4D;
		d1 *= 64D;
		return d < d1 * d1;
	}

	public void func_4042_a(double d, double d1, double d2, float f, float f1)
	{
		float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
		d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
		d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
		d *= f;
		d1 *= f;
		d2 *= f;
		motionX = d;
		motionY = d1;
		motionZ = d2;
		float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
		prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
		timeInGround = 0;
	}

	public void setPositionAndRotation2(double d, double d1, double d2, float f, float f1, int i)
	{
		field_6387_m = d;
		field_6386_n = d1;
		field_6385_o = d2;
		field_6384_p = f;
		field_6383_q = f1;
		field_6388_l = i;
		motionX = velocityX;
		motionY = velocityY;
		motionZ = velocityZ;
	}

	public void setVelocity(double d, double d1, double d2)
	{
		velocityX = motionX = d;
		velocityY = motionY = d1;
		velocityZ = motionZ = d2;
	}

	@Override
	public void setDead() 
	{
		super.setDead();
		
		if (this.player != null)
		{
			final PlayerExtension playerExtension = (PlayerExtension)this.player.getExtendedProperties(PlayerExtension.ID);
			playerExtension.webEntity = null;
		}
	}

	public void matchMotion(int type, double amount)
	{
		if (type == 0) 
		{
			amount = amount - player.motionX;
			player.motionX = amount;

			if (entityStruck != null)
			{ 
				entityStruck.motionX = entityStruck.motionX - amount; 
			}
		}

		if (type == 1) 
		{
			amount = amount - player.motionY;
			player.motionY = amount;

			if (entityStruck != null) 
			{ 
				entityStruck.motionY = entityStruck.motionY - amount; 
			}
		}

		if (type == 2) 
		{
			amount = amount - player.motionZ;
			player.motionZ = amount;

			if (entityStruck != null) 
			{ 
				entityStruck.motionZ = entityStruck.motionZ - amount; 
			}
		}
	}

	public void onUpdate()
	{
		super.onUpdate();

		if (player != null & (inGround || entityStruck != null))
		{
			player.fallDistance = 0.0F;

			if (player.isSneaking()) 
			{
				distance++; 
			}

			Vec3 vectorSlinger = Vec3.createVectorHelper(posX, posY, posZ);
			Vec3 vectorPlayer = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);

			if (distance != 0)
			{
				if(distance < 2) 
				{ 
					distance = 2; 
				}

				if (distance > 1000) 
				{ 
					distance = 1000; 
				}
			}

			if (distance == 0) 
			{ 
				distance = vectorSlinger.squareDistanceTo(vectorPlayer); 
			}

			if (vectorSlinger.squareDistanceTo(vectorPlayer) > distance / 4 * 3)
			{
				Vec3 nextPosition = Vec3.createVectorHelper(
						player.motionX * 1.2D + (posX - player.posX) / 2, 
						player.motionY * 1.2D + (posY - player.posY) / 2, 
						player.motionZ * 1.2D + (posZ - player.posZ) / 2).normalize();

				double multiplier = 0.3D;// + (ww.squareDistanceTo(aa) - ddist) / (ddist);

				if (entityStruck != null) 
				{ 
					multiplier = multiplier / 2; 
				}

				double newMotionX = player.motionX + nextPosition.xCoord * multiplier;
				double newMotionY = player.motionY + nextPosition.yCoord * multiplier;
				double newMotionZ = player.motionZ + nextPosition.zCoord * multiplier;

				if (player.posX > posX) 
				{ 
					if (newMotionX < player.motionX) 
					{
						matchMotion(0, newMotionX); 
					} 
				} 

				else 
				{ 
					if (newMotionX > player.motionX) 
					{ 
						matchMotion(0, newMotionX); 
					} 
				}

				if (player.posY > posY) 
				{ 
					if(newMotionY < player.motionY) 
					{ 
						matchMotion(1, newMotionY); 
					} 
				} 
				
				else 
				{ 
					if (newMotionY > player.motionY) 
					{ 
						matchMotion(1, newMotionY); 
					} 
				}

				if (player.posZ > posZ) 
				{ 
					if (newMotionZ < player.motionZ) 
					{ 
						matchMotion(2, newMotionZ); 
					} 
				} 
				
				else 
				{ 
					if (newMotionZ > player.motionZ) 
					{ 
						matchMotion(2, newMotionZ); 
					} 
				}
			}

			else
			{
				if (player != null)
				{
					boolean isJumping = ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, player, 41);

					if (isJumping)
					{
						distance = distance - 6; 
						SpiderQueen.packetPipeline.sendPacketToServer(new Packet(EnumPacketType.SetDistance, distance));
					}
				}
			}
		}

		if (field_6388_l > 0)
		{
			double d = posX + (field_6387_m - posX) / (double)field_6388_l;
			double d1 = posY + (field_6386_n - posY) / (double)field_6388_l;
			double d2 = posZ + (field_6385_o - posZ) / (double)field_6388_l;
			double d4;

			for(d4 = field_6384_p - (double)rotationYaw; d4 < -180D; d4 += 360D) { }
			for(; d4 >= 180D; d4 -= 360D) { }

			rotationYaw += d4 / (double)field_6388_l;
			rotationPitch += (field_6383_q - (double)rotationPitch) / (double)field_6388_l;
			field_6388_l--;
			setPosition(d, d1, d2);
			setRotation(rotationYaw, rotationPitch);
			return;
		}

		if (!worldObj.isRemote)
		{
			if (player == null || player.isDead || !player.isEntityAlive() || getDistanceSqToEntity(player) > 1024D)
			{
				setDead();

				if (player != null) 
				{ 
					PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);
					playerExtension.webEntity = null; 
				}

				return;
			}

			if (entityStruck != null)
			{
				if (entityStruck.isDead)
				{
					entityStruck = null;
				} 

				else
				{
					posX = entityStruck.posX;
					posY = entityStruck.boundingBox.minY + (double)entityStruck.height * 0.80000000000000004D;
					posZ = entityStruck.posZ;
					return;
				}
			}
		}

		if (shake > 0)
		{
			shake--;
		}

		if (inGround)
		{

			timeInGround++;

			if (timeInGround == 1200)
			{
				setDead();
			}

			return;

		} 

		else
		{
			timeInAir++;
		}

		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);

		if (movingobjectposition != null)
		{
			vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}

		Entity entity = null;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double d3 = 0.0D;

		for (int j = 0; j < list.size(); j++)
		{
			Entity entity1 = (Entity)list.get(j);
			if (!entity1.canBeCollidedWith() || entity1 == player && timeInAir < 5)
			{
				continue;
			}

			float f2 = 0.3F;
			AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f2, f2, f2);
			MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

			if (movingobjectposition1 == null)
			{
				continue;
			}

			double d6 = vec3d.distanceTo(movingobjectposition1.hitVec);
			if (d6 < d3 || d3 == 0.0D)
			{
				entity = entity1;
				d3 = d6;
			}
		}

		if (entity != null)
		{
			movingobjectposition = new MovingObjectPosition(entity);
		}

		if (movingobjectposition != null)
		{
			if (movingobjectposition.entityHit != null)
			{
				if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(player), 0))
				{
					entityStruck = movingobjectposition.entityHit;
				}
			} 

			else
			{
				motionX = motionX * 0.7; motionY = motionY * 0.7; motionZ = motionZ * 0.7;
				inGround = true;
				posX = posX + motionX;
				posY = posY + motionY;
				posZ = posZ + motionZ;
			}
		}

		if (inGround)
		{
			return;
		}

		moveEntity(motionX, motionY, motionZ);
		float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);

		for(rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
		for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
		for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
		for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }

		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		float f1 = 0.92F;

		if (isCollidedVertically || isCollidedHorizontally)
		{
			f1 = 0.5F;
			inGround = true;
		}

		int k = 5;
		double d5 = 0.0D;

		for (int l = 0; l < k; l++)
		{
			double d8 = ((boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(l + 0)) / (double)k) - 0.125D) + 0.125D;
			double d9 = ((boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(l + 1)) / (double)k) - 0.125D) + 0.125D;
			AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(boundingBox.minX, d8, boundingBox.minZ, boundingBox.maxX, d9, boundingBox.maxZ);

			if (worldObj.isAABBInMaterial(axisalignedbb1, Material.water))
			{
				d5 += 1.0D / (double)k;
			}
		}

		if (d5 > 0.0D)
		{
			if (field_4088_k > 0)
			{
				field_4088_k--;
			} 

			else
			{
				char c = '\u01F4';

				if (worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + 1, MathHelper.floor_double(posZ)))
				{
					c = '\u012C';
				}

				if (rand.nextInt(c) == 0)
				{
					field_4088_k = rand.nextInt(30) + 10;
					motionY -= 0.20000000298023224D;
					worldObj.playSoundAtEntity(this, "random.splash", 0.25F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.4F);
					float f3 = MathHelper.floor_double(boundingBox.minY);
					for(int i1 = 0; (float)i1 < 1.0F + width * 20F; i1++)
					{
						float f4 = (rand.nextFloat() * 2.0F - 1.0F) * width;
						float f6 = (rand.nextFloat() * 2.0F - 1.0F) * width;
						worldObj.spawnParticle("bubble", posX + (double)f4, f3 + 1.0F, posZ + (double)f6, motionX, motionY - (double)(rand.nextFloat() * 0.2F), motionZ);
					}

					for(int j1 = 0; (float)j1 < 1.0F + width * 20F; j1++)
					{
						float f5 = (rand.nextFloat() * 2.0F - 1.0F) * width;
						float f7 = (rand.nextFloat() * 2.0F - 1.0F) * width;
						worldObj.spawnParticle("splash", posX + (double)f5, f3 + 1.0F, posZ + (double)f7, motionX, motionY, motionZ);
					}
				}
			}
		}

		if (field_4088_k > 0)
		{
			motionY -= (double)(rand.nextFloat() * rand.nextFloat() * rand.nextFloat()) * 0.20000000000000001D;
		}

		double d7 = d5 * 2D - 1.0D;
		motionY += 0.029999999105930328D * d7;

		if (d5 > 0.0D)
		{
			f1 = (float)((double)f1 * 0.90000000000000002D);
			motionY *= 0.80000000000000004D;
		}
		/*motionX *= f1;
        motionY *= f1;
        motionZ *= f1;*/
		setPosition(posX, posY, posZ);
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short)tileX);
		nbttagcompound.setShort("yTile", (short)tileY);
		nbttagcompound.setShort("zTile", (short)tileZ);
		nbttagcompound.setByte("inTile", (byte)inTile);
		nbttagcompound.setByte("shake", (byte)shake);
		nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		tileX = nbttagcompound.getShort("xTile");
		tileY = nbttagcompound.getShort("yTile");
		tileZ = nbttagcompound.getShort("zTile");
		inTile = nbttagcompound.getByte("inTile") & 0xff;
		shake = nbttagcompound.getByte("shake") & 0xff;
		inGround = nbttagcompound.getByte("inGround") == 1;
	}

	public float getShadowSize()
	{
		return 0.0F;
	}

	public int catchFish()
	{
		return 0;
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void readSpawnData(ByteBuf additionalData) 
	{
		this.player = Minecraft.getMinecraft().thePlayer;	
		final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);
		playerExtension.webEntity = this;
	}
}