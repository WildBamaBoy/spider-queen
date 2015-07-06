package sq.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import radixcore.constant.Particle;
import sq.core.SpiderCore;
import sq.entity.throwable.EntityFreezeBall;
import sq.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemFreezeRod extends Item
{
	public ItemFreezeRod()
	{
		super();
		
		final String name = "freeze-rod";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(1);
		setMaxDamage(32);

		GameRegistry.registerItem(this, name);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		if (!world.isRemote)
		{
			EntityFreezeBall freezeBall = new EntityFreezeBall(world, player);
			world.spawnEntityInWorld(freezeBall);

			Utils.spawnParticlesAroundEntityS(Particle.SNOWBALL, player, 32);
		}
		
		stack.setItemDamage(stack.getItemDamage() + 1);
		
		if (stack.getItemDamage() >= 32)
		{
			stack.stackSize = 0;
		}
		
		world.playSoundAtEntity(player, "sq:freeze.rod", 1.0F, 1.0F);
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean unknown) 
	{
		list.add("Freezes creatures.");
		list.add("Extinguishes fire.");
		list.add("Turns water to ice.");
		
		super.addInformation(stack, player, list, unknown);
	}
}
