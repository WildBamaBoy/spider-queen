package sq.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelSpiderEgg;
import sq.entity.creature.EntitySpiderEgg;

/**
 * Sets the texture on the spider egg model pre-render.
 */
public class RenderSpiderEgg<T extends EntitySpiderEgg> extends Render<T>
{
	private static final ResourceLocation	TEXTURE	= new ResourceLocation("sq:textures/entities/spider-egg.png");
	private final ModelBase					modelEgg;

	public RenderSpiderEgg()
	{
		super(Minecraft.getMinecraft().getRenderManager());
		shadowSize = 0.5F;
		modelEgg = new ModelSpiderEgg();
	}

	public void render(EntitySpiderEgg entitySpiderEgg, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslated(posX, posY + 1.11D, posZ);
			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);

			GL11.glScalef(0.75F, 0.75F, 0.75F);
			GL11.glScalef(-1F, -1F, 1.0F);

			bindTexture(getEntityTexture(entitySpiderEgg));

			modelEgg.render(entitySpiderEgg, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(T entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		render((EntitySpiderEgg) entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpiderEgg entity)
	{
		return TEXTURE;
	}
}