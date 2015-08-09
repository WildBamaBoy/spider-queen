package sq.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import sq.entity.creature.EntitySpiderEgg;

public class ItemSpiderEgg extends Item
{
	public ItemSpiderEgg()
	{
		super();
		
		final String name = "spider-egg";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(8);
		
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

			final EntitySpiderEgg entitySpiderEgg = new EntitySpiderEgg(world, player.getUniqueID());
			entitySpiderEgg.setLocationAndAngles(posX, posY, posZ, world.rand.nextFloat() * 360F, 0.0F);
			world.spawnEntityInWorld(entitySpiderEgg);

			return true;
		}
		
		return true;
	}
}
