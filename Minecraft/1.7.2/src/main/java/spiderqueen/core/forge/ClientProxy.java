package spiderqueen.core.forge;

import spiderqueen.client.render.RenderCocoon;
import spiderqueen.entity.EntityCocoon;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCocoon.class, new RenderCocoon());
	}
}
