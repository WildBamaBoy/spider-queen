package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelMandragora;
import sq.entity.creature.EntityMandragora;

/**
 * Sets the texture on the mandragora model pre-render.
 */
public class RenderMandragora<T extends EntityMandragora> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	
    public RenderMandragora()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelMandragora(), 1.0F);
        texture = new ResourceLocation("sq:textures/entities/mandragora.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}
}
