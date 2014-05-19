/*******************************************************************************
 * AbstractItemSpawner.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.forge.PlayerExtension;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.network.Packet;

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
			final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);

			try
			{
				boolean isQueen = playerExtension.selectedSkin.contains("Queen");
				int index = Integer.parseInt(playerExtension.selectedSkin.substring(playerExtension.selectedSkin.length() - 1));

				if (isQueen)
				{
					if (index < 4)
					{
						index++;
						playerExtension.selectedSkin = "SpiderQueen" + index;
					}

					else
					{
						index = 1;
						playerExtension.selectedSkin = "SpiderKing" + index;
					}
				}

				else
				{
					playerExtension.selectedSkin = "SpiderQueen1";
				}
			}

			catch (Throwable e)
			{
				playerExtension.selectedSkin = "SpiderQueen1";			
			}
			
			SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetSkin, playerExtension.selectedSkin));					
			player.addChatMessage(new ChatComponentText("Skin changed to " + playerExtension.selectedSkin + "."));
		}

		return itemStack;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("spiderqueen:SkinSwitcher");
	}
}
