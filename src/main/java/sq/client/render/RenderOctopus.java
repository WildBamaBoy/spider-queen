package sq.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelOctopus;

/**
 * Sets the texture on the octopus model pre-render.
 */
public class RenderOctopus extends RenderLiving
{
	private final ResourceLocation texture;
	
    public RenderOctopus()
    {
        super(new ModelOctopus(), 1.0F);
        setRenderPassModel(new ModelOctopus());
        texture = new ResourceLocation("sq:textures/entities/octopus.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
