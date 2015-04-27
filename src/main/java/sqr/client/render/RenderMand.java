package sqr.client.render;


import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelMand;

public class RenderMand extends RenderLiving
{

	public RenderMand()
	{
		super(new ModelMand(), 1.0F);
		this.setRenderPassModel(new ModelMand());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		// TODO Auto-generated method stub
		return null;
	}

}
