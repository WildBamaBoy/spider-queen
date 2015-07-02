package sq.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelBee;

public class RenderFriendlyBee extends RenderLiving
{
	private final ResourceLocation texture = new ResourceLocation("sq:textures/entities/friendly-bee.png");
	
    public RenderFriendlyBee()
    {
        super(new ModelBee(), 1.0F);
        setRenderPassModel(new ModelBee());
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
