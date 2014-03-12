// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelYuki extends ModelBase
{

    public ModelYuki()
    {
		head = new ModelRenderer(this,0, 0);
		head.addBox(-4, -8, -4, 8, 8, 8, 0F);
		head.setRotationPoint(0F, -3F, 0F);
		head.rotateAngleX = 0.1745329F;
		head.rotateAngleY = 0F;
		head.rotateAngleZ = 0F;
		head.mirror = false;
		body1 = new ModelRenderer(this,24, 16);
		body1.addBox(-4, 0, -2, 8, 12, 4, 0F);
		body1.setRotationPoint(0F, -3F, 0F);
		body1.rotateAngleX = 0F;
		body1.rotateAngleY = 0F;
		body1.rotateAngleZ = 0F;
		body1.mirror = false;
		rightarm2 = new ModelRenderer(this,48, 18);
		rightarm2.addBox(-3, -3, -1, 6, 12, 2, 0F);
		rightarm2.setRotationPoint(-4F, 5F, -3F);
		rightarm2.rotateAngleX = -3.141593F;
		rightarm2.rotateAngleY = 0.3328681F;
		rightarm2.rotateAngleZ = 0.4730761F;
		rightarm2.mirror = false;
		I_NEED_A_NAME = new ModelRenderer(this,0, 0);
		I_NEED_A_NAME.addBox(0, 0, 0, 1, 1, 1, 0F);
		I_NEED_A_NAME.setRotationPoint(0F, 0F, 0F);
		I_NEED_A_NAME.rotateAngleX = 0F;
		I_NEED_A_NAME.rotateAngleY = 0F;
		I_NEED_A_NAME.rotateAngleZ = 0F;
		I_NEED_A_NAME.mirror = false;
		leftarm = new ModelRenderer(this,40, 18);
		leftarm.addBox(-6, -1, -1, 6, 12, 2, 0F);
		leftarm.setRotationPoint(5F, -2F, 0F);
		leftarm.rotateAngleX = 0F;
		leftarm.rotateAngleY = 0F;
		leftarm.rotateAngleZ = -0.4363323F;
		leftarm.mirror = false;
		belt = new ModelRenderer(this,32, 0);
		belt.addBox(0, 0, 0, 10, 1, 6, 0F);
		belt.setRotationPoint(-5F, 6F, -3F);
		belt.rotateAngleX = 0F;
		belt.rotateAngleY = 0F;
		belt.rotateAngleZ = 0F;
		belt.mirror = false;
		hairbun = new ModelRenderer(this,32, 7);
		hairbun.addBox(-2, -8, 6, 4, 4, 4, 0F);
		hairbun.setRotationPoint(0F, -3F, 0F);
		hairbun.rotateAngleX = 0.5585054F;
		hairbun.rotateAngleY = 0F;
		hairbun.rotateAngleZ = 0F;
		hairbun.mirror = false;
		ribbon = new ModelRenderer(this,48, 7);
		ribbon.addBox(-3, 0, 0, 6, 1, 5, 0F);
		ribbon.setRotationPoint(0F, 6F, 3F);
		ribbon.rotateAngleX = -0.9294652F;
		ribbon.rotateAngleY = 0F;
		ribbon.rotateAngleZ = 0F;
		ribbon.mirror = false;
		rightarm1 = new ModelRenderer(this,48, 18);
		rightarm1.addBox(0, 0, 0, 6, 10, 2, 0F);
		rightarm1.setRotationPoint(-3F, 9F, -3F);
		rightarm1.rotateAngleX = 0.2572064F;
		rightarm1.rotateAngleY = 0.03674378F;
		rightarm1.rotateAngleZ = -2.663924F;
		rightarm1.mirror = false;
		body2 = new ModelRenderer(this,0, 16);
		body2.addBox(-4, 0, -2, 8, 12, 4, 0F);
		body2.setRotationPoint(0F, 9F, 0F);
		body2.rotateAngleX = 0F;
		body2.rotateAngleY = 0F;
		body2.rotateAngleZ = 0F;
		body2.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
		setRotationAngles(f, f1, f2, f3, f4, f5);
		head.render(f5);
		body1.render(f5);
		rightarm2.render(f5);
		I_NEED_A_NAME.render(f5);
		leftarm.render(f5);
		belt.render(f5);
		hairbun.render(f5);
		ribbon.render(f5);
		rightarm1.render(f5);
		body2.render(f5);
	}

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	
	    head.rotateAngleY = f3 / 57.29578F;
        head.rotateAngleX = f4 / 57.29578F;
		hairbun.rotateAngleX = head.rotateAngleX;
		hairbun.rotateAngleY = head.rotateAngleY;
		
		
		 //I_NEED_A_NAME.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
        //Leg1.rotateAngleY = 0.0F;
		
		/*Antenna1.rotateAngleY = Head.rotateAngleY; Antenna1.rotateAngleX = Head.rotateAngleX; Antenna1.rotateAngleZ = Head.rotateAngleZ;
		Antenna2.rotateAngleY = Head.rotateAngleY; Antenna2.rotateAngleX = Head.rotateAngleX; Antenna2.rotateAngleZ = Head.rotateAngleZ;
		Jaws.rotateAngleY = Head.rotateAngleY; Jaws.rotateAngleX = Head.rotateAngleX; Jaws.rotateAngleZ = Head.rotateAngleZ;
		
        Leg1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
        Leg1.rotateAngleY = 0.0F;
		
		Leg2.rotateAngleX = Leg1.rotateAngleX; Leg2.rotateAngleY = Leg1.rotateAngleY; Leg2.rotateAngleZ = Leg1.rotateAngleZ; 
		Leg3.rotateAngleX = Leg1.rotateAngleX; Leg3.rotateAngleY = Leg1.rotateAngleY; Leg3.rotateAngleZ = Leg1.rotateAngleZ; 
		Leg4.rotateAngleX = Leg1.rotateAngleX; Leg4.rotateAngleY = Leg1.rotateAngleY; Leg4.rotateAngleZ = Leg1.rotateAngleZ; 
		Leg5.rotateAngleX = Leg1.rotateAngleX; Leg5.rotateAngleY = Leg1.rotateAngleY; Leg5.rotateAngleZ = Leg1.rotateAngleZ; 
		Leg6.rotateAngleX = Leg1.rotateAngleX; Leg6.rotateAngleY = Leg1.rotateAngleY; Leg6.rotateAngleZ = Leg1.rotateAngleZ; 
		*/
		
		
		/*
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
		*/
		
		//bucket2.rotateAngleX = rightarm.rotateAngleX;bucket2.rotateAngleY = rightarm.rotateAngleY;bucket2.rotateAngleZ = rightarm.rotateAngleZ;
		//handle1.rotateAngleX = rightarm.rotateAngleX;handle1.rotateAngleY = rightarm.rotateAngleY;handle1.rotateAngleZ = rightarm.rotateAngleZ;
		
		//bucket1.rotateAngleX = leftarm.rotateAngleX;bucket1.rotateAngleY = leftarm.rotateAngleY;bucket1.rotateAngleZ = leftarm.rotateAngleZ;
		//handle2.rotateAngleX = leftarm.rotateAngleX;handle2.rotateAngleY = leftarm.rotateAngleY;handle2.rotateAngleZ = leftarm.rotateAngleZ;
    }

	public ModelRenderer head;
	public ModelRenderer body1;
	public ModelRenderer rightarm2;
	public ModelRenderer I_NEED_A_NAME;
	public ModelRenderer leftarm;
	public ModelRenderer belt;
	public ModelRenderer hairbun;
	public ModelRenderer ribbon;
	public ModelRenderer rightarm1;
	public ModelRenderer body2;
}
