package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelBeetle;
import sq.entity.creature.EntityBeetle;

/**
 * Sets the texture on the beetle model pre-render.
 */
public class RenderBeetle<T extends EntityBeetle> extends RenderLiving<T>
{
	private final ResourceLocation[] textures = new ResourceLocation[2];
	
    public RenderBeetle()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelBeetle(), 1.0F);
        textures[0] = new ResourceLocation("sq:textures/entities/beetle-walking.png");
        textures[1] = new ResourceLocation("sq:textures/entities/beetle-flying.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		final EntityBeetle beetle = (EntityBeetle)entity;
		
		if (!beetle.getIsFlying())
		{
			return textures[0];
		}
		
		else
		{
			return textures[1];
		}
	}
}
