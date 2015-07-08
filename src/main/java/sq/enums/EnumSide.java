package sq.enums;

/**
 * Turns Minecraft's side metadata into named numerical enums.
 */
public enum EnumSide 
{
	BOTTOM(0),
	TOP(1),
	EAST(2),
	WEST(3),
	NORTH(4),
	SOUTH(5);
	
	private int id;
	
	EnumSide(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public static EnumSide byId(int id)
	{
		for (EnumSide type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}
		
		return null;
	}
}
