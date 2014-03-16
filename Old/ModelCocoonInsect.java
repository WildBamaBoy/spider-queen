// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;


// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelCocoonInsect extends ModelBase
{

    public ModelCocoonInsect()
    {
	
int yy = -12;
head = new ModelRenderer(this,0, 0);
head.addBox(-4, -4, -8, 8, 8, 8, 0F);
head.setRotationPoint(0, 20+yy, 2);

head.rotateAngleX = 0F;
head.rotateAngleY = 0F;
head.rotateAngleZ = 0F;

cocoon_2 = new ModelRenderer(this,20, 13);
cocoon_2.addBox(0, 0, 0, 10, 9, 10, 0F);
cocoon_2.setRotationPoint(-5, 15+yy, -2);

cocoon_2.rotateAngleX = 0F;
cocoon_2.rotateAngleY = 0F;
cocoon_2.rotateAngleZ = 0F;

feeler1 = new ModelRenderer(this,0, -3);
feeler1.addBox(0, -6, -2, 0, 6, 4, 0F);
feeler1.setRotationPoint(-2, 18+yy, -4);

feeler1.rotateAngleX = 0.4363323F;
feeler1.rotateAngleY = 0.5235988F;
feeler1.rotateAngleZ = 0F;

feeler2 = new ModelRenderer(this,0, -3);
feeler2.addBox(0, -6, -2, 0, 6, 4, 0F);
feeler2.setRotationPoint(2, 18+yy, -4);

feeler2.rotateAngleX = 0.4363323F;
feeler2.rotateAngleY = -0.5235988F;
feeler2.rotateAngleZ = 0F;

head2 = new ModelRenderer(this,28, 4);
head2.addBox(-3, -1, -3, 8, 1, 4, 0F);
head2.setRotationPoint(-1, 24+yy, -7);

head2.rotateAngleX = 0.01745329F;
head2.rotateAngleY = 0F;
head2.rotateAngleZ = 0F;



    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
setRotationAngles(f, f1, f2, f3, f4, f5);
head.render(f5);
cocoon_2.render(f5);
feeler1.render(f5);
feeler2.render(f5);
head2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
	
    }

public ModelRenderer head;
public ModelRenderer cocoon_2;
public ModelRenderer feeler1;
public ModelRenderer feeler2;
public ModelRenderer head2;
}
