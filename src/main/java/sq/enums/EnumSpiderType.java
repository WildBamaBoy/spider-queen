package sq.enums;

import radixcore.util.RadixString;

/**
 * Defines the possible spider types and ties them to an ID.
 */
public enum EnumSpiderType
{
	NONE (-1),
	WIMPY (0),
	NORMAL (1),
	BOOM (2),
	SLINGER (3),
	NOVA (4),
	TANK (5),
	ENDER (6),
	PACK (7),
	RIDER (8);
	
	private int id;
	
	EnumSpiderType(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public static EnumSpiderType byId(int id)
	{
		for (EnumSpiderType type : values())
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
