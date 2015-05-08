package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelFly;

public class RenderFly extends RenderLiving
{
	private final ResourceLocation texture;
	
    public RenderFly()
    {
        super(new ModelFly(), 1.0F);
        setRenderPassModel(new ModelFly());
        texture = new ResourceLocation("sqr:textures/entities/fly.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
