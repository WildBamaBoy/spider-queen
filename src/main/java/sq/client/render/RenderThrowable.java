package sq.client.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sq.entity.throwable.EntityBoomBall;
import sq.entity.throwable.EntityFreezeBall;
import sq.entity.throwable.EntityJackBall;

/**
 * Actually renders a throwable textured object in the world. Used for projectiles.
 */
public class RenderThrowable extends Render
{
	private ResourceLocation jackBallLocation = new ResourceLocation("sq:textures/items/jackball.png");
	private ResourceLocation boomBallLocation = new ResourceLocation("sq:textures/items/boomball.png");
	private ResourceLocation octoBallLocation = new ResourceLocation("sq:textures/items/octoball.png");
	private ResourceLocation freezeBallLocation = new ResourceLocation("sq:textures/items/freezeball.png");

	public RenderThrowable()
	{
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationPitch, float rotationYaw)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)posX, (float)posY, (float)posZ);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		this.bindEntityTexture(entity);
		
		float f = 0;
		float f1 = 1;
		float f2 = 0;
		float f3 = 1;
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

		Tessellator tess = Tessellator.getInstance();
		WorldRenderer ren = tess.getWorldRenderer();
		
		ren.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
		ren.normal(0.0F, 1.0F, 0.0F);
		ren.pos(0.0F - f5, 0.0F - f6, 0.0D).tex(f, f3).endVertex();
		ren.pos(f4 - f5, 0.0F - f6, 0.0D).tex(f1, f3).endVertex();
		ren.pos(f4 - f5, f4 - f6, 0.0D).tex(f1, f2).endVertex();
		ren.pos(0.0F - f5, f4 - f6, 0.0D).tex(f, f2).endVertex();
		tess.draw();

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		if (entity instanceof EntityJackBall)
		{
			return jackBallLocation;
		}

		else if (entity instanceof EntityBoomBall)
		{
			return boomBallLocation;
		}

		else if (entity instanceof EntityFreezeBall)
		{
			return freezeBallLocation;
		}

		else
		{
			return null;
		}
	}
}