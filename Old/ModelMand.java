package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ModelMand extends ModelBase
{

    public ModelMand()
    {
        Head = new ModelRenderer(this,32, 4);
Head.addBox(-4, -8, -4, 8, 8, 8, 0F);
Head.setRotationPoint(0, 8, 0);

Head.rotateAngleX = 3.100328E-17F;
Head.rotateAngleY = 0F;
Head.rotateAngleZ = 0F;
Head.mirror = false;

torso = new ModelRenderer(this,44, 18);
torso.addBox(-3, -10, -2, 6, 10, 4, 0F);
torso.setRotationPoint(0, 18, 0);

torso.rotateAngleX = 0F;
torso.rotateAngleY = 0F;
torso.rotateAngleZ = 0F;
torso.mirror = false;

bipedLeftArm = new ModelRenderer(this,56, 0);
bipedLeftArm.addBox(-1, -1, -1, 2, 10, 2, 0F);
bipedLeftArm.setRotationPoint(4, 9, 0);

bipedLeftArm.rotateAngleX = 0F;
bipedLeftArm.rotateAngleY = 0F;
bipedLeftArm.rotateAngleZ = 0F;
bipedLeftArm.mirror = false;

bipedRightArm = new ModelRenderer(this,56, 0);
bipedRightArm.addBox(-1, -1, -1, 2, 10, 2, 0F);
bipedRightArm.setRotationPoint(-4, 9, 0);

bipedRightArm.rotateAngleX = 0F;
bipedRightArm.rotateAngleY = 0F;
bipedRightArm.rotateAngleZ = 0F;
bipedRightArm.mirror = false;

boob1 = new ModelRenderer(this,46, 20);
boob1.addBox(-3, -7, -7, 6, 3, 2, 0F);
boob1.setRotationPoint(0, 18, 0);

boob1.rotateAngleX = -0.5424333F;
boob1.rotateAngleY = 0F;
boob1.rotateAngleZ = 0F;
boob1.mirror = false;

roots1 = new ModelRenderer(this,0, 18);
roots1.addBox(-4, -8, -4, 8, 6, 8, 0F);
roots1.setRotationPoint(0, 26, 0);

roots1.rotateAngleX = 3.100328E-17F;
roots1.rotateAngleY = 0F;
roots1.rotateAngleZ = 0F;
roots1.mirror = false;

skirtF = new ModelRenderer(this,0, 0);
skirtF.addBox(-4, 0, 0, 8, 8, 0, 0F);
skirtF.setRotationPoint(0, 18, -4);

skirtF.rotateAngleX = -0.3490658F;
skirtF.rotateAngleY = 0F;
skirtF.rotateAngleZ = 0F;
skirtF.mirror = false;

SkirtR = new ModelRenderer(this,0, 0);
SkirtR.addBox(-4, 0, 0, 8, 8, 0, 0F);
SkirtR.setRotationPoint(-4, 18, 0);

SkirtR.rotateAngleX = -0.3490658F;
SkirtR.rotateAngleY = 1.570796F;
SkirtR.rotateAngleZ = 0F;
SkirtR.mirror = false;

SkirtR2 = new ModelRenderer(this,0, 0);
SkirtR2.addBox(-4, 0, 0, 8, 8, 0, 0F);
SkirtR2.setRotationPoint(4, 18, 0);

SkirtR2.rotateAngleX = -0.3490658F;
SkirtR2.rotateAngleY = -1.570796F;
SkirtR2.rotateAngleZ = 0F;
SkirtR2.mirror = false;

SkirtB = new ModelRenderer(this,0, 0);
SkirtB.addBox(-4, 0, 0, 8, 8, 0, 0F);
SkirtB.setRotationPoint(0, 18, 4);

SkirtB.rotateAngleX = -0.3490658F;
SkirtB.rotateAngleY = 3.141593F;
SkirtB.rotateAngleZ = 0F;
SkirtB.mirror = false;

Hair1 = new ModelRenderer(this,0, 9);
Hair1.addBox(-5, -15, 0, 10, 7, 0, 0F);
Hair1.setRotationPoint(0, 8, 0);

Hair1.rotateAngleX = 0F;
Hair1.rotateAngleY = 0.7853982F;
Hair1.rotateAngleZ = 0F;
Hair1.mirror = false;

Hair2 = new ModelRenderer(this,0, 9);
Hair2.addBox(-5, -15, 0, 10, 7, 0, 0F);
Hair2.setRotationPoint(0, 8, 0);

Hair2.rotateAngleX = 0F;
Hair2.rotateAngleY = -0.7853982F;
Hair2.rotateAngleZ = 0F;
Hair2.mirror = false;

Bud1 = new ModelRenderer(this,16, 0);
Bud1.addBox(-2, -12, -3, 6, 0, 6, 0F);
Bud1.setRotationPoint(0, 8, 0);

Bud1.rotateAngleX = 0F;
Bud1.rotateAngleY = -0.8179294F;
Bud1.rotateAngleZ = -0.4089647F;
Bud1.mirror = false;

Bud2 = new ModelRenderer(this,16, 0);
Bud2.addBox(-4, -11, 2, 6, 0, 6, 0F);
Bud2.setRotationPoint(0, 8, 0);

Bud2.rotateAngleX = 0.7435722F;
Bud2.rotateAngleY = -1.524323F;
Bud2.rotateAngleZ = -0.03717861F;
Bud2.mirror = false;

Bud3 = new ModelRenderer(this,16, 0);
Bud3.addBox(-3, -12, -3, 6, 0, 6, 0F);
Bud3.setRotationPoint(0, 8, 0);

Bud3.rotateAngleX = -0.2974289F;
Bud3.rotateAngleY = -0.669215F;
Bud3.rotateAngleZ = 0F;
Bud3.mirror = false;

Fruit = new ModelRenderer(this,0, 22);
Fruit.addBox(1, -11, -4, 2, 2, 2, 0F);
Fruit.setRotationPoint(0, 8, 0);

Fruit.rotateAngleX = 0F;
Fruit.rotateAngleY = 0F;
Fruit.rotateAngleZ = 0F;
Fruit.mirror = false;

roots2 = new ModelRenderer(this,0, 18);
roots2.addBox(0, 0, 0, 8, 6, 8, 0F);
roots2.setRotationPoint(-4, 24, -4);

roots2.rotateAngleX = 3.100328E-17F;
roots2.rotateAngleY = 0F;
roots2.rotateAngleZ = 0F;
roots2.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
Head.render(f5);
torso.render(f5);
bipedLeftArm.render(f5);
bipedRightArm.render(f5);
boob1.render(f5);
roots1.render(f5);
skirtF.render(f5);
SkirtR.render(f5);
SkirtR2.render(f5);
SkirtB.render(f5);
Hair1.render(f5);
Hair2.render(f5);
Bud1.render(f5);
Bud2.render(f5);
Bud3.render(f5);
Fruit.render(f5);
roots2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Head.rotateAngleY = f3 / 57.29578F;
        Head.rotateAngleX = f4 / 57.29578F;
		
		bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.2F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bipedRightArm.rotateAngleY = 0.0F;
        bipedLeftArm.rotateAngleY = 0.0F;
        if(onGround > -9990F)
        {
            float f6 = onGround;
            bipedRightArm.rotationPointZ = MathHelper.sin(torso.rotateAngleY) * 3.5F;
            bipedRightArm.rotationPointX = -MathHelper.cos(torso.rotateAngleY) * 3.5F;
            bipedLeftArm.rotationPointZ = -MathHelper.sin(torso.rotateAngleY) * 3.5F;
            bipedLeftArm.rotationPointX = MathHelper.cos(torso.rotateAngleY) * 3.5F;
            bipedRightArm.rotateAngleY += torso.rotateAngleY;
            bipedLeftArm.rotateAngleY += torso.rotateAngleY;
            bipedLeftArm.rotateAngleX += torso.rotateAngleY;
            f6 = 1.0F - onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            float f7 = MathHelper.sin(f6 * 3.141593F);
            float f8 = MathHelper.sin(onGround * 3.141593F) * -(Head.rotateAngleX - 0.7F) * 0.75F;
            bipedRightArm.rotateAngleX -= (double)f7 * 1.2D + (double)f8;
            bipedRightArm.rotateAngleY += torso.rotateAngleY * 2.0F;
            bipedRightArm.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) * -0.4F;
        }
        bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
		
        /*float f6 = 0.7853982F;
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
        Leg8.rotateAngleZ += -f16;*/
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
public ModelRenderer torso;
public ModelRenderer bipedLeftArm;
public ModelRenderer bipedRightArm;
public ModelRenderer boob1;
public ModelRenderer roots1;
public ModelRenderer skirtF;
public ModelRenderer SkirtR;
public ModelRenderer SkirtR2;
public ModelRenderer SkirtB;
public ModelRenderer Hair1;
public ModelRenderer Hair2;
public ModelRenderer Bud1;
public ModelRenderer Bud2;
public ModelRenderer Bud3;
public ModelRenderer Fruit;
public ModelRenderer roots2;
}
