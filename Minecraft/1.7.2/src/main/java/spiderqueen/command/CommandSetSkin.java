/*******************************************************************************
 * CommandPlayerSkins.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.forge.PlayerExtension;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.constant.Font.Color;
import com.radixshock.radixcore.network.Packet;

public class CommandSetSkin extends CommandBase
{
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/sq.setskin <skinname>";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 4;
	}

	@Override
	public String getCommandName()
	{
		return "sq.setskin";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments)
	{
		if (arguments.length == 1)
		{
			final String argument = arguments[0];
			final EntityPlayer player = (EntityPlayer)sender;
			final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);
			
			if (arguments.equals("reset"))
			{
				playerExtension.selectedSkin = "";
			}
			
			else
			{
				playerExtension.selectedSkin = argument;
				SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetSkin, argument));
			}
		}

		else
		{
			throw new WrongUsageException(getCommandUsage(sender));
		}
	}
}
