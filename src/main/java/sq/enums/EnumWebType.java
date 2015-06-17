package sq.enums;

public enum EnumWebType
{
	NORMAL (0),
	POISON (1);
	
	private int id;
	
	EnumWebType(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name().toLowerCase();
	}
	
	public int getId()
	{
		return id;
	}
	
	public static EnumWebType byId(int id)
	{
		for (EnumWebType type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}
		
		return null;
	}
}
