package sq.items;

import net.minecraft.item.ItemFood;
import sq.core.SQ;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRareFruit extends ItemFood
{
	public ItemRareFruit()
	{
		super(3, 1.5F, false);
		setAlwaysEdible();
		
		final String name = "rare-fruit";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SQ.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
}
