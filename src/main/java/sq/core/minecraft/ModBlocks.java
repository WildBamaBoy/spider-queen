package sq.core.minecraft;

import sq.blocks.BlockAntHill;
import sq.blocks.BlockBeeHive;
import sq.blocks.BlockJack;
import sq.blocks.BlockMandCrop;
import sq.blocks.BlockSpiderRod;
import sq.blocks.BlockStinger;
import sq.blocks.BlockWeb;
import sq.enums.EnumWebType;

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
