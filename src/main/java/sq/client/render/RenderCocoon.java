package sq.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import sq.client.model.ModelCocoon;
import sq.entities.EntityCocoon;

public class RenderCocoon extends RenderLiving<EntityCocoon>
{
	private static final ModelBase MODEL = new ModelCocoon();

    public RenderCocoon(RenderManager renderManagerIn)
    {
        super(renderManagerIn, MODEL, 0.5F);
    }

	@Override
	public void doRender(EntityCocoon entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		GL11.glPushMatrix();
		{
			EntityCocoon entityCocoon = (EntityCocoon)entity;
			GL11.glTranslated(x, y, z);
			GL11.glRotatef(entityCocoon.rotationYaw, 0.0F, 1.0F, 0.0F);
			final float rotateAdjustForHit = entityCocoon.getTimeSinceHit();
			float rotateAdjustForDamage = entityCocoon.getCurrentDamage();

			if (rotateAdjustForDamage < 0.0F)
			{
				rotateAdjustForDamage = 0.0F;
			}

			if (rotateAdjustForHit > 0.0F)
			{
				GL11.glRotatef(MathHelper.sin(rotateAdjustForHit) * rotateAdjustForHit, 1.0F, 0.0F, 0.0F);
			}

			bindTexture(getEntityTexture(entityCocoon));
			GL11.glScalef(-1F, -1F, 1.0F);
			MODEL.render(entityCocoon, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}


	@Override
	protected ResourceLocation getEntityTexture(EntityCocoon entity) 
	{
		return entity.getIsEaten() ? entity.getCocoonType().getDeadTextureLocation() : entity.getCocoonType().getTextureLocation();
	}
}
