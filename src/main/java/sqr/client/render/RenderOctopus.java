package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelOctopus;

public class RenderOctopus extends RenderLiving
{
	
	public RenderOctopus()
	{
		super(new ModelOctopus(), 1.0F);
		this.setRenderPassModel(new ModelOctopus());
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
