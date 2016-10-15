package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import sq.core.minecraft.SpiderItems;
import sq.entities.EntityWebshot;

public class WebshotRenderFactory implements IRenderFactory<EntityWebshot>
{
	@Override
	public Render createRenderFor(RenderManager manager) 
	{
		return new RenderWebshot(manager, SpiderItems.WEB, Minecraft.getMinecraft().getRenderItem());
	}
}
