/*******************************************************************************
 * ModelCocoon.java
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
import sqr.entity.EntityCocoon;
import sqr.enums.EnumTypeVariant;

public class ModelCocoon extends ModelBase
{
	private final ModelRenderer modelWrappedHead;
	private final ModelRenderer modelWrappedBody;
	private final ModelRenderer modelWrappedBodyTall;
	private final ModelRenderer modelVisibleFlatHead;
	
	private final ModelRenderer modelSheepFlatHead;
	
	private final ModelRenderer modelWolfHead;
	private final ModelRenderer modelWolfEarLeft;
	private final ModelRenderer modelWolfEarRight;
	private final ModelRenderer modelWolfNose;
	
	private final ModelRenderer modelChickenBeak;
	private final ModelRenderer modelChickenHead;
	
	private final ModelRenderer modelEndermanHead;
	
	private final ModelRenderer modelVillagerHead;
	private final ModelRenderer modelVillagerNose;
	
	private final ModelRenderer modelHorseHeadStart;
	private final ModelRenderer modelHorseMuzzleUp;
	private final ModelRenderer modelHorseMuzzleDown;
	private final ModelRenderer modelHorseWeb;
	
	private final ModelRenderer modelGhastBody;
	private final ModelRenderer modelGhastFace;
	
	public ModelCocoon()
	{
		super();
		
		this.modelWrappedHead = new ModelRenderer(this, 20, 13);
		this.modelWrappedHead.addBox(0F, 0F, 0F, 10, 9, 10, 0F);
		this.modelWrappedHead.setRotationPoint(-5F, -21F, -5F);
		
		this.modelWrappedBody = new ModelRenderer(this, 16, 8);
		this.modelWrappedBody.addBox(0F, 0F, 0F, 12, 12, 12, 0F);
		this.modelWrappedBody.setRotationPoint(-6F, -12F, -6F);
		
		this.modelWrappedBodyTall = new ModelRenderer(this, 0, 0);
		this.modelWrappedBodyTall.addBox(-5F, 0F, -3.5F, 10, 17, 7);
		this.modelWrappedBodyTall.setRotationPoint(0F, -16F, 0F);
		
		this.modelVisibleFlatHead = new ModelRenderer(this, 2, 2);
		this.modelVisibleFlatHead.addBox(-4F, -4F, -7F, 8, 8, 6, 0F);
		this.modelVisibleFlatHead.setRotationPoint(0F, -16F, -1F);
		
		this.modelSheepFlatHead = new ModelRenderer(this, 2, 2);
		this.modelSheepFlatHead.addBox(-4F, -4F, -7F, 8, 8, 6, 0F);
		this.modelSheepFlatHead.setRotationPoint(0F, -16F, 0F);
		
		this.modelWolfHead = new ModelRenderer(this, 0, 0);
		this.modelWolfHead.addBox(-3, -3, -2, 6, 6, 4, 0F);
		this.modelWolfHead.setRotationPoint(0F, -14.5F, -7F);
		
		this.modelWolfEarLeft = new ModelRenderer(this, 16, 14);
		this.modelWolfEarLeft.addBox(-3, -5, 0, 2, 2, 1, 0F);
		this.modelWolfEarLeft.setRotationPoint(0F, -14.5F, -7F);
		
		this.modelWolfEarRight = new ModelRenderer(this, 16, 14);
		this.modelWolfEarRight.addBox(1, -5, 0, 2, 2, 1, 0F);
		this.modelWolfEarRight.setRotationPoint(0F, -14.5F, -7F);
		
		this.modelWolfNose = new ModelRenderer(this, 0, 10);
		this.modelWolfNose.addBox(-2, 0, -5, 3, 3, 4, 0F);
		this.modelWolfNose.setRotationPoint(0.5F, -14.5F, -7F);
		
		this.modelChickenHead = new ModelRenderer(this, 0, 0);
		this.modelChickenHead.addBox(-2F, -6F, -2F, 4, 6, 3, 0.0F);
		this.modelChickenHead.setRotationPoint(0F, -4F, -7F);
		
		this.modelChickenBeak = new ModelRenderer(this, 14, 0);
		this.modelChickenBeak.addBox(-2F, -4F, -4F, 4, 2, 2, 0.0F);
		this.modelChickenBeak.setRotationPoint(0F, -4F, -7F);
		
		this.modelEndermanHead = new ModelRenderer(this, 32, 16);
		this.modelEndermanHead.addBox(-4F, -8F, -4F, 8, 8, 8);
		this.modelEndermanHead.setRotationPoint(0F, -16F, 0F);
		
		this.modelVillagerHead = new ModelRenderer(this, 32, 14);
		this.modelVillagerHead.addBox(-4F, -10F, -4F, 8, 10, 8);
		this.modelVillagerHead.setRotationPoint(0F, -16F, 0F);
		
		this.modelVillagerNose = new ModelRenderer(this, 56, 0);
		this.modelVillagerNose.addBox(-1F, -1F, -2F, 2, 4, 2);
		this.modelVillagerNose.setRotationPoint(0F, -18F, -3.8F);
		
		this.modelHorseHeadStart = new ModelRenderer(this, 0, 8);
		this.modelHorseHeadStart.addBox(-2F, -14F, -3F, 4, 5, 6);
		this.modelHorseHeadStart.setRotationPoint(0F, -7F, -2F);
		this.setRotation(this.modelHorseHeadStart, 0.2617994F, 0F, 0F);
		
		this.modelHorseMuzzleUp = new ModelRenderer(this, 20, 0);
		this.modelHorseMuzzleUp.addBox(-2F, -14F, -9F, 4, 3, 6);
		this.modelHorseMuzzleUp.setRotationPoint(0F, -7F, -2F);
		this.setRotation(this.modelHorseMuzzleUp, 0.2617994F, 0F, 0F);
		
		this.modelHorseMuzzleDown = new ModelRenderer(this, 0, 0);
		this.modelHorseMuzzleDown.addBox(-2F, -1F, -6.2F, 4, 2, 6);
		this.modelHorseMuzzleDown.setRotationPoint(0F, -16F, -7F);
		this.setRotation(this.modelHorseMuzzleDown, 0.4476924F, 0.0174533F, 0F);
		
		this.modelHorseWeb = new ModelRenderer(this, 51, -6);
		this.modelHorseWeb.addBox(2F, -3F, -6F, 0, 4, 6);
		this.modelHorseWeb.setRotationPoint(0F, -16F, -7F);
		this.setRotation(this.modelHorseWeb, 0.3490659F, -0.0174533F, 0F);
		
		this.modelGhastBody = new ModelRenderer(this, 0, 0);
		this.modelGhastBody.addBox(0F, 0F, 0F, 16, 16, 16);
		this.modelGhastBody.setRotationPoint(-8F, 8F, -8F);
		this.modelGhastBody.setTextureSize(64, 32);
		this.modelGhastBody.mirror = true;
		this.setRotation(this.modelGhastBody, 0F, 0F, 0F);
		
		this.modelGhastFace = new ModelRenderer(this, 0, 0);
		this.modelGhastFace.addBox(0F, 0F, 0F, 14, 14, 1);
		this.modelGhastFace.setRotationPoint(-7F, 9F, -9F);
		this.modelGhastFace.setTextureSize(64, 32);
		this.modelGhastFace.mirror = true;
		this.setRotation(this.modelGhastFace, 0F, 0F, 0F);
	}
	
	@Override
	public void render(Entity entity, float posX, float posY, float posZ, float rotationYaw, float rotationPitch, float partialTickTime)
	{
		final EntityCocoon entityCocoon = (EntityCocoon) entity;
		final EnumTypeVariant cocoonType = entityCocoon.getType();
		
		// TODO
		// this.renderCocoonBase(cocoonSize, partialTickTime);
		
		if (cocoonType == EnumTypeVariant.WOLF)
		{
			this.modelWolfHead.render(partialTickTime);
			this.modelWolfEarLeft.render(partialTickTime);
			this.modelWolfEarRight.render(partialTickTime);
			this.modelWolfNose.render(partialTickTime);
		}
		
		else if (cocoonType == EnumTypeVariant.CHICKEN)
		{
			this.modelChickenHead.render(partialTickTime);
			this.modelChickenBeak.render(partialTickTime);
		}
		
		else if (cocoonType == EnumTypeVariant.SHEEP)
		{
			this.modelSheepFlatHead.render(partialTickTime);
		}
		
		else if (cocoonType == EnumTypeVariant.VILLAGER)
		{
			this.modelVillagerNose.render(partialTickTime);
			this.modelVillagerHead.render(partialTickTime);
		}
		
		else if (cocoonType == EnumTypeVariant.ENDERMAN)
		{
			this.modelEndermanHead.render(partialTickTime);
		}
		
		else if (cocoonType == EnumTypeVariant.HORSE)
		{
			this.modelHorseHeadStart.render(partialTickTime);
			this.modelHorseMuzzleUp.render(partialTickTime);
			this.modelHorseMuzzleDown.render(partialTickTime);
			this.modelHorseWeb.render(partialTickTime);
		}
		
		else if (cocoonType == EnumTypeVariant.GHAST)
		{
			this.modelGhastFace.render(partialTickTime);
		}
		
		else
		{
			this.modelVisibleFlatHead.render(partialTickTime);
		}
	}
	
	// private void renderCocoonBase(EnumCocoonSize cocoonSize, float
	// partialTickTime)
	// {
	// if (cocoonSize == EnumCocoonSize.SMALL)
	// {
	// this.modelWrappedBody.render(partialTickTime);
	// }
	//
	// else if (cocoonSize == EnumCocoonSize.NORMAL)
	// {
	// this.modelWrappedHead.render(partialTickTime);
	// this.modelWrappedBody.render(partialTickTime);
	// }
	//
	// else if (cocoonSize == EnumCocoonSize.TALL)
	// {
	// this.modelWrappedBodyTall.render(partialTickTime);
	// }
	//
	// else if (cocoonSize == EnumCocoonSize.HUGE)
	// {
	// this.modelGhastBody.render(partialTickTime);
	// }
	// }
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
