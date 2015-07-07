package sq.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelMandragora;

/**
 * Sets the texture on the friendly mandragora model pre-render.
 */
public class RenderFriendlyMandragora extends RenderLiving
{
	private final ResourceLocation texture;
	
    public RenderFriendlyMandragora()
    {
        super(new ModelMandragora(), 1.0F);
        setRenderPassModel(new ModelMandragora());
        texture = new ResourceLocation("sq:textures/entities/friendly-mandragora.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
