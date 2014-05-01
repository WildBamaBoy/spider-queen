/*******************************************************************************
 * GuiInventory.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.enums.EnumPacketType;
import spiderqueen.inventory.ContainerSpiderInventory;

import com.radixshock.radixcore.network.Packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Defines the inventory GUI shown when changing an entity's inventory.
 */
@SideOnly(Side.CLIENT)
public class GuiSpiderInventory extends InventoryEffectRenderer
{	
	private static final ResourceLocation resourceLocation = new ResourceLocation("textures/gui/container/generic_54.png");
	
	private EntityHatchedSpider owner;
	private GuiButton exitButton;

	/** The number of rows in the inventory. */
	private int inventoryRows;

	/**
	 * Constructor
	 * 
	 * @param 	entity			The entity who owns the inventory being accessed.
	 * @param 	playerInventory	The inventory of the player opening this GUI.
	 * @param 	entityInventory	The inventory of the entity that the player is interacting with.
	 * @param	fromEditor		Is this GUI being opened from the villager editor?
	 */
	public GuiSpiderInventory(EntityHatchedSpider entity, IInventory playerInventory, IInventory entityInventory, boolean fromEditor)
	{
		super(new ContainerSpiderInventory(playerInventory, entityInventory, entity));

		owner = entity;
		allowUserInput = false;

		char c = '\336';
		int i = c - 108;		
		inventoryRows = entityInventory.getSizeInventory() / 9;
		xSize = xSize + 24;
		ySize = i + inventoryRows * 18;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		buttonList.clear();
		buttonList.add(exitButton = new GuiButton(11, width / 2 + 125, height / 2 + 85, 65, 20, "Exit"));
	}

	@Override
	protected void actionPerformed(GuiButton guibutton)
	{
		if (guibutton == exitButton)
		{
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		fontRendererObj.drawString("Carrier Spider", 8, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float offset, int mouseX, int mouseY)
	{
		this.mc.getTextureManager().bindTexture(resourceLocation);

		int addX = Minecraft.getMinecraft().thePlayer.getActivePotionEffects().size() > 0 ? 120 : 0;

		//Draw the two inventories.
		int x = (width - xSize + addX) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, inventoryRows * 18 + 17);			//Top inventory
		drawTexturedModalRect(x, y + inventoryRows * 18 + 17, 0, 126, xSize, 96);	//Bottom inventory
	}

	@Override
	public void onGuiClosed() 
	{
		super.onGuiClosed();
		owner.inventory.closeInventory();
		SpiderQueen.packetPipeline.sendPacketToServer(new Packet(EnumPacketType.SetInventory, owner.getEntityId(), owner.inventory));
	}
}
