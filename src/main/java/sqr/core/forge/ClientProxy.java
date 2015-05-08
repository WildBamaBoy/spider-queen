package sqr.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import sqr.client.render.RenderSpiderQueen;
import cpw.mods.fml.client.registry.RenderingRegistry;

public final class ClientProxy extends ServerProxy
{
	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderSpiderQueen());
	}

	@Override
	public void registerEventHandlers() 
	{
	}
}
