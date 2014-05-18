/*******************************************************************************
 * ModelSpiderQueen.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import spiderqueen.core.forge.PlayerExtension;
import spiderqueen.entity.EntityHatchedSpider;

public class ModelSpiderQueen extends ModelBase
{
	private final ModelRenderer	head;
	private final ModelRenderer	body;
	private final ModelRenderer	torso;
	private final ModelRenderer	rear;
	private final ModelRenderer	leg1;
	private final ModelRenderer	leg2;
	private final ModelRenderer	leg3;
	private final ModelRenderer	leg4;
	private final ModelRenderer	leg5;
	private final ModelRenderer	leg6;
	private final ModelRenderer	leg7;
	private final ModelRenderer	leg8;
	private final ModelRenderer	armLeft;
	public final ModelRenderer	armRight;
	private final ModelRenderer	breasts;
	private final ModelRenderer	spinner1;
	private final ModelRenderer	spinner2;

	public ModelSpiderQueen()
	{
		head = new ModelRenderer(this, 32, 4);
		head.addBox(-4, -8, -4, 8, 8, 8, 0F);
		head.setRotationPoint(0, 3, -3);

		head.rotateAngleX = 3.100328E-17F;
		head.rotateAngleY = 0F;
		head.rotateAngleZ = 0F;
		head.mirror = false;

		body = new ModelRenderer(this, 1, 0);
		body.addBox(-3, -3, -3, 6, 6, 5, 0F);
		body.setRotationPoint(0, 15, 0);

		body.rotateAngleX = 0F;
		body.rotateAngleY = 0F;
		body.rotateAngleZ = 0F;
		body.mirror = false;

		rear = new ModelRenderer(this, 1, 11);
		rear.addBox(-5, -4, -6, 10, 10, 11, 0F);
		rear.setRotationPoint(0, 11, 7);

		rear.rotateAngleX = 1.230652E-16F;
		rear.rotateAngleY = 0F;
		rear.rotateAngleZ = 0F;
		rear.mirror = false;

		leg1 = new ModelRenderer(this, 18, 0);
		leg1.addBox(-15, -1, -1, 16, 2, 2, 0F);
		leg1.setRotationPoint(-4, 15, 2);

		leg1.rotateAngleX = 0.5759587F;
		leg1.rotateAngleY = 0.1919862F;
		leg1.rotateAngleZ = 0F;
		leg1.mirror = false;

		leg2 = new ModelRenderer(this, 18, 0);
		leg2.addBox(-1, -1, -1, 16, 2, 2, 0F);
		leg2.setRotationPoint(4, 15, 2);

		leg2.rotateAngleX = -0.5759587F;
		leg2.rotateAngleY = -0.1919862F;
		leg2.rotateAngleZ = 0F;
		leg2.mirror = false;

		leg3 = new ModelRenderer(this, 18, 0);
		leg3.addBox(-15, -1, -1, 16, 2, 2, 0F);
		leg3.setRotationPoint(-4, 15, 1);

		leg3.rotateAngleX = 0.2792527F;
		leg3.rotateAngleY = 0.1919862F;
		leg3.rotateAngleZ = 0F;
		leg3.mirror = false;

		leg4 = new ModelRenderer(this, 18, 0);
		leg4.addBox(-1, -1, -1, 16, 2, 2, 0F);
		leg4.setRotationPoint(4, 15, 1);

		leg4.rotateAngleX = -0.2792527F;
		leg4.rotateAngleY = -0.1919862F;
		leg4.rotateAngleZ = 0F;
		leg4.mirror = false;

		leg5 = new ModelRenderer(this, 18, 0);
		leg5.addBox(-15, -1, -1, 16, 2, 2, 0F);
		leg5.setRotationPoint(-4, 15, 0);

		leg5.rotateAngleX = -0.2792527F;
		leg5.rotateAngleY = 0.1919862F;
		leg5.rotateAngleZ = 0F;
		leg5.mirror = false;

		leg6 = new ModelRenderer(this, 18, 0);
		leg6.addBox(-1, -1, -1, 16, 2, 2, 0F);
		leg6.setRotationPoint(4, 15, 0);

		leg6.rotateAngleX = 0.2792527F;
		leg6.rotateAngleY = -0.1919862F;
		leg6.rotateAngleZ = 0F;
		leg6.mirror = false;

		leg7 = new ModelRenderer(this, 18, 0);
		leg7.addBox(-15, -1, -1, 16, 2, 2, 0F);
		leg7.setRotationPoint(-4, 15, -1);

		leg7.rotateAngleX = -0.5759587F;
		leg7.rotateAngleY = 0.1919862F;
		leg7.rotateAngleZ = 0F;
		leg7.mirror = false;

		leg8 = new ModelRenderer(this, 18, 0);
		leg8.addBox(-1, -1, -1, 16, 2, 2, 0F);
		leg8.setRotationPoint(4, 15, -1);

		leg8.rotateAngleX = 0.5759587F;
		leg8.rotateAngleY = -0.1919862F;
		leg8.rotateAngleZ = 0F;
		leg8.mirror = false;

		torso = new ModelRenderer(this, 44, 18);
		torso.addBox(-3, -10, -2, 6, 10, 4, 0F);
		torso.setRotationPoint(0, 13, -3);

		torso.rotateAngleX = 0F;
		torso.rotateAngleY = 0F;
		torso.rotateAngleZ = 0F;
		torso.mirror = false;

		armLeft = new ModelRenderer(this, 56, 0);
		armLeft.addBox(-1, -1, -1, 2, 10, 2, 0F);
		armLeft.setRotationPoint(4, 4, -3);

		armLeft.rotateAngleX = 0F;
		armLeft.rotateAngleY = 0F;
		armLeft.rotateAngleZ = 0F;
		armLeft.mirror = false;

		armRight = new ModelRenderer(this, 56, 0);
		armRight.addBox(-1, -1, -1, 2, 10, 2, 0F);
		armRight.setRotationPoint(-4, 4, -3);

		armRight.rotateAngleX = 0F;
		armRight.rotateAngleY = 0F;
		armRight.rotateAngleZ = 0F;
		armRight.mirror = false;

		breasts = new ModelRenderer(this, 46, 20);
		breasts.addBox(-3, -7, -7, 6, 3, 2, 0F);
		breasts.setRotationPoint(0, 13, -3);

		breasts.rotateAngleX = -0.5424333F;
		breasts.rotateAngleY = 0F;
		breasts.rotateAngleZ = 0F;
		breasts.mirror = false;

		spinner1 = new ModelRenderer(this, 0, 0);
		spinner1.addBox(-1, -3, 0, 2, 3, 1, 0F);
		spinner1.setRotationPoint(2, 15, 11);

		spinner1.rotateAngleX = -0.5876361F;
		spinner1.rotateAngleY = 0F;
		spinner1.rotateAngleZ = 0F;
		spinner1.mirror = false;

		spinner2 = new ModelRenderer(this, 0, 0);
		spinner2.addBox(-1, -3, 0, 2, 3, 1, 0F);
		spinner2.setRotationPoint(-2, 15, 11);

		spinner2.rotateAngleX = -0.5876361F;
		spinner2.rotateAngleY = 0F;
		spinner2.rotateAngleZ = 0F;
		spinner2.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);
		boolean isKing = false;
		
		if (entity instanceof EntityPlayer)
		{
			final EntityPlayer player = (EntityPlayer) entity;
			final PlayerExtension playerExtension = (PlayerExtension) player.getExtendedProperties(PlayerExtension.ID);
			
			if (player.ridingEntity instanceof EntityHatchedSpider)
			{
				final EntityHatchedSpider spider = (EntityHatchedSpider) player.ridingEntity;

				if (spider.isOnLadder())
				{
					final Vec3 lookVector = spider.getLookVec();

					if (lookVector.xCoord <= -0.90 || lookVector.zCoord <= -0.90)
					{
						GL11.glRotatef(270, 1, 0, 0);
						GL11.glTranslated(0, 1.8, 0);
					}

					else if (lookVector.xCoord >= 0.90 || lookVector.zCoord >= 0.90)
					{
						GL11.glRotatef(-90, 1, 0, 0);
						GL11.glTranslated(0, 1.8, 0);
					}
				}
			}
			
			if (playerExtension.selectedSkin.contains("King"))
			{
				isKing = true;
			}
		}

		head.render(f5);
		body.render(f5);
		rear.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
		leg7.render(f5);
		leg8.render(f5);
		torso.render(f5);
		armLeft.render(f5);
		armRight.render(f5);
		spinner1.render(f5);
		spinner2.render(f5);
		
		if (!isKing)
		{
			breasts.render(f5);
		}
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		head.rotateAngleY = f3 / (180F / (float) Math.PI);
		head.rotateAngleX = f4 / (180F / (float) Math.PI);

		final float f6 = 0.7853982F;
		leg1.rotateAngleZ = -f6;
		leg2.rotateAngleZ = f6;
		leg3.rotateAngleZ = -f6 * 0.74F;
		leg4.rotateAngleZ = f6 * 0.74F;
		leg5.rotateAngleZ = -f6 * 0.74F;
		leg6.rotateAngleZ = f6 * 0.74F;
		leg7.rotateAngleZ = -f6;
		leg8.rotateAngleZ = f6;
		final float f7 = -0F;
		final float f8 = 0.3926991F;
		leg1.rotateAngleY = f8 * 2.0F + f7;
		leg2.rotateAngleY = -f8 * 2.0F - f7;
		leg3.rotateAngleY = f8 * 1.0F + f7;
		leg4.rotateAngleY = -f8 * 1.0F - f7;
		leg5.rotateAngleY = -f8 * 1.0F + f7;
		leg6.rotateAngleY = f8 * 1.0F - f7;
		leg7.rotateAngleY = -f8 * 2.0F + f7;
		leg8.rotateAngleY = f8 * 2.0F - f7;
		final float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
		final float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
		final float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
		final float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * f1;
		final float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
		final float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
		final float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
		final float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;

		armRight.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
		armLeft.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		armRight.rotateAngleZ = 0.0F;
		armLeft.rotateAngleZ = 0.0F;
		if (isRiding)
		{
			armRight.rotateAngleX += -0.6283185F;
			armLeft.rotateAngleX += -0.6283185F;
		}
		/*
		 * if(field_1279_h) { bipedLeftArm.rotateAngleX =
		 * bipedLeftArm.rotateAngleX * 0.5F - 0.3141593F; } if(field_1278_i) {
		 * bipedRightArm.rotateAngleX = bipedRightArm.rotateAngleX * 0.5F -
		 * 0.3141593F; }
		 */
		armRight.rotateAngleY = 0.0F;
		armLeft.rotateAngleY = 0.0F;
		if (onGround > -9990F)
		{
			float ff6 = onGround;
			body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(ff6) * 3.141593F * 2.0F) * 0.2F;
			armRight.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 4F - 3F;
			armRight.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 4F;
			armLeft.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 4F - 3F;
			armLeft.rotationPointX = MathHelper.cos(body.rotateAngleY) * 4F;
			armRight.rotateAngleY += body.rotateAngleY;
			armLeft.rotateAngleY += body.rotateAngleY;
			armLeft.rotateAngleX += body.rotateAngleY;
			ff6 = 1.0F - onGround;
			ff6 *= ff6;
			ff6 *= ff6;
			ff6 = 1.0F - ff6;
			final float ff7 = MathHelper.sin(ff6 * 3.141593F);
			final float ff8 = MathHelper.sin(onGround * 3.141593F) * -(head.rotateAngleX - 0.7F) * 0.75F;
			armRight.rotateAngleX -= ff7 * 1.2D + ff8;
			armRight.rotateAngleY += body.rotateAngleY * 2.0F;
			armRight.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) * -0.4F;
		}
		/*
		 * if(isSneak) { Body.rotateAngleX = 0.5F; bipedRightArm.rotateAngleX +=
		 * 0.4F; bipedLeftArm.rotateAngleX += 0.4F; bipedHead.rotationPointY =
		 * 1.0F; } else {
		 */
		body.rotateAngleX = 0.0F;
		head.rotationPointY = 3F;
		// }
		armRight.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		armLeft.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		armRight.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		armLeft.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;

		leg1.rotateAngleY += f9;
		leg2.rotateAngleY += -f9;
		leg3.rotateAngleY += f10;
		leg4.rotateAngleY += -f10;
		leg5.rotateAngleY += f11;
		leg6.rotateAngleY += -f11;
		leg7.rotateAngleY += f12;
		leg8.rotateAngleY += -f12;
		leg1.rotateAngleZ += f13;
		leg2.rotateAngleZ += -f13;
		leg3.rotateAngleZ += f14;
		leg4.rotateAngleZ += -f14;
		leg5.rotateAngleZ += f15;
		leg6.rotateAngleZ += -f15;
		leg7.rotateAngleZ += f16;
		leg8.rotateAngleZ += -f16;
	}
}
