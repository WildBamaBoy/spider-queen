package sq.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sq.client.model.ModelCocoon;
import sq.entity.EntityCocoon;
import sq.enums.EnumCocoonType;

public class RenderCocoon extends Render
{
	private final ResourceLocation[] textures = new ResourceLocation[27];
	private final ModelBase	modelCocoon;

	public RenderCocoon()
	{
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

			bindTexture(getEntityTexture(entityCocoon));
			GL11.glScalef(-1F, -1F, 1.0F);
			modelCocoon.render(entityCocoon, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		render((EntityCocoon) entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
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
