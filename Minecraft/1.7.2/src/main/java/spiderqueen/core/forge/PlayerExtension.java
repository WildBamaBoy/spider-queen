/*******************************************************************************
 * PlayerExtension.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import spiderqueen.core.SpiderQueen;
import spiderqueen.core.util.CreatureReputationEntry;
import spiderqueen.entity.EntityWebslinger;
import spiderqueen.enums.EnumPacketType;

import com.radixshock.radixcore.logic.NBTHelper;
import com.radixshock.radixcore.network.Packet;

public class PlayerExtension implements IExtendedEntityProperties
{
	public static final String					ID	= "SpiderQueenplayerExtension";
	public EntityWebslinger						webEntity;
	public int									totalHumansKilled;
	public String								selectedSkin = "SpiderQueen1";
	private final EntityPlayer					player;
	private final List<CreatureReputationEntry>	reputationEntries;

	public PlayerExtension(EntityPlayer player)
	{
		this.player = player;
		reputationEntries = CreatureReputationEntry.getListOfCleanEntries();
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
		for (final CreatureReputationEntry entry : reputationEntries)
		{
			NBTHelper.autoWriteClassFieldsToNBT(entry.getClass(), entry, nbt, entry.creatureGroupName);
		}

		nbt.setInteger("totalHumansKilled", totalHumansKilled);
		nbt.setString("selectedSkin", selectedSkin);
		
		SpiderQueen.packetPipeline.sendPacketToAllPlayers(new Packet(EnumPacketType.SetSkin, selectedSkin, player.getCommandSenderName()));
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		for (final CreatureReputationEntry entry : reputationEntries)
		{
			NBTHelper.autoReadClassFieldsFromNBT(entry.getClass(), entry, nbt, entry.creatureGroupName);
		}

		totalHumansKilled = nbt.getInteger("totalHumansKilled");
		selectedSkin = nbt.getString("selectedSkin");
	}

	@Override
	public void init(Entity entity, World world)
	{
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(PlayerExtension.ID, new PlayerExtension(player));
	}

	public final List<CreatureReputationEntry> getReputationEntries()
	{
		return reputationEntries;
	}

	public final CreatureReputationEntry getReputationEntry(Class clazz)
	{
		for (final CreatureReputationEntry entry : reputationEntries)
		{
			if (entry.getCreatureClass().toString().equals(clazz.toString())) { return entry; }
		}

		return null;
	}

	public EntityPlayer getPlayer()
	{
		return player;
	}
	
	public static PlayerExtension get(EntityPlayer player)
	{
		return (PlayerExtension) player.getExtendedProperties(ID);
	}
}
