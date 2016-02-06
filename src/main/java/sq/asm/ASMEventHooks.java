package sq.asm;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import radixcore.util.BlockHelper;
import sq.blocks.BlockJack;
import sq.core.SpiderCore;
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
	public static void onPumpkinGenerate(World world, Random random, BlockPos position)
	{
		if (random.nextInt(6) == 0)
		{
			world.setBlockState(position, ModBlocks.jack.getDefaultState().withProperty(BlockJack.FACING, EnumFacing.Plane.HORIZONTAL.random(random)), 2);
		}
	} 

	/**
	 * Make spiders always hostile to other players, even during the day.
	 */
	public static Entity onSpiderFindPlayerToAttack(EntitySpider spider)
	{
		return spider.worldObj.getClosestPlayerToEntity(spider, 16.0D);
	}

	/**
	 * Makes players have spider walking sounds.
	 */
	public static void onPlayStepSound(Entity entity, int posX, int posY, int posZ, Block block)
	{
		Block.SoundType soundtype = block.stepSound;

		if (SpiderCore.getConfig().useSpiderQueenModel && entity instanceof EntityPlayer && !block.getMaterial().isLiquid())
		{
			entity.playSound("mob.spider.step", soundtype.getVolume() * 0.15F, soundtype.getFrequency());
		}
		
		else if (BlockHelper.getBlock(entity.worldObj, posX, posY + 1, posZ) == Blocks.snow_layer)
		{
			soundtype = Blocks.snow_layer.stepSound;
			entity.playSound(soundtype.getStepSound(), soundtype.getVolume() * 0.15F, soundtype.getFrequency());
		}
		
		else if (!block.getMaterial().isLiquid())
		{
			entity.playSound(soundtype.getStepSound(), soundtype.getVolume() * 0.15F, soundtype.getFrequency());
		}
	}
}
