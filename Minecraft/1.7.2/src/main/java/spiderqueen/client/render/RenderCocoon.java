/*******************************************************************************
 * RenderCocoon.java
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
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import spiderqueen.client.model.ModelCocoon;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.enums.EnumCocoonType;

public class RenderCocoon extends Render
{
	private final ModelBase	modelCocoon;

	public RenderCocoon()
	{
		shadowSize = 0.5F;
		modelCocoon = new ModelCocoon();
	}

	public void render(EntityCocoon entityCocoon, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entityCocoon.rotationYaw, 0.0F, 1.0F, 0.0F);
			final float rotateAdjustForHit = entityCocoon.getTimeSinceHit() - rotationPitch / 2;
			float rotateAdjustForDamage = entityCocoon.getCurrentDamage() - rotationPitch / 2;

			if (rotateAdjustForDamage < 0.0F)
			{
				rotateAdjustForDamage = 0.0F;
			}

			if (rotateAdjustForHit > 0.0F)
			{
				GL11.glRotatef(MathHelper.sin(rotateAdjustForHit) * rotateAdjustForHit * rotateAdjustForDamage / 10F, 1.0F, 0.0F, 0.0F);
			}

			bindTexture(getEntityTexture(entityCocoon));
			
			if (entityCocoon.getCocoonType() == EnumCocoonType.GHAST)
			{
				GL11.glScalef(3.5F, 3.5F, 3.5F);
				GL11.glTranslated(0.0D, -0.5D, 0.0);
			}
			
			else
			{
				GL11.glScalef(-1F, -1F, 1.0F);	
			}
			
			modelCocoon.render(entityCocoon, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		render((EntityCocoon) entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		final EntityCocoon entityCocoon = (EntityCocoon) entity;

		String name = entityCocoon.getCocoonType().toString();
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();

		String resourceLocation = "spiderqueen:textures/entity/" + name;

		if (entityCocoon.isEaten())
		{
			resourceLocation += "Dead";
		}

		return new ResourceLocation(resourceLocation += ".png");
	}
}
