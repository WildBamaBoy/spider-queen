package spiderqueen.core.forge;

import spiderqueen.client.render.RenderCocoon;
import spiderqueen.client.render.RenderWeb;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityWeb;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCocoon.class, new RenderCocoon());
		RenderingRegistry.registerEntityRenderingHandler(EntityWeb.class, new RenderWeb());
	}
}
