package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ModelPoisonSpider extends ModelBase
{

	
    public ModelPoisonSpider()
    {
		ninit();
    }
	
	public void ninit()
	{
	Head = new ModelRenderer(this,32, 4);
		Head.addBox(-4F, -4F, -8F, 8, 8, 8, scale);
		Head.setRotationPoint(0F, 15F, -3F);
		Head.rotateAngleX = 0.18081F;
		Head.rotateAngleZ = -0.27122F;
		Body = new ModelRenderer(this,0, 0);
		Body.addBox(-3F, -3F, -3F, 6, 6, 6, scale);
		Body.setRotationPoint(0F, 15F, 0F);
		RearEnd = new ModelRenderer(this,0, 12);
		RearEnd.addBox(-5F, -4F, -6F, 12, 9, 12, scale);
		RearEnd.setRotationPoint(-1F, 14F, 9F);
		Leg1 = new ModelRenderer(this,18, 0);
		Leg1.addBox(-15F, -1F, -1F, 16, 2, 2, scale);
		Leg1.setRotationPoint(-4F, 15F, 2F);
		Leg1.rotateAngleX = 0.57596F;
		Leg1.rotateAngleY = 0.19199F;
		Leg2 = new ModelRenderer(this,18, 0);
		Leg2.addBox(-1F, -1F, -1F, 16, 2, 2, scale);
		Leg2.setRotationPoint(4F, 15F, 2F);
		Leg2.rotateAngleX = -0.57596F;
		Leg2.rotateAngleY = -0.19199F;
		Leg3 = new ModelRenderer(this,18, 0);
		Leg3.addBox(-15F, -1F, -1F, 16, 2, 2, scale);
		Leg3.setRotationPoint(-4F, 15F, 1F);
		Leg3.rotateAngleX = 0.27925F;
		Leg3.rotateAngleY = 0.19199F;
		Leg4 = new ModelRenderer(this,18, 0);
		Leg4.addBox(-1F, -1F, -1F, 16, 2, 2, scale);
		Leg4.setRotationPoint(4F, 15F, 1F);
		Leg4.rotateAngleX = -0.27925F;
		Leg4.rotateAngleY = -0.19199F;
		Leg5 = new ModelRenderer(this,18, 0);
		Leg5.addBox(-15F, -1F, -1F, 16, 2, 2, scale);
		Leg5.setRotationPoint(-4F, 15F, 0F);
		Leg5.rotateAngleX = -0.27925F;
		Leg5.rotateAngleY = 0.19199F;
		Leg6 = new ModelRenderer(this,18, 0);
		Leg6.addBox(-1F, -1F, -1F, 16, 2, 2, scale);
		Leg6.setRotationPoint(4F, 15F, 0F);
		Leg6.rotateAngleX = 0.27925F;
		Leg6.rotateAngleY = -0.19199F;
		Leg7 = new ModelRenderer(this,18, 0);
		Leg7.addBox(-15F, -1F, -1F, 16, 2, 2, scale);
		Leg7.setRotationPoint(-4F, 15F, -1F);
		Leg7.rotateAngleX = -0.57596F;
		Leg7.rotateAngleY = 0.19199F;
		Leg8 = new ModelRenderer(this,18, 0);
		Leg8.addBox(-1F, -1F, -1F, 16, 2, 2, scale);
		Leg8.setRotationPoint(4F, 15F, -1F);
		Leg8.rotateAngleX = 0.57596F;
		Leg8.rotateAngleY = -0.19199F;
		New_Shape1 = new ModelRenderer(this,0, 12);
		New_Shape1.addBox(0F, 0F, 0F, 8, 2, 9, scale);
		New_Shape1.setRotationPoint(-4F, 8F, 4F);
		New_Shape2 = new ModelRenderer(this,0, 12);
		New_Shape2.addBox(-4F, -2F, 0F, 7, 6, 2, scale);
		New_Shape2.setRotationPoint(0F, 14F, 15F);
		New_Shape = new ModelRenderer(this,0, 12);
		New_Shape.addBox(0F, -2F, -3F, 2, 4, 9, scale);
		New_Shape.setRotationPoint(6F, 15F, 7F);
		New_Shape3 = new ModelRenderer(this,0, 12);
		New_Shape3.addBox(-2F, -2F, -3F, 2, 4, 10, scale);
		New_Shape3.setRotationPoint(-6F, 15F, 7F);
	}
	
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        Head.render(f5);
        RearEnd.render(f5);
        Body.render(f5);
		New_Shape.render(f5);
		New_Shape1.render(f5);
		New_Shape2.render(f5);
		New_Shape3.render(f5);
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

	
    public ModelPoisonSpider(float f)
    {
		scale = f;
		ninit();
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
	public ModelRenderer New_Shape1;
	public ModelRenderer New_Shape2;
	public ModelRenderer New_Shape3;
	public ModelRenderer New_Shape;
	private float scale = 0F;
}
