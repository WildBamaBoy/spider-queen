/*******************************************************************************
 * PlayerReputationHandler.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import com.radixshock.radixcore.logic.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerReputationHandler implements IExtendedEntityProperties
{
	public static final String ID = "SpiderQueenReputationHandler";
	private final EntityPlayer player;

	public int reputationCreepers;
	public int reputationHumans;
	public int reputationSkeletons;
	public int reputationZombies;
	public int reputationFriendlySpiderQueens;
	public int reputationEvilSpiderQueen;

	public int creepersKilled;
	public int humansKilled;
	public int skeletonsKilled;
	public int zombiesKilled;
	public int friendlySpidersKilled;
	public int spidersKilled;

	public boolean isAtWarWithCreepers;
	public boolean isAtWarWithHumans;
	public boolean isAtWarWithSkeletons;
	public boolean isAtWarWithZombies;
	public boolean isAtWarWithFriendlySpiderQueens;
	public boolean isAtWarWithEvilSpiderQueen;

	public PlayerReputationHandler(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt) 
	{
		NBTHelper.autoWriteClassFieldsToNBT(this.getClass(), this, nbt);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		NBTHelper.autoReadClassFieldsFromNBT(this.getClass(), this, nbt);
	}

	@Override
	public void init(Entity entity, World world) 
	{
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(PlayerReputationHandler.ID, new PlayerReputationHandler(player));
	}
}
