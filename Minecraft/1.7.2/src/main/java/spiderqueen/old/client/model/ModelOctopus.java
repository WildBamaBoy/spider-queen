// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelOctopus extends ModelBase
{

    public ModelOctopus()
    {
Body = new ModelRenderer(this,0, 16);
Body.addBox(-4, -8, -4, 8, 8, 8, 0F);
Body.setRotationPoint(0, 18, 0);

Body.rotateAngleX = 3.100328E-17F;
Body.rotateAngleY = 0F;
Body.rotateAngleZ = 0F;
Body.mirror = false;

skirtF = new ModelRenderer(this,0, 0);
skirtF.addBox(-4, 0, 0, 8, 8, 0, 0F);
skirtF.setRotationPoint(0, 18, -2);

skirtF.rotateAngleX = -0.3490658F;
skirtF.rotateAngleY = 0F;
skirtF.rotateAngleZ = 0F;
skirtF.mirror = false;

SkirtR = new ModelRenderer(this,0, 0);
SkirtR.addBox(-4, 0, 0, 8, 8, 0, 0F);
SkirtR.setRotationPoint(-2, 18, 0);

SkirtR.rotateAngleX = -0.3490658F;
SkirtR.rotateAngleY = 1.570796F;
SkirtR.rotateAngleZ = 0F;
SkirtR.mirror = false;

SkirtR2 = new ModelRenderer(this,0, 0);
SkirtR2.addBox(-4, 0, 0, 8, 8, 0, 0F);
SkirtR2.setRotationPoint(2, 18, 0);

SkirtR2.rotateAngleX = -0.3490658F;
SkirtR2.rotateAngleY = -1.570796F;
SkirtR2.rotateAngleZ = 0F;
SkirtR2.mirror = false;

SkirtB = new ModelRenderer(this,0, 0);
SkirtB.addBox(-4, 0, 0, 8, 8, 0, 0F);
SkirtB.setRotationPoint(0, 18, 2);

SkirtB.rotateAngleX = -0.3490658F;
SkirtB.rotateAngleY = 3.141593F;
SkirtB.rotateAngleZ = 0F;
SkirtB.mirror = false;

mouth = new ModelRenderer(this,32, 24);
mouth.addBox(-2, -4, -6, 4, 4, 4, 0F);
mouth.setRotationPoint(0, 18, 0);

mouth.rotateAngleX = 0F;
mouth.rotateAngleY = 0F;
mouth.rotateAngleZ = 0F;
mouth.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
setRotationAngles(f, f1, f2, f3, f4, f5);
Body.render(f5);
skirtF.render(f5);
SkirtR.render(f5);
SkirtR2.render(f5);
SkirtB.render(f5);
mouth.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	
	    Body.rotateAngleY = f3 / 57.29578F; //Body.rotateAngleX = f4 / 57.29578F;
		//skirtF.rotateAngleY = f3 / 57.29578F; //skirtF.rotateAngleX = f4 / 57.29578F;
		//SkirtR.rotateAngleY = f3 / 57.29578F + 1.570796F; //SkirtR.rotateAngleX = f4 / 57.29578F;
		//SkirtR2.rotateAngleY = f3 / 57.29578F - 1.570796F; //SkirtR2.rotateAngleX = f4 / 57.29578F;
		//SkirtB.rotateAngleY = f3 / 57.29578F + 3.141593F; //SkirtB.rotateAngleX = f4 / 57.29578F;
		mouth.rotateAngleY = f3 / 57.29578F; mouth.rotateAngleX = f4 / 57.29578F;
		
	}

public ModelRenderer Body;
public ModelRenderer skirtF;
public ModelRenderer SkirtR;
public ModelRenderer SkirtR2;
public ModelRenderer SkirtB;
public ModelRenderer mouth;
}
