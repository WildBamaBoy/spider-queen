package spiderqueen.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCocoon extends ModelBase
{
	private final ModelRenderer modelVisibleHead;
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

		modelVisibleHead = new ModelRenderer(this, 2, 2);
		modelVisibleHead.addBox(-4F, -4F, -7F, 8, 8, 6, 0F);
		modelVisibleHead.setRotationPoint(0F, -16F, -1F);
	}

	public void render(Entity entity, float posX, float posY, float posZ, float rotationYaw, float rotationPitch, float partialTickTime)
	{
		setRotationAngles(posX, posY, posZ, rotationYaw, rotationPitch, partialTickTime);
		
		modelWrappedBody.render(partialTickTime);
		modelWrappedHead.render(partialTickTime);
		modelVisibleHead.render(partialTickTime);
	}

	public void setRotationAngles(float posX, float posY, float posZ, float rotationYaw, float rotationPitch, float partialTickTime)
	{
		modelWrappedBody.rotateAngleY = rotationYaw / 57.29578F;
		modelWrappedHead.rotateAngleY = rotationYaw / 57.29578F;
		modelVisibleHead.rotateAngleY = rotationYaw / 57.29578F;
	}
}
