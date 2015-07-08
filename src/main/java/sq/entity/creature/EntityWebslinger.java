package sq.entity.creature;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import sq.entity.ai.PlayerExtension;

/**
 * The webslinger is the entity spawned when the player right-clicks while holding the webslinger item.
 * This controls the player's movement while the webslinger is actively caught on a block.
 */
public class EntityWebslinger extends Entity
{
	public EntityWebslinger(World world)
	{
		super(world);
		tileX = -1;
		tileY = -1;
		tileZ = -1;
		field_4092_g = 0;
		field_4091_h = false;
		field_4098_a = 0;
		field_4089_j = 0;
		field_4088_k = 0;
		entityHit = null;
		setSize(0.15F, 0.15F);
		targetDistance = 0;
	}

	public EntityWebslinger(World world, double d, double d1, double d2)
	{
		this(world);
		setPosition(d, d1, d2);
	}

	public EntityWebslinger(World world, EntityPlayer entityplayer)
	{
		super(world);
		tileX = -1;
		tileY = -1;
		tileZ = -1;
		field_4092_g = 0;
		field_4091_h = false;
		field_4098_a = 0;
		field_4089_j = 0;
		field_4088_k = 0;
		entityHit = null;
		angler = entityplayer;
		player = entityplayer;

		PlayerExtension playerExtension = PlayerExtension.get(entityplayer);
		playerExtension.webEntity = this;

		setSize(0.25F, 0.25F);
		setLocationAndAngles(entityplayer.posX, (entityplayer.posY + 1.6200000000000001D) - (double)entityplayer.yOffset, entityplayer.posZ, entityplayer.rotationYaw, entityplayer.rotationPitch);
		posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
		posY -= 0.10000000149011612D;
		posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		float f = 8F;
		motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
		motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
		motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F) * f;
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

	public void func_4042_a(double d, double d1, double d2, float f,
			float f1)
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
		field_4090_i = 0;
	}

	public void setPositionAndRotation2(double d, double d1, double d2, float f,
			float f1, int i)
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

	public void matchMotion(int typ, double amt)
	{
		double amt2 = amt;
		if(typ == 0)
		{
			amt2 = amt - player.motionX;
			player.motionX = amt;
			if(entityHit != null) { entityHit.motionX = entityHit.motionX - amt2; }
		}
		if(typ == 1)
		{
			amt2 = amt - player.motionY;
			player.motionY = amt;
			if(entityHit != null) { entityHit.motionY = entityHit.motionY - amt2; }
		}
		if(typ == 2)
		{
			amt2 = amt - player.motionZ;
			player.motionZ = amt;
			if(entityHit != null) { entityHit.motionZ = entityHit.motionZ - amt2; }
		}
	}

	public void onUpdate()
	{
		super.onUpdate();

		if (!worldObj.isRemote)
		{
			return;
		}

		else
		{
			updateSlinger();
		}
	}

	@SideOnly(Side.CLIENT)
	private void updateSlinger()
	{
		if(player != null & (field_4091_h || entityHit != null))
		{
			Vec3 slingerVector = Vec3.createVectorHelper(posX,posY,posZ);
			Vec3 playerVector = Vec3.createVectorHelper(player.posX,player.posY,player.posZ);

			//Prevent the player from experiencing fall damage.
			player.fallDistance = 0.0F;

			//Initialize the target distance.
			if (targetDistance == 0)
			{
				targetDistance = slingerVector.squareDistanceTo(playerVector); 
			}

			//Limit how far away we can get from the slinger ball.
			else if (targetDistance != 0)
			{
				if (targetDistance < 2) 
				{ 
					targetDistance = 2; 
				}

				if(targetDistance > 1000) 
				{ 
					targetDistance = 1000; 
				}
			}

			//Allow the player to reel down by sneaking.
			if (player.isSneaking()) 
			{ 
				//ddist++
				targetDistance += 2; 
			}

			//Don't allow Y motion to bounce when the player is hanging from the slinger.
			boolean isHanging = player.posY - this.posY < 0; //This is negative if the player is hanging from the slinger.
			if (isHanging && player.motionY < 0 && !player.isSneaking())
			{
				player.motionY = 0.0D;
			}

			//If the player is jumping, bring the target distance up to directly behind them.
			if (Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed())
			{
				targetDistance = Math.abs(slingerVector.squareDistanceTo(playerVector)) - 1;
			}

			double distance = slingerVector.squareDistanceTo(playerVector);
			
			//Limit to only increasing motion if player is more than 2 square blocks away. Prevents flying around the hook point.
			if (distance > targetDistance / 1.33D && distance > 2.0D) //1.33D starts the slinger off with a 'pull' on the player.
			{
				//Accelerate the player towards the midpoint between their position and the slinger's.
				Vec3 moveVector = Vec3.createVectorHelper(
						player.motionX * 1.2D + (posX - player.posX) / 2, 
						player.motionY * 1.2D + (posY - player.posY) / 2, 
						player.motionZ * 1.2D + (posZ - player.posZ) / 2).normalize();
				double acceleration = 0.3D;

				if(entityHit != null) 
				{ 
					acceleration = acceleration / 2; 
				}

				double tmotionX = player.motionX * 1D + moveVector.xCoord * acceleration / 2D;
				double tmotionY = player.motionY * 1D + moveVector.yCoord * acceleration / 2D;
				double tmotionZ = player.motionZ * 1D + moveVector.zCoord * acceleration / 2D;

				if(player.posX > posX) { if(tmotionX < player.motionX) { matchMotion(0, tmotionX); } } else { if(tmotionX > player.motionX) { matchMotion(0, tmotionX); } }
				if(player.posY > posY) { if(tmotionY < player.motionY) { matchMotion(1, tmotionY); } } else { if(tmotionY > player.motionY) { matchMotion(1, tmotionY); } }
				if(player.posZ > posZ) { if(tmotionZ < player.motionZ) { matchMotion(2, tmotionZ); } } else { if(tmotionZ > player.motionZ) { matchMotion(2, tmotionZ); } }
			}

			else
			{
				if(angler != null)
				{
					boolean isJumping = Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed();
					
					if (isJumping && slingerVector.squareDistanceTo(playerVector) > 1.5D)
					{
						targetDistance = targetDistance - 6;
						
						if (targetDistance < 1.0D)
						{
							targetDistance = 1.0D;
						}
					}
				}
			}
		}

		if(field_6388_l > 0)
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
		if(!worldObj.isRemote)
		{
			if(player.isDead || !player.isEntityAlive() || getDistanceSqToEntity(player) > 1024D)
			{
				setDead();
				if(angler != null) 
				{ 
					PlayerExtension playerExtension = PlayerExtension.get(angler);
					playerExtension.webEntity = null;
				}

				return;
			}
			if(entityHit != null)
			{
				if(entityHit.isDead)
				{
					entityHit = null;
				} else
				{
					posX = entityHit.posX;
					posY = entityHit.boundingBox.minY + (double)entityHit.height * 0.80000000000000004D;
					posZ = entityHit.posZ;
					return;
				}
			}
		}
		if(field_4098_a > 0)
		{
			field_4098_a--;
		}
		if(field_4091_h)
		{

			field_4090_i++;
			if(field_4090_i == 1200)
			{
				setDead();
			}
			return;

		} else
		{
			field_4089_j++;
		}
		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		if(movingobjectposition != null)
		{
			vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		Entity entity = null;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double d3 = 0.0D;
		for(int j = 0; j < list.size(); j++)
		{
			Entity entity1 = (Entity)list.get(j);
			if(!entity1.canBeCollidedWith() || entity1 == player && field_4089_j < 5)
			{
				continue;
			}
			float f2 = 0.3F;
			AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f2, f2, f2);
			MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
			if(movingobjectposition1 == null)
			{
				continue;
			}
			double d6 = vec3d.distanceTo(movingobjectposition1.hitVec);
			if(d6 < d3 || d3 == 0.0D)
			{
				entity = entity1;
				d3 = d6;
			}
		}

		if(entity != null)
		{
			movingobjectposition = new MovingObjectPosition(entity);
		}
		if(movingobjectposition != null)
		{
			if(movingobjectposition.entityHit != null)
			{
				if(movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(player), 0))
				{
					entityHit = movingobjectposition.entityHit;
				}
			} else
			{
				motionX = motionX * 0.7; motionY = motionY * 0.7; motionZ = motionZ * 0.7;
				field_4091_h = true;
				posX = posX + motionX;
				posY = posY + motionY;
				posZ = posZ + motionZ;

			}
		}
		if(field_4091_h)
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
		if(isCollidedVertically || isCollidedHorizontally)
		{
			f1 = 0.5F;
			field_4091_h = true;
		}
		int k = 5;
		double d5 = 0.0D;
		for(int l = 0; l < k; l++)
		{
			double d8 = ((boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(l + 0)) / (double)k) - 0.125D) + 0.125D;
			double d9 = ((boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(l + 1)) / (double)k) - 0.125D) + 0.125D;
			AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(boundingBox.minX, d8, boundingBox.minZ, boundingBox.maxX, d9, boundingBox.maxZ);
			if(worldObj.isAABBInMaterial(axisalignedbb1, Material.water))
			{
				d5 += 1.0D / (double)k;
			}
		}

		if(d5 > 0.0D)
		{
			if(field_4088_k > 0)
			{
				field_4088_k--;
			} else
			{
				char c = '\u01F4';
				if(worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + 1, MathHelper.floor_double(posZ)))
				{
					c = '\u012C';
				}
				if(rand.nextInt(c) == 0)
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
		if(field_4088_k > 0)
		{
			motionY -= (double)(rand.nextFloat() * rand.nextFloat() * rand.nextFloat()) * 0.20000000000000001D;
		}
		double d7 = d5 * 2D - 1.0D;
		motionY += 0.029999999105930328D * d7;
		if(d5 > 0.0D)
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
		nbttagcompound.setByte("inTile", (byte)field_4092_g);
		nbttagcompound.setByte("shake", (byte)field_4098_a);
		nbttagcompound.setByte("inGround", (byte)(field_4091_h ? 1 : 0));
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		tileX = nbttagcompound.getShort("xTile");
		tileY = nbttagcompound.getShort("yTile");
		tileZ = nbttagcompound.getShort("zTile");
		field_4092_g = nbttagcompound.getByte("inTile") & 0xff;
		field_4098_a = nbttagcompound.getByte("shake") & 0xff;
		field_4091_h = nbttagcompound.getByte("inGround") == 1;
	}

	public float getShadowSize()
	{
		return 0.0F;
	}

	public int catchFish()
	{
		return 0;
	}

	private int tileX;
	private int tileY;
	private int tileZ;
	private int field_4092_g;
	private boolean field_4091_h;
	public int field_4098_a;
	public EntityPlayer angler;
	public EntityPlayer player;
	private int field_4090_i;
	private int field_4089_j;
	private int field_4088_k;
	public Entity entityHit;
	private int field_6388_l;
	private double field_6387_m;
	private double field_6386_n;
	private double field_6385_o;
	private double field_6384_p;
	private double field_6383_q;
	private double velocityX;
	private double velocityY;
	private double velocityZ;
	private double targetDistance;
}