package spiderqueen.old.client.render;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import spiderqueen.old.client.model.ModelAnt;

public class RenderAnt extends RenderLiving
{

    public RenderAnt()
    {
        super(new ModelAnt(), 1.0F);
        setRenderPassModel(new ModelAnt());
    }

}
