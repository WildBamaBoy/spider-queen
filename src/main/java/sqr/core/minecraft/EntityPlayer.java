package sqr.core.minecraft;
//package net.minecraft.src;
//
//import java.util.Iterator;
//import java.util.List;
//
//import sqr.core.minecraft.Entity;
//import sqr.core.minecraft.EntityLivingBase;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockBed;
//import net.minecraft.block.material.Material;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.item.EntityBoat;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.item.EntityMinecart;
//import net.minecraft.entity.monster.EntityCreeper;
//import net.minecraft.entity.monster.EntityMob;
//import net.minecraft.entity.passive.EntityPig;
//import net.minecraft.entity.passive.EntityWolf;
//import net.minecraft.entity.player.EntityPlayer.EnumStatus;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.entity.player.PlayerCapabilities;
//import net.minecraft.entity.projectile.EntityArrow;
//import net.minecraft.entity.projectile.EntityFishHook;
//import net.minecraft.inventory.ContainerPlayer;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.item.EnumAction;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.potion.Potion;
//import net.minecraft.stats.AchievementList;
//import net.minecraft.stats.StatBase;
//import net.minecraft.stats.StatList;
//import net.minecraft.tileentity.TileEntityBrewingStand;
//import net.minecraft.tileentity.TileEntityDispenser;
//import net.minecraft.tileentity.TileEntityFurnace;
//import net.minecraft.tileentity.TileEntitySign;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.ChunkCoordinates;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.FoodStats;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//import net.minecraft.world.chunk.IChunkProvider;
//
//public abstract class EntityPlayer extends EntityLivingBase
//{
//
//    public EntitySWeb webEntity = null; // SNIP
//
//
//    public InventoryPlayer inventory;
//
//
//    public Container inventorySlots;
//
//
//    public Container craftingInventory;
//
//
//    protected FoodStats foodStats;
//
//
//    protected int flyToggleTimer;
//    public byte field_9371_f;
//    public int score;
//    public float prevCameraYaw;
//    public float cameraYaw;
//
//
//    public boolean isSwinging;
//    public int swingProgressInt;
//    public String username;
//
//
//    public int dimension;
//    public String playerCloakUrl;
//
//
//    public int xpCooldown;
//    public double field_20066_r;
//    public double field_20065_s;
//    public double field_20064_t;
//    public double field_20063_u;
//    public double field_20062_v;
//    public double field_20061_w;
//
//
//    protected boolean sleeping;
//
//
//    public ChunkCoordinates playerLocation;
//    private int sleepTimer;
//    public float field_22063_x;
//    public float field_22062_y;
//    public float field_22061_z;
//
//
//    private ChunkCoordinates spawnChunk;
//
//
//    private ChunkCoordinates startMinecartRidingCoordinate;
//    public int timeUntilPortal;
//
//
//    protected boolean inPortal;
//
//
//    public float timeInPortal;
//
//
//    public float prevTimeInPortal;
//
//
//    public PlayerCapabilities capabilities;
//
//
//    public int experienceLevel;
//
//
//    public int experienceTotal;
//
//
//    public float experience;
//
//
//    private ItemStack itemInUse;
//
//
//    private int itemInUseCount;
//    protected float speedOnGround;
//    protected float speedInAir;
//
//
//    public EntityFishHook fishEntity;
//
//    public EntityPlayer(World par1World)
//    {
//        super(par1World);
//
//		webEntity = null; // SNIP
//
//        inventory = new InventoryPlayer(this);
//        foodStats = new FoodStats();
//        flyToggleTimer = 0;
//        field_9371_f = 0;
//        score = 0;
//        isSwinging = false;
//        swingProgressInt = 0;
//        xpCooldown = 0;
//        timeUntilPortal = 20;
//        inPortal = false;
//        capabilities = new PlayerCapabilities();
//        speedOnGround = 0.1F;
//        speedInAir = 0.02F;
//        fishEntity = null;
//        inventorySlots = new ContainerPlayer(inventory, !par1World.isRemote);
//        craftingInventory = inventorySlots;
//        yOffset = 1.62F;
//        ChunkCoordinates chunkcoordinates = par1World.getSpawnPoint();
//        setLocationAndAngles((double)chunkcoordinates.posX + 0.5D, chunkcoordinates.posY + 1, (double)chunkcoordinates.posZ + 0.5D, 0.0F, 0.0F);
//        entityType = "humanoid";
//        field_9353_B = 180F;
//        fireResistance = 20;
//        texture = "/mob/char.png";
//    }
//
//	// SNIP
//    public EnumStatus sleepInWebBedAt(int x, int y, int z)
//    {
//        if(isPlayerSleeping() || !isEntityAlive())
//        {
//            //return EnumStatus.OTHER_PROBLEM;
//        }
//        if(Math.abs(posX - (double)i) > 3D || Math.abs(posY - (double)j) > 2D || Math.abs(posZ - (double)k) > 3D)
//        {
//            //return EnumStatus.TOO_FAR_AWAY;
//        }
//        //setSize(0.2F, 0.2F);
//        //yOffset = 1.62F;
//        if(worldObj.blockExists(x, y, z))
//        {
//            int l = worldObj.getBlockMetadata(x, y, z);
//            int i1 = 1;
//            func_22052_e(i1);
//            setPosition((float)x +0f, (float)y + 1.9375F, (float)z + 0f);
//        } else
//        {
//            setPosition((float)x +0.5F, (float)y + 1.9375F, (float)z + 0.5F);
//        }
//
//
//        sleeping = true;
//        sleepTimer = 0;
//        playerLocation = new ChunkCoordinates(x, y, z);
//        motionX = motionZ = motionY = 0.0D;
//        if(!worldObj.isRemote)
//        {
//            worldObj.updateAllPlayersSleepingFlag();
//        }
//        return EnumStatus.OK;
//    }
//	// -SNIP
//
//    public int getMaxHealth()
//    {
//        return 20;
//    }
//
//    protected void entityInit()
//    {
//        super.entityInit();
//        dataWatcher.addObject(16, Byte.valueOf((byte)0));
//        dataWatcher.addObject(17, Byte.valueOf((byte)0));
//    }
//
//
//    public ItemStack getItemInUse()
//    {
//        return itemInUse;
//    }
//
//
//    public int getItemInUseCount()
//    {
//        return itemInUseCount;
//    }
//
//
//    public boolean isUsingItem()
//    {
//        return itemInUse != null;
//    }
//
//
//    public int getItemInUseDuration()
//    {
//        if (isUsingItem())
//        {
//            return itemInUse.getMaxItemUseDuration() - itemInUseCount;
//        }
//        else
//        {
//            return 0;
//        }
//    }
//
//    public void stopUsingItem()
//    {
//        if (itemInUse != null)
//        {
//            itemInUse.onPlayerStoppedUsing(worldObj, this, itemInUseCount);
//        }
//
//        clearItemInUse();
//    }
//
//    public void clearItemInUse()
//    {
//        itemInUse = null;
//        itemInUseCount = 0;
//
//        if (!worldObj.isRemote)
//        {
//            setEating(false);
//        }
//    }
//
//    public boolean isBlocking()
//    {
//        return isUsingItem() && Items.itemsList[itemInUse.itemID].getItemUseAction(itemInUse) == EnumAction.block;
//    }
//
//
//    public void onUpdate()
//    {
//        if (itemInUse != null)
//        {
//            ItemStack itemstack = inventory.getCurrentItem();
//
//            if (itemstack != itemInUse)
//            {
//                clearItemInUse();
//            }
//            else
//            {
//                if (itemInUseCount <= 25 && itemInUseCount % 4 == 0)
//                {
//                    updateItemUse(itemstack, 5);
//                }
//
//                if (--itemInUseCount == 0 && !worldObj.isRemote)
//                {
//                    onItemUseFinish();
//                }
//            }
//        }
//
//        if (xpCooldown > 0)
//        {
//            xpCooldown--;
//        }
//
//        if (isPlayerSleeping())
//        {
//            sleepTimer++;
//
//            if (sleepTimer > 100)
//            {
//                sleepTimer = 100;
//            }
//
//            if (!worldObj.isRemote)
//            {
//                if (!isInBed())
//                {
//                    wakeUpPlayer(true, true, false);
//                }
//                else if (worldObj.isDaytime())
//                {
//                    //wakeUpPlayer(false, true, true);		// SNIP
//                }
//            }
//        }
//        else if (sleepTimer > 0)
//        {
//            sleepTimer++;
//
//            if (sleepTimer >= 110)
//            {
//                sleepTimer = 0;
//            }
//        }
//
//        super.onUpdate();
//
//        if (!worldObj.isRemote && craftingInventory != null && !craftingInventory.canInteractWith(this))
//        {
//            closeScreen();
//            craftingInventory = inventorySlots;
//        }
//
//        if (capabilities.isFlying)
//        {
//            for (int i = 0; i < 8; i++) { }
//        }
//
//        if (isBurning() && capabilities.disableDamage)
//        {
//            extinguish();
//        }
//
//        field_20066_r = field_20063_u;
//        field_20065_s = field_20062_v;
//        field_20064_t = field_20061_w;
//        double d = posX - field_20063_u;
//        double d1 = posY - field_20062_v;
//        double d2 = posZ - field_20061_w;
//        double d3 = 10D;
//
//        if (d > d3)
//        {
//            field_20066_r = field_20063_u = posX;
//        }
//
//        if (d2 > d3)
//        {
//            field_20064_t = field_20061_w = posZ;
//        }
//
//        if (d1 > d3)
//        {
//            field_20065_s = field_20062_v = posY;
//        }
//
//        if (d < -d3)
//        {
//            field_20066_r = field_20063_u = posX;
//        }
//
//        if (d2 < -d3)
//        {
//            field_20064_t = field_20061_w = posZ;
//        }
//
//        if (d1 < -d3)
//        {
//            field_20065_s = field_20062_v = posY;
//        }
//
//        field_20063_u += d * 0.25D;
//        field_20061_w += d2 * 0.25D;
//        field_20062_v += d1 * 0.25D;
//        addStat(StatList.minutesPlayedStat, 1);
//
//        if (ridingEntity == null)
//        {
//            startMinecartRidingCoordinate = null;
//        }
//
//        if (!worldObj.isRemote)
//        {
//            foodStats.onUpdate(this);
//        }
//    }
//
//
//    protected void updateItemUse(ItemStack par1ItemStack, int par2)
//    {
//        if (par1ItemStack.getItemUseAction() == EnumAction.drink)
//        {
//            worldObj.playSoundAtEntity(this, "random.drink", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
//        }
//
//        if (par1ItemStack.getItemUseAction() == EnumAction.eat)
//        {
//            for (int i = 0; i < par2; i++)
//            {
//                Vec3 vec3d = Vec3.createVectorHelper(((double)rand.nextFloat() - 0.5D) * 0.10000000000000001D, Math.random() * 0.10000000000000001D + 0.10000000000000001D, 0.0D);
//                vec3d.rotateAroundX((-rotationPitch * (float)Math.PI) / 180F);
//                vec3d.rotateAroundY((-rotationYaw * (float)Math.PI) / 180F);
//                Vec3D vec3d1 = Vec3.createVectorHelper(((double)rand.nextFloat() - 0.5D) * 0.29999999999999999D, (double)(-rand.nextFloat()) * 0.59999999999999998D - 0.29999999999999999D, 0.59999999999999998D);
//                vec3d1.rotateAroundX((-rotationPitch * (float)Math.PI) / 180F);
//                vec3d1.rotateAroundY((-rotationYaw * (float)Math.PI) / 180F);
//                vec3d1 = vec3d1.addVector(posX, posY + (double)getEyeHeight(), posZ);
//                worldObj.spawnParticle((new StringBuilder()).append("iconcrack_").append(par1ItemStack.getItem()).toString(), vec3d1.xCoord, vec3d1.yCoord, vec3d1.zCoord, vec3d.xCoord, vec3d.yCoord + 0.050000000000000003D, vec3d.zCoord);
//            }
//
//            worldObj.playSoundAtEntity(this, "random.eat", 0.5F + 0.5F * (float)rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
//        }
//    }
//
//
//    protected void onItemUseFinish()
//    {
//        if (itemInUse != null)
//        {
//            updateItemUse(itemInUse, 16);
//            int i = itemInUse.stackSize;
//            ItemStack itemstack = itemInUse.onFoodEaten(worldObj, this);
//
//            if (itemstack != itemInUse || itemstack != null && itemstack.stackSize != i)
//            {
//                inventory.mainInventory[inventory.currentItem] = itemstack;
//
//                if (itemstack.stackSize == 0)
//                {
//                    inventory.mainInventory[inventory.currentItem] = null;
//                }
//            }
//
//            clearItemInUse();
//        }
//    }
//
//    public void handleHealthUpdate(byte par1)
//    {
//        if (par1 == 9)
//        {
//            onItemUseFinish();
//        }
//        else
//        {
//            super.handleHealthUpdate(par1);
//        }
//    }
//
//
//    protected boolean isMovementBlocked()
//    {
//        return getHealth() <= 0 || isPlayerSleeping();
//    }
//
//
//    protected void closeScreen()
//    {
//        craftingInventory = inventorySlots;
//    }
//
//    public void updateCloak()
//    {
//        playerCloakUrl = (new StringBuilder()).append("http://s3.amazonaws.com/MinecraftCloaks/").append(username).append(".png").toString();
//        cloakUrl = playerCloakUrl;
//    }
//
//
//    public void updateRidden()
//    {
//        double d = posX;
//        double d1 = posY;
//        double d2 = posZ;
//        super.updateRidden();
//        prevCameraYaw = cameraYaw;
//        cameraYaw = 0.0F;
//        addMountedMovementStat(posX - d, posY - d1, posZ - d2);
//    }
//
//
//    public void preparePlayerToSpawn()
//    {
//        yOffset = 1.62F;
//        setSize(0.6F, 1.8F);
//        super.preparePlayerToSpawn();
//        setEntityHealth(getMaxHealth());
//        deathTime = 0;
//    }
//
//
//    private int getSwingSpeedModifier()
//    {
//        if (isPotionActive(Potion.digSpeed))
//        {
//            return 6 - (1 + getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1;
//        }
//
//        if (isPotionActive(Potion.digSlowdown))
//        {
//            return 6 + (1 + getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2;
//        }
//        else
//        {
//            return 6;
//        }
//    }
//
//    protected void updateEntityActionState()
//    {
//        int i = getSwingSpeedModifier();
//
//        if (isSwinging)
//        {
//            swingProgressInt++;
//
//            if (swingProgressInt >= i)
//            {
//                swingProgressInt = 0;
//                isSwinging = false;
//            }
//        }
//        else
//        {
//            swingProgressInt = 0;
//        }
//
//        swingProgress = (float)swingProgressInt / (float)i;
//    }
//
//
//    public void onLivingUpdate()
//    {
//        if (flyToggleTimer > 0)
//        {
//            flyToggleTimer--;
//        }
//
//        if (worldObj.difficultySetting == 0 && getHealth() < getMaxHealth() && (ticksExisted % 20) * 12 == 0)
//        {
//            heal(1);
//        }
//
//        inventory.decrementAnimations();
//        prevCameraYaw = cameraYaw;
//        super.onLivingUpdate();
//        landMovementFactor = speedOnGround;
//        jumpMovementFactor = speedInAir;
//
//        if (isSprinting())
//        {
//            landMovementFactor += (double)speedOnGround * 0.29999999999999999D;
//            jumpMovementFactor += (double)speedInAir * 0.29999999999999999D;
//        }
//
//        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
//        float f1 = (float)Math.atan(-motionY * 0.20000000298023224D) * 15F;
//
//        if (f > 0.1F)
//        {
//            f = 0.1F;
//        }
//
//        if (!onGround || getHealth() <= 0)
//        {
//            f = 0.0F;
//        }
//
//        if (onGround || getHealth() <= 0)
//        {
//            f1 = 0.0F;
//        }
//
//        cameraYaw += (f - cameraYaw) * 0.4F;
//        cameraPitch += (f1 - cameraPitch) * 0.8F;
//
//        if (getHealth() > 0)
//        {
//            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
//
//            if (list != null)
//            {
//                for (int i = 0; i < list.size(); i++)
//                {
//                    Entity entity = (Entity)list.get(i);
//
//                    if (!entity.isDead)
//                    {
//                        collideWithPlayer(entity);
//                    }
//                }
//            }
//        }
//    }
//
//    private void collideWithPlayer(Entity par1Entity)
//    {
//        par1Entity.onCollideWithPlayer(this);
//    }
//
//    public int getScore()
//    {
//        return score;
//    }
//
//
//    public void onDeath(DamageSource par1DamageSource)
//    {
//        super.onDeath(par1DamageSource);
//        setSize(0.2F, 0.2F);
//        setPosition(posX, posY, posZ);
//        motionY = 0.10000000149011612D;
//
//        if (username.equals("Notch"))
//        {
//            dropPlayerItemWithRandomChoice(new ItemStack(Items.appleRed, 1), true);
//        }
//
//        inventory.dropAllItems();
//
//        if (par1DamageSource != null)
//        {
//            motionX = -MathHelper.cos(((attackedAtYaw + rotationYaw) * (float)Math.PI) / 180F) * 0.1F;
//            motionZ = -MathHelper.sin(((attackedAtYaw + rotationYaw) * (float)Math.PI) / 180F) * 0.1F;
//        }
//        else
//        {
//            motionX = motionZ = 0.0D;
//        }
//
//        yOffset = 0.1F;
//        addStat(StatList.deathsStat, 1);
//    }
//
//
//    public void addToPlayerScore(Entity par1Entity, int par2)
//    {
//        score += par2;
//
//        if (par1Entity instanceof EntityPlayer)
//        {
//            addStat(StatList.playerKillsStat, 1);
//        }
//        else
//        {
//            addStat(StatList.mobKillsStat, 1);
//        }
//    }
//
//
//    protected int decreaseAirSupply(int par1)
//    {
//        int i = EnchantmentHelper.getRespiration(inventory);
//
//        if (i > 0 && rand.nextInt(x +1) > 0)
//        {
//            return par1;
//        }
//        else
//        {
//            return super.decreaseAirSupply(par1);
//        }
//    }
//
//
//    public EntityItem dropOneItem()
//    {
//        return dropPlayerItemWithRandomChoice(inventory.decrStackSize(inventory.currentItem, 1), false);
//    }
//
//
//    public EntityItem dropPlayerItem(ItemStack par1ItemStack)
//    {
//        return dropPlayerItemWithRandomChoice(par1ItemStack, false);
//    }
//
//
//    public EntityItem dropPlayerItemWithRandomChoice(ItemStack par1ItemStack, boolean par2)
//    {
//        if (par1ItemStack == null)
//        {
//            return null;
//        }
//
//        EntityItem entityitem = new EntityItem(worldObj, posX, (posY - 0.30000001192092896D) + (double)getEyeHeight(), posZ, par1ItemStack);
//        entityitem.delayBeforeCanPickup = 40;
//        float f = 0.1F;
//
//        if (par2)
//        {
//            float f2 = rand.nextFloat() * 0.5F;
//            float f4 = rand.nextFloat() * (float)Math.PI * 2.0F;
//            entityitem.motionX = -MathHelper.sin(f4) * f2;
//            entityitem.motionZ = MathHelper.cos(f4) * f2;
//            entityitem.motionY = 0.20000000298023224D;
//        }
//        else
//        {
//            float f1 = 0.3F;
//            entityitem.motionX = -MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI) * f1;
//            entityitem.motionZ = MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI) * f1;
//            entityitem.motionY = -MathHelper.sin((rotationPitch / 180F) * (float)Math.PI) * f1 + 0.1F;
//            f1 = 0.02F;
//            float f3 = rand.nextFloat() * (float)Math.PI * 2.0F;
//            f1 *= rand.nextFloat();
//            entityitem.motionX += Math.cos(f3) * (double)f1;
//            entityitem.motionY += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
//            entityitem.motionZ += Math.sin(f3) * (double)f1;
//        }
//
//        joinEntityItemWithWorld(entityitem);
//        addStat(StatList.dropStat, 1);
//        return entityitem;
//    }
//
//
//    protected void joinEntityItemWithWorld(EntityItem par1EntityItem)
//    {
//        worldObj.spawnEntityInWorld(par1EntityItem);
//    }
//
//
//    public float getCurrentPlayerStrVsBlock(Block par1Block)
//    {
//        float f = inventory.getStrVsBlock(par1Block);
//        float f1 = f;
//        int i = EnchantmentHelper.getEfficiencyModifier(inventory);
//
//        if (i > 0 && inventory.canHarvestBlock(par1Block))
//        {
//            f1 += i * x +1;
//        }
//
//        if (isPotionActive(Potion.digSpeed))
//        {
//            f1 *= 1.0F + (float)(getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
//        }
//
//        if (isPotionActive(Potion.digSlowdown))
//        {
//            f1 *= 1.0F - (float)(getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
//        }
//
//        if (isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(inventory))
//        {
//            f1 /= 5F;
//        }
//
//        if (!onGround)
//        {
//            f1 /= 5F;
//        }
//
//        return f1;
//    }
//
//
//    public boolean canHarvestBlock(Block par1Block)
//    {
//        return inventory.canHarvestBlock(par1Block);
//    }
//
//
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.readEntityFromNBT(par1NBTTagCompound);
//        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Inventory");
//        inventory.readFromNBT(nbttaglist);
//        dimension = par1NBTTagCompound.getInteger("Dimension");
//        sleeping = par1NBTTagCompound.getBoolean("Sleeping");
//        sleepTimer = par1NBTTagCompound.getShort("SleepTimer");
//        experience = par1NBTTagCompound.getFloat("XpP");
//        experienceLevel = par1NBTTagCompound.getInteger("XpLevel");
//        experienceTotal = par1NBTTagCompound.getInteger("XpTotal");
//
//        if (sleeping)
//        {
//            playerLocation = new ChunkCoordinates(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
//            wakeUpPlayer(true, true, false);
//        }
//
//        if (par1NBTTagCompound.hasKey("SpawnX") && par1NBTTagCompound.hasKey("SpawnY") && par1NBTTagCompound.hasKey("SpawnZ"))
//        {
//            spawnChunk = new ChunkCoordinates(par1NBTTagCompound.getInteger("SpawnX"), par1NBTTagCompound.getInteger("SpawnY"), par1NBTTagCompound.getInteger("SpawnZ"));
//        }
//
//        foodStats.readNBT(par1NBTTagCompound);
//        capabilities.readCapabilitiesFromNBT(par1NBTTagCompound);
//    }
//
//
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.writeEntityToNBT(par1NBTTagCompound);
//        par1NBTTagCompound.setTag("Inventory", inventory.writeToNBT(new NBTTagList()));
//        par1NBTTagCompound.setInteger("Dimension", dimension);
//        par1NBTTagCompound.setBoolean("Sleeping", sleeping);
//        par1NBTTagCompound.setShort("SleepTimer", (short)sleepTimer);
//        par1NBTTagCompound.setFloat("XpP", experience);
//        par1NBTTagCompound.setInteger("XpLevel", experienceLevel);
//        par1NBTTagCompound.setInteger("XpTotal", experienceTotal);
//
//        if (spawnChunk != null)
//        {
//            par1NBTTagCompound.setInteger("SpawnX", spawnChunk.posX);
//            par1NBTTagCompound.setInteger("SpawnY", spawnChunk.posY);
//            par1NBTTagCompound.setInteger("SpawnZ", spawnChunk.posZ);
//        }
//
//        foodStats.writeNBT(par1NBTTagCompound);
//        capabilities.writeCapabilitiesToNBT(par1NBTTagCompound);
//    }
//
//
//    public void displayGUIChest(IInventory iinventory)
//    {
//    }
//
//    public void displayGUIEnchantment(int x, int y, int z)
//    {
//    }
//
//
//    public void displayWorkbenchGUI(int x, int y, int z)
//    {
//    }
//
//
//    public void onItemPickup(Entity entity, int i)
//    {
//    }
//
//    public float getEyeHeight()
//    {
//        return 0.12F;
//    }
//
//
//    protected void resetHeight()
//    {
//        yOffset = 1.62F;
//    }
//
//
//    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
//    {
//        if (capabilities.disableDamage && !par1DamageSource.canHarmInCreative())
//        {
//            return false;
//        }
//
//        entityAge = 0;
//
//        if (getHealth() <= 0)
//        {
//            return false;
//        }
//
//        if (isPlayerSleeping() && !worldObj.isRemote)
//        {
//            wakeUpPlayer(true, true, false);
//        }
//
//        Entity entity = par1DamageSource.getEntity();
//
//        if ((entity instanceof EntityMob) || (entity instanceof EntityArrow))
//        {
//            if (worldObj.difficultySetting == 0)
//            {
//                par2 = 0;
//            }
//
//            if (worldObj.difficultySetting == 1)
//            {
//                par2 = par2 / 2 + 1;
//            }
//
//            if (worldObj.difficultySetting == 3)
//            {
//                par2 = (par2 * 3) / 2;
//            }
//        }
//
//        if (par2 == 0)
//        {
//            return false;
//        }
//
//        Entity entity1 = entity;
//
//        if ((entity1 instanceof EntityArrow) && ((EntityArrow)entity1).shootingEntity != null)
//        {
//            entity1 = ((EntityArrow)entity1).shootingEntity;
//        }
//
//        if (entity1 instanceof EntityLivingBase)
//        {
//            alertWolves((EntityLivingBase)entity1, false);
//        }
//
//        addStat(StatList.damageTakenStat, par2);
//        return super.attackEntityFrom(par1DamageSource, par2);
//    }
//
//
//    protected int applyPotionDamageCalculations(DamageSource par1DamageSource, int par2)
//    {
//        int i = super.applyPotionDamageCalculations(par1DamageSource, par2);
//
//        if (i <= 0)
//        {
//            return 0;
//        }
//
//        int j = EnchantmentHelper.getEnchantmentModifierDamage(inventory, par1DamageSource);
//
//        if (j > 20)
//        {
//            j = 20;
//        }
//
//        if (j > 0 && j <= 20)
//        {
//            int k = 25 - j;
//            int l = i * z + carryoverDamage;
//            i = l / 25;
//            carryoverDamage = l % 25;
//        }
//
//        return i;
//    }
//
//
//    protected boolean isPVPEnabled()
//    {
//        return false;
//    }
//
//
//    protected void alertWolves(EntityLivingBase par1EntityLivingBase, boolean par2)
//    {
//        if ((par1EntityLivingBase instanceof EntityCreeper) || (par1EntityLivingBase instanceof EntityGhast))
//        {
//            return;
//        }
//
//        if (par1EntityLivingBase instanceof EntityWolf)
//        {
//            EntityWolf entitywolf = (EntityWolf)par1EntityLivingBase;
//
//            if (entitywolf.isTamed() && username.equals(entitywolf.getOwnerName()))
//            {
//                return;
//            }
//        }
//
//        if ((par1EntityLivingBase instanceof EntityPlayer) && !isPVPEnabled())
//        {
//            return;
//        }
//
//        List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityWolf.class, AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(16D, 4D, 16D));
//        Iterator iterator = list.iterator();
//
//        do
//        {
//            if (!iterator.hasNext())
//            {
//                break;
//            }
//
//            Entity entity = (Entity)iterator.next();
//            EntityWolf entitywolf1 = (EntityWolf)entity;
//
//            if (entitywolf1.isTamed() && entitywolf1.getEntityToAttack() == null && username.equals(entitywolf1.getOwnerName()) && (!par2 || !entitywolf1.isSitting()))
//            {
//                entitywolf1.func_48140_f(false);
//                entitywolf1.setTarget(par1EntityLivingBase);
//            }
//        }
//        while (true);
//    }
//
//    protected void damageArmor(int par1)
//    {
//        inventory.damageArmor(par1);
//    }
//
//
//    public int getTotalArmorValue()
//    {
//        return inventory.getTotalArmorValue();
//    }
//
//
//    protected void damageEntity(DamageSource par1DamageSource, int par2)
//    {
//        if (!par1DamageSource.isUnblockable() && isBlocking())
//        {
//            par2 = 1 + par2 >> 1;
//        }
//
//        par2 = applyArmorCalculations(par1DamageSource, par2);
//        par2 = applyPotionDamageCalculations(par1DamageSource, par2);
//        addExhaustion(par1DamageSource.getHungerDamage());
//        health -= par2;
//    }
//
//
//    public void displayGUIFurnace(TileEntityFurnace tileentityfurnace)
//    {
//    }
//
//
//    public void displayGUIDispenser(TileEntityDispenser tileentitydispenser)
//    {
//    }
//
//
//    public void displayGUIEditSign(TileEntitySign tileentitysign)
//    {
//    }
//
//
//    public void displayGUIBrewingStand(TileEntityBrewingStand tileentitybrewingstand)
//    {
//    }
//
//
//    public void useCurrentItemOnEntity(Entity par1Entity)
//    {
//        if (par1Entity.interact(this))
//        {
//            return;
//        }
//
//        ItemStack itemstack = getCurrentEquippedItem();
//
//        if (itemstack != null && (par1Entity instanceof EntityLivingBase))
//        {
//            itemstack.useItemOnEntity((EntityLivingBase)par1Entity);
//
//            if (itemstack.stackSize <= 0)
//            {
//                itemstack.onItemDestroyedByUse(this);
//                destroyCurrentEquippedItem();
//            }
//        }
//    }
//
//
//    public ItemStack getCurrentEquippedItem()
//    {
//        return inventory.getCurrentItem();
//    }
//
//
//    public void destroyCurrentEquippedItem()
//    {
//        inventory.setInventorySlotContents(inventory.currentItem, null);
//    }
//
//
//    public double getYOffset()
//    {
//        return (double)(yOffset - 0.5F);
//    }
//
//
//    public void swingItem()
//    {
//        if (!isSwinging || swingProgressInt >= getSwingSpeedModifier() / 2 || swingProgressInt < 0)
//        {
//            swingProgressInt = -1;
//            isSwinging = true;
//        }
//    }
//
//
//    public void attackTargetEntityWithCurrentItem(Entity par1Entity)
//    {
//        if (!par1Entity.canAttackWithItem())
//        {
//            return;
//        }
//
//        int i = inventory.getDamageVsEntity(par1Entity);
//
//        if (isPotionActive(Potion.damageBoost))
//        {
//            i += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();
//        }
//
//        if (isPotionActive(Potion.weakness))
//        {
//            i -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();
//        }
//
//        int j = 0;
//        int k = 0;
//
//        if (par1Entity instanceof EntityLivingBase)
//        {
//            k = EnchantmentHelper.getEnchantmentModifierLiving(inventory, (EntityLivingBase)par1Entity);
//            y += EnchantmentHelper.getKnockbackModifier(inventory, (EntityLivingBase)par1Entity);
//        }
//
//        if (isSprinting())
//        {
//            j++;
//        }
//
//        if (i > 0 || k > 0)
//        {
//            boolean flag = fallDistance > 0.0F && !onGround && !isOnLadder() && !isInWater() && !isPotionActive(Potion.blindness) && ridingEntity == null && (par1Entity instanceof EntityLivingBase);
//
//            if (flag)
//            {
//                i += rand.nextInt(i / 2 + 2);
//            }
//
//            i += k;
//            boolean flag1 = par1Entity.attackEntityFrom(DamageSource.causePlayerDamage(this), i);
//
//            if (flag1)
//            {
//                if (j > 0)
//                {
//                    par1Entity.addVelocity(-MathHelper.sin((rotationYaw * (float)Math.PI) / 180F) * (float)j * 0.5F, 0.10000000000000001D, MathHelper.cos((rotationYaw * (float)Math.PI) / 180F) * (float)j * 0.5F);
//                    motionX *= 0.59999999999999998D;
//                    motionZ *= 0.59999999999999998D;
//                    setSprinting(false);
//                }
//
//                if (flag)
//                {
//                    onCriticalHit(par1Entity);
//                }
//
//                if (k > 0)
//                {
//                    onEnchantmentCritical(par1Entity);
//                }
//
//                if (i >= 18)
//                {
//                    triggerAchievement(AchievementList.overkill);
//                }
//
//                setLastAttackingEntity(par1Entity);
//            }
//
//            ItemStack itemstack = getCurrentEquippedItem();
//
//            if (itemstack != null && (par1Entity instanceof EntityLivingBase))
//            {
//                itemstack.hitEntity((EntityLivingBase)par1Entity, this);
//
//                if (itemstack.stackSize <= 0)
//                {
//                    itemstack.onItemDestroyedByUse(this);
//                    destroyCurrentEquippedItem();
//                }
//            }
//
//            if (par1Entity instanceof EntityLivingBase)
//            {
//                if (par1Entity.isEntityAlive())
//                {
//                    alertWolves((EntityLivingBase)par1Entity, true);
//                }
//
//                addStat(StatList.damageDealtStat, i);
//                int l = EnchantmentHelper.getFireAspectModifier(inventory, (EntityLivingBase)par1Entity);
//
//                if (l > 0)
//                {
//                    par1Entity.setFire(l * 4);
//                }
//            }
//
//            addExhaustion(0.3F);
//        }
//    }
//
//
//    public void onCriticalHit(Entity entity)
//    {
//    }
//
//    public void onEnchantmentCritical(Entity entity)
//    {
//    }
//
//    public void respawnPlayer()
//    {
//    }
//
//    public abstract void func_6420_o();
//
//    public void onItemStackChanged(ItemStack itemstack)
//    {
//    }
//
//
//    public void setDead()
//    {
//        super.setDead();
//        inventorySlots.onCraftGuiClosed(this);
//
//        if (craftingInventory != null)
//        {
//            craftingInventory.onCraftGuiClosed(this);
//        }
//    }
//
//
//    public boolean isEntityInsideOpaqueBlock()
//    {
//        return !sleeping && super.isEntityInsideOpaqueBlock();
//    }
//
//
//    public EnumStatus sleepInBedAt(int par1, int par2, int par3)
//    {
//        if (!worldObj.isRemote)
//        {
//            if (isPlayerSleeping() || !isEntityAlive())
//            {
//                return EnumStatus.OTHER_PROBLEM;
//            }
//
//            if (!worldObj.worldProvider.func_48217_e())
//            {
//                return EnumStatus.NOT_POSSIBLE_HERE;
//            }
//
//            if (worldObj.isDaytime())
//            {
//                return EnumStatus.NOT_POSSIBLE_NOW;
//            }
//
//            if (Math.abs(posX - (double)par1) > 3D || Math.abs(posY - (double)par2) > 2D || Math.abs(posZ - (double)par3) > 3D)
//            {
//                return EnumStatus.TOO_FAR_AWAY;
//            }
//
//            double d = 8D;
//            double d1 = 5D;
//            List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityMob.class, AxisAlignedBB.getBoundingBox((double)par1 - d, (double)par2 - d1, (double)par3 - d, (double)par1 + d, (double)par2 + d1, (double)par3 + d));
//
//            if (!list.isEmpty())
//            {
//                return EnumStatus.NOT_SAFE;
//            }
//        }
//
//        setSize(0.2F, 0.2F);
//        yOffset = 0.2F;
//
//        if (worldObj.blockExists(par1, par2, par3))
//        {
//            int i = worldObj.getBlockMetadata(par1, par2, par3);
//            int j = BlockBed.getDirection(i);
//            float f = 0.5F;
//            float f1 = 0.5F;
//
//            switch (j)
//            {
//                case 0:
//                    f1 = 0.9F;
//                    break;
//
//                case 2:
//                    f1 = 0.1F;
//                    break;
//
//                case 1:
//                    f = 0.1F;
//                    break;
//
//                case 3:
//                    f = 0.9F;
//                    break;
//            }
//
//            func_22052_e(j);
//            setPosition((float)par1 + f, (float)par2 + 0.9375F, (float)par3 + f1);
//        }
//        else
//        {
//            setPosition((float)par1 + 0.5F, (float)par2 + 0.9375F, (float)par3 + 0.5F);
//        }
//
//        sleeping = true;
//        sleepTimer = 0;
//        playerLocation = new ChunkCoordinates(par1, par2, par3);
//        motionX = motionZ = motionY = 0.0D;
//
//        if (!worldObj.isRemote)
//        {
//            worldObj.updateAllPlayersSleepingFlag();
//        }
//
//        return EnumStatus.OK;
//    }
//
//    private void func_22052_e(int par1)
//    {
//        field_22063_x = 0.0F;
//        field_22061_z = 0.0F;
//
//        switch (par1)
//        {
//            case 0:
//                field_22061_z = -1.8F;
//                break;
//
//            case 2:
//                field_22061_z = 1.8F;
//                break;
//
//            case 1:
//                field_22063_x = 1.8F;
//                break;
//
//            case 3:
//                field_22063_x = -1.8F;
//                break;
//        }
//    }
//
//
//    public void wakeUpPlayer(boolean par1, boolean par2, boolean par3)
//    {
//        setSize(0.6F, 1.8F);
//        resetHeight();
//        ChunkCoordinates chunkcoordinates = playerLocation;
//        ChunkCoordinates chunkcoordinates1 = playerLocation;
//
//        if (chunkcoordinates != null && worldObj.getBlock(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ) == Blocks.bed)
//        {
//            BlockBed.setBedOccupied(worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, false);
//            ChunkCoordinates chunkcoordinates2 = BlockBed.getNearestEmptyChunkCoordinates(worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, 0);
//
//            if (chunkcoordinates2 == null)
//            {
//                chunkcoordinates2 = new ChunkCoordinates(chunkcoordinates.posX, chunkcoordinates.posY + 1, chunkcoordinates.posZ);
//            }
//
//            setPosition((float)chunkcoordinates2.posX + 0.5F, (float)chunkcoordinates2.posY + yOffset + 0.1F, (float)chunkcoordinates2.posZ + 0.5F);
//        }
//
//        sleeping = false;
//
//        if (!worldObj.isRemote && par2)
//        {
//            worldObj.updateAllPlayersSleepingFlag();
//        }
//
//        if (par1)
//        {
//            sleepTimer = 0;
//        }
//        else
//        {
//            sleepTimer = 100;
//        }
//
//        if (par3)
//        {
//            setSpawnChunk(playerLocation);
//        }
//    }
//
//
//    private boolean isInBed()
//    {
//		// SNIP
//        return (worldObj.getBlock(playerLocation.posX, playerLocation.posY, playerLocation.posZ) == Blocks.bed || worldObj.getBlock(playerLocation.posX, playerLocation.posY, playerLocation.posZ) == mod_SpiderQueen.webBed);
//    }
//
//
//    public static ChunkCoordinates verifyRespawnCoordinates(World par0World, ChunkCoordinates par1ChunkCoordinates)
//    {
//        IChunkProvider ichunkprovider = par0World.getChunkProvider();
//        ichunkprovider.loadChunk(par1ChunkCoordinates.posX - 3 >> 4, par1ChunkCoordinates.posZ - 3 >> 4);
//        ichunkprovider.loadChunk(par1ChunkCoordinates.posX + 3 >> 4, par1ChunkCoordinates.posZ - 3 >> 4);
//        ichunkprovider.loadChunk(par1ChunkCoordinates.posX - 3 >> 4, par1ChunkCoordinates.posZ + 3 >> 4);
//        ichunkprovider.loadChunk(par1ChunkCoordinates.posX + 3 >> 4, par1ChunkCoordinates.posZ + 3 >> 4);
//
//        if (par0World.getBlock(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ) != Blocks.bed)
//        {
//            return null;
//        }
//        else
//        {
//            ChunkCoordinates chunkcoordinates = BlockBed.getNearestEmptyChunkCoordinates(par0World, par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ, 0);
//            return chunkcoordinates;
//        }
//    }
//
//
//    public float getBedOrientationInDegrees()
//    {
//        if (playerLocation != null)
//        {
//            int i = worldObj.getBlockMetadata(playerLocation.posX, playerLocation.posY, playerLocation.posZ);
//            int j = BlockBed.getDirection(i);
//
//            switch (j)
//            {
//                case 0:
//                    return 90F;
//
//                case 1:
//                    return 0.0F;
//
//                case 2:
//                    return 270F;
//
//                case 3:
//                    return 180F;
//            }
//        }
//
//        return 0.0F;
//    }
//
//
//    public boolean isPlayerSleeping()
//    {
//        return sleeping;
//    }
//
//
//    public boolean isPlayerFullyAsleep()
//    {
//        return sleeping && sleepTimer >= 100;
//    }
//
//    public int getSleepTimer()
//    {
//        return sleepTimer;
//    }
//
//
//    public void addChatMessage(String s)
//    {
//    }
//
//
//    public ChunkCoordinates getSpawnChunk()
//    {
//        return spawnChunk;
//    }
//
//
//    public void setSpawnChunk(ChunkCoordinates par1ChunkCoordinates)
//    {
//        if (par1ChunkCoordinates != null)
//        {
//            spawnChunk = new ChunkCoordinates(par1ChunkCoordinates);
//        }
//        else
//        {
//            spawnChunk = null;
//        }
//    }
//
//
//    public void triggerAchievement(StatBase par1StatBase)
//    {
//        addStat(par1StatBase, 1);
//    }
//
//
//    public void addStat(StatBase statbase, int i)
//    {
//    }
//
//
//    protected void jump()
//    {
//        super.jump();
//        addStat(StatList.jumpStat, 1);
//
//        if (isSprinting())
//        {
//            addExhaustion(0.8F);
//        }
//        else
//        {
//            addExhaustion(0.2F);
//        }
//    }
//
//
//    public void moveEntityWithHeading(float par1, float par2)
//    {
//        double d = posX;
//        double d1 = posY;
//        double d2 = posZ;
//
//        if (capabilities.isFlying)
//        {
//            double d3 = motionY;
//            float f = jumpMovementFactor;
//            jumpMovementFactor = 0.05F;
//            super.moveEntityWithHeading(par1, par2);
//            motionY = d3 * 0.59999999999999998D;
//            jumpMovementFactor = f;
//        }
//        else
//        {
//            super.moveEntityWithHeading(par1, par2);
//        }
//
//        addMovementStat(posX - d, posY - d1, posZ - d2);
//    }
//
//
//    public void addMovementStat(double par1, double par3, double par5)
//    {
//        if (ridingEntity != null)
//        {
//            return;
//        }
//
//        if (isInsideOfMaterial(Material.water))
//        {
//            int i = Math.round(MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5) * 100F);
//
//            if (i > 0)
//            {
//                addStat(StatList.distanceDoveStat, i);
//                addExhaustion(0.015F * (float)i * 0.01F);
//            }
//        }
//        else if (isInWater())
//        {
//            int j = Math.round(MathHelper.sqrt_double(par1 * par1 + par5 * par5) * 100F);
//
//            if (j > 0)
//            {
//                addStat(StatList.distanceSwumStat, j);
//                addExhaustion(0.015F * (float)j * 0.01F);
//            }
//        }
//        else if (isOnLadder())
//        {
//            if (par3 > 0.0D)
//            {
//                addStat(StatList.distanceClimbedStat, (int)Math.round(par3 * 100D));
//            }
//        }
//        else if (onGround)
//        {
//            int k = Math.round(MathHelper.sqrt_double(par1 * par1 + par5 * par5) * 100F);
//
//            if (k > 0)
//            {
//                addStat(StatList.distanceWalkedStat, k);
//
//                if (isSprinting())
//                {
//                    addExhaustion(0.09999999F * (float)k * 0.01F);
//                }
//                else
//                {
//                    addExhaustion(0.01F * (float)k * 0.01F);
//                }
//            }
//        }
//        else
//        {
//            int l = Math.round(MathHelper.sqrt_double(par1 * par1 + par5 * par5) * 100F);
//
//            if (l > 25)
//            {
//                addStat(StatList.distanceFlownStat, l);
//            }
//        }
//    }
//
//
//    private void addMountedMovementStat(double par1, double par3, double par5)
//    {
//        if (ridingEntity != null)
//        {
//            int i = Math.round(MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5) * 100F);
//
//            if (i > 0)
//            {
//                if (ridingEntity instanceof EntityMinecart)
//                {
//                    addStat(StatList.distanceByMinecartStat, i);
//
//                    if (startMinecartRidingCoordinate == null)
//                    {
//                        startMinecartRidingCoordinate = new ChunkCoordinates(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
//                    }
//                    else if (startMinecartRidingCoordinate.getEuclideanDistanceTo(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) >= 1000D)
//                    {
//                        addStat(AchievementList.onARail, 1);
//                    }
//                }
//                else if (ridingEntity instanceof EntityBoat)
//                {
//                    addStat(StatList.distanceByBoatStat, i);
//                }
//                else if (ridingEntity instanceof EntityPig)
//                {
//                    addStat(StatList.distanceByPigStat, i);
//                }
//            }
//        }
//    }
//
//
//    protected void fall(float par1)
//    {
//        if (capabilities.allowFlying)
//        {
//            return;
//        }
//
//        if (par1 >= 2.0F)
//        {
//            addStat(StatList.distanceFallenStat, (int)Math.round((double)par1 * 100D));
//        }
//
//        super.fall(par1);
//    }
//
//
//    public void onKillEntity(EntityLivingBase par1EntityLivingBase)
//    {
//        if (par1EntityLivingBase instanceof EntityMob)
//        {
//            triggerAchievement(AchievementList.killEnemy);
//        }
//    }
//
//
//    public int getItemIcon(ItemStack par1ItemStack, int par2)
//    {
//        int i = super.getItemIcon(par1ItemStack, par2);
//
//        if (par1ItemStack.itemID == Items.fishingRod && fishEntity != null)
//        {
//            i = par1ItemStack.getIconIndex() + 16;
//        }
//        else
//        {
//            if (par1ItemStack.getItem().func_46058_c())
//            {
//                return par1ItemStack.getItem().func_46057_a(par1ItemStack.getItemDamage(), par2);
//            }
//
//            if (itemInUse != null && par1ItemStack.itemID == Items.bow)
//            {
//                int j = par1ItemStack.getMaxItemUseDuration() - itemInUseCount;
//
//                if (j >= 18)
//                {
//                    return 133;
//                }
//
//                if (j > 13)
//                {
//                    return 117;
//                }
//
//                if (j > 0)
//                {
//                    return 101;
//                }
//            }
//        }
//
//        return i;
//    }
//
//
//    public void setInPortal()
//    {
//        if (timeUntilPortal > 0)
//        {
//            timeUntilPortal = 10;
//            return;
//        }
//        else
//        {
//            inPortal = true;
//            return;
//        }
//    }
//
//
//    public void addExperience(int par1)
//    {
//        score += par1;
//        int i = 0x7fffffff - experienceTotal;
//
//        if (par1 > i)
//        {
//            par1 = i;
//        }
//
//        experience += (float)par1 / (float)xpBarCap();
//        experienceTotal += par1;
//
//        for (; experience >= 1.0F; experience = experience / (float)xpBarCap())
//        {
//            experience = (experience - 1.0F) * (float)xpBarCap();
//            increaseLevel();
//        }
//    }
//
//
//    public void removeExperience(int par1)
//    {
//        experienceLevel -= par1;
//
//        if (experienceLevel < 0)
//        {
//            experienceLevel = 0;
//        }
//    }
//
//
//    public int xpBarCap()
//    {
//        return 7 + (experienceLevel * 7 >> 1);
//    }
//
//
//    private void increaseLevel()
//    {
//        experienceLevel++;
//    }
//
//
//    public void addExhaustion(float par1)
//    {
//        if (capabilities.disableDamage)
//        {
//            return;
//        }
//
//        if (!worldObj.isRemote)
//        {
//            foodStats.addExhaustion(par1);
//        }
//    }
//
//
//    public FoodStats getFoodStats()
//    {
//        return foodStats;
//    }
//
//    public boolean canEat(boolean par1)
//    {
//        return (par1 || foodStats.needFood()) && !capabilities.disableDamage;
//    }
//
//
//    public boolean shouldHeal()
//    {
//        return getHealth() > 0 && getHealth() < getMaxHealth();
//    }
//
//
//    public void setItemInUse(ItemStack par1ItemStack, int par2)
//    {
//        if (par1ItemStack == itemInUse)
//        {
//            return;
//        }
//
//        itemInUse = par1ItemStack;
//        itemInUseCount = par2;
//
//        if (!worldObj.isRemote)
//        {
//            setEating(true);
//        }
//    }
//
//    public boolean canPlayerEdit(int par1, int par2, int par3)
//    {
//        return true;
//    }
//
//
//    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
//    {
//        int i = experienceLevel * 7;
//
//        if (i > 100)
//        {
//            return 100;
//        }
//        else
//        {
//            return i;
//        }
//    }
//
//
//    protected boolean isPlayer()
//    {
//        return true;
//    }
//
//    public void travelToTheEnd(int i)
//    {
//    }
//
//
//    public void copyPlayer(EntityPlayer par1EntityPlayer)
//    {
//        inventory.copyInventory(par1EntityPlayer.inventory);
//        health = par1EntityPlayer.health;
//        foodStats = par1EntityPlayer.foodStats;
//        experienceLevel = par1EntityPlayer.experienceLevel;
//        experienceTotal = par1EntityPlayer.experienceTotal;
//        experience = par1EntityPlayer.experience;
//        score = par1EntityPlayer.score;
//    }
//
//
//    protected boolean canTriggerWalking()
//    {
//        return !capabilities.isFlying;
//    }
//
//    public void func_50009_aI()
//    {
//    }
//}
