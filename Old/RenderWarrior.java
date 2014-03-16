package spiderqueen.old.client.render;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import spiderqueen.old.client.model.ModelWarrior;

public class RenderWarrior extends RenderLiving
{

    public RenderWarrior()
    {
        super(new ModelWarrior(), 1.0F);
        setRenderPassModel(new ModelWarrior());
    }

}
