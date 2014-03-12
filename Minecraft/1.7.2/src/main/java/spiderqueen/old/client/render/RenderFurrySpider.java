package spiderqueen.old.client.render;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import org.lwjgl.opengl.GL11;

import spiderqueen.old.client.model.ModelTinySpider;
import spiderqueen.old.entity.EntityFriendlySpider;

public class RenderFurrySpider extends RenderLiving
{

    public RenderFurrySpider()
    {
        super(new ModelTinySpider(), 1.0F);
        setRenderPassModel(new ModelTinySpider());
    }

    protected float func_191_a(EntityFriendlySpider entityspider)
    {
        return 180F;
    }

    protected int setSpiderEyeBrightness(EntityFriendlySpider entityspider, int i, float f)
    {
        if(i != 0)
        {
            return 0;
        }
        if(i != 0)
        {
            return 0;
        } else
        {
            loadTexture("/imgz/spider_eyes.png");
            float f1 = (1.0F - entityspider.getBrightness(1.0F)) * 0.5F;
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            return 1;
        }
    }

    protected float func_172_a(EntityLiving entityliving)
    {
        return func_191_a((EntityFriendlySpider)entityliving);
    }

    protected int shouldRenderPass(EntityLiving entityliving, int i, float f)
    {
        return setSpiderEyeBrightness((EntityFriendlySpider)entityliving, i, f);
    }
}
