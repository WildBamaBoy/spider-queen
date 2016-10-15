package sq.util;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;

public final class ModelRenderPair 
{
	private ModelBase model;
	private RenderLiving render;
	
	public ModelRenderPair(ModelBase model, RenderLiving render)
	{
		this.model = model;
		this.render = render;
	}
	
	public ModelBase getModel()
	{
		return model;
	}
	
	public RenderLiving getRender()
	{
		return render;
	}
}
