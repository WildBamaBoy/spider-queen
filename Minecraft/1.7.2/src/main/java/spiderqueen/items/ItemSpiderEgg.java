package spiderqueen.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntitySpiderEgg;

public class ItemSpiderEgg extends AbstractItemSpawner 
{
	public ItemSpiderEgg()
	{
		super();
		setCreativeTab(SpiderQueen.getInstance().tabSpiderQueen);
	}
	
	@Override
	public void registerIcons(IIconRegister IIconRegister) 
	{
		itemIcon = IIconRegister.registerIcon("spiderqueen:SpiderEgg");
	}

	@Override
	public boolean spawnEntity(World world, EntityPlayer player, double posX, double posY, double posZ) 
	{
		if (world.isRemote)
		{	
			return true;
		}

		else
		{
			final EntitySpiderEgg entitySpiderEgg = new EntitySpiderEgg(world, player.getCommandSenderName());
			entitySpiderEgg.setLocationAndAngles(posX, posY, posZ, world.rand.nextFloat() * 360F, 0.0F);

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(entitySpiderEgg);
			}

			return true;
		}
	}
}
