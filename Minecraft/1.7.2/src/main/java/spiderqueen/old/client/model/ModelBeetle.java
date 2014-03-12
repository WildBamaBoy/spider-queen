// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelBeetle extends ModelBase
{

    public ModelBeetle()
    {
Head = new ModelRenderer(this,30, 6);
		Head.addBox(-4F, -4F, -8F, 8, 8, 8, 0F);
		Head.setRotationPoint(0F, 17F, -3F);
		Head.rotateAngleX = 0F;
		Head.rotateAngleY = 0F;
		Head.rotateAngleZ = 0F;
		Head.mirror = false;
		Body = new ModelRenderer(this,0, 12);
		Body.addBox(-4F, -7F, -3F, 10, 10, 10, 0F);
		Body.setRotationPoint(-1F, 18F, 0F);
		Body.rotateAngleX = 0F;
		Body.rotateAngleY = 0F;
		Body.rotateAngleZ = 0F;
		Body.mirror = false;
		Leg1 = new ModelRenderer(this,28, 0);
		Leg1.addBox(-9F, -1F, -1F, 10, 1, 1, 0F);
		Leg1.setRotationPoint(-1F, 19F, 2F);
		Leg1.rotateAngleX = 0.5759587F;
		Leg1.rotateAngleY = 0.1919862F;
		Leg1.rotateAngleZ = 0F;
		Leg1.mirror = false;
		Leg2 = new ModelRenderer(this,28, 0);
		Leg2.addBox(-1F, -1F, -1F, 10, 1, 1, 0F);
		Leg2.setRotationPoint(1F, 19F, 1F);
		Leg2.rotateAngleX = -0.5759587F;
		Leg2.rotateAngleY = -0.1919862F;
		Leg2.rotateAngleZ = 0F;
		Leg2.mirror = false;
		Leg3 = new ModelRenderer(this,28, 0);
		Leg3.addBox(-9F, -1F, -1F, 10, 1, 1, 0F);
		Leg3.setRotationPoint(-1F, 19F, 0F);
		Leg3.rotateAngleX = 0.2792527F;
		Leg3.rotateAngleY = 0.1570796F;
		Leg3.rotateAngleZ = 0F;
		Leg3.mirror = false;
		Leg4 = new ModelRenderer(this,28, 0);
		Leg4.addBox(-1F, -1F, -1F, 10, 1, 1, 0F);
		Leg4.setRotationPoint(1F, 19F, -1F);
		Leg4.rotateAngleX = -0.2792527F;
		Leg4.rotateAngleY = -0.1570796F;
		Leg4.rotateAngleZ = 0F;
		Leg4.mirror = false;
		Leg5 = new ModelRenderer(this,28, 0);
		Leg5.addBox(-9F, -1F, -1F, 10, 1, 1, 0F);
		Leg5.setRotationPoint(-1F, 19F, -2F);
		Leg5.rotateAngleX = -0.2792527F;
		Leg5.rotateAngleY = 0.05235988F;
		Leg5.rotateAngleZ = 0F;
		Leg5.mirror = false;
		Leg6 = new ModelRenderer(this,28, 0);
		Leg6.addBox(-1F, -1F, -1F, 10, 1, 1, 0F);
		Leg6.setRotationPoint(1F, 19F, -2F);
		Leg6.rotateAngleX = 0.2792527F;
		Leg6.rotateAngleY = -0.05235988F;
		Leg6.rotateAngleZ = 0F;
		Leg6.mirror = false;
		Horn1 = new ModelRenderer(this,24, 2);
		Horn1.addBox(-1F, 2F, -13F, 2, 2, 5, 0F);
		Horn1.setRotationPoint(0F, 17F, -3F);
		Horn1.rotateAngleX = 0F;
		Horn1.rotateAngleY = 0F;
		Horn1.rotateAngleZ = 0F;
		Horn1.mirror = false;
		Horn2 = new ModelRenderer(this,0, 10);
		Horn2.addBox(-1F, -12F + 6F, -14F, 2, 10, 2, 0F);
		Horn2.setRotationPoint(0F, 17F, -3F);
		Horn2.rotateAngleX = 0f;//.4363323F;
		Horn2.rotateAngleY = 0F;
		Horn2.rotateAngleZ = 0F;
		Horn2.mirror = false;
		Horn3 = new ModelRenderer(this,40, 26);
		Horn3.addBox(-3F, -14F + 6F, -14F, 6, 5, 1, 0F);
		Horn3.setRotationPoint(0F, 17F, -3F);
		Horn3.rotateAngleX = 0f;//.4363323F;
		Horn3.rotateAngleY = 0F;
		Horn3.rotateAngleZ = 0F;
		Horn3.mirror = false;
		Shell1 = new ModelRenderer(this,0, 1);
		Shell1.addBox(0F, 0F, 0F, 8, 1, 8, 0F);
		Shell1.setRotationPoint(1F, 11F, -3F);
		Shell1.rotateAngleX = 0.7435722F;
		Shell1.rotateAngleY = 0F;
		Shell1.rotateAngleZ = 0F;
		Shell1.mirror = false;
		Shell2 = new ModelRenderer(this,0, 1);
		Shell2.addBox(-9F, 0F, 0F, 8, 1, 8, 0F);
		Shell2.setRotationPoint(0F, 11F, -3F);
		Shell2.rotateAngleX = 0.7435722F;
		Shell2.rotateAngleY = 0F;
		Shell2.rotateAngleZ = 0F;
		Shell2.mirror = false;		
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Head.render(f5);
		Body.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
		Leg5.render(f5);
		Leg6.render(f5);
		Horn1.render(f5);
		Horn2.render(f5);
		Horn3.render(f5);
		Shell1.render(f5);
		Shell2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	
	    Head.rotateAngleY = f3 / 57.29578F;
        Head.rotateAngleX = f4 / 57.29578F;
		
		
		Horn1.rotateAngleY = Head.rotateAngleY; Horn1.rotateAngleX = Head.rotateAngleX; Horn1.rotateAngleZ = Head.rotateAngleZ;
		Horn2.rotateAngleY = Head.rotateAngleY; Horn2.rotateAngleX = Head.rotateAngleX; Horn2.rotateAngleZ = Head.rotateAngleZ;
		Horn3.rotateAngleY = Head.rotateAngleY; Horn3.rotateAngleX = Head.rotateAngleX; Horn3.rotateAngleZ = Head.rotateAngleZ;
		
		
		float f6 = 0.7853982F;
        Leg1.rotateAngleZ = -f6;
        Leg2.rotateAngleZ = f6;
        Leg3.rotateAngleZ = -f6 * 0.74F;
        Leg4.rotateAngleZ = f6 * 0.74F;
        Leg5.rotateAngleZ = -f6 * 0.74F;
        Leg6.rotateAngleZ = f6 * 0.74F;
        float f7 = -0F;
        float f8 = 0.3926991F;
        Leg1.rotateAngleY = f8 * 2.0F + f7;
        Leg2.rotateAngleY = -f8 * 2.0F - f7;
        Leg3.rotateAngleY = f8 * 1.0F + f7;
        Leg4.rotateAngleY = -f8 * 1.0F - f7;
        Leg5.rotateAngleY = -f8 * 1.0F + f7;
        Leg6.rotateAngleY = f8 * 1.0F - f7;
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
        Leg1.rotateAngleZ += f13;
        Leg2.rotateAngleZ += -f13;
        Leg3.rotateAngleZ += f14;
        Leg4.rotateAngleZ += -f14;
        Leg5.rotateAngleZ += f15;
        Leg6.rotateAngleZ += -f15;
		
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
