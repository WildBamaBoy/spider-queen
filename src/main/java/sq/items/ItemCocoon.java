package sq.items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import radixcore.constant.Font.Color;
import sq.client.items.MeshCocoon;
import sq.core.SpiderCore;
import sq.core.minecraft.SpiderItems;
import sq.entities.EntityCocoon;
import sq.enums.EnumCocoonType;
import sq.enums.EnumSpiderType;

/**
 * Defines the base cocoon item, which places an entity version of its accompanying type into the world 
 * when the player right-clicks a block.
 */
public class ItemCocoon extends Item implements ISubtypeModelProvider
{
	public ItemCocoon()
	{
		// Base name of a cocoon item. Suffix will be added when getUnlocalizedName() is called.
		final String name = SpiderCore.ID + ".cocoon.";
		
		setUnlocalizedName(name);
		setHasSubtypes(true);
	}

	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
		// Add the appropriate suffix based on our metadata
        return super.getUnlocalizedName() + EnumCocoonType.getCocoonType(stack.getMetadata()).getName();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> list)
    {
    	for (EnumCocoonType type : EnumCocoonType.values())
    	{
    		ItemStack subItemStack = new ItemStack(this, 1, type.getId());
            list.add(subItemStack);
    	}
    }

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if (!world.isRemote)
		{
			if (!player.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}

			final EnumCocoonType cocoonType = EnumCocoonType.getCocoonType(stack.getMetadata());
			final EntityCocoon entityCocoon = new EntityCocoon(world, cocoonType);
			entityCocoon.setPositionAndRotation(pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F, player.rotationYaw * -1, 0F);
			world.spawnEntityInWorld(entityCocoon);

			return EnumActionResult.SUCCESS;
		}
		
		return EnumActionResult.FAIL;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		EnumCocoonType cocoonType = EnumCocoonType.getCocoonType(itemStack.getMetadata());
		
		if (cocoonType.getSpiderTypeYield() != EnumSpiderType.NORMAL)
		{
			if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			{
				list.add("Press SHIFT for info.");
			}

			else
			{
				list.add("Produces the " + cocoonType.getSpiderTypeYield().getFriendlyName() + " Spider.");
				list.add("");
				list.add(Color.BLUE + "Abilities:");

				for (String s : cocoonType.getAbilities())
				{
					list.add(s);
				}

				list.add(Color.RED + "Caveats:");

				for (String s : cocoonType.getCaveats())
				{
					list.add(s);
				}
				
				list.add(Color.GREEN + "Levels up:");
				
				for (String s : cocoonType.getLevelUpConditions())
				{
					list.add(s);
				}
			}
		}
	}

	@Override
	public ItemMeshDefinition getMeshDefinition() 
	{
		return new MeshCocoon("cocoon");
	}

	@Override
	public List<String> getVariantNames() 
	{
		List<String> returnList = new ArrayList<String>();
        
		for (EnumCocoonType type : EnumCocoonType.values())
        {
        	returnList.add("type=" + type.getName());
        }

        return returnList;
	}

	@Override
	public ResourceLocation getResourceLocation() 
	{
		return null;
	}

	public EnumCocoonType getCocoonType(ItemStack stack)
	{
		return EnumCocoonType.getCocoonType(stack.getMetadata());
	}
	
	public static ItemStack getCocoonItemStack(EnumCocoonType type)
	{
		return new ItemStack(SpiderItems.COCOON, 1, type.getId());
	}
}