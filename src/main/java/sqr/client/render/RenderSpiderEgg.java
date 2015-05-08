package sqr.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sqr.client.model.ModelSpiderEgg;

public class RenderSpiderEgg extends Render
{
	private final ResourceLocation	texture	= new ResourceLocation("spiderqueen:textures/entities/spider-egg.png");
	private final ModelBase	modelEgg;

	public RenderSpiderEgg()
	{
		shadowSize = 0.5F;
		modelEgg = new ModelSpiderEgg();
	}

	//TODO entity
//	public void render(EntitySpiderEgg entitySpiderEgg, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
//	{
//		GL11.glPushMatrix();
//		{
//			GL11.glTranslated(posX, posY + 1.11D, posZ);
//			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
//
//			GL11.glScalef(0.75F, 0.75F, 0.75F);
//			GL11.glScalef(-1F, -1F, 1.0F);
//
//			bindTexture(getEntityTexture(entitySpiderEgg));
//
//			modelEgg.render(entitySpiderEgg, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
//		}
//		GL11.glPopMatrix();
//	}
//
//	@Override
//	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
//	{
//		render((EntitySpiderEgg) entity, posX, posY, posZ, rotationYaw, rotationPitch);
//	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}

	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_,
			double p_76986_4_, double p_76986_6_, float p_76986_8_,
			float p_76986_9_) {
		// TODO Auto-generated method stub
		
	}
}
