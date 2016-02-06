package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelJack;
import sq.entity.creature.EntityJack;

/**
 * Sets the texture on the Jack model pre-render.
 */
public class RenderJack<T extends EntityJack> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	
    public RenderJack()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelJack(), 1.0F);
        texture = new ResourceLocation("sq:textures/entities/jack.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}
}
