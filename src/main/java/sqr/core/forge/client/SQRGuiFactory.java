package sqr.core.forge.client;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import sqr.client.gui.GuiConfigPage;
import cpw.mods.fml.client.IModGuiFactory;

public class SQRGuiFactory implements IModGuiFactory
{
	@Override
	public void initialize(Minecraft minecraftInstance) 
	{
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() 
	{
		return GuiConfigPage.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() 
	{
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) 
	{
		return null;
	}
}
