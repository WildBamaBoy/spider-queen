package sq.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import sq.client.render.RenderAnt;
import sq.client.render.RenderBee;
import sq.client.render.RenderBeetle;
import sq.client.render.RenderCocoon;
import sq.client.render.RenderFly;
import sq.client.render.RenderFriendlyCreeper;
import sq.client.render.RenderFriendlySkeleton;
import sq.client.render.RenderFriendlyZombie;
import sq.client.render.RenderJack;
import sq.client.render.RenderMandragora;
import sq.client.render.RenderOctopus;
import sq.client.render.RenderSpiderEgg;
import sq.client.render.RenderSpiderEx;
import sq.client.render.RenderSpiderQueen;
import sq.client.render.RenderThrowable;
import sq.client.render.RenderVines;
import sq.client.render.RenderWasp;
import sq.client.render.RenderWeb;
import sq.client.render.RenderWebslinger;
import sq.client.render.RenderYuki;
import sq.entity.EntityAnt;
import sq.entity.EntityBee;
import sq.entity.EntityBeetle;
import sq.entity.EntityBoomBall;
import sq.entity.EntityCocoon;
import sq.entity.EntityFly;
import sq.entity.EntityFriendlyCreeper;
import sq.entity.EntityFriendlySkeleton;
import sq.entity.EntityFriendlyZombie;
import sq.entity.EntityJack;
import sq.entity.EntityJackBall;
import sq.entity.EntityMandragora;
import sq.entity.EntityOctopus;
import sq.entity.EntitySpiderEgg;
import sq.entity.EntitySpiderEx;
import sq.entity.EntityVines;
import sq.entity.EntityWasp;
import sq.entity.EntityWebShot;
import sq.entity.EntityWebslinger;
import sq.entity.EntityYuki;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityJackBall.class, new RenderThrowable());
		RenderingRegistry.registerEntityRenderingHandler(EntityVines.class, new RenderVines());
		RenderingRegistry.registerEntityRenderingHandler(EntityCocoon.class, new RenderCocoon());
		RenderingRegistry.registerEntityRenderingHandler(EntityWebShot.class, new RenderWeb());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoomBall.class, new RenderThrowable());
		RenderingRegistry.registerEntityRenderingHandler(EntityWebShot.class, new RenderWeb());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderEx.class, new RenderSpiderEx());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderEgg.class, new RenderSpiderEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityWebslinger.class, new RenderWebslinger());
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyCreeper.class, new RenderFriendlyCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlySkeleton.class, new RenderFriendlySkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyZombie.class, new RenderFriendlyZombie());
	}

	@Override
	public void registerEventHandlers() 
	{
	}
}
