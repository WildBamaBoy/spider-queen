//package sqr.core.minecraft;
//
//import java.util.List;
//
//import sqr.entity.EntityFriendlySpider;
//import sqr.entity.EntityPlayer;
//import net.minecraft.block.Block;
//import net.minecraft.entity.EntityAgeable;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//
//public abstract class EntityAnimal extends EntityAgeable
//{
//    private int inLove;
//
//
//    private int breeding;
//
//    public EntityAnimal(World par1World)
//    {
//        super(par1World);
//        breeding = 0;
//    }
//
//
//    protected void updateAITick()
//    {
//        if (getGrowingAge() != 0)
//        {
//            inLove = 0;
//        }
//
//        super.updateAITick();
//    }
//
//
//    public void onLivingUpdate()
//    {
//        super.onLivingUpdate();
//
//
//		// SNIP
//		if(entityToAttack != null)
//		{
//			if(entityToAttack instanceof EntityLivingBase)
//			{
//				EntityLivingBase eta = (EntityLivingBase)entityToAttack;
//				if(eta.spiderfriend) { entityToAttack = null; }
//			}
//			if(entityToAttack instanceof EntityPlayer) { entityToAttack = null; }
//			if(entityToAttack instanceof EntityFriendlySpider) { entityToAttack = null; }
//		}
//		// -SNIP
//
//        if (getGrowingAge() != 0)
//        {
//            inLove = 0;
//        }
//
//        if (inLove > 0)
//        {
//            inLove--;
//            String s = "heart";
//
//            if (inLove % 10 == 0)
//            {
//                double d = rand.nextGaussian() * 0.02D;
//                double d1 = rand.nextGaussian() * 0.02D;
//                double d2 = rand.nextGaussian() * 0.02D;
//                worldObj.spawnParticle(s, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
//            }
//        }
//        else
//        {
//            breeding = 0;
//        }
//    }
//
//
//    protected void attackEntity(Entity par1Entity, float par2)
//    {
//        if (par1Entity instanceof EntityPlayer)
//        {
//            if (par2 < 3F)
//            {
//                double d = par1Entity.posX - posX;
//                double d1 = par1Entity.posZ - posZ;
//                rotationYaw = (float)((Math.atan2(d1, d) * 180D) / Math.PI) - 90F;
//                hasAttacked = true;
//            }
//
//            EntityPlayer entityplayer = (EntityPlayer)par1Entity;
//
//            if (entityplayer.getCurrentEquippedItem() == null || !isWheat(entityplayer.getCurrentEquippedItem()))
//            {
//                entityToAttack = null;
//            }
//        }
//        else if (par1Entity instanceof EntityAnimal)
//        {
//            EntityAnimal entityanimal = (EntityAnimal)par1Entity;
//
//            if (getGrowingAge() > 0 && entityanimal.getGrowingAge() < 0)
//            {
//                if ((double)par2 < 2.5D)
//                {
//                    hasAttacked = true;
//                }
//            }
//            else if (inLove > 0 && entityanimal.inLove > 0)
//            {
//                if (entityanimal.entityToAttack == null)
//                {
//                    entityanimal.entityToAttack = this;
//                }
//
//                if (entityanimal.entityToAttack == this && (double)par2 < 3.5D)
//                {
//                    entityanimal.inLove++;
//                    inLove++;
//                    breeding++;
//
//                    if (breeding % 4 == 0)
//                    {
//                        worldObj.spawnParticle("heart", (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, 0.0D, 0.0D, 0.0D);
//                    }
//
//                    if (breeding == 60)
//                    {
//                        procreate((EntityAnimal)par1Entity);
//                    }
//                }
//                else
//                {
//                    breeding = 0;
//                }
//            }
//            else
//            {
//                breeding = 0;
//                entityToAttack = null;
//            }
//        }
//    }
//
//
//    private void procreate(EntityAnimal par1EntityAnimal)
//    {
//        EntityAnimal entityanimal = spawnBabyAnimal(par1EntityAnimal);
//
//        if (entityanimal != null)
//        {
//            setGrowingAge(6000);
//            par1EntityAnimal.setGrowingAge(6000);
//            inLove = 0;
//            breeding = 0;
//            entityToAttack = null;
//            par1EntityAnimal.entityToAttack = null;
//            par1EntityAnimal.breeding = 0;
//            par1EntityAnimal.inLove = 0;
//            entityanimal.setGrowingAge(-24000);
//            entityanimal.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
//
//            for (int i = 0; i < 7; i++)
//            {
//                double d = rand.nextGaussian() * 0.02D;
//                double d1 = rand.nextGaussian() * 0.02D;
//                double d2 = rand.nextGaussian() * 0.02D;
//                worldObj.spawnParticle("heart", (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
//            }
//
//            worldObj.spawnEntityInWorld(entityanimal);
//        }
//    }
//
//
//    public abstract EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal);
//
//
//    protected void attackBlockedEntity(Entity entity, float f)
//    {
//    }
//
//
//    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
//    {
//        fleeingTick = 60;
//        entityToAttack = null;
//        inLove = 0;
//        return super.attackEntityFrom(par1DamageSource, par2);
//    }
//
//
//    public float getBlockPathWeight(int par1, int par2, int par3)
//    {
//        if (worldObj.getBlock(par1, par2 - 1, par3) == Blocks.grass)
//        {
//            return 10F;
//        }
//        else
//        {
//            return worldObj.getLightBrightness(par1, par2, par3) - 0.5F;
//        }
//    }
//
//
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.writeEntityToNBT(par1NBTTagCompound);
//        par1NBTTagCompound.setInteger("InLove", inLove);
//    }
//
//
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.readEntityFromNBT(par1NBTTagCompound);
//        inLove = par1NBTTagCompound.getInteger("InLove");
//    }
//
//
//    protected Entity findPlayerToAttack()
//    {
//        if (fleeingTick > 0)
//        {
//            return null;
//        }
//
//        float f = 8F;
//
//        if (inLove > 0)
//        {
//            List list = worldObj.getEntitiesWithinAABB(getClass(), boundingBox.expand(f, f, f));
//
//            for (int i = 0; i < list.size(); i++)
//            {
//                EntityAnimal entityanimal = (EntityAnimal)list.get(i);
//
//                if (entityanimal != this && entityanimal.inLove > 0)
//                {
//                    return entityanimal;
//                }
//            }
//        }
//        else if (getGrowingAge() == 0)
//        {
//            List list1 = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityPlayer.class, boundingBox.expand(f, f, f));
//
//            for (int j = 0; j < list1.size(); j++)
//            {
//                EntityPlayer entityplayer = (EntityPlayer)list1.get(j);
//
//                if (entityplayer.getCurrentEquippedItem() != null && isWheat(entityplayer.getCurrentEquippedItem()))
//                {
//                    return entityplayer;
//                }
//            }
//        }
//        else if (getGrowingAge() > 0)
//        {
//            List list2 = worldObj.getEntitiesWithinAABB(getClass(), boundingBox.expand(f, f, f));
//
//            for (int k = 0; k < list2.size(); k++)
//            {
//                EntityAnimal entityanimal1 = (EntityAnimal)list2.get(k);
//
//                if (entityanimal1 != this && entityanimal1.getGrowingAge() < 0)
//                {
//                    return entityanimal1;
//                }
//            }
//        }
//
//        return null;
//    }
//
//
//    public boolean getCanSpawnHere()
//    {
//        int i = MathHelper.floor_double(posX);
//        int j = MathHelper.floor_double(boundingBox.minY);
//        int k = MathHelper.floor_double(posZ);
//        return worldObj.getBlock(x, y - 1, z) == Blocks.grass && worldObj.getFullBlockLightValue(x, y, z) > 8 && super.getCanSpawnHere();
//    }
//
//
//    public int getTalkInterval()
//    {
//        return 120;
//    }
//
//
//    protected boolean canDespawn()
//    {
//        return false;
//    }
//
//
//    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
//    {
//        return 1 + worldObj.rand.nextInt(3);
//    }
//
//
//    public boolean isWheat(ItemStack par1ItemStack)
//    {
//        return par1ItemStack.itemID == Items.wheat;
//    }
//
//
//    public boolean interact(EntityPlayer par1EntityPlayer)
//    {
//        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
//
//        if (itemstack != null && isWheat(itemstack) && getGrowingAge() == 0)
//        {
//            if (!par1EntityPlayer.capabilities.isCreativeMode)
//            {
//                itemstack.stackSize--;
//
//                if (itemstack.stackSize <= 0)
//                {
//                    par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, null);
//                }
//            }
//
//            inLove = 600;
//            entityToAttack = null;
//
//            for (int i = 0; i < 7; i++)
//            {
//                double d = rand.nextGaussian() * 0.02D;
//                double d1 = rand.nextGaussian() * 0.02D;
//                double d2 = rand.nextGaussian() * 0.02D;
//                worldObj.spawnParticle("heart", (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
//            }
//
//            return true;
//        }
//        else
//        {
//            return super.interact(par1EntityPlayer);
//        }
//    }
//
//
//    public boolean isInLove()
//    {
//        return inLove > 0;
//    }
//
//    public void resetInLove()
//    {
//        inLove = 0;
//    }
//
//    public boolean func_48135_b(EntityAnimal par1EntityAnimal)
//    {
//        if (par1EntityAnimal == this)
//        {
//            return false;
//        }
//
//        if (par1EntityAnimal.getClass() != getClass())
//        {
//            return false;
//        }
//        else
//        {
//            return isInLove() && par1EntityAnimal.isInLove();
//        }
//    }
// }
