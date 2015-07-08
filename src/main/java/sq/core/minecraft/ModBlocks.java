package sq.core.minecraft;

import net.minecraft.block.Block;
import sq.blocks.BlockAntHill;
import sq.blocks.BlockBeeHive;
import sq.blocks.BlockJack;
import sq.blocks.BlockLantern;
import sq.blocks.BlockMandCrop;
import sq.blocks.BlockSpiderRod;
import sq.blocks.BlockStinger;
import sq.blocks.BlockWebBed;
import sq.blocks.BlockWebFull;
import sq.blocks.BlockWebGround;
import sq.blocks.BlockWebSide;
import sq.enums.EnumWebType;

/**
 * All blocks in Spider Queen.
 */
public final class ModBlocks
{
	public static BlockAntHill antHill;
	public static BlockBeeHive beeHive;
	public static BlockJack jack;
	public static BlockWebFull poisonWebFull;
	public static BlockWebSide poisonWebSide;
	public static BlockWebGround poisonWebGround;
	public static BlockSpiderRod spiderRod;
	public static BlockStinger stinger;
	public static BlockWebBed webBed;
	public static BlockWebFull webFull;
	public static BlockWebSide webSide;
	public static BlockWebGround webGround;
	public static BlockMandCrop cropMand;
	public static BlockLantern lantern;
	
	public ModBlocks()
	{
		antHill = new BlockAntHill();
		beeHive = new BlockBeeHive();
		jack = new BlockJack();
		poisonWebFull = new BlockWebFull(EnumWebType.POISON);
		poisonWebSide = new BlockWebSide(EnumWebType.POISON);
		poisonWebGround = new BlockWebGround(EnumWebType.POISON);
		webBed = new BlockWebBed();
		spiderRod = new BlockSpiderRod();
		stinger = new BlockStinger();
		webFull = new BlockWebFull(EnumWebType.NORMAL);
		webSide = new BlockWebSide(EnumWebType.NORMAL);
		webGround = new BlockWebGround(EnumWebType.NORMAL);
		cropMand = new BlockMandCrop();
		lantern = new BlockLantern();
	}
	
	public static Block getPoisonWebVariant(Block web)
	{	
		if (web instanceof BlockWebGround)
		{
			return poisonWebGround;
		}
		
		else if (web instanceof BlockWebSide)
		{
			return poisonWebSide;
		}
		
		else if (web instanceof BlockWebFull)
		{
			return poisonWebFull;
		}
		
		return null;
	}
}
