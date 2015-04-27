package sqr.item;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sqr.entity.EntityCocoon;
import sqr.enums.EnumTypeVariant;

public class ItemCocoon extends Item
{
	public ItemCocoon(EnumTypeVariant cocoonType)
	{
		super();
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (entityplayer.inventory.consumeInventoryItem(itemstack.getItem()))
		{
			//world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
			{
				final EntityCocoon entCocoon = new EntityCocoon(world);
				entCocoon.posX = entityplayer.posX;
				entCocoon.posY = entityplayer.posY;
				entCocoon.posZ = entityplayer.posZ;
				entCocoon.setPosition(entCocoon.posX, entCocoon.posY, entCocoon.posZ);

				world.spawnEntityInWorld(entCocoon);
			}
		}

		return itemstack;
	}
}
