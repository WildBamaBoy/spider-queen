package spiderqueen.core.util;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerEatEntry 
{
	private EntityPlayer player;
	private int slot;
	private int count;
	
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
