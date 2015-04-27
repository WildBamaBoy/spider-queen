package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelBeetle;

public class RenderBeetle extends RenderLiving
{
	
	public RenderBeetle()
	{
		super(new ModelBeetle(), 1.0F);
		this.setRenderPassModel(new ModelBeetle());
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
