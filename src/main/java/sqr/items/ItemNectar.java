package sqr.items;

import net.minecraft.item.ItemFood;
import sqr.core.SQR;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemNectar extends ItemFood
{
	public ItemNectar()
	{
		super(3, 0.4F, false);
		setAlwaysEdible();
		
		final String name = "nectar";
		setUnlocalizedName(name);
		setTextureName("sqr:" + name);
		setCreativeTab(SQR.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
}
