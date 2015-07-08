package sq.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import radixcore.util.RadixLogic;
import sq.core.SpiderCore;
import sq.core.radix.PlayerData;
import sq.entity.IRep;

/**
 * An entity AI that causes creatures using the new AI system 
 * to attack the player when they are unliked.
 */
public class AIAttackPlayerOnUnlike extends EntityAITarget
{
	private final int targetChance;
	private EntityLivingBase targetEntity;

	public AIAttackPlayerOnUnlike(EntityCreature owner)
	{
		super(owner, true, true);
		this.targetChance = 80;
		this.setMutexBits(1);
	}

	public boolean shouldExecute()
	{
		//Search for players.
		List<Entity> list = RadixLogic.getAllEntitiesOfTypeWithinDistance(EntityPlayerMP.class, taskOwner, 8);

		if (list.isEmpty())
		{
			return false;
		}

		else
		{
			//Get the first player in the list and set it as the target.
			this.targetEntity = (EntityLivingBase)list.get(0);

			try
			{
				//Check to make sure that the targeted player is unliked. If they're liked, this cannot execute.
				IRep executor = (IRep)this.taskOwner;
				PlayerData data = SpiderCore.getPlayerData((EntityPlayer)targetEntity);

				if (executor.getLikeData(data).getInt() > 0)
				{
					return false;
				}
			}

			catch (Exception e) //Mod compatibility for those who extend EntityPlayer on their mobs.
			{
				return false;
			}

			return true;
		}
	}

	public void startExecuting()
	{
		this.taskOwner.setAttackTarget(this.targetEntity);
		super.startExecuting();
	}
}