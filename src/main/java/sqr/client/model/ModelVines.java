package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVines extends ModelBase
{
	
	public ModelVines()
	{
		final int yy = 7;
		this.Vinehead = new ModelRenderer(this, 0, 0);
		this.Vinehead.addBox(-1, -1, -20, 2, 2, 20, 0F);
		this.Vinehead.setRotationPoint(0, 24 + yy, 18);
		
		this.Vinehead.rotateAngleX = -1.041001F;
		this.Vinehead.rotateAngleY = 0F;
		this.Vinehead.rotateAngleZ = 0F;
		this.Vinehead.mirror = false;
		
		this.Vinehead2 = new ModelRenderer(this, 0, 0);
		this.Vinehead2.addBox(-2, -2, -2, 4, 4, 4, 0F);
		this.Vinehead2.setRotationPoint(0, 24 + yy, 0);
		
		this.Vinehead2.rotateAngleX = 0F;
		this.Vinehead2.rotateAngleY = 0F;
		this.Vinehead2.rotateAngleZ = 0F;
		this.Vinehead2.mirror = false;
		
		this.Vinehead3 = new ModelRenderer(this, 0, 0);
		this.Vinehead3.addBox(-1, -1, -14, 2, 2, 14, 0F);
		this.Vinehead3.setRotationPoint(0, 7 + yy, 8);
		
		this.Vinehead3.rotateAngleX = 0F;
		this.Vinehead3.rotateAngleY = 0F;
		this.Vinehead3.rotateAngleZ = 0F;
		this.Vinehead3.mirror = false;
		
		this.Vinehead4 = new ModelRenderer(this, 0, 0);
		this.Vinehead4.addBox(0, 0, 0, 2, 2, 20, 0F);
		this.Vinehead4.setRotationPoint(-1, 23 + yy, -17);
		
		this.Vinehead4.rotateAngleX = 1.003826F;
		this.Vinehead4.rotateAngleY = 0F;
		this.Vinehead4.rotateAngleZ = 0F;
		this.Vinehead4.mirror = false;
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.Vinehead.render(f5);
		// Vinehead2.render(f5);
		this.Vinehead3.render(f5);
		this.Vinehead4.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		/*
		 * Head.rotateAngleY = f3 / 57.29578F; Head.rotateAngleX = f4 /
		 * 57.29578F;
		 * 
		 * bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F)
		 * * 2.0F * f1 * 0.5F; bipedLeftArm.rotateAngleX = MathHelper.cos(f *
		 * 0.6662F) * 2.0F * f1 * 0.2F; bipedRightArm.rotateAngleZ = 0.0F;
		 * bipedLeftArm.rotateAngleZ = 0.0F; bipedRightArm.rotateAngleY = 0.0F;
		 * bipedLeftArm.rotateAngleY = 0.0F; if(onGround > -9990F) { float f6 =
		 * onGround; bipedRightArm.rotationPointZ =
		 * MathHelper.sin(torso.rotateAngleY) * 3.5F;
		 * bipedRightArm.rotationPointX = -MathHelper.cos(torso.rotateAngleY) *
		 * 3.5F; bipedLeftArm.rotationPointZ =
		 * -MathHelper.sin(torso.rotateAngleY) * 3.5F;
		 * bipedLeftArm.rotationPointX = MathHelper.cos(torso.rotateAngleY) *
		 * 3.5F; bipedRightArm.rotateAngleY += torso.rotateAngleY;
		 * bipedLeftArm.rotateAngleY += torso.rotateAngleY;
		 * bipedLeftArm.rotateAngleX += torso.rotateAngleY; f6 = 1.0F -
		 * onGround; f6 *= f6; f6 *= f6; f6 = 1.0F - f6; float f7 =
		 * MathHelper.sin(f6 * 3.141593F); float f8 = MathHelper.sin(onGround *
		 * 3.141593F) * -(Head.rotateAngleX - 0.7F) * 0.75F;
		 * bipedRightArm.rotateAngleX -= (double)f7 * 1.2D + (double)f8;
		 * bipedRightArm.rotateAngleY += torso.rotateAngleY * 2.0F;
		 * bipedRightArm.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) *
		 * -0.4F; } bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) *
		 * 0.05F + 0.05F; bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 *
		 * 0.09F) * 0.05F + 0.05F; bipedRightArm.rotateAngleX +=
		 * MathHelper.sin(f2 * 0.067F) * 0.05F; bipedLeftArm.rotateAngleX -=
		 * MathHelper.sin(f2 * 0.067F) * 0.05F;
		 */
		/*
		 * float f6 = 0.7853982F; Leg1.rotateAngleZ = -f6; Leg2.rotateAngleZ =
		 * f6; Leg3.rotateAngleZ = -f6 * 0.74F; Leg4.rotateAngleZ = f6 * 0.74F;
		 * Leg5.rotateAngleZ = -f6 * 0.74F; Leg6.rotateAngleZ = f6 * 0.74F;
		 * Leg7.rotateAngleZ = -f6; Leg8.rotateAngleZ = f6; float f7 = -0F;
		 * float f8 = 0.3926991F; Leg1.rotateAngleY = f8 * 2.0F + f7;
		 * Leg2.rotateAngleY = -f8 * 2.0F - f7; Leg3.rotateAngleY = f8 * 1.0F +
		 * f7; Leg4.rotateAngleY = -f8 * 1.0F - f7; Leg5.rotateAngleY = -f8 *
		 * 1.0F + f7; Leg6.rotateAngleY = f8 * 1.0F - f7; Leg7.rotateAngleY =
		 * -f8 * 2.0F + f7; Leg8.rotateAngleY = f8 * 2.0F - f7; float f9 =
		 * -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1; float f10 =
		 * -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1; float
		 * f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
		 * float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F)
		 * * f1; float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F)
		 * * f1; float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) *
		 * 0.4F) * f1; float f15 = Math.abs(MathHelper.sin(f * 0.6662F +
		 * 1.570796F) * 0.4F) * f1; float f16 = Math.abs(MathHelper.sin(f *
		 * 0.6662F + 4.712389F) * 0.4F) * f1; Leg1.rotateAngleY += f9;
		 * Leg2.rotateAngleY += -f9; Leg3.rotateAngleY += f10; Leg4.rotateAngleY
		 * += -f10; Leg5.rotateAngleY += f11; Leg6.rotateAngleY += -f11;
		 * Leg7.rotateAngleY += f12; Leg8.rotateAngleY += -f12;
		 * Leg1.rotateAngleZ += f13; Leg2.rotateAngleZ += -f13;
		 * Leg3.rotateAngleZ += f14; Leg4.rotateAngleZ += -f14;
		 * Leg5.rotateAngleZ += f15; Leg6.rotateAngleZ += -f15;
		 * Leg7.rotateAngleZ += f16; Leg8.rotateAngleZ += -f16;
		 */
	}
	
	/*
	 * public ModelRenderer spiderHead; public ModelRenderer spiderNeck; public
	 * ModelRenderer spiderBody; public ModelRenderer spiderLeg1; public
	 * ModelRenderer spiderLeg2; public ModelRenderer spiderLeg3; public
	 * ModelRenderer spiderLeg4; public ModelRenderer spiderLeg5; public
	 * ModelRenderer spiderLeg6; public ModelRenderer spiderLeg7; public
	 * ModelRenderer spiderLeg8;
	 */
	public ModelRenderer Vinehead;
	public ModelRenderer Vinehead2;
	public ModelRenderer Vinehead3;
	public ModelRenderer Vinehead4;
}
