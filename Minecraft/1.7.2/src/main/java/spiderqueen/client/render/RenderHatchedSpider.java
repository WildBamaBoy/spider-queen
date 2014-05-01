package spiderqueen.client.render;

import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import spiderqueen.entity.EntityHatchedSpider;
import spiderqueen.enums.EnumCocoonType;

public class RenderHatchedSpider extends RenderSpider
{
    private static final ResourceLocation spiderEyesTextures = new ResourceLocation("textures/entity/spider_eyes.png");
    
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) 
	{
		return getEntityTexture((EntityHatchedSpider) par1Entity);
	}
	
	protected ResourceLocation getEntityTexture(EntityHatchedSpider entitySpider)
	{
		if (entitySpider.cocoonType == EnumCocoonType.VILLAGER)
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderVillager" + entitySpider.level + ".png");
		}
		
		else
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderNormal" + entitySpider.level + ".png");			
		}
	}
	
	@Override
    protected int shouldRenderPass(EntityLivingBase entitySpider, int par2, float par3)
    {
		return -1;
    }

	@Override
	protected void preRenderCallback(EntityLivingBase entityLiving, float partialTickTime) 
	{
		final EntityHatchedSpider hatchedSpider = (EntityHatchedSpider) entityLiving;
		
		if (hatchedSpider.cocoonType == EnumCocoonType.EMPTY)
		{
			GL11.glScaled(0.5D, 0.5D, 0.5D);	
		}
		
		else
		{
			super.preRenderCallback(entityLiving, partialTickTime);	
		}
	}
}
