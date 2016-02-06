package sq.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import radixcore.constant.Font.Color;
import sq.core.SpiderCore;
import sq.entity.creature.EntityCocoon;
import sq.enums.EnumCocoonType;
import sq.enums.EnumSpiderType;

/**
 * Defines the base cocoon item, which places an entity version of its accompanying type into the world 
 * when the player right-clicks a block.
 */
public class ItemCocoon extends Item
{
	private EnumCocoonType cocoonType;

	public ItemCocoon(EnumCocoonType type)
	{
		super();

		final String name = "cocoon-" + type.getName();
		setCreativeTab(SpiderCore.getCreativeTab());
		setCocoonType(type);
		setUnlocalizedName(name);

		GameRegistry.registerItem(this, name);
	}

	private void setCocoonType(EnumCocoonType type)
	{
		cocoonType = type;
	}

	public EnumCocoonType getCocoonType()
	{
		return cocoonType;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!worldIn.isRemote)
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}
			
            IBlockState blockState = worldIn.getBlockState(pos);
            
            pos = pos.offset(side);
            double yOffset = 0.0D;

            if (side == EnumFacing.UP && blockState.getBlock() instanceof BlockFence)
            {
                yOffset = 0.5D;
            }
            
			final EntityCocoon entityCocoon = new EntityCocoon(worldIn, cocoonType);
			entityCocoon.setPositionAndRotation(pos.getX() + 0.5F, pos.getY() + yOffset, pos.getZ() + 0.5F, playerIn.rotationYaw * -1, 0F);
			worldIn.spawnEntityInWorld(entityCocoon);

			return true;
		}

		return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
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
}
