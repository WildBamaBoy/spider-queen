package sq.items;

import net.minecraft.item.Item;
import sq.core.SQ;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemWebslinger extends Item
{
	public ItemWebslinger()
	{
		super();
		
		final String name = "webslinger";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SQ.getCreativeTab());
		setMaxStackSize(1);
		
		GameRegistry.registerItem(this, name);
	}
}
