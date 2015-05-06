package sqr.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public final class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int posX, int posY, int posZ)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int posX, int posY, int posZ)
	{
		return null;
	}
}
