/*******************************************************************************
 * RenderWebslinger.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import sqr.entity.EntityWebslinger;

public class RenderWebslinger extends Render
{
	public RenderWebslinger()
	{
	}

	public void renderWebSlinger(EntityWebslinger entityWebslinger, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		final Tessellator tessellator = Tessellator.instance;

		GL11.glPushMatrix();
		{
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);

			GL11.glTranslated(posX, posY, posZ);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

			bindTexture(getEntityTexture(entityWebslinger));

			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.addVertexWithUV(-0.5F, -0.5F, 0.0D, 0, 1);
			tessellator.addVertexWithUV(0.5F, -0.5F, 0.0D, 1, 1);
			tessellator.addVertexWithUV(0.5F, 0.5F, 0.0D, 1, 0);
			tessellator.addVertexWithUV(-0.5F, 0.5F, 0.0D, 0, 0);
			tessellator.draw();

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}
		GL11.glPopMatrix();

		if (entityWebslinger.player != null)
		{
			final float deltaYaw = (entityWebslinger.player.prevRotationYaw + (entityWebslinger.player.rotationYaw - entityWebslinger.player.prevRotationYaw) * rotationPitch) * 3.141593F / 180F;

			final Vec3 vec3d = Vec3.createVectorHelper(-0.5D, 0.03D, 0.8D);
			vec3d.rotateAroundX(-(entityWebslinger.player.prevRotationPitch + (entityWebslinger.player.rotationPitch - entityWebslinger.player.prevRotationPitch) * rotationPitch) * 3.141593F / 180F);
			vec3d.rotateAroundY(-(entityWebslinger.player.prevRotationYaw + (entityWebslinger.player.rotationYaw - entityWebslinger.player.prevRotationYaw) * rotationPitch) * 3.141593F / 180F);

			double correctedPosX = entityWebslinger.player.prevPosX + (entityWebslinger.player.posX - entityWebslinger.player.prevPosX) * rotationPitch + vec3d.xCoord;
			double correctedPosY = entityWebslinger.player.prevPosY + (entityWebslinger.player.posY - entityWebslinger.player.prevPosY) * rotationPitch + vec3d.yCoord;
			double correctedPosZ = entityWebslinger.player.prevPosZ + (entityWebslinger.player.posZ - entityWebslinger.player.prevPosZ) * rotationPitch + vec3d.zCoord;

			if (renderManager.options.thirdPersonView > 0)
			{
				final float deltaYawOffset = (entityWebslinger.player.prevRenderYawOffset + (entityWebslinger.player.renderYawOffset - entityWebslinger.player.prevRenderYawOffset) * rotationPitch) * 3.141593F / 180F;
				final double sinDeltaYawOffset = MathHelper.sin(deltaYawOffset);
				final double cosDeltaYawOffset = MathHelper.cos(deltaYawOffset);
				correctedPosX = entityWebslinger.player.prevPosX + (entityWebslinger.player.posX - entityWebslinger.player.prevPosX) * rotationPitch - cosDeltaYawOffset * 0.35D - sinDeltaYawOffset * 0.85D;
				correctedPosY = entityWebslinger.player.prevPosY + (entityWebslinger.player.posY - entityWebslinger.player.prevPosY) * rotationPitch - 0.45D;
				correctedPosZ = entityWebslinger.player.prevPosZ + (entityWebslinger.player.posZ - entityWebslinger.player.prevPosZ) * rotationPitch - sinDeltaYawOffset * 0.35D + cosDeltaYawOffset * 0.85D;
			}

			final double deltaPosX = entityWebslinger.prevPosX + (entityWebslinger.posX - entityWebslinger.prevPosX) * rotationPitch;
			final double deltaPosY = entityWebslinger.prevPosY + (entityWebslinger.posY - entityWebslinger.prevPosY) * rotationPitch + 0.25D;
			final double deltaPosZ = entityWebslinger.prevPosZ + (entityWebslinger.posZ - entityWebslinger.prevPosZ) * rotationPitch;
			final double correctedDeltaPosX = (float) (correctedPosX - deltaPosX);
			final double correctedDeltaPosY = (float) (correctedPosY - deltaPosY);
			final double correctedDeltaPosZ = (float) (correctedPosZ - deltaPosZ);
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			
			tessellator.startDrawing(3);
			tessellator.setColorOpaque_I(8161437);
			
			final int verteces = 16;
			for (int currentVertex = 0; currentVertex <= verteces; currentVertex++)
			{
				final float vertexPos = (float) currentVertex / (float) verteces;
				tessellator.addVertex(posX + correctedDeltaPosX * vertexPos, posY + correctedDeltaPosY * vertexPos + 0.2F, posZ + correctedDeltaPosZ * vertexPos);
			}

			tessellator.draw();
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		renderWebSlinger((EntityWebslinger) entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1)
	{
		return new ResourceLocation("spiderqueen:textures/entity/Webslinger.png");
	}
}
