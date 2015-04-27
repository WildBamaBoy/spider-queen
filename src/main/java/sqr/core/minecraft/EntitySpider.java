package sqr.core.minecraft;
//package net.minecraft.src;
//
//import net.minecraft.entity.EnumCreatureAttribute;
//import net.minecraft.entity.monster.EntityMob;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//
//public class EntitySpider extends EntityMob
//{
//    public EntitySpider(World par1World)
//    {
//        super(par1World);
//        texture = "/mob/spider.png";
//        setSize(1.4F, 0.9F);
//        //TODO Attribute moveSpeed = 0.8F;
//    }
//
//    protected void entityInit()
//    {
//        super.entityInit();
//        dataWatcher.addObject(16, new Byte((byte)0));
//    }
//
//
//    public void onLivingUpdate()
//    {
//        super.onLivingUpdate();
//    }
//
//
//    public void onUpdate()
//    {
//        super.onUpdate();
//
//        if (!worldObj.isRemote)
//        {
//            func_40148_a(isCollidedHorizontally);
//        }
//    }
//
//    public int getMaxHealth()
//    {
//        return 16;
//    }
//
//
//    public double getMountedYOffset()
//    {
//        return (double)height * 0.75D - 0.5D;
//    }
//
//
//    protected boolean canTriggerWalking()
//    {
//        return false;
//    }
//
//
//    protected Entity findPlayerToAttack()
//    {
//        float f = getBrightness(1.0F);
//
//        if (f < 0.5F)
//        {
//            double d = 16D;
//            return worldObj.getClosestVulnerablePlayerToEntity(this, d);
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//
//    protected String getLivingSound()
//    {
//        return "mob.spider";
//    }
//
//
//    protected String getHurtSound()
//    {
//        return "mob.spider";
//    }
//
//
//    protected String getDeathSound()
//    {
//        return "mob.spiderdeath";
//    }
//
//
//    protected void attackEntity(Entity par1Entity, float par2)
//    {
//        float f = getBrightness(1.0F);
//
//        if (f > 0.5F && rand.nextInt(100) == 0)
//        {
//            //entityToAttack = null; // SNIP
//            //return;
//        }
//
//        if (par2 > 2.0F && par2 < 6F && rand.nextInt(10) == 0)
//        {
//            if (onGround)
//            {
//                double d = par1Entity.posX - posX;
//                double d1 = par1Entity.posZ - posZ;
//                float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
//                motionX = (d / (double)f1) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
//                motionZ = (d1 / (double)f1) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
//                motionY = 0.40000000596046448D;
//            }
//        }
//        else
//        {
//            super.attackEntity(par1Entity, par2);
//        }
//    }
//
//
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.writeEntityToNBT(par1NBTTagCompound);
//    }
//
//
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.readEntityFromNBT(par1NBTTagCompound);
//    }
//
//
//    protected int getDropItemId()
//    {
//        return Items.string;
//    }
//
//
//    protected void dropFewItems(boolean par1, int par2)
//    {
//        super.dropFewItems(par1, par2);
//
//        if (par1 && (rand.nextInt(3) == 0 || rand.nextInt(1 + par2) > 0))
//        {
//            dropItem(Items.spiderEye, 1);
//        }
//    }
//
//
//    public boolean isOnLadder()
//    {
//        return func_40149_l_();
//    }
//
//
//    public void setInWeb()
//    {
//    }
//
//
//    public float spiderScaleAmount()
//    {
//        return 1.0F;
//    }
//
//
//    public EnumCreatureAttribute getCreatureAttribute()
//    {
//        return EnumCreatureAttribute.ARTHROPOD;
//    }
//
//    public boolean isPotionApplicable(PotionEffect par1PotionEffect)
//    {
//        if (par1PotionEffect.getPotionID() == Potion.poison.id)
//        {
//            return false;
//        }
//        else
//        {
//            return super.isPotionApplicable(par1PotionEffect);
//        }
//    }
//
//    public boolean func_40149_l_()
//    {
//        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
//    }
//
//    public void func_40148_a(boolean par1)
//    {
//        byte byte0 = dataWatcher.getWatchableObjectByte(16);
//
//        if (par1)
//        {
//            byte0 |= 1;
//        }
//        else
//        {
//            byte0 &= 0xfe;
//        }
//
//        dataWatcher.updateObject(16, Byte.valueOf(byte0));
//    }
//}
