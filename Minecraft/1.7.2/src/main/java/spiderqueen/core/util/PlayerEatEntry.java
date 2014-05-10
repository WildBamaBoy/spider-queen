/*******************************************************************************
 * PlayerEatEntry.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.util;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerEatEntry
{
	private final EntityPlayer player;
	private final int slot;
	private final int count;

	public PlayerEatEntry(EntityPlayer player, int slot, int count)
	{
		this.player = player;
		this.slot = slot;
		this.count = count;
	}

	public EntityPlayer getPlayer()
	{
		return player;
	}

	public int getSlot()
	{
		return slot;
	}

	public int getCount()
	{
		return count;
	}
}
