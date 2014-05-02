/*******************************************************************************
 * RenderSpiderQueen.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.client.render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import spiderqueen.client.model.ModelSpiderQueen;

public class RenderSpiderQueen extends RenderPlayer
{
	private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");
	private ModelSpiderQueen modelBipedMain;

	public RenderSpiderQueen()
	{
		super();
		this.modelBipedMain = new ModelSpiderQueen();
		this.mainModel = modelBipedMain;
	}

	@Override
	protected int shouldRenderPass(AbstractClientPlayer clientPlayer, int renderPass, float partialTickTime)
	{
		return -1;
	}

	@Override
	protected void func_82408_c(AbstractClientPlayer clientPlayer, int renderPass, float partialTickTime)
	{
		return;
	}

	@Override
	public void doRender(AbstractClientPlayer clientPlayer, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);

		double modY = posY - (double)clientPlayer.yOffset;

		if (clientPlayer.isSneaking() && !(clientPlayer instanceof EntityPlayerSP))
		{
			modY -= 0.125D;
		}

		GL11.glPushMatrix();
		{
			final EntityLivingBase entity = (EntityLivingBase)clientPlayer;

			GL11.glDisable(GL11.GL_CULL_FACE);
			mainModel.onGround = renderSwingProgress(entity, rotationPitch);

			if (renderPassModel != null)
			{
				renderPassModel.onGround = mainModel.onGround;
			}

			mainModel.isRiding = entity.isRiding();

			if (renderPassModel != null)
			{
				renderPassModel.isRiding = mainModel.isRiding;
			}

			try
			{
				final float unknownConstant = -0.0925F;

				float realRotationPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * rotationPitch;
				float realRenderYaw = interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, rotationPitch);
				float realRenderYawHead = interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, rotationPitch);
				float wrappedRotation = handleRotationFloat(entity, rotationPitch);

				if (entity.isRiding() && entity.ridingEntity instanceof EntityLivingBase)
				{
					EntityLivingBase entityRiding = (EntityLivingBase)entity.ridingEntity;
					realRenderYaw = interpolateRotation(entityRiding.prevRenderYawOffset, entityRiding.renderYawOffset, rotationPitch);
					wrappedRotation = MathHelper.wrapAngleTo180_float(realRenderYawHead - realRenderYaw);

					if (wrappedRotation < -85.0F)
					{
						wrappedRotation = -85.0F;
					}

					if (wrappedRotation >= 85.0F)
					{
						wrappedRotation = 85.0F;
					}

					realRenderYaw = realRenderYawHead - wrappedRotation;

					if (wrappedRotation * wrappedRotation > 2500.0F)
					{
						realRenderYaw += wrappedRotation * 0.2F;
					}
				}

				renderLivingAt(entity, posX, posY, posZ);
				rotateCorpse(entity, wrappedRotation, realRenderYaw, rotationPitch);

				GL11.glTranslatef(0.0F, -0.10F, 0.0F);	//Move the model down slightly so that it touches the ground.
				GL11.glScalef(0.7F, 0.7F, -0.7F);		//Scale and flip the new model.

				float limbSwing = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * rotationPitch;
				float limbAngle = entity.limbSwing - entity.limbSwingAmount * (1.0F - rotationPitch);

				if (limbSwing > 1.0F)
				{
					limbSwing = 1.0F;
				}

				mainModel.setLivingAnimations(entity, limbAngle, limbSwing, rotationPitch);
				renderModel(entity, limbAngle, limbSwing, wrappedRotation, realRenderYawHead - realRenderYaw, realRotationPitch, unknownConstant);

				final float brightness = entity.getBrightness(rotationPitch);
				final int colorMultiplier = this.getColorMultiplier(entity, brightness, rotationPitch);

				if ((colorMultiplier >> 24 & 255) > 0 || entity.hurtTime > 0 || entity.deathTime > 0)
				{
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glDepthFunc(GL11.GL_EQUAL);

					if (entity.hurtTime > 0 || entity.deathTime > 0)
					{
						GL11.glColor4f(brightness, 0.0F, 0.0F, 0.4F);
						mainModel.render(entity, limbAngle, limbSwing, wrappedRotation, realRenderYawHead - realRenderYaw, realRotationPitch, unknownConstant);
					}

					if ((colorMultiplier >> 24 & 255) > 0)
					{
						final float colorR = (float)(colorMultiplier >> 16 & 255) / 255.0F;
						final float colorG = (float)(colorMultiplier >> 8 & 255) / 255.0F;
						final float colorB = (float)(colorMultiplier & 255) / 255.0F;
						final float colorA = (float)(colorMultiplier >> 24 & 255) / 255.0F;
						GL11.glColor4f(colorR, colorG, colorB, colorA);
						mainModel.render(entity, limbAngle, limbSwing, wrappedRotation, realRenderYawHead - realRenderYaw, realRotationPitch, unknownConstant);
					}

					GL11.glDepthFunc(GL11.GL_LEQUAL);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_ALPHA_TEST);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}
			}

			catch (Exception exception)
			{
				System.out.println("Failure to render entity. " + exception.getMessage());
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(AbstractClientPlayer clientPlayer)
	{
		return clientPlayer.getLocationSkin();
	}

	@Override
	protected void renderEquippedItems(AbstractClientPlayer clientPlayer, float partialTickTime)
	{
		return;
	}

	@Override
	protected void preRenderCallback(AbstractClientPlayer clientPlayer, float partialTickTime)
	{
		return;
	}

	@Override
	protected void func_96449_a(AbstractClientPlayer clientPlayer, double posX, double posY, double posZ, String string, float unknownFloat, double unknownDouble)
	{
		return;
	}

	@Override
	public void renderFirstPersonArm(EntityPlayer entityPlayer)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		modelBipedMain.onGround = 0.0F;
		modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, entityPlayer);
		//modelBipedMain.armRight.render(0.0625F); //TODO
	}

	@Override
	protected void renderLivingAt(AbstractClientPlayer clientPlayer, double posX, double posY, double posZ)
	{
		if (clientPlayer.isEntityAlive() && clientPlayer.isPlayerSleeping())
		{
			super.renderLivingAt(clientPlayer, posX + (double)clientPlayer.field_71079_bU, posY + (double)clientPlayer.field_71082_cx, posZ + (double)clientPlayer.field_71089_bV);
		}
		else
		{
			super.renderLivingAt(clientPlayer, posX, posY, posZ);
		}
	}

	@Override
	protected void rotateCorpse(AbstractClientPlayer clientPlayer, float unknownFloat1, float unknownFloat2, float unknownFloat3)
	{
		if (clientPlayer.isEntityAlive() && clientPlayer.isPlayerSleeping())
		{
			GL11.glRotatef(clientPlayer.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(getDeathMaxRotation(clientPlayer), 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
		}
		else
		{
			super.rotateCorpse(clientPlayer, unknownFloat1, unknownFloat2, unknownFloat3);
		}
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
		rotateCorpse((AbstractClientPlayer)entityLivingBase, unknownFloat1, unknownFloat2, unknownFloat3);
	}

	@Override
	protected void renderLivingAt(EntityLivingBase entityLivingBase, double posX, double posY, double posZ)
	{
		renderLivingAt((AbstractClientPlayer)entityLivingBase, posX, posY, posZ);
	}

	@Override
	public void doRender(EntityLivingBase entityLivingBase, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		super.doRender((AbstractClientPlayer)entityLivingBase, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return getEntityTexture((AbstractClientPlayer)entity);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch)
	{
		this.doRender((AbstractClientPlayer)entity, posX, posY, posZ, rotationYaw, rotationPitch);
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
		bindTexture(new ResourceLocation("spiderqueen:textures/entity/SpiderQueen1.png"));
	}
}
