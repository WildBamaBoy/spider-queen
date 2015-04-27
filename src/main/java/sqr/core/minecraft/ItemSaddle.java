package sqr.core.minecraft;
//package sqr.item;
//
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.passive.EntityPig;
//import net.minecraft.item.ItemStack;
//import sqr.entity.EntityPoisonSpider;
//
//public class ItemSaddle extends Item
//{
//    public ItemSaddle(int par1)
//    {
//        super(par1);
//        maxStackSize = 1;
//    }
//
//
//    public void useItemOnEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase)
//    {
//        if (par2EntityLivingBase instanceof EntityPig)
//        {
//            EntityPig entitypig = (EntityPig)par2EntityLivingBase;
//
//            if (!entitypig.getSaddled() && !entitypig.isChild())
//            {
//                entitypig.setSaddled(true);
//                par1ItemStack.stackSize--;
//            }
//        }
//
//		// SNIP
//		if(par2EntityLivingBase instanceof EntityPoisonSpider)
//        {
//            EntityPoisonSpider entitypig = (EntityPoisonSpider)par2EntityLivingBase;
//            if(!entitypig.getSaddled())
//            {
//                entitypig.setSaddled(true);
//                par1ItemStack.stackSize--;
//            }
//        }
//		// -SNIP
//    }
//
//
//    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
//    {
//        useItemOnEntity(par1ItemStack, par2EntityLivingBase);
//        return true;
//    }
//}
