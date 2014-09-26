/*******************************************************************************
 * GuiHandler.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sqr.client.gui.GuiSleep;
import sqr.client.gui.GuiSpiderInventory;
import sqr.core.Constants;
import sqr.entity.EntityHatchedSpider;
import sqr.inventory.ContainerSpiderInventory;

import com.radixshock.radixcore.logic.LogicHelper;

import cpw.mods.fml.common.network.IGuiHandler;

/**
 * Handles the inventory GUI.
 */
public class GuiHandler implements IGuiHandler
{
	/**
	 * Constructor
	 * 
	 * @param entityId
	 *            The ID of the entity whose inventory is being edited.
	 */
	public GuiHandler()
	{
	}

	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int posX, int posY, int posZ)
	{
		final EntityHatchedSpider entity = (EntityHatchedSpider) LogicHelper.getEntityOfTypeAtXYZ(EntityHatchedSpider.class, world, posX, posY, posZ);

		if (guiId == Constants.ID_GUI_INVENTORY)
		{
			return new ContainerSpiderInventory(player.inventory, entity.inventory, entity);
		}

		else
		{
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int posX, int posY, int posZ)
	{
		final EntityHatchedSpider entity = (EntityHatchedSpider) LogicHelper.getEntityOfTypeAtXYZ(EntityHatchedSpider.class, world, posX, posY, posZ);

		if (entity != null)
		{
			if (guiId == Constants.ID_GUI_INVENTORY)
			{
				return new GuiSpiderInventory(entity, player.inventory, entity.inventory, false);
			}

			else if (guiId == Constants.ID_GUI_SLEEP)
			{
				return new GuiSleep(player);
			}
		}
		
		return null;
	}
}
