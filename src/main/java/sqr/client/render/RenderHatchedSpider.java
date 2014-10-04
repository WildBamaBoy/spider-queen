/*******************************************************************************
 * RenderHatchedSpider.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sqr.client.model.ModelHatchedSpider;
import sqr.entity.EntityHatchedSpider;
import sqr.enums.EnumCocoonType;

public class RenderHatchedSpider extends RenderLiving
{
	public RenderHatchedSpider()
	{
		super(new ModelHatchedSpider(), 1.0F);
		setRenderPassModel(new ModelHatchedSpider());
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase entityLivingBase)
	{
		return 180.0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return getEntityTexture((EntityHatchedSpider) entity);
	}

	protected ResourceLocation getEntityTexture(EntityHatchedSpider entitySpider)
	{
		final int level = entitySpider.getLevel();
		
		if (entitySpider.cocoonType == EnumCocoonType.VILLAGER)
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderVillager" + level + ".png");
		}

		else if (entitySpider.cocoonType == EnumCocoonType.ENDERMAN || entitySpider.cocoonType == EnumCocoonType._ENDERMINION)
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderEnderman" + level + ".png");
		}

		else if (entitySpider.cocoonType == EnumCocoonType.CREEPER)
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderCreeper" + level + ".png");
		}

		else if (entitySpider.cocoonType == EnumCocoonType.WOLF)
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderWolf" + level + ".png");
		}

		else if (entitySpider.cocoonType == EnumCocoonType.SKELETON)
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderSkeleton" + level + ".png");
		}

		else if (entitySpider.cocoonType == EnumCocoonType.ZOMBIE)
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderZombie" + level + ".png");
		}

		else if (entitySpider.cocoonType == EnumCocoonType.BLAZE)
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderBlaze" + level + ".png");
		}

		else
		{
			return new ResourceLocation("spiderqueen:textures/entity/SpiderNormal" + level + ".png");
		}
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entitySpider, int passNumber, float partialTickTime)
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

		else if (hatchedSpider.cocoonType == EnumCocoonType.ZOMBIE)
		{
			final int level = hatchedSpider.getLevel();
			double scale = 1.0D;

			switch (level)
			{
				case 1:
					scale = 1.0D;
					break;
				case 2:
					scale = 1.3D;
					break;
				case 3:
					scale = 1.5D;
					break;
				default:
					scale = 1.3D;
					break;
			}

			GL11.glScaled(scale, scale, scale);
		}

		else if (hatchedSpider.cocoonType == EnumCocoonType.ENDERMAN)
		{
			GL11.glScaled(1.3D, 1.3D, 1.3D);
			
		}
		
		else
		{
			super.preRenderCallback(entityLiving, partialTickTime);
		}
	}
}
