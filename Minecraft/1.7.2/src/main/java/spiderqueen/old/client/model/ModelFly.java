// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import java.util.Random;
// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModelFly extends ModelBase
{

    public ModelFly()
    {
Head = new ModelRenderer(this,19, 0);
Head.addBox(-3, -3, -4, 6, 5, 5, 0F);
Head.setRotationPoint(0, 17, -3);

Head.rotateAngleX = 0F;
Head.rotateAngleY = 0F;
Head.rotateAngleZ = 0F;
Head.mirror = false;

Body = new ModelRenderer(this,0, 0);
Body.addBox(-2, -3, -5, 4, 4, 5, 0F);
Body.setRotationPoint(0, 18, 2);

Body.rotateAngleX = -0.1745329F;
Body.rotateAngleY = 0F;
Body.rotateAngleZ = 0F;
Body.mirror = false;

RearEnd1 = new ModelRenderer(this,0, 9);
RearEnd1.addBox(-4, -2, 0, 6, 5, 5, 0F);
RearEnd1.setRotationPoint(1, 17, 2);

RearEnd1.rotateAngleX = -0.1745329F;
RearEnd1.rotateAngleY = 0F;
RearEnd1.rotateAngleZ = 0F;
RearEnd1.mirror = false;

Leg1 = new ModelRenderer(this,16, -1);
Leg1.addBox(0, -1, 0, 0, 5, 1, 0F);
Leg1.setRotationPoint(-2, 19, -3);

Leg1.rotateAngleX = 0F;
Leg1.rotateAngleY = 0F;
Leg1.rotateAngleZ = 0F;
Leg1.mirror = false;

RearEnd2 = new ModelRenderer(this,22, 14);
RearEnd2.addBox(-3, -1, 5, 4, 3, 2, 0F);
RearEnd2.setRotationPoint(1, 17, 2);

RearEnd2.rotateAngleX = -0.1745329F;
RearEnd2.rotateAngleY = 0F;
RearEnd2.rotateAngleZ = 0F;
RearEnd2.mirror = false;

Leg2 = new ModelRenderer(this,16, -1);
Leg2.addBox(0, 0, 0, 0, 5, 1, 0F);
Leg2.setRotationPoint(-2, 18, -1);

Leg2.rotateAngleX = 0F;
Leg2.rotateAngleY = 0F;
Leg2.rotateAngleZ = 0F;
Leg2.mirror = false;

Leg3 = new ModelRenderer(this,16, -1);
Leg3.addBox(0, 0, 0, 0, 5, 1, 0F);
Leg3.setRotationPoint(-2, 18, 1);

Leg3.rotateAngleX = 0F;
Leg3.rotateAngleY = 0F;
Leg3.rotateAngleZ = 0F;
Leg3.mirror = false;

Leg4 = new ModelRenderer(this,16, -1);
Leg4.addBox(0, 0, 0, 0, 5, 1, 0F);
Leg4.setRotationPoint(2, 18, 1);

Leg4.rotateAngleX = 0F;
Leg4.rotateAngleY = 0F;
Leg4.rotateAngleZ = 0F;
Leg4.mirror = false;

Leg5 = new ModelRenderer(this,16, -1);
Leg5.addBox(0, 0, 0, 0, 5, 1, 0F);
Leg5.setRotationPoint(2, 18, -1);

Leg5.rotateAngleX = 0F;
Leg5.rotateAngleY = 0F;
Leg5.rotateAngleZ = 0F;
Leg5.mirror = false;

Leg6 = new ModelRenderer(this,16, -1);
Leg6.addBox(0, 0, 0, 0, 5, 1, 0F);
Leg6.setRotationPoint(2, 18, -3);

Leg6.rotateAngleX = 0F;
Leg6.rotateAngleY = 0F;
Leg6.rotateAngleZ = 0F;
Leg6.mirror = false;

Mouth = new ModelRenderer(this,41, 5);
Mouth.addBox(-1, 0, -5, 2, 2, 2, 0F);
Mouth.setRotationPoint(0, 17, -3);

Mouth.rotateAngleX = 0F;
Mouth.rotateAngleY = 0F;
Mouth.rotateAngleZ = 0F;
Mouth.mirror = false;

wing1 = new ModelRenderer(this,33, 14);
wing1.addBox(-4, -1, 0, 4, 0, 5, 0F);
wing1.setRotationPoint(0, 15, -1);

wing1.rotateAngleX = -0.122173F;
wing1.rotateAngleY = -0.2617994F;
wing1.rotateAngleZ = 0F;
wing1.mirror = false;

wing2 = new ModelRenderer(this,33, 14);
wing2.addBox(0, -1, 0, 4, 0, 5, 0F);
wing2.setRotationPoint(0, 15, -1);

wing2.rotateAngleX = -0.122173F;
wing2.rotateAngleY = 0.2617994F;
wing2.rotateAngleZ = 0F;
wing2.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
setRotationAngles(f, f1, f2, f3, f4, f5);
Head.render(f5);
Body.render(f5);
RearEnd1.render(f5);
Leg1.render(f5);
RearEnd2.render(f5);
Leg2.render(f5);
Leg3.render(f5);
Leg4.render(f5);
Leg5.render(f5);
Leg6.render(f5);
Mouth.render(f5);
wing1.render(f5);
wing2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	
	    Head.rotateAngleY = f3 / 57.29578F;
        Head.rotateAngleX = f4 / 57.29578F;
		
		Mouth.rotateAngleY = Head.rotateAngleY; Mouth.rotateAngleX = Head.rotateAngleX; Mouth.rotateAngleZ = Head.rotateAngleZ;
		
        Leg1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
        Leg1.rotateAngleY = 0.0F;
		
		Leg2.rotateAngleX = Leg1.rotateAngleX; Leg2.rotateAngleY = Leg1.rotateAngleY; Leg2.rotateAngleZ = Leg1.rotateAngleZ; 
		Leg3.rotateAngleX = Leg1.rotateAngleX; Leg3.rotateAngleY = Leg1.rotateAngleY; Leg3.rotateAngleZ = Leg1.rotateAngleZ; 
		Leg4.rotateAngleX = Leg1.rotateAngleX; Leg4.rotateAngleY = Leg1.rotateAngleY; Leg4.rotateAngleZ = Leg1.rotateAngleZ; 
		Leg5.rotateAngleX = Leg1.rotateAngleX; Leg5.rotateAngleY = Leg1.rotateAngleY; Leg5.rotateAngleZ = Leg1.rotateAngleZ; 
		Leg6.rotateAngleX = Leg1.rotateAngleX; Leg6.rotateAngleY = Leg1.rotateAngleY; Leg6.rotateAngleZ = Leg1.rotateAngleZ; 
Random rm = new Random();
wing1.rotateAngleX = -0.122173F + ((float)rm.nextInt(5)) * 0.122173F;

wing2.rotateAngleX = -0.122173F + ((float)rm.nextInt(5)) * 0.122173F;
		
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
