package sq.items;

import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sq.core.SpiderCore;

public class ItemRareFruit extends ItemFood
{
	public ItemRareFruit()
	{
		super(3, 1.5F, false);
		setAlwaysEdible();
		
		final String name = "rare-fruit";
		setUnlocalizedName(name);
		setCreativeTab(SpiderCore.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
}
