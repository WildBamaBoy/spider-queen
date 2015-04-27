package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBeetle extends ModelBase
{

	public ModelBeetle()
	{
		this.Head = new ModelRenderer(this,30, 6);
		this.Head.addBox(-4F, -4F, -8F, 8, 8, 8, 0F);
		this.Head.setRotationPoint(0F, 17F, -3F);
		this.Head.rotateAngleX = 0F;
		this.Head.rotateAngleY = 0F;
		this.Head.rotateAngleZ = 0F;
		this.Head.mirror = false;
		this.Body = new ModelRenderer(this,0, 12);
		this.Body.addBox(-4F, -7F, -3F, 10, 10, 10, 0F);
		this.Body.setRotationPoint(-1F, 18F, 0F);
		this.Body.rotateAngleX = 0F;
		this.Body.rotateAngleY = 0F;
		this.Body.rotateAngleZ = 0F;
		this.Body.mirror = false;
		this.Leg1 = new ModelRenderer(this,28, 0);
		this.Leg1.addBox(-9F, -1F, -1F, 10, 1, 1, 0F);
		this.Leg1.setRotationPoint(-1F, 19F, 2F);
		this.Leg1.rotateAngleX = 0.5759587F;
		this.Leg1.rotateAngleY = 0.1919862F;
		this.Leg1.rotateAngleZ = 0F;
		this.Leg1.mirror = false;
		this.Leg2 = new ModelRenderer(this,28, 0);
		this.Leg2.addBox(-1F, -1F, -1F, 10, 1, 1, 0F);
		this.Leg2.setRotationPoint(1F, 19F, 1F);
		this.Leg2.rotateAngleX = -0.5759587F;
		this.Leg2.rotateAngleY = -0.1919862F;
		this.Leg2.rotateAngleZ = 0F;
		this.Leg2.mirror = false;
		this.Leg3 = new ModelRenderer(this,28, 0);
		this.Leg3.addBox(-9F, -1F, -1F, 10, 1, 1, 0F);
		this.Leg3.setRotationPoint(-1F, 19F, 0F);
		this.Leg3.rotateAngleX = 0.2792527F;
		this.Leg3.rotateAngleY = 0.1570796F;
		this.Leg3.rotateAngleZ = 0F;
		this.Leg3.mirror = false;
		this.Leg4 = new ModelRenderer(this,28, 0);
		this.Leg4.addBox(-1F, -1F, -1F, 10, 1, 1, 0F);
		this.Leg4.setRotationPoint(1F, 19F, -1F);
		this.Leg4.rotateAngleX = -0.2792527F;
		this.Leg4.rotateAngleY = -0.1570796F;
		this.Leg4.rotateAngleZ = 0F;
		this.Leg4.mirror = false;
		this.Leg5 = new ModelRenderer(this,28, 0);
		this.Leg5.addBox(-9F, -1F, -1F, 10, 1, 1, 0F);
		this.Leg5.setRotationPoint(-1F, 19F, -2F);
		this.Leg5.rotateAngleX = -0.2792527F;
		this.Leg5.rotateAngleY = 0.05235988F;
		this.Leg5.rotateAngleZ = 0F;
		this.Leg5.mirror = false;
		this.Leg6 = new ModelRenderer(this,28, 0);
		this.Leg6.addBox(-1F, -1F, -1F, 10, 1, 1, 0F);
		this.Leg6.setRotationPoint(1F, 19F, -2F);
		this.Leg6.rotateAngleX = 0.2792527F;
		this.Leg6.rotateAngleY = -0.05235988F;
		this.Leg6.rotateAngleZ = 0F;
		this.Leg6.mirror = false;
		this.Horn1 = new ModelRenderer(this,24, 2);
		this.Horn1.addBox(-1F, 2F, -13F, 2, 2, 5, 0F);
		this.Horn1.setRotationPoint(0F, 17F, -3F);
		this.Horn1.rotateAngleX = 0F;
		this.Horn1.rotateAngleY = 0F;
		this.Horn1.rotateAngleZ = 0F;
		this.Horn1.mirror = false;
		this.Horn2 = new ModelRenderer(this,0, 10);
		this.Horn2.addBox(-1F, -12F + 6F, -14F, 2, 10, 2, 0F);
		this.Horn2.setRotationPoint(0F, 17F, -3F);
		this.Horn2.rotateAngleX = 0f;//.4363323F;
		this.Horn2.rotateAngleY = 0F;
		this.Horn2.rotateAngleZ = 0F;
		this.Horn2.mirror = false;
		this.Horn3 = new ModelRenderer(this,40, 26);
		this.Horn3.addBox(-3F, -14F + 6F, -14F, 6, 5, 1, 0F);
		this.Horn3.setRotationPoint(0F, 17F, -3F);
		this.Horn3.rotateAngleX = 0f;//.4363323F;
		this.Horn3.rotateAngleY = 0F;
		this.Horn3.rotateAngleZ = 0F;
		this.Horn3.mirror = false;
		this.Shell1 = new ModelRenderer(this,0, 1);
		this.Shell1.addBox(0F, 0F, 0F, 8, 1, 8, 0F);
		this.Shell1.setRotationPoint(1F, 11F, -3F);
		this.Shell1.rotateAngleX = 0.7435722F;
		this.Shell1.rotateAngleY = 0F;
		this.Shell1.rotateAngleZ = 0F;
		this.Shell1.mirror = false;
		this.Shell2 = new ModelRenderer(this,0, 1);
		this.Shell2.addBox(-9F, 0F, 0F, 8, 1, 8, 0F);
		this.Shell2.setRotationPoint(0F, 11F, -3F);
		this.Shell2.rotateAngleX = 0.7435722F;
		this.Shell2.rotateAngleY = 0F;
		this.Shell2.rotateAngleZ = 0F;
		this.Shell2.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.Head.render(f5);
		this.Body.render(f5);
		this.Leg1.render(f5);
		this.Leg2.render(f5);
		this.Leg3.render(f5);
		this.Leg4.render(f5);
		this.Leg5.render(f5);
		this.Leg6.render(f5);
		this.Horn1.render(f5);
		this.Horn2.render(f5);
		this.Horn3.render(f5);
		this.Shell1.render(f5);
		this.Shell2.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{

		this.Head.rotateAngleY = f3 / 57.29578F;
		this.Head.rotateAngleX = f4 / 57.29578F;


		this.Horn1.rotateAngleY = this.Head.rotateAngleY; this.Horn1.rotateAngleX = this.Head.rotateAngleX; this.Horn1.rotateAngleZ = this.Head.rotateAngleZ;
		this.Horn2.rotateAngleY = this.Head.rotateAngleY; this.Horn2.rotateAngleX = this.Head.rotateAngleX; this.Horn2.rotateAngleZ = this.Head.rotateAngleZ;
		this.Horn3.rotateAngleY = this.Head.rotateAngleY; this.Horn3.rotateAngleX = this.Head.rotateAngleX; this.Horn3.rotateAngleZ = this.Head.rotateAngleZ;


		final float f6 = 0.7853982F;
		this.Leg1.rotateAngleZ = -f6;
		this.Leg2.rotateAngleZ = f6;
		this.Leg3.rotateAngleZ = -f6 * 0.74F;
		this.Leg4.rotateAngleZ = f6 * 0.74F;
		this.Leg5.rotateAngleZ = -f6 * 0.74F;
		this.Leg6.rotateAngleZ = f6 * 0.74F;
		final float f7 = -0F;
		final float f8 = 0.3926991F;
		this.Leg1.rotateAngleY = f8 * 2.0F + f7;
		this.Leg2.rotateAngleY = -f8 * 2.0F - f7;
		this.Leg3.rotateAngleY = f8 * 1.0F + f7;
		this.Leg4.rotateAngleY = -f8 * 1.0F - f7;
		this.Leg5.rotateAngleY = -f8 * 1.0F + f7;
		this.Leg6.rotateAngleY = f8 * 1.0F - f7;
		final float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
		final float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
		final float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
		final float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * f1;
		final float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
		final float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
		final float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
		final float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;
		this.Leg1.rotateAngleY += f9;
		this.Leg2.rotateAngleY += -f9;
		this.Leg3.rotateAngleY += f10;
		this.Leg4.rotateAngleY += -f10;
		this.Leg5.rotateAngleY += f11;
		this.Leg6.rotateAngleY += -f11;
		this.Leg1.rotateAngleZ += f13;
		this.Leg2.rotateAngleZ += -f13;
		this.Leg3.rotateAngleZ += f14;
		this.Leg4.rotateAngleZ += -f14;
		this.Leg5.rotateAngleZ += f15;
		this.Leg6.rotateAngleZ += -f15;

		/*
		antenna1.rotateAngleY = head.rotateAngleY; antenna1.rotateAngleX = head.rotateAngleX;
		antenna2.rotateAngleY = head.rotateAngleY; antenna2.rotateAngleX = head.rotateAngleX;

		lash1.rotateAngleY = head.rotateAngleY; lash1.rotateAngleX = head.rotateAngleX;
		lash2.rotateAngleY = head.rotateAngleY; lash2.rotateAngleX = head.rotateAngleX;

        rightarm.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
        leftarm.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
        rightarm.rotateAngleZ = 0.0F;
        leftarm.rotateAngleZ = 0.0F;
        rightleg.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
        leftleg.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
        rightleg.rotateAngleY = 0.0F;
        leftleg.rotateAngleY = 0.0F;


        rightarm.rotateAngleY = 0.0F;
        leftarm.rotateAngleY = 0.0F;


        rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;

		//bucket2.rotateAngleX = rightarm.rotateAngleX;bucket2.rotateAngleY = rightarm.rotateAngleY;bucket2.rotateAngleZ = rightarm.rotateAngleZ;
		//handle1.rotateAngleX = rightarm.rotateAngleX;handle1.rotateAngleY = rightarm.rotateAngleY;handle1.rotateAngleZ = rightarm.rotateAngleZ;

		//bucket1.rotateAngleX = leftarm.rotateAngleX;bucket1.rotateAngleY = leftarm.rotateAngleY;bucket1.rotateAngleZ = leftarm.rotateAngleZ;
		//handle2.rotateAngleX = leftarm.rotateAngleX;handle2.rotateAngleY = leftarm.rotateAngleY;handle2.rotateAngleZ = leftarm.rotateAngleZ;*/
	}

	public ModelRenderer Head;
	public ModelRenderer Body;
	public ModelRenderer Leg1;
	public ModelRenderer Leg2;
	public ModelRenderer Leg3;
	public ModelRenderer Leg4;
	public ModelRenderer Leg5;
	public ModelRenderer Leg6;
	public ModelRenderer Horn1;
	public ModelRenderer Horn2;
	public ModelRenderer Horn3;
	public ModelRenderer Shell1;
	public ModelRenderer Shell2;
}
