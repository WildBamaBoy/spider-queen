package spiderqueen.client.render;

import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import spiderqueen.entity.EntityMiniGhast;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMiniGhast extends RenderLiving
{
    private static final ResourceLocation ghastTextures = new ResourceLocation("spiderqueen:textures/entity/MiniGhast.png");

    public RenderMiniGhast()
    {
        super(new ModelGhast(), 0.5F);
    }

    protected ResourceLocation getEntityTexture(EntityMiniGhast par1EntityGhast)
    {
    	return ghastTextures;
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        super.preRenderCallback(par1EntityLivingBase, par2);
        GL11.glTranslated(0.0D, -1.0D, 0.0D);
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityMiniGhast)par1Entity);
    }
}