package sq.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import sq.core.SQ;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemSpiderEgg extends Item
{
	public ItemSpiderEgg()
	{
		super();
		
		final String name = "spider-egg";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SQ.getCreativeTab());
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
