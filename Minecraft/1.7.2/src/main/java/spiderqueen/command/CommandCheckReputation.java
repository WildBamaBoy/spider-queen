/*******************************************************************************
 * CommandCheckReputation.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import spiderqueen.core.forge.PlayerReputationHandler;

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
		final EntityPlayer player = (EntityPlayer)sender;
		final PlayerReputationHandler reputationHandler = (PlayerReputationHandler) player.getExtendedProperties(PlayerReputationHandler.ID);
		final StringBuilder SB = new StringBuilder();
		
		player.addChatMessage(new ChatComponentText(Color.YELLOW + "Spider Queen Reputations"));
		player.addChatMessage(new ChatComponentText(Color.WHITE + "-----------------------------"));
		
		if (reputationHandler.isAtWarWithCreepers)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Creepers: " + Color.RED + reputationHandler.reputationCreepers + "(WAR)"));
		}
		
		else if (reputationHandler.reputationCreepers < 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Creepers: " + Color.RED + reputationHandler.reputationCreepers));
		}
		
		else if (reputationHandler.reputationCreepers >= 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Creepers: " + Color.GREEN + reputationHandler.reputationCreepers));
		}
		
		if (reputationHandler.isAtWarWithHumans)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Humans: " + Color.RED + reputationHandler.reputationHumans + "(WAR)"));
		}
		
		else if (reputationHandler.reputationHumans < 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Humans: " + Color.RED + reputationHandler.reputationHumans));
		}
		
		else if (reputationHandler.reputationHumans >= 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Humans: " + Color.GREEN + reputationHandler.reputationHumans));
		}
		
		if (reputationHandler.isAtWarWithSkeletons)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Skeletons: " + Color.RED + reputationHandler.reputationSkeletons + "(WAR)"));
		}
		
		else if (reputationHandler.reputationSkeletons < 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Skeletons: " + Color.RED + reputationHandler.reputationSkeletons));
		}
		
		else if (reputationHandler.reputationSkeletons >= 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Skeletons: " + Color.GREEN + reputationHandler.reputationSkeletons));
		}
		
		if (reputationHandler.isAtWarWithZombies)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Zombies: " + Color.RED + reputationHandler.reputationZombies + "(WAR)"));
		}
		
		else if (reputationHandler.reputationZombies < 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Zombies: " + Color.RED + reputationHandler.reputationZombies));
		}
		
		else if (reputationHandler.reputationZombies >= 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Zombies: " + Color.GREEN + reputationHandler.reputationZombies));
		}
		
		if (reputationHandler.isAtWarWithFriendlySpiderQueens)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Other Queens: " + Color.RED + reputationHandler.reputationFriendlySpiderQueens + "(WAR)"));
		}
		
		else if (reputationHandler.reputationFriendlySpiderQueens < 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Other Queens: " + Color.RED + reputationHandler.reputationFriendlySpiderQueens));
		}
		
		else if (reputationHandler.reputationFriendlySpiderQueens >= 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Other Queens: " + Color.GREEN + reputationHandler.reputationFriendlySpiderQueens));
		}
		
		if (reputationHandler.isAtWarWithEvilSpiderQueen)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Evil Queen: " + Color.RED + reputationHandler.reputationEvilSpiderQueen + "(WAR)"));
		}
		
		else if (reputationHandler.reputationEvilSpiderQueen < 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Evil Queen: " + Color.RED + reputationHandler.reputationEvilSpiderQueen));
		}
		
		else if (reputationHandler.reputationEvilSpiderQueen >= 0)
		{
			player.addChatMessage(new ChatComponentText(Color.WHITE + "Evil Queen: " + Color.GREEN + reputationHandler.reputationEvilSpiderQueen));
		}
		
		player.addChatMessage(new ChatComponentText(Color.WHITE + "-----------------------------"));
	}
}
