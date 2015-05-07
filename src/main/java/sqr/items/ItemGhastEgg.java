package sqr.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import sqr.core.SQR;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemGhastEgg extends Item
{
	public ItemGhastEgg()
	{
		super();
		
		final String name = "ghast-egg";
		setUnlocalizedName(name);
		setTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
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

			//TODO
//			final EntityCocoon entityCocoon = new EntityCocoon(world, cocoonType);
//			entityCocoon.setPositionAndRotation(posX + 0.5F, posY + 1, posZ + 0.5F, player.rotationYaw * -1, 0F);
//			world.spawnEntityInWorld(entityCocoon);
		}
		
		return true;
	}
}
