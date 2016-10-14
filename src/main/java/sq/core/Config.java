package sq.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public final class Config implements Serializable
{
	private transient final Configuration config;
	
	public boolean allowUpdateChecking;
	
	public Config(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		addConfigValues();
	}

	private void addConfigValues()
	{
		config.setCategoryComment("Init", "Settings that affect how MCA starts up.");
		config.save();
	}

	public void syncConfiguration()
	{
		config.load();
		addConfigValues();
		config.save();
	}

	public Configuration getInstance()
	{
		return config;
	}

	public List<IConfigElement> getCategories()
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
