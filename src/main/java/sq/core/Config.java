package sq.core;

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
	
	public boolean enableYuki;
	public boolean enableJack;
	public boolean enableMandragora;
	public boolean enableFly;
	public boolean enableBeetle;
	public boolean enableAnt;
	public boolean enableFactories;
	
	public int antSpawnCap;
	public int beeSpawnCap;
	
	public Config(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		addConfigValues();
	}

	public void addConfigValues()
	{
		config.setCategoryComment("Init", "Settings that affect how Spider Queen starts up.");
		baseItemId = config.get("Init", "Base Item ID", 38955, "The base ID to use for items in Spider Queen. Only applicable in 1.6.4.").getInt();
		baseBlockId = config.get("Init", "Base Block ID", 3121, "The base ID to use for blocks in Spider Queen. Only applicable in 1.6.4.").getInt();
		baseEntityId = config.get("Init", "Base Entity ID", 127, "The base ID to use for entities in Spider Queen. Only change if you know what you are doing!").getInt();
		enableYuki = config.get("Init", "Enable Yuki?", true, "Enables/disables Yuki.").getBoolean();
		enableJack = config.get("Init", "Enable Jack?", true, "Enables/disables Jack. WARNING: You will not be able to acquire the bug light!").getBoolean();
		enableMandragora = config.get("Init", "Enable Mandragora?", true, "Enables/disables the mandragora. WARNING: Removes friendly mandragoras as well!").getBoolean();
		enableFly = config.get("Init", "Enable Flies?", true, "Enables/disables flies.").getBoolean();
		enableBeetle = config.get("Init", "Enable Beetles?", true, "Enables/disables beetles.").getBoolean();
		enableAnt = config.get("Init", "Enable Ants?", true, "Enables/disables ants.").getBoolean();
		enableFactories = config.get("Init", "Enable Factories?", true, "Enables/disables NPC factories.").getBoolean();
		
		config.setCategoryComment("Performance", "Settings that can affect your game performance.");
		antSpawnCap = config.get("Performance", "Ant spawn cap", 10, "The maximum number of ants that can spawn within a 16 block radius.").getInt();
		beeSpawnCap = config.get("Performance", "Bee spawn cap", 10, "The maximum number of bees that can spawn within a 16 block radius.").getInt();
		
		config.setCategoryComment("Privacy", "Setting pertaining to your privacy while using Spider Queen.");
		allowCrashReporting = config.get("Privacy", "Allow crash reporting", true, "True if Spider Queen can send crash reports to the mod authors. Crash reports may include your Minecraft username, OS version, Java version, and PC username.").getBoolean();
		allowUpdateChecking = config.get("Privacy", "Allow update checking", true, "True if Spider Queen can check for updates. This setting requires a restart in order to take effect.").getBoolean();
		
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
