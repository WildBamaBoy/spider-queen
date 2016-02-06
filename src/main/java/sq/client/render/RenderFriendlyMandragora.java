package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelMandragora;
import sq.entity.friendly.EntityFriendlyMandragora;

/**
 * Sets the texture on the friendly mandragora model pre-render.
 */
public class RenderFriendlyMandragora<T extends EntityFriendlyMandragora> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	
    public RenderFriendlyMandragora()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelMandragora(), 1.0F);
        texture = new ResourceLocation("sq:textures/entities/friendly-mandragora.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}
}
