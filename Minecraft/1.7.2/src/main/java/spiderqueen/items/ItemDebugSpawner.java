package spiderqueen.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityFakePlayer;

public class ItemDebugSpawner extends Item
{
	public ItemDebugSpawner()
	{
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (!world.isRemote)
		{
//			final EntityHatchedSpider spider = new EntityHatchedSpider(world);
			final EntityFakePlayer spider = new EntityFakePlayer(world);
			spider.setPositionAndRotation(posX, posY + 1, posZ, spider.rotationYaw - 90F, 0F);
			world.spawnEntityInWorld(spider);
		}
		
		return true;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		
	}
}
