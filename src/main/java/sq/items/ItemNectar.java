package sq.items;

import net.minecraft.item.ItemFood;
import sq.core.SQ;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemNectar extends ItemFood
{
	public ItemNectar()
	{
		super(3, 0.4F, false);
		setAlwaysEdible();
		
		final String name = "nectar";
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SQ.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
}
