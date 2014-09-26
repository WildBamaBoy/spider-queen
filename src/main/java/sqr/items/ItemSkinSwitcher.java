/*******************************************************************************
 * ItemSkinSwitcher.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import sqr.core.ModPropertiesList;
import sqr.core.SpiderQueen;
import sqr.network.packets.PacketSetSkin;

public class ItemSkinSwitcher extends Item
{
	public ItemSkinSwitcher()
	{
		super();
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			final ModPropertiesList modPropertiesList = SpiderQueen.getInstance().getModProperties();

			try
			{
				boolean isQueen = modPropertiesList.spiderSkin.contains("Queen");
				int index = Integer.parseInt(modPropertiesList.spiderSkin.substring(modPropertiesList.spiderSkin.length() - 1));

				if (isQueen)
				{
					if (index < 4)
					{
						index++;
						modPropertiesList.spiderSkin = "SpiderQueen" + index;
					}

					else
					{
						index = 1;
						modPropertiesList.spiderSkin = "SpiderKing" + index;
					}
				}

				else
				{
					modPropertiesList.spiderSkin = "SpiderQueen1";
				}
			}

			catch (Throwable e)
			{
				modPropertiesList.spiderSkin = "SpiderQueen1";			
			}
			
			SpiderQueen.packetHandler.sendPacketToAllPlayers(new PacketSetSkin(modPropertiesList.spiderSkin, player.getCommandSenderName()));					
			player.addChatMessage(new ChatComponentText("Skin changed to " + modPropertiesList.spiderSkin + "."));
		}

		return itemStack;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:SkinSwitcher");
	}
}
