package sq.enums;

public enum EnumOfferingType
{
	BRAIN,
	HEART,
	SKULL;
	
	public String getName()
	{
		return name().toLowerCase();
	}
}
