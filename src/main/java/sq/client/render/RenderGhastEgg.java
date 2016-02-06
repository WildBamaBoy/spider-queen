package sq.client.render;

import net.minecraft.util.ResourceLocation;
import sq.entity.creature.EntitySpiderEgg;

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
	protected ResourceLocation getEntityTexture(EntitySpiderEgg entity)
	{
		return TEXTURE;
	}
}