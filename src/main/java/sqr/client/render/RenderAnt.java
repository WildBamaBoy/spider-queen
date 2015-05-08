package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelAnt;
import sqr.entity.EntityAnt;
import sqr.enums.EnumAntType;

public class RenderAnt extends RenderLiving
{
	private final ResourceLocation[] textures = new ResourceLocation[2];
	
    public RenderAnt()
    {
        super(new ModelAnt(), 1.0F);
        setRenderPassModel(new ModelAnt());
        textures[0] = new ResourceLocation("sqr:textures/entities/ant-black.png");
        textures[1] = new ResourceLocation("sqr:textures/entities/ant-red.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{		
		final EntityAnt ant = (EntityAnt)entity;
		
		if (ant.getAntType() == EnumAntType.BLACK)
		{
			return textures[0];
		}
		
		else
		{
			return textures[1];
		}
	}
}
