package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelBee;
import sq.entity.creature.EntityBee;

/**
 * Sets the texture on the bee model pre-render.
 */
public class RenderBee<T extends EntityBee> extends RenderLiving<T>
{
	private final ResourceLocation[] textures = new ResourceLocation[4];
	
    public RenderBee()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelBee(), 1.0F);
        textures[0] = new ResourceLocation("sq:textures/entities/gatherer-bee.png");
        textures[1] = new ResourceLocation("sq:textures/entities/warrior-bee-passive.png");
        textures[2] = new ResourceLocation("sq:textures/entities/warrior-bee-attacking.png");
        textures[3] = new ResourceLocation("sq:textures/entities/queen-bee.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		EntityBee bee = (EntityBee)entity;
		
		switch (bee.getBeeType())
		{
		case GATHERER: return textures[0];
		case WARRIOR: return bee.getAttacking() ? textures[2] : textures[1];
		case QUEEN: return textures[3];
		}
		
		return null;
	}
}
