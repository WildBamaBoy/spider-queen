// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;


// Referenced classes of package net.minecraft.src:
//            ModelBase, MathHelper, ModelRenderer

public class ModelSpiderQueen extends ModelBase
{

    public ModelSpiderQueen()
    {
bipedHead = new ModelRenderer(this,32, 4);
bipedHead.addBox(-4, -8, -4, 8, 8, 8, 0F);
bipedHead.setRotationPoint(0, 3, -3);

bipedHead.rotateAngleX = 3.100328E-17F;
bipedHead.rotateAngleY = 0F;
bipedHead.rotateAngleZ = 0F;
bipedHead.mirror = false;

Body = new ModelRenderer(this,1, 0);
Body.addBox(-3, -3, -3, 6, 6, 5, 0F);
Body.setRotationPoint(0, 15, 0);

Body.rotateAngleX = 0F;
Body.rotateAngleY = 0F;
Body.rotateAngleZ = 0F;
Body.mirror = false;

RearEnd = new ModelRenderer(this,1, 11);
RearEnd.addBox(-5, -4, -6, 10, 10, 11, 0F);
RearEnd.setRotationPoint(0, 11, 7);

RearEnd.rotateAngleX = 1.230652E-16F;
RearEnd.rotateAngleY = 0F;
RearEnd.rotateAngleZ = 0F;
RearEnd.mirror = false;

Leg1 = new ModelRenderer(this,18, 0);
Leg1.addBox(-15, -1, -1, 16, 2, 2, 0F);
Leg1.setRotationPoint(-4, 15, 2);

Leg1.rotateAngleX = 0.5759587F;
Leg1.rotateAngleY = 0.1919862F;
Leg1.rotateAngleZ = 0F;
Leg1.mirror = false;

Leg2 = new ModelRenderer(this,18, 0);
Leg2.addBox(-1, -1, -1, 16, 2, 2, 0F);
Leg2.setRotationPoint(4, 15, 2);

Leg2.rotateAngleX = -0.5759587F;
Leg2.rotateAngleY = -0.1919862F;
Leg2.rotateAngleZ = 0F;
Leg2.mirror = false;

Leg3 = new ModelRenderer(this,18, 0);
Leg3.addBox(-15, -1, -1, 16, 2, 2, 0F);
Leg3.setRotationPoint(-4, 15, 1);

Leg3.rotateAngleX = 0.2792527F;
Leg3.rotateAngleY = 0.1919862F;
Leg3.rotateAngleZ = 0F;
Leg3.mirror = false;

Leg4 = new ModelRenderer(this,18, 0);
Leg4.addBox(-1, -1, -1, 16, 2, 2, 0F);
Leg4.setRotationPoint(4, 15, 1);

Leg4.rotateAngleX = -0.2792527F;
Leg4.rotateAngleY = -0.1919862F;
Leg4.rotateAngleZ = 0F;
Leg4.mirror = false;

Leg5 = new ModelRenderer(this,18, 0);
Leg5.addBox(-15, -1, -1, 16, 2, 2, 0F);
Leg5.setRotationPoint(-4, 15, 0);

Leg5.rotateAngleX = -0.2792527F;
Leg5.rotateAngleY = 0.1919862F;
Leg5.rotateAngleZ = 0F;
Leg5.mirror = false;

Leg6 = new ModelRenderer(this,18, 0);
Leg6.addBox(-1, -1, -1, 16, 2, 2, 0F);
Leg6.setRotationPoint(4, 15, 0);

Leg6.rotateAngleX = 0.2792527F;
Leg6.rotateAngleY = -0.1919862F;
Leg6.rotateAngleZ = 0F;
Leg6.mirror = false;

Leg7 = new ModelRenderer(this,18, 0);
Leg7.addBox(-15, -1, -1, 16, 2, 2, 0F);
Leg7.setRotationPoint(-4, 15, -1);

Leg7.rotateAngleX = -0.5759587F;
Leg7.rotateAngleY = 0.1919862F;
Leg7.rotateAngleZ = 0F;
Leg7.mirror = false;

Leg8 = new ModelRenderer(this,18, 0);
Leg8.addBox(-1, -1, -1, 16, 2, 2, 0F);
Leg8.setRotationPoint(4, 15, -1);

Leg8.rotateAngleX = 0.5759587F;
Leg8.rotateAngleY = -0.1919862F;
Leg8.rotateAngleZ = 0F;
Leg8.mirror = false;

torso = new ModelRenderer(this,44, 18);
torso.addBox(-3, -10, -2, 6, 10, 4, 0F);
torso.setRotationPoint(0, 13, -3);

torso.rotateAngleX = 0F;
torso.rotateAngleY = 0F;
torso.rotateAngleZ = 0F;
torso.mirror = false;

bipedLeftArm = new ModelRenderer(this,56, 0);
bipedLeftArm.addBox(-1, -1, -1, 2, 10, 2, 0F);
bipedLeftArm.setRotationPoint(4, 4, -3);

bipedLeftArm.rotateAngleX = 0F;
bipedLeftArm.rotateAngleY = 0F;
bipedLeftArm.rotateAngleZ = 0F;
bipedLeftArm.mirror = false;

bipedRightArm = new ModelRenderer(this,56, 0);
bipedRightArm.addBox(-1, -1, -1, 2, 10, 2, 0F);
bipedRightArm.setRotationPoint(-4, 4, -3);

bipedRightArm.rotateAngleX = 0F;
bipedRightArm.rotateAngleY = 0F;
bipedRightArm.rotateAngleZ = 0F;
bipedRightArm.mirror = false;

boob1 = new ModelRenderer(this,46, 20);
boob1.addBox(-3, -7, -7, 6, 3, 2, 0F);
boob1.setRotationPoint(0, 13, -3);

boob1.rotateAngleX = -0.5424333F;
boob1.rotateAngleY = 0F;
boob1.rotateAngleZ = 0F;
boob1.mirror = false;

spinner = new ModelRenderer(this,0, 0);
spinner.addBox(-1, -3, 0, 2, 3, 1, 0F);
spinner.setRotationPoint(2, 15, 11);

spinner.rotateAngleX = -0.5876361F;
spinner.rotateAngleY = 0F;
spinner.rotateAngleZ = 0F;
spinner.mirror = false;

spinner2 = new ModelRenderer(this,0, 0);
spinner2.addBox(-1, -3, 0, 2, 3, 1, 0F);
spinner2.setRotationPoint(-2, 15, 11);

spinner2.rotateAngleX = -0.5876361F;
spinner2.rotateAngleY = 0F;
spinner2.rotateAngleZ = 0F;
spinner2.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
    
bipedHead.render(f5);
Body.render(f5);
RearEnd.render(f5);
Leg1.render(f5);
Leg2.render(f5);
Leg3.render(f5);
Leg4.render(f5);
Leg5.render(f5);
Leg6.render(f5);
Leg7.render(f5);
Leg8.render(f5);
torso.render(f5);
bipedLeftArm.render(f5);
bipedRightArm.render(f5);
spinner.render(f5);
spinner2.render(f5);

		if(mod_SpiderQueen.isMale == 0 & !mod_SpiderQueen.tMale)
		{
			boob1.render(f5);
		}
		else
		{
			if(mod_SpiderQueen.tMale)
			{
				mod_SpiderQueen.setTMale(false);
			}
		}
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        //bipedHead.rotateAngleY = f3 / 57.29578F;
        //bipedHead.rotateAngleX = f4 / 57.29578F;
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
		
		bipedHead.rotateAngleY = f3 / 57.29578F;
        bipedHead.rotateAngleX = f4 / 57.29578F;
        bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        if(isRiding)
        {
            bipedRightArm.rotateAngleX += -0.6283185F;
            bipedLeftArm.rotateAngleX += -0.6283185F;
        }
        /*if(field_1279_h)
        {
            bipedLeftArm.rotateAngleX = bipedLeftArm.rotateAngleX * 0.5F - 0.3141593F;
        }
        if(field_1278_i)
        {
            bipedRightArm.rotateAngleX = bipedRightArm.rotateAngleX * 0.5F - 0.3141593F;
        }*/
        bipedRightArm.rotateAngleY = 0.0F;
        bipedLeftArm.rotateAngleY = 0.0F;
        if(onGround > -9990F)
        {
            float ff6 = onGround;
            Body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(ff6) * 3.141593F * 2.0F) * 0.2F;
            bipedRightArm.rotationPointZ = MathHelper.sin(Body.rotateAngleY) * 4F - 3F;
            bipedRightArm.rotationPointX = -MathHelper.cos(Body.rotateAngleY) * 4F;
            bipedLeftArm.rotationPointZ = -MathHelper.sin(Body.rotateAngleY) * 4F - 3F;
            bipedLeftArm.rotationPointX = MathHelper.cos(Body.rotateAngleY) * 4F;
            bipedRightArm.rotateAngleY += Body.rotateAngleY;
            bipedLeftArm.rotateAngleY += Body.rotateAngleY;
            bipedLeftArm.rotateAngleX += Body.rotateAngleY;
            ff6 = 1.0F - onGround;
            ff6 *= ff6;
            ff6 *= ff6;
            ff6 = 1.0F - ff6;
            float ff7 = MathHelper.sin(ff6 * 3.141593F);
            float ff8 = MathHelper.sin(onGround * 3.141593F) * -(bipedHead.rotateAngleX - 0.7F) * 0.75F;
            bipedRightArm.rotateAngleX -= (double)ff7 * 1.2D + (double)ff8;
            bipedRightArm.rotateAngleY += Body.rotateAngleY * 2.0F;
            bipedRightArm.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) * -0.4F;
        }
        /*if(isSneak)
        {
            Body.rotateAngleX = 0.5F;
            bipedRightArm.rotateAngleX += 0.4F;
            bipedLeftArm.rotateAngleX += 0.4F;
            bipedHead.rotationPointY = 1.0F;
        } else
        {*/
            Body.rotateAngleX = 0.0F;
            bipedHead.rotationPointY = 3F;
        //}
        bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
		
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

    ModelRenderer bipedHead;
	ModelRenderer Body;
	ModelRenderer RearEnd;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Leg5;
	ModelRenderer Leg6;
	ModelRenderer Leg7;
	ModelRenderer Leg8;
	ModelRenderer torso;
	ModelRenderer bipedLeftArm;
	ModelRenderer bipedRightArm;
	ModelRenderer boob1;
	ModelRenderer spinner;
	ModelRenderer spinner2;
    public boolean field_1278_i;
}
