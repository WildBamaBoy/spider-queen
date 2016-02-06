package sq.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import sq.entity.friendly.EntityFriendlyCreeper;

/**
 * Sets the texture on the friendly creeper model pre-render.
 */
public class RenderFriendlyCreeper extends RenderCreeper
{
	private ResourceLocation texture = new ResourceLocation("sq:textures/entities/friendly-creeper.png");
	private ResourceLocation textureTired = new ResourceLocation("sq:textures/entities/friendly-creeper-tired.png");
	
	public RenderFriendlyCreeper()
	{
		super(Minecraft.getMinecraft().getRenderManager());
	}
	
    @Override
	protected ResourceLocation getEntityTexture(EntityCreeper entity)
    {
    	EntityFriendlyCreeper creeper = (EntityFriendlyCreeper)entity;
    	
    	if (creeper.isTired())
    	{
    		return textureTired;
    	}
    	
    	else
    	{
    		return texture;
    	}
    }
}
