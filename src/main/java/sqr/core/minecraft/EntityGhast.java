package sqr.core.minecraft;
//package sqr.entity;
//
//import java.util.Random;
//
//import net.minecraft.entity.EntityFlying;
//import net.minecraft.entity.monster.IMob;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.projectile.EntityFireball;
//import net.minecraft.stats.AchievementList;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//
//public class EntityGhast extends EntityFlying implements IMob
//{
//    public int courseChangeCooldown;
//    public double waypointX;
//    public double waypointY;
//    public double waypointZ;
//    private Entity targetedEntity;
//
//
//    private int aggroCooldown;
//    public int prevAttackCounter;
//    public int attackCounter;
//	// SNIP
//	public int webHit;
//	private int eggs;
//	// -SNIP
//
//    public EntityGhast(World par1World)
//    {
//        super(par1World);
//        courseChangeCooldown = 0;
//        targetedEntity = null;
//        aggroCooldown = 0;
//        prevAttackCounter = 0;
//        attackCounter = 0;
//        texture = "/mob/ghast.png";
//        setSize(4F, 4F);
//        isImmuneToFire = true;
//        experienceValue = 5;
//		// SNIP
//		webHit = 0;
//		eggs = 3;
//		// -SNIP
//    }
//
//
//    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
//    {
//		// SNIP
//		if(super.attackEntityFrom(par1DamageSource, par2))
//        {
//			if(isInWeb)
//			{
//				if(eggs > 0)
//				{
//					if(eggs == 3)
//					{
//						dropItem(mod_SpiderQueen.itemGhastEgg, 1);
//						eggs--;
//					}
//					else
//					{
//						Random rm = new Random();
//						if(rm.nextInt(10 * (3-eggs)) == 0)
//						{
//							dropItem(mod_SpiderQueen.itemGhastEgg, 1);
//							eggs--;
//						}
//					}
//				}
//			}
//			return true;
//		}
//		// -SNIP
//
//        if ("fireball".equals(par1DamageSource.getDamageType()) && (par1DamageSource.getEntity() instanceof EntityPlayer))
//        {
//            super.attackEntityFrom(par1DamageSource, 1000);
//            ((EntityPlayer)par1DamageSource.getEntity()).triggerAchievement(AchievementList.ghast);
//            return true;
//        }
//        else
//        {
//            return super.attackEntityFrom(par1DamageSource, par2);
//        }
//    }
//
//    protected void entityInit()
//    {
//        super.entityInit();
//        dataWatcher.addObject(16, Byte.valueOf((byte)0));
//    }
//
//    public int getMaxHealth()
//    {
//        return 10;
//    }
//
//
//    public void onUpdate()
//    {
//        super.onUpdate();
//		webHit = 0; // SNIP
//        byte byte0 = dataWatcher.getWatchableObjectByte(16);
//        texture = byte0 != 1 ? "/mob/ghast.png" : "/mob/ghast_fire.png";
//    }
//
//    protected void updateEntityActionState()
//    {
//        if (!worldObj.isRemote && worldObj.difficultySetting == 0)
//        {
//            setDead();
//        }
//
//        despawnEntity();
//        prevAttackCounter = attackCounter;
//        double d = waypointX - posX;
//        double d1 = waypointY - posY;
//        double d2 = waypointZ - posZ;
//        double d3 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
//
//        if (d3 < 1.0D || d3 > 60D)
//        {
//            waypointX = posX + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16F);
//            waypointY = posY + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16F);
//            waypointZ = posZ + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16F);
//        }
//
//        if (courseChangeCooldown-- <= 0)
//        {
//            courseChangeCooldown += rand.nextInt(5) + 2;
//
//            if (isCourseTraversable(waypointX, waypointY, waypointZ, d3))
//            {
//                motionX += (d / d3) * 0.10000000000000001D;
//                motionY += (d1 / d3) * 0.10000000000000001D;
//                motionZ += (d2 / d3) * 0.10000000000000001D;
//            }
//            else
//            {
//                waypointX = posX;
//                waypointY = posY;
//                waypointZ = posZ;
//            }
//        }
//
//        if (targetedEntity != null && targetedEntity.isDead)
//        {
//            targetedEntity = null;
//        }
//
//        if (targetedEntity == null || aggroCooldown-- <= 0)
//        {
//            targetedEntity = worldObj.getClosestVulnerablePlayerToEntity(this, 100D);
//
//            if (targetedEntity != null)
//            {
//                aggroCooldown = 20;
//            }
//        }
//
//        double d4 = 64D;
//
//        if (targetedEntity != null && targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
//        {
//            double d5 = targetedEntity.posX - posX;
//            double d6 = (targetedEntity.boundingBox.minY + (double)(targetedEntity.height / 2.0F)) - (posY + (double)(height / 2.0F));
//            double d7 = targetedEntity.posZ - posZ;
//            renderYawOffset = rotationYaw = (-(float)Math.atan2(d5, d7) * 180F) / (float)Math.PI;
//
//            if (canEntityBeSeen(targetedEntity))
//            {
//                if (attackCounter == 10)
//                {
//                    worldObj.playAuxSFXAtEntity(null, 1007, (int)posX, (int)posY, (int)posZ, 0);
//                }
//
//                attackCounter++;
//
//                if (attackCounter == 20)
//                {
//                    worldObj.playAuxSFXAtEntity(null, 1008, (int)posX, (int)posY, (int)posZ, 0);
//                    EntityFireball entityfireball = new EntityFireball(worldObj, this, d5, d6, d7);
//                    double d8 = 4D;
//                    Vec3D vec3d = getLook(1.0F);
//                    entityfireball.posX = posX + vec3d.xCoord * d8;
//                    entityfireball.posY = posY + (double)(height / 2.0F) + 0.5D;
//                    entityfireball.posZ = posZ + vec3d.zCoord * d8;
//                    worldObj.spawnEntityInWorld(entityfireball);
//                    attackCounter = -40;
//                }
//            }
//            else if (attackCounter > 0)
//            {
//                attackCounter--;
//            }
//        }
//        else
//        {
//            renderYawOffset = rotationYaw = (-(float)Math.atan2(motionX, motionZ) * 180F) / (float)Math.PI;
//
//            if (attackCounter > 0)
//            {
//                attackCounter--;
//            }
//        }
//
//        if (!worldObj.isRemote)
//        {
//            byte byte0 = dataWatcher.getWatchableObjectByte(16);
//            byte byte1 = (byte)(attackCounter <= 10 ? 0 : 1);
//
//            if (byte0 != byte1)
//            {
//                dataWatcher.updateObject(16, Byte.valueOf(byte1));
//            }
//        }
//    }
//
//
//    private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
//    {
//        double d = (waypointX - posX) / par7;
//        double d1 = (waypointY - posY) / par7;
//        double d2 = (waypointZ - posZ) / par7;
//        AxisAlignedBB axisalignedbb = boundingBox.copy();
//
//        for (int i = 1; (double)i < par7; i++)
//        {
//            axisalignedbb.offset(d, d1, d2);
//
//            if (worldObj.getCollidingBoundingBoxes(this, axisalignedbb).size() > 0)
//            {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//
//    protected String getLivingSound()
//    {
//        return "mob.ghast.moan";
//    }
//
//
//    protected String getHurtSound()
//    {
//        return "mob.ghast.scream";
//    }
//
//
//    protected String getDeathSound()
//    {
//        return "mob.ghast.death";
//    }
//
//
//    protected int getDropItemId()
//    {
//        return Items.gunpowder;
//    }
//
//
//    protected void dropFewItems(boolean par1, int par2)
//    {
//        int i = rand.nextInt(2) + rand.nextInt(1 + par2);
//
//        for (int j = 0; j < i; j++)
//        {
//            dropItem(Items.ghastTear, 1);
//        }
//
//        i = rand.nextInt(3) + rand.nextInt(1 + par2);
//
//        for (int k = 0; k < i; k++)
//        {
//            dropItem(Items.gunpowder, 1);
//        }
//    }
//
//
//    protected float getSoundVolume()
//    {
//        return 10F;
//    }
//
//
//    public boolean getCanSpawnHere()
//    {
//        return rand.nextInt(20) == 0 && super.getCanSpawnHere() && worldObj.difficultySetting > 0;
//    }
//
//
//    public int getMaxSpawnedInChunk()
//    {
//        return 1;
//    }
//}
