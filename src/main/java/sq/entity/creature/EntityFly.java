package sq.entity.creature;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import sq.entity.AbstractFlyingMob;
import sq.util.Utils;

/**
 * Flies are passive flying creatures that the player can eat by right-clicking.
 */
public class EntityFly extends AbstractFlyingMob
{
	public EntityFly(World world) 
	{
		super(world, "bee"); //Uses bee sounds.
		setSize(0.9F, 0.9F);
	}
	
	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		
		this.entityToAttack = null;
		
		if (!SpiderCore.getConfig().enableFly)
		{
			setDead();
		}
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
	}

	@Override
	public String getCommandSenderName() 
	{
		return "Fly";
	}

	@Override
	public float getHitDamage() 
	{
		return 0.0F;
	}

	@Override
	public double getMoveSpeed() 
	{
		return 0.7D;
	}

	@Override
	public float getMobMaxHealth() 
	{
		return 20.0F;
	}

    protected Entity findPlayerToAttack()
    {
    	return null;
    }

	@Override
	protected boolean interact(EntityPlayer player) 
	{
		if (!worldObj.isRemote)
		{
			//Disappear with smoke and play the eat and burp sounds on the player.
			Utils.spawnParticlesAroundEntityS("smoke", this, 16);
			worldObj.playSoundAtEntity(player, "random.eat", 0.85F, 1.0F);
			worldObj.playSoundAtEntity(player, "random.burp", 0.85F, 1.0F);
			
			//Heal the player and restore food stats.
			player.heal(3);
			player.getFoodStats().addStats(4, 0.4f);
			
			setDead();
		}
		
		return true;
	}

	@Override
	public boolean isPassive() 
	{
		return true;
	}

	@Override
	public boolean getCanSpawnHere() 
	{
		return true;
	}
}
