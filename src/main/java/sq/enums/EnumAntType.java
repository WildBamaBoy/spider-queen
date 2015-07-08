package sq.enums;

import radixcore.util.RadixString;

/**
 * Defines the types of ants that exist.
 */
public enum EnumAntType 
{
	BLACK (0),
	RED (1);
	
	int id;
	
	EnumAntType(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public static EnumAntType getById(int id)
	{
		for (EnumAntType type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}
		
		return null;
	}
	
	public String getFriendlyName()
	{
		return RadixString.upperFirstLetter(name().toLowerCase());
	}
}
