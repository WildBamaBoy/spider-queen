package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelFly;

public class RenderFly extends RenderLiving
{
	
	public RenderFly()
	{
		super(new ModelFly(), 1.0F);
		this.setRenderPassModel(new ModelFly());
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
