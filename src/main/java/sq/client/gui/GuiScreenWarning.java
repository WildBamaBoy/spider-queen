package sq.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import radixcore.constant.Font.Color;
import radixcore.constant.Font.Format;
import sq.core.SpiderCore;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiScreenWarning extends GuiScreen
{
	private GuiButton buttonContinue;
	@Override
	public void initGui() 
	{
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTickTime) 
	{
		super.drawScreen(width, height, partialTickTime);
		
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, "[" + Color.PURPLE + "Spider Queen" + Format.RESET + "]", width / 2, height / 2 - 90, 0xFFFFFF);
		drawCenteredString(fontRendererObj, "Spider Queen failed to make a few changes to Minecraft.", width / 2, height / 2 - 75, 0xFFFFFF);
		drawCenteredString(fontRendererObj, "You can continue playing, however some parts of the mod may not", width / 2, height / 2 - 55, 0xFFFFFF);
		drawCenteredString(fontRendererObj, "work properly or at all. Try using the version of Forge recommended", width / 2, height / 2 - 45, 0xFFFFFF);
		drawCenteredString(fontRendererObj, "for use with Spider Queen, and also use the standard Minecraft launcher.", width / 2, height / 2 - 35, 0xFFFFFF);
		
		drawCenteredString(fontRendererObj, Color.YELLOW + "Please report the following to the mod authors:", width / 2, height / 2 - 15, 0xFFFFFF);
		
		drawCenteredString(fontRendererObj, "Spider Queen " + SpiderCore.VERSION, width / 2, height / 2 - 5, 0xFFFFFF);
		
		int i = 5;
		
		for (String s : FMLCommonHandler.instance().getBrandings(true))
		{
			drawCenteredString(fontRendererObj, s, width / 2, height / 2 + i, 0xFFFFFF);
			i += 10;
		}
		
		for (String s : SpiderCore.asmErrors)
		{
			drawCenteredString(fontRendererObj, Color.RED + "ERROR: " + s, width / 2, height / 2 + i + 10, 0xFFFFFF);
			i += 10;
		}
		
		drawCenteredString(fontRendererObj, Format.BOLD + Format.UNDERLINE + "Press ESC on your keyboard to close this screen.", width / 2, height / 2 + 95, 0xFFFFFF);
	}
	
	@Override
	protected void actionPerformed(GuiButton guiButton) 
	{
		super.actionPerformed(guiButton);
		
		if (guiButton == buttonContinue)
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
		}
	}
	
	@Override
	protected void keyTyped(char c, int i)
	{
		if (i == Keyboard.KEY_ESCAPE)
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
		}
	}
}
