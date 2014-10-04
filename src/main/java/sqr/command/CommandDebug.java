/*******************************************************************************
 * CommandDebug.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import sqr.core.SpiderQueen;
import sqr.entity.EntityHatchedSpider;

import com.radixshock.radixcore.constant.Font.Color;
import com.radixshock.radixcore.logic.LogicHelper;

/**
 * Defines the debug rule command and what it does.
 */
public class CommandDebug extends CommandBase
{
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/sq.debug <rulename> <true/false>";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return sender.getCommandSenderName().equals("LuvTrumpetStyle") || sender.getCommandSenderName().equals("WildBamaBoy") ||
				sender.getCommandSenderName().contains("Player");
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 4;
	}

	@Override
	public String getCommandName()
	{
		return "sq.debug";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments)
	{
		if (arguments.length == 2 || arguments.length == 1 && (arguments[0].equalsIgnoreCase("on") || arguments[0].equalsIgnoreCase("off")))
		{
			if (arguments[0].equalsIgnoreCase("on"))
			{
				SpiderQueen.getInstance().inDebugMode = true;
				sender.addChatMessage(new ChatComponentText(Color.YELLOW + "Spider Queen debug mode is on."));
				return;
			}

			else if (arguments[0].equalsIgnoreCase("off"))
			{
				SpiderQueen.getInstance().inDebugMode = false;
				sender.addChatMessage(new ChatComponentText(Color.YELLOW + "Spider Queen debug mode is off."));
				return;
			}

			else
			{
				final boolean setValue = arguments[1].equalsIgnoreCase("true");

				if (arguments[0].equalsIgnoreCase("dorapidspidergrowth"))
				{
					SpiderQueen.getInstance().debugDoRapidSpiderGrowth = setValue;
					sender.addChatMessage(new ChatComponentText(Color.YELLOW + "Rule doRapidSpiderGrowth set to " + setValue + "."));
				}

				else if (arguments[0].equalsIgnoreCase("levelupallspiders"))
				{
					try
					{
						final EntityPlayer player = (EntityPlayer) sender;

						for (final EntityHatchedSpider spider : (List<EntityHatchedSpider>) LogicHelper.getAllEntitiesOfTypeWithinDistanceOfEntity(player, EntityHatchedSpider.class, 20))
						{
							if (spider.getLevel() < 3)
							{
								spider.tryLevelUp(true);
							}
						}
					}

					catch (final Throwable e)
					{
						e.printStackTrace();
					}
				}
				
				else if (arguments[0].equalsIgnoreCase("haltspawnplayers"))
				{
					SpiderQueen.getInstance().debugHaltSpawnPlayers = setValue;
					sender.addChatMessage(new ChatComponentText(Color.YELLOW + "Rule haltSpawnPlayers set to " + setValue + "."));
				}

				else
				{
					sender.addChatMessage(new ChatComponentText(Color.RED + "Unrecognized debug rule."));
				}
			}
		}

		else
		{
			throw new WrongUsageException(getCommandUsage(sender));
		}
	}
}
