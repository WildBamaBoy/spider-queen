package spiderqueen.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import spiderqueen.core.Constants;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.enums.EnumCocoonType;

public class ItemCocoon extends Item
{
	private EnumCocoonType cocoonType;
	
	public ItemCocoon(EnumCocoonType cocoonType)
	{
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
		this.cocoonType = cocoonType;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (!world.isRemote)
		{
			EntityCocoon entityCocoon = new EntityCocoon(world, cocoonType);
			posY += 4;
			entityCocoon.setPositionAndRotation(posX, posY, posZ, entityCocoon.rotationYaw - 90F, 0F);
			world.spawnEntityInWorld(entityCocoon);
		}
		return true;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:Cocoon" + cocoonType.toString());
	}
}
