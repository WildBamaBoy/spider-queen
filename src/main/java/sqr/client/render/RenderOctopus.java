package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelOctopus;

public class RenderOctopus extends RenderLiving
{
	private final ResourceLocation texture;
	
    public RenderOctopus()
    {
        super(new ModelOctopus(), 1.0F);
        setRenderPassModel(new ModelOctopus());
        texture = new ResourceLocation("sqr:textures/entities/octopus.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
