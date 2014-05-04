/*******************************************************************************
 * ClientTickHandler.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

import com.radixshock.radixcore.logic.LogicHelper;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

/**
 * Handles ticks client-side.
 */
public class ClientTickHandler
{
	private boolean hasCompletedMainMenuTick;

	/**
	 * Runs this tick handler if appropriate.
	 */
	public void onTick()
	{
		final GuiScreen guiScreen = Minecraft.getMinecraft().currentScreen;

		if (guiScreen != null)
		{
			onTickInGui(guiScreen);
		}
	}

	/**
	 * Fires once per tick when a GUI screen is open.
	 * 
	 * @param 	guiScreen	The GUI that is currently open.
	 */
	public void onTickInGui(GuiScreen guiScreen)
	{
		if (guiScreen instanceof GuiMainMenu)
		{
			if (LogicHelper.getBooleanWithProbability(10) && !hasCompletedMainMenuTick)
			{
				ObfuscationReflectionHelper.setPrivateValue(GuiMainMenu.class, (GuiMainMenu)guiScreen, "Does whatever a spider queen does!", 4);
			}
			
			hasCompletedMainMenuTick = true;
		}

		else if (guiScreen instanceof GuiIngameMenu)
		{
			hasCompletedMainMenuTick = false;
		}
	}
}
