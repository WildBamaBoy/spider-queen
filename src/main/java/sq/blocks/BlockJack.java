package sq.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;
import sq.entity.creature.EntityJack;

/**
 * The Jack block (Jack o'Lantern) will spawn the Jack creature randomly at night.
 */
public class BlockJack extends Block
{
	private static IIcon faceIcon;
	private static IIcon sideIcon;
	private static IIcon topIcon;
	
	public BlockJack() 
	{
		super(Material.gourd);
		
		final String name = "jack";
		setBlockName(name);
		setBlockTextureName("sq:" + name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setTickRandomly(true);
		setHardness(3.0F);
		
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) 
    {
		//We don't care about timing here, let the world tick this block at random.
		super.updateTick(world, x, y, z, random);
		
		if (!world.isDaytime())
		{
			//When we tick at night, delete this block.
			world.setBlockToAir(x, y, z);
			
			//Spawn Jack at this location, as he "emerges" from the Jack o'Lantern block.
			EntityJack jack = new EntityJack(world);
			jack.setPosition(x, y + 1, z);
			world.spawnEntityInWorld(jack);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) 
	{
		//This is from BlockPumpkin. It assigns the correct block icon based on metadata and the side of the block we're working with.
        return side == 1 ? BlockJack.topIcon : (side == 0 ? BlockJack.topIcon : (meta == 2 && side == 2 ? BlockJack.faceIcon : (meta == 3 && side == 5 ? BlockJack.faceIcon : (meta == 0 && side == 3 ? BlockJack.faceIcon : (meta == 1 && side == 4 ? BlockJack.faceIcon : BlockJack.sideIcon)))));
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{
		faceIcon = iconRegister.registerIcon("sq:jack-face");
		sideIcon = iconRegister.registerIcon("sq:jack-side");
		topIcon = iconRegister.registerIcon("sq:jack-top");
	}
	
    @Override
	public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase entityLiving, ItemStack itemStack)
    {
    	//Calculate our meta relative to the player's rotation when the block was placed.
        int meta = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
        BlockHelper.setBlockMetadataWithNotify(world, posX, posY, posZ, meta, 2);
    }
}
