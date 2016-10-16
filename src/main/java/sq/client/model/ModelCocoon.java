package sq.client.model;

import java.lang.reflect.Method;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import sq.api.ICocoonModel;
import sq.entities.EntityCocoon;
import sq.enums.EnumCocoonSize;
import sq.enums.EnumCocoonType;
import sq.util.CocoonRenderCache;
import sq.util.ModelRenderPair;

/**
 * Defines the model for all cocoons
 */
public class ModelCocoon extends ModelBase
{
	/* Reference to protected method getEntityTexture in Render */
	private static final Method REFLECT_GET_ENTITY_TEXTURE;

	static
	{
		//Grab getEntityTexture()...
		Method targetMethod = null;

		for (Method method : Render.class.getDeclaredMethods())
		{
			//As of right now it's the only method that returns a resource location in Render.
			if (method.getReturnType() == ResourceLocation.class)
			{
				targetMethod = method;
				targetMethod.setAccessible(true);
				break;
			}
		}

		REFLECT_GET_ENTITY_TEXTURE = targetMethod;
	}

	/* All the different cocoons */
	private final ModelRenderer	modelWrappedHead;
	private final ModelRenderer	modelWrappedBody;
	private final ModelRenderer	modelWrappedBodyTall;
	private final ModelRenderer	modelVisibleFlatHead;
	private final ModelRenderer modelBeeCocoon;
	private final ModelRenderer modelInsectCocoon;

	public ModelCocoon()
	{
		super();

		modelWrappedHead = new ModelRenderer(this, 20, 13);
		modelWrappedHead.addBox(0F, 0F, 0F, 10, 9, 10, 0F);
		modelWrappedHead.setRotationPoint(-5F, -21F, -5F);

		modelWrappedBody = new ModelRenderer(this, 16, 8);
		modelWrappedBody.addBox(0F, 0F, 0F, 12, 12, 12, 0F);
		modelWrappedBody.setRotationPoint(-6F, -12F, -6F);

		modelWrappedBodyTall = new ModelRenderer(this, 0, 0);
		modelWrappedBodyTall.addBox(-5F, 0F, -3.5F, 10, 17, 7);
		modelWrappedBodyTall.setRotationPoint(0F, -16F, 0F);

		modelVisibleFlatHead = new ModelRenderer(this, 2, 2);
		modelVisibleFlatHead.addBox(-4F, -4F, -7F, 8, 8, 6, 0F);
		modelVisibleFlatHead.setRotationPoint(0F, -16F, -1F);

		modelBeeCocoon = new ModelRenderer(this, 20, 13);
		modelBeeCocoon.addBox(0F, 0F, 0F, 10, 9, 10);
		modelBeeCocoon.setRotationPoint(-5F, 3F, -5F);
		modelBeeCocoon.setTextureSize(64, 32);
		modelBeeCocoon.mirror = true;
		setRotation(modelBeeCocoon, 0F, 0F, 0F);

		modelInsectCocoon = new ModelRenderer(this, 20, 13);
		modelInsectCocoon.addBox(0F, 0F, 0F, 10, 9, 10);
		modelInsectCocoon.setRotationPoint(-5F, 3F, -2F);
		modelInsectCocoon.setTextureSize(64, 32);
		modelInsectCocoon.mirror = true;
		setRotation(modelInsectCocoon, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float posX, float posY, float posZ, float rotationYaw, float rotationPitch, float partialTickTime)
	{
		final EntityCocoon entityCocoon = (EntityCocoon) entity;
		final EnumCocoonType cocoonType = entityCocoon.getCocoonType();
		final EnumCocoonSize cocoonSize = cocoonType.getCocoonSize();

		renderCocoonBase(cocoonSize, partialTickTime);

		/* Get the entity's model and render instances from our render cache. The cache creates a new instance of a model 
		 * when needed, so as to prevent the cocoon's head from moving around when the model instance looked up from the 
		 * render instance is being used by another entity on screen. */
		ModelRenderPair mrp = CocoonRenderCache.getInstance().getModelRenderPair(entityCocoon.getHeldEntity().getClass());
		RenderLiving entityRender = mrp.getRender();
		ModelBase entityModel = mrp.getModel();
		
		GL11.glPushMatrix();
		{
			//Bind texture and make a couple early corrections
			bindEntityTexture(entityRender, entityCocoon.getHeldEntity());
			GL11.glTranslated(0.0D, -1.3D, -0.3D);

			ModelRenderer[] headModels = new ModelRenderer[0];

			//Modded entities can have more control over their cocoon models
			//when implementing ICocoonModel
			if (entityModel instanceof ICocoonModel)
			{
				ICocoonModel cocoonModel = (ICocoonModel)entityModel;
				cocoonModel.preRender();
				headModels = cocoonModel.getHeadModelComponents();
			}

			else //If they don't, it's probably vanilla. Make some guesses about the model and needed corrections.
			{
				if (entityModel instanceof ModelBiped && !(entityModel instanceof ModelEnderman))
				{
					GL11.glTranslated(0.0D, 0.5D, 0.15D);
					ModelBiped bipedModel = (ModelBiped)entityModel;
					headModels = new ModelRenderer[]{bipedModel.bipedHead};
				}

				else if (entityModel instanceof ModelQuadruped)
				{
					GL11.glTranslated(0.0D, 0.0D, 0.65D);
					ModelQuadruped quadModel = (ModelQuadruped)entityModel;
					headModels = new ModelRenderer[]{quadModel.head};
					
					if (cocoonType == EnumCocoonType.PIG)
					{
						GL11.glTranslated(0.0D, -0.5D, 0.0D);
					}
					
					else if (cocoonType == EnumCocoonType.POLARBEAR)
					{
						GL11.glTranslated(0.0D, 0.0D, 0.05D);
					}
				}

				else
				{
					switch (cocoonType)
					{
					case WOLF: 
						ModelWolf wolfModel = (ModelWolf)entityModel;
						headModels = new ModelRenderer[]{wolfModel.wolfHeadMain};
						GL11.glTranslated(0.0D, -0.5D, 0.35D);
						break;
					case CREEPER:
						ModelCreeper creeperModel = (ModelCreeper)entityModel;
						headModels = new ModelRenderer[]{creeperModel.head};
						GL11.glTranslated(0.0D, 0.15D, 0.1D);
						break;
					case ENDERMAN:
						ModelEnderman endermanModel = (ModelEnderman)entityModel;
						headModels = new ModelRenderer[]{endermanModel.bipedHead, endermanModel.bipedHeadwear};
						GL11.glTranslated(0.0D, 1.2D, 0.3D);
						break;
					case CHICKEN: 
						ModelChicken chickenModel = (ModelChicken)entityModel;
						headModels = new ModelRenderer[]{chickenModel.head, chickenModel.bill};
						GL11.glTranslated(0.0D, 0.15D, 0.2D);
						break;
					case VILLAGER: 
						ModelVillager villagerModel = (ModelVillager)entityModel;
						headModels = new ModelRenderer[]{villagerModel.villagerHead};
						GL11.glTranslated(0.0D, 0.3D, 0.3D);
						break;
					case WITCH:
						ModelWitch witchModel = (ModelWitch)entityModel;
						headModels = new ModelRenderer[]{witchModel.villagerHead};
						GL11.glTranslated(0.0D, 0.3D, 0.3D);
						break;
					
					/* Why oh why are some of these private?! */
					case GUARDIAN:
						ModelGuardian guardianModel = (ModelGuardian)entityModel;
						headModels = new ModelRenderer[]{
								ObfuscationReflectionHelper.getPrivateValue(ModelGuardian.class, guardianModel, 0)
						};
						GL11.glTranslated(0.0D, 0.0D, -0.1D);
						GL11.glScaled(0.8D, 0.8D, 0.8D);
						break;
					case HORSE: 
						ModelHorse horseModel = (ModelHorse)entityModel;
						headModels = new ModelRenderer[]{
								ObfuscationReflectionHelper.getPrivateValue(ModelHorse.class, horseModel, 1),
								ObfuscationReflectionHelper.getPrivateValue(ModelHorse.class, horseModel, 2)
						};
						GL11.glTranslated(0.0D, 0.3D, 1.0D);
						break;
					case RABBIT: 
						ModelRabbit rabbitModel = (ModelRabbit)entityModel;
						headModels = new ModelRenderer[]{
								ObfuscationReflectionHelper.getPrivateValue(ModelRabbit.class, rabbitModel, 7),
								ObfuscationReflectionHelper.getPrivateValue(ModelRabbit.class, rabbitModel, 8),
								ObfuscationReflectionHelper.getPrivateValue(ModelRabbit.class, rabbitModel, 9),
								ObfuscationReflectionHelper.getPrivateValue(ModelRabbit.class, rabbitModel, 10),
								ObfuscationReflectionHelper.getPrivateValue(ModelRabbit.class, rabbitModel, 11)
								};
						break;
					}
				}
			}
			
			//Finally, render everything in our head models.
			for (ModelRenderer model : headModels)
			{
				model.render(0.0625F);
			}
		}

		GL11.glPopMatrix();
	}

	private void bindEntityTexture(RenderLiving renderLiving, Entity entity)
	{
		try
		{
			ResourceLocation loc = (ResourceLocation) REFLECT_GET_ENTITY_TEXTURE.invoke(renderLiving, entity);
			renderLiving.bindTexture(loc);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void renderCocoonBase(EnumCocoonSize cocoonSize, float partialTickTime)
	{
		if (cocoonSize == EnumCocoonSize.SMALL)
		{
			modelWrappedBody.render(partialTickTime);
		}

		else if (cocoonSize == EnumCocoonSize.HUGE)
		{
			GL11.glPushMatrix();
			GL11.glScaled(1.5D, 1.5D, 1.5D);
			modelWrappedBody.render(partialTickTime);
			GL11.glPopMatrix();
		}
		
		else if (cocoonSize == EnumCocoonSize.NORMAL)
		{
			modelWrappedHead.render(partialTickTime);
			modelWrappedBody.render(partialTickTime);
		}

		else if (cocoonSize == EnumCocoonSize.TALL)
		{
			modelWrappedBodyTall.render(partialTickTime);
		}

		else if (cocoonSize == EnumCocoonSize.INSECT)
		{
			GL11.glPushMatrix();
			{
				GL11.glTranslated(0.0D, -0.75D, 0.0D);
				modelInsectCocoon.render(partialTickTime);
			}
			GL11.glPopMatrix();
		}

		else if (cocoonSize == EnumCocoonSize.BEE)
		{
			GL11.glPushMatrix();
			{
				GL11.glTranslated(0.0D, -0.75D, 0.0D);
				modelBeeCocoon.render(partialTickTime);
			}
			GL11.glPopMatrix();
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}