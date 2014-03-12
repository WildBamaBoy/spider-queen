// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelJack extends ModelBase
{

    public ModelJack()
    {
head = new ModelRenderer(this,0, 0);
head.addBox(-4, -8, -4, 8, 8, 8, 0F);
head.setRotationPoint(0, 0, -1);

head.rotateAngleX = 0F;
head.rotateAngleY = 0F;
head.rotateAngleZ = 0F;
head.mirror = false;

Body1 = new ModelRenderer(this,0, 16);
Body1.addBox(-3, 0, -8, 6, 8, 8, 0F);
Body1.setRotationPoint(0, 1, 0);

Body1.rotateAngleX = 0.7853982F;
Body1.rotateAngleY = 0F;
Body1.rotateAngleZ = 0F;
Body1.mirror = false;

Neck = new ModelRenderer(this,0, 4);
Neck.addBox(-1, 0, -1, 2, 2, 2, 0F);
Neck.setRotationPoint(0, 0, -1);

Neck.rotateAngleX = 0F;
Neck.rotateAngleY = 0F;
Neck.rotateAngleZ = 0F;
Neck.mirror = false;
/*
Faceglow = new ModelRenderer(this,24, 0);
Faceglow.addBox(-4, -8, -3, 8, 8, 0, 0F);
Faceglow.setRotationPoint(0, 0, 0);

Faceglow.rotateAngleX = 0F;
Faceglow.rotateAngleY = 0F;
Faceglow.rotateAngleZ = 0F;
Faceglow.mirror = false;*/

Headtop = new ModelRenderer(this,0, 0);
Headtop.addBox(-1, -10, -1, 2, 2, 2, 0F);
Headtop.setRotationPoint(0, 0, -1);

Headtop.rotateAngleX = 0F;
Headtop.rotateAngleY = 0F;
Headtop.rotateAngleZ = 0F;
Headtop.mirror = false;

Body3 = new ModelRenderer(this,2, 18);
Body3.addBox(-1, 1, -7, 6, 6, 6, 0F);
Body3.setRotationPoint(0, 1, 0);

Body3.rotateAngleX = 0.7853982F;
Body3.rotateAngleY = 0F;
Body3.rotateAngleZ = 0F;
Body3.mirror = false;

Body2 = new ModelRenderer(this,2, 18);
Body2.addBox(-5, 1, -7, 6, 6, 6, 0F);
Body2.setRotationPoint(0, 1, 0);

Body2.rotateAngleX = 0.7853982F;
Body2.rotateAngleY = 0F;
Body2.rotateAngleZ = 0F;
Body2.mirror = false;

Arm = new ModelRenderer(this,28, 22);
Arm.addBox(-1, -1, -2, 1, 8, 2, 0F);
Arm.setRotationPoint(4, 5, -1);

Arm.rotateAngleX = -1.745329F;
Arm.rotateAngleY = 0F;
Arm.rotateAngleZ = 0F;
Arm.mirror = false;

Hand = new ModelRenderer(this,32, 10);
Hand.addBox(-2, 6, -3, 3, 3, 3, 0F);
Hand.setRotationPoint(4, 5, -1);

Hand.rotateAngleX = -1.570796F;
Hand.rotateAngleY = 0F;
Hand.rotateAngleZ = 0F;
Hand.mirror = false;

Lantern = new ModelRenderer(this,34, 24);
Lantern.addBox(-2, 6, 0, 3, 3, 5, 0F);
Lantern.setRotationPoint(4, 5, -1);

Lantern.rotateAngleX = -1.570796F;
Lantern.rotateAngleY = 0F;
Lantern.rotateAngleZ = 0F;
Lantern.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
setRotationAngles(f, f1, f2, f3, f4, f5);
head.render(f5);
Body1.render(f5);
Neck.render(f5);
//Faceglow.render(f5);
Headtop.render(f5);
Body3.render(f5);
Body2.render(f5);
Arm.render(f5);
Hand.render(f5);
Lantern.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	
	    head.rotateAngleY = f3 / 57.29578F;
        head.rotateAngleX = f4 / 57.29578F;
		
		//Faceglow.rotateAngleY = head.rotateAngleY;Faceglow.rotateAngleX = head.rotateAngleX;
		
        Body1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
        Body2.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
        Body3.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
	}

public ModelRenderer head;
public ModelRenderer Body1;
public ModelRenderer Neck;
//public ModelRenderer Faceglow;
public ModelRenderer Headtop;
public ModelRenderer Body3;
public ModelRenderer Body2;
public ModelRenderer Arm;
public ModelRenderer Hand;
public ModelRenderer Lantern;
}
