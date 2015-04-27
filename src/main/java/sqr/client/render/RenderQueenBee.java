package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelQueenBee;

public class RenderQueenBee extends RenderLiving
{
	
	public RenderQueenBee()
	{
		super(new ModelQueenBee(), 1.0F);
		this.setRenderPassModel(new ModelQueenBee());
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
