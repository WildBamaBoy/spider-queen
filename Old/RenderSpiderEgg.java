package spiderqueen.old.client.render;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import spiderqueen.old.client.model.ModelSpiderEgg;
import spiderqueen.old.entity.EntitySpiderEgg;

public class RenderSpiderEgg extends Render
{

    public RenderSpiderEgg()
    {
        shadowSize = 0.5F;
        modelEgg = new ModelSpiderEgg();
    }

    public void func_157_a(EntitySpiderEgg entityegg, double d, double d1, double d2, 
            float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(180F - f, 0.0F, 1.0F, 0.0F);
        float f2 = 0 - f1;
        float f3 = 0 - f1;
        if(f3 < 0.0F)
        {
            f3 = 0.0F;
        }
        if(f2 > 0.0F)
        {
            GL11.glRotatef(((MathHelper.sin(f2) * f2 * f3) / 10F) * 1, 1.0F, 0.0F, 0.0F);
        }
        loadTexture("/terrain.png");
        float f4 = 0.75F;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
        loadTexture("/imgz/spideregg.png");
        GL11.glScalef(-1F, -1F, 1.0F);
        modelEgg.render((Entity)entityegg,0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        func_157_a((EntitySpiderEgg)entity, d, d1, d2, f, f1);
    }

    protected ModelBase modelEgg;
}
