/*******************************************************************************
 * RenderEnemyQueen.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.client.render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import spiderqueen.client.model.ModelSpiderQueen;

public class RenderEnemyQueen extends RenderLiving
{
	private ModelSpiderQueen modelBipedMain;

	public RenderEnemyQueen()
	{
		super(new ModelSpiderQueen(), 0.0F);
		this.modelBipedMain = new ModelSpiderQueen();
	}

	@Override
	protected void func_96449_a(EntityLivingBase entityLivingBase, double posX, double posY, double posZ, String string, float unknownFloat, double unknownDouble)
	{
		return;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		return;
	}

	@Override
	protected void func_82408_c(EntityLivingBase entityLivingBase, int renderPass, float partialTickTime)
	{
		return;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entityLivingBase, int renderPass, float partialTickTime)
	{
		return -1;
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		return;
	}

	@Override
	protected void rotateCorpse(EntityLivingBase entityLivingBase, float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		super.rotateCorpse(entityLivingBase, unknownFloat1, unknownFloat2, unknownFloat3);
	}

	@Override
	protected void renderLivingAt(EntityLivingBase entityLivingBase, double posX, double posY, double posZ)
	{
		super.renderLivingAt(entityLivingBase, posX, posY, posZ);
	}

	@Override
	public void doRender(EntityLivingBase entityLivingBase, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		super.doRender((EntityLivingBase)entityLivingBase, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		this.doRender((EntityLivingBase)entity, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	private float interpolateRotation(float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		float multiplier;

		for (multiplier = unknownFloat2 - unknownFloat1; multiplier < -180.0F; multiplier += 360.0F)
		{
			;
		}

		while (multiplier >= 180.0F)
		{
			multiplier -= 360.0F;
		}

		return unknownFloat1 + unknownFloat3 * multiplier;
	}

	@Override
	protected void bindEntityTexture(Entity entity) 
	{
		bindTexture(new ResourceLocation("spiderqueen:textures/entity/SpiderQueen.png"));
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO Auto-generated method stub
		return null;
	}
}
