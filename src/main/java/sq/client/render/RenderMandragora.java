package sq.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelMandragora;

/**
 * Sets the texture on the mandragora model pre-render.
 */
public class RenderMandragora extends RenderLiving
{
	private final ResourceLocation texture;
	
    public RenderMandragora()
    {
        super(new ModelMandragora(), 1.0F);
        setRenderPassModel(new ModelMandragora());
        texture = new ResourceLocation("sq:textures/entities/mandragora.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
