package sq.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import sq.core.SpiderCore;
import sq.entities.EntityWebshot;

/**
 * The web item allows the player to shoot a web shot when they right click with this item.
 */
public class ItemWebShot extends Item
{
	public ItemWebShot()
	{
		super();
		
		final String name = SpiderCore.ID + ".web";
		setUnlocalizedName(name);
		setCreativeTab(SpiderCore.getCreativeTab());
	}

	@Override
	public ActionResult onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) 
	{
        world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) * 0.5F);		
        if (!world.isRemote)
		{
			if (!player.capabilities.isCreativeMode)
			{
				itemStack.stackSize--;
			}

			final EntityWebshot web = new EntityWebshot(world, player);
			web.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 1.0F);
			world.spawnEntityInWorld(web);
			
            return new ActionResult(EnumActionResult.SUCCESS, itemStack);
		}
		
        return new ActionResult(EnumActionResult.FAIL, itemStack);
	}
}