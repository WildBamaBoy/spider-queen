package spiderqueen.old.item;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.ItemStack;
import spiderqueen.old.entity.EntityPoisonSpider;

public class ItemSaddle extends Item
{
    public ItemSaddle(int par1)
    {
        super(par1);
        maxStackSize = 1;
    }

    /**
     * Called when a player right clicks a entity with a item.
     */
    public void useItemOnEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving)
    {
        if (par2EntityLiving instanceof EntityPig)
        {
            EntityPig entitypig = (EntityPig)par2EntityLiving;

            if (!entitypig.getSaddled() && !entitypig.isChild())
            {
                entitypig.setSaddled(true);
                par1ItemStack.stackSize--;
            }
        }
		
		// SNIP
		if(par2EntityLiving instanceof EntityPoisonSpider)
        {
            EntityPoisonSpider entitypig = (EntityPoisonSpider)par2EntityLiving;
            if(!entitypig.getSaddled())
            {
                entitypig.setSaddled(true);
                par1ItemStack.stackSize--;
            }
        }
		// -SNIP
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        useItemOnEntity(par1ItemStack, par2EntityLiving);
        return true;
    }
}
