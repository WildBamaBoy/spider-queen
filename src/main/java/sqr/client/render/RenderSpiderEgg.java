package sqr.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sqr.client.model.ModelSpiderEgg;
import sqr.entity.EntitySpiderEgg;

public class RenderSpiderEgg extends Render
{
	
	public RenderSpiderEgg()
	{
		this.shadowSize = 0.5F;
		this.modelEgg = new ModelSpiderEgg();
	}
	
	public void func_157_a(EntitySpiderEgg entityegg, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glRotatef(180F - f, 0.0F, 1.0F, 0.0F);
		final float f2 = 0 - f1;
		float f3 = 0 - f1;
		if (f3 < 0.0F)
		{
			f3 = 0.0F;
		}
		if (f2 > 0.0F)
		{
			GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10F * 1, 1.0F, 0.0F, 0.0F);
		}
		this.bindTexture(new ResourceLocation("/terrain.png"));
		final float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindTexture(new ResourceLocation("/imgz/spideregg.png"));
		GL11.glScalef(-1F, -1F, 1.0F);
		this.modelEgg.render(entityegg, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		this.func_157_a((EntitySpiderEgg) entity, d, d1, d2, f, f1);
	}
	
	protected ModelBase modelEgg;
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
