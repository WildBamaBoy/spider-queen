// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelCocoonChicken extends ModelBase
{

    public ModelCocoonChicken()
    {
       	float scale = 0F;
		Head = new ModelRenderer(this,0, 0);
		Head.addBox(-2F, -6F, -2F, 4, 6, 3, scale);
		Head.setRotationPoint(0F, 7F, -7F);

		Beak = new ModelRenderer(this,14, 0);
		Beak.addBox(-2F, -4F, -4F, 4, 2, 2, scale);
		Beak.setRotationPoint(0F, 7F, -7F);

		Chin = new ModelRenderer(this,14, 4);
		Chin.addBox(-1F, -201F, -3F, 2, 2, 2, scale);
		Chin.setRotationPoint(0F, 4F, -4F);

		New_Shape = new ModelRenderer(this,16, 7);
		New_Shape.addBox(0F, 0F, 0F, 12, 13, 12, scale);
		New_Shape.setRotationPoint(-6F, 0F, -6F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
		Head.render(f5);
		Beak.render(f5);
		Chin.render(f5);
		New_Shape.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        Head.rotateAngleY = f3 / 57.29578F;
        Beak.rotateAngleY = f3 / 57.29578F;
        Chin.rotateAngleY = f3 / 57.29578F;
        New_Shape.rotateAngleY = f3 / 57.29578F;
    }

	ModelRenderer Head;
	ModelRenderer Beak;
	ModelRenderer Chin;
	ModelRenderer New_Shape;
}
