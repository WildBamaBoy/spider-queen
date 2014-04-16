package spiderqueen.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;
import spiderqueen.core.SpiderQueen;

import com.radixshock.radixcore.constant.Font.Color;

public class CommandPlayerSkins extends CommandBase
{
	@Override
	public String getCommandUsage(ICommandSender sender) 
	{
		return "/sq.playerskins <refresh/enable/disable>";
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
		return "sq.playerskins";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments) 
	{
		if (arguments.length == 1)
		{
			final String argument = arguments[0];

			if (argument.equals("refresh"))
			{
				sender.addChatMessage(new ChatComponentText(Color.YELLOW + "Redownloading skins from server..."));

				final int previousListSize = SpiderQueen.getInstance().fakePlayerNames.size();
				final List<String> newFakePlayerNames = SpiderQueen.getInstance().downloadFakePlayerNames();
				final int newListSize = newFakePlayerNames.size();

				if (newFakePlayerNames.size() == 0)
				{
					sender.addChatMessage(new ChatComponentText(Color.RED + "Returned list contained no names. Ensure you are connected to the internet."));
				}

				else
				{
					if (newListSize > previousListSize)
					{
						SpiderQueen.getInstance().fakePlayerNames = newFakePlayerNames;
						sender.addChatMessage(new ChatComponentText(Color.GREEN + "Added " + (newListSize - previousListSize) + " new skins."));
					}

					else
					{
						sender.addChatMessage(new ChatComponentText(Color.RED + "No new skins need to be added."));					
					}
				}
			}

			else if (argument.equals("disable"))
			{
				SpiderQueen.getInstance().doDisplayPlayerSkins = false;
				sender.addChatMessage(new ChatComponentText(Color.YELLOW + "Player skins have been disabled."));
			}

			else if (argument.equals("enable"))
			{
				SpiderQueen.getInstance().doDisplayPlayerSkins = true;
				sender.addChatMessage(new ChatComponentText(Color.YELLOW + "Player skins have been enabled."));
			}

			else
			{
				throw new WrongUsageException(getCommandUsage(sender));
			}
		}

		else
		{
			throw new WrongUsageException(getCommandUsage(sender));
		}
	}
}
