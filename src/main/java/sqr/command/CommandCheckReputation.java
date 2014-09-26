/*******************************************************************************
 * CommandCheckReputation.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import sqr.core.forge.PlayerExtension;
import sqr.core.util.CreatureReputationEntry;

import com.radixshock.radixcore.constant.Font.Color;

public class CommandCheckReputation extends CommandBase
{
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/sq.checkrep";
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
		return "sq.checkrep";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments)
	{
		final EntityPlayer player = (EntityPlayer) sender;
		final PlayerExtension playerExtension = PlayerExtension.get(player);
		new StringBuilder();

		player.addChatMessage(new ChatComponentText(Color.YELLOW + "Spider Queen Reputations"));
		player.addChatMessage(new ChatComponentText(Color.WHITE + "-----------------------------"));

		for (final CreatureReputationEntry entry : playerExtension.getReputationEntries())
		{
			if (entry.isAtWar)
			{
				player.addChatMessage(new ChatComponentText(Color.WHITE + entry.creatureGroupName + ": " + Color.RED + entry.reputationValue + " (WAR)"));
			}

			else if (entry.reputationValue < 0)
			{
				player.addChatMessage(new ChatComponentText(Color.WHITE + entry.creatureGroupName + ": " + Color.RED + entry.reputationValue));
			}

			else if (entry.reputationValue >= 0 && entry.reputationValue < 3)
			{
				player.addChatMessage(new ChatComponentText(Color.WHITE + entry.creatureGroupName + ": " + Color.GREEN + entry.reputationValue));
			}

			else
			{
				player.addChatMessage(new ChatComponentText(Color.WHITE + entry.creatureGroupName + ": " + Color.GREEN + entry.reputationValue + " (FRIEND)"));
			}
		}

		player.addChatMessage(new ChatComponentText(Color.WHITE + "-----------------------------"));
	}
}
