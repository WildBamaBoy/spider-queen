package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ModelTinySpider extends ModelBase
{

    public ModelTinySpider()
    {
		float scale = 0F;
	Head = new ModelRenderer(this,0, 8);
	Head.addBox(-3F, -2F, -4F, 4, 3, 4, scale);
	Head.setRotationPoint(0F, 19F, -3F);

	Body = new ModelRenderer(this,0, 0);
	Body.addBox(-3F, -3F, -3F, 4, 4, 4, scale);
	Body.setRotationPoint(0F, 19F, 0F);

	RearEnd = new ModelRenderer(this,0, 15);
	RearEnd.addBox(-3F, -4F, 0F, 6, 6, 6, scale);
	RearEnd.setRotationPoint(-1F, 18F, 1F);

	Leg1 = new ModelRenderer(this,18, 0);
	Leg1.addBox(-9F, -1F, -1F, 10, 1, 1, scale);
	Leg1.setRotationPoint(-3F, 19F, 1F);
	Leg1.rotateAngleX = 0.57596F;
	Leg1.rotateAngleY = 0.19199F;

	Leg2 = new ModelRenderer(this,18, 0);
	Leg2.addBox(-1F, -1F, -1F, 10, 1, 1, scale);
	Leg2.setRotationPoint(1F, 19F, 1F);
	Leg2.rotateAngleX = -0.57596F;
	Leg2.rotateAngleY = -0.19199F;

	Leg3 = new ModelRenderer(this,18, 0);
	Leg3.addBox(-9F, -1F, -1F, 10, 1, 1, scale);
	Leg3.setRotationPoint(-3F, 19F, 0F);
	Leg3.rotateAngleX = 0.27925F;
	Leg3.rotateAngleY = 0.19199F;

	Leg4 = new ModelRenderer(this,18, 0);
	Leg4.addBox(-1F, -1F, -1F, 10, 1, 1, scale);
	Leg4.setRotationPoint(1F, 19F, 0F);
	Leg4.rotateAngleX = -0.27925F;
	Leg4.rotateAngleY = -0.19199F;

	Leg5 = new ModelRenderer(this,18, 0);
	Leg5.addBox(-9F, -1F, -1F, 10, 1, 1, scale);
	Leg5.setRotationPoint(-3F, 19F, -1F);
	Leg5.rotateAngleX = -0.27925F;
	Leg5.rotateAngleY = 0.19199F;

	Leg6 = new ModelRenderer(this,18, 0);
	Leg6.addBox(-1F, -1F, -1F, 10, 1, 1, scale);
	Leg6.setRotationPoint(1F, 19F, -1F);
	Leg6.rotateAngleX = 0.27925F;
	Leg6.rotateAngleY = -0.19199F;

	Leg7 = new ModelRenderer(this,18, 0);
	Leg7.addBox(-10F, -1F, -1F, 10, 1, 1, scale);
	Leg7.setRotationPoint(-2F, 19F, -2F);
	Leg7.rotateAngleX = -0.57596F;
	Leg7.rotateAngleY = 0.19199F;

	Leg8 = new ModelRenderer(this,18, 0);
	Leg8.addBox(-1F, -1F, -1F, 10, 1, 1, scale);
	Leg8.setRotationPoint(1F, 19F, -2F);
	Leg8.rotateAngleX = 0.57596F;
	Leg8.rotateAngleY = -0.19199F;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        Head.render(f5);
        RearEnd.render(f5);
        Body.render(f5);
        Leg1.render(f5);
        Leg2.render(f5);
        Leg3.render(f5);
        Leg4.render(f5);
        Leg5.render(f5);
        Leg6.render(f5);
        Leg7.render(f5);
        Leg8.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Head.rotateAngleY = f3 / 57.29578F;
        Head.rotateAngleX = f4 / 57.29578F;
        float f6 = 0.7853982F;
        Leg1.rotateAngleZ = -f6;
        Leg2.rotateAngleZ = f6;
        Leg3.rotateAngleZ = -f6 * 0.74F;
        Leg4.rotateAngleZ = f6 * 0.74F;
        Leg5.rotateAngleZ = -f6 * 0.74F;
        Leg6.rotateAngleZ = f6 * 0.74F;
        Leg7.rotateAngleZ = -f6;
        Leg8.rotateAngleZ = f6;
        float f7 = -0F;
        float f8 = 0.3926991F;
        Leg1.rotateAngleY = f8 * 2.0F + f7;
        Leg2.rotateAngleY = -f8 * 2.0F - f7;
        Leg3.rotateAngleY = f8 * 1.0F + f7;
        Leg4.rotateAngleY = -f8 * 1.0F - f7;
        Leg5.rotateAngleY = -f8 * 1.0F + f7;
        Leg6.rotateAngleY = f8 * 1.0F - f7;
        Leg7.rotateAngleY = -f8 * 2.0F + f7;
        Leg8.rotateAngleY = f8 * 2.0F - f7;
        float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
        float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
        float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
        float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * f1;
        float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
        float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
        float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
        float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;
        Leg1.rotateAngleY += f9;
        Leg2.rotateAngleY += -f9;
        Leg3.rotateAngleY += f10;
        Leg4.rotateAngleY += -f10;
        Leg5.rotateAngleY += f11;
        Leg6.rotateAngleY += -f11;
        Leg7.rotateAngleY += f12;
        Leg8.rotateAngleY += -f12;
        Leg1.rotateAngleZ += f13;
        Leg2.rotateAngleZ += -f13;
        Leg3.rotateAngleZ += f14;
        Leg4.rotateAngleZ += -f14;
        Leg5.rotateAngleZ += f15;
        Leg6.rotateAngleZ += -f15;
        Leg7.rotateAngleZ += f16;
        Leg8.rotateAngleZ += -f16;
    }

    /*public ModelRenderer spiderHead;
    public ModelRenderer spiderNeck;
    public ModelRenderer spiderBody;
    public ModelRenderer spiderLeg1;
    public ModelRenderer spiderLeg2;
    public ModelRenderer spiderLeg3;
    public ModelRenderer spiderLeg4;
    public ModelRenderer spiderLeg5;
    public ModelRenderer spiderLeg6;
    public ModelRenderer spiderLeg7;
    public ModelRenderer spiderLeg8;*/
	public ModelRenderer Head;
	public ModelRenderer Body;
	public ModelRenderer RearEnd;
	public ModelRenderer Leg1;
	public ModelRenderer Leg2;
	public ModelRenderer Leg3;
	public ModelRenderer Leg4;
	public ModelRenderer Leg5;
	public ModelRenderer Leg6;
	public ModelRenderer Leg7;
	public ModelRenderer Leg8;

}
