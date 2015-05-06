package sqr.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.core.SQR;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemMandSeeds extends Item
{
	public ItemMandSeeds()
	{
		super();
		
		final String name = "mandragora-seeds";
		setUnlocalizedName(name);
		setTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
		setMaxStackSize(64);
		
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (!world.isRemote && world.isAirBlock(posX, posY + 1, posZ) && world.getBlock(posX, posY, posZ) == Blocks.farmland)
		{
			if (!player.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
			//TODO Place mand crop at location y + 1.
		}
		
		return super.onItemUse(stack, player, world, posX, posY, posZ, meta, xOffset, yOffset, zOffset);
	}
}
