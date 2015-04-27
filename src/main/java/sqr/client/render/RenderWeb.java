package sqr.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sqr.entity.EntityWeb;

public class RenderWeb extends Render
{
	
	public RenderWeb()
	{
	}
	
	public void func_154_a(EntityWeb entityarrow, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		final float f2 = 0.70F;
		GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
		// final int i = ModItems.itemWeb.getIconFromDamage(0); //TODO
		this.bindTexture(new ResourceLocation("/gui/items.png"));
		final Tessellator tessellator = Tessellator.instance;
		// final float f3 = (i % 16 * 16 + 0) / 256F;
		// final float f4 = (i % 16 * 16 + 16) / 256F;
		// final float f5 = (i / 16 * 16 + 0) / 256F;
		// final float f6 = (i / 16 * 16 + 16) / 256F;
		final float f7 = 1.0F;
		final float f8 = 0.5F;
		final float f9 = 0.25F;
		GL11.glRotatef(180F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		// tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
		// tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
		// tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
		// tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
		tessellator.draw();
		GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glPopMatrix();
		
	}
	
	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		this.func_154_a((EntityWeb) entity, d, d1, d2, f, f1);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
