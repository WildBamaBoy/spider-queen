package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCocoonSkeleton extends EntityCocoon
{

    public EntityCocoonSkeleton(World world)
    {
        super(world);
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }
	
    public EntityCocoonSkeleton(World world, double d, double d1, double d2, float rr, int Typ)
    {
		super(world,d,d1,d2,rr,Typ);
    }
	
	public void mySize()
	{
        setSize(1F, 1.1F);
	}
	
	public void doSound()
	{
		worldObj.playSoundAtEntity(this, "mob.skeletonhurt", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
	}
	
	public void onEaten()
	{
		entityDropItem(new ItemStack(mod_SpiderQueen.itemSpiderEgg.shiftedIndex, 3, 0), 0);
	}

	public EntityItem dropItemWithDamage(int i, int j, int f)
    {
        return super.dropItemWithDamage(mod_SpiderQueen.itemCocoonSkeleton.shiftedIndex,j,f);
    }
	

    public boolean canBeCollidedWith()
    {
        return super.canBeCollidedWith();
    }

    public void onUpdate()
    {
        super.onUpdate();
		
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
