package sq.enums;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import sq.core.minecraft.ModBlocks;

/**
 * A enum set that maps a placeholder block to a different one.
 */
public enum EnumPlaceholderBlock
{
	SPONGE (Blocks.sponge, Blocks.mob_spawner, true),
	BEACON (Blocks.beacon, Blocks.air, true),
	BEDROCK (Blocks.bedrock, Blocks.air, true),
	NETHER_QUARTZ (Blocks.quartz_ore, Blocks.air, true);
	
	private Block originalBlock;
	private Block yieldBlock;
	
	/** True if we need to perform additional operations after replacing the block. */
	private boolean doCallback;
	
	EnumPlaceholderBlock(Block original, Block yield, boolean doCallback)
	{
		this.originalBlock = original;
		this.yieldBlock = yield;
		this.doCallback = doCallback;
	}

	public static boolean isPlaceholder(Block block) 
	{
		for (EnumPlaceholderBlock b : values())
		{
			if (b.originalBlock == block)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static EnumPlaceholderBlock getByBlock(Block block)
	{
		for (EnumPlaceholderBlock b : values())
		{
			if (b.originalBlock == block)
			{
				return b;
			}
		}
		
		return null;
	}
	
	public Block getYield()
	{
		return yieldBlock;
	}
	
	public boolean getDoCallback()
	{
		return doCallback;
	}
}
