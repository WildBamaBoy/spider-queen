package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelAnt extends ModelBase
{

	public ModelAnt()
	{
		this.Head = new ModelRenderer(this,32, 4);
		this.Head.addBox(-4, -4, -8, 8, 8, 8, 0F);
		this.Head.setRotationPoint(0, 17, -3);

		this.Head.rotateAngleX = 0F;
		this.Head.rotateAngleY = 0F;
		this.Head.rotateAngleZ = 0F;

		this.Body = new ModelRenderer(this,0, 0);
		this.Body.addBox(-2, -3, -3, 6, 6, 6, 0F);
		this.Body.setRotationPoint(-1, 18, 0);

		this.Body.rotateAngleX = 0F;
		this.Body.rotateAngleY = 0F;
		this.Body.rotateAngleZ = 0F;

		this.RearEnd1 = new ModelRenderer(this,0, 12);
		this.RearEnd1.addBox(-5, -4, -6, 10, 8, 12, 0F);
		this.RearEnd1.setRotationPoint(0, 17, 9);

		this.RearEnd1.rotateAngleX = 0F;
		this.RearEnd1.rotateAngleY = 0F;
		this.RearEnd1.rotateAngleZ = 0F;

		this.Leg1 = new ModelRenderer(this,18, 0);
		this.Leg1.addBox(-9, -1, -1, 10, 1, 1, 0F);
		this.Leg1.setRotationPoint(-1, 19, 2);

		this.Leg1.rotateAngleX = 0.5759587F;
		this.Leg1.rotateAngleY = 0.1919862F;
		this.Leg1.rotateAngleZ = 0F;

		this.Leg2 = new ModelRenderer(this,18, 0);
		this.Leg2.addBox(-1, -1, -1, 10, 1, 1, 0F);
		this.Leg2.setRotationPoint(1, 19, 1);

		this.Leg2.rotateAngleX = -0.5759587F;
		this.Leg2.rotateAngleY = -0.1919862F;
		this.Leg2.rotateAngleZ = 0F;

		this.Leg3 = new ModelRenderer(this,18, 0);
		this.Leg3.addBox(-9, -1, -1, 10, 1, 1, 0F);
		this.Leg3.setRotationPoint(-1, 19, 0);

		this.Leg3.rotateAngleX = 0.2792527F;
		this.Leg3.rotateAngleY = 0.1570796F;
		this.Leg3.rotateAngleZ = 0F;

		this.Leg4 = new ModelRenderer(this,18, 0);
		this.Leg4.addBox(-1, -1, -1, 10, 1, 1, 0F);
		this.Leg4.setRotationPoint(1, 19, -1);

		this.Leg4.rotateAngleX = -0.2792527F;
		this.Leg4.rotateAngleY = -0.1570796F;
		this.Leg4.rotateAngleZ = 0F;

		this.Leg5 = new ModelRenderer(this,18, 0);
		this.Leg5.addBox(-9, -1, -1, 10, 1, 1, 0F);
		this.Leg5.setRotationPoint(-1, 19, -2);

		this.Leg5.rotateAngleX = -0.2792527F;
		this.Leg5.rotateAngleY = 0.05235988F;
		this.Leg5.rotateAngleZ = 0F;

		this.Leg6 = new ModelRenderer(this,18, 0);
		this.Leg6.addBox(-1, -1, -1, 10, 1, 1, 0F);
		this.Leg6.setRotationPoint(1, 19, -2);

		this.Leg6.rotateAngleX = 0.2792527F;
		this.Leg6.rotateAngleY = -0.05235988F;
		this.Leg6.rotateAngleZ = 0F;

		this.RearEnd2 = new ModelRenderer(this,0, 23);
		this.RearEnd2.addBox(-3, -3, 6, 6, 6, 2, 0F);
		this.RearEnd2.setRotationPoint(0, 17, 9);

		this.RearEnd2.rotateAngleX = 0F;
		this.RearEnd2.rotateAngleY = 0F;
		this.RearEnd2.rotateAngleZ = 0F;

		this.RearEnd3 = new ModelRenderer(this,2, 12);
		this.RearEnd3.addBox(-4, -6, -5, 8, 2, 10, 0F);
		this.RearEnd3.setRotationPoint(0, 17, 9);

		this.RearEnd3.rotateAngleX = 0F;
		this.RearEnd3.rotateAngleY = 0F;
		this.RearEnd3.rotateAngleZ = 0F;

		this.Jaws = new ModelRenderer(this,44, 27);
		this.Jaws.addBox(-4, 2, -12, 8, 1, 4, 0F);
		this.Jaws.setRotationPoint(0, 17, -3);

		this.Jaws.rotateAngleX = 0F;
		this.Jaws.rotateAngleY = 0F;
		this.Jaws.rotateAngleZ = 0F;

		this.Antenna1 = new ModelRenderer(this,32, 0);
		this.Antenna1.addBox(-3, -9, -8, 0, 5, 4, 0F);
		this.Antenna1.setRotationPoint(0, 17, -3);

		this.Antenna1.rotateAngleX = 0F;
		this.Antenna1.rotateAngleY = 0F;
		this.Antenna1.rotateAngleZ = 0F;

		this.Antenna2 = new ModelRenderer(this,32, 0);
		this.Antenna2.addBox(3, -9, -8, 0, 5, 4, 0F);
		this.Antenna2.setRotationPoint(0, 17, -3);

		this.Antenna2.rotateAngleX = 0F;
		this.Antenna2.rotateAngleY = 0F;
		this.Antenna2.rotateAngleZ = 0F;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.Head.render(f5);
		this.Body.render(f5);
		this.RearEnd1.render(f5);
		this.Leg1.render(f5);
		this.Leg2.render(f5);
		this.Leg3.render(f5);
		this.Leg4.render(f5);
		this.Leg5.render(f5);
		this.Leg6.render(f5);
		this.RearEnd2.render(f5);
		this.RearEnd3.render(f5);
		this.Jaws.render(f5);
		this.Antenna1.render(f5);
		this.Antenna2.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{

		this.Head.rotateAngleY = f3 / 57.29578F;
		this.Head.rotateAngleX = f4 / 57.29578F;


		this.Antenna1.rotateAngleY = this.Head.rotateAngleY; this.Antenna1.rotateAngleX = this.Head.rotateAngleX; this.Antenna1.rotateAngleZ = this.Head.rotateAngleZ;
		this.Antenna2.rotateAngleY = this.Head.rotateAngleY; this.Antenna2.rotateAngleX = this.Head.rotateAngleX; this.Antenna2.rotateAngleZ = this.Head.rotateAngleZ;
		this.Jaws.rotateAngleY = this.Head.rotateAngleY; this.Jaws.rotateAngleX = this.Head.rotateAngleX; this.Jaws.rotateAngleZ = this.Head.rotateAngleZ;


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
	public ModelRenderer RearEnd1;
	public ModelRenderer Leg1;
	public ModelRenderer Leg2;
	public ModelRenderer Leg3;
	public ModelRenderer Leg4;
	public ModelRenderer Leg5;
	public ModelRenderer Leg6;
	public ModelRenderer RearEnd2;
	public ModelRenderer RearEnd3;
	public ModelRenderer Jaws;
	public ModelRenderer Antenna1;
	public ModelRenderer Antenna2;
}
