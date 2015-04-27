package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelWarrior;

public class RenderWarrior extends RenderLiving
{
	
	public RenderWarrior()
	{
		super(new ModelWarrior(), 1.0F);
		this.setRenderPassModel(new ModelWarrior());
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
