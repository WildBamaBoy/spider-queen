package sq.enums;

public enum EnumAttackBallType 
{
	BOOM (0),
	JACK (1),
	OCTO (2);
	
	int id;
	
	EnumAttackBallType(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public static EnumAttackBallType getById(int id)
	{
		for (EnumAttackBallType type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}
		
		return null;
	}
}
