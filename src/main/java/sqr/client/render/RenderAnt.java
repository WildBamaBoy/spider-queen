package sqr.client.render;


import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelAnt;

public class RenderAnt extends RenderLiving
{
	public RenderAnt()
	{
		super(new ModelAnt(), 1.0F);
		this.setRenderPassModel(new ModelAnt());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}
