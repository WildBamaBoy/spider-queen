package sqr.core.minecraft;

import sqr.blocks.BlockAntHill;
import sqr.blocks.BlockBeeHive;
import sqr.blocks.BlockJack;
import sqr.blocks.BlockMandCrop;
import sqr.blocks.BlockSpiderRod;
import sqr.blocks.BlockStinger;
import sqr.blocks.BlockWeb;
import sqr.enums.EnumWebType;

public final class ModBlocks
{
	public static BlockAntHill antHill;
	public static BlockBeeHive beeHive;
	public static BlockJack jack;
	public static BlockWeb poisonWeb;
	public static BlockSpiderRod spiderRod;
	public static BlockStinger stinger;
	public static BlockWeb web;
	public static BlockMandCrop cropMand;
	
	public ModBlocks()
	{
		antHill = new BlockAntHill();
		beeHive = new BlockBeeHive();
		jack = new BlockJack();
		poisonWeb = new BlockWeb(EnumWebType.POISON);
		spiderRod = new BlockSpiderRod();
		stinger = new BlockStinger();
		web = new BlockWeb(EnumWebType.NORMAL);
		cropMand = new BlockMandCrop();
	}
}
