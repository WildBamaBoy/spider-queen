/*******************************************************************************
 * ClientProxy.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import spiderqueen.client.render.RenderCocoon;
import spiderqueen.client.render.RenderEnemyQueen;
import spiderqueen.client.render.RenderFakePlayer;
import spiderqueen.client.render.RenderHatchedSpider;
import spiderqueen.client.render.RenderSpiderEgg;
import spiderqueen.client.render.RenderSpiderQueen;
import spiderqueen.client.render.RenderWeb;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.entity.EntityOtherQueen;
import spiderqueen.entity.EntitySpiderEgg;
import spiderqueen.entity.EntityWeb;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCocoon.class, new RenderCocoon());
		RenderingRegistry.registerEntityRenderingHandler(EntityWeb.class, new RenderWeb());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderSpiderQueen());
		RenderingRegistry.registerEntityRenderingHandler(EntityFakePlayer.class, new RenderFakePlayer());
		RenderingRegistry.registerEntityRenderingHandler(EntityHatchedSpider.class, new RenderHatchedSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderEgg.class, new RenderSpiderEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityOtherQueen.class, new RenderEnemyQueen());
	}
}
