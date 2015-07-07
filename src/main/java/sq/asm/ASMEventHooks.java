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

/***
 * This class contains the method calls that Spider Queen injects into various parts of Minecraft.
 */
public final class ASMEventHooks 
{
	/**
	 * When the player eats, drop 1 - 3 string at their location.
	 */
	public static void onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			player.triggerAchievement(ModAchievements.eatToGetString);
			player.entityDropItem(new ItemStack(Items.string, world.rand.nextInt(3) + 1), 1.0F);
		}
	}

	/**
	 * When pumpkin patches generate, randomly replace the pumpkin with the Jack block.
	 */
	public static void onPumpkinGenerate(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(6) == 0)
		{
			world.setBlock(x, y, z, ModBlocks.jack, random.nextInt(4), 4);
		}
	} 
	
	/**
	 * Make spiders always hostile to other players, even during the day.
	 */
	public static Entity onSpiderFindPlayerToAttack(EntitySpider spider)
	{
		return spider.worldObj.getClosestVulnerablePlayerToEntity(spider, 16.0D);
	}
}
