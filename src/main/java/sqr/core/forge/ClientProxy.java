package sqr.core.forge;

import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.entity.player.EntityPlayer;
import sqr.client.render.RenderAnt;
import sqr.client.render.RenderBee;
import sqr.client.render.RenderBeetle;
import sqr.client.render.RenderCocoon;
import sqr.client.render.RenderFly;
import sqr.client.render.RenderJack;
import sqr.client.render.RenderMandragora;
import sqr.client.render.RenderOctopus;
import sqr.client.render.RenderSpiderEgg;
import sqr.client.render.RenderSpiderQueen;
import sqr.client.render.RenderWasp;
import sqr.client.render.RenderYuki;
import sqr.entity.EntityAnt;
import sqr.entity.EntityBee;
import sqr.entity.EntityBeetle;
import sqr.entity.EntityCocoon;
import sqr.entity.EntityFly;
import sqr.entity.EntityJack;
import sqr.entity.EntityMandragora;
import sqr.entity.EntityMiniGhast;
import sqr.entity.EntityOctopus;
import sqr.entity.EntitySpiderEgg;
import sqr.entity.EntitySpiderQueen;
import sqr.entity.EntityWasp;
import sqr.entity.EntityYuki;
import cpw.mods.fml.client.registry.RenderingRegistry;

public final class ClientProxy extends ServerProxy
{
	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderSpiderQueen());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnt.class, new RenderAnt());
		RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, new RenderBee());
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new RenderBeetle());
		RenderingRegistry.registerEntityRenderingHandler(EntityFly.class, new RenderFly());
		RenderingRegistry.registerEntityRenderingHandler(EntityJack.class, new RenderJack());
		RenderingRegistry.registerEntityRenderingHandler(EntityMandragora.class, new RenderMandragora());
		RenderingRegistry.registerEntityRenderingHandler(EntityOctopus.class, new RenderOctopus());
		RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp());
		RenderingRegistry.registerEntityRenderingHandler(EntityYuki.class, new RenderYuki());
	}

	@Override
	public void registerEventHandlers() 
	{
	}
}
