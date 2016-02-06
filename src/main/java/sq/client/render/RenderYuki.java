package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelYuki;
import sq.entity.creature.EntityYuki;

/**
 * Sets the texture on the Yuki model pre-render.
 */
public class RenderYuki<T extends EntityYuki> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	
    public RenderYuki()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelYuki(), 1.0F);
        texture = new ResourceLocation("sq:textures/entities/yuki.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}
}
