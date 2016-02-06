package sq.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.entity.creature.EntityMiniGhast;

/**
 * Sets the texture on the mini ghast model pre-render.
 */
public class RenderMiniGhast<T extends EntityMiniGhast> extends RenderLiving<T>
{
	private static final ResourceLocation	ghastTextures	= new ResourceLocation("sq:textures/entities/mini-ghast.png");

	public RenderMiniGhast()
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelGhast(), 0.5F);
	}

	@Override
	protected void preRenderCallback(T entityLivingBase, float partialTickTime)
	{
		super.preRenderCallback(entityLivingBase, partialTickTime);
		GL11.glTranslated(0.0D, -1.0D, 0.0D);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return ghastTextures;
	}
}