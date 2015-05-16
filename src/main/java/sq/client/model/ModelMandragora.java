package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMandragora extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer torso;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer breast1;
	private final ModelRenderer roots1;
	private final ModelRenderer skirtF;
	private final ModelRenderer skirtR;
	private final ModelRenderer skirtB;
	private final ModelRenderer skirtL;
	private final ModelRenderer hair1;
	private final ModelRenderer hair2;
	private final ModelRenderer bud1;
	private final ModelRenderer bud2;
	private final ModelRenderer bud3;
	private final ModelRenderer fruit;
	private final ModelRenderer roots2;

	public ModelMandragora()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 32, 4);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 8F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		torso = new ModelRenderer(this, 44, 18);
		torso.addBox(-3F, -10F, -2F, 6, 10, 4);
		torso.setRotationPoint(0F, 18F, 0F);
		torso.setTextureSize(64, 32);
		torso.mirror = true;
		setRotation(torso, 0F, 0F, 0F);
		leftArm = new ModelRenderer(this, 56, 0);
		leftArm.addBox(-1F, -1F, -1F, 2, 10, 2);
		leftArm.setRotationPoint(4F, 9F, 0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0F, 0F, 0F);
		rightArm = new ModelRenderer(this, 56, 0);
		rightArm.addBox(-1F, -1F, -1F, 2, 10, 2);
		rightArm.setRotationPoint(-4F, 9F, 0F);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0F, 0F, 0F);
		breast1 = new ModelRenderer(this, 46, 20);
		breast1.addBox(-3F, -7F, -7F, 6, 3, 2);
		breast1.setRotationPoint(0F, 18F, 0F);
		breast1.setTextureSize(64, 32);
		breast1.mirror = true;
		setRotation(breast1, -0.5934119F, 0F, 0F);
		roots1 = new ModelRenderer(this, 0, 18);
		roots1.addBox(-4F, -8F, -4F, 8, 6, 8);
		roots1.setRotationPoint(0F, 26F, 0F);
		roots1.setTextureSize(64, 32);
		roots1.mirror = true;
		setRotation(roots1, 0F, 0F, 0F);
		skirtF = new ModelRenderer(this, 0, 0);
		skirtF.addBox(-4F, 0F, 0F, 8, 8, 0);
		skirtF.setRotationPoint(0F, 18F, -4F);
		skirtF.setTextureSize(64, 32);
		skirtF.mirror = true;
		setRotation(skirtF, -0.3490659F, 0F, 0F);
		skirtR = new ModelRenderer(this, 0, 0);
		skirtR.addBox(-4F, 0F, 0F, 8, 8, 0);
		skirtR.setRotationPoint(-4F, 18F, 0F);
		skirtR.setTextureSize(64, 32);
		skirtR.mirror = true;
		setRotation(skirtR, -0.3490659F, 1.570796F, 0F);
		skirtB = new ModelRenderer(this, 0, 0);
		skirtB.addBox(-4F, 0F, 0F, 8, 8, 0);
		skirtB.setRotationPoint(0F, 18F, 4F);
		skirtB.setTextureSize(64, 32);
		skirtB.mirror = true;
		setRotation(skirtB, -0.3490659F, 3.141593F, 0F);
		skirtL = new ModelRenderer(this, 0, 0);
		skirtL.addBox(-4F, 0F, 0F, 8, 8, 0);
		skirtL.setRotationPoint(4F, 18F, 0F);
		skirtL.setTextureSize(64, 32);
		skirtL.mirror = true;
		setRotation(skirtL, -0.3490659F, -1.570796F, 0F);
		hair1 = new ModelRenderer(this, 0, 9);
		hair1.addBox(-5F, -15F, 0F, 10, 7, 0);
		hair1.setRotationPoint(0F, 8F, 0F);
		hair1.setTextureSize(64, 32);
		hair1.mirror = true;
		setRotation(hair1, 0F, 0.7853982F, 0F);
		hair2 = new ModelRenderer(this, 0, 9);
		hair2.addBox(-5F, -15F, 0F, 10, 7, 0);
		hair2.setRotationPoint(0F, 8F, 0F);
		hair2.setTextureSize(64, 32);
		hair2.mirror = true;
		setRotation(hair2, 0F, -0.7853982F, 0F);
		bud1 = new ModelRenderer(this, 16, 0);
		bud1.addBox(-2F, -12F, -3F, 6, 0, 6);
		bud1.setRotationPoint(0F, 8F, 0F);
		bud1.setTextureSize(64, 32);
		bud1.mirror = true;
		setRotation(bud1, 0F, -0.8178613F, -0.4088957F);
		bud2 = new ModelRenderer(this, 16, 0);
		bud2.addBox(-4F, -11F, 2F, 6, 0, 6);
		bud2.setRotationPoint(0F, 8F, 0F);
		bud2.setTextureSize(64, 32);
		bud2.mirror = true;
		setRotation(bud2, 0.7435103F, -1.524318F, 0.0371755F);
		bud3 = new ModelRenderer(this, 16, 0);
		bud3.addBox(-3F, -12F, -3F, 6, 0, 6);
		bud3.setRotationPoint(0F, 8F, 0F);
		bud3.setTextureSize(64, 32);
		bud3.mirror = true;
		setRotation(bud3, -0.2974041F, -0.6691592F, 0F);
		fruit = new ModelRenderer(this, 0, 22);
		fruit.addBox(1F, -11F, -4F, 2, 2, 2);
		fruit.setRotationPoint(0F, 8F, 0F);
		fruit.setTextureSize(64, 32);
		fruit.mirror = true;
		setRotation(fruit, 0F, 0F, 0F);
		roots2 = new ModelRenderer(this, 0, 18);
		roots2.addBox(0F, 0F, 0F, 8, 6, 8);
		roots2.setRotationPoint(-4F, 24F, -4F);
		roots2.setTextureSize(64, 32);
		roots2.mirror = true;
		setRotation(roots2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		head.render(f5);
		torso.render(f5);
		leftArm.render(f5);
		rightArm.render(f5);
		breast1.render(f5);
		roots1.render(f5);
		skirtF.render(f5);
		skirtR.render(f5);
		skirtB.render(f5);
		skirtL.render(f5);
		hair1.render(f5);
		hair2.render(f5);
		bud1.render(f5);
		bud2.render(f5);
		bud3.render(f5);
		fruit.render(f5);
		roots2.render(f5);
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

		rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
		leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.2F;
		rightArm.rotateAngleZ = 0.0F;
		leftArm.rotateAngleZ = 0.0F;
		rightArm.rotateAngleY = 0.0F;
		leftArm.rotateAngleY = 0.0F;
		
		if(onGround > -9990F)
		{
			float f6 = onGround;
			rightArm.rotationPointZ = MathHelper.sin(torso.rotateAngleY) * 3.5F;
			rightArm.rotationPointX = -MathHelper.cos(torso.rotateAngleY) * 3.5F;
			leftArm.rotationPointZ = -MathHelper.sin(torso.rotateAngleY) * 3.5F;
			leftArm.rotationPointX = MathHelper.cos(torso.rotateAngleY) * 3.5F;
			rightArm.rotateAngleY += torso.rotateAngleY;
			leftArm.rotateAngleY += torso.rotateAngleY;
			leftArm.rotateAngleX += torso.rotateAngleY;
			f6 = 1.0F - onGround;
			f6 *= f6;
			f6 *= f6;
			f6 = 1.0F - f6;
			float f7 = MathHelper.sin(f6 * 3.141593F);
			float f8 = MathHelper.sin(onGround * 3.141593F) * -(head.rotateAngleX - 0.7F) * 0.75F;
			rightArm.rotateAngleX -= (double)f7 * 1.2D + (double)f8;
			rightArm.rotateAngleY += torso.rotateAngleY * 2.0F;
			rightArm.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) * -0.4F;
		}
		
		rightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		leftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		rightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		leftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
	}
}
