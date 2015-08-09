package sq.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import radixcore.constant.Font.Color;
import sq.core.SpiderCore;
import sq.entity.creature.EntityCocoon;
import sq.enums.EnumCocoonType;
import sq.enums.EnumSpiderType;

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
		setTextureName("sq:" + name);

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
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (!world.isRemote)
		{
			if (!player.capabilities.isCreativeMode)
			{
				stack.stackSize--;
			}

			posX += Facing.offsetsXForSide[meta];
			posY += Facing.offsetsYForSide[meta];
			posZ += Facing.offsetsZForSide[meta];

			final EntityCocoon entityCocoon = new EntityCocoon(world, cocoonType);
			entityCocoon.setPositionAndRotation(posX + 0.5F, posY + 1, posZ + 0.5F, player.rotationYaw * -1, 0F);
			world.spawnEntityInWorld(entityCocoon);

			return true;
		}

		return super.onItemUse(stack, player, world, posX, posY, posZ, meta, xOffset, yOffset, zOffset);
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
