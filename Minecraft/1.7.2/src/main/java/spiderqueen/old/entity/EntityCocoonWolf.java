package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCocoonWolf extends EntityCocoon
{

    public EntityCocoonWolf(World world)
    {
        super(world);
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }
	

    public EntityCocoonWolf(World world, double d, double d1, double d2, float rr, int Typ)
    {
		super(world,d,d1,d2,rr,Typ);
    }
	
	public void mySize()
	{
        setSize(0.4F, 1.1F);
	}

	public EntityItem dropItemWithDamage(int i, int j, int f)
    {
        return super.dropItemWithDamage(mod_SpiderQueen.itemCocoonWolf.shiftedIndex,j,f);
    }
	

    public boolean canBeCollidedWith()
    {
        return super.canBeCollidedWith();
    }

    public void onUpdate()
    {
        super.onUpdate();
		
    }
	
	public void doSound()
	{
		worldObj.playSoundAtEntity(this, "mob.wolf.hurt", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
	}

	public void onEaten()
	{
		entityDropItem(new ItemStack(mod_SpiderQueen.itemWeb.shiftedIndex, 5, 0), 0);
		Random rr = new Random();
		if(rr.nextInt(3) == 0)
		{
			entityDropItem(new ItemStack(mod_SpiderQueen.itemSpiderEgg.shiftedIndex, 1, 0), 0);
		}
	}



    public float getShadowSize()
    {
        return 0.0F;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        return super.interact(entityplayer);
    }

}
