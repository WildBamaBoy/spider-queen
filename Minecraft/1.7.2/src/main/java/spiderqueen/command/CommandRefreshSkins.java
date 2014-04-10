package spiderqueen.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import spiderqueen.core.SpiderQueen;

import com.radixshock.radixcore.constant.Font.Color;

public class CommandRefreshSkins extends CommandBase
{
	@Override
	public String getCommandUsage(ICommandSender sender) 
	{
		return "/sq.refreshskins";
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
		return "sq.refreshskins";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments) 
	{
		try
		{
			sender.addChatMessage(new ChatComponentText(Color.YELLOW + "Redownloading skins from server..."));
			
			final int previousListSize = SpiderQueen.getInstance().fakePlayerNames.size();
			final List<String> newFakePlayerNames = SpiderQueen.getInstance().downloadFakePlayerNames();
			final int newListSize = newFakePlayerNames.size();
			
			if (newFakePlayerNames.size() == 0)
			{
				throw new Exception("Returned list contained no names. Ensure you are connected to the internet.");
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
		
		catch (Throwable e)
		{
			sender.addChatMessage(new ChatComponentText(Color.RED + "An error occurred: " + e.getMessage()));
		}
	}
}
