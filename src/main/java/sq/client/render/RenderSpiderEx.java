package sq.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import sq.client.model.ModelSpiderEx;
import sq.client.render.layer.LayerSpiderCharge;
import sq.entity.creature.EntitySpiderEx;
import sq.enums.EnumSpiderType;

/**
 * Sets the texture on the extended spiders' models pre-render.
 * Also applies effects on each render pass.
 */
public class RenderSpiderEx<T extends EntitySpiderEx> extends RenderLiving<T>
{
	private static ResourceLocation[][] textures;
	private static ResourceLocation charge = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private static ResourceLocation eyes;

	private ModelSpiderEx model = new ModelSpiderEx();

	public RenderSpiderEx()
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelSpiderEx(), 1.0F);
		this.addLayer(new LayerSpiderCharge(this));
	}

	@Override
	protected float getDeathMaxRotation(T entityLivingBase)
	{
		return 180.0F;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(T spider)
	{
		final int level = spider.getLevel();
		return textures[spider.getSpiderType().getId()][level - 1];
	}

	@Override
	protected void preRenderCallback(T entityLiving, float partialTickTime)
	{
		final EntitySpiderEx spider = (EntitySpiderEx) entityLiving;

		//Scale according to the spider's type.
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
					//Textures are organized as: [id][textures for levels 1, 2, and 3].
					textures[type.getId()][i - 1] = new ResourceLocation("sq:textures/entities/spider-" + type.name().toLowerCase() + "-" + i + ".png");
				}
			}
		}
	}
}