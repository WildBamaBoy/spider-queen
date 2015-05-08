package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelWasp;

public class RenderWasp extends RenderLiving
{
	private final ResourceLocation texture;
	
    public RenderWasp()
    {
        super(new ModelWasp(), 1.0F);
        setRenderPassModel(new ModelWasp());
        texture = new ResourceLocation("sqr:textures/entities/wasp.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
