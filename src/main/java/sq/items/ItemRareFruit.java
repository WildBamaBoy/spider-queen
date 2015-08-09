package sq.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;
import sq.core.SpiderCore;

public class ItemRareFruit extends ItemFood
{
	public ItemRareFruit()
	{
		super(3, 1.5F, false);
		setAlwaysEdible();
		
		final String name = "rare-fruit";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
}
