package sq.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sq.client.model.ModelSpiderEgg;
import sq.entity.EntitySpiderEgg;

public class RenderGhastEgg extends RenderSpiderEgg
{
	private static final ResourceLocation	TEXTURE	= new ResourceLocation("sq:textures/entities/ghast-egg.png");

	public RenderGhastEgg()
	{
		super();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return TEXTURE;
	}
}