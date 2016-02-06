package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelAnt;
import sq.entity.creature.EntityAnt;
import sq.enums.EnumAntType;

/**
 * Sets the texture on the ant model pre-render.
 */
public class RenderAnt<T extends EntityAnt> extends RenderLiving<T>
{
	private final ResourceLocation[] textures = new ResourceLocation[2];
	
    public RenderAnt()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelAnt(), 1.0F);
        textures[0] = new ResourceLocation("sq:textures/entities/ant-black.png");
        textures[1] = new ResourceLocation("sq:textures/entities/ant-red.png");
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity)
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
