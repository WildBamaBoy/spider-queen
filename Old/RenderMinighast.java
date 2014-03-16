package spiderqueen.old.client.render;

import net.minecraft.client.model.ModelGhast;

import org.lwjgl.opengl.GL11;

import spiderqueen.old.entity.EntityMinighast;

public class RenderMinighast extends RenderLiving
{
    public RenderMinighast()
    {
        super(new ModelGhast(), 0.5F);
    }

    protected void func_4014_a(EntityMinighast entityghast, float f)
    {
        EntityMinighast entityghast1 = entityghast;
        float f1 = ((float)entityghast1.prevAttackCounter + (float)(entityghast1.attackCounter - entityghast1.prevAttackCounter) * f) / 20F;
        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }
        f1 = 1.0F / (f1 * f1 * f1 * f1 * f1 * 2.0F + 1.0F);
        float f2 = (8F + f1) / 2.0F;
        float f3 = (8F + 1.0F / f1) / 2.0F;
        GL11.glScalef(f3/8F, f2/8F, f3/8F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
        func_4014_a((EntityMinighast)entityliving, f);
    }
}
