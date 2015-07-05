package sq.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import sq.entity.friendly.IFriendlyEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemEggSpawner extends Item
{
	private Class spawnClass;

	public ItemEggSpawner(Class spawnClass, String itemName)
	{
		this.spawnClass = spawnClass;
		this.setCreativeTab(SpiderCore.getCreativeTab());
		this.setMaxStackSize(1);
		this.setUnlocalizedName(itemName);
		this.setTextureName("sq:" + itemName);

		GameRegistry.registerItem(this, itemName);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (!world.isRemote)
		{
			final Block block = world.getBlock(posX, posY, posZ);
			double verticalOffset = 0.0D;

			posX += Facing.offsetsXForSide[meta];
			posY += Facing.offsetsYForSide[meta];
			posZ += Facing.offsetsZForSide[meta];

			if (meta == 1 && block == Blocks.fence || block == Blocks.nether_brick_fence)
			{
				verticalOffset = 0.5D;
			}

			spawnEntity(world, player, posX + 0.5D, posY + verticalOffset, posZ + 0.5D);

			if (!player.capabilities.isCreativeMode)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}

			return true;
		}

		return false;
	}

	public void spawnEntity(World world, EntityPlayer player, double posX, double posY, double posZ)
	{
		try
		{
			Entity entity = (Entity)spawnClass.getConstructor(World.class).newInstance(world);
			
			if (entity instanceof IFriendlyEntity)
			{
				entity = (Entity) spawnClass.getConstructor(World.class, EntityPlayer.class).newInstance(world, player);
				IFriendlyEntity friendly = (IFriendlyEntity)entity;
				friendly.setFriendPlayerUUID(player.getPersistentID());
			}
			
			entity.setPosition(posX, posY, posZ);
			world.spawnEntityInWorld(entity);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}