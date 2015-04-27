package sqr.client.render;


import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelWasp;

public class RenderWasp extends RenderLiving
{

	public RenderWasp()
	{
		super(new ModelWasp(), 1.0F);
		this.setRenderPassModel(new ModelWasp());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		// TODO Auto-generated method stub
		return null;
	}

}
