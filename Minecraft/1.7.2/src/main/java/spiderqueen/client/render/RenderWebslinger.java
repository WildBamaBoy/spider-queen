// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityWebslinger;

// Referenced classes of package net.minecraft.src:
//            Render, Vec3, EntityPlayer, MathHelper, 
//            GameSettings, EntityWebslinger, Tessellator, RenderManager, 
//            Entity

public class RenderWebslinger extends Render
{

	public RenderWebslinger()
	{
	}

	public void func_4011_a(EntityWebslinger entitysweb, double d, double d1, double d2, float f, float f1)
	{
		final Tessellator tessellator = Tessellator.instance;
		
		GL11.glPushMatrix();
		{
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			
			GL11.glTranslated(d, d1, d2);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			
			bindTexture(getEntityTexture(entitysweb));
			
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

		if (entitysweb.player != null)
		{
			float f9 = ((entitysweb.player.prevRotationYaw + (entitysweb.player.rotationYaw - entitysweb.player.prevRotationYaw) * f1) * 3.141593F) / 180F;
			double d3 = MathHelper.sin(f9);
			double d5 = MathHelper.cos(f9);
			float f11 = 0;//entitysweb.angler.getSwingProgress(f1);
			float f12 = MathHelper.sin(MathHelper.sqrt_float(f11) * 3.141593F);

			final Vec3 vec3d = Vec3.createVectorHelper(-0.5D, 0.03D, 0.8D);
			vec3d.rotateAroundX((-(entitysweb.player.prevRotationPitch + (entitysweb.player.rotationPitch - entitysweb.player.prevRotationPitch) * f1) * 3.141593F) / 180F);
			vec3d.rotateAroundY((-(entitysweb.player.prevRotationYaw + (entitysweb.player.rotationYaw - entitysweb.player.prevRotationYaw) * f1) * 3.141593F) / 180F);
			vec3d.rotateAroundY(f12 * 0.5F);
			vec3d.rotateAroundX(-f12 * 0.7F);
			
			double correctedPosX = entitysweb.player.prevPosX + (entitysweb.player.posX - entitysweb.player.prevPosX) * (double)f1 + vec3d.xCoord;
			double correctedPosY = entitysweb.player.prevPosY + (entitysweb.player.posY - entitysweb.player.prevPosY) * (double)f1 + vec3d.yCoord;
			double correctedPosZ = entitysweb.player.prevPosZ + (entitysweb.player.posZ - entitysweb.player.prevPosZ) * (double)f1 + vec3d.zCoord;
			
			if (renderManager.options.thirdPersonView > 0)
			{
				float f10 = ((entitysweb.player.prevRenderYawOffset + (entitysweb.player.renderYawOffset - entitysweb.player.prevRenderYawOffset) * f1) * 3.141593F) / 180F;
				double d4 = MathHelper.sin(f10);
				double d6 = MathHelper.cos(f10);
				correctedPosX = (entitysweb.player.prevPosX + (entitysweb.player.posX - entitysweb.player.prevPosX) * (double)f1) - d6 * 0.34999999999999998D - d4 * 0.84999999999999998D;
				correctedPosY = (entitysweb.player.prevPosY + (entitysweb.player.posY - entitysweb.player.prevPosY) * (double)f1) - 0.45000000000000001D;
				correctedPosZ = ((entitysweb.player.prevPosZ + (entitysweb.player.posZ - entitysweb.player.prevPosZ) * (double)f1) - d4 * 0.34999999999999998D) + d6 * 0.84999999999999998D;
			}
			
			double d10 = entitysweb.prevPosX + (entitysweb.posX - entitysweb.prevPosX) * (double)f1;
			double d11 = entitysweb.prevPosY + (entitysweb.posY - entitysweb.prevPosY) * (double)f1 + 0.25D;
			double d12 = entitysweb.prevPosZ + (entitysweb.posZ - entitysweb.prevPosZ) * (double)f1;
			double d13 = (float)(correctedPosX - d10);
			double d14 = (float)(correctedPosY - d11);
			double d15 = (float)(correctedPosZ - d12);
			GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
			GL11.glDisable(2896 /*GL_LIGHTING*/);
			tessellator.startDrawing(3);
			tessellator.setColorOpaque_I(0x7c889d);
			int j = 16;
			for(int k = 0; k <= j; k++)
			{
				float f13 = (float)k / (float)j;
				tessellator.addVertex(d + d13 * (double)f13, d1 + d14 * (double)(f13) + 0.2F, d2 + d15 * (double)f13);
			}

			tessellator.draw();
			GL11.glEnable(2896 /*GL_LIGHTING*/);
			GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
		}
	}
	@Override
	public void doRender(Entity entity, double d, double d1, double d2, 
			float f, float f1)
	{
		func_4011_a((EntityWebslinger)entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) 
	{
		return new ResourceLocation("spiderqueen:textures/entity/Webslinger.png");
	}
}
