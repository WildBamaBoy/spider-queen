package sq.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import radixcore.util.BlockHelper;
import sq.core.SpiderCore;
import sq.entity.friendly.IFriendlyEntity;

/**
 * Defines the spawn eggs and spawns them in the world when right-clicked on a block.
 */
public class ItemEggSpawner extends Item
{
	private Class spawnClass;

	public ItemEggSpawner(Class spawnClass, String itemName)
	{
		this.spawnClass = spawnClass;
		this.setCreativeTab(SpiderCore.getCreativeTab());
		this.setMaxStackSize(1);
		this.setUnlocalizedName(itemName);

		GameRegistry.registerItem(this, itemName);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!worldIn.isRemote)
		{
			final Block block = BlockHelper.getBlock(worldIn, pos.getX(), pos.getY(), pos.getZ());

            IBlockState blockState = worldIn.getBlockState(pos);
            
            pos = pos.offset(side);
            double yOffset = 0.0D;

            if (side == EnumFacing.UP && blockState.getBlock() instanceof BlockFence)
            {
                yOffset = 0.5D;
            }
            
			spawnEntity(worldIn, playerIn, pos.getX() + 0.5D, pos.getY() + yOffset, pos.getZ() + 0.5D);

			if (!playerIn.capabilities.isCreativeMode)
			{
				playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
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