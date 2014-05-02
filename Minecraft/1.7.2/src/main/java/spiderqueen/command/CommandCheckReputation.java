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
		player.addChatMessage(new ChatComponentText(Color.YELLOW + "-----------------------------"));
		player.addChatMessage(new ChatComponentText(Color.WHITE + "Creepers: " + reputationHandler.reputationCreepers));
		player.addChatMessage(new ChatComponentText(Color.WHITE + "Humans: " + reputationHandler.reputationHumans));
		player.addChatMessage(new ChatComponentText(Color.WHITE + "Skeletons: " + reputationHandler.reputationSkeletons));
		player.addChatMessage(new ChatComponentText(Color.WHITE + "Zombies: " + reputationHandler.reputationZombies));
		player.addChatMessage(new ChatComponentText(Color.WHITE + "Other Queens: " + reputationHandler.reputationFriendlySpiderQueens));
		player.addChatMessage(new ChatComponentText(Color.WHITE + "Evil Queen: " + reputationHandler.reputationEvilSpiderQueen));
	}
}
