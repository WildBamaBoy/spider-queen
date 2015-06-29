package sq.client.render;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sq.client.model.ModelSpiderEx;
import sq.entity.EntitySpiderEx;
import sq.enums.EnumSpiderType;

public class RenderSpiderEx extends RenderLiving
{
	private static ResourceLocation[][] textures;
	private static ResourceLocation eyes;
	
	public RenderSpiderEx()
	{
		super(new ModelSpiderEx(), 1.0F);
		setRenderPassModel(new ModelSpiderEx());
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase entityLivingBase)
	{
		return 180.0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return getEntityTexture((EntitySpiderEx) entity);
	}

	protected ResourceLocation getEntityTexture(EntitySpiderEx spider)
	{
		final int level = spider.getLevel();
		return textures[spider.getSpiderType().getId()][level - 1];
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entitySpider, int passNumber, float partialTickTime)
	{
        if (passNumber != 0)
        {
            return -1;
        }
        else
        {
            this.bindTexture(eyes);
            GL11.glTranslatef(0.0F, 0.0F, -0.0001F); //Prevent Z clipping.
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

            if (entitySpider.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            return 1;
        }
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLiving, float partialTickTime)
	{
		final EntitySpiderEx spider = (EntitySpiderEx) entityLiving;

		if (spider.getSpiderType() == EnumSpiderType.WIMPY)
		{
			GL11.glScaled(0.5D, 0.5D, 0.5D);
		}

		else if (spider.getSpiderType() == EnumSpiderType.TANK)
		{
			final int level = spider.getLevel();
			double scale = 1.0D;

			switch (level)
			{
			case 1:
				scale = 1.0D;
				break;
			case 2:
				scale = 1.3D;
				break;
			case 3:
				scale = 1.5D;
				break;
			default:
				scale = 1.3D;
				break;
			}

			GL11.glScaled(scale, scale, scale);
		}

		else if (spider.getSpiderType() == EnumSpiderType.ENDER)
		{
			GL11.glScaled(1.3D, 1.3D, 1.3D);
		}

		else
		{
			super.preRenderCallback(entityLiving, partialTickTime);
		}
	}

	static
	{
		textures = new ResourceLocation[EnumSpiderType.values().length][3];
		eyes = new ResourceLocation("sq:textures/entities/spider-eyes.png");
		
		for (EnumSpiderType type : EnumSpiderType.values())
		{
			if (type != EnumSpiderType.NONE)
			{
				for (int i = 1; i < 4; i++)
				{
					textures[type.getId()][i - 1] = new ResourceLocation("sq:textures/entities/spider-" + type.name().toLowerCase() + "-" + i + ".png");
				}
			}
		}
	}
}