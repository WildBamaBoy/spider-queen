//package sqr.core.minecraft;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
//
//import sqr.entity.EntityPlayer;
//import net.minecraft.block.Block;
//import net.minecraft.block.material.Material;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.EntityBodyHelper;
//import net.minecraft.entity.EnumCreatureAttribute;
//import net.minecraft.entity.ai.EntityAITasks;
//import net.minecraft.entity.ai.EntityJumpHelper;
//import net.minecraft.entity.ai.EntityLookHelper;
//import net.minecraft.entity.ai.EntityMoveHelper;
//import net.minecraft.entity.ai.EntitySenses;
//import net.minecraft.entity.item.EntityXPOrb;
//import net.minecraft.entity.passive.EntityWolf;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.pathfinding.PathNavigate;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.potion.PotionHelper;
//import net.minecraft.profiler.Profiler;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.ChunkCoordinates;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.MovingObjectPosition;
//import net.minecraft.world.World;
//
//public abstract class EntityLivingBase extends Entity
//{
//	// SNIP
//	public boolean iJumping() { return isJumping; }
//	public void setAte(int ate) { ateLast = ate; }
//	public int getAte() { return ateLast; }
//	private int ateLast;
//	private TileEntity tetarget;
//	private int followent;
//	public boolean spiderfriend;
//	// - SNIP
//
//
//    public int heartsHalvesLife;
//    public float field_9365_p;
//    public float field_9363_r;
//    public float renderYawOffset;
//    public float prevRenderYawOffset;
//
//
//    public float rotationYawHead;
//
//
//    public float prevRotationYawHead;
//    protected float field_9362_u;
//    protected float field_9361_v;
//    protected float field_9360_w;
//    protected float field_9359_x;
//    protected boolean field_9358_y;
//
//
//    protected String texture;
//    protected boolean field_9355_A;
//    protected float field_9353_B;
//
//
//    protected String entityType;
//    protected float field_9349_D;
//
//
//    protected int scoreValue;
//    protected float field_9345_F;
//
//
//    public float landMovementFactor;
//
//
//    public float jumpMovementFactor;
//    public float prevSwingProgress;
//    public float swingProgress;
//    protected int health;
//    public int prevHealth;
//
//
//    protected int carryoverDamage;
//
//
//    private int livingSoundTime;
//
//
//    public int hurtTime;
//
//
//    public int maxHurtTime;
//
//
//    public float attackedAtYaw;
//
//
//    public int deathTime;
//    public int attackTime;
//    public float prevCameraPitch;
//    public float cameraPitch;
//
//
//    protected boolean dead;
//
//
//    protected int experienceValue;
//    public int field_9326_T;
//    public float field_9325_U;
//    public float field_705_Q;
//    public float field_704_R;
//    public float field_703_S;
//
//
//    protected EntityPlayer attackingPlayer;
//
//
//    protected int recentlyHit;
//
//
//    private EntityLivingBase entityLivingToAttack;
//    private int revengeTimer;
//    private EntityLivingBase lastAttackingEntity;
//
//
//    public int arrowHitTempCounter;
//    public int arrowHitTimer;
//    protected HashMap activePotionsMap;
//
//
//    private boolean potionsNeedUpdate;
//    private int field_39002_c;
//    private EntityLookHelper lookHelper;
//    private EntityMoveHelper moveHelper;
//
//
//    private EntityJumpHelper jumpHelper;
//    private EntityBodyHelper bodyHelper;
//    private PathNavigate navigator;
//    protected EntityAITasks tasks;
//    protected EntityAITasks targetTasks;
//
//
//    private EntityLivingBase attackTarget;
//    private EntitySenses field_48104_at;
//    private float field_48111_au;
//    private ChunkCoordinates homePosition;
//
//
//    private float maximumHomeDistance;
//
//
//    protected int newPosRotationIncrements;
//
//
//    protected double newPosX;
//
//
//    protected double newPosY;
//
//
//    protected double newPosZ;
//
//
//    protected double newRotationYaw;
//
//
//    protected double newRotationPitch;
//    float field_9348_ae;
//
//
//    protected int naturalArmorRating;
//
//
//    protected int entityAge;
//    protected float moveStrafing;
//    protected float moveForward;
//    protected float randomYawVelocity;
//
//
//    protected boolean isJumping;
//    protected float defaultPitch;
//    protected float moveSpeed;
//
//
//    private int jumpTicks;
//
//
//    private Entity currentTarget;
//
//
//    protected int numTicksToChaseTarget;
//
//    public EntityLivingBase(World par1World)
//    {
//        super(par1World);
//
//		spiderfriend = false; // SNIP
//
//        heartsHalvesLife = 20;
//        renderYawOffset = 0.0F;
//        prevRenderYawOffset = 0.0F;
//        rotationYawHead = 0.0F;
//        prevRotationYawHead = 0.0F;
//        field_9358_y = true;
//        texture = "/mob/char.png";
//        field_9355_A = true;
//        field_9353_B = 0.0F;
//        entityType = null;
//        field_9349_D = 1.0F;
//        scoreValue = 0;
//        field_9345_F = 0.0F;
//        landMovementFactor = 0.1F;
//        jumpMovementFactor = 0.02F;
//        attackedAtYaw = 0.0F;
//        deathTime = 0;
//        attackTime = 0;
//        dead = false;
//        field_9326_T = -1;
//        field_9325_U = (float)(Math.random() * 0.89999997615814209D + 0.10000000149011612D);
//        attackingPlayer = null;
//        recentlyHit = 0;
//        entityLivingToAttack = null;
//        revengeTimer = 0;
//        lastAttackingEntity = null;
//        arrowHitTempCounter = 0;
//        arrowHitTimer = 0;
//        activePotionsMap = new HashMap();
//        potionsNeedUpdate = true;
//        tasks = new EntityAITasks();
//        targetTasks = new EntityAITasks();
//        homePosition = new ChunkCoordinates(0, 0, 0);
//        maximumHomeDistance = -1F;
//        field_9348_ae = 0.0F;
//        naturalArmorRating = 0;
//        entityAge = 0;
//        isJumping = false;
//        defaultPitch = 0.0F;
//        //TODO Attribute moveSpeed = 0.7F;
//        jumpTicks = 0;
//        numTicksToChaseTarget = 0;
//        health = getMaxHealth();
//        preventEntitySpawning = true;
//        lookHelper = new EntityLookHelper(this);
//        moveHelper = new EntityMoveHelper(this);
//        jumpHelper = new EntityJumpHelper(this);
//        bodyHelper = new EntityBodyHelper(this);
//        navigator = new PathNavigate(this, par1World, 16F);
//        field_48104_at = new EntitySenses(this);
//        field_9363_r = (float)(Math.random() + 1.0D) * 0.01F;
//        setPosition(posX, posY, posZ);
//        field_9365_p = (float)Math.random() * 12398F;
//        rotationYaw = (float)(Math.random() * Math.PI * 2D);
//        rotationYawHead = rotationYaw;
//        stepHeight = 0.5F;
//    }
//
//
//	// SNIP
//	public void spiderBaitCode()
//	{
//		//if(entityToAttack == null)
//		//{
//
//			boolean changee = false;
//
//		if(spiderfriend)
//		{
//
//			EntityPlayer epl = worldObj.getClosestPlayer(posX, posY, posZ, 96D);
//			if(epl != null)
//			{
//				if(epl.getCurrentEquippedItem() != null)
//				{
//					if(epl.getCurrentEquippedItem().getItem() != null)
//					{
//						if(epl.getCurrentEquippedItem().getItem() == mod_SpiderQueen.spiderbait)
//						{
//							if(mod_SpiderQueen.getDistance3d(posX, posY, posZ, epl.posX, epl.posY, epl.posZ) > 6F)
//							{
//								if(isCollidedHorizontally) { isJumping = true; }
//								moveForward = moveSpeed;
//								faceEntity(epl, 30F, 30F);
//								changee = true;
//							}
//
//							if(mod_SpiderQueen.getDistance3d(posX, posY, posZ, epl.posX, epl.posY, epl.posZ) < 5F)
//							{
//								moveForward *= -1f;
//							}
//
//
//						}
//					}
//				}
//			}
//		}
//
//		if(changee == false)
//		{
//			if(getAte() > 0)
//			{
//				tetarget = null;
//				setAte(getAte()-1);
//			}
//			else
//			{
//				Random rm = new Random();
//				if(tetarget == null & rm.nextInt(130) == 5)
//				{
//					tetarget = (TileEntity) mod_SpiderQueen.getClosestBait(worldObj, this, posX, posY, posZ, 96D);
//				}
//
//				if(tetarget != null)
//				{
//					int bbid = worldObj.getBlock(tetarget.xCoord, tetarget.yCoord, tetarget.zCoord);
//					if(bbid != mod_SpiderQueen.spiderbait) { tetarget = null; }
//				}
//
//				if(tetarget != null)
//				{
//					if(mod_SpiderQueen.getDistance3d(posX, posY, posZ, tetarget.xCoord, tetarget.yCoord, tetarget.zCoord) > 6F)
//					{
//						moveForward = moveSpeed;
//						mod_SpiderQueen.faceTEntity(this,tetarget, 30F);
//					}
//				}
//			}
//		}
//		//}
//	}
//	// -SNIP
//
//    public EntityLookHelper getLookHelper()
//    {
//        return lookHelper;
//    }
//
//    public EntityMoveHelper getMoveHelper()
//    {
//        return moveHelper;
//    }
//
//    public EntityJumpHelper getJumpHelper()
//    {
//        return jumpHelper;
//    }
//
//    public PathNavigate getNavigator()
//    {
//        return navigator;
//    }
//
//    public EntitySenses func_48090_aM()
//    {
//        return field_48104_at;
//    }
//
//    public Random getRNG()
//    {
//        return rand;
//    }
//
//    public EntityLivingBase getAITarget()
//    {
//        return entityLivingToAttack;
//    }
//
//    public EntityLivingBase getLastAttackingEntity()
//    {
//        return lastAttackingEntity;
//    }
//
//    public void setLastAttackingEntity(Entity par1Entity)
//    {
//        if (par1Entity instanceof EntityLivingBase)
//        {
//            lastAttackingEntity = (EntityLivingBase)par1Entity;
//        }
//    }
//
//    public int getAge()
//    {
//        return entityAge;
//    }
//
//    public void func_48079_f(float par1)
//    {
//        rotationYawHead = par1;
//    }
//
//    public float func_48101_aR()
//    {
//        return field_48111_au;
//    }
//
//    public void func_48098_g(float par1)
//    {
//        field_48111_au = par1;
//        setMoveForward(par1);
//    }
//
//    public boolean attackEntityAsMob(Entity par1Entity)
//    {
//        setLastAttackingEntity(par1Entity);
//        return false;
//    }
//
//
//    public EntityLivingBase getAttackTarget()
//    {
//        return attackTarget;
//    }
//
//
//    public void setAttackTarget(EntityLivingBase par1EntityLivingBase)
//    {
//        attackTarget = par1EntityLivingBase;
//    }
//
//    public boolean func_48100_a(Class par1Class)
//    {
//        return (net.minecraft.src.EntityCreeper.class) != par1Class && (net.minecraft.src.EntityGhast.class) != par1Class;
//    }
//
//
//    public void eatGrassBonus()
//    {
//    }
//
//
//    public boolean isWithinHomeDistanceCurrentPosition()
//    {
//        return isWithinHomeDistance(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
//    }
//
//    public boolean isWithinHomeDistance(int par1, int par2, int par3)
//    {
//        if (maximumHomeDistance == -1F)
//        {
//            return true;
//        }
//        else
//        {
//            return homePosition.getDistanceSquared(par1, par2, par3) < maximumHomeDistance * maximumHomeDistance;
//        }
//    }
//
//    public void setHomeArea(int par1, int par2, int par3, int par4)
//    {
//        homePosition.set(par1, par2, par3);
//        maximumHomeDistance = par4;
//    }
//
//    public ChunkCoordinates getHomePosition()
//    {
//        return homePosition;
//    }
//
//    public float getMaximumHomeDistance()
//    {
//        return maximumHomeDistance;
//    }
//
//    public void detachHome()
//    {
//        maximumHomeDistance = -1F;
//    }
//
//    public boolean hasHome()
//    {
//        return maximumHomeDistance != -1F;
//    }
//
//    public void setRevengeTarget(EntityLivingBase par1EntityLivingBase)
//    {
//        entityLivingToAttack = par1EntityLivingBase;
//        revengeTimer = entityLivingToAttack == null ? 0 : 60;
//    }
//
//    protected void entityInit()
//    {
//        dataWatcher.addObject(8, Integer.valueOf(field_39002_c));
//    }
//
//
//    public boolean canEntityBeSeen(Entity par1Entity)
//    {
//        return worldObj.rayTraceBlocks(Vec3.createVectorHelper(posX, posY + (double)getEyeHeight(), posZ), Vec3.createVectorHelper(par1Entity.posX, par1Entity.posY + (double)par1Entity.getEyeHeight(), par1Entity.posZ)) == null;
//    }
//
//
//    public String getTexture()
//    {
//        return texture;
//    }
//
//
//    public boolean canBeCollidedWith()
//    {
//        return !isDead;
//    }
//
//
//    public boolean canBePushed()
//    {
//        return !isDead;
//    }
//
//    public float getEyeHeight()
//    {
//        return height * 0.85F;
//    }
//
//
//    public int getTalkInterval()
//    {
//        return 80;
//    }
//
//
//    public void playLivingSound()
//    {
//        String s = getLivingSound();
//
//        if (s != null)
//        {
//            worldObj.playSoundAtEntity(this, s, getSoundVolume(), getSoundPitch());
//        }
//    }
//
//
//    public void onEntityUpdate()
//    {
//
//		// SNIP
//
//			if(getAte() > 0)
//			{ setAte(getAte()-1); }
//
//		//- SNIP
//
//        prevSwingProgress = swingProgress;
//        super.onEntityUpdate();
//        Profiler.startSection("mobBaseTick");
//
//        if (isEntityAlive() && rand.nextInt(1000) < livingSoundTime++)
//        {
//            livingSoundTime = -getTalkInterval();
//            playLivingSound();
//        }
//
//        if (isEntityAlive() && isEntityInsideOpaqueBlock())
//        {
//            if (!attackEntityFrom(DamageSource.inWall, 1));
//        }
//
//        if (isImmuneToFire() || worldObj.isRemote)
//        {
//            extinguish();
//        }
//
//        if (isEntityAlive() && isInsideOfMaterial(Material.water) && !canBreatheUnderwater() && !activePotionsMap.containsKey(Integer.valueOf(Potion.waterBreathing.id)))
//        {
//            setAir(decreaseAirSupply(getAir()));
//
//            if (getAir() == -20)
//            {
//                setAir(0);
//
//                for (int i = 0; i < 8; i++)
//                {
//                    float f = rand.nextFloat() - rand.nextFloat();
//                    float f1 = rand.nextFloat() - rand.nextFloat();
//                    float f2 = rand.nextFloat() - rand.nextFloat();
//                    worldObj.spawnParticle("bubble", posX + (double)f, posY + (double)f1, posZ + (double)f2, motionX, motionY, motionZ);
//                }
//
//                attackEntityFrom(DamageSource.drown, 2);
//            }
//
//            extinguish();
//        }
//        else
//        {
//            setAir(300);
//        }
//
//        prevCameraPitch = cameraPitch;
//
//        if (attackTime > 0)
//        {
//            attackTime--;
//        }
//
//        if (hurtTime > 0)
//        {
//            hurtTime--;
//        }
//
//        if (heartsLife > 0)
//        {
//            heartsLife--;
//        }
//
//        if (health <= 0)
//        {
//            onDeathUpdate();
//        }
//
//        if (recentlyHit > 0)
//        {
//            recentlyHit--;
//        }
//        else
//        {
//            attackingPlayer = null;
//        }
//
//        if (lastAttackingEntity != null && !lastAttackingEntity.isEntityAlive())
//        {
//            lastAttackingEntity = null;
//        }
//
//        if (entityLivingToAttack != null)
//        {
//            if (!entityLivingToAttack.isEntityAlive())
//            {
//                setRevengeTarget(null);
//            }
//            else if (revengeTimer > 0)
//            {
//                revengeTimer--;
//            }
//            else
//            {
//                setRevengeTarget(null);
//            }
//        }
//
//        updatePotionEffects();
//        field_9359_x = field_9360_w;
//        prevRenderYawOffset = renderYawOffset;
//        prevRotationYawHead = rotationYawHead;
//        prevRotationYaw = rotationYaw;
//        prevRotationPitch = rotationPitch;
//        Profiler.endSection();
//    }
//
//
//    protected void onDeathUpdate()
//    {
//        deathTime++;
//
//        if (deathTime == 20)
//        {
//            if (!worldObj.isRemote && (recentlyHit > 0 || isPlayer()) && !isChild())
//            {
//                for (int i = getExperiencePoints(attackingPlayer); i > 0;)
//                {
//                    int k = EntityXPOrb.getXPSplit(i);
//                    i -= k;
//                    worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX, posY, posZ, k));
//                }
//            }
//
//            onEntityDeath();
//            setDead();
//
//            for (int j = 0; j < 20; j++)
//            {
//                double d = rand.nextGaussian() * 0.02D;
//                double d1 = rand.nextGaussian() * 0.02D;
//                double d2 = rand.nextGaussian() * 0.02D;
//                worldObj.spawnParticle("explode", (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
//            }
//        }
//    }
//
//
//    protected int decreaseAirSupply(int par1)
//    {
//        return par1 - 1;
//    }
//
//
//    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
//    {
//        return experienceValue;
//    }
//
//
//    protected boolean isPlayer()
//    {
//        return false;
//    }
//
//
//    public void spawnExplosionParticle()
//    {
//        for (int i = 0; i < 20; i++)
//        {
//            double d = rand.nextGaussian() * 0.02D;
//            double d1 = rand.nextGaussian() * 0.02D;
//            double d2 = rand.nextGaussian() * 0.02D;
//            double d3 = 10D;
//            worldObj.spawnParticle("explode", (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - d * d3, (posY + (double)(rand.nextFloat() * height)) - d1 * d3, (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - d2 * d3, d, d1, d2);
//        }
//    }
//
//
//    public void updateRidden()
//    {
//        super.updateRidden();
//        field_9362_u = field_9361_v;
//        field_9361_v = 0.0F;
//        fallDistance = 0.0F;
//    }
//
//
//    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
//    {
//        yOffset = 0.0F;
//        newPosX = par1;
//        newPosY = par3;
//        newPosZ = par5;
//        newRotationYaw = par7;
//        newRotationPitch = par8;
//        newPosRotationIncrements = par9;
//    }
//
//
//    public void onUpdate()
//    {
//        super.onUpdate();
//
//        if (arrowHitTempCounter > 0)
//        {
//            if (arrowHitTimer <= 0)
//            {
//                arrowHitTimer = 60;
//            }
//
//            arrowHitTimer--;
//
//            if (arrowHitTimer <= 0)
//            {
//                arrowHitTempCounter--;
//            }
//        }
//
//        onLivingUpdate();
//        double d = posX - prevPosX;
//        double d1 = posZ - prevPosZ;
//        float f = MathHelper.sqrt_double(d * d + d1 * d1);
//        float f1 = renderYawOffset;
//        float f2 = 0.0F;
//        field_9362_u = field_9361_v;
//        float f3 = 0.0F;
//
//        if (f > 0.05F)
//        {
//            f3 = 1.0F;
//            f2 = f * 3F;
//            f1 = ((float)Math.atan2(d1, d) * 180F) / (float)Math.PI - 90F;
//        }
//
//        if (swingProgress > 0.0F)
//        {
//            f1 = rotationYaw;
//        }
//
//        if (!onGround)
//        {
//            f3 = 0.0F;
//        }
//
//        field_9361_v = field_9361_v + (f3 - field_9361_v) * 0.3F;
//
//        if (isAIEnabled())
//        {
//            bodyHelper.func_48650_a();
//        }
//        else
//        {
//            float f4;
//
//            for (f4 = f1 - renderYawOffset; f4 < -180F; f4 += 360F) { }
//
//            for (; f4 >= 180F; f4 -= 360F) { }
//
//            renderYawOffset += f4 * 0.3F;
//            float f5;
//
//            for (f5 = rotationYaw - renderYawOffset; f5 < -180F; f5 += 360F) { }
//
//            for (; f5 >= 180F; f5 -= 360F) { }
//
//            boolean flag = f5 < -90F || f5 >= 90F;
//
//            if (f5 < -75F)
//            {
//                f5 = -75F;
//            }
//
//            if (f5 >= 75F)
//            {
//                f5 = 75F;
//            }
//
//            renderYawOffset = rotationYaw - f5;
//
//            if (f5 * f5 > 2500F)
//            {
//                renderYawOffset += f5 * 0.2F;
//            }
//
//            if (flag)
//            {
//                f2 *= -1F;
//            }
//        }
//
//        for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
//
//        for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
//
//        for (; renderYawOffset - prevRenderYawOffset < -180F; prevRenderYawOffset -= 360F) { }
//
//        for (; renderYawOffset - prevRenderYawOffset >= 180F; prevRenderYawOffset += 360F) { }
//
//        for (; rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
//
//        for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
//
//        for (; rotationYawHead - prevRotationYawHead < -180F; prevRotationYawHead -= 360F) { }
//
//        for (; rotationYawHead - prevRotationYawHead >= 180F; prevRotationYawHead += 360F) { }
//
//        field_9360_w += f2;
//    }
//
//
//    protected void setSize(float par1, float par2)
//    {
//        super.setSize(par1, par2);
//    }
//
//
//    public void heal(int par1)
//    {
//        if (health <= 0)
//        {
//            return;
//        }
//
//        health += par1;
//
//        if (health > getMaxHealth())
//        {
//            health = getMaxHealth();
//        }
//
//        heartsLife = heartsHalvesLife / 2;
//    }
//
//    public abstract int getMaxHealth();
//
//    public int getHealth()
//    {
//        return health;
//    }
//
//    public void setEntityHealth(int par1)
//    {
//        health = par1;
//
//        if (par1 > getMaxHealth())
//        {
//            par1 = getMaxHealth();
//        }
//    }
//
//
//    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
//    {
//        if (worldObj.isRemote)
//        {
//            return false;
//        }
//
//        entityAge = 0;
//
//        if (health <= 0)
//        {
//            return false;
//        }
//
//        if (par1DamageSource.fireDamage() && isPotionActive(Potion.fireResistance))
//        {
//            return false;
//        }
//
//        field_704_R = 1.5F;
//        boolean flag = true;
//
//        if ((float)heartsLife > (float)heartsHalvesLife / 2.0F)
//        {
//            if (par2 <= naturalArmorRating)
//            {
//                return false;
//            }
//
//            damageEntity(par1DamageSource, par2 - naturalArmorRating);
//            naturalArmorRating = par2;
//            flag = false;
//        }
//        else
//        {
//            naturalArmorRating = par2;
//            prevHealth = health;
//            heartsLife = heartsHalvesLife;
//            damageEntity(par1DamageSource, par2);
//            hurtTime = maxHurtTime = 10;
//        }
//
//        attackedAtYaw = 0.0F;
//        Entity entity = par1DamageSource.getEntity();
//
//        if (entity != null)
//        {
//            if (entity instanceof EntityLivingBase)
//            {
//                setRevengeTarget((EntityLivingBase)entity);
//            }
//
//            if (entity instanceof EntityPlayer)
//            {
//                recentlyHit = 60;
//                attackingPlayer = (EntityPlayer)entity;
//            }
//            else if (entity instanceof EntityWolf)
//            {
//                EntityWolf entitywolf = (EntityWolf)entity;
//
//                if (entitywolf.isTamed())
//                {
//                    recentlyHit = 60;
//                    attackingPlayer = null;
//                }
//            }
//        }
//
//        if (flag)
//        {
//            worldObj.setEntityState(this, (byte)2);
//            setBeenAttacked();
//
//            if (entity != null)
//            {
//                double d = entity.posX - posX;
//                double d1;
//
//                for (d1 = entity.posZ - posZ; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
//                {
//                    d = (Math.random() - Math.random()) * 0.01D;
//                }
//
//                attackedAtYaw = (float)((Math.atan2(d1, d) * 180D) / Math.PI) - rotationYaw;
//                knockBack(entity, par2, d, d1);
//            }
//            else
//            {
//                attackedAtYaw = (int)(Math.random() * 2D) * 180;
//            }
//        }
//
//        if (health <= 0)
//        {
//            if (flag)
//            {
//                worldObj.playSoundAtEntity(this, getDeathSound(), getSoundVolume(), getSoundPitch());
//            }
//
//            onDeath(par1DamageSource);
//        }
//        else if (flag)
//        {
//            worldObj.playSoundAtEntity(this, getHurtSound(), getSoundVolume(), getSoundPitch());
//        }
//
//        return true;
//    }
//
//
//    private float getSoundPitch()
//    {
//        if (isChild())
//        {
//            return (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.5F;
//        }
//        else
//        {
//            return (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F;
//        }
//    }
//
//
//    public void performHurtAnimation()
//    {
//        hurtTime = maxHurtTime = 10;
//        attackedAtYaw = 0.0F;
//    }
//
//
//    public int getTotalArmorValue()
//    {
//        return 0;
//    }
//
//    protected void damageArmor(int i)
//    {
//    }
//
//
//    protected int applyArmorCalculations(DamageSource par1DamageSource, int par2)
//    {
//        if (!par1DamageSource.isUnblockable())
//        {
//            int i = 25 - getTotalArmorValue();
//            int j = par2 * x +carryoverDamage;
//            damageArmor(par2);
//            par2 = j / 25;
//            carryoverDamage = j % 25;
//        }
//
//        return par2;
//    }
//
//
//    protected int applyPotionDamageCalculations(DamageSource par1DamageSource, int par2)
//    {
//        if (isPotionActive(Potion.resistance))
//        {
//            int i = (getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
//            int j = 25 - i;
//            int k = par2 * y + carryoverDamage;
//            par2 = k / 25;
//            carryoverDamage = k % 25;
//        }
//
//        return par2;
//    }
//
//
//    protected void damageEntity(DamageSource par1DamageSource, int par2)
//    {
//        par2 = applyArmorCalculations(par1DamageSource, par2);
//        par2 = applyPotionDamageCalculations(par1DamageSource, par2);
//        health -= par2;
//    }
//
//
//    protected float getSoundVolume()
//    {
//        return 1.0F;
//    }
//
//
//    protected String getLivingSound()
//    {
//        return null;
//    }
//
//
//    protected String getHurtSound()
//    {
//        return "damage.hurtflesh";
//    }
//
//
//    protected String getDeathSound()
//    {
//        return "damage.hurtflesh";
//    }
//
//
//    public void knockBack(Entity par1Entity, int par2, double par3, double par5)
//    {
//        isAirBorne = true;
//        float f = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
//        float f1 = 0.4F;
//        motionX /= 2D;
//        motionY /= 2D;
//        motionZ /= 2D;
//        motionX -= (par3 / (double)f) * (double)f1;
//        motionY += f1;
//        motionZ -= (par5 / (double)f) * (double)f1;
//
//        if (motionY > 0.40000000596046448D)
//        {
//            motionY = 0.40000000596046448D;
//        }
//    }
//
//
//    public void onDeath(DamageSource par1DamageSource)
//    {
//        Entity entity = par1DamageSource.getEntity();
//
//        if (scoreValue >= 0 && entity != null)
//        {
//            entity.addToPlayerScore(this, scoreValue);
//        }
//
//        if (entity != null)
//        {
//            entity.onKillEntity(this);
//        }
//
//        dead = true;
//
//        if (!worldObj.isRemote)
//        {
//            int i = 0;
//
//            if (entity instanceof EntityPlayer)
//            {
//                i = EnchantmentHelper.getLootingModifier(((EntityPlayer)entity).inventory);
//            }
//
//            if (!isChild())
//            {
//                dropFewItems(recentlyHit > 0, i);
//
//                if (recentlyHit > 0)
//                {
//                    int j = rand.nextInt(200) - i;
//
//                    if (j < 5)
//                    {
//                        dropRareDrop(j > 0 ? 0 : 1);
//                    }
//                }
//            }
//        }
//
//        worldObj.setEntityState(this, (byte)3);
//    }
//
//    protected void dropRareDrop(int i)
//    {
//    }
//
//
//    protected void dropFewItems(boolean par1, int par2)
//    {
//        int i = getDropItemId();
//
//        if (i > 0)
//        {
//            int j = rand.nextInt(3);
//
//            if (par2 > 0)
//            {
//                y += rand.nextInt(par2 + 1);
//            }
//
//            for (int k = 0; k < j; k++)
//            {
//                dropItem(i, 1);
//            }
//        }
//    }
//
//
//    protected int getDropItemId()
//    {
//        return 0;
//    }
//
//
//    protected void fall(float par1)
//    {
//        super.fall(par1);
//        int i = (int)Math.ceil(par1 - 3F);
//
//        if (i > 0)
//        {
//            if (i > 4)
//            {
//                worldObj.playSoundAtEntity(this, "damage.fallbig", 1.0F, 1.0F);
//            }
//            else
//            {
//                worldObj.playSoundAtEntity(this, "damage.fallsmall", 1.0F, 1.0F);
//            }
//
//            attackEntityFrom(DamageSource.fall, i);
//            int j = worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY - 0.20000000298023224D - (double)yOffset), MathHelper.floor_double(posZ));
//
//            if (j > 0)
//            {
//                StepSound stepsound = Blocks.blocksList[j].stepSound;
//                worldObj.playSoundAtEntity(this, stepsound.getStepSound(), stepsound.getVolume() * 0.5F, stepsound.getPitch() * 0.75F);
//            }
//        }
//    }
//
//
//    public void moveEntityWithHeading(float par1, float par2)
//    {
//        if (isInWater())
//        {
//            double d = posY;
//            moveFlying(par1, par2, isAIEnabled() ? 0.04F : 0.02F);
//            moveEntity(motionX, motionY, motionZ);
//            motionX *= 0.80000001192092896D;
//            motionY *= 0.80000001192092896D;
//            motionZ *= 0.80000001192092896D;
//            motionY -= 0.02D;
//
//            if (isCollidedHorizontally && isOffsetPositionInLiquid(motionX, ((motionY + 0.60000002384185791D) - posY) + d, motionZ))
//            {
//                motionY = 0.30000001192092896D;
//            }
//        }
//        else if (handleLavaMovement())
//        {
//            double d1 = posY;
//            moveFlying(par1, par2, 0.02F);
//            moveEntity(motionX, motionY, motionZ);
//            motionX *= 0.5D;
//            motionY *= 0.5D;
//            motionZ *= 0.5D;
//            motionY -= 0.02D;
//
//            if (isCollidedHorizontally && isOffsetPositionInLiquid(motionX, ((motionY + 0.60000002384185791D) - posY) + d1, motionZ))
//            {
//                motionY = 0.30000001192092896D;
//            }
//        }
//        else
//        {
//            float f = 0.91F;
//
//            if (onGround)
//            {
//                f = 0.5460001F;
//                int i = worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));
//
//                if (i > 0)
//                {
//                    f = Blocks.blocksList[i].slipperiness * 0.91F;
//                }
//            }
//
//            float f1 = 0.1627714F / (f * f * f);
//            float f2;
//
//            if (onGround)
//            {
//                if (isAIEnabled())
//                {
//                    f2 = func_48101_aR();
//                }
//                else
//                {
//                    f2 = landMovementFactor;
//                }
//
//                f2 *= f1;
//            }
//            else
//            {
//                f2 = jumpMovementFactor;
//            }
//
//            moveFlying(par1, par2, f2);
//            f = 0.91F;
//
//            if (onGround)
//            {
//                f = 0.5460001F;
//                int j = worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));
//
//                if (j > 0)
//                {
//                    f = Blocks.blocksList[j].slipperiness * 0.91F;
//                }
//            }
//
//            if (isOnLadder())
//            {
//                float f3 = 0.15F;
//
//                if (motionX < (double)(-f3))
//                {
//                    motionX = -f3;
//                }
//
//                if (motionX > (double)f3)
//                {
//                    motionX = f3;
//                }
//
//                if (motionZ < (double)(-f3))
//                {
//                    motionZ = -f3;
//                }
//
//                if (motionZ > (double)f3)
//                {
//                    motionZ = f3;
//                }
//
//                fallDistance = 0.0F;
//
//                if (motionY < -0.14999999999999999D)
//                {
//                    motionY = -0.14999999999999999D;
//                }
//
//                boolean flag = isSneaking() && (this instanceof EntityPlayer);
//
//                if (flag && motionY < 0.0D)
//                {
//                    motionY = 0.0D;
//                }
//            }
//
//            moveEntity(motionX, motionY, motionZ);
//
//            if (isCollidedHorizontally && isOnLadder())
//            {
//                motionY = 0.20000000000000001D;
//            }
//
//            motionY -= 0.080000000000000002D;
//            motionY *= 0.98000001907348633D;
//            motionX *= f;
//            motionZ *= f;
//        }
//
//        field_705_Q = field_704_R;
//        double d2 = posX - prevPosX;
//        double d3 = posZ - prevPosZ;
//        float f4 = MathHelper.sqrt_double(d2 * d2 + d3 * d3) * 4F;
//
//        if (f4 > 1.0F)
//        {
//            f4 = 1.0F;
//        }
//
//        field_704_R += (f4 - field_704_R) * 0.4F;
//        field_703_S += field_704_R;
//    }
//
//
//
//    // SNIP
//    public boolean isOnLadder()
//    {
//        int i = MathHelper.floor_double(posX);
//        int j = MathHelper.floor_double(boundingBox.minY);
//        int k = MathHelper.floor_double(posZ);
//		if(worldObj.getBlock(x, y, z) == 0)
//		{
//		  if(worldObj.getBlock(i-1, y + 1, k) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i+1, y + 1, k) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i, y + 1, k-1) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i, y + 1, k+1) == mod_SpiderQueen.smallWeb) { return true; }
//
//		  if(worldObj.getBlock(i-1, y + 0, k) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i+1, y + 0, k) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i, y + 0, k-1) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i, y + 0, k+1) == mod_SpiderQueen.smallWeb) { return true; }
//
//		  if(worldObj.getBlock(i-1, y + 1, k) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i+1, y + 1, k) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i, y + 1, k-1) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i, y + 1, k+1) == Blocks.web) { return true; }
//
//		  if(worldObj.getBlock(i-1, y + 0, k) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i+1, y + 0, k) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i, y + 0, k-1) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i, y + 0, k+1) == Blocks.web) { return true; }
//
//		}
//
//        return (worldObj.getBlock(x, y, z) == Blocks.ladder || worldObj.getBlock(x, y, z) == Blocks.vine || worldObj.getBlock(x, y, z) == mod_SpiderQueen.smallWeb  || worldObj.getBlock(i, y + 1, k) == mod_SpiderQueen.smallWeb || worldObj.getBlock(x, y, z) == Blocks.web  || worldObj.getBlock(i, y + 1, k) == Blocks.web);
//    }
//	// -SNIP
//
//
//	// SNIP
//	public boolean isOnWeb()
//    {
//		//if(isJumping == false) { return false; }
//        int i = MathHelper.floor_double(posX);
//        int j = MathHelper.floor_double(boundingBox.minY);
//        int k = MathHelper.floor_double(posZ);
//		if((worldObj.getBlock(i, y + 1, k) == mod_SpiderQueen.smallWeb) || (worldObj.getBlock(i, y + 1, k) == Blocks.web))
//		{
//
//			if(isSneaking() == true)
//			{
//				motionX = motionX * 0.6D;
//				motionZ = motionZ * 0.6D;
//				motionY = motionY * 0.6D - 0.2D;
//			}
//
//			return true;
//		}
//
//
//		if(worldObj.getBlock(x, y, z) == 0)
//		{
//		  if(worldObj.getBlock(i-1, y + 1, k) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i+1, y + 1, k) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i, y + 1, k-1) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i, y + 1, k+1) == mod_SpiderQueen.smallWeb) { return true; }
//
//		  if(worldObj.getBlock(i-1, y + 0, k) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i+1, y + 0, k) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i, y + 0, k-1) == mod_SpiderQueen.smallWeb) { return true; }
//		  if(worldObj.getBlock(i, y + 0, k+1) == mod_SpiderQueen.smallWeb) { return true; }
//
//
//		  if(worldObj.getBlock(i-1, y + 1, k) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i+1, y + 1, k) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i, y + 1, k-1) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i, y + 1, k+1) == Blocks.web) { return true; }
//
//		  if(worldObj.getBlock(i-1, y + 0, k) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i+1, y + 0, k) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i, y + 0, k-1) == Blocks.web) { return true; }
//		  if(worldObj.getBlock(i, y + 0, k+1) == Blocks.web) { return true; }
//
//		}
//
//        return false;
//
//    }
//	// -SNIP
//
//
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        par1NBTTagCompound.setShort("Health", (short)health);
//        par1NBTTagCompound.setShort("HurtTime", (short)hurtTime);
//        par1NBTTagCompound.setShort("DeathTime", (short)deathTime);
//        par1NBTTagCompound.setShort("AttackTime", (short)attackTime);
//
//        if (!activePotionsMap.isEmpty())
//        {
//            NBTTagList nbttaglist = new NBTTagList();
//            NBTTagCompound nbttagcompound;
//
//            for (Iterator iterator = activePotionsMap.values().iterator(); iterator.hasNext(); nbttaglist.appendTag(nbttagcompound))
//            {
//                PotionEffect potioneffect = (PotionEffect)iterator.next();
//                nbttagcompound = new NBTTagCompound();
//                nbttagcompound.setByte("Id", (byte)potioneffect.getPotionID());
//                nbttagcompound.setByte("Amplifier", (byte)potioneffect.getAmplifier());
//                nbttagcompound.setInteger("Duration", potioneffect.getDuration());
//            }
//
//            par1NBTTagCompound.setTag("ActiveEffects", nbttaglist);
//        }
//    }
//
//
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        if (health < -32768)
//        {
//            health = -32768;
//        }
//
//        health = par1NBTTagCompound.getShort("Health");
//
//        if (!par1NBTTagCompound.hasKey("Health"))
//        {
//            health = getMaxHealth();
//        }
//
//        hurtTime = par1NBTTagCompound.getShort("HurtTime");
//        deathTime = par1NBTTagCompound.getShort("DeathTime");
//        attackTime = par1NBTTagCompound.getShort("AttackTime");
//
//        if (par1NBTTagCompound.hasKey("ActiveEffects"))
//        {
//            NBTTagList nbttaglist = par1NBTTagCompound.getTagList("ActiveEffects");
//
//            for (int i = 0; i < nbttaglist.tagCount(); i++)
//            {
//                NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
//                byte byte0 = nbttagcompound.getByte("Id");
//                byte byte1 = nbttagcompound.getByte("Amplifier");
//                int j = nbttagcompound.getInteger("Duration");
//                activePotionsMap.put(Integer.valueOf(byte0), new PotionEffect(byte0, j, byte1));
//            }
//        }
//    }
//
//
//    public boolean isEntityAlive()
//    {
//        return !isDead && health > 0;
//    }
//
//    public boolean canBreatheUnderwater()
//    {
//        return false;
//    }
//
//    public void setMoveForward(float par1)
//    {
//        moveForward = par1;
//    }
//
//    public void setJumping(boolean par1)
//    {
//        isJumping = par1;
//    }
//
//
//    public void onLivingUpdate()
//    {
//        if (jumpTicks > 0)
//        {
//            jumpTicks--;
//        }
//
//        if (newPosRotationIncrements > 0)
//        {
//            double d = posX + (newPosX - posX) / (double)newPosRotationIncrements;
//            double d1 = posY + (newPosY - posY) / (double)newPosRotationIncrements;
//            double d2 = posZ + (newPosZ - posZ) / (double)newPosRotationIncrements;
//            double d3;
//
//            for (d3 = newRotationYaw - (double)rotationYaw; d3 < -180D; d3 += 360D) { }
//
//            for (; d3 >= 180D; d3 -= 360D) { }
//
//            rotationYaw += d3 / (double)newPosRotationIncrements;
//            rotationPitch += (newRotationPitch - (double)rotationPitch) / (double)newPosRotationIncrements;
//            newPosRotationIncrements--;
//            setPosition(d, d1, d2);
//            setRotation(rotationYaw, rotationPitch);
//            List list1 = worldObj.getCollidingBoundingBoxes(this, boundingBox.contract(0.03125D, 0.0D, 0.03125D));
//
//            if (list1.size() > 0)
//            {
//                double d4 = 0.0D;
//
//                for (int j = 0; j < list1.size(); j++)
//                {
//                    AxisAlignedBB axisalignedbb = (AxisAlignedBB)list1.get(j);
//
//                    if (axisalignedbb.maxY > d4)
//                    {
//                        d4 = axisalignedbb.maxY;
//                    }
//                }
//
//                d1 += d4 - boundingBox.minY;
//                setPosition(d, d1, d2);
//            }
//        }
//
//        Profiler.startSection("ai");
//
//        if (isMovementBlocked())
//        {
//            isJumping = false;
//            moveStrafing = 0.0F;
//            moveForward = 0.0F;
//            randomYawVelocity = 0.0F;
//        }
//        else if (isClientWorld())
//        {
//            if (isAIEnabled())
//            {
//                Profiler.startSection("newAi");
//                updateAITasks();
//                Profiler.endSection();
//            }
//            else
//            {
//                Profiler.startSection("oldAi");
//                updateEntityActionState();
//                Profiler.endSection();
//                rotationYawHead = rotationYaw;
//            }
//        }
//
//        Profiler.endSection();
//        boolean flag = isInWater();
//        boolean flag1 = handleLavaMovement();
//
//        if (isJumping)
//        {
//            if (flag)
//            {
//                motionY += 0.039999999105930328D;
//            }
//            else if (flag1)
//            {
//                motionY += 0.039999999105930328D;
//            }
//            else if (onGround && jumpTicks == 0)
//            {
//                jump();
//                jumpTicks = 10;
//            }
//        }
//        else
//        {
//            jumpTicks = 0;
//        }
//
//        moveStrafing *= 0.98F;
//        moveForward *= 0.98F;
//        randomYawVelocity *= 0.9F;
//        float f = landMovementFactor;
//        landMovementFactor *= getSpeedModifier();
//        moveEntityWithHeading(moveStrafing, moveForward);
//        landMovementFactor = f;
//        Profiler.startSection("push");
//        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
//
//        if (list != null && list.size() > 0)
//        {
//            for (int i = 0; i < list.size(); i++)
//            {
//                Entity entity = (Entity)list.get(i);
//
//                if (entity.canBePushed())
//                {
//                    entity.applyEntityCollision(this);
//                }
//            }
//        }
//
//
//
//		spiderBaitCode(); // SNIP
//
//        Profiler.endSection();
//    }
//
//
//    protected boolean isAIEnabled()
//    {
//        return false;
//    }
//
//
//    protected boolean isClientWorld()
//    {
//        return !worldObj.isRemote;
//    }
//
//
//    protected boolean isMovementBlocked()
//    {
//        return health <= 0;
//    }
//
//    public boolean isBlocking()
//    {
//        return false;
//    }
//
//
//    protected void jump()
//    {
//        motionY = 0.41999998688697815D;
//
//        if (isPotionActive(Potion.jump))
//        {
//            motionY += (float)(getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F;
//        }
//
//        if (isSprinting())
//        {
//            float f = rotationYaw * 0.01745329F;
//            motionX -= MathHelper.sin(f) * 0.2F;
//            motionZ += MathHelper.cos(f) * 0.2F;
//        }
//
//        isAirBorne = true;
//    }
//
//
//    protected boolean canDespawn()
//    {
//        return true;
//    }
//
//
//    protected void despawnEntity()
//    {
//        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, -1D);
//
//        if (entityplayer != null)
//        {
//            double d = ((Entity)(entityplayer)).posX - posX;
//            double d1 = ((Entity)(entityplayer)).posY - posY;
//            double d2 = ((Entity)(entityplayer)).posZ - posZ;
//            double d3 = d * d + d1 * d1 + d2 * d2;
//
//            if (canDespawn() && d3 > 16384D)
//            {
//                setDead();
//            }
//
//            if (entityAge > 600 && rand.nextInt(800) == 0 && d3 > 1024D && canDespawn())
//            {
//                setDead();
//            }
//            else if (d3 < 1024D)
//            {
//                entityAge = 0;
//            }
//        }
//    }
//
//    protected void updateAITasks()
//    {
//        entityAge++;
//        Profiler.startSection("checkDespawn");
//        despawnEntity();
//        Profiler.endSection();
//        Profiler.startSection("sensing");
//        field_48104_at.clearSensingCache();
//        Profiler.endSection();
//        Profiler.startSection("targetSelector");
//        targetTasks.onUpdateTasks();
//        Profiler.endSection();
//        Profiler.startSection("goalSelector");
//        tasks.onUpdateTasks();
//        Profiler.endSection();
//        Profiler.startSection("navigation");
//        navigator.onUpdateNavigation();
//        Profiler.endSection();
//        Profiler.startSection("mob tick");
//        updateAITick();
//        Profiler.endSection();
//        Profiler.startSection("controls");
//        moveHelper.onUpdateMoveHelper();
//        lookHelper.onUpdateLook();
//        jumpHelper.doJump();
//
//		spiderBaitCode(); // SNIP
//
//        Profiler.endSection();
//    }
//
//
//    protected void updateAITick()
//    {
//    }
//
//    protected void updateEntityActionState()
//    {
//        entityAge++;
//        despawnEntity();
//        moveStrafing = 0.0F;
//        moveForward = 0.0F;
//        float f = 8F;
//
//        if (rand.nextFloat() < 0.02F)
//        {
//            EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, f);
//
//            if (entityplayer != null)
//            {
//                currentTarget = entityplayer;
//                numTicksToChaseTarget = 10 + rand.nextInt(20);
//            }
//            else
//            {
//                randomYawVelocity = (rand.nextFloat() - 0.5F) * 20F;
//            }
//        }
//
//        if (currentTarget != null)
//        {
//            faceEntity(currentTarget, 10F, getVerticalFaceSpeed());
//
//            if (numTicksToChaseTarget-- <= 0 || currentTarget.isDead || currentTarget.getDistanceSqToEntity(this) > (double)(f * f))
//            {
//                currentTarget = null;
//            }
//        }
//        else
//        {
//            if (rand.nextFloat() < 0.05F)
//            {
//                randomYawVelocity = (rand.nextFloat() - 0.5F) * 20F;
//            }
//
//            rotationYaw += randomYawVelocity;
//            rotationPitch = defaultPitch;
//        }
//
//        boolean flag = isInWater();
//        boolean flag1 = handleLavaMovement();
//
//        if (flag || flag1)
//        {
//            isJumping = rand.nextFloat() < 0.8F;
//        }
//
//		spiderBaitCode(); // SNIP
//    }
//
//
//    public int getVerticalFaceSpeed()
//    {
//        return 40;
//    }
//
//
//    public void faceEntity(Entity par1Entity, float par2, float par3)
//    {
//        double d = par1Entity.posX - posX;
//        double d2 = par1Entity.posZ - posZ;
//        double d1;
//
//        if (par1Entity instanceof EntityLivingBase)
//        {
//            EntityLivingBase entityliving = (EntityLivingBase)par1Entity;
//            d1 = (posY + (double)getEyeHeight()) - (entityliving.posY + (double)entityliving.getEyeHeight());
//        }
//        else
//        {
//            d1 = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY) / 2D - (posY + (double)getEyeHeight());
//        }
//
//        double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
//        float f = (float)((Math.atan2(d2, d) * 180D) / Math.PI) - 90F;
//        float f1 = (float)(-((Math.atan2(d1, d3) * 180D) / Math.PI));
//        rotationPitch = -updateRotation(rotationPitch, f1, par3);
//        rotationYaw = updateRotation(rotationYaw, f, par2);
//    }
//
//
//    private float updateRotation(float par1, float par2, float par3)
//    {
//        float f;
//
//        for (f = par2 - par1; f < -180F; f += 360F) { }
//
//        for (; f >= 180F; f -= 360F) { }
//
//        if (f > par3)
//        {
//            f = par3;
//        }
//
//        if (f < -par3)
//        {
//            f = -par3;
//        }
//
//        return par1 + f;
//    }
//
//
//    public void onEntityDeath()
//    {
//    }
//
//
//    public boolean getCanSpawnHere()
//    {
//        return worldObj.checkBlockCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox);
//    }
//
//
//    protected void kill()
//    {
//        attackEntityFrom(DamageSource.outOfWorld, 4);
//    }
//
//
//    public float getSwingProgress(float par1)
//    {
//        float f = swingProgress - prevSwingProgress;
//
//        if (f < 0.0F)
//        {
//            f++;
//        }
//
//        return prevSwingProgress + f * par1;
//    }
//
//
//    public Vec3D getPosition(float par1)
//    {
//        if (par1 == 1.0F)
//        {
//            return Vec3.createVectorHelper(posX, posY, posZ);
//        }
//        else
//        {
//            double d = prevPosX + (posX - prevPosX) * (double)par1;
//            double d1 = prevPosY + (posY - prevPosY) * (double)par1;
//            double d2 = prevPosZ + (posZ - prevPosZ) * (double)par1;
//            return Vec3.createVectorHelper(d, d1, d2);
//        }
//    }
//
//
//    public Vec3D getLookVec()
//    {
//        return getLook(1.0F);
//    }
//
//
//    public Vec3D getLook(float par1)
//    {
//        if (par1 == 1.0F)
//        {
//            float f = MathHelper.cos(-rotationYaw * 0.01745329F - (float)Math.PI);
//            float f2 = MathHelper.sin(-rotationYaw * 0.01745329F - (float)Math.PI);
//            float f4 = -MathHelper.cos(-rotationPitch * 0.01745329F);
//            float f6 = MathHelper.sin(-rotationPitch * 0.01745329F);
//            return Vec3.createVectorHelper(f2 * f4, f6, f * f4);
//        }
//        else
//        {
//            float f1 = prevRotationPitch + (rotationPitch - prevRotationPitch) * par1;
//            float f3 = prevRotationYaw + (rotationYaw - prevRotationYaw) * par1;
//            float f5 = MathHelper.cos(-f3 * 0.01745329F - (float)Math.PI);
//            float f7 = MathHelper.sin(-f3 * 0.01745329F - (float)Math.PI);
//            float f8 = -MathHelper.cos(-f1 * 0.01745329F);
//            float f9 = MathHelper.sin(-f1 * 0.01745329F);
//            return Vec3.createVectorHelper(f7 * f8, f9, f5 * f8);
//        }
//    }
//
//
//    public float getRenderSizeModifier()
//    {
//        return 1.0F;
//    }
//
//
//    public MovingObjectPosition rayTrace(double par1, float par3)
//    {
//        Vec3D vec3d = getPosition(par3);
//        Vec3D vec3d1 = getLook(par3);
//        Vec3D vec3d2 = vec3d.addVector(vec3d1.xCoord * par1, vec3d1.yCoord * par1, vec3d1.zCoord * par1);
//        return worldObj.rayTraceBlocks(vec3d, vec3d2);
//    }
//
//
//    public int getMaxSpawnedInChunk()
//    {
//        return 4;
//    }
//
//
//    public ItemStack getHeldItem()
//    {
//        return null;
//    }
//
//    public void handleHealthUpdate(byte par1)
//    {
//        if (par1 == 2)
//        {
//            field_704_R = 1.5F;
//            heartsLife = heartsHalvesLife;
//            hurtTime = maxHurtTime = 10;
//            attackedAtYaw = 0.0F;
//            worldObj.playSoundAtEntity(this, getHurtSound(), getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
//            attackEntityFrom(DamageSource.generic, 0);
//        }
//        else if (par1 == 3)
//        {
//            worldObj.playSoundAtEntity(this, getDeathSound(), getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
//            health = 0;
//            onDeath(DamageSource.generic);
//        }
//        else
//        {
//            super.handleHealthUpdate(par1);
//        }
//    }
//
//
//    public boolean isPlayerSleeping()
//    {
//        return false;
//    }
//
//
//    public int getItemIcon(ItemStack par1ItemStack, int par2)
//    {
//        return par1ItemStack.getIconIndex();
//    }
//
//    protected void updatePotionEffects()
//    {
//        Iterator iterator = activePotionsMap.keySet().iterator();
//
//        do
//        {
//            if (!iterator.hasNext())
//            {
//                break;
//            }
//
//            Integer integer = (Integer)iterator.next();
//            PotionEffect potioneffect = (PotionEffect)activePotionsMap.get(integer);
//
//            if (!potioneffect.onUpdate(this) && !worldObj.isRemote)
//            {
//                iterator.remove();
//                onFinishedPotionEffect(potioneffect);
//            }
//        }
//        while (true);
//
//        if (potionsNeedUpdate)
//        {
//            if (!worldObj.isRemote)
//            {
//                if (!activePotionsMap.isEmpty())
//                {
//                    int i = PotionHelper.func_40354_a(activePotionsMap.values());
//                    dataWatcher.updateObject(8, Integer.valueOf(i));
//                }
//                else
//                {
//                    dataWatcher.updateObject(8, Integer.valueOf(0));
//                }
//            }
//
//            potionsNeedUpdate = false;
//        }
//
//        if (rand.nextBoolean())
//        {
//            int j = dataWatcher.getWatchableObjectInt(8);
//
//            if (j > 0)
//            {
//                double d = (double)(j >> 16 & 0xff) / 255D;
//                double d1 = (double)(j >> 8 & 0xff) / 255D;
//                double d2 = (double)(j >> 0 & 0xff) / 255D;
//                worldObj.spawnParticle("mobSpell", posX + (rand.nextDouble() - 0.5D) * (double)width, (posY + rand.nextDouble() * (double)height) - (double)yOffset, posZ + (rand.nextDouble() - 0.5D) * (double)width, d, d1, d2);
//            }
//        }
//    }
//
//    public void clearActivePotions()
//    {
//        Iterator iterator = activePotionsMap.keySet().iterator();
//
//        do
//        {
//            if (!iterator.hasNext())
//            {
//                break;
//            }
//
//            Integer integer = (Integer)iterator.next();
//            PotionEffect potioneffect = (PotionEffect)activePotionsMap.get(integer);
//
//            if (!worldObj.isRemote)
//            {
//                iterator.remove();
//                onFinishedPotionEffect(potioneffect);
//            }
//        }
//        while (true);
//    }
//
//    public Collection getActivePotionEffects()
//    {
//        return activePotionsMap.values();
//    }
//
//    public boolean isPotionActive(Potion par1Potion)
//    {
//        return activePotionsMap.containsKey(Integer.valueOf(par1Potion.id));
//    }
//
//
//    public PotionEffect getActivePotionEffect(Potion par1Potion)
//    {
//        return (PotionEffect)activePotionsMap.get(Integer.valueOf(par1Potion.id));
//    }
//
//
//    public void addPotionEffect(PotionEffect par1PotionEffect)
//    {
//        if (!isPotionApplicable(par1PotionEffect))
//        {
//            return;
//        }
//
//        if (activePotionsMap.containsKey(Integer.valueOf(par1PotionEffect.getPotionID())))
//        {
//            ((PotionEffect)activePotionsMap.get(Integer.valueOf(par1PotionEffect.getPotionID()))).combine(par1PotionEffect);
//            onChangedPotionEffect((PotionEffect)activePotionsMap.get(Integer.valueOf(par1PotionEffect.getPotionID())));
//        }
//        else
//        {
//            activePotionsMap.put(Integer.valueOf(par1PotionEffect.getPotionID()), par1PotionEffect);
//            onNewPotionEffect(par1PotionEffect);
//        }
//    }
//
//    public boolean isPotionApplicable(PotionEffect par1PotionEffect)
//    {
//        if (getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
//        {
//            int i = par1PotionEffect.getPotionID();
//
//            if (i == Potion.regeneration.id || i == Potion.poison.id)
//            {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//
//    public boolean isEntityUndead()
//    {
//        return getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
//    }
//
//
//    public void removePotionEffect(int par1)
//    {
//        activePotionsMap.remove(Integer.valueOf(par1));
//    }
//
//    protected void onNewPotionEffect(PotionEffect par1PotionEffect)
//    {
//        potionsNeedUpdate = true;
//    }
//
//    protected void onChangedPotionEffect(PotionEffect par1PotionEffect)
//    {
//        potionsNeedUpdate = true;
//    }
//
//    protected void onFinishedPotionEffect(PotionEffect par1PotionEffect)
//    {
//        potionsNeedUpdate = true;
//    }
//
//
//    protected float getSpeedModifier()
//    {
//        float f = 1.0F;
//
//        if (isPotionActive(Potion.moveSpeed))
//        {
//            f *= 1.0F + 0.2F * (float)(getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
//        }
//
//        if (isPotionActive(Potion.moveSlowdown))
//        {
//            f *= 1.0F - 0.15F * (float)(getActivePotionEffect(Potion.moveSlowdown).getAmplifier() + 1);
//        }
//
//        return f;
//    }
//
//
//    public void setPositionAndUpdate(double par1, double par3, double par5)
//    {
//        setLocationAndAngles(par1, par3, par5, rotationYaw, rotationPitch);
//    }
//
//
//    public boolean isChild()
//    {
//        return false;
//    }
//
//
//    public EnumCreatureAttribute getCreatureAttribute()
//    {
//        return EnumCreatureAttribute.UNDEFINED;
//    }
//
//
//    public void renderBrokenItemStack(ItemStack par1ItemStack)
//    {
//        worldObj.playSoundAtEntity(this, "random.break", 0.8F, 0.8F + worldObj.rand.nextFloat() * 0.4F);
//
//        for (int i = 0; i < 5; i++)
//        {
//            Vec3 vec3d = Vec3.createVectorHelper(((double)rand.nextFloat() - 0.5D) * 0.10000000000000001D, Math.random() * 0.10000000000000001D + 0.10000000000000001D, 0.0D);
//            vec3d.rotateAroundX((-rotationPitch * (float)Math.PI) / 180F);
//            vec3d.rotateAroundY((-rotationYaw * (float)Math.PI) / 180F);
//            Vec3D vec3d1 = Vec3.createVectorHelper(((double)rand.nextFloat() - 0.5D) * 0.29999999999999999D, (double)(-rand.nextFloat()) * 0.59999999999999998D - 0.29999999999999999D, 0.59999999999999998D);
//            vec3d1.rotateAroundX((-rotationPitch * (float)Math.PI) / 180F);
//            vec3d1.rotateAroundY((-rotationYaw * (float)Math.PI) / 180F);
//            vec3d1 = vec3d1.addVector(posX, posY + (double)getEyeHeight(), posZ);
//            worldObj.spawnParticle((new StringBuilder()).append("iconcrack_").append(par1ItemStack.getItem()).toString(), vec3d1.xCoord, vec3d1.yCoord, vec3d1.zCoord, vec3d.xCoord, vec3d.yCoord + 0.050000000000000003D, vec3d.zCoord);
//        }
//    }
//}
