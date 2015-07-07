package sq.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import sq.core.SpiderCore;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

/**
 * Provides Spider Queen's configuration page.
 */
public final class GuiConfigPage extends GuiConfig
{
	public GuiConfigPage(GuiScreen parent) 
	{
        this(parent, 
        		SpiderCore.getConfig().getConfigCategories(),
                SpiderCore.ID, false, false, GuiConfig.getAbridgedConfigPath(SpiderCore.getConfig().getConfigInstance().toString()));
    }
	
	public GuiConfigPage(GuiScreen parentScreen, List<IConfigElement> configElements, String modID, boolean allRequireWorldRestart, boolean allRequireMcRestart, String title) 
	{
		super(parentScreen, configElements, modID, allRequireWorldRestart, allRequireMcRestart, title);
	}
}
