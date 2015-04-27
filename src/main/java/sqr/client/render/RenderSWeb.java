package sqr.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import sqr.entity.EntitySlingerWeb;

// Referenced classes of package net.minecraft.src:
//            Render, Vec3D, EntityPlayer, MathHelper,
//            GameSettings, EntitySWeb, Tessellator, RenderManager,
//            Entity

public class RenderSWeb extends Render
{
	
	public RenderSWeb()
	{
	}
	
	public void func_4011_a(EntitySlingerWeb entitysweb, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		final int i = 0;
		final byte byte0 = 0;
		this.bindTexture(new ResourceLocation("/imgz/hook.png"));
		final Tessellator tessellator = Tessellator.instance;
		final float f2 = (i * 8 + 0) / 128F;
		final float f3 = (i * 8 + 16) / 128F;
		final float f4 = (byte0 * 8 + 0) / 128F;
		final float f5 = (byte0 * 8 + 16) / 128F;
		final float f6 = 1.0F;
		final float f7 = 0.5F;
		final float f8 = 0.5F;
		GL11.glRotatef(180F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV(0.0F - f7, 0.0F - f8, 0.0D, f2, f5);
		tessellator.addVertexWithUV(f6 - f7, 0.0F - f8, 0.0D, f3, f5);
		tessellator.addVertexWithUV(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
		tessellator.addVertexWithUV(0.0F - f7, 1.0F - f8, 0.0D, f2, f4);
		tessellator.draw();
		GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glPopMatrix();
		if (entitysweb.holder != null)
		{
			final float f9 = (entitysweb.holder.prevRotationYaw + (entitysweb.holder.rotationYaw - entitysweb.holder.prevRotationYaw) * f1) * 3.141593F / 180F;
			final double d3 = MathHelper.sin(f9);
			final double d5 = MathHelper.cos(f9);
			final float f11 = 0;// entitysweb.angler.getSwingProgress(f1);
			final float f12 = MathHelper.sin(MathHelper.sqrt_float(f11) * 3.141593F);
			final Vec3 vec3d = Vec3.createVectorHelper(-0.5D, 0.029999999999999999D, 0.80000000000000004D);
			vec3d.rotateAroundX(-(entitysweb.holder.prevRotationPitch + (entitysweb.holder.rotationPitch - entitysweb.holder.prevRotationPitch) * f1) * 3.141593F / 180F);
			vec3d.rotateAroundY(-(entitysweb.holder.prevRotationYaw + (entitysweb.holder.rotationYaw - entitysweb.holder.prevRotationYaw) * f1) * 3.141593F / 180F);
			vec3d.rotateAroundY(f12 * 0.5F);
			vec3d.rotateAroundX(-f12 * 0.7F);
			double d7 = entitysweb.holder.prevPosX + (entitysweb.holder.posX - entitysweb.holder.prevPosX) * f1 + vec3d.xCoord;
			double d8 = entitysweb.holder.prevPosY + (entitysweb.holder.posY - entitysweb.holder.prevPosY) * f1 + vec3d.yCoord;
			double d9 = entitysweb.holder.prevPosZ + (entitysweb.holder.posZ - entitysweb.holder.prevPosZ) * f1 + vec3d.zCoord;
			if (this.renderManager.options.thirdPersonView > 0)
			{
				final float f10 = (entitysweb.holder.prevRenderYawOffset + (entitysweb.holder.renderYawOffset - entitysweb.holder.prevRenderYawOffset) * f1) * 3.141593F / 180F;
				final double d4 = MathHelper.sin(f10);
				final double d6 = MathHelper.cos(f10);
				d7 = entitysweb.holder.prevPosX + (entitysweb.holder.posX - entitysweb.holder.prevPosX) * f1 - d6 * 0.34999999999999998D - d4 * 0.84999999999999998D;
				d8 = entitysweb.holder.prevPosY + (entitysweb.holder.posY - entitysweb.holder.prevPosY) * f1 - 0.45000000000000001D;
				d9 = entitysweb.holder.prevPosZ + (entitysweb.holder.posZ - entitysweb.holder.prevPosZ) * f1 - d4 * 0.34999999999999998D + d6 * 0.84999999999999998D;
			}
			final double d10 = entitysweb.prevPosX + (entitysweb.posX - entitysweb.prevPosX) * f1;
			final double d11 = entitysweb.prevPosY + (entitysweb.posY - entitysweb.prevPosY) * f1 + 0.25D;
			final double d12 = entitysweb.prevPosZ + (entitysweb.posZ - entitysweb.prevPosZ) * f1;
			final double d13 = (float) (d7 - d10);
			final double d14 = (float) (d8 - d11);
			final double d15 = (float) (d9 - d12);
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			GL11.glDisable(2896 /* GL_LIGHTING */);
			tessellator.startDrawing(3);
			tessellator.setColorOpaque_I(0x7c889d);
			final int j = 16;
			for (int k = 0; k <= j; k++)
			{
				final float f13 = (float) k / (float) j;
				tessellator.addVertex(d + d13 * f13, d1 + d14 * f13 + 0.2F, d2 + d15 * f13);
			}
			
			tessellator.draw();
			GL11.glEnable(2896 /* GL_LIGHTING */);
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
		}
	}
	
	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		this.func_4011_a((EntitySlingerWeb) entity, d, d1, d2, f, f1);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
