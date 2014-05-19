/*******************************************************************************
 * RenderMiniGhast.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

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
	private static final ResourceLocation	ghastTextures	= new ResourceLocation("spiderqueen:textures/entity/MiniGhast.png");

	public RenderMiniGhast()
	{
		super(new ModelGhast(), 0.5F);
	}

	protected ResourceLocation getEntityTexture(EntityMiniGhast entityGhast)
	{
		return ghastTextures;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		super.preRenderCallback(entityLivingBase, partialTickTime);
		GL11.glTranslated(0.0D, -1.0D, 0.0D);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityMiniGhast) entity);
	}
}
