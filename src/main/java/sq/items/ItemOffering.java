package sq.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import sq.core.SQ;
import sq.enums.EnumOfferingType;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemOffering extends Item
{
	private EnumOfferingType offeringType;
	
	public ItemOffering(EnumOfferingType type)
	{
		super();
		
		final String name = type.getName();
		setOfferingType(type);
		setUnlocalizedName(name);
		setTextureName("sq:" + name);
		setCreativeTab(SQ.getCreativeTab());
		
		GameRegistry.registerItem(this, name);
	}
	
	private void setOfferingType(EnumOfferingType type)
	{
		offeringType = type;
	}
	
	private EnumOfferingType getOfferingType()
	{
		return offeringType;
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) 
	{
		//TODO Implement offerings.
		return false;
	}
}
