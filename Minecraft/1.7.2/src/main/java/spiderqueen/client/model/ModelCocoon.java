package spiderqueen.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.enums.EnumCocoonSize;
import spiderqueen.enums.EnumCocoonType;

public class ModelCocoon extends ModelBase
{
	private final ModelRenderer modelWrappedHead;
	private final ModelRenderer modelWrappedBody;
	private final ModelRenderer modelWrappedBodyTall;
	private final ModelRenderer modelVisibleFlatHead;
	
	private final ModelRenderer modelSheepFlatHead;

	private final ModelRenderer modelWolfHead;
	private final ModelRenderer modelWolfEarLeft;
	private final ModelRenderer modelWolfEarRight;
	private final ModelRenderer modelWolfNose;

	private final ModelRenderer modelChickenBeak;
	private final ModelRenderer modelChickenHead;

	private final ModelRenderer modelEndermanHead;
	
	private final ModelRenderer modelVillagerHead;
	private final ModelRenderer modelVillagerNose;

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

		modelSheepFlatHead = new ModelRenderer(this, 2, 2);
		modelSheepFlatHead.addBox(-4F, -4F, -7F, 8, 8, 6, 0F);
		modelSheepFlatHead.setRotationPoint(0F, -16F, 0F);

		modelWolfHead = new ModelRenderer(this, 0, 0);
		modelWolfHead.addBox(-3, -3, -2, 6, 6, 4, 0F);
		modelWolfHead.setRotationPoint(0F, -14.5F, -7F);

		modelWolfEarLeft = new ModelRenderer(this, 16, 14);
		modelWolfEarLeft.addBox(-3, -5, 0, 2, 2, 1, 0F);
		modelWolfEarLeft.setRotationPoint(0F, -14.5F, -7F);

		modelWolfEarRight = new ModelRenderer(this, 16, 14);
		modelWolfEarRight.addBox(1, -5, 0, 2, 2, 1, 0F);
		modelWolfEarRight.setRotationPoint(0F, -14.5F, -7F);

		modelWolfNose = new ModelRenderer(this, 0, 10);
		modelWolfNose.addBox(-2, 0, -5, 3, 3, 4, 0F);
		modelWolfNose.setRotationPoint(0.5F, -14.5F, -7F);

		modelChickenHead = new ModelRenderer(this, 0, 0);
		modelChickenHead.addBox(-2F, -6F, -2F, 4, 6, 3, 0.0F);
		modelChickenHead.setRotationPoint(0F, -4F, -7F);

		modelChickenBeak = new ModelRenderer(this, 14, 0);
		modelChickenBeak.addBox(-2F, -4F, -4F, 4, 2, 2, 0.0F);
		modelChickenBeak.setRotationPoint(0F, -4F, -7F);

		modelEndermanHead = new ModelRenderer(this, 32, 16);
		modelEndermanHead.addBox(-4F, -8F, -4F, 8, 8, 8);
		modelEndermanHead.setRotationPoint(0F, -16F, 0F);

		modelVillagerHead = new ModelRenderer(this, 32, 14);
		modelVillagerHead.addBox(-4F, -10F, -4F, 8, 10, 8);
		modelVillagerHead.setRotationPoint(0F, -16F, 0F);
		
		modelVillagerNose = new ModelRenderer(this, 56, 0);
		modelVillagerNose.addBox(-1F, -1F, -2F, 2, 4, 2);
		modelVillagerNose.setRotationPoint(0F, -18F, -3.8F);
	}

	public void render(Entity entity, float posX, float posY, float posZ, float rotationYaw, float rotationPitch, float partialTickTime)
	{
		final EntityCocoon entityCocoon = (EntityCocoon)entity;
		final EnumCocoonType cocoonType = entityCocoon.getCocoonType();
		final EnumCocoonSize cocoonSize = cocoonType.getCocoonSize();

		renderCocoonBase(cocoonSize, partialTickTime);

		if (cocoonType == EnumCocoonType.WOLF)
		{
			modelWolfHead.render(partialTickTime);
			modelWolfEarLeft.render(partialTickTime);
			modelWolfEarRight.render(partialTickTime);
			modelWolfNose.render(partialTickTime);
		}

		else if (cocoonType == EnumCocoonType.CHICKEN)
		{
			modelChickenHead.render(partialTickTime);
			modelChickenBeak.render(partialTickTime);
		}

		else if (cocoonType == EnumCocoonType.SHEEP)
		{
			modelSheepFlatHead.render(partialTickTime);
		}

		else if (cocoonType == EnumCocoonType.VILLAGER)
		{
			modelVillagerNose.render(partialTickTime);
			modelVillagerHead.render(partialTickTime);
		}

		else if (cocoonType == EnumCocoonType.ENDERMAN)
		{
			modelEndermanHead.render(partialTickTime);
		}

		else
		{
			modelVisibleFlatHead.render(partialTickTime);
		}
	}

	private void renderCocoonBase(EnumCocoonSize cocoonSize, float partialTickTime)
	{
		if (cocoonSize == EnumCocoonSize.SMALL)
		{
			modelWrappedBody.render(partialTickTime);
		}

		else if (cocoonSize == EnumCocoonSize.NORMAL)
		{
			modelWrappedHead.render(partialTickTime);
			modelWrappedBody.render(partialTickTime);
		}

		else
		{
			modelWrappedBodyTall.render(partialTickTime);
		}
	}
}
