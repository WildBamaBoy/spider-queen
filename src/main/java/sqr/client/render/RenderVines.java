package sqr.client.render;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sqr.client.model.ModelVines;
import sqr.entity.EntityVines;

public class RenderVines extends Render
{

	public RenderVines()
	{
		this.shadowSize = 0.5F;
		this.modelVines = new ModelVines();
	}

	public void func_157_a(EntityVines entity, double d, double d1, double d2,
			float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
		final float f2 = -f1;
		float f3 = -f1;
		if(f3 < 0.0F)
		{
			f3 = 0.0F;
		}
		if(f2 > 0.0F)
		{
			GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10F, 1.0F, 0.0F, 0.0F);
		}
		bindTexture(new ResourceLocation("/imgz/vines.png"));
		GL11.glScalef(-1F, -1F, 1.0F);
		this.modelVines.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1)
	{
		this.func_157_a((EntityVines)entity, d, d1, d2, f, f1);
	}

	protected ModelBase modelVines;

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		// TODO Auto-generated method stub
		return null;
	}

}
