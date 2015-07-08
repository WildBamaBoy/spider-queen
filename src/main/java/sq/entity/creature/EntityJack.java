package sq.entity.creature;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import radixcore.constant.Time;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.core.minecraft.ModBlocks;
import sq.core.minecraft.ModItems;
import sq.entity.AbstractFlyingMob;
import sq.entity.throwable.EntityJackBall;

/**
 * Jack is a flying mob that fires a projectile at the player. During the day, he turns into a Jack block.
 */
public class EntityJack extends AbstractFlyingMob
{
	private int attackTimer;

	public EntityJack(World world) 
	{
		super(world, "jack");
		setSize(1.2F, 1.0F);
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		if (!SpiderCore.getConfig().enableJack)
		{
			setDead();
		}

		if (!worldObj.isRemote)
		{
			//Turn into a block during the day.
			if (worldObj.isDaytime())
			{
				int yMov = 0;
				boolean allowBlockPlace =  false;

				while (yMov > -255)
				{
					Block block = worldObj.getBlock((int)posX, (int)posY + yMov, (int)posZ);

					if (block != Blocks.air)
					{
						allowBlockPlace = true;
						break;
					}
					yMov--;
				}

				if (allowBlockPlace)
				{
					worldObj.setBlock((int)posX, (int)posY + yMov + 1, (int)posZ, ModBlocks.jack);
					setDead();
				}
			}
			
			//Decrease attack timer constantly.
			attackTimer--;
			
			//Check for next attack.
			if (attackTimer <= 0)
			{
				attackTimer = Time.SECOND * RadixMath.getNumberInRange(2, 5);
				
				if (this.getEntityToAttack() != null)
				{
					EntityJackBall attackBall = new EntityJackBall(worldObj, this, (EntityLivingBase) this.getEntityToAttack(), 1.6F, 4.0F);
					worldObj.spawnEntityInWorld(attackBall);
				}
			}
		}
	}

	@Override
	public boolean isAIEnabled() 
	{
		return false;
	}

	@Override
	public float getHitDamage() 
	{
		return 6.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 1.0D;
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 80.0F;
	}

	@Override
	public boolean isPassive() 
	{
		return false;
	}
	
	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootLevel)
	{
		if (hitByPlayer)
		{
			this.dropItem(ModItems.lantern, 1);
		}
	}
}
