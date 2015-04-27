package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import sqr.core.SQR;

// Referenced classes of package net.minecraft.src:
//            ModelBase, MathHelper, ModelRenderer

public class ModelSpiderQueen extends ModelBase
{
	
	public ModelSpiderQueen()
	{
		this.bipedHead = new ModelRenderer(this, 32, 4);
		this.bipedHead.addBox(-4, -8, -4, 8, 8, 8, 0F);
		this.bipedHead.setRotationPoint(0, 3, -3);
		
		this.bipedHead.rotateAngleX = 3.100328E-17F;
		this.bipedHead.rotateAngleY = 0F;
		this.bipedHead.rotateAngleZ = 0F;
		this.bipedHead.mirror = false;
		
		this.Body = new ModelRenderer(this, 1, 0);
		this.Body.addBox(-3, -3, -3, 6, 6, 5, 0F);
		this.Body.setRotationPoint(0, 15, 0);
		
		this.Body.rotateAngleX = 0F;
		this.Body.rotateAngleY = 0F;
		this.Body.rotateAngleZ = 0F;
		this.Body.mirror = false;
		
		this.RearEnd = new ModelRenderer(this, 1, 11);
		this.RearEnd.addBox(-5, -4, -6, 10, 10, 11, 0F);
		this.RearEnd.setRotationPoint(0, 11, 7);
		
		this.RearEnd.rotateAngleX = 1.230652E-16F;
		this.RearEnd.rotateAngleY = 0F;
		this.RearEnd.rotateAngleZ = 0F;
		this.RearEnd.mirror = false;
		
		this.Leg1 = new ModelRenderer(this, 18, 0);
		this.Leg1.addBox(-15, -1, -1, 16, 2, 2, 0F);
		this.Leg1.setRotationPoint(-4, 15, 2);
		
		this.Leg1.rotateAngleX = 0.5759587F;
		this.Leg1.rotateAngleY = 0.1919862F;
		this.Leg1.rotateAngleZ = 0F;
		this.Leg1.mirror = false;
		
		this.Leg2 = new ModelRenderer(this, 18, 0);
		this.Leg2.addBox(-1, -1, -1, 16, 2, 2, 0F);
		this.Leg2.setRotationPoint(4, 15, 2);
		
		this.Leg2.rotateAngleX = -0.5759587F;
		this.Leg2.rotateAngleY = -0.1919862F;
		this.Leg2.rotateAngleZ = 0F;
		this.Leg2.mirror = false;
		
		this.Leg3 = new ModelRenderer(this, 18, 0);
		this.Leg3.addBox(-15, -1, -1, 16, 2, 2, 0F);
		this.Leg3.setRotationPoint(-4, 15, 1);
		
		this.Leg3.rotateAngleX = 0.2792527F;
		this.Leg3.rotateAngleY = 0.1919862F;
		this.Leg3.rotateAngleZ = 0F;
		this.Leg3.mirror = false;
		
		this.Leg4 = new ModelRenderer(this, 18, 0);
		this.Leg4.addBox(-1, -1, -1, 16, 2, 2, 0F);
		this.Leg4.setRotationPoint(4, 15, 1);
		
		this.Leg4.rotateAngleX = -0.2792527F;
		this.Leg4.rotateAngleY = -0.1919862F;
		this.Leg4.rotateAngleZ = 0F;
		this.Leg4.mirror = false;
		
		this.Leg5 = new ModelRenderer(this, 18, 0);
		this.Leg5.addBox(-15, -1, -1, 16, 2, 2, 0F);
		this.Leg5.setRotationPoint(-4, 15, 0);
		
		this.Leg5.rotateAngleX = -0.2792527F;
		this.Leg5.rotateAngleY = 0.1919862F;
		this.Leg5.rotateAngleZ = 0F;
		this.Leg5.mirror = false;
		
		this.Leg6 = new ModelRenderer(this, 18, 0);
		this.Leg6.addBox(-1, -1, -1, 16, 2, 2, 0F);
		this.Leg6.setRotationPoint(4, 15, 0);
		
		this.Leg6.rotateAngleX = 0.2792527F;
		this.Leg6.rotateAngleY = -0.1919862F;
		this.Leg6.rotateAngleZ = 0F;
		this.Leg6.mirror = false;
		
		this.Leg7 = new ModelRenderer(this, 18, 0);
		this.Leg7.addBox(-15, -1, -1, 16, 2, 2, 0F);
		this.Leg7.setRotationPoint(-4, 15, -1);
		
		this.Leg7.rotateAngleX = -0.5759587F;
		this.Leg7.rotateAngleY = 0.1919862F;
		this.Leg7.rotateAngleZ = 0F;
		this.Leg7.mirror = false;
		
		this.Leg8 = new ModelRenderer(this, 18, 0);
		this.Leg8.addBox(-1, -1, -1, 16, 2, 2, 0F);
		this.Leg8.setRotationPoint(4, 15, -1);
		
		this.Leg8.rotateAngleX = 0.5759587F;
		this.Leg8.rotateAngleY = -0.1919862F;
		this.Leg8.rotateAngleZ = 0F;
		this.Leg8.mirror = false;
		
		this.torso = new ModelRenderer(this, 44, 18);
		this.torso.addBox(-3, -10, -2, 6, 10, 4, 0F);
		this.torso.setRotationPoint(0, 13, -3);
		
		this.torso.rotateAngleX = 0F;
		this.torso.rotateAngleY = 0F;
		this.torso.rotateAngleZ = 0F;
		this.torso.mirror = false;
		
		this.bipedLeftArm = new ModelRenderer(this, 56, 0);
		this.bipedLeftArm.addBox(-1, -1, -1, 2, 10, 2, 0F);
		this.bipedLeftArm.setRotationPoint(4, 4, -3);
		
		this.bipedLeftArm.rotateAngleX = 0F;
		this.bipedLeftArm.rotateAngleY = 0F;
		this.bipedLeftArm.rotateAngleZ = 0F;
		this.bipedLeftArm.mirror = false;
		
		this.bipedRightArm = new ModelRenderer(this, 56, 0);
		this.bipedRightArm.addBox(-1, -1, -1, 2, 10, 2, 0F);
		this.bipedRightArm.setRotationPoint(-4, 4, -3);
		
		this.bipedRightArm.rotateAngleX = 0F;
		this.bipedRightArm.rotateAngleY = 0F;
		this.bipedRightArm.rotateAngleZ = 0F;
		this.bipedRightArm.mirror = false;
		
		this.boob1 = new ModelRenderer(this, 46, 20);
		this.boob1.addBox(-3, -7, -7, 6, 3, 2, 0F);
		this.boob1.setRotationPoint(0, 13, -3);
		
		this.boob1.rotateAngleX = -0.5424333F;
		this.boob1.rotateAngleY = 0F;
		this.boob1.rotateAngleZ = 0F;
		this.boob1.mirror = false;
		
		this.spinner = new ModelRenderer(this, 0, 0);
		this.spinner.addBox(-1, -3, 0, 2, 3, 1, 0F);
		this.spinner.setRotationPoint(2, 15, 11);
		
		this.spinner.rotateAngleX = -0.5876361F;
		this.spinner.rotateAngleY = 0F;
		this.spinner.rotateAngleZ = 0F;
		this.spinner.mirror = false;
		
		this.spinner2 = new ModelRenderer(this, 0, 0);
		this.spinner2.addBox(-1, -3, 0, 2, 3, 1, 0F);
		this.spinner2.setRotationPoint(-2, 15, 11);
		
		this.spinner2.rotateAngleX = -0.5876361F;
		this.spinner2.rotateAngleY = 0F;
		this.spinner2.rotateAngleZ = 0F;
		this.spinner2.mirror = false;
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		
		this.bipedHead.render(f5);
		this.Body.render(f5);
		this.RearEnd.render(f5);
		this.Leg1.render(f5);
		this.Leg2.render(f5);
		this.Leg3.render(f5);
		this.Leg4.render(f5);
		this.Leg5.render(f5);
		this.Leg6.render(f5);
		this.Leg7.render(f5);
		this.Leg8.render(f5);
		this.torso.render(f5);
		this.bipedLeftArm.render(f5);
		this.bipedRightArm.render(f5);
		this.spinner.render(f5);
		this.spinner2.render(f5);
		
		if (SQR.isMale == 0 & !SQR.tMale)
		{
			this.boob1.render(f5);
		}
		else
		{
			if (SQR.tMale)
			{
				SQR.setTMale(false);
			}
		}
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		// bipedHead.rotateAngleY = f3 / 57.29578F;
		// bipedHead.rotateAngleX = f4 / 57.29578F;
		final float f6 = 0.7853982F;
		this.Leg1.rotateAngleZ = -f6;
		this.Leg2.rotateAngleZ = f6;
		this.Leg3.rotateAngleZ = -f6 * 0.74F;
		this.Leg4.rotateAngleZ = f6 * 0.74F;
		this.Leg5.rotateAngleZ = -f6 * 0.74F;
		this.Leg6.rotateAngleZ = f6 * 0.74F;
		this.Leg7.rotateAngleZ = -f6;
		this.Leg8.rotateAngleZ = f6;
		final float f7 = -0F;
		final float f8 = 0.3926991F;
		this.Leg1.rotateAngleY = f8 * 2.0F + f7;
		this.Leg2.rotateAngleY = -f8 * 2.0F - f7;
		this.Leg3.rotateAngleY = f8 * 1.0F + f7;
		this.Leg4.rotateAngleY = -f8 * 1.0F - f7;
		this.Leg5.rotateAngleY = -f8 * 1.0F + f7;
		this.Leg6.rotateAngleY = f8 * 1.0F - f7;
		this.Leg7.rotateAngleY = -f8 * 2.0F + f7;
		this.Leg8.rotateAngleY = f8 * 2.0F - f7;
		final float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
		final float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
		final float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
		final float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * f1;
		final float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
		final float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
		final float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
		final float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;
		
		this.bipedHead.rotateAngleY = f3 / 57.29578F;
		this.bipedHead.rotateAngleX = f4 / 57.29578F;
		this.bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		if (this.isRiding)
		{
			this.bipedRightArm.rotateAngleX += -0.6283185F;
			this.bipedLeftArm.rotateAngleX += -0.6283185F;
		}
		/*
		 * if(field_1279_h) { bipedLeftArm.rotateAngleX =
		 * bipedLeftArm.rotateAngleX * 0.5F - 0.3141593F; } if(field_1278_i) {
		 * bipedRightArm.rotateAngleX = bipedRightArm.rotateAngleX * 0.5F -
		 * 0.3141593F; }
		 */
		this.bipedRightArm.rotateAngleY = 0.0F;
		this.bipedLeftArm.rotateAngleY = 0.0F;
		if (this.onGround > -9990F)
		{
			float ff6 = this.onGround;
			this.Body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(ff6) * 3.141593F * 2.0F) * 0.2F;
			this.bipedRightArm.rotationPointZ = MathHelper.sin(this.Body.rotateAngleY) * 4F - 3F;
			this.bipedRightArm.rotationPointX = -MathHelper.cos(this.Body.rotateAngleY) * 4F;
			this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.Body.rotateAngleY) * 4F - 3F;
			this.bipedLeftArm.rotationPointX = MathHelper.cos(this.Body.rotateAngleY) * 4F;
			this.bipedRightArm.rotateAngleY += this.Body.rotateAngleY;
			this.bipedLeftArm.rotateAngleY += this.Body.rotateAngleY;
			this.bipedLeftArm.rotateAngleX += this.Body.rotateAngleY;
			ff6 = 1.0F - this.onGround;
			ff6 *= ff6;
			ff6 *= ff6;
			ff6 = 1.0F - ff6;
			final float ff7 = MathHelper.sin(ff6 * 3.141593F);
			final float ff8 = MathHelper.sin(this.onGround * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
			this.bipedRightArm.rotateAngleX -= ff7 * 1.2D + ff8;
			this.bipedRightArm.rotateAngleY += this.Body.rotateAngleY * 2.0F;
			this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * 3.141593F) * -0.4F;
		}
		/*
		 * if(isSneak) { Body.rotateAngleX = 0.5F; bipedRightArm.rotateAngleX +=
		 * 0.4F; bipedLeftArm.rotateAngleX += 0.4F; bipedHead.rotationPointY =
		 * 1.0F; } else {
		 */
		this.Body.rotateAngleX = 0.0F;
		this.bipedHead.rotationPointY = 3F;
		// }
		this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
		
		this.Leg1.rotateAngleY += f9;
		this.Leg2.rotateAngleY += -f9;
		this.Leg3.rotateAngleY += f10;
		this.Leg4.rotateAngleY += -f10;
		this.Leg5.rotateAngleY += f11;
		this.Leg6.rotateAngleY += -f11;
		this.Leg7.rotateAngleY += f12;
		this.Leg8.rotateAngleY += -f12;
		this.Leg1.rotateAngleZ += f13;
		this.Leg2.rotateAngleZ += -f13;
		this.Leg3.rotateAngleZ += f14;
		this.Leg4.rotateAngleZ += -f14;
		this.Leg5.rotateAngleZ += f15;
		this.Leg6.rotateAngleZ += -f15;
		this.Leg7.rotateAngleZ += f16;
		this.Leg8.rotateAngleZ += -f16;
	}
	
	ModelRenderer bipedHead;
	ModelRenderer Body;
	ModelRenderer RearEnd;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Leg5;
	ModelRenderer Leg6;
	ModelRenderer Leg7;
	ModelRenderer Leg8;
	ModelRenderer torso;
	ModelRenderer bipedLeftArm;
	ModelRenderer bipedRightArm;
	ModelRenderer boob1;
	ModelRenderer spinner;
	ModelRenderer spinner2;
	public boolean field_1278_i;
}
