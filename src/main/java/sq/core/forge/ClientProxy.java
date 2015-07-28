package sq.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import sq.client.render.RenderAnt;
import sq.client.render.RenderBee;
import sq.client.render.RenderBeetle;
import sq.client.render.RenderCocoon;
import sq.client.render.RenderFly;
import sq.client.render.RenderFriendlyBee;
import sq.client.render.RenderFriendlyCreeper;
import sq.client.render.RenderFriendlyMandragora;
import sq.client.render.RenderFriendlySkeleton;
import sq.client.render.RenderFriendlyZombie;
import sq.client.render.RenderGhastEgg;
import sq.client.render.RenderHuman;
import sq.client.render.RenderJack;
import sq.client.render.RenderMandragora;
import sq.client.render.RenderMiniGhast;
import sq.client.render.RenderOctopus;
import sq.client.render.RenderOtherQueen;
import sq.client.render.RenderSpiderEgg;
import sq.client.render.RenderSpiderEx;
import sq.client.render.RenderSpiderQueen;
import sq.client.render.RenderThrowable;
import sq.client.render.RenderVines;
import sq.client.render.RenderWasp;
import sq.client.render.RenderWeb;
import sq.client.render.RenderWebslinger;
import sq.client.render.RenderYuki;
import sq.core.SpiderCore;
import sq.entity.creature.EntityAnt;
import sq.entity.creature.EntityBee;
import sq.entity.creature.EntityBeetle;
import sq.entity.creature.EntityCocoon;
import sq.entity.creature.EntityFly;
import sq.entity.creature.EntityGhastEgg;
import sq.entity.creature.EntityHuman;
import sq.entity.creature.EntityJack;
import sq.entity.creature.EntityMandragora;
import sq.entity.creature.EntityMiniGhast;
import sq.entity.creature.EntityOctopus;
import sq.entity.creature.EntitySpiderEgg;
import sq.entity.creature.EntitySpiderEx;
import sq.entity.creature.EntitySpiderQueen;
import sq.entity.creature.EntityVines;
import sq.entity.creature.EntityWasp;
import sq.entity.creature.EntityWebShot;
import sq.entity.creature.EntityWebslinger;
import sq.entity.creature.EntityYuki;
import sq.entity.friendly.EntityFriendlyBee;
import sq.entity.friendly.EntityFriendlyCreeper;
import sq.entity.friendly.EntityFriendlyMandragora;
import sq.entity.friendly.EntityFriendlySkeleton;
import sq.entity.friendly.EntityFriendlyZombie;
import sq.entity.throwable.EntityBoomBall;
import sq.entity.throwable.EntityFreezeBall;
import sq.entity.throwable.EntityJackBall;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class ClientProxy extends ServerProxy
{
	public static RenderSpiderQueen renderSpiderQueen = new RenderSpiderQueen();
	
	@Override
	public void registerRenderers() 
	{
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
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderQueen.class, new RenderOtherQueen());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderEgg.class, new RenderSpiderEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityWebslinger.class, new RenderWebslinger());
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyCreeper.class, new RenderFriendlyCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlySkeleton.class, new RenderFriendlySkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyZombie.class, new RenderFriendlyZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyMandragora.class, new RenderFriendlyMandragora());
		RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, new RenderHuman());
		RenderingRegistry.registerEntityRenderingHandler(EntityGhastEgg.class, new RenderGhastEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityMiniGhast.class, new RenderMiniGhast());
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyBee.class, new RenderFriendlyBee());
		RenderingRegistry.registerEntityRenderingHandler(EntityFreezeBall.class, new RenderThrowable());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderSpiderQueen());
	}
	
	@Override
	public void registerEventHandlers() 
	{
	}
}
