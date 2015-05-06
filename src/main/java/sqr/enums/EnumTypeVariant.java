package sqr.enums;

import java.lang.reflect.Field;

import net.minecraft.item.Item;
import radixcore.util.RadixString;
import sqr.core.SQR;
import sqr.core.minecraft.ModItems;

public enum EnumTypeVariant
{
	EMPTY (-1),
	ANT (0), 
	COW (1), 
	CREEPER (2), 
	GATHERERBEE (3), 
	HUMAN (4), 
	PIG (5), 
	QUEENBEE (6),
	SHEEP (7), 
	SKELETON (8), 
	WARRIORBEE (9), 
	WASP (10), 
	WOLF (11), 
	ZOMBIE (12), 
	ENDERMAN (13, EnumCocoonSize.TALL), 
	BLAZE (14), 
	CHICKEN (15, EnumCocoonSize.SMALL), 
	VILLAGER (16, EnumCocoonSize.TALL), 
	HORSE (17), 
	GHAST (18, EnumCocoonSize.HUGE), 
	_ENDERMINION (19);
	
	int id;
	EnumCocoonSize size;
	
	EnumTypeVariant(int id)
	{
		this.id = id;
		this.size = EnumCocoonSize.NORMAL;
	}
	
	EnumTypeVariant(int id, EnumCocoonSize size)
	{
		this(id);
		this.size = size;
	}
	
	public Item getCocoon()
	{
		Item item = null;
		
		//Lookup the field that would contain this type.
		for (Field f : ModItems.class.getFields())
		{
			try
			{
				if (f.getName().equals("cocoon" + this.toString()))
				{
					item = (Item) f.get(null);
				}
			}
			
			catch (Exception e)
			{
				SQR.getLog().error("Unable to lookup item containing type variant for " + toString() + ".");
			}
		}
		
		if (item == null)
		{
			item = ModItems.cocoonEmpty;
		}
		
		return item;
	}
	
	public EnumCocoonSize getCocoonSize()
	{
		return size;
	}
	
	/**
	 * @return	The defined name of this enum with the first letter capitalized and all others lower-case.
	 */
	@Override
	public String toString()
	{
		return RadixString.upperFirstLetter(this.name().toLowerCase());
	}
	
	public int getId()
	{
		return id;
	}

	public static EnumTypeVariant getByName(String name)
	{
		for (EnumTypeVariant e : EnumTypeVariant.values())
		{
			if (name.toLowerCase().equals(e.name().toLowerCase()))
			{
				return e;
			}
		}
		
		return null;
	}
	
	public static EnumTypeVariant getById(int id)
	{
		for (EnumTypeVariant e : EnumTypeVariant.values())
		{
			if (id == e.id)
			{
				return e;
			}
		}
		
		return null;		
	}
}
