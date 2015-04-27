package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMand extends ModelBase
{
	
	public ModelMand()
	{
		this.Head = new ModelRenderer(this, 32, 4);
		this.Head.addBox(-4, -8, -4, 8, 8, 8, 0F);
		this.Head.setRotationPoint(0, 8, 0);
		
		this.Head.rotateAngleX = 3.100328E-17F;
		this.Head.rotateAngleY = 0F;
		this.Head.rotateAngleZ = 0F;
		this.Head.mirror = false;
		
		this.torso = new ModelRenderer(this, 44, 18);
		this.torso.addBox(-3, -10, -2, 6, 10, 4, 0F);
		this.torso.setRotationPoint(0, 18, 0);
		
		this.torso.rotateAngleX = 0F;
		this.torso.rotateAngleY = 0F;
		this.torso.rotateAngleZ = 0F;
		this.torso.mirror = false;
		
		this.bipedLeftArm = new ModelRenderer(this, 56, 0);
		this.bipedLeftArm.addBox(-1, -1, -1, 2, 10, 2, 0F);
		this.bipedLeftArm.setRotationPoint(4, 9, 0);
		
		this.bipedLeftArm.rotateAngleX = 0F;
		this.bipedLeftArm.rotateAngleY = 0F;
		this.bipedLeftArm.rotateAngleZ = 0F;
		this.bipedLeftArm.mirror = false;
		
		this.bipedRightArm = new ModelRenderer(this, 56, 0);
		this.bipedRightArm.addBox(-1, -1, -1, 2, 10, 2, 0F);
		this.bipedRightArm.setRotationPoint(-4, 9, 0);
		
		this.bipedRightArm.rotateAngleX = 0F;
		this.bipedRightArm.rotateAngleY = 0F;
		this.bipedRightArm.rotateAngleZ = 0F;
		this.bipedRightArm.mirror = false;
		
		this.boob1 = new ModelRenderer(this, 46, 20);
		this.boob1.addBox(-3, -7, -7, 6, 3, 2, 0F);
		this.boob1.setRotationPoint(0, 18, 0);
		
		this.boob1.rotateAngleX = -0.5424333F;
		this.boob1.rotateAngleY = 0F;
		this.boob1.rotateAngleZ = 0F;
		this.boob1.mirror = false;
		
		this.roots1 = new ModelRenderer(this, 0, 18);
		this.roots1.addBox(-4, -8, -4, 8, 6, 8, 0F);
		this.roots1.setRotationPoint(0, 26, 0);
		
		this.roots1.rotateAngleX = 3.100328E-17F;
		this.roots1.rotateAngleY = 0F;
		this.roots1.rotateAngleZ = 0F;
		this.roots1.mirror = false;
		
		this.skirtF = new ModelRenderer(this, 0, 0);
		this.skirtF.addBox(-4, 0, 0, 8, 8, 0, 0F);
		this.skirtF.setRotationPoint(0, 18, -4);
		
		this.skirtF.rotateAngleX = -0.3490658F;
		this.skirtF.rotateAngleY = 0F;
		this.skirtF.rotateAngleZ = 0F;
		this.skirtF.mirror = false;
		
		this.SkirtR = new ModelRenderer(this, 0, 0);
		this.SkirtR.addBox(-4, 0, 0, 8, 8, 0, 0F);
		this.SkirtR.setRotationPoint(-4, 18, 0);
		
		this.SkirtR.rotateAngleX = -0.3490658F;
		this.SkirtR.rotateAngleY = 1.570796F;
		this.SkirtR.rotateAngleZ = 0F;
		this.SkirtR.mirror = false;
		
		this.SkirtR2 = new ModelRenderer(this, 0, 0);
		this.SkirtR2.addBox(-4, 0, 0, 8, 8, 0, 0F);
		this.SkirtR2.setRotationPoint(4, 18, 0);
		
		this.SkirtR2.rotateAngleX = -0.3490658F;
		this.SkirtR2.rotateAngleY = -1.570796F;
		this.SkirtR2.rotateAngleZ = 0F;
		this.SkirtR2.mirror = false;
		
		this.SkirtB = new ModelRenderer(this, 0, 0);
		this.SkirtB.addBox(-4, 0, 0, 8, 8, 0, 0F);
		this.SkirtB.setRotationPoint(0, 18, 4);
		
		this.SkirtB.rotateAngleX = -0.3490658F;
		this.SkirtB.rotateAngleY = 3.141593F;
		this.SkirtB.rotateAngleZ = 0F;
		this.SkirtB.mirror = false;
		
		this.Hair1 = new ModelRenderer(this, 0, 9);
		this.Hair1.addBox(-5, -15, 0, 10, 7, 0, 0F);
		this.Hair1.setRotationPoint(0, 8, 0);
		
		this.Hair1.rotateAngleX = 0F;
		this.Hair1.rotateAngleY = 0.7853982F;
		this.Hair1.rotateAngleZ = 0F;
		this.Hair1.mirror = false;
		
		this.Hair2 = new ModelRenderer(this, 0, 9);
		this.Hair2.addBox(-5, -15, 0, 10, 7, 0, 0F);
		this.Hair2.setRotationPoint(0, 8, 0);
		
		this.Hair2.rotateAngleX = 0F;
		this.Hair2.rotateAngleY = -0.7853982F;
		this.Hair2.rotateAngleZ = 0F;
		this.Hair2.mirror = false;
		
		this.Bud1 = new ModelRenderer(this, 16, 0);
		this.Bud1.addBox(-2, -12, -3, 6, 0, 6, 0F);
		this.Bud1.setRotationPoint(0, 8, 0);
		
		this.Bud1.rotateAngleX = 0F;
		this.Bud1.rotateAngleY = -0.8179294F;
		this.Bud1.rotateAngleZ = -0.4089647F;
		this.Bud1.mirror = false;
		
		this.Bud2 = new ModelRenderer(this, 16, 0);
		this.Bud2.addBox(-4, -11, 2, 6, 0, 6, 0F);
		this.Bud2.setRotationPoint(0, 8, 0);
		
		this.Bud2.rotateAngleX = 0.7435722F;
		this.Bud2.rotateAngleY = -1.524323F;
		this.Bud2.rotateAngleZ = -0.03717861F;
		this.Bud2.mirror = false;
		
		this.Bud3 = new ModelRenderer(this, 16, 0);
		this.Bud3.addBox(-3, -12, -3, 6, 0, 6, 0F);
		this.Bud3.setRotationPoint(0, 8, 0);
		
		this.Bud3.rotateAngleX = -0.2974289F;
		this.Bud3.rotateAngleY = -0.669215F;
		this.Bud3.rotateAngleZ = 0F;
		this.Bud3.mirror = false;
		
		this.Fruit = new ModelRenderer(this, 0, 22);
		this.Fruit.addBox(1, -11, -4, 2, 2, 2, 0F);
		this.Fruit.setRotationPoint(0, 8, 0);
		
		this.Fruit.rotateAngleX = 0F;
		this.Fruit.rotateAngleY = 0F;
		this.Fruit.rotateAngleZ = 0F;
		this.Fruit.mirror = false;
		
		this.roots2 = new ModelRenderer(this, 0, 18);
		this.roots2.addBox(0, 0, 0, 8, 6, 8, 0F);
		this.roots2.setRotationPoint(-4, 24, -4);
		
		this.roots2.rotateAngleX = 3.100328E-17F;
		this.roots2.rotateAngleY = 0F;
		this.roots2.rotateAngleZ = 0F;
		this.roots2.mirror = false;
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.Head.render(f5);
		this.torso.render(f5);
		this.bipedLeftArm.render(f5);
		this.bipedRightArm.render(f5);
		this.boob1.render(f5);
		this.roots1.render(f5);
		this.skirtF.render(f5);
		this.SkirtR.render(f5);
		this.SkirtR2.render(f5);
		this.SkirtB.render(f5);
		this.Hair1.render(f5);
		this.Hair2.render(f5);
		this.Bud1.render(f5);
		this.Bud2.render(f5);
		this.Bud3.render(f5);
		this.Fruit.render(f5);
		this.roots2.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.Head.rotateAngleY = f3 / 57.29578F;
		this.Head.rotateAngleX = f4 / 57.29578F;
		
		this.bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.2F;
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightArm.rotateAngleY = 0.0F;
		this.bipedLeftArm.rotateAngleY = 0.0F;
		if (this.onGround > -9990F)
		{
			float f6 = this.onGround;
			this.bipedRightArm.rotationPointZ = MathHelper.sin(this.torso.rotateAngleY) * 3.5F;
			this.bipedRightArm.rotationPointX = -MathHelper.cos(this.torso.rotateAngleY) * 3.5F;
			this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.torso.rotateAngleY) * 3.5F;
			this.bipedLeftArm.rotationPointX = MathHelper.cos(this.torso.rotateAngleY) * 3.5F;
			this.bipedRightArm.rotateAngleY += this.torso.rotateAngleY;
			this.bipedLeftArm.rotateAngleY += this.torso.rotateAngleY;
			this.bipedLeftArm.rotateAngleX += this.torso.rotateAngleY;
			f6 = 1.0F - this.onGround;
			f6 *= f6;
			f6 *= f6;
			f6 = 1.0F - f6;
			final float f7 = MathHelper.sin(f6 * 3.141593F);
			final float f8 = MathHelper.sin(this.onGround * 3.141593F) * -(this.Head.rotateAngleX - 0.7F) * 0.75F;
			this.bipedRightArm.rotateAngleX -= f7 * 1.2D + f8;
			this.bipedRightArm.rotateAngleY += this.torso.rotateAngleY * 2.0F;
			this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * 3.141593F) * -0.4F;
		}
		this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
		
		/*
		 * float f6 = 0.7853982F; Leg1.rotateAngleZ = -f6; Leg2.rotateAngleZ =
		 * f6; Leg3.rotateAngleZ = -f6 * 0.74F; Leg4.rotateAngleZ = f6 * 0.74F;
		 * Leg5.rotateAngleZ = -f6 * 0.74F; Leg6.rotateAngleZ = f6 * 0.74F;
		 * Leg7.rotateAngleZ = -f6; Leg8.rotateAngleZ = f6; float f7 = -0F;
		 * float f8 = 0.3926991F; Leg1.rotateAngleY = f8 * 2.0F + f7;
		 * Leg2.rotateAngleY = -f8 * 2.0F - f7; Leg3.rotateAngleY = f8 * 1.0F +
		 * f7; Leg4.rotateAngleY = -f8 * 1.0F - f7; Leg5.rotateAngleY = -f8 *
		 * 1.0F + f7; Leg6.rotateAngleY = f8 * 1.0F - f7; Leg7.rotateAngleY =
		 * -f8 * 2.0F + f7; Leg8.rotateAngleY = f8 * 2.0F - f7; float f9 =
		 * -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1; float f10 =
		 * -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1; float
		 * f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
		 * float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F)
		 * * f1; float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F)
		 * * f1; float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) *
		 * 0.4F) * f1; float f15 = Math.abs(MathHelper.sin(f * 0.6662F +
		 * 1.570796F) * 0.4F) * f1; float f16 = Math.abs(MathHelper.sin(f *
		 * 0.6662F + 4.712389F) * 0.4F) * f1; Leg1.rotateAngleY += f9;
		 * Leg2.rotateAngleY += -f9; Leg3.rotateAngleY += f10; Leg4.rotateAngleY
		 * += -f10; Leg5.rotateAngleY += f11; Leg6.rotateAngleY += -f11;
		 * Leg7.rotateAngleY += f12; Leg8.rotateAngleY += -f12;
		 * Leg1.rotateAngleZ += f13; Leg2.rotateAngleZ += -f13;
		 * Leg3.rotateAngleZ += f14; Leg4.rotateAngleZ += -f14;
		 * Leg5.rotateAngleZ += f15; Leg6.rotateAngleZ += -f15;
		 * Leg7.rotateAngleZ += f16; Leg8.rotateAngleZ += -f16;
		 */
	}
	
	/*
	 * public ModelRenderer spiderHead; public ModelRenderer spiderNeck; public
	 * ModelRenderer spiderBody; public ModelRenderer spiderLeg1; public
	 * ModelRenderer spiderLeg2; public ModelRenderer spiderLeg3; public
	 * ModelRenderer spiderLeg4; public ModelRenderer spiderLeg5; public
	 * ModelRenderer spiderLeg6; public ModelRenderer spiderLeg7; public
	 * ModelRenderer spiderLeg8;
	 */
	public ModelRenderer Head;
	public ModelRenderer torso;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer bipedRightArm;
	public ModelRenderer boob1;
	public ModelRenderer roots1;
	public ModelRenderer skirtF;
	public ModelRenderer SkirtR;
	public ModelRenderer SkirtR2;
	public ModelRenderer SkirtB;
	public ModelRenderer Hair1;
	public ModelRenderer Hair2;
	public ModelRenderer Bud1;
	public ModelRenderer Bud2;
	public ModelRenderer Bud3;
	public ModelRenderer Fruit;
	public ModelRenderer roots2;
}
