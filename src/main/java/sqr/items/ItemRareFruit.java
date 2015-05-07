package sqr.items;

import net.minecraft.item.ItemFood;
import sqr.core.SQR;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRareFruit extends ItemFood
{
	public ItemRareFruit()
	{
		super(3, 1.5F, false);
		setAlwaysEdible();
		
		final String name = "rare-fruit";
		setUnlocalizedName(name);
		setTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
}
