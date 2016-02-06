package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelBee;
import sq.entity.friendly.EntityFriendlyBee;

/**
 * Sets the texture on the friendly bee model pre-render.
 */
public class RenderFriendlyBee<T extends EntityFriendlyBee> extends RenderLiving<T>
{
	private final ResourceLocation texture = new ResourceLocation("sq:textures/entities/friendly-bee.png");
	
    public RenderFriendlyBee()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelBee(), 1.0F);
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}
}
