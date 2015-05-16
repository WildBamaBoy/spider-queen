package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelYuki extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer body1;
	private final ModelRenderer rightArm2;
	private final ModelRenderer leftArm;
	private final ModelRenderer belt;
	private final ModelRenderer hairBun;
	private final ModelRenderer ribbon;
	private final ModelRenderer rightArm1;
	private final ModelRenderer body2;

	public ModelYuki()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, -3F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.1745329F, 0F, 0F);
		body1 = new ModelRenderer(this, 24, 16);
		body1.addBox(-4F, 0F, -2F, 8, 12, 4);
		body1.setRotationPoint(0F, -3F, 0F);
		body1.setTextureSize(64, 32);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		rightArm2 = new ModelRenderer(this, 48, 18);
		rightArm2.addBox(-3F, -3F, -1F, 6, 12, 2);
		rightArm2.setRotationPoint(-4F, 5F, -3F);
		rightArm2.setTextureSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, -3.141593F, 0.3316126F, 0.4729842F);
		leftArm = new ModelRenderer(this, 40, 18);
		leftArm.addBox(-6F, -1F, -1F, 6, 12, 2);
		leftArm.setRotationPoint(5F, -2F, 0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0F, 0F, -0.4363323F);
		belt = new ModelRenderer(this, 32, 0);
		belt.addBox(0F, 0F, 0F, 10, 1, 6);
		belt.setRotationPoint(-5F, 6F, -3F);
		belt.setTextureSize(64, 32);
		belt.mirror = true;
		setRotation(belt, 0F, 0F, 0F);
		hairBun = new ModelRenderer(this, 32, 7);
		hairBun.addBox(-2F, -8F, 6F, 4, 4, 4);
		hairBun.setRotationPoint(0F, -3F, 0F);
		hairBun.setTextureSize(64, 32);
		hairBun.mirror = true;
		setRotation(hairBun, 0.5585054F, 0F, 0F);
		ribbon = new ModelRenderer(this, 48, 7);
		ribbon.addBox(-3F, 0F, 0F, 6, 1, 5);
		ribbon.setRotationPoint(0F, 6F, 3F);
		ribbon.setTextureSize(64, 32);
		ribbon.mirror = true;
		setRotation(ribbon, -0.9293878F, 0F, 0F);
		rightArm1 = new ModelRenderer(this, 48, 18);
		rightArm1.addBox(0F, 0F, 0F, 6, 10, 2);
		rightArm1.setRotationPoint(-3F, 9F, -3F);
		rightArm1.setTextureSize(64, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.257087F, 0.0367392F, -2.663896F);
		body2 = new ModelRenderer(this, 0, 16);
		body2.addBox(-4F, 0F, -2F, 8, 12, 4);
		body2.setRotationPoint(0F, 9F, 0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		head.render(f5);
		body1.render(f5);
		rightArm2.render(f5);
		leftArm.render(f5);
		belt.render(f5);
		hairBun.render(f5);
		ribbon.render(f5);
		rightArm1.render(f5);
		body2.render(f5);
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
		hairBun.rotateAngleX = head.rotateAngleX;
		hairBun.rotateAngleY = head.rotateAngleY;
	}

}
