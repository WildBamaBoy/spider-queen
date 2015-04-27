

package sqr.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            Render, Tessellator, RenderManager, Entity

public class RenderCustomball extends Render
{

	public RenderCustomball(int tx)
	{
		this.ntex = tx;
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		bindTexture(new ResourceLocation("/terrain.png"));
		final Tessellator tessellator = Tessellator.instance;
		//TODO
//		final float f2 = (float)(SQR.texx[this.ntex] % 16 * 16 + 0) / 256F;
//		final float f3 = (float)(SQR.texx[this.ntex] % 16 * 16 + 16) / 256F;
//		final float f4 = (float)(SQR.texx[this.ntex] / 16 * 16 + 0) / 256F;
//		final float f5 = (float)(SQR.texx[this.ntex] / 16 * 16 + 16) / 256F;
		final float f6 = 1.0F;
		final float f7 = 0.5F;
		final float f8 = 0.25F;
		GL11.glRotatef(180F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
//		tessellator.addVertexWithUV(0.0F - f7, 0.0F - f8, 0.0D, f2, f5);
//		tessellator.addVertexWithUV(f6 - f7, 0.0F - f8, 0.0D, f3, f5);
//		tessellator.addVertexWithUV(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
//		tessellator.addVertexWithUV(0.0F - f7, 1.0F - f8, 0.0D, f2, f4);
		tessellator.draw();
		GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		GL11.glPopMatrix();
	}

	private final int ntex;

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}
