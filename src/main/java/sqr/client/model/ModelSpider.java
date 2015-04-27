/*******************************************************************************
 * ModelHatchedSpider.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSpider extends ModelBase
{
	public ModelRenderer	villagerSpiderChestRight;
	public ModelRenderer	villagerSpiderChestLeft;

	public ModelRenderer	defaultSpiderHead;
	public ModelRenderer	defaultSpiderNeck;
	public ModelRenderer	defaultSpiderBody;
	public ModelRenderer	defaultSpiderLeg1;
	public ModelRenderer	defaultSpiderLeg2;
	public ModelRenderer	defaultSpiderLeg3;
	public ModelRenderer	defaultSpiderLeg4;
	public ModelRenderer	defaultSpiderLeg5;
	public ModelRenderer	defaultSpiderLeg6;
	public ModelRenderer	defaultSpiderLeg7;
	public ModelRenderer	defaultSpiderLeg8;

	public ModelRenderer	raisedSpiderHead;
	public ModelRenderer	raisedSpiderBody;
	public ModelRenderer	raisedSpiderRearEnd;
	public ModelRenderer	raisedSpiderLeg1;
	public ModelRenderer	raisedSpiderLeg2;
	public ModelRenderer	raisedSpiderLeg3;
	public ModelRenderer	raisedSpiderLeg4;
	public ModelRenderer	raisedSpiderLeg5;
	public ModelRenderer	raisedSpiderLeg6;
	public ModelRenderer	raisedSpiderLeg7;
	public ModelRenderer	raisedSpiderLeg8;

	public ModelRenderer	tinySpiderHead;
	public ModelRenderer	tinySpiderBody;
	public ModelRenderer	tinySpiderRearEnd;
	public ModelRenderer	tinySpiderLeg1;
	public ModelRenderer	tinySpiderLeg2;
	public ModelRenderer	tinySpiderLeg3;
	public ModelRenderer	tinySpiderLeg4;
	public ModelRenderer	tinySpiderLeg5;
	public ModelRenderer	tinySpiderLeg6;
	public ModelRenderer	tinySpiderLeg7;
	public ModelRenderer	tinySpiderLeg8;

	public ModelRenderer	thinSpiderHead;
	public ModelRenderer	thinSpiderBody;
	public ModelRenderer	thinSpiderRearEnd;
	public ModelRenderer	thinSpiderLeg1;
	public ModelRenderer	thinSpiderLeg2;
	public ModelRenderer	thinSpiderLeg3;
	public ModelRenderer	thinSpiderLeg4;
	public ModelRenderer	thinSpiderLeg5;
	public ModelRenderer	thinSpiderLeg6;
	public ModelRenderer	thinSpiderLeg7;
	public ModelRenderer	thinSpiderLeg8;

	public ModelRenderer	hugeSpiderHead;
	public ModelRenderer	hugeSpiderBody;
	public ModelRenderer	hugeSpiderRearEnd;
	public ModelRenderer	hugeSpiderLeg1;
	public ModelRenderer	hugeSpiderLeg2;
	public ModelRenderer	hugeSpiderLeg3;
	public ModelRenderer	hugeSpiderLeg4;
	public ModelRenderer	hugeSpiderLeg5;
	public ModelRenderer	hugeSpiderLeg6;
	public ModelRenderer	hugeSpiderLeg7;
	public ModelRenderer	hugeSpiderLeg8;
	public ModelRenderer	hugeSpiderTopBulk;
	public ModelRenderer	hugeSpiderBackBulk;
	public ModelRenderer	hugeSpiderRightBulk;
	public ModelRenderer	hugeSpiderLeftBulk;

	public ModelRenderer	longLegSpiderBody;
	public ModelRenderer	longLegSpiderLeg8;
	public ModelRenderer	longLegSpiderLeg6;
	public ModelRenderer	longLegSpiderLeg4;
	public ModelRenderer	longLegSpiderLeg2;
	public ModelRenderer	longLegSpiderLeg7;
	public ModelRenderer	longLegSpiderLeg5;
	public ModelRenderer	longLegSpiderLeg3;
	public ModelRenderer	longLegSpiderLeg1;

	public ModelRenderer	flatSpiderHead;
	public ModelRenderer	flatSpiderBody;
	public ModelRenderer	flatSpiderRearEnd;
	public ModelRenderer	flatSpiderLeg8;
	public ModelRenderer	flatSpiderLeg6;
	public ModelRenderer	flatSpiderLeg4;
	public ModelRenderer	flatSpiderLeg2;
	public ModelRenderer	flatSpiderLeg7;
	public ModelRenderer	flatSpiderLeg5;
	public ModelRenderer	flatSpiderLeg3;
	public ModelRenderer	flatSpiderLeg1;

	public ModelSpider()
	{
		this.initDefaultSpider();
		this.initRaisedSpider();
		this.initTinySpider();
		this.initThinSpider();
		this.initHugeSpider();
		this.initLongLegSpider();
		this.initFlatSpider();
		this.initVillagerSpiderChest();
	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float rotateFloat, float rotationYaw, float rotationPitch, float partialTickTime)
	{
//		final EntityHatchedSpider spider = (EntityHatchedSpider) entity;
//		this.setRotationAngles(limbSwing, prevLimbSwing, rotateFloat, rotationYaw, rotationPitch, partialTickTime, entity);
//
//		switch (spider.cocoonType.getSpiderSize())
//		{
//		case HUGE:
//			this.renderHugeSpider(partialTickTime);
//			break;
//		case NORMAL:
//			this.renderDefaultSpider(partialTickTime);
//
//			if (spider.cocoonType == EnumTypeVariant.VILLAGER)
//			{
//				this.renderVillagerSpiderChest(partialTickTime);
//			}
//
//			break;
//		case RAISED:
//			this.renderRaisedSpider(partialTickTime);
//			break;
//		case THIN:
//			this.renderThinSpider(partialTickTime);
//			break;
//		case TINY:
//			this.renderTinySpider(partialTickTime);
//			break;
//		case LONGLEG:
//			this.renderLongLegSpider(partialTickTime);
//			break;
//		case TINYLONGLEG:
//			this.renderTinyLongLegSpider(partialTickTime);
//			break;
//		case SWARM:
//			this.renderSwarmSpiders(entity, partialTickTime);
//			break;
//		case FLAT:
//			this.renderFlatSpider(partialTickTime);
//			break;
//		default:
//			break;
//		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float rotateFloat, float rotationYaw, float rotationPitch, float partialTickTime, Entity entity)
	{
		//TODO
//		final EntityHatchedSpider spider = (EntityHatchedSpider) entity;
//
//		if (EnumTypeVariant.isAnimalBased(spider.cocoonType) || spider.cocoonType == EnumTypeVariant.EMPTY || spider.cocoonType == EnumTypeVariant.VILLAGER || spider.cocoonType == EnumTypeVariant.HUMAN || spider.cocoonType == EnumTypeVariant.HORSE)
//		{
//			this.defaultSpiderHead.rotateAngleY = rotationYaw / (180F / (float) Math.PI);
//			this.defaultSpiderHead.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
//
//			final float quarterCircle = (float) Math.PI / 4F;
//			this.defaultSpiderLeg1.rotateAngleZ = -quarterCircle;
//			this.defaultSpiderLeg2.rotateAngleZ = quarterCircle;
//			this.defaultSpiderLeg3.rotateAngleZ = -quarterCircle * 0.74F;
//			this.defaultSpiderLeg4.rotateAngleZ = quarterCircle * 0.74F;
//			this.defaultSpiderLeg5.rotateAngleZ = -quarterCircle * 0.74F;
//			this.defaultSpiderLeg6.rotateAngleZ = quarterCircle * 0.74F;
//			this.defaultSpiderLeg7.rotateAngleZ = -quarterCircle;
//			this.defaultSpiderLeg8.rotateAngleZ = quarterCircle;
//
//			final float eighthCircle = (float) Math.PI / 8F;
//			this.defaultSpiderLeg1.rotateAngleY = eighthCircle * 2.0F;
//			this.defaultSpiderLeg2.rotateAngleY = -eighthCircle * 2.0F;
//			this.defaultSpiderLeg3.rotateAngleY = eighthCircle * 1.0F;
//			this.defaultSpiderLeg4.rotateAngleY = -eighthCircle * 1.0F;
//			this.defaultSpiderLeg5.rotateAngleY = -eighthCircle * 1.0F;
//			this.defaultSpiderLeg6.rotateAngleY = eighthCircle * 1.0F;
//			this.defaultSpiderLeg7.rotateAngleY = -eighthCircle * 2.0F;
//			this.defaultSpiderLeg8.rotateAngleY = eighthCircle * 2.0F;
//
//			final float frontRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F) * 0.4F) * prevLimbSwing;
//			final float frontMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * prevLimbSwing;
//			final float backMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
//			final float backRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;
//			final float frontRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F) * 0.4F) * prevLimbSwing;
//			final float frontMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * prevLimbSwing;
//			final float backMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
//			final float backRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;
//
//			this.defaultSpiderLeg1.rotateAngleY += frontRotateY;
//			this.defaultSpiderLeg2.rotateAngleY += -frontRotateY;
//			this.defaultSpiderLeg3.rotateAngleY += frontMidRotateY;
//			this.defaultSpiderLeg4.rotateAngleY += -frontMidRotateY;
//			this.defaultSpiderLeg5.rotateAngleY += backMidRotateY;
//			this.defaultSpiderLeg6.rotateAngleY += -backMidRotateY;
//			this.defaultSpiderLeg7.rotateAngleY += backRotateY;
//			this.defaultSpiderLeg8.rotateAngleY += -backRotateY;
//			this.defaultSpiderLeg1.rotateAngleZ += frontRotateZ;
//			this.defaultSpiderLeg2.rotateAngleZ += -frontRotateZ;
//			this.defaultSpiderLeg3.rotateAngleZ += frontMidRotateZ;
//			this.defaultSpiderLeg4.rotateAngleZ += -frontMidRotateZ;
//			this.defaultSpiderLeg5.rotateAngleZ += backMidRotateZ;
//			this.defaultSpiderLeg6.rotateAngleZ += -backMidRotateZ;
//			this.defaultSpiderLeg7.rotateAngleZ += backRotateZ;
//			this.defaultSpiderLeg8.rotateAngleZ += -backRotateZ;
//		}
//
//		else if (spider.cocoonType == EnumTypeVariant.CREEPER)
//		{
//			this.raisedSpiderHead.rotateAngleY = rotationYaw / 57.29578F;
//			this.raisedSpiderHead.rotateAngleX = rotationPitch / 57.29578F;
//			final float f6 = 0.7853982F;
//			this.raisedSpiderLeg1.rotateAngleZ = -f6;
//			this.raisedSpiderLeg2.rotateAngleZ = f6;
//			this.raisedSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
//			this.raisedSpiderLeg4.rotateAngleZ = f6 * 0.74F;
//			this.raisedSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
//			this.raisedSpiderLeg6.rotateAngleZ = f6 * 0.74F;
//			this.raisedSpiderLeg7.rotateAngleZ = -f6;
//			this.raisedSpiderLeg8.rotateAngleZ = f6;
//			final float f7 = -0F;
//			final float f8 = 0.3926991F;
//			this.raisedSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
//			this.raisedSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
//			this.raisedSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
//			this.raisedSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
//			this.raisedSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
//			this.raisedSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
//			this.raisedSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
//			this.raisedSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
//			final float f9 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLimbSwing;
//			final float f10 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * prevLimbSwing;
//			final float f11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * prevLimbSwing;
//			final float f12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * prevLimbSwing;
//			final float f13 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * prevLimbSwing;
//			final float f14 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * prevLimbSwing;
//			final float f15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * prevLimbSwing;
//			final float f16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * prevLimbSwing;
//			this.raisedSpiderLeg1.rotateAngleY += f9;
//			this.raisedSpiderLeg2.rotateAngleY += -f9;
//			this.raisedSpiderLeg3.rotateAngleY += f10;
//			this.raisedSpiderLeg4.rotateAngleY += -f10;
//			this.raisedSpiderLeg5.rotateAngleY += f11;
//			this.raisedSpiderLeg6.rotateAngleY += -f11;
//			this.raisedSpiderLeg7.rotateAngleY += f12;
//			this.raisedSpiderLeg8.rotateAngleY += -f12;
//			this.raisedSpiderLeg1.rotateAngleZ += f13;
//			this.raisedSpiderLeg2.rotateAngleZ += -f13;
//			this.raisedSpiderLeg3.rotateAngleZ += f14;
//			this.raisedSpiderLeg4.rotateAngleZ += -f14;
//			this.raisedSpiderLeg5.rotateAngleZ += f15;
//			this.raisedSpiderLeg6.rotateAngleZ += -f15;
//			this.raisedSpiderLeg7.rotateAngleZ += f16;
//			this.raisedSpiderLeg8.rotateAngleZ += -f16;
//		}
//
//		else if (spider.cocoonType == EnumTypeVariant.WOLF)
//		{
//			this.tinySpiderHead.rotateAngleY = rotationYaw / 57.29578F;
//			this.tinySpiderHead.rotateAngleX = rotationPitch / 57.29578F;
//			final float f6 = 0.7853982F;
//			this.tinySpiderLeg1.rotateAngleZ = -f6;
//			this.tinySpiderLeg2.rotateAngleZ = f6;
//			this.tinySpiderLeg3.rotateAngleZ = -f6 * 0.74F;
//			this.tinySpiderLeg4.rotateAngleZ = f6 * 0.74F;
//			this.tinySpiderLeg5.rotateAngleZ = -f6 * 0.74F;
//			this.tinySpiderLeg6.rotateAngleZ = f6 * 0.74F;
//			this.tinySpiderLeg7.rotateAngleZ = -f6;
//			this.tinySpiderLeg8.rotateAngleZ = f6;
//			final float f7 = -0F;
//			final float f8 = 0.3926991F;
//			this.tinySpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
//			this.tinySpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
//			this.tinySpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
//			this.tinySpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
//			this.tinySpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
//			this.tinySpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
//			this.tinySpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
//			this.tinySpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
//			final float f9 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLimbSwing;
//			final float f10 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * prevLimbSwing;
//			final float f11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * prevLimbSwing;
//			final float f12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * prevLimbSwing;
//			final float f13 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * prevLimbSwing;
//			final float f14 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * prevLimbSwing;
//			final float f15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * prevLimbSwing;
//			final float f16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * prevLimbSwing;
//			this.tinySpiderLeg1.rotateAngleY += f9;
//			this.tinySpiderLeg2.rotateAngleY += -f9;
//			this.tinySpiderLeg3.rotateAngleY += f10;
//			this.tinySpiderLeg4.rotateAngleY += -f10;
//			this.tinySpiderLeg5.rotateAngleY += f11;
//			this.tinySpiderLeg6.rotateAngleY += -f11;
//			this.tinySpiderLeg7.rotateAngleY += f12;
//			this.tinySpiderLeg8.rotateAngleY += -f12;
//			this.tinySpiderLeg1.rotateAngleZ += f13;
//			this.tinySpiderLeg2.rotateAngleZ += -f13;
//			this.tinySpiderLeg3.rotateAngleZ += f14;
//			this.tinySpiderLeg4.rotateAngleZ += -f14;
//			this.tinySpiderLeg5.rotateAngleZ += f15;
//			this.tinySpiderLeg6.rotateAngleZ += -f15;
//			this.tinySpiderLeg7.rotateAngleZ += f16;
//			this.tinySpiderLeg8.rotateAngleZ += -f16;
//		}
//
//		else if (spider.cocoonType == EnumTypeVariant.SKELETON)
//		{
//			this.thinSpiderHead.rotateAngleY = rotationYaw / 57.29578F;
//			this.thinSpiderHead.rotateAngleX = rotationPitch / 57.29578F;
//			final float f6 = 0.7853982F;
//			this.thinSpiderLeg1.rotateAngleZ = -f6;
//			this.thinSpiderLeg2.rotateAngleZ = f6;
//			this.thinSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
//			this.thinSpiderLeg4.rotateAngleZ = f6 * 0.74F;
//			this.thinSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
//			this.thinSpiderLeg6.rotateAngleZ = f6 * 0.74F;
//			this.thinSpiderLeg7.rotateAngleZ = -f6;
//			this.thinSpiderLeg8.rotateAngleZ = f6;
//			final float f7 = -0F;
//			final float f8 = 0.3926991F;
//			this.thinSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
//			this.thinSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
//			this.thinSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
//			this.thinSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
//			this.thinSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
//			this.thinSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
//			this.thinSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
//			this.thinSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
//			final float f9 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLimbSwing;
//			final float f10 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * prevLimbSwing;
//			final float f11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * prevLimbSwing;
//			final float f12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * prevLimbSwing;
//			final float f13 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * prevLimbSwing;
//			final float f14 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * prevLimbSwing;
//			final float f15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * prevLimbSwing;
//			final float f16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * prevLimbSwing;
//			this.thinSpiderLeg1.rotateAngleY += f9;
//			this.thinSpiderLeg2.rotateAngleY += -f9;
//			this.thinSpiderLeg3.rotateAngleY += f10;
//			this.thinSpiderLeg4.rotateAngleY += -f10;
//			this.thinSpiderLeg5.rotateAngleY += f11;
//			this.thinSpiderLeg6.rotateAngleY += -f11;
//			this.thinSpiderLeg7.rotateAngleY += f12;
//			this.thinSpiderLeg8.rotateAngleY += -f12;
//			this.thinSpiderLeg1.rotateAngleZ += f13;
//			this.thinSpiderLeg2.rotateAngleZ += -f13;
//			this.thinSpiderLeg3.rotateAngleZ += f14;
//			this.thinSpiderLeg4.rotateAngleZ += -f14;
//			this.thinSpiderLeg5.rotateAngleZ += f15;
//			this.thinSpiderLeg6.rotateAngleZ += -f15;
//			this.thinSpiderLeg7.rotateAngleZ += f16;
//			this.thinSpiderLeg8.rotateAngleZ += -f16;
//		}
//
//		else if (spider.cocoonType == EnumTypeVariant.ZOMBIE)
//		{
//			this.hugeSpiderHead.rotateAngleY = rotationYaw / 57.29578F;
//			this.hugeSpiderHead.rotateAngleX = rotationPitch / 57.29578F;
//			final float f6 = 0.7853982F;
//			this.hugeSpiderLeg1.rotateAngleZ = -f6;
//			this.hugeSpiderLeg2.rotateAngleZ = f6;
//			this.hugeSpiderLeg3.rotateAngleZ = -f6 * 0.74F;
//			this.hugeSpiderLeg4.rotateAngleZ = f6 * 0.74F;
//			this.hugeSpiderLeg5.rotateAngleZ = -f6 * 0.74F;
//			this.hugeSpiderLeg6.rotateAngleZ = f6 * 0.74F;
//			this.hugeSpiderLeg7.rotateAngleZ = -f6;
//			this.hugeSpiderLeg8.rotateAngleZ = f6;
//			final float f7 = -0F;
//			final float f8 = 0.3926991F;
//			this.hugeSpiderLeg1.rotateAngleY = f8 * 2.0F + f7;
//			this.hugeSpiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
//			this.hugeSpiderLeg3.rotateAngleY = f8 * 1.0F + f7;
//			this.hugeSpiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
//			this.hugeSpiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
//			this.hugeSpiderLeg6.rotateAngleY = f8 * 1.0F - f7;
//			this.hugeSpiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
//			this.hugeSpiderLeg8.rotateAngleY = f8 * 2.0F - f7;
//			final float f9 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * prevLimbSwing;
//			final float f10 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.141593F) * 0.4F) * prevLimbSwing;
//			final float f11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 1.570796F) * 0.4F) * prevLimbSwing;
//			final float f12 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 4.712389F) * 0.4F) * prevLimbSwing;
//			final float f13 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * prevLimbSwing;
//			final float f14 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.141593F) * 0.4F) * prevLimbSwing;
//			final float f15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 1.570796F) * 0.4F) * prevLimbSwing;
//			final float f16 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 4.712389F) * 0.4F) * prevLimbSwing;
//			this.hugeSpiderLeg1.rotateAngleY += f9;
//			this.hugeSpiderLeg2.rotateAngleY += -f9;
//			this.hugeSpiderLeg3.rotateAngleY += f10;
//			this.hugeSpiderLeg4.rotateAngleY += -f10;
//			this.hugeSpiderLeg5.rotateAngleY += f11;
//			this.hugeSpiderLeg6.rotateAngleY += -f11;
//			this.hugeSpiderLeg7.rotateAngleY += f12;
//			this.hugeSpiderLeg8.rotateAngleY += -f12;
//			this.hugeSpiderLeg1.rotateAngleZ += f13;
//			this.hugeSpiderLeg2.rotateAngleZ += -f13;
//			this.hugeSpiderLeg3.rotateAngleZ += f14;
//			this.hugeSpiderLeg4.rotateAngleZ += -f14;
//			this.hugeSpiderLeg5.rotateAngleZ += f15;
//			this.hugeSpiderLeg6.rotateAngleZ += -f15;
//			this.hugeSpiderLeg7.rotateAngleZ += f16;
//			this.hugeSpiderLeg8.rotateAngleZ += -f16;
//		}
//
//		else if (spider.cocoonType == EnumTypeVariant.ENDERMAN || spider.cocoonType == EnumTypeVariant._ENDERMINION)
//		{
//			final float quarterCircle = (float) Math.PI / 4F;
//			this.longLegSpiderLeg1.rotateAngleZ = -quarterCircle;
//			this.longLegSpiderLeg2.rotateAngleZ = quarterCircle;
//			this.longLegSpiderLeg3.rotateAngleZ = -quarterCircle * 0.74F;
//			this.longLegSpiderLeg4.rotateAngleZ = quarterCircle * 0.74F;
//			this.longLegSpiderLeg5.rotateAngleZ = -quarterCircle * 0.74F;
//			this.longLegSpiderLeg6.rotateAngleZ = quarterCircle * 0.74F;
//			this.longLegSpiderLeg7.rotateAngleZ = -quarterCircle;
//			this.longLegSpiderLeg8.rotateAngleZ = quarterCircle;
//
//			final float eighthCircle = (float) Math.PI / 8F;
//			this.longLegSpiderLeg1.rotateAngleY = eighthCircle * 2.0F;
//			this.longLegSpiderLeg2.rotateAngleY = -eighthCircle * 2.0F;
//			this.longLegSpiderLeg3.rotateAngleY = eighthCircle * 1.0F;
//			this.longLegSpiderLeg4.rotateAngleY = -eighthCircle * 1.0F;
//			this.longLegSpiderLeg5.rotateAngleY = -eighthCircle * 1.0F;
//			this.longLegSpiderLeg6.rotateAngleY = eighthCircle * 1.0F;
//			this.longLegSpiderLeg7.rotateAngleY = -eighthCircle * 2.0F;
//			this.longLegSpiderLeg8.rotateAngleY = eighthCircle * 2.0F;
//
//			final float frontRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F) * 0.4F) * prevLimbSwing;
//			final float frontMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * prevLimbSwing;
//			final float backMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
//			final float backRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;
//			final float frontRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F) * 0.4F) * prevLimbSwing;
//			final float frontMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * prevLimbSwing;
//			final float backMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
//			final float backRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;
//
//			this.longLegSpiderLeg1.rotateAngleY += frontRotateY;
//			this.longLegSpiderLeg2.rotateAngleY += -frontRotateY;
//			this.longLegSpiderLeg3.rotateAngleY += frontMidRotateY;
//			this.longLegSpiderLeg4.rotateAngleY += -frontMidRotateY;
//			this.longLegSpiderLeg5.rotateAngleY += backMidRotateY;
//			this.longLegSpiderLeg6.rotateAngleY += -backMidRotateY;
//			this.longLegSpiderLeg7.rotateAngleY += backRotateY;
//			this.longLegSpiderLeg8.rotateAngleY += -backRotateY;
//			this.longLegSpiderLeg1.rotateAngleZ += frontRotateZ;
//			this.longLegSpiderLeg2.rotateAngleZ += -frontRotateZ;
//			this.longLegSpiderLeg3.rotateAngleZ += frontMidRotateZ;
//			this.longLegSpiderLeg4.rotateAngleZ += -frontMidRotateZ;
//			this.longLegSpiderLeg5.rotateAngleZ += backMidRotateZ;
//			this.longLegSpiderLeg6.rotateAngleZ += -backMidRotateZ;
//			this.longLegSpiderLeg7.rotateAngleZ += backRotateZ;
//			this.longLegSpiderLeg8.rotateAngleZ += -backRotateZ;
//		}
//
//		else if (spider.cocoonType == EnumTypeVariant.BLAZE)
//		{
//			this.flatSpiderHead.rotateAngleY = rotationYaw / (180F / (float) Math.PI);
//			this.flatSpiderHead.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
//
//			final float quarterCircle = (float) Math.PI / 4F;
//			this.flatSpiderLeg1.rotateAngleZ = -quarterCircle;
//			this.flatSpiderLeg2.rotateAngleZ = quarterCircle;
//			this.flatSpiderLeg3.rotateAngleZ = -quarterCircle * 0.74F;
//			this.flatSpiderLeg4.rotateAngleZ = quarterCircle * 0.74F;
//			this.flatSpiderLeg5.rotateAngleZ = -quarterCircle * 0.74F;
//			this.flatSpiderLeg6.rotateAngleZ = quarterCircle * 0.74F;
//			this.flatSpiderLeg7.rotateAngleZ = -quarterCircle;
//			this.flatSpiderLeg8.rotateAngleZ = quarterCircle;
//
//			final float eighthCircle = (float) Math.PI / 8F;
//			this.flatSpiderLeg1.rotateAngleY = eighthCircle * 2.0F;
//			this.flatSpiderLeg2.rotateAngleY = -eighthCircle * 2.0F;
//			this.flatSpiderLeg3.rotateAngleY = eighthCircle * 1.0F;
//			this.flatSpiderLeg4.rotateAngleY = -eighthCircle * 1.0F;
//			this.flatSpiderLeg5.rotateAngleY = -eighthCircle * 1.0F;
//			this.flatSpiderLeg6.rotateAngleY = eighthCircle * 1.0F;
//			this.flatSpiderLeg7.rotateAngleY = -eighthCircle * 2.0F;
//			this.flatSpiderLeg8.rotateAngleY = eighthCircle * 2.0F;
//
//			final float frontRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F) * 0.4F) * prevLimbSwing;
//			final float frontMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * prevLimbSwing;
//			final float backMidRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
//			final float backRotateY = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;
//			final float frontRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F) * 0.4F) * prevLimbSwing;
//			final float frontMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * prevLimbSwing;
//			final float backMidRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI / 2F) * 0.4F) * prevLimbSwing;
//			final float backRotateZ = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI * 3F / 2F) * 0.4F) * prevLimbSwing;
//
//			this.flatSpiderLeg1.rotateAngleY += frontRotateY;
//			this.flatSpiderLeg2.rotateAngleY += -frontRotateY;
//			this.flatSpiderLeg3.rotateAngleY += frontMidRotateY;
//			this.flatSpiderLeg4.rotateAngleY += -frontMidRotateY;
//			this.flatSpiderLeg5.rotateAngleY += backMidRotateY;
//			this.flatSpiderLeg6.rotateAngleY += -backMidRotateY;
//			this.flatSpiderLeg7.rotateAngleY += backRotateY;
//			this.flatSpiderLeg8.rotateAngleY += -backRotateY;
//			this.flatSpiderLeg1.rotateAngleZ += frontRotateZ;
//			this.flatSpiderLeg2.rotateAngleZ += -frontRotateZ;
//			this.flatSpiderLeg3.rotateAngleZ += frontMidRotateZ;
//			this.flatSpiderLeg4.rotateAngleZ += -frontMidRotateZ;
//			this.flatSpiderLeg5.rotateAngleZ += backMidRotateZ;
//			this.flatSpiderLeg6.rotateAngleZ += -backMidRotateZ;
//			this.flatSpiderLeg7.rotateAngleZ += backRotateZ;
//			this.flatSpiderLeg8.rotateAngleZ += -backRotateZ;
//		}
	}

	private void initHugeSpider()
	{
		this.hugeSpiderHead = new ModelRenderer(this, 32, 4);
		this.hugeSpiderHead.addBox(-4F, -4F, -8F, 8, 8, 8, 0.0F);
		this.hugeSpiderHead.setRotationPoint(0F, 15F, -3F);
		this.hugeSpiderHead.rotateAngleX = 0.18081F;
		this.hugeSpiderHead.rotateAngleZ = -0.27122F;

		this.hugeSpiderBody = new ModelRenderer(this, 0, 0);
		this.hugeSpiderBody.addBox(-3F, -3F, -3F, 6, 6, 6, 0.0F);
		this.hugeSpiderBody.setRotationPoint(0F, 15F, 0F);

		this.hugeSpiderRearEnd = new ModelRenderer(this, 0, 12);
		this.hugeSpiderRearEnd.addBox(-5F, -4F, -6F, 12, 9, 12, 0.0F);
		this.hugeSpiderRearEnd.setRotationPoint(-1F, 14F, 9F);

		this.hugeSpiderLeg1 = new ModelRenderer(this, 18, 0);
		this.hugeSpiderLeg1.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.hugeSpiderLeg1.setRotationPoint(-4F, 15F, 2F);
		this.hugeSpiderLeg1.rotateAngleX = 0.57596F;
		this.hugeSpiderLeg1.rotateAngleY = 0.19199F;

		this.hugeSpiderLeg2 = new ModelRenderer(this, 18, 0);
		this.hugeSpiderLeg2.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.hugeSpiderLeg2.setRotationPoint(4F, 15F, 2F);
		this.hugeSpiderLeg2.rotateAngleX = -0.57596F;
		this.hugeSpiderLeg2.rotateAngleY = -0.19199F;

		this.hugeSpiderLeg3 = new ModelRenderer(this, 18, 0);
		this.hugeSpiderLeg3.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.hugeSpiderLeg3.setRotationPoint(-4F, 15F, 1F);
		this.hugeSpiderLeg3.rotateAngleX = 0.27925F;
		this.hugeSpiderLeg3.rotateAngleY = 0.19199F;

		this.hugeSpiderLeg4 = new ModelRenderer(this, 18, 0);
		this.hugeSpiderLeg4.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.hugeSpiderLeg4.setRotationPoint(4F, 15F, 1F);
		this.hugeSpiderLeg4.rotateAngleX = -0.27925F;
		this.hugeSpiderLeg4.rotateAngleY = -0.19199F;

		this.hugeSpiderLeg5 = new ModelRenderer(this, 18, 0);
		this.hugeSpiderLeg5.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.hugeSpiderLeg5.setRotationPoint(-4F, 15F, 0F);
		this.hugeSpiderLeg5.rotateAngleX = -0.27925F;
		this.hugeSpiderLeg5.rotateAngleY = 0.19199F;

		this.hugeSpiderLeg6 = new ModelRenderer(this, 18, 0);
		this.hugeSpiderLeg6.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.hugeSpiderLeg6.setRotationPoint(4F, 15F, 0F);
		this.hugeSpiderLeg6.rotateAngleX = 0.27925F;
		this.hugeSpiderLeg6.rotateAngleY = -0.19199F;

		this.hugeSpiderLeg7 = new ModelRenderer(this, 18, 0);
		this.hugeSpiderLeg7.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.hugeSpiderLeg7.setRotationPoint(-4F, 15F, -1F);
		this.hugeSpiderLeg7.rotateAngleX = -0.57596F;
		this.hugeSpiderLeg7.rotateAngleY = 0.19199F;

		this.hugeSpiderLeg8 = new ModelRenderer(this, 18, 0);
		this.hugeSpiderLeg8.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.hugeSpiderLeg8.setRotationPoint(4F, 15F, -1F);
		this.hugeSpiderLeg8.rotateAngleX = 0.57596F;
		this.hugeSpiderLeg8.rotateAngleY = -0.19199F;

		this.hugeSpiderTopBulk = new ModelRenderer(this, 0, 12);
		this.hugeSpiderTopBulk.addBox(0F, 0F, 0F, 8, 2, 9, 0.0F);
		this.hugeSpiderTopBulk.setRotationPoint(-4F, 8F, 4F);

		this.hugeSpiderBackBulk = new ModelRenderer(this, 0, 12);
		this.hugeSpiderBackBulk.addBox(-4F, -2F, 0F, 7, 6, 2, 0.0F);
		this.hugeSpiderBackBulk.setRotationPoint(0F, 14F, 15F);

		this.hugeSpiderLeftBulk = new ModelRenderer(this, 0, 12);
		this.hugeSpiderLeftBulk.addBox(0F, -2F, -3F, 2, 4, 9, 0.0F);
		this.hugeSpiderLeftBulk.setRotationPoint(6F, 15F, 7F);

		this.hugeSpiderRightBulk = new ModelRenderer(this, 0, 12);
		this.hugeSpiderRightBulk.addBox(-2F, -2F, -3F, 2, 4, 10, 0.0F);
		this.hugeSpiderRightBulk.setRotationPoint(-6F, 15F, 7F);
	}

	private void initRaisedSpider()
	{
		this.raisedSpiderHead = new ModelRenderer(this, 32, 4);
		this.raisedSpiderHead.addBox(-4F, -4F, -8F, 8, 8, 8, 0.0F);
		this.raisedSpiderHead.setRotationPoint(0F, 15F, -3F);

		this.raisedSpiderBody = new ModelRenderer(this, 0, 0);
		this.raisedSpiderBody.addBox(-3F, -3F, -3F, 6, 6, 6, 0.0F);
		this.raisedSpiderBody.setRotationPoint(0F, 15F, 0F);

		this.raisedSpiderRearEnd = new ModelRenderer(this, 0, 12);
		this.raisedSpiderRearEnd.addBox(-5F, -4F, -6F, 10, 8, 12, 0.0F);
		this.raisedSpiderRearEnd.setRotationPoint(0F, 11F, 7F);
		this.raisedSpiderRearEnd.rotateAngleX = 0.63284F;

		this.raisedSpiderLeg1 = new ModelRenderer(this, 18, 0);
		this.raisedSpiderLeg1.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.raisedSpiderLeg1.setRotationPoint(-4F, 15F, 2F);
		this.raisedSpiderLeg1.rotateAngleX = 0.57596F;
		this.raisedSpiderLeg1.rotateAngleY = 0.19199F;

		this.raisedSpiderLeg2 = new ModelRenderer(this, 18, 0);
		this.raisedSpiderLeg2.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.raisedSpiderLeg2.setRotationPoint(4F, 15F, 2F);
		this.raisedSpiderLeg2.rotateAngleX = -0.57596F;
		this.raisedSpiderLeg2.rotateAngleY = -0.19199F;

		this.raisedSpiderLeg3 = new ModelRenderer(this, 18, 0);
		this.raisedSpiderLeg3.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.raisedSpiderLeg3.setRotationPoint(-4F, 15F, 1F);
		this.raisedSpiderLeg3.rotateAngleX = 0.27925F;
		this.raisedSpiderLeg3.rotateAngleY = 0.19199F;

		this.raisedSpiderLeg4 = new ModelRenderer(this, 18, 0);
		this.raisedSpiderLeg4.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.raisedSpiderLeg4.setRotationPoint(4F, 15F, 1F);
		this.raisedSpiderLeg4.rotateAngleX = -0.27925F;
		this.raisedSpiderLeg4.rotateAngleY = -0.19199F;

		this.raisedSpiderLeg5 = new ModelRenderer(this, 18, 0);
		this.raisedSpiderLeg5.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.raisedSpiderLeg5.setRotationPoint(-4F, 15F, 0F);
		this.raisedSpiderLeg5.rotateAngleX = -0.27925F;
		this.raisedSpiderLeg5.rotateAngleY = 0.19199F;

		this.raisedSpiderLeg6 = new ModelRenderer(this, 18, 0);
		this.raisedSpiderLeg6.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.raisedSpiderLeg6.setRotationPoint(4F, 15F, 0F);
		this.raisedSpiderLeg6.rotateAngleX = 0.27925F;
		this.raisedSpiderLeg6.rotateAngleY = -0.19199F;

		this.raisedSpiderLeg7 = new ModelRenderer(this, 18, 0);
		this.raisedSpiderLeg7.addBox(-15F, -1F, -1F, 16, 2, 2, 0.0F);
		this.raisedSpiderLeg7.setRotationPoint(-4F, 15F, -1F);
		this.raisedSpiderLeg7.rotateAngleX = -0.57596F;
		this.raisedSpiderLeg7.rotateAngleY = 0.19199F;

		this.raisedSpiderLeg8 = new ModelRenderer(this, 18, 0);
		this.raisedSpiderLeg8.addBox(-1F, -1F, -1F, 16, 2, 2, 0.0F);
		this.raisedSpiderLeg8.setRotationPoint(4F, 15F, -1F);
		this.raisedSpiderLeg8.rotateAngleX = 0.57596F;
		this.raisedSpiderLeg8.rotateAngleY = -0.19199F;
	}

	private void initDefaultSpider()
	{
		this.defaultSpiderHead = new ModelRenderer(this, 32, 4);
		this.defaultSpiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
		this.defaultSpiderHead.setRotationPoint(0.0F, 15, -3.0F);

		this.defaultSpiderNeck = new ModelRenderer(this, 0, 0);
		this.defaultSpiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
		this.defaultSpiderNeck.setRotationPoint(0.0F, 15, 0.0F);

		this.defaultSpiderBody = new ModelRenderer(this, 0, 12);
		this.defaultSpiderBody.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F);
		this.defaultSpiderBody.setRotationPoint(0.0F, 15, 9.0F);

		this.defaultSpiderLeg1 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg1.setRotationPoint(-4.0F, 15, 2.0F);

		this.defaultSpiderLeg2 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg2.setRotationPoint(4.0F, 15, 2.0F);

		this.defaultSpiderLeg3 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg3.setRotationPoint(-4.0F, 15, 1.0F);

		this.defaultSpiderLeg4 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg4.setRotationPoint(4.0F, 15, 1.0F);

		this.defaultSpiderLeg5 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg5.setRotationPoint(-4.0F, 15, 0.0F);

		this.defaultSpiderLeg6 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg6.setRotationPoint(4.0F, 15, 0.0F);

		this.defaultSpiderLeg7 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg7.setRotationPoint(-4.0F, 15, -1.0F);

		this.defaultSpiderLeg8 = new ModelRenderer(this, 18, 0);
		this.defaultSpiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.defaultSpiderLeg8.setRotationPoint(4.0F, 15, -1.0F);
	}

	private void initThinSpider()
	{
		this.thinSpiderHead = new ModelRenderer(this, 32, 4);
		this.thinSpiderHead.addBox(-4F, -6F, -8F, 8, 8, 8, 0.0F);
		this.thinSpiderHead.setRotationPoint(0F, 14F, -3F);

		this.thinSpiderBody = new ModelRenderer(this, 0, 0);
		this.thinSpiderBody.addBox(-3F, -3F, -3F, 6, 4, 6, 0.0F);
		this.thinSpiderBody.setRotationPoint(0F, 16F, 0F);

		this.thinSpiderRearEnd = new ModelRenderer(this, 0, 12);
		this.thinSpiderRearEnd.addBox(-5F, -4F, -6F, 8, 6, 10, 0.0F);
		this.thinSpiderRearEnd.setRotationPoint(1F, 13F, 8F);
		this.thinSpiderRearEnd.rotateAngleX = 0.32023F;

		this.thinSpiderLeg1 = new ModelRenderer(this, 18, 0);
		this.thinSpiderLeg1.addBox(-15F, -1F, -1F, 16, 1, 1, 0.0F);
		this.thinSpiderLeg1.setRotationPoint(-4F, 15F, 2F);
		this.thinSpiderLeg1.rotateAngleX = 0.57596F;
		this.thinSpiderLeg1.rotateAngleY = 0.19199F;

		this.thinSpiderLeg2 = new ModelRenderer(this, 18, 0);
		this.thinSpiderLeg2.addBox(-1F, -1F, -1F, 16, 1, 1, 0.0F);
		this.thinSpiderLeg2.setRotationPoint(4F, 15F, 2F);
		this.thinSpiderLeg2.rotateAngleX = -0.57596F;
		this.thinSpiderLeg2.rotateAngleY = -0.19199F;

		this.thinSpiderLeg3 = new ModelRenderer(this, 18, 0);
		this.thinSpiderLeg3.addBox(-15F, -1F, -1F, 16, 1, 1, 0.0F);
		this.thinSpiderLeg3.setRotationPoint(-4F, 15F, 1F);
		this.thinSpiderLeg3.rotateAngleX = 0.27925F;
		this.thinSpiderLeg3.rotateAngleY = 0.19199F;

		this.thinSpiderLeg4 = new ModelRenderer(this, 18, 0);
		this.thinSpiderLeg4.addBox(-1F, -1F, -1F, 16, 1, 1, 0.0F);
		this.thinSpiderLeg4.setRotationPoint(4F, 15F, 1F);
		this.thinSpiderLeg4.rotateAngleX = -0.27925F;
		this.thinSpiderLeg4.rotateAngleY = -0.19199F;

		this.thinSpiderLeg5 = new ModelRenderer(this, 18, 0);
		this.thinSpiderLeg5.addBox(-15F, -1F, -1F, 16, 1, 1, 0.0F);
		this.thinSpiderLeg5.setRotationPoint(-4F, 15F, 0F);
		this.thinSpiderLeg5.rotateAngleX = -0.27925F;
		this.thinSpiderLeg5.rotateAngleY = 0.19199F;

		this.thinSpiderLeg6 = new ModelRenderer(this, 18, 0);
		this.thinSpiderLeg6.addBox(-1F, -1F, -1F, 16, 1, 1, 0.0F);
		this.thinSpiderLeg6.setRotationPoint(4F, 15F, 0F);
		this.thinSpiderLeg6.rotateAngleX = 0.27925F;
		this.thinSpiderLeg6.rotateAngleY = -0.19199F;

		this.thinSpiderLeg7 = new ModelRenderer(this, 18, 0);
		this.thinSpiderLeg7.addBox(-15F, -1F, -1F, 16, 1, 1, 0.0F);
		this.thinSpiderLeg7.setRotationPoint(-4F, 15F, -1F);
		this.thinSpiderLeg7.rotateAngleX = -0.57596F;
		this.thinSpiderLeg7.rotateAngleY = 0.19199F;

		this.thinSpiderLeg8 = new ModelRenderer(this, 18, 0);
		this.thinSpiderLeg8.addBox(-1F, -1F, -1F, 16, 1, 1, 0.0F);
		this.thinSpiderLeg8.setRotationPoint(4F, 15F, -1F);
		this.thinSpiderLeg8.rotateAngleX = 0.57596F;
		this.thinSpiderLeg8.rotateAngleY = -0.19199F;
	}

	private void initTinySpider()
	{
		this.tinySpiderHead = new ModelRenderer(this, 0, 8);
		this.tinySpiderHead.addBox(-3F, -2F, -4F, 4, 3, 4, 0.0F);
		this.tinySpiderHead.setRotationPoint(0F, 19F, -3F);

		this.tinySpiderBody = new ModelRenderer(this, 0, 0);
		this.tinySpiderBody.addBox(-3F, -3F, -3F, 4, 4, 4, 0.0F);
		this.tinySpiderBody.setRotationPoint(0F, 19F, 0F);

		this.tinySpiderRearEnd = new ModelRenderer(this, 0, 15);
		this.tinySpiderRearEnd.addBox(-3F, -4F, 0F, 6, 6, 6, 0.0F);
		this.tinySpiderRearEnd.setRotationPoint(-1F, 18F, 1F);

		this.tinySpiderLeg1 = new ModelRenderer(this, 18, 0);
		this.tinySpiderLeg1.addBox(-9F, -1F, -1F, 10, 1, 1, 0.0F);
		this.tinySpiderLeg1.setRotationPoint(-3F, 19F, 1F);
		this.tinySpiderLeg1.rotateAngleX = 0.57596F;
		this.tinySpiderLeg1.rotateAngleY = 0.19199F;

		this.tinySpiderLeg2 = new ModelRenderer(this, 18, 0);
		this.tinySpiderLeg2.addBox(-1F, -1F, -1F, 10, 1, 1, 0.0F);
		this.tinySpiderLeg2.setRotationPoint(1F, 19F, 1F);
		this.tinySpiderLeg2.rotateAngleX = -0.57596F;
		this.tinySpiderLeg2.rotateAngleY = -0.19199F;

		this.tinySpiderLeg3 = new ModelRenderer(this, 18, 0);
		this.tinySpiderLeg3.addBox(-9F, -1F, -1F, 10, 1, 1, 0.0F);
		this.tinySpiderLeg3.setRotationPoint(-3F, 19F, 0F);
		this.tinySpiderLeg3.rotateAngleX = 0.27925F;
		this.tinySpiderLeg3.rotateAngleY = 0.19199F;

		this.tinySpiderLeg4 = new ModelRenderer(this, 18, 0);
		this.tinySpiderLeg4.addBox(-1F, -1F, -1F, 10, 1, 1, 0.0F);
		this.tinySpiderLeg4.setRotationPoint(1F, 19F, 0F);
		this.tinySpiderLeg4.rotateAngleX = -0.27925F;
		this.tinySpiderLeg4.rotateAngleY = -0.19199F;

		this.tinySpiderLeg5 = new ModelRenderer(this, 18, 0);
		this.tinySpiderLeg5.addBox(-9F, -1F, -1F, 10, 1, 1, 0.0F);
		this.tinySpiderLeg5.setRotationPoint(-3F, 19F, -1F);
		this.tinySpiderLeg5.rotateAngleX = -0.27925F;
		this.tinySpiderLeg5.rotateAngleY = 0.19199F;

		this.tinySpiderLeg6 = new ModelRenderer(this, 18, 0);
		this.tinySpiderLeg6.addBox(-1F, -1F, -1F, 10, 1, 1, 0.0F);
		this.tinySpiderLeg6.setRotationPoint(1F, 19F, -1F);
		this.tinySpiderLeg6.rotateAngleX = 0.27925F;
		this.tinySpiderLeg6.rotateAngleY = -0.19199F;

		this.tinySpiderLeg7 = new ModelRenderer(this, 18, 0);
		this.tinySpiderLeg7.addBox(-10F, -1F, -1F, 10, 1, 1, 0.0F);
		this.tinySpiderLeg7.setRotationPoint(-2F, 19F, -2F);
		this.tinySpiderLeg7.rotateAngleX = -0.57596F;
		this.tinySpiderLeg7.rotateAngleY = 0.19199F;

		this.tinySpiderLeg8 = new ModelRenderer(this, 18, 0);
		this.tinySpiderLeg8.addBox(-1F, -1F, -1F, 10, 1, 1, 0.0F);
		this.tinySpiderLeg8.setRotationPoint(1F, 19F, -2F);
		this.tinySpiderLeg8.rotateAngleX = 0.57596F;
		this.tinySpiderLeg8.rotateAngleY = -0.19199F;
	}

	private void initLongLegSpider()
	{
		this.longLegSpiderBody = new ModelRenderer(this, 0, 0);
		this.longLegSpiderBody.addBox(-3F, -3F, -3F, 6, 6, 6);
		this.longLegSpiderBody.setRotationPoint(0F, 5F, 0F);

		this.longLegSpiderLeg8 = new ModelRenderer(this, 18, 0);
		this.longLegSpiderLeg8.addBox(-1F, -1F, -1F, 17, 1, 1);
		this.longLegSpiderLeg8.setRotationPoint(2.5F, 9F, -1F);
		this.longLegSpiderLeg8.setTextureSize(64, 32);
		this.longLegSpiderLeg8.mirror = true;
		this.setRotation(this.longLegSpiderLeg8, 0.2974289F, 0.5759587F, 1.230457F);
		this.longLegSpiderLeg6 = new ModelRenderer(this, 18, 0);
		this.longLegSpiderLeg6.addBox(-1F, -1F, -1F, 17, 1, 1);
		this.longLegSpiderLeg6.setRotationPoint(2.5F, 9F, 0F);
		this.longLegSpiderLeg6.setTextureSize(64, 32);
		this.longLegSpiderLeg6.mirror = true;
		this.setRotation(this.longLegSpiderLeg6, 0.5948578F, 0.2792527F, 1.22173F);
		this.longLegSpiderLeg4 = new ModelRenderer(this, 18, 0);
		this.longLegSpiderLeg4.addBox(-1F, -1F, -1F, 17, 1, 1);
		this.longLegSpiderLeg4.setRotationPoint(2F, 9F, 1F);
		this.longLegSpiderLeg4.setTextureSize(64, 32);
		this.longLegSpiderLeg4.mirror = true;
		this.setRotation(this.longLegSpiderLeg4, 0F, -0.2792527F, 1.22173F);
		this.longLegSpiderLeg2 = new ModelRenderer(this, 18, 0);
		this.longLegSpiderLeg2.addBox(-1F, -1F, -1F, 17, 1, 1);
		this.longLegSpiderLeg2.setRotationPoint(2F, 9F, 2F);
		this.longLegSpiderLeg2.setTextureSize(64, 32);
		this.longLegSpiderLeg2.mirror = true;
		this.setRotation(this.longLegSpiderLeg2, 0.2974289F, -0.5759587F, 1.230457F);
		this.longLegSpiderLeg7 = new ModelRenderer(this, 18, 0);
		this.longLegSpiderLeg7.addBox(-15F, -1F, -1F, 17, 1, 1);
		this.longLegSpiderLeg7.setRotationPoint(-2.5F, 10F, -1F);
		this.longLegSpiderLeg7.setTextureSize(64, 32);
		this.longLegSpiderLeg7.mirror = true;
		this.setRotation(this.longLegSpiderLeg7, 0F, -0.5759587F, -1.230457F);
		this.longLegSpiderLeg5 = new ModelRenderer(this, 18, 0);
		this.longLegSpiderLeg5.addBox(-15F, -1F, -1F, 17, 1, 1);
		this.longLegSpiderLeg5.setRotationPoint(-2.5F, 10F, 0F);
		this.longLegSpiderLeg5.setTextureSize(64, 32);
		this.longLegSpiderLeg5.mirror = true;
		this.setRotation(this.longLegSpiderLeg5, 0F, -0.2792527F, -1.22173F);
		this.longLegSpiderLeg3 = new ModelRenderer(this, 18, 0);
		this.longLegSpiderLeg3.addBox(-15F, -1F, -1F, 17, 1, 1);
		this.longLegSpiderLeg3.setRotationPoint(-2.5F, 10F, 1F);
		this.longLegSpiderLeg3.setTextureSize(64, 32);
		this.longLegSpiderLeg3.mirror = true;
		this.setRotation(this.longLegSpiderLeg3, 0F, 0.2792527F, -1.22173F);
		this.longLegSpiderLeg1 = new ModelRenderer(this, 18, 0);
		this.longLegSpiderLeg1.addBox(-15F, -1F, -1F, 18, 1, 1);
		this.longLegSpiderLeg1.setRotationPoint(-2.5F, 10F, 2F);
		this.longLegSpiderLeg1.setTextureSize(64, 32);
		this.longLegSpiderLeg1.mirror = true;
		this.setRotation(this.longLegSpiderLeg1, 0.3665191F, 0.5759587F, -1.230457F);
	}

	private void initFlatSpider()
	{
		this.flatSpiderHead = new ModelRenderer(this, 40, 12);
		this.flatSpiderHead.addBox(-4F, -4F, -8F, 6, 6, 6);
		this.flatSpiderHead.setRotationPoint(1F, 20F, -1F);
		this.flatSpiderHead.setTextureSize(64, 32);
		this.flatSpiderHead.mirror = true;
		this.setRotation(this.flatSpiderHead, 0F, 0F, 0F);
		this.flatSpiderBody = new ModelRenderer(this, 0, 0);
		this.flatSpiderBody.addBox(-3F, -3F, -3F, 6, 4, 5);
		this.flatSpiderBody.setRotationPoint(0F, 20F, 0F);
		this.flatSpiderBody.setTextureSize(64, 32);
		this.flatSpiderBody.mirror = true;
		this.setRotation(this.flatSpiderBody, 0F, 0F, 0F);
		this.flatSpiderRearEnd = new ModelRenderer(this, 0, 12);
		this.flatSpiderRearEnd.addBox(-5F, -4F, -6F, 12, 6, 13);
		this.flatSpiderRearEnd.setRotationPoint(-1F, 21F, 7F);
		this.flatSpiderRearEnd.setTextureSize(64, 32);
		this.flatSpiderRearEnd.mirror = true;
		this.setRotation(this.flatSpiderRearEnd, -0.1487144F, 0F, 0F);
		this.flatSpiderLeg8 = new ModelRenderer(this, 18, 0);
		this.flatSpiderLeg8.addBox(-1F, -1F, -1F, 16, 2, 2);
		this.flatSpiderLeg8.setRotationPoint(4F, 20F, -3F);
		this.flatSpiderLeg8.setTextureSize(64, 32);
		this.flatSpiderLeg8.mirror = true;
		this.setRotation(this.flatSpiderLeg8, 0F, 0.5759587F, 0.1919862F);
		this.flatSpiderLeg6 = new ModelRenderer(this, 18, 0);
		this.flatSpiderLeg6.addBox(-1F, -1F, -1F, 16, 2, 2);
		this.flatSpiderLeg6.setRotationPoint(4F, 20F, -2F);
		this.flatSpiderLeg6.setTextureSize(64, 32);
		this.flatSpiderLeg6.mirror = true;
		this.setRotation(this.flatSpiderLeg6, 0F, 0.2792527F, 0.1919862F);
		this.flatSpiderLeg4 = new ModelRenderer(this, 18, 0);
		this.flatSpiderLeg4.addBox(-1F, -1F, -1F, 16, 2, 2);
		this.flatSpiderLeg4.setRotationPoint(4F, 20F, -1F);
		this.flatSpiderLeg4.setTextureSize(64, 32);
		this.flatSpiderLeg4.mirror = true;
		this.setRotation(this.flatSpiderLeg4, 0F, -0.2792527F, 0.1919862F);
		this.flatSpiderLeg2 = new ModelRenderer(this, 18, 0);
		this.flatSpiderLeg2.addBox(-1F, -1F, -1F, 16, 2, 2);
		this.flatSpiderLeg2.setRotationPoint(4F, 20F, 0F);
		this.flatSpiderLeg2.setTextureSize(64, 32);
		this.flatSpiderLeg2.mirror = true;
		this.setRotation(this.flatSpiderLeg2, 0F, -0.5759587F, 0.1919862F);
		this.flatSpiderLeg7 = new ModelRenderer(this, 18, 0);
		this.flatSpiderLeg7.addBox(-15F, -1F, -1F, 16, 2, 2);
		this.flatSpiderLeg7.setRotationPoint(-4F, 20F, -3F);
		this.flatSpiderLeg7.setTextureSize(64, 32);
		this.flatSpiderLeg7.mirror = true;
		this.setRotation(this.flatSpiderLeg7, 0F, -0.5759587F, -0.1919862F);
		this.flatSpiderLeg5 = new ModelRenderer(this, 18, 0);
		this.flatSpiderLeg5.addBox(-15F, -1F, -1F, 16, 2, 2);
		this.flatSpiderLeg5.setRotationPoint(-4F, 20F, -2F);
		this.flatSpiderLeg5.setTextureSize(64, 32);
		this.flatSpiderLeg5.mirror = true;
		this.setRotation(this.flatSpiderLeg5, 0F, -0.2792527F, -0.1919862F);
		this.flatSpiderLeg3 = new ModelRenderer(this, 18, 0);
		this.flatSpiderLeg3.addBox(-15F, -1F, -1F, 16, 2, 2);
		this.flatSpiderLeg3.setRotationPoint(-4F, 20F, -1F);
		this.flatSpiderLeg3.setTextureSize(64, 32);
		this.flatSpiderLeg3.mirror = true;
		this.setRotation(this.flatSpiderLeg3, 0F, 0.2792527F, -0.1919862F);
		this.flatSpiderLeg1 = new ModelRenderer(this, 18, 0);
		this.flatSpiderLeg1.addBox(-15F, -1F, -1F, 16, 2, 2);
		this.flatSpiderLeg1.setRotationPoint(-4F, 20F, 0F);
		this.flatSpiderLeg1.setTextureSize(64, 32);
		this.flatSpiderLeg1.mirror = true;
		this.setRotation(this.flatSpiderLeg1, 0F, 0.5759587F, -0.1919862F);
	}

	private void initVillagerSpiderChest()
	{
		this.villagerSpiderChestRight = new ModelRenderer(this, 46, 16);
		this.villagerSpiderChestRight.addBox(0F, -4F, -4F, 1, 8, 8);
		this.villagerSpiderChestRight.setRotationPoint(-5.9F, 20F, 11F);
		this.villagerSpiderChestRight.setTextureSize(64, 32);
		this.villagerSpiderChestRight.mirror = true;
		this.setRotation(this.villagerSpiderChestRight, 0F, 0F, 0F);
		this.villagerSpiderChestLeft = new ModelRenderer(this, 46, 16);
		this.villagerSpiderChestLeft.addBox(0F, -4F, -4F, 1, 8, 8);
		this.villagerSpiderChestLeft.setRotationPoint(5.9F, 20F, 11F);
		this.villagerSpiderChestLeft.setTextureSize(64, 32);
		this.villagerSpiderChestLeft.mirror = true;
		this.setRotation(this.villagerSpiderChestLeft, 0F, 3.141593F, 0F);
	}

	private void setRotation(ModelRenderer model, float angleX, float angleY, float angleZ)
	{
		model.rotateAngleX = angleX;
		model.rotateAngleY = angleY;
		model.rotateAngleZ = angleZ;
	}

	private void renderHugeSpider(float partialTickTime)
	{
		this.hugeSpiderHead.render(partialTickTime);
		this.hugeSpiderRearEnd.render(partialTickTime);
		this.hugeSpiderBody.render(partialTickTime);
		this.hugeSpiderLeftBulk.render(partialTickTime);
		this.hugeSpiderTopBulk.render(partialTickTime);
		this.hugeSpiderBackBulk.render(partialTickTime);
		this.hugeSpiderRightBulk.render(partialTickTime);
		this.hugeSpiderLeg1.render(partialTickTime);
		this.hugeSpiderLeg2.render(partialTickTime);
		this.hugeSpiderLeg3.render(partialTickTime);
		this.hugeSpiderLeg4.render(partialTickTime);
		this.hugeSpiderLeg5.render(partialTickTime);
		this.hugeSpiderLeg6.render(partialTickTime);
		this.hugeSpiderLeg7.render(partialTickTime);
		this.hugeSpiderLeg8.render(partialTickTime);
	}

	private void renderRaisedSpider(float partialTickTime)
	{
		this.raisedSpiderHead.render(partialTickTime);
		this.raisedSpiderBody.render(partialTickTime);
		this.raisedSpiderRearEnd.render(partialTickTime);
		this.raisedSpiderLeg1.render(partialTickTime);
		this.raisedSpiderLeg2.render(partialTickTime);
		this.raisedSpiderLeg3.render(partialTickTime);
		this.raisedSpiderLeg4.render(partialTickTime);
		this.raisedSpiderLeg5.render(partialTickTime);
		this.raisedSpiderLeg6.render(partialTickTime);
		this.raisedSpiderLeg7.render(partialTickTime);
		this.raisedSpiderLeg8.render(partialTickTime);
	}

	private void renderDefaultSpider(float partialTickTime)
	{
		this.defaultSpiderHead.render(partialTickTime);
		this.defaultSpiderNeck.render(partialTickTime);
		this.defaultSpiderBody.render(partialTickTime);
		this.defaultSpiderLeg1.render(partialTickTime);
		this.defaultSpiderLeg2.render(partialTickTime);
		this.defaultSpiderLeg3.render(partialTickTime);
		this.defaultSpiderLeg4.render(partialTickTime);
		this.defaultSpiderLeg5.render(partialTickTime);
		this.defaultSpiderLeg6.render(partialTickTime);
		this.defaultSpiderLeg7.render(partialTickTime);
		this.defaultSpiderLeg8.render(partialTickTime);
	}

	private void renderThinSpider(float partialTickTime)
	{
		this.thinSpiderHead.render(partialTickTime);
		this.thinSpiderBody.render(partialTickTime);
		this.thinSpiderRearEnd.render(partialTickTime);
		this.thinSpiderLeg1.render(partialTickTime);
		this.thinSpiderLeg2.render(partialTickTime);
		this.thinSpiderLeg3.render(partialTickTime);
		this.thinSpiderLeg4.render(partialTickTime);
		this.thinSpiderLeg5.render(partialTickTime);
		this.thinSpiderLeg6.render(partialTickTime);
		this.thinSpiderLeg7.render(partialTickTime);
		this.thinSpiderLeg8.render(partialTickTime);
	}

	private void renderTinySpider(float partialTickTime)
	{
		this.tinySpiderHead.render(partialTickTime);
		this.tinySpiderBody.render(partialTickTime);
		this.tinySpiderRearEnd.render(partialTickTime);
		this.tinySpiderLeg1.render(partialTickTime);
		this.tinySpiderLeg2.render(partialTickTime);
		this.tinySpiderLeg3.render(partialTickTime);
		this.tinySpiderLeg4.render(partialTickTime);
		this.tinySpiderLeg5.render(partialTickTime);
		this.tinySpiderLeg6.render(partialTickTime);
		this.tinySpiderLeg7.render(partialTickTime);
		this.tinySpiderLeg8.render(partialTickTime);
	}

	private void renderLongLegSpider(float partialTickTime)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslated(0, 0.35D, 0);
			this.longLegSpiderBody.render(partialTickTime);
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			GL11.glTranslated(0, -0.05D, 0);
			GL11.glScaled(1, 1.5D, 1);
			this.longLegSpiderLeg1.render(partialTickTime);
			this.longLegSpiderLeg2.render(partialTickTime);
			this.longLegSpiderLeg3.render(partialTickTime);
			this.longLegSpiderLeg4.render(partialTickTime);
			this.longLegSpiderLeg5.render(partialTickTime);
			this.longLegSpiderLeg6.render(partialTickTime);
			this.longLegSpiderLeg7.render(partialTickTime);
			this.longLegSpiderLeg8.render(partialTickTime);
		}
		GL11.glPopMatrix();
	}

	private void renderTinyLongLegSpider(float partialTickTime)
	{
		GL11.glPushMatrix();
		{
			GL11.glScaled(0.5D, 0.5D, 0.5D);
			GL11.glTranslated(0.0D, 1.2D, 0.0D);
			this.renderLongLegSpider(partialTickTime);
		}
		GL11.glPopMatrix();
	}

	private void renderSwarmSpiders(Entity entity, float partialTickTime)
	{
		//TODO
//		final EntityHatchedSpider spider = (EntityHatchedSpider) entity;
//		final int level = spider.getLevel();
//
//		if (spider.isOnLadder())
//		{
//			final Vec3 lookVector = spider.getLookVec();
//
//			if (lookVector.xCoord <= -0.90 || lookVector.zCoord <= -0.90)
//			{
//				GL11.glRotatef(270, 1, 0, 0);
//				GL11.glTranslated(0, -1.2, 0);
//			}
//
//			else if (lookVector.xCoord >= 0.90 || lookVector.zCoord >= 0.90)
//			{
//				GL11.glRotatef(-90, 1, 0, 0);
//				GL11.glTranslated(0, -1.2, 0);
//			}
//		}
//
//		if (level == 1)
//		{
//			for (int pass = 0; pass < 9; pass++)
//			{
//				GL11.glPushMatrix();
//				{
//					GL11.glScaled(0.3D, 0.3D, 0.3D);
//					GL11.glTranslated(0, 3.6D, 0D);
//
//					switch (pass)
//					{
//					case 0:
//						GL11.glTranslated(0D, 0.0D, -2.0D);
//						break;
//					case 1:
//						GL11.glTranslated(0D, 0, 2.0D);
//						break;
//					case 2:
//						GL11.glTranslated(1.8D, 0, 0D);
//						break;
//					case 3:
//						GL11.glTranslated(-1.8D, 0, 0D);
//						break;
//					case 4:
//						GL11.glTranslated(1.8D, 0, -2.0D);
//						break;
//					case 5:
//						GL11.glTranslated(-1.8D, 0, -2.0D);
//						break;
//					case 6:
//						GL11.glTranslated(1.8D, 0, 2.0D);
//						break;
//					case 7:
//						GL11.glTranslated(-1.8D, 0, 2.0D);
//						break;
//					default:
//						break;
//					}
//					this.renderDefaultSpider(partialTickTime);
//				}
//				GL11.glPopMatrix();
//			}
//		}
//
//		else if (level == 2)
//		{
//			for (int pass = 0; pass < 13; pass++)
//			{
//				GL11.glPushMatrix();
//				{
//					GL11.glScaled(0.2D, 0.2D, 0.2D);
//					GL11.glTranslated(0, 6.1D, 0D);
//
//					switch (pass)
//					{
//					case 0:
//						GL11.glTranslated(0D, 0.0D, -2.0D);
//						break;
//					case 1:
//						GL11.glTranslated(0D, 0, 2.0D);
//						break;
//					case 2:
//						GL11.glTranslated(1.8D, 0, 0D);
//						break;
//					case 3:
//						GL11.glTranslated(-1.8D, 0, 0D);
//						break;
//					case 4:
//						GL11.glTranslated(1.8D, 0, -2.0D);
//						break;
//					case 5:
//						GL11.glTranslated(-1.8D, 0, -2.0D);
//						break;
//					case 6:
//						GL11.glTranslated(1.8D, 0, 2.0D);
//						break;
//					case 7:
//						GL11.glTranslated(3.5D, 0, 0.0D);
//						break;
//					case 8:
//						GL11.glTranslated(-3.5D, 0, 0.0D);
//						break;
//					case 9:
//						GL11.glTranslated(0D, 0, 0D);
//						break;
//					case 10:
//						GL11.glTranslated(0D, 0, 4D);
//						break;
//					case 11:
//						GL11.glTranslated(-1.8D, 0, 2.0D);
//						break;
//					case 12:
//						GL11.glTranslated(0D, 0, -4D);
//						break;
//					default:
//						break;
//					}
//					this.renderDefaultSpider(partialTickTime);
//				}
//				GL11.glPopMatrix();
//			}
//		}
//
//		else if (level == 3)
//		{
//			for (int pass = 0; pass < 25; pass++)
//			{
//				GL11.glPushMatrix();
//				{
//					GL11.glScaled(0.15D, 0.15D, 0.15D);
//					GL11.glTranslated(0, 8.6D, 0D);
//
//					switch (pass)
//					{
//					case 0:
//						GL11.glTranslated(0D, 0.0D, -2.0D);
//						break;
//					case 1:
//						GL11.glTranslated(0D, 0, 2.0D);
//						break;
//					case 2:
//						GL11.glTranslated(1.8D, 0, 0D);
//						break;
//					case 3:
//						GL11.glTranslated(-1.8D, 0, 0D);
//						break;
//					case 4:
//						GL11.glTranslated(1.8D, 0, -2.0D);
//						break;
//					case 5:
//						GL11.glTranslated(-1.8D, 0, -2.0D);
//						break;
//					case 6:
//						GL11.glTranslated(1.8D, 0, 2.0D);
//						break;
//					case 7:
//						GL11.glTranslated(3.5D, 0, 0.0D);
//						break;
//					case 8:
//						GL11.glTranslated(-3.5D, 0, 0.0D);
//						break;
//					case 9:
//						GL11.glTranslated(0D, 0, 0D);
//						break;
//					case 10:
//						GL11.glTranslated(0D, 0, 4D);
//						break;
//					case 11:
//						GL11.glTranslated(-1.8D, 0, 2.0D);
//						break;
//					case 12:
//						GL11.glTranslated(0D, 0, -4D);
//						break;
//					case 13:
//						GL11.glTranslated(1.8D, 0, -4D);
//						break;
//					case 14:
//						GL11.glTranslated(-1.8D, 0, -4D);
//						break;
//					case 15:
//						GL11.glTranslated(3.5D, 0, -4D);
//						break;
//					case 16:
//						GL11.glTranslated(-3.5D, 0, -4D);
//						break;
//					case 17:
//						GL11.glTranslated(-3.5D, 0, -2D);
//						break;
//					case 18:
//						GL11.glTranslated(-3.5D, 0, 4D);
//						break;
//					case 19:
//						GL11.glTranslated(-3.5D, 0, 2D);
//						break;
//					case 20:
//						GL11.glTranslated(1.8D, 0, 4D);
//						break;
//					case 21:
//						GL11.glTranslated(3.5D, 0, 4D);
//						break;
//					case 22:
//						GL11.glTranslated(3.5D, 0, 2D);
//						break;
//					case 23:
//						GL11.glTranslated(3.5D, 0, -2D);
//						break;
//					case 24:
//						GL11.glTranslated(-1.8D, 0, 4D);
//						break;
//					default:
//						break;
//					}
//					this.renderDefaultSpider(partialTickTime);
//				}
//				GL11.glPopMatrix();
//			}
//		}
	}

	private void renderFlatSpider(float partialTickTime)
	{
		GL11.glPushMatrix();
		{
			GL11.glScalef(1F, 0.8F, 1F);
			GL11.glTranslated(0D, 0.05D, 0D);

			this.flatSpiderHead.render(partialTickTime);
			this.flatSpiderBody.render(partialTickTime);
			this.flatSpiderRearEnd.render(partialTickTime);
			this.flatSpiderLeg8.render(partialTickTime);
			this.flatSpiderLeg6.render(partialTickTime);
			this.flatSpiderLeg4.render(partialTickTime);
			this.flatSpiderLeg2.render(partialTickTime);
			this.flatSpiderLeg7.render(partialTickTime);
			this.flatSpiderLeg5.render(partialTickTime);
			this.flatSpiderLeg3.render(partialTickTime);
			this.flatSpiderLeg1.render(partialTickTime);
		}
		GL11.glPopMatrix();
	}

	private void renderVillagerSpiderChest(float partialTickTime)
	{
		GL11.glPushMatrix();
		{
			GL11.glTranslated(0.0D, -0.30D, 0.0D);
			this.villagerSpiderChestRight.render(partialTickTime);
			this.villagerSpiderChestLeft.render(partialTickTime);
		}
		GL11.glPopMatrix();
	}
}
