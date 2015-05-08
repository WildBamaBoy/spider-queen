package sqr.entity;

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
