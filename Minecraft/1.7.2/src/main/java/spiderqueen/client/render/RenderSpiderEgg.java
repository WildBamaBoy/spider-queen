/*******************************************************************************
 * RenderSpiderEgg.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import spiderqueen.client.model.ModelSpiderEgg;
import spiderqueen.entity.EntitySpiderEgg;

public class RenderSpiderEgg extends Render
{
	private static final ResourceLocation	TEXTURE	= new ResourceLocation("spiderqueen:textures/entity/SpiderEgg.png");
	private final ModelBase					modelEgg;

	public RenderSpiderEgg()
	{
		shadowSize = 0.5F;
		modelEgg = new ModelSpiderEgg();
	}

	public void render(EntitySpiderEgg entitySpiderEgg, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY + 1.11D, posZ);
			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);

			GL11.glScalef(0.75F, 0.75F, 0.75F);
			GL11.glScalef(-1F, -1F, 1.0F);

			bindTexture(getEntityTexture(entitySpiderEgg));

			modelEgg.render(entitySpiderEgg, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		render((EntitySpiderEgg) entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return TEXTURE;
	}
}
