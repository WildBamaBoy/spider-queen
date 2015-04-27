package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelJack extends ModelBase
{
	
	public ModelJack()
	{
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4, -8, -4, 8, 8, 8, 0F);
		this.head.setRotationPoint(0, 0, -1);
		
		this.head.rotateAngleX = 0F;
		this.head.rotateAngleY = 0F;
		this.head.rotateAngleZ = 0F;
		this.head.mirror = false;
		
		this.Body1 = new ModelRenderer(this, 0, 16);
		this.Body1.addBox(-3, 0, -8, 6, 8, 8, 0F);
		this.Body1.setRotationPoint(0, 1, 0);
		
		this.Body1.rotateAngleX = 0.7853982F;
		this.Body1.rotateAngleY = 0F;
		this.Body1.rotateAngleZ = 0F;
		this.Body1.mirror = false;
		
		this.Neck = new ModelRenderer(this, 0, 4);
		this.Neck.addBox(-1, 0, -1, 2, 2, 2, 0F);
		this.Neck.setRotationPoint(0, 0, -1);
		
		this.Neck.rotateAngleX = 0F;
		this.Neck.rotateAngleY = 0F;
		this.Neck.rotateAngleZ = 0F;
		this.Neck.mirror = false;
		/*
		 * Faceglow = new ModelRenderer(this,24, 0); Faceglow.addBox(-4, -8, -3,
		 * 8, 8, 0, 0F); Faceglow.setRotationPoint(0, 0, 0);
		 * 
		 * Faceglow.rotateAngleX = 0F; Faceglow.rotateAngleY = 0F;
		 * Faceglow.rotateAngleZ = 0F; Faceglow.mirror = false;
		 */
		
		this.Headtop = new ModelRenderer(this, 0, 0);
		this.Headtop.addBox(-1, -10, -1, 2, 2, 2, 0F);
		this.Headtop.setRotationPoint(0, 0, -1);
		
		this.Headtop.rotateAngleX = 0F;
		this.Headtop.rotateAngleY = 0F;
		this.Headtop.rotateAngleZ = 0F;
		this.Headtop.mirror = false;
		
		this.Body3 = new ModelRenderer(this, 2, 18);
		this.Body3.addBox(-1, 1, -7, 6, 6, 6, 0F);
		this.Body3.setRotationPoint(0, 1, 0);
		
		this.Body3.rotateAngleX = 0.7853982F;
		this.Body3.rotateAngleY = 0F;
		this.Body3.rotateAngleZ = 0F;
		this.Body3.mirror = false;
		
		this.Body2 = new ModelRenderer(this, 2, 18);
		this.Body2.addBox(-5, 1, -7, 6, 6, 6, 0F);
		this.Body2.setRotationPoint(0, 1, 0);
		
		this.Body2.rotateAngleX = 0.7853982F;
		this.Body2.rotateAngleY = 0F;
		this.Body2.rotateAngleZ = 0F;
		this.Body2.mirror = false;
		
		this.Arm = new ModelRenderer(this, 28, 22);
		this.Arm.addBox(-1, -1, -2, 1, 8, 2, 0F);
		this.Arm.setRotationPoint(4, 5, -1);
		
		this.Arm.rotateAngleX = -1.745329F;
		this.Arm.rotateAngleY = 0F;
		this.Arm.rotateAngleZ = 0F;
		this.Arm.mirror = false;
		
		this.Hand = new ModelRenderer(this, 32, 10);
		this.Hand.addBox(-2, 6, -3, 3, 3, 3, 0F);
		this.Hand.setRotationPoint(4, 5, -1);
		
		this.Hand.rotateAngleX = -1.570796F;
		this.Hand.rotateAngleY = 0F;
		this.Hand.rotateAngleZ = 0F;
		this.Hand.mirror = false;
		
		this.Lantern = new ModelRenderer(this, 34, 24);
		this.Lantern.addBox(-2, 6, 0, 3, 3, 5, 0F);
		this.Lantern.setRotationPoint(4, 5, -1);
		
		this.Lantern.rotateAngleX = -1.570796F;
		this.Lantern.rotateAngleY = 0F;
		this.Lantern.rotateAngleZ = 0F;
		this.Lantern.mirror = false;
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.head.render(f5);
		this.Body1.render(f5);
		this.Neck.render(f5);
		// Faceglow.render(f5);
		this.Headtop.render(f5);
		this.Body3.render(f5);
		this.Body2.render(f5);
		this.Arm.render(f5);
		this.Hand.render(f5);
		this.Lantern.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		
		this.head.rotateAngleY = f3 / 57.29578F;
		this.head.rotateAngleX = f4 / 57.29578F;
		
		// Faceglow.rotateAngleY = head.rotateAngleY;Faceglow.rotateAngleX =
		// head.rotateAngleX;
		
		this.Body1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
		this.Body2.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
		this.Body3.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F;
	}
	
	public ModelRenderer head;
	public ModelRenderer Body1;
	public ModelRenderer Neck;
	// public ModelRenderer Faceglow;
	public ModelRenderer Headtop;
	public ModelRenderer Body3;
	public ModelRenderer Body2;
	public ModelRenderer Arm;
	public ModelRenderer Hand;
	public ModelRenderer Lantern;
}
