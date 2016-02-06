package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

/**
 * Sets the texture on the friendly skeleton model pre-render.
 */
public class RenderFriendlySkeleton extends RenderSkeleton
{
	private ResourceLocation texture = new ResourceLocation("sq:textures/entities/friendly-skeleton.png");
	
	public RenderFriendlySkeleton()
	{
		super(Minecraft.getMinecraft().getRenderManager());
	}
	
    @Override
	protected ResourceLocation getEntityTexture(EntitySkeleton entity)
    {
        return texture;
    }
}
