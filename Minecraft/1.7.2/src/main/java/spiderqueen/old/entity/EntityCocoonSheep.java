package spiderqueen.old.entity;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCocoonSheep extends EntityCocoon
{

    public EntityCocoonSheep(World world)
    {
        super(world);
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }
	
	protected boolean canDespawn()
    {
        return false;
    }
	
    public EntityCocoonSheep(World world, double d, double d1, double d2, float rr, int Typ)
    {
		super(world,d,d1,d2,rr,Typ);
    }
	
	public void mySize()
	{
        setSize(1F, 1.1F);
	}
	
	public void doSound()
	{
		worldObj.playSoundAtEntity(this, "mob.sheep", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
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


	public EntityItem dropItemWithDamage(int i, int j, int f)
    {
        return super.dropItemWithDamage(mod_SpiderQueen.itemCocoonSheep.shiftedIndex,j,f);
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
