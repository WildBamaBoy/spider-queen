package sq.asm;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sq.core.minecraft.ModAchievements;
import sq.core.minecraft.ModBlocks;

public final class ASMEventHooks 
{
	public static void onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			player.triggerAchievement(ModAchievements.eatToGetString);
			player.entityDropItem(new ItemStack(Items.string, world.rand.nextInt(3) + 1), 1.0F);
		}
	}

	public static void onPumpkinGenerate(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(6) == 0)
		{
			world.setBlock(x, y, z, ModBlocks.jack, random.nextInt(4), 4);
		}
	} 
	
	public static Entity onSpiderFindPlayerToAttack(EntitySpider spider)
	{
		//Make spiders ALWAYS hostile to vulnerable players.
		return spider.worldObj.getClosestVulnerablePlayerToEntity(spider, 16.0D);
	}
}
