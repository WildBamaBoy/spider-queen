package sqr.items;

import net.minecraft.item.Item;
import sqr.core.SQR;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemWebslinger extends Item
{
	public ItemWebslinger()
	{
		super();
		
		final String name = "webslinger";
		setUnlocalizedName(name);
		setTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
		setMaxStackSize(1);
		
		GameRegistry.registerItem(this, name);
	}
}
