package sqr.core.forge;

import sqr.client.render.RenderCocoon;
import sqr.entity.EntityCocoon;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCocoon.class, new RenderCocoon());
	}
}
