package sq.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import sq.entity.creature.EntityMiniGhast;

/**
 * Sets the texture on the mini ghast model pre-render.
 */
public class RenderMiniGhast extends RenderLiving
{
	private static final ResourceLocation	ghastTextures	= new ResourceLocation("sq:textures/entities/mini-ghast.png");

	public RenderMiniGhast()
	{
		super(new ModelGhast(), 0.5F);
	}

	protected ResourceLocation getEntityTexture(EntityMiniGhast entityGhast)
	{
		return ghastTextures;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		super.preRenderCallback(entityLivingBase, partialTickTime);
		GL11.glTranslated(0.0D, -1.0D, 0.0D);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityMiniGhast) entity);
	}
}