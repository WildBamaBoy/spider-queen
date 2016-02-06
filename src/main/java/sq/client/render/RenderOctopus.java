package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelOctopus;
import sq.entity.creature.EntityOctopus;

/**
 * Sets the texture on the octopus model pre-render.
 */
public class RenderOctopus<T extends EntityOctopus> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	
    public RenderOctopus()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelOctopus(), 1.0F);
        texture = new ResourceLocation("sq:textures/entities/octopus.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}
}
