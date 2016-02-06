package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

/**
 * Sets the texture on the friendly zombie model pre-render.
 */
public class RenderFriendlyZombie extends RenderZombie
{
	private ResourceLocation texture = new ResourceLocation("sq:textures/entities/friendly-zombie.png");
	
	public RenderFriendlyZombie()
	{
		super(Minecraft.getMinecraft().getRenderManager());
	}
	
    @Override
	protected ResourceLocation getEntityTexture(EntityZombie entity)
    {
        return texture;
    }
}
