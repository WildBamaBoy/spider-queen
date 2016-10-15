package sq.core.minecraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sq.core.SpiderCore;
import sq.items.ItemCocoon;
import sq.items.ItemWebShot;

public class SpiderItems 
{
	public static final Item COCOON;

//	public static final Item EGG_ANT;
//	public static final Item EGG_BEE;
//	public static final Item EGG_BEETLE;
//	public static final Item EGG_FLY;
//	public static final Item EGG_JACK;
//	public static final Item EGG_MANDRAGORA;
//	public static final Item EGG_OCTOPUS;
//	public static final Item EGG_SPIDER_QUEEN;
//	public static final Item EGG_WASP;
//	public static final Item EGG_YUKI;
//	public static final Item EGG_FRIENDLY_CREEPER;
//	public static final Item EGG_FRIENDLY_SKELETON;
//	public static final Item EGG_FRIENDLY_ZOMBIE;
//	public static final Item EGG_HUMAN;
//	
//	public static final Item GHAST_EGG;
//	public static final Item SPIDER_EGG;
	public static final Item WEB;
//	public static final Item WEB_POISON;
//	public static final Item RARE_FRUIT;
//	public static final Item SKULL;
//	public static final Item HEART;
//	public static final Item BRAIN;
//	public static final Item SPIDER_ROD;
//	public static final Item RECALL_ROD;
//	public static final Item FREEZE_ROD;
//	public static final Item WEBSLINGER;
//	public static final Item NECTAR;
//	public static final Item MANDRAGORA_SEEDS;
//	public static final Item ROYAL_BLOOD;
//	public static final Item LANTERN;

	private SpiderItems(){}
	
	public static void registerItems()
	{
		//Only purpose is to cause a call the static constructor.
	}
	
	static
	{
		COCOON = register(new ItemCocoon(), "cocoon");
		CreativeTabs tab = new CreativeTabs(SpiderCore.ID)
		{
			@Override
			public Item getTabIconItem() {
				return new ItemStack(COCOON).getItem();
			}
		};
		COCOON.setCreativeTab(tab);
		SpiderCore.setCreativeTab(tab);
		
		WEB = register(new ItemWebShot(), "webshot");
	}
	
	private static Item register(Item item, String name)
	{
		if (item.getRegistryName() == null)
		{
			item.setRegistryName(name);
		}
		
		GameRegistry.register(item);
		SpiderCore.proxy.registerItemRenderer(item, name);
		
		return item;
	}
}
