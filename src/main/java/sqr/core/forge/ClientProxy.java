/*******************************************************************************
 * ClientProxy.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.core.forge;

import net.minecraft.entity.player.EntityPlayer;
import sqr.client.render.RenderCocoon;
import sqr.client.render.RenderFakePlayer;
import sqr.client.render.RenderHatchedSpider;
import sqr.client.render.RenderMiniGhast;
import sqr.client.render.RenderOtherQueen;
import sqr.client.render.RenderSpiderEgg;
import sqr.client.render.RenderSpiderQueen;
import sqr.client.render.RenderWeb;
import sqr.client.render.RenderWebslinger;
import sqr.core.SpiderQueen;
import sqr.entity.EntityCocoon;
import sqr.entity.EntityFakePlayer;
import sqr.entity.EntityHatchedSpider;
import sqr.entity.EntityMiniGhast;
import sqr.entity.EntityOtherQueen;
import sqr.entity.EntitySpiderEgg;
import sqr.entity.EntityWeb;
import sqr.entity.EntityWebslinger;
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
