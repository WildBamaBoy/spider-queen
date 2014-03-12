// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelCocoonWolf extends ModelBase
{

    public ModelCocoonWolf()
    {
		float y = -12F;
		WolfHead = new ModelRenderer(this,0, 0);
WolfHead.addBox(-3, -3, -2, 6, 6, 4, 0F);
WolfHead.setRotationPoint(0F, 14.5F+y, -7F);

WolfHead.rotateAngleX = 0F;
WolfHead.rotateAngleY = 0F;
WolfHead.rotateAngleZ = 0F;

Ear1 = new ModelRenderer(this,16, 14);
Ear1.addBox(-3, -5, 0, 2, 2, 1, 0F);
Ear1.setRotationPoint(0F, 14.5F+y, -7F);

Ear1.rotateAngleX = 0F;
Ear1.rotateAngleY = 0F;
Ear1.rotateAngleZ = 0F;

Ear2 = new ModelRenderer(this,16, 14);
Ear2.addBox(1, -5, 0, 2, 2, 1, 0F);
Ear2.setRotationPoint(0F, 14.5F+y, -7F);

Ear2.rotateAngleX = 0F;
Ear2.rotateAngleY = 0F;
Ear2.rotateAngleZ = 0F;

Nose = new ModelRenderer(this,0, 10);
Nose.addBox(-2, 0, -5, 3, 3, 4, 0F);
Nose.setRotationPoint(0.5F, 14.5F+y, -7F);

Nose.rotateAngleX = 0F;
Nose.rotateAngleY = 0F;
Nose.rotateAngleZ = 0F;

Cocoon = new ModelRenderer(this,16, 8);
Cocoon.addBox(-6, 0, -5, 12, 12, 12, 0F);
Cocoon.setRotationPoint(0F, 12F+y, 0F);

Cocoon.rotateAngleX = 0F;
Cocoon.rotateAngleY = 0F;
Cocoon.rotateAngleZ = 0F;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
WolfHead.render(f5);
Ear1.render(f5);
Ear2.render(f5);
Nose.render(f5);
Cocoon.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	super.setRotationAngles(f, f1, f2, f3, f4, f5);
    }

public ModelRenderer WolfHead;
public ModelRenderer Ear1;
public ModelRenderer Ear2;
public ModelRenderer Nose;
public ModelRenderer Cocoon;
}
