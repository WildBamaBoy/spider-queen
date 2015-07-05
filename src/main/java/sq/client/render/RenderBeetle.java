package sq.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelBeetle;
import sq.entity.creature.EntityBeetle;

public class RenderBeetle extends RenderLiving
{
	private final ResourceLocation[] textures = new ResourceLocation[2];
	
    public RenderBeetle()
    {
        super(new ModelBeetle(), 1.0F);
        setRenderPassModel(new ModelBeetle());
        textures[0] = new ResourceLocation("sq:textures/entities/beetle-walking.png");
        textures[1] = new ResourceLocation("sq:textures/entities/beetle-flying.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
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
