// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelWarrior extends ModelBase
{

    public ModelWarrior()
    {
float yo = 0F;
head = new ModelRenderer(this,0, 0);
head.addBox(-3, -6, -4, 6, 6, 6, 0F);
head.setRotationPoint(0, 10, 0);

head.rotateAngleX = 0;
head.rotateAngleY = 0;
head.rotateAngleZ = 0;

body = new ModelRenderer(this,16, 12);
body.addBox(-3, 0, -2, 6, 7, 3, 0F);
body.setRotationPoint(0, 10, 0);

body.rotateAngleX = 0.523598775598299F;
body.rotateAngleY = 0;
body.rotateAngleZ = 0;

rightarm = new ModelRenderer(this,8, 12);
rightarm.addBox(-1, -1, -1, 2, 8, 2, 0F);
rightarm.setRotationPoint(-4, 11, 0);

rightarm.rotateAngleX = 0;
rightarm.rotateAngleY = 0;
rightarm.rotateAngleZ = 0.261799387799149F;

leftarm = new ModelRenderer(this,8, 12);
leftarm.addBox(-1, -1, -1, 2, 8, 2, 0F);
leftarm.setRotationPoint(4, 11, 0);

leftarm.rotateAngleX = 0;
leftarm.rotateAngleY = 0;
leftarm.rotateAngleZ = -0.261799387799149F;

rightleg = new ModelRenderer(this,0, 12);
rightleg.addBox(-1, -1, -1, 2, 8, 2, 0F);
rightleg.setRotationPoint(-2, 17, 3);

rightleg.rotateAngleX = 0.55185050809461F;
rightleg.rotateAngleY = 0.135608315982293F;
rightleg.rotateAngleZ = -0.129330153205335F;

leftleg = new ModelRenderer(this,0, 12);
leftleg.addBox(-1, -1, -1, 2, 8, 2, 0F);
leftleg.setRotationPoint(2, 17, 3);

leftleg.rotateAngleX = 0.461444964106414F;
leftleg.rotateAngleY = 0;
leftleg.rotateAngleZ = 0.174532925199433F;

thorax = new ModelRenderer(this,34, 13);
thorax.addBox(-2, -2, 0, 4, 4, 5, 0F);
thorax.setRotationPoint(0, 15, 4);

thorax.rotateAngleX = 0;
thorax.rotateAngleY = 0;
thorax.rotateAngleZ = 0;

antenna1 = new ModelRenderer(this,0, -3);
antenna1.addBox(-2, -10, -3, 0, 4, 3, 0F);
antenna1.setRotationPoint(0, 10, 0);

antenna1.rotateAngleX = 0;
antenna1.rotateAngleY = 0;
antenna1.rotateAngleZ = 0;

antenna2 = new ModelRenderer(this,0, -3);
antenna2.addBox(2, -10, -3, 0, 4, 3, 0F);
antenna2.setRotationPoint(0, 10, 0);

antenna2.rotateAngleX = 0;
antenna2.rotateAngleY = 0;
antenna2.rotateAngleZ = 0;

lash1 = new ModelRenderer(this,26, 0);
lash1.addBox(3, -5, -4, 1, 3, 0, 0F);
lash1.setRotationPoint(0, 10, 0);

lash1.rotateAngleX = 0;
lash1.rotateAngleY = 0;
lash1.rotateAngleZ = 0;

lash2 = new ModelRenderer(this,26, 0);
lash2.addBox(-4, -5, -4, 1, 3, 0, 0F);
lash2.setRotationPoint(0, 10, 0);

lash2.rotateAngleX = 0;
lash2.rotateAngleY = 0;
lash2.rotateAngleZ = 0;

Stinger2 = new ModelRenderer(this,16, 22);
Stinger2.addBox(-1, -1, 5, 2, 2, 1, 0F);
Stinger2.setRotationPoint(0, 15+yo, 4);

Stinger2.rotateAngleX = 0F;
Stinger2.rotateAngleY = 0F;
Stinger2.rotateAngleZ = 0F;

Stinger1 = new ModelRenderer(this,16, 22);
Stinger1.addBox(-1, 0, 6, 2, 1, 1, 0F);
Stinger1.setRotationPoint(0, 15+yo, 4);

Stinger1.rotateAngleX = 0F;
Stinger1.rotateAngleY = 0F;
Stinger1.rotateAngleZ = 0F;		
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
		setRotationAngles(f, f1, f2, f3, f4, f5);
head.render(f5);
body.render(f5);
rightarm.render(f5);
leftarm.render(f5);
rightleg.render(f5);
leftleg.render(f5);
thorax.render(f5);
antenna1.render(f5);
antenna2.render(f5);
lash1.render(f5);
lash2.render(f5);

Stinger1.render(f5);
Stinger2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	
	    head.rotateAngleY = f3 / 57.29578F;
        head.rotateAngleX = f4 / 57.29578F;
		
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
		//handle2.rotateAngleX = leftarm.rotateAngleX;handle2.rotateAngleY = leftarm.rotateAngleY;handle2.rotateAngleZ = leftarm.rotateAngleZ;
    }

public ModelRenderer head;
public ModelRenderer body;
public ModelRenderer rightarm;
public ModelRenderer leftarm;
public ModelRenderer rightleg;
public ModelRenderer leftleg;
public ModelRenderer thorax;
public ModelRenderer antenna1;
public ModelRenderer antenna2;
public ModelRenderer handle1;
public ModelRenderer handle2;
public ModelRenderer bucket1;
public ModelRenderer bucket2;
public ModelRenderer lash1;
public ModelRenderer lash2;
public ModelRenderer Stinger1;
public ModelRenderer Stinger2;
}
