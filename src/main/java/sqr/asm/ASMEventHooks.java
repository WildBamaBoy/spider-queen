package sqr.asm;

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
			player.dropItem(Items.string, world.rand.nextInt(3) + 1);
		}
	}
}
