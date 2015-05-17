package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import sq.core.SpiderCore;

public class ModelFly extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rearEnd1;
	private final ModelRenderer rearEnd2;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer mouth;
	private final ModelRenderer wing1;
	private final ModelRenderer wing2;

	public ModelFly()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 19, 0);
		head.addBox(-3F, -3F, -4F, 6, 5, 5);
		head.setRotationPoint(0F, 17F, -3F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-2F, -3F, -5F, 4, 4, 5);
		body.setRotationPoint(0F, 18F, 2F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, -0.1745329F, 0F, 0F);
		rearEnd1 = new ModelRenderer(this, 0, 9);
		rearEnd1.addBox(-4F, -2F, 0F, 6, 5, 5);
		rearEnd1.setRotationPoint(1F, 17F, 2F);
		rearEnd1.setTextureSize(64, 32);
		rearEnd1.mirror = true;
		setRotation(rearEnd1, -0.1745329F, 0F, 0F);
		rearEnd2 = new ModelRenderer(this, 22, 14);
		rearEnd2.addBox(-3F, -1F, 5F, 4, 3, 2);
		rearEnd2.setRotationPoint(1F, 17F, 2F);
		rearEnd2.setTextureSize(64, 32);
		rearEnd2.mirror = true;
		setRotation(rearEnd2, -0.1745329F, 0F, 0F);
		leg1 = new ModelRenderer(this, 16, -1);
		leg1.addBox(0F, -1F, 0F, 0, 5, 1);
		leg1.setRotationPoint(-2F, 19F, -3F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 16, -1);
		leg2.addBox(0F, 0F, 0F, 0, 5, 1);
		leg2.setRotationPoint(-2F, 18F, -1F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 16, -1);
		leg3.addBox(0F, 0F, 0F, 0, 5, 1);
		leg3.setRotationPoint(-2F, 18F, 1F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 16, -1);
		leg4.addBox(0F, 0F, 0F, 0, 5, 1);
		leg4.setRotationPoint(2F, 18F, 1F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		leg5 = new ModelRenderer(this, 16, -1);
		leg5.addBox(2F, 18F, -1F, 0, 5, 1);
		leg5.setRotationPoint(0F, 0F, 0F);
		leg5.setTextureSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, 0F, 0F, 0F);
		leg6 = new ModelRenderer(this, 16, -1);
		leg6.addBox(0F, 0F, 0F, 0, 5, 1);
		leg6.setRotationPoint(2F, 18F, -3F);
		leg6.setTextureSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0F, 0F, 0F);
		mouth = new ModelRenderer(this, 41, 5);
		mouth.addBox(-1F, 0F, -5F, 2, 2, 2);
		mouth.setRotationPoint(0F, 17F, -3F);
		mouth.setTextureSize(64, 32);
		mouth.mirror = true;
		setRotation(mouth, 0F, 0F, 0F);
		wing1 = new ModelRenderer(this, 33, 14);
		wing1.addBox(-4F, -1F, 0F, 4, 0, 5);
		wing1.setRotationPoint(0F, 15F, -1F);
		wing1.setTextureSize(64, 32);
		wing1.mirror = true;
		setRotation(wing1, -0.122173F, -0.2617994F, 0F);
		wing2 = new ModelRenderer(this, 33, 14);
		wing2.addBox(0F, -1F, 0F, 4, 0, 5);
		wing2.setRotationPoint(0F, 15F, -1F);
		wing2.setTextureSize(64, 32);
		wing2.mirror = true;
		setRotation(wing2, -0.122173F, 0.2617994F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		head.render(f5);
		body.render(f5);
		rearEnd1.render(f5);
		rearEnd2.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
		mouth.render(f5);
		wing1.render(f5);
		wing2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{

		head.rotateAngleY = f3 / 57.29578F;
		head.rotateAngleX = f4 / 57.29578F;

		mouth.rotateAngleY = head.rotateAngleY; mouth.rotateAngleX = head.rotateAngleX; mouth.rotateAngleZ = head.rotateAngleZ;

		leg1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
		leg1.rotateAngleY = 0.0F;

		leg2.rotateAngleX = leg1.rotateAngleX; leg2.rotateAngleY = leg1.rotateAngleY; leg2.rotateAngleZ = leg1.rotateAngleZ; 
		leg3.rotateAngleX = leg1.rotateAngleX; leg3.rotateAngleY = leg1.rotateAngleY; leg3.rotateAngleZ = leg1.rotateAngleZ; 
		leg4.rotateAngleX = leg1.rotateAngleX; leg4.rotateAngleY = leg1.rotateAngleY; leg4.rotateAngleZ = leg1.rotateAngleZ; 
		leg5.rotateAngleX = leg1.rotateAngleX; leg5.rotateAngleY = leg1.rotateAngleY; leg5.rotateAngleZ = leg1.rotateAngleZ; 
		leg6.rotateAngleX = leg1.rotateAngleX; leg6.rotateAngleY = leg1.rotateAngleY; leg6.rotateAngleZ = leg1.rotateAngleZ; 

		wing1.rotateAngleX = -0.122173F + ((float)SpiderCore.rand.nextInt(5)) * 0.122173F;
		wing2.rotateAngleX = -0.122173F + ((float)SpiderCore.rand.nextInt(5)) * 0.122173F;
	}
}
