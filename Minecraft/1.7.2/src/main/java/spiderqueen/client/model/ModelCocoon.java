package spiderqueen.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import spiderqueen.entity.EntityCocoon;
import spiderqueen.enums.EnumCocoonType;

public class ModelCocoon extends ModelBase
{
	private final ModelRenderer modelVisibleFlatHead;

	private final ModelRenderer modelWolfHead;
	private final ModelRenderer modelWolfEarLeft;
	private final ModelRenderer modelWolfEarRight;
	private final ModelRenderer modelWolfNose;
	
	private final ModelRenderer modelWrappedHead;
	private final ModelRenderer modelWrappedBody;

	public ModelCocoon()
	{
		super();

		modelWrappedBody = new ModelRenderer(this, 16, 8);
		modelWrappedBody.addBox(0F, 0F, 0F, 12, 12, 12, 0F);
		modelWrappedBody.setRotationPoint(-6F, -12F, -6F);

		modelWrappedHead = new ModelRenderer(this, 20, 13);
		modelWrappedHead.addBox(0F, 0F, 0F, 10, 9, 10, 0F);
		modelWrappedHead.setRotationPoint(-5F, -21F, -5F);

		modelVisibleFlatHead = new ModelRenderer(this, 2, 2);
		modelVisibleFlatHead.addBox(-4F, -4F, -7F, 8, 8, 6, 0F);
		modelVisibleFlatHead.setRotationPoint(0F, -16F, -1F);

		modelWolfHead = new ModelRenderer(this, 0, 0);
		modelWolfHead.addBox(-3, -3, -2, 6, 6, 4, 0F);
		modelWolfHead.setRotationPoint(0F, -14.5F, -7F);

		modelWolfHead.rotateAngleX = 0F;
		modelWolfHead.rotateAngleY = 0F;
		modelWolfHead.rotateAngleZ = 0F;

		modelWolfEarLeft = new ModelRenderer(this, 16, 14);
		modelWolfEarLeft.addBox(-3, -5, 0, 2, 2, 1, 0F);
		modelWolfEarLeft.setRotationPoint(0F, -14.5F, -7F);

		modelWolfEarLeft.rotateAngleX = 0F;
		modelWolfEarLeft.rotateAngleY = 0F;
		modelWolfEarLeft.rotateAngleZ = 0F;

		modelWolfEarRight = new ModelRenderer(this, 16, 14);
		modelWolfEarRight.addBox(1, -5, 0, 2, 2, 1, 0F);
		modelWolfEarRight.setRotationPoint(0F, -14.5F, -7F);

		modelWolfEarRight.rotateAngleX = 0F;
		modelWolfEarRight.rotateAngleY = 0F;
		modelWolfEarRight.rotateAngleZ = 0F;

		modelWolfNose = new ModelRenderer(this, 0, 10);
		modelWolfNose.addBox(-2, 0, -5, 3, 3, 4, 0F);
		modelWolfNose.setRotationPoint(0.5F, -14.5F, -7F);

		modelWolfNose.rotateAngleX = 0F;
		modelWolfNose.rotateAngleY = 0F;
		modelWolfNose.rotateAngleZ = 0F;
	}

	public void render(Entity entity, float posX, float posY, float posZ, float rotationYaw, float rotationPitch, float partialTickTime)
	{
		final EntityCocoon entityCocoon = (EntityCocoon)entity;
		setRotationAngles(posX, posY, posZ, rotationYaw, rotationPitch, partialTickTime);

		modelWrappedBody.render(partialTickTime);
		modelWrappedHead.render(partialTickTime);
		
		if (entityCocoon.getCocoonType() == EnumCocoonType.Wolf)
		{
			modelWolfHead.render(partialTickTime);
			modelWolfEarLeft.render(partialTickTime);
			modelWolfEarRight.render(partialTickTime);
			modelWolfNose.render(partialTickTime);
		}
		
		else
		{
			modelVisibleFlatHead.render(partialTickTime);
		}
	}

	public void setRotationAngles(float posX, float posY, float posZ, float rotationYaw, float rotationPitch, float partialTickTime)
	{
		modelWrappedBody.rotateAngleY = rotationYaw / 57.29578F;
		modelWrappedHead.rotateAngleY = rotationYaw / 57.29578F;
		modelVisibleFlatHead.rotateAngleY = rotationYaw / 57.29578F;
	}
}
