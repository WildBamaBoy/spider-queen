package sq.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import sq.entity.creature.EntityWebShot;
import sq.enums.EnumWebType;

public class ItemWeb extends Item
{
	private EnumWebType webType;
	
	public ItemWeb(EnumWebType type)
	{
		super();
		
		final String name = "web-" + type.getName();
		setWebType(type);
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
	
	private void setWebType(EnumWebType type)
	{
		webType = type;
	}
	
	public EnumWebType getWebType()
	{
		return webType;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundAtEntity(entityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if (!world.isRemote)
		{
			if (!entityPlayer.capabilities.isCreativeMode)
			{
				itemStack.stackSize--;
			}

			final EntityWebShot web = new EntityWebShot(entityPlayer, webType);
			world.spawnEntityInWorld(web);
		}
		
		return itemStack;
	}
}
