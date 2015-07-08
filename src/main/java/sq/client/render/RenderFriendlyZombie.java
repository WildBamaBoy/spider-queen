package sq.client.render;

import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Sets the texture on the friendly zombie model pre-render.
 */
public class RenderFriendlyZombie extends RenderZombie
{
	private ResourceLocation texture = new ResourceLocation("sq:textures/entities/friendly-zombie.png");
	
    @Override
	protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
}
