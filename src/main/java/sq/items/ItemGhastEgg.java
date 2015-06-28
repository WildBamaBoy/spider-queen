package sq.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import sq.entity.EntityGhastEgg;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemGhastEgg extends Item
{
	public ItemGhastEgg()
	{
		super();
		
		final String name = "ghast-egg";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(1);
		
		GameRegistry.registerItem(this, name);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (!world.isRemote)
		{
			if (!player.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
			posX += Facing.offsetsXForSide[meta];
			posY += Facing.offsetsYForSide[meta];
			posZ += Facing.offsetsZForSide[meta];

			final EntityGhastEgg entityGhastEgg = new EntityGhastEgg(world, player.getUniqueID());
			entityGhastEgg.setLocationAndAngles(posX, posY, posZ, world.rand.nextFloat() * 360F, 0.0F);
			world.spawnEntityInWorld(entityGhastEgg);

			return true;
		}
		
		return true;
	}
}
