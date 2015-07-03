package sq.client.render;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFriendlyCreeper extends RenderCreeper
{
	private ResourceLocation texture = new ResourceLocation("sq:textures/entities/friendly-creeper.png");
	
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
}
