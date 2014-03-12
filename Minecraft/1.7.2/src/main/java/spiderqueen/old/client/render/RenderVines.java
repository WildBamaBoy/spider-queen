package spiderqueen.old.client.render;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import spiderqueen.old.client.model.ModelVines;
import spiderqueen.old.entity.EntityVines;

public class RenderVines extends Render
{

    public RenderVines()
    {
        shadowSize = 0.5F;
        modelVines = new ModelVines();
    }

    public void func_157_a(EntityVines entity, double d, double d1, double d2, 
            float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
        float f2 = (float) -f1;
        float f3 = (float) -f1;
        if(f3 < 0.0F)
        {
            f3 = 0.0F;
        }
        if(f2 > 0.0F)
        {
            GL11.glRotatef(((MathHelper.sin(f2) * f2 * f3) / 10F), 1.0F, 0.0F, 0.0F);
        }
        loadTexture("/imgz/vines.png");
        GL11.glScalef(-1F, -1F, 1.0F);
        modelVines.render((Entity)entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        func_157_a((EntityVines)entity, d, d1, d2, f, f1);
    }

    protected ModelBase modelVines;

}
