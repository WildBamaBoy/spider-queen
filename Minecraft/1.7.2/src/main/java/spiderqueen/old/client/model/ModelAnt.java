// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelAnt extends ModelBase
{

    public ModelAnt()
    {
Head = new ModelRenderer(this,32, 4);
Head.addBox(-4, -4, -8, 8, 8, 8, 0F);
Head.setRotationPoint(0, 17, -3);

Head.rotateAngleX = 0F;
Head.rotateAngleY = 0F;
Head.rotateAngleZ = 0F;

Body = new ModelRenderer(this,0, 0);
Body.addBox(-2, -3, -3, 6, 6, 6, 0F);
Body.setRotationPoint(-1, 18, 0);

Body.rotateAngleX = 0F;
Body.rotateAngleY = 0F;
Body.rotateAngleZ = 0F;

RearEnd1 = new ModelRenderer(this,0, 12);
RearEnd1.addBox(-5, -4, -6, 10, 8, 12, 0F);
RearEnd1.setRotationPoint(0, 17, 9);

RearEnd1.rotateAngleX = 0F;
RearEnd1.rotateAngleY = 0F;
RearEnd1.rotateAngleZ = 0F;

Leg1 = new ModelRenderer(this,18, 0);
Leg1.addBox(-9, -1, -1, 10, 1, 1, 0F);
Leg1.setRotationPoint(-1, 19, 2);

Leg1.rotateAngleX = 0.5759587F;
Leg1.rotateAngleY = 0.1919862F;
Leg1.rotateAngleZ = 0F;

Leg2 = new ModelRenderer(this,18, 0);
Leg2.addBox(-1, -1, -1, 10, 1, 1, 0F);
Leg2.setRotationPoint(1, 19, 1);

Leg2.rotateAngleX = -0.5759587F;
Leg2.rotateAngleY = -0.1919862F;
Leg2.rotateAngleZ = 0F;

Leg3 = new ModelRenderer(this,18, 0);
Leg3.addBox(-9, -1, -1, 10, 1, 1, 0F);
Leg3.setRotationPoint(-1, 19, 0);

Leg3.rotateAngleX = 0.2792527F;
Leg3.rotateAngleY = 0.1570796F;
Leg3.rotateAngleZ = 0F;

Leg4 = new ModelRenderer(this,18, 0);
Leg4.addBox(-1, -1, -1, 10, 1, 1, 0F);
Leg4.setRotationPoint(1, 19, -1);

Leg4.rotateAngleX = -0.2792527F;
Leg4.rotateAngleY = -0.1570796F;
Leg4.rotateAngleZ = 0F;

Leg5 = new ModelRenderer(this,18, 0);
Leg5.addBox(-9, -1, -1, 10, 1, 1, 0F);
Leg5.setRotationPoint(-1, 19, -2);

Leg5.rotateAngleX = -0.2792527F;
Leg5.rotateAngleY = 0.05235988F;
Leg5.rotateAngleZ = 0F;

Leg6 = new ModelRenderer(this,18, 0);
Leg6.addBox(-1, -1, -1, 10, 1, 1, 0F);
Leg6.setRotationPoint(1, 19, -2);

Leg6.rotateAngleX = 0.2792527F;
Leg6.rotateAngleY = -0.05235988F;
Leg6.rotateAngleZ = 0F;

RearEnd2 = new ModelRenderer(this,0, 23);
RearEnd2.addBox(-3, -3, 6, 6, 6, 2, 0F);
RearEnd2.setRotationPoint(0, 17, 9);

RearEnd2.rotateAngleX = 0F;
RearEnd2.rotateAngleY = 0F;
RearEnd2.rotateAngleZ = 0F;

RearEnd3 = new ModelRenderer(this,2, 12);
RearEnd3.addBox(-4, -6, -5, 8, 2, 10, 0F);
RearEnd3.setRotationPoint(0, 17, 9);

RearEnd3.rotateAngleX = 0F;
RearEnd3.rotateAngleY = 0F;
RearEnd3.rotateAngleZ = 0F;

Jaws = new ModelRenderer(this,44, 27);
Jaws.addBox(-4, 2, -12, 8, 1, 4, 0F);
Jaws.setRotationPoint(0, 17, -3);

Jaws.rotateAngleX = 0F;
Jaws.rotateAngleY = 0F;
Jaws.rotateAngleZ = 0F;

Antenna1 = new ModelRenderer(this,32, 0);
Antenna1.addBox(-3, -9, -8, 0, 5, 4, 0F);
Antenna1.setRotationPoint(0, 17, -3);

Antenna1.rotateAngleX = 0F;
Antenna1.rotateAngleY = 0F;
Antenna1.rotateAngleZ = 0F;

Antenna2 = new ModelRenderer(this,32, 0);
Antenna2.addBox(3, -9, -8, 0, 5, 4, 0F);
Antenna2.setRotationPoint(0, 17, -3);

Antenna2.rotateAngleX = 0F;
Antenna2.rotateAngleY = 0F;
Antenna2.rotateAngleZ = 0F;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
setRotationAngles(f, f1, f2, f3, f4, f5);
Head.render(f5);
Body.render(f5);
RearEnd1.render(f5);
Leg1.render(f5);
Leg2.render(f5);
Leg3.render(f5);
Leg4.render(f5);
Leg5.render(f5);
Leg6.render(f5);
RearEnd2.render(f5);
RearEnd3.render(f5);
Jaws.render(f5);
Antenna1.render(f5);
Antenna2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	
	    Head.rotateAngleY = f3 / 57.29578F;
        Head.rotateAngleX = f4 / 57.29578F;
		
		
		Antenna1.rotateAngleY = Head.rotateAngleY; Antenna1.rotateAngleX = Head.rotateAngleX; Antenna1.rotateAngleZ = Head.rotateAngleZ;
		Antenna2.rotateAngleY = Head.rotateAngleY; Antenna2.rotateAngleX = Head.rotateAngleX; Antenna2.rotateAngleZ = Head.rotateAngleZ;
		Jaws.rotateAngleY = Head.rotateAngleY; Jaws.rotateAngleX = Head.rotateAngleX; Jaws.rotateAngleZ = Head.rotateAngleZ;
		
		
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
