package sq.core.forge;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sq.client.gui.GuiSleep;

/**
 * Spider Queen's GUI handler, registered with Forge.
 */
public final class GuiHandler implements IGuiHandler
{
	public static final byte ID_GUI_SLEEP = 1;
	
	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int posX, int posY, int posZ)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int posX, int posY, int posZ)
	{	
		if (guiId == ID_GUI_SLEEP)
		{
			return new GuiSleep(player);
		}
		
		return null;
	}
}
