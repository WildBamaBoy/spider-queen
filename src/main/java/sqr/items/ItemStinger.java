package sqr.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.core.SQR;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemStinger extends Item
{
	public ItemStinger()
	{
		super();
		
		final String name = "stinger";
		setUnlocalizedName(name);
		setTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (!world.isRemote && world.isAirBlock(posX, posY + 1, posZ))
		{
			if (!player.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
			//TODO Place stinger at location y + 1.
		}
		
		return super.onItemUse(stack, player, world, posX, posY, posZ, meta, xOffset, yOffset, zOffset);
	}
}
