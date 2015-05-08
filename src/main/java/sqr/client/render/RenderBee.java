package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelBee;

public class RenderBee extends RenderLiving
{
	private final ResourceLocation[] textures = new ResourceLocation[4];
	
    public RenderBee()
    {
        super(new ModelBee(), 1.0F);
        setRenderPassModel(new ModelBee());
        textures[0] = new ResourceLocation("sqr:textures/entities/gatherer-bee.png");
        textures[1] = new ResourceLocation("sqr:textures/entities/warrior-bee-passive.png");
        textures[2] = new ResourceLocation("sqr:textures/entities/warrior-bee-attacking.png");
        textures[3] = new ResourceLocation("sqr:textures/entities/queen-bee.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
		
		//TODO
//		EntityBee bee = (EntityBee)entity;
//		
//		switch (bee.getBeeType)
//		{
//		case GATHERER: return textures[0];
//		case WARRIOR: return bee.getIsAttacking() ? textures[2] : textures[1];
//		case QUEEN: return textures[3];
//		}
//		
//		return null;
	}
}
