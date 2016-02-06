package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelFly;
import sq.entity.creature.EntityFly;

/**
 * Sets the texture on the fly model pre-render.
 */
public class RenderFly<T extends EntityFly> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	
    public RenderFly()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelFly(), 1.0F);
        texture = new ResourceLocation("sq:textures/entities/fly.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}
}
