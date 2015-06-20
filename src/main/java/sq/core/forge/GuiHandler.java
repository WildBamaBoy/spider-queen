package sq.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sq.client.gui.GuiSleep;
import sq.core.Constants;
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
		System.out.println(guiId);
		
		if (guiId == Constants.ID_GUI_SLEEP)
		{
			return new GuiSleep(player);
		}
		
		return null;
	}
}
