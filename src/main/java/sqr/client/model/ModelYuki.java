package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelYuki extends ModelBase
{
	
	public ModelYuki()
	{
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4, -8, -4, 8, 8, 8, 0F);
		this.head.setRotationPoint(0F, -3F, 0F);
		this.head.rotateAngleX = 0.1745329F;
		this.head.rotateAngleY = 0F;
		this.head.rotateAngleZ = 0F;
		this.head.mirror = false;
		this.body1 = new ModelRenderer(this, 24, 16);
		this.body1.addBox(-4, 0, -2, 8, 12, 4, 0F);
		this.body1.setRotationPoint(0F, -3F, 0F);
		this.body1.rotateAngleX = 0F;
		this.body1.rotateAngleY = 0F;
		this.body1.rotateAngleZ = 0F;
		this.body1.mirror = false;
		this.rightarm2 = new ModelRenderer(this, 48, 18);
		this.rightarm2.addBox(-3, -3, -1, 6, 12, 2, 0F);
		this.rightarm2.setRotationPoint(-4F, 5F, -3F);
		this.rightarm2.rotateAngleX = -3.141593F;
		this.rightarm2.rotateAngleY = 0.3328681F;
		this.rightarm2.rotateAngleZ = 0.4730761F;
		this.rightarm2.mirror = false;
		this.I_NEED_A_NAME = new ModelRenderer(this, 0, 0);
		this.I_NEED_A_NAME.addBox(0, 0, 0, 1, 1, 1, 0F);
		this.I_NEED_A_NAME.setRotationPoint(0F, 0F, 0F);
		this.I_NEED_A_NAME.rotateAngleX = 0F;
		this.I_NEED_A_NAME.rotateAngleY = 0F;
		this.I_NEED_A_NAME.rotateAngleZ = 0F;
		this.I_NEED_A_NAME.mirror = false;
		this.leftarm = new ModelRenderer(this, 40, 18);
		this.leftarm.addBox(-6, -1, -1, 6, 12, 2, 0F);
		this.leftarm.setRotationPoint(5F, -2F, 0F);
		this.leftarm.rotateAngleX = 0F;
		this.leftarm.rotateAngleY = 0F;
		this.leftarm.rotateAngleZ = -0.4363323F;
		this.leftarm.mirror = false;
		this.belt = new ModelRenderer(this, 32, 0);
		this.belt.addBox(0, 0, 0, 10, 1, 6, 0F);
		this.belt.setRotationPoint(-5F, 6F, -3F);
		this.belt.rotateAngleX = 0F;
		this.belt.rotateAngleY = 0F;
		this.belt.rotateAngleZ = 0F;
		this.belt.mirror = false;
		this.hairbun = new ModelRenderer(this, 32, 7);
		this.hairbun.addBox(-2, -8, 6, 4, 4, 4, 0F);
		this.hairbun.setRotationPoint(0F, -3F, 0F);
		this.hairbun.rotateAngleX = 0.5585054F;
		this.hairbun.rotateAngleY = 0F;
		this.hairbun.rotateAngleZ = 0F;
		this.hairbun.mirror = false;
		this.ribbon = new ModelRenderer(this, 48, 7);
		this.ribbon.addBox(-3, 0, 0, 6, 1, 5, 0F);
		this.ribbon.setRotationPoint(0F, 6F, 3F);
		this.ribbon.rotateAngleX = -0.9294652F;
		this.ribbon.rotateAngleY = 0F;
		this.ribbon.rotateAngleZ = 0F;
		this.ribbon.mirror = false;
		this.rightarm1 = new ModelRenderer(this, 48, 18);
		this.rightarm1.addBox(0, 0, 0, 6, 10, 2, 0F);
		this.rightarm1.setRotationPoint(-3F, 9F, -3F);
		this.rightarm1.rotateAngleX = 0.2572064F;
		this.rightarm1.rotateAngleY = 0.03674378F;
		this.rightarm1.rotateAngleZ = -2.663924F;
		this.rightarm1.mirror = false;
		this.body2 = new ModelRenderer(this, 0, 16);
		this.body2.addBox(-4, 0, -2, 8, 12, 4, 0F);
		this.body2.setRotationPoint(0F, 9F, 0F);
		this.body2.rotateAngleX = 0F;
		this.body2.rotateAngleY = 0F;
		this.body2.rotateAngleZ = 0F;
		this.body2.mirror = false;
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.head.render(f5);
		this.body1.render(f5);
		this.rightarm2.render(f5);
		this.I_NEED_A_NAME.render(f5);
		this.leftarm.render(f5);
		this.belt.render(f5);
		this.hairbun.render(f5);
		this.ribbon.render(f5);
		this.rightarm1.render(f5);
		this.body2.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		
		this.head.rotateAngleY = f3 / 57.29578F;
		this.head.rotateAngleX = f4 / 57.29578F;
		this.hairbun.rotateAngleX = this.head.rotateAngleX;
		this.hairbun.rotateAngleY = this.head.rotateAngleY;
		
		// I_NEED_A_NAME.rotateAngleX = MathHelper.cos(100F * 0.6662F +
		// 3.141593F) * 2.5F * f1;
		// Leg1.rotateAngleY = 0.0F;
		
		/*
		 * Antenna1.rotateAngleY = Head.rotateAngleY; Antenna1.rotateAngleX =
		 * Head.rotateAngleX; Antenna1.rotateAngleZ = Head.rotateAngleZ;
		 * Antenna2.rotateAngleY = Head.rotateAngleY; Antenna2.rotateAngleX =
		 * Head.rotateAngleX; Antenna2.rotateAngleZ = Head.rotateAngleZ;
		 * Jaws.rotateAngleY = Head.rotateAngleY; Jaws.rotateAngleX =
		 * Head.rotateAngleX; Jaws.rotateAngleZ = Head.rotateAngleZ;
		 * 
		 * Leg1.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F
		 * * f1; Leg1.rotateAngleY = 0.0F;
		 * 
		 * Leg2.rotateAngleX = Leg1.rotateAngleX; Leg2.rotateAngleY =
		 * Leg1.rotateAngleY; Leg2.rotateAngleZ = Leg1.rotateAngleZ;
		 * Leg3.rotateAngleX = Leg1.rotateAngleX; Leg3.rotateAngleY =
		 * Leg1.rotateAngleY; Leg3.rotateAngleZ = Leg1.rotateAngleZ;
		 * Leg4.rotateAngleX = Leg1.rotateAngleX; Leg4.rotateAngleY =
		 * Leg1.rotateAngleY; Leg4.rotateAngleZ = Leg1.rotateAngleZ;
		 * Leg5.rotateAngleX = Leg1.rotateAngleX; Leg5.rotateAngleY =
		 * Leg1.rotateAngleY; Leg5.rotateAngleZ = Leg1.rotateAngleZ;
		 * Leg6.rotateAngleX = Leg1.rotateAngleX; Leg6.rotateAngleY =
		 * Leg1.rotateAngleY; Leg6.rotateAngleZ = Leg1.rotateAngleZ;
		 */
		
		/*
		 * lash1.rotateAngleY = head.rotateAngleY; lash1.rotateAngleX =
		 * head.rotateAngleX; lash2.rotateAngleY = head.rotateAngleY;
		 * lash2.rotateAngleX = head.rotateAngleX;
		 * 
		 * rightarm.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) *
		 * 2.5F * f1 * 0.5F; leftarm.rotateAngleX = MathHelper.cos(100F *
		 * 0.6662F + 3.141593F) * 2.5F * f1 * 0.5F; rightarm.rotateAngleZ =
		 * 0.0F; leftarm.rotateAngleZ = 0.0F; rightleg.rotateAngleX =
		 * MathHelper.cos(100F * 0.6662F + 3.141593F) * 2.5F * f1;
		 * leftleg.rotateAngleX = MathHelper.cos(100F * 0.6662F + 3.141593F) *
		 * 2.5F * f1; rightleg.rotateAngleY = 0.0F; leftleg.rotateAngleY = 0.0F;
		 * 
		 * 
		 * rightarm.rotateAngleY = 0.0F; leftarm.rotateAngleY = 0.0F;
		 * 
		 * 
		 * rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		 * leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		 * rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		 * leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
		 */
		
		// bucket2.rotateAngleX = rightarm.rotateAngleX;bucket2.rotateAngleY =
		// rightarm.rotateAngleY;bucket2.rotateAngleZ = rightarm.rotateAngleZ;
		// handle1.rotateAngleX = rightarm.rotateAngleX;handle1.rotateAngleY =
		// rightarm.rotateAngleY;handle1.rotateAngleZ = rightarm.rotateAngleZ;
		
		// bucket1.rotateAngleX = leftarm.rotateAngleX;bucket1.rotateAngleY =
		// leftarm.rotateAngleY;bucket1.rotateAngleZ = leftarm.rotateAngleZ;
		// handle2.rotateAngleX = leftarm.rotateAngleX;handle2.rotateAngleY =
		// leftarm.rotateAngleY;handle2.rotateAngleZ = leftarm.rotateAngleZ;
	}
	
	public ModelRenderer head;
	public ModelRenderer body1;
	public ModelRenderer rightarm2;
	public ModelRenderer I_NEED_A_NAME;
	public ModelRenderer leftarm;
	public ModelRenderer belt;
	public ModelRenderer hairbun;
	public ModelRenderer ribbon;
	public ModelRenderer rightarm1;
	public ModelRenderer body2;
}
