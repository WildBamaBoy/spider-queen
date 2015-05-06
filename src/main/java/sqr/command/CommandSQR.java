package sqr.command;

import java.util.Arrays;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import radixcore.constant.Font.Color;
import radixcore.constant.Font.Format;

public final class CommandSQR extends CommandBase
{
	@Override
	public String getCommandName() 
	{
		return "sqr";
	}

	@Override
	public String getCommandUsage(ICommandSender commandSender) 
	{
		return "/sqr <subcommand> <arguments>";
	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] input) 
	{
		try
		{
			final EntityPlayer player = (EntityPlayer)commandSender;
			String subcommand = input[0];
			String[] arguments = (String[]) Arrays.copyOfRange(input, 1, input.length);

			if (subcommand.equalsIgnoreCase("help"))
			{
				displayHelp(commandSender);
			}
		}
		
		catch (Exception e)
		{
			throw new WrongUsageException("An invalid argument was provided. Usage: " + getCommandUsage(commandSender));
		}
	}

	@Override
	public int getRequiredPermissionLevel() 
	{
		return 0;
	}

	private void addChatMessage(ICommandSender commandSender, String message)
	{
		commandSender.addChatMessage(new ChatComponentText(Color.GOLD + "[SQR] " + Format.RESET + message));
	}

	private void addChatMessage(ICommandSender commandSender, String message, boolean noPrefix)
	{
		if (noPrefix)
		{
			commandSender.addChatMessage(new ChatComponentText(message));			
		}

		else
		{
			addChatMessage(commandSender, message);
		}
	}

	private void displayHelp(ICommandSender commandSender)
	{
		addChatMessage(commandSender, Color.DARKRED + "--- " + Color.GOLD + "DEBUG COMMANDS" + Color.DARKRED + " ---", true);
		addChatMessage(commandSender, Color.WHITE + " /sqr [cmd] " + Color.GOLD + " - [desc].", true);

		addChatMessage(commandSender, Color.DARKRED + "--- " + Color.GOLD + "OP COMMANDS" + Color.DARKRED + " ---", true);
		addChatMessage(commandSender, Color.WHITE + " /sqr [cmd] " + Color.GOLD + " - [desc].", true);

		addChatMessage(commandSender, Color.DARKRED + "--- " + Color.GOLD + "GLOBAL COMMANDS" + Color.DARKRED + " ---", true);
		addChatMessage(commandSender, Color.WHITE + " /sqr help " + Color.GOLD + " - Shows this list of commands.", true);
	}
}
