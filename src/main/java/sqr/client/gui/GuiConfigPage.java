package sqr.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import sqr.core.SQR;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class GuiConfigPage extends GuiConfig
{
	public GuiConfigPage(GuiScreen parent) 
	{
        this(parent, 
        		SQR.getConfig().getConfigCategories(),
                SQR.ID, false, false, GuiConfig.getAbridgedConfigPath(SQR.getConfig().getConfigInstance().toString()));
    }
	
	public GuiConfigPage(GuiScreen parentScreen, List<IConfigElement> configElements, String modID, boolean allRequireWorldRestart, boolean allRequireMcRestart, String title) 
	{
		super(parentScreen, configElements, modID, allRequireWorldRestart, allRequireMcRestart, title);
	}
}
