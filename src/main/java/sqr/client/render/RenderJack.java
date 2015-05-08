package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelJack;

public class RenderJack extends RenderLiving
{
	private final ResourceLocation texture;
	
    public RenderJack()
    {
        super(new ModelJack(), 1.0F);
        setRenderPassModel(new ModelJack());
        texture = new ResourceLocation("sqr:textures/entities/jack.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
