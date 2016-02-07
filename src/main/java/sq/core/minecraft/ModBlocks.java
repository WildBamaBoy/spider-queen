package sq.core.minecraft;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
import sq.core.SpiderCore;
import sq.enums.EnumWebType;

/**
 * All blocks in Spider Queen.
 */
public final class ModBlocks
{
	private static ModBlocks instance;
	
	public BlockAntHill antHill;
	public BlockBeeHive beeHive;
	public BlockJack jack;
	public BlockWebFull poisonWebFull;
	public BlockWebSide poisonWebSide;
	public BlockWebGround poisonWebGround;
	public BlockSpiderRod spiderRod;
	public BlockStinger stinger;
	public BlockWebBed webBed;
	public BlockWebFull webFull;
	public BlockWebSide webSide;
	public BlockWebGround webGround;
	public BlockMandCrop cropMand;
	public BlockLantern lantern;
	
	public static ModBlocks getInstance()
	{
		if (instance == null)
		{
			instance = new ModBlocks(); 
		}
		
		return instance;
	}
	
	private ModBlocks()
	{
		instance = this;
		
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
			return SpiderCore.getBlocks().poisonWebGround;
		}
		
		else if (web instanceof BlockWebSide)
		{
			return SpiderCore.getBlocks().poisonWebSide;
		}
		
		else if (web instanceof BlockWebFull)
		{
			return SpiderCore.getBlocks().poisonWebFull;
		}
		
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModelMeshers()
	{
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

		for (Field f : ModBlocks.class.getFields())
		{
			try
			{
				Item item = Item.getItemFromBlock((Block) f.get(instance));
				String name = item.getUnlocalizedName().substring(5);
				mesher.register(item, 0, new ModelResourceLocation("sq:" + name, "inventory"));
			}

			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
