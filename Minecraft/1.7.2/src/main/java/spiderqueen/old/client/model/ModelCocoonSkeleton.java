// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelCocoonSkeleton extends ModelBase
{

    public ModelCocoonSkeleton()
    {
		float scale = 0F;
		float y = -24F;
		Head = new ModelRenderer(this,0, 0);
		Head.addBox(-4F, -4F, -8F, 8, 8, 8, scale);
		Head.setRotationPoint(0F, 20F+y, 0F);

		cocoon_1 = new ModelRenderer(this,16, 8);
		cocoon_1.addBox(0F, 0F, 0F, 12, 12, 12, scale);
		cocoon_1.setRotationPoint(-6F, 24F+y, -6F);

		cocoon_2 = new ModelRenderer(this,20, 13);
		cocoon_2.addBox(0F, 0F, 0F, 10, 9, 10, scale);
		cocoon_2.setRotationPoint(-5F, 15F+y, -5F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
		cocoon_1.render(f5);
		cocoon_2.render(f5);
		Head.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        cocoon_1.rotateAngleY = f3 / 57.29578F;
        cocoon_2.rotateAngleY = f3 / 57.29578F;
        Head.rotateAngleY = f3 / 57.29578F;
    }

	ModelRenderer Head;
	ModelRenderer cocoon_1;
	ModelRenderer cocoon_2;
}
