package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelWasp;
import sq.entity.creature.EntityWasp;

/**
 * Sets the texture on the wasp model pre-render.
 */
public class RenderWasp<T extends EntityWasp> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	
    public RenderWasp()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelWasp(), 1.0F);
        texture = new ResourceLocation("sq:textures/entities/wasp.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}
}
