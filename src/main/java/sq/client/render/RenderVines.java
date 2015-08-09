package sq.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelVines;
import sq.entity.creature.EntityVines;

/**
 * Sets the texture on the vine model pre-render.
 */
public class RenderVines extends Render
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("sq:textures/entities/vines.png");
	public RenderVines()
	{
		shadowSize = 0.5F;
		modelVines = new ModelVines();
	}

	public void func_157_a(EntityVines entity, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
		float f2 = (float) -f1;
		float f3 = (float) -f1;
		if(f3 < 0.0F)
		{
			f3 = 0.0F;
		}
		if(f2 > 0.0F)
		{
			GL11.glRotatef(((MathHelper.sin(f2) * f2 * f3) / 10F), 1.0F, 0.0F, 0.0F);
		}
		GL11.glScalef(-1F, -1F, 1.0F);
		this.bindTexture(TEXTURE);
		modelVines.render((Entity)entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		func_157_a((EntityVines)entity, d, d1, d2, f, f1);
	}

	protected ModelBase modelVines;

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) 
	{
		return TEXTURE;
	}

}
