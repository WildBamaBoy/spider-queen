package sq.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Defines the Octopus's in-game model.
 */
public class ModelOctopus extends ModelBase
{
	private final ModelRenderer body;
	private final ModelRenderer skirtF;
	private final ModelRenderer skirtR;
	private final ModelRenderer skirtL;
	private final ModelRenderer skirtB;
	private final ModelRenderer mouth;

	public ModelOctopus()
	{
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this, 0, 16);
		body.addBox(-4F, -8F, -4F, 8, 8, 8);
		body.setRotationPoint(0F, 18F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		skirtF = new ModelRenderer(this, 0, 0);
		skirtF.addBox(-4F, 0F, 0F, 8, 8, 0);
		skirtF.setRotationPoint(0F, 18F, -2F);
		skirtF.setTextureSize(64, 32);
		skirtF.mirror = true;
		setRotation(skirtF, -0.3490659F, 0F, 0F);
		skirtR = new ModelRenderer(this, 0, 0);
		skirtR.addBox(-4F, 0F, 0F, 8, 8, 0);
		skirtR.setRotationPoint(-2F, 18F, 0F);
		skirtR.setTextureSize(64, 32);
		skirtR.mirror = true;
		setRotation(skirtR, -0.3490659F, 1.570796F, 0F);
		skirtL = new ModelRenderer(this, 0, 0);
		skirtL.addBox(-4F, 0F, 0F, 8, 8, 0);
		skirtL.setRotationPoint(2F, 18F, 0F);
		skirtL.setTextureSize(64, 32);
		skirtL.mirror = true;
		setRotation(skirtL, -0.3490659F, -1.570796F, 0F);
		skirtB = new ModelRenderer(this, 0, 0);
		skirtB.addBox(-4F, 0F, 0F, 8, 8, 0);
		skirtB.setRotationPoint(0F, 18F, 2F);
		skirtB.setTextureSize(64, 32);
		skirtB.mirror = true;
		setRotation(skirtB, -0.3490659F, 3.141593F, 0F);
		mouth = new ModelRenderer(this, 32, 24);
		mouth.addBox(-2F, -4F, -6F, 4, 4, 4);
		mouth.setRotationPoint(0F, 18F, 0F);
		mouth.setTextureSize(64, 32);
		mouth.mirror = true;
		setRotation(mouth, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		body.render(f5);
		skirtF.render(f5);
		skirtR.render(f5);
		skirtL.render(f5);
		skirtB.render(f5);
		mouth.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
	    body.rotateAngleY = f3 / 57.29578F;
		mouth.rotateAngleY = f3 / 57.29578F; mouth.rotateAngleX = f4 / 57.29578F;
	}

}
