package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer

public class ModelOctopus extends ModelBase
{
	
	public ModelOctopus()
	{
		this.Body = new ModelRenderer(this, 0, 16);
		this.Body.addBox(-4, -8, -4, 8, 8, 8, 0F);
		this.Body.setRotationPoint(0, 18, 0);
		
		this.Body.rotateAngleX = 3.100328E-17F;
		this.Body.rotateAngleY = 0F;
		this.Body.rotateAngleZ = 0F;
		this.Body.mirror = false;
		
		this.skirtF = new ModelRenderer(this, 0, 0);
		this.skirtF.addBox(-4, 0, 0, 8, 8, 0, 0F);
		this.skirtF.setRotationPoint(0, 18, -2);
		
		this.skirtF.rotateAngleX = -0.3490658F;
		this.skirtF.rotateAngleY = 0F;
		this.skirtF.rotateAngleZ = 0F;
		this.skirtF.mirror = false;
		
		this.SkirtR = new ModelRenderer(this, 0, 0);
		this.SkirtR.addBox(-4, 0, 0, 8, 8, 0, 0F);
		this.SkirtR.setRotationPoint(-2, 18, 0);
		
		this.SkirtR.rotateAngleX = -0.3490658F;
		this.SkirtR.rotateAngleY = 1.570796F;
		this.SkirtR.rotateAngleZ = 0F;
		this.SkirtR.mirror = false;
		
		this.SkirtR2 = new ModelRenderer(this, 0, 0);
		this.SkirtR2.addBox(-4, 0, 0, 8, 8, 0, 0F);
		this.SkirtR2.setRotationPoint(2, 18, 0);
		
		this.SkirtR2.rotateAngleX = -0.3490658F;
		this.SkirtR2.rotateAngleY = -1.570796F;
		this.SkirtR2.rotateAngleZ = 0F;
		this.SkirtR2.mirror = false;
		
		this.SkirtB = new ModelRenderer(this, 0, 0);
		this.SkirtB.addBox(-4, 0, 0, 8, 8, 0, 0F);
		this.SkirtB.setRotationPoint(0, 18, 2);
		
		this.SkirtB.rotateAngleX = -0.3490658F;
		this.SkirtB.rotateAngleY = 3.141593F;
		this.SkirtB.rotateAngleZ = 0F;
		this.SkirtB.mirror = false;
		
		this.mouth = new ModelRenderer(this, 32, 24);
		this.mouth.addBox(-2, -4, -6, 4, 4, 4, 0F);
		this.mouth.setRotationPoint(0, 18, 0);
		
		this.mouth.rotateAngleX = 0F;
		this.mouth.rotateAngleY = 0F;
		this.mouth.rotateAngleZ = 0F;
		this.mouth.mirror = false;
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.Body.render(f5);
		this.skirtF.render(f5);
		this.SkirtR.render(f5);
		this.SkirtR2.render(f5);
		this.SkirtB.render(f5);
		this.mouth.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		
		this.Body.rotateAngleY = f3 / 57.29578F; // Body.rotateAngleX = f4 /
													// 57.29578F;
		// skirtF.rotateAngleY = f3 / 57.29578F; //skirtF.rotateAngleX = f4 /
		// 57.29578F;
		// SkirtR.rotateAngleY = f3 / 57.29578F + 1.570796F;
		// //SkirtR.rotateAngleX = f4 / 57.29578F;
		// SkirtR2.rotateAngleY = f3 / 57.29578F - 1.570796F;
		// //SkirtR2.rotateAngleX = f4 / 57.29578F;
		// SkirtB.rotateAngleY = f3 / 57.29578F + 3.141593F;
		// //SkirtB.rotateAngleX = f4 / 57.29578F;
		this.mouth.rotateAngleY = f3 / 57.29578F;
		this.mouth.rotateAngleX = f4 / 57.29578F;
		
	}
	
	public ModelRenderer Body;
	public ModelRenderer skirtF;
	public ModelRenderer SkirtR;
	public ModelRenderer SkirtR2;
	public ModelRenderer SkirtB;
	public ModelRenderer mouth;
}
