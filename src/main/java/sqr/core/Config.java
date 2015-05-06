package sqr.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public final class Config 
{
	private final Configuration config;

	public int baseItemId;
	public int baseBlockId;
	public int baseEntityId;
	
	public boolean allowCrashReporting;
	public boolean allowUpdateChecking;
	
	public Config(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		addConfigValues();
	}

	public void addConfigValues()
	{
		config.setCategoryComment("Init", "Settings that affect how SQR starts up.");
		baseItemId = config.get("Init", "Base Item ID", 18892, "The base ID to use for items in SQR. Only applicable in 1.6.4.").getInt();
		baseBlockId = config.get("Init", "Base Block ID", 4130, "The base ID to use for blocks in SQR. Only applicable in 1.6.4.").getInt();
		baseEntityId = config.get("Init", "Base Entity ID", 633, "The base ID to use for entities in SQR. Only change if you know what you are doing!").getInt();

		config.setCategoryComment("Privacy", "Setting pertaining to your privacy while using SQR.");
		allowCrashReporting = config.get("Privacy", "Allow crash reporting", true, "True if SQR can send crash reports to the mod authors. Crash reports may include your Minecraft username, OS version, Java version, and PC username.").getBoolean();
		allowUpdateChecking = config.get("Privacy", "Allow update checking", true, "True if SQR can check for updates.").getBoolean();
		
		config.setCategoryComment("General", "General mod settings.");
		
		config.save();
	}
	
	public void syncConfiguration()
	{
		config.load();
		addConfigValues();
		config.save();
	}
	
	public Configuration getConfigInstance()
	{
		return config;
	}

	public List<IConfigElement> getConfigCategories()
	{
		List<IConfigElement> elements = new ArrayList<IConfigElement>();

		for (String s : config.getCategoryNames())
		{
			if (!s.equals("server"))
			{	
				IConfigElement element = new ConfigElement(config.getCategory(s));
				for (IConfigElement e : (List<IConfigElement>)element.getChildElements())
				{
					elements.add(e);
				}
			}
		}

		return elements;
	}
}
