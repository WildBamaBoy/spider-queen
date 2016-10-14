package sq.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import sq.entities.EntityCocoon;

public class CocoonRenderFactory implements IRenderFactory<EntityCocoon>
{
	@Override
	public Render createRenderFor(RenderManager manager) 
	{
		return new RenderCocoon(manager);
	}
}
