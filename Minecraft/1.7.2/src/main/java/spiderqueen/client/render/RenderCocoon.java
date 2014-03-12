package spiderqueen.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import spiderqueen.client.model.ModelCocoon;
import spiderqueen.core.SpiderQueen;
import spiderqueen.entity.EntityCocoon;

public class RenderCocoon extends Render
{
	public RenderCocoon()
	{
		shadowSize = 0.5F;
		modelCC = new ModelCocoon();
	}

	public void render(EntityCocoon entitycocoon, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glRotatef(180F - f, 0.0F, 1.0F, 0.0F);
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
		this.bindTexture(getEntityTexture(entitycocoon));
		float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		GL11.glScalef(-1F, -1F, 1.0F);
		modelCC.render((Entity)entitycocoon,0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}


	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		render((EntityCocoon)entity, d, d1, d2, f, f1);
	}

	protected ModelBase modelCC;

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		EntityCocoon entityCocoon = (EntityCocoon)entity;
		String resourceLocation = "spiderqueen:textures/entity/cocoons/" + entityCocoon.getCocoonType();
		
		if (entityCocoon.isEaten())
		{
			resourceLocation += "Dead";
		}
		
		return new ResourceLocation(resourceLocation += ".png");
	}
}
