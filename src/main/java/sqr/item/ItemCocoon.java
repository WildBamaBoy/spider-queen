package sqr.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import sqr.core.SQR;
import sqr.entity.EntityCocoon;
import sqr.enums.EnumTypeVariant;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemCocoon extends Item
{
	private EnumTypeVariant type;
	
	public ItemCocoon(EnumTypeVariant type)
	{
		String name = "Cocoon" + type.toString();
		
		this.maxStackSize = 1;
		this.type = type;
		this.setMaxDamage(0);
		this.setUnlocalizedName(name);
		this.setTextureName("sqr:" + name);
		this.setCreativeTab(SQR.getCreativeTab());
		
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
			
			final EntityCocoon cocoon = new EntityCocoon(world, type);
			cocoon.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw * -1, 0.0F);
			world.spawnEntityInWorld(cocoon);
		}
		
		return true;
	}
	
	@Override
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon("spiderqueen:Cocoon" + type.toString());
	}
}
