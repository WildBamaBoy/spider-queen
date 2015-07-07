package sq.client.render;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Sets the texture on the ghast egg model pre-render.
 */
public class RenderGhastEgg extends RenderSpiderEgg
{
	private static final ResourceLocation	TEXTURE	= new ResourceLocation("sq:textures/entities/ghast-egg.png");

	public RenderGhastEgg()
	{
		super();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return TEXTURE;
	}
}