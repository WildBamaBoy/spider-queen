/*******************************************************************************
 * GuiHandlerInventory.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import spiderqueen.client.gui.GuiSpiderInventory;
import spiderqueen.core.Constants;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.inventory.ContainerSpiderInventory;

import com.radixshock.radixcore.logic.LogicHelper;

import cpw.mods.fml.common.network.IGuiHandler;

/**
 * Handles the inventory GUI.
 */
public class GuiHandlerInventory implements IGuiHandler
{	
	/**
	 * Constructor
	 * 
	 * @param 	entityId	The ID of the entity whose inventory is being edited.
	 */
	public GuiHandlerInventory()
	{
	}
	
	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int posX, int posY, int posZ) 
	{
		final EntityHatchedSpider entity = (EntityHatchedSpider)LogicHelper.getEntityOfTypeAtXYZ(EntityHatchedSpider.class, world, posX, posY, posZ);
		
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
		final EntityHatchedSpider entity = (EntityHatchedSpider)LogicHelper.getEntityOfTypeAtXYZ(EntityHatchedSpider.class, world, posX, posY, posZ);
		
		if (guiId == Constants.ID_GUI_INVENTORY)
		{
			return new GuiSpiderInventory(entity, player.inventory, entity.inventory, false);
		}
		
		else
		{
			return null;
		}
	}
}
