package sq.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelCocoon;
import sq.entity.creature.EntityCocoon;

/**
 * Sets the texture on the cocoon model pre-render and applies rotation/translation.
 */
public class RenderCocoon<T extends EntityCocoon> extends Render<T>
{
	private final ModelBase	modelCocoon;

	public RenderCocoon()
	{
		super(Minecraft.getMinecraft().getRenderManager());
		shadowSize = 0.5F;
		modelCocoon = new ModelCocoon();
	}

	public void render(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		GL11.glPushMatrix();
		{
			EntityCocoon entityCocoon = (EntityCocoon)entity;
			GL11.glTranslated(posX, posY, posZ);
			GL11.glRotatef(entityCocoon.rotationYaw, 0.0F, 1.0F, 0.0F);
			final float rotateAdjustForHit = entityCocoon.getTimeSinceHit() - rotationPitch / 2;
			float rotateAdjustForDamage = entityCocoon.getCurrentDamage() - rotationPitch / 2;

			if (rotateAdjustForDamage < 0.0F)
			{
				rotateAdjustForDamage = 0.0F;
			}

			if (rotateAdjustForHit > 0.0F)
			{
				GL11.glRotatef(MathHelper.sin(rotateAdjustForHit) * rotateAdjustForHit * rotateAdjustForDamage / 10F, 1.0F, 0.0F, 0.0F);
			}

			bindTexture(getEntityTexture((T) entityCocoon));
			GL11.glScalef(-1F, -1F, 1.0F);
			modelCocoon.render(entityCocoon, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(T entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		render(entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		final EntityCocoon entityCocoon = (EntityCocoon) entity;
		String name = entityCocoon.getCocoonType().toString().toLowerCase();
		name = name.replace("_", "-"); //For bees
		
		String resourceLocation = "sq:textures/entities/cocoon-" + name;
		
		if (entityCocoon.isEaten())
		{
			resourceLocation += "-dead";
		}

		return new ResourceLocation(resourceLocation += ".png");
	}
}
