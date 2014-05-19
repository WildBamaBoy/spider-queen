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
import spiderqueen.client.render.RenderFakePlayer;
import spiderqueen.client.render.RenderHatchedSpider;
import spiderqueen.client.render.RenderMiniGhast;
import spiderqueen.client.render.RenderOtherQueen;
import spiderqueen.client.render.RenderSpiderEgg;
import spiderqueen.client.render.RenderSpiderQueen;
import spiderqueen.client.render.RenderWeb;
import spiderqueen.client.render.RenderWebslinger;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.entity.EntityFakePlayer;
import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.entity.EntityMiniGhast;
import spiderqueen.entity.EntityOtherQueen;
import spiderqueen.entity.EntitySpiderEgg;
import spiderqueen.entity.EntityWeb;
import spiderqueen.entity.EntityWebslinger;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCocoon.class, new RenderCocoon());
		RenderingRegistry.registerEntityRenderingHandler(EntityWeb.class, new RenderWeb());
		RenderingRegistry.registerEntityRenderingHandler(EntityFakePlayer.class, new RenderFakePlayer());
		RenderingRegistry.registerEntityRenderingHandler(EntityHatchedSpider.class, new RenderHatchedSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderEgg.class, new RenderSpiderEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityOtherQueen.class, new RenderOtherQueen());
		RenderingRegistry.registerEntityRenderingHandler(EntityWebslinger.class, new RenderWebslinger());
		RenderingRegistry.registerEntityRenderingHandler(EntityMiniGhast.class, new RenderMiniGhast());
		
		if (!SpiderQueen.getInstance().getModProperties().useSpiderQueenModel || SpiderQueen.getInstance().getModProperties().isDisabled)
		{
			//Do not override the player renderer when the player doesn't want to use the spider model or when the mod is disabled.
		}
		
		else
		{
			RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderSpiderQueen());
		}
	}
}
