package sqr.client.render;


import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelGatherer;

public class RenderGatherer extends RenderLiving
{

	public RenderGatherer()
	{
		super(new ModelGatherer(), 1.0F);
		this.setRenderPassModel(new ModelGatherer());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		// TODO Auto-generated method stub
		return null;
	}

}
