package sq.asm;

import radixcore.data.WatchedInt;
import sq.core.SpiderCore;
import sq.core.radix.PlayerData;
import sq.entity.IRep;
import sq.entity.ai.RepEntityExtension;
import sq.entity.ai.ReputationContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class ASMEventHooks 
{
	public static void onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			player.entityDropItem(new ItemStack(Items.string, world.rand.nextInt(3) + 1), 1.0F);
		}
	}

	public static Entity onSpiderFindPlayerToAttack(EntitySpider spider)
	{
		int reputation = -1;
		
		if (spider.getClass().equals(EntitySpider.class))
		{
			EntityPlayer player = spider.worldObj.getClosestVulnerablePlayerToEntity(spider, 16.0D);
			if (player != null)
			{
				PlayerData data = SpiderCore.getPlayerData(player);
				RepEntityExtension extension = (RepEntityExtension) spider.getExtendedProperties(RepEntityExtension.ID);
				WatchedInt watchedLikeInstance = ReputationContainer.getLikeDataByClass(spider.getClass(), data);

				if (watchedLikeInstance != null)
				{
					reputation = watchedLikeInstance.getInt();
				}

				if (reputation >= 0)
				{
					if (extension != null && extension.getTimesHitByPlayer() >= 3)
					{
						return player;
					}

					return null;
				}
			}
		}
		
		//Default code for a spider finding a player to attack.
		float f = spider.getBrightness(1.0F);

		if (f < 0.5F || reputation < 0)
		{
			double d0 = 16.0D;
			return spider.worldObj.getClosestVulnerablePlayerToEntity(spider, d0);
		}

		else
		{
			return null;
		}
	}
}
