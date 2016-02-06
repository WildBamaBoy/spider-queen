package sq.items;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import radixcore.constant.Time;
import sq.core.SpiderCore;
import sq.entity.ai.PlayerExtension;
import sq.entity.creature.EntityWebslinger;

/**
 * The webslinger allows the player to throw the webslinger entity when right-clicking with this item.
 */
public class ItemWebslinger extends Item
{
	public ItemWebslinger()
	{
		setMaxStackSize(1);
		setCreativeTab(SpiderCore.getCreativeTab());

		final String name = "webslinger";
		setUnlocalizedName(name);
		setCreativeTab(SpiderCore.getCreativeTab());
		setMaxStackSize(1);

		GameRegistry.registerItem(this, name);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.NONE;
	}

	@Override
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase entitySource, EntityLivingBase entityHit)
	{
		if (entityHit instanceof EntityPlayer)
		{
			final EntityPlayer player = (EntityPlayer) entityHit;
			final PlayerExtension playerExtension = PlayerExtension.get(player);

			if (playerExtension.webEntity != null)
			{
				if (!(entitySource instanceof EntityPlayer))
				{
					playerExtension.webEntity.player = null;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer)
	{
		final PlayerExtension playerExtension = PlayerExtension.get(entityPlayer);

		if (playerExtension.webEntity != null)
		{
			playerExtension.webEntity.player = null;
			playerExtension.webEntity.setDead();
			playerExtension.webEntity = null;
			playerExtension.slingerCooldown = Time.SECOND * 3;
			
			if (entityPlayer.isCollidedHorizontally)
			{
				entityPlayer.jump();
			}
		}

		else
		{
			world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			world.spawnEntityInWorld(new EntityWebslinger(world, entityPlayer));
		}

		entityPlayer.swingItem();
		return itemstack;
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean unknown)
	{
		list.add("Swing from walls and ceilings.");
		list.add("");
		list.add("JUMP to reel in.");
		list.add("SNEAK to drop down.");
	}
}