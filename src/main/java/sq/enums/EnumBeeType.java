package sq.enums;

import radixcore.util.RadixString;

/**
 * Defines a bee's type by tying it to a numerical ID.
 */
public enum EnumBeeType 
{
	WARRIOR (0),
	GATHERER (1),
	QUEEN (2);

	int id;

	EnumBeeType(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public String getFriendlyName()
	{
		return RadixString.upperFirstLetter(name().toLowerCase());
	}
	
	public static EnumBeeType getById(int id)
	{
		for (EnumBeeType type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}

		return null;
	}
}
