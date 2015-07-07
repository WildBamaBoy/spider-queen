package sq.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import sq.entity.creature.EntityWebShot;
import sq.enums.EnumWebType;

/**
 * Sets the texture on the web shot model pre-render.
 */
public class RenderWeb extends Render
{
	private static ResourceLocation textureWebShot = new ResourceLocation("sq:textures/entities/webshot.png");
	private static ResourceLocation textureWebShotPoison = new ResourceLocation("sq:textures/entities/webshot-poison.png");
	
	public RenderWeb()
	{
	}

	public void render(EntityWebShot entityWeb, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		bindEntityTexture(entityWeb);

		GL11.glPushMatrix();
		{
			final Tessellator tessellator = Tessellator.instance;

			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entityWeb.prevRotationYaw + (entityWeb.rotationYaw - entityWeb.prevRotationYaw) * rotationYaw - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entityWeb.prevRotationPitch + (entityWeb.rotationPitch - entityWeb.prevRotationPitch) * rotationYaw, 0.0F, 0.0F, 1.0F);

			final byte b0 = 0;
			final float f2 = 0.0F;
			final float f3 = 0.5F;
			final float f4 = (0 + b0 * 10) / 32.0F;
			final float f5 = (5 + b0 * 10) / 32.0F;
			final float f6 = 0.0F;
			final float f7 = 0.15625F;
			final float f8 = (5 + b0 * 10) / 32.0F;
			final float f9 = (10 + b0 * 10) / 32.0F;
			final float f10 = 0.05625F;

			GL11.glEnable(GL12.GL_RESCALE_NORMAL);

			GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(f10, f10, f10);
			GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
			GL11.glNormal3f(f10, 0.0F, 0.0F);

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, f6, f8);
			tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, f7, f8);
			tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, f7, f9);
			tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, f6, f9);
			tessellator.draw();

			GL11.glNormal3f(-f10, 0.0F, 0.0F);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, f6, f8);
			tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, f7, f8);
			tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, f7, f9);
			tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, f6, f9);
			tessellator.draw();

			for (int i = 0; i < 4; ++i)
			{
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glNormal3f(0.0F, 0.0F, f10);
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, f2, f4);
				tessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, f3, f4);
				tessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, f3, f5);
				tessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, f2, f5);
				tessellator.draw();
			}

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{	
		EntityWebShot web = (EntityWebShot)entity;		
		return web.getType() == EnumWebType.NORMAL ? textureWebShot : textureWebShotPoison;
	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		render((EntityWebShot) var1, var2, var4, var6, var8, var9);
	}
}