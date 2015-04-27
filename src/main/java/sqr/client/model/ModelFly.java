

package sqr.client.model;

import java.util.Random;
// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelFly extends ModelBase
{

	public ModelFly()
	{
		this.Head = new ModelRenderer(this,19, 0);
		this.Head.addBox(-3, -3, -4, 6, 5, 5, 0F);
		this.Head.setRotationPoint(0, 17, -3);

		this.Head.rotateAngleX = 0F;
		this.Head.rotateAngleY = 0F;
		this.Head.rotateAngleZ = 0F;
		this.Head.mirror = false;

		this.Body = new ModelRenderer(this,0, 0);
		this.Body.addBox(-2, -3, -5, 4, 4, 5, 0F);
		this.Body.setRotationPoint(0, 18, 2);

		this.Body.rotateAngleX = -0.1745329F;
		this.Body.rotateAngleY = 0F;
		this.Body.rotateAngleZ = 0F;
		this.Body.mirror = false;

		this.RearEnd1 = new ModelRenderer(this,0, 9);
		this.RearEnd1.addBox(-4, -2, 0, 6, 5, 5, 0F);
		this.RearEnd1.setRotationPoint(1, 17, 2);

		this.RearEnd1.rotateAngleX = -0.1745329F;
		this.RearEnd1.rotateAngleY = 0F;
		this.RearEnd1.rotateAngleZ = 0F;
		this.RearEnd1.mirror = false;

		this.Leg1 = new ModelRenderer(this,16, -1);
		this.Leg1.addBox(0, -1, 0, 0, 5, 1, 0F);
		this.Leg1.setRotationPoint(-2, 19, -3);

		this.Leg1.rotateAngleX = 0F;
		this.Leg1.rotateAngleY = 0F;
		this.Leg1.rotateAngleZ = 0F;
		this.Leg1.mirror = false;

		this.RearEnd2 = new ModelRenderer(this,22, 14);
		this.RearEnd2.addBox(-3, -1, 5, 4, 3, 2, 0F);
		this.RearEnd2.setRotationPoint(1, 17, 2);

		this.RearEnd2.rotateAngleX = -0.1745329F;
		this.RearEnd2.rotateAngleY = 0F;
		this.RearEnd2.rotateAngleZ = 0F;
		this.RearEnd2.mirror = false;

		this.Leg2 = new ModelRenderer(this,16, -1);
		this.Leg2.addBox(0, 0, 0, 0, 5, 1, 0F);
		this.Leg2.setRotationPoint(-2, 18, -1);

		this.Leg2.rotateAngleX = 0F;
		this.Leg2.rotateAngleY = 0F;
		this.Leg2.rotateAngleZ = 0F;
		this.Leg2.mirror = false;

		this.Leg3 = new ModelRenderer(this,16, -1);
		this.Leg3.addBox(0, 0, 0, 0, 5, 1, 0F);
		this.Leg3.setRotationPoint(-2, 18, 1);

		this.Leg3.rotateAngleX = 0F;
		this.Leg3.rotateAngleY = 0F;
		this.Leg3.rotateAngleZ = 0F;
		this.Leg3.mirror = false;

		this.Leg4 = new ModelRenderer(this,16, -1);
		this.Leg4.addBox(0, 0, 0, 0, 5, 1, 0F);
		this.Leg4.setRotationPoint(2, 18, 1);

		this.Leg4.rotateAngleX = 0F;
		this.Leg4.rotateAngleY = 0F;
		this.Leg4.rotateAngleZ = 0F;
		this.Leg4.mirror = false;

		this.Leg5 = new ModelRenderer(this,16, -1);
		this.Leg5.addBox(0, 0, 0, 0, 5, 1, 0F);
		this.Leg5.setRotationPoint(2, 18, -1);

		this.Leg5.rotateAngleX = 0F;
		this.Leg5.rotateAngleY = 0F;
		this.Leg5.rotateAngleZ = 0F;
		this.Leg5.mirror = false;

		this.Leg6 = new ModelRenderer(this,16, -1);
		this.Leg6.addBox(0, 0, 0, 0, 5, 1, 0F);
		this.Leg6.setRotationPoint(2, 18, -3);

		this.Leg6.rotateAngleX = 0F;
		this.Leg6.rotateAngleY = 0F;
		this.Leg6.rotateAngleZ = 0F;
		this.Leg6.mirror = false;

		this.Mouth = new ModelRenderer(this,41, 5);
		this.Mouth.addBox(-1, 0, -5, 2, 2, 2, 0F);
		this.Mouth.setRotationPoint(0, 17, -3);

		this.Mouth.rotateAngleX = 0F;
		this.Mouth.rotateAngleY = 0F;
		this.Mouth.rotateAngleZ = 0F;
		this.Mouth.mirror = false;

		this.wing1 = new ModelRenderer(this,33, 14);
		this.wing1.addBox(-4, -1, 0, 4, 0, 5, 0F);
		this.wing1.setRotationPoint(0, 15, -1);

		this.wing1.rotateAngleX = -0.122173F;
		this.wing1.rotateAngleY = -0.2617994F;
		this.wing1.rotateAngleZ = 0F;
		this.wing1.mirror = false;

		this.wing2 = new ModelRenderer(this,33, 14);
		this.wing2.addBox(0, -1, 0, 4, 0, 5, 0F);
		this.wing2.setRotationPoint(0, 15, -1);

		this.wing2.rotateAngleX = -0.122173F;
		this.wing2.rotateAngleY = 0.2617994F;
		this.wing2.rotateAngleZ = 0F;
		this.wing2.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.Head.render(f5);
		this.Body.render(f5);
		this.RearEnd1.render(f5);
		this.Leg1.render(f5);
		this.RearEnd2.render(f5);
		this.Leg2.render(f5);
		this.Leg3.render(f5);
		this.Leg4.render(f5);
		this.Leg5.render(f5);
		this.Leg6.render(f5);
		this.Mouth.render(f5);
		this.wing1.render(f5);
		this.wing2.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{

		this.Head.rotateAngleY = f3 / 57.29578F;
		this.Head.rotateAngleX = f4 / 57.29578F;

		this.Mouth.rotateAngleY = this.Head.rotateAngleY; this.Mouth.rotateAngleX = this.Head.rotateAngleX; this.Mouth.rotateAngleZ = this.Head.rotateAngleZ;

		this.Leg1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
		this.Leg1.rotateAngleY = 0.0F;

		this.Leg2.rotateAngleX = this.Leg1.rotateAngleX; this.Leg2.rotateAngleY = this.Leg1.rotateAngleY; this.Leg2.rotateAngleZ = this.Leg1.rotateAngleZ;
		this.Leg3.rotateAngleX = this.Leg1.rotateAngleX; this.Leg3.rotateAngleY = this.Leg1.rotateAngleY; this.Leg3.rotateAngleZ = this.Leg1.rotateAngleZ;
		this.Leg4.rotateAngleX = this.Leg1.rotateAngleX; this.Leg4.rotateAngleY = this.Leg1.rotateAngleY; this.Leg4.rotateAngleZ = this.Leg1.rotateAngleZ;
		this.Leg5.rotateAngleX = this.Leg1.rotateAngleX; this.Leg5.rotateAngleY = this.Leg1.rotateAngleY; this.Leg5.rotateAngleZ = this.Leg1.rotateAngleZ;
		this.Leg6.rotateAngleX = this.Leg1.rotateAngleX; this.Leg6.rotateAngleY = this.Leg1.rotateAngleY; this.Leg6.rotateAngleZ = this.Leg1.rotateAngleZ;
		final Random rm = new Random();
		this.wing1.rotateAngleX = -0.122173F + rm.nextInt(5) * 0.122173F;

		this.wing2.rotateAngleX = -0.122173F + rm.nextInt(5) * 0.122173F;

	}


	public ModelRenderer Head;
	public ModelRenderer Body;
	public ModelRenderer RearEnd1;
	public ModelRenderer Leg1;
	public ModelRenderer RearEnd2;
	public ModelRenderer Leg2;
	public ModelRenderer Leg3;
	public ModelRenderer Leg4;
	public ModelRenderer Leg5;
	public ModelRenderer Leg6;
	public ModelRenderer Mouth;
	public ModelRenderer wing1;
	public ModelRenderer wing2;

}
